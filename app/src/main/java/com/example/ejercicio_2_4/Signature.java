package com.example.ejercicio_2_4;


import android.graphics.Bitmap;

import java.io.BufferedInputStream;

public class Signature {
    private int id;
    private String descripcion;
    private Bitmap image;
    private BufferedInputStream path;

    public Signature(String descripcion, Bitmap image, int id) {
        this.descripcion = descripcion;
        this.image = image;
        this.id = id;
    }

    public Signature(String nameOfImage, Bitmap objectBitmap) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getdescripcion() {
        return descripcion;
    }

    public void setdescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }


}
