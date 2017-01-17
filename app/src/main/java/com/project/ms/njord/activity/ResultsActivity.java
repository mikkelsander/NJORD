package com.project.ms.njord.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.project.ms.njord.R;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener {

    Button doneBtn;
    TextView inhale, exhale;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getSupportActionBar().setTitle("Results");

        doneBtn = (Button) findViewById(R.id.activity_results_doneBtn);
        doneBtn.setOnClickListener(this);

        inhale = (TextView) findViewById(R.id.activity_results_inhale_avg);
        inhale.setText("Inhale: 54!");


        exhale = (TextView) findViewById(R.id.activity_results_exhale_avg);
        exhale.setText("Exhale: 65!");

    }

    @Override
    public void onClick(View v) {
        if (v == doneBtn) {
            Intent i = new Intent(this, MainActivity.class);
            finish();

        }

    }

}
