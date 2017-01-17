package com.project.ms.njord.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
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
    private static final int REQUEST_ENABLE_BT = 2;


   // SensorManager sensorManager;


    // UI references
    private Button scanBtn;

    public DeviceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_device, container, false);

        // sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

     //   }

        // Inflate the layout for this fragment
//        scanBtn =  (Button) v.findViewById(R.id.device_scan_btn);
//        scanBtn.setOnClickListener(this);

        //deviceID = (TextView) v.findViewById(R.id.device);


        if (!getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(getActivity(), "Bluetooth Low Energy not supported on this device", Toast.LENGTH_SHORT).show();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        }


        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == scanBtn){

            if (isEmulator()) {
                Toast.makeText(getActivity(), "Emulator detected. Can't run bluetooth", Toast.LENGTH_SHORT).show();
                return;
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
        // User chose not to enable Bluetooth.
        if (requestCode == REQUEST_ENABLE_BT && resultCode == Activity.RESULT_CANCELED) {
            getActivity().getSupportFragmentManager().beginTransaction().remove(this);
            return;
        }

        if (requestCode == REQUEST_ENABLE_LOCATION && resultCode == Activity.RESULT_CANCELED) {
            getActivity().getSupportFragmentManager().beginTransaction().remove(this);
            return;
        }

        // super.onActivityResult(requestCode, resultCode, data);
    }

}
