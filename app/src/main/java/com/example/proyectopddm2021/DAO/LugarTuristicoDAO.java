package com.example.proyectopddm2021.DAO;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.proyectopddm2021.Model.LugarTuristico;
import com.example.proyectopddm2021.View.RegistrarLugarAdministrador;
import com.example.proyectopddm2021.View.RegistrarLugarAdministradorActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Executor;

public class LugarTuristicoDAO {

    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    String mensaje;
    LugarTuristico lt = new LugarTuristico();

    public LugarTuristicoDAO(){
        FirebaseDatabase db =FirebaseDatabase.getInstance();
        databaseReference = db.getReference(LugarTuristico.class.getSimpleName());

        auth = FirebaseAuth.getInstance();
    }

    //Add

    public Task<Void> add(LugarTuristico lugarTuristico)
    {
        auth.createUserWithEmailAndPassword(lugarTuristico.getCorreo(),lugarTuristico.getContrasenia());
        return databaseReference.push().setValue(lugarTuristico);

    }

    public Task<AuthResult> login(LugarTuristico lugarTuristico){
        return auth.signInWithEmailAndPassword(lugarTuristico.getCorreo(), lugarTuristico.getContrasenia());
    }


    public String getPushID(){
        String id = databaseReference.push().getKey();
        return id;
    }
}
