package com.keithloughnane.beer.beerapp.controllers;

import com.keithloughnane.beer.beerapp.dataAccess.local.DataAccess;
import com.keithloughnane.beer.beerapp.dependencyInjection.AppComponent;
import com.keithloughnane.beer.beerapp.models.BeerModel;

import io.reactivex.disposables.Disposable;

/**
 * Created by keith.loughnane@gmail.com on 06/01/2018.
 */

public class FavoriteActivityController extends ControllerWithAdapter {

    public FavoriteActivityController(BeerModel model) {
        super(model);
    }

    @Override
    public void inject(AppComponent component) {
        component.inject(this);
    }

    @Override
    protected Disposable setUpSubscriptions() {
        Disposable superDisposable = super.setUpSubscriptions();
        selectMode.onNext(DataAccess.SelectType.FAV);
        return superDisposable;
    }
}
