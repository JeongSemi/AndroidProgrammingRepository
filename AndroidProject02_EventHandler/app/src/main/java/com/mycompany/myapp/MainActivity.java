package com.mycompany.myapp;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    private ImageView imgTitle;
    private Button btnImg1;
    private Button btnImg2;
    private RadioButton rbImg1, rbImg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgTitle = (ImageView) findViewById(R.id.imgTitle);
        btnImg1 = (Button) findViewById(R.id.btnImg1);
        btnImg2 = (Button) findViewById(R.id.btnImg2);
        rbImg1 = (RadioButton) findViewById(R.id.rbImg1);
        rbImg2 = (RadioButton) findViewById(R.id.rbImg2);

        btnImg1.setOnClickListener(handleBtnImg);
        btnImg2.setOnClickListener(handleBtnImg);
        rbImg1.setOnClickListener(handleBtnImg);
        rbImg2.setOnClickListener(handleBtnImg);
    }

    private View.OnClickListener handleBtnImg = new View.OnClickListener() {
        @Override
        public void onClick(View v) { // view -> 모든 것들의 상위클래스
            if (v == btnImg1 || v == rbImg1) {
                imgTitle.setImageResource(R.drawable.photo1);
            } else if (v == btnImg2 || v == rbImg2) {
                imgTitle.setImageResource(R.drawable.photo2);
            }

        }
    };

    public void handleBtnImg3(View v) {
        imgTitle.setImageResource(R.drawable.photo3);
    }

    public void handleRbImg3(View v) {
        imgTitle.setImageResource(R.drawable.photo3);
    }

}
