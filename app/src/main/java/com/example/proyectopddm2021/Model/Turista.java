package com.example.proyectopddm2021.Model;

import java.io.Serializable;
import java.util.HashMap;

public class Turista implements Serializable {
    String id;
    String nombres;
    String apellidos;
    String telefono;
    String correo;
    String contrasenia;
    //String rol;
    //HashMap <String, String> lugaresFavoritos;

    public Turista() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /*public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public HashMap<String, String> getLugaresFavoritos() {
        return lugaresFavoritos;
    }

    public void setLugaresFavoritos(HashMap<String, String> lugaresFavoritos) {
        this.lugaresFavoritos = lugaresFavoritos;
    }*/
}
