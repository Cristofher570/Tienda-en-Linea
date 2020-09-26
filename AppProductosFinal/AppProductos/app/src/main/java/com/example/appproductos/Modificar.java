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

    BaseD DatosB;

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
                String distribuidor = tempVal.getText().toString();

                tempVal = (TextView)findViewById(R.id.txtStockProducto1);
                String stock = tempVal.getText().toString();

                tempVal = (TextView)findViewById(R.id.txtPrecioProducto1);
                String precio = tempVal.getText().toString();

                if (!nombre.isEmpty() && !descripcion.isEmpty() && !distribuidor.isEmpty() && !stock.isEmpty() && !precio.isEmpty() ){

                    String[] data = {id,nombre,descripcion,distribuidor,stock,precio};

                    DatosB = new BaseD(getApplicationContext(),"", null, 1);
                    DatosB.mantenimientoProductos(accion, data);

                    Toast.makeText(getApplicationContext(),"¡¡MODIFICACION EXITOSA!!", Toast.LENGTH_LONG).show();
                    Intent mostrarProductos = new Intent(Modificar.this, MainActivity.class);
                    startActivity(mostrarProductos);

                }else {
                    Toast.makeText(Modificar.this, "¡¡POR FAVOR LLENE TODOS LOS CAMPOS!!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        Button btnEliminarProductos = (Button)findViewById(R.id.btnBorrarProductos);
        btnEliminarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String valor = getIntent().getExtras().getString("objetoData");
                String[] articulos = valor.split("\n");


                AlertDialog.Builder builder = new AlertDialog.Builder(Modificar.this);
                builder.setTitle("ELIMINAR");
                builder.setMessage("¿DESEA ELIMINAR ESTE REGISTRO?"+ articulos[0]);

                builder.setPositiveButton("ELIMINAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Eliminar();
                    }
                });
                builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
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

            DatosB = new BaseD(getApplicationContext(), "", null, 1);
            DatosB.mantenimientoProductos(accion, data);

            Toast.makeText(getApplicationContext(), "¡¡REGISTRO ELIMINADO CON EXITO!!", Toast.LENGTH_LONG).show();
            Intent mostrarProductos = new Intent(Modificar.this, MainActivity.class);
            startActivity(mostrarProductos);

        } else {
            Toast.makeText(Modificar.this, "¡¡UPS!! El registro no pudo ser eliminado", Toast.LENGTH_SHORT).show();
        }


    }
    private void recibirdatos(){ ;

        String valor = getIntent().getExtras().getString("objetoData");

        TextView id = (TextView) findViewById((R.id.txtIdProducto));

        TextView nombre = (TextView) findViewById(R.id.txtNombreProducto1);

        TextView descripcion = (TextView) findViewById(R.id.txtDescripcionProducto1);

        TextView distribuidor = (TextView) findViewById(R.id.txtFabricanteProducto1);

        TextView stock = (TextView) findViewById(R.id.txtStockProducto1);

        TextView precio = (TextView) findViewById(R.id.txtPrecioProducto1);

        String[] articulos = valor.split("\n");

        id.setText(articulos[5]);
        nombre.setText(articulos[0]);
        descripcion.setText(articulos[1]);
        distribuidor.setText(articulos[2]);
        stock.setText(articulos[3]);
        precio.setText(articulos[4]);


    }
}