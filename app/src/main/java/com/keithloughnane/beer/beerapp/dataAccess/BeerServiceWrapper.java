/*package com.keithloughnane.beer.beerapp.dataAccess;

import com.keithloughnane.beer.beerapp.data.Beer;
import com.keithloughnane.beer.beerapp.dataAccess.remote.BeerService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by user on 06/01/2018.
 */

/* TODO KL: Delete me
public class BeerServiceWrapper implements DataService {
    private final BeerApiService appDatabase;

    public BeerServiceWrapper(BeerApiService localAccess) {
        this.appDatabase = localAccess;
    }

    @Override
    public Observable<List<Beer>> getAllBeers() {
        return appDatabase.getAllBeers();
                /*.map(new Function<List<Beer>, List<Beer>>() { //TODO KL: Reuse this
                    @Override
                    public List<Beer> apply(List<Beer> beers) throws Exception {
                        return beers;
                    //}
                //}); */ /*
    }

    /
    @Override
    public Observable<List<Beer>> getFavBeer() {
        return appDatabase.getAllBeers();
        /*
        return appDatabase.getFavBeer().map(new Function<ArrayList<Beer>, List<Beer>>() {
            @Override
            public List<Beer> apply(ArrayList<Beer> beers) throws Exception {
                return beers;
            }
        });*/ /*
    }

    @Override
    public Observable<List<Beer>> getAbvBeer() {
        return appDatabase.getAllBeers();
        /*
        return appDatabase.getAbvBeer().map(new Function<ArrayList<Beer>, List<Beer>>() {
            @Override
            public List<Beer> apply(ArrayList<Beer> beers) throws Exception {
                return beers;
            }
        });*/ /*
    }

    @Override
    public Observable<List<Beer>> getEbcBeer() {

        return appDatabase.getAllBeers();

        /*
        return appDatabase.getEbcBeer().map(new Function<ArrayList<Beer>, List<Beer>>() {
            @Override
            public List<Beer> apply(ArrayList<Beer> beers) throws Exception {
                return beers;
            }
        });*/ /*
    }

    @Override
    public Observable<List<Beer>> getIbuBeer() {
        return appDatabase.getAllBeers();
        /*
        return appDatabase.getIbuBeer().map(new Function<ArrayList<Beer>, List<Beer>>() {
            @Override
            public List<Beer> apply(ArrayList<Beer> beers) throws Exception {
                return beers;
            }
        });*/ /*
    }

    @Override
    public void update(Beer beer) {
        //TODO KL: Get rid of me
    }
}
*/