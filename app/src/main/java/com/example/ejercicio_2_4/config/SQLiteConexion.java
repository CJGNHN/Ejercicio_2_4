package com.example.ejercicio_2_4.config;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.example.ejercicio_2_4.ShowImagesActivity;
import com.example.ejercicio_2_4.Signature;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class SQLiteConexion extends SQLiteOpenHelper
{
    Context context;
    public SQLiteConexion(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, dbname, factory, version);
    }

    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] imageInBytes;

    public SQLiteConexion(Context context) {
        super(context, Transacciones.NameDatabase, null, Transacciones.versionDatabase);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
      db.execSQL(Transacciones.CreateTableSignature);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(Transacciones.DROPTableSignature);
        onCreate(db);
    }

    public void storeImage(Signature objectModelClass){
        try {
            SQLiteDatabase objectSqLiteDatabase=this.getWritableDatabase();
            Bitmap imageToStoreBitmap=objectModelClass.getImage();

            objectByteArrayOutputStream=new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayOutputStream);

            imageInBytes=objectByteArrayOutputStream.toByteArray();

            ContentValues objectContentValues=new ContentValues();

            objectContentValues.put("imageName", objectModelClass.getdescripcion());
            objectContentValues.put("image", imageInBytes);

            long checkIfQueryRuns = objectSqLiteDatabase.insert("Signature", null, objectContentValues);

            if(checkIfQueryRuns!=-1){
                Toast.makeText(context, "Datos almacenados en la DB", Toast.LENGTH_SHORT).show();
                objectSqLiteDatabase.close();
            }else {
                Toast.makeText(context, "No se almacenaron los datos en la DB", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<Signature> getAllImagesData(){
        try {
            SQLiteDatabase objectSqLiteDatabase=this.getReadableDatabase();
            ArrayList<Signature> objectModelClassList=new ArrayList<>();
            Cursor objectCursor=objectSqLiteDatabase.rawQuery("SELECT * FROM Signature", null);

            if(objectCursor.getCount()!=0){
                while (objectCursor.moveToNext()){
                    String nameOfImage=objectCursor.getString(0);
                    byte [] imageBytes=objectCursor.getBlob(1);

                    Bitmap objectBitmap = BitmapFactory.decodeByteArray(imageBytes, 0 , imageBytes.length);
                    objectModelClassList.add(new Signature(nameOfImage,objectBitmap));
                }
                return objectModelClassList;
            }else{
                Toast.makeText(context, "No existen registros en la base de datos", Toast.LENGTH_SHORT).show();
                return null;
            }
        }catch (Exception e){
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
            return null;
        }
    }

}
