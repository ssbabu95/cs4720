package com.example.sumeetbabu.poiapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.content.*;
import android.widget.Toast;
import android.view.*;
import android.widget.*;
import android.location.*;
import android.app.*;

public class MainActivity extends Activity {

    LocationManager locationManager;
    LocationListener locationListener;
    //RelativeLayout lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper handler = new DbHelper(this);
// Get access to the underlying writeable database
        SQLiteDatabase db = handler.getWritableDatabase();
// Query for items from the database and get a cursor back
        Cursor poiCursor = db.rawQuery("SELECT * FROM POIstorer", null);

        // Find ListView to populate
        ListView lvItems = (ListView) findViewById(R.id.listPOI);
// Setup cursor adapter using cursor from last step
        poiAdapter todoAdapter = new poiAdapter(this, poiCursor, 0);
// Attach cursor adapter to the ListView
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
        if(newPOIname.equals("")) {
            Toast.makeText(getBaseContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i = new Intent(this, create_poi.class);
            i.putExtra("newPOIname", newPOIname);
            startActivity(i);
            // Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    /*public void printGPS(double lat, double lon) {
        Log.i("GPS Coords", (lat + "   " + lon));
        TextView GPSText = (TextView)findViewById(R.id.GPSCoords);
        GPSText.setText("Latitude: " + lat + "   Longitude:" + lon);


    }*/



}
