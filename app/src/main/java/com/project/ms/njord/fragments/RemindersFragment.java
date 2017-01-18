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
import com.project.ms.njord.notifications.NotificationLogic;

public class RemindersFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    //Variables
    NotificationLogic notificationLogic;
    Switch switchNotification;
    Switch switchSound;
    Switch switchVibration;
    SeekBar seekBarNotification;
    TextView notificationIntervalResult;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    String seekBarChoiceTextCandidate0 = "Hver dag";
    String seekBarChoiceTextCandidate1 = "To gange om dagen";
    String seekBarChoiceTextCandidate2 = "To gange i minuttet";
    Boolean inBootState;

    public RemindersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reminders, container, false);
        // Inflate the layout for this fragment

        //Initialize varibles
        notificationLogic = new NotificationLogic(getActivity());
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
        inBootState = true;
        switchNotification.setChecked(sharedPref.getBoolean("switchNotificationOn", false));
        switchSound.setChecked(sharedPref.getBoolean("switchSoundOn", false));
        switchVibration.setChecked(sharedPref.getBoolean("switchVibrationOn", false));
        seekBarNotification.setProgress(sharedPref.getInt("progress", 50));
        notificationIntervalResult.setText(sharedPref.getString("notificationIntervalResult", seekBarChoiceTextCandidate1));
        if (!sharedPref.getBoolean("switchNotificationOn", false)) {
            notificationLogic.switchOff(switchNotification);
        }
        inBootState = false;

        //End of OnCreateView
        return v;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (buttonView.isChecked()) {
            notificationLogic.switchOn(buttonView);
            if (buttonView.getId() == switchNotification.getId() && !inBootState) {
                switchOnEverything();
            }
        } else {
            notificationLogic.switchOff(buttonView);
            if (buttonView.getId() == switchNotification.getId()) {
                switchOffEverything();
            }
        }
    }

    public void switchOnEverything() {
        notificationLogic.switchOn(switchSound);
        notificationLogic.switchOn(switchVibration);
        switchEnable(switchSound);
        switchEnable(switchVibration);
        seekBarEnable(seekBarNotification);
    }

    public void switchOffEverything() {
        notificationLogic.switchOff(switchSound);
        notificationLogic.switchOff(switchVibration);
        switchDisable(switchSound);
        switchDisable(switchVibration);
        seekBarDisable(seekBarNotification);
    }

    public void seekBarEnable(SeekBar seekBar) {
        seekBarNotification.setEnabled(true);
    }

    public void seekBarDisable(SeekBar seekBar) {
        seekBarNotification.setEnabled(false);
    }

    public void switchEnable(CompoundButton buttonView) {
        buttonView.setEnabled(true);
    }

    public void switchDisable(CompoundButton buttonView) {
        buttonView.setEnabled(false);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int seekBarChoice = notificationLogic.seekBarLogic(progress);
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
        notificationLogic.seekBarSaver(progress, seekBarChoice, seekBarChoiceText);
        notificationLogic.callAlarmStarter(getActivity());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}