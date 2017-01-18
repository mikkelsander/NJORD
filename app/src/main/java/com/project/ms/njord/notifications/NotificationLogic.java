package com.project.ms.njord.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import com.project.ms.njord.R;

import static com.project.ms.njord.R.id.switchNotification;
import static com.project.ms.njord.R.id.switchSound;
import static com.project.ms.njord.R.id.switchVibration;

/**
 * Created by simon on 17-01-2017.
 */

public class NotificationLogic {

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    AlarmStart alarmStart;
    Context context;

    public NotificationLogic(Context context) {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPref.edit();
        alarmStart = new AlarmStart();
        this.context = context;
    }

    public void notificationsDialogAccepted() {
        editor.putBoolean("switchNotificationOn", true)
                .putBoolean("switchSoundOn", true)
                .putBoolean("switchVibrationOn", true)
                .commit();
        callAlarmStarter();
    }

    public void switchOnStateSaver(CompoundButton buttonView) {

        switch (buttonView.getId()) {
            case switchNotification:
                editor.putBoolean("switchNotificationOn", true).commit();
                break;
            case switchSound:
                editor.putBoolean("switchSoundOn", true).commit();
                break;
            case switchVibration:
                editor.putBoolean("switchVibrationOn", true).commit();
                break;

        }

    }

    public void switchOffStateSaver(CompoundButton buttonView) {

        switch (buttonView.getId()) {
            case R.id.switchNotification:
                editor.putBoolean("switchNotificationOn", false).commit();
                break;
            case switchSound:
                editor.putBoolean("switchSoundOn", false).commit();
                break;
            case switchVibration:
                editor.putBoolean("switchVibrationOn", false).commit();
                break;
        }
    }

    public void switchOnSwitch(CompoundButton buttonView) {
        buttonView.setChecked(true);
    }

    public void switchOffSwitch(CompoundButton buttonView) {
        buttonView.setChecked(false);
    }

    public void enableSwitch(CompoundButton buttonView) {
        buttonView.setEnabled(true);
    }

    public void disableSwitch(CompoundButton buttonView) {
        buttonView.setEnabled(false);
    }

    public void enableSeekbar(SeekBar seekBar) {
        seekBar.setEnabled(true);
    }

    public void disableSeekbar(SeekBar seekBar) {
        seekBar.setEnabled(false);
    }

    public int seekBarChoiceCreator(int progress) {
        int seekBarChoice;
        if (progress >= 0 && progress < 33) {
            seekBarChoice = 0;
        } else if (progress > 33 && progress < 66) {
            seekBarChoice = 1;
        } else {
            seekBarChoice = 2;
        }
        return seekBarChoice;
    }

    public String seekBarChoiceTextCreator(int SeekBarChoice, String choice0, String choice1, String choice2) {
        String seekBarChoiceTextCandidate0 = choice0;
        String seekBarChoiceTextCandidate1 = choice1;
        String seekBarChoiceTextCandidate2 = choice2;
        String seekBarChoiceText = "";

        switch (SeekBarChoice) {
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
        return seekBarChoiceText;
    }

    public void seekBarSaver(Integer progress, Integer seekBarChoice, String seekBarChoiceText) {
        editor.putInt("progress", progress)
                .putInt("seekBarChoice", seekBarChoice)
                .putString("notificationIntervalResult", seekBarChoiceText)
                .commit();
    }


    public void callAlarmStarter() {
        alarmStart.startAlarm(context, sharedPref.getInt("seekBarSelection", 1));

    }

    public void killAlarmStarter() {

        alarmStart.alarmKiller(context);
    }
}