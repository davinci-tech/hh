package com.huawei.health.manager.reconnect;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes.dex */
class ScanTimeUtil {
    private static long b = 256000;

    private ScanTimeUtil() {
    }

    public static long a() {
        if (b(BaseApplication.getContext()) && !BaseApplication.isTv()) {
            long j = b;
            if (j == 4000 || j == 1200000) {
                b = 1200000L;
            } else {
                b = 4000L;
            }
        } else {
            long j2 = b;
            if (j2 == 0) {
                b = 4000L;
            } else if (j2 < 256000) {
                b = j2 * 2;
            } else {
                b = 256000L;
            }
        }
        return b;
    }

    public static void c() {
        b = 0L;
    }

    private static boolean b(Context context) {
        try {
            context.getPackageManager().getApplicationInfo("com.huawei.iconnect", 0);
            ReleaseLogUtil.e("DEVMGR_ScanTimeUtil", "iconnect pkg exist.");
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.huawei.iconnect", 0);
            if (packageInfo != null) {
                int i = packageInfo.versionCode;
                LogUtil.a("ScanTimeUtil", "iconnect code: ", Integer.valueOf(i));
                if (i > 1) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            ReleaseLogUtil.c("DEVMGR_ScanTimeUtil", "iconnect pkg do not exist.");
            return false;
        }
    }
}
