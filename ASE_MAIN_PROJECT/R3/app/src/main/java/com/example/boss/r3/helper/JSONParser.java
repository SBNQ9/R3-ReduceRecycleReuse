package com.example.boss.r3.helper;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sir on 4/11/2015.
 */
public class JSONParser {

    final String TAG = "JsonParser.java";

    URL url1;
    HttpURLConnection conn;
    BufferedReader rd;
    String line;
    String result = "";

    public JSONObject getJSONFromUrl(String url) {

        // make HTTP request
        try {
            url1 = new URL(url);
            conn = (HttpURLConnection) url1.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result += line;
            }
            rd.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jObj = new JSONObject();
        String json = "{}";
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(result);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // Instantiate a JSON object from the request response

        System.out.println(jObj);
        return jObj;
    }
}
