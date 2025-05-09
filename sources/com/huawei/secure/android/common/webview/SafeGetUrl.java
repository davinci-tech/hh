package com.huawei.secure.android.common.webview;

import android.util.Log;
import android.webkit.WebView;
import com.huawei.secure.android.common.util.b;
import com.huawei.secure.android.common.util.c;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes9.dex */
public class SafeGetUrl {
    private static final String c = "SafeGetUrl";
    private static final long d = 200;

    /* renamed from: a, reason: collision with root package name */
    private String f8659a;
    private WebView b;

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ CountDownLatch f8660a;

        a(CountDownLatch countDownLatch) {
            this.f8660a = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            SafeGetUrl safeGetUrl = SafeGetUrl.this;
            safeGetUrl.setUrl(safeGetUrl.b.getUrl());
            this.f8660a.countDown();
        }
    }

    public SafeGetUrl() {
    }

    public String getUrlMethod() {
        if (this.b == null) {
            return "";
        }
        if (b.a()) {
            return this.b.getUrl();
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        c.a(new a(countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            Log.e(c, "getUrlMethod: InterruptedException " + e.getMessage(), e);
        }
        return this.f8659a;
    }

    public WebView getWebView() {
        return this.b;
    }

    public void setUrl(String str) {
        this.f8659a = str;
    }

    public void setWebView(WebView webView) {
        this.b = webView;
    }

    public SafeGetUrl(WebView webView) {
        this.b = webView;
    }
}
