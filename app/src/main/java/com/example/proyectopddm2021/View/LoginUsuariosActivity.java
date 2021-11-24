package com.example.proyectopddm2021.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectopddm2021.DAO.LugarTuristicoDAO;
import com.example.proyectopddm2021.Model.LugarTuristico;
import com.example.proyectopddm2021.Presenter.LoginUsuariosPresenter;
import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.Utils.Utils;

public class LoginUsuariosActivity extends AppCompatActivity {

    EditText edtCorreo, edtPassword;
    Button btnIngresar, btnRegistrar;
    LoginUsuariosPresenter presenter;
    TextView tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuarios);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        edtCorreo = (EditText) findViewById(R.id.edtCorreoLugTuristico);
        edtPassword = (EditText) findViewById(R.id.edtContrasenaLugTuristico);
        btnIngresar = (Button) findViewById(R.id.btnIngresarLugTuristico);
        btnRegistrar = findViewById(R.id.btnRegistrarUsuarioLugTuristico);

        tx = findViewById(R.id.txt);
        presenter = new LoginUsuariosPresenter(LoginUsuariosActivity.this);

        btnIngresar.setOnClickListener(v -> {
            String tipo = getIntent().getStringExtra("tipoUsuario");
            presenter.send(tipo, edtCorreo, edtPassword);

        });

        btnRegistrar.setOnClickListener(v ->{
            String tipo = getIntent().getStringExtra("tipoUsuario");
            tipoRegistro(tipo);
        });

    }

    public void tipoRegistro(String tipo){
        if(tipo.equals("1")){
            registerActivityTurista();
        }else if(tipo.equals("2")){
            registerActivityLugar();
        }
    }

    private void registerActivityTurista(){
        startActivity(new Intent(this, RegistrarTuristaActivity.class));
        finish();
    }
    private void registerActivityLugar(){
        startActivity(new Intent(this, RegistrarLugarAdministradorActivity.class));
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