package com.keithloughnane.beer.beerapp.activities;


import com.keithloughnane.beer.beerapp.AppComponent;

import io.reactivex.disposables.Disposable;

/**
 * Created by user on 04/01/2018.
 */

abstract class Controller {

    BeerModel model;
    Disposable subscription;

    Controller(BeerModel beerModel){
        model = beerModel;
    }

    void SetUp() {
        subscription = setUpSubscriptions();
    }

    protected abstract Disposable setUpSubscriptions();
    public abstract void inject(AppComponent component);
}
