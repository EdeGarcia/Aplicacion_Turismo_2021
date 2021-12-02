package com.example.proyectopddm2021.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.example.proyectopddm2021.Adapters.GaleriaAdapter;
import com.example.proyectopddm2021.DAO.GaleriaDAO;
import com.example.proyectopddm2021.DAO.LugarTuristicoDAO;
import com.example.proyectopddm2021.DAO.TuristaDAO;
import com.example.proyectopddm2021.Model.Galeria;
import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.View.FullScreenImagen;
import com.example.proyectopddm2021.View.PerfilLugarTuristaActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class GaleriaPresenter {
    Context context;
    String id;
    String idG;
    Boolean resultado = false;
    private LugarTuristicoDAO daoLugar = new LugarTuristicoDAO();
    private TuristaDAO daoTurista = new TuristaDAO();
    private GaleriaDAO daoGaleria = new GaleriaDAO();
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private List<Galeria> galeriaList = new ArrayList<>();
    private GaleriaAdapter lTadapter;
    private Uri uri;


    public GaleriaPresenter(Context context) {
        this.context = context;
    }
    public void getDatosFromFirebase(GridView grid){
        id = daoLugar.IdCurrentUser();
        Query query;
        query = FirebaseDatabase.getInstance().getReference("Galeria").orderByChild("idLugar").equalTo(id);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for(DataSnapshot ds: datasnapshot.getChildren()){
                        String idUsuario = ds.child("idLugar").getValue().toString();
                        idG = ds.child("id").getValue().toString();
                        String fecha = ds.child("fecha").getValue().toString();
                        String url = ds.child("imgUrl").getValue().toString();

                        galeriaList.add(new Galeria(idG,url, fecha, idUsuario));
                        /// se manda el valor 0 tipo = LugarTuristico
                        lTadapter = new GaleriaAdapter(((Activity)context), (ArrayList<Galeria>) galeriaList, R.layout.galeri_item,0);
                        grid.setAdapter(lTadapter);

                    }
                    grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Galeria galeria = galeriaList.get(position);
                            Intent intent = new Intent(context, FullScreenImagen.class);
                            intent.putExtra("idImagen",galeria.getId());
                            context.startActivity(intent);
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }
    public void getDatosFromFirebaseTurista(GridView grid){
        id = PerfilLugarTuristaActivity.id;
        Query query;
        query = FirebaseDatabase.getInstance().getReference("Galeria").orderByChild("idLugar").equalTo(id);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for(DataSnapshot ds: datasnapshot.getChildren()){
                        String idUsuario = ds.child("idLugar").getValue().toString();
                        idG = ds.child("id").getValue().toString();
                        String fecha = ds.child("fecha").getValue().toString();
                        String url = ds.child("imgUrl").getValue().toString();

                        galeriaList.add(new Galeria(idG,url, fecha, idUsuario));
                        /// se manda el valor 1 tipo = Tursta
                        lTadapter = new GaleriaAdapter(((Activity)context), (ArrayList<Galeria>) galeriaList, R.layout.item_galeria_turista,1);
                        grid.setAdapter(lTadapter);

                    }
                    grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Galeria galeria = galeriaList.get(position);
                            Intent intent = new Intent(context, FullScreenImagen.class);
                            intent.putExtra("idImagen",galeria.getId());
                            context.startActivity(intent);
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }
    public Boolean uploadToFirebase(Uri uri, String extension, ProgressBar pbImage) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        StorageReference fileRef = storageReference.child(System.currentTimeMillis() + "." + extension);
//        Mandando la imagen a firebase
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
//                        Haciendo el proceso de crear el objeto para enviar a Realtime database
                        Galeria g = new Galeria();
                        GaleriaDAO gdao = new GaleriaDAO();

                        g.setFecha(dtf.format(now));
                        g.setIdLugar(daoLugar.IdCurrentUser());
                        g.setImgUrl(uri.toString());
                        //limpiando lista para no mostrar img duplicadas en el gridView
                        galeriaList.clear();
                        gdao.uploadPublication(g);
                        pbImage.setVisibility(View.INVISIBLE);
                        Toast.makeText(context, "¡La publicacion se realizo con éxito!", Toast.LENGTH_LONG).show();
                        resultado = true;
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull  UploadTask.TaskSnapshot snapshot) {
                pbImage.setVisibility(View.VISIBLE);
                pbImage.setProgress(100);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pbImage.setVisibility(View.INVISIBLE);
                Toast.makeText(context, "¡Error al subir la imagen!", Toast.LENGTH_LONG).show();
                resultado = false;
            }
        });
        return resultado;
    }

    public void MostrarImagenId(String id, ImageView img){
        Query query;
        query = FirebaseDatabase.getInstance().getReference("Galeria").orderByChild("id").equalTo(id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot ds: snapshot.getChildren()) {
                        String urlImage = ds.child("imgUrl").getValue().toString();
                        uri = Uri.parse(urlImage);
                        Glide.with(context).load(uri).into(img);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void EliminarId(){
        id = daoLugar.IdCurrentUser();
        Query query;
        query = FirebaseDatabase.getInstance().getReference("Galeria").orderByChild("idLugar").equalTo(id);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot ds: snapshot.getChildren()) {
                        String idImagen = ds.child("id").getValue().toString();
                        if(daoGaleria.delete(idImagen).isSuccessful()){
                            Toast.makeText(context, "Se elimino correctamente", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(context, "No se pudo eliminar", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}

