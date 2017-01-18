package com.project.ms.njord.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by simon on 17-01-2017.
 */

public class NotificationLogic {

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    AlarmStart alarmStart;

    public NotificationLogic(Context context) {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPref.edit();
        alarmStart = new AlarmStart();
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