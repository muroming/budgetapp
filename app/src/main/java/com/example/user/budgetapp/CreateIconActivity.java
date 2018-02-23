package com.example.user.budgetapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import layout.CreateIconFragment;

public class CreateIconActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_icon);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));


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
