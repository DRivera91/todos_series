package com.example.daniel.todos_series;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daniel.todos_series.Model.Chapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ChapterDetail extends AppCompatActivity {

    TextView chapter_name, chapter_description;
    ImageView chapter_image;
    CollapsingToolbarLayout collapsingToolbarLayout;

    String chapterId="";
    FirebaseDatabase database;
    DatabaseReference chapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //firebase
        database= FirebaseDatabase.getInstance();
        chapters= database.getReference("Chapter");

        chapter_name=(TextView)findViewById(R.id.name_chapter);
        chapter_description=(TextView)findViewById(R.id.chapter_description);
        chapter_image=(ImageView)findViewById(R.id.img_sChapter);
        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        if(getIntent()!=null)
            chapterId= getIntent().getStringExtra("ChapterId");
        if(!chapterId.isEmpty()){
            getDetailChapter(chapterId);
        }

    }

    private void getDetailChapter(String chapterId) {
        chapters.child(chapterId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Chapter chap = dataSnapshot.getValue(Chapter.class);
                Picasso.with(getBaseContext()).load(chap.getImage())
                        .into(chapter_image);
                collapsingToolbarLayout.setTitle(chap.getName());
                chapter_description.setText(chap.getDescription());
                chapter_name.setText(chap.getName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
