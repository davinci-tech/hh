package com.huawei.hwcloudjs.service.jsapi;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import com.huawei.hwcloudjs.f.d;
import com.huawei.hwcloudjs.f.h;
import com.huawei.hwcloudjs.service.jsapi.bean.BrowserInfo;

/* loaded from: classes9.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private static final String f6260a = "JsCoreHelper";
    private static a b = new a();

    public String a(Context context, String str) {
        Signature[] signatureArr;
        d.c(f6260a, "getAppSign begin", true);
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return "";
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 64);
            return (packageInfo == null || (signatureArr = packageInfo.signatures) == null || signatureArr.length <= 0) ? "" : signatureArr[0].toCharsString();
        } catch (PackageManager.NameNotFoundException unused) {
            d.b(f6260a, "getSign PackageManager.NameNotFoundException", true);
            return "";
        }
    }

    public BrowserInfo a(Context context) {
        d.c(f6260a, "getBrowserInfo begin", true);
        BrowserInfo browserInfo = new BrowserInfo();
        String packageName = context.getPackageName();
        browserInfo.setBrowserType(packageName);
        browserInfo.setClientVersion(h.b(context, packageName));
        browserInfo.setClientVersionCode(h.a(context, packageName) + "");
        return browserInfo;
    }

    public static a a() {
        return b;
    }

    private a() {
    }
}
