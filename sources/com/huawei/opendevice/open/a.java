package com.huawei.opendevice.open;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.openalliance.ad.analysis.h;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.Cdo;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bj;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.dq;
import com.huawei.openalliance.ad.utils.x;
import com.huawei.openalliance.ad.views.interfaces.w;
import com.huawei.openalliance.ad.views.web.NetworkLoadStatusView;
import com.huawei.openalliance.ad.views.web.e;
import defpackage.lso;
import defpackage.lsr;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public abstract class a extends com.huawei.openalliance.ad.activity.f implements w, NetworkLoadStatusView.a, com.huawei.opendevice.open.c, f {

    /* renamed from: a, reason: collision with root package name */
    protected WebView f8214a;
    private NetworkLoadStatusView c;
    private View d;
    private ProgressBar e;
    private String f;
    private WebChromeClient g = new c();
    private boolean h = false;
    protected lsr b = new lsr();

    private void j() {
    }

    protected void a(com.huawei.opendevice.open.c cVar) {
    }

    protected String d() {
        return null;
    }

    protected int e() {
        return 0;
    }

    protected boolean f() {
        return true;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.w
    public Context getContext() {
        return this;
    }

    protected int h() {
        return 0;
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.b != null) {
            if (ho.a()) {
                ho.a("BaseWebActivity", "onResume, record privacy open time.");
            }
            this.b.b(ao.c());
        }
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.b != null) {
            if (ho.a()) {
                ho.a("BaseWebActivity", "onPause, record privacy close time.");
            }
            this.b.e(ao.c());
            this.b.e(d());
        }
        new h(getApplicationContext()).a(this.b);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        StringBuilder sb;
        try {
            if (menuItem.getItemId() == 16908332) {
                super.onBackPressed();
                ViewClickInstrumentation.clickOnMenuItem(this, menuItem);
                return true;
            }
            boolean onOptionsItemSelected = super.onOptionsItemSelected(menuItem);
            ViewClickInstrumentation.clickOnMenuItem(this, menuItem);
            return onOptionsItemSelected;
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onOptionsItemSelected ");
            sb.append(e.getClass().getSimpleName());
            ho.c("BaseWebActivity", sb.toString());
            ViewClickInstrumentation.clickOnMenuItem(this, menuItem);
            return false;
        } catch (Throwable th) {
            e = th;
            sb = new StringBuilder("onOptionsItemSelected ex: ");
            sb.append(e.getClass().getSimpleName());
            ho.c("BaseWebActivity", sb.toString());
            ViewClickInstrumentation.clickOnMenuItem(this, menuItem);
            return false;
        }
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        ho.b("BaseWebActivity", "onKeyDown");
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        lso.c((f) null);
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        StringBuilder sb;
        bj.a(this);
        dd.h(this);
        super.onCreate(bundle);
        this.h = bz.b(this);
        try {
            a(this, 1);
            setContentView(e());
            i();
            j();
            dd.a(this.d, this);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("onCreate ");
            sb.append(e.getClass().getSimpleName());
            ho.c("BaseWebActivity", sb.toString());
        } catch (Throwable th) {
            e = th;
            sb = new StringBuilder("onCreate ex: ");
            sb.append(e.getClass().getSimpleName());
            ho.c("BaseWebActivity", sb.toString());
        }
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i = configuration.uiMode & 48;
        ho.b("BaseWebActivity", "currentNightMode=" + i);
        a(32 == i ? 2 : 0);
    }

    @Override // com.huawei.openalliance.ad.views.web.NetworkLoadStatusView.a
    public void onClick(final View view) {
        dj.a(new Runnable() { // from class: com.huawei.opendevice.open.a.2
            @Override // java.lang.Runnable
            public void run() {
                if (view.getId() == R.id.privacy_set_network) {
                    Intent intent = new Intent("android.settings.WIFI_SETTINGS");
                    intent.setFlags(268435456);
                    dd.a(a.this, intent);
                } else {
                    if (!x.h(a.this) || a.this.f8214a == null) {
                        return;
                    }
                    WebView webView = a.this.f8214a;
                    String str = a.this.f;
                    webView.loadUrl(str);
                    WebViewInstrumentation.loadUrl(webView, str);
                }
            }
        });
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.opendevice.open.c
    public void g() {
        ho.d("BaseWebActivity", "onGrsFailed");
        dj.a(new Runnable() { // from class: com.huawei.opendevice.open.a.3
            @Override // java.lang.Runnable
            public void run() {
                a.this.a();
            }
        });
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.w
    public void c() {
        NetworkLoadStatusView networkLoadStatusView = this.c;
        if (networkLoadStatusView == null) {
            return;
        }
        if (networkLoadStatusView.getCurrentState() == 1 && x.h(this)) {
            this.c.setState(0);
        }
        this.c.setState(1);
    }

    @Override // com.huawei.opendevice.open.c
    public void a(final String str) {
        ho.b("BaseWebActivity", "onGrsSuccess");
        this.f = str;
        dj.a(new Runnable() { // from class: com.huawei.opendevice.open.a.5
            @Override // java.lang.Runnable
            public void run() {
                if (a.this.f8214a != null) {
                    WebView webView = a.this.f8214a;
                    String str2 = str;
                    webView.loadUrl(str2);
                    WebViewInstrumentation.loadUrl(webView, str2);
                }
            }
        });
    }

    @Override // com.huawei.opendevice.open.f
    public void a(lsr lsrVar) {
        ho.b("BaseWebActivity", "onPrivacyInfoUpdate.");
        this.b.d(lsrVar);
    }

    protected void a(WebView webView) {
        dq.a(webView);
        onConfigurationChanged(getResources().getConfiguration());
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.w
    public void a() {
        NetworkLoadStatusView networkLoadStatusView;
        int i;
        if (this.c == null) {
            return;
        }
        if (x.h(this)) {
            networkLoadStatusView = this.c;
            i = -1;
        } else {
            networkLoadStatusView = this.c;
            i = -2;
        }
        networkLoadStatusView.setState(i);
    }

    /* renamed from: com.huawei.opendevice.open.a$a, reason: collision with other inner class name */
    public static class C0223a {
        private Context c;

        @JavascriptInterface
        public boolean showMore() {
            return bz.g(this.c) && x.c();
        }

        @JavascriptInterface
        public String queryThemeColor() {
            String s = dd.s(this.c);
            return TextUtils.isEmpty(s) ? "#FF007DFF" : s;
        }

        @JavascriptInterface
        public int queryApiLevel() {
            return Build.VERSION.SDK_INT;
        }

        @JavascriptInterface
        public boolean isEMuiVersion10() {
            return x.c();
        }

        @JavascriptInterface
        public boolean isDarkMode() {
            return dd.r(this.c);
        }

        @JavascriptInterface
        public String getPkgName() {
            return this.c.getPackageName();
        }

        public C0223a(Context context) {
            this.c = context;
        }
    }

    private void i() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null && f()) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            if (h() != 0) {
                if (this.h) {
                    actionBar.setTitle(h());
                } else {
                    View inflate = getLayoutInflater().inflate(R.layout.action_bar_title_layout, (ViewGroup) null);
                    actionBar.setDisplayShowTitleEnabled(false);
                    actionBar.setDisplayShowCustomEnabled(true);
                    actionBar.setCustomView(inflate);
                    a(inflate);
                    ((TextView) findViewById(R.id.custom_action_bar_title)).setText(h());
                }
            }
        }
        this.d = findViewById(R.id.content_statement);
        this.f8214a = (WebView) findViewById(R.id.content_webview);
        this.e = (ProgressBar) findViewById(R.id.web_progress);
        b(this.f8214a);
        a(this.f8214a);
        e eVar = new e(this);
        eVar.a(this.e);
        WebView webView = this.f8214a;
        if (webView != null) {
            webView.setWebChromeClient(this.g);
            WebView webView2 = this.f8214a;
            if (webView2 instanceof WebView) {
                APMSH5LoadInstrument.setWebViewClient(webView2, eVar);
            } else {
                webView2.setWebViewClient(eVar);
            }
            this.f8214a.addJavascriptInterface(new C0223a(getContext()), Constants.HW_PPS_PRIVACY_JS_NAME);
        }
        a((com.huawei.opendevice.open.c) this);
        lso.c(new d(this));
        NetworkLoadStatusView networkLoadStatusView = (NetworkLoadStatusView) findViewById(R.id.status_view);
        this.c = networkLoadStatusView;
        if (networkLoadStatusView != null) {
            networkLoadStatusView.setState(1);
            this.c.setOnEmptyClickListener(this);
            this.c.setClickable(true);
        }
    }

    private void b(WebView webView) {
        if (webView == null) {
            return;
        }
        webView.setBackgroundColor(0);
    }

    class c extends WebChromeClient {
        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            Cdo.a(a.this.e, i);
            super.onProgressChanged(webView, i);
        }

        private c() {
        }
    }

    private void a(final View view) {
        try {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
            final Toolbar toolbar = (Toolbar) view.getParent();
            if (toolbar != null) {
                toolbar.setLayoutParams(layoutParams);
            }
            view.post(new Runnable() { // from class: com.huawei.opendevice.open.a.4
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        TypedValue typedValue = new TypedValue();
                        int max = Math.max(view.getHeight(), a.this.getTheme().resolveAttribute(android.R.attr.actionBarSize, typedValue, true) ? TypedValue.complexToDimensionPixelSize(typedValue.data, a.this.getResources().getDisplayMetrics()) : 0);
                        if (max > 0) {
                            toolbar.setMinimumHeight(max);
                        }
                    } catch (Throwable unused) {
                        ho.c("BaseWebActivity", "set toolBar min height error.");
                    }
                }
            });
        } catch (Throwable unused) {
            ho.c("BaseWebActivity", "setCustomToolBar error.");
        }
    }

    private void a(Activity activity, int i) {
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        try {
            attributes.getClass().getDeclaredField(ParamConstants.Param.LAYOUT_IN_DISPLAY_CUTOUT_MODE).setInt(attributes, i);
            activity.getWindow().setAttributes(attributes);
        } catch (Throwable unused) {
            ho.c("BaseWebActivity", "setLayoutMode error");
        }
    }

    static class d implements f {
        private WeakReference<f> c;

        @Override // com.huawei.opendevice.open.f
        public void a(lsr lsrVar) {
            f fVar = this.c.get();
            if (fVar != null) {
                fVar.a(lsrVar);
            }
        }

        public d(f fVar) {
            this.c = new WeakReference<>(fVar);
        }
    }

    private void a(int i) {
        WebView webView;
        WebSettings settings;
        if (Build.VERSION.SDK_INT < 29 || (webView = this.f8214a) == null || (settings = webView.getSettings()) == null) {
            return;
        }
        settings.setForceDark(i);
    }
}
