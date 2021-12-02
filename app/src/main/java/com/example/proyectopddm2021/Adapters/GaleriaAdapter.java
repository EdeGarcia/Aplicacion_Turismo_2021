package com.example.proyectopddm2021.Adapters;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.example.proyectopddm2021.Model.Galeria;
import com.example.proyectopddm2021.R;

import java.util.ArrayList;

public class GaleriaAdapter extends BaseAdapter {
    ImageView img;
    Context context;
    ArrayList<Galeria> list;
    View listItemView;
    Galeria galeria;
    private int layoutResource;
    private int tipo;

    public GaleriaAdapter(Activity context) {
        this.context = context;
    }

    public GaleriaAdapter(Activity context, ArrayList<Galeria> list, int layoutResource, int tipo) {
        this.context = context;
        this.list = list;
        this.layoutResource = layoutResource;
        this.tipo = tipo;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        galeria = list.get(position);
        listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false);
        }

        if(tipo == 0){
            img = listItemView.findViewById(R.id.imagenItem);
            Uri url = Uri.parse(galeria.getImgUrl());
            Glide.with(context).load(url).into(img);
        }else if(tipo == 1){
            img = listItemView.findViewById(R.id.imagenItemTurista);
            Uri url = Uri.parse(galeria.getImgUrl());
            Glide.with(context).load(url).into(img);
        }

        return listItemView;
    }



}


