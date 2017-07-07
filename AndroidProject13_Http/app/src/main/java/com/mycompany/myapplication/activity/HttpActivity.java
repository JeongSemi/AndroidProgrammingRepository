package com.mycompany.myapplication.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.util.RealPathUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class HttpActivity extends AppCompatActivity {
    private static final String TAG = "HttpActivity";
    private EditText txtParam1, txtParam2, txtAttach;
    private ScrollView scrollView;
    private TextView txtDisplay;
    private EditText txtAngle;

    private WebSocket webSocket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        txtParam1 = (EditText) findViewById(R.id.txtParam1);
        txtParam2 = (EditText) findViewById(R.id.txtParam2);
        txtAttach = (EditText) findViewById(R.id.txtAttach);
        txtDisplay = (TextView) findViewById(R.id.txtDisplay);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        txtAngle = (EditText) findViewById(R.id.txtAngle);
    }

    public void handleBtnGet(View v) throws IOException {
        String url = "http://192.168.3.117:8080/IoTWebProgramming/http/exam03";
        HttpUrl httpUrl = HttpUrl.parse(url).newBuilder()
                .addQueryParameter("param1", txtParam1.getText().toString())
                .addQueryParameter("param2", txtParam2.getText().toString())
                .build();
        Request request = new Request.Builder()
                .url(httpUrl)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                printlnText(json);
            }
        });

    }

    private boolean checkPermission() {
        int permissionRES = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionWES = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionRES != PackageManager.PERMISSION_GRANTED ||
                permissionWES != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
            return false;
        } else {
            return true;
        }
    }

    public void handleBtnPost(View v) throws Exception {
        String url = "http://192.168.3.117:8080/IoTWebProgramming/http/exam03";
        RequestBody requestBody = new FormBody.Builder()
                .add("param1", txtParam1.getText().toString())
                .add("param2", txtParam2.getText().toString())
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                printlnText(json);
            }
        });
    }

    public void handleBtnAttach(View v) {
        if (checkPermission()) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "이미지 선택"), 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Uri selectedImageUri = data.getData();
                String realPath = RealPathUtil.getRealPath(this, selectedImageUri);
                txtAttach.setText(realPath);
                Log.i(TAG, realPath);

            }
        }
    }

    public void handleBtnMultipart(View v) {
        String filePath = txtAttach.getText().toString();
        String[] paths = filePath.split("/");
        String fileName = paths[paths.length - 1];
        Log.i(TAG, fileName);

        String url = "http://192.168.3.117:8080/IoTWebProgramming/http/exam04";
        RequestBody requestBody = new MultipartBody.Builder()
                .addFormDataPart("param1", txtParam1.getText().toString())
                .addFormDataPart("param2", txtParam2.getText().toString())
                .addFormDataPart("attach", fileName, RequestBody.create(MultipartBody.FORM, new File(filePath)))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                printlnText(json);
            }
        });

    }

    public void handleBtnWebSocketOpen(View v) {
        String url = "ws://192.168.3.117:8080/SensingCarRemoteWebControl/websocket/thermistorsensor";
        Request request = new Request.Builder()
                .url(url)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                HttpActivity.this.webSocket = webSocket;
                printlnText("[WebSocket Open]");
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                final String json = text;
                printlnText(json);
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                HttpActivity.this.webSocket = null;
                printlnText("[WebSocket Closed]");
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            }
        });

    }

    public void handleBtnWebSocketClose(View v) {
        //Code must be 1000-5000 or 0
        webSocket.close(1000, null);
    }

    public void printlnText(final String text) {
        txtDisplay.post(new Runnable() {
            @Override
            public void run() {
                txtDisplay.append(text + "\n");
                txtDisplay.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                }, 100);
            }
        });
    }

    public void handleBtnChangeAngle(View v) {
        String url = "http://192.168.3.117:8080/SensingCarRemoteWebControl/ultrasonicsensor";
        HttpUrl httpUrl = HttpUrl.parse(url).newBuilder()
                .addQueryParameter("command", "change")
                .addQueryParameter("angle", txtAngle.getText().toString())
                .build();
        Request request = new Request.Builder()
                .url(httpUrl)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                printlnText(json);
            }
        });
    }
}
