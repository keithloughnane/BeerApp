package com.keithloughnane.beer.beerapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.keithloughnane.beer.beerapp.UIManager;

import javax.inject.Inject;

/**
 * Created by keith.loughnane@gmail.com on 10/01/2018.
 */

class BaseActivity extends Activity {

    @Inject
    UIManager uiManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiManager.setActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        uiManager.setActivity(this);
    }
}
