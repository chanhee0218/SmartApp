package com.example.suapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
    public static ArrayList<String> selectedPhotos = new ArrayList<>();


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<String> photos=null;
        if(requestCode==RESULT_OK&&requestCode==REQUEST_CODE){
            if(data!=null){
                photos=data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
            }
            Intent startA=new Intent(getApplicationContext(),PhotoPickerActivity.class);
            startA.putStringArrayListExtra("photos",selectedPhotos);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        bottomNavigationView=findViewById(R.id.bottom_navigation_view);
        final FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment1,firstFragment).commitAllowingStateLoss();
        YPhotoPickerIntent intent=new YPhotoPickerIntent(this);
        intent.setMaxSelectCount(1);

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