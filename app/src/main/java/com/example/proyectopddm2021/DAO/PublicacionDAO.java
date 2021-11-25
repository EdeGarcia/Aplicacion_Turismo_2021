package com.example.proyectopddm2021.DAO;

import com.example.proyectopddm2021.Model.Publicacion;
import com.example.proyectopddm2021.Model.Turista;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;

public class PublicacionDAO implements Serializable {
    private DatabaseReference databaseReference;

    public PublicacionDAO() {
        databaseReference = FirebaseDatabase.getInstance().getReference(Publicacion.class.getSimpleName());
    }

    public void uploadPublication(Publicacion p){
        p.setId(databaseReference.push().getKey());
        databaseReference.child(p.getId()).setValue(p);
    }

}
