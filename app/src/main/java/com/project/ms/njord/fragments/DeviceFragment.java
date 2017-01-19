package com.project.ms.njord.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.project.ms.njord.BLE.ScanDeviceActivity;
import com.project.ms.njord.R;

import static com.project.ms.njord.activities.MainActivity.isEmulator;


public class DeviceFragment extends Fragment implements View.OnClickListener {

    ///permission request
    private static final int REQUEST_LOCATION_PERMISSION = 1;

    //enable requests
    private static final int REQUEST_ENABLE_LOCATION = 1;

    // UI references
    private Button scanBtn;

    public DeviceFragment() {
        // Required empty public constructor
    }


    // for shake event handling

    private SensorManager sensorManager;
    private Sensor sensor;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity
    private long shakeTimestamp = 0;
    private final int PAUSE = 1000;

    // when user shakes device it will change activity and start scanning
    private final SensorEventListener shakeEvent = new SensorEventListener() {

        public void onSensorChanged(SensorEvent se) {
            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta; // perform low-cut filter

            if(mAccel > 15) {
                final long now = System.currentTimeMillis();
                // ignore shake events too close to each other (500ms)

                if (shakeTimestamp + PAUSE > now ) {
                    return;
                }

                if (!getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
                    Toast.makeText(getActivity(), "Bluetooth Low Energy not supported on this device", Toast.LENGTH_SHORT).show();
                }
                else {
                    shakeTimestamp = now;
                    Intent i = new Intent(getActivity(), ScanDeviceActivity.class);
                    startActivity(i);
                }
            }

        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_device, container, false);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(shakeEvent, sensor, sensorManager.SENSOR_DELAY_NORMAL);

        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

        scanBtn =  (Button) v.findViewById(R.id.device_scan_btn);
        scanBtn.setOnClickListener(this);


        return v;
    }


    @Override
    public void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

        sensorManager.registerListener(shakeEvent, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(shakeEvent);
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        if (v == scanBtn){

            if (isEmulator()) {
                Toast.makeText(getActivity(), "Emulator detected. Bluetooth not supported", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
                Toast.makeText(getActivity(), "Bluetooth Low Energy not supported on this device", Toast.LENGTH_SHORT).show();
            }

            else {
                Intent i = new Intent(getActivity(), ScanDeviceActivity.class);
                startActivity(i);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == REQUEST_LOCATION_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent enableLocationIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            this.startActivityForResult(enableLocationIntent, REQUEST_ENABLE_LOCATION);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_ENABLE_LOCATION && resultCode == Activity.RESULT_CANCELED) {
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        }

    }





}
