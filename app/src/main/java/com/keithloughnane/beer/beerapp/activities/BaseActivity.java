package com.keithloughnane.beer.beerapp.activities;

import android.graphics.ColorSpace;
import android.support.v7.app.AppCompatActivity;

import com.keithloughnane.beer.beerapp.AppComponent;
import com.keithloughnane.beer.beerapp.BeerApplication;

/**
 * Created by user on 04/01/2018.
 */

public abstract class BaseActivity<M extends BeerModel, C extends Controller> extends AppCompatActivity {
    M model;
    C controller;

    BaseActivity() {
        model = createModel();
        controller = createController();
        injectController(((BeerApplication) getApplication()).component);
        controller.SetUp();
    }

    private void injectController(AppComponent component) {
        controller.inject(component);
    }

    protected abstract M createModel();
    protected abstract C createController();
}
