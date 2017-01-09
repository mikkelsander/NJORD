package com.project.ms.njord.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.ms.njord.dialogsFragments.DialogCallback;
import com.project.ms.njord.entity.DataManager;

import com.project.ms.njord.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener{

    TextView nameView, birthdayView, emailView, heightView, weightView;
    LinearLayout nameLayout, birthdayLayout, emailLayout, heightLayout, weightLayout;

    EditText nameEdit;
    EditText emailEdit;
    EditText birthdayEdit;
    EditText genderEdit;
    EditText heightEdit;
    EditText weightEdit;

    Button changeInfo;

    Boolean isEditing = false;

    Button button;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile_fragment2, container, false);





        emailView = (TextView) v.findViewById(R.id.profile_email_textView);
        emailLayout = (LinearLayout) v.findViewById(R.id.profile_email_layout);
        emailLayout.setOnClickListener(this);

        nameView = (TextView) v.findViewById(R.id.profile_name_textView);
        nameLayout = (LinearLayout) v.findViewById(R.id.profile_name_layout);
        nameLayout.setOnClickListener(this);

        birthdayView = (TextView) v.findViewById(R.id.profile_birthday_textView);
        birthdayLayout = (LinearLayout) v.findViewById(R.id.profile_birthday_layout);
        birthdayLayout.setOnClickListener(this);

        heightView = (TextView) v.findViewById(R.id.profile_height_textView);
        heightLayout = (LinearLayout) v.findViewById(R.id.profile_height_layout);
        heightLayout.setOnClickListener(this);

        weightView = (TextView) v.findViewById(R.id.profile_weight_textView);
        weightLayout = (LinearLayout) v.findViewById(R.id.profile_weight_layout);
        weightLayout.setOnClickListener(this);

      /*  nameEdit.setText(DataManager.dataManager.getProfile().getName());
        setEditable(nameEdit, false);

        emailEdit = (EditText) v.findViewById(R.id.profile_mail_editText);
        emailEdit.setText(DataManager.dataManager.getProfile().getEmail());
        setEditable(emailEdit, false);

        birthdayEdit = (EditText) v.findViewById(R.id.profile_birthday_editText);
        birthdayEdit.setText(DataManager.dataManager.getProfile().getBirthday());
        setEditable(birthdayEdit, false);

        genderEdit = (EditText) v.findViewById(R.id.profile_gender_editText);
        genderEdit.setText(DataManager.dataManager.getProfile().getGender());
        setEditable(genderEdit, false);

        heightEdit = (EditText) v.findViewById(R.id.profile_height_EditText);
        heightEdit.setText(Integer.toString(DataManager.dataManager.getProfile().getHeight()));
        setEditable(heightEdit, false);

        weightEdit = (EditText) v.findViewById(R.id.profile_weight_editText);
        weightEdit.setText(Integer.toString(DataManager.dataManager.getProfile().getWeight()));
        setEditable(weightEdit, false);*/




        return v;


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case 0:

                if (resultCode == Activity.RESULT_OK) {
                    String s = data.getExtras().getString("userResponse");
                    int id = data.getExtras().getInt("viewId");

                    if (id == emailView.getId()) {
                        emailView.setText(s);
                    }
                    if (id == nameView.getId()) {
                        nameView.setText(s);
                    }
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (v == emailLayout) {
            DialogFragment newFragment = new StringPickerFragment();
            Bundle args = new Bundle();
            args.putInt("viewId", v.getId());
            newFragment.setArguments(args);
            newFragment.setTargetFragment(this, 0);
            newFragment.show(getActivity().getSupportFragmentManager(), "emailPicker");
        }

        if(v == nameLayout) {

        }

        if (v == birthdayLayout) {
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.setTargetFragment(this, 0);
            newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
        }

        if (v == heightLayout) {

        }

        if (v == weightLayout) {


        }

    }




   /* @Override
    public void onClick(View v) {

        if (isEditing) { //is editing, therefore save info

            setEditable(nameEdit, false);
            setEditable(emailEdit, false);
            setEditable(birthdayEdit, false);
            setEditable(genderEdit, false);
            setEditable(heightEdit, false);
            setEditable(weightEdit, false);

            //Change button text
            changeInfo.setText("Change info");

        } else { //Not editing, therefore begin editing

            setEditable(nameEdit, true);
            setEditable(emailEdit, true);
            setEditable(birthdayEdit, true);
            setEditable(genderEdit, true);
            setEditable(heightEdit, true);
            setEditable(weightEdit, true);

            changeInfo.setText("Save");
        }

        isEditing = !isEditing;
    }

    private void setEditable(EditText editText, Boolean editable) { //ændre texten.
        if (editable == false) { // disable editing

            editText.setFocusable(false);
            editText.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
            editText.setClickable(false); // user navigates with wheel and selects widget

        } else { // enable editing

            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.setClickable(true);

        }
    }*/

}

