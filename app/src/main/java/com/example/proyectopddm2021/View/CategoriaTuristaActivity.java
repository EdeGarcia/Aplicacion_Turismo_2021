package com.example.proyectopddm2021.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.proyectopddm2021.Adapters.LugarTuristicoAdapter;
import com.example.proyectopddm2021.Model.LugarTuristico;
import com.example.proyectopddm2021.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoriaTuristaActivity extends AppCompatActivity {

    private DatabaseReference databaseF, dbP;

    TextView Categoria;
    String categoria;

    private LugarTuristicoAdapter lTadapter;
    private RecyclerView lTRecyclerView;
    private List<LugarTuristico> lugarTuristicosList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_turista);

        lTRecyclerView = (RecyclerView) findViewById(R.id.recyclerCategoria);
        lTRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseF = FirebaseDatabase.getInstance().getReference();

        getDatosFromFireBase();
    }


    private void getDatosFromFireBase(){
        Categoria = (TextView) findViewById(R.id.tvVolcanes);
        Bundle bundle = this.getIntent().getExtras();
        categoria = bundle.getString("Categoria");
        Query query;
        query = FirebaseDatabase.getInstance().getReference("LugarTuristico")
                .orderByChild("categoria").equalTo(categoria);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for(DataSnapshot ds: datasnapshot.getChildren()){
                        String nombre = ds.child("nombre").getValue().toString();
                        String descripcion = ds.child("descripcion").getValue().toString();
                        lugarTuristicosList.add(new LugarTuristico(nombre, descripcion));
                    }

                    lTadapter = new LugarTuristicoAdapter((ArrayList<LugarTuristico>) lugarTuristicosList, R.layout.itemcategoria);
                    lTRecyclerView.setAdapter(lTadapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }

}