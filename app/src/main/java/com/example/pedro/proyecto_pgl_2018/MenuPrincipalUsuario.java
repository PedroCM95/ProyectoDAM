package com.example.pedro.proyecto_pgl_2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import com.example.pedro.proyecto_pgl_2018.Configuracion.ConfiguracionUsuario;
import com.example.pedro.proyecto_pgl_2018.manager.MyRequest;

public class MenuPrincipalUsuario extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button button_formulary, button_solicitudes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button button_formulary = (Button) findViewById(R.id.button_formulary);
        button_formulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RellenarFormularioInsertar.class);
                startActivity(intent);
            }
        });

        Button button_solicitudes = findViewById(R.id.button_solicitudes);
        button_solicitudes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyRequest.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
       // User = (TextView) findViewById(R.id.Name_User);
        //String Nombre_User = getIntent().getExtras().getString("User");
        //User.setText("BIENVENIDO " + Nombre_User + " A SU PORTAL DE INCIDENCIAS");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_principal_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.preguntas_frecuentes:
                Intent i = new Intent(getApplicationContext(), Preguntas_Frecuentes.class);
                startActivity(i);
                break;
            case R.id.configuracion:
                Intent intent = new Intent(getApplicationContext(), ConfiguracionUsuario.class);
                startActivity(intent);
                break;
            case R.id.formulary:
                Intent x = new Intent(getApplicationContext(), RellenarFormularioInsertar.class);
                startActivity(x);
                break;
        }
        return  true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.profile) {
            Intent intent =  new Intent(getApplicationContext(), MenuSegundarioUsuario.class);
            startActivity(intent);

        } else if (id == R.id.event) {
            Intent intent =  new Intent(getApplicationContext(), RellenarFormularioInsertar.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.settings) {
            Intent intent =  new Intent(getApplicationContext(), MenuSegundarioUsuario.class);
            getApplication().startActivity(intent);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
