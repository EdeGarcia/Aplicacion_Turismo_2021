package com.example.proyectopddm2021.Model;

import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class LoginAdministradorModel {

    public LoginAdministradorModel() {

    }

    public boolean validate(String user, String password){
        boolean res = false;

        if(user.equals("") && password.equals("123456")){
            res = true;
        }

        return res;
    }
}
