package ru.samples.itis.rxpractice.samples;

import android.support.annotation.NonNull;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author Artur Vasilov
 */
public class FilteringObservables {

    @NonNull
    public static Observable<BigInteger> filter() {
        return Observable.just(5, 10, 20, 30, 50, 100)
                .map(new Func1<Integer, BigInteger>() {
                    @Override
                    public BigInteger call(Integer integer) {
                        return new BigInteger("2").pow(integer);
                    }
                })
                .filter(new Func1<BigInteger, Boolean>() {
                    @Override
                    public Boolean call(BigInteger bigInteger) {
                        return bigInteger.compareTo(new BigInteger(String.valueOf(Integer.MAX_VALUE))) > 0;
                    }
                });
    }

    @NonNull
    public static Observable<Integer> first() {
        return Observable.just(1, 2, 4, 8, 16, 32)
                .first(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer > 8;
                    }
                });
    }

    @NonNull
    public static Observable<Integer> debounce() {
        return Observable.interval(500, TimeUnit.MILLISECONDS)
                .debounce(2, TimeUnit.SECONDS)
                .map(new Func1<Long, Integer>() {
                    @Override
                    public Integer call(Long aLong) {
                        return aLong.intValue();
                    }
                });
    }

}
