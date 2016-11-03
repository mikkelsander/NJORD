package com.project.ms.njord.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.project.ms.njord.R;
import com.project.ms.njord.archive.ResultsActivity;

public class TestLungsActivity extends AppCompatActivity implements View.OnClickListener {

    Button doneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lungs);
        doneBtn = (Button)findViewById(R.id.doneBtn);
        doneBtn.setOnClickListener(this);
        getSupportActionBar().setTitle("Test lungs");
    }

    @Override
    public void onClick(View v) {
        if(v == doneBtn){
            Intent i = new Intent(this, ResultsActivity.class);
            startActivity(i);

        }
    }
}
