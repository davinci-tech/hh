package com.huawei.health.h5pro.webkit;

import android.content.Context;
import android.content.MutableContextWrapper;
import android.os.Looper;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.operation.utils.Constants;
import java.lang.ref.WeakReference;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/* loaded from: classes8.dex */
public class WebViewPool {

    /* renamed from: a, reason: collision with root package name */
    public static Queue<WebView> f2476a = null;
    public static volatile boolean c = false;
    public static int d = 1;

    public static void release(WebView webView) {
        LogUtil.i("H5PRO_WebViewManager", "release -> " + webView);
        clearWebView(webView);
        destroyWebView(webView);
    }

    public static void init(Context context, int i) {
        LogUtil.i("H5PRO_WebViewManager", "init -> " + i);
        if (c) {
            return;
        }
        if (context == null) {
            LogUtil.e("H5PRO_WebViewManager", "init -> appContext is null");
            return;
        }
        synchronized (WebViewPool.class) {
            if (c) {
                return;
            }
            d = i;
            f2476a = new ArrayBlockingQueue(d);
            for (int i2 = 0; i2 < d; i2++) {
                f2476a.add(create(context, true));
            }
            c = true;
        }
    }

    public static void init(Context context) {
        init(context, 1);
    }

    public static void destroyWebView(WebView webView) {
        LogUtil.i("H5PRO_WebViewManager", "webview destroy");
        if (webView == null) {
            LogUtil.w("H5PRO_WebViewManager", "destroyWebView: mWebView is null");
        } else if (Looper.getMainLooper() != Looper.myLooper()) {
            LogUtil.e("H5PRO_WebViewManager", "destroyWebView: not in ui thread");
        } else {
            webView.removeAllViews();
            webView.destroy();
        }
    }

    public static WebView create(Context context, boolean z) {
        if (z) {
            context = new MutableContextWrapper(context);
        }
        return new WebView(context);
    }

    public static void clearWebView(WebView webView) {
        if (webView == null) {
            LogUtil.w("H5PRO_WebViewManager", "clearWebView: mWebView is null");
            return;
        }
        ViewParent parent = webView.getParent();
        webView.loadUrl(Constants.ABOUT_BLANK);
        WebViewInstrumentation.loadUrl(webView, Constants.ABOUT_BLANK);
        if (parent != null) {
            ((ViewGroup) parent).removeView(webView);
        }
        webView.stopLoading();
        webView.getSettings().setJavaScriptEnabled(false);
        webView.clearHistory();
    }

    public static WebView acquire(Context context) {
        Context context2 = (Context) new WeakReference(context).get();
        if (context2 == null) {
            LogUtil.e("H5PRO_WebViewManager", "get: context is null");
            return null;
        }
        synchronized (WebViewPool.class) {
            if (!c) {
                return create(context2, false);
            }
            WebView poll = f2476a.poll();
            if (poll == null) {
                poll = create(context2, false);
            } else if (poll.getContext() instanceof MutableContextWrapper) {
                ((MutableContextWrapper) poll.getContext()).setBaseContext(context2);
                LogUtil.i("H5PRO_WebViewManager", "get from cache -> " + poll);
            }
            return poll;
        }
    }
}
