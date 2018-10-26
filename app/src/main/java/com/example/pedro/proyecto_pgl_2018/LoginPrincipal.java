package com.example.pedro.proyecto_pgl_2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPrincipal extends AppCompatActivity {

        LoginPrincipal contexto;
        TextView Countrie;
        CardView Boton_Login;
        EditText editText_Nombre_Login, editText_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_principal);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        contexto = this;
        Countrie = (TextView) findViewById(R.id.TextBienvenida);
        editText_Nombre_Login = (EditText) findViewById(R.id.editText_Nombre_Login);
        editText_password =(EditText) findViewById(R.id.editText_password);
        Boton_Login = (CardView) findViewById(R.id.Boton_Login);
        Boton_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
        String municipality = getIntent().getExtras().getString("municipality").trim();
        Countrie.setText(municipality); // Nos sirve para al valor pasarlo mostrarlo
    }

    private void Login(){

        String name = editText_Nombre_Login.getText().toString().trim();
        String password = editText_password.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            editText_Nombre_Login.setError(getString(R.string.error_de_campo_obligatorio));
            editText_Nombre_Login.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(password)){
            editText_password.setError(getString(R.string.error_de_campo_obligatorio));
            editText_password.requestFocus();
            return;
        }
        Intent intent = new Intent(contexto, MenuPrincipalUsuario.class);
            String Nombre_User = editText_Nombre_Login.getText().toString().trim();
            intent.putExtra("User", Nombre_User);
        startActivity(intent);
    }
}
