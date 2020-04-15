package com.bb.whatscookinggoodlooking.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.bb.whatscookinggoodlooking.R;
import com.bb.whatscookinggoodlooking.adapter.FavoriteRecipesAdapter;
import com.bb.whatscookinggoodlooking.firebase.FavoriteRecipe;
import com.bb.whatscookinggoodlooking.viewmodel.WCGLViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;

public class FavoriteRecipesActivity extends AppCompatActivity {

    private WCGLViewModel wcglViewModel;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @BindView(R.id.favorite_recipes)
    RecyclerView favoriteRecipeRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_recipes);
        ButterKnife.bind(this);

        wcglViewModel = ViewModelProviders.of(this).get(WCGLViewModel.class);

        compositeDisposable.add(wcglViewModel.getFavoriteRecipeList().subscribe(favoriteRecipes ->
        {displayFavoriteRecipes(favoriteRecipes);}, throwable -> {
                    Log.d("TAG_XX", "Error: " + throwable.getLocalizedMessage());}
        ));
    }

    private void displayFavoriteRecipes(List<FavoriteRecipe> favoriteRecipeList){
        for(int i = 0; i< favoriteRecipeList.size(); i++){
            favoriteRecipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            favoriteRecipeRecyclerView.setAdapter(new FavoriteRecipesAdapter(favoriteRecipeList));
        }
    }

    @OnClick(R.id.return_to_main)
    public void returnToMain(){
        finish();
    }
}
