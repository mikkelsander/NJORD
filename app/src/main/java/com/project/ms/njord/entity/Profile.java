package com.project.ms.njord.entity;

/**
 * Created by Oliver on 14-Nov-16.
 */

public class Profile {

    private String name = "";
    private String email = "";
    private String password = "";
    private String birthday = "";
    private int height, weight = 0;
    private String gender = "";


    public Profile(String email, String password){
        this.email=email;
        this.password=password;
    }

    public Profile(String name, String email, String password, String birthday, int height, int weight, String gender) {
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
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;

    }

}
