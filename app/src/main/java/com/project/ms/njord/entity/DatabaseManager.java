package com.project.ms.njord.entity;

import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DatabaseManager {


    private FirebaseDatabase databaseInstance = FirebaseDatabase.getInstance();
    private DatabaseReference profileDatabase = databaseInstance.getReference("Profiles");
    private String profileId;




    public void startDatabase() {

        Profile profile = new Profile("", "");

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("Users");

        // Creating new user node, which returns the unique key value
        // new user node would be /users/$userid/
        String userID = myRef.push().getKey();

        myRef.child(userID).setValue(Profile.class);
        

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                DataManager value = dataSnapshot.getValue(DataManager.class);
                Log.d("database", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("database", "Failed to read value.", error.toException());
            }
        });

    }

    public void createProfile(Profile profile) {

        if (TextUtils.isEmpty(profileId)) {

            profileId = profileDatabase.push().getKey();
        }

        profileDatabase.child(profileId).setValue(profile);

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

            profileDatabase.child(profileId).child("email").setValue(profile.getEmail());

        }

        if (!TextUtils.isEmpty(profile.getEmail())) {

            profileDatabase.child(profileId).child("email").setValue(profile.getEmail());

        }




            .child(userId).child("name").setValue(name);

        profileDatabase.child(profileId).child("name")




        if (!TextUtils.isEmpty(name))
            mFirebaseDatabase.child(userId).child("name").setValue(name);

        if (!TextUtils.isEmpty(email))
            mFirebaseDatabase.child(userId).child("email").setValue(email);
    }
}

}




