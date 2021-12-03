package com.example.proyectopddm2021.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.example.proyectopddm2021.DAO.LugarTuristicoDAO;
import com.example.proyectopddm2021.Model.LugarTuristico;
import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.Utils.Utils;
import com.example.proyectopddm2021.View.PerfilLugarAdministradorActivity;
import com.example.proyectopddm2021.View.PerfilLugarTuristaActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class LugarTuristicoPresenter {
    Context context;
    private DatabaseReference databaseReference;
    private LugarTuristicoDAO daoLugar = new LugarTuristicoDAO();
    FirebaseDatabase db =FirebaseDatabase.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    LugarTuristico lugarTuristicoData = new LugarTuristico();
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    String _nombre, _Uid, _telefono, _ubicacion,_descripcion, _servicios, _correo, _calificacionLT, _uri;

    public LugarTuristicoPresenter(Activity context) {
        this.context = context;
    }

    public LugarTuristicoPresenter() {
    }

    public Map<String, Object> MapeoUpdate(LugarTuristico lugarTuristico){
        Map<String, Object> lugarMap = new HashMap<>();
        lugarMap.put("nombre",lugarTuristico.getNombre());
        lugarMap.put("telefono", lugarTuristico.getTelefono());
        lugarMap.put("ubicacion", lugarTuristico.getUbicacion());
        lugarMap.put("descripcion", lugarTuristico.getDescripcion());
        lugarMap.put("servicio", lugarTuristico.getServicio());
        lugarMap.put("imgUri", lugarTuristico.getImgUri());
        return lugarMap;
    }

    public void DatosPerfil(TextView txtNombre, TextView txtTelefono, TextView txtDescripcion, TextView txtUbicacion, TextView txtServicios, TextView txtCalificacionLT){
        String id = PerfilLugarTuristaActivity.idLugarTuristicoA.toString();
        Query query = FirebaseDatabase.getInstance().getReference("LugarTuristico").orderByChild("id").equalTo(id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot ds: snapshot.getChildren()) {
                        _nombre = ds.child("nombre").getValue().toString();
                        _telefono = ds.child("telefono").getValue().toString();
                        _descripcion = ds.child("descripcion").getValue().toString();
                        _ubicacion = ds.child("ubicacion").getValue().toString();
                        _servicios = ds.child("servicio").getValue().toString();
                        _calificacionLT = ds.child("calificacion").getValue().toString();
                        txtNombre.setText(_nombre);
                        txtTelefono.setText(_telefono);
                        txtDescripcion.setText(_descripcion);
                        txtUbicacion.setText(_ubicacion);
                        txtServicios.setText(_servicios);
                        txtCalificacionLT.setText(_calificacionLT);
                    }
                }else{
                    txtNombre.setText("-");
                    txtDescripcion.setText("-");
                    txtTelefono.setText("-");
                    txtUbicacion.setText("-");
                    txtServicios.setText("-");
                    txtCalificacionLT.setText("-");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });
    }
    public void DatosPerfilLugarAdmin(TextView txtNombre, TextView txtTelefono, TextView txtDescripcion, TextView txtUbicacion, TextView txtServicios){

        databaseReference = db.getReference("LugarTuristico").child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    LugarTuristico lugar = snapshot.getValue(LugarTuristico.class);
                    _nombre = lugar.getNombre();
                    _Uid = lugar.getId();
                    _telefono = lugar.getTelefono();
                    _descripcion = lugar.getDescripcion();
                    _ubicacion = lugar.getUbicacion();
                    _servicios = lugar.getServicio();

                    txtNombre.setText(_nombre);
                    txtDescripcion.setText(_descripcion);
                    txtTelefono.setText(_telefono);
                    txtUbicacion.setText(_ubicacion);
                    txtServicios.setText(_servicios);
                }else{
                    txtNombre.setText("-");
                    txtDescripcion.setText("-");
                    txtTelefono.setText("-");
                    txtUbicacion.setText("-");
                    txtServicios.setText("-");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });
    }

    public void MostrarDatos(EditText edtNombre, EditText edtTelefono, EditText edtDescripcion, EditText edtUbicacion, EditText edtServicios){

        databaseReference = db.getReference("LugarTuristico").child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    LugarTuristico lugar = snapshot.getValue(LugarTuristico.class);
                    _nombre = lugar.getNombre();
                    _Uid = lugar.getId();
                    _telefono = lugar.getTelefono();
                    _descripcion = lugar.getDescripcion();
                    _ubicacion = lugar.getUbicacion();
                    _servicios = lugar.getServicio();

                    edtNombre.setText(_nombre);
                    edtDescripcion.setText(_descripcion);
                    edtTelefono.setText(_telefono);
                    edtUbicacion.setText(_ubicacion);
                    edtServicios.setText(_servicios);
                }else{
                    edtNombre.setText("-");
                    edtDescripcion.setText("-");
                    edtTelefono.setText("-");
                    edtUbicacion.setText("-");
                    edtServicios.setText("-");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });
    }

    /**public void DatosPrincipal(TextView txtNombre, TextView txtCorreo){

        databaseReference = db.getReference("LugarTuristico").child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    LugarTuristico lugar = snapshot.getValue(LugarTuristico.class);
                    _nombre = lugar.getNombre();
                    _Uid = lugar.getId();
                    _correo = lugar.getCorreo();

                    txtNombre.setText(_nombre);
                    txtCorreo.setText(_correo);

                }else{
                    txtNombre.setText("-");
                    txtCorreo.setText("-");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });
    }**/
    public void DatosPrincipal(TextView txtNombre, TextView txtCorreo, Context cont, ImageView img){

        databaseReference = db.getReference("LugarTuristico").child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    LugarTuristico lugar = snapshot.getValue(LugarTuristico.class);
                    _nombre = lugar.getNombre();
                    _Uid = lugar.getId();
                    _correo = lugar.getCorreo();
                    _uri = lugar.getImgUri();

                    txtNombre.setText(_nombre);
                    txtCorreo.setText(_correo);
                    if(_uri == null){
                        img.setImageResource(R.drawable.imagen_default_logo_lugar);
                    }else {
                        Glide.with(cont).load(Uri.parse(_uri)).into(img);
                    }

                }else{
                    txtNombre.setText("-");
                    txtCorreo.setText("-");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });
    }


    public void Editar(Map<String, Object> mapeo,EditText edtNombre, EditText edtDescripcion, EditText edtTelefono, EditText edtServicios, EditText edtUbicacion){
        if(Utils.validateEditText(edtNombre) && Utils.validateEditText(edtDescripcion) && Utils.validateEditText(edtTelefono) && Utils.validateEditText(edtServicios) && Utils.validateEditText(edtUbicacion)) {
            if (!Utils.validateTelefonoLength(edtTelefono)) {
                daoLugar.Update(mapeo).addOnSuccessListener(suc->{
                    Toast.makeText(context, "Se guardaron los cambios correctamente", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, PerfilLugarAdministradorActivity.class));
                }).addOnFailureListener(f->{
                    Toast.makeText(context, "No se pudo actualizar", Toast.LENGTH_SHORT).show();
                });
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void uploadToFirebase(Uri uri, String extension) {
        StorageReference fileRef = storageReference.child(System.currentTimeMillis() + "." + extension);

//        Mandando la imagen a firebase
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //EliminarImagenAnterior();
                        daoLugar.delete(_Uid);
                        LugarTuristico lugarTuristico = new LugarTuristico();
                        lugarTuristico.setImgUri(uri.toString());
//                              Haciendo el proceso de crear el objeto para enviar a Realtime database
                        daoLugar.addFotoPerfil(lugarTuristico);

                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull  UploadTask.TaskSnapshot snapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "¡Error al subir la imagen!", Toast.LENGTH_LONG).show();

            }
        });
    }
    public void MostrarImagenPerfil(ImageView img){
        //en editar
        databaseReference = db.getReference("LugarTuristico").child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    LugarTuristico lugar = snapshot.getValue(LugarTuristico.class);
                    _Uid = lugar.getId();
                    _uri = lugar.getImgUri();

                    if(_uri == null){
                        img.setImageResource(R.drawable.imagen_default_logo_lugar);
                    }else{
                        Glide.with(context).load(Uri.parse(_uri)).into(img);
                    }
                }else{
                    img.setImageResource(R.drawable.imagen_default_logo_lugar);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });
    }
    public void MostrarImagenPerfilTurista(ImageView img, String id){
        databaseReference = db.getReference("LugarTuristico").child(id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    LugarTuristico lugar = snapshot.getValue(LugarTuristico.class);
                    _Uid = lugar.getId();
                    _uri = lugar.getImgUri();

                    if(_uri == null){
                        img.setImageResource(R.drawable.imagen_default_logo_lugar);
                    }else{
                        Glide.with(context).load(Uri.parse(_uri)).into(img);
                    }
                }else{
                    img.setImageResource(R.drawable.imagen_default_logo_lugar);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });
    }


}
