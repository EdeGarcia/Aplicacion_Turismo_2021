package com.example.proyectopddm2021.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectopddm2021.Presenter.LugarTuristicoPresenter;
import com.example.proyectopddm2021.Presenter.PublicacionPresenter;
import com.example.proyectopddm2021.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PublicacionesLugarTuristaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PublicacionesLugarTuristaFragment extends Fragment {
    TextView tvNombrePostT, tvDescripcionPostT, tvNomTurista;
    ImageView imvPerfilPostT, imvImagenPostT;
    RecyclerView recyclerView;
    Activity activity;
    CardView btnLikeT;

    PublicacionPresenter presenter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PublicacionesLugarTuristaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PublicacionesLugarTuristaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PublicacionesLugarTuristaFragment newInstance(String param1, String param2) {
        PublicacionesLugarTuristaFragment fragment = new PublicacionesLugarTuristaFragment();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_publicaciones_lugar_turista, container, false);
        tvNombrePostT = (TextView) v.findViewById(R.id.tvNombrePostListT);
        tvNomTurista = (TextView) v.findViewById(R.id.tvNombreTuristaList);
        tvDescripcionPostT = (TextView) v.findViewById(R.id.tvDescripcionPostListT);
        imvImagenPostT = (ImageView) v.findViewById(R.id.imgPostListT);
        imvPerfilPostT = (ImageView) v.findViewById(R.id.imgLugarPostListT);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerPublicacionesT);
        activity = getActivity();

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        presenter = new PublicacionPresenter(activity);
        presenter.getDatosFromFirebaseT(recyclerView);

        return v;
    }
}