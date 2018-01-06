package com.keithloughnane.beer.beerapp.dataAccess.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.keithloughnane.beer.beerapp.data.Beer;
import com.keithloughnane.beer.beerapp.dataAccess.DataService;
import com.keithloughnane.beer.beerapp.dataAccess.local.BeerStorage;
import com.keithloughnane.beer.beerapp.dataAccess.remote.BeerService;

@Database(entities = {Beer.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BeerStorage beerStorage();
}
