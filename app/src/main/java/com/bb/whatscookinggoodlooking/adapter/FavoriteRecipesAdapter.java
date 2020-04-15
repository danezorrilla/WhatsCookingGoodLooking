package com.bb.whatscookinggoodlooking.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bb.whatscookinggoodlooking.R;
import com.bb.whatscookinggoodlooking.firebase.FavoriteRecipe;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteRecipesAdapter extends RecyclerView.Adapter<FavoriteRecipesAdapter.FavoriteRecipesViewHolder> {

    private List<FavoriteRecipe> favoriteRecipeList;

    public FavoriteRecipesAdapter(List<FavoriteRecipe> favoriteRecipeList) {
        this.favoriteRecipeList = favoriteRecipeList;
    }

    @NonNull
    @Override
    public FavoriteRecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_recipe_list, parent, false);

        return new FavoriteRecipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteRecipesViewHolder holder, int position) {

        Glide.with(holder.itemView.getContext())
                .load(favoriteRecipeList.get(position).getFavoriteRecipeImage())
                .into(holder.favoriteRecipeImage);

        holder.favoriteRecipeName.setText(favoriteRecipeList.get(position).getFavoriteRecipeName());
        holder.favoriteRecipeIngredients.setText(favoriteRecipeList.get(position).getFavoriteRecipeIngredients());
        holder.favoriteRecipeUrl.setText(favoriteRecipeList.get(position).getFavoriteRecipeURL());

    }

    @Override
    public int getItemCount() {
        return favoriteRecipeList.size();
    }

    public class FavoriteRecipesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.favorite_recipe_image)
        ImageView favoriteRecipeImage;

        @BindView(R.id.favorite_recipe_name)
        TextView favoriteRecipeName;

        @BindView(R.id.favorite_recipe_ingredients)
        TextView favoriteRecipeIngredients;

        @BindView(R.id.favorite_recipe_url)
        TextView favoriteRecipeUrl;

        public FavoriteRecipesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
