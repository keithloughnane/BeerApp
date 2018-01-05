package com.keithloughnane.beer.beerapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.keithloughnane.beer.beerapp.BeerViewHolder;
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

    @Inject
    DataAccess dataAccess;

    BaseActivityWithAdapter() {
        super();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

    }


    @Override
    public void downloadComplete() {
        //adapter.notifyDataSetChanged();
        adapter = new Adapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void downloadStarted() {

    }

    class Adapter extends RecyclerView.Adapter<BeerViewHolder> {
        @Override
        public BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BeerViewHolder(new View(getBaseContext()));
        }

        @Override
        public void onBindViewHolder(BeerViewHolder holder, int position) {
            holder.bind(model);
        }

        @Override
        public int getItemCount() {
            return model.beers.size();
        }
    }
}
