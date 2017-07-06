package com.mycompany.myapplication.activity;

import android.app.Notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;

import com.mycompany.myapplication.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void handleBtnNotification(View v) {
        Intent intent = new Intent(this, UIActivity.class);
        //Back 버튼을 클릭했을 경우 마지막 화면이 기본적으로 나옴
        //PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //Back 버튼을 클릭했을 때 UIActivity 화면의 이전 화면(HomeActivity)이 나오도록 함
        PendingIntent pendingIntent = TaskStackBuilder.create(this)
                .addParentStack(UIActivity.class) //Manifest 파일에서 이전 화면의 정보를 얻어 스택에 넣음
                .addNextIntent(intent)
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("MQTT 알림")
                .setContentText("온도가 비정상적으로 높습니다.")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setVibrate(new long[] {1000, 500, 1000, 500})
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .build();

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1, notification);

    }
}
