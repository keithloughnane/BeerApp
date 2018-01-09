package com.keithloughnane.beer.beerapp.controllers;

import com.keithloughnane.beer.beerapp.data.Beer;
import com.keithloughnane.beer.beerapp.dataAccess.local.DataAccess;
import com.keithloughnane.beer.beerapp.models.BeerModel;
import com.keithloughnane.beer.beerapp.util.NetworkObserver;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by keith.loughnane@gmail.com on 06/01/2018.
 */

public abstract class ListViewController extends Controller {
    public final PublishSubject<Beer> holderClick = PublishSubject.create();
    public final PublishSubject<Beer> favoriteClick = PublishSubject.create();
    public final BehaviorSubject<DataAccess.SelectType> selectMode = BehaviorSubject.create();

    @Inject
    public
    DataAccess dataAccess;

    @Inject
    NetworkObserver networkObserver;

    ListViewController(BeerModel beerModel) {
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
    }

    @Override
    protected Disposable setUpSubscriptions() {
        model.view.downloadStarted();
        return dataAccess.sub(selectMode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Beer>>() {
                    @Override
                    public void accept(List<Beer> beers) throws Exception {
                        model.beers.clear();
                        model.beers.addAll(beers);
                        model.view.downloadComplete();
                    }
                });
    }
}
