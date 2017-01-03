package com.project.ms.njord.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.ms.njord.R;
import com.project.ms.njord.entity.DataManager;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    // Controller references
    private DataManager con;

    // UI references
    private TextView titleView;
    private EditText nameView, birthdayView, genderView, heigtView, weigthView;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setTitle("Sign up");




        // Initialize views
        titleView = (TextView) findViewById(R.id.signUp_title_textView);

        nameView = (EditText) findViewById(R.id.signUp_name_editText);
        birthdayView = (EditText) findViewById(R.id.signUp_birthday_editText);
        genderView = (EditText) findViewById(R.id.signUp_gender_editText);
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
            //con.getProfile().setName(nameView.getText().toString());
            finish();
        }
    }

    private boolean userInputValid() {
        // TODO: validate all user input
        return true;
    }
}
