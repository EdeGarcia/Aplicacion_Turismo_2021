package com.example.proyectopddm2021.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyectopddm2021.R;

public class LoginTuristaActivity extends AppCompatActivity {

    Button btnIngresarTurista, btnRegistrarUsuarioTurista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_turista);

        btnIngresarTurista = findViewById(R.id.btnIngresarTurista);
        btnRegistrarUsuarioTurista = findViewById(R.id.btnRegistrarUsuarioTurista);
    }
    public void PasarPerfilLugar(View view) {
        startActivity(new Intent(LoginTuristaActivity.this, PerfilLugarTuristaActivity.class));
    }

    public void PasarRegistrarUsuarioTurista(View view) {
        startActivity(new Intent(LoginTuristaActivity.this, RegistrarTuristaActivity.class));
    }
}