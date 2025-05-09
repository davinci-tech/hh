package com.huawei.hms.ads.uiengineloader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.ads.dynamic.DynamicModule;

/* loaded from: classes4.dex */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4381a = "HiAdTools";

    private static boolean a(Context context, Uri uri) {
        if (context == null || uri == null) {
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(uri.getAuthority(), 0);
        if (resolveContentProvider == null) {
            af.d(f4381a, "Invalid param");
            return false;
        }
        ApplicationInfo applicationInfo = resolveContentProvider.applicationInfo;
        if (applicationInfo == null) {
            return false;
        }
        String str = applicationInfo.packageName;
        af.b(f4381a, "Target provider service's package name is : ".concat(String.valueOf(str)));
        if (str == null) {
            return false;
        }
        boolean z = true;
        if (packageManager.checkSignatures(context.getPackageName(), str) != 0 && (applicationInfo.flags & 1) != 1) {
            z = false;
        }
        if (!z) {
            String b = aa.b(context, str);
            boolean isEmpty = TextUtils.isEmpty(b);
            af.b(f4381a, "is sign empty: ".concat(String.valueOf(isEmpty)));
            if (!isEmpty) {
                if (DynamicModule.getCommonInter() != null) {
                    return DynamicModule.getCommonInter().isTrustApp(str, b);
                }
                af.d("LoaderHandler", "DynamicModule.commonInter is null");
                return false;
            }
        }
        return z;
    }

    public static int a(Context context) {
        StringBuilder sb;
        Object obj;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(aa.b(context), 128);
            if (applicationInfo == null || applicationInfo.metaData == null || (obj = applicationInfo.metaData.get("ppskit_ver_code")) == null) {
                return 0;
            }
            String obj2 = obj.toString();
            af.a(f4381a, "ppsKitVerCode: ".concat(String.valueOf(obj2)));
            return f.a(obj2);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("getPpsKitVerCode runtime ex: ");
            sb.append(e.getClass().getSimpleName());
            af.b(f4381a, sb.toString());
            return 0;
        } catch (Throwable th) {
            e = th;
            sb = new StringBuilder("getPpsKitVerCode ex: ");
            sb.append(e.getClass().getSimpleName());
            af.b(f4381a, sb.toString());
            return 0;
        }
    }
}
