package com.project.ms.njord.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.project.ms.njord.R;
import com.project.ms.njord.activity.MainActivity;


public class ResultsFragment extends Fragment implements View.OnClickListener {

    Button doneBtn;
    public ResultsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_results, container, false);
        // Inflate the layout for this fragment

        doneBtn = (Button) v.findViewById(R.id.fragment_results_doneBtn);
        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == doneBtn) {


        }

    }
}
