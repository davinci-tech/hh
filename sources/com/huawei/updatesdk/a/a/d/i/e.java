package com.huawei.updatesdk.a.a.d.i;

import android.text.TextUtils;

/* loaded from: classes7.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private static String f10811a = "";

    public static boolean a() {
        if ("KidWatch".equals(f10811a)) {
            return true;
        }
        String a2 = c.a("ro.vendor.market.type", "");
        f10811a = a2;
        com.huawei.updatesdk.a.a.a.b("WearDeviceUtil", "Children watch property value is " + a2);
        if (TextUtils.isEmpty(a2)) {
            return false;
        }
        return "KidWatch".equals(a2);
    }
}
