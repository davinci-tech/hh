package com.huawei.updatesdk.b.g;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.text.TextUtils;

/* loaded from: classes7.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private static String f10841a;

    public static String a() {
        String str = f10841a;
        if (str != null) {
            return str;
        }
        Context a2 = com.huawei.updatesdk.a.b.a.a.c().a();
        try {
            StringBuilder sb = new StringBuilder("UpdateSDK##5.0.2.300##");
            sb.append(TextUtils.isEmpty(Build.BRAND) ? "other" : Build.BRAND);
            sb.append("##");
            sb.append(com.huawei.updatesdk.b.c.b.a().e());
            sb.append("##");
            sb.append(a2.getPackageName());
            PackageInfo packageInfo = a2.getPackageManager().getPackageInfo(a2.getPackageName(), 0);
            if (packageInfo != null) {
                sb.append("##");
                sb.append(packageInfo.versionName);
            }
            String sb2 = sb.toString();
            f10841a = sb2;
            return sb2;
        } catch (Exception e) {
            com.huawei.updatesdk.a.a.c.a.a.a.b("ApplicationSession", "getUserAgent() " + e.getMessage());
            return null;
        }
    }
}
