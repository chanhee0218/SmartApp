package com.example.suapp;

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
        stopbtn = view.findViewById(R.id.stop);
        runbtn = view.findViewById(R.id.start);
        retrobtn = view.findViewById(R.id.retrobtn);
        videoView = view.findViewById(R.id.videoview);
        retrobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = getRetrofit();
                RetrofitHellper retrofitHellper = retrofit.create(RetrofitHellper.class);
                retrofitHellper.Auth().enqueue(new Callback<String>() {
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println((t.toString()));
                    }

                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        byte[] video= java.util.Base64.getDecoder().decode(response.body());
                        System.out.println(Arrays.toString(video));
                        System.out.println(video.length);
                        File root = Environment.getExternalStorageDirectory();
                        String path = root.getAbsolutePath() + "/test.mp4";
                        System.out.println(path);
                        File file = new File(path);

                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                            fileOutputStream.write(video);
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            videoView.setVideoPath(path);
                            playVideo();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                });
            }
        });
        return view;
    }
    Retrofit getRetrofit(){
        Gson gson=new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl("http://20.55.17.29:5000/getImage/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }
    public void StartButton(View v){
        playVideo();
    }
    public void StopButton(View v){
        stopVideo();
    }

    private void stopVideo() {
        videoView.pause();

    }

    private void playVideo() {
        videoView.start();
    }
}

