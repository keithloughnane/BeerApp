package com.keithloughnane.beer.beerapp.dataAccess;

import com.keithloughnane.beer.beerapp.data.Beer;
import com.keithloughnane.beer.beerapp.dataAccess.local.AppDatabase;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by user on 06/01/2018.
 */

public class AppDatabaseWrapper implements DataService {

    private final AppDatabase appDatabase;

    public AppDatabaseWrapper(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public Observable<List<Beer>> getAllBeers() {
        return Observable.just(appDatabase.beerStorage().getAllBeers());
    }

    @Override
    public Observable<List<Beer>> getFavBeer() {
        return Observable.just(appDatabase.beerStorage().getFavBeer());
    }

    @Override
    public Observable<List<Beer>> getAbvBeer() {
        return Observable.just(appDatabase.beerStorage().getAbvBeer());
    }

    @Override
    public Observable<List<Beer>> getEbcBeer() {
        return Observable.just(appDatabase.beerStorage().getEbcBeer());
    }

    @Override
    public Observable<List<Beer>> getIbuBeer() {
        return Observable.just(appDatabase.beerStorage().getIbuBeer());
    }

    public void insert(List<Beer> beers) {
        appDatabase.beerStorage().insertAll(beers);
    }
}
