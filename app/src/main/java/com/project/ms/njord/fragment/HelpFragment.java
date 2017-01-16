package com.project.ms.njord.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.project.ms.njord.R;
import com.project.ms.njord.activity.YouTubeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends Fragment implements View.OnClickListener {

    Button watch1, watch2, watch3, watch4, watch5, watch6, watch7, watch8;
    TextView intro;

    public HelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_help, container, false);


        watch1 = (Button) v.findViewById(R.id.help_watch1);
        watch1.setOnClickListener(this);

        watch2 = (Button) v.findViewById(R.id.help_watch2);
        watch2.setOnClickListener(this);

        watch3 = (Button) v.findViewById(R.id.help_watch3);
        watch3.setOnClickListener(this);

        watch4 = (Button) v.findViewById(R.id.help_watch4);
        watch4.setOnClickListener(this);

        watch5 = (Button) v.findViewById(R.id.help_watch5);
        watch5.setOnClickListener(this);

        watch6 = (Button) v.findViewById(R.id.help_watch6);
        watch6.setOnClickListener(this);

        watch7 = (Button) v.findViewById(R.id.help_watch7);
        watch7.setOnClickListener(this);

        watch8 = (Button) v.findViewById(R.id.help_watch8);
        watch8.setOnClickListener(this);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v) {

        if (v == watch1) {

            Intent i = new Intent(getActivity(), YouTubeActivity.class);
            i.putExtra("Address", "El69SuPPFa0");
            startActivity(i);

        } if (v == watch2) {

            Intent i = new Intent(getActivity(), YouTubeActivity.class);
            i.putExtra("Address", "4htzPVbYH7U");
            startActivity(i);

        } if (v == watch3) {

            Intent i = new Intent(getActivity(), YouTubeActivity.class);
            i.putExtra("Address", "39dlHXa77R0");
            startActivity(i);

        } if (v == watch4) {

            Intent i = new Intent(getActivity(), YouTubeActivity.class);
            i.putExtra("Address", "El69SuPPFa0");
            startActivity(i);

        } if (v == watch5) {

            Intent i = new Intent(getActivity(), YouTubeActivity.class);
            i.putExtra("Address", "F_pI1Zbz9ds");
            startActivity(i);

        }if (v == watch6) {

            Intent i = new Intent(getActivity(), YouTubeActivity.class);
            i.putExtra("Address", "T8gxfAsxe3o");
            startActivity(i);

        } if (v == watch7) {

            Intent i = new Intent(getActivity(), YouTubeActivity.class);
            i.putExtra("Address", "1cE5xgssJDY");
            startActivity(i);

        } if (v == watch8) {

            Intent i = new Intent(getActivity(), YouTubeActivity.class);
            i.putExtra("Address", "P85Pf0IDMog");
            startActivity(i);
        }
    }




}
