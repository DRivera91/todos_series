package com.example.daniel.todos_series;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.daniel.todos_series.Interface.ItemClickListener;
import com.example.daniel.todos_series.Model.Chapter;
import com.example.daniel.todos_series.ViewHolder.ChapterViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class chapter_list extends AppCompatActivity {


    RecyclerView recycler_chapter;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference chapList;

    String seasonId="";
    FirebaseRecyclerAdapter<Chapter, ChapterViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //firebase
        database=FirebaseDatabase.getInstance();
        chapList=database.getReference("Chapter");

        recycler_chapter=(RecyclerView)findViewById(R.id.recycler_chapter);
        recycler_chapter.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recycler_chapter.setLayoutManager(layoutManager);

        //se recibe el parametro
        if(getIntent()!=null)
            seasonId=getIntent().getStringExtra("SeasonId");
        if(!seasonId.isEmpty()&& seasonId!=null){
            loadListchapter(seasonId);
        }

    }

    private void loadListchapter(String seasonId) {
        adapter= new FirebaseRecyclerAdapter<Chapter, ChapterViewHolder>(Chapter.class,
                R.layout.chapter_item, ChapterViewHolder.class,
                chapList.orderByChild("TempId").equalTo(seasonId)
        ) {
            @Override
            protected void populateViewHolder(ChapterViewHolder viewHolder, Chapter model, int position) {
                viewHolder.chap_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.chap_image);

                final Chapter local=model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        Intent chapterIntent= new Intent(chapter_list.this, ChapterDetail.class);
                        chapterIntent.putExtra("ChapterId",adapter.getRef(position).getKey());
                        startActivity(chapterIntent);
                        overridePendingTransition(R.anim.goup,R.anim.godown);
                        //Toast.makeText(chapter_list.this,""+local.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        recycler_chapter.setAdapter(adapter);
    }
}
