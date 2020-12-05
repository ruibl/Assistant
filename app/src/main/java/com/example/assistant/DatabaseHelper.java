package com.example.assistant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlt="CREATE TABLE Teacher(" +
                "TNo text primary key," +
                "Tpwd text not null," +
                "TName text not null," +
                "Tphone text not null," +
                "Tclass text not null);";
        Log.i("Exo4:","createDB="+sqlt);
        db.execSQL(sqlt);

        String sqls="CREATE TABLE Student(" +
                "SNo text primary key," +
                "SName text not null," +
                "Sclass text not null," +
                "Sphone text not null," +
                "Spwd text not null);";
        Log.i("Exo4:","createDB="+sqls);
        db.execSQL(sqls);

        String sqlp="CREATE TABLE Parents(" +
                "Pname text primary key," +
                "Ppwd text not null," +
                "Pphone text not null," +
                "Pclass text not null," +
                "PSname text not null);";
        Log.i("Exo4:","createDB="+sqlp);
        db.execSQL(sqlp);

        String sqlleave="CREATE TABLE Leave(" +
                "Lno text primary key," +
                "Lna text not null," +
                "Lcl text not null," +
                "Lph text not null," +
                "Lle text not null," +
                "Lba text not null," +
                "Lth text not null," +
                "Lps text," +
                "Lts text);";
        Log.i("Exo4:","createDB="+sqlleave);
        db.execSQL(sqlleave);









    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }
}
