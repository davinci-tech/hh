package com.huawei.ui.main.stories.me.views.privacy;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.utils.WebViewUtils;
import com.huawei.secure.android.common.ssl.WebViewSSLCheck;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.me.js.JsInteraction;
import com.huawei.ui.main.stories.me.views.privacy.ServiceItemActivity;
import defpackage.nqc;
import defpackage.nrt;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.rho;
import defpackage.rhy;
import defpackage.rim;
import defpackage.rvh;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* loaded from: classes7.dex */
public class ServiceItemActivity extends BaseActivity implements View.OnClickListener {
    private static List<String> c = new ArrayList();
    private static Set<String> e = new HashSet();

    /* renamed from: a, reason: collision with root package name */
    private Context f10382a;
    protected String b;
    private CustomTitleBar f;
    private rvh h;
    private rho i;
    private LinearLayout k;
    private boolean l;
    private RelativeLayout m;
    private LinearLayout n;
    private HealthProgressBar o;
    private int p;
    private LinearLayout q;
    private HealthButton r;
    private nqc t;
    private WebView u;
    private String x;
    protected Handler d = new Handler();
    private String j = "";
    private String g = "";
    private Runnable s = new Runnable() { // from class: com.huawei.ui.main.stories.me.views.privacy.ServiceItemActivity.2
        @Override // java.lang.Runnable
        public void run() {
            String str = Locale.getDefault().getLanguage() + Constants.LINK + Locale.getDefault().getCountry() + ".html";
            String str2 = "file:///android_asset/healthUserAgreement/" + str;
            String str3 = Locale.getDefault().getLanguage() + ".html";
            String str4 = "file:///android_asset/healthUserAgreement/" + str3;
            LogUtil.c("ServiceItemActivity", "User_agreementAll = ", str, ", User_agreement = ", str3);
            if (ServiceItemActivity.this.u != null) {
                WebSettings settings = ServiceItemActivity.this.u.getSettings();
                if (settings != null) {
                    settings.setGeolocationEnabled(false);
                    settings.setAllowFileAccess(false);
                    settings.setAllowContentAccess(false);
                }
                if (ServiceItemActivity.this.e(str)) {
                    WebView webView = ServiceItemActivity.this.u;
                    webView.loadUrl(str2);
                    WebViewInstrumentation.loadUrl(webView, str2);
                } else if (ServiceItemActivity.this.e(str3)) {
                    WebView webView2 = ServiceItemActivity.this.u;
                    webView2.loadUrl(str4);
                    WebViewInstrumentation.loadUrl(webView2, str4);
                } else {
                    WebView webView3 = ServiceItemActivity.this.u;
                    webView3.loadUrl("file:///android_asset/healthUserAgreement/en-US.html");
                    WebViewInstrumentation.loadUrl(webView3, "file:///android_asset/healthUserAgreement/en-US.html");
                }
            }
        }
    };

    public static /* synthetic */ boolean dOX_(View view) {
        return true;
    }

