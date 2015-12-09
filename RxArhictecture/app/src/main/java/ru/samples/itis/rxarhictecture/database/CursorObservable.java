package ru.samples.itis.rxarhictecture.database;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class CursorObservable extends Observable<Cursor> {

    private CursorObservable(OnSubscribe<Cursor> f) {
        super(f);
    }

    @NonNull
    public static CursorObservable create(@Nullable Cursor cursor) {
        return new CursorObservable(subscriber -> {
            if (cursor == null || cursor.isClosed() || !cursor.moveToFirst()) {
                subscriber.onCompleted();
                return;
            }

            try {
                do {
                    subscriber.onNext(cursor);
                } while (cursor.moveToNext());
            } finally {
                cursor.close();
                subscriber.onCompleted();
            }
        });
    }

    @NonNull
    public static CursorObservable create(Context context, @NonNull Uri uri, @Nullable String[] projection,
                                          @Nullable String selection, @Nullable String[] selectionArgs,
                                          @Nullable String sortOrder) {
        Cursor cursor = context.getContentResolver()
                .query(uri, projection, selection, selectionArgs, sortOrder);
        return create(cursor);
    }

    @NonNull
    public static CursorObservable create(Context context, @NonNull Uri uri) {
        return create(context, uri, null, null, null, null);
    }
}


