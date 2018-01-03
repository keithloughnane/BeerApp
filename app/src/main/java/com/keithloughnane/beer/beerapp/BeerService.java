package com.keithloughnane.beer.beerapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by user on 03/01/2018.
 */

public interface BeerService {
    @GET("beers")
    Call<List<Beer>> beers();
}
