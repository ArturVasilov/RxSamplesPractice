package ru.samples.itis.rxarhictecture;

import rx.Subscriber;

/**
 * @author Artur Vasilov
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
        // Do nothing
    }

    @Override
    public void onError(Throwable e) {
        // Do nothing
    }
}
