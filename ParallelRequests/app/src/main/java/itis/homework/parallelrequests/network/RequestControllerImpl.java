package itis.homework.parallelrequests.network;

import java.util.ArrayList;
import java.util.List;

import itis.homework.parallelrequests.app.LoadingStateChangedEvent;
import itis.homework.parallelrequests.app.Otto;

/**
 * @author Artur Vasilov
 */
public class RequestControllerImpl implements RequestsController {

    private final List<RequestType> mLoadingRequests;

    private final List<RequestType> mLoadedRequests;

    private boolean mIsFatalError = false;

    public RequestControllerImpl() {
        mLoadingRequests = new ArrayList<>();
        mLoadedRequests = new ArrayList<>();
    }

    @Override
    public boolean tryRequest(RequestType requestType) {
        if (mIsFatalError) {
            return false;
        }
        if (mLoadingRequests.contains(requestType) || mLoadedRequests.contains(requestType)) {
            return false;
        }

        if (requestType == RequestType.AUTH || requestType == RequestType.CONFIG) {
            mLoadingRequests.add(requestType);
            Otto.get().post(LoadingStateChangedEvent.produce(new RequestsState(RequestsState.GeneralState.LOADING,
                    mLoadingRequests, mLoadedRequests)));
            return true;
        }

        if ((requestType == RequestType.FRIENDS || requestType == RequestType.POSTS
                || requestType == RequestType.MESSAGES || requestType == RequestType.GROUPS)
                && (!mLoadedRequests.contains(RequestType.AUTH) || !mLoadedRequests.contains(RequestType.CONFIG))) {
            mIsFatalError = true;
            Otto.get().post(LoadingStateChangedEvent.produce(new RequestsState(RequestsState.GeneralState.ERROR)));
            return false;
        }

        if (requestType == RequestType.PHOTOS && !mLoadedRequests.contains(RequestType.MESSAGES)) {
            mIsFatalError = true;
            Otto.get().post(LoadingStateChangedEvent.produce(new RequestsState(RequestsState.GeneralState.ERROR)));
            return false;
        }

        mLoadingRequests.add(requestType);
        Otto.get().post(LoadingStateChangedEvent.produce(new RequestsState(RequestsState.GeneralState.LOADING,
                mLoadingRequests, mLoadedRequests)));
        return true;
    }

    @Override
    public void onRequestFinished(RequestType requestType) {
        if (mIsFatalError) {
            Otto.get().post(LoadingStateChangedEvent.produce(new RequestsState(RequestsState.GeneralState.ERROR)));
            return;
        }
        mLoadingRequests.remove(requestType);
        mLoadedRequests.add(requestType);
        if (mLoadedRequests.size() >= RequestType.values().length && mLoadingRequests.isEmpty()) {
            Otto.get().post(LoadingStateChangedEvent.produce(new RequestsState(RequestsState.GeneralState.LOADED)));
        } else {
            Otto.get().post(LoadingStateChangedEvent.produce(new RequestsState(RequestsState.GeneralState.LOADING,
                    mLoadingRequests, mLoadedRequests)));
        }
    }

    @Override
    public void reset() {
        mLoadingRequests.clear();
        mLoadedRequests.clear();
        mIsFatalError = false;

        Otto.get().post(LoadingStateChangedEvent.produce(new RequestsState(RequestsState.GeneralState.WIATING)));
    }
}
