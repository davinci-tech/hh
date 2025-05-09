package com.huawei.hwauthutil.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes.dex */
public class PackageManagerHelper {
    private final PackageManager b;

    public PackageManagerHelper(Context context) {
        if (context == null) {
            this.b = null;
        } else {
            this.b = context.getPackageManager();
        }
    }

    public int a(String str) {
        PackageManager packageManager = this.b;
        if (packageManager == null) {
            return -1;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 16);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
            return 0;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("PackageManagerHelper", "getPackageVersionCode, NameNotFoundException.");
            return 0;
        }
    }
}
