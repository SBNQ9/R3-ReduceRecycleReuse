package com.example.boss.r3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class list extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ImageButton auto = (ImageButton)findViewById(R.id.i1);
        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(list.this, automotive.class);
                startActivity(i1);
            }
        });
        ImageButton battery = (ImageButton)findViewById(R.id.i2);
        battery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(list.this, battery.class);
                startActivity(i2);
            }
        });
        ImageButton construction = (ImageButton)findViewById(R.id.i3);
        construction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(list.this, construction.class);
                startActivity(i3);
            }
        });
        ImageButton elec = (ImageButton)findViewById(R.id.i4);
        elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4 = new Intent(list.this, elec.class);
                startActivity(i4);
            }
        });
        ImageButton garden = (ImageButton)findViewById(R.id.i5);
        garden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5 = new Intent(list.this, garden.class);
                startActivity(i5);
            }
        });
        ImageButton glass = (ImageButton)findViewById(R.id.i6);
        glass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i6 = new Intent(list.this, glass.class);
                startActivity(i6);
            }
        });
        ImageButton hazar = (ImageButton)findViewById(R.id.i7);
        hazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i7 = new Intent(list.this, hazardous.class);
                startActivity(i7);
            }
        });
        ImageButton house = (ImageButton)findViewById(R.id.i8);
        house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i8 = new Intent(list.this, household.class);
                startActivity(i8);
            }
        });
        ImageButton metal = (ImageButton)findViewById(R.id.i9);
        metal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i9 = new Intent(list.this, metal.class);
                startActivity(i9);
            }
        });
        ImageButton paint = (ImageButton)findViewById(R.id.i10);
        paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i10 = new Intent(list.this, paint.class);
                startActivity(i10);
            }
        });
        ImageButton paper = (ImageButton)findViewById(R.id.i11);
        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i11 = new Intent(list.this, paper.class);
                startActivity(i11);
            }
        });
        ImageButton plastic = (ImageButton)findViewById(R.id.i12);
        plastic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i12 = new Intent(list.this, plastic.class);
                startActivity(i12);
            }
        });

    }



}
