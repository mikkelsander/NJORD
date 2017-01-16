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
    Integer initialSeekBarChoice = 1;
    String seekBarChoiceTextCandidate0 = "Hver dag";
    String seekBarChoiceTextCandidate1 = "To gange om dagen";
    String seekBarChoiceTextCandidate2 = "Hvert minut";

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
        if (!sharedPref.getBoolean("switchNotificationOn", false)) {
            switchAllOff();
        }
        seekBarNotification.setProgress(sharedPref.getInt("seekBarChoice", 50));
        notificationIntervalResult.setText(sharedPref.getString("notificationIntervalResult", seekBarChoiceTextCandidate1));


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
                editor.putBoolean("switchNotificationOn", true).commit();
                break;
            case R.id.switchSound:
                editor.putBoolean("switchSoundOn", true).commit();
                break;
            case R.id.switchVibration:
                editor.putBoolean("switchVibrationOn", true).commit();
                break;
        }
    }

    public void switchOff(CompoundButton buttonView) {
        switch (buttonView.getId()) {
            case R.id.switchNotification:
                editor.putBoolean("switchNotificationOn", false).commit();
                break;
            case R.id.switchSound:
                editor.putBoolean("switchSoundOn", false).commit();
                break;
            case R.id.switchVibration:
                editor.putBoolean("switchVibrationOn", false).commit();
                break;
        }
    }

    public void switchAllOn() {
        switchSound.setEnabled(true);
        switchVibration.setEnabled(true);
        switchNotification.setChecked(true);
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
        int seekBarChoice = sharedPref.getInt("seekBarChoice", initialSeekBarChoice);
        String seekBarChoiceText = "";

        if (progress > 0 && progress < 33) {
            seekBarChoice = 0;
        } else if (progress > 33 && progress < 66) {
            seekBarChoice = 1;
        } else {
            seekBarChoice = 2;

        }
        switch (seekBarChoice) {
            case 0:
                seekBarChoiceText = seekBarChoiceTextCandidate0;
                break;
            case 1:
                seekBarChoiceText = seekBarChoiceTextCandidate1;
                break;
            case 2:
                seekBarChoiceText = seekBarChoiceTextCandidate2;
                break;
        }
        notificationIntervalResult.setText(seekBarChoiceText);
        seekBarSaver(progress, seekBarChoiceText);
    }

    public void seekBarSaver(Integer progress, String seekBarChoiceText) {
        editor.putInt("seekBarChoice", progress)
                .putString("notificationIntervalResult", seekBarChoiceText)
                .commit();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
