package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.openalliance.ad.dh;
import com.huawei.openalliance.ad.dk;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.oj;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.dl;
import com.huawei.openalliance.ad.utils.dq;
import com.huawei.operation.utils.Constants;
import com.huawei.uikit.phone.hwprogressbar.widget.HwProgressBar;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* loaded from: classes9.dex */
class w extends com.huawei.openalliance.ad.views.web.d {

    /* renamed from: a, reason: collision with root package name */
    private boolean f8174a = false;
    private boolean b = false;
    private View c;
    private oj d;
    private WebViewClient e;
    private com.huawei.openalliance.ad.views.interfaces.w f;
    private boolean g;
    private long h;
    private final dk i;

    @Override // com.huawei.openalliance.ad.views.web.d, android.webkit.WebViewClient
    public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
        return true;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (this.f != null) {
            if (a(str, webView.getContext())) {
                return true;
            }
            this.f.b(str);
        }
        try {
            oj ojVar = this.d;
            if (ojVar != null) {
                if (ojVar.a(webView, Uri.parse(str))) {
                    return true;
                }
            }
        } catch (Exception unused) {
            ho.c("PPSWebViewClient", "shouldOverrideUrlLoading Exception");
        }
        WebViewClient webViewClient = this.e;
        return webViewClient != null ? webViewClient.shouldOverrideUrlLoading(webView, str) : super.shouldOverrideUrlLoading(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        oj ojVar;
        if (this.f != null && webResourceRequest != null && webResourceRequest.getUrl() != null) {
            if (a(webResourceRequest.getUrl().toString(), webView.getContext())) {
                return true;
            }
            this.f.b(webResourceRequest.getUrl().toString());
        }
        if (webResourceRequest != null && (ojVar = this.d) != null && ojVar.a(webView, webResourceRequest.getUrl())) {
            return true;
        }
        WebViewClient webViewClient = this.e;
        return webViewClient != null ? webViewClient.shouldOverrideUrlLoading(webView, webResourceRequest) : super.shouldOverrideUrlLoading(webView, webResourceRequest);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        WebViewClient webViewClient = this.e;
        return webViewClient != null ? webViewClient.shouldOverrideKeyEvent(webView, keyEvent) : super.shouldOverrideKeyEvent(webView, keyEvent);
    }

    @Override // android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        ho.a("PPSWebViewClient", "url is : %s, diskCache url is : %s", str, dk.d(str));
        try {
        } catch (Exception unused) {
            ho.c("PPSWebViewClient", "getLocalCacheFile error: %s", dl.a(str));
        }
        if (dq.a(str) && this.i.f(dk.d(str))) {
            return b(str);
        }
        ho.a("PPSWebViewClient", "url not cache: %s", str);
        WebViewClient webViewClient = this.e;
        return webViewClient != null ? webViewClient.shouldInterceptRequest(webView, str) : super.shouldInterceptRequest(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        String uri = webResourceRequest.getUrl().toString();
        ho.a("PPSWebViewClient", "url is : %s, diskCache url is : %s", uri, dk.d(uri));
        try {
        } catch (IOException unused) {
            ho.c("PPSWebViewClient", "getLocalCacheFile io exception: %s", dl.a(uri));
        } catch (Exception unused2) {
            ho.c("PPSWebViewClient", "getLocalCacheFile exception: %s", dl.a(uri));
        }
        if (dq.a(uri) && this.i.f(dk.d(uri))) {
            ho.a("PPSWebViewClient", "url cache: %s", uri);
            return b(uri);
        }
        ho.a("PPSWebViewClient", "url not cache: %s", uri);
        WebViewClient webViewClient = this.e;
        return webViewClient != null ? webViewClient.shouldInterceptRequest(webView, webResourceRequest) : super.shouldInterceptRequest(webView, webResourceRequest);
    }

