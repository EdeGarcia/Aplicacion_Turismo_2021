package com.example.proyectopddm2021.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.proyectopddm2021.R;

public class TipoUsuarioActivity extends AppCompatActivity {

    ImageView ivTurista, ivLugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_usuario);

        ivTurista = findViewById(R.id.ivTurista);
        ivLugar = findViewById(R.id.ivLugar);

    }
    public void PasarTurista(View view) {
        startActivity(new Intent(TipoUsuarioActivity.this, LoginTuristaActivity.class));
    }

    public void PasarAdministrador(View view) {
        startActivity(new Intent(TipoUsuarioActivity.this, LoginAdministrador.class));
    }

}