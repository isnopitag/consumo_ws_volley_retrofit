package com.carmona.tecnostecnm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by carmona on 11/10/17.
 */

public class TecnosAdapter extends RecyclerView.Adapter<TecnosAdapter.TecnoViewHolder>{

    private List<Tecs> listaTecnos;

    public TecnosAdapter(List<Tecs> listaTecnos) {
        this.listaTecnos = listaTecnos;
    }

    public static class TecnoViewHolder extends RecyclerView.ViewHolder{

        public ImageView  img_logo:
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
