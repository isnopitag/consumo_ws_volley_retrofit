package com.carmona.tecnostecnm;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by carmona on 11/10/17.
 */

public class TecnosAdapter extends RecyclerView.Adapter<TecnosAdapter.TecnoViewHolder>{

    private List<Tecs> listaTecnos;
    private Context contexto;

    public TecnosAdapter(List<Tecs> listaTecnos, Context con) {
        this.listaTecnos = listaTecnos;
         contexto = con;
    }

    public static class TecnoViewHolder extends RecyclerView.ViewHolder{

        public ImageView  img_logo;
        public TextView txt_long_name;
        public TextView txt_short_name;


        public TecnoViewHolder(View itemView) {
            super(itemView);
            //Se homologa
            img_logo = (ImageView) itemView.findViewById(R.id.img_logo);
            txt_long_name = (TextView) itemView.findViewById(R.id.txt_long_name);
            txt_short_name = (TextView) itemView.findViewById(R.id.txt_short_name);

        }

    }

    @Override
    public TecnoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_tecnos,parent,false);

        return new TecnoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TecnoViewHolder holder, int position) {
        try {
            Bitmap logo = Ion.with(contexto).load(listaTecnos.get(position).getLogoTecno()).withBitmap().asBitmap().get();
            holder.txt_long_name.setText(listaTecnos.get(position).getNombreTecno());
            holder.txt_short_name.setText(listaTecnos.get(position).getNombreCortoTecno());
            holder.img_logo.setImageBitmap(logo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /*@Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }*/

    @Override
    public int getItemCount() {
        return listaTecnos.size();
    }
}
