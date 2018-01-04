package com.keithloughnane.beer.beerapp.activities;

import android.os.Bundle;

import com.keithloughnane.beer.beerapp.AppComponent;
import com.keithloughnane.beer.beerapp.R;

public class MainActivity extends BaseActivityWithAdapter<BeerModel, MainActivityController> {
    private AppComponent component;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        component.inject(this);
    }

    @Override
    protected BeerModel createModel() {
        return new BeerModel();
    }

    @Override
    protected Controller createController() {
        return new MainActivityController(model);
    }
}
