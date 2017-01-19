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

    Button danish, english;
    MediaPlayer danishSound;
    MediaPlayer englishSound;

    public DeStressFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_de_stress, container, false);

        danish = (Button) v.findViewById(R.id.destress_danish_button);
        english = (Button) v.findViewById(R.id.destress_english_button);

        danish.setOnClickListener(this);
        english.setOnClickListener(this);

        danishSound = MediaPlayer.create(getActivity(), R.raw.aerofitdansk);
        englishSound = MediaPlayer.create(getActivity(), R.raw.aerofiteng);


        return v;
    }

    public void onClick(View v) {

        if (v == danish) {

            if (!danishSound.isPlaying() && !englishSound.isPlaying()) {
                Log.d("sound", "playing sound");
                danishSound.start();
            } else if (danishSound.isPlaying()) {
                Log.d("sound", "stopping sound");
                danishSound.pause();
            }
        }

        if (v == english) {

            if (!englishSound.isPlaying() && !danishSound.isPlaying()) {
                Log.d("sound", "playing sound");
                englishSound.start();
            } else if (englishSound.isPlaying()){
                Log.d("sound", "stopping sound");
                englishSound.pause();
            }
        }


    }

}
