package ru.samples.itis.rxarhictecture;

import android.support.annotation.NonNull;

import rx.Subscriber;
import rx.subjects.PublishSubject;

/**
 * @author Artur Vasilov
 */
public class PublishSubjectEvents {

    private final PublishSubject<Integer> mSubject;

    private PublishSubjectEvents() {
        mSubject = PublishSubject.create();
    }

    @NonNull
    public static PublishSubjectEvents getInstance() {
        return Holder.INSTANCE;
    }

    public void register(Subscriber<Integer> subscriber) {
        mSubject.subscribe(subscriber);
    }

    public void unregister(Subscriber<Integer> subscriber) {
        subscriber.unsubscribe();
    }

    public static void next(int value) {
        getInstance().mSubject.onNext(value);
    }

    private static class Holder {
        private static final PublishSubjectEvents INSTANCE =
                new PublishSubjectEvents();
    }

}


