package com.software.angelcantu.ubicateqr.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.software.angelcantu.ubicateqr.EdificioDetallesActivity;
import com.software.angelcantu.ubicateqr.TabbedActivity;
import com.software.angelcantu.ubicateqr.R;
import com.software.angelcantu.ubicateqr.RecyclerViewAdaptador;
import com.software.angelcantu.ubicateqr.entidades.EdificioModelo;
import com.software.angelcantu.ubicateqr.interfaces.IComunicaFragments;

import java.util.ArrayList;


public class EdificioFragment extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject>{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    ImageView campoImagen;

    // TODO: Rename and change types of parameters

    ArrayList<EdificioModelo> listaEdificios;
    RecyclerView recyclerView;
    private OnFragmentInteractionListener mListener;
    Activity actividad;
    IComunicaFragments interfaceComunicaFragments;
    private String titulo;
    private String fk;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            titulo = getArguments().getString("Info");
            fk = getArguments().getString("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edificio, container, false);


        campoImagen = (ImageView)view.findViewById(R.id.imgEdificio);
        listaEdificios = new ArrayList<>();
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerEdificio);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        requestQueue = Volley.newRequestQueue(getContext());
        RecyclerViewAdaptador adaptador = new RecyclerViewAdaptador(listaEdificios);
        recyclerView.setAdapter(adaptador);
        //Toast.makeText(actividad, ""+fk, Toast.LENGTH_SHORT).show();
        //String url_edit= "http://10.12.218.229:80/AppVentas/consultar_ventas.php";//http://10.12.218.192:80/getD?id=1";
        String url_edit= "http://"+getString(R.string.ip)+"/theway/webService.php?id="+fk;
        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url_edit,null,this,this);
        requestQueue.add(jsonObjectRequest);



        return view;
    }


  /*  public void LlenarLista()
    {
        //listaEdificios.add(new EdificioModelo("Edificio D-1", "Dirección de Mantenimiento Industrial","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque quam risus, facilisis nec lacinia varius, laoreet sed justo. Integer porttitor, purus id ultricies fermentum, ipsum leo auctor elit, id ultricies enim diam eget tellus. Fusce eget sagittis massa, id pretium nunc. Curabitur vel enim id lacus accumsan aliquam in vitae lacus.", "Ubicacion", R.drawable.edificiod1,R.drawable.edificiod1));
        //listaEdificios.add(new EdificioModelo("Edificio D-2", "Dirección de Comercialización", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque quam risus, facilisis nec lacinia varius, laoreet sed justo. Integer porttitor, purus id ultricies fermentum, ipsum leo auctor elit, id ultricies enim diam eget tellus. Fusce eget sagittis massa, id pretium nunc. Curabitur vel enim id lacus accumsan aliquam in vitae lacus.","Ubicacion", R.drawable.edificiod2,R.drawable.edificiod2));
        //listaEdificios.add(new EdificioModelo("Edificio D-3", "Direccion de Mecatronica y Electricidad", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque quam risus, facilisis nec lacinia varius, laoreet sed justo. Integer porttitor, purus id ultricies fermentum, ipsum leo auctor elit, id ultricies enim diam eget tellus. Fusce eget sagittis massa, id pretium nunc. Curabitur vel enim id lacus accumsan aliquam in vitae lacus.", "Ubicacion", R.drawable.edificiod1,R.drawable.edificiod1));
        //listaEdificios.add(new EdificioModelo("Edificio D-4", "Direccion de Tecnologias de la informacion", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque quam risus, facilisis nec lacinia varius, laoreet sed justo. Integer porttitor, purus id ultricies fermentum, ipsum leo auctor elit, id ultricies enim diam eget tellus. Fusce eget sagittis massa, id pretium nunc. Curabitur vel enim id lacus accumsan aliquam in vitae lacus.", "Ubicacion", R.drawable.edificiod4,R.drawable.edificiod4));
    }*/

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof Activity){
            this.actividad= (Activity) context;
            interfaceComunicaFragments= (IComunicaFragments) this.actividad;
        }

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(actividad, ""+error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
       // Toast.makeText(actividad, ""+response.toString(), Toast.LENGTH_SHORT).show();
        EdificioModelo edificio= null;
        JSONArray json=response.optJSONArray("edificio");



        try {
            for(int x=0;x<json.length();x++){
                edificio=new EdificioModelo();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(x);
                ///miVenta.setFk_articulo(jsonObject.optInt("FKArticulo"));
                edificio.setNombre(jsonObject.optString("nombre"));
                edificio.setDescripcion(jsonObject.optString("descripcion"));
                edificio.setDescripcion_larga(jsonObject.optString("descripcion_larga"));
                edificio.setUbicacion("sexto");
                edificio.setDato(jsonObject.optString("imagen"));
                listaEdificios.add(edificio);
            }

            //campoImagen.setImageBitmap(edificio.getImagen());

            RecyclerViewAdaptador adaptador=new RecyclerViewAdaptador(listaEdificios);
            recyclerView.setAdapter(adaptador);



            adaptador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // Toast.makeText(getContext(), "Seleccion: "+listaEdificios.get(recyclerView.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_LONG).show();
                    Intent abrir = new Intent(getContext(), EdificioDetallesActivity.class);
                    abrir.putExtra("titulo", listaEdificios.get(recyclerView.getChildAdapterPosition(view)).getNombre());
                    abrir.putExtra("descripcion", listaEdificios.get(recyclerView.getChildAdapterPosition(view)).getDescripcion_larga());
                    startActivity(abrir);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*id_pro.setText(""+miVenta.getFk_articulo());
        nombre.setText(""+name);
        cantidad.setText(""+miVenta.getCantidad());
        precio.setText(""+miVenta.getPrecio());*/
        //listaEdificios.add(new EdificioModelo("Edificio D-4", "Direccion de Tecnologias de la informacion", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque quam risus, facilisis nec lacinia varius, laoreet sed justo. Integer porttitor, purus id ultricies fermentum, ipsum leo auctor elit, id ultricies enim diam eget tellus. Fusce eget sagittis massa, id pretium nunc. Curabitur vel enim id lacus accumsan aliquam in vitae lacus.", "Ubicacion", R.drawable.edificiod4,R.drawable.edificiod4));
        //listaEdificios.add(new EdificioModelo(edificio.getNombre(),edificio.getDescripcion(),edificio.getDescripcion_larga(),"Ubicacion",R.drawable.edificiod1,R.drawable.edificiod1));
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

        void enviarEdificio(EdificioModelo edificio);
    }
}
