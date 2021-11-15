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
import com.example.proyectopddm2021.Presenter.LoginAdministradorPresenter;
import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.Utils.Utils;

public class LoginAdministrador extends AppCompatActivity {

    private LugarTuristicoDAO dao = new LugarTuristicoDAO();
    EditText edtCorreo, edtPassword;
    Button btnIngresar, btnRegistrar;
    LoginAdministradorPresenter presenter;
    TextView tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_administrador);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        edtCorreo = (EditText) findViewById(R.id.edtCorreoLugTuristico);
        edtPassword = (EditText) findViewById(R.id.edtContrasenaLugTuristico);
        btnIngresar = (Button) findViewById(R.id.btnIngresarLugTuristico);
        btnRegistrar = findViewById(R.id.btnRegistrarUsuarioLugTuristico);

        tx = findViewById(R.id.txt);
        //presenter = new LoginAdministradorPresenter();


        btnIngresar.setOnClickListener(v -> {
            if(Utils.validateEditText(edtCorreo)){
                if(!Utils.validarCorreo(edtCorreo.getText().toString())){
                    edtCorreo.setError("Correo no válido");
                }else{
                    if(Utils.validateEditText(edtPassword)){
                        send(getApplicationContext());
                    }
                }
            }
        });

        btnRegistrar.setOnClickListener(v ->{
            registerActivity();
        });
    }

    private void send(Context context){
        if(!Utils.validarCorreo(edtCorreo.getText().toString())){
            edtCorreo.setError("Correo no válido");
        }else{
            LugarTuristico lugar = new LugarTuristico();
            lugar.setCorreo(edtCorreo.getText().toString());
            lugar.setContrasenia(edtPassword.getText().toString());

            dao.login(lugar).addOnSuccessListener(suc ->
            {
                Toast.makeText(this, "¡Bienvenido a SV Tour!", Toast.LENGTH_SHORT).show();
                PrincipalActivity();
            }).addOnFailureListener(er ->
            {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            });
        }

    }

    private void PrincipalActivity(){
        startActivity(new Intent(this, PrincipalAdministradorActivity.class));
        finish();
    }

    private void registerActivity(){
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