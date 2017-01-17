package com.project.ms.njord.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by simon on 15-01-2017.
 */

public class AlarmStart {

    //Variables
    long startTime;
    long interval;

    public void startAlarm(Context context, int seekBarChoice) {


        //Initialize variables
        switch (seekBarChoice) {
            case 0:
                startTime = System.currentTimeMillis();
                ;
                interval = 86400000;
                break;
            case 1:
                startTime = System.currentTimeMillis();
                interval = 43200000;
                break;
            case 2:
                startTime = System.currentTimeMillis();
                interval = 60000;
                break;
        }

        ComponentName receiver = new ComponentName(context, com.project.ms.njord.notifications.BootListener.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmListener.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC, startTime, interval, pendingIntent);

    }

    public void alarmKiller(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmListener.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
    }

}