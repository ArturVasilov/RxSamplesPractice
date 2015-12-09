package ru.samples.itis.rxarhictecture.database;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

/**
 * @author Artur Vasilov
 */
public abstract class Table {

    public abstract void onCreateTable(@NonNull SQLiteDatabase database);

    abstract int getLastUpdatedVersion();

    public void onDestroyTable(@NonNull SQLiteDatabase database) {
        String drop = "DROP TABLE IF EXISTS " + getTableName();
        database.execSQL(drop);
    }

    public void onUpgradeTable(@NonNull SQLiteDatabase database, int newVersion) {
        if (getLastUpdatedVersion() >= newVersion) {
            upgradeTable(database);
        }
    }

    @NonNull
    protected String getTableName() {
        return getClass().getSimpleName();
    }

    protected void upgradeTable(@NonNull SQLiteDatabase database) {
        onDestroyTable(database);
        onCreateTable(database);
    }

}
