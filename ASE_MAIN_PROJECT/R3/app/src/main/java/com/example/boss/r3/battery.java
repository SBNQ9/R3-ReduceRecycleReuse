package com.example.boss.r3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class battery extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        Button hb = (Button)findViewById(R.id.housebat);
        hb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            setContentView(R.layout.housebatery);
            }
        });
        Button bp = (Button)findViewById(R.id.batpack);
        bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.baterypack);
            }
        });
        Button ba = (Button)findViewById(R.id.batapp);
        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.batapp);
            }
        });
        Button la = (Button)findViewById(R.id.labatl);
        la.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.leadacid);
            }
        });
        Button ib = (Button)findViewById(R.id.indusbat);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.indusbat);
            }
        });


    }



}
