package com.project.ms.njord.fragment;

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
import com.project.ms.njord.dialogFragments.StringRequestDialog;
import com.project.ms.njord.entity.DataManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener{

    final String TAG = "ProfileFragment";

    TextView nameView, birthdayView, emailView, heightView, weightView, genderView;
    LinearLayout nameLayout, birthdayLayout, emailLayout, genderLayout, heightLayout, weightLayout;

    private final int CHANGE_EMAIL      = 0;
    private final int CHANGE_NAME       = 1;
    private final int CHANGE_BIRTHDAY   = 2;
    private final int CHANGE_GENDER     = 3;
    private final int CHANGE_HEIGHT     = 4;
    private final int CHANGE_WEIGHT     = 5;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        emailView = (TextView) v.findViewById(R.id.profile_email_textView);
        emailView.setText(DataManager.dataManager.getProfile().getEmail());
        emailLayout = (LinearLayout) v.findViewById(R.id.profile_email_layout);
        emailLayout.setOnClickListener(this);

        nameView = (TextView) v.findViewById(R.id.profile_name_textView);
        nameView.setText(DataManager.dataManager.getProfile().getName());
        nameLayout = (LinearLayout) v.findViewById(R.id.profile_name_layout);
        nameLayout.setOnClickListener(this);

        birthdayView = (TextView) v.findViewById(R.id.profile_birthday_textView);
        birthdayView.setText(DataManager.dataManager.getProfile().getBirthday());
        birthdayLayout = (LinearLayout) v.findViewById(R.id.profile_birthday_layout);
        birthdayLayout.setOnClickListener(this);

        genderView = (TextView) v.findViewById(R.id.profile_gender_textView);
        genderView.setText(DataManager.dataManager.getProfile().getGender());
        genderLayout = (LinearLayout) v.findViewById(R.id.profile_gender_layout);
        genderLayout.setOnClickListener(this);

        heightView = (TextView) v.findViewById(R.id.profile_height_textView);
        heightView.setText(Integer.toString(DataManager.dataManager.getProfile().getHeight()));
        heightLayout = (LinearLayout) v.findViewById(R.id.profile_height_layout);
        heightLayout.setOnClickListener(this);

        weightView = (TextView) v.findViewById(R.id.profile_weight_textView);
        weightView.setText(Integer.toString(DataManager.dataManager.getProfile().getWeight()));
        weightLayout = (LinearLayout) v.findViewById(R.id.profile_weight_layout);
        weightLayout.setOnClickListener(this);

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            String s = data.getExtras().getString("userInput", "not found");
            Log.d(TAG, "userInput: " + s);

            if (requestCode == CHANGE_EMAIL) {
                emailView.setText(s);
                DataManager.dataManager.getProfile().setEmail(s);
            }

            if (requestCode == CHANGE_NAME) {
                nameView.setText(s);
                DataManager.dataManager.getProfile().setName(s);
            }

            if (requestCode == CHANGE_BIRTHDAY) {
                birthdayView.setText(s);
                DataManager.dataManager.getProfile().setBirthday(s);
            }

            if (requestCode == CHANGE_GENDER) {
                genderView.setText(s);
                DataManager.dataManager.getProfile().setGender(s);
            }

            if (requestCode == CHANGE_HEIGHT) {
                heightView.setText(s);
                DataManager.dataManager.getProfile().setHeight(Integer.parseInt(s));

            }

            if (requestCode == CHANGE_WEIGHT) {
                weightView.setText(s);
                DataManager.dataManager.getProfile().setWeight(Integer.parseInt(s));

            }
        }
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG,"Onclick: " + v.toString());

        Bundle args = new Bundle();

        if (v == emailLayout) {
            DialogFragment newFragment = new StringRequestDialog();

            args.putString("title", "email");
            args.putString("message", "edit email");

            newFragment.setTargetFragment(this, 0);
            newFragment.setArguments(args);
            newFragment.show(getActivity().getSupportFragmentManager(), "email dialog");
        }

        if(v == nameLayout) {
               DialogFragment newFragment = new StringRequestDialog();

               args.putString("title", "name");
               args.putString("message", "edit name");

               newFragment.setTargetFragment(this, 1);
               newFragment.setArguments(args);
               newFragment.show(getActivity().getSupportFragmentManager(), "name dialog");
        }

        if (v == birthdayLayout) {
            DialogFragment newFragment = new DateRequestDialog();

            args.putString("title", "birthday");
            args.putString("message", "select birthday");

            newFragment.setTargetFragment(this, 2);
            newFragment.setArguments(args);
            newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
        }

        if(v == genderLayout) {

        }

        if (v == heightLayout) {

        }

        if (v == weightLayout) {


        }
    }
}

