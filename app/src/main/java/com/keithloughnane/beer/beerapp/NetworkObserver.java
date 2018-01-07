package com.keithloughnane.beer.beerapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.keithloughnane.beer.beerapp.dataAccess.BeerLogger;

import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by keith.loughnane@gmail.com on 06/01/2018.
 */

public class NetworkObserver {
    public BehaviorSubject<Boolean> sub = BehaviorSubject.create().create();

    NetworkObserver(Context context, final BeerLogger logger) {
        sub.onNext(true);

        final IntentFilter netIntentFilter = new IntentFilter();
        netIntentFilter.addAction(android.net.ConnectivityManager.CONNECTIVITY_ACTION); //"android.net.conn.CONNECTIVITY_CHANGE"

        BroadcastReceiver mIRNetwork = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean connected = !intent.getBooleanExtra(ConnectivityManager
                        .EXTRA_NO_CONNECTIVITY, false);

                logger.d(connected ? "CONNECTED TO INTERNET" : "DISCONNECTED FROM INTERNET"); //TODO KL: Resources file
                sub.onNext(connected);
            }
        };

        context.registerReceiver(mIRNetwork, netIntentFilter);
    }
}
