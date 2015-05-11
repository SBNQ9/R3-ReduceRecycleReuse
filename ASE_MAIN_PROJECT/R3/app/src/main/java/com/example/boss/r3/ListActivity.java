package com.example.boss.r3;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.boss.r3.helper.GetContactsAsyncTask;
import com.example.boss.r3.helper.MyContact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;


public class ListActivity extends android.app.ListActivity {


    ArrayList<MyContact> returnValues = new ArrayList<MyContact>();
    ArrayList<String> listItems = new ArrayList<String>();
    // String valueTOUpdate_id;
    String valueTOUpdate_fname;
    String valueTOUpdate_street;
    String valueTOUpdate_apt;
    String valueTOUpdate_np;
    String valueTOUpdate_phone;
    double[] doubles;
    double fromLat;
    double fromLong;
    HashMap<Float, String> hmap = new HashMap<Float, String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);
        //Get your cloud contacts
        GetContactsAsyncTask task = new GetContactsAsyncTask();
        try {
            returnValues = task.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Bundle bundle = getIntent().getExtras();
        //fromLat = bundle.getDouble("fromLat");
        //fromLong = bundle.getDouble("fromLong");

        fromLat=39.0302159;
        fromLong=-94.5657865;

        for (MyContact x : returnValues) {

            //listItems.add(x.getStreet() + "," + x.getApt() + "," + x.getNp());

            String value=x.getStreet() + "," + x.getApt() + "," + x.getNp();

            String address = x.getStreet();

            System.out.println(x.getStreet());
            doubles=getLatLongFromGivenAddress(address);
            System.out.println(doubles);
            float distance;


            System.out.println(x);

            Location locationA = new Location("point A");

            locationA.setLatitude(39.0302159);

            locationA.setLongitude(-94.5759685);

            Location locationB = new Location("point B");

            locationB.setLatitude(doubles[0]);

            locationB.setLongitude(doubles[1]);

            distance = (locationA.distanceTo(locationB))*0.000621371f;

            System.out.println(distance);

            hmap.put(new Float(distance), value);


        }

        Map<Float, String> map = new TreeMap<Float, String>(hmap);
        System.out.println("After Sorting:");

        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry me = (Map.Entry)iterator.next();
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());


            listItems.add(me.getValue().toString()+"        "+me.getKey()+"miles");

        }


        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listItems));


        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listItems));


    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String selectedValue = (String) getListAdapter().getItem(position);
        System.out.println(selectedValue);
        String s[]=selectedValue.split(",");
        Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
        selectedContact(s[0]);

        Bundle dataBundle = new Bundle();
        dataBundle.putString("street", valueTOUpdate_street);
        dataBundle.putString("apt", valueTOUpdate_apt);
        System.out.println(valueTOUpdate_phone);
        dataBundle.putString("phone", valueTOUpdate_phone);

        dataBundle.putDouble("fromLat", fromLat);
        dataBundle.putDouble("fromLong",fromLong);
        dataBundle.putDoubleArray("destination",doubles);




        Intent intent = new Intent(this,PostList.class);
        intent.putExtras(dataBundle);
        startActivity(intent);
    }

    /*
     * Retrieves the full details of a selected contact.
     * The details are then passed onto the Update Contacts Record.
     *
     * This is a quick way for demo purposes.
     * You should consider storing this data in a database, shared preferences or text file
     */
    public void selectedContact(String selectedValue){
        for(MyContact x: returnValues){
            if(selectedValue.contains(x.getStreet())){
                //valueTOUpdate_id = x.getDoc_id();
                valueTOUpdate_fname = x.getFirst_name();
                valueTOUpdate_apt = x.getApt();
                valueTOUpdate_phone = x.getPhone();
                valueTOUpdate_street = x.getStreet();
            }
        }

    }


    public  double[] getLatLongFromGivenAddress(String address)
    {
        double lat= 0.0, lng= 0.0;
        double[] add=new double[2];

        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
        try
        {
            List<Address> addresses = geoCoder.getFromLocationName(address , 1);
            if (addresses.size() > 0)
            {

                lat=  addresses.get(0).getLatitude();
                lng= addresses.get(0).getLongitude();

                System.out.println(lat+":"+lng);
                add[0]=lat;
                add[1]=lng;

                Log.d("Latitude", "" + lat);
                Log.d("Longitude", ""+lng);

                return add;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }











    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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
