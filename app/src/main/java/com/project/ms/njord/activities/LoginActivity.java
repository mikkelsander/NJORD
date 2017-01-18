package com.project.ms.njord.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.project.ms.njord.R;
import com.project.ms.njord.fragments.LoginFragment;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        if (savedInstanceState == null)
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.login_fragment_container, new LoginFragment())
                .commit();

    }
}
