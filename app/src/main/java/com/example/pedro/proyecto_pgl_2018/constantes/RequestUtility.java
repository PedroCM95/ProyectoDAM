package com.example.pedro.proyecto_pgl_2018.constantes;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;

import com.example.pedro.proyecto_pgl_2018.pojos.Solicitud;


import com.example.pedro.proyecto_pgl_2018.ManagerEventApplication;

import java.io.IOException;

public class RequestUtility {
    public static  void insert(ContentResolver resolver, Solicitud solicitud, Context contexto){

        Uri uri = Uri.parse(ManagerEventApplication.Solicitud.CONTENT_URI);

        ContentValues values = new ContentValues();
        values.put(ManagerEventApplication.Solicitud.Name, solicitud.getName());
        values.put(ManagerEventApplication.Solicitud.Lugar, solicitud.getLugar());
        values.put(ManagerEventApplication.Solicitud.Queja, solicitud.getQueja());


        Uri uriResultado = resolver.insert(uri, values);

        String requestID = uriResultado.getLastPathSegment();

        if (solicitud.getImagen()!=null){
            try {
                Utilidades.storeImage(solicitud.getImagen(), contexto, "img_" + requestID + ".jpg");
            } catch (IOException e) {
                Toast.makeText(contexto,"No se puede guardar la imagen", Toast.LENGTH_LONG).show();
            }
        }

    }

    static public void delete(ContentResolver resolver, int requestID){

        Uri uri = Uri.parse(ManagerEventApplication.Solicitud.CONTENT_URI + "/" + requestID);
        resolver.delete(uri, null, null);

    }

    static public void update(ContentResolver resolver, Solicitud solicitud){
        Uri uri = Uri.parse(ManagerEventApplication.Solicitud.CONTENT_URI + "/" + solicitud.getID());

        ContentValues values = new ContentValues();
        values.put(ManagerEventApplication.Solicitud.Name, solicitud.getName());
        values.put(ManagerEventApplication.Solicitud.Lugar, solicitud.getLugar());
        values.put(ManagerEventApplication.Solicitud.Queja, solicitud.getQueja());

        resolver.update(uri,  values, null, null);
    }

    static public Solicitud readRecord(ContentResolver resolver, int requestID){
        Uri uri = Uri.parse(ManagerEventApplication.Solicitud.CONTENT_URI + "/" + requestID);

        String[] projection = {
                ManagerEventApplication.Solicitud.Name,
                ManagerEventApplication.Solicitud.Lugar,
                ManagerEventApplication.Solicitud.Queja
        };

        Cursor cursor = resolver.query(uri, projection, null, null, null);

        if (cursor.moveToFirst()){

            Solicitud solicitud = new Solicitud();

            solicitud.setID(requestID);
            solicitud.setName(cursor.getString(cursor.getColumnIndex(ManagerEventApplication.Solicitud.Name)));
            solicitud.setLugar(cursor.getString(cursor.getColumnIndex(ManagerEventApplication.Solicitud.Lugar)));
            solicitud.setQueja(cursor.getString(cursor.getColumnIndex(ManagerEventApplication.Solicitud.Queja)));

            return solicitud;
        }

        return null;
    }
}
