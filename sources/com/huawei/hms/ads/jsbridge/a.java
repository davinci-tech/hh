package com.huawei.hms.ads.jsbridge;

import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import com.huawei.hms.ads.jsb.IWebView;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private volatile String f4342a;

    public String a(IWebView iWebView) {
        if (iWebView == null) {
            return null;
        }
        String str = this.f4342a;
        return str != null ? str : b(iWebView);
    }

    public String a(WebView webView) {
        if (webView == null) {
            return null;
        }
        String str = this.f4342a;
        if (str != null) {
            return str;
        }
        b.a("securityExtSetFrameUrl is null ,get url from native");
        return b(webView);
    }

    private static String b(IWebView iWebView) {
        try {
            return a(new FutureTask(new CallableC0079a(iWebView))).get(1L, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            b.b("Exception will waiting: " + e.getMessage());
            b.b("exception or timeout while waiting for url");
            return null;
        }
    }

    /* renamed from: com.huawei.hms.ads.jsbridge.a$a, reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    static class CallableC0079a implements Callable<String> {

        /* renamed from: a, reason: collision with root package name */
        private IWebView f4343a;
        private WebView b;
        private boolean c;

        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public String call() {
            if (this.c) {
                IWebView iWebView = this.f4343a;
                if (iWebView != null) {
                    return iWebView.getUrl();
                }
                return null;
            }
            WebView webView = this.b;
            if (webView != null) {
                return webView.getUrl();
            }
            return null;
        }

        CallableC0079a(IWebView iWebView) {
            this.c = true;
            this.f4343a = iWebView;
        }

        CallableC0079a(WebView webView) {
            this.b = webView;
        }
    }

    private static String b(WebView webView) {
        try {
            return a(new FutureTask(new CallableC0079a(webView))).get(1L, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            b.b("Exception will waiting: " + e.getMessage());
            b.b("exception or timeout while waiting for url");
            return null;
        }
    }

    public static void a(Runnable runnable) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    private static Future<String> a(FutureTask futureTask) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            futureTask.run();
        } else {
            new Handler(Looper.getMainLooper()).post(futureTask);
        }
        return futureTask;
    }
}
