package com.example.appproductos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Agregar extends AppCompatActivity {

    BaseD DatosB;
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
                String distribuidor = tempVal.getText().toString();

                tempVal = (TextView)findViewById(R.id.txtStockProducto);
                String stock = tempVal.getText().toString();

                tempVal = (TextView)findViewById(R.id.txtPrecioProducto);
                String precio = tempVal.getText().toString();

                if (!nombre.isEmpty() && !descripcion.isEmpty() && !distribuidor.isEmpty() && !stock.isEmpty() && !precio.isEmpty() ){

                    String[] data = {"",nombre,descripcion,distribuidor,stock,precio};

                    DatosB = new BaseD(getApplicationContext(),"", null, 1);
                    DatosB.mantenimientoProductos(accion, data);

                    Toast.makeText(getApplicationContext(),"Guardado Exitoso", Toast.LENGTH_LONG).show();
                    Intent mostrarProductos = new Intent(Agregar.this, MainActivity.class);
                    startActivity(mostrarProductos);

                }else {
                    Toast.makeText(Agregar.this, "¡¡POR FAVOR LLENE TODOS LOS CAMPOS!!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
