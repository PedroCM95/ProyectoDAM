package com.example.pedro.proyecto_pgl_2018.pojos;

import android.graphics.Bitmap;

import com.example.pedro.proyecto_pgl_2018.constantes.Utilidades;

public class Solicitud {
    private int ID;
    private String Name;
    private String Lugar;
    private String Queja;
    private Bitmap imagen;

    public Solicitud() {
        this.ID = Utilidades.SIN_VALOR_INT;
        this.Name = Utilidades.SIN_VALOR_STRING;
        this.Lugar = Utilidades.SIN_VALOR_STRING;
        this.Queja = Utilidades.SIN_VALOR_STRING;
        this.setImagen(null);
    }

    public Solicitud(int ID, String name, String direccion, String queja, Bitmap imagen) {
        this.ID = ID;
        this.Name = name;
        this.Lugar = direccion;
        this.Queja = queja;
        this.imagen = imagen;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String direccion) {
        Lugar = direccion;
    }

    public String getQueja() {
        return Queja;
    }

    public void setQueja(String queja) {
        Queja = queja;


    }
}