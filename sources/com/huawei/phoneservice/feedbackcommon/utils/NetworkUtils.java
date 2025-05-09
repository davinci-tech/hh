package com.huawei.phoneservice.feedbackcommon.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;

/* loaded from: classes6.dex */
public class NetworkUtils {
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager;
        Network activeNetwork;
        NetworkCapabilities networkCapabilities;
        if (context == null || (activeNetwork = (connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetwork()) == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) == null) {
            return false;
        }
        return networkCapabilities.hasTransport(1);
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo()) == null) {
            return false;
        }
        return activeNetworkInfo.isAvailable();
    }
}
