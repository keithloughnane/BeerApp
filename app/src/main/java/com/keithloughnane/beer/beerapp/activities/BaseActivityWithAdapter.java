package com.keithloughnane.beer.beerapp.activities;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.keithloughnane.beer.beerapp.BeerViewHolder;

/**
 * Created by user on 04/01/2018.
 */

abstract class BaseActivityWithAdapter<B, M> extends BaseActivity {

    private final Adapter adapter;

    BaseActivityWithAdapter() {
        super();
        adapter = new Adapter();
    }

    class Adapter extends RecyclerView.Adapter<BeerViewHolder> {
        @Override
        public BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(BeerViewHolder holder, int position) {
            holder.bind(model);
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
