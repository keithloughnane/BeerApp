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

    public Observable<List<Beer>> sub(Observable<SelectType> selectMode) {
        return Observable.combineLatest(networkStatus, selectMode, new BiFunction<Boolean, SelectType, Pair<Boolean, SelectType>>() {
            @Override
            public Pair<Boolean, SelectType> apply(Boolean networkStatus, SelectType selectMode) throws Exception {
                return new Pair<>(networkStatus, selectMode);
            }
        })
                .flatMap(new Function<Pair<Boolean, SelectType>, Observable<Pair<Boolean, SelectType>>>() {

                    @Override
                    public Observable<Pair<Boolean, SelectType>> apply(final Pair<Boolean, SelectType> booleanIntegerPair) throws Exception {
                        if (booleanIntegerPair.first && !refreshed) {

                            return remoteService.getAllBeers()
                                    .observeOn(Schedulers.io())
                                    .subscribeOn(Schedulers.io())
                                    .flatMap(new Function<List<Beer>, Observable<Pair<Boolean, SelectType>>>() {
                                        @Override
                                        public Observable<Pair<Boolean, SelectType>> apply(List<Beer> beers) throws Exception {
                                            ((AppDatabaseWrapper) localService).insert(beers);
                                            refreshed = true;
                                            return Observable.just(booleanIntegerPair); //TODO KL: Changed to doOnNext and just
                                        }
                                    })
                                    .onErrorResumeNext(new Function<Throwable, ObservableSource<Pair<Boolean, SelectType>>>() {
                                        @Override
                                        public ObservableSource<Pair<Boolean, SelectType>> apply(Throwable throwable) throws Exception {
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
                .flatMap(new Function<Pair<Boolean, SelectType>, Observable<List<Beer>>>() {
                    @Override
                    public Observable<List<Beer>> apply(Pair<Boolean, SelectType> booleanIntegerPair) throws Exception {
                        switch (booleanIntegerPair.second) {
                            case ABV:
                                return localService.getAbvBeer();
                            case IBU:
                                return localService.getIbuBeer();
                            case EBC:
                                return localService.getEbcBeer();
                            case FAV:
                                return localService.getFavBeer();
                            case ALL:
                            default:
                                return localService.getAllBeers();
                        }
                    }
                });
    }

    public enum SelectType {
        ABV, IBU, EBC, FAV, ALL;
    }
}
