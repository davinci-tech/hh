package com.huawei.hianalytics;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.huawei.hianalytics.core.common.EnvUtils;
import com.huawei.hianalytics.core.log.HiLog;

/* loaded from: classes4.dex */
public class x0 {

    /* renamed from: a, reason: collision with root package name */
    public static final Object f3962a = new Object();

    /* renamed from: a, reason: collision with other field name */
    public static boolean f107a = false;
    public static boolean b = false;

    public static boolean a(boolean z) {
        ApplicationInfo applicationInfo;
        boolean containsKey;
        if (!z) {
            return a();
        }
        Context appContext = EnvUtils.getAppContext();
        if (appContext != null) {
            try {
                applicationInfo = appContext.getPackageManager().getApplicationInfo(appContext.getPackageName(), 128);
            } catch (Throwable unused) {
                HiLog.w("HmsBaseExist", "applicationInfo not found");
            }
            if (applicationInfo.metaData != null) {
                containsKey = applicationInfo.metaData.containsKey("com.huawei.hms.client.appid");
                HiLog.i("HmsBaseExist", "contains appId: " + containsKey);
                return a() && containsKey;
            }
        }
        containsKey = false;
        HiLog.i("HmsBaseExist", "contains appId: " + containsKey);
        if (a()) {
            return false;
        }
    }

    public static boolean a() {
        if (!f107a) {
            synchronized (f3962a) {
                try {
                    Class.forName("com.huawei.hms.common.HuaweiApi");
                    b = true;
                } catch (ClassNotFoundException unused) {
                    b = false;
                }
                f107a = true;
            }
        }
        boolean z = b;
        HiLog.i("HmsBaseExist", "hms base exist: " + z);
        return z;
    }
}
