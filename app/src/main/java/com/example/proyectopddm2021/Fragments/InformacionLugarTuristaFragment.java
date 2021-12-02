package com.example.proyectopddm2021.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectopddm2021.Model.Calificacion;
import com.example.proyectopddm2021.Presenter.LugarTuristicoPresenter;
import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.View.EditarLugarAdministradorActivity;
import com.example.proyectopddm2021.View.PerfilLugarTuristaActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InformacionLugarTuristaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformacionLugarTuristaFragment extends Fragment {
    TextView txtNombreLugarT, txtDescripcion, txtTelefono, txtUbicacion, txtServicios, txtCalificacion, txtCalificacionLT, txtnVotantes;
    Button btnEditar,  btnGuardarCal, btnCancelarCal;
    RatingBar ratingBar;
    LinearLayout LinearLayoutCalificacion;
    private DatabaseReference databaseReference, dbrCalif, dbrLugT;
    private Calificacion calificacion= new Calificacion();
    float TotalCalfLugT, TotalEstrellas=0;
    private int cantVotantC;
    String idLugarTuristicoA, idTuristaA;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InformacionLugarTuristaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InformacionLugarTuristaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InformacionLugarTuristaFragment newInstance(String param1, String param2) {
        InformacionLugarTuristaFragment fragment = new InformacionLugarTuristaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_informacion_lugar_turista, container, false);
        txtNombreLugarT = v.findViewById(R.id.txtLugar);
        txtDescripcion = v.findViewById(R.id.txtDescripcion);
        txtServicios = v.findViewById(R.id.txtServicios);
        txtTelefono = v.findViewById(R.id.txtTelefono);
        txtUbicacion = v.findViewById(R.id.txtUbicacion);
        txtCalificacionLT = v.findViewById(R.id.txtCalificacionLT);
        txtnVotantes = v.findViewById(R.id.txtnVotantes);
        txtCalificacion = v.findViewById(R.id.txtCalificacion);
        ratingBar = v.findViewById(R.id.ratingBar);
        btnGuardarCal = (Button) v.findViewById(R.id.btnGuardarCalificacion);
        btnCancelarCal = (Button) v.findViewById(R.id.btnCancelarCalificacion);
        LinearLayoutCalificacion = v.findViewById(R.id.LayoutCalificacion);
        idLugarTuristicoA = PerfilLugarTuristaActivity.idLugarTuristicoA;
        idTuristaA = PerfilLugarTuristaActivity.idTuristaA;
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Calificacion .class.getSimpleName());
        dbrCalif = FirebaseDatabase.getInstance().getReference().child("Calificacion");
        dbrLugT = FirebaseDatabase.getInstance().getReference().child("LugarTuristico");

        dbrCalif.orderByChild("idLugarTuristico").equalTo(idLugarTuristicoA).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    cantVotantC = (int) datasnapshot.getChildrenCount();
                    txtnVotantes.setText(Integer.toString(cantVotantC));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        Query query = FirebaseDatabase.getInstance().getReference("Calificacion").orderByChild("idTurista").equalTo(idTuristaA);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for(DataSnapshot ds: datasnapshot.getChildren()){
                        String idLugarTuristico = ds.child("idLugarTuristico").getValue().toString();
                        String idTurista = ds.child("idTurista").getValue().toString();
                        String CantEstrella = ds.child("cantidadEstrella").getValue().toString();
                        if((idLugarTuristico.equals(idLugarTuristicoA)) && (idTurista.equals(idTuristaA))){
                            txtCalificacion.setText("Su calificacion fue de "+CantEstrella+" estrellas");
                            ratingBar.setVisibility(getView().GONE);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                LinearLayoutCalificacion.setVisibility(getView().getVisibility());
            }
        });

        btnCancelarCal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ratingBar.setRating(0);
                LinearLayoutCalificacion.setVisibility(getView().GONE);
            }
        });

        btnGuardarCal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                float cantEstrellaRB = ratingBar.getRating();
                Toast.makeText(getActivity().getApplicationContext(), "Usted ha votado: "+cantEstrellaRB+ " estrellas", Toast.LENGTH_LONG).show();
                ratingBar.setVisibility(getView().GONE);
                LinearLayoutCalificacion.setVisibility(getView().GONE);
                String cantidadEstrella = Float.toString(cantEstrellaRB);
                calificacion.setIdTurista(idTuristaA);
                calificacion.setIdLugarTuristico(idLugarTuristicoA);
                calificacion.setCantidadEstrella(cantidadEstrella);
                add(calificacion);

                dbrCalif.orderByChild("idLugarTuristico").startAt(idLugarTuristicoA).endAt(idLugarTuristicoA).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                        for(DataSnapshot ds: datasnapshot.getChildren()){
                            String countEstrella = ds.child("cantidadEstrella").getValue().toString();
                            float convCountEstr = Float.parseFloat(countEstrella);
                            TotalEstrellas = TotalEstrellas+convCountEstr;
                        }
                        float nVotantes  = (float) cantVotantC;
                        TotalCalfLugT = TotalEstrellas / nVotantes;
                        DecimalFormat decimalFormat = new DecimalFormat("#.0");
                        String res = decimalFormat.format(TotalCalfLugT);
                        dbrLugT.child(idLugarTuristicoA).child("calificacion").setValue(res);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

            }
        });

        LugarTuristicoPresenter presenter = new LugarTuristicoPresenter();
        presenter.DatosPerfil(txtNombreLugarT, txtTelefono, txtDescripcion, txtUbicacion, txtServicios, txtCalificacionLT);

       /* btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditarLugarAdministradorActivity.class);
                startActivity(intent);
            }
        });*/
        return  v;
    }

    public Task<Void> add(Calificacion calificacion)
    {
        return databaseReference.push().setValue(calificacion);
    }

}