package com.keithloughnane.beer.beerapp;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.TextView;

import com.keithloughnane.beer.beerapp.activities.BeerModel;
import com.keithloughnane.beer.beerapp.data.Beer;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 04/01/2018.
 */

public class BeerViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.title)
    TextView title;

    public BeerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Beer beer) {

        //((TextView)itemView.findViewById(R.id.title)).setText(beer.name);
        title.setText(beer.name);
    }
}
