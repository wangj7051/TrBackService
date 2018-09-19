package com.tr.backservice.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tr.backservice.R;
import com.tr.backservice.engine.KeyCodeEnum;
import com.tr.backservice.engine.MediaTypeEnum;
import com.tr.backservice.utils.CommonUtil;
import com.tr.backservice.utils.PlayerMp3Utils;
import com.tr.backservice.utils.PreferUtil;
import com.tr.backservice.view.ToastDialog;

import java.util.List;
import java.util.Set;

public class SwitchAudioSrcActivity extends BaseFragActivity {
    //TAG
    private static final String TAG = "SwitchAudioSrcActivity";

    //---- Widgets in this Activity ----
    private ImageView ivLogo;
    private TextView tvDesc;
    private ToastDialog toastDialog;


    //---- Variables in this Activity ----
    private Context mContext;

    private final int DELAY_RUN_TIME = 1000, DELAY_FINISH_TIME = 3000;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_audiosrc);
        init();
    }

    private void init() {
        // -- Variables --
        mContext = this;

        // -- Widgets --
        ivLogo = (ImageView) findViewById(R.id.iv_logo);
        tvDesc = (TextView) findViewById(R.id.tv_desc);
        loadContents();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent(Intent)");
        loadContents();
    }

    private void loadContents() {
        String keyCodeAction = getIntent().getStringExtra("KEYCODE_ACTION");
        if (!TextUtils.isEmpty(keyCodeAction)) {
            KeyCodeEnum keyCodeEnum = KeyCodeEnum.get(keyCodeAction);
            switch (keyCodeEnum) {
                case MEDIA:
                    switchLogoBy_MEDIA();
                    break;
                case SOURCE:
                    switchLogoBy_SOURCE();
                    break;
            }
        }
    }

    private void switchLogoBy_MEDIA() {
        MediaTypeEnum lastMediaType = PreferUtil.getLastMediaBy_MEDIA(false, null);
        switch (lastMediaType) {
            case MUSIC:
                PreferUtil.getLastMediaBy_MEDIA(true, MediaTypeEnum.BT_MUSIC);
                switchToBtMusic();
                break;
            case BT_MUSIC:
                PreferUtil.getLastMediaBy_MEDIA(true, MediaTypeEnum.MUSIC);
                switchToMusic();
                break;
        }
    }

    private void switchLogoBy_SOURCE() {
        MediaTypeEnum lastMediaType = PreferUtil.getLastMediaBy_SOURCE(false, null);
        switch (lastMediaType) {
            case FM:
                PreferUtil.getLastMediaBy_SOURCE(true, MediaTypeEnum.AM);
                switchToAM();
                break;
            case AM:
                PreferUtil.getLastMediaBy_SOURCE(true, MediaTypeEnum.MUSIC);
                switchToMusic();
                break;
            case MUSIC:
                PreferUtil.getLastMediaBy_SOURCE(true, MediaTypeEnum.BT_MUSIC);
                switchToBtMusic();
                break;
            case BT_MUSIC:
                PreferUtil.getLastMediaBy_SOURCE(true, MediaTypeEnum.FM);
                switchToFM();
                break;
        }
    }

    private void switchToFM() {
        Log.i(TAG, "switchToFM()");
        ivLogo.setImageResource(R.drawable.ic_launcher_bg);
        ivLogo.setVisibility(View.VISIBLE);
        tvDesc.setText(R.string.radio_fm);
        tvDesc.setVisibility(View.VISIBLE);

        //Open APK
        mHandler.removeCallbacksAndMessages(null);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                openRadio(0);
                finish();
            }
        }, DELAY_RUN_TIME);
    }

    private void switchToAM() {
        Log.i(TAG, "switchToAM()");
        ivLogo.setImageResource(R.drawable.ic_launcher_bg);
        ivLogo.setVisibility(View.VISIBLE);
        tvDesc.setText(R.string.radio_am);
        tvDesc.setVisibility(View.VISIBLE);

        //Open APK
        mHandler.removeCallbacksAndMessages(null);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                openRadio(1);
                finish();
            }
        }, DELAY_RUN_TIME);
    }

    /**
     * Open "com.tricheer.radio.MainActivity"
     *
     * @param band 0-FM,1-AM
     */
    private void openRadio(int band) {
        Intent data = new Intent();
        data.putExtra("TARGET_BAND", band);
        CommonUtil.openApk(mContext, "com.tricheer.radio", "com.tricheer.radio.MainActivity", data);
    }

    private void switchToMusic() {
        Log.i(TAG, "switchToMusic()");
        dismissToast();
        ivLogo.setImageResource(R.drawable.ic_launcher_music);
        ivLogo.setVisibility(View.VISIBLE);
        tvDesc.setText("");
        tvDesc.setVisibility(View.GONE);

        //Open APK
        mHandler.removeCallbacksAndMessages(null);
        List<String> listPaths = PlayerMp3Utils.getAllExterSdcardPath();
        if (listPaths != null && listPaths.size() > 0) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    CommonUtil.openApk(mContext, "com.tricheer.player", "com.tricheer.player.MusicPlayerActivity", null);
                    finish();
                }
            }, DELAY_RUN_TIME);
        }

        //
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showToast(getString(R.string.toast_usb_not_exist));
                ivLogo.setVisibility(View.INVISIBLE);
                mHandler.postDelayed(mDelayFinishRunnable, DELAY_FINISH_TIME);
            }
        }, DELAY_RUN_TIME);
    }

    private void switchToBtMusic() {
        Log.i(TAG, "switchToBtMusic()");
        dismissToast();
        ivLogo.setImageResource(R.drawable.ic_launcher_btmusic);
        ivLogo.setVisibility(View.VISIBLE);
        tvDesc.setText("");
        tvDesc.setVisibility(View.GONE);

        //Open APK
        mHandler.removeCallbacksAndMessages(null);
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter != null) {
            Set<BluetoothDevice> setDevices = btAdapter.getBondedDevices();
            if (setDevices != null && setDevices.size() > 0) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        CommonUtil.openApk(mContext, "com.tricheer.bt", "com.tricheer.bt.MusicActivity", null);
                        finish();
                    }
                }, DELAY_RUN_TIME);
                return;
            }
        }

        //Toast
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showToast(getString(R.string.toast_bt_not_exist));
                ivLogo.setVisibility(View.INVISIBLE);
                mHandler.postDelayed(mDelayFinishRunnable, DELAY_FINISH_TIME);
            }
        }, DELAY_RUN_TIME);
    }

    private Runnable mDelayFinishRunnable = new Runnable() {
        @Override
        public void run() {
            finish();
            dismissToast();
        }
    };

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        dismissToast();
        super.onDestroy();
    }

    private void showToast(String msg) {
        if (toastDialog == null) {
            toastDialog = new ToastDialog(mContext);
            toastDialog.setMsg(msg);
            toastDialog.setCanceledOnTouchOutside(false);
            toastDialog.setCancelable(false);
        }
        if (!toastDialog.isShowing()) {
            toastDialog.show();
        }
    }

    private void dismissToast() {
        if (toastDialog != null) {
            toastDialog.dismiss();
            toastDialog = null;
        }
    }
}