package com.project.ms.njord.dialogFragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.project.ms.njord.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumberPickerDialog extends DialogFragment {


    public NumberPickerDialog() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final int GENDER = 4;
        final int HEIGHT = 5;
        final int WEIGHT = 6;


        Bundle args = getArguments();
        String title = args.getString("title", "");
        String message = args.getString("message", "");

        final int code = args.getInt("code");

        final NumberPicker picker = new NumberPicker(getActivity());

        final FrameLayout pickerView = new FrameLayout(getActivity());
        pickerView.addView(picker);


        if (code == HEIGHT) {
            picker.setMinValue(100);
            picker.setMaxValue(215);
            picker.setValue(170);

        }
        if (code == WEIGHT) {
            picker.setMinValue(30);
            picker.setMaxValue(250);
            picker.setValue(70);

        }
        if (code == GENDER) {
            picker.setMinValue(0);
            picker.setMaxValue(1);
            picker.setDisplayedValues(new String[]{"Male","Female"});
        }

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setView(pickerView)
                .setPositiveButton("save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent()
                                .putExtra("userInt", picker.getValue());
                        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);
                        dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, null);
                        dismiss();
                    }
                })
                .create();

    }

}
