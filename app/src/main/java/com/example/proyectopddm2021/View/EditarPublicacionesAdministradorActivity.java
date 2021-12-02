package com.example.proyectopddm2021.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.proyectopddm2021.DAO.LugarTuristicoDAO;
import com.example.proyectopddm2021.DAO.PublicacionDAO;
import com.example.proyectopddm2021.Fragments.PublicacionesLugarAdminFragment;
import com.example.proyectopddm2021.Model.Publicacion;
import com.example.proyectopddm2021.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class EditarPublicacionesAdministradorActivity extends AppCompatActivity {
    ImageView ivEditPost;
    Button btnEdiImgPost, btnEditPost;
    EditText edtTextEditPost;
    Publicacion p;
    ProgressBar pbEditar;
    PublicacionDAO dao;
    boolean resultado = false;
    int subir = 0;

    //Referencia para subir imagen a Storage
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    //
    private Uri imageUri;
    Activity activity;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    LugarTuristicoDAO lugar = new LugarTuristicoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_publicaciones_administrador);

        ivEditPost = (ImageView) findViewById(R.id.ivEditarImagenPost);
        btnEdiImgPost = (Button) findViewById(R.id.btnActualizarImagenPost);
        btnEditPost = (Button) findViewById(R.id.btnEditPost);
        edtTextEditPost = (EditText) findViewById(R.id.edtTextoPost);
        pbEditar = (ProgressBar) findViewById(R.id.pbEditImage);


        pbEditar.setVisibility(View.INVISIBLE);

        cargar();

        btnEdiImgPost.setOnClickListener(v ->{
            Intent galleryIntent = new Intent();
            galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
            galleryIntent.setType("image/*");
            startActivityForResult(galleryIntent, 2);
        });

        btnEditPost.setOnClickListener(w -> {
            if(comprobarEditText()){
                if(imageUri == null){
                    pbEditar.setVisibility(View.VISIBLE);

                    PublicacionDAO dao = new PublicacionDAO();
                    Publicacion pu = new Publicacion();

                    pu.setTexto(edtTextEditPost.getText().toString());
                    pu.setFecha(dtf.format(now));
                    pu.setUsuario(lugar.IdCurrentUser());
                    pu.setImgUrl(p.getImgUrl());

                    dao.deletePublication(p.getId());
                    dao.uploadPublication(pu);

                    pbEditar.setVisibility(View.INVISIBLE);

                    Toast.makeText(getApplicationContext(), "¡Exito!", Toast.LENGTH_SHORT).show();

                    finish();

                }else{
                    //Si escoge una imagen nueva
                    StorageReference photoRef = FirebaseStorage.getInstance().getReferenceFromUrl(p.getImgUrl());

                    photoRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            uploadToFirebase(imageUri);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                        }
                    });
                }

            }else{
                Toast.makeText(getApplicationContext(), "Hace falta una descripción", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private Boolean uploadToFirebase(Uri uri) {
        StorageReference fileRef = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
//        Mandando la imagen a firebase
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
//                        Haciendo el proceso de crear el objeto para enviar a Realtime database
                        PublicacionDAO dao = new PublicacionDAO();
                        Publicacion pu = new Publicacion();

                        pu.setUsuario(lugar.IdCurrentUser());
                        pu.setTexto(edtTextEditPost.getText().toString());
                        pu.setFecha(dtf.format(now));
                        pu.setImgUrl(uri.toString());
                        dao.deletePublication(p.getId());
                        dao.uploadPublication(pu);
                        pbEditar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), "¡Éxito!", Toast.LENGTH_SHORT).show();
                        resultado = true;
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull  UploadTask.TaskSnapshot snapshot) {
                pbEditar.setVisibility(View.VISIBLE);
                pbEditar.setProgress(100);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pbEditar.setVisibility(View.INVISIBLE);
                resultado = false;
            }
        });
        return resultado;
    }

    private boolean comprobarEditText(){
        boolean res = true;

        if(edtTextEditPost.getText().toString().equals("")){
            res = false;
        }

        return  res;
    }

    void cargar() {
        Intent intent;
        try {
            intent = getIntent();
            p = (Publicacion) intent.getSerializableExtra("publicacion");
            Glide.with(getApplicationContext()).load(p.getImgUrl()).into(ivEditPost);
            edtTextEditPost.setText(p.getTexto());

        } catch (Exception e) {

        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && resultCode == RESULT_OK && data != null ){
            imageUri = data.getData();
            ivEditPost.setImageURI(imageUri);
        }
    }
}