package ru.samples.itis.rxpractice.samples;

import android.support.annotation.NonNull;

import ru.samples.itis.rxpractice.content.Person;
import rx.Observable;
import rx.functions.Func2;

/**
 * @author Artur Vasilov
 */
public class CombiningObservables {

    @NonNull
    public static Observable<Integer> merge() {
        Observable<Integer> first = Observable.just(1, 2, 3, 4, 5, 6, 7, 8);
        Observable<Integer> second = Observable.just(1, 2, 3, 4, 5, 6, 7, 8);
        return first.mergeWith(second);
    }

    @NonNull
    public static Observable<Integer> mergeIntervals() {
        Observable<Integer> first = CreatingObservable.interval();
        Observable<Integer> second = CreatingObservable.interval();
        return first.mergeWith(second);
    }

    @NonNull
    public static Observable<Integer> concat() {
        Observable<Integer> first = CreatingObservable.interval();
        Observable<Integer> second = CreatingObservable.interval();
        return first.concatWith(second);
    }

    @NonNull
    public static Observable<Person> zip() {
        Observable<String> names = Observable.just("Ivan", "Vasya");
        Observable<Integer> ages = Observable.just(22, 18);
        return Observable.zip(names, ages, new Func2<String, Integer, Person>() {
            @Override
            public Person call(String s, Integer integer) {
                return new Person(s, integer);
            }
        });
    }
}


