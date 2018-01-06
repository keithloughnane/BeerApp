package com.keithloughnane.beer.beerapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.keithloughnane.beer.beerapp.AppComponent;
import com.keithloughnane.beer.beerapp.BeerApplication;
import com.keithloughnane.beer.beerapp.R;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivityWithAdapter<BeerModel, MainActivityController> {
    //private AppComponent component;

    @BindView(R.id.fav)
    Button favourite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        ((BeerApplication) getApplication()).component.inject(this);
        //component.inject(this);

        RxView.clicks(favourite)
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Intent intent = new Intent(getApplicationContext(), FavoriteActivity.class);
                        startActivity(intent);
                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ((MainActivityController) controller).favouriteClick.onNext(o); //TODO KL: Need whole object?
                    }
                });
    }

    @Override
    protected BeerModel createModel() {

        BeerModel model = new BeerModel();
        model.view = this;
        return model;
    }

    @Override
    protected Controller createController() {
        return new MainActivityController(model);
    }
}
