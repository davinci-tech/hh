package com.huawei.hwcloudjs;

import android.webkit.WebView;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;

/* loaded from: classes5.dex */
final class d implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ WebView f6201a;
    final /* synthetic */ String b;

    @Override // java.lang.Runnable
    public void run() {
        this.f6201a.getSettings().setSavePassword(false);
        WebView webView = this.f6201a;
        String str = this.b;
        webView.loadUrl(str);
        WebViewInstrumentation.loadUrl(webView, str);
    }

    d(WebView webView, String str) {
        this.f6201a = webView;
        this.b = str;
    }
}
