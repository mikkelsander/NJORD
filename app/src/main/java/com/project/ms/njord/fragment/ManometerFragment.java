package com.project.ms.njord.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.project.ms.njord.R;
import com.project.ms.njord.entity.Singleton;
import com.project.ms.njord.simulator.DataSimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class ManometerFragment extends Fragment implements Observer, View.OnClickListener {

    private DataSimulator simData;
    private int inhaleLevel = 0;
    private int exhaleLevel = 0;
    private Date testDate = new Date();
    private ArrayList<Integer> streamOfSpiroData = new ArrayList<>();
    int counter = 1;

    View v;

    int test1int, test2int, test3int;

    //UI references
    TextView lungLevelLive;
    TextView test1;
    TextView test2;
    TextView test3;
    TextView instructions;
    ImageView manometer;
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
        manometer = (ImageView)v.findViewById(R.id.manometer_imageView);

        startBtn = (Button) v.findViewById(R.id.manometer_start_button);
        startBtn.setOnClickListener(this);

        doneBtn = (Button)v.findViewById(R.id.manometer_done_button);
        doneBtn.setOnClickListener(this);

        counter =1;

        return v;
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
                if(counter == 1){
                    test1.setText(Integer.toString(highestReading(streamOfSpiroData)));
                    test1int = highestReading(streamOfSpiroData);
                }else if(counter==2){
                    test2.setText(Integer.toString(highestReading(streamOfSpiroData)));
                    test2int = highestReading(streamOfSpiroData);
                }else if(counter==3){
                    test3.setText(Integer.toString(highestReading(streamOfSpiroData)));
                    test3int = highestReading(streamOfSpiroData);
                }
                if(counter<3){
                    counter++;
                }else {
                    counter = 1;
                    inhaleLevel = average(test1int, test2int, test3int);
                    saveResult(testDate, inhaleLevel, exhaleLevel);
                }
            }
        }.execute(choice);
    }

    /**
     * Saves the test result as a new TestRests object
     */
    public void saveResult(Date date, int insp, int exp){
        Singleton.instance.getProfile().createTetsResult(date, insp, exp);
    }

    /**
     * Calculates the average lung level
     */
    private int average(int test1, int test2, int test3){
        return (test1+test2+test3)/3;
    }

    private int highestReading(ArrayList<Integer> list){
        int i = Collections.max(list);
        return i;
    }

    @Override
    public void update(Observable o, Object arg) {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int data = simData.getExhalePressure();
                lungLevelLive.setText(Integer.toString(data));
                streamOfSpiroData.add(data);
                manometer.getLayoutParams().height = (simData.getExhalePressure()*10);

            }
        };
        getActivity().runOnUiThread(runnable);

    }

    @Override
    public void onClick(View v) {

    }
}
