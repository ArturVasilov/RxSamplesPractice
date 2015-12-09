package itis.homework.parallelrequests.network;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Vasilov
 */
public class RequestsState {

    private final GeneralState mState;

    private final List<RequestType> mLoadingRequests;

    private final List<RequestType> mLoadedRequest;

    public RequestsState(GeneralState state) {
        this(state, new ArrayList<RequestType>(), new ArrayList<RequestType>());
    }

    public RequestsState(GeneralState state, @NonNull List<RequestType> loadingRequests, @NonNull List<RequestType> loadedRequest) {
        mState = state;
        mLoadingRequests = loadingRequests;
        mLoadedRequest = loadedRequest;
    }

    public boolean isWaiting() {
        return mState == GeneralState.WIATING;
    }

    public boolean isLoading() {
        return mState == GeneralState.LOADING;
    }

    public boolean isLoaded() {
        return mState == GeneralState.LOADED;
    }

    public boolean isError() {
        return mState == GeneralState.ERROR;
    }

    public boolean isRequestLoading(RequestType requestType) {
        return mLoadingRequests.contains(requestType);
    }

    public boolean isRequestLoaded(RequestType requestType) {
        return mLoadedRequest.contains(requestType);
    }

    public enum GeneralState {
        WIATING,
        LOADING,
        LOADED,
        ERROR
    }

}
