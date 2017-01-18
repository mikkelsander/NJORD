package com.project.ms.njord.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.project.ms.njord.R;
import com.project.ms.njord.fragments.DeStress;
import com.project.ms.njord.fragments.DeviceFragment;
import com.project.ms.njord.fragments.HomeFragment;
import com.project.ms.njord.fragments.ProfileFragment;
import com.project.ms.njord.fragments.ProgressFragment;
import com.project.ms.njord.fragments.RemindersFragment;
import com.project.ms.njord.fragments.VideoTutorialsFragment;
import com.project.ms.njord.model.Singleton;

import java.util.Observable;
import java.util.Observer;

import io.fabric.sdk.android.Fabric;

//import com.project.ms.njord.controller.DataSimulator;
//import com.project.ms.njord.controller.DataSimulator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Observer {

    NavigationView navigationView = null;
    Toolbar toolbar = null;
    String tag;
    TextView menuUserName;

    SharedPreferences prefs;
    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (!isEmulator()) {
            Fabric.with(this, new Crashlytics());
        }

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
        menuUserName = (TextView) headerView.findViewById(R.id.menuHeader_name_textView);

        Singleton.instance.getProfile().addObserver(this);
        menuUserName.setText(Singleton.instance.getProfile().getName());

        viewPager = (ViewPager) findViewById(R.id.main_viewpager_view);
        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager(),getApplicationContext()));

        tabLayout = (TabLayout) findViewById(R.id.main_tablayout_view);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }
        });

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
            prefs.edit().putString("active_email", "").commit();
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();

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

    private class CustomAdapter extends FragmentPagerAdapter {
        private String fragments [] = {"Power", "De-Stress"};
        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
        super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new HomeFragment();
                case 1:
                    return new DeStress();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments [position];
        }
    }
}
