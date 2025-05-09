package com.huawei.openalliance.ad;

import android.net.Uri;
import android.webkit.WebView;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.listeners.IPPSWebEventCallback;
import com.huawei.openalliance.ad.views.interfaces.w;

/* loaded from: classes5.dex */
public interface oj<V extends com.huawei.openalliance.ad.views.interfaces.w> {
    void a();

    void a(int i);

    void a(int i, int i2);

    void a(WebView webView);

    void a(AdLandingPageData adLandingPageData);

    void a(IPPSWebEventCallback iPPSWebEventCallback);

    void a(Object obj, String str, WebView webView);

    void a(String str, WebView webView);

    void a(String str, String str2, String str3);

    boolean a(WebView webView, Uri uri);

    void b();

    void b(qq qqVar);

    String c(String str);

    void c();

    void c(long j);

    void d(long j);

    long k();
}
