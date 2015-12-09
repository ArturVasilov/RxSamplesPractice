package itis.homework.parallelrequests.network;

import android.support.annotation.StringRes;

import itis.homework.parallelrequests.R;

/**
 * @author Artur Vasilov
 */
public enum RequestType {

    CONFIG(2_000, R.string.config_request),
    AUTH(3_000, R.string.auth_request),
    FRIENDS(6_000, R.string.friends_request),
    POSTS(10_000, R.string.posts_request),
    GROUPS(6_000, R.string.groups_request),
    MESSAGES(8_000, R.string.messages_request),
    PHOTOS(12_000, R.string.photos_request),
    ;

    private final long mDelay;

    @StringRes
    private final int mRequestText;

    RequestType(long delay, @StringRes int requestText) {
        mDelay = delay;
        mRequestText = requestText;
    }

    public long getDelay() {
        return mDelay;
    }

    @StringRes
    public int getRequestText() {
        return mRequestText;
    }
}
