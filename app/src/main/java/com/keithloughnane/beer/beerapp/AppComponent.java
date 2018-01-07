package com.keithloughnane.beer.beerapp;

import com.keithloughnane.beer.beerapp.activities.FavoriteActivityController;
import com.keithloughnane.beer.beerapp.activities.MainActivity;
import com.keithloughnane.beer.beerapp.activities.MainActivityController;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by keith.loughnane@gmail.com on 03/01/2018.
 */


@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity mainActivity);
    void inject(MainActivityController mainActivityController);
    void inject(FavoriteActivityController favoriteActivityController);
}
