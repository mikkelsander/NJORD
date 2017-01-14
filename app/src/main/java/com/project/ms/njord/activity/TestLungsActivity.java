package com.project.ms.njord.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.project.ms.njord.R;
import com.project.ms.njord.entity.DataManager;
import com.project.ms.njord.fragment.LineChartFragment;
import com.project.ms.njord.fragment.ManometerFragment;
import com.project.ms.njord.fragment.ObserverTestFragment;
import com.project.ms.njord.simulator.DataSimulator;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class TestLungsActivity extends AppCompatActivity implements View.OnClickListener, Observer {

    //UI references
    private Button doneBtn, startBtn;
    private FrameLayout fragmentContainer;

    LineChart chart;
    LineData lineData;
    LineDataSet set;
    ManometerFragment mano;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lungs);
        getSupportActionBar().setTitle("Test lungs");

        startBtn = (Button) findViewById(R.id.testLungs_start_button);
        startBtn.setOnClickListener(this);

        doneBtn = (Button)findViewById(R.id.testLungs_done_button);
        doneBtn.setOnClickListener(this);

        mano = new ManometerFragment();

        fragmentContainer = (FrameLayout) findViewById(R.id.testLungs_fragment_container);
        getSupportFragmentManager().beginTransaction().replace(R.id.testLungs_fragment_container,
                mano).commit();
    }

    @Override
    public void onClick(View v) {
        // Save results and open results screen
        if (v == doneBtn) {
            mano.saveResult();
            //Intent i = new Intent(this, ResultsActivity.class);
            //startActivity(i);
        }
        if (v == startBtn) {
            mano.StartReading(true);
        }
    }




    @Override
    public void update(Observable o, Object arg) {

    }
}