package com.software.angelcantu.ubicateqr;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DevelopersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);
    }
    //METODO PARA PONER BOTON DE REGRESAR EN LA PARTE DE LA BARRA
    private void setupActionBar(){
        ActionBar regresar = getSupportActionBar();//SE INSTANCIA LA ACCION DE LA BARRA
        if (regresar != null){//MIENTRAS SEA DIFERENTE DE NULO
            regresar.setDisplayHomeAsUpEnabled(true);//SE MANTIENE HABILITADO
        }
    }
}
