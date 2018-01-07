package com.keithloughnane.beer.beerapp.dataAccess;

import android.util.Log;
import android.util.Pair;

import com.keithloughnane.beer.beerapp.data.Beer;
import com.keithloughnane.beer.beerapp.dataAccess.local.AppDatabase;
import com.keithloughnane.beer.beerapp.dataAccess.local.BeerStorage;
import com.keithloughnane.beer.beerapp.dataAccess.remote.BeerService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by user on 04/01/2018.
 */

public class DataAccess { //TODO KL: Better names

    private static final String TAG = "BeerLog";
    private final Observable<Boolean> networkStatus;
    private final BeerLogger logger;
    private boolean refreshed = false; //TODO, Maybe should be in model
    private BeerService remoteService;
    private BeerStorage localService;

    public DataAccess(BeerService remoteService, BeerStorage localService, Observable<Boolean> networkStatus, BeerLogger logger) {
        this.remoteService = remoteService;
        this.localService = localService;
        this.logger = logger;
        this.networkStatus = networkStatus;
    }

    public Observable<List<Beer>> sub(Observable<SelectType> selectMode) {
        Log.d("KLTest", "sub 000");
        return Observable.combineLatest(networkStatus, selectMode, new BiFunction<Boolean, SelectType, Pair<Boolean, SelectType>>() {
            @Override
            public Pair<Boolean, SelectType> apply(Boolean networkStatus, SelectType selectMode) throws Exception {
                Log.d("KLTest", "sub 100");
                return new Pair<>(networkStatus, selectMode);
            }
        })
                .flatMap(new Function<Pair<Boolean, SelectType>, Observable<Pair<Boolean, SelectType>>>() {

                    @Override
                    public Observable<Pair<Boolean, SelectType>> apply(final Pair<Boolean, SelectType> booleanIntegerPair) throws Exception {
                        if (booleanIntegerPair.first && !refreshed) {
                            Log.d("KLTest", "sub 200");
                            return remoteService.getAllBeers()
                                    .observeOn(Schedulers.io())
                                    .subscribeOn(Schedulers.io())
                                    .flatMap(new Function<List<Beer>, Observable<Pair<Boolean, SelectType>>>() {
                                        @Override
                                        public Observable<Pair<Boolean, SelectType>> apply(List<Beer> beers) throws Exception {
                                            localService.insertAll(beers);
                                            refreshed = true;
                                            logger.d("Test");
                                            logger.e("Test");
                                            Log.d("KLTest", "sub 300");
                                            return Observable.just(booleanIntegerPair); //TODO KL: Changed to doOnNext and just
                                        }
                                    })
                                    .onErrorResumeNext(new Function<Throwable, ObservableSource<Pair<Boolean, SelectType>>>() {
                                        @Override
                                        public ObservableSource<Pair<Boolean, SelectType>> apply(Throwable throwable) throws Exception {
                                            Log.d("KLTest", "sub E400");
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
                        Log.d("KLTest", "sub 500");
                        switch (booleanIntegerPair.second) {
                            case ABV:
                                return Observable.just(localService.getAbvBeer());
                            case IBU:
                                return Observable.just(localService.getIbuBeer());
                            case EBC:
                                return Observable.just(localService.getEbcBeer());
                            case FAV:
                                return Observable.just(localService.getFavBeer());
                            case ALL:
                            default:
                                return Observable.just(localService.getAllBeers());
                        }
                    }
                });
    }

    public void update(Beer beer) {
        localService.updateBeer(beer.favorite, beer.id);
    }

    public enum SelectType {
        ABV, IBU, EBC, FAV, ALL;
    }
}
