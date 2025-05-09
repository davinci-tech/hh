package com.amap.api.col.p0003sl;

import com.amap.api.col.p0003sl.hz;
import com.amap.api.services.core.ServiceSettings;

/* loaded from: classes2.dex */
public final class fc {

    /* renamed from: a, reason: collision with root package name */
    public static final String[] f1033a = {"com.amap.api.services", "com.amap.api.search.admic"};

    public static String a() {
        return ServiceSettings.getInstance().getProtocol() == 1 ? "http://restsdk.amap.com/v3" : "https://restsdk.amap.com/v3";
    }

    public static String b() {
        return ServiceSettings.getInstance().getProtocol() == 1 ? "http://restsdk.amap.com/v4" : "https://restsdk.amap.com/v4";
    }

    public static String c() {
        return ServiceSettings.getInstance().getProtocol() == 1 ? "http://restsdk.amap.com/v5" : "https://restsdk.amap.com/v5";
    }

    public static String d() {
        return ServiceSettings.getInstance().getProtocol() == 1 ? "http://yuntuapi.amap.com" : "https://yuntuapi.amap.com";
    }

    public static String e() {
        return ServiceSettings.getInstance().getProtocol() == 1 ? "http://restsdk.amap.com/rest/me/cpoint" : "https://restsdk.amap.com/rest/me/cpoint";
    }

    public static hz a(boolean z) {
        try {
            return new hz.a("sea", "9.2.0", "AMAP SDK Android Search 9.2.0").a(f1033a).a(z).a("9.2.0").a();
        } catch (hm e) {
            fd.a(e, "ConfigableConst", "getSDKInfo");
            return null;
        }
    }

    public static String f() {
        return ServiceSettings.getInstance().getProtocol() == 1 ? "http://m5.amap.com/ws/mapapi/shortaddress/transform" : "https://m5.amap.com/ws/mapapi/shortaddress/transform";
    }
}
