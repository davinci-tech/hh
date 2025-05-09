package com.huawei.watchface;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.huawei.secure.android.common.webview.UriUtil;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes7.dex */
public class dj {

    /* renamed from: a, reason: collision with root package name */
    public static final String f10987a = "dj";
    private static Integer b;

    public static boolean a() {
        return a(Environment.getApplicationContext());
    }

    public static boolean a(Context context) {
        if (context == null) {
            HwLog.w(f10987a, "Network no Available: context is null.");
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            HwLog.w(f10987a, "Network no Available: ConnectivityManager is null.");
            return false;
        }
        try {
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork == null) {
                HwLog.w(f10987a, "Network no Available: ActiveNetwork is null.");
                return false;
            }
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
            if (networkCapabilities == null) {
                HwLog.w(f10987a, "Network no Available: NetworkCapabilities is null.");
                return false;
            }
            if (networkCapabilities.hasCapability(16)) {
                return true;
            }
            HwLog.w(f10987a, "Network not has capability.");
            return connectivityManager.isDefaultNetworkActive();
        } catch (SecurityException e) {
            HwLog.e(f10987a, "isNetworkAvailable SecurityException " + HwLog.printException((Exception) e));
            return false;
        }
    }

    public static String a(String str) {
        if (str != null) {
            try {
                return UriUtil.getHostByURI(str);
            } catch (Exception e) {
                HwLog.e(f10987a, HwLog.printException(e));
            }
        }
        return null;
    }

    public static int b() {
        NetworkInfo activeNetworkInfo;
        NetworkInfo.State state;
        Application applicationContext = Environment.getApplicationContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) applicationContext.getSystemService("connectivity");
        int i = 0;
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) {
            return 0;
        }
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        if (networkInfo != null && (state = networkInfo.getState()) != null && (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING)) {
            return 1;
        }
        TelephonyManager telephonyManager = (TelephonyManager) applicationContext.getSystemService("phone");
        if (telephonyManager != null) {
            try {
                i = telephonyManager.getNetworkType();
            } catch (Exception e) {
                HwLog.e(f10987a, "getNetworkType error =" + HwLog.printException(e));
            }
        }
        switch (i) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return 2;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return 3;
            case 13:
                return 4;
            default:
                return 5;
        }
    }

    private static NetworkCapabilities d() {
        Application applicationContext = Environment.getApplicationContext();
        if (applicationContext == null) {
            HwLog.w(f10987a, "getNetworkCapability Network no Available: context is null.");
            return null;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) applicationContext.getSystemService("connectivity");
        if (connectivityManager == null) {
            HwLog.w(f10987a, "getNetworkCapability Network no Available: ConnectivityManager is null.");
            return null;
        }
        Network activeNetwork = connectivityManager.getActiveNetwork();
        if (activeNetwork == null) {
            HwLog.w(f10987a, "getNetworkCapability Network no Available: ActiveNetwork is null.");
            return null;
        }
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
        if (networkCapabilities == null) {
            HwLog.w(f10987a, "getNetworkCapability Network no Available: NetworkCapabilities is null.");
        }
        return networkCapabilities;
    }

    public static void a(Integer num) {
        b = num;
    }

    public static int c() {
        boolean z;
        boolean z2;
        Integer num = b;
        if (num != null) {
            return num.intValue();
        }
        int b2 = b();
        NetworkCapabilities d = d();
        if (d != null) {
            z = d.hasCapability(16);
            z2 = d.hasCapability(17);
        } else {
            z = false;
            z2 = false;
        }
        if (!z || z2) {
            if (b2 == 1) {
                b2 = z2 ? 11 : 6;
            } else if (b2 == 2) {
                b2 = 7;
            } else if (b2 == 3) {
                b2 = 8;
            } else if (b2 == 4) {
                b2 = 9;
            } else if (b2 == 5) {
                b2 = 10;
            }
        }
        b = Integer.valueOf(b2);
        return b2;
    }

    public static String b(String str) {
        return TextUtils.isEmpty(str) ? "ip empty" : str.contains(":") ? "ipv6" : str.contains(".") ? "ipv4" : "ip unknown";
    }
}
