package com.project.ms.njord;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.project.ms.njord.activities.TestLungsActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button beginTestBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        beginTestBtn = (Button) findViewById(R.id.beginTestBtn);
        beginTestBtn.setOnClickListener(this);
        getSupportActionBar().setTitle("Home");




    }

    @Override
    public void onClick(View v) {
        if(v == beginTestBtn){
            Intent i = new Intent(this, TestLungsActivity.class);
            startActivity(i);
        }
    }
}
