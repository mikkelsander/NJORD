package com.project.ms.njord.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.ms.njord.R;
import com.project.ms.njord.activity.MainActivity;
import com.project.ms.njord.dialogFragments.DateRequestDialog;
import com.project.ms.njord.dialogFragments.NumberPickerDialog;
import com.project.ms.njord.entity.Singleton;

import static io.fabric.sdk.android.Fabric.TAG;

public class SignUpFragment extends Fragment implements View.OnClickListener,
        AdapterView.OnItemSelectedListener, View.OnFocusChangeListener {

    // UI references
    private EditText nameView,genderView, heightView, weightView, birthdayView;
    private Button confirmButton;

    private final int CHANGE_BIRTHDAY   = 3;
    private final int CHANGE_GENDER     = 4;
    private final int CHANGE_HEIGHT     = 5;
    private final int CHANGE_WEIGHT     = 6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_sign_up, container, false);

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

    // creating skip button in the action bar
  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.skip_button, menu);
        return super.onCreateOptionsMenu(menu);
    }*/

    // handles what happens when skip button is pressed

   // @Override
  /*  public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Closes SignUpFragment and starts MainActivity
        if (id == R.id.skip_button) {
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
*/

    @Override
    public void onClick(View v) {
        Bundle args = new Bundle();

        if (v == confirmButton){
            attemptConfirm();
        }

    }

    private void attemptConfirm() {
        // TODO: save all user input
        if (userInputValid()) {
            Singleton.instance.getProfile().setName(nameView.getText().toString());
            Singleton.instance.getProfile().setBirthday(birthdayView.getText().toString());
            Singleton.instance.getProfile().setGender(genderView.getText().toString());
            Singleton.instance.getProfile().setHeight(heightView.getText().toString());
            Singleton.instance.getProfile().setWeight(weightView.getText().toString());

            Intent i = new Intent(getActivity(), MainActivity.class);
            startActivity(i);
            getActivity().finish();
        }
    }

    private boolean userInputValid() {
        // TODO: validate all user input

        if(heightView.getText().toString().equals("")) return false;
        if(weightView.getText().toString().equals("")) return false;


        return true;
    }

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
            DialogFragment dialog = new DateRequestDialog();

            args.putString("title", "birthday");
            args.putString("message", "select birthday");

            dialog.setTargetFragment(this, CHANGE_BIRTHDAY);
            dialog.setArguments(args);
            dialog.show(getActivity().getSupportFragmentManager(), "date picker");
        }

        if(v == genderView) {
            DialogFragment dialog = new NumberPickerDialog();

            args.putString("title", "gender");
            args.putString("message", "select gender");
            args.putInt("code", CHANGE_GENDER);

            dialog.setTargetFragment(this, CHANGE_GENDER);
            dialog.setArguments(args);
            dialog.show(getActivity().getSupportFragmentManager(), "date dialog");
        }

        if (v == heightView) {
            DialogFragment dialog = new NumberPickerDialog();

            args.putString("title", "height");
            args.putString("message", "select height");
            args.putInt("code", CHANGE_HEIGHT);

            dialog.setTargetFragment(this, CHANGE_HEIGHT);
            dialog.setArguments(args);
            dialog.show(getActivity().getSupportFragmentManager(), "height dialog");
        }

        if (v == weightView) {
            DialogFragment dialog = new NumberPickerDialog();

            args.putString("title", "weight");
            args.putString("message", "select weight");
            args.putInt("code", CHANGE_WEIGHT);

            dialog.setTargetFragment(this, CHANGE_WEIGHT);
            dialog.setArguments(args);
            dialog.show(getActivity().getSupportFragmentManager(), "weight dialog");

        }

    }
}
