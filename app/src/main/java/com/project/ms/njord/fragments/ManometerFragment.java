package com.project.ms.njord.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.project.ms.njord.R;
import com.project.ms.njord.model.Profile;
import com.project.ms.njord.model.Singleton;
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
    private Profile profile = Singleton.instance.getProfile();
    private int counter = 1;
    boolean isReadingData;
    private AsyncTask task;

    private View v;
    private  int test1int, test2int, test3int;

    //UI references
    private TextView lungLevelLive;
    private TextView test1,test2, test3, test4, test5, test6;
    private  TextView instructions;
    private ImageView manometer;
    private Button doneBtn, startBtn;

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
        test4 = (TextView)v.findViewById(R.id.manometer_test4_textView);
        test5 = (TextView)v.findViewById(R.id.manometer_test5_textView);
        test6 = (TextView)v.findViewById(R.id.manometer_test6_textView);

        manometer = (ImageView)v.findViewById(R.id.manometer_imageView);

        startBtn = (Button) v.findViewById(R.id.manometer_start_button);
        startBtn.setOnClickListener(this);

        doneBtn = (Button)v.findViewById(R.id.manometer_done_button);
        doneBtn.setOnClickListener(this);

        counter =1;
        setRetainInstance(true);

        return v;
    }

    /**
     * Starts a new test reading
     */
    private void startReading(){
        instructions.setText("");
        isReadingData = true;
        if(counter==4){
            modifyScreeanLook();
        }
        task = new AsyncTask<Boolean, Void, Void>() {
            @Override
            protected Void doInBackground(Boolean... params) {
                if(counter<4){
                    simData.generateInhale();
                }else{
                    simData.generateExhale();
                }
                return null;
            }
            @Override
            public void onPostExecute(Void param) {

                // Inhale begins
                if(counter == 1){
                    test1.setText(Integer.toString(highestReading(streamOfSpiroData)));
                    test1int = highestReading(streamOfSpiroData);
                    streamOfSpiroData.clear();
                    inhaleLevel = average(test1int, 0, 0);
                    counter++;
                    instructions.setText("Great now do it again");
                }else if(counter == 2){
                    test2.setText(Integer.toString(highestReading(streamOfSpiroData)));
                    test2int = highestReading(streamOfSpiroData);
                    streamOfSpiroData.clear();
                    inhaleLevel = average(test1int, test2int, 0);
                    instructions.setText("Great now do it again");
                    counter++;
                }else if(counter == 3){
                    test3.setText(Integer.toString(highestReading(streamOfSpiroData)));
                    test3int = highestReading(streamOfSpiroData);
                    streamOfSpiroData.clear();
                    inhaleLevel = average(test1int, test2int, test3int);
                    instructions.setText("Great! Now  fill your lungs and exhale as hard as you can");
                    counter++;
                }

                // Exhale begins
                else if(counter == 4){
                    test4.setText(Integer.toString(highestReading(streamOfSpiroData)));
                    test1int = highestReading(streamOfSpiroData);
                    streamOfSpiroData.clear();
                    exhaleLevel = average(test1int, 0, 0);
                    instructions.setText("Great now do it again");
                    counter++;
                }else if(counter == 5) {
                    test5.setText(Integer.toString(highestReading(streamOfSpiroData)));
                    test2int = highestReading(streamOfSpiroData);
                    streamOfSpiroData.clear();
                    exhaleLevel = average(test1int, test2int, 0);
                    instructions.setText("Great now do it again");
                    counter++;
                }else if(counter == 6) {
                    test6.setText(Integer.toString(highestReading(streamOfSpiroData)));
                    test3int = highestReading(streamOfSpiroData);
                    streamOfSpiroData.clear();
                    exhaleLevel = average(test1int, test2int, test3int);
                    instructions.setText("All good, press done to finish test");
                    startBtn.setEnabled(false);


                }

                isReadingData = false;
            }
        }.execute();
    }

    private void modifyScreeanLook() {
        manometer.setImageResource(R.drawable.manometer_red);
    }

    /**
     * Saves the test result as a new TestRests object
     */
    private void saveResult(Date date, int insp, int exp){
        profile.createTestResult(date, insp, exp);
        Singleton.instance.getDataBaseManager().saveProfile(profile);
    }

    /**
     * Calculates the average lung level
     */
    private int average(int test1, int test2, int test3){
        return (test1+test2+test3)/3;
    }

    private int highestReading(ArrayList<Integer> list){
        return Collections.max(list);
    }

    @Override
    public void update(Observable o, Object arg) {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int data;

                if(counter<4){
                    data = simData.getInhalePressure();
                }else data = simData.getExhalePressure();

                lungLevelLive.setText(Integer.toString(data));
                streamOfSpiroData.add(data);
                manometer.getLayoutParams().height = (data*10);
            }
        };
        getActivity().runOnUiThread(runnable);
    }

    @Override
    public void onClick(View v) {
        if(v==startBtn && !isReadingData){
            startReading();
        }
        if (v == doneBtn && !isReadingData) {
            saveResult(testDate,inhaleLevel,exhaleLevel);
            Log.d("testdate",testDate.toString());

            //data to pass to ResultsFragment
            Bundle args = new Bundle();
            args.putInt("inhale", inhaleLevel);
            args.putInt("exhale", exhaleLevel);

            Fragment newFrag = new ResultsFragment();
            newFrag.setArguments(args);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.testLungs_fragment_container, newFrag)
                    .commit();

        }
    }
    @Override
    public void onStop() {
        if(isReadingData) task.cancel(true);
        super.onStop();
    }

}
