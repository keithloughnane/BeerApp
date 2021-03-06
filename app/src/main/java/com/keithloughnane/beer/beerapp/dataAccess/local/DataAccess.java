package com.keithloughnane.beer.beerapp.dataAccess.local;

import android.util.Pair;

import com.keithloughnane.beer.beerapp.data.Beer;
import com.keithloughnane.beer.beerapp.dataAccess.remote.BeerApiService;
import com.keithloughnane.beer.beerapp.util.BeerLogger;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by keith.loughnane@gmail.com on 04/01/2018.
 */

public class DataAccess {
    private final Observable<Boolean> networkStatus;
    private final BeerLogger logger;
    private final BeerApiService remoteApiService;
    private final BeerStorage localStorage;
    private boolean refreshed = false;

    public DataAccess(BeerApiService remoteService, BeerStorage localStorage, Observable<Boolean> networkStatus, BeerLogger logger) {
        this.remoteApiService = remoteService;
        this.localStorage = localStorage;
        this.logger = logger;
        this.networkStatus = networkStatus;
    }

    public Observable<List<Beer>> sub(BehaviorSubject<SelectType> selectMode) {
        return Observable
                .combineLatest(networkStatus, selectMode, new BiFunction<Boolean, SelectType, Pair<Boolean, SelectType>>() {
                    @Override
                    public Pair<Boolean, SelectType> apply(Boolean networkStatus, SelectType selectMode) throws Exception {
                        return new Pair<>(networkStatus, selectMode);
                    }
                })
                .flatMap(new Function<Pair<Boolean, SelectType>, Observable<Pair<Boolean, SelectType>>>() {
                    @Override
                    public Observable<Pair<Boolean, SelectType>> apply(final Pair<Boolean, SelectType> onlineSelectTypePair) throws Exception {
                        if (onlineSelectTypePair.first && !refreshed) {
                            return remoteApiService.getAllBeers()
                                    .subscribeOn(Schedulers.io())
                                    .flatMap(new Function<List<Beer>, Observable<Pair<Boolean, SelectType>>>() {
                                        @Override
                                        public Observable<Pair<Boolean, SelectType>> apply(List<Beer> beers) throws Exception {
                                            localStorage.insertAll(beers);
                                            refreshed = true;
                                            logger.d("Api success. Retrieved item size : " + beers.size());
                                            return Observable.just(onlineSelectTypePair);
                                        }
                                    })
                                    .onErrorResumeNext(new Function<Throwable, ObservableSource<Pair<Boolean, SelectType>>>() {
                                        @Override
                                        public ObservableSource<Pair<Boolean, SelectType>> apply(Throwable throwable) throws Exception {
                                            logger.e("Api failure : " + throwable);
                                            return Observable.just(onlineSelectTypePair);
                                        }
                                    });
                        } else {
                            return Observable.just(onlineSelectTypePair);
                        }
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<Pair<Boolean, SelectType>, Observable<List<Beer>>>() {
                    @Override
                    public Observable<List<Beer>> apply(Pair<Boolean, SelectType> booleanIntegerPair) throws Exception {
                        switch (booleanIntegerPair.second) {
                            case ABV:
                                return Observable.just(localStorage.getAbvBeer());
                            case IBU:
                                return Observable.just(localStorage.getIbuBeer());
                            case EBC:
                                return Observable.just(localStorage.getEbcBeer());
                            case FAV:
                                return Observable.just(localStorage.getFavBeer());
                            case ALL:
                            default:
                                return Observable.just(localStorage.getAllBeers());
                        }
                    }
                });
    }

    public void update(Beer beer) {
        localStorage.updateBeer(beer.favorite, beer.id);
    }

    public enum SelectType {
        ABV, IBU, EBC, FAV, ALL
    }
}
