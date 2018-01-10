package com.keithloughnane.beer.beerapp.view;

import android.os.Bundle;

import com.keithloughnane.beer.beerapp.BeerApplication;
import com.keithloughnane.beer.beerapp.R;
import com.keithloughnane.beer.beerapp.controllers.FavoriteActivityController;

public class FavoriteActivity extends BaseListViewActivity<FavoriteActivityController> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_favorite);
        ((BeerApplication) getApplication()).component.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FavoriteActivityController createController() {
        return new FavoriteActivityController(model);
    }
}
