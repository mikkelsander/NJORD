package com.project.ms.njord.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.project.ms.njord.fragment.SettingsFragment;

/**
 * Created by simon on 15-01-2017.
 */

public class BootListener extends BroadcastReceiver {
    Context context;
    SharedPreferences sharedPreferences;
    SettingsFragment settingsFragment;

    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        settingsFragment = new SettingsFragment();
        new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] params) {
                opdaterkalenderBoot();

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
            }
        }.execute();
    }

    private void opdaterkalenderBoot() {
        settingsFragment.callAlarmStarter();
    }
}