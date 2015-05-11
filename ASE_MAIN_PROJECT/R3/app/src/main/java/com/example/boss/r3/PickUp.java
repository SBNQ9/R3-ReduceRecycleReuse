package com.example.boss.r3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;


public class PickUp extends ActionBarActivity {


    EditText editText_apt;
    EditText editText_phone;
    EditText editText_street;
    EditText editText_fname;
    NumberPicker np;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up);


        System.out.println("create()");
        editText_fname = (EditText) findViewById(R.id.editText_fname);
        editText_phone = (EditText) findViewById(R.id.editText_phone);
        editText_street = (EditText) findViewById(R.id.editText_address);
        editText_apt = (EditText) findViewById(R.id.editText_apt);
        np = (NumberPicker) findViewById(R.id.numberPicker);


        np.setMinValue(0);
        np.setMaxValue(10);
        np.setWrapSelectorWheel(false);




        final Context context = this;



        button = (Button) findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, Calender.class);
                Bundle bundle=new Bundle();
                bundle.putString("editText_fname",editText_fname.getText().toString());
                bundle.putString("editText_phone", editText_phone.getText().toString());
                bundle.putString("editText_street", editText_street.getText().toString());
                bundle.putString("editText_apt",editText_apt.getText().toString());
                bundle.putInt("np", np.getValue());
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });


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
