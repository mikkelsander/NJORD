package com.project.ms.njord.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.project.ms.njord.entity.DataManager;

/**
 * This is the first activity that is started
 * The only purpose of this activity is to start MainActivity or LogiActivity
 * depending on whether or not the user is logged in
 */


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        DataManager.init();

        // Launches login activity if not logged in
        if( !prefs.getBoolean("isLoggedIn", false) ){
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }else{
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }


    }
}
