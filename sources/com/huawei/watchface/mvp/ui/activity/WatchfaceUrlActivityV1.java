package com.huawei.watchface.mvp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.secure.android.common.intent.SafeBroadcastReceiver;
import com.huawei.secure.android.common.util.UrlUtil;
import com.huawei.secure.android.common.webview.SafeWebSettings;
import com.huawei.secure.android.common.webview.SafeWebView;
import com.huawei.secure.android.common.webview.WebViewLoadCallBack;
import com.huawei.uikit.phone.hwtextview.widget.HwTextView;
import com.huawei.watchface.R$color;
import com.huawei.watchface.R$drawable;
import com.huawei.watchface.R$id;
import com.huawei.watchface.R$layout;
import com.huawei.watchface.R$string;
import com.huawei.watchface.api.WebViewActivity;
import com.huawei.watchface.as;
import com.huawei.watchface.at;
import com.huawei.watchface.dc;
import com.huawei.watchface.dj;
import com.huawei.watchface.dw;
import com.huawei.watchface.ef;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.eo;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.mvp.base.BaseActivity;
import com.huawei.watchface.mvp.model.helper.JsSafeUrlSystemParamManager;
import com.huawei.watchface.mvp.ui.activity.WatchfaceUrlActivityV1;
import com.huawei.watchface.mvp.ui.widget.HwToolbar;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.LanguageUtils;
import com.huawei.watchface.utils.SafeHandler;
import com.huawei.watchface.utils.WebViewUtils;
import com.huawei.watchface.utils.callback.IBaseResponseCallback;
import com.huawei.watchface.videoedit.utils.SafeIntent;
import java.util.List;
import java.util.Objects;

