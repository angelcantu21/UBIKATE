package com.software.angelcantu.ubicateqr.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.software.angelcantu.ubicateqr.R;


public class InformacionFragment extends Fragment {
    private String titulo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
        {
            titulo = getArguments().getString("Info");
        }
    }

    TextView txt_titulo;
    ImageView img_info;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_informacion, container, false);
        // Inflate the layout for this fragment
        txt_titulo = (TextView)view.findViewById(R.id.txtTitulo);
        img_info = (ImageView)view.findViewById(R.id.imgInstalacion);
        txt_titulo.setText(titulo);
        if (txt_titulo.getText().toString().equals("Universidad Tecnológica General Mariano Escobedo"))
        {
            img_info.setImageResource(R.drawable.fotoute);
        }
        else if (txt_titulo.getText().toString().equals("Universidad Autónoma de Nuevo León"))
        {
            img_info.setImageResource(R.drawable.fotouanl);
        }

        return view;
    }

}
