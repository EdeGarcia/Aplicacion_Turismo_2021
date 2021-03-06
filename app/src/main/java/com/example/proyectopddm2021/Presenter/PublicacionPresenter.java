package com.example.proyectopddm2021.Presenter;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectopddm2021.Adapters.PublicacionesLugarTuristicoAdapter;
import com.example.proyectopddm2021.DAO.LugarTuristicoDAO;
import com.example.proyectopddm2021.Model.Publicacion;
import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.View.PerfilLugarTuristaActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PublicacionPresenter {
    Context context;
    private PublicacionesLugarTuristicoAdapter lTadapter;
    private List<Publicacion> publicacionList = new ArrayList<>();
    private LugarTuristicoDAO dao = new LugarTuristicoDAO();
    String id;

    public PublicacionPresenter(Context context) {
        this.context = context;
    }
    public void getDatosFromFirebase(RecyclerView lTRecyclerView){
        id = dao.IdCurrentUser();
        Query query;
        query = FirebaseDatabase.getInstance().getReference("Publicacion").orderByChild("usuario").equalTo(id);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for(DataSnapshot ds: datasnapshot.getChildren()){
                        String id = ds.child("id").getValue().toString();
                        String idUsuario = ds.child("usuario").getValue().toString();
                        String descripcion = ds.child("texto").getValue().toString();
                        String fecha = ds.child("fecha").getValue().toString();
                        String url = ds.child("imgUrl").getValue().toString();
                        publicacionList.clear();

                        Query queryLugar = FirebaseDatabase.getInstance().getReference("LugarTuristico").orderByChild("id").equalTo(idUsuario);
                        queryLugar.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    for(DataSnapshot ds2: snapshot.getChildren()) {
                                        String nombre = ds2.child("nombre").getValue().toString();
                                        publicacionList.add(new Publicacion(id,fecha, url, descripcion, nombre));
                                    }
                                    lTadapter = new PublicacionesLugarTuristicoAdapter((ArrayList<Publicacion>) publicacionList, R.layout.item_publicacion, ((Activity)context),0);
                                    lTRecyclerView.setAdapter(lTadapter);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }
                    //lTadapter = new PublicacionesLugarTuristicoAdapter((ArrayList<Publicacion>) publicacionList, R.layout.item_publicacion, ((Activity)context));
                    //lTRecyclerView.setAdapter(lTadapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }
    public void getDatosFromFirebaseT(RecyclerView lTRecyclerView){
        id = PerfilLugarTuristaActivity.idLugarTuristicoA.toString();
        Query query;
        query = FirebaseDatabase.getInstance().getReference("Publicacion").orderByChild("usuario").equalTo(id);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for(DataSnapshot ds: datasnapshot.getChildren()){
                        String id = ds.child("id").getValue().toString();
                        String idUsuario = ds.child("usuario").getValue().toString();
                        String descripcion = ds.child("texto").getValue().toString();
                        String fecha = ds.child("fecha").getValue().toString();
                        String url = ds.child("imgUrl").getValue().toString();

                        Query queryLugar = FirebaseDatabase.getInstance().getReference("LugarTuristico").orderByChild("id").equalTo(idUsuario);
                        queryLugar.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    for(DataSnapshot ds2: snapshot.getChildren()) {
                                        String nombre = ds2.child("nombre").getValue().toString();
                                        publicacionList.add(new Publicacion(id,fecha, url, descripcion, nombre));
                                    }
                                    lTadapter = new PublicacionesLugarTuristicoAdapter((ArrayList<Publicacion>) publicacionList, R.layout.item_publicacion_turista, ((Activity)context),1);
                                    lTRecyclerView.setAdapter(lTadapter);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }


}
