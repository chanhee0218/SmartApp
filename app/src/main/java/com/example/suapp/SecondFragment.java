package com.example.suapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import java.io.File;

import retrofit2.Call;

public class SecondFragment extends Fragment {
    private ProgressBar progressBar;
    static final int PERMISSION_REQUEST_CODE=1;
    private File outputfile,path;
    MediaPlayer player;
    ImageButton imageButton;

    public SecondFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_second, container, false);
        imageButton=view.findViewById(R.id.imgbutton);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Context context=v.getContext();
                player = MediaPlayer.create(context, R.raw.downloadimg);
                player.start();
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                        mp.release();
                    }
                });

            }
        });
        return view;

        }
}