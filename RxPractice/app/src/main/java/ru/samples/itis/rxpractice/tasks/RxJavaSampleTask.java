package ru.samples.itis.rxpractice.tasks;

import android.support.annotation.NonNull;

import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class RxJavaSampleTask {

    /**
     * Sample method for practice - for demo
     *
     * @param version - current version name
     * @return is device has Android version greater than 21
     */
    @NonNull
    public static Observable<Boolean> isLollipopOrHigher(int version) {
        return Observable.just(version >= 21);
    }

}


