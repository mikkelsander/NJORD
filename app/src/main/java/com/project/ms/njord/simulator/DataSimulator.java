package com.project.ms.njord.simulator;

import android.util.Log;

import com.project.ms.njord.activity.TestLungsActivity;
import com.project.ms.njord.entity.DataManager;

import java.util.Observable;

/**
 * Created by Oliver on 05-Jan-17.
 */




public class DataSimulator extends Observable {


    private int inhalePressure = 60;
    private int exhalePressure = 60;


    public void generateExhale() {
        Log.d("simPressure", "generate exhale started");
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < startTime + 7000) {
            exhalePressure = (int) ((Math.random() * 0.1 + 1) * 60);
            Log.d("simPressure", "exp: " + Integer.toString(exhalePressure));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) { /* accept interruption*/}
        }

    }


    public void generateInhale() {
        Log.d("simPressure", "generate inhale started");
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < startTime + 7000) {
            inhalePressure = (int) ((Math.random() * 0.1 + 1) * 60);
            Log.d("simPressure", "insp: " + Integer.toString(inhalePressure));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) { /* accept interruption*/}
        }
    }

    public int getInhalePressure () {
        return this.inhalePressure;
    }

    public int getExhalePressure() {
        return this.exhalePressure;
    }

    public void changeMe() {
        setChanged();
        notifyObservers(this);
    }
}

