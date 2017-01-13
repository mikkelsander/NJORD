package com.project.ms.njord.entity;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DatabaseManager {

    private DatabaseReference mDatabase;

    public void startDatabase() {

        Profile profile = new Profile("", "");
        profile.getName();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("Users");

        // Creating new user node, which returns the unique key value
        // new user node would be /users/$userid/
        String userID = myRef.push().getKey();

        myRef.child(userID).setValue(DataManager.dataManager);
        

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

}




