package com.huawei.hianalytics.visual.autocollect;

import android.text.TextUtils;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.visual.a;
import com.huawei.hianalytics.visual.b;
import com.huawei.operation.utils.Constants;
import java.io.File;
import java.util.LinkedHashMap;

/* loaded from: classes4.dex */
public class HAWebChromeClient {

    /* renamed from: a, reason: collision with root package name */
    public static final LinkedHashMap<String, Integer> f3905a = new LinkedHashMap<>();

    public static void a(final WebView webView) {
        HiLog.d("HAWebChromeClient", "begin to inject js script");
        webView.evaluateJavascript("window._hasdk_h5", new ValueCallback() { // from class: com.huawei.hianalytics.visual.autocollect.HAWebChromeClient$$ExternalSyntheticLambda0
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                HAWebChromeClient.a(webView, (String) obj);
            }
        });
    }

    public static int getMonitorTimes(String str) {
        Integer num = f3905a.get(str);
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public static void a(WebView webView, String str) {
        if (!TextUtils.equals(str, Constants.NULL)) {
            HiLog.d("HAWebChromeClient", "this webview has injected js script");
            return;
        }
        webView.evaluateJavascript(a.a(b.a().g().getFilesDir() + File.separator + "haJsSdk", "haJsCode.js"), new ValueCallback() { // from class: com.huawei.hianalytics.visual.autocollect.HAWebChromeClient$$ExternalSyntheticLambda1
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                HAWebChromeClient.a((String) obj);
            }
        });
    }

    public static void initH5Monitor(WebView webView, int i) {
        if (webView == null) {
            return;
        }
        HiLog.d("HAWebChromeClient", "chrome client process: " + i);
        if (i < 10) {
            f3905a.put(webView.getUrl(), 0);
            return;
        }
        try {
            if (!b.a().b() && b.a().c()) {
                if (!webView.getSettings().getJavaScriptEnabled()) {
                    webView.getSettings().setJavaScriptEnabled(true);
                }
                if (i >= 100 && getMonitorTimes(webView.getUrl()) < 1) {
                    a(webView);
                    f3905a.put(webView.getUrl(), Integer.valueOf(getMonitorTimes(webView.getUrl()) + 1));
                }
                if (i < 100) {
                    f3905a.put(webView.getUrl(), 0);
                }
            }
        } catch (Exception unused) {
            HiLog.w("HAWebChromeClient", "failed to inject js script");
        }
    }

    public static /* synthetic */ void a(String str) {
        if (TextUtils.equals(str, "true")) {
            HiLog.d("HAWebChromeClient", "success to inject js script");
        }
    }
}
