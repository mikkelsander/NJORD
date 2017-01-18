package com.project.ms.njord.fragments;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.ms.njord.R;
import com.project.ms.njord.dialogFragments.DateRequestDialog;
import com.project.ms.njord.dialogFragments.NumberPickerDialog;
import com.project.ms.njord.dialogFragments.StringRequestDialog;
import com.project.ms.njord.model.Singleton;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener{

    final String TAG = "ProfileFragment";

    TextView nameView, birthdayView, emailView, heightView, weightView, genderView;
    LinearLayout nameLayout, birthdayLayout, emailLayout, genderLayout, heightLayout, weightLayout;

    private final int CHANGE_EMAIL      = 1;
    private final int CHANGE_NAME       = 2;
    private final int CHANGE_BIRTHDAY   = 3;
    private final int CHANGE_GENDER     = 4;
    private final int CHANGE_HEIGHT     = 5;
    private final int CHANGE_WEIGHT     = 6;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        emailView = (TextView) v.findViewById(R.id.profile_email_textView);
        emailView.setText(Singleton.instance.getProfile().getEmail());
        emailLayout = (LinearLayout) v.findViewById(R.id.profile_email_layout);
        emailLayout.setOnClickListener(this);

        nameView = (TextView) v.findViewById(R.id.profile_name_textView);
        nameView.setText(Singleton.instance.getProfile().getName());
        nameLayout = (LinearLayout) v.findViewById(R.id.profile_name_layout);
        nameLayout.setOnClickListener(this);

        birthdayView = (TextView) v.findViewById(R.id.profile_birthday_textView);
        birthdayView.setText(Singleton.instance.getProfile().getBirthday());
        birthdayLayout = (LinearLayout) v.findViewById(R.id.profile_birthday_layout);
        birthdayLayout.setOnClickListener(this);

        genderView = (TextView) v.findViewById(R.id.profile_gender_textView);
        genderView.setText(Singleton.instance.getProfile().getGender());
        genderLayout = (LinearLayout) v.findViewById(R.id.profile_gender_layout);
        genderLayout.setOnClickListener(this);

        heightView = (TextView) v.findViewById(R.id.profile_height_textView);
        heightView.setText(Singleton.instance.getProfile().getHeight());
        heightLayout = (LinearLayout) v.findViewById(R.id.profile_height_layout);
        heightLayout.setOnClickListener(this);

        weightView = (TextView) v.findViewById(R.id.profile_weight_textView);
        weightView.setText(Singleton.instance.getProfile().getWeight());
        weightLayout = (LinearLayout) v.findViewById(R.id.profile_weight_layout);
        weightLayout.setOnClickListener(this);

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            String userString = data.getExtras().getString("userString", "");
            int userInt = data.getExtras().getInt("userInt");

            Log.d(TAG, "userInput: " + userString);

            if (requestCode == CHANGE_EMAIL) {
                emailView.setText(userString);
                Singleton.instance.getProfile().setEmail(userString);
            }

            if (requestCode == CHANGE_NAME) {
                nameView.setText(userString);
                Singleton.instance.getProfile().setName(userString);
            }

            if (requestCode == CHANGE_BIRTHDAY) {
                birthdayView.setText(userString);
                Singleton.instance.getProfile().setBirthday(userString);
            }

            if (requestCode == CHANGE_GENDER) {

                if (userInt == 0) {
                    genderView.setText("Male");
                    Singleton.instance.getProfile().setGender("Male");
                }

                if (userInt == 1) {
                    genderView.setText("Female");
                    Singleton.instance.getProfile().setGender("Female");
                }
            }

            if (requestCode == CHANGE_HEIGHT) {
                heightView.setText(Integer.toString(userInt));
                Singleton.instance.getProfile().setHeight(userString);

            }

            if (requestCode == CHANGE_WEIGHT) {
                weightView.setText(Integer.toString(userInt));
                Singleton.instance.getProfile().setWeight(userString);
            }

            Singleton.instance.getDataBaseManager().saveProfile(Singleton.instance.getProfile());
        }
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG,"Onclick: " + v.toString());

        Bundle args = new Bundle();

        if (v == emailLayout) {
            DialogFragment dialog = new StringRequestDialog();

            args.putString("title", "Email");

            dialog.setTargetFragment(this, CHANGE_EMAIL);
            dialog.setArguments(args);
            dialog.show(getActivity().getSupportFragmentManager(), "email dialog");
        }

        if(v == nameLayout) {
               DialogFragment dialog = new StringRequestDialog();

               args.putString("title", "Name");

               dialog.setTargetFragment(this, CHANGE_NAME);
               dialog.setArguments(args);
               dialog.show(getActivity().getSupportFragmentManager(), "name dialog");
        }

        if (v == birthdayLayout) {
            DialogFragment dialog = new DateRequestDialog();

            args.putString("title", "Birthday");

            dialog.setTargetFragment(this, CHANGE_BIRTHDAY);
            dialog.setArguments(args);
            dialog.show(getActivity().getSupportFragmentManager(), "dateView picker");
        }

        if(v == genderLayout) {
            DialogFragment dialog = new NumberPickerDialog();

            args.putString("title", "Gender");
            args.putInt("code", CHANGE_GENDER);

            dialog.setTargetFragment(this, CHANGE_GENDER);
            dialog.setArguments(args);
            dialog.show(getActivity().getSupportFragmentManager(), "dateView dialog");
        }

        if (v == heightLayout) {
            DialogFragment dialog = new NumberPickerDialog();

            args.putString("title", "Height");
            args.putInt("code", CHANGE_HEIGHT);

            dialog.setTargetFragment(this, CHANGE_HEIGHT);
            dialog.setArguments(args);
            dialog.show(getActivity().getSupportFragmentManager(), "height dialog");
        }

        if (v == weightLayout) {
            DialogFragment dialog = new NumberPickerDialog();

            args.putString("title", "Weight");
            args.putInt("code", CHANGE_WEIGHT);

            dialog.setTargetFragment(this, CHANGE_WEIGHT);
            dialog.setArguments(args);
            dialog.show(getActivity().getSupportFragmentManager(), "weight dialog");

        }
    }
}

