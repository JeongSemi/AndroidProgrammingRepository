package com.mycompany.myapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SelfBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "SelfBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "방송 수신");
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        Log.i(TAG, "제목: " + title);
        Log.i(TAG, "내용: " + content);
    }


}
