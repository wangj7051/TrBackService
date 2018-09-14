package com.tr.backservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.tr.backservice.service.controller.ControlMediaBtnResp;

public class BackService extends Service {
    //TAG
    private static final String TAG = "BackService";

    //
    private ControlMediaBtnResp mControlMediaBtnResp;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate(Intent)");
        mControlMediaBtnResp = new ControlMediaBtnResp(this);
    }

    /**
     * Get Service Object
     */
    public class LocalBinder extends Binder {
        public BackService getService() {
            Log.i(TAG, "getService()");
            return BackService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind(Intent)");
        return new LocalBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand(Intent,flags,startId)");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind(Intent)");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy()");
        if (mControlMediaBtnResp != null) {
            mControlMediaBtnResp.destroy();
            mControlMediaBtnResp = null;
        }
        super.onDestroy();
    }
}
