package com.huawei.health.h5pro.webkit;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;
import com.huawei.health.h5pro.vengine.H5ProAppLoadListener;
import com.huawei.health.h5pro.vengine.H5ProAppLoader;
import com.huawei.health.h5pro.vengine.H5ProExecJsValueCbk;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.vengine.H5ProPackageManager;
import com.huawei.health.h5pro.webkit.trustlist.H5ProTrustListChecker;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

/* loaded from: classes3.dex */
public class H5ProAppLoaderImp implements H5ProAppLoader {

    /* renamed from: a, reason: collision with root package name */
    public WeakReference<H5ProInstance> f2463a;
    public WeakReference<WebView> b;
    public WeakReference<H5ProPackageManager> c;
    public boolean d = false;
    public WeakReference<H5ProAppLoadListener> e;

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoader
    public void preloadLightApp(String str, H5ProAppLoader.H5ProPreloadCbk h5ProPreloadCbk) {
    }

    public H5ProAppLoaderImp setWebView(WebView webView) {
        this.b = new WeakReference<>(webView);
        return this;
    }

    public H5ProAppLoaderImp setH5ProPackageManager(H5ProPackageManager h5ProPackageManager) {
        this.c = new WeakReference<>(h5ProPackageManager);
        return this;
    }

    public H5ProAppLoaderImp setH5ProInstance(H5ProInstance h5ProInstance) {
        this.f2463a = new WeakReference<>(h5ProInstance);
        return this;
    }

