package com.keithloughnane.beer.beerapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.keithloughnane.beer.beerapp.BeerApplication;
import com.keithloughnane.beer.beerapp.R;
import com.keithloughnane.beer.beerapp.controllers.Controller;
import com.keithloughnane.beer.beerapp.controllers.MainActivityController;
import com.keithloughnane.beer.beerapp.dataAccess.local.DataAccess;
import com.keithloughnane.beer.beerapp.models.BeerModel;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivityWithBeerAdapter<BeerModel, MainActivityController> {

    @BindView(R.id.abv)
    Button abv;

    @BindView(R.id.ibu)
    Button ibu;

    @BindView(R.id.ebc)
    Button ebc;

    @BindView(R.id.fav)
    Button favourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ((BeerApplication) getApplication()).component.inject(this);
        super.onCreate(savedInstanceState);

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

    @OnClick(R.id.abv)
    public void abvClick() {
        ((MainActivityController) controller).selectMode.onNext(DataAccess.SelectType.ABV);
    }

    @OnClick(R.id.ibu)
    public void ibuClick() {
        ((MainActivityController) controller).selectMode.onNext(DataAccess.SelectType.IBU);
    }

    @OnClick(R.id.ebc)
    public void ebcClick() {
        ((MainActivityController) controller).selectMode.onNext(DataAccess.SelectType.EBC);
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
