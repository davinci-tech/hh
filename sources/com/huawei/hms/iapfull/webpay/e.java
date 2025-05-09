package com.huawei.hms.iapfull.webpay;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* loaded from: classes4.dex */
class e extends WebChromeClient {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ WebViewActivity f4781a;

    @Override // android.webkit.WebChromeClient
    public void onProgressChanged(WebView webView, int i) {
        if (i == 100) {
            this.f4781a.b.setVisibility(8);
        } else {
            this.f4781a.b.setVisibility(0);
            this.f4781a.b.setProgress(i);
        }
        super.onProgressChanged(webView, i);
    }

    e(WebViewActivity webViewActivity) {
        this.f4781a = webViewActivity;
    }
}
