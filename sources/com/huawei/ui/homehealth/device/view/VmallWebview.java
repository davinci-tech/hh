package com.huawei.ui.homehealth.device.view;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.HAWebChromeClient;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.js.JsInteraction;
import com.huawei.operation.view.CustomWebView;
import com.huawei.operation.vmall.VmallLoginWebview;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.homehealth.device.DeviceFragment;
import com.huawei.ui.homehealth.device.view.VmallWebview;
import com.huawei.ui.homehealth.vmall.VmallOperation;
import defpackage.nsz;
import defpackage.nte;
import defpackage.owx;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes6.dex */
public class VmallWebview implements IWebViewLifeCycle {

    /* renamed from: a, reason: collision with root package name */
    private boolean f9415a;
    private WeakReference<Context> b;
    private View c;
    private WeakReference<DeviceFragment> e;
    private boolean f;
    private boolean h;
    private boolean j;
    private boolean k;
    private NetworkStateReceiver m;
    private View n;
    private long o;
    private LinearLayout q;
    private HealthSubHeader s;
    private View t;
    private int u;
    private NestedWebView v;
    private VmallLoginWebview x;
    private boolean g = true;
    private boolean i = true;
    private owx r = new owx();
    private boolean l = false;
    private boolean d = false;
    private d p = new d();

    public VmallWebview(Context context, View view, DeviceFragment deviceFragment) {
        this.b = new WeakReference<>(context);
        this.c = view;
        this.e = new WeakReference<>(deviceFragment);
        this.t = this.c.findViewById(R.id.device_vmall_card_layout);
        g();
    }

    private void p() {
        LogUtil.a("VmallWebview", "startLoadWebView");
        if (this.k) {
            return;
        }
        LogUtil.a("VmallWebview", "startLoadWebView start to load");
        f();
    }

    public void a() {
        NestedWebView nestedWebView = this.v;
        if (nestedWebView != null) {
            nestedWebView.d();
        }
    }

    private void cZZ_(WebView webView) {
        webView.addJavascriptInterface(new JsInteraction(webView.getContext()), "JsInteraction");
    }

    private void j() {
        LogUtil.a("VmallWebview", "initWebViewClient");
        r();
        cZZ_(this.v);
        NestedWebView nestedWebView = this.v;
        WebViewClient webViewClient = new WebViewClient() { // from class: com.huawei.ui.homehealth.device.view.VmallWebview.2
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                LogUtil.a("VmallWebview", "webViewClient shouldOverrideUrlLoading url");
                CustomWebView.setLoginStatus(VmallWebview.this.x != null && VmallWebview.this.x.isVmallLogin());
                VmallWebview.this.a(str);
                VmallWebview.this.c(str);
                return true;
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                LogUtil.a("VmallWebview", "webViewClient shouldOverrideUrlLoading request");
                CustomWebView.setLoginStatus(VmallWebview.this.x != null && VmallWebview.this.x.isVmallLogin());
                String uri = webResourceRequest.getUrl().toString();
                VmallWebview.this.a(uri);
                VmallWebview.this.c(uri);
                return true;
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                LogUtil.b("VmallWebview", "webViewClient onReceivedError ", "getErrorCode = ", Integer.valueOf(webResourceError.getErrorCode()), " getDescription", webResourceError.getDescription());
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                VmallWebview.this.f = true;
                VmallWebview.this.e(8, 0);
                if (SystemClock.elapsedRealtime() - VmallWebview.this.o > 6000) {
                    VmallWebview.this.o();
                }
                VmallWebview.this.p.removeMessages(1);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                LogUtil.b("VmallWebview", "webViewClient onReceivedSslError ", "error = ", sslError.toString());
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
                LogUtil.b("VmallWebview", "webViewClient onReceivedHttpError ", "getStatusCode = ", Integer.valueOf(webResourceResponse.getStatusCode()), " getReasonPhrase", webResourceResponse.getReasonPhrase());
                super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                LogUtil.a("VmallWebview", "webViewClient onPageFinished");
                VmallWebview.this.s();
                if (!VmallWebview.this.f) {
                    VmallWebview.this.e(0, 8);
                }
                VmallWebview.this.o = SystemClock.elapsedRealtime();
                VmallWebview.this.k();
            }
        };
        if (nestedWebView instanceof WebView) {
            APMSH5LoadInstrument.setWebViewClient(nestedWebView, webViewClient);
        } else {
            nestedWebView.setWebViewClient(webViewClient);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        this.k = true;
        WeakReference<DeviceFragment> weakReference = this.e;
        if (weakReference == null) {
            LogUtil.h("VmallWebview", "onPageFinishedInner weakRef is null");
            return;
        }
        if (weakReference.get() == null) {
            LogUtil.h("VmallWebview", "onPageFinishedInner deviceFragment is null");
        } else if (!this.j) {
            LogUtil.a("VmallWebview", "webview loading not in deviceFragment");
        } else {
            this.h = false;
            u();
        }
    }

