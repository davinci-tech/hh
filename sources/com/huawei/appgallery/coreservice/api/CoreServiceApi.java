package com.huawei.appgallery.coreservice.api;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.huawei.appgallery.coreservice.e;
import defpackage.afr;
import defpackage.afu;

/* loaded from: classes8.dex */
public abstract class CoreServiceApi {
    public static void setHomeCountry(Context context, String str) {
        afr.e().d(context, str);
    }

    @Deprecated
    public static void setAppName(Context context, String str) {
        afr.e().e(context, str);
    }

    public static IConnectionResult getGuideInstallPendingIntent(Context context) {
        return e.b(context);
    }

    public static String getAppGalleryPkg(Context context) {
        return e.a(context);
    }

    public static AppGalleryInfo getAppGalleryInfo(Context context) {
        String a2 = e.a(context);
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(a2, 128);
            if (packageInfo != null) {
                return new AppGalleryInfo(packageInfo.versionName, packageInfo.versionCode);
            }
        } catch (PackageManager.NameNotFoundException unused) {
            afu.b("CoreServiceApi", "NameNotFoundException ");
        } catch (RuntimeException unused2) {
            afu.a("CoreServiceApi", "getPackageInfo RuntimeException");
        }
        return null;
    }
}
