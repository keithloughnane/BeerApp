package com.keithloughnane.beer.beerapp;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.keithloughnane.beer.beerapp.dataAccess.BeerLogger;
import com.keithloughnane.beer.beerapp.dataAccess.DataAccess;
import com.keithloughnane.beer.beerapp.dataAccess.local.AppDatabase;
import com.keithloughnane.beer.beerapp.dataAccess.remote.BeerApiService;

import java.util.logging.Logger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 03/01/2018.
 */

@Module
public class AppModule {
    Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    BeerApiService remoteData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.punkapi.com/v2/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BeerApiService service = retrofit.create(BeerApiService.class);

        return service;
    }

    @Provides
    @Singleton
    public AppDatabase localData(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "beer").build();
    }

    @Provides
    @Singleton
    public DataAccess dataAccess(BeerApiService localAccess, AppDatabase remoteAccess, NetworkObserver networkObserver, BeerLogger logger) {
        return new DataAccess(localAccess, remoteAccess.beerStorage(), networkObserver.sub, logger);
    }

    @Provides
    @Singleton
    public BeerLogger providesLogger() {
        return new BeerLogger();
    }

    @Provides
    @Singleton
    public NetworkObserver providesNetworkObserver(Context context, BeerLogger logger) {
        return new NetworkObserver(context, logger);
    }

    @Provides
    public Context context() {
        return context;
    }
}
