package com.bb.whatscookinggoodlooking.network;

import com.bb.whatscookinggoodlooking.model.bigbang.Recipe;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WCGLService {

    @GET("/search")
    public Observable<Recipe> getRecipeList(@Query("q") String query, @Query("app_id") String app_id,
                                            @Query("app_key") String app_key);
}
