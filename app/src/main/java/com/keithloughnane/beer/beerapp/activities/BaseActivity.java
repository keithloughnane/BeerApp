package com.keithloughnane.beer.beerapp.activities;

import android.graphics.ColorSpace;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by user on 04/01/2018.
 */

public abstract class BaseActivity<M extends BeerModel, C extends Controller> extends AppCompatActivity {
    M model;
    C controller;

    BaseActivity() {
        model = createModel();
        controller = createController();
    }

    protected abstract M createModel();
    protected abstract C createController();
}
