package com.keithloughnane.beer.beerapp.dataAccess;

import com.keithloughnane.beer.beerapp.data.Beer;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by user on 06/01/2018.
 */

public interface DataService {
    Observable<List<Beer>> getAllBeers();

    Observable<List<Beer>> getFavBeer();

    Observable<List<Beer>> getAbvBeer();

    Observable<List<Beer>> getEbcBeer();

    Observable<List<Beer>> getIbuBeer();
}
