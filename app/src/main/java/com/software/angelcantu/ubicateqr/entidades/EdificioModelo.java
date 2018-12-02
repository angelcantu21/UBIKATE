package com.software.angelcantu.ubicateqr.entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.Serializable;

public class EdificioModelo implements Serializable {

    private String nombre;
    private String descripcion;
    private String ubicacion;
    private Bitmap imagen;
    private String dato;
    private String descripcion_larga;
    private int imagenDetalle;

    public EdificioModelo(String nombre, String descripcion, String descripcion_larga, String ubicacion, Bitmap imagen, int imagenDetalle) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descripcion_larga = descripcion_larga;
        this.ubicacion = ubicacion;
        this.imagen = imagen;
        this.imagenDetalle = imagenDetalle;
    }
    public EdificioModelo(){

    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;

        try {
            byte[] byteCode= Base64.decode(dato, Base64.DEFAULT);
            this.imagen= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion_larga() {
        return descripcion_larga;
    }

    public void setDescripcion_larga(String descripcion_larga) {
        this.descripcion_larga = descripcion_larga;
    }

    public int getImagenDetalle() {
        return imagenDetalle;
    }

    public void setImagenDetalle(int imagenDetalle) {
        this.imagenDetalle = imagenDetalle;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
