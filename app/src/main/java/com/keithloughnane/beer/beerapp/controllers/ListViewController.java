package com.keithloughnane.beer.beerapp.controllers;

import com.keithloughnane.beer.beerapp.UIManager;
import com.keithloughnane.beer.beerapp.data.Beer;
import com.keithloughnane.beer.beerapp.dataAccess.local.DataAccess;
import com.keithloughnane.beer.beerapp.models.BeerModel;
import com.keithloughnane.beer.beerapp.util.NetworkObserver;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by keith.loughnane@gmail.com on 06/01/2018.
 */

public abstract class ListViewController extends Controller {
    public final PublishSubject<Beer> holderClick = PublishSubject.create();
    public final PublishSubject<Beer> favoriteSelect = PublishSubject.create();
    public final PublishSubject<Object> favoriteNavigate = PublishSubject.create();
    public final BehaviorSubject<DataAccess.SelectType> selectMode = BehaviorSubject.create();

    @Inject
    public DataAccess dataAccess;

    @Inject
    NetworkObserver networkObserver;

    @Inject
    UIManager uiManager;

    ListViewController(BeerModel beerModel) {
        super(beerModel);
    }

    @Override
    protected Disposable setUpSubscriptions() {
        model.view.downloadStarted();
        return new CompositeDisposable(
                setUpDataSubscriptions(),
                holderClick
                        .subscribe(new Consumer<Beer>() {
                            @Override
                            public void accept(Beer beer) throws Exception {
                                uiManager.showBeerProfile(beer);
                            }
                        }),
                favoriteSelect.subscribe(new Consumer<Beer>() {
                    @Override
                    public void accept(Beer beer) throws Exception {
                        dataAccess.update(beer);
                    }
                }),
                favoriteNavigate
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object notUser) throws Exception {
                                uiManager.showFavorites();
                            }
                        }));
    }

    private Disposable setUpDataSubscriptions() {
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
