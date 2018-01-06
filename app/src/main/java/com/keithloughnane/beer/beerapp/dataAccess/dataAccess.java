package com.keithloughnane.beer.beerapp.dataAccess;

import android.util.Log;

import com.keithloughnane.beer.beerapp.data.Beer;
import com.keithloughnane.beer.beerapp.dataAccess.local.AppDatabase;
import com.keithloughnane.beer.beerapp.dataAccess.remote.BeerService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 04/01/2018.
 */

public class DataAccess  { //TODO KL: Better names

    private static final String TAG = "BeerLog";
    private DataService remoteService;
    private DataService localService;
    private DataService dataService;

    public DataAccess(BeerServiceWrapper remoteService, AppDatabaseWrapper localService) {
        this.remoteService = remoteService;
        this.localService = localService;

        dataService =remoteService;
    }

    public Observable<List<Beer>> getAllBeer() {
        return dataService.getAllBeers();
    }

    public Observable<List<Beer>> getFavBeer() {
        return dataService.getFavBeer();
    }

    public Observable<List<Beer>> getAbvBeer() {
        return dataService.getAbvBeer();
    }

    public Observable<List<Beer>> getEbcBeer() {
        return dataService.getEbcBeer();
    }

    public Observable<List<Beer>> getIbuBeer() {
        return dataService.getIbuBeer();
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
