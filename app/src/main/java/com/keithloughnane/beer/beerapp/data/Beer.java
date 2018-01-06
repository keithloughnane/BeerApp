package com.keithloughnane.beer.beerapp.data;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.keithloughnane.beer.beerapp.dataAccess.local.BeerConverter;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 03/01/2018.
 */

@Entity

@TypeConverters({BeerConverter.class})
public class Beer implements Serializable {
    @PrimaryKey
    public int id;
    public boolean favorite = false;
    public String name;
    public String tagline;
    public String first_brewed; //TODO KL. Right type?
    public String description;
    public String image_url;
    public float abv;
    public float ibu;
    public float target_fg;
    public float target_og;
    public float ebc;
    public float srm;
    public float ph;
    public float attenuation_level;
    @Embedded(prefix="volume")
    public UnitVolume volume;
    @Embedded(prefix="boil")
    public UnitVolume boil_volume;
    @Embedded(prefix="method")
    public Method method;
    @Embedded(prefix="ingredients")
    public Ingredients ingredients;

    public ArrayList<String> food_pairing;
    public String brewers_tips;
    public String contributed_by;

    //@ColumnInfo(name = "item1_id")

}
