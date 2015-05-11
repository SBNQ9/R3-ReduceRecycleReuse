package com.example.boss.r3;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.boss.r3.helper.JSONParser;

import org.json.JSONObject;


public class ServiceProvider extends ActionBarActivity {

    EditText empId;
    Button btnLogin;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider);


        context = this;
        empId = (EditText) findViewById(R.id.editText);
        btnLogin = (Button) findViewById(R.id.button);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String username = empId.getText().toString();


                // Execute the AsyncLogin class
                new AsyncLogin().execute(username);

            }
        });
    }

    protected class AsyncLogin extends AsyncTask<String, JSONObject, String> {

        String serviceProviderName = null;

        @Override
        protected String doInBackground(String... params) {

            //RestAPI api = new RestAPI();
            String userAuth = "";
            try {

                String url = "http://kc-sce-cs551.kc.umkc.edu/aspnet_client/Group8/Recycle/Recycle/Service1.svc/authenticate/";
                // Creating JSON Parser instance
                JSONParser jParser = new JSONParser();
                System.out.println(url);

                url = url + params[0];

                System.out.println(url);
                // getting JSON string from URL
                JSONObject json = jParser.getJSONFromUrl(url);
                // Call the User Authentication Method in API
                if (json != null) {
                    serviceProviderName = json.getString("NameEmp");
                    System.out.println("Name" + serviceProviderName);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncLogin", e.getMessage());

            }

            System.out.println("Name" + serviceProviderName);

            if (serviceProviderName != null)
                return serviceProviderName;
            else
                return null;
        }


        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            Toast.makeText(context, "Please Wait...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub


            System.out.println("CAME INTO POST EXECUTE" + result + "result");

            //Check user validity
            if (result != null) {
                Intent i = new Intent(ServiceProvider.this,
                        Collect.class);
                i.putExtra("username", serviceProviderName);
                startActivity(i);
            } else {
                Toast.makeText(context, "Not valid username/password ", Toast.LENGTH_SHORT).show();
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
        getMenuInflater().inflate(R.menu.menu_service, menu);
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
