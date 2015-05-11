package com.example.boss.r3;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class PostList extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);





        Button direct = (Button)findViewById(R.id.direct);
        direct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundle = getIntent().getExtras();

                double fromLat=bundle.getDouble("fromLat");
                double fromLng=bundle.getDouble("fromLong");

                double[] doubles=bundle.getDoubleArray("destination");
                String uri = "http://maps.google.com/maps?saddr="+fromLat+","+fromLng+"&daddr="+doubles[0]+","+doubles[1]+"&dirflg=r";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                try
                {
                    startActivity(intent);
                }
                catch(ActivityNotFoundException ex)
                {
                    try
                    {
                        Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        startActivity(unrestrictedIntent);
                    }
                    catch(ActivityNotFoundException innerEx)
                    {
                        Toast.makeText(getBaseContext(), "Please install a maps application", Toast.LENGTH_LONG).show();
                    }
                }
                //Intent intent = new Intent(PostList.this, );


                // startActivity(intent);
            }
        });


        Button comp = (Button)findViewById(R.id.comp);
        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = getIntent().getExtras();


                String phone=bundle.getString("phone");


                Bundle data=new Bundle();
                data.putString("phone",phone);
                Intent intent = new Intent(PostList.this, AfterPickUp.class);
                intent.putExtras(data);
                startActivity(intent);

            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
