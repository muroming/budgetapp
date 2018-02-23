package com.example.user.budgetapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import layout.IconsFragment;
import layout.TotalInfoFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.icon_container);
        if (fragment == null) {
            fragment = new IconsFragment();
            fm.beginTransaction()
                    .add(R.id.icon_container, fragment)
                    .commit();
        }

        fragment = fm.findFragmentById(R.id.total_info_container);
        if(fragment == null){
            fragment = new TotalInfoFragment();
            fm.beginTransaction()
                    .add(R.id.total_info_container, fragment)
                    .commit();
        }
    }
}
