package com.example.proyectopddm2021.Model;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


public class LugarTuristico implements Serializable {
    String id;
    String calificacion;
    String categoria;
    String nombre;
    String correo;
    String contrasenia;
    String ubicacion;
    String telefono;
    String descripcion;
    String servicio;
    String tipoUsuario;
    String imgUri;
    Context contextA;
    public LugarTuristico(String id,String nombre, String descripcion, String uri) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imgUri = uri;
    }
    public LugarTuristico(String id,String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public LugarTuristico() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Context getContextA() {
        return contextA;
    }

    public void setContextA(Context context) {
        this.contextA = context;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }
}
