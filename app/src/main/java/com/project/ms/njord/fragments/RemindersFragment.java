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
import com.project.ms.njord.notifications.NotificationLogic;

public class RemindersFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

    //Variables
    private  NotificationLogic notificationLogic;
    private  Switch switchNotification;
    private  Switch switchSound;
    private Switch switchVibration;
    private SeekBar seekBarNotification;
    private TextView notificationInterval;
    private TextView notificationIntervalResult;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    final private String seekBarChoiceTextCandidate0 = "Every day";
    final private String seekBarChoiceTextCandidate1 = "Twice a day";
    final private  String seekBarChoiceTextCandidate2 = "Two times every minute";
    private  Boolean inBootState;

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
        notificationInterval = (TextView) v.findViewById(R.id.notificationInterval);
        notificationIntervalResult = (TextView) v.findViewById(R.id.notificationIntervalResult);

        //Set listeners
        switchNotification.setOnCheckedChangeListener(this);
        switchSound.setOnCheckedChangeListener(this);
        switchVibration.setOnCheckedChangeListener(this);
        seekBarNotification.setOnSeekBarChangeListener(this);

        //Set initial values
        inBootState = true;
        switchNotification.setChecked(sharedPref.getBoolean("switchNotificationOn", false));
        if (!sharedPref.getBoolean("switchNotificationOn", false)) {
            masterSwitchOff();
        }
        inBootState = false;
        switchSound.setChecked(sharedPref.getBoolean("switchSoundOn", false));
        switchVibration.setChecked(sharedPref.getBoolean("switchVibrationOn", false));
        seekBarNotification.setProgress(sharedPref.getInt("progress", 1));
        notificationIntervalResult.setText(sharedPref.getString("notificationIntervalResult", seekBarChoiceTextCandidate1));

        //End of OnCreateView
        return v;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (buttonView.isChecked()) {
            notificationLogic.switchOnStateSaver(buttonView);
            if (buttonView.getId() == switchNotification.getId() && !inBootState) {
                editor.putBoolean("notificationsAdvised", true).commit();
                masterSwitchOn();
            }
        } else {
            notificationLogic.switchOffStateSaver(buttonView);
            if (buttonView.getId() == switchNotification.getId()) {
                masterSwitchOff();
            }
        }

    }

    private void masterSwitchOn() {
        notificationLogic.switchOnSwitch(switchSound);
        notificationLogic.switchOnSwitch(switchVibration);
        notificationLogic.enableSwitch(switchSound);
        notificationLogic.enableSwitch(switchVibration);
        notificationLogic.enableSeekbar(seekBarNotification);
        notificationLogic.enableText(notificationInterval);
        notificationLogic.enableText(notificationIntervalResult);
        notificationLogic.switchOnStateSaver(switchSound);
        notificationLogic.switchOnStateSaver(switchVibration);

    }

    private void masterSwitchOff() {
        notificationLogic.switchOffSwitch(switchSound);
        notificationLogic.switchOffSwitch(switchVibration);
        notificationLogic.disableSwitch(switchSound);
        notificationLogic.disableSwitch(switchVibration);
        notificationLogic.disableSeekbar(seekBarNotification);
        notificationLogic.disableText(notificationInterval);
        notificationLogic.disableText(notificationIntervalResult);
        notificationLogic.switchOffStateSaver(switchSound);
        notificationLogic.switchOffStateSaver(switchVibration);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        String seekBarChoiceText = notificationLogic.seekBarChoiceTextCreator(progress, seekBarChoiceTextCandidate0, seekBarChoiceTextCandidate1, seekBarChoiceTextCandidate2);

        notificationIntervalResult.setText(seekBarChoiceText);
        notificationLogic.seekBarSaver(progress, seekBarChoiceText);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}