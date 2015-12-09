package itis.homework.parallelrequests.app;

import android.os.Looper;
import android.support.annotation.NonNull;

import com.squareup.otto.Bus;

/**
 * @author Artur Vasilov
 */
public class Otto {

    private final Bus mBus;

    private Otto(Bus bus) {
        mBus = bus;
    }

    @NonNull
    public static Otto get() {
        return Holder.INSTANCE;
    }

    public void post(final @NonNull Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            mBus.post(event);
        }
        else {
            MainQueue.get().invoke(new Runnable() {
                @Override
                public void run() {
                    mBus.post(event);
                }
            });
        }
    }

    public void register(@NonNull Object object) {
        mBus.register(object);
    }

    public void unregister(@NonNull Object object) {
        mBus.unregister(object);
    }

    private static final class Holder {
        public static final Otto INSTANCE = new Otto(new Bus());
    }
}
