package com.huawei.hms.kit.awareness.status;

/* loaded from: classes9.dex */
public interface CapabilityStatus {
    public static final int AWA_CAP_CODE_AMBIENT_LIGHT = 20006;
    public static final int AWA_CAP_CODE_APPLICATION = 20012;
    public static final int AWA_CAP_CODE_BEACON = 20008;
    public static final int AWA_CAP_CODE_BEHAVIOR = 20004;
    public static final int AWA_CAP_CODE_DARK_MODE = 20013;
    public static final int AWA_CAP_CODE_HEADSET = 20000;
    public static final int AWA_CAP_CODE_INCAR_BLUETOOTH = 20009;
    public static final int AWA_CAP_CODE_LOCATION_CAPTURE = 20001;
    public static final int AWA_CAP_CODE_LOCATION_LOW_POWER_BARRIER = 20003;
    public static final int AWA_CAP_CODE_LOCATION_NORMAL_BARRIER = 20002;
    public static final int AWA_CAP_CODE_SCREEN = 20010;
    public static final int AWA_CAP_CODE_TIME = 20005;
    public static final int AWA_CAP_CODE_WEATHER = 20007;
    public static final int AWA_CAP_CODE_WIFI = 20011;

    int[] getCapabilities();
}
