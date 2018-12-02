package com.software.angelcantu.ubicateqr.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.software.angelcantu.ubicateqr.R;
import com.software.angelcantu.ubicateqr.entidades.EdificioModelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class InformacionFragment extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject> {
    private String titulo;
    private String fk;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
        {
            titulo = getArguments().getString("Info");
            fk = getArguments().getString("id");
        }
    }

    TextView txt_titulo, txt_desc;
    ImageView img_info;

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    ImageView campoImagen;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        requestQueue = Volley.newRequestQueue(getContext());

        String url_edit= "http://"+getString(R.string.ip)+"/theway/consultempresa.php?id="+titulo;
        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url_edit,null,this,this);
        requestQueue.add(jsonObjectRequest);

        View view = inflater.inflate(R.layout.fragment_informacion, container, false);
        // Inflate the layout for this fragment
        txt_titulo = (TextView)view.findViewById(R.id.txtTitulo);
        img_info = (ImageView)view.findViewById(R.id.imgInstalacion);
        txt_desc = view.findViewById(R.id.txtNombreEdific);

        return view;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        //Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        EdificioModelo edificio=new EdificioModelo();

        JSONArray json= response.optJSONArray("empresa");
        JSONObject jsonObject=null;
        try{
            jsonObject=json.getJSONObject(0);
            edificio.setNombre(jsonObject.optString("nombre"));
            edificio.setDescripcion(jsonObject.optString("descripcion"));
        }catch (JSONException e){
            e.printStackTrace();
        }

        txt_titulo.setText(edificio.getNombre());
        txt_desc.setText(edificio.getDescripcion());

        if (txt_titulo.getText().toString().equals("Universidad Tecnologica General Mariano Escobedo"))
        {
            img_info.setImageResource(R.drawable.fotoute);
        }
        else if (txt_titulo.getText().toString().equals("Universidad Autonoma de Nuevo Leon"))
        {
            img_info.setImageResource(R.drawable.fotouanl);
        }
    }
}
