package com.example.proyectopddm2021.DAO;

import com.example.proyectopddm2021.Model.Turista;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TuristaDAO {
    private DatabaseReference databaseReference;

    public TuristaDAO(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Turista.class.getSimpleName());
    }


}
