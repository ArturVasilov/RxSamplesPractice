package ru.samples.itis.rxarhictecture.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import static ru.samples.itis.rxarhictecture.database.Utils.safeIntFromCursor;
import static ru.samples.itis.rxarhictecture.database.Utils.safeStringFromCursor;

/**
 * @author Artur Vasilov
 */
public class PersonsProvider {

    public static final Uri URI = DatabaseProvider.BASE_CONTENT_URI
            .buildUpon()
            .appendPath(PersonTable.getTable().getTableName())
            .build();


    public static void save(Context context, @NonNull Person person) {
        context.getContentResolver().insert(URI, toContentValues(person));
    }

    public static void save(Context context, @NonNull List<Person> persons) {
        ContentValues[] values = new ContentValues[persons.size()];
        for (int i = 0; i < persons.size(); i++) {
            values[i] = toContentValues(persons.get(i));
        }
        context.getContentResolver().bulkInsert(URI, values);
    }

    @NonNull
    public static ContentValues toContentValues(@NonNull Person person) {
        ContentValues values = new ContentValues();
        values.put(PersonTable.Columns.NAME, person.getName());
        values.put(PersonTable.Columns.AGE, person.getAge());
        return values;
    }

    @NonNull
    public static Person fromCursor(@NonNull Cursor cursor) {
        String name = safeStringFromCursor(cursor, PersonTable.Columns.NAME);
        int age = safeIntFromCursor(cursor, PersonTable.Columns.AGE);
        return new Person(name, age);
    }

    @NonNull
    public static List<Person> listFromCursor(@NonNull Cursor cursor) {
        List<Person> airports = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return airports;
        }
        try {
            do {
                airports.add(fromCursor(cursor));
            } while (cursor.moveToNext());
            return airports;
        } finally {
            cursor.close();
        }
    }

    public static void clear(Context context) {
        context.getContentResolver().delete(URI, null, null);
    }

}
