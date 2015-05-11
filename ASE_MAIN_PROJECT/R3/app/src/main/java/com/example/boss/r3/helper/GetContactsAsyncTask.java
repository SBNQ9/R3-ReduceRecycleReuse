package com.example.boss.r3.helper;

import android.os.AsyncTask;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
//import com.wordpress.michaelkyazze.codeperspective102.MyContact;

/**
 * Async Task to retrieve your stored contacts from mongolab
 * @author KYAZZE MICHAEL
 *
 */
public class GetContactsAsyncTask extends AsyncTask<MyContact, Void, ArrayList<MyContact>> {
	static BasicDBObject user = null;
	static String OriginalObject = "";
	static String server_output = null;
	static String temp_output = null;

	@Override
	protected ArrayList<MyContact> doInBackground(MyContact... arg0) {



		ArrayList<MyContact> mycontacts = new ArrayList<MyContact>();
        ArrayList<String> al=new ArrayList<String>();
        HashMap<String,String> hmap=new HashMap<String,String>();

		try
		{

			QueryBuilder qb = new QueryBuilder();
	        URL url = new URL(qb.buildContactsGetURL());
	        HttpURLConnection conn = (HttpURLConnection) url
					.openConnection();
	        conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			while ((temp_output = br.readLine()) != null) {
				server_output = temp_output;
			}

            // create a basic db list
			String mongoarray = "{ artificial_basicdb_list: "+server_output+"}";
	//		System.out.println(mongoarray);
            Object o = com.mongodb.util.JSON.parse(mongoarray);
     //       System.out.println(o);

			DBObject dbObj = (DBObject) o;
			BasicDBList contacts = (BasicDBList) dbObj.get("artificial_basicdb_list");

      //      System.out.println(contacts);
       int count=0;


		  for (Object obj : contacts) {
			DBObject userObj = (DBObject) obj;

              System.out.println(userObj);



              Date date1=new Date();

              SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");

              String doc=userObj.get("document").toString();


              String s[]=doc.split(",");
              for(int i=0;i<s.length;i++) {
                  String s1[] = s[i].split(":",2);
                  al.add(s1[1].substring(2,(s1[1].length()-2)));
                  System.out.println(s1[1]);
              }
              System.out.println(al);

              MyContact temp = new MyContact();

              if (date1.toString().compareTo(al.get(5)) == 0) {
                  System.out.println("start is equal to end");
              }
              System.out.println("before"+temp.getFirst_name());
                  temp.setFirst_name(al.get(0+count));
              System.out.println("after"+temp.getFirst_name());

              System.out.println("before"+temp.getStreet());
                  temp.setStreet(al.get(1+count));
              System.out.println("after"+temp.getStreet());


              System.out.println("before"+temp.getApt());
                  temp.setApt(al.get(2+count));
              System.out.println("after"+temp.getApt());

              System.out.println("before"+temp.getNp());
                  temp.setNp(Integer.parseInt(al.get(3+count)));
              System.out.println("after"+temp.getNp());

              System.out.println("before"+temp.getDate());
                  temp.setDate(al.get(5+count));
              System.out.println("after"+temp.getDate());

              System.out.println("before"+temp.getPhone());
              temp.setPhone(al.get(4+count));
              System.out.println("after"+temp.getPhone());

              //System.out.println(al);
               System.out.println(temp);
                  mycontacts.add(temp);
              //System.out.println(mycontacts);
              count+=6;
			}

		}catch (Exception e) {
			e.getMessage();
		}


		return mycontacts;
	}
}
