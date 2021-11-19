package com.example.proyectopddm2021.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyectopddm2021.Presenter.LugarTuristicoPresenter;
import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.View.EditarLugarAdministradorActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InformacionLugarAdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformacionLugarAdminFragment extends Fragment {
    TextView txtNombreLugarT, txtDescripcion, txtTelefono, txtUbicacion, txtServicios;
    Button btnEditar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InformacionLugarAdminFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InformacionLugarAdminFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InformacionLugarAdminFragment newInstance(String param1, String param2) {
        InformacionLugarAdminFragment fragment = new InformacionLugarAdminFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View f = inflater.inflate(R.layout.fragment_informacion_lugar_admin, container, false);
        txtNombreLugarT = f.findViewById(R.id.txtLugar);
        txtDescripcion = f.findViewById(R.id.txtDescripcion);
        txtServicios = f.findViewById(R.id.txtServicios);
        txtTelefono = f.findViewById(R.id.txtTelefono);
        txtUbicacion = f.findViewById(R.id.txtUbicacion);
        btnEditar = f.findViewById(R.id.btnEditar);

        LugarTuristicoPresenter presenter = new LugarTuristicoPresenter();
        presenter.DatosPerfil(txtNombreLugarT, txtTelefono, txtDescripcion, txtUbicacion, txtServicios);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditarLugarAdministradorActivity.class);
                startActivity(intent);
            }
        });
        return  f;
    }
}