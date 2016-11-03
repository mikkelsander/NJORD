package com.project.ms.njord.archive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.project.ms.njord.R;

public class DeviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        getSupportActionBar().setTitle("Device");
    }
}
