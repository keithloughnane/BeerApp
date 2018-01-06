package com.keithloughnane.beer.beerapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.keithloughnane.beer.beerapp.activities.BeerModel;
import com.keithloughnane.beer.beerapp.activities.BeerProfileActivity;
import com.keithloughnane.beer.beerapp.activities.Controller;
import com.keithloughnane.beer.beerapp.activities.FavoriteActivity;
import com.keithloughnane.beer.beerapp.data.Beer;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by user on 04/01/2018.
 */

public class BeerViewHolder extends RecyclerView.ViewHolder {
    private final ControllerWithAdapter controller;

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

    public BeerViewHolder(View itemView, ControllerWithAdapter controller) {
        super(itemView);
        this.controller = controller;
        ButterKnife.bind(this, itemView);

    }

    public void bind(final Beer beer) {
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

        RxView.clicks(itemView)
                .map(new Function<Object, Beer>() {

                    @Override
                    public Beer apply(Object o) throws Exception {
                        return beer;
                    }
                })
                .doOnNext(new Consumer<Beer>() {
                    @Override
                    public void accept(Beer beer) throws Exception {
                        Intent intent = new Intent(itemView.getContext(), BeerProfileActivity.class);

                        /*
                                Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
                         */

                        intent.putExtra(BeerProfileActivity.BEER_PARAM, beer);
                        startActivity(itemView.getContext(), intent, null);
                    }
                })
                .subscribe(controller.holderClick);
    }
}
