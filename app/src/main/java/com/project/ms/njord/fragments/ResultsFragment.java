package com.project.ms.njord.fragments;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.project.ms.njord.R;
import com.project.ms.njord.notifications.NotificationLogic;

public class ResultsFragment extends Fragment implements View.OnClickListener {

    Button doneBtn;
    TextView inhale, exhale;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    RemindersFragment remindersFragment;
    Bundle bundle;
    NotificationLogic notificationLogic;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_results, container, false);

        // getActivity().getSupportActionBar().setTitle("Results");

        notificationLogic = new NotificationLogic(getActivity());
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = sharedPref.edit();

        Bundle args = getArguments();

        doneBtn = (Button) v.findViewById(R.id.activity_results_doneBtn);
        doneBtn.setOnClickListener(this);

        inhale = (TextView) v.findViewById(R.id.activity_results_inhale_avg);
        inhale.setText(Integer.toString(args.getInt("inhale", 0)));
        exhale = (TextView) v.findViewById(R.id.activity_results_exhale_avg);
        exhale.setText(Integer.toString(args.getInt("exhale", 0)));


        return v;
    }

    //Generates Dialog asking for Push notification permission. If given all permissions are switched on with standard prefs.
    public void showNotificationAlertDialog() {
        editor.putBoolean("notificationsAdvised", true).commit();
        new AlertDialog.Builder(getActivity())
                .setTitle("Notifications")
                .setMessage("Training on a regular bases is important. AEROFIT would like permission to send you daily reminders." +
                        "\n\n" +
                        "You can always change your selection in the menu under Reminders.")
                .setPositiveButton("Agree", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        notificationLogic.notificationsDialogAccepted();
                        showConfirmNotificationToast();
                        getActivity().finish();

                    }
                })
                .setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                })
                .show();
    }

    public void showConfirmNotificationToast() {
        Toast.makeText(getActivity(), "Notifications activated", Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onClick(View v) {
        if (v == doneBtn) {
            if (!sharedPref.getBoolean("notificationsAdvised", false)) {
                showNotificationAlertDialog();
            }else{
            getActivity().finish();
            }
        }
    }

}
