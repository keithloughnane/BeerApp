package com.keithloughnane.beer.beerapp.activities;

import android.util.Log;

import com.keithloughnane.beer.beerapp.AppComponent;
import com.keithloughnane.beer.beerapp.ControllerWithAdapter;
import com.keithloughnane.beer.beerapp.NetworkObserver;
import com.keithloughnane.beer.beerapp.data.Beer;
import com.keithloughnane.beer.beerapp.dataAccess.DataAccess;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by user on 04/01/2018.
 */

public class MainActivityController extends ControllerWithAdapter {
    @Inject
    DataAccess dataAccess;

    @Inject
    NetworkObserver networkObserver;

    public PublishSubject<Object> favouriteClick = PublishSubject.create();
    PublishSubject<Integer> selectMode = PublishSubject.create();

    MainActivityController(BeerModel beerMode) {
        super(beerMode);
    }

    @Override
    protected Disposable setUpSubscriptions() {
        model.view.downloadStarted();
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

        selectMode.onNext(1);
        networkObserver.sub.onNext(true);


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

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return Disposables.empty();
    }

    @Override
    public void inject(AppComponent component) {
        component.inject(this);
    }
}