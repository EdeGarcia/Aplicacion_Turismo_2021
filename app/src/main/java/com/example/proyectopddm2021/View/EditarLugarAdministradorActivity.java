package com.example.proyectopddm2021.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyectopddm2021.DAO.LugarTuristicoDAO;
import com.example.proyectopddm2021.Model.LugarTuristico;
import com.example.proyectopddm2021.Presenter.LugarTuristicoPresenter;
import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.Utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Map;

public class EditarLugarAdministradorActivity extends AppCompatActivity {

    Button btnGuardar;
    EditText edtNombre, edtUbicacion, edtDescripcion, edtTelefono, edtServicios;
    private Spinner spinner;
    private LugarTuristico lugar = new LugarTuristico();
    private LugarTuristicoDAO dao = new LugarTuristicoDAO();
    private LugarTuristicoPresenter presenter ;
    FloatingActionButton btnFotoPerfil;
    ImageView img;
    private Uri imageUri;
    private String extension;
    Map<String, Object> map;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_lugar_administrador);

        edtNombre = findViewById(R.id.edtNombreEdit);
        edtDescripcion = findViewById(R.id.edtDescripcionEdit);
        edtServicios = findViewById(R.id.edtServiciosEdit);
        edtTelefono = findViewById(R.id.edtTelefonoEdit);
        edtUbicacion = findViewById(R.id.edtUbicacionEdit);
        btnGuardar = findViewById(R.id.btnGuardarLugar);
        btnFotoPerfil = (FloatingActionButton) findViewById(R.id.btnAgregarImagenPerfilAdmin);
        img = (ImageView) findViewById(R.id.imgPerfilEditar);

        presenter = new LugarTuristicoPresenter(this);
        presenter.MostrarImagenPerfil(img);
        presenter.MostrarDatos(edtNombre, edtTelefono, edtDescripcion,edtUbicacion, edtServicios);


        map = presenter.MapeoUpdate(lugar);

        btnFotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent();
                editIntent.setAction(Intent.ACTION_GET_CONTENT);
                editIntent.setType("image/*");
                startActivityForResult(editIntent, 2);

            }
        });
        btnGuardar.setOnClickListener(v->{
            extension = getFileExtension(imageUri);
            presenter.uploadToFirebase(imageUri,extension);
            presenter.Editar(Data(getApplicationContext()), edtNombre, edtDescripcion, edtTelefono, edtServicios, edtUbicacion);
        });
    }

    public Map<String, Object> Data(Context context){
        lugar.setNombre(edtNombre.getText().toString());
        lugar.setDescripcion(edtDescripcion.getText().toString());
        lugar.setServicio(edtServicios.getText().toString());
        lugar.setTelefono(edtTelefono.getText().toString());
        lugar.setUbicacion(edtUbicacion.getText().toString());
        lugar.setImgUri(imageUri.toString());
        Map<String, Object> map = presenter.MapeoUpdate(lugar);
        return map;
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && resultCode == RESULT_OK && data != null ){
            imageUri = data.getData();
            img.setImageURI(imageUri);
        }
    }
}