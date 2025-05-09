package com.huawei.agconnect.apms.instrument.webview;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.huawei.agconnect.apms.Agent;
import com.huawei.agconnect.apms.collect.model.event.webview.WebViewErrorEvent;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import com.huawei.agconnect.apms.rst;

/* loaded from: classes8.dex */
public class APMSWebViewClient extends WebViewClient {
    private static final AgentLog LOG = AgentLogManager.getAgentLog();

    public static class ReportErrorEventRunnable implements Runnable {
        public int errorCode;
        public String errorDesc;
        public boolean forMainFrame;
        public String method;
        public String mimeType;
        public int primaryError;
        public int statusCode;
        public String url;

        public ReportErrorEventRunnable(String str, String str2, String str3, boolean z, int i, int i2, int i3, String str4) {
            this.url = str;
            this.method = str2;
            this.mimeType = str3;
            this.forMainFrame = z;
            this.statusCode = i;
            this.errorCode = i2;
            this.primaryError = i3;
            this.errorDesc = str4;
        }

        @Override // java.lang.Runnable
        public void run() {
            rst.cde.add(new WebViewErrorEvent(System.currentTimeMillis(), this.url, this.method, this.mimeType, this.forMainFrame, this.statusCode, this.errorCode, this.primaryError, this.errorDesc));
        }
    }

    private void reportWebViewErrorEvent(String str, String str2, String str3, boolean z, int i, int i2, int i3, String str4) {
        Agent.getExecutor().execute(new ReportErrorEventRunnable(str, str2, str3, z, i, i2, i3, str4));
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        LOG.debug("webView onPageFinished: " + str);
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        LOG.debug("webView pageStarted: " + str);
        if (Agent.isDisabled() || Agent.isWebViewMonitorDisabled() || webView.getTag(APMSH5LoadInstrument.WEB_VIEW_TAG) != null) {
            return;
        }
        webView.addJavascriptInterface(new APMSJavaScriptBridge(), "apmsJsBridge");
        webView.setTag(APMSH5LoadInstrument.WEB_VIEW_TAG, Integer.valueOf(APMSH5LoadInstrument.WEB_VIEW_TAG));
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
        super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
        if (Agent.isDisabled() || Agent.isWebViewMonitorDisabled()) {
            return;
        }
        String uri = webResourceRequest.getUrl().toString();
        String method = webResourceRequest.getMethod();
        boolean isForMainFrame = webResourceRequest.isForMainFrame();
        String mimeType = webResourceResponse.getMimeType();
        int statusCode = webResourceResponse.getStatusCode();
        LOG.debug("onReceivedError: method: " + method + ", forMainFrame: " + isForMainFrame + ", statusCode: " + statusCode);
        reportWebViewErrorEvent(uri, method, mimeType, isForMainFrame, statusCode, -1, -1, "");
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
        if (Agent.isDisabled() || Agent.isWebViewMonitorDisabled()) {
            return;
        }
        String url = sslError.getUrl();
        int primaryError = sslError.getPrimaryError();
        LOG.debug("onReceivedError: primaryError: " + primaryError);
        reportWebViewErrorEvent(url, "", "", false, -1, -1, primaryError, "");
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        super.onReceivedError(webView, webResourceRequest, webResourceError);
        if (Agent.isDisabled() || Agent.isWebViewMonitorDisabled()) {
            return;
        }
        String uri = webResourceRequest.getUrl().toString();
        String method = webResourceRequest.getMethod();
        boolean isForMainFrame = webResourceRequest.isForMainFrame();
        int errorCode = webResourceError.getErrorCode();
        String obj = webResourceError.getDescription().toString();
        LOG.debug("onReceivedError: method: " + method + ", forMainFrame: " + isForMainFrame + ", errorCode: " + errorCode + ", description: " + obj);
        reportWebViewErrorEvent(uri, method, "", isForMainFrame, -1, errorCode, -1, obj);
    }
}
