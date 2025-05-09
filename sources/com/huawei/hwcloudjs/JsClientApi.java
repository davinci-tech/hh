package com.huawei.hwcloudjs;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebView;
import com.huawei.hwcloudjs.api.IAccessToken;
import com.huawei.hwcloudjs.api.ILocDialog;
import com.huawei.hwcloudjs.api.ValidateWhiteListListener;
import com.huawei.hwcloudjs.api.WebviewDownloadListenner;
import com.huawei.hwcloudjs.api.WebviewFileChooserListenner;
import com.huawei.hwcloudjs.core.JSRequest;
import com.huawei.hwcloudjs.d.c.a;
import com.huawei.hwcloudjs.service.debugtool.SetUrl;
import com.huawei.hwcloudjs.service.hms.HmsLiteCoreApi;
import com.huawei.hwcloudjs.service.hms.a;
import com.huawei.hwcloudjs.service.jsapi.JsCoreApi;
import com.huawei.hwcloudjs.service.jsmsg.NativeMsg;
import com.huawei.hwcloudjs.support.enables.INoProguard;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class JsClientApi {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, HWCloudJSBridge> f6182a = new HashMap();
    private static Class b;
    private static IAccessToken c;
    private static Class d;
    private static HashMap<String, Class> e;
    private static WebviewFileChooserListenner f;
    private static WebviewDownloadListenner g;
    private static String h;
    private static boolean i;
    private static int j;

    /* loaded from: classes9.dex */
    public interface Permission extends INoProguard {
        public static final String BASE = "com.huawei.javascript.base";
    }

    public static WebviewFileChooserListenner webviewFileChooserListenner() {
        return f;
    }

    public static String webViewUserAgentString() {
        return h;
    }

    public static WebviewDownloadListenner webViewDownLoadListener() {
        return g;
    }

    public static int webSettingsMixmode() {
        return j;
    }

    public static void unRegisterJsApi(String str) {
        com.huawei.hwcloudjs.core.a.b.a().b(str);
    }

    public static void setUrlToWebView(SetUrl.GetUrlCallBack getUrlCallBack) {
        SetUrl.a().a(getUrlCallBack);
    }

    public static void setMaxCacheSize(long j2) {
        com.huawei.hwcloudjs.e.a.c.d().a(j2);
    }

    public static void setJsUrl(String str) {
        com.huawei.hwcloudjs.d.c.a.a.b().b(str);
    }

    public static void setJSOption(SdkOpt sdkOpt) {
        if (sdkOpt != null) {
            com.huawei.hwcloudjs.b.a.a(sdkOpt.f6183a);
        }
    }

    public static void setGrsAppName(String str) {
        HmsLiteCoreApi.mGrsAppName = str;
    }

    public static void registerwebViewUserAgentString(String str, boolean z) {
        h = str;
        i = z;
    }

    public static void registerwebViewDownLoadListener(WebviewDownloadListenner webviewDownloadListenner) {
        g = webviewDownloadListenner;
    }

    public static void registerjsInterface(HashMap<String, Class> hashMap) {
        e = hashMap;
    }

    public static void registerWebviewFileChooserListenner(WebviewFileChooserListenner webviewFileChooserListenner) {
        f = webviewFileChooserListenner;
    }

    public static void registerWebviewClass(Class cls) {
        d = cls;
    }

    public static void registerWebSettingsMixmode(int i2) {
        j = i2;
    }

    public static void registerValidateWhiteListListener(ValidateWhiteListListener validateWhiteListListener) {
        com.huawei.hwcloudjs.d.a.c.a();
        com.huawei.hwcloudjs.d.a.c.a(validateWhiteListListener);
    }

    public static void registerUrlWhiteList(List<String> list) {
        if (list != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                com.huawei.hwcloudjs.d.a.c.a().b(it.next());
            }
        }
    }

    public static void registerJsApi(Class<? extends JSRequest> cls) {
        com.huawei.hwcloudjs.core.a.b.a().a(cls);
        if (com.huawei.hwcloudjs.core.c.class.isAssignableFrom(cls)) {
            com.huawei.hwcloudjs.core.d.a().a((Class<? extends com.huawei.hwcloudjs.core.c>) cls);
        }
    }

    public static void registerIAccessTokenClass(IAccessToken iAccessToken) {
        c = iAccessToken;
    }

    public static void registerExtKit(Class<? extends ILocDialog> cls) {
        com.huawei.hwcloudjs.core.extkit.a.b(com.huawei.hwcloudjs.core.extkit.b.f6200a, cls);
    }

    public static void registerActionbarClass(Class cls) {
        b = cls;
    }

    public static void preload(String[] strArr, int i2, Context context) {
        com.huawei.hwcloudjs.e.a.e.a(strArr, i2, context);
    }

    public static void notifyNativeMsgToBridge(String str, NativeMsg nativeMsg) {
        if (nativeMsg != null) {
            Map<String, HWCloudJSBridge> map = f6182a;
            synchronized (map) {
                HWCloudJSBridge hWCloudJSBridge = map.get(str);
                if (hWCloudJSBridge != null) {
                    hWCloudJSBridge.onReceive(nativeMsg);
                }
            }
        }
    }

    public static void notifyNativeMsg(NativeMsg nativeMsg) {
        if (nativeMsg != null) {
            com.huawei.hwcloudjs.service.jsmsg.a.a().a((com.huawei.hwcloudjs.service.jsmsg.a) nativeMsg);
        }
    }

    public static boolean isneedoriginalUserAgent() {
        return i;
    }

    public static boolean isUrlWhileList(String str) {
        return com.huawei.hwcloudjs.d.a.c.a().a(str);
    }

    public static void handleActivityResult(int i2, int i3, Intent intent) {
        a.b bVar = new a.b();
        bVar.a(intent);
        bVar.a(i2);
        bVar.b(i3);
        com.huawei.hwcloudjs.service.hms.a.a().a((com.huawei.hwcloudjs.service.hms.a) bVar);
    }

    public static void handleActivityPermissionResult(int i2, String[] strArr, int[] iArr) {
        a.C0158a c0158a = new a.C0158a();
        c0158a.a(i2);
        c0158a.a(iArr);
        com.huawei.hwcloudjs.d.c.a.a().a((com.huawei.hwcloudjs.d.c.a) c0158a);
    }

    public static Class getjsWebviewClass() {
        return d;
    }

    public static HashMap<String, Class> getjsInterfacemap() {
        return e;
    }

    public static Class getjsActionbarClass() {
        return b;
    }

    public static IAccessToken getIAccessTokenClass() {
        return c;
    }

    public static void destroyApi(String str) {
        Map<String, HWCloudJSBridge> map = f6182a;
        synchronized (map) {
            HWCloudJSBridge remove = map.remove(str);
            if (remove != null) {
                remove.detach();
            }
        }
        com.huawei.hwcloudjs.core.d.a().a(str);
    }

    public static final class SdkOpt {

        /* renamed from: a, reason: collision with root package name */
        private boolean f6183a = true;

        public static final class Builder {

            /* renamed from: a, reason: collision with root package name */
            private boolean f6184a = true;

            public Builder setShowAuthDlg(boolean z) {
                this.f6184a = z;
                return this;
            }

            public SdkOpt build() {
                SdkOpt sdkOpt = new SdkOpt();
                sdkOpt.f6183a = this.f6184a;
                return sdkOpt;
            }
        }
    }

    public static String createApi(WebView webView, SdkOpt sdkOpt) {
        if (sdkOpt != null) {
            com.huawei.hwcloudjs.b.a.a(sdkOpt.f6183a);
        }
        return createApi(webView);
    }

    public static String createApi(WebView webView) {
        com.huawei.hwcloudjs.b.a.a(webView.getContext());
        HWCloudJSBridge hWCloudJSBridge = new HWCloudJSBridge();
        hWCloudJSBridge.attach(webView);
        String bridgeId = hWCloudJSBridge.getBridgeId();
        Map<String, HWCloudJSBridge> map = f6182a;
        synchronized (map) {
            map.put(bridgeId, hWCloudJSBridge);
        }
        return bridgeId;
    }

    public static void clearUserPermissionSet() {
        com.huawei.hwcloudjs.d.c.a.a.b().a();
    }

    static {
        registerJsApi(JsCoreApi.class);
    }
}
