package com.example.proyectopddm2021.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.proyectopddm2021.Presenter.LoginAdministradorPresenter;
import com.example.proyectopddm2021.R;

public class LoginAdministrador extends AppCompatActivity {
    EditText edtUser, edtPassword;
    Button btnIngresar, btnRegistrar;
    LoginAdministradorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_administrador);

        edtUser = (EditText) findViewById(R.id.edtUsuarioLugTuristico);
        edtPassword = (EditText) findViewById(R.id.edtContrasenaLugTuristico);
        btnIngresar = (Button) findViewById(R.id.btnIngresarLugTuristico);

        presenter = new LoginAdministradorPresenter();

        btnIngresar.setOnClickListener(v -> {
            if(presenter.validation(edtUser, edtPassword, getApplicationContext())){
                homeActivity();
            }
        });

    }

    private void homeActivity(){
        startActivity(new Intent(this, RegistrarTuristaActivity.class));
        finish();
    }
}