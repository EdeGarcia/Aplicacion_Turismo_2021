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
import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.Utils.Utils;

public class RegistrarLugarAdministrador extends AppCompatActivity {

    private EditText edtServicios, edtCategoria, edtUbicacion;
    private Button btnRegistrar_;
    private String correo, name, password, telephone, description;
    private LugarTuristicoDAO dao = new LugarTuristicoDAO();
    private int idContador;
    private TextView txtId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_lugar_administrador2);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        correo = getIntent().getStringExtra("correo");
        name = getIntent().getStringExtra("nombre");
        password = getIntent().getStringExtra("contrasenia");
        telephone = getIntent().getStringExtra("telefono");
        description = getIntent().getStringExtra("descripcion");

        edtServicios = (EditText) findViewById(R.id.edtServicios);
        edtCategoria = (EditText) findViewById(R.id.edtUbicacacionLugar);
        edtUbicacion = (EditText) findViewById(R.id.edtCategoria);
        btnRegistrar_ = (Button) findViewById(R.id.btnRegistrarUsuarioLugTuristico);

        btnRegistrar_.setOnClickListener(v -> {
            if(Utils.validateEditText(edtServicios) && Utils.validateEditText(edtCategoria) && Utils.validateEditText(edtUbicacion)){

                send(getApplicationContext());
            }
        });
    }

    private void send(Context context){
        idContador++;
        LugarTuristico lugar = new LugarTuristico();
        lugar.setId(idContador);
        lugar.setCorreo(correo);
        lugar.setNombre(name);
        lugar.setContrasenia(password);
        lugar.setTelefono(telephone);
        lugar.setDescripcion(description);
        lugar.setUbicacion(edtUbicacion.getText().toString());
        lugar.setCategoria(edtCategoria.getText().toString());

        //txtId.setText(dao.getPushID());
        //dao.addNameCollection(user);
        dao.add(lugar).addOnSuccessListener(suc ->
        {
            Toast.makeText(this, "Registro ingresado", Toast.LENGTH_SHORT).show();
            PaginaPrincipal();
        }).addOnFailureListener(er ->
        {
            Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
            PaginaRegistrar();
        });
    }

    public void PaginaRegistrar() {
        startActivity(new Intent(RegistrarLugarAdministrador.this, RegistrarLugarAdministrador.class));
        finish();
    }
    public void PaginaPrincipal(){
        startActivity(new Intent(RegistrarLugarAdministrador.this, PerfilLugarAdministradorActivity.class));
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