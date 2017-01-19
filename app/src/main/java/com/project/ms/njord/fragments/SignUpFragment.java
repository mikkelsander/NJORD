package com.project.ms.njord.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.ms.njord.R;
import com.project.ms.njord.activities.MainActivity;
import com.project.ms.njord.dialogFragments.DateRequestDialog;
import com.project.ms.njord.dialogFragments.NumberPickerDialog;
import com.project.ms.njord.model.Profile;
import com.project.ms.njord.model.Singleton;

import static io.fabric.sdk.android.Fabric.TAG;

public class SignUpFragment extends Fragment implements View.OnClickListener,
        AdapterView.OnItemSelectedListener, View.OnFocusChangeListener {

    // UI references
    private EditText nameView,genderView, heightView, weightView, birthdayView;
    private Button confirmButton, skipButton;
    private Profile profile;
    private SharedPreferences prefs;

    private final int CHANGE_BIRTHDAY   = 3;
    private final int CHANGE_GENDER     = 4;
    private final int CHANGE_HEIGHT     = 5;
    private final int CHANGE_WEIGHT     = 6;
    private InputMethodManager imm;

    String email;
    String password;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);

        // Initialize views
        nameView = (EditText) v.findViewById(R.id.signUp_name_editText);
        birthdayView = (EditText) v.findViewById(R.id.signUp_birthday_editText);
        birthdayView.setOnFocusChangeListener(this);
        genderView = (EditText) v.findViewById(R.id.signUp_gender_editText);
        genderView.setOnFocusChangeListener(this);
        heightView = (EditText) v.findViewById(R.id.signUp_height_editText);
        heightView.setOnFocusChangeListener(this);
        weightView = (EditText) v.findViewById(R.id.signUp_weight_editText);
        weightView.setOnFocusChangeListener(this);
        confirmButton = (Button) v.findViewById(R.id.signUp_confirm_button);
        confirmButton.setOnClickListener(this);
        skipButton = (Button) v.findViewById(R.id.signUp_skip_button);
        skipButton.setOnClickListener(this);

        imm = (InputMethodManager)getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);


        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        profile = Singleton.instance.getProfile();

        email = getArguments().getString("email", "change me");
        password = getArguments().getString("password", "1234");

        return v;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            String userString = data.getExtras().getString("userString", "not found");
            int userInt = data.getExtras().getInt("userInt");
            Log.d(TAG, "userInput: " + userString);
            if (requestCode == CHANGE_BIRTHDAY) {
                birthdayView.setText(userString);
            }
            if (requestCode == CHANGE_GENDER) {

                if (userInt == 0) {
                    genderView.setText("Male");
                }
                if (userInt == 1) {
                    genderView.setText("Female");
                }
            }
            if (requestCode == CHANGE_HEIGHT) {
                heightView.setText(Integer.toString(userInt));
            }
            if (requestCode == CHANGE_WEIGHT) {
                weightView.setText(Integer.toString(userInt));
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == confirmButton){
            confirm();
        }
        if (v == skipButton){
            Singleton.instance.createProfile(email, password);
            prefs.edit().putBoolean("isLoggedIn", true).commit();
            prefs.edit().putString("active_email", profile.getEmail()).commit();
            Intent i = new Intent(getActivity(), MainActivity.class);
            startActivity(i);
            getActivity().finish();

        }

    }



    private void confirm() {
        // TODO: save all user input

        Singleton.instance.createProfile(email, password);

        profile.setName(nameView.getText().toString());
        profile.setBirthday(birthdayView.getText().toString());
        profile.setGender(genderView.getText().toString());
        profile.setHeight(heightView.getText().toString());
        profile.setWeight(weightView.getText().toString());

        Singleton.instance.getDataBaseManager().saveProfile(profile);

        prefs.edit().putBoolean("isLoggedIn", true).commit();
        prefs.edit().putString("active_email", profile.getEmail()).commit();

        Toast.makeText(getActivity(), "saving data", Toast.LENGTH_SHORT ).show();

        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
        getActivity().finish();

    }


    /*private boolean userInputValid() {
        // TODO: validate all user input

        if(heightView.getText().toString().equals("")) return false;
        if(weightView.getText().toString().equals("")) return false;

        return true;
    }
*/
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Bundle args = new Bundle();

        if (v == birthdayView) {
            if(v.isFocused()) {
                DialogFragment dialog = new DateRequestDialog();

                args.putString("title", "Date of birth");
                args.putString("message", "select birthday");

                dialog.setTargetFragment(this, CHANGE_BIRTHDAY);
                dialog.setArguments(args);
                dialog.show(getActivity().getSupportFragmentManager(), "dateView picker");
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);


            }
        }

        if(v == genderView) {
            if(v.isFocused()) {
                DialogFragment dialog = new NumberPickerDialog();

                args.putString("title", "gender");
                args.putString("message", "select gender");
                args.putInt("code", CHANGE_GENDER);

                dialog.setTargetFragment(this, CHANGE_GENDER);
                dialog.setArguments(args);
                dialog.show(getActivity().getSupportFragmentManager(), "dateView dialog");
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            }
        }

        if (v == heightView) {
            if(v.isFocused()) {
                DialogFragment dialog = new NumberPickerDialog();

                args.putString("title", "height");
                args.putString("message", "select height");
                args.putInt("code", CHANGE_HEIGHT);

                dialog.setTargetFragment(this, CHANGE_HEIGHT);
                dialog.setArguments(args);
                dialog.show(getActivity().getSupportFragmentManager(), "height dialog");

                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }

        if (v == weightView) {
            if(v.isFocused()) {
                DialogFragment dialog = new NumberPickerDialog();

                args.putString("title", "weight");
                args.putString("message", "select weight");
                args.putInt("code", CHANGE_WEIGHT);

                dialog.setTargetFragment(this, CHANGE_WEIGHT);
                dialog.setArguments(args);
                dialog.show(getActivity().getSupportFragmentManager(), "weight dialog");
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }

    }
}
