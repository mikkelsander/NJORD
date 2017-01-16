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

public class TestLungsActivity extends AppCompatActivity implements View.OnClickListener {

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



        mano = new ManometerFragment();

        fragmentContainer = (FrameLayout) findViewById(R.id.testLungs_fragment_container);
        getSupportFragmentManager().beginTransaction().replace(R.id.testLungs_fragment_container,
                mano).commit();
    }

    @Override
    public void onClick(View v) {
        // Save results and open results screen
        if (v == doneBtn) {
            /*
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new ResultsFragment());
            fragmentTransaction.commit();
            */
            Intent i = new Intent(this, ResultsActivity.class);
            startActivity(i);
        }
        if (v == startBtn) {
            mano.StartReading(true);
        }
    }
}