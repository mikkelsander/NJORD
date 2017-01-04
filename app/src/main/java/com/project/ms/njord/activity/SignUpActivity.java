package com.project.ms.njord.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.project.ms.njord.R;
import com.project.ms.njord.entity.DataManager;
import com.project.ms.njord.fragment.DatePickerFragment;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemSelectedListener {

    // Controller references
    private DataManager con;

    // UI references
    private TextView titleView, birthdayView;
    private EditText nameView, heigtView, weigthView;
    private Spinner genderView;
    private Button confirmButton;
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setTitle("Sign up");


        // Initialize views
        titleView = (TextView) findViewById(R.id.signUp_title_textView);

        nameView = (EditText) findViewById(R.id.signUp_name_editText);

        birthdayView = (TextView) findViewById(R.id.signUp_birthday_textView);
        birthdayView.setOnClickListener(this);


        Spinner spinner = (Spinner) findViewById(R.id.signUp_gender_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        heigtView = (EditText) findViewById(R.id.signUp_height_editText);
        weigthView = (EditText) findViewById(R.id.signUp_weight_editText);

        confirmButton = (Button) findViewById(R.id.signUp_confirm_button);
        confirmButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == confirmButton) {
            attemptConfirm();
        }
        if (v == birthdayView) {
            showDatePickerDialog(v);
        }
    }

    private void attemptConfirm() {
        // TODO: save all user input
        if (userInputValid()) {
            DataManager.dataManager.getProfile().setName(nameView.getText().toString());
            DataManager.dataManager.getProfile().setBirthday(birthdayView.getText().toString());
            DataManager.dataManager.getProfile().setGender(gender);
            //  DataManager.dataManager.getProfile().setHeight(heigtView.getText().toString()));


            finish();
        }
    }

    private boolean userInputValid() {
        // TODO: validate all user input
        return true;
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        gender = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}
