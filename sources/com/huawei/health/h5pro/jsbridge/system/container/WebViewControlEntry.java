package com.huawei.health.h5pro.jsbridge.system.container;

import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.utils.CommonUtil;
import com.huawei.health.h5pro.utils.LogUtil;

/* loaded from: classes3.dex */
public class WebViewControlEntry extends JsBaseModule {
    @JavascriptInterface
    public void resizeWebViewHeight(final long j, final int i) {
        LogUtil.i(this.TAG, "resizeWebViewHeight enter: " + i);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.health.h5pro.jsbridge.system.container.WebViewControlEntry.1
            @Override // java.lang.Runnable
            public void run() {
                if (WebViewControlEntry.this.mH5ProInstance == null || WebViewControlEntry.this.mH5ProInstance.getWebView() == null) {
                    return;
                }
                WebView webView = WebViewControlEntry.this.mH5ProInstance.getWebView();
                ViewGroup.LayoutParams layoutParams = webView.getLayoutParams();
                if (layoutParams == null) {
                    WebViewControlEntry.this.onFailureCallback(j, "webViewLayoutParams is null");
                    return;
                }
                layoutParams.height = CommonUtil.dip2px(WebViewControlEntry.this.mContext, i);
                webView.setLayoutParams(layoutParams);
                WebViewControlEntry.this.onSuccessCallback(j);
            }
        });
    }
}
