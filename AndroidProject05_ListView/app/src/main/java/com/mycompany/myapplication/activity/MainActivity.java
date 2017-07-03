package com.mycompany.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mycompany.myapplication.content.FoodList;
import com.mycompany.myapplication.content.ReviewList;
import com.mycompany.myapplication.dto.Food;
import com.mycompany.myapplication.dto.Review;
import com.mycompany.myapplication.R;

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
        ReviewList reviewList = new ReviewList(this);
        frameLayout.addView(reviewList);

        for(int i=1; i<=10; i++) {
            Review review = new Review();
            review.setPhoto(R.drawable.member1);
            review.setTitle("ListView와 Adapter");
            review.setStar(R.drawable.star10);
            review.setContent("Adapter는 item XML를 Inflation해서 데이터를 세팅한 다음 ListView에 제공하는 역할을 합니다.");
            reviewList.addItem(review);
        }
    }

    public void handleButton2(View v) {
        frameLayout.removeAllViews();
        FoodList foodList = new FoodList(this);
        frameLayout.addView(foodList);

        for(int i=1; i<=6; i++) {
            Food food = new Food();
            food.setFno(i);
            food.setFname("삼겹살"+i);
            food.setFphoto(getResources().getIdentifier("food"+i, "drawable", getPackageName()));
            food.setFstar(getResources().getIdentifier("star"+i, "drawable", getPackageName()));
            food.setFdesc("한국인이 즐겨 먹는 음식입니다. 쌈장과 쌈과 함께 먹으면 끝내줍니다. 가락동에서 유명합니다.");
            foodList.addItem(food);
        }
    }

    public void handleButton3(View v) {

    }

}
