package com.example.proyectopddm2021.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.proyectopddm2021.Model.Publicacion;
import com.example.proyectopddm2021.R;

public class EditarPublicacionesAdministradorActivity extends AppCompatActivity {
    ImageView ivEditPost;
    Button btnEdiImgPost, btnEditPost;
    EditText edtTextEditPost;
    Publicacion p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_publicaciones_administrador);

        ivEditPost = (ImageView) findViewById(R.id.ivEditarImagenPost);
        btnEdiImgPost = (Button) findViewById(R.id.btnActualizarImagenPost);
        btnEditPost = (Button) findViewById(R.id.btnEditPost);
        edtTextEditPost = (EditText) findViewById(R.id.edtTextoPost);

        cargar();


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
}