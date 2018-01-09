package com.keithloughnane.beer.beerapp.dependencyInjection;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.keithloughnane.beer.beerapp.R;
import com.keithloughnane.beer.beerapp.UIManager;
import com.keithloughnane.beer.beerapp.dataAccess.local.BeerDatabase;
import com.keithloughnane.beer.beerapp.dataAccess.local.DataAccess;
import com.keithloughnane.beer.beerapp.dataAccess.remote.BeerApiService;
import com.keithloughnane.beer.beerapp.util.BeerLogger;
import com.keithloughnane.beer.beerapp.util.NetworkObserver;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by keith.loughnane@gmail.com on 03/01/2018.
 */

@Module
public class AppModule {
    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    Context providesContext() {
        return context;
    }

    @Provides
    @Singleton
    BeerApiService remoteData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(BeerApiService.class);
    }

    @Provides
    @Singleton
    BeerDatabase providesAppDatabase(Context context) {
        return Room.databaseBuilder(context,
                BeerDatabase.class, "beer").build();
    }

    @Provides
    @Singleton
    DataAccess providesDataAccess(BeerApiService localAccess, BeerDatabase remoteAccess, NetworkObserver networkObserver, BeerLogger logger) {
        return new DataAccess(localAccess, remoteAccess.beerStorage(), networkObserver.sub, logger);
    }

    @Provides
    @Singleton
    BeerLogger providesLogger() {
        return new BeerLogger();
    }

    @Provides
    @Singleton
    NetworkObserver providesNetworkObserver(Context context, BeerLogger logger) {
        return new NetworkObserver(context, logger);
    }

    @Provides
    @Singleton
    UIManager providesUIManager(Context context) {
        return new UIManager(context);
    }
}