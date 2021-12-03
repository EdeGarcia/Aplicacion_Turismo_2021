package com.example.proyectopddm2021.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.proyectopddm2021.DAO.GaleriaDAO;
import com.example.proyectopddm2021.DAO.PublicacionDAO;
import com.example.proyectopddm2021.Model.Galeria;
import com.example.proyectopddm2021.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class  GaleriaAdapter extends BaseAdapter {
    ImageView img;
    CardView cardEliminar;
    Context context;
    ArrayList<Galeria> list;
    View listItemView;
    Galeria galeria;
    private int layoutResource;
    private int tipo;
    private GaleriaDAO dao = new GaleriaDAO();

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
            cardEliminar = listItemView.findViewById(R.id.btnEliminarImagenGaleria);

            Uri url = Uri.parse(galeria.getImgUrl());
            Glide.with(context).load(url).into(img);

            cardEliminar.setOnClickListener(v ->{
                new AlertDialog.Builder(context)
                        .setTitle("Eliminar")
                        .setMessage("¿Está seguro que desea eliminar la publicación?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                StorageReference photoRef = FirebaseStorage.getInstance().getReferenceFromUrl(url.toString());
                                photoRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // Eliminar de la base de datos y storage
                                        dao.delete(galeria.getId());
                                        list.remove(position);
                                        notifyDataSetChanged();
                                        list.clear();
                                        Toast.makeText(context, "¡Eliminado con éxito!",Toast.LENGTH_LONG).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        // Uh-oh, an error occurred!
                                        Toast.makeText(context, "¡Ocurrió un error!",Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            });

        }else if(tipo == 1){
            img = listItemView.findViewById(R.id.imagenItemTurista);
            Uri url = Uri.parse(galeria.getImgUrl());
            Glide.with(context).load(url).into(img);
        }

        return listItemView;
    }




}


