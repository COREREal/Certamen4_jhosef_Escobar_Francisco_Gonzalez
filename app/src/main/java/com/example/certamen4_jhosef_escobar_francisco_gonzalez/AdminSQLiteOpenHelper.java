package com.example.certamen4_jhosef_escobar_francisco_gonzalez;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    @Override
    public void onCreate(SQLiteDatabase Autos) {
        Autos.execSQL("Create table tablaAutos (Patente int primary key, Marca Text, Modelo Text, Sede Text, Fecha Text, Seguro boolean)");
    }

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
