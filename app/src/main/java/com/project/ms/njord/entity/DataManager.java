package com.project.ms.njord.entity;

/**
 * Created by Oliver on 14-Nov-16.
 */

public class DataManager {

    public static DataManager dataManager;

    private Profile profile;
    private Progress progress;
    private TestResult[] testResult;

    private DataManager(){
        createProfile("guest@mail.com","12345");
    }

    public static void init(){
        if(dataManager==null){
            dataManager = new DataManager();
        }
    }

    public void createProfile(String email, String password){
        profile = new Profile(email, password);
    }



    public void createTetsResult(){
        // TODO: lav nyt test resultat til hver test session
    }


    public Profile getProfile(){
        return profile;
    }

    public Progress getProgress(){
        return progress;
    }

    public TestResult getTestResult(int i){
        return testResult[i];
    }
}
