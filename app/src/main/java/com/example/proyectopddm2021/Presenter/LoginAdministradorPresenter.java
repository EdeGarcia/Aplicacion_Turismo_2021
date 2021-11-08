package com.example.proyectopddm2021.Presenter;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectopddm2021.Model.LoginAdministradorModel;

public class LoginAdministradorPresenter {
    public LoginAdministradorPresenter() {
    }

    LoginAdministradorModel model = new LoginAdministradorModel();

    public boolean validation(EditText edtUser, EditText edtPassword, Context context){
        boolean res = false;
        if(edtUser.getText().toString().equals("") && edtPassword.getText().toString().equals("")){
            Toast.makeText(context, "Campos obligatorios", Toast.LENGTH_SHORT).show();
            res = false;
        }else{
            if(model.validate(edtUser.getText().toString(), edtPassword.getText().toString())){
                res = true;
            }
        }
        return res;
    }
}
