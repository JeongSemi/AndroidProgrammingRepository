package com.mycompany.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private LinearLayout linearLayoutTop;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayoutTop = (LinearLayout) findViewById(R.id.linearLayoutTop);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
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
        frameLayout.removeAllViews();
        Content1 content1 = new Content1(this);
        frameLayout.addView(content1);

        for(int i=1; i<=10; i++) {
            Item1 item = new Item1();
            item.setPhoto(getResources().getIdentifier("member"+i, "drawable", getPackageName()));
            item.setTitle("반복 학습이 중요" + i);
            item.setStar(getResources().getIdentifier("star"+i, "drawable", getPackageName()));
            item.setContent("이거 너무 쉬운거 아닙니까? 반복 학습을 꼭 해야 하겠어요. 내 것으로 만들어야 겠어요.");
            content1.addItem(item);
        }
    }

    public void handleButton2(View v) {

    }

    public void handleButton3(View v) {

    }

}
