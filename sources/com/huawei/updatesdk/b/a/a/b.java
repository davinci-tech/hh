package com.huawei.updatesdk.b.a.a;

import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.huawei.updatesdk.a.a.d.d;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private final Map<String, com.huawei.updatesdk.b.a.a.a> f10820a;

    public String a(PackageInfo packageInfo) {
        if (packageInfo == null || packageInfo.packageName == null || TextUtils.isEmpty(packageInfo.applicationInfo.sourceDir)) {
            return null;
        }
        com.huawei.updatesdk.b.a.a.a aVar = this.f10820a.get(packageInfo.packageName);
        if (aVar != null && aVar.b() == packageInfo.lastUpdateTime && aVar.c() == packageInfo.versionCode) {
            return aVar.a();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(packageInfo.lastUpdateTime);
        sb.append(packageInfo.versionCode);
        sb.append(packageInfo.packageName);
        String str = "packagekey" + packageInfo.packageName;
        String str2 = "fileshakey" + packageInfo.packageName;
        boolean z = !TextUtils.equals(sb.toString(), com.huawei.updatesdk.b.b.a.d().a(str));
        if (z) {
            com.huawei.updatesdk.b.b.a.d().a(str, sb.toString());
        }
        String a2 = com.huawei.updatesdk.b.b.a.d().a(str2);
        if (TextUtils.isEmpty(a2) || z) {
            a2 = d.a(packageInfo.applicationInfo.sourceDir, "SHA-256");
            com.huawei.updatesdk.b.b.a.d().a(str2, a2);
        }
        com.huawei.updatesdk.b.a.a.a aVar2 = new com.huawei.updatesdk.b.a.a.a();
        aVar2.a(a2);
        aVar2.a(packageInfo.lastUpdateTime);
        aVar2.a(packageInfo.versionCode);
        this.f10820a.put(packageInfo.packageName, aVar2);
        return a2;
    }

    /* renamed from: com.huawei.updatesdk.b.a.a.b$b, reason: collision with other inner class name */
    static class C0276b {

        /* renamed from: a, reason: collision with root package name */
        private static final b f10821a = new b();
    }

    public static b a() {
        return C0276b.f10821a;
    }

    private b() {
        this.f10820a = new HashMap();
    }
}
