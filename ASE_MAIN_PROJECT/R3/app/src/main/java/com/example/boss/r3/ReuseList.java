package com.example.boss.r3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.boss.r3.helper.DBhelper;

import java.util.ArrayList;
import java.util.List;


public class ReuseList extends android.app.ListActivity {

    private DBhelper DbHelper;
    ArrayList<Employee> imageArry = new ArrayList<Employee>();
    ContactImageAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reuse_list);

        DbHelper = new DBhelper(this);
        DbHelper.open();
        List<Employee> contacts = DbHelper.retriveEmpDetails();

        DbHelper.close();


        for (Employee cn : contacts) {
            String log = "ITEM:" + cn.getCat() + " Name: " + cn.getName()
                    + " ,Image: " + cn.getBm()+",ITEMCOST :"+cn.getItemCost()+",Phone"+cn.getPhnum();

// Writing Contacts to log
            Log.d("Result: ", log);
//add contacts data in arrayList
            imageArry.add(cn);

        }
        adapter = new ContactImageAdapter(this,R.layout.for_reuse_list,imageArry);
        ListView dataList = (ListView) findViewById(R.id.reuseItem);
        dataList.setAdapter(adapter);


    }



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        /*String selectedValue = (String) getListAdapter().getItem(position);
        System.out.println(selectedValue);
        String s[]=selectedValue.split(",");
        Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();

        */

        Intent intent = new Intent(this,LastReuse.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reuse_list, menu);
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
