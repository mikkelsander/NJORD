package com.project.ms.njord.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.project.ms.njord.R;
import com.project.ms.njord.fragments.ManometerFragment;

public class TestLungsActivity extends AppCompatActivity {

    LineChart chart;
    LineData lineData;
    LineDataSet set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lungs);
        getSupportActionBar().setTitle("Power Test");

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.testLungs_fragment_container, new ManometerFragment())
                    .commit();
        }

    }

}