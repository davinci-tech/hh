package com.huawei.openalliance.ad.views.web;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ca;
import com.huawei.openalliance.ad.views.interfaces.w;
import com.huawei.operation.utils.Constants;
import java.net.URISyntaxException;

/* loaded from: classes5.dex */
public class e extends d {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8198a = "e";
    private ProgressBar b;
    private w c;

    @Override // com.huawei.openalliance.ad.views.web.d, android.webkit.WebViewClient
    public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
        return true;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        ho.a(f8198a, "shouldOverrideUrlLoading url=%s", str);
        if (a(str)) {
            return true;
        }
        b(str);
        return true;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        ho.a(f8198a, "WebResourceRequest url=%s", webResourceRequest.getUrl().toString());
        String uri = webResourceRequest.getUrl().toString();
        if (a(uri)) {
            return true;
        }
        b(uri);
        return true;
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        super.onReceivedError(webView, webResourceRequest, webResourceError);
        ho.a(f8198a, "onReceivedError error:%s", webResourceError.getDescription());
        a(webView);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        ho.a(f8198a, "onReceivedError description:%s", str);
        a(webView);
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        ho.a(f8198a, "onPageStarted url=%s", str);
        ProgressBar progressBar = this.b;
        if (progressBar != null) {
            progressBar.setVisibility(0);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        ProgressBar progressBar = this.b;
        if (progressBar != null) {
            progressBar.setVisibility(4);
            this.b.setProgress(100);
        }
        b();
    }

    public void a(ProgressBar progressBar) {
        this.b = progressBar;
    }

    @Override // com.huawei.openalliance.ad.views.web.d
    protected void a(WebView webView) {
        ho.b(f8198a, "onReceivedError");
        webView.loadUrl(Constants.ABOUT_BLANK);
        WebViewInstrumentation.loadUrl(webView, Constants.ABOUT_BLANK);
        ProgressBar progressBar = this.b;
        if (progressBar != null && progressBar.getVisibility() == 0) {
            this.b.setVisibility(4);
        }
        w wVar = this.c;
        if (wVar != null) {
            wVar.a();
        }
    }

    private String c() {
        return bz.a(this.c.getContext()).d() ? "com.android.browser" : "com.android.chrome";
    }

    private void b(String str) {
        String str2;
        String str3;
        Context context = this.c.getContext();
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        ca caVar = new ca(context);
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL);
        intent.setData(Uri.parse(str));
        String a2 = caVar.a(intent);
        ho.a(f8198a, "preferred browser:%s", a2);
        try {
            if (TextUtils.isEmpty(a2)) {
                if (caVar.a(c())) {
                    a2 = c();
                }
                intent.setClipData(com.huawei.openalliance.ad.constant.Constants.CLIP_DATA);
                context.startActivity(intent);
                return;
            }
            intent.setClipData(com.huawei.openalliance.ad.constant.Constants.CLIP_DATA);
            context.startActivity(intent);
            return;
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(context, R.string._2130851413_res_0x7f023655, 1).show();
            str2 = f8198a;
            str3 = "openUrlByBrowser ActivityNotFoundException";
            ho.d(str2, str3);
            return;
        } catch (Exception unused2) {
            str2 = f8198a;
            str3 = "openUrlByBrowser Exception";
            ho.d(str2, str3);
            return;
        }
        intent.setPackage(a2);
    }

    private void b() {
        ho.b(f8198a, "onPageFinished");
        this.c.c();
    }

    private boolean a(String str) {
        Context context = this.c.getContext();
        if (context != null && !TextUtils.isEmpty(str)) {
            if (TextUtils.equals(str, "oobe://more")) {
                try {
                    Intent intent = new Intent();
                    intent.setClassName("com.huawei.systemmanager", "com.huawei.dataprivacycenter.MainActivity");
                    intent.setClipData(com.huawei.openalliance.ad.constant.Constants.CLIP_DATA);
                    context.startActivity(intent);
                } catch (Exception e) {
                    ho.c(f8198a, e.getClass().getSimpleName());
                }
                return true;
            }
            if (!str.startsWith("http")) {
                try {
                    Intent parseUri = Intent.parseUri(str, 1);
                    parseUri.addCategory("android.intent.category.BROWSABLE");
                    parseUri.setComponent(null);
                    parseUri.setSelector(null);
                    if (context.getPackageManager().queryIntentActivities(parseUri, 0).size() > 0) {
                        parseUri.setClipData(com.huawei.openalliance.ad.constant.Constants.CLIP_DATA);
                        context.startActivity(parseUri);
                    }
                } catch (URISyntaxException e2) {
                    ho.c(f8198a, e2.getClass().getSimpleName());
                }
                return true;
            }
        }
        return false;
    }

    public e(w wVar) {
        this.c = wVar;
    }
}
