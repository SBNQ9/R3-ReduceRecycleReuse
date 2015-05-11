package com.example.boss.r3;

/**
 * Created by sankalp on 5/1/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;


public class ContactImageAdapter extends ArrayAdapter<Employee>{
    Context context;
    int layoutResourceId;
    ArrayList<Employee> data=new ArrayList<Employee>();
    public ContactImageAdapter(Context context, int layoutResourceId, ArrayList<Employee> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ImageHolder holder = null;
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ImageHolder();
            holder.txtTitle = (TextView)row.findViewById(R.id.txtItemCost);
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtItemCost=(TextView)row.findViewById(R.id.txtItemCost);
            row.setTag(holder);
        }
        else
        {
            holder = (ImageHolder)row.getTag();
        }
        Employee picture = data.get(position);
        holder.txtTitle.setText(picture.phnum);
        holder.txtItemCost.setText(picture.itemCost);
 //convert byte to bitmap take from contact class
        Bitmap theImage =picture.bm;
        holder.imgIcon.setImageBitmap(theImage);
        return row;
    }
    static class ImageHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
        TextView txtItemCost;
    }
}
