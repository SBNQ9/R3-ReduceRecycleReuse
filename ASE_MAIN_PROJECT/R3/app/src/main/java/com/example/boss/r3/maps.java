package com.example.boss.r3;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import android.content.Intent;


public class maps extends Activity {

    ListView list;
    TextView name;
    Button Btngetdata;
    ArrayList<HashMap<String, String>> oslist = new ArrayList<HashMap<String, String>>();
//    EditText zip;

    public String latitude;
    public String longitude;
    //URL to get JSON Array
    //JSON Node Names
    private static final String TAG_NAME = "name";
    private static final String TAG_R = "results";
    private static final String TAG_A = "address";
    private static final String TAG_L = "locations";
    private static final String TAG_LAT = "lat_long";
    public static final String TAG_LT = "latitude";
    public static final String TAG_LO = "longitude";

    public String lat_fi;
    public String lng_fi;


    //public String zip;
    JSONArray android = null;
    JSONArray loctaion = null;
    JSONArray lat = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        oslist = new ArrayList<HashMap<String, String>>();
        Btngetdata = (Button) findViewById(R.id.getdata);
        Btngetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JSONParse().execute();
            }

        });

    }

    public class JSONParse extends AsyncTask<String, String, JSONObject> {
        public ProgressDialog pDialog;

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            name = (TextView) findViewById(R.id.name);
            //adrs = (TextView)findViewById(R.id.adrs);
            pDialog = new ProgressDialog(maps.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        EditText zipcode = (EditText) findViewById(R.id.zipcode);
        String zip = zipcode.getText().toString();
        public String url = "https://proapi.whitepages.com/2.1/business.json?api_key=358ffc0ef5c4916d45f5f0c9edf61469&postal_code=" + zip + "&name=Recycling+Centers";

        public JSONObject doInBackground(String... args) {

            parser jParser = new parser();
            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(url);
            return json;
        }


        @Override
        public void onPostExecute(JSONObject json) {

            pDialog.dismiss();
            try {
                // Getting JSON Array from URL
                android = json.getJSONArray(TAG_R);
                for (int i = 0; i < android.length(); i++) {
                    JSONObject c = android.getJSONObject(i);
                    // Storing  JSON item in a Variable
                    loctaion = c.getJSONArray(TAG_L);
                    // Adding value HashMap key => value
                    for (int j = 0; j < loctaion.length(); j++) {
                        JSONObject l = loctaion.getJSONObject(j);
                        String address = l.getString(TAG_A);
                        latitude = l.getJSONObject(TAG_LAT).getString(TAG_LT);
                        longitude = l.getJSONObject(TAG_LAT).getString(TAG_LO);

                        HashMap<String, String> map = new HashMap<String, String>();
//                        map.put(TAG_NAME, name);
                        map.put(TAG_A, address);
                        map.put(TAG_LT, latitude);
                        map.put(TAG_LO, longitude);
                        oslist.add(map);
                        list = (ListView) findViewById(R.id.list);
                        ListAdapter adapter = new SimpleAdapter(maps.this, oslist,
                                R.layout.activity_parser,
                                new String[]{TAG_A, TAG_LT, TAG_LO}, new int[]{
                                R.id.name, R.id.lat, R.id.lng});
//                    ListAdapter adapter = new SimpleAdapter(MainActivity.this, oslist,
//                            R.layout.activity_parser,
//                            new String[] { TAG_NAME, TAG_A }, new int[] {
//                            R.id.name, R.id.adrs});
                        list.setAdapter(adapter);
                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(maps.this, "You Clicked at " + oslist.get(+position).get("address")
                                    +oslist.get(+position).get("latitude"), Toast.LENGTH_SHORT).show();
                                lat_fi= oslist.get(+position).get("latitude");
                                lng_fi= oslist.get(+position).get("longitude");
                                Intent intent=new Intent(maps.this,MyActivity.class);
                                intent.putExtra("lat_fi", lat_fi);
                                intent.putExtra("lng_fi", lng_fi);
                                startActivity(intent);
                             }

                        });
                    }

                }

            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}