package com.example.pedro.proyecto_pgl_2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RellenarFormulario extends AppCompatActivity {

    RellenarFormulario contexto;

    EditText editTextNombre;
    EditText editTextDNI;
    EditText editTextEmail;
    EditText editTextTelefono;
    EditText editTextQueja;
    CheckBox checkboxEducation,checkboxScience, checkboxUrbanismy ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rellenar_formulario);



        editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        editTextDNI = (EditText) findViewById(R.id.editTextDNI);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextTelefono = (EditText) findViewById(R.id.editTextTelefono);
        editTextQueja = (EditText) findViewById(R.id.editTextQueja);


        checkboxScience = (CheckBox) findViewById(R.id.checkboxScience);
        checkboxEducation = (CheckBox) findViewById(R.id.checkboxEducation);
        checkboxUrbanismy = (CheckBox) findViewById(R.id.checkboxUrbanismy);

        Button EnviarFormulario = (Button) findViewById(R.id.EnviarFormulario);
        EnviarFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviar();


            }
        });

        Button CancelarFormulario = (Button) findViewById(R.id.CancelarFormulario);
        CancelarFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
    }




    private void enviar(){

        editTextNombre.setError(null);
        editTextDNI.setError(null);
        editTextEmail.setError(null);
        editTextTelefono.setError(null);
        editTextQueja.setError(null);


        String nombre = editTextNombre.getText().toString().trim();
        String dni = editTextDNI.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String telefono = editTextTelefono.getText().toString().trim();
        String queja = editTextQueja.getText().toString().trim();


        if(checkboxScience.isChecked()){

        }else if (checkboxEducation.isChecked()){


        }else if(checkboxUrbanismy.isChecked()){


        }

        if(TextUtils.isEmpty(nombre)){

            editTextNombre.setError(getString(R.string.error_de_campo_obligatorio));
            editTextNombre.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(dni)){

            editTextDNI.setError(getString(R.string.error_de_campo_obligatorio));
            editTextDNI.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(email)){

            editTextEmail.setError(getString(R.string.error_de_campo_obligatorio));
            editTextEmail.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(queja)){

            editTextQueja.setError(getString(R.string.error_de_campo_obligatorio));
            editTextQueja.requestFocus();
            return;
        }



        int dniint = Integer.parseInt(dni);

        if(dniint<9 || dniint>10){

            editTextDNI.setError(getString(R.string.error_valor_emtre_0_9));
            editTextDNI.requestFocus();
            return;
        }

        int tlf = Integer.parseInt(telefono);

        if(tlf<9 || tlf>10){

            editTextTelefono.setError(getString(R.string.error_valor_emtre_0_9));
            editTextTelefono.requestFocus();
            return;
        }

        Toast.makeText(getApplicationContext(),"Se ha enviado su solicitud correctamente", Toast.LENGTH_LONG).show();
    }
}
