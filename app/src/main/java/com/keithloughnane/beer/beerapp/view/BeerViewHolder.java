package com.keithloughnane.beer.beerapp.view;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.keithloughnane.beer.beerapp.R;
import com.keithloughnane.beer.beerapp.controllers.ControllerWithAdapter;
import com.keithloughnane.beer.beerapp.data.Beer;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static android.support.v4.content.ContextCompat.getDrawable;
import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by keith.loughnane@gmail.com on 04/01/2018.
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

    @BindView(R.id.ph)
    TextView ph;

    @BindView(R.id.favorite)
    ImageButton favorite;

    public BeerViewHolder(View itemView, ControllerWithAdapter controller) {
        super(itemView);
        this.controller = controller;
        ButterKnife.bind(this, itemView);
    }

    public void bind(final Beer beer) { //TODO KL: Deal with subs
        title.setText(beer.name);
        tagLine.setText(beer.tagline);

        abv.setText(itemView.getResources().getString(R.string.abv_s, beer.abv));
        ibu.setText(itemView.getResources().getString(R.string.ibu_s, beer.ibu));
        ebc.setText(itemView.getResources().getString(R.string.ebc_s, beer.srm));
        ph.setText(itemView.getResources().getString(R.string.ph_s, beer.ph));

        setFavoriteIcon(beer);

        Picasso
                .with(itemView.getContext())
                .load(beer.imageUrl)
                .fit()
                .centerInside()
                .into(beerImage);

        setFavoriteIcon(beer);

        RxView.clicks(favorite)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        beer.favorite = !beer.favorite;
                        setFavoriteIcon(beer);
                    }
                })
                .map(new Function<Object, Beer>() {
                    @Override
                    public Beer apply(Object o) throws Exception {
                        return beer;
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(controller.favoriteClick);

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
                        intent.putExtra(BeerProfileActivity.BEER_PARAM, beer);
                        startActivity(itemView.getContext(), intent, null);
                    }
                })
                .subscribe(controller.holderClick);
    }

    private void setFavoriteIcon(Beer beer) {
        favorite.setBackground(getDrawable(itemView.getContext(), beer.favorite ? R.drawable.favorite_checked : R.drawable.favorite_unchecked));
    }
}
