package com.huawei.openalliance.ad.views;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.agconnect.apms.instrument.webview.APMSH5LoadInstrument;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.co;
import com.huawei.openalliance.ad.constant.HiAdWidgets;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.listeners.IPPSWebEventCallback;
import com.huawei.openalliance.ad.nv;
import com.huawei.openalliance.ad.oj;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.az;
import com.huawei.openalliance.ad.utils.bv;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dq;
import com.huawei.openalliance.ad.views.d;
import com.huawei.operation.utils.Constants;
import com.huawei.uikit.phone.hwprogressbar.widget.HwProgressBar;

/* loaded from: classes5.dex */
public class PPSWebView extends RelativeLayout implements com.huawei.openalliance.ad.views.interfaces.w {
    private View.OnTouchListener A;
    private RelativeLayout.LayoutParams B;

    /* renamed from: a, reason: collision with root package name */
    private d.a f7985a;
    private WebView b;
    private d c;
    private View d;
    private oj e;
    private ActionBar f;
    private AdLandingPageData g;
    private w h;
    private View i;
    private ProgressBar j;
    private PPSAppDetailView k;
    private RelativeLayout.LayoutParams l;
    private TextView m;
    private int n;
    private boolean o;
    private boolean p;
    private boolean q;
    private int r;
    private int s;
    private int t;
    private String u;
    private boolean v;
    private co w;
    private boolean x;
    private boolean y;
    private View.OnKeyListener z;

    @Override // com.huawei.openalliance.ad.views.interfaces.w
    public void c() {
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        this.h.a(webViewClient);
    }

    public void setWebViewBackgroundColor(int i) {
        WebView webView = this.b;
        if (webView != null) {
            webView.setBackgroundColor(i);
        }
    }

    public void setWebChromeClient(WebChromeClient webChromeClient) {
        WebView webView = this.b;
        if (webView != null) {
            webView.setWebChromeClient(webChromeClient);
        }
    }

    public void setScrollListener(View.OnScrollChangeListener onScrollChangeListener) {
        WebView webView = this.b;
        if (webView != null) {
            webView.setOnScrollChangeListener(onScrollChangeListener);
        }
    }

    public void setRealOpenTime(long j) {
        this.e.d(j);
    }

    public void setPPSWebEventCallback(IPPSWebEventCallback iPPSWebEventCallback) {
        this.e.a(iPPSWebEventCallback);
    }

    public void setErrorPageView(View view) {
        if (view == null) {
            return;
        }
        a(view);
    }

    public void setAdLandingPageData(AdLandingPageData adLandingPageData) {
        this.g = adLandingPageData;
        this.e.a(adLandingPageData);
    }

    public void onStop() {
        this.e.a(this.n);
    }

    public void onResume() {
        this.e.c(System.currentTimeMillis());
        if (this.o) {
            return;
        }
        this.o = true;
        this.e.a();
    }

    public void loadPage() {
        if (this.g != null) {
            this.e.a(this.b);
            this.e.a(this.g.getLandingUrl(), this.b);
            this.u = this.g.getLandingUrl();
        }
    }

    public WebView getWebView() {
        return this.b;
    }

    public long getWebHasShownTime() {
        return this.e.k();
    }

    public oj getWebDetailPresenter() {
        return this.e;
    }

    public PPSAppDetailView getTopDetailView() {
        return this.k;
    }

    public WebSettings getSettings() {
        WebView webView = this.b;
        if (webView != null) {
            return webView.getSettings();
        }
        return null;
    }

