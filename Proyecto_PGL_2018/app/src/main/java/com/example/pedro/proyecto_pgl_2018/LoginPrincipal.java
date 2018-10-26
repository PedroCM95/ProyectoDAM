package com.example.pedro.proyecto_pgl_2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

public class LoginPrincipal extends AppCompatActivity {

        LoginPrincipal contexto;
        TextView Countrie;
        CardView Boton_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_principal);

        contexto = this;

        Countrie = (TextView) findViewById(R.id.TextBienvenida);
        Boton_Login = (CardView) findViewById(R.id.Boton_Login);

        Boton_Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(contexto, MenuPrincipalUsuario.class);


                startActivity(intent);



            }
        });


        String municipality = getIntent().getExtras().getString("municipality").trim();
        Countrie.setText(municipality); // Nos sirve para al valor pasarlo mostrarlo
    }
}
