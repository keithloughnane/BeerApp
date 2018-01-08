package com.keithloughnane.beer.beerapp.data;

import android.arch.persistence.room.Embedded;

import java.io.Serializable;

/**
 * Created by keith.loughnane@gmail.com on 08/01/2018.
 */

public class MethodStep implements Serializable {
    @Embedded
    public UnitAmount temp;
    public float duration;
    public String twist;
}
