package com.example.pedro.proyecto_pgl_2018.pojos;

import com.example.pedro.proyecto_pgl_2018.constantes.Utilidades;

public class User {
    private String nombre, password, apellidos;
    private int PK_ID;

    public User(){
        this.PK_ID = Utilidades.SIN_VALOR_INT;
        this.nombre = Utilidades.SIN_VALOR_STRING;
        this.setPassword(Utilidades.SIN_VALOR_STRING);
        this.setApellidos(Utilidades.SIN_VALOR_STRING);
    }

    public User(int PK_ID, String nombre, String password, String apellidos){
        this.PK_ID = PK_ID;
        this.nombre = nombre;
        this.setPassword(password);
        this.setApellidos(apellidos);
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getPK_ID() {
        return PK_ID;
    }

    public void setPK_ID(int PK_ID) {
        this.PK_ID = PK_ID;
    }
}
