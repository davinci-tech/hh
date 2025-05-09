package com.huawei.openalliance.ad.views.web;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.nv;
import com.huawei.openalliance.ad.oj;
import com.huawei.openalliance.ad.utils.ck;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.dq;
import com.huawei.openalliance.ad.utils.x;
import com.huawei.openalliance.ad.views.interfaces.q;
import com.huawei.openalliance.ad.views.web.PureNetworkLoadStatusView;
import com.huawei.operation.utils.Constants;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class PureWebView extends RelativeLayout implements q, PureNetworkLoadStatusView.a, PureNetworkLoadStatusView.b {

    /* renamed from: a, reason: collision with root package name */
    private ProgressBar f8184a;
    private PureNetworkLoadStatusView b;
    private WebView c;
    private String d;
    private oj e;
    private com.huawei.openalliance.ad.views.web.c f;
    private Handler g;
    private long h;
    private boolean i;
    private int j;
    private a k;
    private Runnable l;

    public interface a {
        void a(int i);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.w
    public void a(String str, String str2, String str3) {
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        this.f.a(webViewClient);
    }

    public void setProcessBarNeedHide(boolean z) {
        this.i = z;
        ProgressBar progressBar = this.f8184a;
        if (progressBar != null) {
            progressBar.setVisibility(z ? 8 : 0);
        }
    }

    public void setOnLoadFinishedListener(a aVar) {
        this.k = aVar;
    }

    public WebView getWebView() {
        return this.c;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.w
    public String getCurrentPageUrl() {
        return this.d;
    }

    public void e() {
        WebView webView = getWebView();
        if (webView == null) {
            return;
        }
        webView.setBackgroundColor(0);
        dq.a(this.c);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.q
    public void d() {
        WebView webView = this.c;
        if (webView != null) {
            webView.destroy();
        }
        this.c = null;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.w
    public void c() {
        ho.a("PureWebView", "showPageFinishPage");
        PureNetworkLoadStatusView pureNetworkLoadStatusView = this.b;
        if (pureNetworkLoadStatusView == null) {
            return;
        }
        int currentState = pureNetworkLoadStatusView.getCurrentState();
        ho.a("PureWebView", "state:%s", Integer.valueOf(currentState));
        if (this.b.getCurrentState() == 1 && x.h(getContext())) {
            this.b.setState(0);
            WebView webView = this.c;
            if (webView != null) {
                webView.setVisibility(0);
                this.c.requestFocus();
            }
        }
        a aVar = this.k;
        if (aVar != null) {
            aVar.a(currentState);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.w
    public void b(String str) {
        this.d = str;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.w
    public void b() {
        WebView webView = this.c;
        if (webView != null) {
            webView.loadUrl(Constants.ABOUT_BLANK);
            WebViewInstrumentation.loadUrl(webView, Constants.ABOUT_BLANK);
        }
        ProgressBar progressBar = this.f8184a;
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
    }

    public void a(final String str, final long j) {
        this.h = j;
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.web.PureWebView.2
            @Override // java.lang.Runnable
            public void run() {
                PureWebView.this.d = str;
                PureWebView.this.e();
                PureWebView.this.b(str, j);
            }
        });
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.q
    public void a(String str) {
        a(str, 0L);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.q
    public void a(Object obj, String str) {
        this.e.a(obj, str, getWebView());
    }

    @Override // com.huawei.openalliance.ad.views.web.PureNetworkLoadStatusView.a
    public void a(PureNetworkLoadStatusView pureNetworkLoadStatusView) {
        WebView webView;
        View rootView;
        if (pureNetworkLoadStatusView == null || (webView = this.c) == null || (rootView = webView.getRootView()) == null || rootView.getParent() != null) {
            return;
        }
        pureNetworkLoadStatusView.addView(rootView);
    }

    @Override // com.huawei.openalliance.ad.views.web.PureNetworkLoadStatusView.b
    public void a(final View view) {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.web.PureWebView.1
            @Override // java.lang.Runnable
            public void run() {
                if (view.getId() == R.id.privacy_set_network) {
                    Intent intent = new Intent("android.settings.WIFI_SETTINGS");
                    intent.setFlags(268435456);
                    dd.a(PureWebView.this.getContext(), intent);
                } else if (!x.h(PureWebView.this.getContext())) {
                    if (PureWebView.this.b != null) {
                        PureWebView.this.b.setState(-2);
                    }
                } else {
                    PureWebView pureWebView = PureWebView.this;
                    pureWebView.b(pureWebView.getCurrentPageUrl(), PureWebView.this.h);
                }
            }
        });
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.w
    public void a() {
        PureNetworkLoadStatusView pureNetworkLoadStatusView;
        int i;
        if (this.b == null) {
            return;
        }
        if (x.h(getContext())) {
            pureNetworkLoadStatusView = this.b;
            i = -1;
        } else {
            pureNetworkLoadStatusView = this.b;
            i = -2;
        }
        pureNetworkLoadStatusView.setState(i);
    }

    private void c(Context context) {
        ho.b("PureWebView", "safeInitPureWebView");
        try {
            if (!context.isDeviceProtectedStorage()) {
                ho.b("PureWebView", "context is not in DeviceProtectedStorage.");
                inflate(context, R.layout.pure_web_layout, this);
                this.c = (WebView) findViewById(R.id.content_webview);
                return;
            }
            inflate(context, R.layout.pure_web_layout_without_webview, this);
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.replace_layout);
            Context context2 = (Context) ck.a(context, "createCredentialProtectedStorageContext", (Class<?>[]) null, (Object[]) null);
            WebView webView = new WebView(context2);
            this.c = webView;
            webView.setVerticalScrollBarEnabled(true);
            this.c.setClipToPadding(true);
            if (Build.VERSION.SDK_INT >= 29) {
                this.c.setVerticalScrollbarThumbDrawable(context2.getDrawable(R.drawable._2131428582_res_0x7f0b04e6));
            }
            this.c.setId(R.id.content_webview);
            linearLayout.addView(this.c, new LinearLayout.LayoutParams(-1, -1));
        } catch (Throwable th) {
            ho.c("PureWebView", "safe init pure web view err: %s.", th.getClass().getSimpleName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, long j) {
        ho.a("PureWebView", "startLoad");
        this.j = 0;
        PureNetworkLoadStatusView pureNetworkLoadStatusView = this.b;
        if (pureNetworkLoadStatusView != null) {
            pureNetworkLoadStatusView.setState(1);
        }
        WebView webView = this.c;
        if (webView != null) {
            webView.setVisibility(4);
        }
        if (j > 0) {
            a(j);
        }
        this.e.a(str, getWebView());
    }

    private void b(Context context) {
        ho.b("PureWebView", "initPureWebView.");
        try {
            ho.b("PureWebView", "api version is %s.", Integer.valueOf(Build.VERSION.SDK_INT));
            c(context);
        } catch (Throwable th) {
            ho.c("PureWebView", "init pure web view err: %s.", th.getClass().getSimpleName());
        }
    }

    private void a(Context context) {
        b(context);
        this.f8184a = (ProgressBar) findViewById(R.id.web_progress);
        this.b = (PureNetworkLoadStatusView) findViewById(R.id.status_view);
        this.l = new c(this);
        PureNetworkLoadStatusView pureNetworkLoadStatusView = this.b;
        if (pureNetworkLoadStatusView != null) {
            pureNetworkLoadStatusView.setState(1);
            this.b.setOnEmptyClickListener(this);
            this.b.setClickable(true);
            this.b.setOnConfigurationChangedListener(this);
        }
        this.e = new nv(context, this);
        this.c.setWebChromeClient(new b());
        WebView webView = this.c;
        com.huawei.openalliance.ad.views.web.c cVar = new com.huawei.openalliance.ad.views.web.c(this);
        this.f = cVar;
        if (webView instanceof WebView) {
            APMSH5LoadInstrument.setWebViewClient(webView, cVar);
        } else {
            webView.setWebViewClient(cVar);
        }
        this.f.a(this.e);
    }

    private void a(long j) {
        if (this.g == null) {
            this.g = new Handler(Looper.getMainLooper());
        }
        this.g.removeCallbacks(this.l);
        this.g.postDelayed(this.l, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        ProgressBar progressBar;
        ho.a("PureWebView", "handleProgressChanged:" + i);
        this.j = i;
        if (this.i || (progressBar = this.f8184a) == null) {
            return;
        }
        if (i == 100) {
            progressBar.setVisibility(8);
            return;
        }
        if (progressBar.getVisibility() == 8) {
            this.f8184a.setVisibility(0);
        }
        this.f8184a.setProgress(i);
    }

    /* loaded from: classes9.dex */
    class b extends WebChromeClient {
        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            PureWebView.this.a(i);
            super.onProgressChanged(webView, i);
        }

        private b() {
        }
    }

    public PureWebView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.i = false;
        a(context);
    }

    /* loaded from: classes9.dex */
    static class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<PureWebView> f8188a;

        @Override // java.lang.Runnable
        public void run() {
            PureWebView pureWebView = this.f8188a.get();
            if (pureWebView != null && pureWebView.j < 100) {
                pureWebView.a();
                if (pureWebView.c != null) {
                    pureWebView.c.stopLoading();
                }
            }
        }

        public c(PureWebView pureWebView) {
            this.f8188a = new WeakReference<>(pureWebView);
        }
    }

    public PureWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = false;
    }

    public PureWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = false;
        a(context);
    }

    public PureWebView(Context context) {
        super(context);
        this.i = false;
        a(context);
    }
}
