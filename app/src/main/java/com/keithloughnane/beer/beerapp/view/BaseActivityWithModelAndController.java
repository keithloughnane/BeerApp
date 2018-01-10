package com.keithloughnane.beer.beerapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.keithloughnane.beer.beerapp.UIManager;
import com.keithloughnane.beer.beerapp.dependencyInjection.AppComponent;
import com.keithloughnane.beer.beerapp.BeerApplication;
import com.keithloughnane.beer.beerapp.controllers.Controller;
import com.keithloughnane.beer.beerapp.models.BeerModel;

import javax.inject.Inject;

/**
 * Created by keith.loughnane@gmail.com on 04/01/2018.
 */

public abstract class BaseActivityWithModelAndController<M extends BeerModel, C extends Controller> extends BaseActivity {
    M model;
    C controller;



    private void injectController(AppComponent component) {
        controller.inject(component);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppComponent component = ((BeerApplication) getApplication()).component;

        model = createModel();
        controller = createController();

        injectController(component);
        controller.setUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        controller.dispose();
    }

    protected abstract M createModel();
    protected abstract C createController();
}
