package com.keithloughnane.beer.beerapp.dataAccess;

import android.util.Pair;

import com.keithloughnane.beer.beerapp.data.Beer;
import com.keithloughnane.beer.beerapp.dataAccess.local.AppDatabase;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by user on 04/01/2018.
 */

public class DataAccess { //TODO KL: Better names

    private static final String TAG = "BeerLog";
    private final Observable<Boolean> networkStatus;
    boolean refreshed = false;
    private DataService remoteService;
    private DataService localService;
    private DataService dataService;

    public DataAccess(BeerServiceWrapper remoteService, AppDatabaseWrapper localService, final AppDatabase d, Observable<Boolean> networkStatus) {
        this.remoteService = remoteService;
        this.localService = localService;

        dataService = localService;

        this.networkStatus = networkStatus;

/*
        remoteService.getAllBeers()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<List<Beer>>() {
                    @Override
                    public void accept(List<Beer> beers) throws Exception {
                        d.beerStorage().insertAll(beers);
                    }
                });*/
    }

    public Observable<List<Beer>> sub(Observable<Integer> selectMode) {
        return Observable.combineLatest(networkStatus, selectMode, new BiFunction<Boolean, Integer, Pair<Boolean, Integer>>() {
            @Override
            public Pair<Boolean, Integer> apply(Boolean networkStatus, Integer selectMode) throws Exception {
                return new Pair<>(networkStatus, selectMode);
            }
        })
                .flatMap(new Function<Pair<Boolean, Integer>, Observable<Pair<Boolean, Integer>>>() {

                    @Override
                    public Observable<Pair<Boolean, Integer>> apply(final Pair<Boolean, Integer> booleanIntegerPair) throws Exception {
                        if (booleanIntegerPair.first && !refreshed) {

                            return remoteService.getAllBeers()
                                    .observeOn(Schedulers.io())
                                    .subscribeOn(Schedulers.io())
                                    .flatMap(new Function<List<Beer>, Observable<Pair<Boolean, Integer>>>() {
                                        @Override
                                        public Observable<Pair<Boolean, Integer>> apply(List<Beer> beers) throws Exception {
                                            ((AppDatabaseWrapper) localService).insert(beers);
                                            refreshed = true;
                                            return Observable.just(booleanIntegerPair); //TODO KL: Changed to doOnNext and just
                                        }
                                    })
                                    .onErrorResumeNext(new Function<Throwable, ObservableSource<Pair<Boolean, Integer>>>() {
                                        @Override
                                        public ObservableSource<Pair<Boolean, Integer>> apply(Throwable throwable) throws Exception {
                                            return Observable.just(booleanIntegerPair); //TODO KL: Changed to doOnNext and just
                                        }
                                    });
                        } else {
                            return Observable.just(booleanIntegerPair);
                        }
                    }
                })
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<Pair<Boolean, Integer>, Observable<List<Beer>>>() {
                    @Override
                    public Observable<List<Beer>> apply(Pair<Boolean, Integer> booleanIntegerPair) throws Exception {
                        return localService.getAllBeers();
                    }
                });
    }

    /*
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
    */
}
