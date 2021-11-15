package com.example.proyectopddm2021.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyectopddm2021.R;

public class PrincipalAdministradorActivity extends AppCompatActivity {

    Button btnPublicaciones, btnPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_administrador);
        btnPerfil = (Button) findViewById(R.id.btnPefil);
        btnPublicaciones = (Button) findViewById(R.id.btnPublicaciones);

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IrAPerfil();
            }
        });
    }
    public void IrAPerfil(){
        startActivity(new Intent(this, PerfilLugarAdministradorActivity.class));
    }
}