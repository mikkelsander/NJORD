package com.project.ms.njord.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.project.ms.njord.R;
import com.project.ms.njord.fragments.LoginFragment;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (savedInstanceState == null)
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.login_fragment_container, new LoginFragment())
                .commit();

    }
}
