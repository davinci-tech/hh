package com.huawei.health.h5pro.webkit;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;
import com.huawei.health.h5pro.vengine.H5ProAppLoadListener;
import com.huawei.health.h5pro.vengine.H5ProAppLoader;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.vengine.H5ProPackageManager;
import com.huawei.health.h5pro.vengine.H5ResultCallback;
import com.huawei.health.h5pro.vengine.load.H5ProInstanceStatus;
import com.huawei.health.h5pro.vengine.load.H5ProInstanceStatusListener;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.operation.utils.Constants;
import java.lang.ref.WeakReference;
import java.net.URLConnection;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes3.dex */
public class WebKitInstance extends H5ProInstance {

    /* renamed from: a, reason: collision with root package name */
    public H5ProPackageManager f2473a;
    public H5ProAppLoadListener e;
    public H5ProWebChromeClient f;
    public WebView g;
    public H5ProWebViewClient i;
    public boolean b = false;
    public boolean d = false;
    public boolean c = false;

    @Override // com.huawei.health.h5pro.vengine.H5ProInstance
    public void setIsEnableOnResumeCallback(boolean z) {
        this.b = z;
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProInstance
    public void setIsEnableOnPauseCallback(boolean z) {
        this.c = z;
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProInstance
    public void setIsEnableOnDestroyCallback(boolean z) {
        this.d = z;
    }

    public void setCustomViewListener(WebChromeCustomViewListener webChromeCustomViewListener) {
        H5ProWebChromeClient h5ProWebChromeClient = this.f;
        if (h5ProWebChromeClient != null) {
            h5ProWebChromeClient.setCustomViewListener(webChromeCustomViewListener);
        }
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProInstance
    public void setBackGroundColor(int i) {
        WebView webView = this.g;
        if (webView != null) {
            webView.setBackgroundColor(i);
        }
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProInstance
    public void registerJsModule(String str, Object obj) {
        WebView webView = this.g;
        if (webView != null) {
            webView.addJavascriptInterface(obj, str);
        }
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProInstance
    public void registerInstanceStatusListener(H5ProInstanceStatusListener h5ProInstanceStatusListener) {
        H5ProPackageManager h5ProPackageManager = this.f2473a;
        if (h5ProPackageManager != null) {
            h5ProPackageManager.setInstanceStatusListener(h5ProInstanceStatusListener);
        }
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProInstance
    public String readPreRequestsJson() {
        H5ProAppInfo appInfo;
        return (this.f2473a == null || (appInfo = getAppInfo()) == null || TextUtils.isEmpty(appInfo.getPkgName())) ? "" : this.f2473a.readPreRequestsJson(appInfo.getPkgName());
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProInstance
    public void onStatusChanged(H5ProInstanceStatus h5ProInstanceStatus) {
        if (h5ProInstanceStatus == H5ProInstanceStatus.CONFIG_PREPARED) {
            a();
        }
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProInstance
    public void onResume() {
        super.onResume();
        WebView webView = this.g;
        if (webView != null) {
            webView.onResume();
            this.g.resumeTimers();
            if (this.b) {
                c("onResume");
            }
            interceptWebViewPause(false);
        }
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProInstance
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        H5ProWebChromeClient h5ProWebChromeClient = this.f;
        if (h5ProWebChromeClient != null) {
            h5ProWebChromeClient.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProInstance
    public void onPause() {
        super.onPause();
        if (this.g != null) {
            if (this.c) {
                c("onPause");
            }
            if (isInterceptWebViewPause()) {
                LogUtil.e("H5PRO_WebKitInstance", "onPause: isInterceptWebViewPause -> true");
            } else {
                this.g.onPause();
            }
        }
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProInstance
    public void onMultiWindowModeChange(int i) {
        WebView webView = this.g;
        if (webView != null) {
            webView.evaluateJavascript(String.format("if (typeof(window.onMultiWindowModeChange) === 'function'){window.onMultiWindowModeChange(%s);}", Integer.valueOf(i)), null);
        }
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProInstance
    public void onActivityResult(int i, int i2, Intent intent) {
        H5ProWebChromeClient h5ProWebChromeClient = this.f;
        if (h5ProWebChromeClient != null) {
            h5ProWebChromeClient.onActivityResult(i, i2, intent);
        }
    }

    public boolean isTitleValid(String str) {
        return (str == null || Patterns.WEB_URL.matcher(str).matches() || URLUtil.isValidUrl(str)) ? false : true;
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProInstance
    public void goBack(H5ResultCallback h5ResultCallback) {
        WebView webView = this.g;
        if (webView != null) {
            webView.evaluateJavascript("if (typeof(window.onGoBack) === 'function') {window.onGoBack();}", new ValueCallbackImpl(webView, h5ResultCallback));
            return;
        }
        LogUtil.w("H5PRO_WebKitInstance", "goBack: mWebView is null");
        if (h5ResultCallback != null) {
            h5ResultCallback.onResult(Boolean.TRUE);
        } else {
            LogUtil.w("H5PRO_WebKitInstance", "goBack: callback is null");
        }
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProInstance
    public WebView getWebView() {
        return this.g;
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProInstance
    public String getUrl() {
        if (this.g == null) {
            LogUtil.w("H5PRO_WebKitInstance", "getUrl: webview is null");
            return "";
        }
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            return this.g.getUrl();
        }
        Handler handler = new Handler(Looper.getMainLooper());
        final String[] strArr = {null};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        handler.post(new Runnable() { // from class: com.huawei.health.h5pro.webkit.WebKitInstance.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (strArr) {
                    if (WebKitInstance.this.g == null) {
                        LogUtil.w("H5PRO_WebKitInstance", "run: webview is null");
                        strArr[0] = "";
                    } else {
                        strArr[0] = WebKitInstance.this.g.getUrl();
                    }
                    countDownLatch.countDown();
                }
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException unused) {
            LogUtil.e("H5PRO_WebKitInstance", "getUrl InterruptedException");
        }
        return strArr[0];
    }

    public String getMimeType(String str) {
        if (str == null) {
            str = "";
        }
        if (str.contains("#")) {
            str = str.replace("#", "%23");
        }
        try {
            return URLConnection.getFileNameMap().getContentTypeFor(str);
        } catch (StringIndexOutOfBoundsException unused) {
            LogUtil.e("H5PRO_WebKitInstance", "StringIndexOutOfBoundsException: " + str);
            return "";
        }
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProInstance
    public H5ProAppLoader getAppLoader() {
        H5ProAppLoaderImp h5ProAppLoadListener = new H5ProAppLoaderImp().setWebView(this.g).setH5ProPackageManager(this.f2473a).setH5ProInstance(this).setH5ProAppLoadListener(this.e);
        H5ProWebViewClient h5ProWebViewClient = this.i;
        if (h5ProWebViewClient != null) {
            h5ProWebViewClient.setH5ProAppLoader(h5ProAppLoadListener);
        }
        return h5ProAppLoadListener;
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProInstance
    public H5ProAppLoadListener getAppLoadListener() {
        return this.e;
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProInstance
    public void destroy() {
        if (this.g != null && this.d) {
            c("onDestroy");
        }
        super.destroy();
        if (Looper.getMainLooper() == Looper.myLooper()) {
            e();
        } else {
            this.g.post(new Runnable() { // from class: com.huawei.health.h5pro.webkit.WebKitInstance.2
                @Override // java.lang.Runnable
                public void run() {
                    WebKitInstance.this.e();
                }
            });
        }
        this.e = null;
        this.i = null;
        this.f = null;
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProInstance
    public boolean canGoBack() {
        WebView webView = this.g;
        if (webView == null) {
            return false;
        }
        return webView.canGoBack();
    }

    private void a() {
        H5ProAppInfo appInfo;
        if (this.f2473a == null || (appInfo = getAppInfo()) == null || TextUtils.isEmpty(appInfo.getPkgName())) {
            return;
        }
        setAppInfo(this.f2473a.getAppInfo(appInfo.getPkgName()));
    }

    public static void setDarkMode(Context context, WebView webView, int i) {
        if (context == null || webView == null) {
            return;
        }
        WebKitUtil.setDarkMode(context, webView, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        WebView webView = this.g;
        if (webView == null) {
            LogUtil.w("H5PRO_WebKitInstance", "clearWebView: mWebView is null");
            return;
        }
        ViewParent parent = webView.getParent();
        WebView webView2 = this.g;
        webView2.loadUrl(Constants.ABOUT_BLANK);
        WebViewInstrumentation.loadUrl(webView2, Constants.ABOUT_BLANK);
        if (parent != null) {
            ((ViewGroup) parent).removeView(this.g);
        }
        this.g.stopLoading();
        this.g.getSettings().setJavaScriptEnabled(false);
        this.g.clearHistory();
        this.g.removeAllViews();
        try {
            LogUtil.i("H5PRO_WebKitInstance", "webview destroy");
            this.g.destroy();
        } catch (Throwable th) {
            LogUtil.e("H5PRO_WebKitInstance", "webview destroy fail:" + th.getMessage());
        }
        this.g = null;
    }

    public static class ValueCallbackImpl implements ValueCallback<String> {
        public WeakReference<WebView> b;
        public WeakReference<H5ResultCallback> e;

        @Override // android.webkit.ValueCallback
        public void onReceiveValue(String str) {
            LogUtil.i("H5PRO_WebKitInstance", "receive h5 result: " + str);
            if (TextUtils.equals("true", str)) {
                c(false);
                return;
            }
            WebView webView = (WebView) GeneralUtil.getReferent(this.b);
            if (webView == null || !webView.canGoBack()) {
                c(true);
            } else {
                webView.goBack();
                c(false);
            }
        }

        private void c(boolean z) {
            H5ResultCallback h5ResultCallback = (H5ResultCallback) GeneralUtil.getReferent(this.e);
            if (h5ResultCallback != null) {
                h5ResultCallback.onResult(Boolean.valueOf(z));
            }
        }

        public ValueCallbackImpl(WebView webView, H5ResultCallback h5ResultCallback) {
            this.b = new WeakReference<>(webView);
            this.e = new WeakReference<>(h5ResultCallback);
        }
    }

    private void c(String str) {
        WebView webView = this.g;
        if (webView != null) {
            webView.evaluateJavascript(String.format("if (typeof(window.%sCallback) === 'function') {window.%sCallback();}", str, str), null);
        }
    }

    public WebKitInstance(WebView webView, H5ProAppLoadListener h5ProAppLoadListener) {
        this.g = webView;
        this.e = h5ProAppLoadListener;
    }

    public WebKitInstance(Context context, H5ProAppLoadListener h5ProAppLoadListener) {
        long time = new Date().getTime();
        this.g = new WebView(context);
        LogUtil.i("H5PRO_WebKitInstance", "createView duration: " + (new Date().getTime() - time));
        this.e = h5ProAppLoadListener;
        this.f2473a = new QuickPackageManager(context);
        H5ProWebChromeClient h5ProWebChromeClient = new H5ProWebChromeClient(context, this, this.e);
        this.f = h5ProWebChromeClient;
        this.g.setWebChromeClient(h5ProWebChromeClient);
        H5ProWebViewClient h5ProWebViewClient = new H5ProWebViewClient(this, this.e);
        this.i = h5ProWebViewClient;
        WebView webView = this.g;
        if (webView instanceof WebView) {
            APMSH5LoadInstrument.setWebViewClient(webView, h5ProWebViewClient);
        } else {
            webView.setWebViewClient(h5ProWebViewClient);
        }
        WebKitUtil.initWebSetting(context, this.g);
        WebKitUtil.setBasicSecurity(this.g);
        WebView.setWebContentsDebuggingEnabled(!WebKitUtil.isStrictMode());
    }
}
