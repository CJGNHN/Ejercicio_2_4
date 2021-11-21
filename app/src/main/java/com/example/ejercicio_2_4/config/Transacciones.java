package com.example.ejercicio_2_4.config;

import android.os.Build;

public class Transacciones
{

    public static final String NameDatabase="DBSignature";
    public static final int versionDatabase=1;
    public static final String TablaSignature="Signature";

    public static final String id="id";
    public static final String Descripcion="Descripcion";
    public static final String ImgFirma="ImgFirma";


    public static final String CreateTableSignature ="CREATE TABLE Signature (id INTEGER PRIMARY KEY AUTOINCREMENT,"+"Descripcion TEXT,"+"ImgFirma Bitmap);";


    public static final String DROPTableSignature ="DROP TABLE IF EXISTS Signature;";

}