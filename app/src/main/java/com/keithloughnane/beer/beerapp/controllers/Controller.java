package com.keithloughnane.beer.beerapp.controllers;


import com.keithloughnane.beer.beerapp.dependencyInjection.AppComponent;
import com.keithloughnane.beer.beerapp.models.BeerModel;

import io.reactivex.disposables.Disposable;

/**
 * Created by keith.loughnane@gmail.com on 04/01/2018.
 */

public abstract class Controller {

    final BeerModel model;
    private Disposable subscription;

    Controller(BeerModel beerModel){
        model = beerModel;
    }

    public void setUp() {
        subscription = setUpSubscriptions();
    }

    public void dispose() {
        subscription.dispose();
    }

    protected abstract Disposable setUpSubscriptions();
    public abstract void inject(AppComponent component);
}
