package ru.samples.itis.rxarhictecture.database;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

/**
 * @author Artur Vasilov
 */
public class PersonTable extends Table {

    private static PersonTable sTable;

    public static PersonTable getTable() {
        PersonTable table = sTable;
        if (table == null) {
            synchronized (PersonTable.class) {
                table = sTable;
                if (table == null) {
                    table = sTable = new PersonTable();
                }
            }
        }
        return table;
    }

    @Override
    public void onCreateTable(@NonNull SQLiteDatabase database) {
        String create = "CREATE TABLE IF NOT EXISTS " + getTableName() + " (" +
                Columns.NAME + " VARCHAR(200), " +
                Columns.AGE + " INTEGER(10)" + ");";
        database.execSQL(create);
    }

    @Override
    int getLastUpdatedVersion() {
        return 0;
    }

    public interface Columns {
        String NAME = "name";
        String AGE = "age";
    }

}
