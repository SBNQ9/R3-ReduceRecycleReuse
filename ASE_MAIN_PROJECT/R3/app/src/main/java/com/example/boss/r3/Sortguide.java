package com.example.boss.r3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class Sortguide extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);


        ImageButton sort = (ImageButton)findViewById(R.id.sortguide);
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sortguide.this, list.class);
                startActivity(intent);
            }
        });


        ImageButton map = (ImageButton)findViewById(R.id.maps);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sortguide.this, Recycle.class);
                startActivity(intent);
            }
        });

        ImageButton reuse = (ImageButton)findViewById(R.id.reuse);
        reuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sortguide.this, Reuse.class);
                startActivity(intent);
            }
        });

        ImageButton reduce = (ImageButton)findViewById(R.id.reduce);
        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sortguide.this, Reduce.class);
                startActivity(intent);
            }
        });

    }



}
