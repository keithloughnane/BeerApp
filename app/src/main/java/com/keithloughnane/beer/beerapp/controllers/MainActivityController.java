package com.keithloughnane.beer.beerapp.controllers;

import com.keithloughnane.beer.beerapp.dataAccess.local.DataAccess;
import com.keithloughnane.beer.beerapp.dependencyInjection.AppComponent;
import com.keithloughnane.beer.beerapp.models.BeerModel;

import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;


/**
 * Created by keith.loughnane@gmail.com on 04/01/2018.
 */

public class MainActivityController extends ControllerWithAdapter {
    private static final DataAccess.SelectType DEFAULT_SELECT_TYPE = DataAccess.SelectType.ALL;

    public final PublishSubject<Object> favoriteClick = PublishSubject.create();

    public MainActivityController(BeerModel beerMode) {
        super(beerMode);
    }

    @Override
    protected Disposable setUpSubscriptions() {
        Disposable superDisposable = super.setUpSubscriptions();
        selectMode.onNext(DEFAULT_SELECT_TYPE);
        return superDisposable;
    }

    @Override
    public void inject(AppComponent component) {
        component.inject(this);
    }
}