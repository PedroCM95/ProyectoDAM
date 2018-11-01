package com.example.pedro.proyecto_pgl_2018;

import android.app.Application;
import android.provider.BaseColumns;

public class ManagerEventApplication extends Application {

    public static String AUTHORITY = "com.example.pedro.proyecto_pgl_2018.Provider.ManagerEventsContentProvider";// Anotación aqui colocamos las variables globales que nos acompañana desde el inicio de nuestra aplicación

    public static final class Solicitud implements BaseColumns {

        public static  String TABLE_NAME = "Request";
        public static String CONTENT_URI = "content://" + ManagerEventApplication.AUTHORITY + "/" + TABLE_NAME;

        public static String Name = "Nombre";
        public static String Departament = "Departamento";
        public static String DNI = "DNI";
        public static String Email = "Email";

        //Implementamos las distintas tablas que va a tener nuestra base de datos
    }
}
