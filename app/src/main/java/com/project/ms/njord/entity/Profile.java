package com.project.ms.njord.entity;


import java.util.Observable;

/**
 * Created by Oliver on 14-Nov-16.
 */

public class Profile extends Observable {

    private String email = "";
    private String password = "";
    private String name = "Name";
    private String birthday = "";
    private String gender = "";
    private String height = "";
    private String weight = "";

    public Profile() {

    };


    public Profile(String email, String password){
        this.email=email;
        this.password=password;
    }

    public Profile(String name, String email, String password, String birthday, String gender, String height, String weight) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers();
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

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
        setChanged();
        notifyObservers();
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
        setChanged();
        notifyObservers();
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
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

}
