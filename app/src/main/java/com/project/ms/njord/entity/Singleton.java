package com.project.ms.njord.entity;

/**
 * Created by Oliver on 14-Nov-16.
 */

public class Singleton {

    public static Singleton instance;
    public DatabaseManager dbManager;
    public Profile profile;

    private Singleton(){
        dbManager = new DatabaseManager();
        createGuestProfile();
    }

    public static void init(){
        if(instance ==null){
            instance = new Singleton();
        }
    }

    public void changeProfile(Profile newProfile){
        this.profile = newProfile;
    }


    public void createGuestProfile() {
        profile = new Profile();
    }

    public void createProfile(String email, String password){
        profile = new Profile(email, password);
        profile.getTestResults().add(new TestResult());
        profile.getTestResults().add(new TestResult());
        dbManager.saveProfile(profile);
        dbManager.setProfileChangeListener();
    }

    public Profile getProfile() {return profile;}
    public DatabaseManager getDataBaseManager() {return dbManager;}

}
