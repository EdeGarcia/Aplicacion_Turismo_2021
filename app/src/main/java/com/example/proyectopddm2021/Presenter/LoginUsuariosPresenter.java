package com.example.proyectopddm2021.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectopddm2021.DAO.LugarTuristicoDAO;
import com.example.proyectopddm2021.DAO.TuristaDAO;
import com.example.proyectopddm2021.Model.LoginAdministradorModel;
import com.example.proyectopddm2021.Model.LugarTuristico;
import com.example.proyectopddm2021.Model.Turista;
import com.example.proyectopddm2021.Utils.Utils;
import com.example.proyectopddm2021.View.PrincipalAdministradorActivity;
import com.example.proyectopddm2021.View.PrincipalTuristaActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoginUsuariosPresenter {

    private Context context;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    private FirebaseDatabase db =FirebaseDatabase.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private LugarTuristicoDAO dao = new LugarTuristicoDAO();
    private LugarTuristicoDAO daoLugar = new LugarTuristicoDAO();
    private TuristaDAO daoTurista = new TuristaDAO();

    public LoginUsuariosPresenter(Activity context) {
        this.context = context;
    }

    public void send(String tipo, EditText edtCorreo, EditText edtPassword){
        if(Utils.validateEditText(edtCorreo)){
            if(!Utils.validarCorreo(edtCorreo.getText().toString())){
                edtCorreo.setError("Correo no válido");
            }else{
                if (Utils.validateEditText(edtPassword)) {

                    if(!Utils.validarCorreo(edtCorreo.getText().toString())){
                        edtCorreo.setError("Correo no válido");
                    }else{
                        if(tipo.equals("1")){
                            Turista turista = new Turista();
                            turista.setCorreo(edtCorreo.getText().toString());
                            turista.setContrasenia(edtPassword.getText().toString());
                            daoTurista.login(turista).addOnSuccessListener(suc ->
                            {
                                Toast.makeText(context, "¡Bienvenido a SV Tour!", Toast.LENGTH_SHORT).show();
                                context.startActivity(new Intent(context, PrincipalTuristaActivity.class));
                                ((Activity)context).finish();
                            }).addOnFailureListener(er ->
                            {
                                Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                            });

                        }else if(tipo.equals("2")){
                            LugarTuristico lugar = new LugarTuristico();
                            lugar.setCorreo(edtCorreo.getText().toString());
                            lugar.setContrasenia(edtPassword.getText().toString());
                            daoLugar.login(lugar).addOnSuccessListener(suc ->
                            {
                                Toast.makeText(context, "¡Bienvenido a SV Tour!", Toast.LENGTH_SHORT).show();
                                context.startActivity(new Intent(context, PrincipalAdministradorActivity.class));
                                ((Activity)context).finish();
                            }).addOnFailureListener(er ->
                            {
                                Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                            });
                        }

                    }
                }
            }
        }


    }

}
