package com.example.pedro.proyecto_pgl_2018.constantes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Utilidades {

    static public void loadImageFromStorage(Context contexto, String imagenFichero, ImageView img) throws FileNotFoundException{
        File f = contexto.getFileStreamPath(imagenFichero);
        Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
        img.setImageBitmap(b);
    }

    public static void storeImage(Bitmap image, Context contexto, String fileName) throws IOException{
        FileOutputStream fos = contexto.openFileOutput(fileName, Context.MODE_PRIVATE);
        image.compress(Bitmap.CompressFormat.PNG, 100, fos);
        fos.close();
    }


    public static final long SYNC_INTERVAL = 60; // Cada 60 segundos
    public static final boolean VERSION_ADMINISTRADOR = false;

    public static final String RUTA_SERVIDOR = "http://192.168.1.5:8080/ProyectoDAM/webresources";

    public static final int SIN_VALOR_INT = -1;
    public static final String SIN_VALOR_STRING = "";

    public static final int INSERTAR = 1;
    public static final int GUARDAR = 2;

    public static final int OPERACION_INSERTAR = 1;
    public static final int OPERACION_MODIFICAR = 2;
    public static final int OPERACION_BORRAR = 3;

}


