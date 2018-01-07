package com.keithloughnane.beer.beerapp.controllers;


import com.keithloughnane.beer.beerapp.dependencyInjection.AppComponent;
import com.keithloughnane.beer.beerapp.models.BeerModel;

import io.reactivex.disposables.Disposable;

/**
 * Created by keith.loughnane@gmail.com on 04/01/2018.
 */

public abstract class Controller {

    protected BeerModel model;
    Disposable subscription;

    public Controller(BeerModel beerModel){
        model = beerModel;
    }

    public void SetUp() {
        subscription = setUpSubscriptions();
    }

    protected abstract Disposable setUpSubscriptions();
    public abstract void inject(AppComponent component);
}
