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
import com.project.ms.njord.notifications.AlarmStart;

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
        switchSound.setOnCheckedChangeListener(this);
        switchVibration.setOnCheckedChangeListener(this);
        seekBarNotification.setOnSeekBarChangeListener(this);

        //Set initial values
        switchNotification.setChecked(sharedPref.getBoolean("switchNotificationOn", false));
        switchSound.setChecked(sharedPref.getBoolean("switchSoundOn", false));
        switchVibration.setChecked(sharedPref.getBoolean("switchVibrationOn", false));
        seekBarNotification.setProgress(sharedPref.getInt("seekBarChoice", 50));
        notificationIntervalResult.setText(sharedPref.getString("notificationIntervalResult", seekBarChoiceTextCandidate1));
        if (!sharedPref.getBoolean("switchNotificationOn", false)) {
            switchOff(switchNotification);
            AlarmStart.startAlarm(getContext());
        }

        //End of OnCreateView
        return v;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (buttonView.isChecked()) {
            switchOn(buttonView);
        } else {
            switchOff(buttonView);
        }
    }

    public void switchOn(CompoundButton buttonView) {
        buttonView.setChecked(true);
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
        if (buttonView.getId() == switchNotification.getId()) {
            switchOn(switchSound);
            switchOn(switchVibration);
            switchEnable(switchSound);
            switchEnable(switchVibration);
        }
        callAlarmStarter();
    }

    public void switchOff(CompoundButton buttonView) {
        buttonView.setChecked(false);
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
        if (buttonView.getId() == switchNotification.getId()) {
            switchOff(switchSound);
            switchOff(switchVibration);
            switchDisable(switchSound);
            switchDisable(switchVibration);
        }
        callAlarmStarter();
    }

    public void switchEnable(CompoundButton buttonView) {
        buttonView.setEnabled(true);
    }

    public void switchDisable(CompoundButton buttonView) {
        buttonView.setEnabled(false);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        notificationIntervalResult.setText(String.valueOf(progress));
        int seekBarChoice = 1;
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
        callAlarmStarter();
    }

    public void callAlarmStarter() {
        AlarmStart.startAlarm(getContext());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}