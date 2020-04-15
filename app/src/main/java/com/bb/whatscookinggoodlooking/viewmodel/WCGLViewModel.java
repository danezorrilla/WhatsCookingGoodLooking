package com.bb.whatscookinggoodlooking.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bb.whatscookinggoodlooking.firebase.FavoriteRecipe;
import com.bb.whatscookinggoodlooking.model.bigbang.Recipe;
import com.bb.whatscookinggoodlooking.network.WCGLRetrofit;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class WCGLViewModel extends AndroidViewModel {

    private WCGLRetrofit wcglRetrofit;

    private DatabaseReference firebaseRef;

    List<FavoriteRecipe> favoriteRecipeList = new ArrayList<>();

    private PublishSubject<List<FavoriteRecipe>> favoriteRecipeObservable = PublishSubject.create();


    public WCGLViewModel(@NonNull Application application) {
        super(application);
        wcglRetrofit = new WCGLRetrofit();

        firebaseRef = FirebaseDatabase.getInstance().getReference().child("favorite_recipes");
    }

    public Observable<Recipe> getRecipeListRx(String name){
        return wcglRetrofit
                .getRecipeList(name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public void addNewFavoriteRecipe(FavoriteRecipe favoriteRecipe){
        String databaseKey = firebaseRef.push().getKey();
        if(databaseKey != null)
            firebaseRef.child(databaseKey).setValue(favoriteRecipe);
    }

    public Observable<List<FavoriteRecipe>> getFavoriteRecipeList(){

        firebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    FavoriteRecipe currentFR = ds.getValue(FavoriteRecipe.class);
                    favoriteRecipeList.add(currentFR);
                }
                favoriteRecipeObservable.onNext(favoriteRecipeList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        return favoriteRecipeObservable;

    }
}
