package com.project.ms.njord.entity;

/**
 * Created by Oliver on 19-Nov-16.
 */

public class TestResult {

    private int date;
    private int inhaleAverage, exhaleAverage;
    private int inhaleTest1, inhaleTest2, inhaleTest3, exhaleTest1, exhaleTest2, exhaleTest3;
    private int[] dataPoints;

    public TestResult(){

    }

    public TestResult(int date, int inhaleAverage, int exhaleAverage, int inhaleTest1, int inhaleTest2,
                      int inhaleTest3, int exhaleTest1, int exhaleTest2, int exhaleTest3, int[] dataPoints) {
        this.date = date;
        this.inhaleAverage = inhaleAverage;
        this.exhaleAverage = exhaleAverage;
        this.inhaleTest1 = inhaleTest1;
        this.inhaleTest2 = inhaleTest2;
        this.inhaleTest3 = inhaleTest3;
        this.exhaleTest1 = exhaleTest1;
        this.exhaleTest2 = exhaleTest2;
        this.exhaleTest3 = exhaleTest3;
    }

    public int getDate() {
        return date;
    }

    public int getInhaleAverage() {
        return inhaleAverage;
    }

    public int getExhaleAverage() {
        return exhaleAverage;
    }

    public int getInhaleTest1() {
        return inhaleTest1;
    }

    public int getInhaleTest2() {
        return inhaleTest2;
    }

    public int getInhaleTest3() {
        return inhaleTest3;
    }

    public int getExhaleTest1() {
        return exhaleTest1;
    }

    public int getExhaleTest2() {
        return exhaleTest2;
    }

    public int getExhaleTest3() {
        return exhaleTest3;
    }
}

