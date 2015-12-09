package ru.samples.itis.rxpractice.samples;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * @author Artur Vasilov
 */
public class Operators {

    @NonNull
    public static Observable<String> mapStrings() {
        return Observable
                .just("Hello", "World")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + " :message from Rx:";
                    }
                });
    }

    @NonNull
    public static Observable<Integer> map() {
        return Observable
                .just("Hello", "World")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                });
    }

    @NonNull
    public static Observable<Integer> multipleMap() {
        return Observable
                .just("Hello", "World")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + " :message from Rx:";
                    }
                })
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                })
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer * 2;
                    }
                });
    }

    @NonNull
    public static Observable<Integer> reduce() {
        return Observable.range(1, 10)
                .reduce(1, new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer, Integer integer2) {
                        return integer * integer2;
                    }
                });
    }

}
