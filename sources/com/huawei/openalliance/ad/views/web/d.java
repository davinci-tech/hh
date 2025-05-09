package com.huawei.openalliance.ad.views.web;

import android.content.Context;
import android.net.http.SslError;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.views.web.g;

/* loaded from: classes5.dex */
public class d extends WebViewClient {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8193a = "d";

    protected void a(WebView webView) {
    }

    @Override // android.webkit.WebViewClient
    public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
        return true;
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(final WebView webView, final SslErrorHandler sslErrorHandler, final SslError sslError) {
        String str = f8193a;
        ho.c(str, "onReceivedSslError %s", sslError);
        if (webView == null) {
            return;
        }
        try {
            ho.b(str, "WebView ssl check");
            f.a(sslErrorHandler, sslError, sslError.getUrl(), webView.getContext().getApplicationContext(), new g.a() { // from class: com.huawei.openalliance.ad.views.web.d.1
                @Override // com.huawei.openalliance.ad.views.web.g.a
                public void b(Context context, String str2) {
                    ho.a(d.f8193a, "onCancel:%s", str2);
                    dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.web.d.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            d.this.a(webView, sslErrorHandler, sslError);
                        }
                    });
                }

                @Override // com.huawei.openalliance.ad.views.web.g.a
                public void a(Context context, String str2) {
                    ho.a(d.f8193a, "onProceed:%s", str2);
                    dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.web.d.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (sslErrorHandler != null) {
                                sslErrorHandler.proceed();
                            }
                        }
                    });
                }
            });
        } catch (Exception | NoClassDefFoundError e) {
            ho.c(f8193a, e.getClass().getSimpleName());
            if (sslErrorHandler != null) {
                sslErrorHandler.cancel();
            }
        }
    }

    protected void a(final WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        ho.a(f8193a, "handleSslError");
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.web.d.2
            @Override // java.lang.Runnable
            public void run() {
                d.this.a(webView);
            }
        });
    }
}
