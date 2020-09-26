package com.example.appproductos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BaseD DatosB;
    Cursor misProductos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        obtenerDatosProductos();

        ImageButton ibmostrar = (ImageButton) findViewById(R.id.ibmostrar);
        ibmostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AgregarProductos = new Intent(MainActivity.this, Agregar.class);
                startActivity(AgregarProductos);
            }
        });

    }
    void obtenerDatosProductos(){
        DatosB = new BaseD(getApplicationContext(), "", null, 1);
        misProductos = DatosB.mantenimientoProductos("consultar", null);
        if( misProductos.moveToFirst() ){
            mostrarDatosProductos();
        } else{
            Toast.makeText(getApplicationContext(), "¡¡UPS!! Aun no tienes registros",Toast.LENGTH_LONG).show();
            Intent agregarProductos = new Intent(MainActivity.this, Agregar.class);
            startActivity(agregarProductos);
        }
    }



    void mostrarDatosProductos(){
        ListView ltsProductos = (ListView)findViewById(R.id.ltsProductos);
        final ArrayList<String> stringArrayList = new ArrayList<String>();

        final ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,stringArrayList);
        ltsProductos.setAdapter(stringArrayAdapter);


        do {
            stringArrayList.add( misProductos.getString(1) + "\n" + misProductos.getString(2) + "\n" + misProductos.getString(3)+ "\n" + misProductos.getString(4) + "\n" + misProductos.getString(5)+ "\n" + misProductos.getString(0) + "\n" );

        }while(misProductos.moveToNext());
        stringArrayAdapter.notifyDataSetChanged();


        TextView BuscarProductos = (TextView) findViewById(R.id.txtBuscarProductos);
        BuscarProductos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                stringArrayAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        ltsProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mostrarProductos = new Intent(MainActivity.this, Modificar.class);
                mostrarProductos.putExtra("objetoData", stringArrayList.get(i));
                startActivity(mostrarProductos);
            }
        });
    }
}