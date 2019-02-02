package com.example.pedro.proyecto_pgl_2018.pojos;

import com.example.pedro.proyecto_pgl_2018.constantes.Utilidades;

public class Solicitud {
    private int ID;
    private String name;
    private String lugar;
    private String queja;
    //private Bitmap imagen;

    public Solicitud() {
        this.ID = Utilidades.SIN_VALOR_INT;
        this.name = Utilidades.SIN_VALOR_STRING;
        this.setLugar(Utilidades.SIN_VALOR_STRING);
        this.setQueja(Utilidades.SIN_VALOR_STRING);
       // this.setImagen(null);
    }

    public Solicitud(int ID, String name, String lugar, String queja) {
        this.ID = ID;
        this.name = name;
        this.setLugar(lugar);
        this.setQueja(queja);
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

   /* public Bitmap getImagen() {
        return imagen;
    }
*/
   /* public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String direccion) {
        lugar = direccion;
    }

    public String getQueja() {
        return queja;
    }

    public void setQueja(String queja) {
        this.queja = queja;


    }
}