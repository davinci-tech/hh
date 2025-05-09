package com.huawei.watchface.mvp.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.gson.Gson;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hms.ads.jsb.JsbConfig;
import com.huawei.hms.ads.jsb.PPSJsBridge;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.profile.profile.ProfileExtendConstants;
import com.huawei.secure.android.common.ssl.WebViewSSLCheck;
import com.huawei.secure.android.common.util.EncodeUtil;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.secure.android.common.webview.SafeWebSettings;
import com.huawei.secure.android.common.webview.SafeWebView;
import com.huawei.secure.android.common.webview.WebViewLoadCallBack;
import com.huawei.uikit.hwprogressbar.widget.HwProgressBar;
import com.huawei.watchface.R$id;
import com.huawei.watchface.R$layout;
import com.huawei.watchface.R$string;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.api.WebViewActivity;
import com.huawei.watchface.aq;
import com.huawei.watchface.as;
import com.huawei.watchface.at;
import com.huawei.watchface.ba;
import com.huawei.watchface.co;
import com.huawei.watchface.cv;
import com.huawei.watchface.dc;
import com.huawei.watchface.dp;
import com.huawei.watchface.ei;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.mvp.model.datatype.WatchResourcesInfo;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.helper.JsSafeUrlSystemParamManager;
import com.huawei.watchface.mvp.model.latona.BackgroundOptionalConfig;
import com.huawei.watchface.mvp.model.webview.JSInterface;
import com.huawei.watchface.mvp.model.webview.JsInterfaceMarketing;
import com.huawei.watchface.mvp.ui.dialog.CustomViewDialog;
import com.huawei.watchface.mvp.ui.view.CustomWebView;
import com.huawei.watchface.t;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.ChoosePicUtil;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.DensityUtil;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.PermissionUtils;
import com.huawei.watchface.utils.WebViewUtils;
import com.huawei.watchface.utils.callback.CloseWebCallback;
import com.huawei.watchface.utils.callback.DelectUserDesignationWatchFaceCallback;
import com.huawei.watchface.utils.callback.IBaseResponseCallback;
import com.huawei.watchface.utils.callback.IH5LoadingCallback;
import com.huawei.watchface.utils.callback.MagicH5LoadingCallback;
import com.huawei.watchface.utils.callback.OnWebViewStatusCallback;
import com.huawei.watchface.utils.callback.OperateWatchFaceCallback;
import com.huawei.watchface.utils.callback.PluginOperationAdapter;
import com.huawei.watchface.utils.callback.SendCurrentUrlCallback;
import com.huawei.watchface.utils.callback.SendNoNetMsgCallback;
import com.huawei.watchface.utils.callback.SendServerErrorMsgCallback;
import com.huawei.watchface.utils.callback.ToastCallback;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import com.huawei.watchface.utils.permission.PermissionUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class CustomWebView implements View.OnClickListener, CloseWebCallback, DelectUserDesignationWatchFaceCallback, IH5LoadingCallback, MagicH5LoadingCallback, OnWebViewStatusCallback, OperateWatchFaceCallback, SendCurrentUrlCallback, SendNoNetMsgCallback, SendServerErrorMsgCallback, ToastCallback {
    protected static final String[] CAMERA_PERMISSIONS = {"android.permission.CAMERA"};
    public static final int CHOOSE_VIDEO_TO_CLIP = 1;
    protected static final String[] GALLERY_PERMISSIONS;
    public static final int ID_CAMERA_PERMISSIONS = 1;
    protected static final int ID_GALLERY_PERMISSIONS = 2;
    private static final String e = "CustomWebView";
    private static String f;
    protected PPSJsBridge c;
    private int g;
    public boolean isH5Clip;
    private String j;
    private ValueCallback<Uri> l;
    private ValueCallback<Uri[]> m;
    protected Activity mActivity;
    protected int mChooseToClipResourceStyle;
    protected Context mContext;
    protected CustomViewDialog mCustomViewDialog;
    protected SafeWebView mWebView;
    private Uri n;
    private Handler o;
    private HwProgressBar p;
    private PluginOperationAdapter q;
    private co r;
    private String t;
    private boolean v;
    private boolean w;

    /* renamed from: a, reason: collision with root package name */
    public boolean f11111a = false;
    public boolean b = false;
    private String h = "";
    private String i = "";
    private String k = null;
    private String s = "";
    private boolean u = false;
    private boolean x = false;
    protected SparseArray<PermissionUtils.PermissonListener> d = new SparseArray<>();

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void s(String str) {
    }

    public void k() {
    }

    static {
        String[] strArr;
        if (CommonUtils.isAndroid13()) {
            strArr = new String[]{"android.permission.READ_MEDIA_IMAGES"};
        } else {
            strArr = new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
        }
        GALLERY_PERMISSIONS = strArr;
    }

    public CustomWebView(JSInterface jSInterface, Context context, HwProgressBar hwProgressBar, SafeWebView safeWebView, Handler handler, int i) {
        this.g = -1;
        this.j = null;
        this.g = i;
        this.mContext = context;
        this.o = handler;
        this.mWebView = safeWebView;
        this.p = hwProgressBar;
        this.mActivity = (Activity) context;
        this.q = (PluginOperationAdapter) ba.a(context).getAdapter();
        this.w = HwWatchFaceApi.getInstance(this.mContext).isOversea();
        this.v = HwWatchFaceApi.getInstance(this.mContext).isBetaVersion();
        if (((PluginOperationAdapter) ba.a(this.mContext).getAdapter()) == null) {
            HwLog.i(e, "PluginOperationAdapter is null");
            return;
        }
        this.j = CommonUtils.g(this.mContext);
        s();
        if (jSInterface != null) {
            addJavascriptInterface(jSInterface);
        }
    }

    public void addJavascriptInterface(JSInterface jSInterface) {
        Object tag = this.mWebView.getTag();
        if (tag != null && ((Boolean) tag).booleanValue()) {
            HwLog.e(e, "mWebView set JSInterface again");
            return;
        }
        jSInterface.setOperateWatchFaceCallback(this, this);
        jSInterface.setSendCallback(this, this, this);
        jSInterface.setWidgetCallback(this);
        jSInterface.setTouchCallback(this);
        jSInterface.setH5LoadingCallback(this);
        jSInterface.setWebView(this.mWebView);
        this.mWebView.addJavascriptInterface(jSInterface, "JsInteraction");
        JsInterfaceMarketing jsInterfaceMarketing = new JsInterfaceMarketing(this.mContext);
        jsInterfaceMarketing.setWebView(this.mWebView);
        jsInterfaceMarketing.setMagicH5LoadingCallback(this);
        this.mWebView.addJavascriptInterface(jsInterfaceMarketing, WatchFaceConstant.JS_INTERFACE);
        PPSJsBridge.init(new JsbConfig.Builder().enableUserInfo(true).enableLog(true).build());
        HwLog.i(e, "PPSJsBridge bind mWebView");
        this.c = new PPSJsBridge(this.mWebView);
        this.mWebView.setTag(true);
    }

    public ValueCallback<Uri> a() {
        HwLog.i(e, "getUploadMessage");
        return this.l;
    }

    public void a(ValueCallback<Uri> valueCallback) {
        HwLog.i(e, "setUploadMessage");
        this.l = valueCallback;
    }

    public CustomViewDialog b() {
        HwLog.i(e, "getCustomViewDialog");
        return this.mCustomViewDialog;
    }

    public void a(boolean z) {
        this.x = z;
    }

    public ValueCallback<Uri[]> c() {
        HwLog.i(e, "getUploadMessageForAndroid5");
        return this.m;
    }

    public void b(ValueCallback<Uri[]> valueCallback) {
        HwLog.i(e, "setUploadMessageForAndroid5");
        this.m = valueCallback;
    }

    public Uri d() {
        HwLog.i(e, "getImageUri");
        return this.n;
    }

    private void s() {
        HwLog.i(e, "initWebViewSettings");
        FlavorConfig.openWebviewDebugMode();
        WebSettings settings = this.mWebView.getSettings();
        settings.setCacheMode(2);
        settings.setAllowFileAccess(true);
        settings.setUseWideViewPort(true);
        settings.setGeolocationEnabled(false);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        String userAgentString = this.mWebView.getSettings().getUserAgentString();
        this.mWebView.getSettings().setUserAgentString(userAgentString + "; HuaweiHealth");
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setUseWideViewPort(false);
        settings.setTextZoom(100);
        settings.setLoadWithOverviewMode(true);
        settings.setMixedContentMode(2);
        CookieManager.getInstance().setAcceptThirdPartyCookies(this.mWebView, true);
        SafeWebSettings.initWebviewAndSettings(this.mWebView);
        t();
    }

    private void t() {
        if (!this.v && !HwWatchFaceApi.getInstance(this.mContext).isRelease()) {
            HwLog.i(e, "initWebViewSettingsEvent setWebContentsDebuggingEnabled");
        }
        this.mWebView.setWebViewLoadCallBack(new WebViewLoadCallBack() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda12
            @Override // com.huawei.secure.android.common.webview.WebViewLoadCallBack
            public final void onCheckError(String str, WebViewLoadCallBack.ErrorCode errorCode) {
                CustomWebView.a(str, errorCode);
            }
        });
        this.mWebView.setWebChromeClient(new c());
        SafeWebView safeWebView = this.mWebView;
        d dVar = new d();
        if (safeWebView instanceof SafeWebView) {
            APMSH5LoadInstrument.setSafeWebViewClient(safeWebView, dVar, false);
        } else {
            safeWebView.setWebViewClient(dVar, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(String str, WebViewLoadCallBack.ErrorCode errorCode) {
        HwLog.e(e, "initWebViewSettingsEvent -- onCheckError: " + errorCode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, boolean z) {
        at a2 = as.b().a();
        if (a2 != null) {
            List<String> a3 = a2.a();
            if (!ArrayUtils.isEmpty(a3)) {
                this.mWebView.setWhitelistNotMatchSubDomain((String[]) a3.toArray(new String[0]));
            }
        }
        String str2 = e;
        HwLog.i(str2, "load() enter.");
        if (TextUtils.isEmpty(str)) {
            HwLog.i(str2, "load() originUrl isEmpty");
            str = Constants.ABOUT_BLANK;
        }
        String b2 = WebViewUtils.b(str);
        this.i = b2;
        if (!b(b2)) {
            b(false);
        } else if (aq.a(this.i)) {
            HwLog.e(str2, "load() url contain xss char");
        } else {
            b(true);
            c(this.i, z);
        }
    }

    public void loadSafeUrl(final String str, final boolean z) {
        JsSafeUrlSystemParamManager.getInstance().a(new JsSafeUrlSystemParamManager.a() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.1
            @Override // com.huawei.watchface.mvp.model.helper.JsSafeUrlSystemParamManager.a
            public void onGetFinished() {
                HwLog.i(CustomWebView.e, "loadSafeUrl - onGetFinished.");
                at a2 = as.b().a();
                if (a2 == null || !a2.e(str)) {
                    HwLog.i(CustomWebView.e, "loadSafeUrl - loadPage: SupportType is null.");
                    CustomWebView.this.b(str, z);
                } else {
                    CustomWebView.this.a(str, z);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final String str, final boolean z) {
        as.b().a(new IBaseResponseCallback() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.12
            @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
            public void onResponse(int i, Object obj) {
                CustomWebView.this.a(str, z);
            }
        });
        as.b().c();
    }

    private void c(String str, boolean z) {
        if (CommonUtils.f() || str.startsWith("file:///android_asset/")) {
            String str2 = e;
            HwLog.i(str2, "loadUrl url isNetworkConnected");
            HashMap hashMap = new HashMap(2);
            hashMap.put("x-version", this.j);
            HwLog.i(str2, "init time loadUrl:" + (System.currentTimeMillis() - HwWatchFaceApi.getInstance(this.mContext).getInitTime()));
            this.mWebView.loadUrl(str, hashMap);
            if (z) {
                SafeWebView safeWebView = this.mWebView;
                safeWebView.loadUrl("javascript:window.location.reload( true )");
                WebViewInstrumentation.loadUrl(safeWebView, "javascript:window.location.reload( true )");
            }
            this.mWebView.postDelayed(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.23
                @Override // java.lang.Runnable
                public void run() {
                    CustomWebView.this.mWebView.clearHistory();
                    HwLog.i(CustomWebView.e, "loadUrl url postDelayed");
                }
            }, 1000L);
            return;
        }
        if (this.g == 3001) {
            HashMap hashMap2 = new HashMap(2);
            hashMap2.put("x-version", this.j);
            this.mWebView.loadUrl(str, hashMap2);
            HwLog.i(e, "loadUrl url TYPE_MINI_SHOP_FRAGMENT");
            return;
        }
        this.o.sendEmptyMessage(2003);
        HwLog.i(e, "loadUrl url MSG_ON_NET_WORK");
    }

    public void a(String str) {
        String str2 = e;
        HwLog.i(str2, "reload");
        if (TextUtils.isEmpty(str)) {
            HwLog.i(str2, "reload tmpReloadUrl is isEmpty");
            str = Constants.ABOUT_BLANK;
        }
        String b2 = WebViewUtils.b(str);
        this.i = b2;
        if (!b(b2)) {
            b(false);
            return;
        }
        b(true);
        if (CommonUtils.f()) {
            HwLog.i(str2, "reload mOriginUrl isNetworkConnected");
            HashMap hashMap = new HashMap(2);
            hashMap.put("x-version", this.j);
            this.mWebView.loadUrl(this.i, hashMap);
            return;
        }
        if (this.g == 3001) {
            HwLog.i(str2, "reload mOriginUrl TYPE_MINI_SHOP_FRAGMENT");
            HashMap hashMap2 = new HashMap(2);
            hashMap2.put("x-version", this.j);
            this.mWebView.loadUrl(this.i, hashMap2);
            return;
        }
        HwLog.i(str2, "reload mOriginUrl MSG_ON_NET_WORK");
        this.o.sendEmptyMessage(2003);
    }

    public boolean e() {
        SafeWebView safeWebView = this.mWebView;
        if (safeWebView == null) {
            HwLog.i(e, "WebView is null");
            return false;
        }
        if (!safeWebView.canGoBack()) {
            HwLog.i(e, "WebView no go back.");
            return false;
        }
        HwLog.i(e, "webView canGoBack");
        this.mWebView.goBack();
        return true;
    }

    public HwProgressBar f() {
        HwLog.i(e, "getProgressBar");
        return this.p;
    }

    public void g() {
        String str = e;
        HwLog.i(str, "clearUploadMessage");
        ValueCallback<Uri> valueCallback = this.l;
        if (valueCallback != null) {
            valueCallback.onReceiveValue(null);
            this.l = null;
            return;
        }
        ValueCallback<Uri[]> valueCallback2 = this.m;
        if (valueCallback2 != null) {
            valueCallback2.onReceiveValue(new Uri[0]);
            this.m = null;
        } else {
            HwLog.d(str, "clearUploadMessage mUploadMessage = null,mUploadMessageForAndroid5 = null");
        }
    }

    public void b(boolean z) {
        HwLog.i(e, "setJsEnable isEnable:" + z);
        SafeWebView safeWebView = this.mWebView;
        if (safeWebView != null) {
            safeWebView.getSettings().setJavaScriptEnabled(z);
        }
    }

    public boolean b(String str) {
        at a2 = as.b().a();
        return a2 != null && a2.e(str);
    }

    public void c(String str) {
        String str2;
        HwLog.i(e, "callBackWebViewStatus status:" + str);
        if (TextUtils.isEmpty(this.t)) {
            str2 = "";
        } else {
            String encodeForJavaScript = EncodeUtil.encodeForJavaScript(this.t);
            this.t = encodeForJavaScript;
            str2 = WebViewUtils.getUrl(encodeForJavaScript, str);
        }
        loadCallJsUrl(str2);
    }

    public void loadCallJsUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.i(e, "TextUtils.isEmpty(callJs)");
            return;
        }
        SafeWebView safeWebView = this.mWebView;
        safeWebView.loadUrl(str);
        WebViewInstrumentation.loadUrl(safeWebView, str);
    }

    public void transmitClippedPath(final String str) {
        String str2 = e;
        HwLog.i(str2, "transmitClippedPath() enter");
        if (TextUtils.isEmpty(str)) {
            HwLog.i(str2, "transmitClippedPath() imgPath is null");
        } else {
            this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.25
                @Override // java.lang.Runnable
                public void run() {
                    CustomWebView.this.loadCallJsUrl(WebViewUtils.getUrl("transmitClippedPath", str));
                }
            });
        }
    }

    public void h() {
        HwLog.i(e, "transmitCancelPickImage() enter.");
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.26
            @Override // java.lang.Runnable
            public void run() {
                CustomWebView.this.loadCallJsUrl(WebViewUtils.c("transmitCancelPickImage()"));
            }
        });
    }

    public void transmitWearClippedPath(final String str) {
        if (str == null || str.isEmpty()) {
            HwLog.w(e, "transmitWearClippedPath() clippedWearList is null");
        } else {
            HwLog.i(e, "transmitWearClippedPath() enter.");
            this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda30
                @Override // java.lang.Runnable
                public final void run() {
                    CustomWebView.this.t(str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void t(String str) {
        this.mWebView.evaluateJavascript(WebViewUtils.getUrl("transmitWearClippedPath", str), new ValueCallback() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda21
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                CustomWebView.this.u((String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void u(String str) {
        ((Activity) this.mContext).runOnUiThread(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda27
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.hideLoadingDialog();
            }
        });
    }

    public void transmitPortraitClippedPath(String str, String str2, String str3, boolean z, boolean z2) {
        HwLog.i(e, "transmitPortraitClippedPath() enter.");
        HashMap hashMap = new HashMap();
        hashMap.put("imageOriginal", str);
        hashMap.put("imageForeground", str2);
        hashMap.put("imageBackground", str3);
        hashMap.put("isFaceDetected", z ? "true" : "false");
        if (z2) {
            hashMap.put("isError", "true");
        }
        transmitClippedPath(GsonHelper.toJson(hashMap));
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitWatchFaceTimeWidgetColor(int i) {
        final String a2 = CommonUtils.a(i);
        HwLog.i(e, "color is:" + i + "hexColorString:" + a2);
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.27
            @Override // java.lang.Runnable
            public void run() {
                String str = "javascript:transmitWatchFaceTimeWidgetColor('" + a2 + Constants.RIGHT_BRACKET;
                HwLog.i(CustomWebView.e, "transmitWatchFaceTextColor:" + str);
                CustomWebView.this.loadCallJsUrl(str);
            }
        });
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitWatchFaceThemeWearInfo(final String str) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.28
            @Override // java.lang.Runnable
            public void run() {
                CustomWebView.this.loadCallJsUrl("javascript:transmitWatchFaceThemeWearAlbumInfo('" + str + Constants.RIGHT_BRACKET);
            }
        });
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitWatchFaceThemeAlbumInfo(final String str) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.29
            @Override // java.lang.Runnable
            public void run() {
                String str2 = "javascript:transmitWatchFaceThemeAlbumInfo('" + str + Constants.RIGHT_BRACKET;
                HwLog.iBetaLog(CustomWebView.e, "transmitWatchFaceThemeAlbumInfo:" + str2);
                CustomWebView.this.loadCallJsUrl(str2);
            }
        });
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitWatchFaceThemeAlbumLoadingProgress(final String str) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.2
            @Override // java.lang.Runnable
            public void run() {
                String str2 = "javascript:transmitWatchFaceThemeAlbumLoadingProgress('" + str + Constants.RIGHT_BRACKET;
                HwLog.i(CustomWebView.e, "transmitWatchFaceThemeAlbumLoadingProgress:" + str2);
                CustomWebView.this.loadCallJsUrl(str2);
            }
        });
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitWatchFaceThemeWearLoadingProgress(final String str) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.3
            @Override // java.lang.Runnable
            public void run() {
                CustomWebView.this.loadCallJsUrl("javascript:transmitWatchFaceThemeWearAlbumLoadingProgress('" + str + Constants.RIGHT_BRACKET);
            }
        });
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void showLoadingDialog(String str) {
        HwLog.i(e, "showLoadingDialog() enter.");
        if (this.r == null) {
            this.r = co.b(this.mContext);
        }
        this.r.a(str);
        this.r.c();
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void hideLoadingDialog() {
        HwLog.i(e, "hideLoadingDialog() enter.");
        co coVar = this.r;
        if (coVar == null || !coVar.isShowing()) {
            return;
        }
        this.r.dismiss();
        this.r = null;
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitWatchFaceAlbumInstallResult(final int i) {
        HwLog.i(e, "transmitWatchFaceThemeAlbumInfo:" + i);
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.4
            @Override // java.lang.Runnable
            public void run() {
                String str = "javascript:transmitWatchFaceAlbumInstallResult('" + i + Constants.RIGHT_BRACKET;
                HwLog.i(CustomWebView.e, "transmitWatchFaceAlbumInstallResult:" + str);
                CustomWebView.this.loadCallJsUrl(str);
            }
        });
    }

    public void d(final String str) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.5
            @Override // java.lang.Runnable
            public void run() {
                CustomWebView.this.loadCallJsUrl(WebViewUtils.getUrl("transmitClippedImageTypeIsNotSupport", str));
            }
        });
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitWatchFaceWearInstallResult(final int i) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.6
            @Override // java.lang.Runnable
            public void run() {
                CustomWebView.this.loadCallJsUrl("javascript:transmitWatchFaceWearAlbumInstallResult('" + i + Constants.RIGHT_BRACKET);
            }
        });
    }

    @Override // com.huawei.watchface.utils.callback.DelectUserDesignationWatchFaceCallback
    public void onDelectWatchFaceState(final String str, final String str2) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.7
            @Override // java.lang.Runnable
            public void run() {
                String url = WebViewUtils.getUrl("transmitDeleteThemeSortIdRequest", str + "', '" + str2);
                String str3 = CustomWebView.e;
                StringBuilder sb = new StringBuilder("transmitWatchFaceSortList:");
                sb.append(url);
                HwLog.i(str3, sb.toString());
                CustomWebView.this.loadCallJsUrl(url);
            }
        });
    }

    public void i() {
        HwLog.i(e, "notifyConfigChange:");
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda25
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.H();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void H() {
        HwLog.i(e, "notifyConfigChange:javascript:notifyConfigChange()");
        loadCallJsUrl("javascript:notifyConfigChange()");
    }

    class c extends WebChromeClient {
        private c() {
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            if (i >= 100) {
                HwLog.i(CustomWebView.e, "setProgressBar >= 100");
                CustomWebView.this.p.setProgress(i);
                CustomWebView.this.o.sendMessageDelayed(CustomWebView.this.o.obtainMessage(2000), 500L);
            } else {
                CustomWebView.this.p.setProgress(i);
            }
            super.onProgressChanged(webView, i);
        }
    }

    class d extends WebViewClient {
        private d() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            CustomWebView.this.i = WebViewUtils.b(str);
            CustomWebView.this.k = str;
            CustomWebView customWebView = CustomWebView.this;
            customWebView.g(customWebView.k);
            if (!WebViewUtils.a(CustomWebView.this.i)) {
                if (WebViewUtils.a(CustomWebView.this.mContext, CustomWebView.this.q, CustomWebView.this.i)) {
                    HwLog.i(CustomWebView.e, "scheme is known");
                    return true;
                }
                HwLog.i(CustomWebView.e, "scheme is unknown");
                return true;
            }
            CustomWebView.this.b(true);
            CustomWebView customWebView2 = CustomWebView.this;
            if (customWebView2.b(customWebView2.i)) {
                return false;
            }
            HwLog.d(CustomWebView.e, "shouldOverrideUrlHandler isWhiteUrl false");
            CustomWebView.this.mWebView.removeJavascriptInterface("JsInteraction");
            CustomWebView.this.mWebView.removeJavascriptInterface(Constants.HBS_SDK);
            CustomWebView.this.mWebView.removeJavascriptInterface(WatchFaceConstant.JS_INTERFACE);
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            HwLog.i(CustomWebView.e, "onPageStarted");
            FlavorConfig.safeHwLog(CustomWebView.e, "onPageStarted url:" + str);
            JsSafeUrlSystemParamManager.getInstance().a(WebViewUtils.a((WebView) CustomWebView.this.mWebView));
            at a2 = as.b().a();
            if (a2 == null || a2.e(str)) {
                CustomWebView.this.p.setVisibility(0);
                if (TextUtils.isEmpty(str)) {
                    HwLog.w(CustomWebView.e, "WebViewClientBase onPageStarted url is null.");
                    return;
                }
                CustomWebView.this.k = str;
                CustomWebView.this.i = str;
                if (!HwWatchFaceApi.getInstance(CustomWebView.this.mContext).isTestVersion() && !CustomWebView.this.k.toLowerCase(Locale.ENGLISH).startsWith("https://")) {
                    HwLog.i(CustomWebView.e, "not https protocol removeJavascriptInterface");
                    if (!CommonUtils.c(str)) {
                        HwLog.i(CustomWebView.e, "not file protocol removeJavascriptInterface");
                        CustomWebView.this.mWebView.removeJavascriptInterface("JsInteraction");
                        CustomWebView.this.mWebView.removeJavascriptInterface(Constants.HBS_SDK);
                        CustomWebView.this.mWebView.removeJavascriptInterface(WatchFaceConstant.JS_INTERFACE);
                    }
                }
                if (CustomWebView.this.mWebView.getProgress() < 100) {
                    HwLog.i(CustomWebView.e, "onPageStarted mWebView.getProgress() < 100 ");
                    CustomWebView.this.o.sendEmptyMessageDelayed(2007, OpAnalyticsConstants.H5_LOADING_DELAY);
                }
                super.onPageStarted(webView, str, bitmap);
                return;
            }
            HwLog.e(CustomWebView.e, "onPageStarted url is not in white list");
            webView.stopLoading();
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            String uri = webResourceRequest.getUrl().toString();
            if (TextUtils.isEmpty(uri)) {
                return null;
            }
            if (uri.contains("/watchface/localfile/")) {
                try {
                    String filterFilePath = CommonUtils.filterFilePath(Uri.parse(SafeString.substring(uri, uri.indexOf("/watchface/localfile/") + 21)).getPath());
                    FlavorConfig.safeHwLog(CustomWebView.e, "imgPath:" + filterFilePath);
                    JsSafeUrlSystemParamManager.getInstance();
                    if (JsSafeUrlSystemParamManager.c(filterFilePath)) {
                        return new WebResourceResponse(WebViewUtils.d(uri), "UTF-8", new FileInputStream(filterFilePath));
                    }
                    return new WebResourceResponse(WebViewUtils.d(uri), "UTF-8", null);
                } catch (FileNotFoundException unused) {
                    HwLog.i(CustomWebView.e, "WebViewClientBase shouldInterceptRequest fail");
                    return null;
                }
            }
            a(uri);
            return null;
        }

        private void a(String str) {
            if (HwWatchFaceApi.getInstance(CustomWebView.this.mContext).isTestVersion() || str.toLowerCase(Locale.ENGLISH).startsWith("https://")) {
                return;
            }
            HwLog.i(CustomWebView.e, "not https protocol removeJavascriptInterface");
            if ((CommonUtils.c(CustomWebView.this.i) && str.toLowerCase(Locale.ENGLISH).startsWith(cv.g())) || CustomWebView.this.h.equals(CustomWebView.this.i)) {
                return;
            }
            CustomWebView customWebView = CustomWebView.this;
            customWebView.h = customWebView.i;
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.d.1
                @Override // java.lang.Runnable
                public void run() {
                    CustomWebView.this.mWebView.stopLoading();
                    CustomWebView.this.mWebView.removeJavascriptInterface("JsInteraction");
                    CustomWebView.this.mWebView.removeJavascriptInterface(Constants.HBS_SDK);
                    CustomWebView.this.mWebView.removeJavascriptInterface(WatchFaceConstant.JS_INTERFACE);
                    HwLog.i(CustomWebView.e, "mUnsafeUrl removeJavascriptInterface and stopLoading!");
                    CustomWebView.this.a(CustomWebView.this.i);
                }
            });
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            if (TextUtils.isEmpty(str)) {
                HwLog.i(CustomWebView.e, "url is null!");
                return null;
            }
            if (str.contains("/watchface/localfile/")) {
                try {
                    String filterFilePath = CommonUtils.filterFilePath(Uri.parse(SafeString.substring(str, str.indexOf("/watchface/localfile/") + 21)).getPath());
                    if (filterFilePath != null) {
                        return new WebResourceResponse(WebViewUtils.d(str), "UTF-8", new FileInputStream(filterFilePath));
                    }
                } catch (FileNotFoundException unused) {
                    HwLog.i(CustomWebView.e, "WebViewClientBase shouldInterceptRequest fail");
                    return null;
                }
            }
            b(str);
            return null;
        }

        private void b(String str) {
            boolean z = (TextUtils.isEmpty(str) || str.toLowerCase(Locale.ENGLISH).startsWith("https://")) ? false : true;
            if (HwWatchFaceApi.getInstance(CustomWebView.this.mContext).isTestVersion() || !z) {
                return;
            }
            HwLog.i(CustomWebView.e, "not https protocol removeJavascriptInterface");
            if ((CommonUtils.c(CustomWebView.this.i) && str.toLowerCase(Locale.ENGLISH).startsWith(cv.g())) || CustomWebView.this.h.equals(CustomWebView.this.i)) {
                return;
            }
            CustomWebView customWebView = CustomWebView.this;
            customWebView.h = customWebView.i;
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.d.2
                @Override // java.lang.Runnable
                public void run() {
                    CustomWebView.this.mWebView.stopLoading();
                    CustomWebView.this.mWebView.removeJavascriptInterface("JsInteraction");
                    CustomWebView.this.mWebView.removeJavascriptInterface(Constants.HBS_SDK);
                    CustomWebView.this.mWebView.removeJavascriptInterface(WatchFaceConstant.JS_INTERFACE);
                    HwLog.i(CustomWebView.e, "mUnsafeUrl removeJavascriptInterface and stopLoading!");
                    CustomWebView.this.a(CustomWebView.this.i);
                }
            });
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            HwLog.i(CustomWebView.e, "onPageFinished() enter.");
            CustomWebView.this.f11111a = true;
            long currentTimeMillis = System.currentTimeMillis();
            ei eiVar = new ei();
            eiVar.a(currentTimeMillis);
            eiVar.a(str);
            super.onPageFinished(webView, str);
            FlavorConfig.safeHwLog(CustomWebView.e, "onPageFinished url:" + str);
            CustomWebView.this.p.setVisibility(8);
            JsSafeUrlSystemParamManager.getInstance().a(WebViewUtils.a((WebView) CustomWebView.this.mWebView));
            if (CustomWebView.this.o.hasMessages(2007)) {
                CustomWebView.this.o.removeMessages(2007);
            }
            c(str);
            Message obtainMessage = CustomWebView.this.o.obtainMessage(10086);
            obtainMessage.obj = str;
            if (!CustomWebView.this.x) {
                CustomWebView.this.o.sendMessage(obtainMessage);
            } else {
                obtainMessage.arg1 = 11;
                CustomWebView.this.o.sendMessageDelayed(obtainMessage, 1000L);
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            CustomWebView customWebView = CustomWebView.this;
            customWebView.g(customWebView.k);
            HwWatchFaceBtManager.getInstance(CustomWebView.this.mContext).refreshUi(CustomWebView.this.mContext);
            countDownLatch.countDown();
            try {
                if (!countDownLatch.await(2000L, TimeUnit.MILLISECONDS)) {
                    HwLog.w(CustomWebView.e, "setDarkMode time out!");
                }
            } catch (InterruptedException e) {
                HwLog.e(CustomWebView.e, "setDarkMode: InterruptedException " + HwLog.printException((Exception) e));
            }
            HwLog.i(CustomWebView.e, "onPageFinished() end.");
            long currentTimeMillis2 = System.currentTimeMillis();
            eiVar.b(currentTimeMillis2);
            eiVar.c(currentTimeMillis2 - currentTimeMillis);
            eiVar.a_();
        }

        private void c(String str) {
            if (!str.contains("health/watchFace") || CustomWebView.this.q == null) {
                return;
            }
            boolean isBluetoothConnected = CustomWebView.this.q.isBluetoothConnected();
            HwLog.i(CustomWebView.e, "onPageFinishedForWatchFace() connected: " + isBluetoothConnected);
            if (isBluetoothConnected) {
                return;
            }
            CustomWebView.this.transmitDeviceConnectState(isBluetoothConnected, DensityUtil.getStringById(R$string.wearable_device_is_not_connected_notice));
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            HwLog.i(CustomWebView.e, "onReceivedError errorCode is " + i + "description is " + str);
            CustomWebView.this.f11111a = false;
            CustomWebView.this.o.sendEmptyMessage(2001);
            webView.loadUrl(" ");
            WebViewInstrumentation.loadUrl(webView, " ");
            super.onReceivedError(webView, i, str, str2);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            HwLog.i(CustomWebView.e, "onReceivedHttpError enter.");
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (sslError == null) {
                HwLog.w(CustomWebView.e, "onReceivedSslError error is null");
                return;
            }
            HwLog.i(CustomWebView.e, "onReceivedSslError errorType is " + sslError.getPrimaryError());
            WebViewUtils.a(sslError);
            WebViewSSLCheck.checkServerCertificateNew(sslErrorHandler, sslError, CustomWebView.this.mContext);
        }

        @Override // android.webkit.WebViewClient
        public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
            HwLog.i(CustomWebView.e, "doUpdateVisitedHistory() enter.");
            if (TextUtils.isEmpty(str)) {
                HwLog.e(CustomWebView.e, "doUpdateVisitedHistory() url is null");
                return;
            }
            if (!CommonUtils.f()) {
                if (CustomWebView.this.o != null) {
                    CustomWebView.this.o.sendEmptyMessage(2001);
                } else {
                    HwLog.e(CustomWebView.e, "doUpdateVisitedHistory() mHandler is null");
                }
            }
            super.doUpdateVisitedHistory(webView, str, z);
        }
    }

    @Override // com.huawei.watchface.utils.callback.SendCurrentUrlCallback
    public String getCurrentUrl() {
        if (TextUtils.isEmpty(this.k)) {
            HwLog.i(e, "getCurrentUrl mCurrentUrl is empty");
            return "";
        }
        HwLog.i(e, "getCurrentUrl mCurrentUrl != null");
        return this.k;
    }

    @Override // com.huawei.watchface.utils.callback.SendCurrentUrlCallback
    public String getWebViewUrl() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return u();
        }
        HwLog.i(e, "getWebViewUrlNotUiThread");
        return v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String u() {
        try {
            return this.mWebView.getUrl();
        } catch (Throwable unused) {
            HwLog.e(e, "getWebViewUrlUiThread error");
            return "";
        }
    }

    private String v() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.8
                @Override // java.lang.Runnable
                public void run() {
                    String unused = CustomWebView.f = CustomWebView.this.u();
                    if (TextUtils.isEmpty(CustomWebView.f)) {
                        HwLog.e(CustomWebView.e, "getWebViewUrl is null.");
                    }
                    countDownLatch.countDown();
                }
            });
            if (!countDownLatch.await(ProfileExtendConstants.TIME_OUT, TimeUnit.MILLISECONDS)) {
                HwLog.e(e, " webView await false");
            }
        } catch (InterruptedException e2) {
            HwLog.e(e, "getWebViewUrl InterruptedException: " + HwLog.printException((Exception) e2));
        }
        return f;
    }

    @Override // com.huawei.watchface.utils.callback.CloseWebCallback
    public void onCloseWebCallback() {
        if (this.mActivity == null || this.g == 3001) {
            return;
        }
        HwLog.i(e, "onCloseWebCallback");
        this.mActivity.finish();
    }

    @Override // com.huawei.watchface.utils.callback.ToastCallback
    public void onToast(String str, String str2) {
        String str3 = e;
        HwLog.i(str3, "onToast");
        if (this.o != null) {
            HwLog.i(str3, "onToast not null");
            this.o.obtainMessage(2005, str).sendToTarget();
        }
    }

    @Override // com.huawei.watchface.utils.callback.SendServerErrorMsgCallback
    public void onSendServerErrorMsgCallback() {
        String str = e;
        HwLog.i(str, "onSendServerErrorMsgCallback");
        if (this.o != null) {
            HwLog.i(str, "onSendServerErrorMsgCallback not null");
            this.o.obtainMessage(2006).sendToTarget();
        }
    }

    @Override // com.huawei.watchface.utils.callback.SendServerErrorMsgCallback
    public void onSendWatchFaceServerErrorCallback() {
        String str = e;
        HwLog.i(str, "onSendWatchFaceServerErrorCallBack");
        if (this.o != null) {
            HwLog.i(str, "onSendWatchFaceServerErrorCallBack not null");
            this.o.obtainMessage(2021).sendToTarget();
        }
    }

    @Override // com.huawei.watchface.utils.callback.SendServerErrorMsgCallback
    public void onSendWatchFaceServerRetryCallback() {
        String str = e;
        HwLog.i(str, "onSendWatchFaceServerRetryCallBack");
        if (this.o != null) {
            HwLog.i(str, "onSendWatchFaceServerRetryCallBack not null");
            this.o.obtainMessage(2022).sendToTarget();
        }
    }

    @Override // com.huawei.watchface.utils.callback.SendNoNetMsgCallback
    public void onSendNoNetMsgCallback() {
        String str = e;
        HwLog.i(str, "onSendNoNetMsgCallBack");
        if (this.o != null) {
            HwLog.i(str, "onSendNoNetMsgCallBack not null");
            this.o.obtainMessage(2003).sendToTarget();
        }
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void callTransmitProgressJsFunction(final String str, final int i, final String str2) {
        if (ba.a(this.mContext).a() && "000000001".equals(str)) {
            if (this.o != null) {
                HwLog.i(e, "start send message");
                Message obtainMessage = this.o.obtainMessage();
                obtainMessage.what = 2025;
                obtainMessage.arg1 = i;
                this.o.sendMessage(obtainMessage);
                return;
            }
            return;
        }
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.9
            @Override // java.lang.Runnable
            public void run() {
                String url = WebViewUtils.getUrl("transmitProgress", com.huawei.watchface.d.a().k(str) + "', '" + i + "', '" + str2);
                String str3 = CustomWebView.e;
                StringBuilder sb = new StringBuilder("callTransmitProgressJsFunction:");
                sb.append(url);
                HwLog.i(str3, sb.toString());
                CustomWebView.this.loadCallJsUrl(url);
            }
        });
    }

    public void j() {
        this.mWebView.evaluateJavascript(WebViewUtils.c("isNotInstallation()"), new ValueCallback() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda29
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                CustomWebView.s((String) obj);
            }
        });
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitDownloadWatchFaceResult(final String str, final String str2, final int i) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.10
            @Override // java.lang.Runnable
            public void run() {
                String url = WebViewUtils.getUrl("transmitDownloadWatchFaceResult", str + "', '" + str2 + "', '" + i);
                String str3 = CustomWebView.e;
                StringBuilder sb = new StringBuilder("transmitDownloadWatchFaceResult:");
                sb.append(url);
                HwLog.i(str3, sb.toString());
                CustomWebView.this.loadCallJsUrl(url);
            }
        });
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitMembershipDialogStatus() {
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.11
            @Override // java.lang.Runnable
            public void run() {
                String c2 = WebViewUtils.c("transmitMembershipDialogStatus()");
                HwLog.i(CustomWebView.e, "transmitMembershipDialogStatus:" + c2);
                CustomWebView.this.loadCallJsUrl(c2);
            }
        });
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitDownloadProgressWatchFace(final String str, final int i, final String str2) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.13
            @Override // java.lang.Runnable
            public void run() {
                String url = WebViewUtils.getUrl("transmitDownloadProgressWatchFace", str + "', '" + i + "', '" + str2);
                String str3 = CustomWebView.e;
                StringBuilder sb = new StringBuilder("callDownloadProgressJsFuncion:");
                sb.append(url);
                HwLog.i(str3, sb.toString());
                CustomWebView.this.loadCallJsUrl(url);
            }
        });
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitInstallWatchFaceResult(String str, final String str2, final int i) {
        HwLog.i(e, " transmitInstallWatchFaceResult hiTopId:" + str);
        final String c2 = com.huawei.watchface.d.a().c(str);
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.14
            @Override // java.lang.Runnable
            public void run() {
                String url = WebViewUtils.getUrl("transmitInstallWatchFaceResult", c2 + "', '" + str2 + "', '" + i);
                String str3 = CustomWebView.e;
                StringBuilder sb = new StringBuilder("transmitInstallWatchFaceResult:");
                sb.append(url);
                HwLog.i(str3, sb.toString());
                CustomWebView.this.loadCallJsUrl(url);
            }
        });
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitSetDefaultWatchFaceResult(final String str, final String str2, final int i) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.15
            @Override // java.lang.Runnable
            public void run() {
                String url = WebViewUtils.getUrl("transmitSetDefaultWatchFaceResult", WatchResourcesInfo.markDownHiTopId(str) + "', '" + str2 + "', '" + i);
                String str3 = CustomWebView.e;
                StringBuilder sb = new StringBuilder("transmitSetDefaultWatchFaceResult:");
                sb.append(url);
                HwLog.i(str3, sb.toString());
                CustomWebView.this.loadCallJsUrl(url);
            }
        });
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitCancelInstallWatchFaceResult(String str, final String str2, final int i) {
        if (i == 1) {
            t.a().f(str, str2);
        }
        final String f2 = com.huawei.watchface.d.a().f(str);
        HwLog.i(e, "transmitCancelInstallWatchFaceResult before:" + str + ",version  ," + str2 + ",aod after:" + f2);
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.16
            @Override // java.lang.Runnable
            public void run() {
                String url = WebViewUtils.getUrl("transmitCancelInstallWatchFaceResult", f2 + "', '" + str2 + "', '" + i);
                String str3 = CustomWebView.e;
                StringBuilder sb = new StringBuilder("transmitCancelInstallWatchFaceResult:");
                sb.append(url);
                HwLog.i(str3, sb.toString());
                CustomWebView.this.loadCallJsUrl(url);
            }
        });
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitGetResult() {
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.17
            @Override // java.lang.Runnable
            public void run() {
                String c2 = WebViewUtils.c("transmitGetResult()");
                HwLog.i(CustomWebView.e, "transmitGetResult:" + c2);
                CustomWebView.this.loadCallJsUrl(c2);
            }
        });
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitDeleteWatchFaceResult(final String str, final String str2, final int i) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.18
            @Override // java.lang.Runnable
            public void run() {
                String d2 = com.huawei.watchface.d.a().d(str);
                HwLog.i(CustomWebView.e, "transmitDeleteWatchFaceResult  hitopid1: " + str + "," + d2);
                CustomWebView.this.loadCallJsUrl(WebViewUtils.getUrl("transmitDeleteWatchFaceResult", d2 + "', '" + str2 + "', '" + i));
            }
        });
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitWatchInfoChange(int i) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.19
            @Override // java.lang.Runnable
            public void run() {
                String c2 = WebViewUtils.c("transmitWatchInfoChange()");
                HwLog.i(CustomWebView.e, "transmitWatchInfoChange:" + c2);
                CustomWebView.this.loadCallJsUrl(c2);
            }
        });
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void callGetWatchFaceDeleteIdJsFunction() {
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.20
            @Override // java.lang.Runnable
            public void run() {
                String c2 = WebViewUtils.c("transmitDeleteWatchFaceResult()");
                HwLog.i(CustomWebView.e, "callGetWatchFaceDeleteIdJsFunction:" + c2);
                CustomWebView.this.loadCallJsUrl(c2);
            }
        });
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public Context getCustomWebViewContext() {
        return this.mActivity;
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void showToast(String str) {
        String str2 = e;
        HwLog.i(str2, "showToast");
        Handler handler = this.o;
        if (handler == null) {
            HwLog.e(str2, "showToast error: handler is null");
        } else {
            handler.obtainMessage(2005, str).sendToTarget();
        }
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitWatchFaceNames(final String str) {
        String str2 = e;
        HwLog.i(str2, "transmitWatchFaceNames");
        if (this.o == null || TextUtils.isEmpty(str)) {
            HwLog.e(str2, "showToast error: handler or names is null");
        } else {
            this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.21
                @Override // java.lang.Runnable
                public void run() {
                    String url = WebViewUtils.getUrl("transmitWatchFaceNames", str);
                    HwLog.i(CustomWebView.e, "transmitWatchFaceNames:" + url);
                    CustomWebView.this.loadCallJsUrl(url);
                }
            });
        }
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void setWatchFaceConfirmButton() {
        Handler handler = this.o;
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = 2026;
            this.o.sendMessage(obtainMessage);
        }
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void saveSuccessComeback() {
        String str = e;
        HwLog.i(str, "saveSuccessComeback() enter.");
        Handler handler = this.o;
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = Constants.UPLOAD_SUCCESS;
            this.o.sendMessage(obtainMessage);
            return;
        }
        HwLog.e(str, "saveSuccessComeback() mHandler is null.");
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitWatchFaceGoBack() {
        HwLog.i(e, "transmitWatchFaceGoBack");
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.22
            @Override // java.lang.Runnable
            public void run() {
                CustomWebView.this.loadCallJsUrl(WebViewUtils.c("transmitWatchFaceGoBack()"));
            }
        });
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void resetWatchFaceAlbumInfoStatus(int i) {
        HwLog.i(e, "resetWatchFaceAlbumInfoStatus");
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda23
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.G();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void G() {
        loadCallJsUrl(WebViewUtils.c("resetWatchFaceAlbumInfoStatus()"));
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitRefreshPullData() {
        HwLog.i(e, "transmitRefreshPullData");
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView.24
            @Override // java.lang.Runnable
            public void run() {
                CustomWebView.this.loadCallJsUrl(WebViewUtils.c("transmitRefreshPullData()"));
            }
        });
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitWatchFaceLoginResult(final String str) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.r(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r(String str) {
        String url = WebViewUtils.getUrl("transmitWatchFaceLoginResult", str);
        HwLog.i(e, "transmitWatchFaceLoginResult:" + url);
        loadCallJsUrl(url);
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitAppGoBack() {
        HwLog.i(e, "transmitAppGoBack");
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.F();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void F() {
        loadCallJsUrl(WebViewUtils.c("transmitAppGoBack()"));
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitLoadingOpen() {
        HwLog.i(e, "transmitLoadingOpen");
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda28
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.E();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E() {
        String c2 = WebViewUtils.c("transmitLoadingOpen()");
        HwLog.i(e, "transmitLoadingOpen:" + c2);
        loadCallJsUrl(c2);
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitDeviceConnectState(final boolean z, final String str) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.a(z, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(boolean z, String str) {
        String url = WebViewUtils.getUrl("transmiDeviceConnectState", z + "', '" + str);
        String str2 = e;
        StringBuilder sb = new StringBuilder("transmiDeviceConnectState");
        sb.append(url);
        HwLog.i(str2, sb.toString());
        loadCallJsUrl(url);
    }

    public int getChooseToClipResourceStyle() {
        return this.mChooseToClipResourceStyle;
    }

    public void a(int i) {
        this.mChooseToClipResourceStyle = i;
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void chooseToClip(int i, boolean z) {
        HwLog.i(e, "chooseToClip() clipType: " + i + ",h5Clip: " + z);
        if (this.mActivity == null) {
            return;
        }
        this.isH5Clip = z;
        this.mChooseToClipResourceStyle = i;
        l();
    }

    protected void l() {
        HwLog.i(e, "choosePictureToClip() enter.");
        View inflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R$layout.watchface_dialog_select_user_photo, (ViewGroup) null);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.mContext);
        builder.setNegativeButton(R$string.cancel, new View.OnClickListener() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda24
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomWebView.lambda$onClick$hianalytics1(CustomWebView.this, view);
            }
        });
        builder.setTitle(DensityUtil.getStringById(R$string.photo_album_select_upload_way)).setView(inflate);
        this.mCustomViewDialog = builder.create();
        inflate.findViewById(R$id.photo_album_take_photo).setOnClickListener(this);
        inflate.findViewById(R$id.photo_album_gallery).setOnClickListener(this);
        this.mCustomViewDialog.show();
    }

    private /* synthetic */ void a(View view) {
        h();
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public int getSoftKeyboardHeight() {
        return b(this.mContext) - a(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(String str) {
        if (this.mWebView == null) {
            return;
        }
        if (CommonUtils.isAndroid13()) {
            HwLog.i(e, "setDarkMode isAndroid13");
            return;
        }
        if (!TextUtils.isEmpty(str) && str.contains(this.s) && Build.VERSION.SDK_INT >= 29) {
            this.mWebView.getSettings().setForceDark(0);
            HwLog.i(e, "setDarkMode FORCE_DARK_OFF");
        } else {
            dc.a(this.mContext, this.mWebView);
        }
    }

    private int a(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(com.huawei.openalliance.ad.constant.Constants.NATIVE_WINDOW_SUB_DIR);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    private int b(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService(com.huawei.openalliance.ad.constant.Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class).invoke(defaultDisplay, defaultDisplay);
        } catch (ClassNotFoundException unused) {
            HwLog.i(e, "ClassNotFoundException");
        } catch (IllegalAccessException unused2) {
            HwLog.i(e, "IllegalAccessException");
        } catch (NoSuchMethodException unused3) {
            HwLog.i(e, "NoSuchMethodException");
        } catch (InvocationTargetException unused4) {
            HwLog.i(e, "InvocationTargetException");
        }
        return displayMetrics.heightPixels;
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitPayWatchFaceResult(final String str, final String str2, final String str3) {
        String str4 = e;
        HwLog.i(str4, "transmitPayWatchFaceResult");
        if (this.o == null) {
            HwLog.e(str4, "showToast error: handler or names is null");
        } else {
            this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    CustomWebView.this.b(str, str2, str3);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(String str, String str2, String str3) {
        String url = WebViewUtils.getUrl("transmitPayWatchFaceResult", str + "', '" + str2 + "', '" + str3);
        String str4 = e;
        StringBuilder sb = new StringBuilder("transmitPayWatchFaceResult:");
        sb.append(url);
        HwLog.i(str4, sb.toString());
        loadCallJsUrl(url);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R$id.photo_album_take_photo) {
            this.u = true;
            n();
        } else if (view.getId() == R$id.photo_album_gallery) {
            this.u = false;
            m();
        } else {
            HwLog.e(e, "onClick() invalid click id.");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void m() {
        HwLog.i(e, "onClick photo_album_gallery");
        String[] strArr = GALLERY_PERMISSIONS;
        if (PermissionUtils.checkPermission(strArr[0]) || CommonUtils.isAndroid14()) {
            ChoosePicUtil.b(this.mActivity, this.mChooseToClipResourceStyle);
        } else {
            Activity activity = this.mActivity;
            requestPermissionAsync(activity, strArr, new b(activity, this.mChooseToClipResourceStyle), 2);
        }
        CustomViewDialog customViewDialog = this.mCustomViewDialog;
        if (customViewDialog != null) {
            customViewDialog.dismiss();
        }
    }

    public void n() {
        HwLog.i(e, "onClick photo_album_take_photo");
        String[] strArr = CAMERA_PERMISSIONS;
        if (ArrayUtils.isEmpty(PermissionUtils.checkPermission(strArr))) {
            ChoosePicUtil.a(this.mActivity, this.mChooseToClipResourceStyle);
        } else {
            Activity activity = this.mActivity;
            requestPermissionAsync(activity, strArr, new a(activity, this.mChooseToClipResourceStyle), 1);
        }
        CustomViewDialog customViewDialog = this.mCustomViewDialog;
        if (customViewDialog != null) {
            customViewDialog.dismiss();
        }
    }

    public void o() {
        HwLog.i(e, "goToCameraOrGalley enter");
        if (this.u) {
            n();
        } else {
            m();
        }
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void cancelSearchImage(boolean z, boolean z2) {
        HwLog.i(e, "cancelSearchImage enter isGoBack :" + z2);
        if (z2 && !w()) {
            this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda35
                @Override // java.lang.Runnable
                public final void run() {
                    CustomWebView.this.D();
                }
            });
        }
        if (z) {
            o();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void D() {
        String url = WebViewUtils.getUrl("transmitGotoSearchPage", "");
        HwLog.i(e, "cancelSearchImage:" + url);
        loadCallJsUrl(url);
    }

    public void a(final String... strArr) {
        String str = e;
        HwLog.i(str, "notifyH5ApplyPermission");
        if (this.mWebView == null) {
            HwLog.e(str, "notifyH5ApplyPermission error: mWebView is null");
        } else if (ArrayUtils.isEmpty(strArr)) {
            HwLog.e(str, "notifyH5ApplyPermission error: permissions is null");
        } else {
            this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda11
                @Override // java.lang.Runnable
                public final void run() {
                    CustomWebView.this.c(strArr);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(String[] strArr) {
        String url = WebViewUtils.getUrl("transmitApplyPermissionResult", String.join("_", strArr));
        HwLog.i(e, "transmitApplyPermissionResult:" + url);
        loadCallJsUrl(url);
    }

    protected void requestPermissionAsync(Activity activity, String[] strArr, PermissionUtils.PermissonListener permissonListener, int i) {
        this.d.put(i, permissonListener);
        HwLog.i(e, "requestPermissionAsync() permissionId:" + i + ", permissons:" + strArr[0]);
        PermissionUtils.requestPermissionIfNeed(activity, strArr, i);
        b(strArr);
    }

    public void b(String[] strArr) {
        String str = e;
        HwLog.i(str, "toNotifyH5ApplyPermission enter");
        boolean z = this.mChooseToClipResourceStyle == 4;
        HashSet hashSet = new HashSet();
        if (ArrayUtils.isEmpty(strArr)) {
            HwLog.i(str, "toNotifyH5ApplyPermission permission is empty");
            return;
        }
        Map<String, String> a2 = PermissionUtils.a(z);
        for (String str2 : strArr) {
            if (!PermissionUtils.checkPermission(str2) && a2.containsKey(str2)) {
                String str3 = a2.get(str2);
                if (!TextUtils.isEmpty(str3)) {
                    hashSet.add(str3);
                }
            }
        }
        if (ArrayUtils.isEmpty(hashSet)) {
            return;
        }
        a((String[]) hashSet.toArray(new String[0]));
    }

    public void a(int i, boolean z) {
        synchronized (this) {
            HwLog.i(e, "onRequestPermissionsResult() requestCode: " + i + ", result: " + z);
            a("0");
            PermissionUtils.PermissonListener permissonListener = this.d.get(i);
            this.d.remove(i);
            a(permissonListener, z);
        }
    }

    private void a(PermissionUtils.PermissonListener permissonListener, boolean z) {
        if (permissonListener != null) {
            permissonListener.onRequested(z);
        }
    }

    public static class a implements PermissionUtils.PermissonListener {

        /* renamed from: a, reason: collision with root package name */
        private Activity f11141a;
        private int b;

        public a(Activity activity, int i) {
            this.f11141a = activity;
            this.b = i;
        }

        @Override // com.huawei.watchface.utils.PermissionUtils.PermissonListener
        public void onRequested(boolean z) {
            dp.a("android.permission.CAMERA", false, "sp_permission_file");
            if (z) {
                ChoosePicUtil.a(this.f11141a, this.b);
            } else if (Environment.sIsAarInTheme) {
                CommonUtils.showPermissionSettingGuide(this.f11141a, DensityUtil.getStringById(R$string.watch_face_camera_permission_content));
            } else {
                HwWatchFaceApi.getInstance(this.f11141a).showHealthAppSettingGuide(this.f11141a, PermissionUtil.PermissionType.CAMERA.getPermission(), new View.OnClickListener() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$a$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        CustomWebView.a.lambda$onClick$hianalytics1(CustomWebView.a.this, view);
                    }
                }, new View.OnClickListener() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$a$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        CustomWebView.a.lambda$onClick$hianalytics2(CustomWebView.a.this, view);
                    }
                });
            }
        }

        private /* synthetic */ void b(View view) {
            HwLog.d(CustomWebView.e, "positiveListener click");
            CustomWebView.notifyH5(this.f11141a);
        }

        private /* synthetic */ void a(View view) {
            HwLog.d(CustomWebView.e, "negativeListener click");
            CustomWebView.notifyH5(this.f11141a);
        }

        static void lambda$onClick$hianalytics2(a aVar, View view) {
            aVar.a(view);
            ViewClickInstrumentation.clickOnView(view);
        }

        static void lambda$onClick$hianalytics1(a aVar, View view) {
            aVar.b(view);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    protected static class b implements PermissionUtils.PermissonListener {

        /* renamed from: a, reason: collision with root package name */
        private Activity f11142a;
        private int b;

        public b(Activity activity, int i) {
            this.f11142a = activity;
            this.b = i;
        }

        @Override // com.huawei.watchface.utils.PermissionUtils.PermissonListener
        public void onRequested(boolean z) {
            dp.a("android.permission.WRITE_EXTERNAL_STORAGE", false, "sp_permission_file");
            if (z && PermissionUtils.checkPermission(CustomWebView.GALLERY_PERMISSIONS[0])) {
                ChoosePicUtil.b(this.f11142a, this.b);
            } else if (Environment.sIsAarInTheme) {
                CommonUtils.showPermissionSettingGuide(this.f11142a, DensityUtil.getStringById(R$string.watch_face_camera_permission_content));
            } else {
                HwWatchFaceApi.getInstance(this.f11142a).showHealthAppSettingGuide(this.f11142a, CommonUtils.isAndroid13() ? PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES.getPermission() : PermissionUtil.PermissionType.STORAGE.getPermission(), new View.OnClickListener() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$b$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        CustomWebView.b.lambda$onClick$hianalytics1(CustomWebView.b.this, view);
                    }
                }, new View.OnClickListener() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$b$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        CustomWebView.b.lambda$onClick$hianalytics2(CustomWebView.b.this, view);
                    }
                });
            }
        }

        private /* synthetic */ void b(View view) {
            HwLog.d(CustomWebView.e, "positiveListener click");
            CustomWebView.notifyH5(this.f11142a);
        }

        private /* synthetic */ void a(View view) {
            HwLog.d(CustomWebView.e, "negativeListener click");
            CustomWebView.notifyH5(this.f11142a);
        }

        static void lambda$onClick$hianalytics2(b bVar, View view) {
            bVar.a(view);
            ViewClickInstrumentation.clickOnView(view);
        }

        static void lambda$onClick$hianalytics1(b bVar, View view) {
            bVar.b(view);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public static void notifyH5(Activity activity) {
        if (activity == null || !(activity instanceof WebViewActivity)) {
            return;
        }
        ((WebViewActivity) activity).notifyH5ApplyPermission("0");
    }

    @Override // com.huawei.watchface.utils.callback.OnWebViewStatusCallback
    public void onWebViewStatusCallback(String str) {
        if (this.o != null) {
            this.t = str;
        }
    }

    public void e(String str) {
        String url = WebViewUtils.getUrl("transmitNetworkChange", str);
        HwLog.i(e, "notifyH5NetworkChange() callJs: " + url);
        loadCallJsUrl(url);
    }

    @Override // com.huawei.watchface.utils.callback.IH5LoadingCallback
    public void onLoadSuccess() {
        HwLog.i(e, "onLoadSuccess() enter.");
        boolean z = t.a().f11180a;
        String str = t.a().b;
        if (z) {
            t.a().f11180a = false;
            t.a().b = null;
            transmitTryOutOver("1", str, "2");
        }
    }

    @Override // com.huawei.watchface.utils.callback.MagicH5LoadingCallback
    public void onMagicLoadSuccess() {
        HwLog.i(e, "onMagicLoadSuccess() enter.");
        this.b = true;
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitWatchFaceVideoAlbumInfo(final String str) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda31
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.q(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void q(String str) {
        String str2 = "javascript:transmitWatchFaceVideoAlbumInfo('" + str + Constants.RIGHT_BRACKET;
        HwLog.iBetaLog(e, "transmitWatchFaceVideoAlbumInfo:" + str2);
        loadCallJsUrl(str2);
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitWatchFaceVideoAlbumLoadingProgress(final String str) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.p(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void p(String str) {
        String str2 = "javascript:transmitWatchFaceVideoAlbumLoadingProgress('" + str + Constants.RIGHT_BRACKET;
        HwLog.i(e, "transmitWatchFaceVideoAlbumLoadingProgress:" + str2);
        loadCallJsUrl(str2);
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitWatchFaceVideoInstallResult(final int i) {
        HwLog.i(e, "transmitWatchFaceVideoInstallResult:" + i);
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda26
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.d(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(int i) {
        String str = "javascript:transmitWatchFaceVideoInstallResult('" + i + Constants.RIGHT_BRACKET;
        HwLog.i(e, "transmitWatchFaceVideoInstallResult:" + str);
        loadCallJsUrl(str);
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitFinishPay(String str) {
        String str2 = e;
        HwLog.i(str2, "transmitFinishPay");
        SafeWebView safeWebView = this.mWebView;
        if (safeWebView == null) {
            HwLog.e(str2, "showToast error: mWebView is null");
        } else {
            safeWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    CustomWebView.this.C();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void C() {
        String url = WebViewUtils.getUrl("transmitFinishPay", "");
        HwLog.i(e, "transmitFinishPay:" + url);
        loadCallJsUrl(url);
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitTryOutOver(final String str, final String str2, final String str3) {
        String str4 = e;
        HwLog.i(str4, "transmitTryOutOver");
        SafeWebView safeWebView = this.mWebView;
        if (safeWebView == null) {
            HwLog.e(str4, "transmitTryOutOver --  mWebView is null");
        } else {
            safeWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda14
                @Override // java.lang.Runnable
                public final void run() {
                    CustomWebView.this.a(str, str2, str3);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(String str, String str2, String str3) {
        String url = WebViewUtils.getUrl("transmitTryOutOver", str + "', '" + str2 + "', '" + str3);
        String str4 = e;
        StringBuilder sb = new StringBuilder("transmitTryOutOver:");
        sb.append(url);
        HwLog.i(str4, sb.toString());
        loadCallJsUrl(url);
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitDownloadUrl(final int i, final String str) {
        String str2 = e;
        HwLog.i(str2, "transmitDownloadUrl");
        SafeWebView safeWebView = this.mWebView;
        if (safeWebView == null) {
            HwLog.e(str2, "transmitDownloadUrl --  mWebView is null");
        } else {
            safeWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda16
                @Override // java.lang.Runnable
                public final void run() {
                    CustomWebView.this.c(i, str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(int i, String str) {
        String url = WebViewUtils.getUrl("transmitDownloadUrl", i + "', '" + str);
        String str2 = e;
        StringBuilder sb = new StringBuilder("transmitDownloadUrl:");
        sb.append(url);
        HwLog.i(str2, sb.toString());
        loadCallJsUrl(url);
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitDecryptResult(final int i, final String str) {
        String str2 = e;
        HwLog.i(str2, "transmitDecryptResult");
        SafeWebView safeWebView = this.mWebView;
        if (safeWebView == null) {
            HwLog.e(str2, "transmitDecryptResult --  mWebView is null");
        } else {
            safeWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda22
                @Override // java.lang.Runnable
                public final void run() {
                    CustomWebView.this.b(i, str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(int i, String str) {
        String url = WebViewUtils.getUrl("transmitDecryptResult", i + "', '" + str);
        String str2 = e;
        StringBuilder sb = new StringBuilder("transmitDecryptResult:");
        sb.append(url);
        HwLog.i(str2, sb.toString());
        loadCallJsUrl(url);
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitWatchFaceKaleidoscopeInfo(final String str) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda38
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.o(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o(String str) {
        String str2 = "javascript:transmitWatchFaceKaleidoscopeInfo('" + str + Constants.RIGHT_BRACKET;
        HwLog.iBetaLog(e, "transmitWatchFaceKaleidoscopeInfo:" + str2);
        loadCallJsUrl(str2);
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitWatchFaceKaleidoscopeLoadingProgress(final String str) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.n(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(String str) {
        String str2 = "javascript:transmitWatchFaceKaleidoscopeLoadingProgress('" + str + Constants.RIGHT_BRACKET;
        HwLog.i(e, "transmitWatchFaceKaleidoscopeLoadingProgress:" + str2);
        loadCallJsUrl(str2);
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitWatchFaceKaleidoscopeInstallResult(final int i) {
        HwLog.i(e, "transmitWatchFaceKaleidoscopeInstallResult:" + i);
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda17
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.c(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(int i) {
        String str = "javascript:transmitWatchFaceKaleidoscopeInstallResult('" + i + Constants.RIGHT_BRACKET;
        HwLog.i(e, "transmitWatchFaceKaleidoscopeInstallResult:" + str);
        loadCallJsUrl(str);
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void resetKaleidoscopeCurrentMaxIndex() {
        Handler handler = this.o;
        if (handler != null) {
            handler.obtainMessage(2023).sendToTarget();
        }
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void reselectionWearTransmit() {
        String str = e;
        HwLog.i(str, "reselectionWearTransmit() -- enter.");
        if (this.o != null) {
            HwLog.i(str, "reselectionWearTransmit() -- mHandler is not null.");
            this.o.obtainMessage(20991).sendToTarget();
        }
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void notifyWatchfaceModeChange(int i) {
        String str = e;
        HwLog.i(str, "notifyDeviceModeChange -- enter.");
        if (this.o != null) {
            HwLog.i(str, "notifyDeviceModeChange() -- mHandler is not null.");
            this.o.obtainMessage(2024, Integer.valueOf(i)).sendToTarget();
        }
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void handleCallbackResult(final String str, int i) {
        String str2 = e;
        HwLog.i(str2, "handleCallbackResult:" + str);
        if (HwWatchFaceApi.getInstance(this.mContext).getIsTaskExecuting() && i == 9) {
            HwLog.d(str2, "getIsTaskExecuting is true");
            HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).setTaskExecuting(true);
        } else {
            this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    CustomWebView.this.m(str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(String str) {
        String str2 = "javascript:handleCallbackResult('" + str + Constants.RIGHT_BRACKET;
        HwLog.i(e, "handleCallbackResult:" + str2);
        loadCallJsUrl(str2);
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitWatchFaceAlbumApplyResult(final int i) {
        HwLog.i(e, "transmitWatchFaceAlbumApplyResult:" + i);
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.b(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(int i) {
        String str = "javascript:transmitWatchFaceAlbumApplyResult('" + i + Constants.RIGHT_BRACKET;
        HwLog.i(e, "transmitWatchFaceAlbumApplyResult:" + str);
        loadCallJsUrl(str);
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void notifyPhotoPackageName(final String str) {
        HwLog.i(e, "notifyPhotoPackageName:" + str);
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda19
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.l(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(String str) {
        String str2 = "javascript:transmitWatchFaceAlbumPackageName('" + str + Constants.RIGHT_BRACKET;
        HwLog.i(e, "notifyPhotoPackageName:" + str2);
        loadCallJsUrl(str2);
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void notifyWearAlbumPackageName(final String str) {
        HwLog.i(e, "notifyWearAlbumPackageName:" + str);
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.k(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(String str) {
        String str2 = "javascript:transmitWatchFaceWearAlbumPackageName('" + str + Constants.RIGHT_BRACKET;
        HwLog.i(e, "notifyWearAlbumPackageName:" + str2);
        loadCallJsUrl(str2);
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void notifyKaleidoscopePackageName(final String str) {
        HwLog.i(e, "notifyKaleidoscopePackageName:" + str);
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.j(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(String str) {
        String str2 = "javascript:transmitWatchFaceKaleidoscopePackageName('" + str + Constants.RIGHT_BRACKET;
        HwLog.i(e, "notifyKaleidoscopePackageName:" + str2);
        loadCallJsUrl(str2);
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void notifyVideoAlbumPackageName(final String str) {
        HwLog.i(e, "notifyVideoAlbumPackageName:" + str);
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda18
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.i(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(String str) {
        String str2 = "javascript:transmitWatchFaceVideoAlbumPackageName('" + str + Constants.RIGHT_BRACKET;
        HwLog.i(e, "notifyVideoAlbumPackageName:" + str2);
        loadCallJsUrl(str2);
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void notifyGetWatchfaceStore() {
        HwLog.i(e, "notifyGetWatchfaceStore:");
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.B();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void B() {
        HwLog.i(e, "notifyGetWatchfaceStore:javascript:transmitGetWatchfaceStore()");
        loadCallJsUrl("javascript:transmitGetWatchfaceStore()");
    }

    private boolean w() {
        if (this.mWebView != null) {
            return false;
        }
        HwLog.e(e, "mWebView is null");
        return true;
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitHealthChildStatusChange(final String str) {
        HwLog.i(e, "transmitHealthChildStatusChange:");
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda33
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.h(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(String str) {
        String str2 = "javascript:transmitHealthChildStatusChange('" + str + Constants.RIGHT_BRACKET;
        HwLog.i(e, "transmitHealthChildStatusChange:" + str2);
        loadCallJsUrl(str2);
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void notifyGetShortCutIsCreate() {
        HwLog.i(e, "notifyGetShortCutIsCreate:");
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.A();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void A() {
        HwLog.i(e, "notifyGetShortCutIsCreate:javascript:notifyGetShortCutIsCreate()");
        loadCallJsUrl("javascript:notifyGetShortCutIsCreate()");
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitUserTokenUpdate() {
        HwLog.i(e, "transmitUserTokenUpdate:");
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda34
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.z();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void z() {
        HwLog.i(e, "transmitUserTokenUpdate:javascript:transmitUserTokenUpdate()");
        loadCallJsUrl("javascript:transmitUserTokenUpdate()");
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitSearchToImgCut() {
        HwLog.i(e, "transmitSearchToImgCut() enter.");
        if (w()) {
            return;
        }
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda32
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.y();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void y() {
        HwLog.i(e, "transmitSearchToImgCut:javascript:transmitSearchToImgCut()");
        loadCallJsUrl("javascript:transmitSearchToImgCut()");
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void notifyRefreshPage(String str) {
        String str2 = e;
        HwLog.i(str2, "notifyRefreshPage -- enter.");
        if (this.o != null) {
            HwLog.i(str2, "notifyRefreshPage() -- mHandler is not null.");
            this.o.obtainMessage(2030, str).sendToTarget();
        }
    }

    public void p() {
        PPSJsBridge pPSJsBridge = this.c;
        if (pPSJsBridge != null) {
            pPSJsBridge.destroy();
        }
        SafeWebView safeWebView = this.mWebView;
        if (safeWebView != null) {
            safeWebView.removeJavascriptInterface("JsInteraction");
            this.mWebView.removeJavascriptInterface(Constants.HBS_SDK);
            this.mWebView.removeJavascriptInterface(WatchFaceConstant.JS_INTERFACE);
        }
        Handler handler = this.o;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        if (this.r != null) {
            this.r = null;
        }
    }

    public void a(final BackgroundOptionalConfig backgroundOptionalConfig) {
        if (backgroundOptionalConfig == null) {
            HwLog.i(e, "transmitClippedKaleidoscopeInfo() backgroundOptionalConfig is null");
        } else {
            HwLog.i(e, "transmitClippedKaleidoscopeInfo() enter.");
            this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda37
                @Override // java.lang.Runnable
                public final void run() {
                    CustomWebView.this.b(backgroundOptionalConfig);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(BackgroundOptionalConfig backgroundOptionalConfig) {
        loadCallJsUrl(WebViewUtils.getUrl("transmitClippedKaleidoscopeInfo", new Gson().toJson(backgroundOptionalConfig)));
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitSelectImageFailed() {
        if (w()) {
            return;
        }
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda20
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.x();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void x() {
        loadCallJsUrl("javascript:transmitSelectImageFailed()");
    }

    @Override // com.huawei.watchface.utils.callback.OperateWatchFaceCallback
    public void transmitUploadImageResult(final int i, final String str) {
        HwLog.i(e, "transmitUploadImageResult");
        if (w()) {
            return;
        }
        this.mWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.view.CustomWebView$$ExternalSyntheticLambda36
            @Override // java.lang.Runnable
            public final void run() {
                CustomWebView.this.a(i, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, String str) {
        String url = WebViewUtils.getUrl("transmitUploadImageResult", i + "', '" + str);
        String str2 = e;
        StringBuilder sb = new StringBuilder("transmitUploadImageResult:");
        sb.append(url);
        HwLog.i(str2, sb.toString());
        loadCallJsUrl(url);
    }

    static void lambda$onClick$hianalytics1(CustomWebView customWebView, View view) {
        customWebView.a(view);
        ViewClickInstrumentation.clickOnView(view);
    }
}
