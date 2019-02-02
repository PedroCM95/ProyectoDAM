package com.example.pedro.proyecto_pgl_2018.sync;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.pedro.proyecto_pgl_2018.constantes.Utilidades;
import com.example.pedro.proyecto_pgl_2018.pojos.Bitacora;
import com.example.pedro.proyecto_pgl_2018.pojos.Solicitud;
import com.example.pedro.proyecto_pgl_2018.constantes.RequestBitacora;
import com.example.pedro.proyecto_pgl_2018.constantes.RequestUtility;
import com.example.pedro.proyecto_pgl_2018.volley.CicloVolley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Sincronizacion {
    private static final String LOGTAG = "Pedro - Sincronizacion";
    private static ContentResolver resolvedor;
    private static Context contexto;
    private static boolean esperandoRespuestaDeServidor = false;

    public Sincronizacion(Context contexto){
        this.resolvedor = contexto.getContentResolver();
        this.contexto = contexto;
        //recibirActualizacionesDelServidor(); //La primera vez se cargan los datos siempre
    }

    public synchronized static boolean isEsperandoRespuestaDeServidor() {
        return esperandoRespuestaDeServidor;
    }

    public synchronized static void setEsperandoRespuestaDeServidor(boolean esperandoRespuestaDeServidor) {
        Sincronizacion.esperandoRespuestaDeServidor = esperandoRespuestaDeServidor;
    }

    public synchronized boolean sincronizar(){
        Log.i("sincronizacion","SINCRONIZAR");

        if(isEsperandoRespuestaDeServidor()){
            return true;
        }

        if(Utilidades.VERSION_ADMINISTRADOR){
            enviarActualizacionesAlServidor();
        } else {
            recibirActualizacionesDelServidor();
        }


        return true;
    }



    private static void enviarActualizacionesAlServidor(){

        ArrayList<Bitacora> registrosBitacora = RequestBitacora.readAllRecord(resolvedor);

        for(Bitacora bitacora : registrosBitacora){

            switch(bitacora.getOperacion()){
                case Utilidades.OPERACION_INSERTAR:
                    Solicitud solicitud = null;
                    try {
                        solicitud = RequestUtility.readRecord(resolvedor, bitacora.getID_Request());
                        CicloVolley.addRequest(solicitud, true, bitacora.getID());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case Utilidades.OPERACION_MODIFICAR:
                    try {
                        solicitud = RequestUtility.readRecord(resolvedor, bitacora.getID_Request());
                        CicloVolley.updateRequest(solicitud, true, bitacora.getID());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case Utilidades.OPERACION_BORRAR:
                    CicloVolley.delRequest(bitacora.getID_Request(), true, bitacora.getID());
                    break;
            }
            Log.i("sincronizacion", "acabo de enviar");
        }
    }

    private static void recibirActualizacionesDelServidor(){ CicloVolley.getAllRequest();
    }

    public static void realizarActualizacionesDelServidorUnaVezRecibidas(JSONArray jsonArray){
        Log.i("sincronizacion", "recibirActualizacionesDelServidor");

        try {
            ArrayList<Integer> identificadoresDeRegistrosActualizados = new ArrayList<Integer>();
            ArrayList<Solicitud> registrosNuevos = new ArrayList<>();
            ArrayList<Solicitud> registrosViejos = RequestUtility.readAllRecord(resolvedor);
            ArrayList<Integer> identificadoresDeRegistrosViejos = new ArrayList<Integer>();
            for(Solicitud i : registrosViejos) identificadoresDeRegistrosViejos.add(i.getID());

            JSONObject obj = null;
            for (int i = 0; i < jsonArray.length(); i++ ){
                obj = jsonArray.getJSONObject(i);
                registrosNuevos.add(new Solicitud(obj.getInt("PK_ID"), obj.getString("Nombre"), obj.getString("Lugar"), obj.getString("Queja")));
            }

            for(Solicitud solicitud: registrosNuevos) {
                try {
                    if(identificadoresDeRegistrosViejos.contains(solicitud.getID())) {
                        RequestUtility.update(resolvedor, solicitud);
                    } else {
                        RequestUtility.insert(resolvedor, solicitud);
                    }
                    identificadoresDeRegistrosActualizados.add(solicitud.getID());
                } catch (Exception e){
                    Log.i("sincronizacion",
                            "Probablemente el registro ya existía en la BD."+"" +
                                    " Esto se podría controlar mejor con una Bitácora.");
                }
            }

            for(Solicitud solicitud: registrosViejos){
                if(!identificadoresDeRegistrosActualizados.contains(solicitud.getID())){
                    try {
                        RequestUtility.delete(resolvedor, solicitud.getID());
                    }catch(Exception e){
                        Log.i("sincronizacion", "Error al borrar el registro con id:" + solicitud.getID());
                    }
                }
            }

            //CicloVolley.getAllCiclo(); //Los baja y los guarda en SQLite
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
