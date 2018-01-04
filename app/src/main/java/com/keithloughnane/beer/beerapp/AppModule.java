package com.keithloughnane.beer.beerapp;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.keithloughnane.beer.beerapp.data.Beer;
import com.keithloughnane.beer.beerapp.dataAccess.local.AppDatabase;
import com.keithloughnane.beer.beerapp.dataAccess.remote.BeerService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 03/01/2018.
 */

@Module
class AppModule {

    @Provides
    @Singleton
    BeerService providesBeer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.punkapi.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BeerService service = retrofit.create(BeerService.class);

        return service;
    }

    @Provides
    @Singleton
    public AppDatabase testSql(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "beer").build();
    }
}
