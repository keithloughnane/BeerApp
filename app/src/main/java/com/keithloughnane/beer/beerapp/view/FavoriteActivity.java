package com.keithloughnane.beer.beerapp.view;

import android.os.Bundle;

import com.keithloughnane.beer.beerapp.R;
import com.keithloughnane.beer.beerapp.controllers.FavoriteActivityController;
import com.keithloughnane.beer.beerapp.models.BeerModel;

public class FavoriteActivity extends BaseActivityWithBeerAdapter<BeerModel, FavoriteActivityController> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_favorite);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected BeerModel createModel() { //TODO KL: Move to Super. Generics should take care of
        BeerModel model = new BeerModel();
        model.view = this;
        return model;
    }

    @Override
    protected FavoriteActivityController createController() {
        return new FavoriteActivityController(model);
    }
}
