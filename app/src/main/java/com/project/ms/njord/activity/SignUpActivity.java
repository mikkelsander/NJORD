package com.project.ms.njord.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.project.ms.njord.R;
import com.project.ms.njord.dialogFragments.DateRequestDialog;
import com.project.ms.njord.entity.Singleton;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemSelectedListener {

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

    // creating skip button in the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.skip_button, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handles what happens when skip button is pressed
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Closes SignUpActivity and starts MainActivity
        if (id == R.id.skip_button) {
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onClick(View v) {
        if (v == confirmButton){
            attemptConfirm();
        }
        if (v == birthdayView) {
            birthdayView.setText("22/01/1991");
        }
    }

    private void attemptConfirm() {
        // TODO: save all user input
        if (userInputValid()) {
            Singleton.instance.getProfile().setName(nameView.getText().toString());
            Singleton.instance.getProfile().setBirthday(birthdayView.getText().toString());
            Singleton.instance.getProfile().setGender(gender);
            Singleton.instance.getProfile().setHeight(heigtView.getText().toString());
            Singleton.instance.getProfile().setWeight(weigthView.getText().toString());

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    private boolean userInputValid() {
        // TODO: validate all user input

        if(heigtView.getText().toString().equals("")) return false;
        if(weigthView.getText().toString().equals("")) return false;


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
        DialogFragment newFragment = new DateRequestDialog();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }



}
