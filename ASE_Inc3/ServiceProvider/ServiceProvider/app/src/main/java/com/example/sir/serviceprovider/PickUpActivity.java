package com.example.sir.serviceprovider;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class PickUpActivity extends ActionBarActivity {

    Button Send;
    EditText phone, msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up);
        /*phone = (EditText) findViewById(R.id.editText1);
        msg = (EditText) findViewById(R.id.editText2);
*/




        Send = (Button) findViewById(R.id.button3);
        Send.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Bundle bundle = getIntent().getExtras();
                String phone=bundle.getString("username");
                sendSMS(phone, "YOUR PICK UP IS DONE..... RECYCLE HAS BEEN COLLECTED");
            }
        });
    }

    public void sendSMS(String phoneNumber, String message)
    {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pick_up, menu);
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
