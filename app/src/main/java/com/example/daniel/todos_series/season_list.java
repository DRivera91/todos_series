package com.example.daniel.todos_series;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.daniel.todos_series.Interface.ItemClickListener;
import com.example.daniel.todos_series.Model.Season;
import com.example.daniel.todos_series.Model.Serie;
import com.example.daniel.todos_series.ViewHolder.SeasonViewHolder;
import com.example.daniel.todos_series.ViewHolder.SerieViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class season_list extends AppCompatActivity {

    RecyclerView recycler_season;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference seasList;
    String serieId="";
    FirebaseRecyclerAdapter<Season, SeasonViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //firebase
        database=FirebaseDatabase.getInstance();
        seasList=database.getReference("Season");

        recycler_season=(RecyclerView)findViewById(R.id.recycler_season);
        recycler_season.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recycler_season.setLayoutManager(layoutManager);


        //se recibe el parametro
        if(getIntent()!=null)
            serieId=getIntent().getStringExtra("SerieId");
        if(!serieId.isEmpty()&&serieId!=null){
            loadListSeason(serieId);
        }
    }

    public void OnBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.godown,R.anim.godown);
    }

    private void loadListSeason(String serieId) {
        adapter= new FirebaseRecyclerAdapter<Season, SeasonViewHolder>(Season.class,
            R.layout.season_item, SeasonViewHolder.class,
                seasList.orderByChild("MenuId").equalTo(serieId)
        ){
            @Override
            protected void populateViewHolder(SeasonViewHolder viewHolder, Season model, int position) {
                viewHolder.seas_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.seas_image);

                final Season local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent seasonIntent= new Intent(season_list.this, chapter_list.class);
                        seasonIntent.putExtra("SeasonId",adapter.getRef(position).getKey());
                        startActivity(seasonIntent);
                        overridePendingTransition(R.anim.goup,R.anim.godown);
                        //Toast.makeText(season_list.this,""+local.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        recycler_season.setAdapter(adapter);
    }
}
