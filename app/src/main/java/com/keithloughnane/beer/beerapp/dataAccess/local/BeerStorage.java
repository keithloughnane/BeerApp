package com.keithloughnane.beer.beerapp.dataAccess.local;

/**
 * Created by keith.loughnane@gmail.com on 03/01/2018.
 */

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.keithloughnane.beer.beerapp.data.Beer;

import java.util.List;

@Dao
public interface BeerStorage {

    @Query("SELECT * FROM beer")
    List<Beer> getAll();

    @Query("SELECT * FROM beer")
    List<Beer> getAllBeers();

    @Query("SELECT * FROM beer WHERE favorite = 1")
    List<Beer> getFavBeer();

    @Query("SELECT * FROM beer ORDER BY abv")
    List<Beer> getAbvBeer();

    @Query("SELECT * FROM beer ORDER BY ebc")
    List<Beer> getEbcBeer();

    @Query("SELECT * FROM beer ORDER BY ibu")
    List<Beer> getIbuBeer();

    //If row exists don't replace but continue inserting the rest
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<Beer> beers);

    @Delete
    void delete(Beer... beers);

    @Query("UPDATE beer SET favorite= :favorite WHERE id = :id")
    int updateBeer(boolean favorite, int id);

}