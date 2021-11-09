package com.example.proyectopddm2021.DAO;

import com.example.proyectopddm2021.Model.LugarTuristico;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LugarTuristicoDAO {

    private DatabaseReference databaseReference;

    public LugarTuristicoDAO(){
        FirebaseDatabase db =FirebaseDatabase.getInstance();
        databaseReference = db.getReference(LugarTuristico.class.getSimpleName());
    }

    //Add

    public Task<Void> add(LugarTuristico lugarTuristico)
    {
        return databaseReference.push().setValue(lugarTuristico);
    }

    
}
