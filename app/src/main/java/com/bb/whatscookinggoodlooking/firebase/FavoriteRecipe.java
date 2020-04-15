package com.bb.whatscookinggoodlooking.firebase;

import java.util.List;

public class FavoriteRecipe {

    private String favoriteRecipeName;

    private String favoriteRecipeImage;

    private String favoriteRecipeIngredients;

    private String favoriteRecipeURL;

    public FavoriteRecipe() {
    }

    public FavoriteRecipe(String favoriteRecipeName, String favoriteRecipeImage, String favoriteRecipeIngredients, String favoriteRecipeURL) {
        this.favoriteRecipeName = favoriteRecipeName;
        this.favoriteRecipeImage = favoriteRecipeImage;
        this.favoriteRecipeIngredients = favoriteRecipeIngredients;
        this.favoriteRecipeURL = favoriteRecipeURL;
    }

    public String getFavoriteRecipeName() {
        return favoriteRecipeName;
    }

    public void setFavoriteRecipeName(String favoriteRecipeName) {
        this.favoriteRecipeName = favoriteRecipeName;
    }

    public String getFavoriteRecipeImage() {
        return favoriteRecipeImage;
    }

    public void setFavoriteRecipeImage(String favoriteRecipeImage) {
        this.favoriteRecipeImage = favoriteRecipeImage;
    }

    public String getFavoriteRecipeIngredients() {
        return favoriteRecipeIngredients;
    }

    public void setFavoriteRecipeIngredients(String favoriteRecipeIngredients) {
        this.favoriteRecipeIngredients = favoriteRecipeIngredients;
    }

    public String getFavoriteRecipeURL() {
        return favoriteRecipeURL;
    }

    public void setFavoriteRecipeURL(String favoriteRecipeURL) {
        this.favoriteRecipeURL = favoriteRecipeURL;
    }
}
