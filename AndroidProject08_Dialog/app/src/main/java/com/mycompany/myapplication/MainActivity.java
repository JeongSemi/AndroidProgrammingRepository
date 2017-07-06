package com.mycompany.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleBtnMessageDialog(View v) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("제목")
                .setMessage("알려 줄 메시지!!!!!")
                .setPositiveButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "닫기 버튼 클릭");
                    }
                })
                .setNegativeButton("취소", null)
                .setNeutralButton("확인", null)
                .create();
        dialog.show();
    }

    public void handleBtnListDialog(View v) {
        final String[] menus = {"메뉴1", "메뉴2", "메뉴3"};
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("선택하세요:)")
                .setItems(menus, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedMenu = menus[which];
                        Log.i(TAG, selectedMenu);
                    }
                })
                .create();
        dialog.show();
    }

    public void handleBtnSingleChoiceDialog(View v) {
        final String[] menus = {"메뉴1", "메뉴2", "메뉴3"};
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("선택하세요:)")
                .setSingleChoiceItems(menus, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedMenu = menus[which];
                        Log.i(TAG, selectedMenu);
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }

    public void handleBtnMultiChoiceDialog(View v) {
        final String[] menus = {"메뉴1", "메뉴2", "메뉴3", "메뉴4", "메뉴5", "메뉴6"};
        final boolean[] selected = {false, true, false, false, true, false};
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("선택하세요:)")
                .setMultiChoiceItems(menus, selected, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                selected[which] = isChecked;
                            }
                        }
                )
                .setPositiveButton("취소", null)
                .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < selected.length; i++)
                            if (selected[i]) {
                                String menu = menus[i];
                                Log.i(TAG, menu);
                            }
                    }
                })
                .create();
        dialog.show();
    }

    public void handleBtnStickProgressDialog(final View v) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setTitle("통신 상태");
        progressDialog.setMessage("다운로드 중입니다");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(1024);
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <= 1024; i++) {
                    final int value = i;
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.setProgress(value);
                            progressDialog.setSecondaryProgress(2 * value);

                        }
                    };
                    v.post(runnable);
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                    }
                }
            }
        };
        thread.start();
        progressDialog.show();
    }

    public void handleBtnCircleProgressDialog(final View v) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setTitle("통신 상태");
        progressDialog.setMessage("다운로드 중입니다");
        progressDialog.show();
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                };
                v.post(runnable);
            }

        };
        thread.start();
    }

    public void handleBtnCustomDialog(View v) {
        CustomDialog dialog = new CustomDialog();
        dialog.show(getSupportFragmentManager(), null);
    }
}
