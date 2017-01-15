package com.project.ms.njord.entity;

import java.util.Date;

/**
 * Created by Oliver on 19-Nov-16.
 */

public class TestResult {

    private Date date;
    private int inhaleLevel, exhaleLevel;
    private int[] dataPoints;

    public TestResult(){

    }

    public TestResult(Date date, int inhaleLevel, int exhaleLevel) {
        this.date = date;
        this.inhaleLevel = inhaleLevel;
        this.exhaleLevel = exhaleLevel;

    }

    public Date getDate() {
        return date;
    }

    public int getInhaleLevel() {
        return inhaleLevel;
    }

    public int getExhaleLevel() {
        return exhaleLevel;
    }
}

