package com.example.srq5d.loginreuse;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

public class MainActivity extends ActionBarActivity {
    String itemCost="",itemCategory="",userAddress="",userPhone="",userImage="";

    EditText uname,phone,icost;
    Spinner spincategory;
    Button submit;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname=(EditText)findViewById(R.id.uname);
        icost=(EditText)findViewById(R.id.itemcost);
        spincategory=(Spinner)findViewById(R.id.category);
        spincategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
               Toast.makeText(getBaseContext(), spincategory.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        phone=(EditText)findViewById(R.id.phone);
        submit=(Button)findViewById(R.id.submit);


        /*if (name.matches("")) {
             Toast.makeText(context,"Enter your name",Toast.LENGTH_LONG).show();
            return;
        }
        */

        /*if (phnumbr.matches("")) {
            Toast.makeText(context,"Enter your phonenumber",Toast.LENGTH_LONG).show();
            return;
        }
        */

        /*if(phnumbr.length()<10){
            Toast.makeText(context,"PhoneNumber is not Valid",Toast.LENGTH_SHORT).show();
        }
        */


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=uname.getText().toString();
                String phnumbr=phone.getText().toString();
                String cate=spincategory.getSelectedItem().toString();
                String itemcost=icost.getText().toString();
                String imageval=icost.getText().toString();

                new AsyncInsert().execute(name,cate,itemcost,phnumbr,imageval);


            }
        });
    }

    protected class AsyncInsert extends AsyncTask<String, JSONObject, String> {

        String userName="";


        @Override
        protected String doInBackground(String... params) {
            String userAuth = "";
            try {

                String url = "http://kc-sce-cs551.kc.umkc.edu/aspnet_client/Group8/Reuse/Reuse/Service1.svc/insertInfo/";
                // Creating JSON Parser instance
                // Call the User Authentication Method in API
                JSONParser jParser = new JSONParser();
                url =url + params[0] +"/"+ params[1]+"/" + params[2] +"/"+params[3]+"/"+params[4];

                System.out.println(url);
                JSONObject json = jParser.getJSONFromUrl(url);
                // Call the User Authentication Method in API
                if(json!=null) {
                    itemCost=json.getString("Cost");
                    userImage=json.getString("Image");
                    itemCategory = json.getString("Item");
                    userName = json.getString("Name");
                    userPhone= json.getString("PhoneNumber");


                    //System.out.println("Name" + serviceProviderName);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncLogin", e.getMessage());

            }

           // System.out.println("Name" + serviceProviderName);

            if (userName != null)
                return userName;
            else
                return null;
        }


        @Override
        protected void onPreExecute() {

            super.onPreExecute();

//            Toast.makeText(context, "Please Wait...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub




            System.out.println("CAME INTO POST EXECUTE" + result + "result");

            //Check user validity
            if (result!=null) {
                Intent i = new Intent(MainActivity.this,
                        ReuseActivity.class);
                i.putExtra("username", userName);
               // Toast.makeText(context,"Details Are Inserted",Toast.LENGTH_SHORT).show();
                startActivity(i);
            } else {
                //Toast.makeText(context, "Not valid username/password ", Toast.LENGTH_SHORT).show();
            }

        }

    }
    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:

                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
