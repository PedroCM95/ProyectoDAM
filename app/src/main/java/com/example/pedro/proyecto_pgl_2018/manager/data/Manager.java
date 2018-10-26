package com.example.pedro.proyecto_pgl_2018.manager.data;

import android.provider.BaseColumns;

import com.example.pedro.proyecto_pgl_2018.ManagerEventApplication;

public class Manager implements BaseColumns {
    public static  String TABLE_NAME = "request";
    public static String CONTENT_URI = "content://" + ManagerEventApplication.AUTHORITY + "/" + TABLE_NAME;

    public static String Name = "Nombre";
    public static String Departament = "Departamento";
    public static String DNI = "DNI";
    public static String Email = "Email";

    //Implementamos las distintas tablas que va a tener nuestra base de datos
}
