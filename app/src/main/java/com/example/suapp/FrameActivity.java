package com.example.suapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yongbeam.y_photopicker.util.photopicker.PhotoPickerActivity;
import com.yongbeam.y_photopicker.util.photopicker.utils.YPhotoPickerIntent;

import java.util.ArrayList;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;

public class FrameActivity extends AppCompatActivity {
    public final static int REQUEST_CODE=1;
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
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment1, fragment).commit();
    }

}