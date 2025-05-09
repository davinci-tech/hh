package com.huawei.health.h5pro.utils;

import android.os.Build;

/* loaded from: classes3.dex */
public class H5ProBuild {

    /* renamed from: a, reason: collision with root package name */
    public static final Class f2456a;
    public static final boolean b;
    public static final int c;

    public static boolean isNewHonor() {
        return "HONOR".equals(Build.MANUFACTURER);
    }

    public static boolean isEmuiGe100() {
        return c >= 21;
    }

    public static boolean isEmuiBuildEx() {
        return f2456a != null;
    }

    public static int getEmuiVersionCodeEx() {
        if (!isNewHonor() && isEmuiBuildEx()) {
            return SystemProperties.getInt("ro.build.hw_emui_api_level", 0);
        }
        return 0;
    }

    public static Class getEmuiBuildEx() {
        return CommonUtil.getClass("com.huawei.android.os.BuildEx");
    }

    static {
        b = CommonUtil.getClass("com.hihonor.android.os.Build") != null;
        f2456a = getEmuiBuildEx();
        c = getEmuiVersionCodeEx();
    }
}
