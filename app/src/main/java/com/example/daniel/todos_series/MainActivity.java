package com.example.daniel.todos_series;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.example.daniel.todos_series.Interface.ItemClickListener;
import com.example.daniel.todos_series.Model.Serie;
import com.example.daniel.todos_series.ViewHolder.SerieViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference category;
    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Serie, SerieViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        database= FirebaseDatabase.getInstance();
        category= database.getReference("Serie");

        //carga de series
        recycler_menu=findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);

        loadSerie();


    }

    private void loadSerie() {
        adapter = new FirebaseRecyclerAdapter<Serie, SerieViewHolder>(Serie.class, R.layout.serie_item,SerieViewHolder.class,category) {
            @Override
            protected void populateViewHolder(SerieViewHolder viewHolder, Serie model, int position) {
                viewHolder.serie_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.serie_image);
                final Serie clickItem= model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent serieIntent= new Intent(MainActivity.this, season_list.class);
                        serieIntent.putExtra("SerieId",adapter.getRef(position).getKey());
                        startActivity(serieIntent);
                        overridePendingTransition(R.anim.goup,R.anim.godown);
                    }
                });
            }
        };
        Log.d("TAG",""+adapter.getItemCount());
        recycler_menu.setAdapter(adapter);
    }
}
