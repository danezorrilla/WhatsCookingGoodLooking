package com.bb.whatscookinggoodlooking.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bb.whatscookinggoodlooking.R;
import com.bb.whatscookinggoodlooking.firebase.FavoriteRecipe;
import com.bb.whatscookinggoodlooking.model.bigbang.Recipe;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WCGLAdapter extends RecyclerView.Adapter<WCGLAdapter.WCGLViewHolder> {

    public interface FavoriteRecipeInterface{
        void addToFavorite(FavoriteRecipe favoriteRecipe);
        void getRecipeURL(String recipeURL);
    }

    private Recipe recipe;
    private FavoriteRecipeInterface favoriteRecipeInterface;

    public WCGLAdapter(Recipe recipe, FavoriteRecipeInterface favoriteRecipeInterface) {
        this.recipe = recipe;
        this.favoriteRecipeInterface = favoriteRecipeInterface;
    }

    @NonNull
    @Override
    public WCGLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.searched_recipe_list, parent, false);

        return new WCGLViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WCGLViewHolder holder, int position) {

        Glide.with(holder.itemView.getContext())
                .load(recipe.getHits().get(position).getRecipe().getImage())
                .into(holder.searchedRecipeImage);

        holder.searchedRecipeName.setText(recipe.getHits().get(position).getRecipe().getLabel());
        holder.searchedRecipeIngredients.setText(recipe.getHits().get(position).getRecipe()
                .getIngredientLines().toString());
        holder.searchedRecipeUrl.setText(recipe.getHits().get(position).getRecipe().getUrl());

        holder.searchedRecipeUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = holder.searchedRecipeUrl.getText().toString();

                favoriteRecipeInterface.getRecipeURL(url);
            }
        });

        holder.addToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String favoriteRecipeImage = recipe.getHits().get(position).getRecipe().getImage();
                String favoriteRecipeName = holder.searchedRecipeName.getText().toString();
                String favoriteRecipeIngredients = holder.searchedRecipeIngredients.getText().toString();
                String favoriteRecipeUrl = holder.searchedRecipeUrl.getText().toString();
                FavoriteRecipe favoriteRecipe = new FavoriteRecipe(favoriteRecipeName, favoriteRecipeImage,
                        favoriteRecipeIngredients, favoriteRecipeUrl);
                favoriteRecipeInterface.addToFavorite(favoriteRecipe);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recipe.getHits().size();
    }

    public class WCGLViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.searched_recipe_image)
        ImageView searchedRecipeImage;

        @BindView(R.id.searched_recipe_name)
        TextView searchedRecipeName;

        @BindView(R.id.searched_recipe_ingredients)
        TextView searchedRecipeIngredients;

        @BindView(R.id.searched_recipe_url)
        TextView searchedRecipeUrl;

        @BindView(R.id.add_to_favorites)
        Button addToFavorite;

        public WCGLViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
