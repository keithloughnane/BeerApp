package com.keithloughnane.beer.beerapp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by user on 04/01/2018.
 */

public class UnitVolume {
    public float value;
    public String unit; //TODO KL: Change to ENUM
}
