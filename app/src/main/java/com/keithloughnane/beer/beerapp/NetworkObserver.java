package com.keithloughnane.beer.beerapp;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by user on 06/01/2018.
 */

public class NetworkObserver {
    public PublishSubject<Boolean> sub = PublishSubject.create();

    NetworkObserver() {

    }
}
