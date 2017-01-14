package com.project.ms.njord.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.project.ms.njord.R;
import com.project.ms.njord.activity.ResultsActivity;
import com.project.ms.njord.entity.DataManager;
import com.project.ms.njord.simulator.DataSimulator;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class ManometerFragment extends Fragment implements View.OnClickListener, Observer {

    private DataSimulator simData;
    private int inhaleLevel;
    private int exhaleLevel;
    private Date testDate;
    View v;

    //UI references
    TextView lungLevelLive;
    TextView test1;
    TextView test2;
    TextView test3;
    TextView instructions;
    Button doneBtn, startBtn;

    public ManometerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_manometer, container, false);

        simData = new DataSimulator();
        simData.addObserver(this);

        instructions = (TextView)v.findViewById(R.id.manomater_userInstructions_textView);
        lungLevelLive = (TextView)v.findViewById(R.id.manometer_level_textView);
        test1 = (TextView)v.findViewById(R.id.manometer_test1_textView);
        test2 = (TextView)v.findViewById(R.id.manometer_test2_textView);
        test3 = (TextView)v.findViewById(R.id.manometer_test3_textView);




        return v;
    }



    @Override
    public void onClick(View v) {

    }

    /**
     * Starts a new test reading
     */
    public void StartReading(boolean choice){

        new AsyncTask<Boolean, Void, Void>() {
            @Override
            protected Void doInBackground(Boolean... params) {

                if(params[0]){
                    simData.generateExhale();
                }else{
                    simData.generateInhale();
                }
                return null;
            }

            @Override
            public void onPostExecute(Void param) {
                //TODO:Update view
                test1.setText(Integer.toString(testReading()));
            }
        }.execute(choice);
    }

    /**
     * Saves the test result as a new TestRests object
     */
    public void saveResult(){
        DataManager.dataManager.createTetsResult(testDate, inhaleLevel, exhaleLevel);
    }

    /**
     * Calculates the average lung level
     */
    private int average(int test1, int test2, int test3){
        return (test1+test2+test3)/3;
    }

    private int testReading(){
        return 11;
    }

    @Override
    public void update(Observable o, Object arg) {

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                lungLevelLive.setText(Integer.toString(simData.getExhalePressure()));
            }
        };

        getActivity().runOnUiThread(runnable);

    }
}
