package com.huawei.hwcloudjs.api;

import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

/* loaded from: classes9.dex */
public interface IJsWebview {
    void onReceivedError(WebView webView, int i, String str, String str2);

    void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse);

    boolean overrideUrlLoading(WebView webView, String str);
}
