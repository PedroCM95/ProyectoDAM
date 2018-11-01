package com.example.pedro.proyecto_pgl_2018;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import com.example.pedro.proyecto_pgl_2018.constantes.Utilidades;
import java.io.IOException;

public class RellenarFormulario extends AppCompatActivity {

    EditText editTextNombre, editTextDNI, editTextEmail, editTextTelefono, editTextQueja;
    Button EnviarFormulario, button_captura;
    ImageView picture;

    final int COD_SELECCIONA = 1;
    public static final int CAMERA_REQUEST = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rellenar_formulario);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_formulario);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        picture = (ImageView) findViewById(R.id.picture);
        button_captura = (Button) findViewById(R.id.button_captura);
        button_captura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarImagen();
            }
        });
        editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        editTextDNI = (EditText) findViewById(R.id.editTextDNI);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextTelefono = (EditText) findViewById(R.id.editTextTelefono);
        editTextQueja = (EditText) findViewById(R.id.editTextQueja);

        Button EnviarFormulario = (Button) findViewById(R.id.EnviarFormulario);
        EnviarFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviar();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.add(Menu.NONE, Utilidades.GUARDAR, Menu.NONE, "Guardar");
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menuItem.setIcon(R.drawable.ic_action_guardar);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case Utilidades.GUARDAR:
                attemptGuardar();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    void attemptGuardar(){

        editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        editTextDNI = (EditText) findViewById(R.id.editTextDNI);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextTelefono = (EditText) findViewById(R.id.editTextTelefono);
        editTextQueja = (EditText) findViewById(R.id.editTextQueja);
        picture = (ImageView) findViewById(R.id.picture);

        editTextNombre.setError(null);
        editTextDNI.setError(null);
        editTextEmail.setError(null);
        editTextTelefono.setError(null);
        editTextQueja.setError(null);
       // picture = null;

        String nombre = String.valueOf(editTextNombre.getText());
        String dni = String.valueOf(editTextDNI.getText());
        String email = String.valueOf(editTextEmail.getText());
        String telefono = String.valueOf(editTextTelefono.getText());
        String queja = String.valueOf(editTextQueja.getText());
        //String imagen = String.valueOf(picture.getImageAlpha());

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
        if(TextUtils.isEmpty(telefono)){

            editTextQueja.setError(getString(R.string.error_de_campo_obligatorio));
            editTextQueja.requestFocus();
            return;
        }
    }

    private void cargarImagen() {

        final CharSequence[] opciones = {"Sacar Foto", "Cargar Imagen", "Cancelar"};
        final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(RellenarFormulario.this);
        alertOpciones.setTitle("Seleccione una Opción");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Sacar Foto")) {
                    tomarFotografia();
                } else {

                    if (opciones[i].equals("Cargar Imagen")) {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"), COD_SELECCIONA);
                    } else {
                        dialogInterface.dismiss();
                    }
                }
            }
        });

        alertOpciones.show();
    }

    private void tomarFotografia() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CAMERA_REQUEST:
                if (resultCode == RESULT_OK) {
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    picture.setImageBitmap(bitmap);
                    try {
                        Utilidades.storeImage(bitmap, this, "imagen.jpg");
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(), "No se pudo guardar la imagen", Toast.LENGTH_LONG).show();
                    }
                } else {

                }
                break;
            case COD_SELECCIONA:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    picture.setImageURI(uri);

                } else {

                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void enviar() {

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
        if(TextUtils.isEmpty(telefono)){

            editTextQueja.setError(getString(R.string.error_de_campo_obligatorio));
            editTextQueja.requestFocus();
            return;
        }
        Toast.makeText(getApplicationContext(),"Se ha enviado su solicitud correctamente", Toast.LENGTH_LONG).show();
    }
}
