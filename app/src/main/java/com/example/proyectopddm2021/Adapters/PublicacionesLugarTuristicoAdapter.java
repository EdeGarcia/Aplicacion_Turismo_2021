package com.example.proyectopddm2021.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectopddm2021.Model.LugarTuristico;
import com.example.proyectopddm2021.Model.Publicacion;
import com.example.proyectopddm2021.R;

import java.util.ArrayList;

public class PublicacionesLugarTuristicoAdapter extends RecyclerView.Adapter<PublicacionesLugarTuristicoAdapter.PublicacionesViewHolder>{
    private Context mContext;
    private int layoutResource;
    private ArrayList<Publicacion> arrayListPublicacion;

    public PublicacionesLugarTuristicoAdapter(ArrayList<Publicacion> arrayListPublicacion, int layoutResource, Activity mContext) {
        this.mContext = mContext;
        this.layoutResource = layoutResource;
        this.arrayListPublicacion = arrayListPublicacion;
    }

    @Override
    public PublicacionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false);
        PublicacionesLugarTuristicoAdapter.PublicacionesViewHolder viewh=new PublicacionesViewHolder(view,mContext);
        return viewh;
    }

    @Override
    public void onBindViewHolder(@NonNull PublicacionesLugarTuristicoAdapter.PublicacionesViewHolder holder, int position) {
        Publicacion publicacion = arrayListPublicacion.get(position);
        holder.tvDescripcion.setText(publicacion.getTexto());
        holder.IdPublicacion = publicacion.getId();
        holder.tvNombre.setText(publicacion.getUsuario());
        holder.UrlImagen  = publicacion.getImgUrl();
        Glide.with(mContext).load(holder.UrlImagen).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return arrayListPublicacion.size();
    }


    public class PublicacionesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        String IdPublicacion, UrlImagen;
        TextView tvNombre, tvDescripcion;
        ImageView img;
        Button btnMeGusta, btnFavoritos;
        Context context;

        public PublicacionesViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombrePostList);
            tvDescripcion = (TextView) itemView.findViewById(R.id.tvDescripcionPostList);
            img = (ImageView) itemView.findViewById(R.id.imgPostList);
            this.context = context;
            //IdPublicacion = context.IdPublicacion;
            //UrlImagen = context.UriImagen;
        }

        @Override
        public void onClick(View v) {

        }
    }
}
