package com.keithloughnane.beer.beerapp.dataAccess.remote;

import com.keithloughnane.beer.beerapp.data.Beer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by user on 03/01/2018.
 */

public interface BeerService {
    @GET("beers")
    Call<List<Beer>> beers();
}
