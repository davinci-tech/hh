package com.huawei.hwcloudjs.core;

import android.webkit.WebView;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hwcloudjs.support.enables.INoProguard;
import java.lang.ref.WeakReference;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JsCallback implements INoProguard {

    /* renamed from: a, reason: collision with root package name */
    private static final int f6193a = 0;
    private static final int b = 9999;
    private static final String c = "JsCallback";
    private static final String d = "javascript:%s.callback(%s,%s,%d);";
    private final String e;
    private WeakReference<WebView> f;
    private String g;
    private String h;

    private String a(String str) {
        return str;
    }

    public final void success(String str) {
        a(a(str), 0);
    }

    public final void success(Object obj) {
        a(new b().a(obj), 0);
    }

    public final void success() {
        success("{}");
    }

    public String getwebviewId() {
        return this.g;
    }

    public WebView getWebView() {
        return this.f.get();
    }

    public final void failure(String str) {
        failure(9999, str);
    }

    public final void failure(int i, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", i);
            jSONObject.put("message", str);
        } catch (JSONException unused) {
            com.huawei.hwcloudjs.f.d.b(c, "failure put json error", true);
        }
        a(a(jSONObject.toString()), i);
    }

    public final void failure(int i) {
        failure(i, "");
    }

    public final void failure() {
        failure(9999);
    }

    private void a(String str, int i) {
        WebView webView = getWebView();
        if (webView == null) {
            return;
        }
        a(webView, String.format(Locale.ENGLISH, d, "hbs", this.e, str, Integer.valueOf(i)));
    }

    static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private WebView f6194a;
        private String b;

        @Override // java.lang.Runnable
        public void run() {
            this.f6194a.getSettings().setSavePassword(false);
            WebView webView = this.f6194a;
            String str = this.b;
            webView.loadUrl(str);
            WebViewInstrumentation.loadUrl(webView, str);
        }

        public a(WebView webView, String str) {
            this.f6194a = webView;
            this.b = str;
        }
    }

    private void a(WebView webView, String str) {
        webView.post(new a(webView, str));
    }

    public JsCallback(WebView webView, String str, String str2) {
        this.g = str2;
        this.f = new WeakReference<>(webView);
        this.e = "\"" + str + "\"";
        this.h = str;
    }
}
