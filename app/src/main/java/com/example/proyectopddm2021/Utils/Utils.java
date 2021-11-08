package com.example.proyectopddm2021.Utils;

import android.widget.EditText;

public class Utils {
    public static boolean validateEditText(EditText editText){
        boolean res = true;
        if(editText.getText().toString().equalsIgnoreCase("")){
            res = false;
            editText.setError("Campo obligagorio");
        }
        return res;
    }
}
