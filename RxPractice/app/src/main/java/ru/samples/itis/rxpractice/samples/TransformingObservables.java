package ru.samples.itis.rxpractice.samples;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author Artur Vasilov
 */
public class TransformingObservables {

    @NonNull
    public static Observable<Integer> flatMap(Observable<List<String>> listObservable) {
        return listObservable
                .flatMap(new Func1<List<String>, Observable<String>>() {
                    @Override
                    public Observable<String> call(List<String> list) {
                        return Observable.from(list);
                    }
                })
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.length();
                    }
                });
    }

}
