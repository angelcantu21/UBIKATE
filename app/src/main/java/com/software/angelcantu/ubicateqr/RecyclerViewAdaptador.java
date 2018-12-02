package com.software.angelcantu.ubicateqr;

/**
 * Created by ANGELCANTU on 26/01/2018.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.software.angelcantu.ubicateqr.entidades.EdificioModelo;

import java.util.List;

public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder> implements View.OnClickListener{

    public List<EdificioModelo> ListasEdificio;
    private View.OnClickListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        //Variables
        private TextView nombre, descricpion, ubicacion;
        private ImageView imagen;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView)itemView.findViewById(R.id.txtTitulo);
            descricpion = (TextView)itemView.findViewById(R.id.txtDescripcion);
            ubicacion = (TextView) itemView.findViewById(R.id.txtCarrera);
            imagen = (ImageView) itemView.findViewById(R.id.imgEdificio);
        }
    }

    public RecyclerViewAdaptador(List<EdificioModelo> listaEdificio) {
        ListasEdificio = listaEdificio;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contenedor, null,false);
        //RecyclerView.LayoutParams layParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //view.setLayoutParams(layParams);
        view.setOnClickListener(this);
        //ViewHolder viewHolder = new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nombre.setText(ListasEdificio.get(position).getNombre());
        holder.descricpion.setText(ListasEdificio.get(position).getDescripcion());
        holder.ubicacion.setText(ListasEdificio.get(position).getUbicacion());
        if (ListasEdificio.get(position).getImagen()!=null) {
            holder.imagen.setImageBitmap(ListasEdificio.get(position).getImagen());
            //   holder.imagen.setImageResource(ListasEdificio.get(position).getImagen());
        }else
        {
            holder.imagen.setImageResource(R.drawable.ic_menu_camera);
        }

    }

    @Override
    public int getItemCount() {return ListasEdificio.size();
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }
}

