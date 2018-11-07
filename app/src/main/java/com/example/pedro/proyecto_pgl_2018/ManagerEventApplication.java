package com.example.pedro.proyecto_pgl_2018;

import android.app.Application;
import android.provider.BaseColumns;

public class ManagerEventApplication extends Application {

    public static final String AUTHORITY = "com.example.pedro.proyecto_pgl_2018.Provider.ManagerEventsContentProvider";// Anotación aqui colocamos las variables globales que nos acompañana desde el inicio de nuestra aplicación

    public static final class Solicitud implements BaseColumns {

        public static  String TABLE_NAME = "Request";
        public static final String CONTENT_URI = "content://" + ManagerEventApplication.AUTHORITY + "/" + TABLE_NAME;

        public static final String Name = "Nombre";
        public static final String Lugar = "Direccion";
        public static final String Queja = "Queja";

        //Implementamos las distintas tablas que va a tener nuestra base de datos
    }
}
