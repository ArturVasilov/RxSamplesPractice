package ru.samples.itis.rxarhictecture;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;

/**
 * @author Artur Vasilov
 */
public class Samples {

    @NonNull
    public static Observable<Boolean> isLollipopOrHigher() {
        return Observable.just(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
    }

    @NonNull
    public static Observable<String> descriptionUrl() {
        return isLollipopOrHigher()
                .map(isLollipopOrHigher -> isLollipopOrHigher
                        ? "http://developer.android.com/about/versions/android-5.0.html"
                        : "http://developer.android.com/about/versions/android-4.0.html");
    }

    public static void publishSubjectSample() {
        PublishSubject<Integer> subject = PublishSubject.create();
        subject.onNext(5);

        Action1<Integer> action1 = integer -> Log.i("From action1", String.valueOf(integer));
        Subscription subscription1 = subject.subscribe(action1);
        subject.onNext(10);

        Action1<Integer> action2 = integer -> Log.i("From action2", String.valueOf(integer));
        Subscription subscription2 = subject.subscribe(action2);
        subject.onNext(20);

        subscription1.unsubscribe();
        subject.onNext(40);

        subscription2.unsubscribe();
        subject.onNext(80);
    }

    public static void publishSubjectSample2() {
        Observable<String> seconds = Observable.interval(1, TimeUnit.SECONDS)
                .take(5, TimeUnit.SECONDS)
                .map(String::valueOf);

        Subject<String, String> subject = PublishSubject.create();
        Subscription subjectSubscription = seconds.subscribe(subject);

        final Subscription subscription = subject.subscribe(s -> Log.i(s, s));
        subject.subscribe(s -> Log.i(s, s));

        new Handler(Looper.getMainLooper()).postDelayed(
                subscription::unsubscribe, 2_000);
        new Handler(Looper.getMainLooper()).postDelayed(
                subjectSubscription::unsubscribe, 3_000);
    }

    public static void replaySubject() {
        ReplaySubject<Integer> subject = ReplaySubject.create();
        subject.onNext(5);

        Action1<Integer> action1 = integer -> Log.i("From action1", String.valueOf(integer));
        Subscription subscription1 = subject.subscribe(action1);
        subject.onNext(10);

        Action1<Integer> action2 = integer -> Log.i("From action2", String.valueOf(integer));
        Subscription subscription2 = subject.subscribe(action2);
        subject.onNext(20);

        subscription1.unsubscribe();
        subject.onNext(40);

        subscription2.unsubscribe();
        subject.onNext(80);
    }

    public static void behaviourSubject() {
        BehaviorSubject<Integer> subject = BehaviorSubject.create();
        subject.onNext(5);

        Action1<Integer> action1 = integer -> Log.i("From action1", String.valueOf(integer));
        Subscription subscription1 = subject.subscribe(action1);
        subject.onNext(10);

        Action1<Integer> action2 = integer -> Log.i("From action2", String.valueOf(integer));
        Subscription subscription2 = subject.subscribe(action2);
        subject.onNext(20);

        subscription1.unsubscribe();
        subject.onNext(40);

        subscription2.unsubscribe();
        subject.onNext(80);
    }

    public static void hotObservable() {
        Observable<Integer> observable = Observable.create(subscriber -> {
            Log.i("Sample", "Creating obseravble");
            subscriber.onNext(10);
            subscriber.onCompleted();
        });

        observable.subscribe();

        ConnectableObservable<Integer> hot = observable.publish();
        hot.subscribe(integer -> Log.i("Sample", "aaa"));
        hot.subscribe(integer -> Log.i("Sample", "bbb"));
        hot.subscribe(integer -> Log.i("Sample", "ccc"));
        //call connect to emit the same stream for all subscribers
        hot.connect();
    }

}
