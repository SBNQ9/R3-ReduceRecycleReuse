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

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUp extends ActionBarActivity {

    String usersignupname="",usersignemail="",usersignpsswrd="";
    EditText username,useremail,userpasswrd;
    Button usersign;
    String uname="",uemail="",upasswrd="";
    //Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);




            useremail=(EditText)findViewById(R.id.etEmail);
            username=(EditText)findViewById(R.id.etUserName);
            userpasswrd=(EditText)findViewById(R.id.etPass);
            usersign=(Button)findViewById(R.id.btnSingIn);
            usersign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uemail=useremail.getText().toString();

                    if(!isEmailValid(uemail))
                    {
                        Toast.makeText(getApplicationContext(), "Enter valid email", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        upasswrd=userpasswrd.getText().toString();
                        if(!checkPasswordLength(upasswrd))
                        {
                            Toast.makeText(getApplicationContext(), "Password must be at least 8 characters", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            uname=username.getText().toString();
                            // all fields valid - add logic to invoke service call
                            new AsyncInsertSignup().execute(uname,upasswrd,uemail);


                        }
                    }
                }
            });
        }

        protected class AsyncInsertSignup extends AsyncTask<String, JSONObject, String> {

//        String signuptest="";

            @Override
            protected String doInBackground(String... params) {

                try {

                    System.out.print("came:");
                    String url = "http://kc-sce-cs551.kc.umkc.edu/aspnet_client/Group8/SignUp_In/SignUp_In/Service1.svc/insertInfo/";
                    JSONParser jParser = new JSONParser();
                    url =url + params[0] +"/"+ params[1]+"/"+params[2];

                    System.out.println(url);
                    JSONObject json = jParser.getJSONFromUrl(url);
                    // Call the User Authentication Method in API
                    if(json!=null) {

                        usersignupname=json.getString("Name");
                        usersignemail=json.getString("userEmail");
                        usersignpsswrd=json.getString("Pwd");
                        System.out.println(usersignupname);
                    }
                }
                catch (Exception e){
                    Log.d("Not Inserted", e.getMessage());
                }

                if (usersignupname!=null)
                    return usersignupname;

                else
                    return null;
            }

            @Override
            protected void onPreExecute() {

                super.onPreExecute();

                // Toast.makeText(context, "Please Wait...", Toast.LENGTH_SHORT).show();
            }

            protected void onPostExecute(String result){

                System.out.println("CAME INTO POST EXECUTE" + result + "result");

                //Check user validity
                if (result!=null) {
                    System.out.print("Sankalp data inserted hurrehhhh!!!!!!!!!!!!");
                    Toast.makeText(getApplicationContext(),"Your Successfully SignedUp you are Navigating to SignIn Page",Toast.LENGTH_SHORT).show();
                    Intent i3 = new Intent(SignUp.this,
                            SignIn.class);
                    i3.putExtra("username", usersignupname);
                    // Toast.makeText(context,"Details Are Inserted",Toast.LENGTH_SHORT).show();
                    startActivity(i3);
                } else {
                    //Toast.makeText(context, "Not valid username/password ", Toast.LENGTH_SHORT).show();
                }



          }

        }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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

    public boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public boolean checkPasswordLength(String pwd)
    {
        return pwd.length() >= 8;
    }


}
