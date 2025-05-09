package com.huawei.health.h5pro.core;

import android.os.Handler;
import android.os.Looper;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes3.dex */
public class H5ProReverseControlExecutor {

    /* renamed from: a, reason: collision with root package name */
    public static final H5ProReverseControlExecutor f2372a = new H5ProReverseControlExecutor();
    public static final Map<String, H5ProInstance> d = new HashMap();

    /* loaded from: classes8.dex */
    public interface OnReverseControlCallback {
        void onCallback(int i, String str);
    }

    public void remove(H5ProInstance h5ProInstance) {
        Map<String, H5ProInstance> map;
        String baseUrl;
        if (h5ProInstance == null) {
            LogUtil.w("H5PRO_H5ProReverseControlExecutor", "remove: instance is null");
            return;
        }
        H5ProAppInfo appInfo = h5ProInstance.getAppInfo();
        if (appInfo == null) {
            LogUtil.w("H5PRO_H5ProReverseControlExecutor", "remove: appInfo is null");
            return;
        }
        if (appInfo.getH5ProAppType() == H5ProAppInfo.H5ProAppType.H5_MINI_PROGRAM) {
            map = d;
            baseUrl = appInfo.getPkgName();
        } else {
            map = d;
            baseUrl = appInfo.getBaseUrl();
        }
        map.remove(baseUrl);
    }

    public void put(H5ProInstance h5ProInstance) {
        Map<String, H5ProInstance> map;
        String baseUrl;
        if (h5ProInstance == null) {
            LogUtil.w("H5PRO_H5ProReverseControlExecutor", "put: instance is null");
            return;
        }
        H5ProAppInfo appInfo = h5ProInstance.getAppInfo();
        if (appInfo == null) {
            LogUtil.w("H5PRO_H5ProReverseControlExecutor", "put: appInfo is null");
            return;
        }
        if (appInfo.getH5ProAppType() == H5ProAppInfo.H5ProAppType.H5_MINI_PROGRAM) {
            map = d;
            baseUrl = appInfo.getPkgName();
        } else {
            map = d;
            baseUrl = appInfo.getBaseUrl();
        }
        map.put(baseUrl, h5ProInstance);
    }

    public void execute(String str, String str2, final OnReverseControlCallback onReverseControlCallback) {
        H5ProInstance h5ProInstance = d.get(str);
        if (h5ProInstance == null) {
            LogUtil.w("H5PRO_H5ProReverseControlExecutor", "execute: h5ProInstance is null");
            b(-1, "invalid parameter : " + str, onReverseControlCallback);
            return;
        }
        final WebView webView = h5ProInstance.getWebView();
        if (webView == null) {
            LogUtil.w("H5PRO_H5ProReverseControlExecutor", "execute: webView is null");
            b(-1, "webView is null", onReverseControlCallback);
            return;
        }
        if (str2 == null) {
            str2 = "";
        }
        final String format = String.format(Locale.ENGLISH, "javascript:onReverseControlListener('%s')", str2);
        if (onReverseControlCallback == null) {
            webView.evaluateJavascript(format, null);
        } else {
            new H5ProJsExecutor(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.health.h5pro.core.H5ProReverseControlExecutor.1
                @Override // java.lang.Runnable
                public void run() {
                    webView.evaluateJavascript(format, new ValueCallback<String>() { // from class: com.huawei.health.h5pro.core.H5ProReverseControlExecutor.1.1
                        @Override // android.webkit.ValueCallback
                        public void onReceiveValue(String str3) {
                            LogUtil.i("H5PRO_H5ProReverseControlExecutor", "execute: onReceiveValue");
                            AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                            H5ProReverseControlExecutor.this.b(0, str3, onReverseControlCallback);
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, String str, OnReverseControlCallback onReverseControlCallback) {
        if (onReverseControlCallback != null) {
            onReverseControlCallback.onCallback(i, str);
        } else {
            LogUtil.w("H5PRO_H5ProReverseControlExecutor", "onReverseControlCallback： callback is null");
        }
    }

    /* loaded from: classes8.dex */
    public static class H5ProJsExecutor extends Handler {
        public H5ProJsExecutor(Looper looper) {
            super(looper);
        }
    }
}
