package com.project.ms.njord.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
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

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private AlarmStart alarmStart;
    private Context context;

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
                callAlarmStarter();
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
                killAlarmStarter();
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

    public void enableText(View view) {
        view.setAlpha((float) 1.0);
    }

    public void disableText(View view) {
        view.setAlpha((float) 0.5);
    }

    public void enableSeekbar(SeekBar seekBar) {
        seekBar.setEnabled(true);
    }

    public void disableSeekbar(SeekBar seekBar) {
        seekBar.setEnabled(false);
    }

    public String seekBarChoiceTextCreator(int progress, String choice0, String choice1, String choice2) {
        String seekBarChoiceTextCandidate0 = choice0;
        String seekBarChoiceTextCandidate1 = choice1;
        String seekBarChoiceTextCandidate2 = choice2;
        String seekBarChoiceText = "";

        switch (progress) {
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

    public void seekBarSaver(Integer progress, String seekBarChoiceText) {
        editor.putInt("progress", progress)
                .putString("notificationIntervalResult", seekBarChoiceText)
                .commit();
        callAlarmStarter();
    }


    public void callAlarmStarter() {
        alarmStart.startAlarm(context, sharedPref.getInt("progress", 1));

    }

    private void killAlarmStarter() {

        alarmStart.alarmKiller(context);
    }
}