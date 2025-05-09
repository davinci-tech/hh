package com.huawei.operation.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.utils.WebViewUtils;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.nrt;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;

/* loaded from: classes9.dex */
public class AppMarketUrlActivity extends BaseActivity implements View.OnClickListener {
    private static final String APP_MARKET_USER_URL = "/minisite/cloudservice/health/appgallery-terms.htm?country=";
    private static final String TAG = "AppMarketUrlActivity";
    private static final int TEXT_SIZE_ZOOM_DEFAULT = 100;
    private Context mContext;
    private CustomTitleBar mCustomTitleBar;
    private HealthProgressBar mHealthProgressBar;
    private LinearLayout mLoadingLayout;
    private LinearLayout mMainLayout;
    private RelativeLayout mNoNetworkLayout;
    private HealthButton mSetNetworkBtn;
    private WebView mUserAgreement;
    private String mWebUrl;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        if (getIntent() == null) {
            LogUtil.h(TAG, "onCreate getIntent is null");
            finish();
            return;
        }
        try {
            this.mWebUrl = getIntent().getStringExtra("url");
        } catch (ClassCastException unused) {
            this.mWebUrl = "";
        }
        if (CommonUtil.w(this.mContext.getApplicationContext())) {
            setContentView(R.layout.fragment_web_view_error_1);
            initErrorView();
        } else {
            setContentView(R.layout.activity_app_market_url);
            initView();
            loadWebView();
        }
    }

    private void initErrorView() {
        LogUtil.a(TAG, "initErrorView()");
        ((HealthButton) findViewById(R.id.btn_go_setting)).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.operation.activity.AppMarketUrlActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    CommonUtil.p(AppMarketUrlActivity.this.mContext, "com.google.android.webview");
                } catch (Exception unused) {
                    LogUtil.b(AppMarketUrlActivity.TAG, "initErrorView, Exception");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void initView() {
        LogUtil.a(TAG, "initView()");
        WebView webView = (WebView) findViewById(R.id.hw_health_user_agreement_webview);
        this.mUserAgreement = webView;
        nrt.cKg_(this.mContext, webView);
        this.mLoadingLayout = (LinearLayout) findViewById(R.id.service_layout_loading);
        this.mMainLayout = (LinearLayout) findViewById(R.id.service_item_linear);
        this.mNoNetworkLayout = (RelativeLayout) findViewById(R.id.reload_layout);
        this.mSetNetworkBtn = (HealthButton) findViewById(R.id.btn_no_net_work);
        this.mLoadingLayout.setVisibility(0);
        HealthProgressBar healthProgressBar = (HealthProgressBar) this.mLoadingLayout.findViewById(R.id.service_info_loading);
        this.mHealthProgressBar = healthProgressBar;
        healthProgressBar.setVisibility(0);
        this.mSetNetworkBtn.setOnClickListener(this);
        showNoNetwork();
    }

    private void showNoNetwork() {
        if (CommonUtil.aa(this.mContext.getApplicationContext())) {
            return;
        }
        this.mNoNetworkLayout.setVisibility(0);
        this.mMainLayout.setVisibility(8);
        this.mLoadingLayout.setVisibility(8);
    }

    private void loadWebView() {
        this.mCustomTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.hw_health_service_title_layout);
        WebViewUtils.setWebSettings(this.mContext, this.mUserAgreement, true);
        WebView webView = this.mUserAgreement;
        String termsUrl = termsUrl();
        webView.loadUrl(termsUrl);
        WebViewInstrumentation.loadUrl(webView, termsUrl);
        this.mCustomTitleBar.setTitleText(this.mUserAgreement.getTitle());
        WebView webView2 = this.mUserAgreement;
        UserAgreementWebViewClient userAgreementWebViewClient = new UserAgreementWebViewClient(this.mContext, this.mHealthProgressBar, this.mLoadingLayout, TAG);
        if (webView2 instanceof WebView) {
            APMSH5LoadInstrument.setWebViewClient(webView2, userAgreementWebViewClient);
        } else {
            webView2.setWebViewClient(userAgreementWebViewClient);
        }
        this.mUserAgreement.setWebChromeClient(new WebChromeClient() { // from class: com.huawei.operation.activity.AppMarketUrlActivity.2
            @Override // android.webkit.WebChromeClient
            public void onReceivedTitle(WebView webView3, String str) {
                super.onReceivedTitle(webView3, str);
                AppMarketUrlActivity.this.mCustomTitleBar.setTitleText(str);
            }
        });
    }

    private String termsUrl() {
        return this.mWebUrl + (APP_MARKET_USER_URL + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010) + Constants.LANGUAGE + LanguageUtil.e());
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        destroyWebView();
        LogUtil.a(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.h(TAG, "onClick view is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (view == this.mSetNetworkBtn) {
                CommonUtil.q(this.mContext);
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void destroyWebView() {
        LogUtil.a(TAG, "destroyWebView");
        if (this.mUserAgreement != null) {
            LogUtil.a(TAG, "onDestroy destroyWebView");
            ViewParent parent = this.mUserAgreement.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.mUserAgreement);
            }
            try {
                this.mUserAgreement.destroy();
            } catch (Exception unused) {
                LogUtil.b(TAG, "destroyWebView exception");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
