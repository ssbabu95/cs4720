package com.example.sumeetbabu.poiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.*;
import android.widget.Toast;
import android.view.View;
import android.widget.*;
import android.content.DialogInterface.*;
import android.location.*;
import android.widget.AbsListView.*;
import android.view.*;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    LocationListener locationListener;
    //RelativeLayout lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // instantiate the location manager, note you will need to request permissions in your manifest
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                // get the last know location from your location manager.
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                // now get the lat/lon from the location and do something with it.
                printGPS(location.getLatitude(), location.getLongitude());
            }
        });

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

    public void onNewClick(View v) {
        EditText q = (EditText) findViewById(R.id.editText);
        String newPOIname = q.getText().toString();
        Intent i = new Intent(this, create_poi.class);
        i.putExtra("newPOIname", newPOIname);
        startActivity(i);
        // Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT).show();
    }

    public void printGPS(double lat, double lon) {
        Log.i("GPS Coords", (lat + "   " + lon));
        TextView GPSText = (TextView)findViewById(R.id.GPSCoords);
        GPSText.setText("Latitude: " + lat + "   Longitude:" + lon);


    }



}
