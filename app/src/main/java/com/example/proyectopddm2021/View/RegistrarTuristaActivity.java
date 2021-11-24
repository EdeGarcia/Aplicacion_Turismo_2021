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
import com.example.proyectopddm2021.Presenter.RegistrarUsuariosPresenter;
import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.Utils.Utils;

public class RegistrarTuristaActivity extends AppCompatActivity {

    Button btnRegistrar_;
    EditText edtNombreTurista, edtApellidoTurista, edtTelefono, edtCorreo, edtContrasenia, edtConfContrasenia;
    private RegistrarUsuariosPresenter presenter;

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

        presenter = new RegistrarUsuariosPresenter(RegistrarTuristaActivity.this);
        btnRegistrar_.setOnClickListener(v -> {
            presenter.sendRegistrarTurista(edtNombreTurista,edtApellidoTurista,edtTelefono, edtCorreo,edtContrasenia, edtConfContrasenia);
        });


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