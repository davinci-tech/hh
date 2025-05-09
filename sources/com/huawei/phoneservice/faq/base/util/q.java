package com.huawei.phoneservice.faq.base.util;

import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;

/* loaded from: classes9.dex */
public class q {
    public static void cdw_(WebView webView) {
        webView.removeJavascriptInterface("accessibility");
        webView.removeJavascriptInterface("accessibilityTraversal");
        webView.removeJavascriptInterface("searchBoxJavaBridge_");
    }

    public static void cdv_(WebView webView) {
        if (webView != null) {
            i.d("initWebView", "");
            webView.getSettings().setAllowFileAccess(false);
            webView.getSettings().setAllowContentAccess(false);
            webView.getSettings().setGeolocationEnabled(false);
            webView.getSettings().setSavePassword(false);
        }
    }

    public static void cdu_(WebView webView) {
        if (webView != null) {
            ViewParent parent = webView.getParent();
            if (parent != null && (parent instanceof ViewGroup)) {
                ((ViewGroup) parent).removeView(webView);
            }
            webView.setDownloadListener(null);
            if (webView instanceof WebView) {
                APMSH5LoadInstrument.setWebViewClient(webView, null);
            } else {
                webView.setWebViewClient(null);
            }
            webView.setWebChromeClient(null);
            webView.stopLoading();
            webView.clearHistory();
            webView.clearCache(true);
            webView.removeAllViews();
            webView.destroy();
        }
    }
}
