package com.project.ms.njord.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.project.ms.njord.R;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener {

    Button doneBtn;
    TextView inhale, exhale;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

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

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPref.edit();

    }

    @Override
    public void onClick(View v) {
        if (v == doneBtn) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();

            //Check if this is the first run of the app, and if so call for dialog
            if (sharedPref.getBoolean("firstRun", true)) {
                showNotificationAlertDialog();
            }

        }

    }

    //Generates Dialog asking for Push notification permission
    public void showNotificationAlertDialog() {
        editor.putBoolean("firstRun", false).commit();
        new AlertDialog.Builder(this)
                .setTitle("Notifications")
                .setMessage("Training on a regular bases is important. AEROFIT would like permission to send you daily reminders." +
                        "\n\n" +
                        "You can always change your selection in the menu under Preferences.")
                .setPositiveButton("Activate Notifications", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showConfirmNotificationAlertDialog();
                    }
                }).setNegativeButton("No thanks", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }

    public void showConfirmNotificationAlertDialog() {
        Toast.makeText(this, "Notifications activated", Toast.LENGTH_LONG)
                .show();
    }
}
