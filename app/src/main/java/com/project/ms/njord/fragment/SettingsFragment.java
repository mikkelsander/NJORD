package com.project.ms.njord.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.project.ms.njord.R;
import com.project.ms.njord.activity.MainActivity;

public class SettingsFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    //Variables
    Switch switchNotification;
    Switch switchSound;
    Switch switchVibration;
    SeekBar seekBarNotification;
    TextView notificationIntervalResult;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        // Inflate the layout for this fragment
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = sharedPref.edit();

        //Cast to views
        switchNotification = (Switch) v.findViewById(R.id.switchNotification);
        switchSound = (Switch) v.findViewById(R.id.switchSound);
        switchVibration = (Switch) v.findViewById(R.id.switchVibration);
        seekBarNotification = (SeekBar) v.findViewById(R.id.seekBarNotification);
        notificationIntervalResult = (TextView) v.findViewById(R.id.notificationIntervalResult);

        //Set listeners
        switchNotification.setOnCheckedChangeListener(this);
        seekBarNotification.setOnSeekBarChangeListener(this);

        //Set initial values
        notificationIntervalResult.setText("");
        if (!switchNotification.isChecked()) {
            switchAllOff();
        }

        //End of OnCreateView
        return v;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (buttonView.getId() == switchNotification.getId()) {

            if (buttonView.isChecked()) {
                switchAllOn();
            } else {
                switchAllOff();
            }

        } else {
            if (buttonView.isChecked()) {
                switchOn(buttonView);
            } else {
                switchOff(buttonView);
            }
        }
    }

    public void switchOn(CompoundButton buttonView) {
        switch (buttonView.getId()) {
            case R.id.switchNotification:
                editor.putInt("switchNotification", 1).commit();
                break;
            case R.id.switchSound:
                editor.putInt("switchSound", 1).commit();
                break;
            case R.id.switchVibration:
                editor.putInt("switchVibration", 1).commit();
                break;
        }
    }

    public void switchOff(CompoundButton buttonView) {
        switch (buttonView.getId()) {
            case R.id.switchNotification:
                editor.putInt("switchNotification", 0).commit();
                break;
            case R.id.switchSound:
                editor.putInt("switchSound", 0).commit();
                break;
            case R.id.switchVibration:
                editor.putInt("switchVibration", 0).commit();
                break;
        }
    }

    public void switchAllOn() {
        switchSound.setEnabled(true);
        switchVibration.setEnabled(true);
        switchSound.setChecked(true);
        switchVibration.setChecked(true);
        switchOn(switchNotification);
        switchOn(switchSound);
        switchOn(switchVibration);
    }

    public void switchAllOff() {
        switchSound.setChecked(false);
        switchVibration.setChecked(false);
        switchSound.setEnabled(false);
        switchVibration.setEnabled(false);
        switchOff(switchNotification);
        switchOff(switchSound);
        switchOff(switchVibration);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        notificationIntervalResult.setText(String.valueOf(progress));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
