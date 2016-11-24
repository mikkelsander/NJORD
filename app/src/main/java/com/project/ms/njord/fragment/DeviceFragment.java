package com.project.ms.njord.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.project.ms.njord.R;


public class DeviceFragment extends Fragment implements View.OnClickListener {

    // UI references
    private Button changeDeviceBtn;
    private TextView deviceID;

    public DeviceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_device, container, false);

        // Inflate the layout for this fragment
        changeDeviceBtn =  (Button) v.findViewById(R.id.device_forget_button);
        changeDeviceBtn.setOnClickListener(this);

        deviceID = (TextView) v.findViewById(R.id.device_id_textView);

        return v;
    }


    @Override
    public void onClick(View v) {
        if (v == changeDeviceBtn){
            searchForDevice();
            deviceID.setText("No spirometer connected");
        }
    }

    private void searchForDevice() {
        // TODO: Search for at new device over bluetooth
        pressentUserWithDviceChoices();
    }

    private void pressentUserWithDviceChoices() {
        // TODO: inflate at view that presents the user with near by devices that can be connected to

    }
}
