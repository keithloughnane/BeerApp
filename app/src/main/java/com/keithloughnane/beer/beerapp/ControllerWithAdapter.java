package com.keithloughnane.beer.beerapp;

import com.keithloughnane.beer.beerapp.activities.BeerModel;
import com.keithloughnane.beer.beerapp.activities.Controller;
import com.keithloughnane.beer.beerapp.data.Beer;

import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by user on 06/01/2018.
 */

public abstract class ControllerWithAdapter extends Controller {
    public PublishSubject<Beer> holderClick = PublishSubject.create();
    public PublishSubject<Object> favoriteClick = PublishSubject.create();

    public ControllerWithAdapter(BeerModel beerModel) {
        super(beerModel);

        holderClick
                //.subscribeOn()
        .subscribe(new Consumer<Beer>() {
            @Override
            public void accept(Beer beer) throws Exception {

            }
        });

        favoriteClick.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                 //TODO KL: Update here
            }
        });
    } //TODO KL: Rename
}
