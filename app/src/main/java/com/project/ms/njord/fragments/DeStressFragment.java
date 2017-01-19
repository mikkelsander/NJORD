package com.project.ms.njord.fragments;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.project.ms.njord.R;

import static com.project.ms.njord.R.id.imageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeStressFragment extends Fragment implements View.OnClickListener {

    Button danish, english;
    MediaPlayer danishSound;
    MediaPlayer englishSound;
    ImageView playEnglishImage, playDanishImage, pauseDanishImage, pauseEnglishImage;

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

        playEnglishImage = (ImageView)v.findViewById(R.id.destress_englishPlay_image);
        playDanishImage = (ImageView)v.findViewById(R.id.destress_danishPlay_image);
        pauseEnglishImage = (ImageView)v.findViewById(R.id.destress_englishPause_image);
        pauseDanishImage = (ImageView)v.findViewById(R.id.destress_danishPause_image);


        danishSound = MediaPlayer.create(getActivity(), R.raw.aerofitdansk);
        englishSound = MediaPlayer.create(getActivity(), R.raw.aerofiteng);


        return v;
    }

    public void onClick(View v) {

        if (v == danish) {

            if (!danishSound.isPlaying() && !englishSound.isPlaying()) {
                Log.d("sound", "playing sound Danish");
                danishSound.start();
                playDanishImage.setVisibility(View.INVISIBLE);
                pauseDanishImage.setVisibility(View.VISIBLE);
            } else if (danishSound.isPlaying()) {
                Log.d("sound", "pausing sound Danish");
                danishSound.pause();
                playDanishImage.setVisibility(View.VISIBLE);
                pauseDanishImage.setVisibility(View.INVISIBLE);
            }
        }

        if (v == english) {

            if (!englishSound.isPlaying() && !danishSound.isPlaying()) {
                Log.d("sound", "playing sound Enlish");
                englishSound.start();
                playEnglishImage.setVisibility(View.INVISIBLE);
                pauseEnglishImage.setVisibility(View.VISIBLE);
            } else if (englishSound.isPlaying()){
                Log.d("sound", "pausing sound English");
                englishSound.pause();
                playEnglishImage.setVisibility(View.VISIBLE);
                pauseEnglishImage.setVisibility(View.INVISIBLE);
            }
        }


    }

}
