package ru.samples.itis.rxarhictecture.database;

import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * @author Artur Vasilov
 */
public class Person {

    private final String mName;
    private final int mAge;

    public Person(@NonNull String name, int age) {
        mName = name;
        mAge = age;
    }

    @NonNull
    public String getName() {
        return TextUtils.isEmpty(mName) ? "" : mName;
    }

    public int getAge() {
        return mAge;
    }
}
