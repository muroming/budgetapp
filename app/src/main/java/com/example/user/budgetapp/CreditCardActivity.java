package com.example.user.budgetapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import layout.CreditCardFragment;

public class CreditCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.credit_card_container);
        if(fragment == null){
            fragment = new CreditCardFragment();
            fm.beginTransaction()
                    .add(R.id.credit_card_container, fragment)
                    .commit();
        }
    }
}
