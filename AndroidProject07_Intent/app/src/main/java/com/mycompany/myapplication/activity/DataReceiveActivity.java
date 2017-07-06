package com.mycompany.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.dto.Review;

public class DataReceiveActivity extends AppCompatActivity {
    private static final String TAG = "DataReceiveActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_receive);

        Intent intent = getIntent();
        int key1 = intent.getIntExtra("key1", 0);
        String key2 = intent.getStringExtra("key2");
        Review key3 = (Review) intent.getSerializableExtra("key3");

        Log.i(TAG, ""+key1);
        Log.i(TAG, key2);
        Log.i(TAG, key3.getTitle());
    }
}
