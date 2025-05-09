package com.huawei.hwidauth.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.content.FileProvider;
import androidx.webkit.ProxyConfig;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.android.app.ActionBarEx;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hms.common.utils.PresetUtil;
import com.huawei.hms.common.utils.UriCheckUtils;
import com.huawei.hwidauth.api.Result;
import com.huawei.hwidauth.api.ResultCallBack;
import com.huawei.hwidauth.h.f;
import com.huawei.hwidauth.ui.f;
import com.huawei.operation.utils.Constants;
import com.huawei.secure.android.common.activity.SafeActivity;
import com.huawei.secure.android.common.intent.SafeBundle;
import com.huawei.secure.android.common.intent.SafeIntent;
import com.huawei.secure.android.common.util.EncodeUtil;
import com.huawei.secure.android.common.util.ScreenUtil;
import com.huawei.secure.android.common.webview.SafeWebView;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import defpackage.kqc;
import defpackage.kqm;
import defpackage.kqn;
import defpackage.kqq;
import defpackage.kqs;
import defpackage.krj;
import defpackage.krl;
import defpackage.krm;
import defpackage.krw;
import defpackage.ksa;
import defpackage.ksb;
import defpackage.ksc;
import defpackage.ksg;
import defpackage.ksi;
import defpackage.ksm;
import defpackage.kso;
import defpackage.ksr;
import defpackage.kss;
import defpackage.kst;
import defpackage.ksy;
import defpackage.kta;
import defpackage.ktb;
import defpackage.ktd;
import defpackage.kte;
import defpackage.ktf;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class WebViewActivity extends SafeActivity implements com.huawei.hwidauth.e.b, f.b {
    private RelativeLayout b;
    private SafeWebView c;
    private ProgressBar e;
    private kso f;
    private ksc g;
    private ActionBar h;
    private int l;
    private Uri m;
    private ValueCallback<?> o;
    private String p;
    private AlertDialog q;
    private AlertDialog r;
    private PermissionRequest s;
    private AlertDialog t;

    /* renamed from: a, reason: collision with root package name */
    private TextView f6371a = null;
    private String d = "0";
    private String j = "";
    private List<String> i = new ArrayList();
    private boolean k = false;
    private boolean n = false;
    private Handler v = new Handler(Looper.getMainLooper()) { // from class: com.huawei.hwidauth.ui.WebViewActivity.14
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            if (message.what == 1001) {
                WebViewActivity.this.g.b(WebViewActivity.this.d);
            }
            super.handleMessage(message);
        }
    };

    @Override // com.huawei.secure.android.common.activity.SafeActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ksy.b("WebViewActivity", "enter onCreate", true);
        Intent intent = getIntent();
        if (intent == null) {
            ksy.c("WebViewActivity", "intent is null.", true);
            b();
            return;
        }
        SafeIntent safeIntent = new SafeIntent(intent);
        if (safeIntent.hasIntentBomb()) {
            ksy.c("WebViewActivity", "has intent bomb.", true);
            b();
            return;
        }
        ksi.bQE_(this);
        setContentView(R.layout.hwid_auth_webview);
        ksr.c(this);
        ksi.a((Activity) this);
        ScreenUtil.disableScreenshots(this);
        this.c = (SafeWebView) findViewById(R.id.hwid_auth_webView);
        this.f = new kso();
        ksc kscVar = new ksc(this, this);
        this.g = kscVar;
        kscVar.init(safeIntent);
        m();
        l();
        this.g.d();
        ksm.e(this).d();
        ksy.b("WebViewActivity", "out onCreate", true);
    }

    private void l() {
        if (kst.d()) {
            Window window = getWindow();
            int systemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
            window.clearFlags(201326592);
            window.getDecorView().setSystemUiVisibility(systemUiVisibility | 9472);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
            n();
            return;
        }
        ksy.b("WebViewActivity", "is below EMUI10.0.", true);
    }

    private void n() {
        View childAt = ((ViewGroup) getWindow().findViewById(android.R.id.content)).getChildAt(0);
        if (childAt != null) {
            childAt.setFitsSystemWindows(true);
        }
    }

    private void m() {
        ProgressBar progressBar;
        ksy.b("WebViewActivity", "initViews", true);
        if (!ksi.c("com.huawei.android.app.ActionBarEx")) {
            ksy.b("WebViewActivity", "initViews else", true);
            t();
            ag();
            ai();
        } else {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.hwid_auth_top_view);
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
            }
            ActionBar actionBar = getActionBar();
            this.h = actionBar;
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(false);
                i(" ");
                ActionBarEx.setStartIcon(this.h, true, (Drawable) null, new View.OnClickListener() { // from class: com.huawei.hwidauth.ui.WebViewActivity.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        WebViewActivity.this.g.e(6, "User cancel", "");
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
        }
        this.e = (ProgressBar) findViewById(R.id.hwid_auth_Progressbar);
        if (ksi.b() && (progressBar = this.e) != null) {
            progressBar.setProgressDrawable(getDrawable(R.drawable._2131429230_res_0x7f0b076e));
        }
        this.b = (RelativeLayout) findViewById(R.id.hwid_auth_loading);
        ksy.b("WebViewActivity", "create webview", true);
        this.c.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        r();
    }

    private void t() {
        ksy.b("WebViewActivity", "setStatusBarColor ==", true);
        Window window = getWindow();
        window.clearFlags(AppRouterExtras.COLDSTART);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(getResources().getColor(R.color._2131298380_res_0x7f09084c));
    }

    private void r() {
        ksy.b("WebViewActivity", "init WebView.", true);
        if (this.c == null) {
            ksy.c("WebViewActivity", "WebView is null.", true);
            return;
        }
        ArrayList<String> c2 = krj.c().c(this);
        this.c.setWhitelistNotMathcSubDomain((String[]) c2.toArray(new String[c2.size()]));
        d(c2);
        q();
        ah();
        p();
    }

    private void p() {
        ksy.b("WebViewActivity", "webViewPerformanceSetting ==", true);
        SafeWebView safeWebView = this.c;
        if (safeWebView == null) {
            ksy.c("WebViewActivity", "mWebView is null.", true);
        } else {
            safeWebView.setLayerType(2, null);
        }
    }

    private void q() {
        ksy.b("WebViewActivity", "webViewSetting start.", true);
        SafeWebView safeWebView = this.c;
        ksb ksbVar = new ksb(this.g, this, this.c, this.f, this);
        if (safeWebView instanceof WebView) {
            APMSH5LoadInstrument.setWebViewClient(safeWebView, ksbVar);
        } else {
            safeWebView.setWebViewClient(ksbVar);
        }
        this.c.setWebChromeClient(new c());
        this.c.addJavascriptInterface(new a(), "webLoader");
        this.c.addJavascriptInterface(new d(), "hwop");
        if (kte.c(this)) {
            this.c.setBackgroundColor(getResources().getColor(R.color._2131298380_res_0x7f09084c));
        } else {
            this.c.setBackgroundColor(0);
        }
    }

    private void d(ArrayList<String> arrayList) {
        URL url;
        ksy.b("WebViewActivity", "checkAcceptThirdPartyCookies ==", true);
        if ("from_other_app_signin".equalsIgnoreCase(this.g.c())) {
            try {
                url = new URL(this.g.a().replaceAll("[\\\\#]", "/"));
            } catch (MalformedURLException unused) {
                ksy.b("WebViewActivity", "MalformedURLException", true);
                url = null;
            }
            String host = url != null ? url.getHost() : "";
            ksy.b("WebViewActivity", "checkAcceptThirdPartyCookies host== " + host, false);
            if (arrayList.contains(host)) {
                ksy.b("WebViewActivity", "checkAcceptThirdPartyCookies contain", true);
                CookieManager.getInstance().setAcceptThirdPartyCookies(this.c, true);
            } else {
                ksy.b("WebViewActivity", "checkAcceptThirdPartyCookies not contain", true);
                this.g.e(6, "checkAcceptThirdPartyCookies not contain", "");
            }
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        ksy.b("WebViewActivity", "onRequestPermissionsResult requestCode = " + i, true);
        if (i == 1006) {
            if (this.g.a(iArr)) {
                i();
                return;
            } else {
                i(true);
                bQy_(null);
                return;
            }
        }
        if (i == 1007) {
            if (this.g.a(iArr)) {
                o();
                return;
            } else {
                i(false);
                bQy_(null);
                return;
            }
        }
        if (i == 1008) {
            if (this.g.a(iArr)) {
                e(true);
            } else {
                e(false);
            }
        }
    }

    private void e(final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.huawei.hwidauth.ui.WebViewActivity.9
            @Override // java.lang.Runnable
            public void run() {
                if (z) {
                    ksy.b("WebViewActivity", "onPermissionRequest: granted", true);
                    WebViewActivity.this.s.grant(WebViewActivity.this.s.getResources());
                } else {
                    ksy.b("WebViewActivity", "onPermissionRequest: deny", true);
                    WebViewActivity.this.s.deny();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        ksy.b("WebViewActivity", "enter getDevAuthCode", true);
        this.v.sendEmptyMessage(1001);
    }

    private void s() {
        SafeWebView safeWebView = this.c;
        if (safeWebView == null) {
            ksy.c("WebViewActivity", "mWebView is null.", true);
            return;
        }
        WebSettings settings = safeWebView.getSettings();
        settings.setUserAgentString(settings.getUserAgentString() + d((Context) this));
        StringBuilder sb = new StringBuilder("getUserAgentString:");
        sb.append(settings.getUserAgentString());
        ksy.b("WebViewActivity", sb.toString(), false);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void a() {
        char c2;
        ksy.b("WebViewActivity", "loadWebViewUrl start.", true);
        s();
        String c3 = this.g.c();
        c3.hashCode();
        switch (c3.hashCode()) {
            case -2108773991:
                if (c3.equals("scan_code_login")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -1899443177:
                if (c3.equals("from_open_realNameInfo")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -1245469133:
                if (c3.equals("from_open_auth_app_list")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case -982451862:
                if (c3.equals("from_open_childInfo")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case -776617635:
                if (c3.equals("from_open_center_mng_new")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case -563575172:
                if (c3.equals("from_open_center_mng")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case -313839168:
                if (c3.equals("from_qr_authorize")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case -39986271:
                if (c3.equals("verify_password")) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            case -29230078:
                if (c3.equals("verify_password_new")) {
                    c2 = '\b';
                    break;
                }
                c2 = 65535;
                break;
            case 201671127:
                if (c3.equals("from_signin")) {
                    c2 = '\t';
                    break;
                }
                c2 = 65535;
                break;
            case 208375119:
                if (c3.equals("from_v3_signin")) {
                    c2 = '\n';
                    break;
                }
                c2 = 65535;
                break;
            case 341052952:
                if (c3.equals("open_personal_info")) {
                    c2 = 11;
                    break;
                }
                c2 = 65535;
                break;
            case 1416141828:
                if (c3.equals("from_other_app_signin")) {
                    c2 = '\f';
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
                ksy.c("WebViewActivity", "scan_code_login", true);
                z();
                break;
            case 1:
            case 3:
            case 6:
                w();
                break;
            case 2:
                ksy.c("WebViewActivity", "from_open_auth_app_list", true);
                aa();
                break;
            case 4:
                ksy.c("WebViewActivity", "from_open_center_mng_new", true);
                g(com.huawei.hwidauth.i.a.a().c());
                break;
            case 5:
                ksy.c("WebViewActivity", "from_open_center_mng", true);
                g(com.huawei.hwidauth.i.a.a().c());
                break;
            case 7:
                ksy.c("WebViewActivity", "verify_password", true);
                ab();
                break;
            case '\b':
                ksy.c("WebViewActivity", "verify_password_new", true);
                ab();
                break;
            case '\t':
                ksy.c("WebViewActivity", "from_signin", true);
                ac();
                break;
            case '\n':
                ksy.c("WebViewActivity", "from_v3_signin", true);
                ac();
                break;
            case 11:
                ksy.c("WebViewActivity", "open_personal_info", true);
                u();
                break;
            case '\f':
                ksy.c("WebViewActivity", "from_other_app_signin", true);
                ad();
                break;
            default:
                ksy.c("WebViewActivity", "from error", true);
                this.g.e(6, "User cancel", "");
                finish();
                break;
        }
    }

    private void w() {
        char c2;
        String c3 = this.g.c();
        c3.hashCode();
        int hashCode = c3.hashCode();
        if (hashCode == -1899443177) {
            if (c3.equals("from_open_realNameInfo")) {
                c2 = 0;
            }
            c2 = 65535;
        } else if (hashCode != -982451862) {
            if (hashCode == -313839168 && c3.equals("from_qr_authorize")) {
                c2 = 2;
            }
            c2 = 65535;
        } else {
            if (c3.equals("from_open_childInfo")) {
                c2 = 1;
            }
            c2 = 65535;
        }
        if (c2 == 0) {
            ksy.c("WebViewActivity", "open_realNameInfo", true);
            v();
        } else if (c2 == 1) {
            ksy.c("WebViewActivity", "open_childInfo", true);
            y();
        } else if (c2 == 2) {
            ksy.c("WebViewActivity", "qr_authorize", true);
            x();
        } else {
            ksy.b("WebViewActivity", "dealOtherOper not match", true);
        }
    }

    private void u() {
        ksy.b("WebViewActivity", "handleOpenPersonalUrl start.", true);
        String j = com.huawei.hwidauth.i.a.a().j();
        ksy.b("WebViewActivity", "atRemoteLoginUrl：" + j, false);
        b(this.c, j, this.g.d(this));
    }

    private void v() {
        ksy.b("WebViewActivity", "handleOpenRealNameInfoUrl start.", true);
        String j = com.huawei.hwidauth.i.a.a().j();
        ksy.b("WebViewActivity", "atRemoteLoginUrl：" + j, false);
        b(this.c, j, this.g.b(this));
    }

    private void x() {
        ksy.b("WebViewActivity", "handleQrAurhorizeUrl start.", true);
        String e2 = this.g.e(this);
        ksy.b("WebViewActivity", "qrAurhorizeUrl：" + e2, false);
        b(this.c, e2);
    }

    private void y() {
        ksy.b("WebViewActivity", "handleOpenChildInfoUrl start.", true);
        String j = com.huawei.hwidauth.i.a.a().j();
        ksy.b("WebViewActivity", "atRemoteLoginUrl：" + j, false);
        b(this.c, j, this.g.a(this));
    }

    private void b(SafeWebView safeWebView, String str, String str2) {
        if (safeWebView != null && d(str) && safeWebView.isWhiteListUrl(str)) {
            String str3 = str + str2;
            safeWebView.loadUrl(str3);
            WebViewInstrumentation.loadUrl(safeWebView, str3);
            return;
        }
        this.g.e(2015, "webview or url is invalid.", "");
    }

    private void a(SafeWebView safeWebView, String str, Map map) {
        if (safeWebView != null && d(str) && safeWebView.isWhiteListUrl(str)) {
            safeWebView.loadUrl(str, map);
        } else {
            this.g.e(2015, "webview or url is invalid.", "");
        }
    }

    private void b(SafeWebView safeWebView, String str) {
        if (safeWebView != null && d(str) && safeWebView.isWhiteListUrl(str)) {
            safeWebView.loadUrl(str);
            WebViewInstrumentation.loadUrl(safeWebView, str);
        } else {
            this.g.e(2015, "webview or url is invalid.", "");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(SafeWebView safeWebView, String str, byte[] bArr) {
        if (safeWebView != null && d(str) && safeWebView.isWhiteListUrl(str)) {
            safeWebView.postUrl(str, bArr);
        } else {
            this.g.e(2015, "webview or url is invalid.", "");
        }
    }

    private boolean d(String str) {
        String a2 = ktf.a(str);
        if (!TextUtils.isEmpty(str) && f(a2)) {
            return true;
        }
        ksy.b("WebViewActivity", "is not a right url", true);
        return false;
    }

    private boolean f(String str) {
        return ProxyConfig.MATCH_HTTPS.equalsIgnoreCase(str) || "http".equalsIgnoreCase(str) || com.huawei.hwcloudjs.g.a.c.equalsIgnoreCase(str) || "mqq".equalsIgnoreCase(str) || "weixin".equalsIgnoreCase(str);
    }

    private void ab() {
        ksy.b("WebViewActivity", "handleVerifyPasswordUrl start.", true);
        String str = com.huawei.hwidauth.i.a.a().i() + this.g.c(ksi.e(this));
        this.j = ksi.s();
        String str2 = str + "&clientNonce=" + this.j;
        ksy.b("WebViewActivity", "verifyPasswordUrl：" + str2, false);
        a(this.c, str2, this.g.b());
    }

    private void z() {
        ksy.b("WebViewActivity", "handleAuthListUrl start.", true);
        String str = com.huawei.hwidauth.i.a.a().h() + this.g.e(ksi.e(this));
        ksy.b("WebViewActivity", "qrLoginUrl：" + str, false);
        a(this.c, str, this.g.h(str));
    }

    private void aa() {
        ksy.b("WebViewActivity", "handleAuthListUrl start.", true);
        String e2 = com.huawei.hwidauth.i.a.a().e();
        ksy.b("WebViewActivity", "authAppListUrl：" + e2, false);
        c(this.c, e2, ksi.b(this.g.d(ksi.e(this))));
    }

    private void g(String str) {
        ksy.b("WebViewActivity", "handleCenterUrl start.", true);
        String j = com.huawei.hwidauth.i.a.a().j();
        ksy.b("WebViewActivity", "centerUrl：" + j, false);
        b(this.c, j, this.g.e(ksi.e(this), str));
    }

    private void ad() {
        ksy.b("WebViewActivity", "handleOtherAppSignInUrl start.", true);
        ksc kscVar = this.g;
        String o = kscVar.o(kscVar.a());
        ksy.b("WebViewActivity", "handleOtherAppSignInUrl：" + o, false);
        b(this.c, o);
    }

    private void ac() {
        ksy.b("WebViewActivity", "handleSignInUrl start.", true);
        String f = this.g.f(ksi.e(this));
        ksy.b("WebViewActivity", "handleSignInUrl sigInUrl：" + f, false);
        HashMap<String, String> g = this.g.g(f);
        ksy.b("WebViewActivity", "signInHeaders", true);
        ksy.b("WebViewActivity", "signInHeaders：" + g, false);
        a(this.c, f, g);
    }

    private void ah() {
        ksy.b("WebViewActivity", "setWebViewSafeSettings ==", true);
        WebSettings settings = this.c.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setSupportZoom(false);
        settings.setSavePassword(false);
        settings.setCacheMode(2);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setMixedContentMode(1);
        settings.setGeolocationEnabled(false);
        settings.setAllowContentAccess(false);
        settings.setDomStorageEnabled(true);
        this.c.removeJavascriptInterface("searchBoxJavaBridge_");
        this.c.removeJavascriptInterface("accessibility");
        this.c.removeJavascriptInterface("accessibilityTraversal");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(String str) {
        if (TextUtils.isEmpty(str)) {
            str = " ";
        }
        TextView textView = this.f6371a;
        if (textView != null) {
            textView.setText(str);
        } else {
            setTitle(str);
        }
    }

    private void ai() {
        this.f6371a = (TextView) findViewById(R.id.hwid_auth_title_text);
        ImageView imageView = (ImageView) findViewById(R.id.hwid_auth_close_imageview);
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.hwidauth.ui.WebViewActivity.12
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    WebViewActivity.this.g.e(6, "User cancel", "");
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    private void ag() {
        try {
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.hide();
            }
        } catch (Exception unused) {
            ksy.c("WebViewActivity", "hideActionbar Exception", true);
        }
    }

    @Override // com.huawei.hwidauth.ui.f.b
    public void a(final String str) {
        ksy.b("WebViewActivity", "callbackForJs ==", true);
        runOnUiThread(new Runnable() { // from class: com.huawei.hwidauth.ui.WebViewActivity.15
            @Override // java.lang.Runnable
            public void run() {
                if (WebViewActivity.this.c != null) {
                    SafeWebView safeWebView = WebViewActivity.this.c;
                    String h = WebViewActivity.this.h(str);
                    safeWebView.loadUrl(h);
                    WebViewInstrumentation.loadUrl(safeWebView, h);
                }
            }
        });
    }

    @Override // com.huawei.hwidauth.ui.f.b
    public void a(boolean z) {
        ksy.b("WebViewActivity", "setLoading loading =" + z, true);
        RelativeLayout relativeLayout = this.b;
        if (relativeLayout != null && z) {
            relativeLayout.setVisibility(0);
            return;
        }
        if (relativeLayout != null && relativeLayout.getVisibility() == 0) {
            this.b.setVisibility(8);
        }
        a();
    }

    @Override // com.huawei.hwidauth.ui.f.b
    public void b(final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        runOnUiThread(new Runnable() { // from class: com.huawei.hwidauth.ui.WebViewActivity.3
            @Override // java.lang.Runnable
            public void run() {
                if (WebViewActivity.this.c != null) {
                    SafeWebView safeWebView = WebViewActivity.this.c;
                    String str2 = str;
                    safeWebView.loadUrl(str2);
                    WebViewInstrumentation.loadUrl(safeWebView, str2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String h(String str) {
        return "javascript:getDevAuthCodeCallback('" + EncodeUtil.encodeForJavaScript(str) + Constants.RIGHT_BRACKET;
    }

    @Override // com.huawei.hwidauth.ui.f.b
    public void b() {
        ksy.b("WebViewActivity", "exitApp finish", true);
        finish();
    }

    @Override // com.huawei.hwidauth.ui.f.b
    public void a(int i, Intent intent) {
        ksy.b("WebViewActivity", "exit resultCode", true);
        setResult(i, intent);
        finish();
    }

    @Override // com.huawei.hwidauth.ui.f.b
    public void b(int i, Intent intent) {
        ksy.b("WebViewActivity", "exitApp finish", true);
        if (TextUtils.isEmpty(this.g.h())) {
            ksy.b("WebViewActivity", "exitApp finish", true);
            finish();
        } else {
            setResult(i, intent);
            finish();
        }
    }

    @Override // com.huawei.hwidauth.e.b
    public void a(Intent intent) {
        ksy.b("WebViewActivity", "WeixinAuthHandler receive:", true);
        if (-1 == intent.getIntExtra("resultCode", 0)) {
            ksy.b("WebViewActivity", "get weChat code success", true);
            final String stringExtra = intent.getStringExtra("code");
            final String stringExtra2 = intent.getStringExtra("state");
            final String g = com.huawei.hwidauth.i.a.a().g();
            ksy.b("WebViewActivity", "weChatCodeAuthUrl: " + g, false);
            new Handler(getMainLooper()).post(new Runnable() { // from class: com.huawei.hwidauth.ui.WebViewActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    WebViewActivity webViewActivity = WebViewActivity.this;
                    webViewActivity.c(webViewActivity.c, g, ksi.b(WebViewActivity.this.g.a(stringExtra, stringExtra2)));
                }
            });
        } else {
            ksy.c("WebViewActivity", "not allowed to login with weChat", true);
        }
        krm.e().c();
    }

    final class a {
        a() {
        }

        @JavascriptInterface
        public void getDevAuthCode(final String str) {
            ksy.b("WebViewActivity", "getDevAuthCode " + str, false);
            WebViewActivity.this.d = "0";
            if (ksm.e(WebViewActivity.this).b()) {
                a(str);
            } else if (ksm.e(WebViewActivity.this).a()) {
                ksm.e(WebViewActivity.this).a(new f.a() { // from class: com.huawei.hwidauth.ui.WebViewActivity.a.2
                    @Override // com.huawei.hwidauth.h.f.a
                    public void a(SafeBundle safeBundle) {
                        a.this.a(str);
                    }

                    @Override // com.huawei.hwidauth.h.f.a
                    public void a(int i, String str2) {
                        ksy.b("WebViewActivity", "getDevAuthCode get allowlist failed from file.", true);
                        WebViewActivity.this.a(WebViewActivity.this.g.d("1", "9999"));
                    }
                });
            } else {
                ksm.e(WebViewActivity.this).c(new f.a() { // from class: com.huawei.hwidauth.ui.WebViewActivity.a.1
                    @Override // com.huawei.hwidauth.h.f.a
                    public void a(SafeBundle safeBundle) {
                        a.this.a(str);
                    }

                    @Override // com.huawei.hwidauth.h.f.a
                    public void a(int i, String str2) {
                        ksy.b("WebViewActivity", "getDevAuthCode get allowlist failed.", true);
                        WebViewActivity.this.a(WebViewActivity.this.g.d("1", "9999"));
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String str) {
            if (ksm.e(WebViewActivity.this).d("getDevAuthCode", WebViewActivity.this.p, WebViewActivity.this.c)) {
                WebViewActivity.this.e(str);
                return;
            }
            ksy.b("WebViewActivity", "getDevAuthCode allowlist match result: false", true);
            ksg.c(WebViewActivity.this, 907115013, 2016, "interface getDevAuthCode check allowlist failed, url is " + WebViewActivity.this.p, WebViewActivity.this.g.j(), "accountPickerH5.getDevAuthCode", "api_ret");
            WebViewActivity webViewActivity = WebViewActivity.this;
            webViewActivity.a(webViewActivity.g.d("1", "9999"));
        }

        @JavascriptInterface
        public void getDevAuthCode(final String str, final String str2) {
            ksy.b("WebViewActivity", "getDevAuthCode " + str + "--param==" + str2, false);
            if (ksm.e(WebViewActivity.this).b()) {
                d(str, str2);
            } else if (ksm.e(WebViewActivity.this).a()) {
                ksm.e(WebViewActivity.this).a(new f.a() { // from class: com.huawei.hwidauth.ui.WebViewActivity.a.4
                    @Override // com.huawei.hwidauth.h.f.a
                    public void a(SafeBundle safeBundle) {
                        a.this.d(str, str2);
                    }

                    @Override // com.huawei.hwidauth.h.f.a
                    public void a(int i, String str3) {
                        ksy.b("WebViewActivity", "getDevAuthCode get allowlist failed from file.", true);
                        WebViewActivity.this.a(WebViewActivity.this.g.d("1", "9999"));
                    }
                });
            } else {
                ksm.e(WebViewActivity.this).c(new f.a() { // from class: com.huawei.hwidauth.ui.WebViewActivity.a.6
                    @Override // com.huawei.hwidauth.h.f.a
                    public void a(SafeBundle safeBundle) {
                        a.this.d(str, str2);
                    }

                    @Override // com.huawei.hwidauth.h.f.a
                    public void a(int i, String str3) {
                        ksy.b("WebViewActivity", "getDevAuthCode with two params get allowlist failed.", true);
                        WebViewActivity.this.a(WebViewActivity.this.g.d("1", "9999"));
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(String str, String str2) {
            if (ksm.e(WebViewActivity.this).d("getDevAuthCode", WebViewActivity.this.p, WebViewActivity.this.c)) {
                try {
                    WebViewActivity.this.d = new JSONObject(str2).getString("oprType");
                } catch (JSONException unused) {
                    WebViewActivity.this.d = "-1";
                    ksy.c("WebViewActivity", "JSONException", true);
                }
                WebViewActivity.this.e(str);
                return;
            }
            ksy.b("WebViewActivity", "getDevAuthCode with two params allowlist match result: false", true);
            WebViewActivity webViewActivity = WebViewActivity.this;
            webViewActivity.a(webViewActivity.g.d("1", "9999"));
            ksg.c(WebViewActivity.this, 907115013, 2016, "interface getDevAuthCode check allowlist failed, url is " + WebViewActivity.this.p, WebViewActivity.this.g.j(), "accountPickerH5.getDevAuthCode", "api_ret");
        }

        @JavascriptInterface
        public void intoApp(final String str) {
            ksy.b("WebViewActivity", "enter intoApp", true);
            ksy.b("WebViewActivity", "enter intoApp returnMsg = " + str, false);
            new Handler(WebViewActivity.this.getMainLooper()).post(new Runnable() { // from class: com.huawei.hwidauth.ui.WebViewActivity.a.8
                @Override // java.lang.Runnable
                public void run() {
                    ksy.b("WebViewActivity", "intoApp finish WebViewActivity", true);
                    if (TextUtils.isEmpty(str)) {
                        WebViewActivity.this.g.e(7, "", "");
                        ksy.c("WebViewActivity", "enter intoApp returnMsg is null ", true);
                        WebViewActivity.this.finish();
                        return;
                    }
                    if ("scan_code_login".equalsIgnoreCase(WebViewActivity.this.g.c()) && !str.equalsIgnoreCase("OK")) {
                        kqm kqmVar = new kqm("atExpired".equalsIgnoreCase(str) ? 2008 : 404, str);
                        kqn kqnVar = new kqn(kqmVar);
                        kqmVar.b(false);
                        a.this.d(ksi.j(), kqnVar, str, 907115008, "accountPickerH5.qrCodeAuthLogin", 404);
                        return;
                    }
                    if (!str.equalsIgnoreCase("OK")) {
                        if (!"open_personal_info".equalsIgnoreCase(WebViewActivity.this.g.c()) || !str.equalsIgnoreCase("atExpired")) {
                            if (("verify_password".equalsIgnoreCase(WebViewActivity.this.g.c()) || "verify_password_new".equalsIgnoreCase(WebViewActivity.this.g.c())) && str.equalsIgnoreCase("atExpired")) {
                                String k = WebViewActivity.this.k();
                                kqm kqmVar2 = new kqm(2008, str);
                                kqc kqcVar = new kqc("", kqmVar2);
                                kqmVar2.b(false);
                                a.this.d(ksi.g(), kqcVar, str, 907115003, k, 404);
                                return;
                            }
                            if (!WebViewActivity.this.g.f()) {
                                if (!WebViewActivity.this.g.g()) {
                                    if ((!"from_open_realNameInfo".equalsIgnoreCase(WebViewActivity.this.g.c()) && !"from_open_childInfo".equalsIgnoreCase(WebViewActivity.this.g.c())) || !str.equalsIgnoreCase("atExpired")) {
                                        WebViewActivity.this.g.e(7, str, "");
                                        return;
                                    }
                                    kqm kqmVar3 = new kqm(2008, str);
                                    kqs kqsVar = new kqs(kqmVar3);
                                    kqmVar3.b(false);
                                    if (!"from_open_childInfo".equalsIgnoreCase(WebViewActivity.this.g.c())) {
                                        a.this.d(ksi.f(), kqsVar, str, 907115010, "accountPickerH5.openRealNameInfo", 404);
                                        return;
                                    }
                                    ksy.b("WebViewActivity", "enter intoApp = OPEN_CHILD_INFO ", true);
                                    ksy.b("WebViewActivity", "enter intoApp = OPEN_CHILD_INFO " + str, false);
                                    a.this.d(ksi.l(), kqsVar, str, 907115012, "accountPickerH5.openChildAccountDetailInfo", 2008);
                                    return;
                                }
                                Intent intent = new Intent();
                                intent.putExtra("retValue", str);
                                WebViewActivity.this.setResult(8, intent);
                                ksg.c(WebViewActivity.this, 907115001, 200, str, WebViewActivity.this.g.j(), "accountPickerH5.signIn_pageFromOtherApp", "api_ret");
                                WebViewActivity.this.b();
                                return;
                            }
                            Intent intent2 = new Intent();
                            intent2.putExtra("retValue", str);
                            WebViewActivity.this.setResult(8, intent2);
                            ksg.c(WebViewActivity.this, 907115001, 200, str, WebViewActivity.this.g.j(), "accountPickerH5.signIn_v3", "api_ret");
                            WebViewActivity.this.b();
                            return;
                        }
                        kqm kqmVar4 = new kqm(2008, str);
                        kqs kqsVar2 = new kqs(kqmVar4);
                        kqmVar4.b(false);
                        a.this.d(ksi.i(), kqsVar2, str, 907115006, "accountPickerH5.openPersonalInfo", 404);
                        return;
                    }
                    a.this.d(str);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(String str) {
            ksy.b("WebViewActivity", "handleSuccessMsg start.", true);
            if (WebViewActivity.this.c()) {
                kqm kqmVar = new kqm(501, str);
                kqq kqqVar = new kqq(kqmVar);
                kqmVar.b(true);
                d(ksi.n(), kqqVar, str, 907115004, "accountPickerH5.deleteAccount", 200);
                return;
            }
            if (!WebViewActivity.this.d()) {
                if (!"from_open_realNameInfo".equalsIgnoreCase(WebViewActivity.this.g.c())) {
                    if (!"from_open_childInfo".equalsIgnoreCase(WebViewActivity.this.g.c())) {
                        WebViewActivity.this.g.e(7, str, "");
                        ksy.b("WebViewActivity", "enter intoApp returnMsg = " + str, false);
                        WebViewActivity.this.finish();
                        return;
                    }
                    kqm kqmVar2 = new kqm(504, str);
                    kqs kqsVar = new kqs(kqmVar2);
                    kqmVar2.b(true);
                    d(ksi.f(), kqsVar, str, 907115012, "accountPickerH5.openChildAccountDetailInfo", 200);
                    return;
                }
                kqm kqmVar3 = new kqm(503, str);
                kqs kqsVar2 = new kqs(kqmVar3);
                kqmVar3.b(true);
                d(ksi.f(), kqsVar2, str, 907115010, "accountPickerH5.openRealNameInfo", 200);
                return;
            }
            kqm kqmVar4 = new kqm(TypedValues.PositionType.TYPE_DRAWPATH, str);
            kqq kqqVar2 = new kqq(kqmVar4);
            kqmVar4.b(true);
            d(ksi.n(), kqqVar2, str, 907115004, "accountPickerH5.appealSelf", 200);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(ResultCallBack resultCallBack, Result result, String str, int i, String str2, int i2) {
            if (resultCallBack != null && result != null) {
                resultCallBack.onResult(result);
                WebViewActivity webViewActivity = WebViewActivity.this;
                ksg.c(webViewActivity, i, i2, str, webViewActivity.g.j(), str2, "api_ret");
                WebViewActivity.this.finish();
                return;
            }
            WebViewActivity webViewActivity2 = WebViewActivity.this;
            ksg.c(webViewActivity2, i, 404, "resultCallBack is null", webViewActivity2.g.j(), str2, "api_ret");
        }

        @JavascriptInterface
        public void callWeChatAuthorize(String str, String str2, String str3) {
            ksi.a(str);
            WebViewActivity.this.b(str, str2, str3);
        }

        @JavascriptInterface
        public void setForbiddenGoBackUrl(final String str) {
            ksy.b("WebViewActivity", "setForbiddenGoBackUrl:" + str, false);
            if (ksm.e(WebViewActivity.this).b()) {
                b(str);
            } else if (ksm.e(WebViewActivity.this).a()) {
                ksm.e(WebViewActivity.this).a(new f.a() { // from class: com.huawei.hwidauth.ui.WebViewActivity.a.9
                    @Override // com.huawei.hwidauth.h.f.a
                    public void a(SafeBundle safeBundle) {
                        a.this.b(str);
                    }

                    @Override // com.huawei.hwidauth.h.f.a
                    public void a(int i, String str2) {
                        ksy.b("WebViewActivity", "setForbiddenGoBackUrl get allowlist failed from file.", true);
                    }
                });
            } else {
                ksm.e(WebViewActivity.this).c(new f.a() { // from class: com.huawei.hwidauth.ui.WebViewActivity.a.10
                    @Override // com.huawei.hwidauth.h.f.a
                    public void a(SafeBundle safeBundle) {
                        a.this.b(str);
                    }

                    @Override // com.huawei.hwidauth.h.f.a
                    public void a(int i, String str2) {
                        ksy.b("WebViewActivity", "setForbiddenGoBackUrl get allowlist failed.", true);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(String str) {
            if (ksm.e(WebViewActivity.this).d("setForbiddenGoBackUrl", WebViewActivity.this.p, WebViewActivity.this.c)) {
                if (TextUtils.isEmpty(str) || WebViewActivity.this.i.contains(str)) {
                    return;
                }
                WebViewActivity.this.i.add(str);
                return;
            }
            ksy.b("WebViewActivity", "setForbiddenGoBackUrl allowlist match result: false", true);
        }

        @JavascriptInterface
        public void verifyResult(final String str, final String str2) {
            ksy.b("WebViewActivity", "checkUserPasswordResult start", true);
            if (ksm.e(WebViewActivity.this).b()) {
                e(str, str2);
            } else if (ksm.e(WebViewActivity.this).a()) {
                ksm.e(WebViewActivity.this).a(new f.a() { // from class: com.huawei.hwidauth.ui.WebViewActivity.a.7
                    @Override // com.huawei.hwidauth.h.f.a
                    public void a(SafeBundle safeBundle) {
                        a.this.e(str, str2);
                    }

                    @Override // com.huawei.hwidauth.h.f.a
                    public void a(int i, String str3) {
                        WebViewActivity.this.g.e(7, "match allowlist failed", "");
                        WebViewActivity.this.finish();
                    }
                });
            } else {
                ksm.e(WebViewActivity.this).c(new f.a() { // from class: com.huawei.hwidauth.ui.WebViewActivity.a.3
                    @Override // com.huawei.hwidauth.h.f.a
                    public void a(SafeBundle safeBundle) {
                        a.this.e(str, str2);
                    }

                    @Override // com.huawei.hwidauth.h.f.a
                    public void a(int i, String str3) {
                        ksy.b("WebViewActivity", "verifyResult get allowlist failed.", true);
                        WebViewActivity.this.g.e(7, "match allowlist failed", "");
                        WebViewActivity.this.finish();
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e(String str, final String str2) {
            if (ksm.e(WebViewActivity.this).d("verifyResult", WebViewActivity.this.p, WebViewActivity.this.c)) {
                WebViewActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.hwidauth.ui.WebViewActivity.a.5
                    @Override // java.lang.Runnable
                    public void run() {
                        ksy.b("WebViewActivity", "checkUserPasswordResult finish WebViewActivity", true);
                        String k = WebViewActivity.this.k();
                        if ((!"verify_password".equalsIgnoreCase(WebViewActivity.this.g.c()) && !"verify_password_new".equalsIgnoreCase(WebViewActivity.this.g.c())) || TextUtils.isEmpty(str2)) {
                            WebViewActivity.this.g.e(7, str2, "");
                            ksg.c(WebViewActivity.this, 907115003, 7, "token is empty or mPresenter.getFrom() is not verify_password", WebViewActivity.this.g.j(), k, "api_ret");
                            WebViewActivity.this.finish();
                            return;
                        }
                        ResultCallBack g = ksi.g();
                        kqm kqmVar = new kqm(200, "check password success.");
                        kqmVar.b(true);
                        kqc kqcVar = new kqc(str2, kqmVar);
                        if (g != null) {
                            g.onResult(kqcVar);
                            ksg.c(WebViewActivity.this, 907115003, 200, "check password Success", WebViewActivity.this.g.j(), k, "api_ret");
                            WebViewActivity.this.finish();
                        }
                    }
                });
                return;
            }
            ksy.b("WebViewActivity", "verifyResult allowlist match result: false", true);
            ksg.c(WebViewActivity.this, 907115013, 2016, "interface verifyResult check allowlist failed, url is " + WebViewActivity.this.p, WebViewActivity.this.g.j(), WebViewActivity.this.k(), "api_ret");
            WebViewActivity.this.g.e(7, "match allowlist failed", "");
            WebViewActivity.this.finish();
        }
    }

    final class d {
        d() {
        }

        @JavascriptInterface
        public String getAuthInfo() {
            return WebViewActivity.this.g.d(WebViewActivity.this.c, WebViewActivity.this.getPackageName());
        }

        @JavascriptInterface
        public void authCancel(String str) {
            ksy.b("WebViewActivity", "js call authCancel parameter:" + str, false);
            ksy.b("WebViewActivity", "js call authCancel", true);
            WebViewActivity.this.g.e(6, "User cancel", "");
        }

        @JavascriptInterface
        public void authSuccCloseH5(String str) {
            ksy.b("WebViewActivity", "js call auth suc parameter:" + str, false);
            ksy.b("WebViewActivity", "js call auth suc.", true);
            WebViewActivity.this.g.e(200, "Sign In Success", "");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, String str2, final String str3) {
        if (!ksi.d(this) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            ksy.b("WebViewActivity", "WXApp not Installed or parms invalid", true);
            final String g = com.huawei.hwidauth.i.a.a().g();
            new Handler(getMainLooper()).post(new Runnable() { // from class: com.huawei.hwidauth.ui.WebViewActivity.4
                @Override // java.lang.Runnable
                public void run() {
                    WebViewActivity.this.c.postUrl(g, ksi.b(WebViewActivity.this.g.a("-1", str3)));
                }
            });
            return;
        }
        krm.e().b(this);
        IWXAPI createWXAPI = WXAPIFactory.createWXAPI(this, str, true);
        createWXAPI.registerApp(str);
        SendAuth.Req req = new SendAuth.Req();
        req.scope = str2;
        req.state = str3;
        createWXAPI.sendReq(req);
    }

    @Override // com.huawei.hwidauth.ui.f.b
    public void a(final int i, final String str) {
        runOnUiThread(new Runnable() { // from class: com.huawei.hwidauth.ui.WebViewActivity.2
            @Override // java.lang.Runnable
            public void run() {
                if (WebViewActivity.this.b != null && WebViewActivity.this.b.getVisibility() == 0) {
                    WebViewActivity.this.b.setVisibility(8);
                }
                WebViewActivity.this.g.e(i, str, "");
            }
        });
    }

    @Override // com.huawei.hwidauth.ui.f.b
    public void c(String str) {
        this.p = str;
    }

    private String d(Context context) {
        ksy.b("WebViewActivity", "enter getUAExtra", true);
        StringBuilder sb = new StringBuilder("; phoneservice; hwidOAuthSDK_ver=6.12.0.302; app=");
        sb.append(context.getPackageName());
        sb.append("; app_ver=");
        sb.append(ksi.c(this));
        sb.append("; noNeedClientNonce; supportChooseFile=true");
        if (ksi.d(this)) {
            sb.append("; wechatinstalled");
        }
        if ("from_other_app_signin".equalsIgnoreCase(this.g.c())) {
            sb.append("; service=");
            sb.append(this.g.i());
            sb.append("; X-Huawei-Client-Info=");
            ksc kscVar = this.g;
            sb.append(kscVar.k(kscVar.a()));
        }
        sb.append("; faceRealnameSupport; androidVersionCode=");
        sb.append(Build.VERSION.SDK_INT);
        return sb.toString();
    }

    class c extends WebChromeClient {
        private c() {
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            if (WebViewActivity.this.e != null) {
                WebViewActivity.this.e.setProgress(i);
                if (i == 100) {
                    WebViewActivity.this.e.setVisibility(8);
                    return;
                } else {
                    WebViewActivity.this.e.setVisibility(0);
                    return;
                }
            }
            ksy.c("WebViewActivity", "mProgressBar is null.", true);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            ksy.b("WebViewActivity", "onConsoleMessage: message = " + consoleMessage.message(), false);
            ksy.b("WebViewActivity", "onConsoleMessage: sourceId = " + consoleMessage.sourceId(), false);
            ksy.b("WebViewActivity", "onConsoleMessage: lineNumber = " + consoleMessage.lineNumber(), false);
            ksy.b("WebViewActivity", "onConsoleMessage: messageLevel = " + consoleMessage.messageLevel(), false);
            return super.onConsoleMessage(consoleMessage);
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            ksy.b("WebViewActivity", "enter onReceivedTitle", true);
            ksy.b("WebViewActivity", "enter onReceivedTitle : " + str, false);
            String str2 = " ";
            if (TextUtils.isEmpty(str)) {
                str = " ";
            }
            if (!str.startsWith("http")) {
                str2 = str;
            } else if ("from_other_app_signin".equalsIgnoreCase(WebViewActivity.this.g.c())) {
                WebViewActivity.this.i(" ");
            }
            if (webView.getUrl() == null || str2.equalsIgnoreCase("Authorization")) {
                return;
            }
            ksy.b("WebViewActivity", "url:" + webView.getUrl(), false);
            WebViewActivity.this.i(str2);
        }

        public void openFileChooser(ValueCallback<Uri> valueCallback) {
            ksy.b("WebViewActivity", " openFileChooser < 3.0 " + Build.VERSION.SDK_INT, true);
            WebViewActivity.this.bQu_(valueCallback);
        }

        public void openFileChooser(ValueCallback<Uri> valueCallback, String str) {
            ksy.b("WebViewActivity", "openFileChooser For Android 3.0+ " + Build.VERSION.SDK_INT, true);
            WebViewActivity.this.bQu_(valueCallback);
        }

        public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
            ksy.b("WebViewActivity", "openFileChooser For Android > 4.1 " + Build.VERSION.SDK_INT, true);
            if (!WebViewActivity.this.g.m()) {
                WebViewActivity.this.bQu_(valueCallback);
                return;
            }
            WebViewActivity webViewActivity = WebViewActivity.this;
            webViewActivity.j(webViewActivity.getString(R.string._2130841117_res_0x7f020e1d));
            if (valueCallback != null) {
                try {
                    valueCallback.onReceiveValue(null);
                } catch (Exception e) {
                    ksy.b("WebViewActivity", "openFileChooser  e" + e.getClass().getSimpleName(), true);
                }
            }
        }

        @Override // android.webkit.WebChromeClient
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            ksy.b("WebViewActivity", "onShowFileChooser For Android > 5.0 " + Build.VERSION.SDK_INT, true);
            WebViewActivity.this.o = valueCallback;
            if (!WebViewActivity.this.g.m()) {
                WebViewActivity.this.l = 1002;
                ksy.b("WebViewActivity", "onShowFileChooser showChoosePicDialog", true);
                WebViewActivity.this.d(1002);
                return true;
            }
            WebViewActivity webViewActivity = WebViewActivity.this;
            webViewActivity.j(webViewActivity.getString(R.string._2130841117_res_0x7f020e1d));
            if (valueCallback != null) {
                try {
                    valueCallback.onReceiveValue(new Uri[0]);
                } catch (Exception e) {
                    ksy.b("WebViewActivity", "openFileChooser  e" + e.getClass().getSimpleName(), true);
                }
            }
            ksy.b("WebViewActivity", "onShowFileChooser return", true);
            return true;
        }

        @Override // android.webkit.WebChromeClient
        public void onPermissionRequest(PermissionRequest permissionRequest) {
            try {
                ksy.b("WebViewActivity", "onPermissionRequest:" + Arrays.toString(permissionRequest.getResources()), true);
                WebViewActivity.this.s = permissionRequest;
                for (String str : permissionRequest.getResources()) {
                    if ("android.webkit.resource.VIDEO_CAPTURE".equals(str)) {
                        WebViewActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.hwidauth.ui.WebViewActivity.c.5
                            @Override // java.lang.Runnable
                            public void run() {
                                if (!WebViewActivity.this.f()) {
                                    WebViewActivity.this.s.grant(WebViewActivity.this.s.getResources());
                                } else {
                                    WebViewActivity.this.g.a(1008);
                                }
                            }
                        });
                        return;
                    }
                }
            } catch (NullPointerException e) {
                ksy.b("WebViewActivity", "openFileChooser  e" + e.getClass().getSimpleName(), true);
            }
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        ktd.bQN_(getWindow());
        super.onPause();
        SafeWebView safeWebView = this.c;
        if (safeWebView != null) {
            safeWebView.onPause();
        }
    }

    private void af() {
        kss.b(e());
        ksy.b("WebViewActivity", "deleteFiles....successful", true);
    }

    private String e() {
        try {
            return getDir("hwId", 0).getCanonicalPath() + "/";
        } catch (IOException e2) {
            ksy.c("WebViewActivity", "IOException:" + e2.getClass().getSimpleName(), true);
            return "";
        }
    }

    @Override // com.huawei.secure.android.common.activity.SafeActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ksy.c("WebViewActivity", "onDestroy", true);
        AlertDialog alertDialog = this.q;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.q.dismiss();
        }
        AlertDialog alertDialog2 = this.r;
        if (alertDialog2 != null && alertDialog2.isShowing()) {
            this.r.dismiss();
        }
        AlertDialog alertDialog3 = this.t;
        if (alertDialog3 != null && alertDialog3.isShowing()) {
            this.t.dismiss();
        }
        krl.e(getApplicationContext()).d("fileDownLoadLastUpdate", String.valueOf(System.currentTimeMillis()));
        af();
        SafeWebView safeWebView = this.c;
        if (safeWebView != null) {
            safeWebView.stopLoading();
            this.c.setVisibility(8);
            this.c.removeAllViews();
            this.c.clearFormData();
            this.c.clearHistory();
            this.c.destroy();
        }
        this.c = null;
        krm.e().c();
        new e().start();
    }

    class e extends Thread {
        private e() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            ktb.a(WebViewActivity.this);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        ksy.b("WebViewActivity", "onBackPressed", true);
        try {
            SafeWebView safeWebView = this.c;
            if (safeWebView != null && safeWebView.canGoBack()) {
                String url = this.c.getUrl();
                boolean z = false;
                ksy.b("WebViewActivity", "currentUrl:" + url, false);
                if (!TextUtils.isEmpty(url)) {
                    String[] split = url.split("\\?");
                    if (split.length > 0 && (split[0].endsWith("wapLogin.html") || split[0].endsWith("welcome.html"))) {
                        this.g.e(6, "User cancel", "");
                        super.onBackPressed();
                        return;
                    }
                }
                Iterator<String> it = this.i.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    String next = it.next();
                    if (!TextUtils.isEmpty(url) && url.startsWith(next)) {
                        z = true;
                        break;
                    }
                }
                ksy.b("WebViewActivity", "goBackUseWap:" + z, true);
                if (z) {
                    h();
                    String j = j();
                    SafeWebView safeWebView2 = this.c;
                    safeWebView2.loadUrl(j);
                    WebViewInstrumentation.loadUrl(safeWebView2, j);
                    return;
                }
                this.c.goBack();
                return;
            }
            this.g.e(6, "User cancel", "");
            super.onBackPressed();
        } catch (RuntimeException unused) {
            ksy.c("WebViewActivity", "catch Exception throw by FragmentManager!", true);
        }
    }

    private void h() {
        if ("from_other_app_signin".equals(this.g.c())) {
            ksy.b("WebViewActivity", "onBackPressed setDiyTitle:", true);
            i("");
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.secure.android.common.activity.SafeActivity, android.app.Activity
    public void onResume() {
        ktd.bQL_(getWindow());
        super.onResume();
        SafeWebView safeWebView = this.c;
        if (safeWebView != null) {
            safeWebView.onResume();
        }
    }

    public boolean c() {
        return this.k;
    }

    @Override // com.huawei.hwidauth.ui.f.b
    public void b(boolean z) {
        this.k = z;
    }

    public boolean d() {
        return this.n;
    }

    @Override // com.huawei.hwidauth.ui.f.b
    public void c(boolean z) {
        this.n = z;
    }

    @Override // com.huawei.secure.android.common.activity.SafeActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        ksy.b("WebViewActivity", "onActivityResult " + i + " resultCode " + i2, true);
        super.onActivityResult(i, i2, intent);
        if (i == 1004 || i == 1003) {
            bQs_(i, i2, intent);
        }
    }

    private void g() {
        ksy.b("WebViewActivity", "showNoNetworkDialog", true);
        AlertDialog create = kte.bQP_(this).create();
        this.q = create;
        create.setCancelable(false);
        if (isFinishing()) {
            return;
        }
        kte.bQO_(this.q);
        this.q.show();
    }

    private void bQs_(int i, int i2, Intent intent) {
        ksy.b("WebViewActivity", "dealRequestGalleryAndCamera start.", true);
        if (!ksi.g(this)) {
            bQy_(null);
            g();
            return;
        }
        if (i == 1004 && intent != null && intent.getData() != null) {
            this.m = intent.getData();
        }
        if (d(-1 == i2 && UriCheckUtils.isUriValid(i == 1004 ? 1 : 0, this.m))) {
            bQt_(this.m);
        }
    }

    private void bQt_(Uri uri) {
        ksy.b("WebViewActivity", "startCompressPic", true);
        new krw(this, uri, new com.huawei.hwidauth.h.b() { // from class: com.huawei.hwidauth.ui.WebViewActivity.6
            @Override // com.huawei.hwidauth.h.b
            public void a(Bundle bundle) {
                Uri uri2 = (Uri) bundle.getParcelable("request_pic_uri_tag");
                ksy.b("WebViewActivity", "startCompressPic onSuccess", true);
                WebViewActivity.this.bQy_(uri2);
            }

            @Override // com.huawei.hwidauth.h.b
            public void b(Bundle bundle) {
                ksy.b("WebViewActivity", "startCompressPic onError", true);
                WebViewActivity.this.bQy_(null);
            }
        }).execute(1);
    }

    private boolean d(boolean z) {
        ksy.b("WebViewActivity", "checkNeedUpdatePic isResultOK = " + z, true);
        if (this.o == null) {
            return false;
        }
        if (z && this.m != null) {
            return true;
        }
        ksy.b("WebViewActivity", "checkNeedUpdatePic onReceiveValue = null mTmpPicUri = " + this.m, false);
        bQy_(null);
        return false;
    }

    private void i(boolean z) {
        boolean z2;
        String string;
        ksy.b("WebViewActivity", "enter  showRefuseDailog", true);
        boolean z3 = false;
        if (z) {
            z2 = checkSelfPermission("android.permission.CAMERA") != 0;
        } else {
            z3 = checkSelfPermission(ksi.d()) != 0;
            z2 = false;
        }
        if (z3) {
            string = getResources().getString(R.string._2130851246_res_0x7f0235ae, kta.b(this), getResources().getString(R.string._2130841121_res_0x7f020e21));
        } else {
            string = z2 ? getResources().getString(R.string._2130851246_res_0x7f0235ae, kta.b(this), getResources().getString(R.string._2130841122_res_0x7f020e22)) : "";
        }
        AlertDialog.Builder a2 = kte.a(this, string, getResources().getString(R.string._2130841123_res_0x7f020e23), null);
        if (isFinishing()) {
            return;
        }
        AlertDialog create = a2.create();
        kte.bQO_(create);
        ksy.b("WebViewActivity", "enter  showRefuseDailog show", true);
        create.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(String str) {
        Toast.makeText(this, str, 1).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bQu_(ValueCallback<Uri> valueCallback) {
        this.o = valueCallback;
        this.l = 1001;
        ksy.b("WebViewActivity", "localOpenFileChoose", true);
        ksy.b("WebViewActivity", "localOpenFileChoose showChoosePicDialog", true);
        d(1001);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        ksy.b("WebViewActivity", "enter -showChoosePicDialog", true);
        Uri bQI_ = ktb.bQI_(this);
        this.m = bQI_;
        if (bQI_ == null) {
            ksy.b("WebViewActivity", "showChoosePicDialog mTmpPicUri null", true);
            return;
        }
        this.l = i;
        AlertDialog create = new ksa(this, this.m, new com.huawei.hwidauth.ui.d() { // from class: com.huawei.hwidauth.ui.WebViewActivity.10
            @Override // com.huawei.hwidauth.ui.d
            public void a() {
                ksy.b("WebViewActivity", "clickCancel", true);
                WebViewActivity.this.bQy_(null);
            }

            @Override // com.huawei.hwidauth.ui.d
            public void b() {
                ksy.b("WebViewActivity", "clickCamera", true);
                if (!WebViewActivity.this.f()) {
                    WebViewActivity.this.i();
                } else {
                    WebViewActivity.this.c(0);
                }
            }

            @Override // com.huawei.hwidauth.ui.d
            public void c() {
                ksy.b("WebViewActivity", "clickPhoto", true);
                boolean z = WebViewActivity.this.checkSelfPermission(ksi.d()) == 0;
                ksy.b("WebViewActivity", "clickPhoto hasStoragePer" + z, true);
                if (z) {
                    WebViewActivity.this.o();
                } else {
                    WebViewActivity.this.c(1);
                }
            }
        }).create();
        this.r = create;
        kte.bQO_(create);
        this.r.setCanceledOnTouchOutside(false);
        this.r.setCancelable(false);
        this.r.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.huawei.hwidauth.ui.WebViewActivity.8
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                ksy.b("WebViewActivity", "dialog cancel", true);
                WebViewActivity.this.bQy_(null);
            }
        });
        ksy.b("WebViewActivity", "show showChoosePicDialog", true);
        if (isFinishing()) {
            return;
        }
        kte.bQO_(this.r);
        this.r.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f() {
        boolean z = checkSelfPermission("android.permission.CAMERA") == 0;
        ksy.b("WebViewActivity", "clickCamera hasCameraPer: " + z, true);
        return !z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        ksy.b("WebViewActivity", "init showPermissionTipDialog", true);
        AlertDialog create = bQz_(i).create();
        this.t = create;
        kte.bQO_(create);
        this.t.setCanceledOnTouchOutside(false);
        this.t.setCancelable(false);
        ksy.b("WebViewActivity", "show showPermissionTipDialog", true);
        if (isFinishing()) {
            return;
        }
        kte.bQO_(this.t);
        this.t.show();
    }

    private AlertDialog.Builder bQz_(final int i) {
        ksy.b("WebViewActivity", "createPermissionDialog", true);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, kte.a(this));
        builder.setTitle(getResources().getString(R.string._2130851244_res_0x7f0235ac));
        builder.setNeutralButton(getResources().getString(R.string._2130851243_res_0x7f0235ab), new DialogInterface.OnClickListener() { // from class: com.huawei.hwidauth.ui.WebViewActivity.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                int i3 = i;
                if (i3 == 0) {
                    ksy.b("WebViewActivity", "startCamera", true);
                    WebViewActivity.this.g.a(1006);
                } else if (i3 == 1) {
                    ksy.b("WebViewActivity", "startGallery", true);
                    WebViewActivity.this.g.d(1007);
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i2);
            }
        });
        if (i == 0) {
            ksy.b("WebViewActivity", "show camera text", true);
            builder.setMessage(getResources().getString(R.string._2130851242_res_0x7f0235aa));
        } else if (i == 1) {
            builder.setMessage(getResources().getString(R.string._2130851247_res_0x7f0235af));
            ksy.b("WebViewActivity", "show gallery text", true);
        }
        return builder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.m == null) {
            return;
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.setFlags(3);
        String str = getPackageName() + ".hwidauth.fileProvider";
        ksy.b("WebViewActivity", "start Camera authority = " + str, false);
        Uri uriForFile = FileProvider.getUriForFile(this, str, new File(this.m.getPath()));
        ksy.b("WebViewActivity", "startCamera tmpCropUri = " + uriForFile, false);
        intent.putExtra("output", uriForFile);
        String systemAppPackage = PresetUtil.getSystemAppPackage(this, intent);
        if (!TextUtils.isEmpty(systemAppPackage)) {
            ksy.b("WebViewActivity", "start Camare, packageName = " + systemAppPackage, true);
            intent.setPackage(systemAppPackage);
        }
        try {
            startActivityForResult(intent, 1003);
        } catch (Exception e2) {
            ksy.c("WebViewActivity", "startCamera :" + e2.getClass().getSimpleName(), true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        Intent intent = new Intent("android.intent.action.PICK", (Uri) null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, Constants.IMAGE_TYPE);
        String systemAppPackage = PresetUtil.getSystemAppPackage(this, intent);
        if (!TextUtils.isEmpty(systemAppPackage)) {
            ksy.b("WebViewActivity", "start Gallery, packageName = " + systemAppPackage, true);
            intent.setPackage(systemAppPackage);
        }
        try {
            startActivityForResult(intent, 1004);
        } catch (Exception e2) {
            ksy.c("WebViewActivity", "startGallery" + e2.getClass().getSimpleName(), true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void bQy_(Uri uri) {
        ksy.b("WebViewActivity", "uploadCardPic RESULTCODE", true);
        ValueCallback<?> valueCallback = this.o;
        if (valueCallback != null) {
            try {
                int i = this.l;
                if (i == 1001) {
                    valueCallback.onReceiveValue(uri);
                } else if (i == 1002) {
                    valueCallback.onReceiveValue(uri == null ? new Uri[0] : new Uri[]{uri});
                }
            } catch (Exception e2) {
                ksy.b("WebViewActivity", "Exception e" + e2.getClass().getSimpleName(), true);
            }
            this.o = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String k() {
        return "verify_password_new".equalsIgnoreCase(this.g.c()) ? "accountPickerH5.chkUserPassword_v3" : "accountPickerH5.chkUserPassword_v2";
    }

    private String j() {
        return "javascript:goBack()";
    }
}