    public H5ProAppLoaderImp setH5ProAppLoadListener(H5ProAppLoadListener h5ProAppLoadListener) {
        this.e = new WeakReference<>(h5ProAppLoadListener);
        return this;
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoader
    public void preloadMiniProgram(String str, H5ProAppLoader.H5ProPreloadCbk h5ProPreloadCbk) {
        H5ProPackageManager h5ProPackageManager = (H5ProPackageManager) GeneralUtil.getReferent(this.c);
        if (h5ProPackageManager != null) {
            h5ProPackageManager.installApp(str, h5ProPreloadCbk);
        }
    }

    public void onViewCreated(WebView webView, String str) {
        synchronized (H5ProAppLoaderImp.class) {
            if (this.d && webView != null) {
                this.d = false;
                H5ProAppLoadListener h5ProAppLoadListener = (H5ProAppLoadListener) GeneralUtil.getReferent(this.e);
                if (h5ProAppLoadListener != null) {
                    h5ProAppLoadListener.onViewCreated((H5ProInstance) GeneralUtil.getReferent(this.f2463a), webView);
                }
            }
        }
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoader
    public void loadMiniProgram(final String str, final String str2) {
        preloadMiniProgram(str, new H5ProAppLoader.H5ProPreloadCbk() { // from class: com.huawei.health.h5pro.webkit.H5ProAppLoaderImp.2
            @Override // com.huawei.health.h5pro.vengine.H5ProAppLoader.H5ProPreloadCbk
            public void onSuccess() {
                LogUtil.i(H5ProAppLoaderImp.this.d(), "loadMiniProgram onSuccess.");
                H5ProAppLoaderImp.this.d(str, str2);
            }

            @Override // com.huawei.health.h5pro.vengine.H5ProAppLoader.H5ProPreloadCbk
            public void onFailure(int i, String str3) {
                LogUtil.e(H5ProAppLoaderImp.this.d(), String.format(Locale.ROOT, "loadMiniProgram onFailure: %s", str3));
                H5ProAppLoadListener h5ProAppLoadListener = (H5ProAppLoadListener) GeneralUtil.getReferent(H5ProAppLoaderImp.this.e);
                if (h5ProAppLoadListener != null) {
                    H5ProInstance h5ProInstance = (H5ProInstance) GeneralUtil.getReferent(H5ProAppLoaderImp.this.f2463a);
                    if (h5ProInstance != null && h5ProInstance.getAppInfo() == null) {
                        H5ProAppInfo h5ProAppInfo = new H5ProAppInfo();
                        h5ProAppInfo.setPkgName(str);
                        h5ProInstance.setAppInfo(h5ProAppInfo);
                    }
                    h5ProAppLoadListener.onException(h5ProInstance, str3);
                }
            }
        });
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoader
    public void loadLightApp(String str) {
        if (H5ProTrustListChecker.disableHttpLoad(str)) {
            LogUtil.w(d(), "loadLightApp: disableHttpLoad -> " + str);
            return;
        }
        H5ProInstance h5ProInstance = (H5ProInstance) GeneralUtil.getReferent(this.f2463a);
        WebView webView = (WebView) GeneralUtil.getReferent(this.b);
        if (webView == null || h5ProInstance == null || !WebKitUtil.isUrlTrusted(webView.getContext(), str)) {
            return;
        }
        LogUtil.i(d(), "loadLightApp trusted");
        H5ProAppInfo h5ProAppInfo = new H5ProAppInfo();
        try {
            URL url = new URL(str);
            h5ProAppInfo.setBaseUrl(url.getProtocol() + "://" + url.getHost());
        } catch (MalformedURLException e) {
            LogUtil.e(d(), "loadLightApp==>parse URL fail:", e.getMessage());
        }
        h5ProAppInfo.setH5ProAppType(H5ProAppInfo.H5ProAppType.H5_LIGHT_APP);
        h5ProInstance.setAppInfo(h5ProAppInfo);
        webView.loadUrl(str);
        WebViewInstrumentation.loadUrl(webView, str);
        c(false);
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoader
    public void loadData(String str, String str2, String str3) {
        WebView webView = (WebView) GeneralUtil.getReferent(this.b);
        if (webView != null) {
            webView.loadData(str, str2, str3);
        }
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProAppLoader
    public void execJs(final String str, final H5ProExecJsValueCbk h5ProExecJsValueCbk) {
        new H5ProApploaderHandler(Looper.getMainLooper()).post(new HandlerRunnable() { // from class: com.huawei.health.h5pro.webkit.H5ProAppLoaderImp.3
            @Override // java.lang.Runnable
            public void run() {
                WebView webView = (WebView) GeneralUtil.getReferent(H5ProAppLoaderImp.this.b);
                if (webView != null) {
                    if (h5ProExecJsValueCbk == null) {
                        webView.evaluateJavascript(str, null);
                    } else {
                        webView.evaluateJavascript(str, new ValueCallback<String>() { // from class: com.huawei.health.h5pro.webkit.H5ProAppLoaderImp.3.1
                            @Override // android.webkit.ValueCallback
                            public void onReceiveValue(String str2) {
                                h5ProExecJsValueCbk.onReceiveValue(str2);
                            }
                        });
                    }
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d() {
        return LogUtil.getTag(this.f2463a.get(), "AppLoaderIml");
    }

    private void c(boolean z) {
        H5ProAppLoadListener h5ProAppLoadListener = (H5ProAppLoadListener) GeneralUtil.getReferent(this.e);
        if (!z) {
            if (h5ProAppLoadListener != null) {
                h5ProAppLoadListener.onViewCreated((H5ProInstance) GeneralUtil.getReferent(this.f2463a), (View) GeneralUtil.getReferent(this.b));
            }
        } else {
            this.d = true;
            if (h5ProAppLoadListener != null) {
                h5ProAppLoadListener.onViewPreCreate((H5ProInstance) GeneralUtil.getReferent(this.f2463a), (View) GeneralUtil.getReferent(this.b));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final String str, final String str2) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new H5ProApploaderHandler(Looper.getMainLooper()).post(new HandlerRunnable() { // from class: com.huawei.health.h5pro.webkit.H5ProAppLoaderImp.1
                @Override // java.lang.Runnable
                public void run() {
                    H5ProAppLoaderImp.this.b(str, str2);
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                }
            });
        } else {
            b(str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, String str2) {
        String str3;
        String str4;
        H5ProPackageManager h5ProPackageManager = (H5ProPackageManager) GeneralUtil.getReferent(this.c);
        H5ProInstance h5ProInstance = (H5ProInstance) GeneralUtil.getReferent(this.f2463a);
        H5ProAppLoadListener h5ProAppLoadListener = (H5ProAppLoadListener) GeneralUtil.getReferent(this.e);
        WebView webView = (WebView) GeneralUtil.getReferent(this.b);
        if (h5ProPackageManager == null || webView == null || h5ProInstance == null || h5ProAppLoadListener == null) {
            return;
        }
        h5ProInstance.setAppInfo(h5ProPackageManager.getAppInfo(str));
        try {
            if (TextUtils.isEmpty(str2)) {
                str4 = h5ProPackageManager.joinUrl(str);
            } else {
                str4 = h5ProPackageManager.joinUrl(str) + str2;
            }
        } catch (FileNotFoundException e) {
            str3 = str + ":" + e.getMessage();
        }
        if (WebKitUtil.isUrlTrusted(webView.getContext(), str4)) {
            LogUtil.i(d(), "doLoad trusted.");
            webView.loadUrl(str4);
            WebViewInstrumentation.loadUrl(webView, str4);
            c(false);
            return;
        }
        str3 = str + ": url not trusted";
        h5ProAppLoadListener.onException(h5ProInstance, str3);
    }

    public static class H5ProApploaderHandler extends Handler {
        public H5ProApploaderHandler(Looper looper) {
            super(looper);
        }
    }

    public static abstract class HandlerRunnable implements Runnable {
        public HandlerRunnable() {
        }
    }
}
