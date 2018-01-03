package com.keithloughnane.beer.beerapp;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by user on 03/01/2018.
 */

@Entity
class Beer {
    @PrimaryKey
    int id;
}
