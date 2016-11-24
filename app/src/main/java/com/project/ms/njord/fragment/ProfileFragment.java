package com.project.ms.njord.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.project.ms.njord.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {


    EditText nameEdit;
    EditText emailEdit;
    EditText birthdayEdit;
    EditText genderEdit;
    EditText heightEdit;
    EditText weightEdit;

    Button changeInfo;

    Boolean isEditing = false;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        nameEdit = (EditText) v.findViewById(R.id.profile_name_editText);
        nameEdit.setText("Mads Werner");
        setEditable(nameEdit, false);

        emailEdit = (EditText) v.findViewById(R.id.profile_mail_editText);
        emailEdit.setText("Mads@gmail.com");
        setEditable(emailEdit, false);

        birthdayEdit = (EditText) v.findViewById(R.id.profile_birthday_editText);
        birthdayEdit.setText("19/08/1993");
        setEditable(birthdayEdit, false);

        genderEdit = (EditText) v.findViewById(R.id.profile_gender_editText);
        genderEdit.setText("Male");
        setEditable(genderEdit, false);

        heightEdit = (EditText) v.findViewById(R.id.profile_height_EditText);
        heightEdit.setText("192");
        setEditable(heightEdit, false);

        weightEdit = (EditText) v.findViewById(R.id.profile_weight_editText);
        weightEdit.setText("122");
        setEditable(weightEdit, false);


        changeInfo = (Button) v.findViewById(R.id.profile_changeinfo_button);
        changeInfo.setOnClickListener(this);

        return v;


    }

    @Override
    public void onClick(View v) {

        if (isEditing) { //is editing, therefore save info
/*
            //Set values from the editTexts into the views
            String newName = nameEdit.getText().toString();
            nameView.setText(newName);
            String newEmail = emailEdit.getText().toString();
            emailView.setText(newEmail);
            String newBirthday = birthdayEdit.getText().toString();
            birthdayView.setText(newBirthday);

            //Set visibility of editTexts and Views
            nameView.setVisibility(View.VISIBLE);
            nameEdit.setVisibility(View.GONE);
            emailView.setVisibility(View.VISIBLE);
            emailEdit.setVisibility(View.GONE);
            birthdayView.setVisibility(View.VISIBLE);
            birthdayEdit.setVisibility(View.GONE);
*/

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
/*
            nameView.setVisibility(View.GONE);
            nameEdit.setVisibility(View.VISIBLE);

            nameEdit.setText(nameView.getText());

            emailView.setVisibility(View.GONE);
            emailEdit.setVisibility(View.VISIBLE);

            emailView.setText(emailView.getText());

            birthdayView.setVisibility(View.GONE);
            birthdayEdit.setVisibility(View.VISIBLE);

            birthdayView.setText(birthdayView.getText());
*/
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
    }

}

