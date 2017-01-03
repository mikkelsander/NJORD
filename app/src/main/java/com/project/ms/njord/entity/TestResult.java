package com.project.ms.njord.entity;

/**
 * Created by Oliver on 19-Nov-16.
 */

public class TestResult {

    private int date;
    private int lungeLevel;
    private int[] dataPoints;

    public TestResult(int date, int lungLevel, int[] dataPoints){
        this.date = date;
        this.lungeLevel = lungLevel;
        this.dataPoints = dataPoints;
    }

}
