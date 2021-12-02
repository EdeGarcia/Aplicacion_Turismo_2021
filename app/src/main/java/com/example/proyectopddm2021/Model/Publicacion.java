package com.example.proyectopddm2021.Model;

import java.io.Serializable;

public class Publicacion implements Serializable {
     String id;
     String fecha;
     String imgUrl;
     String texto;
     String usuario;

    public Publicacion() {

    }

    public Publicacion(String id, String fecha, String imgUrl, String texto, String usuario) {
        this.id = id;
        this.fecha = fecha;
        this.imgUrl = imgUrl;
        this.texto = texto;
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
