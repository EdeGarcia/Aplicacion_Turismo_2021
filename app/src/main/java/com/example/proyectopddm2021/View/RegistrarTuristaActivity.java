package com.example.proyectopddm2021.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyectopddm2021.R;

public class RegistrarTuristaActivity extends AppCompatActivity {

    Button btnRegistrarUsuarioTurista_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_turista);

        btnRegistrarUsuarioTurista_ = findViewById(R.id.btnRegistrarUsuarioTurista);
    }

    public void PasarPerfilLugar(View view) {
        startActivity(new Intent(RegistrarTuristaActivity.this, PerfilLugarTuristaActivity.class));
    }
}