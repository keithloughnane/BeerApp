package com.keithloughnane.beer.beerapp.dataAccess;

import android.util.Log;

import com.keithloughnane.beer.beerapp.data.Beer;
import com.keithloughnane.beer.beerapp.dataAccess.local.AppDatabase;
import com.keithloughnane.beer.beerapp.dataAccess.remote.BeerService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 04/01/2018.
 */

public class DataAccess {

    private static final String TAG = "BeerLog";
    private final BeerService remoteService;
    private AppDatabase localService;

    public DataAccess(BeerService remoteService, AppDatabase localService) {
        this.remoteService = remoteService;
        this.localService = localService;
    }

    public Observable getBeer() {
        return remoteService.beers();
    }

    /*
    private void testApi() {
        Observable<List<Beer>> repos = remoteService.beers();

        repos.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Call<List<Beer>> call, final Response<List<Beer>> response) {
                Log.d(TAG, "onResponse: " + response);
                Observable.timer(10, TimeUnit.SECONDS)
                        .observeOn(Schedulers.io())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                Beer beer1 = response.body().get(0);
                                Beer beer2 = response.body().get(1);

                                Beer[] realBeers = {beer1, beer2};

                                localService.beerStorage().insertAll(realBeers);

                                List<Beer> newBeer = localService.beerStorage().getAll();
                            }
                        });
            }

            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t) {

            }
        });
    } */
}
