package com.example.proyectopddm2021.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.proyectopddm2021.Adapters.GaleriaAdapter;
import com.example.proyectopddm2021.Presenter.GaleriaPresenter;
import com.example.proyectopddm2021.R;

public class FullScreenImagen extends AppCompatActivity {
    ImageView img;
    Uri url;
    private GaleriaAdapter galeriaAdapter;
    private GaleriaPresenter presenter;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_imagen);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        img = (ImageView) findViewById(R.id.MostrarImagen);

        Intent datos = getIntent();
        String id = datos.getExtras().getString("idImagen");
        galeriaAdapter = new GaleriaAdapter(this);
        presenter = new GaleriaPresenter(FullScreenImagen.this);
        presenter.MostrarImagenId(id,img);

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}