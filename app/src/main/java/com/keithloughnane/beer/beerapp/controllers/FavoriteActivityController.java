package com.keithloughnane.beer.beerapp.controllers;

import com.keithloughnane.beer.beerapp.dependencyInjection.AppComponent;
import com.keithloughnane.beer.beerapp.models.BeerModel;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by user on 06/01/2018.
 */

public class FavoriteActivityController extends ControllerWithAdapter {

    public FavoriteActivityController(BeerModel model) {
        super(model);
    }

    @Override
    protected Disposable setUpSubscriptions() {
        super.setUpSubscriptions();

        //selectMode.onNext(DataAccess.SelectType.FAV);
        //networkObserver.sub.onNext(true);

        return Disposables.empty();
    }

    @Override
    public void inject(AppComponent component) {
        component.inject(this);
    }
}
