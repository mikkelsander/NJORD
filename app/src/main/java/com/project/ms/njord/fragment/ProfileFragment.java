package com.project.ms.njord.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.project.ms.njord.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    TextView nameView;
    EditText nameEdit;

    TextView emailView;
    EditText emailEdit;

    TextView birthdayView;
    EditText birthdayEdit;

    TextView genderView;
    EditText genderEdit;

    TextView heightView;
    EditText heightEdit;

    TextView weightView;
    EditText weightEdit;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        nameView = (TextView) v.findViewById(R.id.profile_name_textView);
        nameEdit = (EditText) v.findViewById(R.id.profile_name_editText);





        return v;


    }
}

