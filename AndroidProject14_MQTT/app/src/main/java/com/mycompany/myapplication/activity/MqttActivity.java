package com.mycompany.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mycompany.myapplication.R;

public class MqttActivity extends AppCompatActivity {

    private TextView txtDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mqtt);
        txtDisplay = (TextView) findViewById(R.id.txtDisplay);
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        txtDisplay.append(message);
    }
}
