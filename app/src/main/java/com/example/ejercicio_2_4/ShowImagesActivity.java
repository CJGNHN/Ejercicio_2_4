package com.example.ejercicio_2_4;

import android.accounts.Account;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejercicio_2_4.config.RVAdapter;
import com.example.ejercicio_2_4.config.SQLiteConexion;
import com.example.ejercicio_2_4.config.Transacciones;

public class ShowImagesActivity extends AppCompatActivity {
    private SQLiteConexion objectDatabaseHandler;
    private RecyclerView objectRecyclerView;

    private RVAdapter objectRvAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_images);
        try {
            objectRecyclerView=findViewById(R.id.imagesRV);
            objectDatabaseHandler=new SQLiteConexion(this);

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void getData(View view){
        try {
            objectRvAdapter=new RVAdapter(objectDatabaseHandler.getAllImagesData());
            objectRecyclerView.setHasFixedSize(true);

            objectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            objectRecyclerView.setAdapter(objectRvAdapter);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}