package com.example.boss.r3.helper; /**
 * Created by sankalp on 5/1/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.boss.r3.Employee;

import java.util.ArrayList;
import java.util.List;

public class DBhelper {
    public static final String EMP_ID = "id";
    public static final String EMP_NAME = "name";
    public static final String EMP_PHNUM = "phnum";
    public static final String EMP_CAT = "cat";
    public static final String EMP_ITEMCOST = "itemcost";
    public static final String EMP_PHOTO = "photo";

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "EmployessDB.db";
    private static final int DATABASE_VERSION = 1;

    private static final String EMPLOYEES_TABLE = "Employees";

    private static final String CREATE_EMPLOYEES_TABLE = "create table "
            + EMPLOYEES_TABLE + " (" + EMP_ID
            + " integer primary key autoincrement, " + EMP_PHOTO
            + " blob not null, " + EMP_NAME + " text not null, "
            + EMP_PHNUM + " text not null, "
            +EMP_CAT + " text not null, "
            +EMP_ITEMCOST + " text not null );";

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_EMPLOYEES_TABLE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + EMPLOYEES_TABLE);
            onCreate(db);
        }
    }

    public void Reset() {
        mDbHelper.onUpgrade(this.mDb, 1, 1);
    }

    public DBhelper(Context ctx) {
        mCtx = ctx;
        mDbHelper = new DatabaseHelper(mCtx);
    }

    public DBhelper open() throws SQLException {
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public void insertEmpDetails(Employee employee) {
        ContentValues cv = new ContentValues();
        cv.put(EMP_PHOTO, Utility.getBytes(employee.getBm()));

        cv.put(EMP_NAME, employee.getName());
        cv.put(EMP_PHNUM, employee.getPhnum());
        cv.put(EMP_CAT,employee.getCat());
        cv.put(EMP_ITEMCOST,employee.getItemCost());
        mDb.insert(EMPLOYEES_TABLE, null, cv);
    }

    public List<Employee> retriveEmpDetails() throws SQLException {
 /*       Cursor cur = mDb.query(true, EMPLOYEES_TABLE, new String[] { EMP_PHOTO,
                EMP_NAME, EMP_PHNUM,EMP_CAT,EMP_ITEMCOST }, null, null, null, null, null, null);*/

        List<Employee> contactList = new ArrayList<Employee>();


        String selectQuery = "SELECT  * FROM " + EMPLOYEES_TABLE;

        //SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = mDb.rawQuery(selectQuery, null);
        if (cur.moveToFirst()) {
            byte[] blob = cur.getBlob(cur.getColumnIndex(EMP_PHOTO));
            String name = cur.getString(cur.getColumnIndex(EMP_NAME));
            String ph = cur.getString(cur.getColumnIndex(EMP_PHNUM));
            String category=cur.getString(cur.getColumnIndex(EMP_CAT));
            String itemCost=cur.getString(cur.getColumnIndex(EMP_ITEMCOST));

            contactList.add(new Employee(Utility.getPhoto(blob), name, ph, category, itemCost));
        }
        cur.close();
        return contactList;
    }
}