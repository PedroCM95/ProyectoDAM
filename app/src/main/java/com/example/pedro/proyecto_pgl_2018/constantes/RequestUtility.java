package com.example.pedro.proyecto_pgl_2018.constantes;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.example.pedro.proyecto_pgl_2018.pojos.Bitacora;
import com.example.pedro.proyecto_pgl_2018.pojos.Solicitud;


import com.example.pedro.proyecto_pgl_2018.ManagerEventApplication;

import java.util.ArrayList;

public class RequestUtility {

    public static Uri insert(ContentResolver resolver, Solicitud solicitud) {

        Uri uri = ManagerEventApplication.Solicitud.CONTENT_URI;

        ContentValues values = new ContentValues();
        if(solicitud.getID() != Utilidades.SIN_VALOR_INT) values.put(ManagerEventApplication.Solicitud._ID, solicitud.getID());
        values.put(ManagerEventApplication.Solicitud.Name, solicitud.getName());
        values.put(ManagerEventApplication.Solicitud.Lugar, solicitud.getLugar());
        values.put(ManagerEventApplication.Solicitud.Queja, solicitud.getQueja());


       /* Uri uriResultado = resolver.insert(uri, values);

        String requestID = uriResultado.getLastPathSegment();

        if (solicitud.getImagen() != null) {
            try {
                Utilidades.storeImage(solicitud.getImagen(), contexto, "img_" + requestID + ".jpg");
            } catch (IOException e) {
                Toast.makeText(contexto, "No se puede guardar la imagen", Toast.LENGTH_LONG).show();
            }
        }*/

        return resolver.insert(uri, values);
    }

    static public void insertRecordBitacora(ContentResolver resolver, Solicitud solicitud) {

        Uri uri = insert(resolver, solicitud);
       // int id = Integer.valueOf(uri.getLastPathSegment());
        solicitud.setID(Integer.parseInt(uri.getLastPathSegment()));

        Bitacora bitacora = new Bitacora();
        bitacora.setID_Request(solicitud.getID());
        bitacora.setOperacion(Utilidades.OPERACION_INSERTAR);
        RequestBitacora.insert(resolver, bitacora);

    }

    static public void delete(ContentResolver resolver, int requestID)   {

        Uri uri = Uri.parse(ManagerEventApplication.Solicitud.CONTENT_URI + "/" + requestID);
        resolver.delete(uri, null, null);

    }

    static public void deleteRecordBitacora(ContentResolver resolver, int requestID)  {

        delete(resolver, requestID);

        Bitacora bitacora = new Bitacora();
        bitacora.setID_Request(requestID);
        bitacora.setOperacion(Utilidades.OPERACION_BORRAR);
        RequestBitacora.insert(resolver, bitacora);

    }

    static public void update(ContentResolver resolver, Solicitud solicitud)  {
        Uri uri = Uri.parse(ManagerEventApplication.Solicitud.CONTENT_URI + "/" + solicitud.getID());

        ContentValues values = new ContentValues();
        values.put(ManagerEventApplication.Solicitud.Name, solicitud.getName());
        values.put(ManagerEventApplication.Solicitud.Lugar, solicitud.getLugar());
        values.put(ManagerEventApplication.Solicitud.Queja, solicitud.getQueja());

        resolver.update(uri,  values, null, null);

        //String requestID = uriResultado.getLastPathSegment();

       /* if (solicitud.getImagen()!=null){
            try {
                Utilidades.storeImage(solicitud.getImagen(), contexto, "img_" + solicitud.getID() + ".jpg");
            } catch (IOException e) {
                Toast.makeText(contexto,"No se puede guardar la imagen", Toast.LENGTH_LONG).show();
            }
        }*/
    }

    static public void updateRecordBitacora(ContentResolver resolver, Solicitud solicitud)  {

        update(resolver, solicitud);

        Bitacora bitacora = new Bitacora();
        bitacora.setID_Request(solicitud.getID());
        bitacora.setOperacion(Utilidades.OPERACION_MODIFICAR);
        RequestBitacora.insert(resolver, bitacora);

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

    static public ArrayList<Solicitud> readAllRecord(ContentResolver resolver) {
        Uri uri = ManagerEventApplication.Solicitud.CONTENT_URI;

        String[] projection = {
                ManagerEventApplication.Solicitud._ID,
                ManagerEventApplication.Solicitud.Name,
                ManagerEventApplication.Solicitud.Lugar,
                ManagerEventApplication.Solicitud.Queja

        };

        Cursor cursor = resolver.query(uri, projection, null, null, null);

        ArrayList<Solicitud> registros = new ArrayList<>();
        Solicitud solicitud;

        while (cursor.moveToNext()){

            solicitud = new Solicitud();

            solicitud.setID(cursor.getInt(cursor.getColumnIndex(ManagerEventApplication.Solicitud._ID)));
            solicitud.setName(cursor.getString(cursor.getColumnIndex(ManagerEventApplication.Solicitud.Name)));
            solicitud.setLugar(cursor.getString(cursor.getColumnIndex(ManagerEventApplication.Solicitud.Lugar)));
            solicitud.setQueja(cursor.getString(cursor.getColumnIndex(ManagerEventApplication.Solicitud.Queja)));

            registros.add(solicitud);
        }

        return registros;
    }
}
