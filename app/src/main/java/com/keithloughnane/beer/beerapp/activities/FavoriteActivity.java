package com.keithloughnane.beer.beerapp.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.keithloughnane.beer.beerapp.R;

public class FavoriteActivity extends BaseActivityWithAdapter {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_favorite);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected BeerModel createModel() {
        BeerModel model = new BeerModel();
        model.view = this;
        return model;
    }

    @Override
    protected Controller createController() {
        return new FavoriteActivityController(model);
    }
}
