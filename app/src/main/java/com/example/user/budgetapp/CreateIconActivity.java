package com.example.user.budgetapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import layout.CreateIconFragment;

public class CreateIconActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_icon);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.create_icon_container);
        if(fragment == null){
            fragment = new CreateIconFragment();
            fm.beginTransaction()
                    .add(R.id.create_icon_container, fragment)
                    .commit();
        }
    }
}
