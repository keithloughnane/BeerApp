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

    //@Inject
    //NetworkObserver networkObserver;

    public PublishSubject<Object> favouriteClick = PublishSubject.create();
    //PublishSubject<DataAccess.SelectType> selectMode = PublishSubject.create();

    public MainActivityController(BeerModel beerMode) {
        super(beerMode);
    }

    @Override
    protected Disposable setUpSubscriptions() {
        super.setUpSubscriptions();

        //model.view.downloadStarted();
        //Log.e("KLTest", "setUpSubscriptions");

        /*
        dataAccess.sub(selectMode)
                //.subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Beer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("KLTest", "onSubscribe : " + d);

                    }

                    @Override
                    public void onNext(List<Beer> beers) { //TODO KL: Should I be using this
                        Log.d("KLTest", ":" + beers);
                        model.beers.clear();
                        model.beers.addAll(beers);
                        model.view.downloadComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("KLTest", "onError: " + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Log.e("KLTest", "onNext");

        selectMode.onNext(DataAccess.SelectType.ALL);
        networkObserver.sub.onNext(true);
*/

        favouriteClick
                //.subscribe();
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object view) {
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