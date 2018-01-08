package com.keithloughnane.beer.beerapp.data;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.keithloughnane.beer.beerapp.dataAccess.local.BeerConverter;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by keith.loughnane@gmail.com on 03/01/2018.
 */

@Entity
@TypeConverters({BeerConverter.class})
public class Beer implements Serializable { //TODO KL: Use JavaStyle names
    @PrimaryKey
    public int id;

    public boolean favorite = false;
    public String name;
    public String tagline;
    public String description;
    public float abv;
    public float ibu;
    public float ebc;
    public float srm;
    public float ph;

    @SerializedName("target_fg")
    public float targetFg;

    @SerializedName("target_og")
    public float targetOg;

    @SerializedName("attenuation_level")
    public float attenuationLevel;

    @SerializedName("brewers_tips")
    public String brewersTips;

    @SerializedName("contributed_by")
    public String contributedBy;

    @SerializedName("first_brewed")
    public String firstBrewed;

    @SerializedName("image_url")
    public String imageUrl;

    @SerializedName("food_pairing")
    public ArrayList<String> foodPairing;

    @Embedded(prefix = "volume")
    public UnitAmount volume;

    @Embedded(prefix = "boil")
    @SerializedName("boil_volume")
    public UnitAmount boilVolume;

    @Embedded(prefix = "method")
    public Method method;

    @Embedded(prefix = "ingredients")
    public Ingredients ingredients;
}
