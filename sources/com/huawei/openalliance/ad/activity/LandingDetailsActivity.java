package com.huawei.openalliance.ad.activity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.co;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.dc;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.IAd;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.inter.data.IPlacementAd;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.data.h;
import com.huawei.openalliance.ad.oj;
import com.huawei.openalliance.ad.ox;
import com.huawei.openalliance.ad.pn;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.at;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dk;
import com.huawei.openalliance.ad.utils.x;
import com.huawei.openalliance.ad.views.AppDownloadButton;
import com.huawei.openalliance.ad.views.AppDownloadButtonStyle;
import com.huawei.openalliance.ad.views.ExtandAppDownloadButtonStyle;
import com.huawei.openalliance.ad.views.ExtandAppDownloadButtonStyleHm;
import com.huawei.openalliance.ad.views.PPSWebView;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class LandingDetailsActivity extends e {

    /* renamed from: a, reason: collision with root package name */
    private AppDownloadButton f6568a;
    private RelativeLayout b;
    private PPSWebView c;
    private ImageView d;
    private co e;
    private IAd f;
    private AdLandingPageData g;
    private MaterialClickInfo h;
    private boolean i;
    private MaterialClickInfo j;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.g = null;
        this.f = null;
        dc.a((com.huawei.openalliance.ad.inter.data.d) null);
        PPSWebView pPSWebView = this.c;
        if (pPSWebView != null) {
            pPSWebView.destroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.e, com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!c()) {
            ho.c("LandingDetailActivity", "parse ad data ex, skip open app detail activity.");
            finish();
            return;
        }
        try {
            ho.b("LandingDetailActivity", "onCreate start.");
            d();
            f();
            dd.A(this);
            i();
            h();
            if (this.i) {
                this.f6568a.setAdLandingPageData(this.g);
                b();
                this.c.setAdLandingPageData(this.g);
                oj webDetailPresenter = this.c.getWebDetailPresenter();
                if (webDetailPresenter != null) {
                    webDetailPresenter.b(null);
                }
                this.c.a(this.g.getAppInfo().getAppDetailUrl());
                return;
            }
            ho.b("LandingDetailActivity", "adLandingPageData is null.");
            IAd iAd = this.f;
            if (iAd instanceof INativeAd) {
                this.f6568a.setNativeAd((INativeAd) iAd);
            }
            IAd iAd2 = this.f;
            if (iAd2 instanceof IPlacementAd) {
                this.f6568a.setPlacementAd((IPlacementAd) iAd2);
            }
            IAd iAd3 = this.f;
            if (iAd3 instanceof h) {
                this.f6568a.setAdLandingPageData(new AdLandingPageData(pn.a((h) iAd3), getApplicationContext(), true));
            }
            IAd iAd4 = this.f;
            if (iAd4 instanceof com.huawei.openalliance.ad.inter.data.d) {
                this.f6568a.setAdLandingPageData(new AdLandingPageData(ox.a((com.huawei.openalliance.ad.inter.data.d) iAd4), getApplicationContext(), true));
            }
            b();
            AdLandingPageData adLandingPageData = new AdLandingPageData();
            this.g = adLandingPageData;
            this.c.setAdLandingPageData(adLandingPageData);
            oj webDetailPresenter2 = this.c.getWebDetailPresenter();
            if (webDetailPresenter2 != null) {
                webDetailPresenter2.b(null);
            }
            this.c.a(this.f.getAppInfo().getAppDetailUrl());
        } catch (Throwable th) {
            ho.c("LandingDetailActivity", "onCreate ex: %s", th.getClass().getSimpleName());
            finish();
        }
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        ho.b("LandingDetailActivity", "onConfigurationChanged.");
        g();
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void finish() {
        ho.b("LandingDetailActivity", "landing detail activity is finish.");
        if (this.p != null) {
            this.p.setBackgroundColor(getResources().getColor(R.color._2131297887_res_0x7f09065f));
        }
        super.finish();
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x001f, code lost:
    
        if (r1 != null) goto L14;
     */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity, android.view.Window.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            int r0 = com.huawei.openalliance.ad.th.a(r5)     // Catch: java.lang.Throwable -> L41
            if (r0 != 0) goto L24
            android.view.ViewGroup r1 = r4.p     // Catch: java.lang.Throwable -> L41
            if (r1 == 0) goto L24
            android.view.ViewGroup r1 = r4.p     // Catch: java.lang.Throwable -> L41
            com.huawei.openalliance.ad.inter.data.MaterialClickInfo r1 = com.huawei.openalliance.ad.th.a(r1, r5)     // Catch: java.lang.Throwable -> L41
            r4.h = r1     // Catch: java.lang.Throwable -> L41
            com.huawei.openalliance.ad.views.AppDownloadButton r2 = r4.f6568a     // Catch: java.lang.Throwable -> L41
            if (r2 == 0) goto L1b
            com.huawei.openalliance.ad.inter.data.MaterialClickInfo r3 = r4.j     // Catch: java.lang.Throwable -> L41
            if (r3 != 0) goto L1b
            goto L21
        L1b:
            if (r2 == 0) goto L24
            com.huawei.openalliance.ad.inter.data.MaterialClickInfo r1 = r4.j     // Catch: java.lang.Throwable -> L41
            if (r1 == 0) goto L24
        L21:
            r2.a(r1)     // Catch: java.lang.Throwable -> L41
        L24:
            r1 = 1
            if (r1 != r0) goto L55
            android.view.ViewGroup r0 = r4.p     // Catch: java.lang.Throwable -> L41
            if (r0 == 0) goto L55
            android.view.ViewGroup r0 = r4.p     // Catch: java.lang.Throwable -> L41
            com.huawei.openalliance.ad.inter.data.MaterialClickInfo r1 = r4.h     // Catch: java.lang.Throwable -> L41
            r2 = 0
            com.huawei.openalliance.ad.th.a(r0, r5, r2, r1)     // Catch: java.lang.Throwable -> L41
            com.huawei.openalliance.ad.views.AppDownloadButton r0 = r4.f6568a     // Catch: java.lang.Throwable -> L41
            if (r0 == 0) goto L55
            com.huawei.openalliance.ad.inter.data.MaterialClickInfo r1 = r4.j     // Catch: java.lang.Throwable -> L41
            if (r1 != 0) goto L55
            com.huawei.openalliance.ad.inter.data.MaterialClickInfo r1 = r4.h     // Catch: java.lang.Throwable -> L41
            r0.a(r1)     // Catch: java.lang.Throwable -> L41
            goto L55
        L41:
            r0 = move-exception
            java.lang.Class r0 = r0.getClass()
            java.lang.String r0 = r0.getSimpleName()
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "LandingDetailActivity"
            java.lang.String r2 = "dispatchTouchEvent exception : %s"
            com.huawei.openalliance.ad.ho.c(r1, r2, r0)
        L55:
            boolean r5 = super.dispatchTouchEvent(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.activity.LandingDetailsActivity.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // com.huawei.openalliance.ad.activity.e
    protected void a() {
        setContentView(x.n(this) ? R.layout.hiad_interstitial_landing_details : (x.q(this) && x.r(this)) ? R.layout.hiad_interstitial_foldable_landing_details : R.layout.hiad_activity_landing_details);
    }

    private AppInfo j() {
        AdLandingPageData adLandingPageData;
        if (this.i && (adLandingPageData = this.g) != null) {
            return adLandingPageData.getAppInfo();
        }
        IAd iAd = this.f;
        if (iAd != null) {
            return iAd.getAppInfo();
        }
        return null;
    }

    private void i() {
        PPSWebView pPSWebView = this.c;
        if (pPSWebView == null) {
            ho.b("LandingDetailActivity", "set force dark ppsWebView is null.");
            return;
        }
        WebView webView = pPSWebView.getWebView();
        if (webView == null) {
            ho.b("LandingDetailActivity", "set force dark webView is null.");
            this.c.setVisibility(8);
            findViewById(R.id.landing_load_fail_view).setVisibility(0);
            return;
        }
        webView.setLayerType(1, null);
        webView.setBackgroundColor(getResources().getColor(R.color._2131297990_res_0x7f0906c6));
        webView.setVerticalScrollBarEnabled(false);
        this.c.getSettings().setDomStorageEnabled(true);
        if (webView instanceof com.huawei.openalliance.ad.views.linkscroll.e) {
            float a2 = ao.a(this, 24.0f);
            float[] fArr = {a2, a2, a2, a2, 0.0f, 0.0f, 0.0f, 0.0f};
            com.huawei.openalliance.ad.views.linkscroll.e eVar = (com.huawei.openalliance.ad.views.linkscroll.e) webView;
            eVar.setRadiusArray(fArr);
            eVar.setSupportWebViewRadius(true);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            WebSettings settings = webView.getSettings();
            if (32 == (getResources().getConfiguration().uiMode & 48)) {
                settings.setForceDark(2);
            } else {
                settings.setForceDark(0);
            }
        }
    }

    private void h() {
        this.c.addJavascriptInterface(new at(this, j()), Constants.PPS_LANDING_DETAILS);
    }

    private void g() {
        Resources resources;
        int i;
        if (x.n(this)) {
            a(0.72f, 0.74f, false);
            resources = getResources();
            i = R.dimen._2131363287_res_0x7f0a05d7;
        } else if (x.q(this) && x.r(this)) {
            a(0.72f, 0.74f, true);
            resources = getResources();
            i = R.dimen._2131363281_res_0x7f0a05d1;
        } else {
            a(1.0f, 0.84f, false);
            resources = getResources();
            i = R.dimen._2131363276_res_0x7f0a05cc;
        }
        int dimensionPixelSize = resources.getDimensionPixelSize(i);
        AppDownloadButton appDownloadButton = this.f6568a;
        if (appDownloadButton == null || dimensionPixelSize <= 0) {
            return;
        }
        appDownloadButton.setMinWidth(dimensionPixelSize);
        this.f6568a.setMaxWidth(dimensionPixelSize);
    }

    private void f() {
        AppDownloadButton appDownloadButton;
        AppDownloadButtonStyle extandAppDownloadButtonStyle;
        if (this.f6568a == null) {
            ho.b("LandingDetailActivity", "appDownloadButton is null.");
            return;
        }
        co a2 = bz.a(this);
        this.e = a2;
        if (a2.g()) {
            appDownloadButton = this.f6568a;
            extandAppDownloadButtonStyle = new ExtandAppDownloadButtonStyleHm(this);
        } else {
            appDownloadButton = this.f6568a;
            extandAppDownloadButtonStyle = new ExtandAppDownloadButtonStyle(this);
        }
        appDownloadButton.setAppDownloadButtonStyle(extandAppDownloadButtonStyle);
        this.f6568a.setFixedWidth(false);
        g();
    }

    private void e() {
        try {
            int f = dd.f(this);
            ViewGroup viewGroup = (ViewGroup) findViewById(R.id.app_download_container);
            ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
            layoutParams.height += f;
            viewGroup.setLayoutParams(layoutParams);
        } catch (Throwable th) {
            ho.b("LandingDetailActivity", "match bar err: %s", th.getClass().getSimpleName());
        }
    }

    private void d() {
        this.p = (ViewGroup) findViewById(R.id.landing_activity_root);
        this.b = (RelativeLayout) findViewById(R.id.landing_detail_parent);
        this.c = (PPSWebView) findViewById(R.id.landing_details_webView);
        this.d = (ImageView) findViewById(R.id.landing_close_image_view);
        this.f6568a = (AppDownloadButton) findViewById(R.id.app_download_btn_detail);
        e();
        this.p.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.activity.LandingDetailsActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LandingDetailsActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.activity.LandingDetailsActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LandingDetailsActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private boolean c() {
        Serializable serializableExtra;
        try {
            SafeIntent safeIntent = new SafeIntent(getIntent());
            MaterialClickInfo materialClickInfo = (MaterialClickInfo) be.b(safeIntent.getStringExtra("click_info"), MaterialClickInfo.class, new Class[0]);
            if (materialClickInfo != null && cz.p(materialClickInfo.c()) && materialClickInfo.a() != null) {
                this.j = materialClickInfo;
                ho.a("LandingDetailActivity", "orgClickInfo: %s", materialClickInfo.toString());
            }
            serializableExtra = safeIntent.getSerializableExtra(MapKeyNames.APP_DETAIL_DATA);
        } catch (Throwable th) {
            ho.c("LandingDetailActivity", "parse ad data ex: %s", th.getClass().getSimpleName());
        }
        if (serializableExtra instanceof AdLandingPageData) {
            this.g = (AdLandingPageData) serializableExtra;
            this.i = true;
            return true;
        }
        if (serializableExtra instanceof IAd) {
            this.f = (IAd) serializableExtra;
            this.i = false;
            return true;
        }
        com.huawei.openalliance.ad.inter.data.d a2 = dc.a();
        if (a2 != null) {
            this.f = a2;
            this.i = false;
            return true;
        }
        ho.c("LandingDetailActivity", "start app detail activity, ad content is empty.");
        return false;
    }

    private void b() {
        this.f6568a.setSource(5);
    }

    private void a(float f, float f2, boolean z) {
        RelativeLayout.LayoutParams layoutParams;
        RelativeLayout relativeLayout = this.b;
        if (relativeLayout == null || (layoutParams = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams()) == null) {
            return;
        }
        int c = z ? dk.c(this) : dk.c(this) + ao.h(this);
        layoutParams.width = (int) (dk.b(this) * f);
        layoutParams.height = (int) (c * f2);
        this.b.setLayoutParams(layoutParams);
    }
}
