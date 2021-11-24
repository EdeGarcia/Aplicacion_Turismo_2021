package com.example.proyectopddm2021.Adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectopddm2021.Model.LugarTuristico;
import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.View.CategoriaTuristaActivity;
import com.example.proyectopddm2021.View.LoginUsuariosActivity;
import com.example.proyectopddm2021.View.PerfilLugarAdministradorActivity;
import com.example.proyectopddm2021.View.PerfilLugarTuristaActivity;
import com.example.proyectopddm2021.View.RegistrarTuristaActivity;

import java.util.ArrayList;

public class LugarTuristicoAdapter extends RecyclerView.Adapter<LugarTuristicoAdapter.LugarTuristicoViewHolder> {

    private Context mContext;
    private int layoutResource;
    private String id;
    private ArrayList<LugarTuristico> arrayListLugarTuristico;

    public LugarTuristicoAdapter(ArrayList<LugarTuristico> arrayListLugarTuristico, int layoutResource, Activity context) {
        this.arrayListLugarTuristico = arrayListLugarTuristico;
        this.layoutResource = layoutResource;
        this.mContext = context;
    }

    @NonNull
    @Override
    public LugarTuristicoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutResource, viewGroup, false);
        LugarTuristicoViewHolder viewh=new LugarTuristicoViewHolder(view,mContext);
        return viewh;

    }

    @Override
    public void onBindViewHolder(@NonNull LugarTuristicoViewHolder lugarTuristicoViewHolder, int position) {
        LugarTuristico lugarTuristico = arrayListLugarTuristico.get(position);
        id = lugarTuristico.getId();
        lugarTuristicoViewHolder.tvId.setText(id);
        lugarTuristicoViewHolder.tvnombreC.setText(lugarTuristico.getNombre());
        lugarTuristicoViewHolder.tvdescripcionC.setText("Descripcion: " + lugarTuristico.getDescripcion());
        lugarTuristicoViewHolder.imgCategoria.setImageResource(R.drawable.categoria_lagos);
        //modificar esto de imagenes

    }

    @Override
    public int getItemCount() {
        return arrayListLugarTuristico.size();
    }

    public class LugarTuristicoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvnombreC, tvdescripcionC, tvId;
        ImageView imgCategoria;
        Context context;

        public LugarTuristicoViewHolder(@NonNull View itemView, Context context) {
            super(itemView);

            itemView.setOnClickListener(this);
            tvnombreC = itemView.findViewById(R.id.tvnombreC);
            tvdescripcionC = itemView.findViewById(R.id.tvdescripcionC);
            imgCategoria = itemView.findViewById(R.id.imgCategoria);
            tvId = itemView.findViewById(R.id.tvIdC);
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, PerfilLugarTuristaActivity.class);
            intent.putExtra("idLugar",id);
            context.startActivity(intent);
        }

    }

}
