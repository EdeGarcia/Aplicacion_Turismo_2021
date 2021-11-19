package com.example.proyectopddm2021.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectopddm2021.DAO.LugarTuristicoDAO;
import com.example.proyectopddm2021.DAO.TuristaDAO;
import com.example.proyectopddm2021.Model.LugarTuristico;
import com.example.proyectopddm2021.Model.Turista;
import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.Utils.Utils;

public class RegistrarTuristaActivity extends AppCompatActivity {

    Button btnRegistrar_;
    EditText edtNombreTurista, edtApellidoTurista, edtTelefono, edtCorreo, edtContrasenia, edtConfContrasenia;
    private TuristaDAO dao = new TuristaDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_turista);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnRegistrar_ = (Button) findViewById(R.id.btnRegistrar);
        edtNombreTurista = (EditText) findViewById(R.id.edtNombreTurista);
        edtApellidoTurista = (EditText) findViewById(R.id.edtApellidoTurista);
        edtTelefono = (EditText) findViewById(R.id.edtTelefonoTurista);
        edtCorreo = (EditText) findViewById(R.id.edtCorreoTuristaRegistro);
        edtContrasenia = (EditText) findViewById(R.id.edtContrasenaTuristaRegistro);
        edtConfContrasenia = (EditText) findViewById(R.id.edtConfirmarContrasenaTuristaRegistro);


        btnRegistrar_.setOnClickListener(v -> {
            if(Utils.validateEditText(edtNombreTurista) && Utils.validateEditText(edtApellidoTurista)){
                if(Utils.validateEditText(edtTelefono) ){
                    if(!Utils.validateTelefonoLength(edtTelefono)){
                        if(Utils.validateEditText(edtCorreo)){
                            if(!Utils.validarCorreo(edtCorreo.getText().toString())){
                                edtCorreo.setError("Correo no válido");
                            }else{
                                if(Utils.validateEditText(edtContrasenia)){
                                    if(!Utils.validatePasswordLength(edtContrasenia)){
                                        if(Utils.validateEditText(edtConfContrasenia)){
                                            if(!Utils.validatePasswordLength(edtConfContrasenia)){
                                                if (!edtContrasenia.getText().toString().equals(edtConfContrasenia.getText().toString())) {
                                                    edtConfContrasenia.setError("No coincide la contraseña");
                                                }else{
                                                    send(getApplicationContext());
                                                    PaginaPrincipal();
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
    private void send(Context context){
        Turista turista = new Turista();
        turista.setCorreo(edtCorreo.getText().toString());
        turista.setNombres(edtNombreTurista.getText().toString());
        turista.setContrasenia(edtContrasenia.getText().toString());
        turista.setTelefono(edtTelefono.getText().toString());
        turista.setApellidos(edtApellidoTurista.getText().toString());

        dao.addAuth(turista).addOnSuccessListener(suc ->
        {
            dao.login(turista).addOnSuccessListener(su->{
                dao.add(turista).addOnSuccessListener(s->{
                    Toast.makeText(this, "Registro ingresado", Toast.LENGTH_SHORT).show();
                    PaginaPrincipal();
                });

            });

        }).addOnFailureListener(er ->
        {
            Toast.makeText(this, "Hubo problemas al registrar", Toast.LENGTH_SHORT).show();
            PaginaRegistrar();
        });
    }

    public void PaginaPrincipal(){
        startActivity(new Intent(RegistrarTuristaActivity.this, PrincipalTuristaActivity.class));
        finish();
    }
    public void PaginaRegistrar() {
        startActivity(new Intent(RegistrarTuristaActivity.this, RegistrarTuristaActivity.class));
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