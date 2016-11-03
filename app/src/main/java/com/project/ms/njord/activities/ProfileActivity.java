package com.project.ms.njord.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.project.ms.njord.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("Profile");

    }
}
