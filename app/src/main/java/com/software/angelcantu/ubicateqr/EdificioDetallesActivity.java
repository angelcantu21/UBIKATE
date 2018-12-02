package com.software.angelcantu.ubicateqr;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.software.angelcantu.ubicateqr.entidades.EdificioModelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EdificioDetallesActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject>, View.OnClickListener{

    TextView txt_titulo, txt_descripcion;
    ImageView imagen;
    Button btn_mapa;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    String titulo;
    float x, y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edificio_detalles);

        txt_titulo = (TextView) findViewById(R.id.txtTituloDetalle);
        txt_descripcion = (TextView) findViewById(R.id.txtDescripcionDetalle);
        imagen = (ImageView)findViewById(R.id.imagenDetalle);
        Bundle recibir = getIntent().getExtras();
        titulo = recibir.getString("titulo");
        String descripcion = recibir.getString("descripcion");
        btn_mapa = findViewById(R.id.btnMapa);
        btn_mapa.setOnClickListener(this);

        requestQueue = Volley.newRequestQueue(getApplicationContext());


        txt_titulo.setText(titulo);
        txt_descripcion.setText(descripcion);
        Cargar();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), ""+error.toString(), Toast.LENGTH_LONG).show();
    }

    public void Cargar(){
        String url_edit= "http://"+getString(R.string.ip)+"/theway/imagenDetalle.php?nombre="+titulo;
        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url_edit,null,this,this);
        requestQueue.add(jsonObjectRequest);
    }
    @Override
    public void onResponse(JSONObject response) {
        //Toast.makeText(getApplicationContext(), ""+response.toString(), Toast.LENGTH_LONG).show();
        EdificioModelo edificio=new EdificioModelo();

        JSONArray json=response.optJSONArray("edificio");
        JSONObject jsonObject=null;
        float latitud=0f, longitud=0f;
        try {
            jsonObject=json.getJSONObject(0);
            edificio.setDato(jsonObject.optString("imagen"));
            latitud = (float) jsonObject.optDouble("latitud");
            longitud = (float) jsonObject.optDouble("longitud");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (edificio.getImagen()!=null) {
            imagen.setImageBitmap(edificio.getImagen());
        }else {
            imagen.setImageResource(R.drawable.ic_menu_camera);
        }
        x=latitud;
        y=longitud;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnMapa:
                Intent in = new Intent(getApplicationContext(), MapsActivity.class);
                in.putExtra("cor_x", x);
                in.putExtra("cor_y", y);
                startActivity(in);
                break;
        }
    }
}
