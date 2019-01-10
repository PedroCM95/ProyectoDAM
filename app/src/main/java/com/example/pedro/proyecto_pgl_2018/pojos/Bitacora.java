package com.example.pedro.proyecto_pgl_2018.pojos;

import android.graphics.Bitmap;

import com.example.pedro.proyecto_pgl_2018.constantes.Utilidades;

public class Bitacora {

    int ID;
    int ID_Request;
    int operacion;
    //private Bitmap imagen;

    public Bitacora() {
        this.ID = Utilidades.SIN_VALOR_INT;
        this.ID_Request = Utilidades.SIN_VALOR_INT;
        this.operacion = Utilidades.SIN_VALOR_INT;
       // this.setImagen(null);
    }

    public Bitacora(int ID, int ID_Request, int operacion) {
        this.ID = ID;
        this.ID_Request = ID_Request;
        this.operacion = operacion;
       // this.imagen = imagen;
    }

   /* public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }*/

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID_Request() {
        return ID_Request;
    }

    public void setID_Request(int ID_Request) {
        this.ID_Request = ID_Request;
    }

    public int getOperacion() {
        return operacion;
    }

    public void setOperacion(int operacion) {
        this.operacion = operacion;
    }
}
