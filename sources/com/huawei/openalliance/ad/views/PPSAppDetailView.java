package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.openalliance.ad.beans.metadata.AdSource;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.co;
import com.huawei.openalliance.ad.constant.ClickDestination;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.dh;
import com.huawei.openalliance.ad.dk;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.gc;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.listeners.INonwifiActionListener;
import com.huawei.openalliance.ad.os;
import com.huawei.openalliance.ad.ph;
import com.huawei.openalliance.ad.rr;
import com.huawei.openalliance.ad.rt;
import com.huawei.openalliance.ad.ru;
import com.huawei.openalliance.ad.th;
import com.huawei.openalliance.ad.utils.Cdo;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.az;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.views.AppDownloadButton;
import com.huawei.profile.profile.ProfileExtendConstants;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class PPSAppDetailView extends RelativeLayout {
    private ScanningRelativeLayout A;
    private ParticleRelativeLayout B;
    private com.huawei.openalliance.ad.views.interfaces.a C;
    private int D;
    private int E;
    private MaterialClickInfo F;
    private long G;
    private int H;
    private int I;
    private Handler J;
    private View.OnClickListener K;
    private View.OnTouchListener L;

    /* renamed from: a, reason: collision with root package name */
    protected Context f7833a;
    protected AppDownloadButton b;
    protected int c;
    protected SixElementsView d;
    private AdLandingPageData e;
    private ImageView f;
    private PPSLabelView g;
    private boolean h;
    private AppInfo i;
    private gc j;
    private ContentRecord k;
    private View l;
    private com.huawei.openalliance.ad.analysis.h m;
    private int n;
    private int o;
    private int p;
    private boolean q;
    private boolean r;
    private com.huawei.openalliance.ad.views.interfaces.c s;
    private INonwifiActionListener t;
    private boolean u;
    private String v;
    private co w;
    private boolean x;
    private com.huawei.openalliance.ad.views.interfaces.e y;
    private RelativeLayout z;

    public void a(int i) {
    }

    protected int getDetailStyle() {
        return 1;
    }

    public void setOnNonWifiDownloadListener(INonwifiActionListener iNonwifiActionListener) {
        this.t = iNonwifiActionListener;
    }

    public void setNonBtnSource(int i) {
        this.I = i;
    }

    public void setNeedShowDspInfo(boolean z) {
        this.h = z;
    }

    public void setNeedPerBeforDownload(boolean z) {
        this.u = z;
    }

    public void setLoadAppIconSelf(boolean z) {
        this.r = z;
    }

    public void setInteractedListener(com.huawei.openalliance.ad.views.interfaces.a aVar) {
        this.C = aVar;
    }

    public void setInterType(int i) {
        this.E = i;
    }

    public void setDetailViewType(int i) {
        this.D = i;
    }

    public void setBtnSource(int i) {
        this.H = i;
        AppDownloadButton appDownloadButton = this.b;
        if (appDownloadButton != null) {
            appDownloadButton.setSource(i);
        }
    }

    public void setAppRelated(boolean z) {
        this.q = z;
    }

    public void setAppIconImageDrawable(Drawable drawable) {
        ImageView imageView = this.f;
        if (imageView == null || drawable == null) {
            return;
        }
        imageView.setBackground(null);
        this.f.setImageDrawable(drawable);
    }

    public void setAppDetailClickListener(com.huawei.openalliance.ad.views.interfaces.c cVar) {
        this.s = cVar;
    }

    public void setAdLandingData(AdLandingPageData adLandingPageData) {
        String str;
        if (adLandingPageData == null) {
            return;
        }
        try {
            ho.b("PPSAppDetailView", "set ad landing data");
            this.e = adLandingPageData;
            this.k = adLandingPageData.x();
            this.i = this.e.getAppInfo();
            PPSLabelView pPSLabelView = this.g;
            if (pPSLabelView != null) {
                pPSLabelView.setDataAndRefreshUi(this.k);
            }
            if (this.i == null) {
                a(this.l, 8);
            } else {
                a();
            }
            MetaData metaData = (MetaData) be.b(this.e.s(), MetaData.class, new Class[0]);
            if (metaData != null) {
                this.v = cz.c(metaData.a());
            }
            this.x = adLandingPageData.z();
        } catch (RuntimeException unused) {
            str = "setAdLandingPageData RuntimeException";
            ho.c("PPSAppDetailView", str);
        } catch (Exception unused2) {
            str = "setAdLandingPageData error";
            ho.c("PPSAppDetailView", str);
        }
    }

    public AppInfo getAppInfo() {
        return this.i;
    }

    public AppDownloadButton getAppDownloadButton() {
        return this.b;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            if (th.a(motionEvent) == 0) {
                MaterialClickInfo b2 = th.b(this, motionEvent);
                this.F = b2;
                AppDownloadButton appDownloadButton = this.b;
                if (appDownloadButton != null) {
                    appDownloadButton.a(b2);
                }
            }
            if (1 == motionEvent.getAction()) {
                th.b(this, motionEvent, null, this.F);
                AppDownloadButton appDownloadButton2 = this.b;
                if (appDownloadButton2 != null) {
                    appDownloadButton2.a(this.F);
                }
                SixElementsView sixElementsView = this.d;
                if (sixElementsView != null) {
                    sixElementsView.setOrgClickInfo(this.F);
                }
            }
        } catch (Throwable th) {
            ho.c("PPSAppDetailView", "dispatchTouchEvent exception : %s", th.getClass().getSimpleName());
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void d() {
        com.huawei.openalliance.ad.views.interfaces.e eVar = this.y;
        if (eVar == null || !eVar.b()) {
            return;
        }
        ho.b("PPSAppDetailView", "stop animation.");
        Cdo.a((View) this.z, false);
        this.y.a();
    }

    public void c() {
        o();
        com.huawei.openalliance.ad.views.interfaces.e eVar = this.y;
        if (eVar == null || eVar.b()) {
            return;
        }
        this.y.setAutoRepeat(true);
        ho.b("PPSAppDetailView", "start animation.");
        try {
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.app_download_btn_rl);
            Cdo.a((View) this.z, true);
            this.y.a(relativeLayout, this.e);
        } catch (Throwable th) {
            ho.c("PPSAppDetailView", "start animation error: %s", th.getClass().getSimpleName());
        }
    }

    protected void b(Context context) {
        AppDownloadButton appDownloadButton = (AppDownloadButton) findViewById(R.id.app_download_btn);
        this.b = appDownloadButton;
        if (appDownloadButton == null) {
            return;
        }
        this.b.setTextColor(context.getResources().getColor(R.color._2131297955_res_0x7f0906a3));
    }

    public void b() {
        AppDownloadButton appDownloadButton = this.b;
        if (appDownloadButton != null) {
            a(appDownloadButton, 8);
        }
    }

    public void a(String str) {
        ContentRecord contentRecord = this.k;
        if (contentRecord != null) {
            contentRecord.c(str);
            AppDownloadButton appDownloadButton = this.b;
            if (appDownloadButton != null) {
                appDownloadButton.updateContent(str);
            }
        }
    }

    public void a(Integer num) {
        com.huawei.openalliance.ad.views.interfaces.a aVar = this.C;
        if (aVar != null) {
            aVar.a(num.intValue());
        }
    }

    public void a(ImageView imageView, String str, az.a aVar) {
        if (TextUtils.isEmpty(str) || imageView == null || this.f7833a == null) {
            return;
        }
        ho.b("PPSAppDetailView", "load app icon:" + cz.f(str));
        com.huawei.openalliance.ad.utils.m.d(new a(str, aVar, this.q, this.f7833a));
    }

    protected void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100125_res_0x7f0601dd});
            if (obtainStyledAttributes != null) {
                try {
                    int integer = obtainStyledAttributes.getInteger(0, 0);
                    this.c = integer;
                    ho.a("PPSAppDetailView", "FullScreen %d", Integer.valueOf(integer));
                } finally {
                }
            }
            obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100151_res_0x7f0601f7});
            if (obtainStyledAttributes != null) {
                try {
                    int integer2 = obtainStyledAttributes.getInteger(0, 0);
                    this.D = integer2;
                    ho.a("PPSAppDetailView", "detailViewType %d", Integer.valueOf(integer2));
                } finally {
                }
            }
        }
    }

    public void a(long j) {
        ContentRecord contentRecord = this.k;
        if (contentRecord == null) {
            return;
        }
        contentRecord.f(j);
        AppDownloadButton appDownloadButton = this.b;
        if (appDownloadButton != null) {
            appDownloadButton.updateStartShowTime(j);
        }
    }

    protected void a() {
        ImageView imageView;
        h();
        SixElementsView sixElementsView = this.d;
        if (sixElementsView != null) {
            sixElementsView.a(false);
            this.d.a(this.e);
        }
        if (this.r && (imageView = this.f) != null) {
            a(imageView, this.i.getIconUrl(), new b(this.f));
        }
        this.l.setOnTouchListener(this.L);
        j();
        i();
        this.J.sendEmptyMessageDelayed(1001, ProfileExtendConstants.TIME_OUT);
        AppDownloadButton appDownloadButton = this.b;
        if (appDownloadButton != null) {
            setCancelDownloadButtonVisibility(appDownloadButton.refreshStatus());
        }
    }

    protected int a(Context context) {
        return this.c == 1 ? R.layout.hiad_landing_app_detail_half : R.layout.hiad_landing_app_detail;
    }

    private void setCancelDownloadButtonVisibility(AppStatus appStatus) {
        AppInfo appInfo;
        if (appStatus == AppStatus.DOWNLOAD && (appInfo = this.e.getAppInfo()) != null && appInfo.u()) {
            a(appInfo);
        }
    }

    private void o() {
        AppDownloadButton appDownloadButton;
        AppDownloadButtonStyle extandAppDownloadButtonStyle;
        co coVar = this.w;
        if (coVar == null || this.b == null) {
            return;
        }
        if (coVar.g()) {
            appDownloadButton = this.b;
            extandAppDownloadButtonStyle = new ExtandAppDownloadButtonStyleHm(this.f7833a);
        } else {
            appDownloadButton = this.b;
            extandAppDownloadButtonStyle = new ExtandAppDownloadButtonStyle(this.f7833a);
        }
        appDownloadButton.setAppDownloadButtonStyle(extandAppDownloadButtonStyle);
        this.b.refreshStatus();
    }

    private boolean n() {
        AppDownloadButton appDownloadButton;
        if (this.e == null || this.y == null || (appDownloadButton = this.b) == null) {
            return false;
        }
        AppStatus refreshStatus = appDownloadButton.refreshStatus();
        return refreshStatus == AppStatus.DOWNLOAD || refreshStatus == AppStatus.INSTALLED;
    }

    private boolean m() {
        return n() && os.x(this.e.f());
    }

    private boolean l() {
        return n() && os.y(this.e.f());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean k() {
        return n() && !os.w(this.e.f());
    }

    private void j() {
        if (os.x(this.e.f())) {
            ScanningRelativeLayout scanningRelativeLayout = this.A;
            this.y = scanningRelativeLayout;
            this.z = scanningRelativeLayout;
            Cdo.a((View) scanningRelativeLayout, true);
        } else {
            if (os.y(this.e.f())) {
                ParticleRelativeLayout particleRelativeLayout = this.B;
                this.y = particleRelativeLayout;
                this.z = particleRelativeLayout;
                Cdo.a((View) this.A, false);
                Cdo.a((View) this.B, true);
                return;
            }
            Cdo.a((View) this.A, false);
        }
        Cdo.a((View) this.B, false);
    }

    private void i() {
        com.huawei.openalliance.ad.views.interfaces.e eVar;
        com.huawei.openalliance.ad.views.interfaces.e eVar2;
        AppDownloadButton appDownloadButton = this.b;
        if (appDownloadButton != null) {
            appDownloadButton.setAdLandingPageData(this.e);
            this.b.setNeedShowPermision(this.u);
            ho.b("PPSAppDetailView", "enable btn scan: %s", Boolean.valueOf(os.x(this.e.f())));
            ho.b("PPSAppDetailView", "enable btn particle: %s", Boolean.valueOf(os.y(this.e.f())));
            if (!m() || (eVar2 = this.y) == null || eVar2.b()) {
                if (l() && (eVar = this.y) != null && !eVar.b()) {
                    ho.b("PPSAppDetailView", "show btn particle animation");
                }
                o();
            } else {
                this.b.setAppDownloadButtonStyle(new aa(this.f7833a));
            }
            this.b.setOnDownloadStatusChangedListener(new AppDownloadButton.OnDownloadStatusChangedListener() { // from class: com.huawei.openalliance.ad.views.PPSAppDetailView.4
                @Override // com.huawei.openalliance.ad.views.AppDownloadButton.OnDownloadStatusChangedListener
                public void onUserCancel(AppInfo appInfo) {
                }

                @Override // com.huawei.openalliance.ad.views.AppDownloadButton.OnDownloadStatusChangedListener
                public void onStatusChanged(AppStatus appStatus) {
                    PPSAppDetailView.this.a(appStatus);
                }
            });
            this.b.setButtonTextWatcherInner(new AppDownloadButton.e() { // from class: com.huawei.openalliance.ad.views.PPSAppDetailView.5
                @Override // com.huawei.openalliance.ad.views.AppDownloadButton.e
                public CharSequence a(CharSequence charSequence, AppStatus appStatus) {
                    return !PPSAppDetailView.this.q ? com.huawei.openalliance.ad.utils.j.a(PPSAppDetailView.this.v, PPSAppDetailView.this.f7833a.getString(R.string._2130851084_res_0x7f02350c)) : charSequence;
                }
            });
            this.b.setOnNonWifiDownloadListener(new AppDownloadButton.OnNonWifiDownloadListener() { // from class: com.huawei.openalliance.ad.views.PPSAppDetailView.6
                @Override // com.huawei.openalliance.ad.views.AppDownloadButton.OnNonWifiDownloadListener
                public boolean onNonWifiDownload(AppInfo appInfo, long j) {
                    if ((PPSAppDetailView.this.t == null || !PPSAppDetailView.this.t.onAppDownload(appInfo, j)) && PPSAppDetailView.this.j.bn() && PPSAppDetailView.this.x) {
                        PPSAppDetailView.this.b.b();
                        return false;
                    }
                    PPSAppDetailView.this.b.setAllowedNonWifiNetwork(true);
                    return true;
                }
            });
            this.b.setSource(this.H);
        }
    }

    private void h() {
        ContentRecord contentRecord;
        MetaData h;
        AdSource b2;
        if (!MLAsrConstants.LAN_ZH.equalsIgnoreCase(com.huawei.openalliance.ad.utils.d.a()) || (contentRecord = this.k) == null || !this.h || (h = contentRecord.h()) == null || (b2 = AdSource.b(h.K())) == null || this.g == null) {
            return;
        }
        if (TextUtils.isEmpty(b2.a()) && TextUtils.isEmpty(b2.b())) {
            ho.a("PPSAppDetailView", "loadDspInfo error");
            this.g.setVisibility(8);
        } else {
            ho.a("PPSAppDetailView", "loading dsp info");
            this.g.setVisibility(8);
            this.g.setTextForAppDetailView(b2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getClickDestination() {
        return this.E == 9 ? ClickDestination.HARMONY_SERVICE : ClickDestination.WEB;
    }

    private int getButtonRadius() {
        AppDownloadButton appDownloadButton = getAppDownloadButton();
        if (appDownloadButton == null) {
            return 0;
        }
        return appDownloadButton.getRoundRadius();
    }

    private boolean g() {
        AdLandingPageData adLandingPageData = this.e;
        return adLandingPageData != null && os.q(adLandingPageData.f()) && this.D == 0;
    }

    private boolean f() {
        AdLandingPageData adLandingPageData = this.e;
        if (adLandingPageData != null) {
            return os.p(adLandingPageData.f());
        }
        return false;
    }

    private boolean e() {
        AdLandingPageData adLandingPageData = this.e;
        if (adLandingPageData != null) {
            if (os.o(adLandingPageData.f())) {
                return true;
            }
            if (os.q(this.e.f()) && this.D == 1) {
                return true;
            }
        }
        return false;
    }

    private int c(Context context) {
        if (R.layout.hiad_landing_expand_button_detail_half == a(context)) {
            return ao.a(context, dd.l(context) ? 270 : 480);
        }
        return com.huawei.openalliance.ad.utils.d.a(context, dd.k(context));
    }

    private void b(Context context, AttributeSet attributeSet) {
        String str;
        try {
            a(context, attributeSet);
            this.f7833a = context;
            this.w = bz.a(context);
            this.j = fh.b(context);
            this.m = new com.huawei.openalliance.ad.analysis.h(context);
            this.n = ViewConfiguration.get(context).getScaledTouchSlop();
            this.l = inflate(context, a(context), this);
            this.A = (ScanningRelativeLayout) findViewById(R.id.hiad_detail_btn_scan);
            this.B = (ParticleRelativeLayout) findViewById(R.id.hiad_detail_btn_particle);
            this.f = (ImageView) findViewById(R.id.app_icon);
            this.d = (SixElementsView) findViewById(R.id.app_detail_six_elements);
            PPSLabelView pPSLabelView = (PPSLabelView) findViewById(R.id.hiad_dsp_info);
            this.g = pPSLabelView;
            if (pPSLabelView != null) {
                pPSLabelView.setVisibility(8);
            }
            b(context);
            AppDownloadButton appDownloadButton = this.b;
            if (appDownloadButton != null) {
                appDownloadButton.a(this.K);
                this.b.setAppDetailView(this);
            }
            int buttonRadius = getButtonRadius();
            if (this.A != null && buttonRadius > 0) {
                ho.b("PPSAppDetailView", "got button radius: %s", Integer.valueOf(buttonRadius));
                this.A.setRadius(buttonRadius);
            }
            int c = c(context);
            ho.b("PPSAppDetailView", "screenWidth is %d", Integer.valueOf(c));
            if (this.g == null || !MLAsrConstants.LAN_ZH.equalsIgnoreCase(com.huawei.openalliance.ad.utils.d.a())) {
                return;
            }
            this.g.setMaxWidth((int) (c * 0.25d));
        } catch (RuntimeException unused) {
            str = "init RuntimeException";
            ho.c("PPSAppDetailView", str);
        } catch (Exception unused2) {
            str = "init error";
            ho.c("PPSAppDetailView", str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        int i;
        if (this.G > 0 && ao.c() - this.G <= 500) {
            ho.c("PPSAppDetailView", "fast click");
            return;
        }
        this.G = ao.c();
        if (!z) {
            if (this.s == null || e()) {
                return;
            }
            this.s.a(new com.huawei.openalliance.ad.views.interfaces.b(false, false, getClickDestination(), 28));
            return;
        }
        if (f()) {
            this.b.setSource(this.I);
            this.b.performClick();
        } else if (this.s != null) {
            com.huawei.openalliance.ad.views.interfaces.b bVar = new com.huawei.openalliance.ad.views.interfaces.b(true, false, getClickDestination(), 28);
            if (!g()) {
                i = e() ? 1 : 0;
                this.s.a(bVar);
            }
            bVar.a(i);
            this.s.a(bVar);
        }
    }

    private void a(AppInfo appInfo) {
        new ph(this.f7833a).a(appInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AppStatus appStatus) {
        setCancelDownloadButtonVisibility(appStatus);
        if (ho.a()) {
            ho.a("PPSAppDetailView", "onStatusChanged: %s", appStatus);
        }
        if ((appStatus == AppStatus.DOWNLOAD || appStatus == AppStatus.INSTALLED) && k()) {
            c();
        } else {
            d();
        }
    }

    static class b implements az.a {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<ImageView> f7844a;

        @Override // com.huawei.openalliance.ad.utils.az.a
        public void a() {
        }

        @Override // com.huawei.openalliance.ad.utils.az.a
        public void a(final Drawable drawable) {
            if (drawable == null) {
                return;
            }
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSAppDetailView.b.1
                @Override // java.lang.Runnable
                public void run() {
                    ImageView imageView = (ImageView) b.this.f7844a.get();
                    if (imageView == null) {
                        return;
                    }
                    imageView.setBackground(null);
                    imageView.setImageDrawable(drawable);
                }
            });
        }

        public b(ImageView imageView) {
            this.f7844a = new WeakReference<>(imageView);
        }
    }

    private void a(View view, int i) {
        if (view != null) {
            view.setVisibility(i);
        }
    }

    static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private String f7841a;
        private az.a b;
        private boolean c;
        private dk d;
        private Context e;

        @Override // java.lang.Runnable
        public void run() {
            if (this.e == null) {
                return;
            }
            rt rtVar = new rt();
            rtVar.b(false);
            rtVar.c(true);
            rtVar.a("icon");
            rtVar.c(this.f7841a);
            if (!this.c) {
                rtVar.c(fh.b(this.e).e());
            }
            ru a2 = new rr(this.e, rtVar).a();
            if (a2 == null) {
                return;
            }
            String a3 = a2.a();
            if (TextUtils.isEmpty(a3)) {
                return;
            }
            String c = this.d.c(a3);
            if (TextUtils.isEmpty(c)) {
                return;
            }
            az.a(this.e, c, new az.a() { // from class: com.huawei.openalliance.ad.views.PPSAppDetailView.a.1
                @Override // com.huawei.openalliance.ad.utils.az.a
                public void a() {
                }

                @Override // com.huawei.openalliance.ad.utils.az.a
                public void a(final Drawable drawable) {
                    if (drawable == null) {
                        return;
                    }
                    dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSAppDetailView.a.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            a.this.b.a(drawable);
                        }
                    });
                }
            });
        }

        public a(String str, az.a aVar, boolean z, Context context) {
            this.f7841a = str;
            this.b = aVar;
            this.c = z;
            context = context != null ? context.getApplicationContext() : context;
            this.e = context;
            this.d = dh.a(context, "normal");
        }
    }

    public PPSAppDetailView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = false;
        this.o = 0;
        this.p = 0;
        this.q = true;
        this.r = true;
        this.u = false;
        this.c = 0;
        this.x = true;
        this.D = 0;
        this.G = -1L;
        this.H = 5;
        this.I = 5;
        this.J = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.huawei.openalliance.ad.views.PPSAppDetailView.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                if (!PPSAppDetailView.this.k()) {
                    return true;
                }
                PPSAppDetailView.this.c();
                return true;
            }
        });
        this.K = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSAppDetailView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PPSAppDetailView.this.q) {
                    PPSAppDetailView.this.b.onClick(null);
                    if (PPSAppDetailView.this.s != null) {
                        PPSAppDetailView.this.s.a(new com.huawei.openalliance.ad.views.interfaces.b(PPSAppDetailView.this.q, true, AppStatus.INSTALLED == PPSAppDetailView.this.b.getStatus() ? "app" : ""));
                    }
                } else if (PPSAppDetailView.this.s != null) {
                    PPSAppDetailView.this.s.a(new com.huawei.openalliance.ad.views.interfaces.b(false, false, PPSAppDetailView.this.getClickDestination()));
                } else {
                    ho.b("PPSAppDetailView", "onButtonClick, appDetailClickListener is null");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.L = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.PPSAppDetailView.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ho.b("PPSAppDetailView", "action: %s, viewId: %s", Integer.valueOf(motionEvent.getAction()), Integer.valueOf(view.getId()));
                if (PPSAppDetailView.this.b != null) {
                    int action = motionEvent.getAction();
                    if (action == 0) {
                        PPSAppDetailView.this.o = (int) motionEvent.getRawX();
                        PPSAppDetailView.this.p = (int) motionEvent.getRawY();
                    } else if (action == 1) {
                        int rawX = (int) motionEvent.getRawX();
                        int rawY = (int) motionEvent.getRawY();
                        if (PPSAppDetailView.this.q) {
                            PPSAppDetailView.this.b.setClickActionListener(new com.huawei.openalliance.ad.views.interfaces.x() { // from class: com.huawei.openalliance.ad.views.PPSAppDetailView.3.1
                                @Override // com.huawei.openalliance.ad.views.interfaces.x
                                public void b(AppDownloadButton appDownloadButton) {
                                    if (PPSAppDetailView.this.s != null) {
                                        PPSAppDetailView.this.s.a(new com.huawei.openalliance.ad.views.interfaces.b(PPSAppDetailView.this.q, true, AppStatus.INSTALLED == appDownloadButton.getStatus() ? "app" : ""));
                                    }
                                }

                                @Override // com.huawei.openalliance.ad.views.interfaces.x
                                public void a(AppDownloadButton appDownloadButton) {
                                    if (PPSAppDetailView.this.s != null) {
                                        PPSAppDetailView.this.s.a(new com.huawei.openalliance.ad.views.interfaces.b(PPSAppDetailView.this.q, false, AppStatus.INSTALLED == appDownloadButton.getStatus() ? "app" : ""));
                                    }
                                }
                            });
                        }
                        PPSAppDetailView pPSAppDetailView = PPSAppDetailView.this;
                        pPSAppDetailView.a(pPSAppDetailView.q);
                        if (!dd.a(PPSAppDetailView.this.o, PPSAppDetailView.this.p, rawX, rawY, PPSAppDetailView.this.n)) {
                            if (ho.a()) {
                                ho.a("PPSAppDetailView", "touch up download area x=%d, y=%d", Integer.valueOf(rawX), Integer.valueOf(rawY));
                            }
                            PPSAppDetailView.this.m.a(rawX, rawY, PPSAppDetailView.this.k);
                        }
                    }
                }
                return true;
            }
        };
        b(context, attributeSet);
    }

    public PPSAppDetailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = false;
        this.o = 0;
        this.p = 0;
        this.q = true;
        this.r = true;
        this.u = false;
        this.c = 0;
        this.x = true;
        this.D = 0;
        this.G = -1L;
        this.H = 5;
        this.I = 5;
        this.J = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.huawei.openalliance.ad.views.PPSAppDetailView.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                if (!PPSAppDetailView.this.k()) {
                    return true;
                }
                PPSAppDetailView.this.c();
                return true;
            }
        });
        this.K = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSAppDetailView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PPSAppDetailView.this.q) {
                    PPSAppDetailView.this.b.onClick(null);
                    if (PPSAppDetailView.this.s != null) {
                        PPSAppDetailView.this.s.a(new com.huawei.openalliance.ad.views.interfaces.b(PPSAppDetailView.this.q, true, AppStatus.INSTALLED == PPSAppDetailView.this.b.getStatus() ? "app" : ""));
                    }
                } else if (PPSAppDetailView.this.s != null) {
                    PPSAppDetailView.this.s.a(new com.huawei.openalliance.ad.views.interfaces.b(false, false, PPSAppDetailView.this.getClickDestination()));
                } else {
                    ho.b("PPSAppDetailView", "onButtonClick, appDetailClickListener is null");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.L = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.PPSAppDetailView.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ho.b("PPSAppDetailView", "action: %s, viewId: %s", Integer.valueOf(motionEvent.getAction()), Integer.valueOf(view.getId()));
                if (PPSAppDetailView.this.b != null) {
                    int action = motionEvent.getAction();
                    if (action == 0) {
                        PPSAppDetailView.this.o = (int) motionEvent.getRawX();
                        PPSAppDetailView.this.p = (int) motionEvent.getRawY();
                    } else if (action == 1) {
                        int rawX = (int) motionEvent.getRawX();
                        int rawY = (int) motionEvent.getRawY();
                        if (PPSAppDetailView.this.q) {
                            PPSAppDetailView.this.b.setClickActionListener(new com.huawei.openalliance.ad.views.interfaces.x() { // from class: com.huawei.openalliance.ad.views.PPSAppDetailView.3.1
                                @Override // com.huawei.openalliance.ad.views.interfaces.x
                                public void b(AppDownloadButton appDownloadButton) {
                                    if (PPSAppDetailView.this.s != null) {
                                        PPSAppDetailView.this.s.a(new com.huawei.openalliance.ad.views.interfaces.b(PPSAppDetailView.this.q, true, AppStatus.INSTALLED == appDownloadButton.getStatus() ? "app" : ""));
                                    }
                                }

                                @Override // com.huawei.openalliance.ad.views.interfaces.x
                                public void a(AppDownloadButton appDownloadButton) {
                                    if (PPSAppDetailView.this.s != null) {
                                        PPSAppDetailView.this.s.a(new com.huawei.openalliance.ad.views.interfaces.b(PPSAppDetailView.this.q, false, AppStatus.INSTALLED == appDownloadButton.getStatus() ? "app" : ""));
                                    }
                                }
                            });
                        }
                        PPSAppDetailView pPSAppDetailView = PPSAppDetailView.this;
                        pPSAppDetailView.a(pPSAppDetailView.q);
                        if (!dd.a(PPSAppDetailView.this.o, PPSAppDetailView.this.p, rawX, rawY, PPSAppDetailView.this.n)) {
                            if (ho.a()) {
                                ho.a("PPSAppDetailView", "touch up download area x=%d, y=%d", Integer.valueOf(rawX), Integer.valueOf(rawY));
                            }
                            PPSAppDetailView.this.m.a(rawX, rawY, PPSAppDetailView.this.k);
                        }
                    }
                }
                return true;
            }
        };
        b(context, attributeSet);
    }

    public PPSAppDetailView(Context context) {
        super(context);
        this.h = false;
        this.o = 0;
        this.p = 0;
        this.q = true;
        this.r = true;
        this.u = false;
        this.c = 0;
        this.x = true;
        this.D = 0;
        this.G = -1L;
        this.H = 5;
        this.I = 5;
        this.J = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.huawei.openalliance.ad.views.PPSAppDetailView.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                if (!PPSAppDetailView.this.k()) {
                    return true;
                }
                PPSAppDetailView.this.c();
                return true;
            }
        });
        this.K = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSAppDetailView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PPSAppDetailView.this.q) {
                    PPSAppDetailView.this.b.onClick(null);
                    if (PPSAppDetailView.this.s != null) {
                        PPSAppDetailView.this.s.a(new com.huawei.openalliance.ad.views.interfaces.b(PPSAppDetailView.this.q, true, AppStatus.INSTALLED == PPSAppDetailView.this.b.getStatus() ? "app" : ""));
                    }
                } else if (PPSAppDetailView.this.s != null) {
                    PPSAppDetailView.this.s.a(new com.huawei.openalliance.ad.views.interfaces.b(false, false, PPSAppDetailView.this.getClickDestination()));
                } else {
                    ho.b("PPSAppDetailView", "onButtonClick, appDetailClickListener is null");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.L = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.PPSAppDetailView.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ho.b("PPSAppDetailView", "action: %s, viewId: %s", Integer.valueOf(motionEvent.getAction()), Integer.valueOf(view.getId()));
                if (PPSAppDetailView.this.b != null) {
                    int action = motionEvent.getAction();
                    if (action == 0) {
                        PPSAppDetailView.this.o = (int) motionEvent.getRawX();
                        PPSAppDetailView.this.p = (int) motionEvent.getRawY();
                    } else if (action == 1) {
                        int rawX = (int) motionEvent.getRawX();
                        int rawY = (int) motionEvent.getRawY();
                        if (PPSAppDetailView.this.q) {
                            PPSAppDetailView.this.b.setClickActionListener(new com.huawei.openalliance.ad.views.interfaces.x() { // from class: com.huawei.openalliance.ad.views.PPSAppDetailView.3.1
                                @Override // com.huawei.openalliance.ad.views.interfaces.x
                                public void b(AppDownloadButton appDownloadButton) {
                                    if (PPSAppDetailView.this.s != null) {
                                        PPSAppDetailView.this.s.a(new com.huawei.openalliance.ad.views.interfaces.b(PPSAppDetailView.this.q, true, AppStatus.INSTALLED == appDownloadButton.getStatus() ? "app" : ""));
                                    }
                                }

                                @Override // com.huawei.openalliance.ad.views.interfaces.x
                                public void a(AppDownloadButton appDownloadButton) {
                                    if (PPSAppDetailView.this.s != null) {
                                        PPSAppDetailView.this.s.a(new com.huawei.openalliance.ad.views.interfaces.b(PPSAppDetailView.this.q, false, AppStatus.INSTALLED == appDownloadButton.getStatus() ? "app" : ""));
                                    }
                                }
                            });
                        }
                        PPSAppDetailView pPSAppDetailView = PPSAppDetailView.this;
                        pPSAppDetailView.a(pPSAppDetailView.q);
                        if (!dd.a(PPSAppDetailView.this.o, PPSAppDetailView.this.p, rawX, rawY, PPSAppDetailView.this.n)) {
                            if (ho.a()) {
                                ho.a("PPSAppDetailView", "touch up download area x=%d, y=%d", Integer.valueOf(rawX), Integer.valueOf(rawY));
                            }
                            PPSAppDetailView.this.m.a(rawX, rawY, PPSAppDetailView.this.k);
                        }
                    }
                }
                return true;
            }
        };
        b(context, (AttributeSet) null);
    }
}
