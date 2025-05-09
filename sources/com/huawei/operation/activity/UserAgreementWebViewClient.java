package com.huawei.operation.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.secure.android.common.ssl.WebViewSSLCheck;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.nrt;

/* loaded from: classes9.dex */
public class UserAgreementWebViewClient extends WebViewClient {
    private Context mContext;
    private HealthProgressBar mHealthProgressBar;
    private LinearLayout mLoadingLayout;
    private String mTag;

    public UserAgreementWebViewClient(Context context, HealthProgressBar healthProgressBar, LinearLayout linearLayout, String str) {
        this.mContext = context;
        this.mHealthProgressBar = healthProgressBar;
        this.mLoadingLayout = linearLayout;
        this.mTag = str;
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.mHealthProgressBar.setVisibility(4);
        this.mLoadingLayout.setVisibility(8);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        LogUtil.h(this.mTag, "onReceivedSslError");
        if (sslError != null) {
            WebViewSSLCheck.checkServerCertificateNew(sslErrorHandler, sslError, this.mContext);
        }
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        nrt.cKg_(this.mContext, webView);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(this.mTag, "load url is null");
            return false;
        }
        return openApkToHandleUrl(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        nrt.cKg_(this.mContext, webView);
        if (webResourceRequest == null || webResourceRequest.getUrl() == null) {
            LogUtil.h(this.mTag, "load url request is null");
            return false;
        }
        return openApkToHandleUrl(webView, webResourceRequest.getUrl().toString());
    }

    private boolean openApkToHandleUrl(WebView webView, String str) {
        try {
            if (!str.startsWith("http://") && !str.startsWith("https://")) {
                this.mContext.startActivity(new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str)));
                return true;
            }
            if (webView != null) {
                webView.loadUrl(str);
                WebViewInstrumentation.loadUrl(webView, str);
            }
            return true;
        } catch (ActivityNotFoundException unused) {
            LogUtil.b(this.mTag, "ActivityNotFoundException：not install apk to open this url");
            return true;
        }
    }
}
