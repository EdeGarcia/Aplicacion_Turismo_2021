package com.example.proyectopddm2021.DAO;

import com.example.proyectopddm2021.Model.LugarTuristico;
import com.example.proyectopddm2021.Model.Turista;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TuristaDAO {
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;

    public TuristaDAO(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Turista.class.getSimpleName());
        auth = FirebaseAuth.getInstance();
    }
    public Task<Void> add(Turista turista)
    {
        auth.createUserWithEmailAndPassword(turista.getCorreo(),turista.getContrasenia());
        return databaseReference.push().setValue(turista);
    }

    public Task<AuthResult> login(Turista turista){
        return auth.signInWithEmailAndPassword(turista.getCorreo(), turista.getContrasenia());
    }


}
