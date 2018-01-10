package com.keithloughnane.beer.beerapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.keithloughnane.beer.beerapp.data.Beer;
import com.keithloughnane.beer.beerapp.view.BeerProfileActivity;
import com.keithloughnane.beer.beerapp.view.FavoriteActivity;

/**
 * Created by keith.loughnane@gmail.com on 09/01/2018.
 */

public class UIManager {
    private Context activityContext;

    public void setActivity(Activity activity) {
        this.activityContext = activity;
    }

    public void showFavorites() {
        if (activityContext != null) {
            Intent intent = new Intent(activityContext, FavoriteActivity.class);
            activityContext.startActivity(intent);
        }
    }

    public void showBeerProfile(Beer beer) {
        if (activityContext != null) {
            Intent intent = new Intent(activityContext, BeerProfileActivity.class);
            intent.putExtra(BeerProfileActivity.BEER_PARAM, beer);
            activityContext.startActivity(intent);
        }
    }
}
