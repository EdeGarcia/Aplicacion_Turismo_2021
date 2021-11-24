package com.example.proyectopddm2021.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectopddm2021.Model.LugarTuristico;
import com.example.proyectopddm2021.R;

import java.io.FileDescriptor;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PublicacionesLugarAdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PublicacionesLugarAdminFragment extends Fragment {
    LinearLayout linearLayout;
    ImageView imgSubir, imgCargada;
    Uri selectedImage;
    Bitmap img;
    TextView txtMensaje;
    Context context;
    LugarTuristico lugar = new LugarTuristico();;
    final int READ_EXTERNAL_STORAGE_PERMISSION_CODE =23;
    final int WRITE_EXTERNAL_STORAGE_PERMISSION_CODE =23;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PublicacionesLugarAdminFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PublicacionesLugarAdminFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PublicacionesLugarAdminFragment newInstance(String param1, String param2) {
        PublicacionesLugarAdminFragment fragment = new PublicacionesLugarAdminFragment();
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
    ActivityResultLauncher<Intent> activityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent data = result.getData();
                    if( result.getResultCode() == Activity.RESULT_OK) {
                        selectedImage = data.getData();
                        Bitmap bmp = null;
                        try{
                            bmp = getBitMapFromURI(selectedImage);
                            if ( bmp != null ) {
                                img = bmp;
                                imgCargada.setImageBitmap(bmp);
                            }

                        } catch (IOException ioException) {
                            txtMensaje.setText("No se pudo cargar la imagen");
                            ioException.printStackTrace();
                        }
                    }
                }
            });
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_publicaciones_lugar_admin, container, false);
        linearLayout = (LinearLayout) v.findViewById(R.id.layoutContImagenesPost);
        imgSubir = v.findViewById(R.id.imgSubir);
        imgCargada = (ImageView) v.findViewById(R.id.imgCargadaPost);
        txtMensaje = (TextView) v.findViewById(R.id.tvMensaje);

        linearLayout.setVisibility(View.GONE);

        img = ((BitmapDrawable) imgSubir.getDrawable()).getBitmap();

         imgSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFile(getActivity());
                linearLayout.setVisibility(View.VISIBLE);

            }
        });
        return v;
    }

    public void openFile(Activity activity) {
        context = activity;
        //Toast.makeText(this, "Menú abrir", Toast.LENGTH_SHORT).show();
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_PERMISSION_CODE);
            }
        }
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        lugar.setContextA(context);
        activityResult.launch(intent);

    }
    private Bitmap getBitMapFromURI(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor = lugar.getContextA().getContentResolver().openFileDescriptor(uri,"r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_EXTERNAL_STORAGE_PERMISSION_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        }
    }
}