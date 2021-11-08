package com.example.proyectopddm2021.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.Utils.Utils;

public class RegistrarLugarAdministradorActivity extends AppCompatActivity {
    private EditText edtUsuario, edtNombre, edtContrasenia_one, edtContrasenia_two, edtTelefono, edtDescripcion;
    Button btnSiguiente_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_lugar_administrador);

        edtUsuario = (EditText) findViewById(R.id.edtUsuarioAdmin);
        edtNombre = (EditText) findViewById(R.id.edtNombreLugarAdmin);
        edtContrasenia_one = (EditText) findViewById(R.id.edtContraseniaAdmin);
        edtContrasenia_two = (EditText) findViewById(R.id.edtConfirmarContraseniaAdmin);
        edtTelefono = (EditText) findViewById(R.id.edtTelefonoLugar);
        edtDescripcion = (EditText) findViewById(R.id.edtDescripcionLugarAdmin);
        btnSiguiente_ = (Button) findViewById(R.id.btnSigRegistrarLugarTurista);

        btnSiguiente_.setOnClickListener(v -> {
            if(Utils.validateEditText(edtUsuario) && Utils.validateEditText(edtNombre) && Utils.validateEditText(edtContrasenia_one) && Utils.validateEditText(edtContrasenia_two) && Utils.validateEditText(edtTelefono) && Utils.validateEditText(edtDescripcion)){
                if(!edtContrasenia_one.getText().toString().equals(edtContrasenia_two.getText().toString())){
                   edtContrasenia_two.setError("No coincide la contraseña");
                }else{
                    nextScreenRegister();
                }
            }
        });
    }

    private void nextScreenRegister(){
        Intent intent = new Intent(this, RegistrarLugarAdministrador.class);
        intent.putExtra("usuario", edtUsuario.getText().toString());
        intent.putExtra("nombre", edtNombre.getText().toString());
        intent.putExtra("contrasenia", edtContrasenia_one.getText().toString());
        intent.putExtra("telefono", edtTelefono.getText().toString());
        intent.putExtra("descripcion", edtDescripcion.getText().toString());
        startActivity(intent);
    }


}