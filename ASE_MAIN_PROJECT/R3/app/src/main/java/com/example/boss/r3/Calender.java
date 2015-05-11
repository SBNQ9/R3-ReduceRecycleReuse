package com.example.boss.r3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.boss.r3.helper.MyContact;
import com.example.boss.r3.helper.SaveAsyncTask;

import java.net.UnknownHostException;
import java.util.Date;


public class Calender extends ActionBarActivity {

    RelativeLayout rl;
    CalendarView cal;

    @SuppressLint({ "NewApi", "NewApi" })

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        rl = (RelativeLayout) findViewById(R.id.rl);

        cal = new CalendarView(Calender.this);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                ((int) RelativeLayout.LayoutParams.MATCH_PARENT, (int) RelativeLayout.LayoutParams.MATCH_PARENT);

        params.topMargin = 100;
        cal.setLayoutParams(params);

        rl.addView(cal);

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // TODO Auto-generated method stub

                Toast.makeText(getBaseContext(), "Selected Date is\n\n"
                                + dayOfMonth + " : " + month + " : " + year,
                        Toast.LENGTH_LONG).show();





            }
        });
    }


    public void saveContact(View v) throws UnknownHostException {

        MyContact contact = new MyContact();

        Bundle bundle = getIntent().getExtras();

        String editText_fname =  bundle.getString("editText_fname");
        String editText_phone =  bundle.getString("editText_phone");
        String editText_street = bundle.getString("editText_street");
        String editText_apt = bundle.getString("editText_apt");
        Integer np =bundle.getInt("np");
        long val=cal.getDate();
        Date date1=new Date(val);

        String date=date1.toString();

        contact.first_name = editText_fname;
        contact.phone = editText_phone;
        contact.street = editText_street;
        contact.apt = editText_apt;
        contact.np=np;
        contact.date=date;

        SaveAsyncTask tsk = new SaveAsyncTask();
        tsk.execute(contact);
        final Context context = this;
        Intent intent = new Intent(context, Schedule.class);
        intent.putExtra("date",date.toString());
        intent.putExtra("cal",cal.toString());
        startActivity(intent);
    }


    private boolean checkConnectivity() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null && manager.
                getActiveNetworkInfo().isConnectedOrConnecting();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calender, menu);
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
