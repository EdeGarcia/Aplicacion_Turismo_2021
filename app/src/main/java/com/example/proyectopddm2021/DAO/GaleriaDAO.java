package com.example.proyectopddm2021.DAO;

import com.example.proyectopddm2021.Model.Galeria;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GaleriaDAO {
    private DatabaseReference databaseReference;

    public GaleriaDAO() {
        databaseReference = FirebaseDatabase.getInstance().getReference(Galeria.class.getSimpleName());
    }
    public void uploadPublication(Galeria g){
        g.setId(databaseReference.push().getKey());
        databaseReference.child(g.getId()).setValue(g);
    }
    public Task<Void> delete(String id){
        return databaseReference.child(id).removeValue();
    }
}
