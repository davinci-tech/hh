package com.huawei.hwcloudjs.f;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Looper;
import android.webkit.WebView;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static final String f6223a = "DeviceUtils";

    public static boolean f() {
        Context a2 = com.huawei.hwcloudjs.b.a.a();
        if (a2 == null || !(a2.getSystemService("connectivity") instanceof ConnectivityManager)) {
            return true;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) a2.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }

    public static String e() {
        return Build.DISPLAY;
    }

    public static String d() {
        return Build.VERSION.RELEASE;
    }

    public static String c() {
        return Build.MODEL;
    }

    private static String b(WebView webView) {
        StringBuilder sb;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        String[] strArr = {null};
        try {
            d.c(f6223a, "webView post", true);
            webView.post(new a(strArr, webView, countDownLatch));
            d.c(f6223a, "webView await", true);
            if (!countDownLatch.await(500L, TimeUnit.MILLISECONDS)) {
                d.c(f6223a, "webView await false", true);
            }
        } catch (InterruptedException e) {
            e = e;
            sb = new StringBuilder("latch.await InterruptedException:");
            sb.append(e.getClass().getSimpleName());
            d.b(f6223a, sb.toString(), true);
            return strArr[0];
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("getWebViewUrl Exception:");
            sb.append(e.getClass().getSimpleName());
            d.b(f6223a, sb.toString(), true);
            return strArr[0];
        }
        return strArr[0];
    }

    public static String b() {
        return Locale.getDefault().getLanguage();
    }

    public static String a(WebView webView) {
        if (webView == null) {
            return "";
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return b(webView);
        }
        try {
            return webView.getUrl();
        } catch (Exception e) {
            d.b(f6223a, "getWebViewUrl myLooper Exception:" + e.getClass().getSimpleName(), true);
            return "";
        }
    }

    public static String a() {
        return Locale.getDefault().getCountry();
    }
}
