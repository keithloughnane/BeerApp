package com.keithloughnane.beer.beerapp.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by keith.loughnane@gmail.com on 06/01/2018.
 */

public class NetworkObserver {
    public final BehaviorSubject<Boolean> sub = BehaviorSubject.create();

    public NetworkObserver(Context context, final BeerLogger logger) {
        final IntentFilter netIntentFilter = new IntentFilter();
        netIntentFilter.addAction(android.net.ConnectivityManager.CONNECTIVITY_ACTION);

        BroadcastReceiver mIRNetwork = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean connected = !intent.getBooleanExtra(ConnectivityManager
                        .EXTRA_NO_CONNECTIVITY, false);

                logger.d(connected ? "CONNECTED TO INTERNET" : "DISCONNECTED FROM INTERNET");
                sub.onNext(connected);
            }
        };

        context.registerReceiver(mIRNetwork, netIntentFilter);
    }
}
