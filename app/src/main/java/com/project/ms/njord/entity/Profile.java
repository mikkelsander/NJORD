package com.project.ms.njord.entity;

/**
 * Created by Oliver on 14-Nov-16.
 */

public class Profile {

    private String name, email, password;
    private int birthday, height, weight;
    private enum Gender{MALE, FEMALE};
    private Gender gender;


    public Profile(String email, String password){
        this.email=email;
        this.password=password;
    }

    public Profile(String name, String email, String password, int birthday, int height, int weight, boolean male) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        if(male) this.gender = Gender.MALE;
        else gender = Gender.FEMALE;
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

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

}
