package itis.homework.parallelrequests.network;

import android.os.Looper;
import android.os.NetworkOnMainThreadException;
import android.os.SystemClock;
import android.support.annotation.NonNull;

import rx.Observable;
import rx.Subscriber;

/**
 * @author Artur Vasilov
 */
public class RequestProcessor implements RequestsService {

    private final RequestsController mRequestsController;

    public RequestProcessor(RequestsController requestsController) {
        mRequestsController = requestsController;
    }

    @Override
    public Observable<Boolean> config() {
        return process(RequestType.CONFIG);
    }

    @Override
    public Observable<Boolean> auth() {
        return process(RequestType.AUTH);
    }

    @Override
    public Observable<Boolean> friends() {
        return process(RequestType.FRIENDS);
    }

    @Override
    public Observable<Boolean> posts() {
        return process(RequestType.POSTS);
    }

    @Override
    public Observable<Boolean> groups() {
        return process(RequestType.GROUPS);
    }

    @Override
    public Observable<Boolean> messages() {
        return process(RequestType.MESSAGES);
    }

    @Override
    public Observable<Boolean> photos() {
        return process(RequestType.PHOTOS);
    }

    @Override
    public void reset() {
        mRequestsController.reset();
    }

    @NonNull
    private Observable<Boolean> process(RequestType requestType) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    throw new NetworkOnMainThreadException();
                }

                if (!mRequestsController.tryRequest(requestType)) {
                    subscriber.onError(new Throwable());
                }

                SystemClock.sleep(requestType.getDelay());
                mRequestsController.onRequestFinished(requestType);

                subscriber.onCompleted();
            }
        });
    }
}
