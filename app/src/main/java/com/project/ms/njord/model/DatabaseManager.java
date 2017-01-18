package com.project.ms.njord.model;

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
    private DatabaseReference profileRef;
    private String uniqueEmail;

    public DatabaseManager() {
        databaseInstance = FirebaseDatabase.getInstance();
        profileRef = databaseInstance.getReference("Profiles");
    }



    public void saveProfile(Profile profile) {

        if (TextUtils.isEmpty(uniqueEmail)) {

            uniqueEmail = profile.getEmail();

        }

        profileRef.child(uniqueEmail).setValue(profile);

    }

/*

    public void loadProfile(String id) {

        profileRef.child(uniqueEmail).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Profile profile = dataSnapshot.getValue(Profile.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }) ;

    }
*/


    public void syncProfile(String email) {
        // User data change listener
        profileRef.child(email).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Profile profile = dataSnapshot.getValue(Profile.class);

                // Check for null
                if (profile == null) {
                    Log.d(TAG, "Profile data is null!");
                    return;
                }

                Singleton.instance.updateProfile(profile);

                Log.d(TAG, "Syncing profile data!");

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }


    public void deleteProfile(String email){

        profileRef.child(email).removeValue();
    }


    //in case in the future we want to only push one of profile attribute to the database
    private void updateProfile(Profile profile) {
        // updating the user via child nodes

        if (!TextUtils.isEmpty(profile.getEmail())) {

            profileRef.child(uniqueEmail).child("email").setValue(profile.getEmail());

        }

        if (!TextUtils.isEmpty(profile.getName())) {

            profileRef.child(uniqueEmail).child("name").setValue(profile.getName());

        }

        if (!TextUtils.isEmpty(profile.getBirthday())) {

            profileRef.child(uniqueEmail).child("birthday").setValue(profile.getBirthday());

        }

        if (!TextUtils.isEmpty(profile.getGender())) {

            profileRef.child(uniqueEmail).child("gender").setValue(profile.getGender());
        }

        if (!TextUtils.isEmpty(profile.getHeight())) {

            profileRef.child(uniqueEmail).child("height").setValue(profile.getHeight());

        }

        if (!TextUtils.isEmpty(profile.getWeight())) {

            profileRef.child(uniqueEmail).child("weight").setValue(profile.getWeight());

        }

    }
}






