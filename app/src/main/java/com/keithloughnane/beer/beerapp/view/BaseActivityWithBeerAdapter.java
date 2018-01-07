package com.keithloughnane.beer.beerapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.keithloughnane.beer.beerapp.R;
import com.keithloughnane.beer.beerapp.controllers.ControllerWithAdapter;
import com.keithloughnane.beer.beerapp.dataAccess.local.DataAccess;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by keith.loughnane@gmail.com on 04/01/2018.
 */

abstract class BaseActivityWithBeerAdapter<M,C> extends BaseActivity implements BeerView {

    @Inject
    DataAccess dataAccess;
    @BindView(R.id.content_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.failureIcon)
    View failure;
    private Adapter adapter;

    BaseActivityWithBeerAdapter() {
        super();
    }

    class Adapter extends RecyclerView.Adapter<BeerViewHolder> {
        @Override
        public BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BeerViewHolder(LayoutInflater.from(getBaseContext()).inflate(R.layout.beer_view_holder, null), (ControllerWithAdapter) controller); //TODO KL: Refactor to avoid cast
        }

        @Override
        public void onBindViewHolder(BeerViewHolder holder, int position) {
            holder.bind(model.beers.get(position));
        }

        @Override
        public int getItemCount() {
            return model.beers.size();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this);
        adapter = new Adapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void downloadComplete() {
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);

        if (model.beers.size() == 0) {
            failure.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            failure.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void downloadStarted() {
        recyclerView.setVisibility(View.GONE);
        failure.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }
}
