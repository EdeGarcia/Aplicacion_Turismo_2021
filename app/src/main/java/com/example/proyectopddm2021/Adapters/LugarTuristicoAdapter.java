package com.example.proyectopddm2021.Adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectopddm2021.Model.LugarTuristico;
import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.View.LoginTuristaActivity;
import com.example.proyectopddm2021.View.PerfilLugarAdministradorActivity;
import com.example.proyectopddm2021.View.RegistrarTuristaActivity;

import java.util.ArrayList;

public class LugarTuristicoAdapter extends RecyclerView.Adapter<LugarTuristicoAdapter.LugarTuristicoViewHolder> {

    private Context mContext;
    private int layoutResource;
    private ArrayList<LugarTuristico> arrayListLugarTuristico;

    public LugarTuristicoAdapter(ArrayList<LugarTuristico> arrayListLugarTuristico, int layoutResource) {
        this.arrayListLugarTuristico = arrayListLugarTuristico;
        this.layoutResource = layoutResource;
    }

    @NonNull
    @Override
    public LugarTuristicoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutResource, viewGroup, false);
        return new LugarTuristicoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LugarTuristicoViewHolder lugarTuristicoViewHolder, int position) {
        LugarTuristico lugarTuristico = arrayListLugarTuristico.get(position);
        lugarTuristicoViewHolder.tvnombreC.setText(lugarTuristico.getNombre());
        lugarTuristicoViewHolder.tvdescripcionC.setText("Descripcion: " + lugarTuristico.getDescripcion());
        //modificar esto de imagenes
        lugarTuristicoViewHolder.imgCategoria.setImageResource(R.drawable.categoria_lagos);
    }

    @Override
    public int getItemCount() {
        return arrayListLugarTuristico.size();
    }

    public class LugarTuristicoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvnombreC, tvdescripcionC;
        ImageView imgCategoria;

        public LugarTuristicoViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            tvnombreC = itemView.findViewById(R.id.tvnombreC);
            tvdescripcionC = itemView.findViewById(R.id.tvdescripcionC);
            imgCategoria = itemView.findViewById(R.id.imgCategoria);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, PerfilLugarAdministradorActivity.class);
            mContext.startActivity(intent);
        }

    }

}
