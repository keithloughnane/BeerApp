package com.keithloughnane.beer.beerapp.activities;

import com.keithloughnane.beer.beerapp.AppComponent;
import com.keithloughnane.beer.beerapp.data.Beer;
import com.keithloughnane.beer.beerapp.dataAccess.DataAccess;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by user on 04/01/2018.
 */

public class MainActivityController extends Controller {
    @Inject
    DataAccess dataAccess;

    MainActivityController(BeerModel beerMode) {
        super(beerMode);
    }

    @Override
    protected Disposable setUpSubscriptions() {
        return dataAccess.getBeer()
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {

                    }

                });
    }

    @Override
    public void inject(AppComponent component) {
        component.inject(this);
    }
}