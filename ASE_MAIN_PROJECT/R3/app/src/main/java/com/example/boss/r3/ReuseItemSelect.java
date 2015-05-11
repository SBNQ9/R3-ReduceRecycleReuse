package com.example.boss.r3;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.boss.r3.helper.DBhelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class ReuseItemSelect extends ActionBarActivity {


    String itemCost="",itemCategory="",userAddress="",userPhone="",userImage="";

    EditText uname,phone,icost;
    Spinner spincategory;
    Button submit;
    Context context;
    ImageView viewImage,imgselect;
    Button b,b2;
    byte[] byteArray;
    String imgbyte="";
    Bitmap thumbnail;
    DBhelper DbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reuse_item_select);



        uname=(EditText)findViewById(R.id.uname);
        icost=(EditText)findViewById(R.id.itemcost);
        spincategory=(Spinner)findViewById(R.id.category);
        viewImage=(ImageView)findViewById(R.id.viewImage);
        spincategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                Toast.makeText(getBaseContext(), spincategory.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        phone=(EditText)findViewById(R.id.phone);
        submit=(Button)findViewById(R.id.submit);


        b=(Button)findViewById(R.id.btnSelectPhoto);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseImage();
            }
        });




        /*if (name.matches("")) {
             Toast.makeText(context,"Enter your name",Toast.LENGTH_LONG).show();
            return;
        }
        */

        /*if (phnumbr.matches("")) {
            Toast.makeText(context,"Enter your phonenumber",Toast.LENGTH_LONG).show();
            return;
        }
        */

        /*if(phnumbr.length()<10){
            Toast.makeText(context,"PhoneNumber is not Valid",Toast.LENGTH_SHORT).show();
        }
        */


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String name = uname.getText().toString();
                    String phnumbr = phone.getText().toString();
                    String cate = spincategory.getSelectedItem().toString();
                    String itemCost = icost.getText().toString();



                    DbHelper = new DBhelper(getBaseContext());
                    Employee employee_One = new Employee(thumbnail,name,phnumbr,cate,itemCost);
                    DbHelper.open();
                    DbHelper.insertEmpDetails(employee_One);
                    DbHelper.close();

                    System.out.println("values inserted");

                    Intent i = new Intent(ReuseItemSelect.this,AfterSignIn.class);
                    i.putExtra("username", name);
//                    Toast.makeText(context,"Details Are Inserted",Toast.LENGTH_SHORT).show();
                    startActivity(i);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }



    private void ChooseImage(){
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(ReuseItemSelect.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    viewImage.setImageBitmap(bitmap);



                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                // The selected image is saved in cursor
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();

                //coloumnIndex stores filepath
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                thumbnail = (BitmapFactory.decodeFile(picturePath));
                //Log.w("path of image from gallery......******************.........", picturePath + "");
               viewImage.setImageBitmap(thumbnail);

               /* ByteArrayOutputStream stream = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteArray = stream.toByteArray();
                System.out.println("came to convert"+byteArray);
*/
                //imgbyte= Base64.encodeToString(byteArray, Base64.DEFAULT);
                // System.out.println(imgbyte+"converted");



            }
        }



    }




    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reuse_item_select, menu);
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
