package com.mycompany.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayoutTop;

    private LinearLayout content1;
    private LinearLayout content2;
    private LinearLayout content3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayoutTop = (LinearLayout) findViewById(R.id.linearLayoutTop);
        content1 = (LinearLayout) findViewById(R.id.content1);
        content2 = (LinearLayout) findViewById(R.id.content2);
        content3 = (LinearLayout) findViewById(R.id.content3);

    }

    public void handleRadioButton1(View v) {
        linearLayoutTop.setBackgroundResource(R.drawable.photo1);
    }

    public void handleRadioButton2(View v) {
        linearLayoutTop.setBackgroundResource(R.drawable.photo2);
    }

    public void handleRadioButton3(View v) {
        linearLayoutTop.setBackgroundResource(R.drawable.photo3);
    }

    public void handleButton1(View v) {
        content1.setVisibility(View.VISIBLE);
        content2.setVisibility(View.INVISIBLE);
        content3.setVisibility(View.INVISIBLE);
    }

    public void handleButton2(View v) {
        content1.setVisibility(View.INVISIBLE);
        content2.setVisibility(View.VISIBLE);
        content3.setVisibility(View.INVISIBLE);
    }

    public void handleButton3(View v) {
        content1.setVisibility(View.INVISIBLE);
        content2.setVisibility(View.INVISIBLE);
        content3.setVisibility(View.VISIBLE);
    }
}
