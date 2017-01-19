package com.project.ms.njord.dialogFragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;


import java.util.Calendar;


public class DateRequestDialog extends DialogFragment implements
        android.app.DatePickerDialog.OnDateSetListener {

    public DateRequestDialog() {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        String title = getArguments().getString("title", "");

        DatePickerDialog datePicker = new android.app.DatePickerDialog(getActivity(), this, year, month, day);
        datePicker.setTitle(title);
        return datePicker;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

        String date = day +"/"+(1+month)+"/"+ year;

        Intent i = new Intent()
                .putExtra("userString", date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
        dismiss();
    }
}
