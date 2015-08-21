package qwerty.cadence;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.location.Location;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class cadence extends Activity  implements SensorListener {
    SensorManager sm = null;
    TextView yViewO = null;
    private TextView speedText;
    private TextView cadenceText;
    private TextView frontGearIndicator;
    private TextView rearGearIndicator;

    private int circuit = 0;
    private ArrayList<Integer> rearStar = new ArrayList<>();
    private ArrayList<Integer> frontStar = new ArrayList<>();
    private int fGear = 0;
    private int rGear = 0;
    private int slope=0;
    private int zeroSlope=0;
    private int a=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadence);
        yViewO = (TextView) findViewById(R.id.slope);
        speedText = (TextView) findViewById(R.id.speedVal);
        cadenceText = (TextView) findViewById(R.id.rearStarInputFirst);
        frontGearIndicator = (TextView) findViewById(R.id.FrontGearIndicator);
        rearGearIndicator = (TextView) findViewById(R.id.RearGearIndicator);

        speedText.setTypeface(Typeface.createFromAsset(getAssets(),"Electron.ttf"));
        cadenceText.setTypeface(Typeface.createFromAsset(getAssets(),"Electron.ttf"));  //set fonts

        final String pref = getIntent().getStringExtra("pref"); //get data from settings
        SharedPreferences mSettings = getSharedPreferences(pref, Context.MODE_PRIVATE);

        // Getting data from settings file and add to rearStar ArrayList
        if (mSettings.contains(WheelSettings.APP_FIRST_REAR_STAR_PREFERENCES)) {
            rearStar.add(Integer.valueOf(mSettings.getString(WheelSettings.APP_FIRST_REAR_STAR_PREFERENCES, "16")));
        }
        if (mSettings.contains(WheelSettings.APP_SECOND_REAR_STAR_PREFERENCES)) {
            rearStar.add(Integer.valueOf(mSettings.getString(WheelSettings.APP_SECOND_REAR_STAR_PREFERENCES, "0")));
        }
        if (mSettings.contains(WheelSettings.APP_THIRD_REAR_STAR_PREFERENCES)) {
            rearStar.add(Integer.valueOf(mSettings.getString(WheelSettings.APP_THIRD_REAR_STAR_PREFERENCES, "0")));
        }
        if (mSettings.contains(WheelSettings.APP_FORTH_REAR_STAR_PREFERENCES)) {
            rearStar.add(Integer.valueOf(mSettings.getString(WheelSettings.APP_FORTH_REAR_STAR_PREFERENCES, "0")));
        }
        if (mSettings.contains(WheelSettings.APP_FIFTH_REAR_STAR_PREFERENCES)) {
            rearStar.add(Integer.valueOf(mSettings.getString(WheelSettings.APP_FIFTH_REAR_STAR_PREFERENCES, "0")));
        }
        if (mSettings.contains(WheelSettings.APP_SIXTH_REAR_STAR_PREFERENCES)) {
            rearStar.add(Integer.valueOf(mSettings.getString(WheelSettings.APP_SIXTH_REAR_STAR_PREFERENCES, "0")));
        }
        if (mSettings.contains(WheelSettings.APP_SEVENTH_REAR_STAR_PREFERENCES)) {
            rearStar.add(Integer.valueOf(mSettings.getString(WheelSettings.APP_SEVENTH_REAR_STAR_PREFERENCES, "0")));
        }
        if (mSettings.contains(WheelSettings.APP_EIGHTH_REAR_STAR_PREFERENCES)) {
            rearStar.add(Integer.valueOf(mSettings.getString(WheelSettings.APP_EIGHTH_REAR_STAR_PREFERENCES, "0")));
        }
        if (mSettings.contains(WheelSettings.APP_NINETH_REAR_STAR_PREFERENCES)) {
            rearStar.add(Integer.valueOf(mSettings.getString(WheelSettings.APP_NINETH_REAR_STAR_PREFERENCES, "0")));
        }
        if (mSettings.contains(WheelSettings.APP_TENTH_REAR_STAR_PREFERENCES)) {
            rearStar.add(Integer.valueOf(mSettings.getString(WheelSettings.APP_TENTH_REAR_STAR_PREFERENCES, "0")));
        }

        if (mSettings.contains(WheelSettings.APP_FIRST_FRONT_STAR_PREFERENCES)) {
            frontStar.add(Integer.valueOf(mSettings.getString(WheelSettings.APP_FIRST_FRONT_STAR_PREFERENCES, "56")));
        }
        if (mSettings.contains(WheelSettings.APP_SECOND_FRONT_STAR_PREFERENCES)) {
            frontStar.add(Integer.valueOf(mSettings.getString(WheelSettings.APP_SECOND_FRONT_STAR_PREFERENCES, "0")));
        }
        if (mSettings.contains(WheelSettings.APP_THIRD_FRONT_STAR_PREFERENCES)) {
            frontStar.add(Integer.valueOf(mSettings.getString(WheelSettings.APP_THIRD_FRONT_STAR_PREFERENCES, "0")));
        }
        if (mSettings.contains(WheelSettings.APP_CIRCUIT_PREFERENCES)) {
            circuit = (mSettings.getInt(WheelSettings.APP_CIRCUIT_PREFERENCES, 215));
        }
        for (int i=frontStar.size()-1; i>0; i--){
            if (frontStar.get(i) ==0){
                frontStar.remove(i);
                }
        }
        for (int i=rearStar.size()-1; i>0; i--){
            if (rearStar.get(i) ==0){
                rearStar.remove(i);
            }
        }
       reload();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_test_screen3, menu);
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

    public void addFrontGear(View view) {
        if (Integer.valueOf(String.valueOf(frontGearIndicator.getText())) < frontStar.size()) {
            fGear=Integer.valueOf(String.valueOf(frontGearIndicator.getText()));
            frontGearIndicator.setText(String.valueOf(1 + Integer.valueOf(String.valueOf(frontGearIndicator.getText()))));
            reload();
            }
    }

    public void addRearGear(View view) {
        if (Integer.valueOf(String.valueOf(rearGearIndicator.getText())) < rearStar.size()) {
            rGear = Integer.valueOf(String.valueOf(rearGearIndicator.getText()));
            rearGearIndicator.setText(String.valueOf(1 + Integer.valueOf(String.valueOf(rearGearIndicator.getText()))));
      reload();
        }
    }

    public void subFrontGear(View view) {
        if (Integer.valueOf(String.valueOf(frontGearIndicator.getText())) > 1) {
            frontGearIndicator.setText(String.valueOf(Integer.valueOf(String.valueOf(frontGearIndicator.getText()))-1));
            fGear=Integer.valueOf(String.valueOf(frontGearIndicator.getText()))-1;
            reload();
        }
    }
    //qq

    public void subRearGear(View view) {
        if (Integer.valueOf(String.valueOf(rearGearIndicator.getText())) > 1) {
            rearGearIndicator.setText(String.valueOf( Integer.valueOf(String.valueOf(rearGearIndicator.getText()))-1));
            rGear=Integer.valueOf(String.valueOf(rearGearIndicator.getText()))-1;
            reload();
        }
    }

    private void reload(){          //Reload page and calculate cadence & speed
        LocationManager locationManager = (LocationManager)
                this.getSystemService(Context.LOCATION_SERVICE);
        // Define a listener that responds to location updates
        LocationListener locationListener;
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                speedText.setText(String.valueOf((int) (location.getSpeed() / 5 * 18))); //Calculate speed
                cadenceText.setText(String.valueOf((int) (location.getSpeed() * 6000 /
                        (circuit * frontStar.get(fGear) / rearStar.get(rGear)))));       //Calculate cadence

                if ((int) (location.getSpeed() * 6000 /                                 // if cadence too high set color red
                        (circuit * frontStar.get(fGear) / rearStar.get(rGear)))>130){
                    cadenceText.setTextColor(Color.RED);
                }
                else cadenceText.setTextColor(Color.GREEN);
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
                0, locationListener); //start gps
      }
    public void onSensorChanged(int sensor, float[] values) {
        synchronized (this) {
               if ((slope+zeroSlope)>0){
                   yViewO.setTextColor(Color.RED);
               }
            else yViewO.setTextColor(Color.GREEN);
            slope = (int)values[1];
                yViewO.setText(" " +Math.abs(slope+zeroSlope)+ "'");

        }
    }

    public void onAccuracyChanged(int sensor, int accuracy) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this,
                SensorManager.SENSOR_ORIENTATION ,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        sm.unregisterListener(this);
        super.onStop();
    }

    public void resetSlope(View view) {
        zeroSlope = slope*-1;

    }
}