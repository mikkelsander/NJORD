package com.project.ms.njord.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.project.ms.njord.R;

public class TestLungsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button doneBtn;
    private TextView instrucTxt, lungLevelTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lungs);
        getSupportActionBar().setTitle("Test lungs");

        doneBtn = (Button)findViewById(R.id.testLungs_done_button);
        doneBtn.setOnClickListener(this);

        instrucTxt = (TextView) findViewById(R.id.testLungs_instructions_textView);
        lungLevelTxt = (TextView) findViewById(R.id.testLungs_lungLevel_textView);


    }

    @Override
    public void onClick(View v) {
        if (v == doneBtn) {
            Intent i = new Intent(this, ResultsActivity.class);
            startActivity(i);
            finish();
        }
    }





}