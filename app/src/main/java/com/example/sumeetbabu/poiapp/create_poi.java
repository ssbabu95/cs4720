package com.example.sumeetbabu.poiapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class create_poi extends Activity {

    Bitmap bTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Create New POI");
        setContentView(R.layout.activity_create_poi);
        //Grab name from previous intent and set POI name field
        Intent intent = getIntent();
        String POIname = intent.getExtras().getString("newPOIname");
        TextView name = (TextView) findViewById(R.id.NameField);
        name.setText(POIname);
        //Set Date field
        TextView dateField = (TextView) findViewById(R.id.DateField);
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        //Date date = new Date();
        dateField.setText(intent.getExtras().getString("date"));
        //Set Location field
        //LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        TextView locationField = (TextView) findViewById(R.id.LocationField);
        locationField.setText(intent.getExtras().getString("location"));
        //Saved picture
        if(savedInstanceState != null) {
                bTemp = savedInstanceState.getParcelable("bitmap");
            if(bTemp != null) {
                ImageView imv = (ImageView) findViewById(R.id.ImageThumb);
                imv.setImageBitmap(bTemp);
            }
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_poi, menu);
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

    public void onTakePic(View view) {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    public void onCreatePOIClick(View view) {
       DbHelper mDbHelper = new DbHelper(getBaseContext());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        TextView name1 = (TextView) findViewById(R.id.NameField);
        String name = name1.getText().toString();
        TextView creator1 = (TextView) findViewById(R.id.CreatorField);
        String creator = creator1.getText().toString();
        //DateFormat format = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
        TextView dt = (TextView) findViewById(R.id.DateField);
        String date = dt.getText().toString();
        TextView location1 = (TextView) findViewById(R.id.LocationField);
        String location = location1.getText().toString();
        Drawable pic = ((ImageView)findViewById(R.id.ImageThumb)).getDrawable();
        if(pic != null) {
            Bitmap photo = ((BitmapDrawable)pic).getBitmap();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, bos);
            byte[] bArray = bos.toByteArray();
            values.put("name", name);
            values.put("creator", creator);
            values.put("location", location);
            values.put("date", date);
            values.put("image", bArray);
            db.insert("POIstorer", null, values);
            db.close();
            Intent tent = new Intent(this, MainActivity.class);
            startActivity(tent);
            finish();
        }
        else {
            Toast.makeText(getBaseContext(), "Please take a photo", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                Bundle extras = data.getExtras();
                bTemp = (Bitmap) extras.get("data");
                Bitmap resized = Bitmap.createScaledBitmap(bTemp, (int)(bTemp.getWidth()*0.2), (int)(bTemp.getHeight()*0.2), true);
                ImageView imv = (ImageView) findViewById(R.id.ImageThumb);
                //android.view.ViewGroup.LayoutParams layoutParams = imv.getLayoutParams();
                //layoutParams.width = 80;
                //layoutParams.height = 80;
                //imv.setLayoutParams(layoutParams);
                imv.setImageBitmap(bTemp);
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle toSave) {
        super.onSaveInstanceState(toSave);
        toSave.putParcelable("bitmap", bTemp);
    }
}
