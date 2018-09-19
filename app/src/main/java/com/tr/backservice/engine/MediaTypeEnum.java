package com.tr.backservice.engine;

import android.util.SparseArray;

public enum MediaTypeEnum {
    FM(1), AM(2), MUSIC(3), BT_MUSIC(4);

    private int mType;

    MediaTypeEnum(int type) {
        mType = type;
    }

    public int getType() {
        return mType;
    }

    private static SparseArray<MediaTypeEnum> mSaEnums;

    public static MediaTypeEnum getByType(int type) {
        if (mSaEnums == null) {
            mSaEnums = new SparseArray<>();
            mSaEnums.put(AM.getType(), AM);
            mSaEnums.put(FM.getType(), FM);
            mSaEnums.put(MUSIC.getType(), MUSIC);
            mSaEnums.put(BT_MUSIC.getType(), BT_MUSIC);
        }
        return mSaEnums.get(type);
    }
}
