package com.example.proyectopddm2021.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectopddm2021.DAO.PublicacionDAO;
import com.example.proyectopddm2021.Model.LugarTuristico;
import com.example.proyectopddm2021.Model.Publicacion;
import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.View.EditarPublicacionesAdministradorActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class PublicacionesLugarTuristicoAdapter extends RecyclerView.Adapter<PublicacionesLugarTuristicoAdapter.PublicacionesViewHolder>{
    private Context mContext;
    private int layoutResource;
    private ArrayList<Publicacion> arrayListPublicacion;
    private int tipo;
    //
    private PublicacionDAO dao = new PublicacionDAO();

//    private ;

    public PublicacionesLugarTuristicoAdapter(ArrayList<Publicacion> arrayListPublicacion, int layoutResource, Activity mContext,int tipo) {
        this.mContext = mContext;
        this.layoutResource = layoutResource;
        this.arrayListPublicacion = arrayListPublicacion;
        this.tipo = tipo;

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

        if(tipo==0){
            holder.tvDescripcion.setText(publicacion.getTexto());
            holder.IdPublicacion = publicacion.getId();
            holder.tvNombre.setText(publicacion.getUsuario());
            holder.UrlImagen  = publicacion.getImgUrl();
            Glide.with(mContext).load(holder.UrlImagen).into(holder.imgLugar);
            holder.deletePublication.setOnClickListener(v ->{
                new AlertDialog.Builder(mContext)
                        .setTitle("Eliminar")
                        .setMessage("??Est?? seguro que desea eliminar la publicaci??n?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //OK
                                StorageReference photoRef = FirebaseStorage.getInstance().getReferenceFromUrl(publicacion.getImgUrl());

                                photoRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // Eliminar de la base de datos y storage
                                        dao.deletePublication(publicacion.getId());
                                        //eliminar elemento del recycler view
                                        arrayListPublicacion.remove(position);
                                        notifyItemRemoved(position);

                                        Toast.makeText(mContext, "??Eliminado con ??xito!",Toast.LENGTH_LONG).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        // Uh-oh, an error occurred!
                                        Toast.makeText(mContext, "??Ocurri?? un error!",Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        })

                        // CANCEL
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            });
            holder.editPublication.setOnClickListener( w -> {
                Intent intent = new Intent(mContext, EditarPublicacionesAdministradorActivity.class);

                intent.putExtra("publicacion", publicacion);

                w.getContext().startActivity(intent);
            });
        }else if(tipo==1){
            holder.tvDescripcionT.setText(publicacion.getTexto());
            holder.IdPublicacionT = publicacion.getId();
//            holder.tvNombreT.setText(publicacion.getUsuario());
            holder.UrlImagenT  = publicacion.getImgUrl();
            Glide.with(mContext).load(holder.UrlImagenT).into(holder.imgTurista);
        }

    }

    @Override
    public int getItemCount() {
        return arrayListPublicacion.size();
    }


    public class PublicacionesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        String IdPublicacion, IdPublicacionT,UrlImagenT, UrlImagen;
        TextView tvNombre,tvNombreT, tvDescripcion, tvDescripcionT;
        ImageView imgLugar, imgTurista;
        Button btnMeGusta, btnFavoritos;
        Context context;

        //
        ImageView editPublication, deletePublication;

        public PublicacionesViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            //botones Lugar turistico
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombrePostList);
            tvDescripcion = (TextView) itemView.findViewById(R.id.tvDescripcionPostList);
            imgLugar = (ImageView) itemView.findViewById(R.id.imgPostList);

            //botones Turista
            tvDescripcionT = (TextView) itemView.findViewById(R.id.tvDescripcionPostListT);
            imgTurista = (ImageView) itemView.findViewById(R.id.imgPostListT);

            //
            editPublication = (ImageView) itemView.findViewById(R.id.editPublication);
            deletePublication = (ImageView) itemView.findViewById(R.id.deletePublication);

            this.context = context;
        }

        @Override
        public void onClick(View v) {

        }
    }
}
