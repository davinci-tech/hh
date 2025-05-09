package com.huawei.secure.android.common.detect;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.secure.android.common.detect.b.c;

/* loaded from: classes9.dex */
public abstract class ProxyDetect {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8573a = "ProxyDetect";

    public static boolean isWifiProxy(Context context) {
        int i;
        String str = null;
        try {
            str = System.getProperty("http.proxyHost");
            String property = System.getProperty("http.proxyPort");
            if (property == null) {
                property = "-1";
            }
            i = Integer.parseInt(property);
        } catch (SecurityException e) {
            c.b(f8573a, "message : " + e.getMessage());
            i = 0;
        }
        return (TextUtils.isEmpty(str) || i == -1) ? false : true;
    }
}
