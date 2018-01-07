package com.keithloughnane.beer.beerapp.dataAccess;

import android.util.Log;

/**
 * Created by user on 07/01/2018.
 */

public class BeerLogger {
    private static final String TAG = "BeerLog";

    public void d(String message) {
        Log.d(TAG, message);
    }

    public void e(String message) {
        Log.d(TAG, message);
    }
}
