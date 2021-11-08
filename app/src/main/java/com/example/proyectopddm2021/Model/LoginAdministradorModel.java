package com.example.proyectopddm2021.Model;

public class LoginAdministradorModel {

    public LoginAdministradorModel() {
    }

    public boolean validate(String user, String password){
        boolean res = false;

        if(user.equals("A") && password.equals("A")){
            res = true;
        }

        return res;
    }
}
