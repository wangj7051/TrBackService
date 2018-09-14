package com.tr.backservice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.tr.backservice.activity.BaseFragActivity;
import com.tr.backservice.service.BackService;

public class MainActivity extends BaseFragActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, BackService.class));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1000);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
