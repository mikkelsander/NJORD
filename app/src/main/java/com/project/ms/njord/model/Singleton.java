package com.project.ms.njord.model;

import java.util.ArrayList;

/**
 * Created by Oliver on 14-Nov-16.
 */

public class Singleton {

    public static Singleton instance;
    final private DatabaseManager dbManager;
    private Profile profile;

    private Singleton(){
        dbManager = new DatabaseManager();
        createGuestProfile();
    }

    public static void init(){
        if(instance ==null){
            instance = new Singleton();
        }
    }

    public void updateProfile(Profile newProfile){
        this.profile = newProfile;
    }


    private void createGuestProfile() {
        profile = new Profile();
    }

    public void createProfile(String email, String password){
        this.profile = new Profile(email, password);
        dbManager.saveProfile(profile);
        dbManager.syncProfile(profile.getEmail());
    }

    public void createProfile (String email, String password, String name, String birthday, String gender,
                   String height, String weight) {

       this.profile = new Profile(email, password, name, birthday, gender, height, weight);
    }


    public Profile getProfile() {return profile;}
    public DatabaseManager getDataBaseManager() {return dbManager;}

}
