package com.keithloughnane.beer.beerapp;

import android.util.Log;

import com.keithloughnane.beer.beerapp.activities.BeerModel;
import com.keithloughnane.beer.beerapp.activities.Controller;
import com.keithloughnane.beer.beerapp.data.Beer;
import com.keithloughnane.beer.beerapp.dataAccess.DataAccess;

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
    public PublishSubject<Beer> holderClick = PublishSubject.create();
    public PublishSubject<Beer> favoriteClick = PublishSubject.create();
    public BehaviorSubject<DataAccess.SelectType> selectMode = BehaviorSubject.create().create();

    @Inject
    public
    DataAccess dataAccess; //TODO KL: Move to super

    @Inject
    protected
    NetworkObserver networkObserver;

    public ControllerWithAdapter(BeerModel beerModel) {
        super(beerModel);

        holderClick
                //.subscribeOn()
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
                //.subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Beer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("KLTest", "onSubscribe : " + d);
                    }

                    @Override
                    public void onNext(List<Beer> beers) { //TODO KL: Should I be using this
                        Log.d("KLTest", "CONTROLLER:" + beers);
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

        return Disposables.empty();
    }
}
