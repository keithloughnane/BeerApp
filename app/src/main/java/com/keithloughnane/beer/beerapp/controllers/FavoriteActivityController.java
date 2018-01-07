package com.keithloughnane.beer.beerapp.controllers;

import com.keithloughnane.beer.beerapp.dependencyInjection.AppComponent;
import com.keithloughnane.beer.beerapp.models.BeerModel;
import com.keithloughnane.beer.beerapp.dataAccess.local.DataAccess;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by user on 06/01/2018.
 */

public class FavoriteActivityController extends ControllerWithAdapter {




    public FavoriteActivityController(BeerModel model) {
        super(model);
    }

    @Override
    protected Disposable setUpSubscriptions() {
        super.setUpSubscriptions();



        /*
        dataAccess.getAllBeer()
                .subscribeOn(Schedulers.io())
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
*/

        selectMode.onNext(DataAccess.SelectType.FAV);
        networkObserver.sub.onNext(true);

        return Disposables.empty();
    }

    @Override
    public void inject(AppComponent component) {
        component.inject(this);
    }
}
