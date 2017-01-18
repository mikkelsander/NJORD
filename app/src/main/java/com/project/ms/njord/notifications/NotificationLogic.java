package com.project.ms.njord.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.CompoundButton;

import com.project.ms.njord.R;

import static com.project.ms.njord.R.id.switchNotification;

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

    public void switchOn(CompoundButton buttonView) {
        buttonView.setChecked(true);
        switch (buttonView.getId()) {
            case switchNotification:
                editor.putBoolean("switchNotificationOn", true).commit();
                callAlarmStarter(context);
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
        buttonView.setChecked(false);
        switch (buttonView.getId()) {
            case R.id.switchNotification:
                editor.putBoolean("switchNotificationOn", false).commit();
                killAlarmStarter(context);
                break;
            case R.id.switchSound:
                editor.putBoolean("switchSoundOn", false).commit();
                break;
            case R.id.switchVibration:
                editor.putBoolean("switchVibrationOn", false).commit();
                break;
        }
    }

    public int seekBarLogic(int progress) {
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

    public void seekBarSaver(Integer progress, Integer seekBarChoice, String seekBarChoiceText) {
        editor.putInt("progress", progress)
                .putInt("seekBarChoice", seekBarChoice)
                .putString("notificationIntervalResult", seekBarChoiceText)
                .commit();
    }

    public void callAlarmStarter(Context context) {
        if (sharedPref.getBoolean("switchNotificationOn", false)) {
            alarmStart.startAlarm(context, sharedPref.getInt("seekBarChoice", 1));
        }
    }

    public void killAlarmStarter(Context context) {

        alarmStart.alarmKiller(context);
    }
}