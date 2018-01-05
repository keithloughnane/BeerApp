package com.keithloughnane.beer.beerapp;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.keithloughnane.beer.beerapp.activities.BeerModel;
import com.keithloughnane.beer.beerapp.data.Beer;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 04/01/2018.
 */

public class BeerViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.beer_image)
    ImageView beerImage;

    @BindView(R.id.tag_line)
    TextView tagLine;

    @BindView(R.id.abv)
    TextView abv;

    @BindView(R.id.ibu)
    TextView ibu;

    @BindView(R.id.ebc)
    TextView ebc;

    @BindView(R.id.srm)
    TextView srm;

    @BindView(R.id.ph)
    TextView ph;

    public BeerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Beer beer) {
        title.setText(beer.name);
        tagLine.setText(beer.tagline);
        abv.setText("" + beer.abv);
        ibu.setText("" + beer.ibu);
        srm.setText("" + beer.srm);
        ph.setText("" + beer.ph);

        Picasso
                .with(itemView.getContext())
                .load(beer.image_url)
                .fit()
                .centerInside()
                .into(beerImage);
    }
}
