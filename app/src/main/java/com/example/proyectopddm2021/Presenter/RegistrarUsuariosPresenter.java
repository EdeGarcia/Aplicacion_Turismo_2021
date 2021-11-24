package com.example.proyectopddm2021.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.proyectopddm2021.DAO.LugarTuristicoDAO;
import com.example.proyectopddm2021.DAO.TuristaDAO;
import com.example.proyectopddm2021.Model.Categoria;
import com.example.proyectopddm2021.Model.LugarTuristico;
import com.example.proyectopddm2021.Model.Turista;
import com.example.proyectopddm2021.Utils.Utils;
import com.example.proyectopddm2021.View.PrincipalAdministradorActivity;
import com.example.proyectopddm2021.View.PrincipalTuristaActivity;
import com.example.proyectopddm2021.View.RegistrarLugarAdministrador;
import com.example.proyectopddm2021.View.RegistrarLugarAdministradorActivity;
import com.example.proyectopddm2021.View.RegistrarTuristaActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RegistrarUsuariosPresenter {
    private Context context;
    private Spinner spinnerCat;
    DatabaseReference mDataBase;
    private LugarTuristicoDAO daoLugar = new LugarTuristicoDAO();
    private TuristaDAO daoTurista = new TuristaDAO();
    final List<Categoria> categorias = new ArrayList<>();
    private Turista turista = new Turista();
    LugarTuristico lugar = new LugarTuristico();

    public RegistrarUsuariosPresenter(Activity context) {
        this.context = context;
    }


    public void sendRegistrarLugar(Spinner spinner,EditText edtServicios, EditText edtUbicacion,  LugarTuristico lugarT){

        if(Utils.validateEditText(edtServicios) && Utils.validateEditText(edtUbicacion)){
            spinnerCat = spinner;
            lugar.setCorreo(lugarT.getCorreo());
            lugar.setNombre(lugarT.getNombre());
            lugar.setContrasenia(lugarT.getContrasenia());
            lugar.setTelefono(lugarT.getTelefono());
            lugar.setDescripcion(lugarT.getDescripcion());
            lugar.setUbicacion(edtUbicacion.getText().toString());
            lugar.setCategoria(spinnerCat.getSelectedItem().toString());
            lugar.setServicio(edtServicios.getText().toString());


            daoLugar.addAuth(lugar).addOnSuccessListener(suc ->
            {
                daoLugar.login(lugar).addOnSuccessListener(su->{
                    daoLugar.add(lugar).addOnSuccessListener(s->{
                        Toast.makeText(context, "Registro ingresado", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, PrincipalAdministradorActivity.class));
                        ((Activity)context).finish();
                    });

                });

            }).addOnFailureListener(er ->
            {
                Toast.makeText(context, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, RegistrarLugarAdministrador.class));
                ((Activity)context).finish();
            });
        }

    }

    public void sendRegistrarTurista(EditText edtNombreTurista, EditText edtApellidoTurista, EditText edtTelefono, EditText edtCorreo, EditText edtContrasenia, EditText edtConfContrasenia){
        if(Utils.validateEditText(edtNombreTurista) && Utils.validateEditText(edtApellidoTurista)){
            if(Utils.validateEditText(edtTelefono) ){
                if(!Utils.validateTelefonoLength(edtTelefono)){
                    if(Utils.validateEditText(edtCorreo)){
                        if(!Utils.validarCorreo(edtCorreo.getText().toString())){
                            edtCorreo.setError("Correo no válido");
                        }else{
                            if(Utils.validateEditText(edtContrasenia)){
                                if(!Utils.validatePasswordLength(edtContrasenia)){
                                    if(Utils.validateEditText(edtConfContrasenia)){
                                        if(!Utils.validatePasswordLength(edtConfContrasenia)){
                                            if (!edtContrasenia.getText().toString().equals(edtConfContrasenia.getText().toString())) {
                                                edtConfContrasenia.setError("No coincide la contraseña");
                                            }else{

                                                turista.setCorreo(edtCorreo.getText().toString());
                                                turista.setNombres(edtNombreTurista.getText().toString());
                                                turista.setContrasenia(edtContrasenia.getText().toString());
                                                turista.setTelefono(edtTelefono.getText().toString());
                                                turista.setApellidos(edtApellidoTurista.getText().toString());

                                                daoTurista.addAuth(turista).addOnSuccessListener(suc ->
                                                {
                                                    daoTurista.login(turista).addOnSuccessListener(su->{
                                                        daoTurista.add(turista).addOnSuccessListener(s->{
                                                            Toast.makeText(context, "Registro ingresado", Toast.LENGTH_SHORT).show();
                                                            context.startActivity(new Intent(context, PrincipalTuristaActivity.class));
                                                            ((Activity)context).finish();
                                                        });

                                                    });

                                                }).addOnFailureListener(er ->
                                                {
                                                    Toast.makeText(context, "Hubo problemas al registrar", Toast.LENGTH_SHORT).show();
                                                    context.startActivity(new Intent(context, RegistrarTuristaActivity.class));
                                                    ((Activity)context).finish();
                                                });
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    public void sendRegistrarLugar1(EditText edtNombre, EditText edtCorreo, EditText edtContrasenia_one, EditText edtContrasenia_two, EditText edtTelefono, EditText edtDescripcion){
        if( Utils.validateEditText(edtNombre) && Utils.validateEditText(edtCorreo) ){
            if(!Utils.validarCorreo(edtCorreo.getText().toString())){
                edtCorreo.setError("Correo no válido");
            }else{
                if(Utils.validateEditText(edtContrasenia_one)){
                    if (!Utils.validatePasswordLength(edtContrasenia_one)){
                        if(Utils.validateEditText(edtContrasenia_two)) {
                            if (!Utils.validatePasswordLength(edtContrasenia_two)) {
                                if (!edtContrasenia_one.getText().toString().equals(edtContrasenia_two.getText().toString())) {
                                    edtContrasenia_two.setError("No coincide la contraseña");
                                } else {
                                    if (Utils.validateEditText(edtTelefono)) {
                                        if (!Utils.validateTelefonoLength(edtTelefono)) {
                                            if (Utils.validateEditText(edtDescripcion)) {
                                                Toast.makeText(context, "PASE", Toast.LENGTH_SHORT).show();

                                                Intent intent = new Intent(context, RegistrarLugarAdministrador.class);
                                                intent.putExtra("correo", edtCorreo.getText().toString());
                                                intent.putExtra("nombre", edtNombre.getText().toString());
                                                intent.putExtra("contrasenia", edtContrasenia_one.getText().toString());
                                                intent.putExtra("telefono", edtTelefono.getText().toString());
                                                intent.putExtra("descripcion", edtDescripcion.getText().toString());
                                                context.startActivity(intent);
                                                //((Activity)context).finish();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void loadcategoria(Spinner spin){
        mDataBase = FirebaseDatabase.getInstance().getReference();
        mDataBase.child("Categorias").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        String id = ds.getKey();
                        String categoria = ds.child("categoria").getValue().toString();
                        categorias.add(new Categoria(id, categoria));
                    }

                    ArrayAdapter<Categoria> arrayAdapter = new ArrayAdapter<>(((Activity)context), android.R.layout.simple_dropdown_item_1line, categorias);
                    spin.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