    static {
        c.add("3rdshare");
        c.add("3rdsdk");
        c.add("di");
        e.add("VmallUserAgreement");
        e.add("VmallPrivacy");
        e.add("SagaPrivacyStatement");
        e.add("SagaPersonalInfoCollection");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f10382a = this;
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.h = new rvh(this.f10382a, this, null);
        try {
            this.x = intent.getStringExtra("Agreement_key");
            this.l = intent.getBooleanExtra("Is_show_cancel_key", true);
            this.j = intent.getStringExtra("device_id");
            this.g = intent.getStringExtra(PluginPayAdapter.KEY_DEVICE_INFO_NAME);
            this.p = intent.getIntExtra("product_type", 0);
        } catch (Exception unused) {
            LogUtil.b("ServiceItemActivity", "onCreate getStringExtra error!");
        }
        if (CommonUtil.w(this.f10382a.getApplicationContext())) {
            setContentView(R.layout.fragment_web_view_error_1);
            q();
            return;
        }
        try {
            setContentView(R.layout.hw_show_settings_about_serviceitem);
            s();
            w();
        } catch (RuntimeException unused2) {
            LogUtil.b("ServiceItemActivity", "onCreate setContentView webview error.");
            finish();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void loadApplicationTheme() {
        loadTheme("EmergencyTheme");
    }

    private void q() {
        LogUtil.a("ServiceItemActivity", "initErrorView ServiceItemActivity.isErrorWebView");
        ((Button) findViewById(R.id.btn_go_setting)).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.views.privacy.ServiceItemActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("ServiceItemActivity", "initErrorView ServiceItemActivity.uninstallApk");
                try {
                    CommonUtil.p(ServiceItemActivity.this.f10382a, "com.google.android.webview");
                } catch (Exception unused) {
                    LogUtil.b("ServiceItemActivity", "initErrorView test");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.f = (CustomTitleBar) nsy.cMc_(this, R.id.mini_shop__webview_titlebar);
    }

    private void s() {
        LogUtil.a("ServiceItemActivity", "initView()");
        WebView webView = (WebView) findViewById(R.id.hw_health_user_agreement_webview);
        this.u = webView;
        webView.setBackgroundColor(0);
        nrt.cKh_(this.f10382a, this.u);
        this.k = (LinearLayout) findViewById(R.id.service_layout_loading);
        this.n = (LinearLayout) findViewById(R.id.service_item_linear);
        this.q = (LinearLayout) findViewById(R.id.stop_service_layout);
        HealthButton healthButton = (HealthButton) findViewById(R.id.stop_service);
        healthButton.setOnClickListener(this);
        if (!CommonUtil.bb()) {
            healthButton.setText(R$string.IDS_hw_stop_health2);
        }
        this.m = (RelativeLayout) findViewById(R.id.reload_layout);
        this.r = (HealthButton) findViewById(R.id.btn_no_net_work);
        this.k.setVisibility(0);
        HealthProgressBar healthProgressBar = (HealthProgressBar) this.k.findViewById(R.id.service_info_loading);
        this.o = healthProgressBar;
        healthProgressBar.setVisibility(0);
        this.r.setOnClickListener(this);
        this.f = (CustomTitleBar) nsy.cMc_(this, R.id.hw_health_service_title_layout);
        c(this.x);
        this.f.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.views.privacy.ServiceItemActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ServiceItemActivity.this.u != null && ServiceItemActivity.this.u.canGoBack()) {
                    ServiceItemActivity.this.u.goBack();
                    ServiceItemActivity.this.v();
                } else {
                    ServiceItemActivity.this.finish();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (CommonUtil.v(this.f10382a)) {
            return;
        }
        this.u.setOnLongClickListener(new View.OnLongClickListener() { // from class: rio
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return ServiceItemActivity.dOX_(view);
            }
        });
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void c(String str) {
        char c2;
        if (str == null) {
            return;
        }
        switch (str.hashCode()) {
            case -1786394060:
                if (str.equals("SagaPersonalInfoCollection")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -1268873553:
                if (str.equals("SagaPrivacyStatement")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case -242466470:
                if (str.equals("3rdshare")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 3205:
                if (str.equals("di")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 647003220:
                if (str.equals("WatchHealthUserAgreement")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 1412359502:
                if (str.equals("HealthUserPrivacyStatement")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 1568460917:
                if (str.equals("3rdsdk")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            this.f.setTitleText(this.f10382a.getString(R$string.IDS_setting_wearhome_privacy_BG_statement));
            return;
        }
        if (c2 == 1) {
            this.f.setTitleText(this.f10382a.getString(R$string.IDS_hwh_personal_info));
            return;
        }
        if (c2 == 2) {
            this.f.setTitleText(this.f10382a.getString(R$string.IDS_setting_wearhome_end_user_agreement));
            return;
        }
        if (c2 == 3) {
            this.f.setTitleText(this.f10382a.getString(R$string.IDS_hw_health_list_third_shared_msg));
            return;
        }
        if (c2 == 4) {
            this.f.setTitleText(this.f10382a.getString(R$string.IDS_hwh_third_party_sdk));
        } else if (c2 == 5) {
            this.f.setTitleText(this.f10382a.getString(R$string.IDS_hw_health_privacy_statement_abstract));
        } else {
            LogUtil.h("ServiceItemActivity", "updateTitleText default");
        }
    }

    private void y() {
        if (CommonUtil.aa(this.f10382a.getApplicationContext()) || !CommonUtil.v(this.f10382a)) {
            return;
        }
        this.m.setVisibility(0);
        this.n.setVisibility(8);
        this.k.setVisibility(8);
    }

    private void w() {
        if (CommonUtil.bu() && !c.contains(this.x)) {
            this.d.postDelayed(this.s, 100L);
            this.o.setVisibility(4);
            this.k.setVisibility(8);
            return;
        }
        y();
        WebSettings settings = this.u.getSettings();
        settings.setGeolocationEnabled(false);
        settings.setAllowContentAccess(false);
        if (CommonUtil.aa(this.f10382a)) {
            settings.setCacheMode(-1);
        } else {
            settings.setCacheMode(1);
        }
        settings.setSupportZoom(true);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowFileAccess(false);
        if (CommonUtil.v(this.f10382a) && !e.contains(this.x) && this.l && !SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", false)) {
            this.f.setRightButtonVisibility(0);
            this.f.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R$string.accessibility_more_item));
            this.f.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.views.privacy.ServiceItemActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ServiceItemActivity.this.x();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        v();
        dPa_(this.u);
        dOY_(this.u);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        WebView webView = this.u;
        if (webView != null && webView.canGoBack()) {
            this.u.goBack();
            if (this.u.canGoBack()) {
                LogUtil.a("ServiceItemActivity", "mUserAgreement can go back, don't need show stop");
                return;
            } else {
                v();
                return;
            }
        }
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        if (("HealthUserAgreement".equals(this.x) || "HealthPrivacy".equals(this.x)) && this.l) {
            this.q.setVisibility(0);
        } else {
            this.q.setVisibility(8);
        }
    }

    private void dOY_(WebView webView) {
        WebViewClient webViewClient = new WebViewClient() { // from class: com.huawei.ui.main.stories.me.views.privacy.ServiceItemActivity.5
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView2, String str) {
                super.onPageFinished(webView2, str);
                ServiceItemActivity.this.o.setVisibility(4);
                ServiceItemActivity.this.k.setVisibility(8);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(WebView webView2, SslErrorHandler sslErrorHandler, SslError sslError) {
                LogUtil.b("ServiceItemActivity", "on received ssl error");
                if (sslError != null) {
                    WebViewSSLCheck.checkServerCertificateNew(sslErrorHandler, sslError, ServiceItemActivity.this.f10382a);
                }
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, String str) {
                return ServiceItemActivity.this.dOZ_(webView2, str);
            }
        };
        if (webView instanceof WebView) {
            APMSH5LoadInstrument.setWebViewClient(webView, webViewClient);
        } else {
            webView.setWebViewClient(webViewClient);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean dOZ_(WebView webView, String str) {
        if (!CommonUtil.v(this.f10382a)) {
            LogUtil.a("ServiceItemActivity", "loadOverrideUrl is OOBE");
            return true;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ServiceItemActivity", "loadOverrideUrl url is null");
            return true;
        }
        nrt.cKh_(this.f10382a, webView);
        if (!a(str)) {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str).normalizeScheme());
            ComponentName resolveActivity = intent.resolveActivity(getPackageManager());
            if (resolveActivity != null) {
                nsn.cLM_(intent, resolveActivity.getPackageName(), this, nsf.h(com.huawei.ui.commonui.R$string.IDS_browser_app_name));
            }
            return true;
        }
        if (rhy.d(str)) {
            b(str);
            return true;
        }
        if (str.contains("minisite/cloudservice/health") && this.l) {
            this.q.setVisibility(0);
        } else {
            this.q.setVisibility(8);
        }
        int indexOf = str.indexOf("minisite/legal/privacy/statement.htm");
        if (indexOf < 0) {
            return false;
        }
        String str2 = str.substring(0, indexOf + 36) + ("?country=" + b() + t());
        webView.loadUrl(str2);
        WebViewInstrumentation.loadUrl(webView, str2);
        return true;
    }

    private void b(String str) {
        try {
            Intent parseUri = Intent.parseUri(str, 1);
            if (getPackageManager().resolveActivity(parseUri, 0) == null) {
                return;
            }
            try {
                startActivity(parseUri);
            } catch (ActivityNotFoundException e2) {
                LogUtil.b("ServiceItemActivity", "shouldOverrideUrlByDeepLink ActivityNotFoundException: ", e2.getMessage());
            }
        } catch (URISyntaxException e3) {
            LogUtil.b("ServiceItemActivity", "shouldOverrideUrlByDeepLink URISyntaxException: ", e3.getMessage());
        }
    }

    protected void dPa_(final WebView webView) {
        LogUtil.a("ServiceItemActivity", "loadWebViewUrl");
        if (webView == null) {
            LogUtil.h("ServiceItemActivity", "loadWebViewUrl webView is null");
            return;
        }
        if ("HealthPrivacy".equals(this.x) && !CommonUtil.bb()) {
            JsInteraction jsInteraction = new JsInteraction(this.f10382a);
            webView.addJavascriptInterface(jsInteraction, "JsInteraction");
            webView.addJavascriptInterface(jsInteraction, "checkMore");
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.me.views.privacy.ServiceItemActivity.9
            @Override // java.lang.Runnable
            public void run() {
                ServiceItemActivity.this.r();
                if (TextUtils.isEmpty(ServiceItemActivity.this.b)) {
                    LogUtil.h("ServiceItemActivity", "loadWebViewUrl mDomaniUrl is empty");
                    return;
                }
                if (ServiceItemActivity.this.isFinishing() || ServiceItemActivity.this.isDestroyed()) {
                    LogUtil.h("ServiceItemActivity", "loadWebViewUrl isFinishing or isDestroyed");
                    return;
                }
                if (ServiceItemActivity.this.d != null) {
                    final String replaceSpace = WebViewUtils.replaceSpace(ServiceItemActivity.this.k());
                    LogUtil.a("ServiceItemActivity", "showUrl: " + replaceSpace);
                    ServiceItemActivity.this.d.post(new Runnable() { // from class: com.huawei.ui.main.stories.me.views.privacy.ServiceItemActivity.9.5
                        @Override // java.lang.Runnable
                        public void run() {
                            WebSettings settings = webView.getSettings();
                            if (settings != null) {
                                settings.setJavaScriptEnabled(ServiceItemActivity.this.a(replaceSpace));
                            }
                            WebView webView2 = webView;
                            String str = replaceSpace;
                            webView2.loadUrl(str);
                            WebViewInstrumentation.loadUrl(webView2, str);
                        }
                    });
                    return;
                }
                LogUtil.h("ServiceItemActivity", "loadWebViewUrl mHandler is null");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(String str) {
        if ("SagaPrivacyStatement".equals(this.x) || "SagaPersonalInfoCollection".equals(this.x) || "WatchHealthUserAgreement".equals(this.x) || "BetaUserAgreement".equals(this.x) || "HealthUserPrivacyStatement".equals(this.x)) {
            return true;
        }
        return rhy.a(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void r() {
        char c2;
        if (this.x == null) {
            this.x = "";
        }
        String str = this.x;
        str.hashCode();
        switch (str.hashCode()) {
            case -1786394060:
                if (str.equals("SagaPersonalInfoCollection")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -1268873553:
                if (str.equals("SagaPrivacyStatement")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -173663665:
                if (str.equals("BetaUserAgreement")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 647003220:
                if (str.equals("WatchHealthUserAgreement")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 1412359502:
                if (str.equals("HealthUserPrivacyStatement")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 != 0 && c2 != 1 && c2 != 2 && c2 != 3) {
            if (c2 == 4) {
                this.b = GRSManager.a(this.f10382a).getNoCheckUrl("userAgreementDomain", b());
                return;
            } else {
                this.b = GRSManager.a(this.f10382a).getNoCheckUrl("domainConsumerHuawei", b());
                return;
            }
        }
        String noCheckUrl = GRSManager.a(this.f10382a).getNoCheckUrl("domainLegalCloudHuawei", b());
        this.b = noCheckUrl;
        if (TextUtils.isEmpty(noCheckUrl)) {
            this.b = GRSManager.a(this.f10382a).getNoCheckUrl("domainPrivacyConsumerHuawei", b());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        View inflate = LayoutInflater.from(this.f10382a).inflate(R.layout.disagree_with_this_service, (ViewGroup) null);
        nqc nqcVar = new nqc(this.f10382a, inflate);
        this.t = nqcVar;
        nqcVar.cEh_(this.f, 17);
        inflate.findViewById(R.id.disagree_service).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.views.privacy.ServiceItemActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ServiceItemActivity.this.t != null) {
                    ServiceItemActivity.this.t.b();
                }
                ServiceItemActivity.this.d();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (("HealthUserPrivacyStatement".equals(this.x) || "WatchHealthUserAgreement".equals(this.x)) && !TextUtils.isEmpty(this.j)) {
            rho rhoVar = new rho(this.j, this.g, this);
            this.i = rhoVar;
            rhoVar.c();
            return;
        }
        this.h.d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public String k() {
        char c2;
        if (getIntent() == null) {
            return h();
        }
        if (this.x == null) {
            this.x = "default";
        }
        String str = this.x;
        switch (str.hashCode()) {
            case -1786394060:
                if (str.equals("SagaPersonalInfoCollection")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case -1268873553:
                if (str.equals("SagaPrivacyStatement")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case -359318731:
                if (str.equals("VmallUserAgreement")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -242466470:
                if (str.equals("3rdshare")) {
                    c2 = '\b';
                    break;
                }
                c2 = 65535;
                break;
            case -173663665:
                if (str.equals("BetaUserAgreement")) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            case 3205:
                if (str.equals("di")) {
                    c2 = '\n';
                    break;
                }
                c2 = 65535;
                break;
            case 647003220:
                if (str.equals("WatchHealthUserAgreement")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case 993592510:
                if (str.equals("VmallPrivacy")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 1250543980:
                if (str.equals("HealthPrivacy")) {
                    c2 = 11;
                    break;
                }
                c2 = 65535;
                break;
            case 1412359502:
                if (str.equals("HealthUserPrivacyStatement")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 1568460917:
                if (str.equals("3rdsdk")) {
                    c2 = '\t';
                    break;
                }
                c2 = 65535;
                break;
            case 2009023843:
                if (str.equals("HealthUserAgreement")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                return this.b + "/minisite/cloudservice/vmall/terms.htm?country=CN&language=zh_Hans_CN";
            case 1:
                return this.b + "/minisite/cloudservice/vmall/privacy-statement.htm?country=CN&language=zh_Hans_CN";
            case 2:
                return h();
            case 3:
                return j();
            case 4:
                return n();
            case 5:
                return m();
            case 6:
                return p();
            case 7:
                return i();
            case '\b':
            case '\t':
            case '\n':
                return o();
            default:
                return e();
        }
    }

    private String i() {
        String b = b();
        LogUtil.a("ServiceItemActivity", "getBetaUserAgreementUrl: serviceCountry" + b, " getUrlLanguage ", t());
        if (TextUtils.isEmpty(b)) {
            return this.b + "/terms/scope/huawei/health-beta/beta-terms.htm?&code=cn" + t();
        }
        return this.b + "/terms/scope/huawei/health-beta/beta-terms.htm?&code=" + b + t();
    }

    private String n() {
        String b = b();
        if (TextUtils.isEmpty(b)) {
            return this.b + "/legal/huawei-watch-buds/privacy-statement.htm?country=cn" + t();
        }
        return this.b + "/legal/huawei-watch-buds/privacy-statement.htm?country=" + b + t();
    }

    private String m() {
        return n() + "&contenttag=di";
    }

    private String o() {
        return this.b + l() + "CN&language=zh_Hans_CN&contenttag=" + this.x;
    }

    private String p() {
        String d = rim.d(this.p, "user_agreement");
        if (!TextUtils.isEmpty(d)) {
            return rim.b(this.p, d, b(), f());
        }
        return this.b + "/legal/wearable-eula/terms.htm?code=" + b() + t();
    }

    private String j() {
        String b = b();
        if (TextUtils.isEmpty(b)) {
            return this.b + "/legal/privacy/statement.htm?branchid=0&version=latest&contenttag=default&code=cn" + t();
        }
        return this.b + "/legal/privacy/statement.htm?branchid=0&version=latest&contenttag=default&code=" + b + t();
    }

    protected String e() {
        String b = b();
        if (TextUtils.isEmpty(b)) {
            return g();
        }
        return this.b + l() + b + t();
    }

    private String l() {
        return CommonUtil.cc() ? "/terms/scope/huawei/health/privacy-statement.htm?code=" : "/minisite/cloudservice/health/privacy-statement.htm?country=";
    }

    private String t() {
        return Constants.LANGUAGE + f();
    }

    private String f() {
        Locale locale = Locale.getDefault();
        String script = locale.getScript();
        String language = locale.getLanguage();
        if (!TextUtils.isEmpty(script)) {
            language = language + "_" + script;
        }
        return language + "_" + locale.getCountry();
    }

    protected void a() {
        CustomTitleBar customTitleBar = this.f;
        if (customTitleBar == null) {
            LogUtil.h("ServiceItemActivity", "hindOobeMenuView mCustomTitleBar is null");
        } else {
            customTitleBar.setRightButtonVisibility(8);
            this.l = false;
        }
    }

    private String g() {
        return this.b + "/minisite/cloudservice/health/privacy-statement.htm?country=cn" + t();
    }

    private String h() {
        if (CommonUtil.cc()) {
            return this.b + "/terms/scope/huawei/health/terms.htm?code=" + b() + t();
        }
        return this.b + "/minisite/cloudservice/health/terms.htm?country=" + b() + t();
    }

    protected String b() {
        if (!Utils.o()) {
            return "CN";
        }
        Context context = BaseApplication.getContext();
        String b = SharedPreferenceManager.b(context, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "key_privacy_notice_state");
        String b2 = SharedPreferenceManager.b(context, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country");
        if (TextUtils.isEmpty(b2)) {
            b2 = Locale.getDefault().getCountry();
            if (TextUtils.isEmpty(b2)) {
                b2 = "GB";
            }
            SharedPreferenceManager.e(context, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country", b2, (StorageParams) null);
        }
        LogUtil.a("ServiceItemActivity", "getServiceCountry state = ", b, ", country = ", b2);
        if ("privacy_notice_state_before_login".equals(b)) {
            return b2;
        }
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1010);
        if (Utils.l()) {
            LogUtil.a("ServiceItemActivity", "getServiceCountry loginCountry = ", accountInfo);
            if (TextUtils.isEmpty(accountInfo)) {
                return b2;
            }
        }
        return accountInfo;
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        LogUtil.a("ServiceItemActivity", "onRestart()");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.a("ServiceItemActivity", "onResume()");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        LogUtil.a("ServiceItemActivity", "onPause()");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        nqc nqcVar = this.t;
        if (nqcVar != null) {
            nqcVar.b();
        }
        this.t = null;
        c();
        LogUtil.a("ServiceItemActivity", "onDestroy");
        super.onDestroy();
        rho rhoVar = this.i;
        if (rhoVar != null) {
            rhoVar.e();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (nsn.a(500)) {
            LogUtil.a("ServiceItemActivity", "isFastClick");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.r) {
            CommonUtil.q(this.f10382a);
        } else if (view.getId() == R.id.stop_service) {
            this.h.d();
        } else {
            LogUtil.a("ServiceItemActivity", "error click!");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c() {
        LogUtil.a("ServiceItemActivity", "destroyWebView");
        if (this.u != null) {
            LogUtil.a("ServiceItemActivity", "onDestroy destroyWebView");
            ViewParent parent = this.u.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.u);
            }
            try {
                this.u.destroy();
            } catch (Throwable unused) {
                LogUtil.b("ServiceItemActivity", "destroyWebView Throwable");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(String str) {
        try {
            return Arrays.asList(getResources().getAssets().list("healthUserAgreement")).contains(str);
        } catch (IOException unused) {
            LogUtil.b("ServiceItemActivity", "isFileExists IOException");
            return false;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.h.c(i, i2);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
