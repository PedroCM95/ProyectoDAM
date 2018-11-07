package com.example.pedro.proyecto_pgl_2018;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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

import com.example.pedro.proyecto_pgl_2018.constantes.RequestUtility;
import com.example.pedro.proyecto_pgl_2018.constantes.Utilidades;
import com.example.pedro.proyecto_pgl_2018.pojos.Solicitud;

public class RellenarFormularioInsertar extends AppCompatActivity {

    EditText editTextNombre, editTextLugar, editTextQueja;
    Button  button_captura;
    ImageView picture;

    final int COD_SELECCIONA = 1;
    public static final int CAMERA_REQUEST = 2;

    Bitmap bitmap = null;


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
        editTextLugar = (EditText) findViewById(R.id.editTextLugar);
        editTextQueja = (EditText) findViewById(R.id.editTextQueja);
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
        editTextQueja = (EditText) findViewById(R.id.editTextQueja);
        editTextLugar = (EditText) findViewById(R.id.editTextLugar);

        editTextNombre.setError(null);
        editTextQueja.setError(null);
        editTextLugar.setError(null);

        String nombre = String.valueOf(editTextNombre.getText());
        String lugar = String.valueOf(editTextLugar.getText());
        String queja = String.valueOf(editTextQueja.getText());

        if(TextUtils.isEmpty(nombre)){

            editTextNombre.setError(getString(R.string.error_de_campo_obligatorio));
            editTextNombre.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(lugar)){

            editTextLugar.setError(getString(R.string.error_de_campo_obligatorio));
            editTextLugar.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(queja)){

            editTextQueja.setError(getString(R.string.error_de_campo_obligatorio));
            editTextQueja.requestFocus();
            return;
        }


        Solicitud solicitud = new Solicitud(Utilidades.SIN_VALOR_INT, nombre, lugar, queja, bitmap);
        RequestUtility.insert(getContentResolver(), solicitud, this);
        finish();


    }

    private void cargarImagen() {

        final CharSequence[] opciones = {"Sacar Foto", "Cargar Imagen", "Cancelar"};
        final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(RellenarFormularioInsertar.this);
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
                    bitmap = (Bitmap) data.getExtras().get("data");
                    picture.setImageBitmap(bitmap);

                } else {

                }
                break;
            case COD_SELECCIONA:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    picture.setImageURI(uri);
                    bitmap = ((BitmapDrawable) picture.getDrawable()).getBitmap();

                } else {

                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
