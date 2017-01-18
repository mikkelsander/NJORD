package com.project.ms.njord.fragments;


import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.project.ms.njord.R;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeStress extends Fragment implements View.OnClickListener {


    final MediaPlayer mp = new MediaPlayer();
    Button danish,english;

    public DeStress() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_de_stress, container, false);

        danish = (Button) v.findViewById(R.id.destress_danish_button);
        danish.setOnClickListener(this);

        return v;

    }

    @Override
    public void onClick(View v) {

        if(mp.isPlaying())
        {
            mp.stop();
        }

        try {
            mp.reset();
            AssetFileDescriptor afd;
        //    afd = getAssets().openFd("Aerofit- ny modstand -musik.mp3");
         //   mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();
            mp.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
