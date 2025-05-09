package com.huawei.health.h5pro.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes3.dex */
public class H5ProNetworkUtil {
    public static ConnectivityManager c;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface NetworkType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface TransportType {
    }

    public static boolean isWifiConnected(Context context) {
        return isTransportTypeConnected(context, 1);
    }

    public static boolean isVpnConnected(Context context) {
        return isTransportTypeConnected(context, 4);
    }

    public static boolean isTransportTypeConnected(NetworkCapabilities networkCapabilities, int i) {
        if (i == 5) {
            return networkCapabilities.hasTransport(5);
        }
        if (i != 6) {
            return networkCapabilities.hasTransport(i);
        }
        if (Build.VERSION.SDK_INT >= 27) {
            return networkCapabilities.hasTransport(6);
        }
        return false;
    }

    public static boolean isTransportTypeConnected(Context context, int... iArr) {
        if (iArr != null && iArr.length != 0) {
            checkConnectivityManager(context);
            ConnectivityManager connectivityManager = c;
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasCapability(16) && networkCapabilities.hasCapability(12)) {
                for (int i : iArr) {
                    if (isTransportTypeConnected(networkCapabilities, i)) {
                        return true;
                    }
                }
                return false;
            }
            LogUtil.w("H5PRO_H5ProNetworkUtil", "isTransportTypeConnected: network is unavailable");
        }
        return false;
    }

    public static boolean isOtherConnected(Context context) {
        return isTransportTypeConnected(context, 2, 3, 5, 6);
    }

    public static boolean isNetworkTypeConnected(Context context, int... iArr) {
        if (iArr != null && iArr.length != 0) {
            checkConnectivityManager(context);
            NetworkInfo activeNetworkInfo = c.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.isAvailable()) {
                int type = activeNetworkInfo.getType();
                for (int i : iArr) {
                    if (type == i) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isNetworkAvailable(Context context) {
        checkConnectivityManager(context);
        ConnectivityManager connectivityManager = c;
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        if (networkCapabilities == null) {
            LogUtil.w("H5PRO_H5ProNetworkUtil", "isNetworkAvailable: networkCapabilities is null");
            return false;
        }
        if (networkCapabilities.hasCapability(16)) {
            return networkCapabilities.hasCapability(12);
        }
        LogUtil.w("H5PRO_H5ProNetworkUtil", "isNetworkAvailable: no capability NET_CAPABILITY_VALIDATED");
        return false;
    }

    public static boolean isMobileConnected(Context context) {
        return isTransportTypeConnected(context, 0);
    }

    public static void checkConnectivityManager(Context context) {
        if (c == null && context != null) {
            Context applicationContext = context.getApplicationContext();
            ConnectivityManager connectivityManager = (ConnectivityManager) applicationContext.getSystemService(ConnectivityManager.class);
            c = connectivityManager;
            if (connectivityManager == null) {
                c = (ConnectivityManager) applicationContext.getSystemService("connectivity");
            }
        }
    }
}
