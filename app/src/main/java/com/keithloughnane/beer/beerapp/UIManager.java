package com.keithloughnane.beer.beerapp;

import android.content.Context;
import android.content.Intent;

import com.keithloughnane.beer.beerapp.data.Beer;
import com.keithloughnane.beer.beerapp.view.BeerProfileActivity;
import com.keithloughnane.beer.beerapp.view.FavoriteActivity;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by keith.loughnane@gmail.com on 09/01/2018.
 */

public class UIManager {
    private final Context context;

    public UIManager(Context context) {
        this.context = context;
    }

    public void showFavorites() {
        Intent intent = new Intent(context, FavoriteActivity.class);
        context.startActivity(intent);
    }

    public void showBeerProfile(Beer beer) {
        Intent intent = new Intent(context, BeerProfileActivity.class);
        intent.putExtra(BeerProfileActivity.BEER_PARAM, beer);
        context.startActivity(intent);
    }
}
