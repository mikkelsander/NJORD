package com.project.ms.njord.controller;

import com.project.ms.njord.entity.Profile;

/**
 * Created by Oliver on 14-Nov-16.
 */

public class ProfileController {

    public static ProfileController profileController;


    Profile profile;

    public void createProfile(String email, String password){
        profile = new Profile(email, password);
    }

    public Profile getProfile(){
        return profile;
    }
}
