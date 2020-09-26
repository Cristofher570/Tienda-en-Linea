package com.example.appproductos;

import androidx.annotation.Nullable;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseD extends SQLiteOpenHelper {
    static String nameDB = "db_productos";
    static String tblProductos = "CREATE TABLE articulos(idProducto integer primary key autoincrement, nombre text, descripcion text, distribuidor text, stock text, precio text)";

    public BaseD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nameDB, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tblProductos);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public Cursor mantenimientoProductos(String accion, String[] data){
        SQLiteDatabase sqLiteDatabaseReadable=getReadableDatabase();
        SQLiteDatabase sqLiteDatabaseWritable=getWritableDatabase();
        Cursor cursor = null;
        switch (accion){
            case "consultar":
                cursor=sqLiteDatabaseReadable.rawQuery("SELECT * FROM articulos ", null);
                break;
            case "nuevo":
                sqLiteDatabaseWritable.execSQL("INSERT INTO articulos (nombre,descripcion,distribuidor,stock,precio) VALUES('"+ data[1] +"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"')");
                break;

            case "modificar":
                sqLiteDatabaseWritable.execSQL("UPDATE articulos SET nombre = '"+ data[1] +"', descripcion = '"+ data[2] +"',  distribuidor = '"+ data[3] +"' , stock = '"+ data[4] +"', precio = '"+ data[5] +"' WHERE idProducto = '"+ data[0] +"';");
                break;

            case "eliminar":
                sqLiteDatabaseWritable.execSQL("DELETE FROM articulos WHERE idProducto = '"+ data[0] +"';");

            default:
                break;
        }
        return cursor;
    }
}
