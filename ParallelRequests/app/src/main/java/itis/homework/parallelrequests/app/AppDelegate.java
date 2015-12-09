package itis.homework.parallelrequests.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import itis.homework.parallelrequests.network.RequestControllerImpl;
import itis.homework.parallelrequests.network.RequestProcessor;
import itis.homework.parallelrequests.network.RequestsService;

/**
 * @author Artur Vasilov
 */
public class AppDelegate extends Application {

    private RequestsService mRequestsService;

    @Override
    public void onCreate() {
        super.onCreate();
        mRequestsService = new RequestProcessor(new RequestControllerImpl());
    }

    @NonNull
    public static AppDelegate get(Context context) {
        return ((AppDelegate) context.getApplicationContext());
    }

    @NonNull
    public RequestsService getRequestsService() {
        return mRequestsService;
    }
}
