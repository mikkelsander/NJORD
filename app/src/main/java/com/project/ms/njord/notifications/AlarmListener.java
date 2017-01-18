package com.project.ms.njord.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;

import com.project.ms.njord.R;
import com.project.ms.njord.activities.SplashActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by simon on 15-01-2017.
 */

public class AlarmListener extends BroadcastReceiver {

    //Variables
    NotificationManager notificationManager = null;
    SharedPreferences sharedPref;
    PendingIntent pendingIntent;

    @Override
    public void onReceive(Context context, Intent intent) {

        //Initialize variables
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, SplashActivity.class), 0);

        //Build notification
        Notification.Builder notificationBuilder = new Notification.Builder(context)
                .setContentTitle("Så er det tid til at blæse!")
                .setContentText("Blæs, mester, blæs!")
                .setSmallIcon(R.drawable.ic_tv_dark)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setCategory(Notification.CATEGORY_ALARM)
                .setContentIntent(pendingIntent)
                .setOnlyAlertOnce(true);

        //Add relevent user preferences
        if (sharedPref.getBoolean("switchSoundOn", false)) {
            notificationBuilder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        }
        if (sharedPref.getBoolean("switchVibrationOn", false)) {
            notificationBuilder.setVibrate(new long[]{1000L, 500L, 1000L, 500L, 1000L});
        }

        //Finalize build and notify via notification manager
        Notification notification = notificationBuilder.build();
        notificationManager.notify(0, notification);
    }
}