    public d getCustomEmuiActionBar() {
        return this.c;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.w
    public String getCurrentPageUrl() {
        return this.u;
    }

    public void destroy() {
        WebView webView = this.b;
        if (webView != null) {
            webView.destroy();
        }
    }

    public void d() {
        Context context = getContext();
        TextView textView = new TextView(context);
        this.m = textView;
        textView.setId(R.id.trial_play_loading_text);
        this.m.setText(R.string._2130851109_res_0x7f023525);
        this.m.setTextSize(1, 14.0f);
        this.m.setTextColor(context.getResources().getColor(R.color._2131297896_res_0x7f090668));
        this.j = new ProgressBar(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        addView(this.m, layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(ao.a(context, 72.0f), ao.a(context, 72.0f));
        layoutParams2.setMargins(0, 0, 0, ao.a(context, 16.0f));
        layoutParams2.addRule(2, R.id.trial_play_loading_text);
        layoutParams2.addRule(13);
        addView(this.j, layoutParams2);
        WebView webView = this.b;
        if (webView != null) {
            webView.setWebChromeClient(new b());
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.w
    public void b(String str) {
        this.u = str;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.w
    public void b() {
        WebView webView = this.b;
        if (webView != null) {
            webView.loadUrl(Constants.ABOUT_BLANK);
            WebViewInstrumentation.loadUrl(webView, Constants.ABOUT_BLANK);
        }
        View view = this.d;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public void addJavascriptInterface(Object obj, String str) {
        WebView webView = this.b;
        if (webView != null) {
            webView.addJavascriptInterface(obj, str);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.w
    public void a(String str, String str2, String str3) {
        this.e.a(str, str2, str3);
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            ho.b("PPSWebView", "load page url is empty.");
        } else if (this.g != null) {
            this.e.a(this.b);
            this.e.a(str, this.b);
            this.u = str;
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.w
    public void a() {
        View view = this.i;
        if (view != null) {
            view.setVisibility(0);
        }
        WebView webView = this.b;
        if (webView != null) {
            webView.setVisibility(4);
        }
        View view2 = this.d;
        if (view2 != null) {
            view2.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        View view = this.i;
        if (view != null) {
            view.setVisibility(8);
        }
        WebView webView = this.b;
        if (webView != null) {
            webView.setVisibility(0);
        }
    }

    private void g() {
        View view = this.i;
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSWebView.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    PPSWebView.this.h();
                    PPSWebView.this.loadPage();
                    ViewClickInstrumentation.clickOnView(view2);
                }
            });
        }
    }

    private void f() {
        ImageView imageView = (ImageView) findViewById(R.id.hiad_id_load_fail_img);
        if (imageView != null) {
            Drawable drawable = getResources().getDrawable(R.drawable._2131428556_res_0x7f0b04cc);
            if (dd.c()) {
                imageView.setImageBitmap(az.b(drawable));
            }
        }
    }

    private void e() {
        RelativeLayout.LayoutParams layoutParams = this.l;
        if (layoutParams != null) {
            addView(this.k, layoutParams);
        }
        WebView webView = this.b;
        if (webView != null) {
            addView(webView, this.B);
        }
    }

    private void d(Context context) {
        PPSAppDetailView pPSAppDetailView = new PPSAppDetailView(context);
        this.k = pPSAppDetailView;
        pPSAppDetailView.setId(R.id.hiad_landing_top_app_detail);
        this.k.setVisibility(8);
        this.l = new RelativeLayout.LayoutParams(-1, -2);
    }

    private void c(Context context) {
        this.b = dq.a(context);
        this.B = new RelativeLayout.LayoutParams(-1, -1);
        WebView webView = this.b;
        if (webView != null) {
            webView.setId(R.id.hiad_webview);
            this.b.requestFocus();
            this.b.setWebChromeClient(new a());
            WebView webView2 = this.b;
            w wVar = new w(this);
            this.h = wVar;
            if (webView2 instanceof WebView) {
                APMSH5LoadInstrument.setWebViewClient(webView2, wVar);
            } else {
                webView2.setWebViewClient(wVar);
            }
            this.b.setOnKeyListener(this.z);
            this.b.setOnTouchListener(this.A);
        }
    }

    private void b(Context context) {
        co a2 = bz.a(context);
        this.w = a2;
        boolean g = a2.g();
        this.x = g;
        this.y = g && this.w.a(HiAdWidgets.HW_PROGRESS_BAR);
        ho.b("PPSWebView", "isHmOS: %s, useHmProgressBar: %s", Boolean.valueOf(this.x), Boolean.valueOf(this.y));
    }

    static /* synthetic */ int b(PPSWebView pPSWebView) {
        int i = pPSWebView.n;
        pPSWebView.n = i + 1;
        return i;
    }

    private void a(View view) {
        View view2 = this.i;
        if (view2 != null) {
            removeView(view2);
        }
        this.i = view;
        if (view != null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            d dVar = this.c;
            if (dVar != null) {
                layoutParams.addRule(3, dVar.getId());
            }
            addView(this.i, layoutParams);
            this.i.setVisibility(8);
        }
        g();
        f();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0110  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(android.content.Context r11, boolean r12) {
        /*
            Method dump skipped, instructions count: 295
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.views.PPSWebView.a(android.content.Context, boolean):void");
    }

    private void a(Context context) {
        this.q = true;
        this.e = new nv(context, this);
        this.t = ViewConfiguration.get(context).getScaledTouchSlop();
        b(context);
        try {
            a(context, false);
        } catch (Throwable unused) {
            ho.c("PPSWebView", "init webview error");
        }
    }

    /* loaded from: classes9.dex */
    class a extends WebChromeClient {
        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            String c = PPSWebView.this.e.c(str);
            boolean isShowPageTitle = PPSWebView.this.g != null ? PPSWebView.this.g.isShowPageTitle() : false;
            if (PPSWebView.this.c == null) {
                if (PPSWebView.this.f != null) {
                    PPSWebView.this.f.setTitle(isShowPageTitle ? c : " ");
                }
            } else {
                PPSWebView.this.c.a(isShowPageTitle);
                PPSWebView.this.c.setTitle(c);
            }
            super.onReceivedTitle(webView, c);
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            if (PPSWebView.this.d != null) {
                if (i == 100) {
                    PPSWebView.this.d.setVisibility(8);
                } else {
                    if (PPSWebView.this.d.getVisibility() == 8) {
                        PPSWebView.this.d.setVisibility(0);
                    }
                    if (PPSWebView.this.y) {
                        ((HwProgressBar) PPSWebView.this.d).setProgress(i, true);
                    } else {
                        ((e) PPSWebView.this.d).setProgress(i);
                    }
                }
            }
            super.onProgressChanged(webView, i);
        }

        private a() {
        }
    }

    public PPSWebView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.n = 0;
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = 0;
        this.s = 0;
        this.x = false;
        this.y = false;
        this.z = new View.OnKeyListener() { // from class: com.huawei.openalliance.ad.views.PPSWebView.1
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i3, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 0 || i3 != 4 || PPSWebView.this.b == null || !PPSWebView.this.b.canGoBack() || !bv.e(PPSWebView.this.b.getContext())) {
                    return false;
                }
                PPSWebView.this.b.goBack();
                return true;
            }
        };
        this.A = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.PPSWebView.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent != null && motionEvent.getAction() == 0) {
                    PPSWebView.b(PPSWebView.this);
                    PPSWebView.this.r = (int) motionEvent.getRawX();
                    PPSWebView.this.s = (int) motionEvent.getRawY();
                }
                if (motionEvent == null || 1 != motionEvent.getAction()) {
                    return false;
                }
                int rawX = (int) motionEvent.getRawX();
                int rawY = (int) motionEvent.getRawY();
                if (dd.a(PPSWebView.this.r, PPSWebView.this.s, rawX, rawY, PPSWebView.this.t)) {
                    return false;
                }
                if (ho.a()) {
                    ho.a("PPSWebView", "touch up download area x=%d, y=%d", Integer.valueOf(rawX), Integer.valueOf(rawY));
                }
                PPSWebView.this.e.a(rawX, rawY);
                return false;
            }
        };
        a(context);
    }

    class b extends WebChromeClient {
        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            if (PPSWebView.this.j != null && PPSWebView.this.m != null) {
                if (i == 100) {
                    PPSWebView.this.j.setVisibility(8);
                    PPSWebView.this.m.setVisibility(8);
                } else {
                    if (PPSWebView.this.j.getVisibility() == 8) {
                        PPSWebView.this.j.setVisibility(0);
                    }
                    if (PPSWebView.this.m.getVisibility() == 8) {
                        PPSWebView.this.m.setVisibility(0);
                    }
                }
            }
            super.onProgressChanged(webView, i);
        }

        private b() {
        }
    }

    public PPSWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.n = 0;
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = 0;
        this.s = 0;
        this.x = false;
        this.y = false;
        this.z = new View.OnKeyListener() { // from class: com.huawei.openalliance.ad.views.PPSWebView.1
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i3, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 0 || i3 != 4 || PPSWebView.this.b == null || !PPSWebView.this.b.canGoBack() || !bv.e(PPSWebView.this.b.getContext())) {
                    return false;
                }
                PPSWebView.this.b.goBack();
                return true;
            }
        };
        this.A = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.PPSWebView.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent != null && motionEvent.getAction() == 0) {
                    PPSWebView.b(PPSWebView.this);
                    PPSWebView.this.r = (int) motionEvent.getRawX();
                    PPSWebView.this.s = (int) motionEvent.getRawY();
                }
                if (motionEvent == null || 1 != motionEvent.getAction()) {
                    return false;
                }
                int rawX = (int) motionEvent.getRawX();
                int rawY = (int) motionEvent.getRawY();
                if (dd.a(PPSWebView.this.r, PPSWebView.this.s, rawX, rawY, PPSWebView.this.t)) {
                    return false;
                }
                if (ho.a()) {
                    ho.a("PPSWebView", "touch up download area x=%d, y=%d", Integer.valueOf(rawX), Integer.valueOf(rawY));
                }
                PPSWebView.this.e.a(rawX, rawY);
                return false;
            }
        };
        a(context);
    }

    public PPSWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.n = 0;
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = 0;
        this.s = 0;
        this.x = false;
        this.y = false;
        this.z = new View.OnKeyListener() { // from class: com.huawei.openalliance.ad.views.PPSWebView.1
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i3, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 0 || i3 != 4 || PPSWebView.this.b == null || !PPSWebView.this.b.canGoBack() || !bv.e(PPSWebView.this.b.getContext())) {
                    return false;
                }
                PPSWebView.this.b.goBack();
                return true;
            }
        };
        this.A = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.PPSWebView.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent != null && motionEvent.getAction() == 0) {
                    PPSWebView.b(PPSWebView.this);
                    PPSWebView.this.r = (int) motionEvent.getRawX();
                    PPSWebView.this.s = (int) motionEvent.getRawY();
                }
                if (motionEvent == null || 1 != motionEvent.getAction()) {
                    return false;
                }
                int rawX = (int) motionEvent.getRawX();
                int rawY = (int) motionEvent.getRawY();
                if (dd.a(PPSWebView.this.r, PPSWebView.this.s, rawX, rawY, PPSWebView.this.t)) {
                    return false;
                }
                if (ho.a()) {
                    ho.a("PPSWebView", "touch up download area x=%d, y=%d", Integer.valueOf(rawX), Integer.valueOf(rawY));
                }
                PPSWebView.this.e.a(rawX, rawY);
                return false;
            }
        };
        a(context);
    }

    public PPSWebView(Context context, ActionBar actionBar, AdLandingPageData adLandingPageData, d.a aVar, boolean z, boolean z2) {
        super(context);
        this.n = 0;
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = 0;
        this.s = 0;
        this.x = false;
        this.y = false;
        this.z = new View.OnKeyListener() { // from class: com.huawei.openalliance.ad.views.PPSWebView.1
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i3, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 0 || i3 != 4 || PPSWebView.this.b == null || !PPSWebView.this.b.canGoBack() || !bv.e(PPSWebView.this.b.getContext())) {
                    return false;
                }
                PPSWebView.this.b.goBack();
                return true;
            }
        };
        this.A = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.PPSWebView.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent != null && motionEvent.getAction() == 0) {
                    PPSWebView.b(PPSWebView.this);
                    PPSWebView.this.r = (int) motionEvent.getRawX();
                    PPSWebView.this.s = (int) motionEvent.getRawY();
                }
                if (motionEvent == null || 1 != motionEvent.getAction()) {
                    return false;
                }
                int rawX = (int) motionEvent.getRawX();
                int rawY = (int) motionEvent.getRawY();
                if (dd.a(PPSWebView.this.r, PPSWebView.this.s, rawX, rawY, PPSWebView.this.t)) {
                    return false;
                }
                if (ho.a()) {
                    ho.a("PPSWebView", "touch up download area x=%d, y=%d", Integer.valueOf(rawX), Integer.valueOf(rawY));
                }
                PPSWebView.this.e.a(rawX, rawY);
                return false;
            }
        };
        this.q = false;
        this.v = z2;
        this.g = adLandingPageData;
        this.f7985a = aVar;
        this.f = actionBar;
        b(context);
        this.e = new nv(context, adLandingPageData, this);
        a(context, z);
    }

    public PPSWebView(Context context) {
        super(context);
        this.n = 0;
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = 0;
        this.s = 0;
        this.x = false;
        this.y = false;
        this.z = new View.OnKeyListener() { // from class: com.huawei.openalliance.ad.views.PPSWebView.1
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i3, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 0 || i3 != 4 || PPSWebView.this.b == null || !PPSWebView.this.b.canGoBack() || !bv.e(PPSWebView.this.b.getContext())) {
                    return false;
                }
                PPSWebView.this.b.goBack();
                return true;
            }
        };
        this.A = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.PPSWebView.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent != null && motionEvent.getAction() == 0) {
                    PPSWebView.b(PPSWebView.this);
                    PPSWebView.this.r = (int) motionEvent.getRawX();
                    PPSWebView.this.s = (int) motionEvent.getRawY();
                }
                if (motionEvent == null || 1 != motionEvent.getAction()) {
                    return false;
                }
                int rawX = (int) motionEvent.getRawX();
                int rawY = (int) motionEvent.getRawY();
                if (dd.a(PPSWebView.this.r, PPSWebView.this.s, rawX, rawY, PPSWebView.this.t)) {
                    return false;
                }
                if (ho.a()) {
                    ho.a("PPSWebView", "touch up download area x=%d, y=%d", Integer.valueOf(rawX), Integer.valueOf(rawY));
                }
                PPSWebView.this.e.a(rawX, rawY);
                return false;
            }
        };
        a(context);
    }
}
