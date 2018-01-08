package com.keithloughnane.beer.beerapp.dataAccess.local;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.keithloughnane.beer.beerapp.data.Ingredient;
import com.keithloughnane.beer.beerapp.data.MethodStep;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by keith.loughnane@gmail.com on 04/01/2018.
 */

public class BeerConverter {
    @TypeConverter
    public static ArrayList<String> fromString(String value) { //TODO KL: Reuse
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<String> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public static ArrayList<Ingredient> fromIngredientString(String value) {
        Type listType = new TypeToken<ArrayList<Ingredient>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromIngredientList(ArrayList<Ingredient> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public static ArrayList<MethodStep> fromMethodStepString(String value) {
        Type listType = new TypeToken<ArrayList<Ingredient>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromMethodStepList(ArrayList<MethodStep> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
