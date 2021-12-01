package com.example.proyectopddm2021.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
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
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectopddm2021.DAO.LugarTuristicoDAO;
import com.example.proyectopddm2021.DAO.PublicacionDAO;
import com.example.proyectopddm2021.Model.LugarTuristico;
import com.example.proyectopddm2021.Model.Publicacion;
import com.example.proyectopddm2021.Model.Turista;
import com.example.proyectopddm2021.Presenter.PublicacionPresenter;
import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.View.PrincipalAdministradorActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileDescriptor;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PublicacionesLugarAdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class PublicacionesLugarAdminFragment extends Fragment {
    LinearLayout linearLayout;
    ImageView imgSubir, imgCargada;
    public RecyclerView RecyclerViewPost;
    boolean resultado = false;
//    Botones
    Button btnPublicar, btnCancelar;
    TextView txtMensaje;
    ProgressBar pbImage;
    Context context;
    EditText edtTextoPublicacion;
    LugarTuristicoDAO lugar = new LugarTuristicoDAO();
    PublicacionPresenter presenter;


    //Referencia para subir imagen a Storage
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    //
    private Uri imageUri;
    Activity activity;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    Context applicationContext = PrincipalAdministradorActivity.getContextOfApplication();


    final int READ_EXTERNAL_STORAGE_PERMISSION_CODE =23;
    final int WRITE_EXTERNAL_STORAGE_PERMISSION_CODE =23;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int requestCode;
    private int resultCode;
    @Nullable
    private Intent data;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_publicaciones_lugar_admin, container, false);
        linearLayout = (LinearLayout) v.findViewById(R.id.layoutContImagenesPost);
        imgCargada = (ImageView) v.findViewById(R.id.imgCargadaPost);
        imgSubir = (ImageView) v.findViewById(R.id.imgSubir);
        RecyclerViewPost = (RecyclerView) v.findViewById(R.id.recyclerPublicaciones);
        pbImage = (ProgressBar) v.findViewById(R.id.pbImage);
        edtTextoPublicacion = (EditText) v.findViewById(R.id.edtDescripcionPublicacion);
        activity = getActivity();

//        Botones
        btnPublicar = (Button) v.findViewById(R.id.btnPublicar);
        btnCancelar = (Button) v.findViewById(R.id.btnCancelar);

        linearLayout.setVisibility(View.GONE);
//        Hide the progressbar
        pbImage.setVisibility(View.INVISIBLE);
        RecyclerViewPost.setLayoutManager(new LinearLayoutManager(activity));

        presenter = new PublicacionPresenter(activity);
        presenter.getDatosFromFirebase(RecyclerViewPost);

        imgSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 2);
            }
        });

        btnPublicar.setOnClickListener( s -> {
            if(imageUri != null){
                if(!uploadToFirebase(imageUri)){
                    reset();
                }

            }else{
                Toast.makeText(activity, "¡Seleccione una imagen!", Toast.LENGTH_LONG).show();
            }
        });

        btnCancelar.setOnClickListener(su->{
            reset();
        });

        return v;
    }

    private Boolean uploadToFirebase(Uri uri) {
        StorageReference fileRef = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
//        Mandando la imagen a firebase
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
//                        Haciendo el proceso de crear el objeto para enviar a Realtime database
                        Publicacion p = new Publicacion();
                        PublicacionDAO dao = new PublicacionDAO();

                        p.setTexto(edtTextoPublicacion.getText().toString());
                        p.setFecha(dtf.format(now));
                        p.setUsuario(lugar.IdCurrentUser());
                        p.setImgUrl(uri.toString());

                        dao.uploadPublication(p);
                        pbImage.setVisibility(View.INVISIBLE);
                        Toast.makeText(activity, "¡La publicación se realizo con éxito!", Toast.LENGTH_LONG).show();
                        resultado = true;
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull  UploadTask.TaskSnapshot snapshot) {
                pbImage.setVisibility(View.VISIBLE);
                pbImage.setProgress(100);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pbImage.setVisibility(View.INVISIBLE);
                Toast.makeText(activity, "¡Error al subir la imagen!", Toast.LENGTH_LONG).show();
                resultado = false;
            }
        });
        return resultado;
    }

//
    private String getFileExtension(Uri uri) {
        ContentResolver cr = applicationContext.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && resultCode == RESULT_OK && data != null ){
            imageUri = data.getData();
//            imgSubir.setImageURI(imageUri);
            imgCargada.setImageURI(imageUri);
            linearLayout.setVisibility(View.VISIBLE);
        }
    }

    private void reset(){
        imageUri = null;
        imgCargada.setImageURI(imageUri);
        edtTextoPublicacion.setText("");
        edtTextoPublicacion.setHint("Escriba algo...");
        edtTextoPublicacion.clearFocus();
        linearLayout.setVisibility(View.GONE);
    }
}