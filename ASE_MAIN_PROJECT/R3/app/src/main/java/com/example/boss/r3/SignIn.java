package com.example.boss.r3;

import android.content.Intent;
import android.os.AsyncTask;
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


public class SignIn extends ActionBarActivity {



    Button signin;
    EditText signusername,signuserpsswrd;

    String userSignperu="",userSignpswd="",signinstaus="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);




        signusername=(EditText)findViewById(R.id.etUserName);
        signuserpsswrd=(EditText)findViewById(R.id.etPass);
        signin=(Button)findViewById(R.id.btnSingIn);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignperu=signusername.getText().toString();
                userSignpswd=signuserpsswrd.getText().toString();

                new AsyncCheckSignin().execute(userSignperu,userSignpswd);
            }
        });


    }

    protected class AsyncCheckSignin extends AsyncTask<String, JSONObject, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            Boolean status = false;
            try {

                String url = "http://kc-sce-cs551.kc.umkc.edu/aspnet_client/Group8/SignUp_In/SignUp_In/Service1.svc/checkDetails/";
                JSONParser jParser = new JSONParser();
                url =url + params[0] +"/"+ params[1];

                System.out.println(url);
                JSONObject json = jParser.getJSONFromUrl(url);
                // Call the User Authentication Method in API
                if(json!=null) {

                    status=json.getBoolean("Value");
                    //System.out.println("Name" + serviceProviderName);

                }

            }
            catch (Exception e){
                Log.d("Login Details Are Wrong", e.getMessage());
            }


            return status;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            // Toast.makeText(context, "Please Wait...", Toast.LENGTH_SHORT).show();
        }

        protected void onPostExecute( Boolean status){

            System.out.print("Value status recorded sankalp"+status);
            if(status){
                System.out.print("Values are correct");
                Toast.makeText(getApplicationContext(), "SignIn Successful", Toast.LENGTH_SHORT).show();
                Intent i4 = new Intent(SignIn.this,
                        AfterSignIn.class);
                startActivity(i4);
            }
            else {
                Toast.makeText(getApplicationContext(),"Details are Invalid",Toast.LENGTH_SHORT).show();
            }
        }
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_in, menu);
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
