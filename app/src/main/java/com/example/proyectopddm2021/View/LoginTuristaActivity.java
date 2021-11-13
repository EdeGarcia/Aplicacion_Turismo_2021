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
import android.widget.Toast;

import com.example.proyectopddm2021.DAO.LugarTuristicoDAO;
import com.example.proyectopddm2021.DAO.TuristaDAO;
import com.example.proyectopddm2021.Model.LugarTuristico;
import com.example.proyectopddm2021.Model.Turista;
import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.Utils.Utils;

public class LoginTuristaActivity extends AppCompatActivity {

    private TuristaDAO dao = new TuristaDAO();
    Button btnIngresarTurista, btnRegistrarUsuarioTurista;
    EditText edtCorreo, edtContrasenia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_turista);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnIngresarTurista = (Button) findViewById(R.id.btnIngresarTurista);
        btnRegistrarUsuarioTurista = (Button) findViewById(R.id.btnRegistrarUsuarioTurista);
        edtCorreo = findViewById(R.id.edtCorreoTurista);
        edtContrasenia = findViewById(R.id.edtContrasenaUsuarioTurista);

        btnIngresarTurista.setOnClickListener(v -> {
            if(Utils.validateEditText(edtCorreo)){
                if(!Utils.validarCorreo(edtCorreo.getText().toString())){
                    edtCorreo.setError("Correo no válido");
                }else{
                    if(Utils.validateEditText(edtContrasenia)) {
                        send(getApplicationContext());
                    }
                }

            }
        });

        btnRegistrarUsuarioTurista.setOnClickListener(v ->{
            registerActivity();
        });
    }
    private void send(Context context){

        if(!Utils.validarCorreo(edtCorreo.getText().toString())){
            edtCorreo.setError("Correo no válido");
        }else{
            Turista turista = new Turista();
            turista.setCorreo(edtCorreo.getText().toString());
            turista.setContrasenia(edtContrasenia.getText().toString());

            dao.login(turista).addOnSuccessListener(suc ->
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
        startActivity(new Intent(this, PrincipalTuristaActivity.class));
        finish();
    }

    private void registerActivity(){
        startActivity(new Intent(LoginTuristaActivity.this, RegistrarTuristaActivity.class));
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