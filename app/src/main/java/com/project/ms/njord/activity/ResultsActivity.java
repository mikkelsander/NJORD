package com.project.ms.njord.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.project.ms.njord.R;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener {

    Button doneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getSupportActionBar().setTitle("Results");

        doneBtn = (Button) findViewById(R.id.activity_results_doneBtn);
        doneBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == doneBtn) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);

        }

    }

}
