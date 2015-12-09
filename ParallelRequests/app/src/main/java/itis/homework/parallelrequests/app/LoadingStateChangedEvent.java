package itis.homework.parallelrequests.app;

import android.support.annotation.NonNull;

import com.squareup.otto.Produce;

import itis.homework.parallelrequests.network.RequestsState;

/**
 * @author Artur Vasilov
 */
public class LoadingStateChangedEvent {

    private final RequestsState mState;

    private LoadingStateChangedEvent(RequestsState state) {
        mState = state;
    }

    @Produce
    @NonNull
    public static LoadingStateChangedEvent produce(@NonNull RequestsState state) {
        return new LoadingStateChangedEvent(state);
    }

    @NonNull
    public RequestsState getState() {
        return mState;
    }
}
