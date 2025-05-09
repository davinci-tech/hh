package com.huawei.watchface.mvp.model.webview;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import androidx.core.view.MotionEventCompat;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hms.hihealth.data.DeviceInfo;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hms.support.api.entity.pay.PayReq;
import com.huawei.hms.support.api.pay.PayResultInfo;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.operation.h5pro.jsmodules.device.DevicePairUtils;
import com.huawei.operation.utils.Constants;
import com.huawei.secure.android.common.encrypt.hash.HMACSHA256;
import com.huawei.secure.android.common.util.EncodeUtil;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.secure.android.common.webview.SafeWebView;
import com.huawei.watchface.R$color;
import com.huawei.watchface.R$string;
import com.huawei.watchface.a;
import com.huawei.watchface.ab;
import com.huawei.watchface.ai;
import com.huawei.watchface.ap;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.WebViewActivity;
import com.huawei.watchface.as;
import com.huawei.watchface.at;
import com.huawei.watchface.bc;
import com.huawei.watchface.bd;
import com.huawei.watchface.bf;
import com.huawei.watchface.bl;
import com.huawei.watchface.cu;
import com.huawei.watchface.dp;
import com.huawei.watchface.ds;
import com.huawei.watchface.dx;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.eo;
import com.huawei.watchface.fa;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.mvp.model.datatype.GetmarkeHitopIdBean;
import com.huawei.watchface.mvp.model.datatype.InstallWatchFaceBean;
import com.huawei.watchface.mvp.model.datatype.MarketH5Bean;
import com.huawei.watchface.mvp.model.datatype.querysubinfodetail.QuerySubInfoDetailResp;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.helper.JsSafeUrlSystemParamManager;
import com.huawei.watchface.mvp.model.helper.systemparam.NewSystemParamManager;
import com.huawei.watchface.mvp.model.pay.JsPayResult;
import com.huawei.watchface.mvp.model.pay.MagicJsPayResult;
import com.huawei.watchface.n;
import com.huawei.watchface.s;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.DensityUtil;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.MobileInfoHelper;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.callback.MagicH5LoadingCallback;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class JsInterfaceMarketing {
    public static final String H5_IMAGE_NAME = "h5_image.jpg";
    private static final int LOGIN_TYPE_DO_NOTHING = 0;
    private static final int LOGIN_TYPE_GOTO_DEST = 3;
    private static final int LOGIN_TYPE_GO_HOME = 1;
    private static final int LOGIN_TYPE_REFRESH_CURRENT = 2;
    public static final int RESULT_CODE_GET_IMAGE_CONTENT = 1001;
    private static final String TAG = "JsInterfaceMarketing";
    private static final int TYPE_GET_PARAMS = 1;
    public static final int UNKNOWN_EMUI = 0;
    private static final String WQHD_FOLD_SCREEN = "2480*2200";
    private static final String X_CLIENTTRACEID = MobileInfoHelper.generateUUID();
    private String finalObjJumpUrl = "";
    protected Context mContext;
    private String mDestUrl;
    private MagicH5LoadingCallback mLoadingCallback;
    private int mLoginType;
    private String mResult;
    private SafeWebView mWebview;

    public JsInterfaceMarketing(Context context) {
        this.mContext = context;
        getSupportOnlineServiceState();
    }

    public void setWebView(SafeWebView safeWebView) {
        this.mWebview = safeWebView;
    }

    public void setMagicH5LoadingCallback(MagicH5LoadingCallback magicH5LoadingCallback) {
        this.mLoadingCallback = magicH5LoadingCallback;
    }

    private void getSupportOnlineServiceState() {
        as b = as.b();
        if (b.a() == null) {
            b.c();
        }
    }

    @JavascriptInterface
    public void pay(String str, final String str2) {
        if (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "pay")) {
            final JsPayResult jsPayResult = new JsPayResult();
            if (TextUtils.isEmpty(str)) {
                HwLog.i(TAG, "pay payReqJson is null");
                jsPayResult.setResultCode(-1);
                callH5PayResult(jsPayResult, str2);
                return;
            }
            PayReq payReq = (PayReq) dx.a().a(str, PayReq.class);
            if (payReq == null) {
                HwLog.i(TAG, "pay payReq is null");
                jsPayResult.setResultCode(-1);
                callH5PayResult(jsPayResult, str2);
            } else {
                payReq.sdkChannel = 0;
                payReq.serviceCatalog = "X4";
                bd.a().a(payReq, new bc() { // from class: com.huawei.watchface.mvp.model.webview.JsInterfaceMarketing.1
                    @Override // com.huawei.watchface.bc
                    public void a(int i, PayResultInfo payResultInfo) {
                        HwLog.i(JsInterfaceMarketing.TAG, "pay onResult retCode: " + i);
                        jsPayResult.setPayResultInfo(payResultInfo);
                        jsPayResult.setResultCode(i);
                        JsInterfaceMarketing.this.callH5PayResult(jsPayResult, str2);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callH5PayResult(JsPayResult jsPayResult, String str) {
        HwLog.i(TAG, "callH5PayResult jsCallMethod: " + str);
        try {
            if (this.mWebview == null || TextUtils.isEmpty(str)) {
                return;
            }
            callH5Js(str, dx.a().a(jsPayResult));
        } catch (Exception e) {
            HwLog.i(TAG, "callH5PayResult Exception: " + HwLog.printException(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callH5PayResult(MagicJsPayResult magicJsPayResult, String str) {
        HwLog.i(TAG, "callH5PayResult jsCallMethod: " + str);
        try {
            if (this.mWebview == null || TextUtils.isEmpty(str)) {
                return;
            }
            callH5Js(str, dx.a().a(magicJsPayResult));
        } catch (Exception e) {
            HwLog.i(TAG, "callH5PayResult Exception: " + HwLog.printException(e));
        }
    }

    private void callH5Js(String str, String str2) {
        String encodeForJavaScript = EncodeUtil.encodeForJavaScript(str);
        String str3 = TAG;
        HwLog.i(str3, "callH5PayResult encodeMethod: " + encodeForJavaScript);
        final String str4 = Constants.JAVA_SCRIPT + encodeForJavaScript + Constants.LEFT_BRACKET_ONLY + str2 + Constants.RIGHT_BRACKET_ONLY;
        FlavorConfig.safeHwLog(str3, str4);
        this.mWebview.post(new Runnable() { // from class: com.huawei.watchface.mvp.model.webview.JsInterfaceMarketing$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                JsInterfaceMarketing.this.m916xb19aeac9(str4);
            }
        });
    }

    /* renamed from: lambda$callH5Js$1$com-huawei-watchface-mvp-model-webview-JsInterfaceMarketing, reason: not valid java name */
    /* synthetic */ void m916xb19aeac9(String str) {
        SafeWebView safeWebView = this.mWebview;
        if (safeWebView != null) {
            safeWebView.evaluateJavascript(str, new ValueCallback() { // from class: com.huawei.watchface.mvp.model.webview.JsInterfaceMarketing$$ExternalSyntheticLambda1
                @Override // android.webkit.ValueCallback
                public final void onReceiveValue(Object obj) {
                    HwLog.i(JsInterfaceMarketing.TAG, "callH5PayResult onReceiveValue value: " + ((String) obj));
                }
            });
        }
    }

    @JavascriptInterface
    public String getParams() {
        String str = TAG;
        HwLog.i(str, "getParams: enter");
        if (!JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "getParams")) {
            HwLog.i(str, "getParams() !isInSensitiveURLWhiteList.");
            return "";
        }
        return getParams(1, "");
    }

    private String getParams(int i, String str) {
        if (i != 1) {
            return "";
        }
        createResult();
        FlavorConfig.safeHwLog(TAG, "mResult:" + this.mResult);
        return this.mResult;
    }

    @JavascriptInterface
    public void checkLogin(int i, String str, String str2) {
        String str3 = TAG;
        HwLog.i(str3, "checkLogin() loginType: " + i);
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.i(str3, "checkLogin() !isInURLWhiteList.");
            return;
        }
        if (i < 0 || i > 3) {
            HwLog.w(str3, "checkLogin() wrong type");
            return;
        }
        this.mLoginType = i;
        this.mDestUrl = str;
        if (!HwWatchFaceApi.getInstance(this.mContext).isLogin() || !HwWatchFaceApi.getInstance(this.mContext).isLoginParamSuitable()) {
            HwLog.i(str3, "checkLogin() not Login");
            InstallWatchFaceBean installWatchFaceBean = new InstallWatchFaceBean();
            installWatchFaceBean.setActionUrl(this.mDestUrl);
            ap.a(this.mContext).a(3, installWatchFaceBean);
            return;
        }
        HwLog.i(str3, "checkLogin() has Login");
        loadUrl();
    }

    @JavascriptInterface
    public String getWatchFaceIndexUrl() {
        return !JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview) ? "" : HwWatchFaceApi.getInstance(this.mContext).getWatchFaceUrl(true);
    }

    @JavascriptInterface
    public void showBusyNet() {
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            ds.a(R$string.server_busy);
        }
    }

    @JavascriptInterface
    public void showNoNet() {
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            ds.a(R$string.network_is_error);
        }
    }

    @JavascriptInterface
    public String isNetWork() {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return "";
        }
        return CommonUtils.f() + "";
    }

    @JavascriptInterface
    public String isWifiNet() {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return "";
        }
        return CommonUtils.k(this.mContext) + "";
    }

    @JavascriptInterface
    public void showH5Notice(String str) {
        HwLog.i(TAG, "showH5Notice: enter ");
        if (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "showH5Notice")) {
            ds.a(str);
        }
    }

    @JavascriptInterface
    public boolean isRoot() {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            return false;
        }
        boolean F = CommonUtils.F();
        HwLog.i(TAG, "isRoot : " + F);
        return F;
    }

    @JavascriptInterface
    public void hwLog(String str, String str2, String str3) {
        char c;
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            if (TextUtils.isEmpty(str)) {
                HwLog.w(TAG, "logger: tag == null ");
                return;
            }
            if (TextUtils.isEmpty(str3)) {
                HwLog.dBetaLog(str, str2);
                return;
            }
            int hashCode = str3.hashCode();
            if (hashCode == 100) {
                if (str3.equals(FitRunPlayAudio.PLAY_TYPE_D)) {
                    c = 3;
                }
                c = 65535;
            } else if (hashCode == 101) {
                if (str3.equals("e")) {
                    c = 2;
                }
                c = 65535;
            } else if (hashCode != 105) {
                if (hashCode == 119 && str3.equals("w")) {
                    c = 1;
                }
                c = 65535;
            } else {
                if (str3.equals("i")) {
                    c = 0;
                }
                c = 65535;
            }
            if (c == 0) {
                HwLog.iBetaLog(str, str2);
                return;
            }
            if (c == 1) {
                HwLog.wBetaLog(str, str2);
            } else if (c == 2) {
                HwLog.eBetaLog(str, str2);
            } else {
                HwLog.dBetaLog(str, str2);
            }
        }
    }

    @JavascriptInterface
    public void hwReleaseLog(String str, String str2, String str3) {
        char c;
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            if (TextUtils.isEmpty(str)) {
                HwLog.w(TAG, "logger: tag == null ");
                return;
            }
            if (TextUtils.isEmpty(str3)) {
                HwLog.d(str, str2);
                return;
            }
            int hashCode = str3.hashCode();
            if (hashCode == 100) {
                if (str3.equals(FitRunPlayAudio.PLAY_TYPE_D)) {
                    c = 3;
                }
                c = 65535;
            } else if (hashCode == 101) {
                if (str3.equals("e")) {
                    c = 2;
                }
                c = 65535;
            } else if (hashCode != 105) {
                if (hashCode == 119 && str3.equals("w")) {
                    c = 1;
                }
                c = 65535;
            } else {
                if (str3.equals("i")) {
                    c = 0;
                }
                c = 65535;
            }
            if (c == 0) {
                HwLog.i(str, str2);
                return;
            }
            if (c == 1) {
                HwLog.w(str, str2);
            } else if (c == 2) {
                HwLog.e(str, str2);
            } else {
                HwLog.d(str, str2);
            }
        }
    }

    @JavascriptInterface
    public void addActionBarBackClickListener(String str) {
        if (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "addActionBarBackClickListener")) {
            goBackOrJumpUrl(str, false);
        }
    }

    private void goJumpUrl(final String str) {
        BackgroundTaskUtils.postInMainThread(new Runnable() { // from class: com.huawei.watchface.mvp.model.webview.JsInterfaceMarketing$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                JsInterfaceMarketing.this.m918xd6232315(str);
            }
        });
    }

    /* renamed from: lambda$goJumpUrl$2$com-huawei-watchface-mvp-model-webview-JsInterfaceMarketing, reason: not valid java name */
    /* synthetic */ void m918xd6232315(String str) {
        checkWhiteListAndJumpUrl(str, true);
    }

    private void goBackOrJumpUrl(String str, final boolean z) {
        if (TextUtils.isEmpty(this.finalObjJumpUrl)) {
            this.finalObjJumpUrl = str;
        } else if (TextUtils.equals(this.finalObjJumpUrl, str)) {
            this.finalObjJumpUrl = "";
        } else {
            this.finalObjJumpUrl = str;
        }
        BackgroundTaskUtils.postInMainThread(new Runnable() { // from class: com.huawei.watchface.mvp.model.webview.JsInterfaceMarketing$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                JsInterfaceMarketing.this.m917x6ab71c4c(z);
            }
        });
    }

    /* renamed from: lambda$goBackOrJumpUrl$3$com-huawei-watchface-mvp-model-webview-JsInterfaceMarketing, reason: not valid java name */
    /* synthetic */ void m917x6ab71c4c(boolean z) {
        checkWhiteListAndJumpUrl(this.finalObjJumpUrl, z);
    }

    private void checkWhiteListAndJumpUrl(String str, boolean z) {
        if (this.mWebview != null) {
            if (TextUtils.isEmpty(str)) {
                if (this.mWebview.canGoBack()) {
                    this.mWebview.goBack();
                    return;
                }
                if (z) {
                    HwLog.i(TAG, "load watchface home page");
                    at a2 = as.b().a();
                    String watchFaceUrl = HwWatchFaceApi.getInstance(this.mContext).getWatchFaceUrl(true);
                    if (a2 == null || !a2.e(watchFaceUrl)) {
                        return;
                    }
                    SafeWebView safeWebView = this.mWebview;
                    safeWebView.loadUrl(watchFaceUrl);
                    WebViewInstrumentation.loadUrl(safeWebView, watchFaceUrl);
                    return;
                }
                HwLog.i(TAG, "H5-back-Url = null and mWebview.canGoBack() is false");
                Context context = this.mContext;
                if (context == null || !(context instanceof WebViewActivity)) {
                    return;
                }
                ((WebViewActivity) context).finish();
                return;
            }
            at a3 = as.b().a();
            if (a3 != null && a3.e(str)) {
                SafeWebView safeWebView2 = this.mWebview;
                safeWebView2.loadUrl(str);
                WebViewInstrumentation.loadUrl(safeWebView2, str);
                return;
            }
            this.mWebview.goBack();
        }
    }

    private void createResult() {
        Context context;
        QuerySubInfoDetailResp b;
        JSONObject jSONObject = new JSONObject();
        try {
            context = this.mContext;
        } catch (JSONException e) {
            HwLog.e(TAG, "JSONException" + HwLog.printException((Exception) e));
        }
        if (context == null) {
            HwLog.i(TAG, "mContext is null");
            return;
        }
        String packageName = context.getPackageName();
        jSONObject.put("appPackageName", packageName);
        try {
            PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(packageName, 16384);
            jSONObject.put("appVersionCode", packageInfo.versionCode);
            jSONObject.put("appVersionName", packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e2) {
            HwLog.e(TAG, HwLog.printException((Exception) e2));
        } catch (RuntimeException e3) {
            HwLog.e(TAG, "runtimeException " + HwLog.printException((Exception) e3));
        }
        int[] n = CommonUtils.n();
        String str = n[0] + "*" + n[1];
        if (cu.e()) {
            str = WQHD_FOLD_SCREEN;
        }
        jSONObject.put("screen", str);
        jSONObject.put("statusbarHeight", buildHeightJson(CommonUtils.h(this.mContext)));
        jSONObject.put("toolbarHeight", buildHeightJson(CommonUtils.i(this.mContext)));
        jSONObject.put("themeColor", getThemeColor());
        jSONObject.put("defaultPadding", getDefaultPaddingProportion());
        jSONObject.put("appLayout", "1".equals(CommonUtils.o()) ? "Pad" : DeviceInfo.STR_TYPE_PHONE);
        jSONObject.put("os", Build.VERSION.RELEASE);
        jSONObject.put("themeVersion", "");
        jSONObject.put("isoCode", HwWatchFaceApi.getInstance(this.mContext).getCommonCountryCode());
        try {
            jSONObject.put("rom", CommonUtils.l());
            jSONObject.put("emuiVersionCode", cu.b());
            jSONObject.put("emuiVersionName", cu.c());
            jSONObject.put("buildNumeber", MobileInfoHelper.getBuildNumber());
            String generateDeviceIDWithSeparator = MobileInfoHelper.generateDeviceIDWithSeparator();
            if (!TextUtils.isEmpty(generateDeviceIDWithSeparator)) {
                if (generateDeviceIDWithSeparator.contains(":")) {
                    jSONObject.put("deviceid", SafeString.substring(generateDeviceIDWithSeparator, 0, generateDeviceIDWithSeparator.indexOf(":")));
                    jSONObject.put("deviceId", SafeString.substring(generateDeviceIDWithSeparator, 0, generateDeviceIDWithSeparator.indexOf(":")));
                }
                jSONObject.put("idType", SafeString.substring(generateDeviceIDWithSeparator, generateDeviceIDWithSeparator.indexOf("=") + 1));
            }
        } catch (Exception e4) {
            HwLog.e(TAG, "getParams error ==" + HwLog.printException(e4));
        }
        jSONObject.put("nickName", HwWatchFaceApi.getInstance(this.mContext).getNickName());
        jSONObject.put("hc", HwWatchFaceApi.getInstance(this.mContext).getCommonCountryCode());
        jSONObject.put("channel", 35001111);
        Locale locale = this.mContext.getResources().getConfiguration().locale;
        if (locale != null) {
            jSONObject.put("lang", locale.getLanguage() + com.huawei.openalliance.ad.constant.Constants.LINK + locale.getCountry());
        }
        jSONObject.put("extParam", "");
        jSONObject.put("userStatus", HwWatchFaceApi.getInstance(this.mContext).isLogin());
        jSONObject.put("deviceModel", Build.MODEL);
        if (WatchFaceHttpUtil.a() != null) {
            jSONObject.put(HwPayConstant.KEY_SIGN, WatchFaceHttpUtil.a().getSign());
        }
        jSONObject.put(JsbMapKeyNames.H5_BRAND, Build.BRAND);
        jSONObject.put("deviceDigest", getDeviceDigest());
        jSONObject.put("UserToken", HwWatchFaceApi.getInstance(this.mContext).getToken());
        jSONObject.put(JsbMapKeyNames.H5_USER_ID, HwWatchFaceApi.getInstance(this.mContext).getUserId());
        jSONObject.put(WatchFaceConstant.X_CLIENTTRACEID, X_CLIENTTRACEID);
        jSONObject.put("userHeadUrl", HwWatchFaceApi.getInstance(this.mContext).getHeadUrl());
        jSONObject.put("isAppMarketVersion", "1");
        String[] h = n.a(this.mContext).h();
        jSONObject.put("sessionId", ArrayUtils.a(h, 0));
        jSONObject.put("sessionType", ArrayUtils.a(h, 1));
        jSONObject.put("deviceType", HwWatchFaceApi.getInstance(this.mContext).getDeviceType());
        jSONObject.put(JsbMapKeyNames.H5_NPA, HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getNonPersonalizedAdSwitch(JsbMapKeyNames.H5_NPA));
        jSONObject.put(JsbMapKeyNames.HW_DSP_NPA, HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getNonPersonalizedAdSwitch(JsbMapKeyNames.HW_DSP_NPA));
        jSONObject.put(JsbMapKeyNames.THIRD_DSP_NPA, HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getNonPersonalizedAdSwitch(JsbMapKeyNames.THIRD_DSP_NPA));
        jSONObject.put("aarAbilityVersion", "1019100");
        jSONObject.put(DevicePairUtils.DEVICE_CONNECT_STATE, HwWatchFaceApi.getInstance(this.mContext).getDeviceConnectState());
        jSONObject.put("connectedEquipment", HwWatchFaceApi.getInstance(this.mContext).getConnectedEquipment());
        ab a2 = ab.a();
        if (a2 != null && (b = a2.b()) != null) {
            jSONObject.put("memberStatus", b.getMemberStatus() != null ? b.getMemberStatus() : "0");
            if (b.getSubInfo() != null) {
                jSONObject.put("memberExpireDate", b.getSubInfo().getValidDate());
            }
        }
        MagicH5LoadingCallback magicH5LoadingCallback = this.mLoadingCallback;
        if (magicH5LoadingCallback != null) {
            magicH5LoadingCallback.onMagicLoadSuccess();
        }
        this.mResult = jSONObject.toString();
    }

    private String buildHeightJson(int i) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("dp", Integer.valueOf(DensityUtil.a(i)));
        linkedHashMap.put("pixel", Integer.valueOf(i));
        return dx.a().a(linkedHashMap);
    }

    private void loadUrl() {
        SafeWebView safeWebView;
        String str = TAG;
        HwLog.i(str, "loadUrl type:" + this.mLoginType);
        int i = this.mLoginType;
        if (i == 0) {
            HwLog.i(str, "Do nothing.");
            return;
        }
        if (i == 1) {
            HwLog.i(str, "Go home.");
            SafeWebView safeWebView2 = this.mWebview;
            if (safeWebView2 == null) {
                return;
            }
            safeWebView2.post(new Runnable() { // from class: com.huawei.watchface.mvp.model.webview.JsInterfaceMarketing.2
                @Override // java.lang.Runnable
                public void run() {
                    JsInterfaceMarketing.this.mWebview.clearHistory();
                }
            });
            return;
        }
        if (i == 2) {
            HwLog.i(str, "Refresh current.");
            SafeWebView safeWebView3 = this.mWebview;
            if (safeWebView3 == null) {
                return;
            }
            safeWebView3.post(new Runnable() { // from class: com.huawei.watchface.mvp.model.webview.JsInterfaceMarketing.3
                @Override // java.lang.Runnable
                public void run() {
                    JsInterfaceMarketing.this.mWebview.reload();
                }
            });
            return;
        }
        if (i != 3) {
            return;
        }
        HwLog.i(str, "Go to new page.");
        if (TextUtils.isEmpty(this.mDestUrl) || (safeWebView = this.mWebview) == null) {
            return;
        }
        safeWebView.post(new Runnable() { // from class: com.huawei.watchface.mvp.model.webview.JsInterfaceMarketing.4
            @Override // java.lang.Runnable
            public void run() {
                JsInterfaceMarketing jsInterfaceMarketing = JsInterfaceMarketing.this;
                jsInterfaceMarketing.loadUrlAfterWhiteList(jsInterfaceMarketing.mDestUrl);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadUrlAfterWhiteList(String str) {
        at a2 = as.b().a();
        if (a2 == null || !a2.e(str)) {
            return;
        }
        SafeWebView safeWebView = this.mWebview;
        safeWebView.loadUrl(str);
        WebViewInstrumentation.loadUrl(safeWebView, str);
    }

    public static String getDeviceDigest() {
        String generateDeviceIDWithSeparator = MobileInfoHelper.generateDeviceIDWithSeparator();
        String substring = SafeString.substring(generateDeviceIDWithSeparator, 0, generateDeviceIDWithSeparator.indexOf(":"));
        String a2 = ai.a(DensityUtil.getStringById(CommonUtils.z() ? R$string.deviceKey : R$string.deviceKeyBelowAndroidO));
        String generateDeviceIDWithSeparator2 = MobileInfoHelper.generateDeviceIDWithSeparator();
        return SafeString.substring(generateDeviceIDWithSeparator2, generateDeviceIDWithSeparator2.indexOf("=") + 1) + "_" + CommonUtils.b(HMACSHA256.hmacEncrypt(CommonUtils.e(substring), CommonUtils.d(a2)));
    }

    private String getThemeColor() {
        int c = DensityUtil.c(R$color.emui_accent);
        String str = "#" + String.format(Locale.ROOT, "%06x", Integer.valueOf((c & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (16711680 & c) | (c & 255)));
        HwLog.i(TAG, "getThemeColor : " + str);
        return str;
    }

    private String getDefaultPaddingProportion() {
        float p = (CommonUtils.p() * 1.0f) / CommonUtils.j(Environment.getApplicationContext());
        HwLog.i(TAG, "getDefaultPaddingProportion : " + p);
        return Float.toString(p);
    }

    @JavascriptInterface
    public void startAlbum() {
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            Context context = this.mContext;
            if (context instanceof WebViewActivity) {
                CommonUtils.a((Activity) context, 1001);
            }
        }
    }

    @JavascriptInterface
    public String getSystemInstallAppStatus(String str) {
        String str2 = TAG;
        HwLog.i(str2, "getSystemInstallAppStatus: enter ");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview) || TextUtils.isEmpty(str)) {
            return "0";
        }
        boolean a2 = a.a(str);
        HwLog.i(str2, "installHiSkyToneStatus:" + a2);
        return a2 ? "1" : "0";
    }

    @JavascriptInterface
    public void getApplicationInfo(String str) {
        HwLog.i(TAG, "getApplicationInfo enter");
        getApplicationInfo(str, 9);
    }

    @JavascriptInterface
    public void getApplicationInfo(String str, int i) {
        HwLog.i(TAG, "getApplicationInfo: enter ");
        if (JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "getApplicationInfo")) {
            fa.a((Activity) this.mContext, this.mWebview).a(str, i);
        }
    }

    @JavascriptInterface
    public void bireport(String str, String str2) {
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            LinkedHashMap<String, String> buildBiReportInfo = buildBiReportInfo(str2);
            if (buildBiReportInfo != null) {
                eo.a().a(1, str, buildBiReportInfo);
            } else {
                HwLog.w(TAG, "bireport() - Bi Info is null.");
            }
        }
    }

    private LinkedHashMap<String, String> buildBiReportInfo(String str) {
        try {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                linkedHashMap.put(next, jSONObject.getString(next));
            }
            return linkedHashMap;
        } catch (JSONException unused) {
            HwLog.e(TAG, "buildBiReportInfo() Build Bi Report Info - JSONException");
            return null;
        }
    }

    @JavascriptInterface
    public void downloadApp(String str, String str2) {
        String str3 = TAG;
        HwLog.i(str3, "downloadApp() enter, campId:" + str2);
        if (!JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "downloadApp_1")) {
            HwLog.e(str3, "downloadApp() !isInSensitiveURLWhiteList1.");
        } else {
            fa.a((Activity) this.mContext, this.mWebview).a(str, false);
            dp.c("campId", str2);
        }
    }

    @JavascriptInterface
    public void triggerClick(String str, String str2, String str3) {
        HwLog.i(TAG, "triggerClick enter , campId:" + str2);
        if (JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            fa a2 = fa.a((Activity) this.mContext, this.mWebview);
            if (a2 != null) {
                a2.d(str);
            }
            dp.c("campId", str2);
            dp.c("operator", str3);
        }
    }

    @JavascriptInterface
    public void downloadApp(String str, String str2, String str3) {
        String str4 = TAG;
        HwLog.i(str4, "downloadApp() enter , campId:" + str2);
        if (!JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "downloadApp_2")) {
            HwLog.e(str4, "downloadApp() !isInSensitiveURLWhiteList2.");
            return;
        }
        fa.a((Activity) this.mContext, this.mWebview).a(str, false);
        dp.c("campId", str2);
        dp.c("operator", str3);
    }

    @JavascriptInterface
    public void cancelDownloadApp(String str, String str2, String str3) {
        String str4 = TAG;
        HwLog.i(str4, "cancelDownloadApp() enter ");
        if (!JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "downloadApp_2")) {
            HwLog.e(str4, "cancelDownloadApp() !isInSensitiveURLWhiteList2.");
        } else {
            fa.a((Activity) this.mContext, this.mWebview).a(str, true);
        }
    }

    @JavascriptInterface
    public void openDownloadApp(String str, String str2, String str3) {
        String str4 = TAG;
        HwLog.i(str4, "openDownloadApp() enter... ");
        if (!JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "openDownloadApp_1")) {
            HwLog.e(str4, "openDownloadApp() !isInSensitiveURLWhiteList1.");
        } else {
            fa.a((Activity) this.mContext, this.mWebview).a(str, str3, str2, (String) null);
        }
    }

    @JavascriptInterface
    public void openDownloadApp(String str, String str2, String str3, String str4) {
        String str5 = TAG;
        HwLog.i(str5, "openDownloadApp() is operator: " + str4);
        if (!JsSafeUrlSystemParamManager.getInstance().isInSensitiveURLWhiteList(this.mWebview, "openDownloadApp_2")) {
            HwLog.e(str5, "openDownloadApp() !isInSensitiveURLWhiteList2.");
        } else {
            fa.a((Activity) this.mContext, this.mWebview).a(str, str3, str2, str4);
        }
    }

    @JavascriptInterface
    public void recordShowStartEvent(String str) {
        String str2 = TAG;
        HwLog.i(str2, "recordShowStartEvent: enter");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.e(str2, "recordShowStartEvent() !isInURLWhiteList.");
        } else {
            fa.a((Activity) this.mContext, this.mWebview).b(str);
        }
    }

    @JavascriptInterface
    public void recordImpressionEvent(String str) {
        String str2 = TAG;
        HwLog.i(str2, "recordImpressionEvent: enter ");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.e(str2, "recordImpressionEvent() !isInURLWhiteList.");
        } else {
            fa.a((Activity) this.mContext, this.mWebview).c(str);
        }
    }

    @JavascriptInterface
    public String getNetworkStatus() {
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.e(TAG, "getNetworkStatus() !isInURLWhiteList.");
            return "";
        }
        Context context = this.mContext;
        return context instanceof WebViewActivity ? ((WebViewActivity) context).getNetworkInfo() : "";
    }

    @JavascriptInterface
    public void exitMarketPage() {
        String str = TAG;
        HwLog.i(str, "exitMarketPage: enter");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.e(str, "getNetworkStatus() !isInURLWhiteList.");
        } else {
            fa.a();
        }
    }

    @JavascriptInterface
    public void payVip(String str) {
        String str2 = TAG;
        HwLog.i(str2, "payVip  enter:" + str);
        final MagicJsPayResult magicJsPayResult = new MagicJsPayResult();
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.e(str2, "payVip() !isInURLWhiteList.");
            magicJsPayResult.setResultCode(-1);
            callH5PayResult(magicJsPayResult, "transmitPayResult");
            return;
        }
        try {
            MarketH5Bean marketH5Bean = (MarketH5Bean) GsonHelper.fromJson(str, MarketH5Bean.class);
            if (marketH5Bean == null) {
                HwLog.e(str2, "payVip MarketH5Bean is null");
                magicJsPayResult.setResultCode(-1);
                callH5PayResult(magicJsPayResult, "transmitPayResult");
                return;
            }
            s.a(this.mContext).a(marketH5Bean, magicJsPayResult, new bf() { // from class: com.huawei.watchface.mvp.model.webview.JsInterfaceMarketing.5
                @Override // com.huawei.watchface.bf
                public void onResult(int i, PayResultInfo payResultInfo) {
                    HwLog.i(JsInterfaceMarketing.TAG, "payVip onResult resultCode:" + i);
                    if (i != 0) {
                        s.a(Environment.getApplicationContext()).a();
                    } else {
                        s.a(JsInterfaceMarketing.this.mContext).b(JsInterfaceMarketing.this.mContext);
                    }
                    if (payResultInfo == null) {
                        magicJsPayResult.setResultCode(i);
                    } else {
                        magicJsPayResult.copyInfo(payResultInfo);
                    }
                    JsInterfaceMarketing.this.callH5PayResult(magicJsPayResult, "transmitPayResult");
                }
            });
        } catch (Exception e) {
            HwLog.e(TAG, "payVip error:" + HwLog.printException(e));
            magicJsPayResult.setResultCode(-1);
            callH5PayResult(magicJsPayResult, "transmitPayResult");
        }
    }

    @JavascriptInterface
    public void jumpByParam(String str) {
        String str2 = TAG;
        HwLog.i(str2, "jumpByParam enter:" + str);
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.e(str2, "jumpByParam() !isInURLWhiteList.");
            return;
        }
        try {
            final MarketH5Bean marketH5Bean = (MarketH5Bean) GsonHelper.fromJson(str, MarketH5Bean.class);
            if (marketH5Bean == null) {
                HwLog.d(str2, "jumpByParam marketH5Bean is null.");
            } else if (TextUtils.isEmpty(marketH5Bean.getHitopId())) {
                HwLog.e(str2, "jumpByParam hiTopId is null");
            } else {
                BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.mvp.model.webview.JsInterfaceMarketing$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        JsInterfaceMarketing.this.m919x87891d26(marketH5Bean);
                    }
                });
            }
        } catch (Exception e) {
            HwLog.e(TAG, "jumpByParam Exception:" + HwLog.printException(e));
        }
    }

    /* renamed from: lambda$jumpByParam$4$com-huawei-watchface-mvp-model-webview-JsInterfaceMarketing, reason: not valid java name */
    /* synthetic */ void m919x87891d26(MarketH5Bean marketH5Bean) {
        String hitopId;
        String str;
        bl blVar = new bl(marketH5Bean.getHitopId());
        GetmarkeHitopIdBean.ListBean e = blVar.e(blVar.c());
        if (e != null && !TextUtils.isEmpty(e.getHitopId())) {
            hitopId = e.getHitopId();
            str = e.getVersion();
        } else {
            HwLog.i(TAG, "jumpByParam listBean or hiTopId is null");
            hitopId = marketH5Bean.getHitopId();
            str = null;
        }
        String from = TextUtils.isEmpty(marketH5Bean.getFrom()) ? "campaign.h5.1005" : marketH5Bean.getFrom();
        String fromSub = TextUtils.isEmpty(marketH5Bean.getFromSub()) ? "aop" : marketH5Bean.getFromSub();
        HwLog.i(TAG, "jumpByParam hiTopId:" + hitopId + ",version:" + str + ",from:" + from + ",fromSub:" + fromSub);
        goJumpUrl(HwWatchFaceApi.getInstance(this.mContext).getMagicWatchFaceDetailUrl(hitopId, str, from, fromSub));
    }

    @JavascriptInterface
    public void chooseBuyVip(String str) {
        String str2 = TAG;
        HwLog.i(str2, "chooseBuyVip enter:" + str);
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.e(str2, "chooseBuyVip() !isInURLWhiteList.");
            return;
        }
        try {
            MarketH5Bean marketH5Bean = (MarketH5Bean) GsonHelper.fromJson(str, MarketH5Bean.class);
            s.a(this.mContext).a(this.mContext, HwWatchFaceApi.getInstance(this.mContext).getVipPayUrl(TextUtils.isEmpty(marketH5Bean.getFrom()) ? "campaign.h5.1005" : marketH5Bean.getFrom(), TextUtils.isEmpty(marketH5Bean.getFromSub()) ? "aop" : marketH5Bean.getFromSub()));
        } catch (Exception e) {
            HwLog.e(TAG, "chooseBuyVip error:" + HwLog.printException(e));
        }
    }

    @JavascriptInterface
    public String getMarketH5DefaultDevice() {
        String str = TAG;
        HwLog.i(str, "getMarketH5DefaultDevice: enter");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.e(str, "getMarketH5DefaultDevice() !isInURLWhiteList.");
            return "";
        }
        String systemParam = NewSystemParamManager.getSystemParam("client_marketH5_default_device", "{\"name\":\"Galileo-L10E\",\"screen\":\"466*466\",\"themeVersion\":\"1.1\"}");
        HwLog.d(str, "getMarketH5DefaultDevice:" + systemParam);
        return systemParam;
    }

    @JavascriptInterface
    public void goBackForMagicH5(String str) {
        String str2 = TAG;
        HwLog.i(str2, "goBackForMagicH5 enter");
        if (!JsSafeUrlSystemParamManager.getInstance().isInURLWhiteList(this.mWebview)) {
            HwLog.e(str2, "goBackForMagicH5() !isInURLWhiteList.");
        } else {
            goBackOrJumpUrl(str, true);
        }
    }
}
