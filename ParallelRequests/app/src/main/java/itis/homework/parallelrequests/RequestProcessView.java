package itis.homework.parallelrequests;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import itis.homework.parallelrequests.app.LoadingStateChangedEvent;
import itis.homework.parallelrequests.app.MainQueue;
import itis.homework.parallelrequests.app.Otto;
import itis.homework.parallelrequests.network.RequestType;
import itis.homework.parallelrequests.network.RequestsState;

/**
 * @author Artur Vasilov
 */
public class RequestProcessView extends LinearLayout {

    private TextView mTimerTextView;
    private ViewGroup mStatusViewContainer;

    private final Map<RequestType, RequestStatusView> mStatusViews = new HashMap<>();

    private long mLoadingStartedTime;

    private TimerTask mTimerTask;

    private Timer mTimer;

    public RequestProcessView(Context context) {
        super(context);
        init();
    }

    public RequestProcessView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RequestProcessView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.request_processor_view, this);
        mTimerTextView = (TextView) findViewById(R.id.timerTextView);
        mStatusViewContainer = (ViewGroup) findViewById(R.id.requestsStatusViewContainer);

        mTimer = new Timer();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mLoadingStartedTime = System.currentTimeMillis();
        Otto.get().register(this);

        setupViews();
        setup(new RequestsState(RequestsState.GeneralState.WIATING));
    }

    @Override
    protected void onDetachedFromWindow() {
        clearTimer();
        Otto.get().unregister(this);
        super.onDetachedFromWindow();
    }

    @Subscribe
    public void onLoadingStateChanged(LoadingStateChangedEvent event) {
        setup(event.getState());
    }

    private void setupViews() {
        for (RequestType requestType : RequestType.values()) {
            RequestStatusView view = mStatusViews.get(requestType);
            if (view == null) {
                view = new RequestStatusView(getContext());
                mStatusViewContainer.addView(view);
            }
            mStatusViews.put(requestType, view);
        }
    }

    private void setup(@NonNull RequestsState state) {
        if (state.isWaiting()) {
            onWaiting();
        } else if (state.isLoaded()) {
            onLoaded();
        } else if (state.isError()) {
            onError();
        } else {
            onStartLoading();
            for (RequestType requestType : RequestType.values()) {
                RequestStatusView view = mStatusViews.get(requestType);
                if (state.isRequestLoaded(requestType)) {
                    view.loaded(requestType.getRequestText());
                } else if (state.isRequestLoading(requestType)) {
                    view.loading(requestType.getRequestText());
                } else {
                    view.waiting(requestType.getRequestText());
                }
                mStatusViews.put(requestType, view);
            }
        }
    }

    private void onWaiting() {
        for (RequestType requestType : RequestType.values()) {
            RequestStatusView view = mStatusViews.get(requestType);
            view.waiting(requestType.getRequestText());
        }
        clearTimer();
    }

    private void onError() {
        for (RequestType requestType : RequestType.values()) {
            RequestStatusView view = mStatusViews.get(requestType);
            view.error(requestType.getRequestText());
        }
        stopTimer();
    }

    private void onLoaded() {
        for (RequestType requestType : RequestType.values()) {
            RequestStatusView view = mStatusViews.get(requestType);
            view.loaded(requestType.getRequestText());
        }
        stopTimer();
    }

    private void onStartLoading() {
        if (mTimerTask == null) {
            startTimer();
        }
    }

    private void startTimer() {
        mLoadingStartedTime = System.currentTimeMillis();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                long timeDiff = System.currentTimeMillis() - mLoadingStartedTime;
                final int seconds = (int) (timeDiff / DateUtils.SECOND_IN_MILLIS);
                final int millis = (int) (timeDiff - seconds * DateUtils.SECOND_IN_MILLIS);
                MainQueue.get().invoke(new Runnable() {
                    @Override
                    public void run() {
                        mTimerTextView.setText(String.format("%1$s,%2$s", seconds, (int) (Math.ceil(millis / 100))));
                    }
                });
            }
        };
        mTimer.scheduleAtFixedRate(mTimerTask, 0, 50);
    }

    private void clearTimer() {
        mTimerTextView.setText("0");
        stopTimer();
    }

    private void stopTimer() {
        if (mTimerTask != null) {
            mTimerTask.cancel();
        }
    }
}
