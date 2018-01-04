package com.keithloughnane.beer.beerapp;

import android.app.Application;

/**
 * Created by user on 03/01/2018.
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
