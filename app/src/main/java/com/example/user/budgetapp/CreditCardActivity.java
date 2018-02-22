package com.example.user.budgetapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import layout.CreditCardFragment;

public class CreditCardActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);

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
