package com.example.ejercicio_2_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ejercicio_2_4.config.SQLiteConexion;
import com.example.ejercicio_2_4.config.Transacciones;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;


public class MainActivity extends AppCompatActivity {

    private CaptureBitmapView mSig;
    EditText Descripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Descripcion = ( EditText) findViewById(R.id.editTextDescripcion);
        Button salvar = (Button) findViewById(R.id.btnSalvar);
        Button ShowImagen = (Button) findViewById(R.id.moveToShowActivity);

        LinearLayout mContent = (LinearLayout) findViewById(R.id.signLayout);
        mSig = new CaptureBitmapView(this, null);
        mContent.addView(mSig, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);


        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { salvar();}
        });


        ShowImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowImagesActivity.class);
                startActivity(intent);

            }
        });
    }


   private void salvar() {

        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        Bitmap signature = mSig.getBitmap();
        ContentValues valores = new ContentValues();
        valores.put(Transacciones.Descripcion, Descripcion.getText().toString());
        valores.put(Transacciones.ImgFirma, signature.toString());

        if (Descripcion.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Favor no dejar campos vacios", Toast.LENGTH_LONG).show();
        } else {

            Long Registro = db.insert(Transacciones.TablaSignature, Transacciones.id, valores);
            Toast.makeText(getApplicationContext(), "Registro INGRESADO : Codigo :" + Registro.toString(), Toast.LENGTH_LONG).show();

            db.close();
        }
        LimpiarPantalla();
    }

    private void LimpiarPantalla()
    {
    Descripcion.setText("");
    mSig.ClearCanvas();

    }

}
