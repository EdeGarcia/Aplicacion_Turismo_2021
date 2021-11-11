package com.example.proyectopddm2021.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectopddm2021.DAO.LugarTuristicoDAO;
import com.example.proyectopddm2021.Model.LugarTuristico;
import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.Utils.Utils;

public class RegistrarLugarAdministrador extends AppCompatActivity {

    private EditText edtServicios, edtCategoria, edtUbicacion;
    private Button btnRegistrar_;
    private String user, name, password, telephone, description;
    private LugarTuristicoDAO dao = new LugarTuristicoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_lugar_administrador2);

        user = getIntent().getStringExtra("usuario");
        name = getIntent().getStringExtra("usuario");
        password = getIntent().getStringExtra("usuario");
        telephone = getIntent().getStringExtra("usuario");
        description = getIntent().getStringExtra("usuario");

        edtServicios = (EditText) findViewById(R.id.edtServicios);
        edtCategoria = (EditText) findViewById(R.id.edtUbicacacionLugar);
        edtUbicacion = (EditText) findViewById(R.id.edtCategoria);
        btnRegistrar_ = (Button) findViewById(R.id.btnRegistrarUsuarioLugTuristico);



        btnRegistrar_.setOnClickListener(v -> {
            if(Utils.validateEditText(edtServicios) && Utils.validateEditText(edtCategoria) && Utils.validateEditText(edtUbicacion)){
                send();
            }
        });
    }

    private void send(){
        LugarTuristico lugar = new LugarTuristico();
        lugar.setUsuario(user);
        lugar.setNombre(name);
        lugar.setContrasenia(password);
        lugar.setTelefono(telephone);
        lugar.setDescripcion(description);
        lugar.setUbicacion(edtUbicacion.getText().toString());
        lugar.setCategoria(edtCategoria.getText().toString());

        dao.add(lugar).addOnSuccessListener(suc ->
        {
            Toast.makeText(this, "Registro ingresado", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(er ->
        {
            Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    public void PasarRegistroLugar2(View view) {
        startActivity(new Intent(RegistrarLugarAdministrador.this, RegistrarLugarAdministrador.class));
    }

}