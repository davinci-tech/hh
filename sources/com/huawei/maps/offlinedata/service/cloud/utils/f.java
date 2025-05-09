package com.huawei.maps.offlinedata.service.cloud.utils;

import com.huawei.maps.offlinedata.BuildConfig;

/* loaded from: classes5.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    private static int f6471a;
    private static String b;

    public static int a() {
        String b2 = b();
        if (b2 == null || b2.length() == 0) {
            return f6471a;
        }
        String[] split = b2.split("\\.");
        if (split.length > 2 && split[2].length() == 1) {
            split[2] = "0" + split[2];
        }
        int length = split.length <= 4 ? split.length : 4;
        String str = "";
        for (int i = 0; i < length; i++) {
            str = str + split[i];
        }
        if (str.length() > 0) {
            f6471a = Integer.parseInt(str);
        }
        return f6471a;
    }

    public static String b() {
        String str = b;
        if (str != null) {
            return str;
        }
        b = BuildConfig.VERSION_NAME;
        return BuildConfig.VERSION_NAME;
    }
}
