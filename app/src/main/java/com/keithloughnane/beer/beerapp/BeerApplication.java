package com.keithloughnane.beer.beerapp;

import android.app.Application;

import com.keithloughnane.beer.beerapp.dependencyInjection.AppComponent;
import com.keithloughnane.beer.beerapp.dependencyInjection.AppModule;
import com.keithloughnane.beer.beerapp.dependencyInjection.DaggerAppComponent;

/**
 * Created by keith.loughnane@gmail.com on 03/01/2018.
 */

public class BeerApplication extends Application {
    public AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
