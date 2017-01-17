package com.project.ms.njord.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.project.ms.njord.R;
import com.project.ms.njord.activities.TestLungsActivity;
import com.project.ms.njord.simulator.DataSimulator;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button beginTestBtn;
    private DataSimulator dataSimulator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        // Inflates the layout for this fragment
        beginTestBtn = (Button) v.findViewById(R.id.home_beginTest_button);
        beginTestBtn.setOnClickListener(this);

        dataSimulator = new DataSimulator();

        return v;
    }

    public void onClick(View v) {
        if(v == beginTestBtn){
            // dataSimulator.generateExhale();
           //  dataSimulator.generateInhale();
            Intent i = new Intent(getActivity(), TestLungsActivity.class);
            startActivity(i);
        }


    }
}
