/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.project.ms.njord.BLE;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.ms.njord.R;

import java.util.ArrayList;
import java.util.List;

public class ScanDeviceActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private LeDeviceListAdapter mLeDeviceListAdapter;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeScanner mBluetoothLeScanner;
    private ScanFilter uuidFilter;
    private List<ScanFilter> mScanFilters = new ArrayList<ScanFilter>();
    private ScanSettings mScanSettings;
    private boolean mScanning;
    private Handler mHandler;

    ListView listView;

   /* ///permission request
    private static final int REQUEST_LOCATION_PERMISSION = 1;

    //enable requests
    private static final int REQUEST_ENABLE_LOCATION = 1;

    */

    private static final int REQUEST_ENABLE_BT = 2;


    // Stops scanning after 10 seconds.
    private static final long SCAN_PERIOD = 10000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_device);

        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(this);

        mHandler = new Handler();

/*
        *** MOVED to device fragment.  But keeping the code here, to make sure we dont forget it
            in case of refactoring

        // Use this check to determine whether BLE is supported on the device.  Then you can
        // selectively disable BLE-related features.
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "Bluetooth Low Energy not supporte", Toast.LENGTH_SHORT).show();
            finish();
        }

        // for android versions >= 6.0 you need to request for permissions at run time

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            return;
        }

*/


        // Initializes a Bluetooth adapter
        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        //get scanner
        mBluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();

        // uuidFilter = new ScanFilter.Builder().BLEscanner(new ParcelUuid(UUID.fromString("00001809-0000-1000-8000-00805f9b34fb"))).BLEscanner();
        //mScanFilters.add(uuidFilter);
        mScanSettings = new ScanSettings.Builder().build();
    }



  /*
   *** MOVED to device fragment.  But keeping the code here, to make sure we dont forget it
            in case of refactoring

  @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == REQUEST_LOCATION_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent enableLocationIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            this.startActivityForResult(enableLocationIntent, REQUEST_ENABLE_LOCATION);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // User chose not to enable Bluetooth.
        if (requestCode == REQUEST_ENABLE_BT && resultCode == Activity.RESULT_CANCELED) {
            finish();
            return;
        }

        if (requestCode == REQUEST_ENABLE_LOCATION && resultCode == Activity.RESULT_CANCELED) {
            finish();
            return;
        }

       // super.onActivityResult(requestCode, resultCode, data);
    }

*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_scan, menu);
        if (!mScanning) {
            menu.findItem(R.id.menu_stop).setVisible(false);
            menu.findItem(R.id.menu_scan).setVisible(true);
            menu.findItem(R.id.menu_refresh).setActionView(null);
        } else {
            menu.findItem(R.id.menu_stop).setVisible(true);
            menu.findItem(R.id.menu_scan).setVisible(false);
            menu.findItem(R.id.menu_refresh).setActionView(
                    R.layout.actionbar_indeterminate_progress);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_scan:
                mLeDeviceListAdapter.clear();
                scanLeDevice(true);
                break;
            case R.id.menu_stop:
                scanLeDevice(false);
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Ensures Bluetooth is enabled on the device.  If Bluetooth is not currently enabled,
        // fire an intent to display a dialog asking the user to grant permission to enable it.
        if (!mBluetoothAdapter.isEnabled()) {
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                Log.d("onResume", "asking for permission");
            }
        }


        // Initializes list view adapter.
        mLeDeviceListAdapter = new LeDeviceListAdapter();
        listView.setAdapter(mLeDeviceListAdapter);

        //start scanning when activity is in the foreground
        try {
            scanLeDevice(true);
        }
        catch(NullPointerException e) {
            Toast.makeText(  this, "Sorry, couldn't initiate scanner, try again", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        try {
            scanLeDevice(false);
        }
        catch (NullPointerException e) {
            Log.d("OnPause", "Attempting to pause: no scanner" );
        }
        mLeDeviceListAdapter.clear();
    }

/*    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        final BluetoothDevice device = mLeDeviceListAdapter.getDevice(position);
        if (device == null) return;
        final Intent intent = new Intent(this, ConnectDeviceActivity.class);
        intent.putExtra(ConnectDeviceActivity.EXTRAS_DEVICE_NAME, device.getName());
        intent.putExtra(ConnectDeviceActivity.EXTRAS_DEVICE_ADDRESS, device.getAddress());
        if (mScanning) {
            mBluetoothLeScanner.stopScan(mLeScanCallback);
            mScanning = false;
        }
        startActivity(intent);
    }*/

    private void scanLeDevice(final boolean enable) {
        if (enable) {
            // Stops scanning after a pre-defined scan period.
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScanning = false;
                    mBluetoothLeScanner.stopScan(mLeScanCallback);

                    invalidateOptionsMenu();
                }
            }, SCAN_PERIOD);

            mBluetoothLeScanner.startScan(mScanFilters, mScanSettings, mLeScanCallback);
            Log.d("scan", "started");
            mScanning = true;

        } else {
            mScanning = false;
            mBluetoothLeScanner.stopScan(mLeScanCallback);
            mScanning = false;
            Log.d("scan", "stopped");

        }
        invalidateOptionsMenu();
    }


    // Adapter for holding devices found through scanning.
    private class LeDeviceListAdapter extends BaseAdapter {
        private ArrayList<BluetoothDevice> mLeDevices;
        private LayoutInflater mInflator;

        public LeDeviceListAdapter() {
            super();
            mLeDevices = new ArrayList<BluetoothDevice>();
            mInflator = getLayoutInflater();
        }

        public void addDevice(BluetoothDevice device) {
            if(!mLeDevices.contains(device)) {
                mLeDevices.add(device);
            }
        }

        public BluetoothDevice getDevice(int position) {
            return mLeDevices.get(position);
        }

        public void clear() {
            mLeDevices.clear();
        }

        @Override
        public int getCount() {
            return mLeDevices.size();
        }

        @Override
        public Object getItem(int i) {
            return mLeDevices.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            // View Holder pattern

            if (view == null) {

                view = mInflator.inflate(R.layout.listitem_device, null);

                viewHolder = new ViewHolder();
                viewHolder.deviceAddress = (TextView) view.findViewById(R.id.device_address);
                viewHolder.deviceName = (TextView) view.findViewById(R.id.device_name);
                view.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            BluetoothDevice device = mLeDevices.get(i);
            String deviceName = device.getName();

            if (deviceName != null && deviceName.length() > 0)
                viewHolder.deviceName.setText(deviceName);
            else
                viewHolder.deviceName.setText("unknown device");
            viewHolder.deviceAddress.setText(device.getAddress());

            return view;
        }
    }

    private ScanCallback mLeScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {

            mLeDeviceListAdapter.addDevice((result.getDevice()));
            mLeDeviceListAdapter.notifyDataSetChanged();

            super.onScanResult(callbackType, result);
            Log.d("ScanCallBack", "on Scan Result");
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
            Log.d("ScanCallback", "Error");
        }
    };

    private class ViewHolder {
        TextView deviceName;
        TextView deviceAddress;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        BluetoothDevice device = mLeDeviceListAdapter.getDevice(position);

        if (device == null) return;

        Intent i = new Intent(this, ConnectDeviceActivity.class);
        i.putExtra(ConnectDeviceActivity.EXTRAS_DEVICE_NAME, device.getName());
        i.putExtra(ConnectDeviceActivity.EXTRAS_DEVICE_ADDRESS, device.getAddress());


        if (mScanning) {
            mBluetoothLeScanner.stopScan(mLeScanCallback);
            mScanning = false;
        }

        startActivity(i);
    }

}