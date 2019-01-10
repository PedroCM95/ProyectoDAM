package com.example.pedro.proyecto_pgl_2018.constantes;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.pedro.proyecto_pgl_2018.ManagerEventApplication;
import com.example.pedro.proyecto_pgl_2018.pojos.Bitacora;
import com.example.pedro.proyecto_pgl_2018.pojos.Bitacora;
import com.example.pedro.proyecto_pgl_2018.pojos.Solicitud;

import java.io.IOException;
import java.util.ArrayList;

public class RequestBitacora {

    public static void insert(ContentResolver resolver, Bitacora bitacora){

        Uri uri = ManagerEventApplication.Bitacora.CONTENT_URI;

        ContentValues values = new ContentValues();
        values.put(ManagerEventApplication.Bitacora.ID_REQUEST, bitacora.getID_Request());
        values.put(ManagerEventApplication.Bitacora.OPERACION, bitacora.getOperacion());

        /*Uri uriResultado = resolver.insert(uri, values);

        String bitacoraID = uriResultado.getLastPathSegment();

        if (bitacora.getImagen()!=null){
            try {
                Utilidades.storeImage(bitacora.getImagen(), contexto, "img_" + bitacoraID + ".jpg");
            } catch (IOException e) {
                Toast.makeText(contexto,"No se puede guardar la imagen", Toast.LENGTH_LONG).show();
            }
        }*/
        resolver.insert(uri, values);
    }

    static public void delete(ContentResolver resolver, int bitacoraID){

        Uri uri = Uri.parse(ManagerEventApplication.Bitacora.CONTENT_URI + "/" + bitacoraID);
        resolver.delete(uri, null, null);

    }

    static public void update(ContentResolver resolver, Bitacora bitacora, Context contexto){
        Uri uri = Uri.parse(ManagerEventApplication.Bitacora.CONTENT_URI + "/" + bitacora.getID());

        ContentValues values = new ContentValues();
        values.put(ManagerEventApplication.Bitacora.ID_REQUEST, bitacora.getID_Request());
        values.put(ManagerEventApplication.Bitacora.OPERACION, bitacora.getOperacion());

        resolver.update(uri,  values, null, null);

        //String bitacoraID = uriResultado.getLastPathSegment();

        /*if (bitacora.getImagen()!=null){
            try {
                Utilidades.storeImage(bitacora.getImagen(), contexto, "img_" + bitacora.getID() + ".jpg");
            } catch (IOException e) {
                Toast.makeText(contexto,"No se puede guardar la imagen", Toast.LENGTH_LONG).show();
            }
        }*/
    }

    static public Bitacora readRecord(ContentResolver resolver, int bitacoraID){
        Uri uri = Uri.parse(ManagerEventApplication.Bitacora.CONTENT_URI + "/" + bitacoraID);

        String[] projection = {
                ManagerEventApplication.Bitacora._ID,
                ManagerEventApplication.Bitacora.ID_REQUEST,
                ManagerEventApplication.Bitacora.OPERACION

        };

        Cursor cursor = resolver.query(uri, projection, null, null, null);

        if (cursor.moveToFirst()){

            Bitacora bitacora = new Bitacora();

            bitacora.setID(bitacoraID);
            bitacora.setID_Request(cursor.getInt(cursor.getColumnIndex(ManagerEventApplication.Bitacora.ID_REQUEST)));
            bitacora.setOperacion(cursor.getInt(cursor.getColumnIndex(ManagerEventApplication.Bitacora.OPERACION)));

            return bitacora;
        }

        return null;
    }

    static public ArrayList<Bitacora> readAllRecord(ContentResolver resolver){
        Uri uri = ManagerEventApplication.Bitacora.CONTENT_URI;
        Log.i("bitacora",  "bitacoraprincipio");
        String[] projection = {
                ManagerEventApplication.Bitacora._ID,
                ManagerEventApplication.Bitacora.ID_REQUEST,
                ManagerEventApplication.Bitacora.OPERACION

        };

        Cursor cursor = resolver.query(uri, projection, null, null, null);

        ArrayList<Bitacora> bitacoras = new ArrayList<>();
        Bitacora bitacora;

        while (cursor.moveToNext()){

            bitacora = new Bitacora();

            bitacora.setID(cursor.getInt(cursor.getColumnIndex(ManagerEventApplication.Bitacora._ID)));
            bitacora.setID_Request(cursor.getInt(cursor.getColumnIndex(ManagerEventApplication.Bitacora.ID_REQUEST)));
            bitacora.setOperacion(cursor.getInt(cursor.getColumnIndex(ManagerEventApplication.Bitacora.OPERACION)));

            bitacoras.add(bitacora);
        }

        return bitacoras;

    }
}

