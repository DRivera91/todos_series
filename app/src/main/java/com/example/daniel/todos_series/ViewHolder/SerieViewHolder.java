package com.example.daniel.todos_series.ViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daniel.todos_series.Interface.ItemClickListener;
import com.example.daniel.todos_series.R;

public class SerieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView serie_name;
    public ImageView serie_image;

    private ItemClickListener itemClickListener;



    public SerieViewHolder(View itemView) {
        super(itemView);
        serie_name= itemView.findViewById(R.id.menu_name);
        serie_image= itemView.findViewById(R.id.menu_image);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(),false);
    }

}
