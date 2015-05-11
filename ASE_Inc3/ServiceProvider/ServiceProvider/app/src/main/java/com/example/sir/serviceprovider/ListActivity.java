package com.example.sir.serviceprovider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
//import android.app.ListActivity;

import com.example.sir.serviceprovider.helper.GetContactsAsyncTask;
import com.example.sir.serviceprovider.helper.MyContact;

import java.util.ArrayList;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Get your cloud contacts
        GetContactsAsyncTask task = new GetContactsAsyncTask();
        try {
            returnValues = task.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for(MyContact x: returnValues){

            listItems.add(x.getStreet()+"/n"+x.getApt()+"/n"+x.getNp());
        }



        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listItems));


    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String selectedValue = (String) getListAdapter().getItem(position);
        Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
       selectedContact(selectedValue);

        Bundle dataBundle = new Bundle();
        dataBundle.putString("street", valueTOUpdate_street);
        dataBundle.putString("apt", valueTOUpdate_apt);

        dataBundle.putString("phone", valueTOUpdate_phone);

        Intent intent = new Intent(this,PickUpActivity.class);
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
            if(selectedValue.contains(x.getFirst_name())){
                //valueTOUpdate_id = x.getDoc_id();
                valueTOUpdate_fname = x.getFirst_name();
                valueTOUpdate_apt = x.getApt();
                valueTOUpdate_phone = x.getPhone();
                valueTOUpdate_street = x.getStreet();
            }
        }

    }


}
