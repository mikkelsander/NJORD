package com.project.ms.njord.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.project.ms.njord.R;
import com.project.ms.njord.model.Singleton;

/**
 * This is the first activity that is started
 * The only purpose of this activity is to start MainActivity or LogiActivity
 * depending on whether or not the user is logged in
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Singleton.init();


        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... params) {

                // user not logged in
                if (!prefs.getBoolean("isLoggedIn", false)) {
                    return false;
                } else {
                    try {
                        Singleton.instance.getDataBaseManager().syncProfile(prefs.getString("active_email", "EmailNotFound"));

                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }


            @Override
            protected void onPostExecute(Boolean isLoggedIn) {
                super.onPostExecute(isLoggedIn);

                if (isLoggedIn) {
                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }

                if (!isLoggedIn) {
                    Intent i = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        }.execute();
    }
}

