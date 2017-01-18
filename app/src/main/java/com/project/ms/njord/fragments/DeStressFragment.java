package com.project.ms.njord.fragments;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.project.ms.njord.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeStressFragment extends Fragment implements View.OnClickListener {


    Button danish,english;

    public DeStressFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_de_stress, container, false);

        danish = (Button) rootView.findViewById(R.id.destress_danish_button);

        english = (Button) rootView.findViewById(R.id.destress_english_button);

        final MediaPlayer sound = MediaPlayer.create(getActivity(), R.raw.aerofiteng);

        danish.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                sound.setLooping(true);
                Log.d("sound", "playing sound");
                sound.start();
            }
        });

        return rootView;

    }

    @Override
    public void onClick(View v) {



    }
}
