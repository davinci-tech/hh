package com.huawei.hianalytics;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.huawei.hianalytics.core.log.HiLog;

/* loaded from: classes4.dex */
public abstract class n0 {
    public abstract int a();

    public final String a(Context context) {
        TelephonyManager telephonyManager;
        String deviceId;
        l m550a = i.a().m550a();
        if (TextUtils.isEmpty(m550a.f51b)) {
            if (j.m562a(context, "android.permission.READ_PHONE_STATE")) {
                try {
                    telephonyManager = (TelephonyManager) context.getSystemService("phone");
                } catch (SecurityException unused) {
                    HiLog.e("DeviceIdUtils", "no permissions");
                }
                if (telephonyManager != null) {
                    deviceId = telephonyManager.getDeviceId();
                    m550a.f51b = deviceId;
                }
            }
            deviceId = "";
            m550a.f51b = deviceId;
        }
        return m550a.f51b;
    }

    public final String b(Context context) {
        String str;
        l m550a = i.a().m550a();
        if (TextUtils.isEmpty(m550a.d)) {
            String str2 = "";
            if (context != null && j.m562a(context, "android.permission.READ_PHONE_STATE")) {
                try {
                    str = Build.getSerial();
                } catch (SecurityException unused) {
                    HiLog.e("DeviceIdUtils", "no permissions");
                    str = "";
                }
                if (!str.equalsIgnoreCase("unknown")) {
                    str2 = str;
                }
            }
            m550a.d = str2;
        }
        return m550a.d;
    }
}
