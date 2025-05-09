package com.huawei.updatesdk.a.a.d.j;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;

/* loaded from: classes7.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static ConnectivityManager f10812a;

    public static boolean d(Context context) {
        ConnectivityManager b;
        if (context == null || (b = b(context)) == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = b.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return a(b);
        }
        return true;
    }

    public static int c(Context context) {
        return a(a(context));
    }

    private static ConnectivityManager b(Context context) {
        if (f10812a == null) {
            f10812a = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
        }
        return f10812a;
    }

    private static boolean a(ConnectivityManager connectivityManager) {
        NetworkCapabilities networkCapabilities;
        Network activeNetwork = connectivityManager.getActiveNetwork();
        if (activeNetwork == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) == null) {
            return false;
        }
        return networkCapabilities.hasCapability(16);
    }

    private static NetworkInfo a(Context context) {
        ConnectivityManager b = b(context);
        if (b != null) {
            return b.getActiveNetworkInfo();
        }
        return null;
    }

    public static int a(NetworkInfo networkInfo) {
        if (networkInfo != null && networkInfo.isConnected()) {
            int type = networkInfo.getType();
            if (1 == type || 13 == type) {
                return 1;
            }
            if (type == 0) {
                switch (networkInfo.getSubtype()) {
                }
                return 1;
            }
        }
        return 0;
    }
}
