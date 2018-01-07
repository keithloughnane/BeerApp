package com.keithloughnane.beer.beerapp.controllers;

import android.util.Log;

import com.keithloughnane.beer.beerapp.util.NetworkObserver;
import com.keithloughnane.beer.beerapp.models.BeerModel;
import com.keithloughnane.beer.beerapp.data.Beer;
import com.keithloughnane.beer.beerapp.dataAccess.local.DataAccess;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by keith.loughnane@gmail.com on 06/01/2018.
 */

public abstract class ControllerWithAdapter extends Controller {
    public final PublishSubject<Beer> holderClick = PublishSubject.create();
    public final PublishSubject<Beer> favoriteClick = PublishSubject.create();
    public final BehaviorSubject<DataAccess.SelectType> selectMode = BehaviorSubject.create();

    @Inject
    public
    DataAccess dataAccess; //TODO KL: Move to super

    @Inject
    NetworkObserver networkObserver;

    ControllerWithAdapter(BeerModel beerModel) {
        super(beerModel);

        holderClick //TODO KL: What is this for?
        .subscribe(new Consumer<Beer>() {
            @Override
            public void accept(Beer beer) throws Exception {

            }
        });

        favoriteClick.subscribe(new Consumer<Beer>() {
            @Override
            public void accept(Beer beer) throws Exception {
                 dataAccess.update(beer);
            }
        });
    } //TODO KL: Rename

    @Override
    protected Disposable setUpSubscriptions() {
        model.view.downloadStarted();
        dataAccess.sub(selectMode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Beer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<Beer> beers) { //TODO KL: Should I be using this
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

        selectMode.onNext(DataAccess.SelectType.ALL);
        networkObserver.sub.onNext(true);

        return Disposables.empty();
    }
}
