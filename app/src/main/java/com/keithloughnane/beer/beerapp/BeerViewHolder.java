package com.keithloughnane.beer.beerapp;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.CollapsibleActionView;
import android.view.View;

import com.keithloughnane.beer.beerapp.activities.BeerModel;

/**
 * Created by user on 04/01/2018.
 */

public class BeerViewHolder extends RecyclerView.ViewHolder {
    public BeerViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(BeerModel model) {
        itemView.setMinimumHeight(100);
        itemView.setMinimumWidth(100);
        itemView.setBackgroundColor(Color.RED);
    }
}
