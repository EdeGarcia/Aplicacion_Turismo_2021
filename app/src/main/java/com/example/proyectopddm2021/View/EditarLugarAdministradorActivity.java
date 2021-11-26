package com.example.proyectopddm2021.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyectopddm2021.DAO.LugarTuristicoDAO;
import com.example.proyectopddm2021.Model.LugarTuristico;
import com.example.proyectopddm2021.Presenter.LugarTuristicoPresenter;
import com.example.proyectopddm2021.R;
import com.example.proyectopddm2021.Utils.Utils;

import java.util.Map;

public class EditarLugarAdministradorActivity extends AppCompatActivity {

    Button btnGuardar;
    EditText edtNombre, edtUbicacion, edtDescripcion, edtTelefono, edtServicios;
    private Spinner spinner;
    private LugarTuristico lugar = new LugarTuristico();
    private LugarTuristicoDAO dao = new LugarTuristicoDAO();
    private LugarTuristicoPresenter presenter ;
    Map<String, Object> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_lugar_administrador);

        edtNombre = findViewById(R.id.edtNombreEdit);
        edtDescripcion = findViewById(R.id.edtDescripcionEdit);
        edtServicios = findViewById(R.id.edtServiciosEdit);
        edtTelefono = findViewById(R.id.edtTelefonoEdit);
        edtUbicacion = findViewById(R.id.edtUbicacionEdit);
        btnGuardar = findViewById(R.id.btnGuardarLugar);

        presenter = new LugarTuristicoPresenter(this);
        presenter.MostrarDatos(edtNombre, edtTelefono, edtDescripcion,edtUbicacion, edtServicios);


        map = presenter.MapeoUpdate(lugar);
        btnGuardar.setOnClickListener(v->{
            presenter.Editar(Data(getApplicationContext()), edtNombre, edtDescripcion, edtTelefono, edtServicios, edtUbicacion);
        });
    }

    public Map<String, Object> Data(Context context){
        lugar.setNombre(edtNombre.getText().toString());
        lugar.setDescripcion(edtDescripcion.getText().toString());
        lugar.setServicio(edtServicios.getText().toString());
        lugar.setTelefono(edtTelefono.getText().toString());
        lugar.setUbicacion(edtUbicacion.getText().toString());
        Map<String, Object> map = presenter.MapeoUpdate(lugar);
        return map;
    }

}