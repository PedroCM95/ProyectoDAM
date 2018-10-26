package com.example.pedro.proyecto_pgl_2018.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pedro.proyecto_pgl_2018.manager.data.Manager;


public class RequestDatabase extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "manager_event.db";
    private final static int DATABASE_VERSION = 1;

    public RequestDatabase( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Manager.TABLE_NAME +
                    " ( _id INTEGER PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT, " +
                    Manager.Name + " TEXT, " +
                    Manager.DNI + " TEXT, " +
                    Manager.Departament + " TEXT, " +
                    Manager.Email + " TEXT );");

        initilizeData(db);
    }

    private void initilizeData(SQLiteDatabase db) {
        db.execSQL("INSERT INTO " + Manager.TABLE_NAME + " ( " + Manager._ID + "," + Manager.Name + "," + Manager.DNI + "," + Manager.Departament + "," + Manager.Email +" )" +
                "VALUES (1, 'Solicitud Alcantarillado', '86578425T', 'Urbanismo', 'pedro@gmail.com')");
        db.execSQL("INSERT INTO " + Manager.TABLE_NAME + " ( " + Manager._ID + "," + Manager.Name + "," + Manager.DNI + "," + Manager.Departament + "," + Manager.Email +" )" +
                "VALUES (2, 'Solicitud Aceras', '65784215W', 'Urbanismo', 'cabello@gmail.com')");
        db.execSQL("INSERT INTO " + Manager.TABLE_NAME + " ( " + Manager._ID + "," + Manager.Name + "," + Manager.DNI + "," + Manager.Departament + "," + Manager.Email +" )" +
                "VALUES (3, 'Solicitud Obras', '97548651X', 'Concejalia Obras', 'munguia@gmail.com')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Manager.TABLE_NAME);
        onCreate(db);
    }
}
