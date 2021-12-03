package com.example.examenrecuperacion;

import java.io.Serializable;

public class getter implements Serializable {


    private String url;
    private String nombre;
    private  String telefono;
    private  String longitud;
    private  String latitud;
    private  String key;

    public getter(String nombre, String telefono, String longitud, String latitud,String url, String key) {
        this.url = url;
        this.nombre = nombre;
        this.telefono = telefono;
        this.longitud = longitud;
        this.latitud = latitud;
        this.key = key;
    }



    public getter(){}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
