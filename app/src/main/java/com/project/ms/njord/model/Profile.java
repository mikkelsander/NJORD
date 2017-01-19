package com.project.ms.njord.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

/**
 * Created by Oliver on 14-Nov-16.
 */

public class Profile extends Observable {
    private String email = "guest@guest";
    private String password = "";
    private String name = "Guest";
    private String birthday = "";
    private String gender = "";
    private String height = "";
    private String weight = "";
    private ArrayList<TestResult> testResults = new ArrayList<>();

    public Profile() {
        // Required empty constructor
    }

    public Profile(String email, String password){
        this.email=email;
        this.password=password;
        this.name= "";
    }

    public Profile(String email, String password, String name, String birthday, String gender,
                   String height, String weight) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.testResults = new ArrayList<TestResult>();
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        setChanged();
        notifyObservers();
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
        setChanged();
        notifyObservers();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers();
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
        setChanged();
        notifyObservers();
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String height) {
        this.height = height;
        setChanged();
        notifyObservers();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
        setChanged();
        notifyObservers();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
        setChanged();
        notifyObservers();

    }

    public ArrayList<TestResult> getTestResults() {
        return testResults;
    }

    public void createTestResult(Date date, int insp, int exp){
        TestResult result = new TestResult(date, insp, exp);
        testResults.add(result);
    }

}
