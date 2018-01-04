package com.keithloughnane.beer.beerapp;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by user on 04/01/2018.
 */

class Volume {
    float value;
    String unit; //TODO KL: Change to ENUM
}
