package ru.samples.itis.rxpractice.tasks;

import android.support.annotation.NonNull;

import java.util.Comparator;
import java.util.List;

import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class HomeTask {

    /**
     * TODO : 1
     *
     * Rewrite all methods using lambdas, method references and etc (if not done yet)
     */

    /**
     * TODO : 2
     *
     * compare all items in observables, put result sequence into one integer observable
     */
    @NonNull
    public static <T> Observable<Integer> compareObservableItems(Observable<T> first,
                                                                 Observable<T> second,
                                                                 Comparator<T> comparator) {
        throw new RuntimeException("Observable not implemented exception");
    }

    /**
     * TODO : 3
     *
     * return observable with single integer represents Student's statistics value
     * for two samples to determine if samples are different
     */
    @NonNull
    public static Observable<Double> studentStatistics(List<Double> first, List<Double> second) {
        throw new RuntimeException("Observable not implemented exception");
    }

}


