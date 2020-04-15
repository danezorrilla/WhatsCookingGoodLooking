
package com.bb.whatscookinggoodlooking.model.bigbang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hit {

    @SerializedName("recipe")
    @Expose
    private Recipe_ recipe;
    @SerializedName("bookmarked")
    @Expose
    private Boolean bookmarked;
    @SerializedName("bought")
    @Expose
    private Boolean bought;

    public Recipe_ getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe_ recipe) {
        this.recipe = recipe;
    }

    public Boolean getBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(Boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

    public Boolean getBought() {
        return bought;
    }

    public void setBought(Boolean bought) {
        this.bought = bought;
    }

}
