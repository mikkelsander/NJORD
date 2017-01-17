package com.project.ms.njord.activities;

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
import com.project.ms.njord.fragments.RemindersFragment;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener {

    Button doneBtn;
    TextView inhale, exhale;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    RemindersFragment settingsFragment;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        getSupportActionBar().setTitle("Results");

        settingsFragment = new RemindersFragment();
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPref.edit();

        bundle = getIntent().getExtras();

        doneBtn = (Button) findViewById(R.id.activity_results_doneBtn);
        doneBtn.setOnClickListener(this);

        inhale = (TextView) findViewById(R.id.activity_results_inhale_avg);
        inhale.setText(Integer.toString(bundle.getInt("inhale")));
        exhale = (TextView) findViewById(R.id.activity_results_exhale_avg);
        exhale.setText(Integer.toString(bundle.getInt("exhale")));
    }

    //Generates Dialog asking for Push notification permission. If given all permissions are switched on with standard prefs.
    public void showNotificationAlertDialog() {
        //editor.putBoolean("firstRun", false).commit();
        new AlertDialog.Builder(this)
                .setTitle("Notifications")
                .setMessage("Training on a regular bases is important. AEROFIT would like permission to send you daily reminders." +
                        "\n\n" +
                        "You can always change your selection in the menu under Preferences.")
                .setPositiveButton("Activate Notifications", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // settingsFragment.callAlarmStarter();
                        showConfirmNotificationToast();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();

                    }
                })
                .setNegativeButton("No thanks", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                })
                .show();
    }

    public void showConfirmNotificationToast() {
        Toast.makeText(this, "Notifications activated", Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onClick(View v) {
        if (v == doneBtn) {
            //Check if this is the first run of the app, and if so call for dialog
            //if (sharedPref.getBoolean("firstRun", true)) {
            showNotificationAlertDialog();
            //}else{
            //Intent i = new Intent(this, MainActivity.class);
            //startActivity(i);
            //finish();
            //}
        }
    }

}
