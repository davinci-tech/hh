package com.huawei.opendevice.open;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toolbar;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.openalliance.ad.activity.SafeIntent;
import com.huawei.openalliance.ad.activity.e;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.dl;
import com.huawei.openalliance.ad.utils.w;
import com.huawei.openalliance.ad.utils.x;
import com.huawei.openalliance.ad.views.interfaces.q;
import com.huawei.openalliance.ad.views.web.PureWebView;

/* loaded from: classes5.dex */
public class TransparencyStatementActivity extends e implements PureWebView.a {

    /* renamed from: a, reason: collision with root package name */
    private TextView f8211a;
    private PureWebView b;
    private String c;
    private boolean e = false;
    private int d = 1;

    protected int b() {
        return R.string._2130851031_res_0x7f0234d7;
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onResume() {
        super.onResume();
        int i = this.d;
        if (i == -1 || i == -2) {
            f();
        }
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        try {
            if (menuItem.getItemId() == 16908332) {
                super.onBackPressed();
                ViewClickInstrumentation.clickOnMenuItem(this, menuItem);
                return true;
            }
            boolean onOptionsItemSelected = super.onOptionsItemSelected(menuItem);
            ViewClickInstrumentation.clickOnMenuItem(this, menuItem);
            return onOptionsItemSelected;
        } catch (Throwable th) {
            ho.c("Transparency", "onOptionsItemSelected ex: " + th.getClass().getSimpleName());
            ViewClickInstrumentation.clickOnMenuItem(this, menuItem);
            return false;
        }
    }

    @Override // com.huawei.openalliance.ad.activity.e, com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        boolean j = x.j(this);
        this.e = j;
        if (j) {
            setTheme(R.style.HiAdThemeNoActionBarFullScreen);
        }
        super.onCreate(bundle);
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        if (configuration == null) {
            return;
        }
        super.onConfigurationChanged(configuration);
        if (Build.VERSION.SDK_INT < 29) {
            return;
        }
        if (this.e) {
            e(2);
            return;
        }
        int i = configuration.uiMode & 48;
        ho.b("Transparency", "currentNightMode=" + i);
        if (32 == i) {
            e(2);
        } else {
            e(0);
        }
    }

