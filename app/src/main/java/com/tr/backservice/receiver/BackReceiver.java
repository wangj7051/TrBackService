package com.tr.backservice.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;

import com.tr.backservice.MainActivity;
import com.tr.backservice.engine.KeyCodeEnum;

import java.util.HashSet;
import java.util.Set;

public class BackReceiver extends BroadcastReceiver {
    //TAG
    private static final String TAG = "BackReceiver";

    /**
     * {@link BackReceiverListener} object set
     */
    private static Set<BackReceiverListener> mSetListeners = new HashSet<>();

    public interface BackReceiverListener {
        void onGotMediaKeyCode(KeyEvent event);

        void onGotMediaKeyCode(KeyCodeEnum keyCodeEnum);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i(TAG, "action: " + action);
        if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            startBackService(context);

            //Media buttons
        } else if (Intent.ACTION_MEDIA_BUTTON.equals(action)) {
            KeyEvent keyEvent = intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
            for (BackReceiverListener l : mSetListeners) {
                if (l != null) {
                    l.onGotMediaKeyCode(keyEvent);
                }
            }
        } else {
            KeyCodeEnum kce = KeyCodeEnum.get(action);
            for (BackReceiverListener l : mSetListeners) {
                if (l != null) {
                    l.onGotMediaKeyCode(kce);
                }
            }
        }
    }

    private void startBackService(Context context) {
        Intent backServiceIntent = new Intent(context, MainActivity.class);
        context.startActivity(backServiceIntent);
    }

    public static void register(BackReceiverListener l) {
        if (l != null) {
            mSetListeners.add(l);
        }
    }

    public static void unregister(BackReceiverListener l) {
        mSetListeners.remove(l);
    }
}