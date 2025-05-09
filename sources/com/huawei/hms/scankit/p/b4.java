package com.huawei.hms.scankit.p;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.huawei.hms.hihealth.data.DeviceInfo;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes4.dex */
public class b4 {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f5742a = false;
    public static String b;

    public static String b(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperator();
    }

    public static String c() {
        return Build.MODEL;
    }

    public static String d() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", String.class).invoke(cls, "ro.build.version.emui");
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return null;
        } catch (RuntimeException | InvocationTargetException | Exception unused2) {
            return "";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.getType() == 1) {
            return "wifi";
        }
        if (activeNetworkInfo == null || activeNetworkInfo.getType() != 0) {
            return DeviceInfo.STR_TYPE_UNKNOWN;
        }
        String subtypeName = activeNetworkInfo.getSubtypeName();
        switch (((TelephonyManager) context.getSystemService("phone")).getNetworkType()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return "2G";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return "3G";
            case 13:
                return "4G";
            default:
                if (!subtypeName.equalsIgnoreCase("TD-SCDMA") && !subtypeName.equalsIgnoreCase("WCDMA") && !subtypeName.equalsIgnoreCase("CDMA2000")) {
                    return subtypeName;
                }
                return "3G";
        }
    }

    public static String a() {
        return Build.VERSION.RELEASE;
    }

    public static String a(Context context, boolean z) {
        return new a1(context, z).a();
    }

    public static String b() {
        return "";
    }
}
