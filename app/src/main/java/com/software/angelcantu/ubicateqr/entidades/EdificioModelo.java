package com.software.angelcantu.ubicateqr.entidades;

import java.io.Serializable;

public class EdificioModelo implements Serializable {

    private String nombre;
    private String descripcion;
    private String ubicacion;
    private int imagen;
    private String descripcion_larga;
    private int imagenDetalle;

    public EdificioModelo(String nombre, String descripcion, String descripcion_larga, String ubicacion, int imagen, int imagenDetalle) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descripcion_larga = descripcion_larga;
        this.ubicacion = ubicacion;
        this.imagen = imagen;
        this.imagenDetalle = imagenDetalle;
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

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
