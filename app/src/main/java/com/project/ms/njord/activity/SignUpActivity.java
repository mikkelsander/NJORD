package com.project.ms.njord.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.project.ms.njord.R;
import com.project.ms.njord.entity.DataManager;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    // Controller references
    private DataManager con;

    // UI references
    private TextView titleView;
    private EditText nameView, birthdayView, heigtView, weigthView;
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
        birthdayView = (EditText) findViewById(R.id.signUp_birthday_editText);


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
        if (v == confirmButton){
            attemptConfirm();
        }
    }

    private void attemptConfirm() {
        // TODO: save all user input
        if(userInputValid()) {
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
}




