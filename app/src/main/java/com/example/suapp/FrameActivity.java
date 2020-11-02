package com.example.suapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FrameActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager=getSupportFragmentManager();
    private FirstFragment firstFragment=new FirstFragment();
    private SecondFragment secondFragment=new SecondFragment();
    private ThirdFragment thirdFragment=new ThirdFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        bottomNavigationView=findViewById(R.id.bottom_navigation_view);
        final FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment1,firstFragment).commitAllowingStateLoss();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.first:{
                        FragmentTransaction transaction=fragmentManager.beginTransaction();
                        transaction.replace(R.id.fragment1,firstFragment).commitAllowingStateLoss();

                    }
                    case R.id.second:{
                        FragmentTransaction transaction=fragmentManager.beginTransaction();
                        transaction.replace(R.id.fragment1,secondFragment).commitAllowingStateLoss();

                    }
                    case R.id.third:{
                        FragmentTransaction transaction=fragmentManager.beginTransaction();
                        transaction.replace(R.id.fragment1,thirdFragment).commitAllowingStateLoss();
                    }
                }

                return true;
            }
        });
    }
}