package com.keithloughnane.beer.beerapp;

/**
 * Created by user on 03/01/2018.
 */

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
interface BeerStorage {

    @Query("SELECT * FROM beer")
    List<Beer> getAll();
/*
    @Query("SELECT * FROM beer WHERE id")
    List<Beer> loadAllByIds(int[] id);
*/
    /*
    @Query("SELECT * FROM beer")
    Beer findByName(String first, String last);
    */


    @Insert
    void insertAll(Beer... beers);

    @Delete
    void delete(Beer... beers);
}