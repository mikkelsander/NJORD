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


    Button watch;
    TextView intro;

    public HelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_help, container, false);


        intro = (TextView) v.findViewById(R.id.fragment_help_info);
        intro.setText("Aerofit er udstyr til åndedrætstræning. Aerofit er udviklet, så du kan styrke og forbedre dit åndedræt på den mest effektive måde.\n" +
                "\n" +
                "Aerofit er et medicinsk udviklet personligt træningsudstyr, der hjælper sportsfolk, elite og motionsudøvere – både til vands og til lands, med at komme i bedre form og blive bedre til deres idræt. Aerofit virker ved at styrke åndedrætsmuskulaturen således at du får mere ilt til rådighed når du skal yde maximalt. Derved kan du arbejde hårdere og længere.\n" +
                "\n" +
                "Samtidig skaber Aerofit også succes for en lang række andre mennesker herunder musikere – sangere og alle typer blæsere samt ikke mindst skuespillere.\n" +
                "\n" +
                "Endelig har det vist sig at en række simple øvelser få minutter om dagen hjælper stressramte med at finde balancen igen, herunder få bedre søvn og højere livskvalitet.\n" +
                "\n" +
                "Lige nu træner en række af landets bedste idrætsudøvere med Aerofit træningssystem – med stor succes!\n" +
                "\n");

        watch = (Button) v.findViewById(R.id.fragment_help_watch);
        watch.setOnClickListener(this);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getActivity(),YouTubeActivity.class);
        startActivity(i);
    }
}
