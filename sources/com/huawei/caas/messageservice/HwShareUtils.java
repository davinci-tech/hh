package com.huawei.caas.messageservice;

/* loaded from: classes8.dex */
public class HwShareUtils {
    public static final int ABILITY_NOT_SUPPORT = 2001;
    public static final int DATA_CHECK_FAIL = 2004;
    public static final int INPUT_PARAMETER_FAIL = 2003;
    public static final int PERMISSION_ERROR = 2002;
    public static final int SCENE_MESSAGE = 1;
    public static final int SCENE_MOMENTS = 2;
    public static final int SEND_INFO_FAIL = 1;
    public static final int SEND_INFO_SUCCESS = 0;
    public static final int TARGET_SCENE_FAIL = 2005;

    public enum SendResultEnum {
        SEND_SUCCESS,
        SEND_CANCEL,
        SEND_FAIL
    }

    public enum ShareTypeEnum {
        SHARE_PRODUCT_MSG,
        SHARE_MUSIC_MSG,
        SHARE_WEBPAGE_MSG
    }
}
