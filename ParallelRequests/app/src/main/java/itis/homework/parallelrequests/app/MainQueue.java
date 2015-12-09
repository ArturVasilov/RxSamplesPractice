package itis.homework.parallelrequests.app;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

/**
 * @author Artur Vasilov
 */
public class MainQueue {

    private static volatile Handler sMainHandler;

    private MainQueue() {
        getMainHandler();
    }

    @NonNull
    public static MainQueue get() {
        return Holder.MAIN_QUEUE;
    }

    @NonNull
    public static Handler getMainHandler() {
        Handler handler = sMainHandler;
        if (sMainHandler == null) {
            synchronized (MainQueue.class) {
                handler = sMainHandler;
                if (sMainHandler == null) {
                    handler = sMainHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        return handler;
    }

    public void invoke(Runnable runnable) {
        sMainHandler.post(runnable);
    }

    private static class Holder {
        private static final MainQueue MAIN_QUEUE = new MainQueue();
    }
}
