package com.huawei.openalliance.ad.views.web;

import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.oj;
import com.huawei.openalliance.ad.views.interfaces.q;
import com.huawei.operation.utils.Constants;

/* loaded from: classes5.dex */
public class c extends d {

    /* renamed from: a, reason: collision with root package name */
    protected q f8192a;
    private ProgressBar b;
    private WebViewClient c;
    private oj d;

    @Override // com.huawei.openalliance.ad.views.web.d, android.webkit.WebViewClient
    public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
        return true;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        q qVar = this.f8192a;
        if (qVar != null) {
            qVar.b(str);
        }
        try {
            oj ojVar = this.d;
            if (ojVar != null) {
                if (ojVar.a(webView, Uri.parse(str))) {
                    return true;
                }
            }
        } catch (Exception unused) {
            ho.c("PureWebViewClient", "shouldOverrideUrlLoading Exception");
        }
        WebViewClient webViewClient = this.c;
        return webViewClient != null ? webViewClient.shouldOverrideUrlLoading(webView, str) : super.shouldOverrideUrlLoading(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        oj ojVar;
        if (this.f8192a != null && webResourceRequest != null && webResourceRequest.getUrl() != null) {
            this.f8192a.b(webResourceRequest.getUrl().toString());
        }
        if (webResourceRequest != null && (ojVar = this.d) != null && ojVar.a(webView, webResourceRequest.getUrl())) {
            return true;
        }
        WebViewClient webViewClient = this.c;
        return webViewClient != null ? webViewClient.shouldOverrideUrlLoading(webView, webResourceRequest) : super.shouldOverrideUrlLoading(webView, webResourceRequest);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        super.onReceivedError(webView, webResourceRequest, webResourceError);
        ho.a("PureWebViewClient", "onReceivedError error:%s", webResourceError.getDescription());
        a(webView);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        ho.a("PureWebViewClient", "onReceivedError description:%s", str);
        a(webView);
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        ho.a("PureWebViewClient", "onPageStarted url=%s", str);
        ProgressBar progressBar = this.b;
        if (progressBar != null) {
            progressBar.setVisibility(0);
        }
        WebViewClient webViewClient = this.c;
        if (webViewClient != null) {
            webViewClient.onPageStarted(webView, str, bitmap);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        if (!webView.getSettings().getLoadsImagesAutomatically()) {
            webView.getSettings().setLoadsImagesAutomatically(true);
        }
        ProgressBar progressBar = this.b;
        if (progressBar != null) {
            progressBar.setVisibility(4);
            this.b.setProgress(100);
        }
        b();
        WebViewClient webViewClient = this.c;
        if (webViewClient != null) {
            webViewClient.onPageFinished(webView, str);
        }
    }

    public void a(oj ojVar) {
        this.d = ojVar;
    }

    public void a(WebViewClient webViewClient) {
        this.c = webViewClient;
    }

    @Override // com.huawei.openalliance.ad.views.web.d
    protected void a(WebView webView) {
        ho.b("PureWebViewClient", "onReceivedError");
        webView.loadUrl(Constants.ABOUT_BLANK);
        WebViewInstrumentation.loadUrl(webView, Constants.ABOUT_BLANK);
        ProgressBar progressBar = this.b;
        if (progressBar != null && progressBar.getVisibility() == 0) {
            this.b.setVisibility(4);
        }
        q qVar = this.f8192a;
        if (qVar != null) {
            qVar.a();
        }
    }

    private void b() {
        ho.b("PureWebViewClient", "onPageFinished");
        this.f8192a.c();
    }

    public c(q qVar) {
        this.f8192a = qVar;
    }
}
