package com.example.proyectopddm2021.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectopddm2021.Presenter.RegistrarUsuariosPresenter;
import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.Utils.Utils;

public class RegistrarLugarAdministradorActivity extends AppCompatActivity {
    private EditText edtCorreo, edtNombre, edtContrasenia_one, edtContrasenia_two, edtTelefono, edtDescripcion;
    private Button btnSiguiente_;
    private RegistrarUsuariosPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_lugar_administrador);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        edtCorreo = (EditText) findViewById(R.id.edtCorreoAdmin);
        edtNombre = (EditText) findViewById(R.id.edtNombreLugarAdmin);
        edtContrasenia_one = (EditText) findViewById(R.id.edtContraseniaAdmin);
        edtContrasenia_two = (EditText) findViewById(R.id.edtConfirmarContraseniaAdmin);
        edtTelefono = (EditText) findViewById(R.id.edtTelefonoLugar);
        edtDescripcion = (EditText) findViewById(R.id.edtDescripcionLugarAdmin);
        btnSiguiente_ = (Button) findViewById(R.id.btnSigRegistrarLugarTurista);

        btnSiguiente_.setOnClickListener(v -> {
            if( Utils.validateEditText(edtNombre) && Utils.validateEditText(edtCorreo) ){
                if(!Utils.validarCorreo(edtCorreo.getText().toString())){
                    edtCorreo.setError("Correo no válido");
                }else{
                    if(Utils.validateEditText(edtContrasenia_one)){
                        if (!Utils.validatePasswordLength(edtContrasenia_one)){
                            if(Utils.validateEditText(edtContrasenia_two)) {
                                if (!Utils.validatePasswordLength(edtContrasenia_two)) {
                                    if (!edtContrasenia_one.getText().toString().equals(edtContrasenia_two.getText().toString())) {
                                        edtContrasenia_two.setError("No coincide la contraseña");
                                    } else {
                                        if (Utils.validateEditText(edtTelefono)) {
                                            if (!Utils.validateTelefonoLength(edtTelefono)) {
                                                if (Utils.validateEditText(edtDescripcion)) {
                                                    nextScreenRegister();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });


    }

    private void nextScreenRegister(){
        Intent intent = new Intent(this, RegistrarLugarAdministrador.class);
        intent.putExtra("correo", edtCorreo.getText().toString());
        intent.putExtra("nombre", edtNombre.getText().toString());
        intent.putExtra("contrasenia", edtContrasenia_one.getText().toString());
        intent.putExtra("telefono", edtTelefono.getText().toString());
        intent.putExtra("descripcion", edtDescripcion.getText().toString());
        startActivity(intent);
        finish();
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }


}