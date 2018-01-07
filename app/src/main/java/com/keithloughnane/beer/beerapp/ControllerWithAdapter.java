package com.keithloughnane.beer.beerapp;

import com.keithloughnane.beer.beerapp.activities.BeerModel;
import com.keithloughnane.beer.beerapp.activities.Controller;
import com.keithloughnane.beer.beerapp.data.Beer;
import com.keithloughnane.beer.beerapp.dataAccess.DataAccess;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by user on 06/01/2018.
 */

public abstract class ControllerWithAdapter extends Controller {
    public PublishSubject<Beer> holderClick = PublishSubject.create();
    public PublishSubject<Beer> favoriteClick = PublishSubject.create();

    @Inject
    public
    DataAccess dataAccess; //TODO KL: Move to super

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
}
