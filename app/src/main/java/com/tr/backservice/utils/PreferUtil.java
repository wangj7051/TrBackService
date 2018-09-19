package com.tr.backservice.utils;

import com.tr.backservice.engine.MediaTypeEnum;

public class PreferUtil extends PreferenceHelper {
    /**
     * Get last media flag
     *
     * @param isSet         true-Set flag to preference.
     * @param mediaTypeEnum {@link com.tr.backservice.engine.MediaTypeEnum}
     * @return {@link com.tr.backservice.engine.MediaTypeEnum}
     */
    public static MediaTypeEnum getLastMediaBy_MEDIA(boolean isSet, MediaTypeEnum mediaTypeEnum) {
        final String PREFER_KEY = "com.tr.backservice.LAST_MEDIA_TYPE-MEDIA";

        //Set
        if (isSet) {
            saveInt(PREFER_KEY, mediaTypeEnum.getType());
        }

        //Get
        int type = getInt(PREFER_KEY, MediaTypeEnum.BT_MUSIC.getType());
        return MediaTypeEnum.getByType(type);
    }

    /**
     * Get last media flag
     *
     * @param isSet         true-Set flag to preference.
     * @param mediaTypeEnum {@link com.tr.backservice.engine.MediaTypeEnum}
     * @return {@link com.tr.backservice.engine.MediaTypeEnum}
     */
    public static MediaTypeEnum getLastMediaBy_SOURCE(boolean isSet, MediaTypeEnum mediaTypeEnum) {
        final String PREFER_KEY = "com.tr.backservice.LAST_MEDIA_TYPE-SOURCE";

        //Set
        if (isSet) {
            saveInt(PREFER_KEY, mediaTypeEnum.getType());
        }

        //Get
        int type = getInt(PREFER_KEY, MediaTypeEnum.BT_MUSIC.getType());
        return MediaTypeEnum.getByType(type);
    }
}
