package com.mycompany.myapplication.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mycompany.myapplication.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void handleBtnUIActivity(View v) {
        Intent intent = new Intent(this, UIActivity.class);
        startActivity(intent);
    }

    public void handleBtnWebBrowser(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com")); //보여달라
        startActivity(intent);
    }

    public void handleBtnDialActivity(View v) {
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-9981-4763")); //보여달라
            startActivity(intent);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                handleBtnDialActivity(null);
            } else {
                Toast.makeText(this, "권한을 얻지 못함", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void handleBtnMapAcitivity(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.515889, 127.07275?z=16"));
        startActivity(intent);
    }
}
