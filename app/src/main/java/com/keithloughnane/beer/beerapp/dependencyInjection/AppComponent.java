package com.keithloughnane.beer.beerapp.dependencyInjection;

import com.keithloughnane.beer.beerapp.controllers.FavoriteActivityController;
import com.keithloughnane.beer.beerapp.view.BeerProfileActivity;
import com.keithloughnane.beer.beerapp.view.FavoriteActivity;
import com.keithloughnane.beer.beerapp.view.MainActivity;
import com.keithloughnane.beer.beerapp.controllers.MainActivityController;

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
    void inject(FavoriteActivity favoriteActivity);
    void inject(BeerProfileActivity beerProfileActivity);
}
