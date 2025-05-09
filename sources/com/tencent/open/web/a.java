package com.tencent.open.web;

import android.webkit.WebSettings;
import android.webkit.WebView;
import com.tencent.open.log.SLog;

/* loaded from: classes7.dex */
public class a {
    public static void a(WebView webView) {
        if (webView == null) {
            return;
        }
        b(webView);
        WebSettings settings = webView.getSettings();
        if (settings != null) {
            a(settings);
            b(settings);
        }
    }

    private static void a(WebSettings webSettings) {
        try {
            webSettings.setSavePassword(false);
            webSettings.setAllowFileAccess(false);
            webSettings.setAllowFileAccessFromFileURLs(false);
            webSettings.setAllowUniversalAccessFromFileURLs(false);
        } catch (Exception e) {
            SLog.e("WebViewUtils", "Exception", e);
        }
    }

    private static void b(WebView webView) {
        webView.removeJavascriptInterface("searchBoxJavaBridge_");
        webView.removeJavascriptInterface("accessibility");
        webView.removeJavascriptInterface("accessibilityTraversal");
    }

    private static void b(WebSettings webSettings) {
        webSettings.setJavaScriptEnabled(true);
    }
}
