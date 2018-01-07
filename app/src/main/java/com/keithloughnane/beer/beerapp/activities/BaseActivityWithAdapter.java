package com.keithloughnane.beer.beerapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.keithloughnane.beer.beerapp.BeerViewHolder;
import com.keithloughnane.beer.beerapp.ControllerWithAdapter;
import com.keithloughnane.beer.beerapp.R;
import com.keithloughnane.beer.beerapp.dataAccess.DataAccess;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.observable.ObservableScan;

/**
 * Created by user on 04/01/2018.
 */

abstract class BaseActivityWithAdapter<B, M> extends BaseActivity implements BeerView {

    private Adapter adapter;

    @BindView(R.id.content_view)
    RecyclerView recyclerView;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.failureIcon)
    View failure;

    @Inject
    DataAccess dataAccess;

    BaseActivityWithAdapter() {
        super();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this);
        adapter = new Adapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        Log.d("KLTest", "recycle set up");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void downloadComplete() {
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);


        //if (model.beers.size() == 0) {
        //    failure.setVisibility(View.VISIBLE);
        //} else {
            recyclerView.setVisibility(View.VISIBLE);
        //}

    }

    @Override
    public void downloadStarted() {
        recyclerView.setVisibility(View.GONE);
        failure.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    class Adapter extends RecyclerView.Adapter<BeerViewHolder> {
        @Override
        public BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BeerViewHolder(LayoutInflater.from(getBaseContext()).inflate(R.layout.beer_view_holder ,null),  (ControllerWithAdapter) controller); //TODO KL: Refactor to avoid cast
        }

        @Override
        public void onBindViewHolder(BeerViewHolder holder, int position) {
            holder.bind(model.beers.get(position));
        }

        @Override
        public int getItemCount() {
            Log.d("KLTest", "count = " + model.beers.size());
            return model.beers.size();
        }
    }
}
