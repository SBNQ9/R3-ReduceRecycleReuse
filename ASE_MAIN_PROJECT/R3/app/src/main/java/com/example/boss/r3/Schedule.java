package com.example.boss.r3;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boss.r3.helper.ScheduleClient;

import java.util.Date;


public class Schedule extends ActionBarActivity {


    ScheduleClient scheduleClient;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        bundle = getIntent().getExtras();


        // Create a new service client and bind our activity to this service
        scheduleClient = new ScheduleClient(this);
        scheduleClient.doBindService();


        String date =  bundle.getString("date");
        TextView textView=(TextView) findViewById(R.id.textView);
        textView.setText("You have scheduled pick up on"+date);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_schedule, menu);
        return true;
    }

    public void toNotify(View v){
        // Get the date from our datepicker

        // Create a new calendar set to the date chosen
        // we set the time to midnight (i.e. the first minute of that day)
        String dat =  bundle.getString("date");
        Date date=new Date(dat);



// Ask our service to set an alarm for that date, this activity talks to the client that talks to the service
        scheduleClient.setAlarmForNotification(date);
        // Notify the user what they just did
        Toast.makeText(this, "Notification set for: " + date, Toast.LENGTH_SHORT).show();


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
