package com.project.ms.njord.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.project.ms.njord.BLE.DeviceScanActivity;
import com.project.ms.njord.R;


public class DeviceFragment extends Fragment implements View.OnClickListener {

    // UI references
    private Button scanBtn;

    public DeviceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_device, container, false);

        // Inflate the layout for this fragment
        scanBtn =  (Button) v.findViewById(R.id.device_scan_btn);
        scanBtn.setOnClickListener(this);

        //deviceID = (TextView) v.findViewById(R.id.device);

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == scanBtn){
            Intent i = new Intent(getActivity(), DeviceScanActivity.class);
            startActivity(i);

        }
    }

}
