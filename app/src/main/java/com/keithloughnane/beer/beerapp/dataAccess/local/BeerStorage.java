package com.keithloughnane.beer.beerapp.dataAccess.local;

/**
 * Created by user on 03/01/2018.
 */

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.keithloughnane.beer.beerapp.data.Beer;
import com.keithloughnane.beer.beerapp.dataAccess.DataService;

import java.util.List;

@Dao
public interface BeerStorage {

    @Query("SELECT * FROM beer")
    List<Beer> getAll();

    @Query("SELECT * FROM beer")
    List<Beer> getAllBeers();

    @Query("SELECT * FROM beer")
    List<Beer> getFavBeer();

    @Query("SELECT * FROM beer")
    List<Beer> getAbvBeer();

    @Query("SELECT * FROM beer")
    List<Beer> getEbcBeer();

    @Query("SELECT * FROM beer")
    List<Beer> getIbuBeer();

    /*
    @Query("SELECT * FROM beer WHERE id")
    List<Beer> loadAllByIds(int[] id);
*/
    /*
    @Query("SELECT * FROM beer")
    Beer findByName(String first, String last);
    */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Beer... beers);

    @Delete
    void delete(Beer... beers);
}