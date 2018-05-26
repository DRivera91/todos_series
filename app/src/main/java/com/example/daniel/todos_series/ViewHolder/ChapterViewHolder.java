package com.example.daniel.todos_series.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daniel.todos_series.Interface.ItemClickListener;
import com.example.daniel.todos_series.R;

public class ChapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView chap_name;
    public ImageView chap_image;

    private ItemClickListener itemClickListener;

    public ChapterViewHolder(View itemView) {
        super(itemView);
        chap_name= itemView.findViewById(R.id.chapter_name);
        chap_image= itemView.findViewById(R.id.chapter_image);

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
