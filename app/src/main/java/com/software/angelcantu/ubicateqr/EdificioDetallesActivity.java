package com.software.angelcantu.ubicateqr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class EdificioDetallesActivity extends AppCompatActivity {

    TextView txt_titulo, txt_descripcion;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edificio_detalles);

        txt_titulo = (TextView) findViewById(R.id.txtTituloDetalle);
        txt_descripcion = (TextView) findViewById(R.id.txtDescripcionDetalle);
        imagen = (ImageView)findViewById(R.id.imagenDetalle);

        Bundle recibir = getIntent().getExtras();
        String titulo = recibir.getString("titulo");
        String descripcion = recibir.getString("descripcion");
        int foto = recibir.getInt("imagen");
        txt_titulo.setText(titulo);
        txt_descripcion.setText(descripcion);
        imagen.setImageResource(foto);
    }
}
