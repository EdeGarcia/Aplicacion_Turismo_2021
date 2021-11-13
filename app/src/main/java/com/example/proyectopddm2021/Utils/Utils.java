package com.example.proyectopddm2021.Utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.EditText;

import java.util.regex.Pattern;

public class Utils {
    public static boolean r = true;
    public static boolean validateEditText(EditText editText){
        boolean res = true;
        if(editText.getText().toString().equalsIgnoreCase("")){
            res = false;
            editText.setError("Campo obligagorio");
        }
        return res;
    }
    public static Boolean validatePasswordLength(EditText editText){
        int n = editText.getText().toString().length();
        if(n == 6){
            r=false;
        }else{
            r=true;
        }

        if(r){
            editText.setError("Requieres de 6 caracteres");
        }
        return r;
    }
    public static Boolean validateTelefonoLength(EditText editText){
        int n = editText.getText().toString().length();
        if(n == 8){
            r=false;
        }else{
            r=true;
        }

        if(r){
            editText.setError("Requieres de 8 caracteres");
        }
        return r;
    }
    public static boolean validarCorreo(String correo){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(correo).matches();
    }
}
