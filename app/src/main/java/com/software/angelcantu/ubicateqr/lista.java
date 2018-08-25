package com.software.angelcantu.ubicateqr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class lista extends AppCompatActivity {

    ListView lv;
    ArrayList<String> lista;
    ArrayAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lv = (ListView)findViewById(R.id.lista);

        //Base_Datos base_datos = new Base_Datos(getApplicationContext());
        //lista = base_datos.llenar_lv();
        //adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista);
        //lv.setAdapter(adaptador);
    }
}
