package com.huawei.watchface.mvp.model.webview;

import android.content.Context;
import android.webkit.WebView;
import com.huawei.watchface.api.PortraitListener;
import com.huawei.watchface.dz;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.manager.WatchFaceKaleidoscopeManager;
import com.huawei.watchface.mvp.model.datatype.DownloadQueryBean;
import com.huawei.watchface.mvp.model.datatype.TryoutBean;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.helper.JsSafeUrlSystemParamManager;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.callback.PluginOperationAdapter;
import com.huawei.watchface.utils.callback.SendCurrentUrlCallback;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import com.huawei.watchface.utils.constants.WebViewConstant;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class JsInteractAddition {
    public static final String HI_TOP_ID = "hiTopId";
    public static final String HI_TOP_URL = "url";
    public static final String HI_TOP_VERSION = "version";

    /* renamed from: a, reason: collision with root package name */
    private static final String f11087a = "JsInteractAddition";
    private WeakReference<WebView> b;
    private WeakReference<WebView> c;
    private PortraitListener d;

    static class a {

        /* renamed from: a, reason: collision with root package name */
        public static final JsInteractAddition f11088a = new JsInteractAddition();
    }

    private JsInteractAddition() {
    }

    public static JsInteractAddition getInstance() {
        return a.f11088a;
    }

    public void setPortraitListener(PortraitListener portraitListener) {
        this.d = portraitListener;
    }

    public void a(String str) {
        PortraitListener portraitListener = this.d;
        if (portraitListener != null) {
            portraitListener.onStartSeparationPortrait(str);
        }
    }

    public void a(WebView webView) {
        this.b = new WeakReference<>(webView);
    }

    public void b(WebView webView) {
        this.c = new WeakReference<>(webView);
    }

    public WebView a() {
        WeakReference<WebView> weakReference = this.c;
        if (weakReference != null && weakReference.get() != null) {
            HwLog.d(f11087a, "get magic webView");
            return this.c.get();
        }
        WeakReference<WebView> weakReference2 = this.b;
        if (weakReference2 != null) {
            return weakReference2.get();
        }
        return null;
    }

    public boolean a(SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter) {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(a())) {
            return false;
        }
        boolean d = CommonUtils.d();
        HwLog.i(f11087a, "isTahitiForWeb() isTahiti: " + d);
        return d;
    }

    public void a(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter) {
        if (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(a(), WebViewConstant.InterfaceName.INTERFACE_CHOOSE_WEAR_TO_CLIP)) {
            if (pluginOperationAdapter != null) {
                pluginOperationAdapter.chooseWearToClip();
            } else {
                HwLog.e(f11087a, "choosePicToClip error adapter is null");
            }
        }
    }

    public String b(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter) {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(a())) {
            return "";
        }
        String albumPackageName = pluginOperationAdapter != null ? pluginOperationAdapter.getAlbumPackageName() : "";
        HwLog.i(f11087a, "getAlbumPackageName return:" + albumPackageName);
        return albumPackageName;
    }

    public String a(PluginOperationAdapter pluginOperationAdapter) {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(a())) {
            return "";
        }
        String videoAlbumPackageName = pluginOperationAdapter != null ? pluginOperationAdapter.getVideoAlbumPackageName() : "";
        HwLog.i(f11087a, "getVideoAlbumPackageName retStr: " + videoAlbumPackageName);
        return videoAlbumPackageName;
    }

    public int c(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter) {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(a())) {
            return -1;
        }
        if (pluginOperationAdapter != null) {
            return pluginOperationAdapter.getWatchFaceInstallState();
        }
        HwLog.e(f11087a, "getWatchFaceNames error adapter is null");
        return -1;
    }

    public void a(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter, String str) {
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(a())) {
            if (pluginOperationAdapter != null) {
                pluginOperationAdapter.getWatchFaceNames(str);
            } else {
                HwLog.e(f11087a, "getWatchFaceNames error adapter is null");
            }
        }
    }

    public void a(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter, HashMap<String, String> hashMap) {
        String str = f11087a;
        HwLog.i(str, "getWatchFaceThemeWearInfo");
        if (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(a(), WebViewConstant.InterfaceName.INTERFACE_GETWATCHFACETHEMEALBUMINFO)) {
            HwLog.i(str, "getWatchFaceThemeWearInfo enter");
            if (pluginOperationAdapter != null) {
                HwLog.i(str, "getWatchFaceThemeWearInfo enter adapter is not null");
                pluginOperationAdapter.getWatchFaceThemeWearInfo(hashMap.get(HI_TOP_ID), hashMap.get("version"), hashMap.get("url"), hashMap.get(WatchFaceConstant.HASHCODE));
            }
        }
    }

    public void b(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter, String str) {
        if (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(a(), WebViewConstant.InterfaceName.INTERFACE_SAVEWATCHFACETHEMEALBUMINFO) && pluginOperationAdapter != null) {
            pluginOperationAdapter.saveWatchFaceThemeWearInfo(str);
        }
    }

    public String d(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter) {
        if (!JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(a(), "getWatchFaceUrlBase")) {
            return "";
        }
        String str = f11087a;
        HwLog.i(str, "getWatchFaceUrlBase");
        if (pluginOperationAdapter != null) {
            return pluginOperationAdapter.getWatchFaceUrlBase();
        }
        HwLog.e(str, "getWatchFaceUrlBase error adapter is null");
        return "";
    }

    public void a(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter, String str, String str2) {
        if (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(a(), "setWatchFaceDeleteButton")) {
            String str3 = f11087a;
            HwLog.i(str3, "setWatchFaceDeleteId hiTopId: " + str + " version:" + str2);
            if (pluginOperationAdapter != null) {
                pluginOperationAdapter.setWatchFaceDeleteId(str, str2);
            } else {
                HwLog.e(str3, "setWatchFaceDeleteButton error adapter is null");
            }
        }
    }

    public void b(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter, String str, String str2) {
        if (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(a(), "deleteWatchFace")) {
            String str3 = f11087a;
            HwLog.i(str3, "deleteWatchFace hiTopId: " + str + " version:" + str2);
            if (pluginOperationAdapter != null) {
                pluginOperationAdapter.deleteWatchFace(str, str2);
            } else {
                HwLog.e(str3, "deleteWatchFace error adapter is null");
            }
        }
    }

    public void c(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter, String str, String str2) {
        if (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(a(), "cancelInstallWatchFace")) {
            String str3 = f11087a;
            HwLog.i(str3, "cancelInstallWatchFace hiTopId: " + str + " version:" + str2);
            if (pluginOperationAdapter != null) {
                pluginOperationAdapter.cancelInstallWatchFace(str, str2);
            } else {
                HwLog.e(str3, "cancelInstallWatchFace error adapter is null");
            }
        }
    }

    public String e(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter) {
        return (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(a(), "getTokenAndDeviceType") && pluginOperationAdapter != null) ? pluginOperationAdapter.getTokenAndDeviceType() : "";
    }

    public String b(PluginOperationAdapter pluginOperationAdapter) {
        return !JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(a()) ? "" : pluginOperationAdapter.getWatchFaceSortState();
    }

    public String c(PluginOperationAdapter pluginOperationAdapter) {
        return !JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(a()) ? "" : pluginOperationAdapter.getWatchFaceSortList();
    }

    public void a(String str, PluginOperationAdapter pluginOperationAdapter) {
        if (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(a(), "setWatchFaceSortList")) {
            pluginOperationAdapter.setWatchFaceSortList(str);
        }
    }

    public String d(PluginOperationAdapter pluginOperationAdapter) {
        return !JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(a()) ? "" : pluginOperationAdapter.getWatchFaceData();
    }

    public String a(PluginOperationAdapter pluginOperationAdapter, DownloadQueryBean downloadQueryBean) {
        if (downloadQueryBean == null) {
            HwLog.w(f11087a, "sendStartDownloadWatchFace -- tryoutBean parse failed");
            return "-2";
        }
        String str = f11087a;
        HwLog.i(str, "sendStartDownloadWatchFace hitopId:" + downloadQueryBean.getHitopId() + ", cnTitle:" + downloadQueryBean.getCnTitle());
        if (pluginOperationAdapter != null) {
            return pluginOperationAdapter.downloadAndInstallWatchFace(downloadQueryBean);
        }
        HwLog.i(str, "sendStartDownloadWatchFace() fail mAdapter is null.");
        return "1";
    }

    public void a(PluginOperationAdapter pluginOperationAdapter, String str) {
        if (!JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(a(), "sendStartTryoutDownload")) {
            HwLog.w(f11087a, "sendStartTryoutDownload -- not in white list");
            return;
        }
        TryoutBean tryoutBean = (TryoutBean) GsonHelper.fromJson(str, TryoutBean.class);
        if (tryoutBean == null) {
            HwLog.w(f11087a, "sendStartTryoutDownload -- tryoutBean parse failed");
            return;
        }
        if (pluginOperationAdapter != null) {
            HwLog.i(f11087a, "sendStartTryoutDownload() hiTopId:" + tryoutBean.getTryOutHiTopId() + " cnTitle:" + tryoutBean.getCnTitle() + " version:" + tryoutBean.getVersion() + " productId:" + tryoutBean.getProductId() + " resourceType:" + tryoutBean.getResourceType());
            dz.b("");
            pluginOperationAdapter.installTryoutWatchFace(tryoutBean);
            return;
        }
        HwLog.i(f11087a, "sendStartTryoutDownload() fail mAdapter is null.");
    }

    public String e(PluginOperationAdapter pluginOperationAdapter) {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(a())) {
            HwLog.e(f11087a, "getTryOutWatchFacePackageName -- not in white list");
            return "";
        }
        String tryOutWatchFacePackageName = pluginOperationAdapter != null ? pluginOperationAdapter.getTryOutWatchFacePackageName() : "";
        HwLog.i(f11087a, "getTryOutWatchFacePackageName retStr: " + tryOutWatchFacePackageName);
        return tryOutWatchFacePackageName;
    }

    public String f(PluginOperationAdapter pluginOperationAdapter) {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(a())) {
            return "";
        }
        String kaleidoscopePackageName = pluginOperationAdapter != null ? pluginOperationAdapter.getKaleidoscopePackageName() : "";
        HwLog.i(f11087a, "getKaleidoscopePackageName retStr: " + kaleidoscopePackageName);
        return kaleidoscopePackageName;
    }

    public void f(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter) {
        if (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(a(), WebViewConstant.InterfaceName.INTERFACE_CHOOSEPICTOCLIP)) {
            if (pluginOperationAdapter != null) {
                pluginOperationAdapter.choosePicToClip();
            } else {
                HwLog.e(f11087a, "choosePicToClip error adapter is null");
            }
        }
    }

    public void g(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter) {
        if (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(a(), WebViewConstant.InterfaceName.INTERFACE_CHOOSEPICTOCLIP)) {
            if (pluginOperationAdapter != null) {
                pluginOperationAdapter.choosePicToH5Clip();
            } else {
                HwLog.e(f11087a, "choosePicToClip error adapter is null");
            }
        }
    }

    public void c(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter, String str) {
        HwLog.i(f11087a, "JsInteraction, saveWatchFaceThemeAlbumInfo");
        if (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(a(), WebViewConstant.InterfaceName.INTERFACE_SAVEWATCHFACETHEMEALBUMINFO) && pluginOperationAdapter != null) {
            pluginOperationAdapter.saveWatchFaceThemeAlbumInfo(str);
        }
    }

    public void d(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter, String str) {
        HwLog.i(f11087a, "JsInteraction, saveWatchFaceThemeAlbumInfo");
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(a()) && pluginOperationAdapter != null) {
            pluginOperationAdapter.applyWatchFaceThemeAlbumInfo(str);
        }
    }

    public void b(Context context, SendCurrentUrlCallback sendCurrentUrlCallback, PluginOperationAdapter pluginOperationAdapter, HashMap<String, String> hashMap) {
        if (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(a(), WebViewConstant.InterfaceName.INTERFACE_GETWATCHFACETHEMEALBUMINFO) && pluginOperationAdapter != null) {
            pluginOperationAdapter.getWatchFaceThemeAlbumInfo(hashMap.get(HI_TOP_ID), hashMap.get("version"), hashMap.get("url"), hashMap.get(WatchFaceConstant.HASHCODE));
        }
    }

    public void b(PluginOperationAdapter pluginOperationAdapter, String str) {
        if (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(a(), WebViewConstant.InterfaceName.INTERFACE_GETWATCHFACETHEMEALBUMINFO) && pluginOperationAdapter != null) {
            pluginOperationAdapter.syncWatchFaceThemeAlbumInfo(str);
        }
    }

    public String a(WebView webView, PluginOperationAdapter pluginOperationAdapter) {
        return (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(webView) && pluginOperationAdapter != null) ? pluginOperationAdapter.getWearPackageName() : "";
    }

    public void a(HashMap<String, String> hashMap, WebView webView) {
        HwLog.i(f11087a, "getWatchFaceKaleidoscopeInfo() enter.");
        if (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(a(), WebViewConstant.InterfaceName.INTERFACE_GETWATCHFACETHEMEALBUMINFO)) {
            WatchFaceKaleidoscopeManager.getInstance(Environment.getApplicationContext()).getWatchFaceInfo(hashMap.get(HI_TOP_ID), hashMap.get("version"), hashMap.get("url"), hashMap.get(WatchFaceConstant.HASHCODE));
        }
    }
}
