package com.example.proyectopddm2021.Presenter;

import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.proyectopddm2021.Model.LugarTuristico;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class LugarTuristicoPresenter {
    private DatabaseReference databaseReference;
    FirebaseDatabase db =FirebaseDatabase.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    LugarTuristico lugarTuristicoData = new LugarTuristico();

    String _nombre, _Uid, _telefono, _ubicacion,_descripcion, _servicios, _correo;

    public LugarTuristicoPresenter() {

    }

    public Map<String, Object> MapeoUpdate(LugarTuristico lugarTuristico){
        Map<String, Object> lugarMap = new HashMap<>();
        lugarMap.put("nombre",lugarTuristico.getNombre());
        lugarMap.put("telefono", lugarTuristico.getTelefono());
        lugarMap.put("ubicacion", lugarTuristico.getUbicacion());
        lugarMap.put("descripcion", lugarTuristico.getDescripcion());
        lugarMap.put("servicio", lugarTuristico.getServicio());
        return lugarMap;
    }
    public void DatosPerfil(TextView txtNombre, TextView txtTelefono, TextView txtDescripcion, TextView txtUbicacion, TextView txtServicios){

        databaseReference = db.getReference("LugarTuristico").child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    LugarTuristico lugar = snapshot.getValue(LugarTuristico.class);
                    _nombre = lugar.getNombre();
                    _Uid = lugar.getId();
                    _telefono = lugar.getTelefono();
                    _descripcion = lugar.getDescripcion();
                    _ubicacion = lugar.getUbicacion();
                    _servicios = lugar.getServicio();

                    txtNombre.setText(_nombre);
                    txtDescripcion.setText(_descripcion);
                    txtTelefono.setText(_telefono);
                    txtUbicacion.setText(_ubicacion);
                    txtServicios.setText(_servicios);
                }else{
                    txtNombre.setText("-");
                    txtDescripcion.setText("-");
                    txtTelefono.setText("-");
                    txtUbicacion.setText("-");
                    txtServicios.setText("-");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });
    }

    public void DatosEditar(EditText edtNombre, EditText edtTelefono, EditText edtDescripcion, EditText edtUbicacion, EditText edtServicios){

        databaseReference = db.getReference("LugarTuristico").child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    LugarTuristico lugar = snapshot.getValue(LugarTuristico.class);
                    _nombre = lugar.getNombre();
                    _Uid = lugar.getId();
                    _telefono = lugar.getTelefono();
                    _descripcion = lugar.getDescripcion();
                    _ubicacion = lugar.getUbicacion();
                    _servicios = lugar.getServicio();

                    edtNombre.setText(_nombre);
                    edtDescripcion.setText(_descripcion);
                    edtTelefono.setText(_telefono);
                    edtUbicacion.setText(_ubicacion);
                    edtServicios.setText(_servicios);
                }else{
                    edtNombre.setText("-");
                    edtDescripcion.setText("-");
                    edtTelefono.setText("-");
                    edtUbicacion.setText("-");
                    edtServicios.setText("-");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });
    }


    public void DatosPrincipal(TextView txtNombre, TextView txtCorreo){

        databaseReference = db.getReference("LugarTuristico").child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    LugarTuristico lugar = snapshot.getValue(LugarTuristico.class);
                    _nombre = lugar.getNombre();
                    _Uid = lugar.getId();
                    _correo = lugar.getCorreo();

                    txtNombre.setText(_nombre);
                    txtCorreo.setText(_correo);

                }else{
                    txtNombre.setText("-");
                    txtCorreo.setText("-");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });
    }
}
