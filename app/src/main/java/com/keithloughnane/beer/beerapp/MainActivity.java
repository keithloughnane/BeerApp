package com.keithloughnane.beer.beerapp;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "BeerLog";
    private AppDatabase db;

    @Inject
    Beer beer;

    private AppComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = DaggerAppComponent.builder().build();
        component.inject(this);
        setContentView(R.layout.activity_main);

        //testSql();
        testApi();
    }

    private void testApi() {
        Beer beer99 = beer;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.punkapi.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BeerService service = retrofit.create(BeerService.class);
        Call<List<Beer>> repos = service.beers();
        repos.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Call<List<Beer>> call, final Response<List<Beer>> response) {
                Log.d(TAG, "onResponse: " + response);
                Observable.timer(10, TimeUnit.SECONDS)
                        .observeOn(Schedulers.io())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                testSql();

                                Beer beer1 = response.body().get(0);
                                Beer beer2 = response.body().get(1);


                                Beer[] realBeers = {beer1, beer2};

                                db.beerStorage().insertAll(realBeers);

                                List<Beer> newBeer = db.beerStorage().getAll();
                            }
                        });



            }

            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t) {

            }
        });
    }

    private void testSql() {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "beer").build();
    }
}
