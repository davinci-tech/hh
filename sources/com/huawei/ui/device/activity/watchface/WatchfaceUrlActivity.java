package com.huawei.ui.device.activity.watchface;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.activity.UserAgreementWebViewClient;
import com.huawei.operation.utils.WebViewUtils;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.nrt;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.util.Locale;

/* loaded from: classes9.dex */
public class WatchfaceUrlActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private String f9272a;
    private HealthTextView b;
    private Context c;
    private Handler d = new Handler() { // from class: com.huawei.ui.device.activity.watchface.WatchfaceUrlActivity.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("WatchfaceUrlActivity", "handleMessage(), message is null");
                return;
            }
            super.handleMessage(message);
            if (message.what == 1) {
                WatchfaceUrlActivity.this.a();
            }
        }
    };
    private CustomTitleBar e;
    private LinearLayout f;
    private String g;
    private LinearLayout h;
    private HealthProgressBar i;
    private String j;
    private String k;
    private RelativeLayout l;
    private HealthButton m;
    private String n;
    private WebView o;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = this;
        if (getIntent() == null) {
            finish();
            return;
        }
        if (!Utils.o()) {
            this.f9272a = "CN";
        }
        if (LoginInit.getInstance(this.c).getIsLogined()) {
            this.f9272a = LoginInit.getInstance(this.c).getAccountInfo(1010);
        }
        this.n = getIntent().getStringExtra("Agreement_key");
        if (CommonUtil.w(this.c.getApplicationContext())) {
            setContentView(R.layout.fragment_web_view_error_1);
            b();
        } else {
            setContentView(R.layout.activity_watchface_url_item);
            LogUtil.a("WatchfaceUrlActivity", "onCreate()");
            e();
            c();
        }
    }

    private void c() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.device.activity.watchface.WatchfaceUrlActivity.1
            @Override // java.lang.Runnable
            public void run() {
                String commonCountryCode = GRSManager.a(WatchfaceUrlActivity.this.c).getCommonCountryCode();
                WatchfaceUrlActivity watchfaceUrlActivity = WatchfaceUrlActivity.this;
                watchfaceUrlActivity.j = GRSManager.a(watchfaceUrlActivity.c).getNoCheckUrl("domainConsumerHuawei", commonCountryCode);
                if (TextUtils.isEmpty(WatchfaceUrlActivity.this.j)) {
                    LogUtil.h("WatchfaceUrlActivity", "loadWebViewUrl mHwConsumerUrl is empty");
                } else if (!WatchfaceUrlActivity.this.isFinishing() && !WatchfaceUrlActivity.this.isDestroyed()) {
                    WatchfaceUrlActivity.this.d.sendEmptyMessage(1);
                } else {
                    LogUtil.h("WatchfaceUrlActivity", "loadWebViewUrl isFinishing or isDestroyed");
                }
            }
        });
    }

    private void b() {
        LogUtil.a("WatchfaceUrlActivity", "ServiceItemActivity.isErrorWebView");
        ((HealthButton) findViewById(R.id.btn_go_setting)).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.watchface.WatchfaceUrlActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("WatchfaceUrlActivity", "ServiceItemActivity.uninstallApk");
                try {
                    CommonUtil.p(WatchfaceUrlActivity.this.c, "com.google.android.webview");
                } catch (Exception unused) {
                    LogUtil.b("WatchfaceUrlActivity", "ServiceItemActivity test");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.mini_shop__webview_titlebar);
        this.e = customTitleBar;
        customTitleBar.setTitleText(this.c.getString(R.string._2130843340_res_0x7f0216cc));
    }

    private void e() {
        LogUtil.a("WatchfaceUrlActivity", "initView()");
        WebView webView = (WebView) findViewById(R.id.hw_health_user_agreement_webview);
        this.o = webView;
        nrt.cKg_(this.c, webView);
        this.b = (HealthTextView) findViewById(R.id.check_more);
        if (CommonUtil.cd()) {
            this.b.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.watchface.WatchfaceUrlActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClassName("com.huawei.systemmanager", "com.huawei.dataprivacycenter.MainActivity");
                    WatchfaceUrlActivity.this.startActivity(intent);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        } else {
            this.b.setVisibility(8);
        }
        this.f = (LinearLayout) findViewById(R.id.service_layout_loading);
        this.h = (LinearLayout) findViewById(R.id.service_item_linear);
        this.l = (RelativeLayout) findViewById(R.id.reload_layout);
        this.m = (HealthButton) findViewById(R.id.btn_no_net_work);
        this.f.setVisibility(0);
        HealthProgressBar healthProgressBar = (HealthProgressBar) this.f.findViewById(R.id.service_info_loading);
        this.i = healthProgressBar;
        healthProgressBar.setVisibility(0);
        this.m.setOnClickListener(this);
        g();
    }

    private void g() {
        if (CommonUtil.aa(this.c.getApplicationContext())) {
            return;
        }
        this.l.setVisibility(0);
        this.h.setVisibility(8);
        this.f.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.e = (CustomTitleBar) nsy.cMc_(this, R.id.hw_health_service_title_layout);
        this.g = this.j + "/minisite/cloudservice/themes/terms.htm?country=";
        if ("termsurl".equals(this.n)) {
            this.g = this.j + "/minisite/cloudservice/themes/terms.htm?country=";
            this.k = this.c.getString(R.string._2130843340_res_0x7f0216cc);
        }
        if ("privacyurl".equals(this.n)) {
            this.g = this.j + "/minisite/cloudservice/themes/privacy-statement.htm?country=";
            this.k = this.c.getString(R.string._2130843316_res_0x7f0216b4);
        }
        this.e.setTitleText(this.k);
        WebViewUtils.setWebSettings(this.c, this.o, false);
        WebView webView = this.o;
        String h = h();
        webView.loadUrl(h);
        WebViewInstrumentation.loadUrl(webView, h);
        WebView webView2 = this.o;
        UserAgreementWebViewClient userAgreementWebViewClient = new UserAgreementWebViewClient(this.c, this.i, this.f, "WatchfaceUrlActivity");
        if (webView2 instanceof WebView) {
            APMSH5LoadInstrument.setWebViewClient(webView2, userAgreementWebViewClient);
        } else {
            webView2.setWebViewClient(userAgreementWebViewClient);
        }
    }

    private String h() {
        StringBuilder sb;
        String language = Locale.getDefault().getLanguage();
        String script = Locale.getDefault().getScript();
        if (TextUtils.isEmpty(this.f9272a)) {
            String b = SharedPreferenceManager.b(this.c, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country");
            this.f9272a = b;
            if (TextUtils.isEmpty(b)) {
                this.f9272a = "GB";
            }
        }
        String country = Locale.getDefault().getCountry();
        if (TextUtils.isEmpty(script)) {
            sb = new StringBuilder();
            sb.append(language);
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(language);
            sb2.append("_");
            sb2.append(script);
            sb = sb2;
        }
        sb.append("_");
        sb.append(country);
        return this.g + this.f9272a + Constants.LANGUAGE + sb.toString();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        LogUtil.a("WatchfaceUrlActivity", "onRestart()");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.a("WatchfaceUrlActivity", "onResume()");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        LogUtil.a("WatchfaceUrlActivity", "onPause()");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        d();
        LogUtil.a("WatchfaceUrlActivity", "onDestroy");
        super.onDestroy();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.m) {
            CommonUtil.q(this.c);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d() {
        LogUtil.a("WatchfaceUrlActivity", "destroyWebView");
        if (this.o != null) {
            LogUtil.a("WatchfaceUrlActivity", "onDestroy destroyWebView");
            ViewParent parent = this.o.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.o);
            }
            try {
                this.o.destroy();
            } catch (Exception unused) {
                LogUtil.b("WatchfaceUrlActivity", "destroyWebView exception");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
