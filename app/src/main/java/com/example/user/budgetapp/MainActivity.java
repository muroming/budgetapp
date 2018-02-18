package com.example.user.budgetapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import layout.IconsFragment;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.icon_container);
        if (fragment == null) {
            fragment = new IconsFragment();
            fm.beginTransaction()
                    .add(R.id.icon_container, fragment)
                    .commit();
        }
    }
}
