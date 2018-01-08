package com.keithloughnane.beer.beerapp.dataAccess.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.keithloughnane.beer.beerapp.data.Beer;

@Database(entities = {Beer.class}, version = 1, exportSchema = false)
public abstract class BeerDatabase extends RoomDatabase {
    public abstract BeerStorage beerStorage();
}
