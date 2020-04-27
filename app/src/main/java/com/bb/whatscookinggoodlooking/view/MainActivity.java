package com.bb.whatscookinggoodlooking.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.bb.whatscookinggoodlooking.R;
import com.bb.whatscookinggoodlooking.adapter.WCGLAdapter;
import com.bb.whatscookinggoodlooking.firebase.FavoriteRecipe;
import com.bb.whatscookinggoodlooking.model.bigbang.Recipe;
import com.bb.whatscookinggoodlooking.viewmodel.WCGLViewModel;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity implements WCGLAdapter.FavoriteRecipeInterface {

    private WCGLViewModel wcglViewModel;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @BindView(R.id.hot_chef)
    ImageView imageView;

    @BindView(R.id.recipe_name)
    EditText searchRecipeName;

    @BindView(R.id.searched_recipe_list)
    RecyclerView searchedRecipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        wcglViewModel = ViewModelProviders.of(this).get(WCGLViewModel.class);

        loadImage();
    }

    private void loadImage(){
        Glide.with(this).load(R.drawable.sexy_chef).into(imageView);
    }

    @OnClick(R.id.search_for_recipe)
    public void searchForRecipe(){
        String name = searchRecipeName.getText().toString();
        Log.d("TAG_X", "Name: " + name);

        compositeDisposable.add(wcglViewModel.getRecipeListRx(name).subscribe(recipe ->
        {displayRecipeList(recipe);}, throwable -> {Log.d("TAG_X",
                "Error: " + throwable.getLocalizedMessage());}
                ));

        searchRecipeName.setText("");
    }

    private void displayRecipeList(Recipe recipe){
        for (int i = 0; i < recipe.getHits().size(); i++){
            Log.d("TAG_XX", "Recipe Name: " + recipe.getHits().get(i).getRecipe().getLabel());
            searchedRecipeList.setLayoutManager(new LinearLayoutManager(this));
            searchedRecipeList.setAdapter(new WCGLAdapter(recipe, this));

        }
    }

    @OnClick(R.id.view_favorite_recipes)
    public void viewFavoriteRecipes(){
        Intent intent = new Intent(this, FavoriteRecipesActivity.class);
        startActivity(intent);
    }

    @Override
    public void addToFavorite(FavoriteRecipe favoriteRecipe) {
        String favoriteRecipeName = favoriteRecipe.getFavoriteRecipeName();
        String favoriteRecipeImage = favoriteRecipe.getFavoriteRecipeImage();
        String favoriteRecipeIngredients = favoriteRecipe.getFavoriteRecipeIngredients();
        String favoriteRecipeUrl = favoriteRecipe.getFavoriteRecipeURL();

        FavoriteRecipe newFavoriteRecipe = new FavoriteRecipe(favoriteRecipeName, favoriteRecipeImage,
                favoriteRecipeIngredients, favoriteRecipeUrl);
        wcglViewModel.addNewFavoriteRecipe(newFavoriteRecipe);
    }

    @Override
    public void getRecipeURL(String recipeURL) {
        System.out.println(recipeURL);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipeURL));
        startActivity(browserIntent);
    }
}
