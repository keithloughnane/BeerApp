package com.keithloughnane.beer.beerapp.activities;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.keithloughnane.beer.beerapp.AppComponent;
import com.keithloughnane.beer.beerapp.R;
import com.keithloughnane.beer.beerapp.data.Beer;
import com.keithloughnane.beer.beerapp.dataAccess.DataAccess;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by user on 04/01/2018.
 */

public class MainActivityController extends Controller {
    @Inject
    DataAccess dataAccess;
    public PublishSubject<Object> favouriteClick = PublishSubject.create();

    MainActivityController(BeerModel beerMode) {
        super(beerMode);
    }

    @Override
    protected Disposable setUpSubscriptions() {
        model.view.downloadStarted();
        dataAccess.getBeer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<Beer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("KLTest", "onSubscribe : " + d);
                    }

                    @Override
                    public void onNext(ArrayList<Beer> beers) { //TODO KL: Should I be using this
                        Log.d("KLTest", ":" + beers);
                        model.beers = beers;
                        model.view.downloadComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("KLTest", "onError: " + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

                     /*
                     @Override
                     public void onSubscribe(Disposable d) {
                         Log.d("KLTest", "onSubscribe : " + d);
                     }

                     @Override
                     public void onNext(ArrayList<Beer> beers) {
                         Log.d("KLTest", ":" + beers);
                         model.beers = beers;
                         model.view.downloadComplete();
                     }

                     @Override
                     public void onError(Throwable e) {
                         Log.e("KLTest", "onError: " + e);
                     }

                     @Override
                     public void onComplete() {

                     }
                 }*/

        favouriteClick
                //.subscribe();
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object view) {
                        //Intent intent = new Intent(this, FavoriteActivity.class);
                        //intent.putExtra(EXTRA_MESSAGE, message);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return Disposables.empty();
    }

    @Override
    public void inject(AppComponent component) {
        component.inject(this);
    }
}