    @Override // android.webkit.WebViewClient
    public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
        WebViewClient webViewClient = this.e;
        if (webViewClient != null) {
            webViewClient.onUnhandledKeyEvent(webView, keyEvent);
        } else {
            super.onUnhandledKeyEvent(webView, keyEvent);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onTooManyRedirects(WebView webView, Message message, Message message2) {
        WebViewClient webViewClient = this.e;
        if (webViewClient != null) {
            webViewClient.onTooManyRedirects(webView, message, message2);
        } else {
            super.onTooManyRedirects(webView, message, message2);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onScaleChanged(WebView webView, float f, float f2) {
        WebViewClient webViewClient = this.e;
        if (webViewClient != null) {
            webViewClient.onScaleChanged(webView, f, f2);
        } else {
            super.onScaleChanged(webView, f, f2);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
        WebViewClient webViewClient = this.e;
        if (webViewClient != null) {
            webViewClient.onReceivedLoginRequest(webView, str, str2, str3);
        } else {
            super.onReceivedLoginRequest(webView, str, str2, str3);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedHttpError(WebView webView, final WebResourceRequest webResourceRequest, final WebResourceResponse webResourceResponse) {
        final boolean isForMainFrame = webResourceRequest.isForMainFrame();
        ho.c("PPSWebViewClient", "onReceivedHttpError, isForMainFrame:" + isForMainFrame + ", ReasonPhrase:" + webResourceResponse.getReasonPhrase());
        if (isForMainFrame) {
            b(webView);
        }
        WebViewClient webViewClient = this.e;
        if (webViewClient != null) {
            webViewClient.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
        } else if (isForMainFrame) {
            a(webView, isForMainFrame);
        } else {
            super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
        }
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.w.3
            @Override // java.lang.Runnable
            public void run() {
                if (w.this.f != null) {
                    w.this.f.a(String.valueOf(webResourceRequest.getUrl()), "onReceivedHttpError", "mainframe:" + String.valueOf(isForMainFrame) + ", statusCode:" + webResourceResponse.getStatusCode() + ", reasonPhrase:" + webResourceResponse.getReasonPhrase());
                }
            }
        });
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
        WebViewClient webViewClient = this.e;
        if (webViewClient != null) {
            webViewClient.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
        } else {
            super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, final WebResourceRequest webResourceRequest, final WebResourceError webResourceError) {
        final boolean isForMainFrame = webResourceRequest.isForMainFrame();
        ho.c("PPSWebViewClient", "onReceivedError, isForMainFrame:" + isForMainFrame + ", description:" + ((Object) webResourceError.getDescription()));
        if (isForMainFrame) {
            b(webView);
        }
        WebViewClient webViewClient = this.e;
        if (webViewClient != null) {
            webViewClient.onReceivedError(webView, webResourceRequest, webResourceError);
        } else if (isForMainFrame) {
            a(webView, isForMainFrame);
        } else {
            super.onReceivedError(webView, webResourceRequest, webResourceError);
        }
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.w.2
            @Override // java.lang.Runnable
            public void run() {
                if (w.this.f != null) {
                    w.this.f.a(String.valueOf(webResourceRequest.getUrl()), "onReceivedError", "mainframe:" + String.valueOf(isForMainFrame) + ", errorCode:" + webResourceError.getErrorCode() + ", desc:" + String.valueOf(webResourceError.getDescription()));
                }
            }
        });
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, final int i, final String str, final String str2) {
        ho.c("PPSWebViewClient", "onReceivedError, errorCode: %d description: %s", Integer.valueOf(i), str);
        b(webView);
        WebViewClient webViewClient = this.e;
        if (webViewClient != null) {
            webViewClient.onReceivedError(webView, i, str, str2);
        } else {
            a(webView, true);
        }
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.w.1
            @Override // java.lang.Runnable
            public void run() {
                if (w.this.f != null) {
                    w.this.f.a(str2, "onReceivedError", "mainframe:true, errorCode:" + i + ", desc:" + str);
                }
            }
        });
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedClientCertRequest(WebView webView, ClientCertRequest clientCertRequest) {
        WebViewClient webViewClient = this.e;
        if (webViewClient != null) {
            webViewClient.onReceivedClientCertRequest(webView, clientCertRequest);
        } else {
            super.onReceivedClientCertRequest(webView, clientCertRequest);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        long currentTimeMillis = System.currentTimeMillis();
        this.h = currentTimeMillis;
        ho.a("PPSWebViewClient", "onPageFinished, load start time is: %d", Long.valueOf(currentTimeMillis));
        if (TextUtils.isEmpty(str)) {
            return;
        }
        com.huawei.openalliance.ad.views.interfaces.w wVar = this.f;
        if (wVar != null) {
            wVar.b(str);
        }
        if (Uri.parse(str) != null) {
            String k = cz.k(str);
            if (!TextUtils.isEmpty(k) && fh.b(webView.getContext()).k(k)) {
                if (this.d != null) {
                    ho.c("PPSWebViewClient", "url is blocked");
                    this.d.c();
                }
                com.huawei.openalliance.ad.views.interfaces.w wVar2 = this.f;
                if (wVar2 != null) {
                    wVar2.b();
                    return;
                }
                return;
            }
        }
        WebViewClient webViewClient = this.e;
        if (webViewClient != null) {
            webViewClient.onPageStarted(webView, str, bitmap);
            return;
        }
        View view = this.c;
        if (view != null) {
            view.setVisibility(0);
            if (this.g) {
                ((HwProgressBar) this.c).setProgress(0);
            } else {
                ((e) this.c).a();
            }
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        if (!webView.getSettings().getLoadsImagesAutomatically()) {
            webView.getSettings().setLoadsImagesAutomatically(true);
        }
        View view = this.c;
        if (view != null) {
            view.setVisibility(8);
            if (this.g) {
                ((HwProgressBar) this.c).setProgress(100, true);
            } else {
                ((e) this.c).setProgress(100);
            }
        }
        if (this.d != null && a(str)) {
            if (ho.a()) {
                long currentTimeMillis = System.currentTimeMillis();
                ho.a("PPSWebViewClient", "onPageFinished, load finish time is: %d", Long.valueOf(currentTimeMillis));
                ho.a("PPSWebViewClient", "onPageFinished, load time is: %d", Long.valueOf(currentTimeMillis - this.h));
            }
            this.d.b();
        }
        WebViewClient webViewClient = this.e;
        if (webViewClient != null) {
            webViewClient.onPageFinished(webView, str);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageCommitVisible(WebView webView, String str) {
        WebViewClient webViewClient = this.e;
        if (webViewClient != null) {
            webViewClient.onPageCommitVisible(webView, str);
        } else {
            super.onPageCommitVisible(webView, str);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onLoadResource(WebView webView, String str) {
        WebViewClient webViewClient = this.e;
        if (webViewClient != null) {
            webViewClient.onLoadResource(webView, str);
        } else {
            super.onLoadResource(webView, str);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onFormResubmission(WebView webView, Message message, Message message2) {
        WebViewClient webViewClient = this.e;
        if (webViewClient != null) {
            webViewClient.onFormResubmission(webView, message, message2);
        } else {
            super.onFormResubmission(webView, message, message2);
        }
    }

    @Override // android.webkit.WebViewClient
    public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
        WebViewClient webViewClient = this.e;
        if (webViewClient != null) {
            webViewClient.doUpdateVisitedHistory(webView, str, z);
        } else {
            super.doUpdateVisitedHistory(webView, str, z);
        }
    }

    public void a(oj ojVar) {
        this.d = ojVar;
    }

    public void a(WebViewClient webViewClient) {
        this.e = webViewClient;
    }

    @Override // com.huawei.openalliance.ad.views.web.d
    public void a(WebView webView, SslErrorHandler sslErrorHandler, final SslError sslError) {
        final boolean z;
        if (this.f != null) {
            z = TextUtils.equals(cz.c(sslError.getUrl(), "/"), cz.c(this.f.getCurrentPageUrl(), "/"));
            if (z) {
                b(webView);
            }
        } else {
            z = false;
        }
        WebViewClient webViewClient = this.e;
        if (webViewClient != null) {
            webViewClient.onReceivedSslError(webView, sslErrorHandler, sslError);
        } else if (z) {
            a(webView, true);
        }
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.w.4
            @Override // java.lang.Runnable
            public void run() {
                if (w.this.f != null) {
                    w.this.f.a(sslError.getUrl(), "onReceivedSslError", "mainframe:" + z + ", SSL error: " + String.valueOf(sslError));
                }
            }
        });
    }

    public void a(View view, boolean z) {
        this.c = view;
        this.g = z;
        if (dd.c()) {
            ho.b("PPSWebViewClient", "rtl language, set rtl direction.");
            if (z) {
                view.setRotation(180.0f);
            } else {
                view.setLayoutDirection(1);
            }
        }
    }

    private void b(WebView webView) {
        ho.b("PPSWebViewClient", "processError");
        this.b = true;
        View view = this.c;
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        this.c.setVisibility(8);
        if (this.g) {
            ((HwProgressBar) this.c).setProgress(0);
        } else {
            ((e) this.c).a();
        }
    }

    private WebResourceResponse b(String str) {
        return new WebResourceResponse(dq.b(str), "UTF-8", new FileInputStream(new File(this.i.c(dk.d(str)))));
    }

    private boolean a(String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!fh.b(context).k(cz.k(str))) {
            return false;
        }
        ho.b("PPSWebViewClient", "url is blocked");
        oj ojVar = this.d;
        if (ojVar == null) {
            return true;
        }
        ojVar.c();
        return true;
    }

    private boolean a(String str) {
        if (this.d == null || str == null || TextUtils.equals(Constants.ABOUT_BLANK, str)) {
            return false;
        }
        if (this.b) {
            this.b = false;
            return false;
        }
        if (this.f8174a) {
            return false;
        }
        this.f8174a = true;
        return true;
    }

    private void a(WebView webView, boolean z) {
        if (z) {
            webView.loadUrl(Constants.ABOUT_BLANK);
            WebViewInstrumentation.loadUrl(webView, Constants.ABOUT_BLANK);
            com.huawei.openalliance.ad.views.interfaces.w wVar = this.f;
            if (wVar != null) {
                wVar.a();
            }
        }
    }

    public w(com.huawei.openalliance.ad.views.interfaces.w wVar) {
        this.f = wVar;
        this.i = dh.a(wVar.getContext(), com.huawei.openalliance.ad.constant.Constants.WEBVIEW_CACHE);
    }
}
