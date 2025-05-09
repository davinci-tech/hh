package com.huawei.hms.maps.utils;

/* loaded from: classes4.dex */
public class MapsAdvUtil {

    /* renamed from: a, reason: collision with root package name */
    private static String f5033a = "";

    public static void setAccessToken(String str) {
        f5033a = str;
    }

    public static String getAccessToken() {
        return f5033a;
    }

    public static boolean containsMapsBasic() {
        return a("com.huawei.hms.maps.provider.inhuawei.MapViewDelegate");
    }

    public static boolean containsMapsAdvance() {
        return a("com.huawei.hms.maps.HuaweiMapEx");
    }

    private static boolean a(String str) {
        try {
            Class.forName(str, false, MapsAdvUtil.class.getClassLoader());
            return true;
        } catch (ClassNotFoundException unused) {
            LogM.d("MapsAdvUtil", "The class:" + str + " not found.");
            return false;
        }
    }
}
