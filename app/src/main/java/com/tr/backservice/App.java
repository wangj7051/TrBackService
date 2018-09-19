package com.tr.backservice;

import android.app.Application;

import com.tr.backservice.utils.PreferUtil;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //
        PreferUtil.init(this);
    }
}
