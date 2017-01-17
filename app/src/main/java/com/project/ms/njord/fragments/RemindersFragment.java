package com.project.ms.njord.fragments;

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
import com.project.ms.njord.activities.MainActivity;
import com.project.ms.njord.notifications.AlarmStart;

public class RemindersFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

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
    AlarmStart alarmStart;
    Boolean inBootState;

    public RemindersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reminders, container, false);
        // Inflate the layout for this fragment

        //Initialize varibles
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = sharedPref.edit();
        alarmStart = new AlarmStart();

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
        inBootState = true;
        switchNotification.setChecked(sharedPref.getBoolean("switchNotificationOn", false));
        switchSound.setChecked(sharedPref.getBoolean("switchSoundOn", false));
        switchVibration.setChecked(sharedPref.getBoolean("switchVibrationOn", false));
        seekBarNotification.setProgress(sharedPref.getInt("progress", 50));
        notificationIntervalResult.setText(sharedPref.getString("notificationIntervalResult", seekBarChoiceTextCandidate1));
        if (!sharedPref.getBoolean("switchNotificationOn", false)) {
            switchOff(switchNotification);
            inBootState = false;
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
        if (buttonView.getId() == switchNotification.getId() && !inBootState) {
            switchOnEverything();
        }
    }

    public void switchOff(CompoundButton buttonView) {
        buttonView.setChecked(false);
        switch (buttonView.getId()) {
            case R.id.switchNotification:
                editor.putBoolean("switchNotificationOn", false).commit();
                killAlarmStarter();
                break;
            case R.id.switchSound:
                editor.putBoolean("switchSoundOn", false).commit();
                break;
            case R.id.switchVibration:
                editor.putBoolean("switchVibrationOn", false).commit();
                break;
        }
        if (buttonView.getId() == switchNotification.getId()) {
            switchOffEverything();
        }
    }

    public void switchOnEverything() {
        switchOn(switchSound);
        switchOn(switchVibration);
        switchEnable(switchSound);
        switchEnable(switchVibration);
    }

    public void switchOffEverything() {
        switchOff(switchSound);
        switchOff(switchVibration);
        switchDisable(switchSound);
        switchDisable(switchVibration);
    }

    public void switchEnable(CompoundButton buttonView) {
        buttonView.setEnabled(true);
    }

    public void switchDisable(CompoundButton buttonView) {
        buttonView.setEnabled(false);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int seekBarChoice = seekBarLogic(progress);
        String seekBarChoiceText = "";

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
        seekBarSaver(progress, seekBarChoice, seekBarChoiceText);
    }

    public void seekBarSaver(Integer progress, Integer seekBarChoice, String seekBarChoiceText) {
        editor.putInt("progress", progress)
                .putInt("seekBarChoice", seekBarChoice)
                .putString("notificationIntervalResult", seekBarChoiceText)
                .commit();
        callAlarmStarter();
    }

    public void callAlarmStarter() {

        alarmStart.startAlarm(getActivity());
    }

    public void killAlarmStarter() {

        alarmStart.alarmKiller(getActivity());
    }

    public int seekBarLogic(int progress) {
        int seekBarChoice;
        if (progress > 0 && progress < 33) {
            seekBarChoice = 0;
        } else if (progress > 33 && progress < 66) {
            seekBarChoice = 1;
        } else {
            seekBarChoice = 2;
        }
        return seekBarChoice;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}