package com.keithloughnane.beer.beerapp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 03/01/2018.
 */

@Module
class AppModule {

    @Provides
    Beer providesBeer() {
        return new Beer();
    }

}
