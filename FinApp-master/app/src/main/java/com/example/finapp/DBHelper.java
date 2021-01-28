package com.example.finapp;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static int  version = 1;
    private static String  name = "Usuario.db";

    public DBHelper(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strc = "CREATE TABLE Usuario(" +
                        "id integer primary key autoincrement," +
                        "nome varchar(50)," +
                        "usuario varchar(50)," +
                        "senha varchar(50)" +
                        ")";
        db.execSQL(strc);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
