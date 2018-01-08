package com.keithloughnane.beer.beerapp.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by keith.loughnane@gmail.com on 04/01/2018.
 */

public class Ingredients implements Serializable {
    public ArrayList<Ingredient> malt;
    public ArrayList<Ingredient> hops;
    public String yeast;
}
