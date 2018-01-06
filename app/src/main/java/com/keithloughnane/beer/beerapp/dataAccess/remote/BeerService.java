package com.keithloughnane.beer.beerapp.dataAccess.remote;

import com.keithloughnane.beer.beerapp.data.Beer;
import com.keithloughnane.beer.beerapp.dataAccess.DataService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by user on 03/01/2018.
 */

public interface BeerService {

    @GET("beers")
    Observable<ArrayList<Beer>> getAllBeers();

    @GET("beers")
    Observable<ArrayList<Beer>> getFavBeer();

    @GET("beers")
    Observable<ArrayList<Beer>> getAbvBeer();

    @GET("beers")
    Observable<ArrayList<Beer>> getEbcBeer();

    @GET("beers")
    Observable<ArrayList<Beer>> getIbuBeer();
}
