package com.huawei.operation.view;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebHistoryItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.FileProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hidatamanager.util.LogUtils;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.adapter.AchievementShareCallback;
import com.huawei.operation.adapter.CloseWebCallback;
import com.huawei.operation.adapter.JsDataCallback;
import com.huawei.operation.adapter.OnActivityQuitCallback;
import com.huawei.operation.adapter.OnActivityShowButtonCallback;
import com.huawei.operation.adapter.OnCaptureExtCallback;
import com.huawei.operation.adapter.OnHealthDataCallback;
import com.huawei.operation.adapter.OnLoginCallback;
import com.huawei.operation.adapter.OnVmallCouponCallback;
import com.huawei.operation.adapter.OnWebViewStatusCallback;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.adapter.SendCurrentUrlCallback;
import com.huawei.operation.adapter.SendNoNetMsgCallback;
import com.huawei.operation.adapter.SendServerErrorMsgCallback;
import com.huawei.operation.adapter.SetFullscreenCallback;
import com.huawei.operation.adapter.SetLeftBtnArrowTypeCallback;
import com.huawei.operation.adapter.SetShareDataCallback;
import com.huawei.operation.adapter.SetTitleCallback;
import com.huawei.operation.adapter.ShareCallback;
import com.huawei.operation.adapter.SleepQuestionnaireCallback;
import com.huawei.operation.adapter.SportsStatisticsCallback;
import com.huawei.operation.adapter.StartAppSettingPage;
import com.huawei.operation.adapter.StartFitnessPageCallback;
import com.huawei.operation.adapter.StartGpsTrackPageCallback;
import com.huawei.operation.adapter.StartMiniShopWebPage;
import com.huawei.operation.adapter.StartSportPage;
import com.huawei.operation.adapter.StartWebPage;
import com.huawei.operation.adapter.ToastCallback;
import com.huawei.operation.adapter.TouchSignalCallback;
import com.huawei.operation.adapter.UpdateCustomTitleBarCallback;
import com.huawei.operation.adapter.VmallArgSignCallback;
import com.huawei.operation.adapter.VmallLoginCallback;
import com.huawei.operation.beans.TitleBean;
import com.huawei.operation.beans.WebViewConfig;
import com.huawei.operation.h5pro.TrustListCheckerImpl;
import com.huawei.operation.js.InJavaScriptLocalObj;
import com.huawei.operation.js.JsInteraction;
import com.huawei.operation.myhuawei.MyHuaweiLogin;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.operation.share.CaptureUtils;
import com.huawei.operation.share.ShareConfig;
import com.huawei.operation.share.ShareConfigCallback;
import com.huawei.operation.utils.ActivityHtmlPathApi;
import com.huawei.operation.utils.CasLoginUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.InformationBiOperate;
import com.huawei.operation.utils.KakaTaskUtils;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.operation.utils.UriConstants;
import com.huawei.operation.utils.Utils;
import com.huawei.operation.utils.WebViewUtils;
import com.huawei.operation.view.CustomWebView;
import com.huawei.operation.vmall.AuthVmall;
import com.huawei.pluginbase.PluginBaseAdapter;
import com.huawei.profile.profile.ProfileExtendConstants;
import com.huawei.secure.android.common.ssl.WebViewSSLCheck;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.fdu;
import defpackage.gic;
import defpackage.gmz;
import defpackage.ixx;
import defpackage.jcu;
import defpackage.jdi;
import defpackage.jdx;
import defpackage.koq;
import defpackage.nrt;
import defpackage.nru;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public class CustomWebView implements SetTitleCallback, ShareCallback, AchievementShareCallback, ToastCallback, JsDataCallback, StartGpsTrackPageCallback, StartFitnessPageCallback, TouchSignalCallback, StartMiniShopWebPage, StartWebPage, StartAppSettingPage, CloseWebCallback, SendServerErrorMsgCallback, SendNoNetMsgCallback, SendCurrentUrlCallback, OnLoginCallback, ShareConfigCallback, OnWebViewStatusCallback, OnActivityQuitCallback, OnVmallCouponCallback, SleepQuestionnaireCallback, VmallArgSignCallback, VmallLoginCallback, OnCaptureExtCallback, OnActivityShowButtonCallback, UpdateCustomTitleBarCallback, SetFullscreenCallback, SetLeftBtnArrowTypeCallback, SetShareDataCallback, StartSportPage, SportsStatisticsCallback, OnHealthDataCallback {
    public static final String APP_MARKET_TITLE = "应用市场";
    private static final String CONTENT_TYPE_HTML = "text/html";
    private static final String PERSONAL = "/personal";
    private static final String SHOW_FILE_CHOOSER_GALLERY = "SHOW_FILE_CHOOSER_GALLERY";
    private static final String SHOW_FILE_CHOOSER_TAKE_PHOTO = "SHOW_FILE_CHOOSER_TAKE_PHOTO";
    private static final String TAG = "PluginOperation_CustomWebView";
    private static String sWebViewUrl;
    private Pattern mAchieveMedalPattern;
    private Activity mActivity;
    private PluginOperationAdapter mAdapter;
    private String mAppVersion;
    private Context mContext;
    private CustomViewDialog mCustomViewDialog;
    private String mFromWhere;
    private Handler mHandler;
    private Uri mImageUri;
    private InformationBiOperate mInformationBiOperate;
    private boolean mIsLoadMyHuaweiFail;
    private StringBuilder mJsFunctionBack;
    private StringBuilder mJsFunctionSecondBack;
    private StringBuilder mJsFunctionThirdBack;
    private JsInteraction mJsInteraction;
    private String mMobileVmallHost;
    private WebViewClientImpl mMyWebViewClientImpl;
    private HealthProgressBar mProgressBar;
    private ShareConfig mShareConfig;
    private TextView mTextTitle;
    private int mType;
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadMessageForAndroid5;
    private ArrayList<String> mUrlLists;
    private WebView mWebView;
    private WebViewConfig mWebViewConfig;
    private String mWebViewStatusFunction;
    private List<String> mWebViewTitles;
    private String sBiCurrentUrl;
    private static final Set<String> BLACK_URL = new HashSet();
    private static final Set<String> SAME_BLACK_URL = new HashSet();
    private static final ArrayList<String> BLACK_URL_LIST = new ArrayList<>();
    private static boolean sIsWebViewNoFirstLogin = false;
    private static boolean sIsVmallLoginHasImplemented = false;
    private static Handler sInnerHandler = new Handler() { // from class: com.huawei.operation.view.CustomWebView.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 4001) {
                return;
            }
            boolean unused = CustomWebView.sIsWebViewNoFirstLogin = false;
            boolean unused2 = CustomWebView.sIsVmallLoginHasImplemented = false;
        }
    };
    private boolean mIsHasLoginCallbackUrl = false;
    private boolean mIsHasVmallSignUrl = false;
    private boolean mIsVmallCouponPage = false;
    private boolean mIsAuthVmall = false;
    private long mLastVmallRemoteLoginTime = 0;
    private String mUnsafeUrl = "";
    private String mOriginUrl = "";
    private String mVmallUrl = null;
    private String mRegisterVmallCouponFunctionRes = "";
    private String mRegisterActivityQuitFunctionRes = "";
    private String mRegisterActivityShareFunctionRes = "";
    private String mCurrentUrl = null;
    private String mCaptureFunction = "";
    private FrameLayout mFrameLayout = null;
    private long mLoadStartTime = 0;
    private long mLoadEndTime = 0;
    private String mAnnualUrlPre = "";
    private boolean mIsInformation = false;
    private AtomicBoolean mHasLoadedStart = new AtomicBoolean(false);
    private boolean mIsCoreJsMounted = false;
    private boolean mIsFirstLoadMyHuawei = true;

    public CustomWebView(Context context, HealthProgressBar healthProgressBar, WebView webView, Handler handler, int i) {
        this.mType = -1;
        this.mAppVersion = null;
        this.mType = i;
        this.mContext = context;
        this.mHandler = handler;
        this.mWebView = webView;
        this.mProgressBar = healthProgressBar;
        this.mActivity = context instanceof Activity ? (Activity) context : null;
        PluginBaseAdapter adapter = PluginOperation.getInstance(context).getAdapter();
        this.mAdapter = adapter instanceof PluginOperationAdapter ? (PluginOperationAdapter) adapter : null;
        this.mShareConfig = new ShareConfig(this.mContext, handler);
        this.mInformationBiOperate = new InformationBiOperate(context);
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        if (pluginOperationAdapter == null) {
            LogUtil.a(TAG, "PluginOperationAdapter is null");
            return;
        }
        this.mAppVersion = pluginOperationAdapter.getInfo(new String[]{"getAppInfo"}).get("grayVersion");
        this.mWebViewTitles = Collections.synchronizedList(new ArrayList(10));
        this.mUrlLists = new ArrayList<>(10);
        initWebViewSettings();
        InJavaScriptLocalObj inJavaScriptLocalObj = new InJavaScriptLocalObj();
        inJavaScriptLocalObj.setVmallLoginCallback(this);
        this.mWebView.addJavascriptInterface(inJavaScriptLocalObj, Constants.JAVA_APP_MARKET_OBJECT);
        registerCoreJsInterface();
        InJavaScriptLocalObj inJavaScriptLocalObj2 = new InJavaScriptLocalObj();
        inJavaScriptLocalObj2.setVmallLoginCallback(this);
        this.mWebView.addJavascriptInterface(inJavaScriptLocalObj2, Constants.JAVA_OBJ);
        initJsFuctionBackStringBuilder();
        this.mWebView.addJavascriptInterface(this, "override_back_btn_click");
    }

    private void registerCoreJsInterface() {
        JsInteraction jsInteraction = new JsInteraction(this.mContext);
        this.mJsInteraction = jsInteraction;
        jsInteraction.setShareCallback(this, this, this);
        this.mJsInteraction.setWidgetCallback(this, this, this);
        this.mJsInteraction.setTouchCallback(this, this, this);
        this.mJsInteraction.setSendCallback(this, this, this);
        this.mJsInteraction.setVmallCallback(this, this, this, this);
        this.mJsInteraction.setActivityShareButtonCallback(this);
        this.mJsInteraction.setStarCallback(this, this);
        this.mJsInteraction.setStarAndEndCallback(this, this, this, this);
        this.mJsInteraction.setTitleBarCallback(this, this, this, this);
        this.mJsInteraction.setStartSportPageCallback(this);
        this.mJsInteraction.setSportsStatisticsCallback(this);
        this.mJsInteraction.setOnHealthDataCallback(this);
        this.mWebView.addJavascriptInterface(this.mJsInteraction, "JsInteraction");
        this.mIsCoreJsMounted = true;
    }

    public void onDestroy() {
        JsInteraction jsInteraction = this.mJsInteraction;
        if (jsInteraction != null) {
            jsInteraction.onDestroy();
            this.mJsInteraction = null;
        }
    }

    private void initJsFuctionBackStringBuilder() {
        StringBuilder sb = new StringBuilder(Constants.JAVA_SCRIPT);
        this.mJsFunctionBack = sb;
        sb.append("(function(){document.getElementById('");
        sb.append("btn-back");
        sb.append("').onclick=function(){window.");
        sb.append("override_back_btn_click");
        sb.append(".jsBack();return false}})()");
        StringBuilder sb2 = new StringBuilder(Constants.JAVA_SCRIPT);
        this.mJsFunctionSecondBack = sb2;
        sb2.append("(function(){$('#");
        sb2.append("top-header-1");
        sb2.append(" #");
        sb2.append("btn-back");
        sb2.append("')[0].onclick=function(){window.");
        sb2.append("override_back_btn_click");
        sb2.append(".jsBack();return false}})()");
        StringBuilder sb3 = new StringBuilder(Constants.JAVA_SCRIPT);
        this.mJsFunctionThirdBack = sb3;
        sb3.append("(function(){");
        sb3.append("var bindBackTimer = setTimeout(function(){");
        sb3.append("var vmallTitleBar = document.getElementById('myVmallTitle');");
        sb3.append("if (!vmallTitleBar) { return;}");
        sb3.append("var vmallBack = vmallTitleBar.getElementsByClassName('icon-common icon-ic_public_back');");
        sb3.append("if (!vmallBack || vmallBack.length < 1) {return;}");
        sb3.append("vmallBack[0].onclick = function(){");
        sb3.append("window.override_back_btn_click.jsBack();return false;};");
        sb3.append("clearTimeout(bindBackTimer);");
        sb3.append("}, 800)})()");
    }

    public void setInformationFlag(boolean z) {
        this.mIsInformation = z;
    }

    public static void setLoginStatus(boolean z) {
        sIsWebViewNoFirstLogin = z;
        sIsVmallLoginHasImplemented = z;
    }

    public static void setIsWebViewNoFirstLogin(boolean z) {
        sIsWebViewNoFirstLogin = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addVmallPressBackListner(String str) {
        if (this.mIsHasVmallSignUrl) {
            LogUtil.a(TAG, "addVmallPressBackListner enter!");
            if (TextUtils.isEmpty(this.mJsFunctionBack) || TextUtils.isEmpty(this.mJsFunctionSecondBack) || TextUtils.isEmpty(this.mJsFunctionThirdBack)) {
                initJsFuctionBackStringBuilder();
            }
            WebView webView = this.mWebView;
            String sb = this.mJsFunctionBack.toString();
            webView.loadUrl(sb);
            WebViewInstrumentation.loadUrl(webView, sb);
            WebView webView2 = this.mWebView;
            String sb2 = this.mJsFunctionSecondBack.toString();
            webView2.loadUrl(sb2);
            WebViewInstrumentation.loadUrl(webView2, sb2);
            WebView webView3 = this.mWebView;
            String sb3 = this.mJsFunctionThirdBack.toString();
            webView3.loadUrl(sb3);
            WebViewInstrumentation.loadUrl(webView3, sb3);
        }
    }

    @JavascriptInterface
    public void jsBack() {
        if (this.mWebView == null) {
            LogUtil.h(TAG, "jsBack mWebView null ");
            return;
        }
        Activity activity = this.mActivity;
        if (activity == null) {
            LogUtil.h(TAG, "jsBack mActivity null ");
        } else {
            activity.runOnUiThread(new Runnable() { // from class: com.huawei.operation.view.CustomWebView.2
                @Override // java.lang.Runnable
                public void run() {
                    if (!CustomWebView.this.isTop()) {
                        if (!CustomWebView.this.mWebView.canGoBack()) {
                            LogUtil.a(CustomWebView.TAG, "mWebView JsBack false");
                            CustomWebView.this.mActivity.finish();
                            return;
                        } else {
                            LogUtil.a(CustomWebView.TAG, "mWebView JsBack true");
                            CustomWebView.this.loadUrl();
                            return;
                        }
                    }
                    LogUtil.a(CustomWebView.TAG, "mWebView JsBack isTop");
                    CustomWebView.this.mActivity.finish();
                }
            });
        }
    }

    public void initFrameLayout(Context context, FrameLayout frameLayout) {
        if (context instanceof Activity) {
            this.mActivity = (Activity) context;
        }
        this.mFrameLayout = frameLayout;
    }

    public String getWebViewTitles() {
        if (koq.b(this.mWebViewTitles)) {
            return "";
        }
        return this.mWebViewTitles.get(r0.size() - 1);
    }

    public void setFromWhere(String str) {
        if (str == null) {
            LogUtil.h(TAG, "setFromWhere fromWhere is null");
        } else {
            this.mFromWhere = str;
        }
    }

    public void setMyWebViewClientImpl(WebViewClientImpl webViewClientImpl) {
        this.mMyWebViewClientImpl = webViewClientImpl;
    }

    public String getRegisterActivityQuitFunctionRes() {
        return this.mRegisterActivityQuitFunctionRes;
    }

    public void setRegisterActivityQuitFunctionRes(String str) {
        this.mRegisterActivityQuitFunctionRes = str;
    }

    public String getmRegisterActivityShareFunctionRes() {
        return this.mRegisterActivityShareFunctionRes;
    }

    public void setmRegisterActivityShareFunctionRes(String str) {
        this.mRegisterActivityShareFunctionRes = str;
    }

    public String getRegisterVmallCouponFunctionRes() {
        return this.mRegisterVmallCouponFunctionRes;
    }

    private void setRegisterVmallCouponFunctionRes(String str) {
        this.mRegisterVmallCouponFunctionRes = str;
    }

    public void clear() {
        Handler handler = sInnerHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public ValueCallback<Uri> getUploadMessage() {
        LogUtil.a(TAG, "getUploadMessage");
        return this.mUploadMessage;
    }

    public void setUploadMessage(ValueCallback<Uri> valueCallback) {
        LogUtil.a(TAG, "setUploadMessage");
        this.mUploadMessage = valueCallback;
    }

    public CustomViewDialog getCustomViewDialog() {
        LogUtil.a(TAG, "getCustomViewDialog");
        return this.mCustomViewDialog;
    }

    public void setCustomViewDialog(CustomViewDialog customViewDialog) {
        LogUtil.a(TAG, "setCustomViewDialog");
        this.mCustomViewDialog = customViewDialog;
    }

    public ValueCallback<Uri[]> getUploadMessageForAndroid5() {
        LogUtil.a(TAG, "getUploadMessageForAndroid5");
        return this.mUploadMessageForAndroid5;
    }

    public void setUploadMessageForAndroid5(ValueCallback<Uri[]> valueCallback) {
        LogUtil.a(TAG, "setUploadMessageForAndroid5");
        this.mUploadMessageForAndroid5 = valueCallback;
    }

    public Uri getImageUri() {
        LogUtil.a(TAG, "getImageUri");
        return this.mImageUri;
    }

    private void initWebViewSettings() {
        LogUtil.a(TAG, "initWebViewSettings");
        WebSettings settings = this.mWebView.getSettings();
        if (CommonUtil.aa(this.mContext)) {
            settings.setCacheMode(-1);
        } else {
            settings.setCacheMode(1);
        }
        settings.setAllowFileAccess(false);
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        String userAgentString = this.mWebView.getSettings().getUserAgentString();
        this.mWebView.getSettings().setUserAgentString(userAgentString + "; HuaweiHealth");
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setUseWideViewPort(false);
        settings.setTextZoom(100);
        settings.setSavePassword(false);
        settings.setGeolocationEnabled(false);
        settings.setAllowContentAccess(false);
        settings.setLoadWithOverviewMode(true);
        settings.setBlockNetworkImage(false);
        settings.setMixedContentMode(2);
        CookieManager.getInstance().setAcceptThirdPartyCookies(this.mWebView, true);
        initWebViewSettingsEvent();
    }

    private void initWebViewSettingsEvent() {
        if (!CommonUtil.as() && !CommonUtil.ag(this.mContext)) {
            WebView.setWebContentsDebuggingEnabled(true);
            LogUtil.a(TAG, "initWebViewSettingsEvent setWebContentsDebuggingEnabled");
        }
        this.mWebView.setOnKeyListener(new View.OnKeyListener() { // from class: com.huawei.operation.view.CustomWebView.3
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                LogUtil.a(CustomWebView.TAG, "keyCode", Integer.valueOf(i), Integer.valueOf(keyEvent.getAction()));
                if (keyEvent.getAction() != 1 || i != 4) {
                    return false;
                }
                if (CustomWebView.this.mAdapter != null) {
                    if (CustomWebView.this.mAdapter.canFinish(CustomWebView.this.mActivity) != 1) {
                        if (CustomWebView.this.mAdapter.canFinish(CustomWebView.this.mActivity) == 2) {
                            LogUtil.a(CustomWebView.TAG, "breathe show stop dialog");
                            return true;
                        }
                        LogUtil.c(CustomWebView.TAG, "initWebViewSettingsEvent mAdapter.canFinish(mActivity) result not 1 2");
                    } else {
                        LogUtil.a(CustomWebView.TAG, "breathe finish activity");
                        CustomWebView.this.mActivity.finish();
                        return true;
                    }
                }
                CustomWebView.this.callbackWebViewStatus(Constants.WEBVIEW_STATUS_BACK);
                if (CustomWebView.this.mWebView.canGoBack()) {
                    if (CustomWebView.this.mIsHasVmallSignUrl) {
                        if (!CustomWebView.this.isTop()) {
                            CustomWebView.this.loadUrl();
                            return true;
                        }
                        LogUtil.a(CustomWebView.TAG, "mIsHasVmallSignUrl KeyEvent setResult");
                        CustomWebView.this.mActivity.setResult(-1);
                        return false;
                    }
                    if (CustomWebView.this.appGoBack()) {
                        return true;
                    }
                    LogUtil.a(CustomWebView.TAG, "KeyEvent mWebView.canGoBack");
                    return CustomWebView.this.processBack();
                }
                LogUtil.a(CustomWebView.TAG, "mWebView KeyEvent setResult");
                CustomWebView.this.mActivity.setResult(-1);
                return false;
            }
        });
        this.mWebView.setWebChromeClient(new WebChromeClientBase());
        WebView webView = this.mWebView;
        WebViewClientBase webViewClientBase = new WebViewClientBase();
        if (webView instanceof WebView) {
            APMSH5LoadInstrument.setWebViewClient(webView, webViewClientBase);
        } else {
            webView.setWebViewClient(webViewClientBase);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean appGoBack() {
        String str = this.mFromWhere;
        if (str == null || !"appMarket".equals(str)) {
            return false;
        }
        LogUtil.a(TAG, "mFromWhere");
        Handler handler = this.mHandler;
        if (handler == null) {
            return true;
        }
        handler.sendEmptyMessage(Constants.APP_MARKET_WEB_VIEW_BACK);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean processBack() {
        LogUtil.a(TAG, "processBack WebView go back");
        if (!isWhiteUrl(WebViewUtils.replaceSpace(this.mWebView.getUrl()))) {
            setJsEnable(false);
            return false;
        }
        setJsEnable(true);
        if (this.mIsHasVmallSignUrl || this.mIsHasLoginCallbackUrl) {
            if (isTop()) {
                LogUtil.a(TAG, "processBack mIsHasVmallSignUrl, do history update self!");
                this.mActivity.finish();
                return false;
            }
            loadUrl();
            return true;
        }
        this.mWebView.goBack();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0068, code lost:
    
        if (r4.contains(r0.getHost()) != false) goto L27;
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setBrowserTitle(java.lang.String r4) {
        /*
            r3 = this;
            java.lang.String r0 = "PluginOperation_CustomWebView"
            java.lang.String r1 = ""
            if (r4 != 0) goto L11
            java.lang.String r4 = "setBrowserTitle title is null!"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r4)
            r4 = r1
        L11:
            java.lang.String r2 = "应用市场"
            boolean r2 = android.text.TextUtils.equals(r2, r4)
            if (r2 == 0) goto L25
            java.lang.String r4 = "setBrowserTitle app market title not is APPMARKET"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r4)
            return
        L25:
            android.widget.TextView r2 = r3.mTextTitle
            if (r2 == 0) goto La7
            java.lang.String r2 = "setBrowserTitle mTextTitle != null"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r2)
            java.util.ArrayList r2 = com.huawei.operation.utils.WebViewUtils.getTitleList()
            boolean r2 = com.huawei.operation.utils.WebViewUtils.isUnreasonableTitle(r2, r4)
            if (r2 == 0) goto L3e
            goto L6a
        L3e:
            android.webkit.WebView r2 = r3.mWebView
            if (r2 == 0) goto L6c
            java.lang.String r0 = r3.getWebViewUrlUiThread()
            if (r0 == 0) goto L4d
            android.net.Uri r0 = android.net.Uri.parse(r0)
            goto L4e
        L4d:
            r0 = 0
        L4e:
            if (r0 == 0) goto L76
            java.lang.String r2 = r0.getHost()
            if (r2 == 0) goto L76
            java.lang.String r2 = r0.getHost()
            int r2 = r2.length()
            if (r2 == 0) goto L76
            java.lang.String r0 = r0.getHost()
            boolean r0 = r4.contains(r0)
            if (r0 == 0) goto L76
        L6a:
            r4 = r1
            goto L76
        L6c:
            java.lang.String r2 = "setBrowserTitle isUnreasonableTitle is false, mWebView = null"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.c(r0, r2)
        L76:
            java.util.List<java.lang.String> r0 = r3.mWebViewTitles
            if (r0 == 0) goto L8f
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L8f
            java.util.List<java.lang.String> r0 = r3.mWebViewTitles
            int r1 = r0.size()
            int r1 = r1 + (-1)
            java.lang.Object r0 = r0.get(r1)
            r1 = r0
            java.lang.String r1 = (java.lang.String) r1
        L8f:
            boolean r0 = r4.equals(r1)
            if (r0 != 0) goto La7
            android.os.Handler r0 = r3.mHandler
            r1 = 2002(0x7d2, float:2.805E-42)
            android.os.Message r0 = r0.obtainMessage(r1, r4)
            r0.sendToTarget()
            java.util.List<java.lang.String> r0 = r3.mWebViewTitles
            if (r0 == 0) goto La7
            r0.add(r4)
        La7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.operation.view.CustomWebView.setBrowserTitle(java.lang.String):void");
    }

    public void load(String str) {
        LogUtil.a(TAG, "load");
        if (TextUtils.isEmpty(str)) {
            LogUtil.a(TAG, "load originUrl isEmpty");
            str = Constants.ABOUT_BLANK;
        }
        String replaceSpace = WebViewUtils.replaceSpace(str);
        this.mOriginUrl = replaceSpace;
        if (!isWhiteUrl(replaceSpace)) {
            setJsEnable(false);
            removeJavascriptInterface(this.mOriginUrl);
            return;
        }
        setJsEnable(true);
        setAllowFileAccess(this.mOriginUrl);
        if (!this.mIsCoreJsMounted) {
            registerCoreJsInterface();
        }
        loadUrl(this.mOriginUrl);
    }

    private void setAllowFileAccess(String str) {
        if (Utils.isHttpOrHttps(str)) {
            return;
        }
        WebView webView = this.mWebView;
        if (webView == null) {
            LogUtil.h(TAG, "allowFileAccess: mWebView is null");
            return;
        }
        WebSettings settings = webView.getSettings();
        if (Utils.isWhiteFileUrl(str)) {
            settings.setAllowFileAccess(true);
            return;
        }
        settings.setAllowFileAccess(false);
        LogUtil.h(TAG, "allowFileAccess: isWhiteFileUrl false -> " + str);
    }

    private void removeJavascriptInterface(String str) {
        if (CommonUtil.cc() || str.toLowerCase(Locale.ENGLISH).startsWith("https://")) {
            return;
        }
        if (!str.startsWith(Constants.JAVA_SCRIPT)) {
            LogUtil.a(TAG, "not https or JAVA_SCRIPT protocol removeJavascriptInterface");
            if (Utils.isWhiteFileUrl(str)) {
                return;
            }
            LogUtil.a(TAG, "not file protocol removeJavascriptInterface");
            this.mWebView.removeJavascriptInterface("JsInteraction");
            this.mWebView.removeJavascriptInterface(Constants.HBS_SDK);
            this.mIsCoreJsMounted = false;
            return;
        }
        LogUtil.a(TAG, "JAVA_SCRIPT protocol not removeJavascriptInterface");
    }

    private void loadUrl(String str) {
        if (CommonUtil.aa(this.mContext) || str.startsWith("file:///android_asset/") || str.contains(ConfigConstants.BREATHE_URL) || Utils.isMatchThirdDeviceH5UrlPrefix(str)) {
            HashMap hashMap = new HashMap(2);
            hashMap.put("x-version", this.mAppVersion);
            this.mWebView.loadUrl(str, hashMap);
            this.mWebView.postDelayed(new Runnable() { // from class: com.huawei.operation.view.CustomWebView.4
                @Override // java.lang.Runnable
                public void run() {
                    CustomWebView.this.mWebView.clearHistory();
                    LogUtil.a(CustomWebView.TAG, "loadUrl url postDelayed");
                }
            }, 1000L);
            return;
        }
        if (this.mType == 3001) {
            HashMap hashMap2 = new HashMap(2);
            hashMap2.put("x-version", this.mAppVersion);
            this.mWebView.loadUrl(str, hashMap2);
            LogUtil.a(TAG, "loadUrl url TYPE_MINI_SHOP_FRAGMENT");
            return;
        }
        this.mHandler.sendEmptyMessage(2003);
        LogUtil.a(TAG, "loadUrl url MSG_ON_NET_WORK");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadUrl() {
        WebBackForwardList copyBackForwardList = this.mWebView.copyBackForwardList();
        if (copyBackForwardList.getCurrentIndex() < 2) {
            this.mWebView.goBack();
            return;
        }
        String url = copyBackForwardList.getItemAtIndex(copyBackForwardList.getCurrentIndex()).getUrl();
        WebHistoryItem itemAtIndex = copyBackForwardList.getItemAtIndex(copyBackForwardList.getCurrentIndex() - 1);
        if (WebViewUtils.cannotGoBackUrl(itemAtIndex.getUrl())) {
            goBackWhenLastUrlisInvalid(copyBackForwardList, url);
        } else {
            goBackWhenExistUrl(copyBackForwardList, itemAtIndex);
        }
    }

    public void reload(String str) {
        LogUtil.a(TAG, "reload");
        if (TextUtils.isEmpty(str)) {
            LogUtil.a(TAG, "reload tmpReloadUrl is isEmpty");
            str = Constants.ABOUT_BLANK;
        }
        String replaceSpace = WebViewUtils.replaceSpace(str);
        this.mOriginUrl = replaceSpace;
        if (!isWhiteUrl(replaceSpace)) {
            setJsEnable(false);
            return;
        }
        setJsEnable(true);
        setAllowFileAccess(this.mOriginUrl);
        removeJavascriptInterface(this.mOriginUrl);
        if (CommonUtil.aa(this.mContext)) {
            LogUtil.a(TAG, "reload mOriginUrl isNetworkConnected");
            HashMap hashMap = new HashMap(2);
            hashMap.put("x-version", this.mAppVersion);
            this.mWebView.loadUrl(this.mOriginUrl, hashMap);
            this.mHandler.postDelayed(new Runnable() { // from class: com.huawei.operation.view.CustomWebView$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    CustomWebView.this.m760lambda$reload$0$comhuaweioperationviewCustomWebView();
                }
            }, 200L);
            LogUtil.a(TAG, "reload mOriginUrl = ", this.mOriginUrl);
            return;
        }
        if (this.mType == 3001) {
            LogUtil.a(TAG, "reload mOriginUrl TYPE_MINI_SHOP_FRAGMENT");
            HashMap hashMap2 = new HashMap(2);
            hashMap2.put("x-version", this.mAppVersion);
            this.mWebView.loadUrl(this.mOriginUrl, hashMap2);
            return;
        }
        LogUtil.a(TAG, "reload mOriginUrl MSG_ON_NET_WORK");
        this.mHandler.sendEmptyMessage(2003);
    }

    /* renamed from: lambda$reload$0$com-huawei-operation-view-CustomWebView, reason: not valid java name */
    /* synthetic */ void m760lambda$reload$0$comhuaweioperationviewCustomWebView() {
        if (this.mOriginUrl.contains("#")) {
            this.mWebView.reload();
        }
    }

    public boolean goBack() {
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        if (pluginOperationAdapter != null) {
            if (pluginOperationAdapter.canFinish(this.mActivity) == 1) {
                LogUtil.a(TAG, "breathe finish activity");
                this.mActivity.finish();
                return true;
            }
            if (this.mAdapter.canFinish(this.mActivity) == 2) {
                LogUtil.a(TAG, "breathe show stop dialog");
                return true;
            }
            LogUtil.c(TAG, "goBack mAdapter.canFinish(mActivity) result not 1 2");
        }
        if (!this.mWebView.canGoBack()) {
            LogUtil.a(TAG, "WebView no go back.");
            return false;
        }
        if (this.mIsHasVmallSignUrl || this.mIsHasLoginCallbackUrl) {
            if (isTop()) {
                this.mActivity.finish();
                return false;
            }
            loadUrl();
            return true;
        }
        if (!TextUtils.isEmpty(this.mCurrentUrl) && this.mCurrentUrl.contains(Constants.ABOUT_BLANK) && !CommonUtil.aa(this.mContext)) {
            LogUtil.h(TAG, "goBack the net isn't connected");
            return false;
        }
        if (this.mCurrentUrl.contains("health_help_all") && this.mWebView.canGoBack()) {
            LogUtil.a(TAG, "goBack canGoBack");
            this.mWebView.goBack();
            return true;
        }
        if (isFirstUrl()) {
            this.mActivity.finish();
            return false;
        }
        LogUtil.a(TAG, "goBack webView canGoBack");
        this.mWebView.goBack();
        return true;
    }

    private boolean isFirstUrl() {
        WebBackForwardList copyBackForwardList = this.mWebView.copyBackForwardList();
        if (copyBackForwardList.getSize() <= 1) {
            return true;
        }
        int i = 0;
        for (int i2 = 0; i2 < copyBackForwardList.getCurrentIndex(); i2++) {
            WebHistoryItem itemAtIndex = copyBackForwardList.getItemAtIndex(i2);
            if (itemAtIndex != null && !isBlackUrl(itemAtIndex.getUrl())) {
                i++;
            }
        }
        return i == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isTop() {
        WebBackForwardList copyBackForwardList = this.mWebView.copyBackForwardList();
        int i = 0;
        for (int i2 = 0; i2 <= copyBackForwardList.getCurrentIndex(); i2++) {
            WebHistoryItem itemAtIndex = copyBackForwardList.getItemAtIndex(i2);
            if (itemAtIndex != null && !WebViewUtils.cannotGoBackUrl(itemAtIndex.getUrl())) {
                i++;
            }
        }
        return i == 1;
    }

    private void goBackWhenLastUrlisInvalid(WebBackForwardList webBackForwardList, String str) {
        int currentIndex = webBackForwardList.getCurrentIndex() - 2;
        while (currentIndex >= 0) {
            WebHistoryItem itemAtIndex = webBackForwardList.getItemAtIndex(currentIndex);
            if (!WebViewUtils.cannotGoBackUrl(itemAtIndex.getUrl()) && !itemAtIndex.getUrl().equals(str)) {
                this.mWebView.goBackOrForward(currentIndex - webBackForwardList.getCurrentIndex());
                return;
            }
            currentIndex--;
            if (currentIndex < 0) {
                this.mActivity.finish();
                return;
            }
        }
    }

    private void goBackWhenExistUrl(WebBackForwardList webBackForwardList, WebHistoryItem webHistoryItem) {
        int isLoopBackScene = isLoopBackScene(webBackForwardList);
        if (isLoopBackScene != 0) {
            this.mWebView.goBackOrForward(0 - isLoopBackScene);
            return;
        }
        if (isBlackUrl(webHistoryItem.getUrl())) {
            int backStepWhenUrl = getBackStepWhenUrl(webBackForwardList, 2);
            if (backStepWhenUrl >= webBackForwardList.getSize()) {
                this.mActivity.finish();
                return;
            } else {
                this.mWebView.goBackOrForward(0 - backStepWhenUrl);
                return;
            }
        }
        this.mWebView.goBack();
    }

    private int getBackStepWhenUrl(WebBackForwardList webBackForwardList, int i) {
        if (webBackForwardList.getCurrentIndex() <= i) {
            return i;
        }
        WebHistoryItem itemAtIndex = webBackForwardList.getItemAtIndex(webBackForwardList.getCurrentIndex() - i);
        return (isBlackUrl(itemAtIndex.getUrl()) || WebViewUtils.cannotGoBackUrl(itemAtIndex.getUrl())) ? getBackStepWhenUrl(webBackForwardList, i + 1) : i;
    }

    private boolean isBlackUrl(String str) {
        initBlackSet();
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        Iterator<String> it = SAME_BLACK_URL.iterator();
        while (it.hasNext()) {
            if (str.equals(it.next())) {
                return true;
            }
        }
        Iterator<String> it2 = BLACK_URL.iterator();
        while (it2.hasNext()) {
            if (str.contains(it2.next())) {
                return true;
            }
        }
        return isRedictUrl(str);
    }

    private boolean isRedictUrl(String str) {
        return !TextUtils.isEmpty(this.mCurrentUrl) && this.mCurrentUrl.contains(Constants.SUFFIX_HTML) && this.mCurrentUrl.startsWith(str);
    }

    private int isLoopBackScene(WebBackForwardList webBackForwardList) {
        initBlackUrlList();
        String url = webBackForwardList.getCurrentItem().getUrl();
        int currentIndex = webBackForwardList.getCurrentIndex();
        Iterator<String> it = BLACK_URL_LIST.iterator();
        int i = 0;
        while (it.hasNext()) {
            String[] split = it.next().split(",");
            if (split.length >= 2 && url.equals(split[0])) {
                i = getBackStepsAtBlackUrl(currentIndex, webBackForwardList, url, split);
            }
        }
        return i;
    }

    private int getBackStepsAtBlackUrl(int i, WebBackForwardList webBackForwardList, String str, String[] strArr) {
        int i2 = i - 1;
        int i3 = 1;
        while (i2 >= 0 && checkUrlCanJump(webBackForwardList.getItemAtIndex(i2).getUrl(), strArr, str)) {
            i2--;
            i3++;
        }
        return i3;
    }

    private boolean checkUrlCanJump(String str, String[] strArr, String str2) {
        return str.equals(str2) || str.contains(strArr[1]) || WebViewUtils.cannotGoBackUrl(str);
    }

    private void initBlackUrlList() {
        ArrayList<String> arrayList = BLACK_URL_LIST;
        if (koq.b(arrayList)) {
            arrayList.addAll(Arrays.asList(this.mContext.getResources().getStringArray(R.array._2130968660_res_0x7f040054)));
        }
    }

    private void initBlackSet() {
        Set<String> set = BLACK_URL;
        if (set.isEmpty()) {
            set.addAll(Arrays.asList(this.mContext.getResources().getStringArray(R.array._2130968658_res_0x7f040052)));
            SAME_BLACK_URL.addAll(Arrays.asList(this.mContext.getResources().getStringArray(R.array._2130968697_res_0x7f040079)));
        }
    }

    public void setTextView(TextView textView) {
        LogUtil.a(TAG, "setTextView");
        this.mTextTitle = textView;
    }

    public HealthProgressBar getProgressBar() {
        LogUtil.a(TAG, "getProgressBar");
        return this.mProgressBar;
    }

    public String getCaptureFunction() {
        return this.mCaptureFunction;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getGetDialogTips(String str) {
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        if (pluginOperationAdapter != null) {
            String queryServiceNameById = this.mAdapter.queryServiceNameById(pluginOperationAdapter.getServiceIdByUrl(str));
            if (!TextUtils.isEmpty(queryServiceNameById)) {
                return String.format(Locale.ROOT, this.mContext.getString(R.string._2130842535_res_0x7f0213a7), queryServiceNameById);
            }
        }
        return String.format(this.mContext.getString(R.string._2130842536_res_0x7f0213a8), String.format(Locale.ROOT, this.mContext.getString(R.string._2130842535_res_0x7f0213a7), Utils.getHostByJdk(str)));
    }

    public void clearUploadMessage() {
        LogUtil.a(TAG, "clearUploadMessage");
        ValueCallback<Uri> valueCallback = this.mUploadMessage;
        if (valueCallback != null) {
            valueCallback.onReceiveValue(null);
            this.mUploadMessage = null;
            return;
        }
        ValueCallback<Uri[]> valueCallback2 = this.mUploadMessageForAndroid5;
        if (valueCallback2 != null) {
            valueCallback2.onReceiveValue(new Uri[0]);
            this.mUploadMessageForAndroid5 = null;
        } else {
            LogUtil.c(TAG, "clearUploadMessage mUploadMessage = null,mUploadMessageForAndroid5 = null");
        }
    }

    public void takePhoto() {
        File file;
        LogUtil.a(TAG, "takePhoto");
        try {
            file = WebViewUtils.createImageFile();
        } catch (IOException unused) {
            LogUtil.b(TAG, "takePhoto IOException createImageFile fail");
            file = null;
        }
        if (file != null) {
            try {
                Intent takePhotoIntent = getTakePhotoIntent();
                if (takePhotoIntent == null) {
                    LogUtil.h(TAG, "takePhoto: intent is null");
                    return;
                }
                this.mImageUri = FileProvider.getUriForFile(this.mContext, jcu.f13746a, file);
                takePhotoIntent.addFlags(1);
                takePhotoIntent.putExtra("output", this.mImageUri);
                this.mActivity.startActivityForResult(takePhotoIntent, 20001);
                return;
            } catch (ActivityNotFoundException unused2) {
                LogUtil.a(TAG, "activity not found exception.");
                return;
            }
        }
        LogUtil.a(TAG, "takePhoto outputImage is null");
    }

    private Intent getTakePhotoIntent() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        PackageManager packageManager = this.mActivity.getPackageManager();
        if (packageManager == null) {
            LogUtil.h(TAG, "getTakePhotoIntent: packageManager is null");
            return null;
        }
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
        if (resolveActivity == null) {
            LogUtil.h(TAG, "getTakePhotoIntent: resolveInfo is null");
            return null;
        }
        ActivityInfo activityInfo = resolveActivity.activityInfo;
        if (activityInfo == null) {
            LogUtil.h(TAG, "getTakePhotoIntent: activityInfo is null");
            return null;
        }
        intent.setComponent(new ComponentName(activityInfo.packageName, activityInfo.name));
        return intent;
    }

    public void setJsEnable(boolean z) {
        LogUtil.a(TAG, "setJsEnable isEnable:", Boolean.valueOf(z));
        WebView webView = this.mWebView;
        if (webView != null) {
            webView.getSettings().setJavaScriptEnabled(z);
        }
    }

    public String getUrlBiType(String str) {
        if (this.mAdapter == null || TextUtils.isEmpty(str)) {
            LogUtil.a(TAG, "getUrlBiType mAdapter is null or url is empty");
            return "";
        }
        List<String> queryUrlList = this.mAdapter.queryUrlList(Constants.BI_URL);
        if (koq.b(queryUrlList)) {
            LogUtil.h(TAG, "getUrlBiType queryUrlList isEmpty!");
            return "";
        }
        for (String str2 : queryUrlList) {
            if (str2.contains("#")) {
                String[] split = str2.split("#", 2);
                if (split.length == 2 && str.contains(split[1])) {
                    return split[0];
                }
            }
        }
        return "";
    }

    public boolean isWhiteUrl(String str) {
        if (!Utils.needAuth(this.mContext)) {
            LogUtil.a(TAG, "isWhiteUrl do not need auth version!");
            return true;
        }
        if (TrustListCheckerImpl.containsXss(str)) {
            LogUtil.h(TAG, "isWhiteUrl: url cannot contain Xss");
            return false;
        }
        if (Utils.isWhiteFileUrl(str)) {
            return true;
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        ArrayList<String> arrayList = this.mUrlLists;
        if (arrayList != null) {
            arrayList.add(str);
        }
        if (!str.toLowerCase(Locale.ENGLISH).startsWith("https://") && !str.toLowerCase(Locale.ENGLISH).startsWith("http://") && !str.startsWith(Constants.PREFIX_ENCODE_HTTPS)) {
            return false;
        }
        boolean isWhiteUrlLogicJudge = Utils.isWhiteUrlLogicJudge(str, this.mAdapter);
        LogUtil.a(TAG, "isWhiteUrl isValidUrl is ", Boolean.valueOf(isWhiteUrlLogicJudge));
        if (isWhiteUrlLogicJudge) {
            return true;
        }
        reportValidUrl(str);
        LogUtil.a(TAG, "invalid url return false");
        return false;
    }

    private void reportValidUrl(String str) {
        if (health.compact.a.Utils.o()) {
            LogUtil.a(TAG, "reportValidUrl isOversea return");
            return;
        }
        HashMap hashMap = new HashMap(5);
        hashMap.put("click", "1");
        ArrayList<String> arrayList = this.mUrlLists;
        if (arrayList != null && arrayList.size() >= 2) {
            hashMap.put(Constants.LAST_URL, this.mUrlLists.get(r1.size() - 1));
        } else {
            hashMap.put(Constants.LAST_URL, "");
        }
        hashMap.put(Constants.INVALID_URL, str);
        ixx.d().d(this.mContext, AnalyticsValue.HEALTH_SHOP_INVALID_URL_2120008.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void isCustomTitle(String str) {
        boolean z = true;
        if (TextUtils.isEmpty(str) || this.mAdapter == null) {
            Object[] objArr = new Object[4];
            objArr[0] = "url = ";
            objArr[1] = Boolean.valueOf(TextUtils.isEmpty(str));
            objArr[2] = "mAdapter  = ";
            objArr[3] = Boolean.valueOf(this.mAdapter == null);
            LogUtil.h(TAG, objArr);
            return;
        }
        if (!str.contains("vmall.com") && !str.contains(Constants.VMALL_TYPE_WITH_PREFIX)) {
            z = false;
        }
        if (str.contains(Constants.MEMBER_TERMS_KEYWORD) || str.contains(Constants.RENEW_TERMS_KEYWORD)) {
            return;
        }
        if (WebViewUtils.isValidUrl(str) || z) {
            for (TitleBean titleBean : getCloudTitleBeanList()) {
                if (titleBean != null && str.contains(titleBean.fetchGetUrl())) {
                    if (z) {
                        titleBean.configSetIsShowAppBar(false);
                    }
                    TitleBean createTitleConfig = createTitleConfig(titleBean);
                    ActivityHtmlPathApi activityHtmlPathApi = (ActivityHtmlPathApi) Services.a("PluginOperation", ActivityHtmlPathApi.class);
                    if (activityHtmlPathApi == null) {
                        LogUtil.h(TAG, "activityHtmlPathApi is null");
                        this.mHandler.obtainMessage(2012, createTitleConfig).sendToTarget();
                        return;
                    }
                    String activityKeyUrlSp = WebViewUtils.getActivityKeyUrlSp(this.mContext);
                    if (str.startsWith(activityKeyUrlSp + activityHtmlPathApi.getActivityHtmlPath() + Constants.ACTIVITY_HTML)) {
                        createTitleConfig.configSetRightBtn(TitleBean.RIGHT_BTN_TYPE_IS_MY_ACTIVITY);
                        setRegisterActivityQuitFunctionRes("");
                    } else {
                        if (str.startsWith(activityKeyUrlSp + activityHtmlPathApi.getActivityHtmlPath() + Constants.MY_ACTIVITY_HTML)) {
                            createTitleConfig.configSetRightBtn("");
                            setRegisterActivityQuitFunctionRes("");
                        } else {
                            LogUtil.a(TAG, "url does not match");
                        }
                    }
                    this.mHandler.obtainMessage(2012, createTitleConfig).sendToTarget();
                    return;
                }
            }
            this.mHandler.obtainMessage(2012, null).sendToTarget();
            return;
        }
        LogUtil.a(TAG, "isCustomTitle isValidUrl is false");
    }

    private TitleBean createTitleConfig(TitleBean titleBean) {
        TitleBean titleBean2 = new TitleBean();
        titleBean2.configSetUrl(titleBean.fetchGetUrl());
        titleBean2.configSetLeftBtn(titleBean.fetchGetLeftBtn());
        titleBean2.configSetRightBtn(titleBean.fetchGetRightBtn());
        titleBean2.configSetFeatureUrl(titleBean.fetchGetFeatureUrl());
        titleBean2.configSetShoppingCartUrl(titleBean.fetchGetShoppingCartUrl());
        titleBean2.configSetOrderManagerUrl(titleBean.fetchGetOrderManagerUrl());
        titleBean2.configSetIsShowAppBar(titleBean.fetchIsShowAppBar());
        return titleBean2;
    }

    private List<TitleBean> getCloudTitleBeanList() {
        List<TitleBean> list;
        WebViewConfig webViewConfig = this.mWebViewConfig;
        if (webViewConfig != null) {
            list = webViewConfig.getTitleBeanConfigs();
        } else {
            WebViewConfig queryWebViewConfig = this.mAdapter.queryWebViewConfig();
            this.mWebViewConfig = queryWebViewConfig;
            LogUtil.c(TAG, "queryWebViewConfig = ", queryWebViewConfig);
            WebViewConfig webViewConfig2 = this.mWebViewConfig;
            if (webViewConfig2 != null) {
                list = webViewConfig2.getTitleBeanConfigs();
            } else {
                LogUtil.a(TAG, "mWebViewConfig is null");
                list = null;
            }
        }
        if (list != null && !list.isEmpty()) {
            LogUtil.a(TAG, "isCustomTitle in cloudTitleBeanList");
            return list;
        }
        LogUtil.a(TAG, "isCustomTitle in sLocalTitleBeans");
        return WebViewUtils.getLocalTitleBeans(this.mContext);
    }

    public void callbackWebViewStatus(String str) {
        LogUtil.a(TAG, "callBackWebViewStatus status:", str);
        String url = !TextUtils.isEmpty(this.mWebViewStatusFunction) ? WebViewUtils.getUrl(this.mWebViewStatusFunction, str) : "";
        if (TextUtils.isEmpty(url)) {
            LogUtil.a(TAG, "TextUtils.isEmpty(callJs)1");
            return;
        }
        WebView webView = this.mWebView;
        webView.loadUrl(url);
        WebViewInstrumentation.loadUrl(webView, url);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processVmallLogin() {
        sIsVmallLoginHasImplemented = true;
        this.mHandler.sendEmptyMessageDelayed(4001, OpAnalyticsConstants.H5_LOADING_DELAY);
        this.mWebView.stopLoading();
        gmz d = gmz.d();
        if (String.valueOf(true).equals(d.c(401))) {
            startVmallLogin();
        } else {
            shareHuaweiAccountWithVmallDialog(d);
        }
    }

    private void shareHuaweiAccountWithVmallDialog(final gmz gmzVar) {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.mContext).b(R.string._2130843517_res_0x7f02177d).e(BaseApplication.getContext().getResources().getString(R.string._2130843519_res_0x7f02177f)).cyU_(R.string._2130841555_res_0x7f020fd3, new View.OnClickListener() { // from class: com.huawei.operation.view.CustomWebView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                gmzVar.c(401, true, (String) null, (IBaseResponseCallback) null);
                CustomWebView.this.startVmallLogin();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.operation.view.CustomWebView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                gmzVar.c(401, false, (String) null, (IBaseResponseCallback) null);
                CustomWebView.this.mActivity.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        a2.setCancelable(false);
        Activity activity = this.mActivity;
        if (activity == null || activity.isFinishing()) {
            return;
        }
        a2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startVmallLogin() {
        LogUtil.a(TAG, "startVmallLogin");
        if (!sIsWebViewNoFirstLogin) {
            this.mWebView.stopLoading();
            vmallAtLogin(true);
            return;
        }
        try {
            String openApiVmallHost = WebViewUtils.getOpenApiVmallHost();
            if (TextUtils.isEmpty(openApiVmallHost)) {
                LogUtil.b(TAG, "startVmallLogin mCasLoginUrlDefault is empty");
                return;
            }
            LogUtil.c(TAG, "startVmallLogin casLoginUrlDefault = ", openApiVmallHost);
            WebView webView = this.mWebView;
            String casLoginUrl = CasLoginUtil.getCasLoginUrl(openApiVmallHost + "/");
            webView.loadUrl(casLoginUrl);
            WebViewInstrumentation.loadUrl(webView, casLoginUrl);
        } catch (UnsupportedEncodingException e) {
            LogUtil.b(TAG, "startVmallLogin UnsupportedEncodingException ", e.getMessage());
        }
    }

    public void startVmallLogout() {
        LogUtil.a(TAG, "startVmallLogout");
        if (!TextUtils.isEmpty(this.mMobileVmallHost)) {
            LogUtil.c(TAG, "startVmallLogout mobileVmallHost = ", this.mMobileVmallHost);
            WebView webView = this.mWebView;
            String str = this.mMobileVmallHost + UriConstants.URL_CASLOGOUT_DEFAULT;
            webView.loadUrl(str);
            WebViewInstrumentation.loadUrl(webView, str);
            setLoginStatus(false);
            return;
        }
        LogUtil.b(TAG, "startVmallLogout mobileVmallHost is empty");
    }

    @Override // com.huawei.operation.adapter.UpdateCustomTitleBarCallback
    public void updateCustomTitleBarVisibility(final String str) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.operation.view.CustomWebView.7
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a(CustomWebView.TAG, "updateCustomTitleBarVisibility");
                CustomWebView.this.mHandler.obtainMessage(Constants.UPDATE_CUSTOM_TITLE_BAR_VISIBILITY, str).sendToTarget();
            }
        });
    }

    @Override // com.huawei.operation.adapter.SetFullscreenCallback
    public void setFullscreen(final String str) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.operation.view.CustomWebView.8
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a(CustomWebView.TAG, "setFullscreen");
                CustomWebView.this.mHandler.obtainMessage(Constants.SET_FULLSCREEN, str).sendToTarget();
            }
        });
    }

    @Override // com.huawei.operation.adapter.SetLeftBtnArrowTypeCallback
    public void setLeftBtnArrowType() {
        this.mWebView.post(new Runnable() { // from class: com.huawei.operation.view.CustomWebView.9
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a(CustomWebView.TAG, "setLeftBtnArrowType");
                CustomWebView.this.mHandler.obtainMessage(Constants.LEFT_BTN_ARROW_TYPE, "").sendToTarget();
            }
        });
    }

    @Override // com.huawei.operation.adapter.SetLeftBtnArrowTypeCallback
    public void setNextH5PageLeftBtnArrowType() {
        this.mWebView.post(new Runnable() { // from class: com.huawei.operation.view.CustomWebView.10
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a(CustomWebView.TAG, "setLeftBtnArrowType");
                CustomWebView.this.mHandler.obtainMessage(Constants.NEXT_H5_PAGE_LEFT_BTN_ARROW_TYPE, "").sendToTarget();
            }
        });
    }

    @Override // com.huawei.operation.adapter.SetShareDataCallback
    public void setShareData(final String str, final String str2, final String str3, final String str4) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.operation.view.CustomWebView.11
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a(CustomWebView.TAG, "setShareData.");
                Bundle bundle = new Bundle();
                bundle.putString(Constants.SHARE_BITMAPURL, str);
                bundle.putString(Constants.SHARE_TITLE, str2);
                bundle.putString(Constants.SHARE_CONTENT, str3);
                bundle.putString(Constants.SHARE_URL, str4);
                Message obtainMessage = CustomWebView.this.mHandler.obtainMessage(Constants.SET_SHARE_DATA);
                obtainMessage.setData(bundle);
                CustomWebView.this.mHandler.sendMessage(obtainMessage);
            }
        });
    }

    @Override // com.huawei.operation.adapter.SportsStatisticsCallback
    public void callSportSumDataJsFunction(final int i, final Object obj, final String str) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.operation.view.CustomWebView.12
            @Override // java.lang.Runnable
            public void run() {
                String str2;
                if (TextUtils.isEmpty(str)) {
                    str2 = "";
                } else {
                    str2 = WebViewUtils.getUrl(str, i + "', '" + HiJsonUtil.e(obj));
                }
                if (TextUtils.isEmpty(str2)) {
                    LogUtil.h(CustomWebView.TAG, "callJs isEmpty.");
                    return;
                }
                LogUtil.a(CustomWebView.TAG, "callback Js function", str2);
                WebView webView = CustomWebView.this.mWebView;
                webView.loadUrl(str2);
                WebViewInstrumentation.loadUrl(webView, str2);
            }
        });
    }

    @Override // com.huawei.operation.adapter.OnHealthDataCallback
    public void onHealthDataStatisticsCallback(final int i, final Object obj, final String str) {
        LogUtils.d(TAG, "onHealthDataStatisticsCallback");
        if (TextUtils.isEmpty(str)) {
            LogUtils.w(TAG, "onHealthDataStatisticsCallback: callback is empty,ignore!");
        } else {
            this.mWebView.post(new Runnable() { // from class: com.huawei.operation.view.CustomWebView.13
                @Override // java.lang.Runnable
                public void run() {
                    String url;
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.put("code", Integer.valueOf(i));
                    Object obj2 = obj;
                    if (obj2 == null) {
                        url = WebViewUtils.getUrl(str, linkedHashMap.toString());
                    } else {
                        linkedHashMap.put("data", obj2);
                        url = WebViewUtils.getUrl(str, linkedHashMap.toString());
                    }
                    if (TextUtils.isEmpty(url)) {
                        LogUtils.w(CustomWebView.TAG, "onCoreSleepDetailCallback:callJs is empty, ignore!");
                        return;
                    }
                    LogUtils.d(CustomWebView.TAG, "onCoreSleepDetailCallback:callJs data:" + url);
                    WebView webView = CustomWebView.this.mWebView;
                    webView.loadUrl(url);
                    WebViewInstrumentation.loadUrl(webView, url);
                }
            });
        }
    }

    class WebChromeClientBase extends WebChromeClient {
        private View mCustomView;
        private WebChromeClient.CustomViewCallback mCustomViewCallback;

        private WebChromeClientBase() {
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            if (CustomWebView.this.mType == 3001) {
                return;
            }
            if (i < 100) {
                if (CustomWebView.this.mProgressBar.getVisibility() == 8) {
                    CustomWebView.this.mProgressBar.setVisibility(0);
                }
                CustomWebView.this.mProgressBar.setProgress(i);
            } else {
                LogUtil.a(CustomWebView.TAG, "setProgressBar >= 100");
                CustomWebView.this.mProgressBar.setProgress(i);
                CustomWebView.this.mHandler.sendMessageDelayed(CustomWebView.this.mHandler.obtainMessage(2000), 500L);
                CustomWebView.this.mHasLoadedStart.set(false);
            }
            super.onProgressChanged(webView, i);
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            CustomWebView.this.setBrowserTitle(str);
        }

        @Override // android.webkit.WebChromeClient
        public void onGeolocationPermissionsShowPrompt(final String str, final GeolocationPermissions.Callback callback) {
            LogUtil.a(CustomWebView.TAG, "onGeolocationPermissionsShowPrompt() enter");
            CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(CustomWebView.this.mContext);
            builder.b(CustomWebView.this.mContext.getString(R.string._2130843263_res_0x7f02167f)).e(CustomWebView.this.getGetDialogTips(str)).cyV_(CustomWebView.this.mContext.getString(R.string._2130841555_res_0x7f020fd3), new View.OnClickListener() { // from class: com.huawei.operation.view.CustomWebView.WebChromeClientBase.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a(CustomWebView.TAG, "onGeolocationPermissionsShowPromptDialog setPositiveButton");
                    callback.invoke(str, true, false);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).cyS_(CustomWebView.this.mContext.getString(R.string._2130841939_res_0x7f021153), new View.OnClickListener() { // from class: com.huawei.operation.view.CustomWebView.WebChromeClientBase.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a(CustomWebView.TAG, "onGeolocationPermissionsShowPromptDialog setNegativeButton");
                    callback.invoke(str, false, false);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            if (CustomWebView.this.mActivity.isFinishing()) {
                callback.invoke(str, false, false);
                return;
            }
            CustomTextAlertDialog a2 = builder.a();
            a2.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.huawei.operation.view.CustomWebView.WebChromeClientBase.3
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    LogUtil.a(CustomWebView.TAG, "onGeolocationPermissionsShowPromptDialog setOnCancelListener");
                    callback.invoke(str, false, false);
                }
            });
            a2.show();
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTouchIconUrl(WebView webView, String str, boolean z) {
            LogUtil.c(CustomWebView.TAG, "onReceivedTouchIconUrl");
            super.onReceivedTouchIconUrl(webView, str, z);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
            LogUtil.a(CustomWebView.TAG, "onCreateWindow");
            return super.onCreateWindow(webView, z, z2, message);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            LogUtil.a(CustomWebView.TAG, "onShowFileChooser Android > 5.0");
            CustomWebView.this.mUploadMessageForAndroid5 = valueCallback;
            Object systemService = CustomWebView.this.mContext.getSystemService("layout_inflater");
            if (!(systemService instanceof LayoutInflater)) {
                return false;
            }
            View inflate = ((LayoutInflater) systemService).inflate(R.layout.hw_show_select_pic_dialog_view, (ViewGroup) null);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.line_take_photo);
            LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.line_gallery);
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.operation.view.CustomWebView.WebChromeClientBase.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a(CustomWebView.TAG, "selectImageDialog ok");
                    WebChromeClientBase.this.onShowFileChooser(CustomWebView.SHOW_FILE_CHOOSER_TAKE_PHOTO);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.operation.view.CustomWebView.WebChromeClientBase.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a(CustomWebView.TAG, "selectImageDialog negative");
                    WebChromeClientBase.this.onShowFileChooser(CustomWebView.SHOW_FILE_CHOOSER_GALLERY);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            CustomViewDialog.Builder builder = new CustomViewDialog.Builder(CustomWebView.this.mContext);
            builder.a(CustomWebView.this.mContext.getString(R.string._2130842067_res_0x7f0211d3)).czg_(inflate).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.operation.view.CustomWebView.WebChromeClientBase.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CustomWebView.this.clearUploadMessage();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            CustomWebView.this.mCustomViewDialog = builder.e();
            CustomWebView.this.mCustomViewDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.huawei.operation.view.CustomWebView.WebChromeClientBase.7
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    CustomWebView.this.clearUploadMessage();
                }
            });
            CustomWebView.this.mCustomViewDialog.show();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onShowFileChooser(String str) {
            String str2;
            boolean z;
            if (CustomWebView.this.mContext == null || CustomWebView.this.mActivity == null) {
                LogUtil.h(CustomWebView.TAG, "onShowFileChooser: mContext or mActivity is null");
                return;
            }
            if (!PermissionUtil.g()) {
                if (jdi.c(CustomWebView.this.mContext, getPermissions(str))) {
                    jumpToOperationPage(str);
                    return;
                }
                if (WebViewUtils.isLeftLarger(Build.VERSION.SDK_INT, 23)) {
                    boolean z2 = false;
                    boolean z3 = CommonUtil.a(CustomWebView.this.mContext, "android.permission.WRITE_EXTERNAL_STORAGE") || CustomWebView.this.mActivity.shouldShowRequestPermissionRationale("android.permission.WRITE_EXTERNAL_STORAGE");
                    boolean z4 = CommonUtil.a(CustomWebView.this.mContext, "android.permission.READ_EXTERNAL_STORAGE") || CustomWebView.this.mActivity.shouldShowRequestPermissionRationale("android.permission.READ_EXTERNAL_STORAGE");
                    if (TextUtils.equals(str, CustomWebView.SHOW_FILE_CHOOSER_TAKE_PHOTO)) {
                        z = (z3 || z4 || CustomWebView.this.mActivity.shouldShowRequestPermissionRationale("android.permission.CAMERA")) ? false : true;
                        str2 = CustomWebView.this.mContext.getString(R.string._2130842069_res_0x7f0211d5);
                    } else if (TextUtils.equals(str, CustomWebView.SHOW_FILE_CHOOSER_GALLERY)) {
                        z = (z3 || z4) ? false : true;
                        str2 = CustomWebView.this.mContext.getString(nsn.d(PermissionUtil.PermissionType.STORAGE));
                    } else {
                        str2 = "";
                        if (z2 && !TextUtils.isEmpty(str2)) {
                            nsn.c(CustomWebView.this.mContext, str2);
                            return;
                        }
                    }
                    z2 = z;
                    if (z2) {
                        nsn.c(CustomWebView.this.mContext, str2);
                        return;
                    }
                }
                requestPermission(getPermissions(str));
                return;
            }
            onShowFileChooserForAndroidT(str);
        }

        private void onShowFileChooserForAndroidT(final String str) {
            LogUtil.h(CustomWebView.TAG, "onShowFileChooserForAndroidT");
            PermissionUtil.b(CustomWebView.this.mContext, CustomWebView.SHOW_FILE_CHOOSER_TAKE_PHOTO.equalsIgnoreCase(str) ? PermissionUtil.PermissionType.CAMERA_IMAGE : PermissionUtil.PermissionType.STORAGE, new CustomPermissionAction(CustomWebView.this.mContext) { // from class: com.huawei.operation.view.CustomWebView.WebChromeClientBase.8
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.h(CustomWebView.TAG, "onShowFileChooserForAndroidT: onGranted");
                    WebChromeClientBase.this.jumpToOperationPage(str);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void jumpToOperationPage(String str) {
            if (TextUtils.equals(str, CustomWebView.SHOW_FILE_CHOOSER_TAKE_PHOTO)) {
                CustomWebView.this.takePhoto();
                return;
            }
            if (TextUtils.equals(str, CustomWebView.SHOW_FILE_CHOOSER_GALLERY)) {
                if (WebViewUtils.isLeftLarger(Build.VERSION.SDK_INT, 21)) {
                    WebViewUtils.openFileChooserImplForAndroid5(CustomWebView.this.mActivity);
                    return;
                } else {
                    WebViewUtils.openFileChooserImpl(CustomWebView.this.mActivity);
                    return;
                }
            }
            LogUtil.h(CustomWebView.TAG, "jumpToOperationPage: " + str + " not implemented");
        }

        private void requestPermission(String[] strArr) {
            if (strArr != null && strArr.length != 0) {
                jdi.bFL_(CustomWebView.this.mActivity, strArr, new PermissionsResultAction() { // from class: com.huawei.operation.view.CustomWebView.WebChromeClientBase.9
                    @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onGranted() {
                        LogUtil.a(CustomWebView.TAG, "onGranted()");
                    }

                    @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onDenied(String str) {
                        LogUtil.a(CustomWebView.TAG, "onDenied()");
                    }
                });
                for (String str : strArr) {
                    if (!TextUtils.isEmpty(str)) {
                        CommonUtil.k(CustomWebView.this.mContext, str);
                        LogUtil.c(CustomWebView.TAG, "permission is " + str);
                    }
                }
                return;
            }
            LogUtil.h(CustomWebView.TAG, "requestPermission: Invalid parameter 'permissions'");
        }

        private String[] getPermissions(String str) {
            if (TextUtils.equals(str, CustomWebView.SHOW_FILE_CHOOSER_TAKE_PHOTO)) {
                return new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
            }
            if (TextUtils.equals(str, CustomWebView.SHOW_FILE_CHOOSER_GALLERY)) {
                return new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
            }
            LogUtil.h(CustomWebView.TAG, "getPermissions: " + str + " not implemented");
            return new String[0];
        }

        @Override // android.webkit.WebChromeClient
        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            super.onShowCustomView(view, customViewCallback);
            if (this.mCustomView != null) {
                customViewCallback.onCustomViewHidden();
                return;
            }
            this.mCustomView = view;
            if (CustomWebView.this.mFrameLayout != null) {
                CustomWebView.this.mFrameLayout.addView(this.mCustomView);
            }
            this.mCustomViewCallback = customViewCallback;
            CustomWebView.this.mWebView.setVisibility(8);
            CustomWebView.this.mActivity.setRequestedOrientation(0);
        }

        @Override // android.webkit.WebChromeClient
        public void onHideCustomView() {
            CustomWebView.this.mWebView.setVisibility(0);
            View view = this.mCustomView;
            if (view == null) {
                return;
            }
            view.setVisibility(8);
            if (CustomWebView.this.mFrameLayout != null) {
                CustomWebView.this.mFrameLayout.removeView(this.mCustomView);
            }
            this.mCustomViewCallback.onCustomViewHidden();
            this.mCustomView = null;
            CustomWebView.this.mActivity.setRequestedOrientation(1);
            super.onHideCustomView();
        }
    }

    public String getBiCurrentUrl() {
        return this.sBiCurrentUrl;
    }

    private void setTimeBiAnalysis() {
        long j = this.mLoadEndTime - this.mLoadStartTime;
        if (j <= 0 || j > OpAnalyticsConstants.H5_LOADING_DELAY) {
            return;
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(2);
        linkedHashMap.put(OpAnalyticsConstants.DELAY_MS, String.valueOf(j));
        linkedHashMap.put(OpAnalyticsConstants.URL_HEADER, getBiCurrentUrl());
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_WEBVIEW_LOADING_DELAY_80060002.value(), linkedHashMap);
    }

    class WebViewClientBase extends WebViewClient {
        private WebViewClientBase() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            CustomWebView.this.mOriginUrl = WebViewUtils.replaceSpace(str);
            CustomWebView.this.mCurrentUrl = str;
            CustomWebView.this.mHasLoadedStart.set(true);
            if (WebViewUtils.isValidUrl(CustomWebView.this.mOriginUrl)) {
                CustomWebView.this.mHandler.obtainMessage(2020).sendToTarget();
            }
            CustomWebView customWebView = CustomWebView.this;
            customWebView.setDarkMode(customWebView.mCurrentUrl);
            if (Utils.isNeedLoginWithVmallUrl(CustomWebView.this.mCurrentUrl, CustomWebView.this.mContext, CustomWebView.this)) {
                return true;
            }
            CustomWebView.this.interceptMyhuawei(str);
            CustomWebView.this.interceptUpLoginPage(str);
            CustomWebView.this.interceptLoginPage(str);
            if (!WebViewUtils.isValidUrl(CustomWebView.this.mOriginUrl)) {
                if (WebViewUtils.schemeHandle(CustomWebView.this.mContext, CustomWebView.this.mAdapter, CustomWebView.this.mOriginUrl) || WebViewUtils.schemeHandleVmall(CustomWebView.this.mContext, CustomWebView.this.mOriginUrl, CustomWebView.this.mVmallUrl)) {
                    LogUtil.a(CustomWebView.TAG, "scheme is known");
                    return true;
                }
                LogUtil.a(CustomWebView.TAG, "scheme is unknown");
                return true;
            }
            CustomWebView.this.setJsEnable(true);
            CustomWebView customWebView2 = CustomWebView.this;
            if (customWebView2.isWhiteUrl(customWebView2.mOriginUrl)) {
                if (!WebViewUtils.isFullScreen(CustomWebView.this.mOriginUrl)) {
                    if (!TextUtils.isEmpty(CustomWebView.this.mMobileVmallHost) && CustomWebView.this.mCurrentUrl.startsWith(CustomWebView.this.mMobileVmallHost) && !CustomWebView.sIsWebViewNoFirstLogin && !CustomWebView.sIsVmallLoginHasImplemented) {
                        CustomWebView customWebView3 = CustomWebView.this;
                        customWebView3.mVmallUrl = customWebView3.mCurrentUrl;
                        CustomWebView.this.processVmallLogin();
                    }
                    return false;
                }
                Intent intent = new Intent(CustomWebView.this.mContext, (Class<?>) WebViewActivity.class);
                intent.putExtra("url", CustomWebView.this.mOriginUrl);
                CustomWebView.this.mContext.startActivity(intent);
                return true;
            }
            LogUtil.c(CustomWebView.TAG, "shouldOverrideUrlHandler isWhiteUrl false");
            CustomWebView.this.mWebView.removeJavascriptInterface("JsInteraction");
            CustomWebView.this.mWebView.removeJavascriptInterface(Constants.HBS_SDK);
            CustomWebView.this.mIsCoreJsMounted = false;
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            LogUtil.a(CustomWebView.TAG, "onPageStarted");
            if (CustomWebView.this.mContext instanceof WebViewActivity) {
                ((WebViewActivity) CustomWebView.this.mContext).enableSlowWholeDocumentDraw(str);
            }
            KakaTaskUtils.onAchieveEvent(str, CustomWebView.this.mContext);
            CustomWebView.this.mProgressBar.setVisibility(0);
            if (!TextUtils.isEmpty(str)) {
                CustomWebView.this.mCurrentUrl = str;
                CustomWebView.this.mOriginUrl = str;
                CustomWebView.this.mHasLoadedStart.set(true);
                if (WebViewUtils.isValidUrl(CustomWebView.this.mCurrentUrl)) {
                    CustomWebView.this.mHandler.obtainMessage(2020).sendToTarget();
                }
                CustomWebView.this.mLoadStartTime = System.currentTimeMillis();
                CustomWebView.this.interceptPayUrl(str);
                CustomWebView.this.interceptMyhuawei(str);
                CustomWebView.this.interceptUpLoginPage(str);
                CustomWebView.this.interceptLoginPage(str);
                CustomWebView customWebView = CustomWebView.this;
                customWebView.setDarkMode(customWebView.mCurrentUrl);
                CustomWebView.this.urlVerification(str);
                if (CustomWebView.this.mWebView.getProgress() < 100) {
                    LogUtil.a(CustomWebView.TAG, "onPageStarted mWebView.getProgress() < 100 ");
                    CustomWebView.this.mHandler.sendEmptyMessageDelayed(2007, OpAnalyticsConstants.H5_LOADING_DELAY);
                }
                if (WebViewUtils.isHealthVmall(CustomWebView.this.mCurrentUrl)) {
                    if (CustomWebView.this.isLoginHealthVmall()) {
                        LogUtil.a(CustomWebView.TAG, "health vmall: true");
                        super.onPageStarted(webView, str, bitmap);
                    } else {
                        LogUtil.h(CustomWebView.TAG, "health vmall: false");
                    }
                } else if (!TextUtils.isEmpty(CustomWebView.this.mMobileVmallHost) && CustomWebView.this.mCurrentUrl.startsWith(CustomWebView.this.mMobileVmallHost)) {
                    LogUtil.a(CustomWebView.TAG, "WebViewClientBase onPageStarted isVmall mIsWebViewNoFirstLogin is ", Boolean.valueOf(CustomWebView.sIsWebViewNoFirstLogin));
                    CustomWebView customWebView2 = CustomWebView.this;
                    customWebView2.mVmallUrl = customWebView2.mCurrentUrl;
                    if (!CustomWebView.sIsWebViewNoFirstLogin) {
                        CustomWebView.this.processVmallLogin();
                    } else {
                        super.onPageStarted(webView, str, bitmap);
                    }
                } else {
                    super.onPageStarted(webView, str, bitmap);
                }
                if (CustomWebView.this.mHandler != null) {
                    Message obtainMessage = CustomWebView.this.mHandler.obtainMessage(Constants.ON_PAGE_STARTED);
                    obtainMessage.obj = str;
                    CustomWebView.this.mHandler.sendMessage(obtainMessage);
                    return;
                }
                return;
            }
            LogUtil.h(CustomWebView.TAG, "WebViewClientBase onPageStarted url is null.");
        }

        private WebResourceResponse getAnnualMedalWebResourceResponse(WebView webView, WebResourceRequest webResourceRequest, String str) {
            WebResourceResponse shouldInterceptRequest;
            if (webResourceRequest == null) {
                shouldInterceptRequest = super.shouldInterceptRequest(webView, str);
            } else {
                shouldInterceptRequest = super.shouldInterceptRequest(webView, webResourceRequest);
            }
            if (!str.contains(Constants.ANNUAL_MEDAL_PATH_PREFIX) && !str.contains(Constants.ANNUAL_MEDAL_PATH_PREFIX_COMMON)) {
                return shouldInterceptRequest;
            }
            try {
                String c = CommonUtil.c(Uri.parse(str.replace(Constants.ANNUAL_MEDAL_PATH_PREFIX, "").replace(Constants.ANNUAL_MEDAL_PATH_PREFIX_COMMON, "")).getPath());
                if (!GeneralUtil.isSafePath(c)) {
                    return shouldInterceptRequest;
                }
                if (CustomWebView.this.mAchieveMedalPattern == null) {
                    CustomWebView.this.mAchieveMedalPattern = Pattern.compile("^(/data/)((data)|(user/[0-9]))/" + BaseApplication.getAppPackage() + "/files/((achievemedalpng)|(achievemedal))/");
                }
                if (!CustomWebView.this.mAchieveMedalPattern.matcher(c).find()) {
                    LogUtil.h(CustomWebView.TAG, "Invalid Resource: " + c);
                    return shouldInterceptRequest;
                }
                return new WebResourceResponse("image/png", "UTF-8", new FileInputStream(c));
            } catch (IOException unused) {
                LogUtil.b(CustomWebView.TAG, "getAnnualMedalWebResourceResponse: failed to obtain resources");
                return shouldInterceptRequest;
            }
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            String uri = webResourceRequest.getUrl().toString();
            if (TextUtils.isEmpty(uri)) {
                return super.shouldInterceptRequest(webView, webResourceRequest);
            }
            if (uri.contains(Constants.ANNUAL_MEDAL_PATH_PREFIX) || uri.contains(Constants.ANNUAL_MEDAL_PATH_PREFIX_COMMON)) {
                return getAnnualMedalWebResourceResponse(webView, webResourceRequest, uri);
            }
            removeJsInterface(uri);
            return betaVersionResult(uri);
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.a(CustomWebView.TAG, "url is null!");
                return super.shouldInterceptRequest(webView, str);
            }
            if (str.contains(Constants.ANNUAL_MEDAL_PATH_PREFIX) || str.contains(Constants.ANNUAL_MEDAL_PATH_PREFIX_COMMON)) {
                return getAnnualMedalWebResourceResponse(webView, null, str);
            }
            notTestVersionAndNotHttps(str);
            return webResourceResponseResult(str);
        }

        private void removeJsInterface(String str) {
            if (CommonUtil.cc()) {
                return;
            }
            if (str.toLowerCase(Locale.ENGLISH).startsWith("http://") || str.toLowerCase(Locale.ENGLISH).startsWith(Constants.PREFIX_ENCODE_HTTP)) {
                LogUtil.a(CustomWebView.TAG, "not https protocol removeJavascriptInterface2");
                if ((Utils.isWhiteFileUrl(CustomWebView.this.mOriginUrl) && str.toLowerCase(Locale.ENGLISH).startsWith(Constants.PREFIX_FILE_WHITE)) || CustomWebView.this.mUnsafeUrl.equals(CustomWebView.this.mOriginUrl)) {
                    return;
                }
                CustomWebView customWebView = CustomWebView.this;
                customWebView.mUnsafeUrl = customWebView.mOriginUrl;
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.operation.view.CustomWebView.WebViewClientBase.1
                    @Override // java.lang.Runnable
                    public void run() {
                        CustomWebView.this.mWebView.removeJavascriptInterface("JsInteraction");
                        CustomWebView.this.mWebView.removeJavascriptInterface(Constants.HBS_SDK);
                        CustomWebView.this.mIsCoreJsMounted = false;
                        LogUtil.a(CustomWebView.TAG, "mUnsafeUrl removeJavascriptInterface and stopLoading!");
                        CustomWebView.this.reload(CustomWebView.this.mOriginUrl);
                    }
                });
            }
        }

        private WebResourceResponse betaVersionResult(String str) {
            if (!CommonUtil.as() || health.compact.a.Utils.o()) {
                return null;
            }
            String hostByJdk = Utils.getHostByJdk(str);
            if (TextUtils.isEmpty(hostByJdk) || hostByJdk.endsWith(Constants.CRIP_COM) || hostByJdk.endsWith(Constants.TRIP_COM) || hostByJdk.endsWith(Constants.C_CTRIP_COM)) {
                return null;
            }
            ArrayList<Boolean> booleanArrayList = getBooleanArrayList(str);
            booleanArrayList.add(Boolean.valueOf(str.contains("/healthMallPlat/")));
            boolean z = str.contains("/messageH5/") && !str.contains("/audio/");
            Iterator<Boolean> it = booleanArrayList.iterator();
            while (it.hasNext()) {
                if (it.next().booleanValue() || z) {
                    return getWebResourceResponse(str);
                }
            }
            return null;
        }

        private WebResourceResponse getWebResourceResponse(String str) {
            String str2 = CustomWebView.CONTENT_TYPE_HTML;
            try {
                URLConnection openConnection = URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
                openConnection.setRequestProperty("x-version", CustomWebView.this.mAppVersion);
                openConnection.setRequestProperty("x-huid", LoginInit.getInstance(CustomWebView.this.mContext).getAccountInfo(1011));
                String contentType = openConnection.getContentType();
                if (!str.contains(Constants.ACTIVITY_URL_CN) || TextUtils.isEmpty(contentType) || !contentType.startsWith(CustomWebView.CONTENT_TYPE_HTML)) {
                    str2 = contentType;
                }
                return new WebResourceResponse(str2, openConnection.getHeaderField("encoding"), openConnection.getInputStream());
            } catch (IllegalStateException e) {
                LogUtil.b(CustomWebView.TAG, "WebViewClientBase shouldInterceptRequest IllegalStateException", e.getMessage());
                return null;
            } catch (MalformedURLException e2) {
                LogUtil.b(CustomWebView.TAG, "WebViewClientBase shouldInterceptRequest MalformedURLException", e2.getMessage());
                return null;
            } catch (IOException unused) {
                LogUtil.b(CustomWebView.TAG, "WebViewClientBase shouldInterceptRequest IOException WebResourceResponse fail");
                return null;
            }
        }

        private void notTestVersionAndNotHttps(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if ((str.toLowerCase(Locale.ENGLISH).startsWith("http://") || str.toLowerCase(Locale.ENGLISH).startsWith(Constants.PREFIX_ENCODE_HTTP)) && !CommonUtil.cc()) {
                LogUtil.a(CustomWebView.TAG, "not https protocol removeJavascriptInterface3");
                if ((Utils.isWhiteFileUrl(CustomWebView.this.mOriginUrl) && str.toLowerCase(Locale.ENGLISH).startsWith(Constants.PREFIX_FILE_WHITE)) || CustomWebView.this.mUnsafeUrl.equals(CustomWebView.this.mOriginUrl)) {
                    return;
                }
                CustomWebView customWebView = CustomWebView.this;
                customWebView.mUnsafeUrl = customWebView.mOriginUrl;
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.operation.view.CustomWebView.WebViewClientBase.2
                    @Override // java.lang.Runnable
                    public void run() {
                        CustomWebView.this.mWebView.stopLoading();
                        CustomWebView.this.mWebView.removeJavascriptInterface("JsInteraction");
                        CustomWebView.this.mWebView.removeJavascriptInterface(Constants.HBS_SDK);
                        CustomWebView.this.mIsCoreJsMounted = false;
                        LogUtil.a(CustomWebView.TAG, "mUnsafeUrl removeJavascriptInterface and stopLoading!");
                        CustomWebView.this.reload(CustomWebView.this.mOriginUrl);
                    }
                });
            }
        }

        private WebResourceResponse webResourceResponseResult(String str) {
            ArrayList<Boolean> booleanArrayList = getBooleanArrayList(str);
            booleanArrayList.add(Boolean.valueOf(str.contains("/messageH5/")));
            Iterator<Boolean> it = booleanArrayList.iterator();
            while (it.hasNext()) {
                if (it.next().booleanValue() && !str.contains("/audio/")) {
                    return getWebResourceResponse(str);
                }
            }
            return null;
        }

        private ArrayList<Boolean> getBooleanArrayList(String str) {
            ArrayList<Boolean> arrayList = new ArrayList<>(5);
            arrayList.add(Boolean.valueOf(str.contains("/web/")));
            arrayList.add(Boolean.valueOf(str.contains("/miniShop/")));
            arrayList.add(Boolean.valueOf(str.contains("/breathePractice/")));
            arrayList.add(Boolean.valueOf(str.contains("/heartIndex/")));
            return arrayList;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            LogUtil.a(CustomWebView.TAG, "onPageFinished");
            CustomWebView.this.addVmallPressBackListner(str);
            CustomWebView.this.biServiceProcessing(webView, str);
            if (Utils.isNeedLoginWithVmallUrl(CustomWebView.this.mCurrentUrl, CustomWebView.this.mContext, CustomWebView.this)) {
                return;
            }
            CustomWebView.this.mProgressBar.setVisibility(8);
            if (!CustomWebView.this.mIsVmallCouponPage) {
                CustomWebView customWebView = CustomWebView.this;
                customWebView.isCustomTitle(customWebView.mCurrentUrl);
                CustomWebView.this.mIsVmallCouponPage = false;
            }
            if (("file:///android_asset/stressGame/html/twoVideoPlay.html".equals(str) || "file:///android_asset/stressGame/html/twoVideoPlay_old.html".equals(str)) && CustomWebView.this.mMyWebViewClientImpl != null) {
                CustomWebView.this.mMyWebViewClientImpl.onMyPageFinished();
            }
            if (CustomWebView.this.mHandler.hasMessages(2007)) {
                CustomWebView.this.mHandler.removeMessages(2007);
            }
            String healthIdVmallHost = WebViewUtils.getHealthIdVmallHost();
            LogUtil.c(CustomWebView.TAG, "onPageFinished hwidVmallHost = ", healthIdVmallHost);
            if (TextUtils.equals(str, healthIdVmallHost + UriConstants.URL_VMALL_CAS_REMOTE_LOGIN)) {
                if (System.currentTimeMillis() - CustomWebView.this.mLastVmallRemoteLoginTime < 1000) {
                    super.onPageFinished(webView, str);
                    return;
                } else {
                    CustomWebView.this.mLastVmallRemoteLoginTime = System.currentTimeMillis();
                }
            }
            onPageFinishedForVmall(webView, str);
            Message obtainMessage = CustomWebView.this.mHandler.obtainMessage(10086);
            obtainMessage.obj = str;
            CustomWebView.this.mHandler.sendMessage(obtainMessage);
            super.onPageFinished(webView, str);
        }

        private void onPageFinishedForVmall(WebView webView, String str) {
            if (WebViewUtils.isUpLoginUrl(str)) {
                String autoLoginUrl = CasLoginUtil.getAutoLoginUrl();
                if (!TextUtils.isEmpty(autoLoginUrl) && CustomWebView.this.mWebView != null) {
                    WebView webView2 = CustomWebView.this.mWebView;
                    webView2.loadUrl(autoLoginUrl);
                    WebViewInstrumentation.loadUrl(webView2, autoLoginUrl);
                }
            }
            if (WebViewUtils.isVmallMcpLoginUrl(str)) {
                String url = WebViewUtils.getUrl("window.java_obj.showSource(document.getElementsByTagName", "pre", "[0].innerHTML);");
                webView.loadUrl(url);
                WebViewInstrumentation.loadUrl(webView, url);
                String url2 = WebViewUtils.getUrl("document.getElementsByTagName", Constants.SUFFIX_HTML, "[0].style.visibility='hidden';");
                webView.loadUrl(url2);
                WebViewInstrumentation.loadUrl(webView, url2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            LogUtil.a(CustomWebView.TAG, "onReceivedError errorCode is ", Integer.valueOf(i), "description is ", str);
            CustomWebView.this.mHandler.sendEmptyMessage(2001);
            webView.loadUrl(" ");
            WebViewInstrumentation.loadUrl(webView, " ");
            super.onReceivedError(webView, i, str, str2);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            LogUtil.a(CustomWebView.TAG, "onReceivedHttpError ", "getStatusCode = ", Integer.valueOf(webResourceResponse.getStatusCode()), " getReasonPhrase", webResourceResponse.getReasonPhrase());
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (sslError == null) {
                LogUtil.h(CustomWebView.TAG, "onReceivedSslError error is null");
                return;
            }
            LogUtil.a(CustomWebView.TAG, "onReceivedSslError errorType is ", Integer.valueOf(sslError.getPrimaryError()));
            WebViewUtils.processSslError(sslError);
            WebViewSSLCheck.checkServerCertificateNew(sslErrorHandler, sslError, CustomWebView.this.mContext);
        }

        @Override // android.webkit.WebViewClient
        public void doUpdateVisitedHistory(final WebView webView, final String str, final boolean z) {
            jdx.b(new Runnable() { // from class: com.huawei.operation.view.CustomWebView$WebViewClientBase$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    CustomWebView.WebViewClientBase.this.m762x4d9ddc95(str, webView, z);
                }
            });
        }

        /* renamed from: lambda$doUpdateVisitedHistory$1$com-huawei-operation-view-CustomWebView$WebViewClientBase, reason: not valid java name */
        /* synthetic */ void m762x4d9ddc95(final String str, final WebView webView, final boolean z) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (str.contains(UriConstants.OAUTH_URL_LOGINCALLBACK)) {
                CustomWebView.this.mIsHasLoginCallbackUrl = true;
            }
            String healthRecommendHost = WebViewUtils.getHealthRecommendHost();
            LogUtil.c(CustomWebView.TAG, "doUpdateVisitedHistory healthRecommendHost = ", healthRecommendHost);
            if (str.startsWith(healthRecommendHost + UriConstants.URL_VMALL_SIGN)) {
                CustomWebView.this.mIsHasVmallSignUrl = true;
            }
            if (WebViewUtils.isUpLoginUrl(str) || WebViewUtils.isVmallMcpLoginUrl(str)) {
                CustomWebView.this.mIsHasVmallSignUrl = true;
            }
            if (str.contains("vmall.com")) {
                CustomWebView.this.mIsHasVmallSignUrl = true;
            }
            if (!CommonUtil.aa(CustomWebView.this.mContext) && !str.contains(ConfigConstants.BREATHE_URL)) {
                if (CustomWebView.this.mHandler != null) {
                    CustomWebView.this.mHandler.sendEmptyMessage(2001);
                } else {
                    LogUtil.h(CustomWebView.TAG, "doUpdateVisitedHistory mHandler is null");
                }
            }
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.operation.view.CustomWebView$WebViewClientBase$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    CustomWebView.WebViewClientBase.this.m761x3ce80fd4(webView, str, z);
                }
            });
        }

        /* renamed from: lambda$doUpdateVisitedHistory$0$com-huawei-operation-view-CustomWebView$WebViewClientBase, reason: not valid java name */
        /* synthetic */ void m761x3ce80fd4(WebView webView, String str, boolean z) {
            LogUtil.a(CustomWebView.TAG, "doUpdateVisitedHistory: call super");
            super.doUpdateVisitedHistory(webView, str, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void urlVerification(String str) {
        if (CommonUtil.cc()) {
            LogUtil.h(TAG, "urlVerification: test");
            return;
        }
        if (this.mCurrentUrl.toLowerCase(Locale.ENGLISH).startsWith("https://")) {
            LogUtil.h(TAG, "urlVerification: https");
            return;
        }
        if (Utils.isWhiteFileUrl(str)) {
            return;
        }
        LogUtil.a(TAG, "urlVerification: removeJavascriptInterface");
        this.mWebView.removeJavascriptInterface("JsInteraction");
        this.mWebView.removeJavascriptInterface(Constants.HBS_SDK);
        this.mWebView.removeJavascriptInterface("innerapi");
        this.mWebView.removeJavascriptInterface("tradeApi");
        this.mIsCoreJsMounted = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isLoginHealthVmall() {
        LogUtil.a(TAG, "isLoginHealthVmall");
        this.mVmallUrl = this.mCurrentUrl;
        if (sIsWebViewNoFirstLogin || !this.mIsAuthVmall) {
            return true;
        }
        this.mHandler.sendEmptyMessageDelayed(4001, OpAnalyticsConstants.H5_LOADING_DELAY);
        this.mWebView.stopLoading();
        startVmallLogin();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void biServiceProcessing(WebView webView, String str) {
        InformationBiOperate informationBiOperate = this.mInformationBiOperate;
        if (informationBiOperate != null) {
            informationBiOperate.setBrowseBiAnalytics(this.mContext, str, webView.getTitle());
        }
        if (TextUtils.isEmpty(str) || str.equals(this.sBiCurrentUrl)) {
            return;
        }
        if (!TextUtils.isEmpty(this.sBiCurrentUrl)) {
            ixx.d().a(getBiIdFromUrl(this.sBiCurrentUrl), getUrlLifeCycleBiMap(this.sBiCurrentUrl));
        }
        this.sBiCurrentUrl = str;
        ixx.d().e(getBiIdFromUrl(this.sBiCurrentUrl), getUrlLifeCycleBiMap(this.sBiCurrentUrl));
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(2);
        linkedHashMap.put("flag", "0");
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_WEBVIEW_LOADING_SUCCESS_RATE_80060001.value(), linkedHashMap);
        this.mLoadEndTime = System.currentTimeMillis();
        setTimeBiAnalysis();
    }

    @Override // com.huawei.operation.adapter.SendCurrentUrlCallback
    public String getCurrentUrl() {
        if (TextUtils.isEmpty(this.mCurrentUrl)) {
            LogUtil.a(TAG, "getCurrentUrl mCurrentUrl is empty");
            return "";
        }
        LogUtil.a(TAG, "getCurrentUrl mCurrentUrl != null");
        return this.mCurrentUrl;
    }

    @Override // com.huawei.operation.adapter.SendCurrentUrlCallback
    public String getWebViewUrl() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return getWebViewUrlUiThread();
        }
        return TextUtils.isEmpty(this.mCurrentUrl) ? getWebViewUrlNotUiThread() : this.mCurrentUrl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getWebViewUrlUiThread() {
        try {
            return this.mWebView.getUrl();
        } catch (Throwable unused) {
            LogUtil.b(TAG, "getWebViewUrlUiThread error");
            return "";
        }
    }

    private String getWebViewUrlNotUiThread() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            this.mWebView.post(new Runnable() { // from class: com.huawei.operation.view.CustomWebView.14
                @Override // java.lang.Runnable
                public void run() {
                    String unused = CustomWebView.sWebViewUrl = CustomWebView.this.getWebViewUrlUiThread();
                    if (TextUtils.isEmpty(CustomWebView.sWebViewUrl)) {
                        LogUtil.b(CustomWebView.TAG, "getWebViewUrl is null.");
                    }
                    countDownLatch.countDown();
                }
            });
            if (!countDownLatch.await(ProfileExtendConstants.TIME_OUT, TimeUnit.MILLISECONDS)) {
                LogUtil.b(TAG, " webView await false");
            }
        } catch (InterruptedException e) {
            LogUtil.b(TAG, "getWebViewUrl InterruptedException: ", e.getMessage());
        }
        return sWebViewUrl;
    }

    @Override // com.huawei.operation.adapter.SetTitleCallback
    public void setTitle(final String str) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.operation.view.CustomWebView.15
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a(CustomWebView.TAG, "setTitle");
                CustomWebView.this.setBrowserTitle(str);
            }
        });
    }

    @Override // com.huawei.operation.adapter.SetTitleCallback
    public void setTitleRightBlank(boolean z) {
        LogUtils.i(TAG, "setTitleRightBlank");
        if (this.mHandler != null) {
            Message obtain = Message.obtain();
            obtain.what = 2009;
            obtain.obj = Boolean.valueOf(z);
            this.mHandler.sendMessage(obtain);
            return;
        }
        LogUtil.h(TAG, "setTitleRightBlank mHandler is null");
    }

    @Override // com.huawei.operation.adapter.ShareCallback
    public void onShare(String str, String str2) {
        LogUtil.a(TAG, "onShare activityId = ", str, "  shareType:", str2);
        ShareConfig shareConfig = this.mShareConfig;
        if (shareConfig != null) {
            shareConfig.obtainShareConfig(str, str2, this);
        }
    }

    @Override // com.huawei.operation.adapter.ShareCallback
    public void onShare(String str, String str2, String str3, String str4) {
        ShareConfig shareConfig = this.mShareConfig;
        if (shareConfig != null) {
            shareConfig.obtainShareConfig(str, str2, str3, str4, this);
        }
    }

    @Override // com.huawei.operation.adapter.OnCaptureExtCallback
    public void onShare(String str, String str2, HashMap<String, Object> hashMap) {
        new CaptureUtils(this.mContext).share(str, str2, hashMap);
    }

    @Override // com.huawei.operation.adapter.OnCaptureCallback
    public void onShare(String str) {
        new CaptureUtils(this.mContext).share(str);
    }

    @Override // com.huawei.operation.adapter.OnCaptureCallback
    public void onShareMultiple(String str, int i) {
        new CaptureUtils(this.mContext).share(str, i);
    }

    @Override // com.huawei.operation.share.ShareConfigCallback
    public void onShareConfig(String str, String str2, Bitmap bitmap, String str3) {
        LogUtil.a(TAG, "onShareConfig");
        if (this.mAdapter != null) {
            fdu fduVar = new fdu(2);
            fduVar.awp_(bitmap);
            fduVar.f(str3);
            fduVar.c(str);
            fduVar.a(str2);
            if (this.mIsInformation) {
                fduVar.b("14");
            } else {
                fduVar.b(AnalyticsValue.SUCCESSES_SHARE_1100002.value());
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("name", str);
            linkedHashMap.put(Constants.BI_ACTIVITY_SHARE_CONTENT, 1);
            fduVar.b((Map<String, Object>) linkedHashMap);
            this.mAdapter.share(this.mContext.getApplicationContext(), fduVar, new IBaseResponseCallback() { // from class: com.huawei.operation.view.CustomWebView.16
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a(CustomWebView.TAG, "IBaseResponseCallback errCode:", Integer.valueOf(i));
                }
            });
        }
    }

    @Override // com.huawei.operation.adapter.StartMiniShopWebPage
    public void onStartMiniShopWebPage(String str, String str2) {
        LogUtil.a(TAG, "onStartMiniShopWebPage type is ", str2);
        Message obtainMessage = this.mHandler.obtainMessage(2004);
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        bundle.putString("type", str2);
        obtainMessage.setData(bundle);
        this.mHandler.sendMessage(obtainMessage);
    }

    @Override // com.huawei.operation.adapter.StartWebPage
    public void onStartWebPage(String str) {
        LogUtil.a(TAG, "onStartWebPage");
        Message obtainMessage = this.mHandler.obtainMessage(2016);
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        obtainMessage.setData(bundle);
        this.mHandler.sendMessage(obtainMessage);
    }

    @Override // com.huawei.operation.adapter.StartAppSettingPage
    public void onStartAppSettingPage() {
        LogUtil.a(TAG, "onStartAppSettingPage");
        Message obtainMessage = this.mHandler.obtainMessage(2018);
        obtainMessage.setData(new Bundle());
        this.mHandler.sendMessage(obtainMessage);
    }

    @Override // com.huawei.operation.adapter.TouchSignalCallback
    public void onTouchSignalCallback(boolean z) {
        LogUtil.c(TAG, "onTouchSignalCallback flag is ", Boolean.valueOf(z));
    }

    @Override // com.huawei.operation.adapter.AchievementShareCallback
    public void onAchievementShare(String str, String str2) {
        LogUtil.a(TAG, "onAchievementShare");
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        if (pluginOperationAdapter != null) {
            pluginOperationAdapter.startAchieveAwardShare(this.mContext, str, str2);
        } else {
            LogUtil.a(TAG, "onAchievementShare mAdapter is null");
        }
    }

    @Override // com.huawei.operation.adapter.StartFitnessPageCallback
    public void onStartFitnessPage(Context context, String str, String str2) {
        LogUtil.a(TAG, "onStartFitnessPage");
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        if (pluginOperationAdapter != null) {
            pluginOperationAdapter.startFitnessPage(context, str, str2);
        } else {
            LogUtil.a(TAG, "onStartFitnessPage mAdapter is null");
        }
    }

    @Override // com.huawei.operation.adapter.StartGpsTrackPageCallback
    public void onStartGpsTrackPage(Context context, int i, String str, float f) {
        LogUtil.a(TAG, "onStartGpsTrackPage sportType:", Integer.valueOf(i), " targetType:", str, " targetValue:", Float.valueOf(f));
        PluginOperationAdapter pluginOperationAdapter = this.mAdapter;
        if (pluginOperationAdapter != null) {
            pluginOperationAdapter.startGpsTrackPage(context, i, str, f);
        } else {
            LogUtil.a(TAG, "onStartGpsTrackPage mAdapter is null");
        }
    }

    @Override // com.huawei.operation.adapter.CloseWebCallback
    public void onCloseWebCallback() {
        if (this.mActivity == null || this.mType == 3001) {
            return;
        }
        LogUtil.a(TAG, "onCloseWebCallback");
        this.mActivity.finish();
    }

    @Override // com.huawei.operation.adapter.VmallLoginCallback
    public void onVmallLoginCallback(int i, Object obj) {
        LogUtil.a(TAG, "onVmallLoginCallback");
        if (i != 0 || sIsWebViewNoFirstLogin) {
            return;
        }
        setIsWebViewNoFirstLogin(true);
        sInnerHandler.sendEmptyMessageDelayed(4001, 1200000L);
        LogUtil.a(TAG, "onPageStarted isHealthVmall removeMessages(MAG_WEB_VIEW_LOAD)");
        this.mHandler.removeMessages(4001);
        if (this.mWebView != null) {
            LogUtil.a(TAG, "pagerStartedWeb != null");
            Message obtainMessage = this.mHandler.obtainMessage(2016);
            Bundle bundle = new Bundle();
            bundle.putString("url", this.mVmallUrl);
            obtainMessage.setData(bundle);
            this.mHandler.sendMessage(obtainMessage);
        }
    }

    @Override // com.huawei.operation.adapter.CloseWebCallback
    public void goBackToMiniShop() {
        LogUtil.a(TAG, "goBackToMiniShop");
        Intent intent = new Intent();
        try {
            intent.setComponent(new ComponentName(Constants.APP_PACKAGE, this.mContext.getPackageManager().getLaunchIntentForPackage(Constants.APP_PACKAGE).getComponent().getClassName()));
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setFlags(AppRouterExtras.COLDSTART);
            this.mContext.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.a(TAG, "activity not found exception.");
        } catch (IllegalStateException e) {
            LogUtil.b(TAG, "goBackToMiniShop ", e.getMessage());
        }
        Intent intent2 = new Intent();
        intent2.setAction("com.huawei.plugin.operation.action_jumt_to_fearture_page");
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).sendBroadcast(intent2);
    }

    @Override // com.huawei.operation.adapter.ToastCallback
    public void onToast(String str, String str2) {
        LogUtil.a(TAG, "onToast");
        if (this.mHandler != null) {
            LogUtil.a(TAG, "onToast not null");
            this.mHandler.obtainMessage(2005, str).sendToTarget();
        }
    }

    @Override // com.huawei.operation.adapter.SendServerErrorMsgCallback
    public void onSendServerErrorMsgCallback() {
        LogUtil.a(TAG, "onSendServerErrorMsgCallback");
        if (this.mHandler != null) {
            LogUtil.a(TAG, "onSendServerErrorMsgCallback not null");
            this.mHandler.obtainMessage(2006).sendToTarget();
        }
    }

    @Override // com.huawei.operation.adapter.SendNoNetMsgCallback
    public void onSendNoNetMsgCallback() {
        LogUtil.a(TAG, "onSendNoNetMsgCallBack");
        if (this.mHandler != null) {
            LogUtil.a(TAG, "onSendNoNetMsgCallBack not null");
            this.mHandler.obtainMessage(2003).sendToTarget();
        }
    }

    @Override // com.huawei.operation.adapter.OnLoginCallback
    public void onLogin(String str, String str2, String str3) {
        LogUtil.a(TAG, "onLogin");
        if (this.mHandler != null) {
            LogUtil.a(TAG, "onLogin is beginning send Message to WebViewActivity");
            Bundle bundle = new Bundle();
            bundle.putString("huid", str);
            bundle.putString("serviceId", str2);
            bundle.putString("function", str3);
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.what = 2010;
            obtainMessage.setData(bundle);
            this.mHandler.sendMessage(obtainMessage);
        }
    }

    @Override // com.huawei.operation.adapter.JsDataCallback
    public void callJsSportDataFunction(String str, String str2, String str3, boolean z) {
        LogUtil.a(TAG, "callJsSportDataFunction enter");
        if (this.mHandler != null) {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.JS_PARAM, str);
            bundle.putString(Constants.JS_FUNCTION_RES, str2);
            bundle.putString(Constants.JS_FUNCTION_ERR, str3);
            bundle.putBoolean(Constants.IS_LEGAL, z);
            Message obtainMessage = this.mHandler.obtainMessage(2011);
            obtainMessage.setData(bundle);
            this.mHandler.sendMessage(obtainMessage);
        }
    }

    @Override // com.huawei.operation.adapter.JsDataCallback
    public void callJsServiceFunction(String str, String str2, String str3, String str4, boolean z) {
        LogUtil.a(TAG, "callJsServiceFunction enter");
        if (this.mHandler != null) {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.JS_SERVICE_TYPE, str);
            bundle.putString(Constants.JS_FUNCTION_RES, str4);
            bundle.putString(Constants.JS_FUNC_TYPE, str2);
            bundle.putString(Constants.JS_PARAM, str3);
            bundle.putBoolean(Constants.IS_LEGAL, z);
            Message obtainMessage = this.mHandler.obtainMessage(2013);
            obtainMessage.setData(bundle);
            this.mHandler.sendMessage(obtainMessage);
        }
    }

    @Override // com.huawei.operation.adapter.OnWebViewStatusCallback
    public void onWebViewStatusCallback(String str) {
        if (this.mHandler != null) {
            this.mWebViewStatusFunction = str;
        }
    }

    @Override // com.huawei.operation.adapter.SleepQuestionnaireCallback
    public void callSleepQuestionnaireJsFunction(final int i, final String str, final String str2) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.operation.view.CustomWebView.17
            @Override // java.lang.Runnable
            public void run() {
                String str3;
                if (TextUtils.isEmpty(str2)) {
                    str3 = "";
                } else {
                    str3 = WebViewUtils.getUrl(str2, i + "', '" + str);
                }
                if (!TextUtils.isEmpty(str3)) {
                    WebView webView = CustomWebView.this.mWebView;
                    webView.loadUrl(str3);
                    WebViewInstrumentation.loadUrl(webView, str3);
                    return;
                }
                LogUtil.c(CustomWebView.TAG, "TextUtils.isEmpty(callJs)2");
            }
        });
    }

    @Override // com.huawei.operation.adapter.VmallArgSignCallback
    public void callVmallArgSignJsFunction(final int i, final String str, final String str2) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.operation.view.CustomWebView.18
            @Override // java.lang.Runnable
            public void run() {
                String str3;
                CustomWebView.this.judgeTriggerVmallLogin(i, str);
                if (TextUtils.isEmpty(str2)) {
                    str3 = "";
                } else {
                    str3 = WebViewUtils.getUrl(str2, i + "', '" + str);
                }
                if (!TextUtils.isEmpty(str3)) {
                    WebView webView = CustomWebView.this.mWebView;
                    webView.loadUrl(str3);
                    WebViewInstrumentation.loadUrl(webView, str3);
                    return;
                }
                LogUtil.c(CustomWebView.TAG, "TextUtils.isEmpty(callJs)3");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005d, code lost:
    
        if (new org.json.JSONObject(r9).getInt("errorCode") != 0) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void judgeTriggerVmallLogin(int r8, java.lang.String r9) {
        /*
            r7 = this;
            java.lang.String r0 = "agrType"
            boolean r1 = android.text.TextUtils.isEmpty(r9)
            java.lang.String r2 = "PluginOperation_CustomWebView"
            if (r1 == 0) goto L14
            java.lang.String r8 = "responseData is null! "
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r8)
            return
        L14:
            boolean r1 = com.huawei.operation.view.CustomWebView.sIsWebViewNoFirstLogin
            if (r1 != 0) goto L9a
            r1 = 200(0xc8, float:2.8E-43)
            if (r8 != r1) goto L9a
            r8 = 0
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: org.json.JSONException -> L49
            r1.<init>(r9)     // Catch: org.json.JSONException -> L49
            java.lang.String r3 = "signInfo"
            org.json.JSONArray r1 = r1.getJSONArray(r3)     // Catch: org.json.JSONException -> L49
            r3 = r8
        L2a:
            int r4 = r1.length()     // Catch: org.json.JSONException -> L49
            if (r3 >= r4) goto L52
            org.json.JSONObject r4 = r1.getJSONObject(r3)     // Catch: org.json.JSONException -> L49
            int r5 = r4.getInt(r0)     // Catch: org.json.JSONException -> L49
            r6 = 134(0x86, float:1.88E-43)
            if (r5 == r6) goto L44
            int r4 = r4.getInt(r0)     // Catch: org.json.JSONException -> L49
            r5 = 10023(0x2727, float:1.4045E-41)
            if (r4 != r5) goto L46
        L44:
            int r8 = r8 + 1
        L46:
            int r3 = r3 + 1
            goto L2a
        L49:
            java.lang.String r0 = "Vmall query JSONException"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r0)
        L52:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: org.json.JSONException -> L60
            r0.<init>(r9)     // Catch: org.json.JSONException -> L60
            java.lang.String r9 = "errorCode"
            int r9 = r0.getInt(r9)     // Catch: org.json.JSONException -> L60
            if (r9 == 0) goto L6c
            goto L69
        L60:
            java.lang.String r9 = "Vmall sign JSONException"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r9)
        L69:
            r9 = 2
            if (r8 != r9) goto La3
        L6c:
            r8 = 1
            r7.mIsAuthVmall = r8
            java.lang.String r8 = "shouldOverrideUrlLoading isHealthVmall sendEmptyMessageDelayed DELAY"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r8)
            android.os.Handler r8 = r7.mHandler
            r9 = 4001(0xfa1, float:5.607E-42)
            r0 = 30000(0x7530, double:1.4822E-319)
            r8.sendEmptyMessageDelayed(r9, r0)
            java.lang.String r8 = "loadUrlVmall mIsWebViewNoFirstLogin = false mWebView.stopLoading()"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r8)
            android.webkit.WebView r8 = r7.mWebView
            r9 = 4
            r8.setVisibility(r9)
            android.webkit.WebView r8 = r7.mWebView
            r8.stopLoading()
            r7.startVmallLogin()
            goto La3
        L9a:
            java.lang.String r8 = "resCode Error && !mIsWebViewNoFirstLogin."
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r8)
        La3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.operation.view.CustomWebView.judgeTriggerVmallLogin(int, java.lang.String):void");
    }

    @Override // com.huawei.operation.adapter.OnActivityQuitCallback
    public void onActivityQuitCallback(String str) {
        if (this.mHandler != null) {
            setRegisterActivityQuitFunctionRes(str);
            Message obtain = Message.obtain();
            obtain.what = 2014;
            this.mHandler.sendMessage(obtain);
        }
    }

    @Override // com.huawei.operation.adapter.OnVmallCouponCallback
    public void onVmallCouponCallback(String str) {
        if (this.mHandler != null) {
            setRegisterVmallCouponFunctionRes(str);
            Message obtain = Message.obtain();
            obtain.what = 2017;
            this.mHandler.sendMessage(obtain);
            this.mIsVmallCouponPage = true;
        }
    }

    @Override // com.huawei.operation.adapter.OnCaptureCallback
    public void onParseFunction(String str) {
        this.mCaptureFunction = str;
    }

    @Override // com.huawei.operation.adapter.OnCaptureCallback
    public void onCapture(String str) {
        if (this.mWebView != null) {
            LogUtil.a(TAG, "onCapture");
            new CaptureUtils(this.mContext).capture(this.mWebView, str);
        }
    }

    @Override // com.huawei.operation.adapter.OnCaptureCallback
    public void onNoGranted(String str) {
        if (this.mWebView != null) {
            LogUtil.a(TAG, "onNoGranted");
            new CaptureUtils(this.mContext).captureNoPermission(this.mWebView, str);
        }
    }

    @Override // com.huawei.operation.adapter.OnCaptureCallback
    public void onCustomUserPrefSet(final String str, final String str2, final String str3) {
        jdx.b(new Runnable() { // from class: com.huawei.operation.view.CustomWebView.19
            @Override // java.lang.Runnable
            public void run() {
                HiUserPreference hiUserPreference = new HiUserPreference();
                hiUserPreference.setKey(str2);
                hiUserPreference.setValue(str3);
                final boolean userPreference = HiHealthManager.d(BaseApplication.getContext()).setUserPreference(hiUserPreference);
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.operation.view.CustomWebView.19.1
                    @Override // java.lang.Runnable
                    public void run() {
                        WebView webView = CustomWebView.this.mWebView;
                        String url = WebViewUtils.getUrl(str, String.valueOf(userPreference));
                        webView.loadUrl(url);
                        WebViewInstrumentation.loadUrl(webView, url);
                    }
                });
            }
        });
    }

    private void interceptVmallHomePage(String str) {
        String str2 = this.mMobileVmallHost + "/";
        if (TextUtils.isEmpty(this.mMobileVmallHost) || !str2.equals(str)) {
            return;
        }
        String shopHomePageUrl = OperationUtils.getShopHomePageUrl();
        if (TextUtils.isEmpty(shopHomePageUrl)) {
            return;
        }
        this.mWebView.stopLoading();
        reload(shopHomePageUrl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void interceptMyhuawei(String str) {
        String url = this.mWebView.getUrl();
        LogUtil.a(TAG, "interceptMyhuawei with ", str, " page: ", url);
        Map<String, Object> myHuaweiLogin = MyHuaweiLogin.getMyHuaweiLogin(str, url, this.mIsFirstLoadMyHuawei);
        boolean d = nru.d((Map) myHuaweiLogin, MyHuaweiLogin.IS_START_LOGIN, false);
        final String b = nru.b(myHuaweiLogin, MyHuaweiLogin.RELOAD_URL, str);
        if (this.mIsLoadMyHuaweiFail || !d) {
            return;
        }
        LogUtil.a(TAG, "interceptMyhuawei reloadUrl with ", b);
        this.mWebView.stopLoading();
        MyHuaweiLogin.handleMyHuaweiLogin(new UiCallback<String>() { // from class: com.huawei.operation.view.CustomWebView.20
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str2) {
                ReleaseLogUtil.c(CustomWebView.TAG, "login Myhuawei for error");
                CustomWebView.this.mIsLoadMyHuaweiFail = true;
                CustomWebView.this.reload(b);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onSuccess(String str2) {
                LogUtil.a(CustomWebView.TAG, "set cookie ", str2);
                CookieManager.getInstance().setCookie(b, str2);
                CustomWebView.this.mIsFirstLoadMyHuawei = false;
                CustomWebView.this.reload(b);
            }
        }, this.mActivity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void interceptUpLoginPage(String str) {
        if (str.contains(this.mMobileVmallHost + ConfigConstants.VMALL_WAP_LOGIN_URL)) {
            this.mWebView.stopLoading();
            vmallAtLogin(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void interceptLoginPage(String str) {
        if (str.contains(this.mMobileVmallHost + "/account/applogin?url=/personal")) {
            setLoginStatus(false);
            processVmallLogin();
        }
    }

    private void vmallAtLogin(boolean z) {
        LogUtil.a(TAG, "enter vmallAtLogin");
        if (TextUtils.isEmpty(this.mCurrentUrl)) {
            return;
        }
        final String str = this.mCurrentUrl;
        if (!z) {
            String d = gic.d(gic.d(str));
            str = d.substring(d.indexOf(Constants.SUFFIX_URL) + 4);
            if (!str.toLowerCase(Locale.ENGLISH).startsWith("https://")) {
                str = this.mMobileVmallHost + str;
            }
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        AuthVmall.vmallAtLogin(this.mContext, new IBaseResponseCallback() { // from class: com.huawei.operation.view.CustomWebView.21
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0) {
                    LogUtil.a(CustomWebView.TAG, "vmallAtLogin success.");
                    if (!TextUtils.isEmpty(str)) {
                        CustomWebView.this.mWebView.post(new Runnable() { // from class: com.huawei.operation.view.CustomWebView.21.1
                            @Override // java.lang.Runnable
                            public void run() {
                                CustomWebView.this.mHandler.removeMessages(4001);
                                CustomWebView.setLoginStatus(true);
                                CustomWebView.this.mWebView.stopLoading();
                                AuthVmall.setCookie(str);
                                CustomWebView.this.reload(str);
                            }
                        });
                    }
                } else {
                    LogUtil.b(CustomWebView.TAG, "vmallAtLogin failed:", Integer.valueOf(i));
                }
                countDownLatch.countDown();
            }
        });
        try {
            if (countDownLatch.await(2L, TimeUnit.SECONDS)) {
                return;
            }
        } catch (InterruptedException e) {
            LogUtil.b(TAG, "startVmallLoginByAt InterruptedException ie = ", e.getMessage());
        }
        reload(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDarkMode(String str) {
        if (this.mWebView == null) {
            return;
        }
        if (!TextUtils.isEmpty(str) && str.contains(this.mAnnualUrlPre) && Build.VERSION.SDK_INT >= 29) {
            this.mWebView.getSettings().setForceDark(0);
        } else {
            nrt.cKg_(this.mContext, this.mWebView);
        }
    }

    public void setAnnualUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mAnnualUrlPre = str;
    }

    public void setMobileVmallHostUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mMobileVmallHost = str;
    }

    @Override // com.huawei.operation.adapter.OnActivityShowButtonCallback
    public void onShowShareButtonWithFunc(String str) {
        if (this.mHandler != null) {
            if (!TextUtils.isEmpty(str)) {
                setmRegisterActivityShareFunctionRes(str);
            } else {
                LogUtil.h(TAG, "onShowShareButtonWithFunc functionStr is empty");
            }
            Message obtain = Message.obtain();
            obtain.what = 2030;
            this.mHandler.sendMessage(obtain);
            return;
        }
        LogUtil.h(TAG, "onShowShareButtonWithFunc mHandler is null1");
    }

    @Override // com.huawei.operation.adapter.OnActivityShowButtonCallback
    public void onShowShareButtonWithId(String str, String str2) {
        if (this.mHandler != null) {
            Message obtain = Message.obtain();
            obtain.what = 2030;
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                Bundle bundle = new Bundle();
                bundle.putString("activityId", str);
                bundle.putString(Constants.ACTIVITY_SHARE_TYPE, str2);
                obtain.setData(bundle);
            } else {
                LogUtil.h(TAG, "onShowShareButtonWithId activityId or shareType is empty");
            }
            this.mHandler.sendMessage(obtain);
            return;
        }
        LogUtil.h(TAG, "onShowShareButtonWithFunc mHandler is null2");
    }

    @Override // com.huawei.operation.adapter.OnActivityShowButtonCallback
    public void onShowRightButtonWithType(int i) {
        if (this.mHandler != null) {
            Message obtain = Message.obtain();
            obtain.what = 2031;
            obtain.arg1 = i;
            this.mHandler.sendMessage(obtain);
            return;
        }
        LogUtil.h(TAG, "onShowShareButtonWithFunc mHandler is null3");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.operation.adapter.StartSportPage
    public void startToSportPage(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1943875629:
                if (str.equals("indoor_running")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1346536324:
                if (str.equals(Constants.OUTDOOR_RUNNING)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1062484062:
                if (str.equals(Constants.MAIN_HEALTH)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -168965370:
                if (str.equals("calories")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 3500280:
                if (str.equals(Constants.RIDE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 985074424:
                if (str.equals(Constants.SLEEP_CARD_DETAIL)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1669513245:
                if (str.equals(Constants.SPORT_FITNESS)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1691801664:
                if (str.equals(Constants.SPORT_RECORD_SWIMMING)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                startPageMsg(Constants.START_TO_INDOOR_RUNNING);
                break;
            case 1:
                startPageMsg(2039);
                break;
            case 2:
                startPageMsg(Constants.START_TO_MAIN_ACTIVITY);
                break;
            case 3:
                startPageMsg(2038);
                break;
            case 4:
                startPageMsg(2034);
                break;
            case 5:
                startPageMsg(2033);
                break;
            case 6:
                startPageMsg(2037);
                break;
            case 7:
                startPageMsg(2035);
                break;
            default:
                LogUtil.c(TAG, "handleStartToSportPage default.");
                break;
        }
    }

    private void startPageMsg(final int i) {
        this.mWebView.post(new Runnable() { // from class: com.huawei.operation.view.CustomWebView.22
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a(CustomWebView.TAG, "startToSportPage", Integer.valueOf(i));
                CustomWebView.this.mHandler.obtainMessage(i, "").sendToTarget();
            }
        });
    }

    public String getBiIdFromUrl(String str) {
        return TextUtils.isEmpty(str) ? "" : Utils.getHostByJdk(str).replace(".", "_");
    }

    public LinkedHashMap<String, String> getUrlLifeCycleBiMap(String str) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        if (!TextUtils.isEmpty(str)) {
            linkedHashMap.put("url", str);
        }
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void interceptPayUrl(String str) {
        Context context = this.mContext;
        if (context == null || !(context instanceof Activity)) {
            return;
        }
        Activity activity = (Activity) context;
        if (com.huawei.health.h5pro.utils.CommonUtil.isShouldSelfProtection(context, str, false)) {
            activity.getWindow().addFlags(8192);
        } else {
            activity.getWindow().clearFlags(8192);
        }
    }
}
