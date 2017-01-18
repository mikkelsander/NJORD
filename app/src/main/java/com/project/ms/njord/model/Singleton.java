package com.project.ms.njord.model;

/**
 * Created by Oliver on 14-Nov-16.
 */

public class Singleton {

    public static Singleton instance;
    private DatabaseManager dbManager;
    private Profile profile;

    private Singleton(){
        dbManager = new DatabaseManager();
        profile = new Profile();
    }

    public static void init(){
        if(instance ==null){
            instance = new Singleton();
        }
    }

    public void updateProfile(Profile newProfile){
        this.profile = newProfile;
    }


    public void createGuestProfile() {
        profile = new Profile();
    }

    public void createProfile(String email, String password){
        profile = new Profile(email, password);
        dbManager.saveProfile(profile);
        dbManager.syncProfile(profile.getEmail());
    }

    public Profile getProfile() {return profile;}
    public DatabaseManager getDataBaseManager() {return dbManager;}

}
