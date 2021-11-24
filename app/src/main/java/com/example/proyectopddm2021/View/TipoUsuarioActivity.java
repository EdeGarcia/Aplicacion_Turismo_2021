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
        Intent intent = new Intent(TipoUsuarioActivity.this, LoginUsuariosActivity.class);
        intent.putExtra("tipoUsuario","1");
        startActivity(intent);
    }

    public void PasarAdministrador(View view) {
        Intent intent = new Intent(TipoUsuarioActivity.this, LoginUsuariosActivity.class);
        intent.putExtra("tipoUsuario","2");
        startActivity(intent);
    }

}