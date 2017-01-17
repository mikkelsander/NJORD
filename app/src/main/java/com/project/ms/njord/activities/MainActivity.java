package com.project.ms.njord.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
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
import com.project.ms.njord.model.Singleton;
import com.project.ms.njord.fragments.DeviceFragment;
import com.project.ms.njord.fragments.VideoTutorialsFragment;
import com.project.ms.njord.fragments.HomeFragment;
import com.project.ms.njord.fragments.ProfileFragment;
import com.project.ms.njord.fragments.ProgressFragment;
import com.project.ms.njord.fragments.RemindersFragment;

import java.util.Observable;
import java.util.Observer;

//import com.project.ms.njord.controller.DataSimulator;
//import com.project.ms.njord.controller.DataSimulator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Observer {

    NavigationView navigationView = null;
    Toolbar toolbar = null;
    String tag;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //TODO: overfør ikke crashrapport ved emulatorcrash
        //Fabric.with(this, new Crashlytics());

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // if fresh start show fragment home

        if(savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment(), "home" )
                    .commit();
        }

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

        //
        if (!(Singleton.instance.getProfile() == null)){
            menuUserName.setText(Singleton.instance.getProfile().getName());
            Singleton.instance.getProfile().addObserver(this);
        }
        else {
            menuUserName.setText("Guest");
        }

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
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment(), "home")
                    .addToBackStack(null)
                    .commit();

            getSupportActionBar().setTitle("Home");

        } else if (id == R.id.nav_profile) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ProfileFragment(), "profile")
                    .addToBackStack(null)
                    .commit();
            getSupportActionBar().setTitle("Profile");


        } else if (id == R.id.nav_device) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new DeviceFragment(), "device")
                    .addToBackStack(null)
                    .commit();
            getSupportActionBar().setTitle("Spirometer");

        } else if (id == R.id.nav_reminders) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new RemindersFragment(), "reminders")
                    .addToBackStack(null)
                    .commit();
            getSupportActionBar().setTitle("Preferences");

        } else if (id == R.id.nav_progress) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ProgressFragment(), "progress")
                    .addToBackStack(null)
                    .commit();
            getSupportActionBar().setTitle("Progress");

        } else if (id == R.id.nav_video_tutorials) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new VideoTutorialsFragment(), "video_tutorials")
                    .addToBackStack(null)
                    .commit();
            getSupportActionBar().setTitle("Video tutorials");

        } else if (id == R.id.nav_logOut){
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
