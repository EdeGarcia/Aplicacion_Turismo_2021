package com.example.proyectopddm2021.Model;

public class Galeria {
    String id;
    String imgUrl;
    String fecha;
    String idLugar;

    public Galeria() {
    }

    public Galeria(String id, String imgUrl, String fecha, String idLugar) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.fecha = fecha;
        this.idLugar = idLugar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(String idLugar) {
        this.idLugar = idLugar;
    }
}

