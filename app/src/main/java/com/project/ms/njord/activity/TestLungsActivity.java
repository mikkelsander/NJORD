package com.project.ms.njord.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.project.ms.njord.R;
import com.project.ms.njord.fragment.ManometerFragment;
import java.util.Observable;
import java.util.Observer;

public class TestLungsActivity extends AppCompatActivity {

    LineChart chart;
    LineData lineData;
    LineDataSet set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lungs);
        getSupportActionBar().setTitle("Power Test");

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.testLungs_fragment_container, new ManometerFragment())
                .commit();
    }

}