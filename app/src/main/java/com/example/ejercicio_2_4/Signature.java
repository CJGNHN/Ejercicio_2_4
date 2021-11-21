package com.example.ejercicio_2_4;


import android.graphics.Bitmap;

import java.io.BufferedInputStream;

public class Signature {
    private String descripcion;
    private Bitmap image;

    public Signature (String descripcion, Bitmap image) {
        this.descripcion = descripcion;
        this.image = image;

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
