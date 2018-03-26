package com.example.user.budgetapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import layout.CreateIconFragment;
import layout.CreditCardFragment;
import layout.MainFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<Fragment> pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPages();
        viewPager = findViewById(R.id.main_view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(1);
        viewPager.setOffscreenPageLimit(2);
    }

    private void initPages() {
        pages = new ArrayList<>();
        pages.add(new CreateIconFragment());
        pages.add(new MainFragment());
        pages.add(new CreditCardFragment());
    }

    public void returnToMainPage(){
        viewPager.setCurrentItem(1);
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter{
        ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return pages.get(position);
        }

        @Override
        public int getCount() {
            return pages.size();
        }
    }
}
