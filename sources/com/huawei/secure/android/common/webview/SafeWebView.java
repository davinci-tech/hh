package com.huawei.secure.android.common.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SafeBrowsingResponse;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.huawei.secure.android.common.util.LogsUtil;
import com.huawei.secure.android.common.webview.WebViewLoadCallBack;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes6.dex */
public class SafeWebView extends WebView {
    private static final String f = "SafeWebView";

    /* renamed from: a, reason: collision with root package name */
    private String f8661a;
    private String[] b;
    private String[] c;
    private String[] d;
    private WebViewLoadCallBack e;

    final class b extends WebViewClient {

        /* renamed from: a, reason: collision with root package name */
        private WebViewClient f8662a;
        private boolean b;

        @Override // android.webkit.WebViewClient
        public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
            WebViewClient webViewClient = this.f8662a;
            if (webViewClient != null) {
                webViewClient.doUpdateVisitedHistory(webView, str, z);
            } else {
                super.doUpdateVisitedHistory(webView, str, z);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onFormResubmission(WebView webView, Message message, Message message2) {
            WebViewClient webViewClient = this.f8662a;
            if (webViewClient != null) {
                webViewClient.onFormResubmission(webView, message, message2);
            } else {
                super.onFormResubmission(webView, message, message2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onLoadResource(WebView webView, String str) {
            WebViewClient webViewClient = this.f8662a;
            if (webViewClient != null) {
                webViewClient.onLoadResource(webView, str);
            } else {
                super.onLoadResource(webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageCommitVisible(WebView webView, String str) {
            WebViewClient webViewClient = this.f8662a;
            if (webViewClient != null) {
                webViewClient.onPageCommitVisible(webView, str);
            } else {
                super.onPageCommitVisible(webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            WebViewClient webViewClient = this.f8662a;
            if (webViewClient != null) {
                webViewClient.onPageFinished(webView, str);
            } else {
                super.onPageFinished(webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            WebViewClient webViewClient = this.f8662a;
            if (webViewClient != null && !this.b) {
                webViewClient.onPageStarted(webView, str, bitmap);
            } else if (SafeWebView.this.isWhiteListUrl(str)) {
                super.onPageStarted(webView, str, bitmap);
            } else {
                SafeWebView.this.onCheckError(webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedClientCertRequest(WebView webView, ClientCertRequest clientCertRequest) {
            WebViewClient webViewClient = this.f8662a;
            if (webViewClient != null) {
                webViewClient.onReceivedClientCertRequest(webView, clientCertRequest);
            } else {
                super.onReceivedClientCertRequest(webView, clientCertRequest);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            WebViewClient webViewClient = this.f8662a;
            if (webViewClient != null) {
                webViewClient.onReceivedError(webView, i, str, str2);
            } else {
                super.onReceivedError(webView, i, str, str2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
            WebViewClient webViewClient = this.f8662a;
            if (webViewClient != null) {
                webViewClient.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
            } else {
                super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            WebViewClient webViewClient = this.f8662a;
            if (webViewClient != null) {
                webViewClient.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            } else {
                super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
            WebViewClient webViewClient = this.f8662a;
            if (webViewClient != null) {
                webViewClient.onReceivedLoginRequest(webView, str, str2, str3);
            } else {
                super.onReceivedLoginRequest(webView, str, str2, str3);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            WebViewClient webViewClient = this.f8662a;
            if (webViewClient != null) {
                webViewClient.onReceivedSslError(webView, sslErrorHandler, sslError);
            } else {
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
            }
        }

        @Override // android.webkit.WebViewClient
        public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
            WebViewClient webViewClient = this.f8662a;
            return webViewClient != null ? webViewClient.onRenderProcessGone(webView, renderProcessGoneDetail) : super.onRenderProcessGone(webView, renderProcessGoneDetail);
        }

        @Override // android.webkit.WebViewClient
        public void onSafeBrowsingHit(WebView webView, WebResourceRequest webResourceRequest, int i, SafeBrowsingResponse safeBrowsingResponse) {
            WebViewClient webViewClient = this.f8662a;
            if (webViewClient != null) {
                webViewClient.onSafeBrowsingHit(webView, webResourceRequest, i, safeBrowsingResponse);
            } else {
                super.onSafeBrowsingHit(webView, webResourceRequest, i, safeBrowsingResponse);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onScaleChanged(WebView webView, float f, float f2) {
            WebViewClient webViewClient = this.f8662a;
            if (webViewClient != null) {
                webViewClient.onScaleChanged(webView, f, f2);
            } else {
                super.onScaleChanged(webView, f, f2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onTooManyRedirects(WebView webView, Message message, Message message2) {
            WebViewClient webViewClient = this.f8662a;
            if (webViewClient != null) {
                webViewClient.onTooManyRedirects(webView, message, message2);
            } else {
                super.onTooManyRedirects(webView, message, message2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
            WebViewClient webViewClient = this.f8662a;
            if (webViewClient != null) {
                webViewClient.onUnhandledKeyEvent(webView, keyEvent);
            } else {
                super.onUnhandledKeyEvent(webView, keyEvent);
            }
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            WebViewClient webViewClient = this.f8662a;
            return webViewClient != null ? webViewClient.shouldInterceptRequest(webView, webResourceRequest) : super.shouldInterceptRequest(webView, webResourceRequest);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
            WebViewClient webViewClient = this.f8662a;
            return webViewClient != null ? webViewClient.shouldOverrideKeyEvent(webView, keyEvent) : super.shouldOverrideKeyEvent(webView, keyEvent);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            WebViewClient webViewClient = this.f8662a;
            if (webViewClient != null && !this.b) {
                return webViewClient.shouldOverrideUrlLoading(webView, str);
            }
            if (SafeWebView.this.isWhiteListUrl(str)) {
                WebViewClient webViewClient2 = this.f8662a;
                return webViewClient2 != null ? webViewClient2.shouldOverrideUrlLoading(webView, str) : super.shouldOverrideUrlLoading(webView, str);
            }
            SafeWebView.this.onCheckError(webView, str);
            return false;
        }

        private b(WebViewClient webViewClient, boolean z) {
            this.f8662a = webViewClient;
            this.b = z;
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            WebViewClient webViewClient = this.f8662a;
            if (webViewClient != null) {
                webViewClient.onReceivedError(webView, webResourceRequest, webResourceError);
            } else {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
            }
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            WebViewClient webViewClient = this.f8662a;
            if (webViewClient != null) {
                return webViewClient.shouldInterceptRequest(webView, str);
            }
            return super.shouldInterceptRequest(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            WebViewClient webViewClient = this.f8662a;
            if (webViewClient != null) {
                return webViewClient.shouldOverrideUrlLoading(webView, webResourceRequest);
            }
            return super.shouldOverrideUrlLoading(webView, webResourceRequest);
        }
    }

    public SafeWebView(Context context) {
        super(context);
        a();
    }

    private boolean a(String str) {
        return URLUtil.isHttpUrl(str);
    }

    public String getDefaultErrorPage() {
        return this.f8661a;
    }

    public WebViewLoadCallBack getWebViewLoadCallBack() {
        return this.e;
    }

    @Deprecated
    public String[] getWhitelist() {
        String[] strArr = this.b;
        if (strArr == null) {
            return null;
        }
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public String[] getWhitelistNotMatchSubDomain() {
        String[] strArr = this.c;
        if (strArr == null) {
            return null;
        }
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    @Deprecated
    public String[] getWhitelistNotMathcSubDomain() {
        String[] strArr = this.c;
        if (strArr == null) {
            return null;
        }
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public String[] getWhitelistWithPath() {
        String[] strArr = this.d;
        if (strArr == null) {
            return null;
        }
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public boolean isWhiteListUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            LogsUtil.e(f, "url is null");
            return false;
        }
        if (!URLUtil.isNetworkUrl(str)) {
            return true;
        }
        String[] whitelistWithPath = getWhitelistWithPath();
        String[] whitelistNotMatchSubDomain = getWhitelistNotMatchSubDomain();
        String[] whitelist = getWhitelist();
        boolean z = whitelistWithPath == null || whitelistWithPath.length == 0;
        boolean z2 = whitelistNotMatchSubDomain == null || whitelistNotMatchSubDomain.length == 0;
        boolean z3 = whitelist == null || whitelist.length == 0;
        if (z && z2 && z3) {
            return true;
        }
        return (whitelistWithPath == null || whitelistWithPath.length == 0) ? (whitelistNotMatchSubDomain == null || whitelistNotMatchSubDomain.length == 0) ? UriUtil.isUrlHostInWhitelist(str, whitelist) : UriUtil.isUrlHostSameWhitelist(str, whitelistNotMatchSubDomain) : UriUtil.isUrlHostAndPathInWhitelist(str, whitelistWithPath);
    }

    @Override // android.webkit.WebView
    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        if (!a(str)) {
            super.loadDataWithBaseURL(str, str2, str3, str4, str5);
            return;
        }
        Log.e(f, "loadDataWithBaseURL: http url , not safe");
        if (!TextUtils.isEmpty(this.f8661a)) {
            super.loadDataWithBaseURL(this.f8661a, str2, str3, str4, str5);
        } else if (getWebViewLoadCallBack() != null) {
            Log.e(f, "WebViewLoadCallBack");
            getWebViewLoadCallBack().onCheckError(str, WebViewLoadCallBack.ErrorCode.HTTP_URL);
        }
    }

    @Override // android.webkit.WebView
    public void loadUrl(String str) {
        if (isWhiteListUrl(str) && !a(str)) {
            super.loadUrl(str);
            return;
        }
        Log.e(f, "loadUrl: url is not in white list or http url not safe");
        if (!TextUtils.isEmpty(this.f8661a)) {
            super.loadUrl(this.f8661a);
        } else if (getWebViewLoadCallBack() != null) {
            Log.e(f, "WebViewLoadCallBack");
            getWebViewLoadCallBack().onCheckError(str, WebViewLoadCallBack.ErrorCode.HTTP_URL);
        }
    }

    public final void onCheckError(WebView webView, String str) {
        LogsUtil.e(f, "onCheckError url is not in white list ", str);
        webView.stopLoading();
        String defaultErrorPage = getDefaultErrorPage();
        if (!TextUtils.isEmpty(defaultErrorPage)) {
            webView.loadUrl(defaultErrorPage);
        } else if (getWebViewLoadCallBack() != null) {
            Log.e(f, "onPageStarted WebViewLoadCallBack");
            getWebViewLoadCallBack().onCheckError(str, WebViewLoadCallBack.ErrorCode.URL_NOT_IN_WHITE_LIST);
        }
    }

    @Override // android.webkit.WebView
    public void postUrl(String str, byte[] bArr) {
        if (isWhiteListUrl(str) && !a(str)) {
            super.postUrl(str, bArr);
            return;
        }
        Log.e(f, "postUrl: url is not in white list or http url not safe");
        if (!TextUtils.isEmpty(this.f8661a)) {
            super.postUrl(this.f8661a, bArr);
        } else if (getWebViewLoadCallBack() != null) {
            Log.e(f, "WebViewLoadCallBack");
            getWebViewLoadCallBack().onCheckError(str, WebViewLoadCallBack.ErrorCode.HTTP_URL);
        }
    }

    public void setDefaultErrorPage(String str) {
        this.f8661a = str;
    }

    @Override // android.webkit.WebView
    public void setWebViewClient(WebViewClient webViewClient) {
        super.setWebViewClient(new b(webViewClient, true));
    }

    public void setWebViewLoadCallBack(WebViewLoadCallBack webViewLoadCallBack) {
        this.e = webViewLoadCallBack;
    }

    @Deprecated
    public void setWhitelist(String[] strArr) {
        this.b = strArr == null ? null : (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public void setWhitelistNotMatchSubDomain(String[] strArr) {
        this.c = strArr == null ? null : (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    @Deprecated
    public void setWhitelistNotMathcSubDomain(String[] strArr) {
        this.c = strArr == null ? null : (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public void setWhitelistWithPath(String[] strArr) {
        this.d = strArr == null ? null : (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    private void a() {
        SafeWebSettings.initWebviewAndSettings(this);
        setWebViewClient(null);
    }

    public void setWebViewClient(WebViewClient webViewClient, boolean z) {
        super.setWebViewClient(new b(webViewClient, z));
    }

    public SafeWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public SafeWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public SafeWebView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a();
    }

    @Override // android.webkit.WebView
    public void loadUrl(String str, Map<String, String> map) {
        if (isWhiteListUrl(str) && !a(str)) {
            super.loadUrl(str, map);
            return;
        }
        Log.e(f, "loadUrl: url is not in white list or http url not safe");
        if (!TextUtils.isEmpty(this.f8661a)) {
            super.loadUrl(this.f8661a, map);
        } else if (getWebViewLoadCallBack() != null) {
            Log.e(f, "WebViewLoadCallBack");
            getWebViewLoadCallBack().onCheckError(str, WebViewLoadCallBack.ErrorCode.HTTP_URL);
        }
    }
}
