package com.example.sumeetbabu.poiapp;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.content.*;
import android.widget.Toast;
import android.view.*;
import android.widget.*;
import android.location.*;
import android.app.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.Handler;

public class MainActivity extends Activity {

    //LocationManager locationManager;
    Location location;
    public static Activity fa;
    //RelativeLayout lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fa = this;
        DbHelper handler = new DbHelper(this);
        SQLiteDatabase db = handler.getWritableDatabase();
        Cursor poiCursor = db.rawQuery("SELECT * FROM POIstorer", null);

        ListView lvItems = (ListView) findViewById(R.id.listPOI);
        poiAdapter todoAdapter = new poiAdapter(this, poiCursor, 0);
        lvItems.setAdapter(todoAdapter);
        /*findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // instantiate the location manager, note you will need to request permissions in your manifest
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                // get the last know location from your location manager.
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                // now get the lat/lon from the location and do something with it.
                printGPS(location.getLatitude(), location.getLongitude());
            }
        }); */

        //lay = (RelativeLayout) findViewById(R.id.rellay);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onNewPOIClick(View v) {
        EditText q = (EditText) findViewById(R.id.createPOIName);
        String newPOIname = q.getText().toString();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        } catch(final SecurityException ex) {
            Toast.makeText(getBaseContext(), "Sorry, application does not have permissions to get the location.", Toast.LENGTH_SHORT).show();
        }
        if(newPOIname.equals("")) {
            Toast.makeText(getBaseContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i = new Intent(this, create_poi.class);
            i.putExtra("newPOIname", newPOIname);
            i.putExtra("date", dateFormat.format(date));
            i.putExtra("location", location.getLatitude() + ", " + location.getLongitude());
            startActivity(i);
            //finish();
            // Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }



}
