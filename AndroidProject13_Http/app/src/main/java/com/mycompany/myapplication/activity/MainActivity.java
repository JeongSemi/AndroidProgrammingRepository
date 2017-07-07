package com.mycompany.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mycompany.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleBtnHttp(View v) {
        Intent intent = new Intent(this, HttpActivity.class);
        startActivity(intent);
    }

    public void handleBtnGetListData(View v) {
        Intent intent = new Intent(this, UIActivity.class);
        startActivity(intent);
    }

}
