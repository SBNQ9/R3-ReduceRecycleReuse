package com.example.boss.r3;

import android.graphics.Bitmap;

/**
 * Created by sankalp on 5/1/2015.
 */
public class Employee {

    String phnum;
    String cat;

    public String getItemCost() {
        return itemCost;
    }

    public void setItemCost(String itemCost) {
        this.itemCost = itemCost;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getPhnum() {
        return phnum;
    }

    public void setPhnum(String phnum) {
        this.phnum = phnum;
    }

    String itemCost;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getBm() {
        return bm;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    String name;
    Bitmap bm;
    public Employee(Bitmap bm, String name,String phnum,String cat,String itemCost)
    {
         setCat(cat);
        setBm(bm);
        setName(name);
        setItemCost(itemCost);
        setPhnum(phnum);

    }

}
