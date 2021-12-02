package com.example.proyectopddm2021.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectopddm2021.DAO.LugarTuristicoDAO;
import com.example.proyectopddm2021.Model.Categoria;
import com.example.proyectopddm2021.Model.LugarTuristico;
import com.example.proyectopddm2021.Presenter.RegistrarUsuariosPresenter;
import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.Utils.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RegistrarLugarAdministrador extends AppCompatActivity {

    private EditText edtServicios, edtUbicacion;
    private Spinner spinnerCat;
    private Button btnRegistrar_;
    private String correo, name, password, telephone, description;
    private RegistrarUsuariosPresenter presenter;
    DatabaseReference mDataBase;
    private LugarTuristicoDAO dao = new LugarTuristicoDAO();

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
        spinnerCat = (Spinner) findViewById(R.id.spinnerCat);
        edtUbicacion = (EditText) findViewById(R.id.edtUbicacacionLugar);
        btnRegistrar_ = (Button) findViewById(R.id.btnRegistrarUsuarioLugTuristico);

        mDataBase = FirebaseDatabase.getInstance().getReference();

        btnRegistrar_.setOnClickListener(v -> {
            if(Utils.validateEditText(edtServicios) && Utils.validateEditText(edtUbicacion)){

                send(getApplicationContext());
            }
        });

        loadcategoria();

    }

    private void send(Context context){
        LugarTuristico lugar = new LugarTuristico();
        lugar.setCorreo(correo);
        lugar.setNombre(name);
        lugar.setContrasenia(password);
        lugar.setTelefono(telephone);
        lugar.setDescripcion(description);
        lugar.setUbicacion(edtUbicacion.getText().toString());
        lugar.setCategoria(spinnerCat.getSelectedItem().toString());
        lugar.setServicio(edtServicios.getText().toString());
        lugar.setCalificacion("0.0");

        dao.addAuth(lugar).addOnSuccessListener(suc ->
        {
            dao.login(lugar).addOnSuccessListener(su->{
                dao.add(lugar).addOnSuccessListener(s->{
                    Toast.makeText(this, "Registro ingresado", Toast.LENGTH_SHORT).show();
                    PaginaPrincipal();
                });

            });

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
        startActivity(new Intent(RegistrarLugarAdministrador.this, PrincipalAdministradorActivity.class));
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

    public void loadcategoria(){
        final List<Categoria> categorias = new ArrayList<>();
        mDataBase.child("Categorias").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        String id = ds.getKey();
                        String categoria = ds.child("categoria").getValue().toString();
                        categorias.add(new Categoria(id, categoria));
                    }

                    ArrayAdapter<Categoria> arrayAdapter = new ArrayAdapter<>(RegistrarLugarAdministrador.this, android.R.layout.simple_dropdown_item_1line, categorias);
                    spinnerCat.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}