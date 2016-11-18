package com.project.ms.njord.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.project.ms.njord.R;
import com.project.ms.njord.activity.TestLungsActivity;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button beginTestBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        // Inflates the layout for this fragment
        beginTestBtn = (Button) v.findViewById(R.id.beginTestBtn);
        beginTestBtn.setOnClickListener(this);

        return v;
    }

    public void onClick(View v) {
        if(v == beginTestBtn){
            Intent i = new Intent(getActivity(), TestLungsActivity.class);
            startActivity(i);
        }
    }

}
