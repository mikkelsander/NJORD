package com.project.ms.njord.fragment;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.project.ms.njord.R;
import com.project.ms.njord.activity.SignUpActivity;

import java.util.Calendar;


public class DatePickerFragment extends DialogFragment implements
        DatePickerDialog.OnDateSetListener {

    final String TAG = "DatePicker";

    public DatePickerFragment() {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it


        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // do some stuff for example write on log and update TextField on activity
        Log.d(TAG, "Date ="+day+" "+(1+month)+" "+year);
        ((TextView) getActivity().findViewById(R.id.signUp_birthday_editText)).setText(day +"/"+(1+month)+"/"+ year);
    }
}
