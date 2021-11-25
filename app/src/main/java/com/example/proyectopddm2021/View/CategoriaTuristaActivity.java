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
import com.example.proyectopddm2021.Presenter.CategoriaPresenter;
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

    String categoria, nLista;
    public RecyclerView lTRecyclerView;
    private CategoriaPresenter presenter;
    TextView txtLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_turista);

        lTRecyclerView = (RecyclerView) findViewById(R.id.recyclerCategoria);
        lTRecyclerView.setLayoutManager(new LinearLayoutManager(CategoriaTuristaActivity.this));

        Bundle bundle = this.getIntent().getExtras();
        categoria = bundle.getString("Categoria");
        nLista = bundle.getString("nLista");
        txtLista = (TextView) findViewById(R.id.nlista);
        txtLista.setText(nLista);

        presenter = new CategoriaPresenter(CategoriaTuristaActivity.this);
        databaseF = FirebaseDatabase.getInstance().getReference();

        presenter.getDatosFromFireBase(categoria, lTRecyclerView);
    }




}