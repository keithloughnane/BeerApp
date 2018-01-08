package com.keithloughnane.beer.beerapp.data;

import android.arch.persistence.room.Embedded;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by keith.loughnane@gmail.com on 04/01/2018.
 */

public class Method implements Serializable {
    @SerializedName("mash_temp")
    public ArrayList<MethodStep> mashTemp;

    @Embedded(prefix = "fermentation")
    public MethodStep fermentation;
}
