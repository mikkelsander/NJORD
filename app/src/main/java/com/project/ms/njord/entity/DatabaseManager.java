package com.project.ms.njord.entity;

import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DatabaseManager {

    final private String TAG = "DatabaseManager";

    private FirebaseDatabase databaseInstance;
    private DatabaseReference profileDatabase, database;
    private String profileId;
    private String dataId;

    public DatabaseManager() {
        databaseInstance = FirebaseDatabase.getInstance();
        profileDatabase = databaseInstance.getReference("Profiles");
        database = databaseInstance.getReference("Singleton");
    }

/*
    public void saveDataTree (Singleton dataManager) {

        if (TextUtils.isEmpty(dataId)) {

            dataId = database.push().getKey();
        }

        database.child(dataId).setValue(dataManager);

    }*/



    public void saveProfile(Profile profile) {

        if (TextUtils.isEmpty(profileId)) {

            profileId = profileDatabase.push().getKey();
        }

        profileDatabase.child(profileId).setValue(profile);

    }


    public void setProfileChangeListener() {
        // User data change listener
        profileDatabase.child(profileId).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Profile newProfile = dataSnapshot.getValue(Profile.class);

                // Check for null
                if (newProfile == null) {
                    Log.d(TAG, "Profile data is null!");
                    return;
                }

                Singleton.instance.updateProfile(newProfile);

                Log.d(TAG, "Profile data is changed!");

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }


    private void updateProfile(Profile profile) {
        // updating the user via child nodes

        if (!TextUtils.isEmpty(profile.getEmail())) {

            profileDatabase.child(profileId).child("email").setValue(profile.getEmail());

        }

        if (!TextUtils.isEmpty(profile.getName())) {

            profileDatabase.child(profileId).child("name").setValue(profile.getName());

        }

        if (!TextUtils.isEmpty(profile.getBirthday())) {

            profileDatabase.child(profileId).child("birthday").setValue(profile.getBirthday());

        }

        if (!TextUtils.isEmpty(profile.getGender())) {

            profileDatabase.child(profileId).child("gender").setValue(profile.getGender());
        }

        if (!TextUtils.isEmpty(profile.getHeight())) {

            profileDatabase.child(profileId).child("height").setValue(profile.getHeight());

        }

        if (!TextUtils.isEmpty(profile.getWeight())) {

            profileDatabase.child(profileId).child("weight").setValue(profile.getWeight());

        }

    }
}






