package com.keithloughnane.beer.beerapp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.keithloughnane.beer.beerapp.R;
import com.keithloughnane.beer.beerapp.data.Beer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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

        pairing.setText(buildFoodPairingString(beer.food_pairing));

        abv.setText(getResources().getString(R.string.abv_s, beer.abv));
        ibu.setText(getResources().getString(R.string.ibu_s, beer.ibu));
        ebc.setText(getResources().getString(R.string.ebc_s, beer.srm));
        srm.setText(getResources().getString(R.string.srm_s, beer.srm));
        ph.setText(getResources().getString(R.string.ph_s, beer.ph));

        brewerTips.setText(beer.brewers_tips);
    }

    String buildFoodPairingString(ArrayList<String> input) {
        StringBuilder pairingTextBuilder = new StringBuilder();
        for (String s : input) {
            pairingTextBuilder.append(s).append("\n");
        }
        String pairingText = pairingTextBuilder.toString();
        return pairingText.substring(0, pairingText.length() - 1);
    }
}
