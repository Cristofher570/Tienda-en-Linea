package com.example.appproductos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Agregar extends AppCompatActivity {

    BaseD miDB;
    String accion = "nuevo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        Button btnMostrarProductos = (Button)findViewById(R.id.btnMostrarProductos);
        btnMostrarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mostrarProductos = new Intent(Agregar.this, MainActivity.class);
                startActivity(mostrarProductos);
            }
        });

        Button btnGuardarProductos = (Button)findViewById(R.id.btnGuardarProductos);
        btnGuardarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tempVal = (TextView)findViewById(R.id.txtNombreProducto);
                String nombre = tempVal.getText().toString();

                tempVal = (TextView)findViewById(R.id.txtDescripcionProducto);
                String descripcion = tempVal.getText().toString();

                tempVal = (TextView)findViewById(R.id.txtFabricanteProducto);
                String fabricante = tempVal.getText().toString();

                tempVal = (TextView)findViewById(R.id.txtStockProducto);
                String stock = tempVal.getText().toString();

                tempVal = (TextView)findViewById(R.id.txtPrecioProducto);
                String precio = tempVal.getText().toString();

                if (!nombre.isEmpty() && !descripcion.isEmpty() && !fabricante.isEmpty() && !stock.isEmpty() && !precio.isEmpty() ){

                    String[] data = {"",nombre,descripcion,fabricante,stock,precio};

                    miDB = new BaseD(getApplicationContext(),"", null, 1);
                    miDB.mantenimientoProductos(accion, data);

                    Toast.makeText(getApplicationContext(),"Registro de Producto insertado con exito", Toast.LENGTH_LONG).show();
                    Intent mostrarProductos = new Intent(Agregar.this, MainActivity.class);
                    startActivity(mostrarProductos);

                }else {
                    Toast.makeText(Agregar.this, "Debe de llenar todos los datos del Producto", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
