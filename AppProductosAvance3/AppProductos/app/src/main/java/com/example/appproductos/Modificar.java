package com.example.appproductos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Entity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Array;
import java.util.ArrayList;

public class Modificar extends AppCompatActivity {

    BaseD miDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        recibirdatos();

        Button btnModificarProductos = (Button)findViewById(R.id.btnModificarProductos);
        btnModificarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String accion = "modificar";

                TextView tempVal = (TextView)findViewById(R.id.txtNombreProducto1);
                String nombre = tempVal.getText().toString();

                tempVal = (TextView)findViewById(R.id.txtIdProducto);
                String id = tempVal.getText().toString();

                tempVal = (TextView)findViewById(R.id.txtDescripcionProducto1);
                String descripcion = tempVal.getText().toString();

                tempVal = (TextView)findViewById(R.id.txtFabricanteProducto1);
                String fabricante = tempVal.getText().toString();

                tempVal = (TextView)findViewById(R.id.txtStockProducto1);
                String stock = tempVal.getText().toString();

                tempVal = (TextView)findViewById(R.id.txtPrecioProducto1);
                String precio = tempVal.getText().toString();

                if (!nombre.isEmpty() && !descripcion.isEmpty() && !fabricante.isEmpty() && !stock.isEmpty() && !precio.isEmpty() ){

                    String[] data = {id,nombre,descripcion,fabricante,stock,precio};

                    miDB = new BaseD(getApplicationContext(),"", null, 1);
                    miDB.mantenimientoProductos(accion, data);

                    Toast.makeText(getApplicationContext(),"Registro de Producto Modificado con exito", Toast.LENGTH_LONG).show();
                    Intent mostrarProductos = new Intent(Modificar.this, MainActivity.class);
                    startActivity(mostrarProductos);

                }else {
                    Toast.makeText(Modificar.this, "Debe de llenar todos los datos del Producto", Toast.LENGTH_SHORT).show();
                }


            }
        });

        Button btnEliminarProductos = (Button)findViewById(R.id.btnBorrarProductos);
        btnEliminarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String valor = getIntent().getExtras().getString("objetoData");
                String[] productos = valor.split("\n");


                AlertDialog.Builder builder = new AlertDialog.Builder(Modificar.this);
                builder.setTitle("Eliminar Registro");
                builder.setMessage("Esta seguro que quiere eliminar "+ productos[0]);

                builder.setPositiveButton("Si, Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Eliminar();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });




    }

    private void Eliminar(){
        String accion = "eliminar";

        TextView tempVal = (TextView) findViewById(R.id.txtIdProducto);
        String id = tempVal.getText().toString();

        if (!id.isEmpty()) {

            String[] data = {id};

            miDB = new BaseD(getApplicationContext(), "", null, 1);
            miDB.mantenimientoProductos(accion, data);

            Toast.makeText(getApplicationContext(), "Registro de Producto Eliminado con exito", Toast.LENGTH_LONG).show();
            Intent mostrarProductos = new Intent(Modificar.this, MainActivity.class);
            startActivity(mostrarProductos);

        } else {
            Toast.makeText(Modificar.this, "No se pudo eliminar", Toast.LENGTH_SHORT).show();
        }


    }
    private void recibirdatos(){ ;

        String valor = getIntent().getExtras().getString("objetoData");

        TextView id = (TextView) findViewById((R.id.txtIdProducto));

        TextView nombre = (TextView) findViewById(R.id.txtNombreProducto1);

        TextView descripcion = (TextView) findViewById(R.id.txtDescripcionProducto1);

        TextView fabricante = (TextView) findViewById(R.id.txtFabricanteProducto1);

        TextView stock = (TextView) findViewById(R.id.txtStockProducto1);

        TextView precio = (TextView) findViewById(R.id.txtPrecioProducto1);

        String[] productos = valor.split("\n");

        id.setText(productos[5]);
        nombre.setText(productos[0]);
        descripcion.setText(productos[1]);
        fabricante.setText(productos[2]);
        stock.setText(productos[3]);
        precio.setText(productos[4]);


    }
}