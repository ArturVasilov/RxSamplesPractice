package ru.samples.itis.rxpractice.tasks;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class RxJavaTask1 {

    /**
     * TODO : implement this method
     *
     * This method takes list of strings and creates an observable of integers,
     * that represents length of strings which contains letter 'r' (or 'R')
     *
     * @param list - list of string
     * @return integer observable with strings length
     */
    @NonNull
    public static Observable<Integer> task1Observable(@NonNull List<String> list) {
        throw new RuntimeException("Observable not implemented exception");
    }

}
































































































































/*
return Observable.from(list)
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s.contains("r") || s.contains("R");
                    }
                })
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.length();
                    }
                });


        return Observable.from(list)
                .filter(s -> s.contains("r") || s.contains("R"))
                .map(String::length);
 */


