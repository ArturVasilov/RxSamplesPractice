package ru.samples.itis.rxpractice.samples;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * @author Artur Vasilov
 */
public class HelloWorld {

    private static final String TAG = HelloWorld.class.getSimpleName();

    public static void helloWorld() {
        Observable<String> observable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("Hello");
                        subscriber.onNext("World");
                        subscriber.onCompleted();
                    }
                });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, s);
            }
        };

        observable.subscribe(subscriber);

        subscriber.unsubscribe();
        observable.subscribe(subscriber);
    }

    public static void helloWorldSimple() {
        Observable<String> observable = Observable.just("Hello", "World");

        Action1<String> action = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG, s);
            }
        };

        observable.subscribe(action);
    }

    public static void helloWorldSimplest() {
        Observable<String> observable = Observable.just("Hello", "World");
        Action1<String> action = s -> Log.i(TAG, s);
        observable.subscribe(action);
    }

    public static void helloWorldRepeated() {
        Observable<String> observable = Observable
                .just("Hello", "World")
                .repeat(2);

        Action1<String> action = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG, s);
            }
        };

        observable.subscribe(action);
    }

    public static void noRxHelloWorld() {
        String[] hellos = {"Hello", "World"};
        for (String s : hellos) {
            Log.i(TAG, s);
        }
    }

}
