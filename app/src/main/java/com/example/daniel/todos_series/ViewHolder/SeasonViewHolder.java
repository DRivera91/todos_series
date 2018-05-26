package com.example.daniel.todos_series.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daniel.todos_series.Interface.ItemClickListener;
import com.example.daniel.todos_series.R;

public class SeasonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView seas_name;
    public ImageView seas_image;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public SeasonViewHolder(View itemView) {
        super(itemView);
        seas_name= itemView.findViewById(R.id.season_name);
        seas_image= itemView.findViewById(R.id.season_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(),false);
    }
}
