package ru.samples.itis.rxpractice.tasks;

import android.support.annotation.NonNull;

import java.math.BigInteger;

import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class RxJavaTask2 {

    /**
     * TODO : implement this method
     *
     * Take the range of integers [1..100000] and apply next functions:
     * 1) Multiply all elements by 2
     * 2) Remove 40 000 elements from start and 40 000 elements from end
     * 3) Remove all values which is not divided by 3
     * 4) Transform the left sequence of integers into sequence of BigInteger objects
     * 5) Multiply all values into single BigInteger object
     * 6) Cache the result
     *
     * @return observable with cached result of single BigInteger object
     */
    @NonNull
    public static Observable<BigInteger> task2Observable() {
        throw new RuntimeException("Observable not implemented exception");
    }

}




















































































































/*
        return Observable.range(1, 100_000)
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer * 2;
                    }
                })
                .skip(40_000)
                .take(20_000)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer % 3 == 0;
                    }
                })
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return String.valueOf(integer);
                    }
                })
                .map(new Func1<String, BigInteger>() {
                    @Override
                    public BigInteger call(String s) {
                        return new BigInteger(s);
                    }
                })
                .reduce(BigInteger.ONE, new Func2<BigInteger, BigInteger, BigInteger>() {
                    @Override
                    public BigInteger call(BigInteger bigInteger, BigInteger bigInteger2) {
                        return bigInteger.multiply(bigInteger2);
                    }
                })
                .first()
                .cache();




        return Observable.range(1, 100_000)
                .map(integer -> integer * 2)
                .skip(40_000)
                .skipLast(40_000)
                .filter(integer -> integer % 3 == 0)
                .map(BigInteger::valueOf)
                .reduce(BigInteger::multiply)
                .cache();
 */
