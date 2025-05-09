package defpackage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/* loaded from: classes5.dex */
public class ksw {
    public static String a(Context context) {
        NetworkInfo.State state;
        ksy.b("NetUtils", "getNetType begin", true);
        if (context == null) {
            ksy.c("NetUtils", "context is null", true);
            return "NONE";
        }
        Context applicationContext = context.getApplicationContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) applicationContext.getSystemService("connectivity");
        if (connectivityManager == null) {
            return "NONE";
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            ksy.c("NetUtils", "activeNetInfo is null or activeNetInfo is not available", true);
            return "NONE";
        }
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        if (networkInfo != null && (state = networkInfo.getState()) != null && (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING)) {
            return "WIFI";
        }
        if (applicationContext.checkSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            ksy.b("NetUtils", "no Permission READ_PHONE_STATE", true);
            return "NONE";
        }
        return c(applicationContext);
    }

    private static String c(Context context) {
        if (context == null) {
            ksy.c("NetUtils", "activity is null", true);
            return "NONE";
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            ksy.c("NetUtils", "telephonyManager is null", true);
            return "NONE";
        }
        try {
            int networkType = telephonyManager.getNetworkType();
            ksy.b("NetUtils", "networkType is " + networkType, false);
            switch (networkType) {
            }
        } catch (Throwable th) {
            ksy.c("NetUtils", "no permission " + th.getClass().getSimpleName(), true);
            return "NONE";
        }
        return "NONE";
    }
}
