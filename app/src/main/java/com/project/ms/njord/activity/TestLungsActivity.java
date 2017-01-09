package com.project.ms.njord.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.project.ms.njord.R;
import com.project.ms.njord.fragment.ObserverTestFragment;
import com.project.ms.njord.simulator.DataSimulator;

import org.w3c.dom.Text;

import java.util.Observable;
import java.util.Observer;

public class TestLungsActivity extends AppCompatActivity implements View.OnClickListener, Observer {

    private Button doneBtn, startBtn;
    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lungs);
        getSupportActionBar().setTitle("Test lungs");

        startBtn = (Button) findViewById(R.id.testLungs_start_button);
        startBtn.setOnClickListener(this);

        doneBtn = (Button)findViewById(R.id.testLungs_done_button);
        doneBtn.setOnClickListener(this);

        fragmentContainer = (FrameLayout) findViewById(R.id.testLungs_fragment_container);

        getSupportFragmentManager().beginTransaction().replace(R.id.testLungs_fragment_container,
                new ObserverTestFragment()).commit();

    }

    @Override
    public void onClick(View v) {
        if (v == doneBtn) {
            Intent i = new Intent(this, ResultsActivity.class);
            startActivity(i);
            finish();

        if (v == startBtn) {

        }
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        TextView inhaleView = (TextView) findViewById(R.id.testNumberTextView);
        TextView exhaleView = (TextView) findViewById(R.id.testNumberTextView2);

    }
}