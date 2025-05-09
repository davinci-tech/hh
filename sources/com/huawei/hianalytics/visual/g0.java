package com.huawei.hianalytics.visual;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.huawei.hianalytics.core.log.HiLog;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class g0 {

    /* renamed from: a, reason: collision with root package name */
    public static final Map<Integer, String> f3919a = new a();

    public static final class a extends HashMap<Integer, String> {
        public a() {
            put(1, "2G");
            put(2, "2G");
            put(4, "2G");
            put(7, "2G");
            put(11, "2G");
            put(3, "3G");
            put(8, "3G");
            put(9, "3G");
            put(10, "3G");
            put(15, "3G");
            put(5, "3G");
            put(6, "3G");
            put(12, "3G");
            put(14, "3G");
            put(13, "4G");
            if (Build.VERSION.SDK_INT >= 29) {
                put(20, "5G");
            }
        }
    }

    public static NetworkInfo a(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                return connectivityManager.getActiveNetworkInfo();
            }
            return null;
        } catch (Throwable th) {
            HiLog.e("NetworkTypeUtils", "cannot get network state, ensure permission android.permission.ACCESS_NETWORK_STATE in the manifest: " + th.getMessage());
            return null;
        }
    }

    public static String b(Context context) {
        if (context == null) {
            return "unknown";
        }
        try {
            NetworkInfo a2 = a(context);
            if (a2 == null || !a2.isConnected()) {
                return "none";
            }
            if (a2 != null) {
                int[] iArr = {1, 6, 7, 9};
                for (int i = 0; i < 4; i++) {
                    if (iArr[i] == a2.getType()) {
                        return "wifi";
                    }
                }
            }
            if (a2 == null) {
                return "unknown";
            }
            int[] iArr2 = {0, 2, 3, 4, 5};
            for (int i2 = 0; i2 < 5; i2++) {
                if (iArr2[i2] == a2.getType()) {
                    int subtype = a2.getSubtype();
                    Map<Integer, String> map = f3919a;
                    String str = map.containsKey(Integer.valueOf(subtype)) ? map.get(Integer.valueOf(subtype)) : "unknown";
                    if (str == null) {
                        str = "unknown";
                    }
                    return str.equals("unknown") ? subtype != 16 ? subtype != 17 ? "unknown" : "3G" : "2G" : str;
                }
            }
            return "unknown";
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    public static boolean c(Context context) {
        NetworkInfo a2;
        try {
            a2 = a(context);
        } catch (Throwable unused) {
        }
        return a2 != null && a2.isConnected();
    }
}
