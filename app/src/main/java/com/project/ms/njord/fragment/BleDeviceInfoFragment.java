package com.project.ms.njord.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.ms.njord.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BleDeviceInfoFragment extends Fragment {


    public BleDeviceInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ble_device_info, container, false);
    }

}
