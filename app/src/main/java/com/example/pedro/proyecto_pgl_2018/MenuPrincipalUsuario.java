package com.example.pedro.proyecto_pgl_2018;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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
import android.widget.TextView;

import com.example.pedro.proyecto_pgl_2018.Configuracion.ConfiguracionUsuario;
import com.example.pedro.proyecto_pgl_2018.fragments.BienvenidoFragment;
import com.example.pedro.proyecto_pgl_2018.fragments.ConsultarFragment;
import com.example.pedro.proyecto_pgl_2018.fragments.IncidenciasFragment;
import com.example.pedro.proyecto_pgl_2018.fragments.RegistrarUsuarioFragment;
import com.example.pedro.proyecto_pgl_2018.interfaces.IFragments;
import com.example.pedro.proyecto_pgl_2018.manager.MyRequest;
import com.example.pedro.proyecto_pgl_2018.manager.RellenarFormularioModificar;

public class MenuPrincipalUsuario extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IFragments {

    Button button_formulary, button_solicitudes;
    TextView Bienvenido;
    public static final String nombre ="nombre";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        android.support.v4.app.Fragment miFragment=new BienvenidoFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();

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

        Fragment miFragment = null;
        boolean fragmentSeleccionado = false;

        if (id == R.id.profile) {
            Intent intent =  new Intent(getApplicationContext(), MenuSegundarioUsuario.class);
            startActivity(intent);

        }
        else if (id == R.id.register) {
            miFragment = new RegistrarUsuarioFragment();
            fragmentSeleccionado=true;

        }
        else if (id == R.id.show) {
            miFragment = new ConsultarFragment();
            fragmentSeleccionado=true;

        }
        else if (id == R.id.event) {
            miFragment = new IncidenciasFragment();
            fragmentSeleccionado=true;
        }
        else if (id == R.id.show) {
                Intent x =  new Intent(getApplicationContext(), MyRequest.class);
                startActivity(x);

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setType("image/*");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else if (id == R.id.settings) {
            Intent intent =  new Intent(getApplicationContext(), ConfiguracionUsuario.class);
            getApplication().startActivity(intent);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        if (fragmentSeleccionado==true){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
