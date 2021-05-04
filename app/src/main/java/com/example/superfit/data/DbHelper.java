package com.example.superfit.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Класс для работы с БД
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = DbHelper.class.getSimpleName();

    //имя файла бд
    private static final String DATABASE_NAME = "superfit.db";

    //версия БД
    private static final int DATABASE_VERSION = 1;

    public DbHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_USER_TABLE = "CREATE TABLE " + Contract.UserEntry.TABLE_NAME + " ("
                +  Contract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +  Contract.UserEntry.COLUMN_NAME + " TEXT NOT NULL, "
                +  Contract.UserEntry.COLUMN_EMAIL + " TEXT NOT NULL, "
                +  Contract.UserEntry.COLUMN_CODE + " INTEGER NOT NULL, "
                +  Contract.UserEntry.COLUMN_WEIGHT + " REAL NOT NULL DEFAULT 0, "
                + Contract.UserEntry.COLUMN_HEIGHT+ " REAL NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // запись в журнал
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);

        // удаление старой таблицы и создание новой
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        // создание новой таблицы
        onCreate(db);
    }
}
