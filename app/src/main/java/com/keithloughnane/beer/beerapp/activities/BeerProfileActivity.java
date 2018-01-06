package com.keithloughnane.beer.beerapp.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.keithloughnane.beer.beerapp.R;
import com.keithloughnane.beer.beerapp.data.Beer;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeerProfileActivity extends AppCompatActivity {

    public static final String BEER_PARAM = "BEER_PARAM";


    @BindView(R.id.beer_image)
    ImageView beerImage;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tag_line)
    TextView tagLine;
    @BindView(R.id.pairing)
    TextView pairing;
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
    @BindView(R.id.extra_details)
    RecyclerView extraDetails;
    @BindView(R.id.brewer_tips)
    TextView brewerTips;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_profile);
        ButterKnife.bind(this);

        Beer beer = (Beer) getIntent().getSerializableExtra(BEER_PARAM);

        Picasso.with(this)
                .load(beer.image_url)
                .into(beerImage);

        title.setText(beer.name);
        tagLine.setText(beer.tagline);
        //pairing.setText(beer.food_pairing.get(0)); //TODO KL: Fix!
        //pairing.setText(Arrays.toString(beer.food_pairing).replaceAll("\\[|\\]", ""));

        String pairingText = "";
        for (String s : beer.food_pairing) {
            pairingText += s + "\n";
        }

        pairing.setText(pairingText);


        abv.setText("" + beer.abv);
        ibu.setText("" + beer.ibu);
        ebc.setText("" + beer.ebc);
        srm.setText("" + beer.srm);
        ph.setText("" + beer.ph);
        brewerTips.setText(beer.brewers_tips);

    }
}
