package com.project.ms.njord.controller;

import com.project.ms.njord.entity.Profile;

/**
 * Created by Oliver on 14-Nov-16.
 */

public class EntityController {

    public void createProfile(String name, String email, String password, int birthday, int height, int weight, Boolean male){
        Profile profile = new Profile(name, email, password, birthday, height, weight, male);
    }
    public void createProfile(String email, String password){
        Profile profile = new Profile(email, password);
    }

}
