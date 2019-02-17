package com.example.pedro.proyecto_pgl_2018;

import android.app.Application;
import android.net.Uri;
import android.provider.BaseColumns;

public class ManagerEventApplication extends Application {

    public static final String AUTHORITY = "com.example.pedro.proyecto_pgl_2018.Provider.ManagerEventsContentProvider";// Anotación aqui colocamos las variables globales que nos acompañana desde el inicio de nuestra aplicación

    public static final class Solicitud implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/Solicitudes");

        public static final String Name = "Nombre";
        public static final String Lugar = "Lugar";
        public static final String Queja = "Queja";

        //Implementamos las distintas tablas que va a tener nuestra base de datos
    }

    public static final class User implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/Usuario");

        public static final String nombre = "Nombre";
        public static final String password = "Password";
        public static final String apellidos = "Apellidos";

        //Implementamos las distintas tablas que va a tener nuestra base de datos
    }

    public static final class Bitacora implements BaseColumns {


        //public static final String CONTENT_URI = "content://" + ManagerEventApplication.AUTHORITY + "/" + TABLE_NAME;

        public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/Bitacora");

        public static final String ID_REQUEST = "ID_Request";
        public static final String OPERACION = "Operacion";


        //Implementamos las distintas tablas que va a tener nuestra base de datos
    }
}
