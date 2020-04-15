package com.bb.whatscookinggoodlooking.network;

import com.bb.whatscookinggoodlooking.model.bigbang.Recipe;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WCGLRetrofit {

    private WCGLService wcglService;

    public WCGLRetrofit (){
        wcglService = createService(getRetrofit());
    }

    private Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("https://api.edamam.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private WCGLService createService(Retrofit retrofit){
        return retrofit.create(WCGLService.class);
    }

    public Observable<Recipe> getRecipeList(String name){
        return wcglService.getRecipeList(name, "613143a4", "34599102607c31a594356d0c2901ad07");
    }
}
