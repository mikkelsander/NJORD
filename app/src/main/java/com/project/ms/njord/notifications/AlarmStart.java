package com.project.ms.njord.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by simon on 15-01-2017.
 */

public class AlarmStart {

    //Variables
    long startTime;
    long interval;
    Calendar calendar;

    public void startAlarm(Context context, int seekBarSelection) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.roll(Calendar.DATE, +1);

        //Initialize variables
        switch (seekBarSelection) {
            case 0:
                startTime = calendar.getTimeInMillis();
                ;
                interval = 86400000;
                break;
            case 1:
                startTime = calendar.getTimeInMillis();
                interval = 43200000;
                break;
            case 2:
                startTime = System.currentTimeMillis();
                interval = 30000;
                break;
        }

        ComponentName receiver = new ComponentName(context, com.project.ms.njord.notifications.BootListener.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmListener.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), 86400000, pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis() + 60000, 86400000, pendingIntent);

    }

    public void alarmKiller(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmListener.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
    }

}