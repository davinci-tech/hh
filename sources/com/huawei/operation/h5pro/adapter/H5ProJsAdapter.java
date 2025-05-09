package com.huawei.operation.h5pro.adapter;

import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.huawei.health.h5pro.core.H5ProEngineFactory;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.vengine.H5ProAppLoadListener;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.h5pro.TrustListCheckerImpl;
import com.huawei.operation.h5pro.jsmodules.InnerApi;
import com.huawei.operation.h5pro.jsmodules.trade.JsTradeApi;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* loaded from: classes5.dex */
public class H5ProJsAdapter {
    private static final String TAG = "H5PRO_H5ProJsAdapter";
    private H5ProInstance h5ProInstance;
    private WeakHashMap<String, JsBaseModule> jsApiMap = new WeakHashMap<>();
    private WeakReference<WebView> mWebView;

    public H5ProJsAdapter(WebView webView, RelativeLayout relativeLayout) {
        LogUtil.a(TAG, "H5ProJsAdapter start.");
        this.mWebView = new WeakReference<>(webView);
        this.h5ProInstance = initH5ProInstance(webView, relativeLayout);
    }

    public void mountH5ProInterfaces(String str) {
        mountInnerApi(str);
        mountJsTradeApi();
    }

    public void cleanH5ProInterfaces() {
        WeakHashMap<String, JsBaseModule> weakHashMap = this.jsApiMap;
        if (weakHashMap == null || weakHashMap.isEmpty()) {
            return;
        }
        JsBaseModule jsBaseModule = this.jsApiMap.get("innerapi");
        if (jsBaseModule != null) {
            jsBaseModule.onDestroy();
            this.jsApiMap.remove("innerapi");
            LogUtil.a(TAG, "cleanH5ProInterfaces: innerApi");
        }
        JsBaseModule jsBaseModule2 = this.jsApiMap.get("tradeApi");
        if (jsBaseModule2 != null) {
            jsBaseModule2.onDestroy();
            this.jsApiMap.remove("tradeApi");
            LogUtil.a(TAG, "cleanH5ProInterfaces: tradeApi");
        }
    }

    private H5ProInstance initH5ProInstance(WebView webView, RelativeLayout relativeLayout) {
        H5ProInstance createInstance = H5ProEngineFactory.getH5ProEngine().createInstance(webView, (H5ProAppLoadListener) null);
        createInstance.setJsCbkInvoker(new JsCbkInvoker(webView));
        createInstance.setViewControls(new H5ViewControls(relativeLayout));
        return createInstance;
    }

    private void mountInnerApi(String str) {
        LogUtil.a(TAG, "mountInnerApi start.");
        WeakReference<WebView> weakReference = this.mWebView;
        if (weakReference == null) {
            LogUtil.h(TAG, "mountInnerApi:", "mWebView is null");
            return;
        }
        WebView webView = weakReference.get();
        if (webView == null) {
            LogUtil.h(TAG, "mountInnerApi:", "webView is null");
            return;
        }
        if (!TrustListCheckerImpl.isMountSpecialJsModule(false, str)) {
            LogUtil.h(TAG, "mountInnerApi: untrusted -> " + str);
        } else {
            InnerApi innerApi = new InnerApi();
            innerApi.onMount(webView.getContext(), this.h5ProInstance);
            webView.addJavascriptInterface(innerApi, "innerapi");
            this.jsApiMap.put("innerapi", innerApi);
        }
    }

    private void mountJsTradeApi() {
        LogUtil.a(TAG, "mountJsTradeApi start.");
        WeakReference<WebView> weakReference = this.mWebView;
        if (weakReference == null || weakReference.get() == null) {
            LogUtil.h(TAG, "mountJsTradeApi:", "webView is null");
            return;
        }
        WebView webView = this.mWebView.get();
        JsTradeApi jsTradeApi = new JsTradeApi();
        jsTradeApi.onMount(webView.getContext(), this.h5ProInstance);
        webView.addJavascriptInterface(jsTradeApi, "tradeApi");
        this.jsApiMap.put("tradeApi", jsTradeApi);
    }
}
