package com.example.suapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class FirstFragment extends Fragment {
    private static final int REQUEST_CODE=0;
    ImageView imageView;
    MediaPlayer mediaPlayer,imgnul;
    Bitmap bitmap,sendbit;
    Button button,imgsendbtn;
    Uri path;

    public FirstFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==REQUEST_CODE){
            if(resultCode==RESULT_OK){
                try{
                    InputStream inputStream=getActivity().getContentResolver().openInputStream(data.getData());
                    bitmap= BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                    imageView.setImageBitmap(bitmap);



                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(resultCode==RESULT_CANCELED){
                Toast.makeText(getActivity(), "이미지 선택 취소", Toast.LENGTH_SHORT).show();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_first, container, false);
        imgsendbtn=view.findViewById(R.id.upload);
        button=view.findViewById(R.id.button_fra);
        imageView=view.findViewById(R.id.img_fra1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        imgsendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bitmap==null){
                    Context context=v.getContext();
                    imgnul=MediaPlayer.create(context,R.raw.notfoundimg);
                    imgnul.start();

                }else{
                    uploadImage();
                    Context context=v.getContext();
                    mediaPlayer=MediaPlayer.create(context,R.raw.uploadtts);
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.stop();
                            mp.release();
                        }
                    });
                }
            }
        });

        return view;

    }

    private void uploadImage() {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,75,byteArrayOutputStream);
        byte[] imageInByte=byteArrayOutputStream.toByteArray();
        String encodedImage=Base64.encodeToString(imageInByte,Base64.DEFAULT);
        Call<ResponsePojo> call=RetroClient.getInstance().getApi().uploadImage(encodedImage);
        call.enqueue(new Callback<ResponsePojo>() {
            @Override
            public void onResponse(Call<ResponsePojo> call, Response<ResponsePojo> response) {
                Toast.makeText(getActivity(), response.body().getRemarks(), Toast.LENGTH_SHORT).show();
                if(response.body().isStatus()){

                }else{

                }
            }

            @Override
            public void onFailure(Call<ResponsePojo> call, Throwable t) {
                Toast.makeText(getActivity(), "NetWorkFailed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}