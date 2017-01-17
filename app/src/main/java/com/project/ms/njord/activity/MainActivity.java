package com.project.ms.njord.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.ms.njord.R;
import com.project.ms.njord.entity.Singleton;
import com.project.ms.njord.fragment.DeviceFragment;
import com.project.ms.njord.fragment.HelpFragment;
import com.project.ms.njord.fragment.HomeFragment;
import com.project.ms.njord.fragment.LoginFragment;
import com.project.ms.njord.fragment.ProfileFragment;
import com.project.ms.njord.fragment.ProgressFragment;
import com.project.ms.njord.fragment.SettingsFragment;

import java.util.Observable;
import java.util.Observer;

//import com.project.ms.njord.controller.DataSimulator;
//import com.project.ms.njord.controller.DataSimulator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Observer {

    NavigationView navigationView = null;
    Toolbar toolbar = null;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //TODO: overfør ikke crashrapport ved emulatorcrash
        //Fabric.with(this, new Crashlytics());


        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting initial fragment to Home
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new HomeFragment());
        fragmentTransaction.commit();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initializing Navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Setting the user name in the navigation drawer
        View headerView = navigationView.getHeaderView(0);
        TextView menuUserName = (TextView) headerView.findViewById(R.id.menuHeader_name_textView);


        if (!(Singleton.instance.getProfile() == null)){
            menuUserName.setText(Singleton.instance.getProfile().getName());
            Singleton.instance.getProfile().addObserver(this);
        }
        else {
            menuUserName.setText("Guest");
        }
        // Adding activity as obser to profile

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_home) {
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new HomeFragment())
                    .addToBackStack(null)
                    .commit();
            getSupportActionBar().setTitle("Home");

        } else if (id == R.id.nav_profile) {
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new ProfileFragment())
                    .addToBackStack(null)
                    .commit();
            getSupportActionBar().setTitle("Profile");


        } else if (id == R.id.nav_device) {
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new DeviceFragment())
                    .addToBackStack(null)
                    .commit();
            getSupportActionBar().setTitle("Spirometer");

        } else if (id == R.id.nav_settings) {
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new SettingsFragment())
                    .addToBackStack(null)
                    .commit();
            getSupportActionBar().setTitle("Preferences");

        } else if (id == R.id.nav_progress) {
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new ProgressFragment())
                    .addToBackStack(null)
                    .commit();
            getSupportActionBar().setTitle("Progress");

        } else if (id == R.id.nav_help) {
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new HelpFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("Help");

        }else if (id == R.id.nav_logOut){
            prefs.edit().putBoolean("isLoggedIn", false).commit();
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void update(Observable o, Object arg) {
        // Updates the user name in the navigation drawer when changed
        View headerView = navigationView.getHeaderView(0);
        TextView menuUserName = (TextView) headerView.findViewById(R.id.menuHeader_name_textView);
        menuUserName.setText(Singleton.instance.getProfile().getName());
    }


    public static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }

}