/* loaded from: classes9.dex */
public class WatchfaceUrlActivityV1 extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "WatchfaceV1";
    private ef mAnalyticsH5Event;
    private Button mBtnNetSetting;
    private Activity mContext;
    private SafeHandler mHandler;
    private boolean mIsNetworkAvailable;
    private View mIvBack;
    private LinearLayout mLoadingLayout;
    private LinearLayout mMainLayout;
    private b mNetworkBroadcastReceiver;
    private RelativeLayout mNoNetWorkLayout;
    private TextView mNoNetworkTips;
    private ProgressBar mProgressBar;
    private ImageView mTipsImageView;
    private String mTitle;
    private View mToolBar;
    private HwTextView mTvTitle;
    private String mWebSource;
    private int mWebType;
    private SafeWebView mWebView;

    @Override // com.huawei.watchface.mvp.base.BaseActivity, com.huawei.secure.android.common.activity.SafeFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        this.mHandler = new a(this);
        initIntent();
        if (CommonUtils.e(this.mContext.getApplicationContext())) {
            setContentView(R$layout.watchface_fragment_web_view_error_1);
            initErrorView();
        } else {
            setContentView(R$layout.watchface_activity_url_item);
            HwLog.i(TAG, "onCreate()");
            initView();
        }
    }

    @Override // com.huawei.watchface.mvp.base.BaseActivity, com.huawei.secure.android.common.activity.SafeFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        registerNetworkReceiver();
    }

    private void initIntent() {
        if (getIntent() == null) {
            finish();
            return;
        }
        SafeIntent safeIntent = new SafeIntent(getIntent());
        int intExtra = safeIntent.getIntExtra("WebType", 102);
        this.mWebType = intExtra;
        if (intExtra == 102) {
            String stringExtra = safeIntent.getStringExtra("WebSource");
            this.mWebSource = stringExtra;
            if (TextUtils.isEmpty(stringExtra)) {
                finish();
            }
            at a2 = as.b().a();
            if (a2 != null && !a2.a(this.mWebSource)) {
                this.mWebSource = "";
                HwLog.i(TAG, "mWebSource is not in white list");
                return;
            } else {
                HwLog.i(TAG, "mWebSource is in white list");
                return;
            }
        }
        this.mWebSource = LanguageUtils.b() ? "file:///android_asset/watchfaceOpenSource.html" : "file:///android_asset/watchfaceOpenSourceOverseas.html";
    }

    private void initErrorToolbar() {
        HwLog.i(TAG, "initErrorToolbar() enter.");
        HwToolbar hwToolbar = (HwToolbar) LayoutInflater.from(this).inflate(R$layout.watchface_activity_common_toolbar, (ViewGroup) null).findViewById(R$id.tl_common_toolbar);
        this.mTvTitle = (HwTextView) hwToolbar.findViewById(R$id.tv_common_toolbar_title);
        View a2 = dw.a(hwToolbar, R$id.iv_back);
        this.mIvBack = a2;
        dw.b(a2, 0);
        dw.a(this.mIvBack, new View.OnClickListener() { // from class: com.huawei.watchface.mvp.ui.activity.WatchfaceUrlActivityV1$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WatchfaceUrlActivityV1.this.m931x9ae18e3b(view);
            }
        });
    }

    /* renamed from: lambda$initErrorToolbar$0$com-huawei-watchface-mvp-ui-activity-WatchfaceUrlActivityV1, reason: not valid java name */
    /* synthetic */ void m931x9ae18e3b(View view) {
        goBack();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void initErrorView() {
        HwLog.i(TAG, "initErrorView");
        ((Button) findViewById(R$id.btn_go_setting)).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.watchface.mvp.ui.activity.WatchfaceUrlActivityV1$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WatchfaceUrlActivityV1.this.m932x7d10697c(view);
            }
        });
        initErrorToolbar();
    }

    /* renamed from: lambda$initErrorView$1$com-huawei-watchface-mvp-ui-activity-WatchfaceUrlActivityV1, reason: not valid java name */
    /* synthetic */ void m932x7d10697c(View view) {
        HwLog.i(TAG, "enter btnGoSetting click");
        try {
            CommonUtils.b(this.mContext, "com.google.android.webview");
        } catch (Exception e) {
            HwLog.i(TAG, "initErrorView btnGoSetting click error" + HwLog.printException(e));
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void initContentView() {
        HwLog.i(TAG, "initContentView()");
        this.mWebView = (SafeWebView) findViewById(R$id.hw_health_user_agreement_webview);
        this.mLoadingLayout = (LinearLayout) findViewById(R$id.service_layout_loading);
        this.mToolBar = findViewById(R$id.tl_common_toolbar);
        this.mMainLayout = (LinearLayout) findViewById(R$id.service_item_linear);
        this.mNoNetWorkLayout = (RelativeLayout) findViewById(R$id.reload_layout);
        this.mBtnNetSetting = (Button) findViewById(R$id.btn_no_net_work);
        this.mLoadingLayout.setVisibility(0);
        this.mNoNetworkTips = (TextView) dw.a(this, R$id.tips_no_net_work);
        this.mTipsImageView = (ImageView) dw.a(this, R$id.img_no_net_work);
        this.mBtnNetSetting.setOnClickListener(this);
        this.mTvTitle = (HwTextView) dw.a(this, R$id.tv_common_toolbar_title);
        this.mIvBack = dw.a(this, R$id.iv_back);
        this.mProgressBar = (ProgressBar) dw.a(this, R$id.prgress_load_url);
        dw.b(this.mIvBack, 0);
        dw.a(this.mIvBack, new View.OnClickListener() { // from class: com.huawei.watchface.mvp.ui.activity.WatchfaceUrlActivityV1$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WatchfaceUrlActivityV1.this.m930x5d927b6e(view);
            }
        });
        dw.c(this.mToolBar, CommonUtils.h(Environment.getApplicationContext()));
        if (LanguageUtils.a(this.mContext) && dc.a(this.mContext)) {
            ((ImageView) this.mIvBack).setColorFilter(ResourcesCompat.getColor(getResources(), R$color.emui_primary_dark, null));
        }
        initWebView();
        showNoNetOrContent();
    }

    /* renamed from: lambda$initContentView$2$com-huawei-watchface-mvp-ui-activity-WatchfaceUrlActivityV1, reason: not valid java name */
    /* synthetic */ void m930x5d927b6e(View view) {
        goBack();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void initView() {
        if (CommonUtils.e(this.mContext.getApplicationContext())) {
            setContentView(R$layout.watchface_fragment_web_view_error_1);
            initErrorView();
        } else {
            setContentView(R$layout.watchface_activity_url_item);
            HwLog.i(TAG, "onCreate()");
            initContentView();
        }
    }

    private void showNoNetOrContent() {
        if (!CommonUtils.f()) {
            this.mNoNetWorkLayout.setVisibility(0);
            this.mMainLayout.setVisibility(8);
            this.mLoadingLayout.setVisibility(8);
            return;
        }
        loadSafeUrl(this.mWebSource);
    }

    @Override // com.huawei.secure.android.common.activity.SafeFragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initIntent();
        initView();
    }

    private void initWebView() {
        HwLog.i(TAG, "initWebViewSettings");
        this.mAnalyticsH5Event = new ef();
        this.mWebView.requestFocus();
        this.mWebView.setOverScrollMode(2);
        WebSettings settings = this.mWebView.getSettings();
        settings.setSaveFormData(false);
        settings.setGeolocationEnabled(false);
        settings.setCacheMode(2);
        settings.setJavaScriptEnabled(true);
        settings.setBlockNetworkImage(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setTextZoom(100);
        settings.setMixedContentMode(2);
        CookieManager.getInstance().setAcceptThirdPartyCookies(this.mWebView, true);
        SafeWebSettings.initWebviewAndSettings(this.mWebView);
        SafeWebView safeWebView = this.mWebView;
        d dVar = new d();
        if (safeWebView instanceof SafeWebView) {
            APMSH5LoadInstrument.setSafeWebViewClient(safeWebView, dVar, false);
        } else {
            safeWebView.setWebViewClient(dVar, false);
        }
        this.mWebView.setWebChromeClient(new c());
        this.mWebView.setWebViewLoadCallBack(new WebViewLoadCallBack() { // from class: com.huawei.watchface.mvp.ui.activity.WatchfaceUrlActivityV1$$ExternalSyntheticLambda1
            @Override // com.huawei.secure.android.common.webview.WebViewLoadCallBack
            public final void onCheckError(String str, WebViewLoadCallBack.ErrorCode errorCode) {
                HwLog.e(WatchfaceUrlActivityV1.TAG, "initWebViewSettingsEvent -- onCheckError: " + errorCode);
            }
        });
    }

    private void loadSafeUrl(final String str) {
        JsSafeUrlSystemParamManager.getInstance().a(new JsSafeUrlSystemParamManager.a() { // from class: com.huawei.watchface.mvp.ui.activity.WatchfaceUrlActivityV1$$ExternalSyntheticLambda4
            @Override // com.huawei.watchface.mvp.model.helper.JsSafeUrlSystemParamManager.a
            public final void onGetFinished() {
                WatchfaceUrlActivityV1.this.m933xb7dfa4be(str);
            }
        });
    }

    /* renamed from: lambda$loadSafeUrl$4$com-huawei-watchface-mvp-ui-activity-WatchfaceUrlActivityV1, reason: not valid java name */
    /* synthetic */ void m933xb7dfa4be(String str) {
        at a2 = as.b().a();
        if (a2 != null && a2.a(str)) {
            List<String> a3 = a2.a();
            if (!ArrayUtils.isEmpty(a3)) {
                this.mWebView.setWhitelistNotMatchSubDomain((String[]) a3.toArray(new String[0]));
            }
            SafeWebView safeWebView = this.mWebView;
            if (safeWebView != null) {
                safeWebView.loadUrl(str);
                WebViewInstrumentation.loadUrl(safeWebView, str);
                return;
            }
            return;
        }
        HwLog.i(TAG, "loadSafeUrl - loadPage: SupportType is null.");
        loadSafeUrlAfterloadWhite(str);
    }

    private void loadSafeUrlAfterloadWhite(final String str) {
        as.b().a(new IBaseResponseCallback() { // from class: com.huawei.watchface.mvp.ui.activity.WatchfaceUrlActivityV1$$ExternalSyntheticLambda5
            @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
            public final void onResponse(int i, Object obj) {
                WatchfaceUrlActivityV1.this.m934x19bcc566(str, i, obj);
            }
        });
        as.b().c();
    }

    /* renamed from: lambda$loadSafeUrlAfterloadWhite$5$com-huawei-watchface-mvp-ui-activity-WatchfaceUrlActivityV1, reason: not valid java name */
    /* synthetic */ void m934x19bcc566(String str, int i, Object obj) {
        if (obj instanceof at) {
            at atVar = (at) obj;
            List<String> a2 = atVar.a();
            if (!ArrayUtils.isEmpty(a2)) {
                this.mWebView.setWhitelistNotMatchSubDomain((String[]) a2.toArray(new String[0]));
            }
            boolean a3 = atVar.a(str);
            HwLog.i(TAG, "reload whitelist isWhiteUrl : " + a3);
            if (a3) {
                SafeWebView safeWebView = this.mWebView;
                safeWebView.loadUrl(str);
                WebViewInstrumentation.loadUrl(safeWebView, str);
                return;
            }
            HwLog.e(TAG, "reload isWhiteUrl false.");
        }
    }

    class d extends WebViewClient {
        private d() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            HwLog.e(WatchfaceUrlActivityV1.TAG, "onPageFinished");
            if (WatchfaceUrlActivityV1.this.mHandler.hasMessages(2007)) {
                WatchfaceUrlActivityV1.this.mHandler.removeMessages(2007);
            }
            Message obtainMessage = WatchfaceUrlActivityV1.this.mHandler.obtainMessage(10086);
            obtainMessage.obj = webView.getTitle();
            WatchfaceUrlActivityV1.this.mHandler.sendMessage(obtainMessage);
            super.onPageFinished(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            HwLog.i(WatchfaceUrlActivityV1.TAG, "onReceivedError errorCode is " + i + "description is " + str);
            WatchfaceUrlActivityV1.this.mHandler.sendEmptyMessage(2001);
            super.onReceivedError(webView, i, str, str2);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            HwLog.i(WatchfaceUrlActivityV1.TAG, "shouldOverrideUrlLoading ");
            if (str == null) {
                HwLog.i(WatchfaceUrlActivityV1.TAG, "shouldOverrideUrlLoading url == null");
                return false;
            }
            if (WebViewUtils.a(str)) {
                return false;
            }
            WebViewUtils.a(WatchfaceUrlActivityV1.this.mContext, str);
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            at a2 = as.b().a();
            if (a2 == null || a2.a(str)) {
                WatchfaceUrlActivityV1.this.mWebSource = str;
                WebViewUtils.a(WatchfaceUrlActivityV1.this.mContext, WatchfaceUrlActivityV1.this.mWebView, WatchfaceUrlActivityV1.this.mWebSource);
                super.onPageStarted(webView, str, bitmap);
                return;
            }
            HwLog.e(WatchfaceUrlActivityV1.TAG, "onPageStarted url is not in white list");
            FlavorConfig.safeHwLog(WatchfaceUrlActivityV1.TAG, "onPageStarted url is not in white list:" + str);
            webView.stopLoading();
            WatchfaceUrlActivityV1.this.goBack();
            WebViewUtils.a(WatchfaceUrlActivityV1.this.mContext, str);
        }
    }

    public class c extends WebChromeClient {
        public c() {
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            Message obtainMessage = WatchfaceUrlActivityV1.this.mHandler.obtainMessage(10089);
            obtainMessage.obj = str;
            WatchfaceUrlActivityV1.this.mHandler.sendMessage(obtainMessage);
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            if (!TextUtils.isEmpty(WatchfaceUrlActivityV1.this.mWebSource) && WatchfaceUrlActivityV1.this.mProgressBar != null) {
                WatchfaceUrlActivityV1.this.mProgressBar.setVisibility(i >= 100 ? 8 : 0);
                WatchfaceUrlActivityV1.this.mProgressBar.setProgress(i);
            }
            if (WatchfaceUrlActivityV1.this.mContext == null || WatchfaceUrlActivityV1.this.mContext.isFinishing() || i < 99 || WatchfaceUrlActivityV1.this.mHandler == null) {
                return;
            }
            WatchfaceUrlActivityV1.this.mHandler.sendEmptyMessage(2000);
        }
    }

    @Override // com.huawei.watchface.mvp.base.BaseActivity, com.huawei.secure.android.common.activity.SafeFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        HwLog.i(TAG, "onDestroy");
        SafeHandler safeHandler = this.mHandler;
        if (safeHandler != null) {
            safeHandler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
        destroyWebView();
        unregisterNetworkReceiver();
        super.onDestroy();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.mBtnNetSetting) {
            CommonUtils.f(this.mContext);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        goBack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goBack() {
        SafeWebView safeWebView = this.mWebView;
        if (safeWebView != null && safeWebView.canGoBack()) {
            if (dj.a()) {
                dw.b(this.mMainLayout, 0);
                dw.b(this.mLoadingLayout, 8);
                dw.b(this.mNoNetWorkLayout, 8);
                dw.b(this.mTvTitle, 0);
                this.mWebView.goBack();
                return;
            }
            Activity activity = this.mContext;
            if (activity != null) {
                activity.finish();
                return;
            }
            return;
        }
        Activity activity2 = this.mContext;
        if (activity2 != null) {
            activity2.finish();
        }
    }

    private void destroyWebView() {
        HwLog.i(TAG, "destroyWebView");
        if (this.mWebView != null) {
            HwLog.i(TAG, "onDestroy destroyWebView");
            ViewParent parent = this.mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(this.mWebView);
            }
            try {
                this.mWebView.destroy();
            } catch (Exception unused) {
                HwLog.i(TAG, "destroyWebView exception");
            }
        }
    }

    static class a extends SafeHandler<WatchfaceUrlActivityV1> {
        public a(WatchfaceUrlActivityV1 watchfaceUrlActivityV1) {
            super(watchfaceUrlActivityV1);
        }

        @Override // com.huawei.watchface.utils.SafeHandler
        public void handlerMessageAction(Message message) {
            if (getObj() == null) {
                return;
            }
            final WatchfaceUrlActivityV1 obj = getObj();
            int i = message.what;
            if (i == 2000) {
                HwLog.i(WatchfaceUrlActivityV1.TAG, "MSG_PROGRESSBAR_GONE");
                dw.b(obj.mLoadingLayout, 8);
                dw.b(obj.mProgressBar, 8);
            } else if (i != 2001) {
                if (i == 2003) {
                    HwLog.i(WatchfaceUrlActivityV1.TAG, "MSG_ON_NET_WORK");
                    obj.showNoNetworkPage();
                    obj.setAnalyticsH5Event(message.what, "MSG_ON_NET_WORK noNetWork");
                } else if (i == 2022) {
                    HwLog.i(WatchfaceUrlActivityV1.TAG, "MSG_WATCH_FACE_SERVER_RETRY");
                    obj.showWatchFaceServerRetryPage();
                    obj.setAnalyticsH5Event(message.what, "MSG_WATCH_FACE_SERVER_RETRY");
                } else if (i == 4001) {
                    HwLog.i(WatchfaceUrlActivityV1.TAG, "MAG_WEB_VIEW_LOAD");
                    obj.showUnstableNetworkPage();
                    obj.setAnalyticsH5Event(message.what, "MAG_WEB_VIEW_LOAD");
                } else if (i == 10086) {
                    HwLog.i(WatchfaceUrlActivityV1.TAG, "handleLoadPage() ON_PAGE_FINISH");
                    obj.dealOnPageFinish(message.obj);
                } else if (i == 10089) {
                    HwLog.i(WatchfaceUrlActivityV1.TAG, "handleLoadPage() ON_PAGE_TITLE_FINISH");
                    obj.updateTitle(message.obj);
                } else if (i != 20253) {
                    switch (i) {
                        case 2006:
                            HwLog.i(WatchfaceUrlActivityV1.TAG, "MSG_SERVER_ERROR");
                            obj.showServerErrorPage();
                            obj.setAnalyticsH5Event(message.what, "MSG_SERVER_ERROR");
                            break;
                        case 2007:
                            HwLog.i(WatchfaceUrlActivityV1.TAG, "MSG_CONNECT_TIMEOUT");
                            obj.showUnstableNetworkPage();
                            obj.setAnalyticsH5Event(message.what, "MSG_CONNECT_TIMEOUT");
                            break;
                        case 2008:
                            HwLog.i(WatchfaceUrlActivityV1.TAG, "MSG_SSL_HANDLE_ERROR");
                            obj.showSslErrorTipsPage();
                            obj.setAnalyticsH5Event(message.what, "MSG_SSL_HANDLE_ERROR");
                            break;
                    }
                } else {
                    HwLog.i(WatchfaceUrlActivityV1.TAG, "handleTitle() UPLOAD_SUCCESS");
                    BackgroundTaskUtils.postInMainThread(new Runnable() { // from class: com.huawei.watchface.mvp.ui.activity.WatchfaceUrlActivityV1$a$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            WatchfaceUrlActivityV1.a.a(WatchfaceUrlActivityV1.this);
                        }
                    });
                }
            } else if (CommonUtils.f()) {
                HwLog.i(WatchfaceUrlActivityV1.TAG, "MSG_ON_RECEIVED_ERROR showNoServicePage");
                obj.showNoServicePage();
                obj.setAnalyticsH5Event(message.what, "MSG_ON_RECEIVED_ERROR showNoServicePage");
            } else {
                HwLog.i(WatchfaceUrlActivityV1.TAG, "MSG_ON_RECEIVED_ERROR showNoNetworkPage");
                obj.showNoNetworkPage();
                obj.setAnalyticsH5Event(message.what, "MSG_ON_RECEIVED_ERROR showNoNetworkPage");
            }
            getObj().uploadH5LoadUrl();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void a(WatchfaceUrlActivityV1 watchfaceUrlActivityV1) {
            if (watchfaceUrlActivityV1.mWebView != null) {
                watchfaceUrlActivityV1.mWebView.goBack();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAnalyticsH5Event(int i, String str) {
        ef efVar = this.mAnalyticsH5Event;
        if (efVar != null) {
            efVar.a(i, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadH5LoadUrl() {
        ef efVar = this.mAnalyticsH5Event;
        if (efVar == null || efVar.d() == null) {
            return;
        }
        ef efVar2 = this.mAnalyticsH5Event;
        eo.a(efVar2, efVar2.g());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealOnPageFinish(Object obj) {
        dw.b(this.mLoadingLayout, 8);
        this.mWebView.getSettings().setBlockNetworkImage(false);
        if (obj == null || !(obj instanceof String)) {
            return;
        }
        this.mTitle = (String) obj;
        FlavorConfig.safeHwLog(TAG, "title:" + this.mTitle);
        if (TextUtils.isEmpty(this.mTitle) || UrlUtil.isHttpsUrl(this.mTitle) || UrlUtil.isHttpUrl(this.mTitle)) {
            return;
        }
        dw.a(this.mTvTitle, this.mTitle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTitle(Object obj) {
        if (obj == null || !(obj instanceof String)) {
            return;
        }
        this.mTitle = (String) obj;
        FlavorConfig.safeHwLog(TAG, "updateTitle title:" + this.mTitle);
        if (TextUtils.isEmpty(this.mTitle) || UrlUtil.isHttpsUrl(this.mTitle) || UrlUtil.isHttpUrl(this.mTitle)) {
            return;
        }
        dw.a(this.mTvTitle, this.mTitle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSslErrorTipsPage() {
        HwLog.i(TAG, "showSslErrorTipsPage");
        dw.b(this.mNoNetWorkLayout, 0);
        dw.b(this.mLoadingLayout, 8);
        dw.b(this.mMainLayout, 8);
        dw.b(this.mBtnNetSetting, 8);
        this.mNoNetworkTips.setGravity(0);
        this.mNoNetworkTips.setText(getString(R$string.IDS_hwh_home_healthshop_unable_connect_server_tips));
        this.mTipsImageView.setBackgroundResource(R$drawable.watchface_ic_abnormal);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNoServicePage() {
        HwLog.i(TAG, "showNoServicePage");
        dw.b(this.mNoNetWorkLayout, 0);
        dw.b(this.mLoadingLayout, 8);
        dw.b(this.mMainLayout, 8);
        dw.b(this.mTvTitle, 8);
        dw.b(this.mBtnNetSetting, 8);
        this.mNoNetworkTips.setGravity(17);
        this.mNoNetworkTips.setText(getString(R$string.IDS_hwh_home_healthshop_unable_connect_server_tips));
        this.mTipsImageView.setBackgroundResource(R$drawable.watchface_ic_network);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showUnstableNetworkPage() {
        HwLog.i(TAG, "showNoServicePage");
        dw.b(this.mNoNetWorkLayout, 0);
        dw.b(this.mLoadingLayout, 8);
        dw.b(this.mMainLayout, 8);
        dw.b(this.mBtnNetSetting, 8);
        this.mNoNetworkTips.setGravity(17);
        this.mNoNetworkTips.setText(getString(R$string.network_unstable_notice2));
        this.mTipsImageView.setBackgroundResource(R$drawable.watchface_ic_network);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNoNetworkPage() {
        HwLog.i(TAG, "showNoNetworkPage");
        dw.b(this.mNoNetWorkLayout, 0);
        dw.b(this.mLoadingLayout, 8);
        dw.b(this.mMainLayout, 8);
        dw.b(this.mTvTitle, 8);
        dw.b(this.mBtnNetSetting, 0);
        this.mNoNetworkTips.setGravity(17);
        this.mNoNetworkTips.setText(getString(R$string.IDS_hwh_home_healthshop_no_net_work_pls_click_again));
        this.mTipsImageView.setBackgroundResource(R$drawable.watchface_ic_network);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showServerErrorPage() {
        HwLog.i(TAG, "showServerErrorPage");
        dw.b(this.mNoNetWorkLayout, 0);
        dw.b(this.mLoadingLayout, 8);
        dw.b(this.mMainLayout, 8);
        dw.b(this.mBtnNetSetting, 8);
        this.mNoNetworkTips.setGravity(17);
        this.mNoNetworkTips.setText(getString(R$string.IDS_hwh_home_healthshop_servers_error));
        this.mTipsImageView.setBackgroundResource(R$drawable.watchface_ic_network);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showWatchFaceServerRetryPage() {
        HwLog.i(TAG, "showWatchFaceServerRetryPage");
        dw.b(this.mNoNetWorkLayout, 0);
        dw.b(this.mLoadingLayout, 8);
        dw.b(this.mMainLayout, 8);
        dw.b(this.mBtnNetSetting, 8);
        this.mNoNetworkTips.setGravity(17);
        this.mNoNetworkTips.setText(getString(R$string.IDS_watchface_server_retry));
        this.mTipsImageView.setBackgroundResource(R$drawable.watchface_ic_network);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNetworkAvailablePage() {
        dw.b(this.mNoNetWorkLayout, 8);
        dw.b(this.mBtnNetSetting, 8);
        dw.b(this.mTvTitle, 0);
        dw.b(this.mMainLayout, 0);
        dw.b(this.mLoadingLayout, 0);
    }

    class b extends SafeBroadcastReceiver {
        private b() {
        }

        @Override // com.huawei.secure.android.common.intent.SafeBroadcastReceiver
        public void onReceiveMsg(Context context, Intent intent) {
            if (intent == null || TextUtils.isEmpty(intent.getAction())) {
                HwLog.i(WatchfaceUrlActivityV1.TAG, "NetworkBroadcastReceiver intent or action is null.");
                return;
            }
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(Objects.requireNonNull(intent.getAction())) && WatchfaceUrlActivityV1.this.mWebView != null && dj.a()) {
                WatchfaceUrlActivityV1.this.mIsNetworkAvailable = true;
                SafeWebView safeWebView = WatchfaceUrlActivityV1.this.mWebView;
                String str = WatchfaceUrlActivityV1.this.mWebSource;
                safeWebView.loadUrl(str);
                WebViewInstrumentation.loadUrl(safeWebView, str);
                WatchfaceUrlActivityV1.this.showNetworkAvailablePage();
            }
        }
    }

    private void registerNetworkReceiver() {
        if (this.mNetworkBroadcastReceiver == null) {
            this.mNetworkBroadcastReceiver = new b();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        if (CommonUtils.isAndroid13()) {
            registerReceiver(this.mNetworkBroadcastReceiver, intentFilter, WebViewActivity.HW_SIGNATURE_OR_SYSTEM, null, 4);
        } else {
            registerReceiver(this.mNetworkBroadcastReceiver, intentFilter, WebViewActivity.HW_SIGNATURE_OR_SYSTEM, null);
        }
    }

    private void unregisterNetworkReceiver() {
        try {
            unregisterReceiver(this.mNetworkBroadcastReceiver);
        } catch (Exception e) {
            HwLog.e(TAG, "unregisterNetworkReceiver" + HwLog.printException(e));
        }
    }

    @Override // com.huawei.watchface.mvp.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
