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
import android.widget.Toast;

import com.software.angelcantu.ubicateqr.EdificioDetallesActivity;
import com.software.angelcantu.ubicateqr.TabbedActivity;
import com.software.angelcantu.ubicateqr.R;
import com.software.angelcantu.ubicateqr.RecyclerViewAdaptador;
import com.software.angelcantu.ubicateqr.entidades.EdificioModelo;
import com.software.angelcantu.ubicateqr.interfaces.IComunicaFragments;

import java.util.ArrayList;


public class EdificioFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<EdificioModelo> listaEdificios;
    RecyclerView recyclerView;
    private OnFragmentInteractionListener mListener;
    Activity actividad;
    IComunicaFragments interfaceComunicaFragments;

    public EdificioFragment()
    {

    }
    public static EdificioFragment newInstance(String param1, String param2) {
        EdificioFragment fragment = new EdificioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edificio, container, false);

        listaEdificios = new ArrayList<>();
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerEdificio);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        LlenarLista();

        RecyclerViewAdaptador adaptador = new RecyclerViewAdaptador(listaEdificios);
        recyclerView.setAdapter(adaptador);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Seleccion: "+
                        listaEdificios.get(recyclerView.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_LONG).show();
                Intent abrir = new Intent(getContext(), EdificioDetallesActivity.class);
                abrir.putExtra("titulo", listaEdificios.get(recyclerView.getChildAdapterPosition(view)).getNombre());
                abrir.putExtra("descripcion", listaEdificios.get(recyclerView.getChildAdapterPosition(view)).getDescripcion_larga());
                abrir.putExtra("imagen", listaEdificios.get(recyclerView.getChildAdapterPosition(view)).getImagen());
                startActivity(abrir);
                //interfaceComunicaFragments.enviarEdificio(listaEdificios.get(recyclerView.getChildAdapterPosition(view)));
            }
        });

        return view;
    }


    public void LlenarLista()
    {
        listaEdificios.add(new EdificioModelo("Edificio D-1", "Dirección de Mantenimiento Industrial","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque quam risus, facilisis nec lacinia varius, laoreet sed justo. Integer porttitor, purus id ultricies fermentum, ipsum leo auctor elit, id ultricies enim diam eget tellus. Fusce eget sagittis massa, id pretium nunc. Curabitur vel enim id lacus accumsan aliquam in vitae lacus.", "Ubicacion", R.drawable.edificiod1,R.drawable.edificiod1));
        listaEdificios.add(new EdificioModelo("Edificio D-2", "Dirección de Comercialización", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque quam risus, facilisis nec lacinia varius, laoreet sed justo. Integer porttitor, purus id ultricies fermentum, ipsum leo auctor elit, id ultricies enim diam eget tellus. Fusce eget sagittis massa, id pretium nunc. Curabitur vel enim id lacus accumsan aliquam in vitae lacus.","Ubicacion", R.drawable.edificiod2,R.drawable.edificiod2));
        listaEdificios.add(new EdificioModelo("Edificio D-3", "Direccion de Mecatronica y Electricidad", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque quam risus, facilisis nec lacinia varius, laoreet sed justo. Integer porttitor, purus id ultricies fermentum, ipsum leo auctor elit, id ultricies enim diam eget tellus. Fusce eget sagittis massa, id pretium nunc. Curabitur vel enim id lacus accumsan aliquam in vitae lacus.", "Ubicacion", R.drawable.edificiod1,R.drawable.edificiod1));
        listaEdificios.add(new EdificioModelo("Edificio D-4", "Direccion de Tecnologias de la informacion", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque quam risus, facilisis nec lacinia varius, laoreet sed justo. Integer porttitor, purus id ultricies fermentum, ipsum leo auctor elit, id ultricies enim diam eget tellus. Fusce eget sagittis massa, id pretium nunc. Curabitur vel enim id lacus accumsan aliquam in vitae lacus.", "Ubicacion", R.drawable.edificiod4,R.drawable.edificiod4));
    }

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

        void enviarEdificio(EdificioModelo edificio);
    }
}
