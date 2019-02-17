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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.pedro.proyecto_pgl_2018.pojos.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginPrincipal extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {


        TextView Countrie;
        CardView Boton_Login, Boton_Registrar;
        EditText editText_Nombre_Login, editText_password;

        RequestQueue rq;
        JsonRequest jrq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_principal);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Countrie = (TextView) findViewById(R.id.TextBienvenida);
        editText_Nombre_Login = (EditText) findViewById(R.id.editText_Nombre_Login);
        editText_password =(EditText) findViewById(R.id.editText_password);

        Boton_Registrar = (CardView) findViewById(R.id.Boton_Registrar);
        Boton_Login = (CardView) findViewById(R.id.Boton_Login);
        Boton_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
        Boton_Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuPrincipalUsuario.class);
                startActivity(intent);
            }
        });

        rq = Volley.newRequestQueue(getApplicationContext());

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

        String url ="http://192.168.1.5/login/sesion.php?nombre="+editText_Nombre_Login.getText().toString()+
                "&password="+editText_password.getText().toString();

        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);

    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "No se encontró al usuario" + error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        User usuario = new User();
        Toast.makeText(getApplicationContext(), " Se ha encontrado el usuario " + editText_Nombre_Login.getText().toString(), Toast.LENGTH_SHORT).show();


        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try{

            jsonObject = jsonArray.getJSONObject(0);
            usuario.setNombre(jsonObject.optString("nombre"));
            usuario.setPassword(jsonObject.optString("password"));
            usuario.setApellidos(jsonObject.optString("apellidos"));
        }catch (JSONException e){
            e.printStackTrace();
        }



        Intent intencion = new Intent(getApplicationContext(), MenuPrincipalUsuario.class);
        startActivity(intencion);
    }
}
