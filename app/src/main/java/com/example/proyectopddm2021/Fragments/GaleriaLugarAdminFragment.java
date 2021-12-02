package com.example.proyectopddm2021.Fragments;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.proyectopddm2021.DAO.GaleriaDAO;
import com.example.proyectopddm2021.Presenter.GaleriaPresenter;
import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.View.PrincipalAdministradorActivity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GaleriaLugarAdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class GaleriaLugarAdminFragment extends Fragment {

    //variables
    LinearLayout lySubir;
    ImageView imgCargada;
    ProgressBar pbImage;
    Button btnPublicar, btnCancelar;
    GridView gridGaleria;

    private Uri imageUri;
    private GaleriaPresenter presenter;
    Activity activity;
    Context applicationContext = PrincipalAdministradorActivity.getContextOfApplication();
    String extension;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GaleriaLugarAdminFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GaleriaLugarAdminFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GaleriaLugarAdminFragment newInstance(String param1, String param2) {
        GaleriaLugarAdminFragment fragment = new GaleriaLugarAdminFragment();
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
        View v =  inflater.inflate(R.layout.fragment_galeria_lugar_admin, container, false);

        imgCargada = (ImageView) v.findViewById(R.id.imgCargadaGaleria);
        lySubir = (LinearLayout) v.findViewById(R.id.layoutSubirImgGaleria);
        pbImage = (ProgressBar) v.findViewById(R.id.pbImageGaleria);
        btnPublicar = (Button) v.findViewById(R.id.btnSubirGaleria);
        btnCancelar = (Button) v.findViewById(R.id.btnCancelarGaleria);

        gridGaleria = (GridView) v.findViewById(R.id.GVGaleriaTurista);
        activity = getActivity();


        presenter = new GaleriaPresenter(getActivity());
        presenter.getDatosFromFirebase(gridGaleria);

        lySubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 2);

            }
        });

        btnPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(imageUri != null){
                    extension = getFileExtension(imageUri);
                    presenter.uploadToFirebase(imageUri, extension,pbImage);
                    if(!imageUri.toString().isEmpty()){
                        imageUri = null;
                        imgCargada.setImageURI(imageUri);
                    }

                }else{
                    Toast.makeText(activity, "¡Seleccione una imagen!", Toast.LENGTH_LONG).show();
                }

            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageUri = null;
                imgCargada.setImageURI(imageUri);
            }
        });


        return v;
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cr = applicationContext.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && resultCode == RESULT_OK && data != null ){
            imageUri = data.getData();
//            imgSubir.setImageURI(imageUri);
            imgCargada.setImageURI(imageUri);
        }
    }
}