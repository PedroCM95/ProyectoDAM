package com.example.pedro.proyecto_pgl_2018;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity  {

    Activity contexto;
    Button button_principal;
    Spinner lista;
    ArrayAdapter<String> adaptador;
    String[] datos = {"Las Palmas de Gran Canaria", "Telde", "Agaete", "Agüimes", "Artenara", "Arucas", "Firgas", "Gáldar", "Aldea de San Nicolás", "Mogán", "Moya", "San Bartolomé de Tirajana"
            , "Santa Brígida", "Santa Lucía de Tirajana", "Santa María de Guía", "Tejeda", "Teror", "Valleseco", "Valsequillo", "San Mateo"};

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            contexto = this;
            button_principal = (Button) findViewById(R.id.button_principal);
            lista = (Spinner) findViewById(R.id.spinner_lista);
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
            lista.setAdapter(adaptador);
            button_principal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contexto, LoginPrincipal.class);
                String municipality = lista.getSelectedItem().toString().trim();
                intent.putExtra("municipality", municipality);
                startActivity(intent);
                }
            });
    }
}




