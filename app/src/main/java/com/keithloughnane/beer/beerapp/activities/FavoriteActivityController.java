package com.keithloughnane.beer.beerapp.activities;

import android.util.Log;

import com.keithloughnane.beer.beerapp.AppComponent;
import com.keithloughnane.beer.beerapp.ControllerWithAdapter;
import com.keithloughnane.beer.beerapp.data.Beer;
import com.keithloughnane.beer.beerapp.dataAccess.DataAccess;

import org.reactivestreams.Subscription;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by user on 06/01/2018.
 */

public class FavoriteActivityController extends ControllerWithAdapter {

    @Inject
    DataAccess dataAccess; //TODO KL: Move to super

    public FavoriteActivityController(BeerModel model) {
        super(model);
    }

    @Override
    protected Disposable setUpSubscriptions() {
        model.view.downloadStarted();
        dataAccess.getBeer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<Beer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("KLTest", "onSubscribe : " + d);
                    }

                    @Override
                    public void onNext(ArrayList<Beer> beers) { //TODO KL: Should I be using this
                        Log.d("KLTest", ":" + beers);
                        model.beers = beers;
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

        return Disposables.empty();
    }

    @Override
    public void inject(AppComponent component) {
        component.inject(this);
    }
}
