package com.huawei.openalliance.ad.views.web;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.dh;
import com.huawei.openalliance.ad.dk;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.jf;
import com.huawei.openalliance.ad.nv;
import com.huawei.openalliance.ad.rr;
import com.huawei.openalliance.ad.rt;
import com.huawei.openalliance.ad.ru;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dq;
import com.huawei.openalliance.ad.utils.m;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class b implements jf.a {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f8189a = new byte[0];
    private static b g;
    private WebView b;
    private jf c;
    private Map<String, Integer> d = new HashMap(5);
    private String e;
    private final int f;

    public void a(String str) {
        if (cz.b(str)) {
            return;
        }
        ho.b("PreloadWebView", "preLoad begin");
        this.e = str;
        WebView webView = this.b;
        webView.loadUrl(str);
        WebViewInstrumentation.loadUrl(webView, str);
        this.c.a();
        this.c.b();
    }

    @Override // com.huawei.openalliance.ad.jf.a
    public void a() {
        WebView webView = this.b;
        if (webView != null) {
            webView.destroy();
        }
        this.b = null;
        this.c = null;
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final Context context, final String str) {
        final dk a2 = dh.a(context, Constants.WEBVIEW_CACHE);
        a2.a(100);
        a2.a(Math.max(a2.c(), Constants.WEB_VIEW_CACHE_TOTAL_MAX_SIZE));
        m.e(new Runnable() { // from class: com.huawei.openalliance.ad.views.web.b.1
            @Override // java.lang.Runnable
            public void run() {
                rt rtVar = new rt();
                rtVar.b(false);
                rtVar.c(true);
                rtVar.d(Constants.WEBVIEW_CACHE);
                rtVar.a(2000);
                rtVar.b(2000);
                rtVar.c(str);
                ru a3 = new rr(context, rtVar).a();
                if (a3 != null) {
                    String a4 = a3.a();
                    if (TextUtils.isEmpty(a4)) {
                        return;
                    }
                    ho.a("PreloadWebView", "download url is : %s , filePath is : %s", str, a2.c(a4));
                }
            }
        });
    }

    private static void b() {
        synchronized (f8189a) {
            g = null;
        }
    }

    static class a {
        @JavascriptInterface
        public boolean isPreload() {
            ho.a("PreloadWebView", "isPreload:true");
            return true;
        }

        private a() {
        }
    }

    /* renamed from: com.huawei.openalliance.ad.views.web.b$b, reason: collision with other inner class name */
    class C0220b extends WebViewClient {
        @Override // android.webkit.WebViewClient
        public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onLoadResource(WebView webView, String str) {
            ho.a("PreloadWebView", " onLoadResource url is :" + str);
            int intValue = b.this.d.get(b.this.e) != null ? ((Integer) b.this.d.get(b.this.e)).intValue() : 0;
            if (!dq.a(str) || intValue >= b.this.f) {
                ho.a("PreloadWebView", "don't download url :" + str);
            } else {
                b.this.d.put(b.this.e, Integer.valueOf(intValue + 1));
                b.b(webView.getContext(), str);
            }
            super.onLoadResource(webView, str);
        }

        C0220b() {
        }
    }

    public static b a(Context context) {
        b bVar;
        synchronized (f8189a) {
            if (g == null) {
                g = new b(context);
            }
            bVar = g;
        }
        return bVar;
    }

    private b(Context context) {
        dd.h(context);
        this.c = new jf(this);
        WebView webView = new WebView(context);
        this.b = webView;
        C0220b c0220b = new C0220b();
        if (webView instanceof WebView) {
            APMSH5LoadInstrument.setWebViewClient(webView, c0220b);
        } else {
            webView.setWebViewClient(c0220b);
        }
        this.b.addJavascriptInterface(new a(), Constants.PPS_JS_NAME);
        nv.b(this.b);
        this.b.getSettings().setJavaScriptEnabled(false);
        this.f = fh.b(context).r();
    }
}
