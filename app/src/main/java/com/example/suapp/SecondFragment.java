package com.example.suapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.Base64.Decoder;

import android.widget.VideoView;

import com.bumptech.glide.load.Encoder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SecondFragment extends Fragment {
    Button stopbtn,runbtn,retrobtn;
    VideoView videoView;
    String path=Environment.getExternalStorageDirectory().getAbsolutePath()+"/scon/video.mp4";
    final static AdminActivity adminactivity=new AdminActivity();
    static String value=adminactivity.value;

    RetroHelper retroHelper;

    public SecondFragment() {
        // Required empty public constructor
    }

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_second, container, false);
        stopbtn = view.findViewById(R.id.stop);
        runbtn = view.findViewById(R.id.run);
        videoView = view.findViewById(R.id.videoview);
        videoView.setVideoPath(path);
        playVideo();

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("test", Context.MODE_PRIVATE);
        String easy = sharedPreferences.getString("inputText","");

        RetroHelper.getInstance(easy);
        stopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopVideo();
            }
        });
        runbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo();
            }
        });
        return view;
    }


    private void stopVideo() {
        videoView.pause();

    }

    private void playVideo() {
        videoView.start();
    }
}

