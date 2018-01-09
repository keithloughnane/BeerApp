package com.keithloughnane.beer.beerapp.view;

import android.os.Bundle;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.keithloughnane.beer.beerapp.BeerApplication;
import com.keithloughnane.beer.beerapp.R;
import com.keithloughnane.beer.beerapp.controllers.MainActivityController;
import com.keithloughnane.beer.beerapp.dataAccess.local.DataAccess;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseListViewActivity<MainActivityController> {

    @BindView(R.id.abv)
    Button abv;

    @BindView(R.id.ibu)
    Button ibu;

    @BindView(R.id.ebc)
    Button ebc;

    @BindView(R.id.fav)
    Button favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ((BeerApplication) getApplication()).component.inject(this);
        super.onCreate(savedInstanceState);

        RxView.clicks(favorite).subscribe(controller.favoriteNavigate);
    }

    @OnClick(R.id.abv)
    public void abvClick() {
        controller.selectMode.onNext(DataAccess.SelectType.ABV);
    }

    @OnClick(R.id.ibu)
    public void ibuClick() {
        controller.selectMode.onNext(DataAccess.SelectType.IBU);
    }

    @OnClick(R.id.ebc)
    public void ebcClick() {
        controller.selectMode.onNext(DataAccess.SelectType.EBC);
    }

    @Override
    protected MainActivityController createController() {
        return new MainActivityController(model);
    }
}
