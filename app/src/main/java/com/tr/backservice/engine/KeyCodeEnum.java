package com.tr.backservice.engine;

import java.util.HashMap;
import java.util.Map;

public enum KeyCodeEnum {
    MEDIA(-100, "tricheer.intent.action.MEDIA_SWITCH"),
    SOURCE(-101, "tricheer.intent.action.SWC_SOURCE_CHANGE");

    private static Map<String, KeyCodeEnum> mMapEnums;

    private int mKeyCode;
    private String mAction;

    KeyCodeEnum(int keyCode, String action) {
        mKeyCode = keyCode;
        mAction = action;
    }

    public String getAction() {
        return mAction;
    }

    public final int getKeyCode() {
        return mKeyCode;
    }

    public static KeyCodeEnum get(String action) {
        if (mMapEnums == null) {
            mMapEnums = new HashMap<>();
            mMapEnums.put(MEDIA.getAction(), MEDIA);
            mMapEnums.put(SOURCE.getAction(), SOURCE);
        }
        return mMapEnums.get(action);
    }

}
