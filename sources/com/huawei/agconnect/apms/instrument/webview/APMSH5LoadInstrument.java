package com.huawei.agconnect.apms.instrument.webview;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.huawei.agconnect.apms.Agent;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import com.huawei.secure.android.common.webview.SafeWebView;

/* loaded from: classes2.dex */
public class APMSH5LoadInstrument {
    private static final AgentLog LOG = AgentLogManager.getAgentLog();
    public static final int WEB_VIEW_TAG = 202106250;

    public static void setSafeWebViewClient(SafeWebView safeWebView, WebViewClient webViewClient, boolean z) {
        try {
        } catch (Exception e) {
            LOG.warn("failed to set webview client: " + e.getMessage());
        }
        if (Agent.isDisabled()) {
            safeWebView.setWebViewClient(webViewClient, z);
            return;
        }
        if (Agent.isWebViewMonitorDisabled()) {
            safeWebView.setWebViewClient(webViewClient, z);
            return;
        }
        LOG.debug("add js bridge to h5.");
        safeWebView.getSettings().setJavaScriptEnabled(true);
        safeWebView.addJavascriptInterface(new APMSJavaScriptBridge(), "apmsJsBridge");
        safeWebView.setTag(WEB_VIEW_TAG, Integer.valueOf(WEB_VIEW_TAG));
        safeWebView.setWebViewClient(webViewClient, z);
    }

    public static void setWebViewClient(WebView webView, WebViewClient webViewClient) {
        try {
        } catch (Exception e) {
            LOG.warn("failed to set webview client: " + e.getMessage());
        }
        if (Agent.isDisabled()) {
            webView.setWebViewClient(webViewClient);
            return;
        }
        if (Agent.isWebViewMonitorDisabled()) {
            webView.setWebViewClient(webViewClient);
            return;
        }
        LOG.debug("add js bridge to h5.");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new APMSJavaScriptBridge(), "apmsJsBridge");
        webView.setTag(WEB_VIEW_TAG, Integer.valueOf(WEB_VIEW_TAG));
        webView.setWebViewClient(webViewClient);
    }
}
