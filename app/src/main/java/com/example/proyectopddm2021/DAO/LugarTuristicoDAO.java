package com.example.proyectopddm2021.DAO;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.proyectopddm2021.Model.LugarTuristico;
import com.example.proyectopddm2021.View.RegistrarLugarAdministrador;
import com.example.proyectopddm2021.View.RegistrarLugarAdministradorActivity;
import com.google.android.gms.common.internal.AuthAccountRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;
import java.util.concurrent.Executor;

public class LugarTuristicoDAO {

    private DatabaseReference databaseReference;
    private FirebaseAuth auth;

    public LugarTuristicoDAO(){
        FirebaseDatabase db =FirebaseDatabase.getInstance();
        databaseReference = db.getReference(LugarTuristico.class.getSimpleName());
        auth = FirebaseAuth.getInstance();
    }

    //Add

    public Task<AuthResult> addAuth(LugarTuristico lugarTuristico){
        return auth.createUserWithEmailAndPassword(lugarTuristico.getCorreo(),lugarTuristico.getContrasenia());
    }
    public Task<Void> add(LugarTuristico lugarTuristico)
    {
        lugarTuristico.setId(IdCurrentUser());
        lugarTuristico.setTipoUsuario("Administrador");
        return databaseReference.child(IdCurrentUser()).setValue(lugarTuristico);
    }
    public Task<Void> addFotoPerfil(LugarTuristico lugarTuristico){
        return databaseReference.child(IdCurrentUser()).child("imgUri").setValue(lugarTuristico.getImgUri());
    }

    public void delete(String id){
        databaseReference.child(id).child("imgUri").removeValue();
    }

    public Task<AuthResult> login(LugarTuristico lugarTuristico){
        return auth.signInWithEmailAndPassword(lugarTuristico.getCorreo(), lugarTuristico.getContrasenia());
    }
    public void signOut(){
        auth.signOut();
    }

    public Task<Void> Update(Map<String, Object> lugarMap){
        return databaseReference.child(IdCurrentUser()).updateChildren(lugarMap);
    }

    public String IdCurrentUser(){
        FirebaseUser user = auth.getInstance().getCurrentUser();
        String key = user.getUid();
        return key;
    }

}
