package com.keithloughnane.beer.beerapp.controllers;

import android.util.Log;

import com.keithloughnane.beer.beerapp.dependencyInjection.AppComponent;
import com.keithloughnane.beer.beerapp.models.BeerModel;
import com.keithloughnane.beer.beerapp.dataAccess.local.DataAccess;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.subjects.PublishSubject;


/**
 * Created by keith.loughnane@gmail.com on 04/01/2018.
 */

public class MainActivityController extends ControllerWithAdapter {
    @Inject
    DataAccess dataAccess;

    public PublishSubject<Object> favouriteClick = PublishSubject.create();

    public MainActivityController(BeerModel beerMode) {
        super(beerMode);
    }

    @Override
    protected Disposable setUpSubscriptions() {
        super.setUpSubscriptions();

        favouriteClick
                //.subscribe();
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object view) { //TODO KL: What is this for?
                        //Intent intent = new Intent(this, FavoriteActivity.class);
                        //intent.putExtra(EXTRA_MESSAGE, message);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("KLTest", "onError: " + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        selectMode.onNext(DataAccess.SelectType.ALL);
        networkObserver.sub.onNext(true);

        return Disposables.empty();
    }

    @Override
    public void inject(AppComponent component) {
        component.inject(this);
    }
}