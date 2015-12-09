package ru.samples.itis.rxarhictecture;

import android.content.Context;
import android.content.Loader;

import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Artur Vasilov
 */
public class RxWeatherLoader extends Loader<List<Weather>> {

    private Subscription mSubscription;

    private Observable<List<Weather>> mWeatherObservable;

    public RxWeatherLoader(Context context) {
        super(context);

        Observable<Weather> kazan = ApiFactory.getWeatherService()
                .weather("Kazan", BuildConfig.API_KEY);

        Observable<Weather> moscow = ApiFactory.getWeatherService()
                .weather("Moscow", BuildConfig.API_KEY);

        mWeatherObservable = Observable.merge(kazan, moscow)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .cache();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();

        mSubscription = mWeatherObservable.subscribe(this::deliverResult);
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        mSubscription.unsubscribe();
    }
}



