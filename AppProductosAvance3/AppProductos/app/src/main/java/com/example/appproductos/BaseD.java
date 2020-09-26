package com.example.appproductos;

import androidx.annotation.Nullable;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseD extends SQLiteOpenHelper {
    static String nameDB = "db_productos"; //declaracion de la instancia de la BD
    static String tblProductos = "CREATE TABLE productos(idProducto integer primary key autoincrement, nombre text, descripcion text, fabricante text, stock text, precio text)";

    public BaseD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nameDB, factory, version); //nameDB -> Creacion de la BD en SQLite...
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
                cursor=sqLiteDatabaseReadable.rawQuery("SELECT * FROM productos ", null);
                break;
            case "nuevo":
                sqLiteDatabaseWritable.execSQL("INSERT INTO productos (nombre,descripcion,fabricante,stock,precio) VALUES('"+ data[1] +"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"')");
                break;

            case "modificar":
                sqLiteDatabaseWritable.execSQL("UPDATE productos SET nombre = '"+ data[1] +"', descripcion = '"+ data[2] +"',  fabricante = '"+ data[3] +"' , stock = '"+ data[4] +"', precio = '"+ data[5] +"' WHERE idProducto = '"+ data[0] +"';");
                break;

            case "eliminar":
                sqLiteDatabaseWritable.execSQL("DELETE FROM productos WHERE idProducto = '"+ data[0] +"';");

            default:
                break;
        }
        return cursor;
    }
}
