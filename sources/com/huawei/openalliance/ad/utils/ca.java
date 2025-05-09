package com.huawei.openalliance.ad.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.huawei.openalliance.ad.ho;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class ca {

    /* renamed from: a, reason: collision with root package name */
    private final PackageManager f7651a;

    /* JADX WARN: Removed duplicated region for block: B:10:0x003b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.String r0 = "PackageManagerHelper"
            boolean r1 = android.text.TextUtils.isEmpty(r5)
            r2 = 0
            if (r1 == 0) goto La
            return r2
        La:
            android.content.pm.PackageManager r1 = r4.f7651a     // Catch: java.lang.Exception -> L11 android.content.pm.PackageManager.NameNotFoundException -> L2c
            android.content.pm.ApplicationInfo r5 = r1.getApplicationInfo(r5, r2)     // Catch: java.lang.Exception -> L11 android.content.pm.PackageManager.NameNotFoundException -> L2c
            goto L36
        L11:
            r5 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "getApplicationInfo "
            r1.<init>(r3)
            java.lang.Class r5 = r5.getClass()
            java.lang.String r5 = r5.getSimpleName()
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            com.huawei.openalliance.ad.ho.c(r0, r5)
            goto L35
        L2c:
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            java.lang.String r1 = "isAppInstalledStatusEnable package not find! package:%s"
            com.huawei.openalliance.ad.ho.a(r0, r1, r5)
        L35:
            r5 = 0
        L36:
            if (r5 == 0) goto L3b
            boolean r5 = r5.enabled
            return r5
        L3b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.utils.ca.a(java.lang.String):boolean");
    }

    public String a(Intent intent) {
        if (intent == null) {
            return null;
        }
        List<ResolveInfo> queryIntentActivities = this.f7651a.queryIntentActivities(intent, 0);
        int size = queryIntentActivities.size();
        for (int i = 0; i < size; i++) {
            String str = queryIntentActivities.get(i).activityInfo.packageName;
            if (b(str)) {
                return str;
            }
        }
        return null;
    }

    private boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        this.f7651a.getPreferredActivities(arrayList, arrayList2, str);
        int size = arrayList2.size();
        for (int i = 0; i < size; i++) {
            ho.a("PackageManagerHelper", " preferredCompents pkg: %s", ((ComponentName) arrayList2.get(i)).getPackageName());
        }
        return arrayList2.size() > 0;
    }

    public ca(Context context) {
        this.f7651a = context.getPackageManager();
    }
}
