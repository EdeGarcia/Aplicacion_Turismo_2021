package com.example.proyectopddm2021.Presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectopddm2021.Adapters.LugarTuristicoAdapter;
import com.example.proyectopddm2021.Model.Categoria;
import com.example.proyectopddm2021.Model.LugarTuristico;
import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.View.CategoriaTuristaActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoriaPresenter {
    Context context;
    private LugarTuristicoAdapter lTadapter;
    private List<LugarTuristico> lugarTuristicosList = new ArrayList<>();

    public CategoriaPresenter(Activity context) {
        this.context = context;
    }
    public void getDatosFromFireBase(String categoria, RecyclerView lTRecyclerView){

        Query query;
        query = FirebaseDatabase.getInstance().getReference("LugarTuristico").orderByChild("categoria").equalTo(categoria);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for(DataSnapshot ds: datasnapshot.getChildren()){
                        String id = ds.getKey();
                        String nombre = ds.child("nombre").getValue().toString();
                        String descripcion = ds.child("descripcion").getValue().toString();
                        lugarTuristicosList.add(new LugarTuristico(id,nombre, descripcion));
                    }

                    lTadapter = new LugarTuristicoAdapter((ArrayList<LugarTuristico>) lugarTuristicosList, R.layout.itemcategoria, ((Activity)context));
                    lTRecyclerView.setAdapter(lTadapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }
}
