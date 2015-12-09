package itis.homework.parallelrequests.network;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Artur Vasilov
 */
public class ThreadUtils {

    private static final long RETRY_DELAY = 100;
    private static final int MINIMUM_POOL_SIZE = 4;
    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private static final int MAXIMUM_POOL_SIZE = CORE_POOL_SIZE > 2 ? CORE_POOL_SIZE * 2 : MINIMUM_POOL_SIZE;
    private static final int KEEP_ALIVE_TIME = 10;

    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    private static final RejectedExecutionHandler REJECTED_EXECUTION_HANDLER = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(final Runnable runnable, ThreadPoolExecutor executor) {
            HANDLER.postDelayed(() -> THREAD_POOL_EXECUTOR.execute(runnable), RETRY_DELAY);
        }
    };

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE * 2,
            MAXIMUM_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(MAXIMUM_POOL_SIZE),
            REJECTED_EXECUTION_HANDLER);

    @NonNull
    public static Thread runInBackground(@NonNull final Runnable runnable) {
        Thread thread = new Thread(runnable);
        THREAD_POOL_EXECUTOR.execute(thread);
        return thread;
    }
}