    @Override // com.huawei.openalliance.ad.views.web.PureWebView.a
    public void a(int i) {
        this.d = i;
        if (this.e && i == 0) {
            dj.a(new Runnable() { // from class: com.huawei.opendevice.open.TransparencyStatementActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    if (TransparencyStatementActivity.this.f8211a != null) {
                        TransparencyStatementActivity.this.f8211a.setVisibility(0);
                    }
                }
            });
        }
    }

    @Override // com.huawei.openalliance.ad.activity.e
    public void a() {
        ho.b("Transparency", "initLayout");
        try {
            e();
            setContentView(R.layout.transparency_activity_layout);
        } catch (Throwable unused) {
            ho.b("Transparency", "set content view error");
        }
        m();
        d();
        g();
        f();
    }

    private void m() {
        if (this.e || !j()) {
            return;
        }
        try {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(AMapEngineUtils.HALF_MAX_P20_WIDTH);
            window.setNavigationBarColor(getResources().getColor(R.color._2131297959_res_0x7f0906a7));
            window.setStatusBarColor(getResources().getColor(R.color._2131297959_res_0x7f0906a7));
        } catch (Throwable th) {
            ho.c("Transparency", "setStatusAndNavigationBarColor error. %s", th.getClass().getSimpleName());
        }
    }

    private String h() {
        String stringExtra = new SafeIntent(getIntent()).getStringExtra(MapKeyNames.DSA_URL);
        ho.b("Transparency", "statement url:%s", dl.a(stringExtra));
        return stringExtra;
    }

    private void f() {
        if (this.c == null) {
            this.c = h();
        }
        b(this.c);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void g() {
        PureWebView pureWebView = (PureWebView) findViewById(R.id.webview);
        this.b = pureWebView;
        if (pureWebView == 0) {
            ho.c("Transparency", "get webview error.");
            return;
        }
        c cVar = new c(this.b);
        if (pureWebView instanceof WebView) {
            APMSH5LoadInstrument.setWebViewClient((WebView) pureWebView, cVar);
        } else {
            pureWebView.setWebViewClient(cVar);
        }
        this.b.setOnLoadFinishedListener(this);
        this.b.setProcessBarNeedHide(this.e);
        this.b.a(new a(this), "HwPPSTransparency");
        Resources resources = getResources();
        if (resources == null) {
            ho.c("Transparency", "resources is null");
        } else {
            onConfigurationChanged(resources.getConfiguration());
        }
    }

    private boolean j() {
        return bz.b(this);
    }

    private void i() {
        ActionBar actionBar = getActionBar();
        if (actionBar == null) {
            return;
        }
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        if (j()) {
            caf_(actionBar);
        }
        actionBar.setTitle(b());
    }

    private void c() {
        TextView textView = (TextView) findViewById(R.id.web_appbar_tv);
        this.f8211a = textView;
        if (textView == null) {
            ho.c("Transparency", "get text view failed");
        } else {
            textView.setText(b());
            this.f8211a.setVisibility(8);
        }
    }

    private void d() {
        if (this.e) {
            c();
        } else {
            i();
        }
    }

    private void e() {
        Resources resources;
        int i;
        View decorView = getWindow().getDecorView();
        if (decorView == null) {
            return;
        }
        if (this.e || !j()) {
            resources = getResources();
            i = R.color._2131297258_res_0x7f0903ea;
        } else {
            resources = getResources();
            i = R.color._2131297959_res_0x7f0906a7;
        }
        decorView.setBackgroundColor(resources.getColor(i));
    }

    private void e(int i) {
        PureWebView pureWebView = this.b;
        if (pureWebView == null || pureWebView.getWebView() == null) {
            ho.b("Transparency", "webView is null");
            return;
        }
        WebSettings settings = this.b.getWebView().getSettings();
        if (settings != null) {
            settings.setForceDark(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final String str) {
        dj.a(new Runnable() { // from class: com.huawei.opendevice.open.TransparencyStatementActivity.1
            @Override // java.lang.Runnable
            public void run() {
                if (TransparencyStatementActivity.this.b == null) {
                    return;
                }
                TransparencyStatementActivity.this.b.a(str, PreConnectManager.CONNECT_INTERNAL);
            }
        });
    }

    public static class a {
        private final Context d;

        @JavascriptInterface
        public boolean isDarkMode() {
            return dd.r(this.d);
        }

        @JavascriptInterface
        public int getDeviceType() {
            return w.a(this.d).c();
        }

        public a(Context context) {
            this.d = context;
        }
    }

    class c extends com.huawei.openalliance.ad.views.web.e {
        @Override // com.huawei.openalliance.ad.views.web.e, android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (!TransparencyStatementActivity.this.e) {
                return super.shouldOverrideUrlLoading(webView, str);
            }
            TransparencyStatementActivity.this.b(str);
            return true;
        }

        @Override // com.huawei.openalliance.ad.views.web.e, android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            ho.a("Transparency", "WebResourceRequest url=%s", webResourceRequest.getUrl().toString());
            if (!TransparencyStatementActivity.this.e) {
                return super.shouldOverrideUrlLoading(webView, webResourceRequest);
            }
            TransparencyStatementActivity.this.b(webResourceRequest.getUrl().toString());
            return true;
        }

        public c(q qVar) {
            super(qVar);
        }
    }

    private void caf_(ActionBar actionBar) {
        View inflate = getLayoutInflater().inflate(R.layout.action_bar_title_layout, (ViewGroup) null);
        try {
            try {
                actionBar.setDisplayShowCustomEnabled(true);
                actionBar.setCustomView(inflate);
                Toolbar toolbar = (Toolbar) inflate.getParent();
                if (toolbar != null) {
                    toolbar.setBackgroundColor(getResources().getColor(R.color._2131297959_res_0x7f0906a7));
                }
            } finally {
                inflate.setVisibility(8);
                actionBar.setDisplayShowCustomEnabled(false);
            }
        } catch (Throwable unused) {
            ho.c("Transparency", "setCustomToolBar error.");
        }
    }
}
