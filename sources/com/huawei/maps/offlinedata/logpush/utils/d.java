package com.huawei.maps.offlinedata.logpush.utils;

import com.huawei.hms.framework.common.SystemPropUtils;
import com.huawei.maps.offlinedata.utils.g;

/* loaded from: classes5.dex */
public class d {
    public static boolean a() {
        boolean equalsIgnoreCase = "CN".equalsIgnoreCase(a("ro.product.locale.region", ""));
        g.a("ROMUtil", "isChineseRom is " + equalsIgnoreCase);
        return equalsIgnoreCase;
    }

    public static String a(String str, String str2) {
        return SystemPropUtils.getProperty("get", str, "android.os.SystemProperties", str2);
    }

    public static boolean b() {
        return a.a(com.huawei.maps.offlinedata.utils.a.a());
    }
}
