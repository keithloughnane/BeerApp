package com.keithloughnane.beer.beerapp;

import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by user on 06/01/2018.
 */

public class NetworkObserver {
    public BehaviorSubject<Boolean> sub = BehaviorSubject.create().create();

    NetworkObserver() {
        sub.onNext(true);
    }
}
