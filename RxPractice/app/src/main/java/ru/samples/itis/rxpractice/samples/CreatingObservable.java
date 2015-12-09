package ru.samples.itis.rxpractice.samples;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * @author Artur Vasilov
 */
public class CreatingObservable {

    @NonNull
    public static Observable<Integer> create() {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(5);
                subscriber.onNext(-10);
                subscriber.onNext(8);
                subscriber.onNext(19);
                subscriber.onCompleted();
            }
        });
    }

    @NonNull
    public static Observable<Integer> from() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(-10);
        list.add(8);
        list.add(19);
        return Observable.from(list);
    }

    @NonNull
    public static Observable<Integer> just() {
        return Observable.just(5, -10, 8, 19);
    }

    @NonNull
    public static Observable<Integer> range() {
        return Observable.range(2, 15);
    }

    @NonNull
    public static Observable<Integer> interval() {
        return Observable.interval(500, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Integer>() {
                    @Override
                    public Integer call(Long aLong) {
                        return aLong.intValue();
                    }
                })
                .take(30, TimeUnit.SECONDS);
    }

}
