package com.keithloughnane.beer.beerapp;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.ArrayList;

/**
 * Created by user on 03/01/2018.
 */

@Entity

@TypeConverters({BeerConverter.class})
class Beer {
    @PrimaryKey
    int id;
    String name;
    String tagline;
    String first_brewed; //TODO KL. Right type?
    String description;
    String image_url;
    float abv;
    float ibu;
    float target_fg;
    float target_og;
    float ebc;
    float srm;
    float ph;
    float attenuation_level;
    @Embedded(prefix="volume")
    UnitVolume volume;
    @Embedded(prefix="boil")
    UnitVolume boil_volume;
    @Embedded(prefix="method")
    Method method;
    @Embedded(prefix="ingredients")
    Ingredients ingredients;

    ArrayList<String> food_pairing;
    String brewers_tips;
    String contributed_by;

    //@ColumnInfo(name = "item1_id")

}