    private void i() {
        this.q = (LinearLayout) this.t.findViewById(R.id.vmall_no_network_layout);
        LinearLayout linearLayout = (LinearLayout) this.t.findViewById(R.id.no_network_text_icon_layout);
        if (linearLayout == null) {
            nsz.cLX_("initNoNetworkLayout noNetworkIconLayout == null", this.t, R.id.no_network_text_icon_layout);
            return;
        }
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: oha
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VmallWebview.this.daa_(view);
            }
        });
        HealthButton healthButton = (HealthButton) this.t.findViewById(R.id.vmall_net_setting_btn);
        if (healthButton == null) {
            nsz.cLX_("initNoNetworkLayout vmallNoNetworkButton == null", this.t, R.id.vmall_net_setting_btn);
        } else {
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: ohb
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VmallWebview.this.dab_(view);
                }
            });
            m();
        }
    }

    public /* synthetic */ void daa_(View view) {
        if (this.v != null && !TextUtils.isEmpty(this.r.d())) {
            q();
        }
        if (this.f9415a && this.e.get() != null) {
            LogUtil.a("VmallWebview", "update vmall marketing data");
            this.e.get().b();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dab_(View view) {
        WeakReference<Context> weakReference = this.b;
        if (weakReference == null || weakReference.get() == null) {
            LogUtil.a("VmallWebview", "initWebView: get context fail");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            CommonUtil.q(this.b.get());
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void g() {
        LogUtil.a("VmallWebview", "initWebView");
        if (this.t == null) {
            LogUtil.b("VmallWebview", "inflate vmall webview fail");
            return;
        }
        i();
        this.s = (HealthSubHeader) this.t.findViewById(R.id.device_sub_header);
        this.v = (NestedWebView) this.t.findViewById(R.id.vmall_webview);
        this.n = this.t.findViewById(R.id.loading_progress);
        NestedWebView nestedWebView = this.v;
        if (nestedWebView == null) {
            LogUtil.b("VmallWebview", "mVmallWebview found with null");
            return;
        }
        nestedWebView.setBackgroundColor(0);
        this.v.getBackground().setAlpha(0);
        this.v.setDrawingCacheEnabled(false);
        j();
    }

    private void f() {
        LogUtil.a("VmallWebview", "loadWebview");
        l();
        if (TextUtils.isEmpty(this.r.d()) || this.v == null) {
            return;
        }
        LogUtil.a("VmallWebview", "load vmall url");
        q();
    }

    private void r() {
        LogUtil.a("VmallWebview", "setWebviewContent");
        this.v.setWebChromeClient(new b());
        this.v.setFocusableInTouchMode(false);
        this.v.requestFocus();
        WebSettings settings = this.v.getSettings();
        nte.cMB_(this.v);
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setUserAgentString(settings.getUserAgentString() + "; HuaweiHealth");
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setUseWideViewPort(false);
        settings.setTextZoom(100);
        settings.setSavePassword(false);
        settings.setGeolocationEnabled(true);
        settings.setAllowContentAccess(false);
        settings.setLoadWithOverviewMode(true);
        CookieManager.getInstance().setAcceptThirdPartyCookies(this.v, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        LogUtil.a("VmallWebview", "retryLoadOnce");
        if (this.i) {
            q();
            this.i = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str) {
        LogUtil.a("VmallWebview", "vmallUrlLoading, url: " + str);
        if (TextUtils.equals(str, this.r.d())) {
            LogUtil.a("VmallWebview", "url equals mVmallUrl, reload vmall home page.");
            o();
            return;
        }
        LogUtil.a("VmallWebview", "load url with webview activity");
        if (this.l) {
            this.l = false;
            LogUtil.h("VmallWebview", "need not to refresh webview");
        } else {
            LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: ohc
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    VmallWebview.this.a(str, i, obj);
                }
            }, AnalyticsValue.HEALTH_CONFIGURE_PAGE_FRAGMENT_2020029.value());
        }
    }

    public /* synthetic */ void a(String str, int i, Object obj) {
        LogUtil.a("VmallWebview", "browsingToLogin response");
        if (i != 0) {
            LogUtil.h("VmallWebview", "getViewClickListener errorCode = ", Integer.valueOf(i));
        } else {
            e(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, int i2) {
        NestedWebView nestedWebView;
        Object[] objArr = new Object[4];
        objArr[0] = "setVmallVisible vmallVisible:";
        objArr[1] = Boolean.valueOf(i == 0);
        objArr[2] = " noNetworkVisible:";
        objArr[3] = Boolean.valueOf(i2 == 0);
        LogUtil.a("VmallWebview", objArr);
        this.n.setVisibility(8);
        if (this.s != null && (nestedWebView = this.v) != null) {
            nestedWebView.setVisibility(i);
            this.s.setVisibility(i);
        }
        if (this.q != null) {
            if (CommonUtil.bf() && TextUtils.isEmpty(this.r.d())) {
                this.q.setVisibility(8);
                LogUtil.a("VmallWebview", "setVmallLoadVisible isHonor, VmallUrl isEmpty, setVisibility GONE");
            } else {
                this.q.setVisibility(i2);
            }
        } else if (i2 == 0) {
            LogUtil.h("VmallWebview", "mVmallNoNetworkLayout is null");
        }
        if (i == 8 && i2 == 0 && this.i) {
            LogUtil.a("VmallWebview", "setVmallVisible reload");
            n();
            this.i = false;
        }
    }

    private void l() {
        if (this.r.b() == null || this.s == null) {
            return;
        }
        LogUtil.a("VmallWebview", "set vmall head title");
        this.s.setHeadTitleText(this.r.b());
        this.s.setMoreTextVisibility(8);
        this.s.setSubHeaderBackgroundColor(ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296971_res_0x7f0902cb));
        this.s.setRightArrayVisibility(8);
    }

    public void b() {
        a();
        q();
    }

    private void q() {
        this.i = true;
        LogUtil.a("VmallWebview", "mVmallUrl: " + this.r.d());
        if (this.v == null || "".equals(this.r.d())) {
            LogUtil.a("VmallWebview", "mVmallWebview is null");
            return;
        }
        l();
        this.f = false;
        NestedWebView nestedWebView = this.v;
        String d2 = this.r.d();
        nestedWebView.loadUrl(d2);
        WebViewInstrumentation.loadUrl(nestedWebView, d2);
        VmallLoginWebview vmallLoginWebview = this.x;
        if (vmallLoginWebview == null || !vmallLoginWebview.isVmallLogin()) {
            e();
        }
        HandlerExecutor.d(new Runnable() { // from class: ogt
            @Override // java.lang.Runnable
            public final void run() {
                VmallWebview.this.d();
            }
        }, 7000L);
    }

    public /* synthetic */ void d() {
        LogUtil.a("VmallWebview", "vmall load wait");
        if (this.k) {
            return;
        }
        LogUtil.a("VmallWebview", "mIsVmallLoaded is false");
        e(8, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        ViewGroup.LayoutParams layoutParams = this.v.getLayoutParams();
        if (this.u > 0 && layoutParams.height != this.u) {
            LogUtil.a("VmallWebview", "changeVmallHeight layoutParams.height = ", Integer.valueOf(layoutParams.height));
        }
        layoutParams.height = this.u;
        this.v.setLayoutParams(layoutParams);
    }

    private void e(String str) {
        LogUtil.a("VmallWebview", "loadWebViewActivity, url: " + str);
        WeakReference<Context> weakReference = this.b;
        if (weakReference == null || weakReference.get() == null) {
            LogUtil.a("VmallWebview", "loadWebViewActivity: get context fail");
            return;
        }
        Intent intent = new Intent(this.b.get(), (Class<?>) WebViewActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("url", str);
        intent.putExtra("EXTRA_BI_ID", String.valueOf(0));
        intent.putExtra("EXTRA_BI_NAME", "");
        intent.putExtra("EXTRA_BI_SOURCE", "CONFIGURE_PAGE_CARD");
        intent.putExtra("EXTRA_BI_SHOWTIME", "SHOW_TIME_BI");
        try {
            this.b.get().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("VmallWebview", "ActivityNotFound", e.getMessage());
        }
    }

    public void c(int i) {
        LogUtil.a("VmallWebview", "changeVmallHeight");
        this.u = i;
    }

    public void c() {
        new VmallOperation().a(new VmallOperation.VmallResourceCallback() { // from class: ogz
            @Override // com.huawei.ui.homehealth.vmall.VmallOperation.VmallResourceCallback
            public final void obtainVmallResource(owx owxVar) {
                VmallWebview.this.a(owxVar);
            }
        });
    }

    public /* synthetic */ void a(owx owxVar) {
        if (this.d) {
            return;
        }
        if (owxVar == null) {
            ReleaseLogUtil.e("R_VmallWebview", "loadVmallContent vmallResource == null isHonor: ", Boolean.valueOf(CommonUtil.bf()));
            if (CommonUtil.bf()) {
                this.r = new owx();
            }
            h();
            return;
        }
        c(owxVar);
    }

    private void c(owx owxVar) {
        if (!owxVar.e()) {
            LogUtil.a("VmallWebview", "empty resource");
            return;
        }
        this.f9415a = false;
        if (owxVar.b(this.r)) {
            LogUtil.a("VmallWebview", "vmall resource has init");
            return;
        }
        this.r = owxVar;
        LogUtil.a("VmallWebview", "Url has get, start to load vmall");
        c(this.r.d());
        if (this.c == null) {
            LogUtil.h("VmallWebview", "this activity is destroy, finish vmall loading");
        } else {
            p();
        }
    }

    private void h() {
        if (this.r.e()) {
            return;
        }
        this.f9415a = true;
        this.r.e(true);
        LogUtil.a("VmallWebview", "dealEmptyMarketingData show noNetwork layout only");
        i();
        e(8, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        if (str.matches(".*vmall.com/.*/.*/index.html\\?pageId=.*")) {
            LogUtil.a("VmallWebview", "vmallUrl pageId is ", str.split("pageId=")[1].split("&cid")[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        LogUtil.a("VmallWebview", "onNetworkReceive, isNetwork: ", Boolean.valueOf(z), ", mIsLoadError: ", Boolean.valueOf(this.f));
        if (LoginInit.getInstance(BaseApplication.getContext()).isKidAccount() || (this.r.e() && !this.r.a())) {
            e(8, 8);
        } else if (z) {
            if (!this.g && this.v != null) {
                LogUtil.a("VmallWebview", "onNetworkReceive network is accessible, start to load vmall");
                WeakReference<DeviceFragment> weakReference = this.e;
                if (weakReference != null && weakReference.get() != null) {
                    DeviceFragment deviceFragment = this.e.get();
                    deviceFragment.d();
                    deviceFragment.e();
                }
                if (!TextUtils.isEmpty(this.r.d())) {
                    q();
                }
            }
        } else {
            LogUtil.a("VmallWebview", "onNetworkReceive onReceive: default");
        }
        this.g = z;
    }

    private void m() {
        LogUtil.a("VmallWebview", "registerNetworkChangeBroadcastReceiver");
        if (this.m != null) {
            LogUtil.h("VmallWebview", "mNetworkStateReceiver is not null");
            return;
        }
        NetworkStateReceiver networkStateReceiver = new NetworkStateReceiver();
        this.m = networkStateReceiver;
        networkStateReceiver.a(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        BaseApplication.getContext().registerReceiver(this.m, intentFilter);
    }

    private void t() {
        LogUtil.a("VmallWebview", "unRegisterNetworkChangeBroadcastReceiver");
        try {
            if (this.m != null) {
                BaseApplication.getContext().unregisterReceiver(this.m);
            }
        } catch (IllegalArgumentException unused) {
            LogUtil.b("VmallWebview", "unRegisterNetworkChangeBroadcastReceiver IllegalArgumentException");
        }
    }

    private void n() {
        LinearLayout linearLayout;
        LogUtil.a("VmallWebview", "reloadVmallWhenFailed");
        if (this.f9415a) {
            LogUtil.a("VmallWebview", "reloadVmallWhenFailed with EmptyResource");
            return;
        }
        if ((this.v == null || TextUtils.isEmpty(this.r.d()) || !this.f || this.v.getVisibility() != 0) && ((linearLayout = this.q) == null || linearLayout.getVisibility() != 0)) {
            return;
        }
        LogUtil.a("VmallWebview", "reload vmall");
        q();
        this.f = false;
    }

    public void e() {
        LogUtil.a("VmallWebview", "vmallPreLoad");
        VmallLoginWebview vmallLoginWebview = this.x;
        if (vmallLoginWebview == null || !vmallLoginWebview.isVmallLogin()) {
            LogUtil.a("VmallWebview", "current state is logout, start to prelogin");
            HandlerExecutor.d(new Runnable() { // from class: com.huawei.ui.homehealth.device.view.VmallWebview.5
                @Override // java.lang.Runnable
                public void run() {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    if (VmallWebview.this.b == null || VmallWebview.this.b.get() == null) {
                        LogUtil.a("VmallWebview", "vmallPreLoad: get context fail");
                        return;
                    }
                    try {
                        WebView webView = new WebView((Context) VmallWebview.this.b.get());
                        VmallWebview.this.x = new VmallLoginWebview(webView);
                        webView.setWebChromeClient(new b());
                        webView.getSettings().setJavaScriptEnabled(true);
                        nte.cMB_(webView);
                        WebViewClient webViewClient = new WebViewClient() { // from class: com.huawei.ui.homehealth.device.view.VmallWebview.5.3
                            @Override // android.webkit.WebViewClient
                            public void onPageFinished(WebView webView2, String str) {
                                super.onPageFinished(webView2, str);
                                if (VmallWebview.this.x != null) {
                                    VmallWebview.this.x.onPageFinishedForVmall(webView2, str);
                                }
                            }
                        };
                        if (webView instanceof WebView) {
                            WebView webView2 = webView;
                            APMSH5LoadInstrument.setWebViewClient(webView, webViewClient);
                        } else {
                            webView.setWebViewClient(webViewClient);
                        }
                        VmallWebview.this.x.preLogin(true);
                    } catch (Exception unused) {
                        LogUtil.a("VmallWebview", "MissingWebViewPackageException");
                    }
                    ReleaseLogUtil.e("VmallWebview", "vmall preload finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
                }
            }, 6000L);
        }
    }

    @Override // com.huawei.ui.homehealth.device.view.IWebViewLifeCycle
    public void onResume() {
        LogUtil.a("VmallWebview", "onResume");
        VmallLoginWebview vmallLoginWebview = this.x;
        if (vmallLoginWebview == null || !vmallLoginWebview.isVmallLogin()) {
            LogUtil.a("VmallWebview", "preLoad onResume");
            e();
        }
    }

    private void u() {
        if (!this.j || this.f) {
            LogUtil.h("VmallWebview", "updateResumeFinishedState is invisible or LoadError");
        } else {
            if (this.h || !this.k) {
                return;
            }
            this.p.removeMessages(1);
            this.p.sendEmptyMessageDelayed(1, 5000L);
        }
    }

    @Override // com.huawei.ui.homehealth.device.view.IWebViewLifeCycle
    public void onHiddenChanged(boolean z) {
        LogUtil.a("VmallWebview", "onHiddenChanged: ", Boolean.valueOf(z));
        NestedWebView nestedWebView = this.v;
        if (nestedWebView == null) {
            LogUtil.h("VmallWebview", "onHiddenChanged mVmallWebview is null.");
            return;
        }
        this.j = !z;
        if (z) {
            nestedWebView.d();
        }
        String url = this.v.getUrl();
        if (TextUtils.isEmpty(url)) {
            return;
        }
        LogUtil.c("VmallWebview", "VmallWebview cookie: ", CookieManager.getInstance().getCookie(url));
    }

    @Override // com.huawei.ui.homehealth.device.view.IWebViewLifeCycle
    public void onDestroy() {
        LogUtil.a("VmallWebview", "onDestroy");
        this.d = true;
        t();
        NestedWebView nestedWebView = this.v;
        if (nestedWebView != null) {
            nestedWebView.destroy();
        }
        VmallLoginWebview vmallLoginWebview = this.x;
        if (vmallLoginWebview != null) {
            vmallLoginWebview.clear();
        }
    }

    public static class NetworkStateReceiver extends BroadcastReceiver {
        private WeakReference<VmallWebview> c;

        public void a(VmallWebview vmallWebview) {
            this.c = new WeakReference<>(vmallWebview);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            VmallWebview vmallWebview;
            if (context == null) {
                LogUtil.h("VmallWebview", "context is null");
            } else {
                if (this.c.get() == null || (vmallWebview = this.c.get()) == null) {
                    return;
                }
                vmallWebview.d(NetworkUtil.j());
            }
        }
    }

    static class b extends WebChromeClient {
        private b() {
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            HAWebChromeClient.initH5Monitor(webView, i);
            super.onProgressChanged(webView, i);
        }
    }

    class d extends Handler {
        private d() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                LogUtil.a("VmallWebview_VmallHandler", "checkResume finished");
                VmallWebview.this.h = true;
            } else {
                LogUtil.h("VmallWebview_VmallHandler", "invalid type");
            }
        }
    }
}
