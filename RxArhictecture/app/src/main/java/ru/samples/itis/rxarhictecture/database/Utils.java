package ru.samples.itis.rxarhictecture.database;

import android.database.Cursor;
import android.support.annotation.NonNull;

/**
 * @author Artur Vasilov
 */
public class Utils {

    public static String safeStringFromCursor(@NonNull Cursor cursor, @NonNull String column) {
        try {
            return cursor.getString(cursor.getColumnIndex(column));
        } catch (Exception e) {
            return "";
        }
    }

    public static int safeIntFromCursor(@NonNull Cursor cursor, @NonNull String column) {
        try {
            return cursor.getInt(cursor.getColumnIndex(column));
        } catch (Exception e) {
            return 0;
        }
    }
}
