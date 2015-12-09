package itis.homework.parallelrequests;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import itis.homework.parallelrequests.app.AppDelegate;
import itis.homework.parallelrequests.network.RequestsService;
import itis.homework.parallelrequests.network.ThreadUtils;

/**
 * @author Artur Vasilov
 */
public class SampleService extends IntentService {

    private boolean mIsConfigLoaded = false;
    private boolean mIsAuthLoaded = false;

    public static void start(Context context) {
        Intent intent = new Intent(context, SampleService.class);
        context.startService(intent);
    }

    private RequestsService mRequestsService;

    public SampleService() {
        super(SampleService.class.getName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mRequestsService = AppDelegate.get(this).getRequestsService();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mRequestsService.reset();

        ThreadUtils.runInBackground(new Runnable() {
            @Override
            public void run() {
                mRequestsService.config();
                mIsConfigLoaded = true;
                tryLoadSecondState();
            }
        });

        ThreadUtils.runInBackground(new Runnable() {
            @Override
            public void run() {
                mRequestsService.auth();
                mIsAuthLoaded = true;
                tryLoadSecondState();
            }
        });

        mRequestsService.auth();

        mRequestsService.friends();
        mRequestsService.posts();
        mRequestsService.messages();
        mRequestsService.groups();

        mRequestsService.photos();
    }

    private void tryLoadSecondState() {
        if (mIsAuthLoaded && mIsConfigLoaded) {
            //TODO : load requests from second state
        }
    }
}
