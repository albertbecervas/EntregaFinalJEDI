package com.code.albert.evilmemory.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.albert.evilmemory.R;

import java.util.ArrayList;


public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.AdapterViewHolder>{

    ArrayList<RankingPlayer> rankingPlayers;
    public MyCustomAdapter(ArrayList<RankingPlayer> rankingPlayers){
        this.rankingPlayers = rankingPlayers;
    }


    @Override
    public MyCustomAdapter.AdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //Instancia un layout XML en la correspondiente vista.
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        //Inflamos en la vista el layout para cada elemento
        View view = inflater.inflate(R.layout.rowlayout, viewGroup, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyCustomAdapter.AdapterViewHolder adapterViewholder, int position) {
        int iconLayout = rankingPlayers.get(position).getIcon();
        //Dependiendo del entero se asignará una imagen u otra
        switch (iconLayout){
            case 0:
                //male
                adapterViewholder.icon.setImageDrawable(adapterViewholder.v.getResources().getDrawable(R.drawable.ic_angel));
                break;
            case 1:
                //female
                adapterViewholder.icon.setImageDrawable(adapterViewholder.v.getResources().getDrawable(R.drawable.ic_evil));
                break;
        }
        adapterViewholder.name.setText(rankingPlayers.get(position).getName());
        adapterViewholder.score.setText(""+rankingPlayers.get(position).getScore());

    }

    @Override
    public int getItemCount() {
        //Debemos retornar el tamaño de todos los elementos contenidos en el viewholder
        //Por defecto es return 0 --> No se mostrará nada.
        return rankingPlayers.size();
    }

    public void setData(ArrayList<RankingPlayer> rankingPlayers){
        this.rankingPlayers = rankingPlayers;

    }



    //Definimos una clase viewholder que funciona como adapter para
    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        /*
        *  Mantener una referencia a los elementos de nuestro ListView mientras el usuario realiza
        *  scrolling en nuestra aplicación. Así que cada vez que obtenemos la vista de un item,
        *  evitamos las frecuentes llamadas a findViewById, la cuál se realizaría únicamente la primera vez y el resto
        *  llamaríamos a la referencia en el ViewHolder, ahorrándonos procesamiento.
        */

        public ImageView icon;
        public TextView name;
        public TextView score;
        public View v;
        public AdapterViewHolder(View itemView) {
            super(itemView);
            this.v = itemView;
            this.icon = (ImageView) itemView.findViewById(R.id.icon);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.score = (TextView) itemView.findViewById(R.id.score);
        }
    }
}
