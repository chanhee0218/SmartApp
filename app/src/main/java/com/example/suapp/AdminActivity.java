package com.example.suapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editText);
        textView=findViewById(R.id.textView);

    }

    public void clickSetBt(View view) {
        if(editText.getText().toString().isEmpty()){
            Toast.makeText(this, "값을 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
        else {
            SharedPreferences sharedPreferences= getSharedPreferences("test", MODE_PRIVATE);
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.putString("inputText",editText.getText().toString());
            editor.commit();
            Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
            value=sharedPreferences.getString("MyUrl","");

        }
    }// clickSetBt()..

    public void clickGetBt(View view) {
        SharedPreferences sharedPreferences= getSharedPreferences("test", MODE_PRIVATE);
        String inputText = sharedPreferences.getString("inputText","");
        textView.setText(inputText);
        Toast.makeText(this, "불러오기 하였습니다..", Toast.LENGTH_SHORT).show();
    }
}

