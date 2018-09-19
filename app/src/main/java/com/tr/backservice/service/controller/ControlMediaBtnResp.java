package com.tr.backservice.service.controller;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;

import com.tr.backservice.activity.SwitchAudioSrcActivity;
import com.tr.backservice.engine.KeyCodeEnum;
import com.tr.backservice.receiver.BackReceiver;

public class ControlMediaBtnResp implements BackReceiver.BackReceiverListener {
    //TAG
    private static final String TAG = "ControlMediaBtnResp";

    private Context mContext;
    private ComponentName mComponentName;
    private AudioManager mAudioManager;

    public ControlMediaBtnResp(@NonNull Context context) {
        mContext = context;
        mComponentName = new ComponentName(context.getPackageName(), BackReceiver.class.getName());
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        register();
    }

    private void register() {
        BackReceiver.register(this);
        mAudioManager.registerMediaButtonEventReceiver(mComponentName);
    }

    @Override
    public void onGotMediaKeyCode(KeyCodeEnum keyCodeEnum) {
        Log.i(TAG, "onGotMediaKeyCode(" + keyCodeEnum + ")");

        //Switch
        Intent i = new Intent(mContext, SwitchAudioSrcActivity.class);
        i.putExtra("KEYCODE_ACTION", keyCodeEnum.getAction());
        mContext.startActivity(i);
    }

    @Override
    public void onGotMediaKeyCode(KeyEvent event) {
        final int action = event.getAction();
        Log.i(TAG, "onGotMediaKeyCode(KeyEvent) > [" + action + "," + event.getKeyCode() + "]");
        switch (action) {
            case KeyEvent.ACTION_UP:
                break;
        }
    }

    private void unregister() {
        BackReceiver.unregister(this);
        mAudioManager.unregisterMediaButtonEventReceiver(mComponentName);
    }

    public void destroy() {
        unregister();
        mComponentName = null;
    }
}
