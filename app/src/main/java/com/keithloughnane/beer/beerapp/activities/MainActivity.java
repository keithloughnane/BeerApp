package com.keithloughnane.beer.beerapp.activities;

import android.os.Bundle;

import com.keithloughnane.beer.beerapp.AppComponent;
import com.keithloughnane.beer.beerapp.DaggerAppComponent;
import com.keithloughnane.beer.beerapp.R;
import com.keithloughnane.beer.beerapp.data.Beer;

import javax.inject.Inject;

public class MainActivity extends BaseActivityWithAdapter<BeerModel, MainActivityController> {
    private AppComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = DaggerAppComponent.builder().build();
        component.inject(this);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected BeerModel createModel() {
        return null;
    }

    @Override
    protected Controller createController() {
        return null;
    }
}
