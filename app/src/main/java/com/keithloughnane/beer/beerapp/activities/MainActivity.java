package com.keithloughnane.beer.beerapp.activities;

import android.os.Bundle;

import com.keithloughnane.beer.beerapp.AppComponent;
import com.keithloughnane.beer.beerapp.BeerApplication;
import com.keithloughnane.beer.beerapp.R;

public class MainActivity extends BaseActivityWithAdapter<BeerModel, MainActivityController> {
    //private AppComponent component;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        ((BeerApplication) getApplication()).component.inject(this);
        //component.inject(this);
    }

    @Override
    protected BeerModel createModel() {

        BeerModel model = new BeerModel();
        model.view = this;
        return model;
    }

    @Override
    protected Controller createController() {
        return new MainActivityController(model);
    }
}
