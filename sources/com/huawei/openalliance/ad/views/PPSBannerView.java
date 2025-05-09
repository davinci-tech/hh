package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.ads.ChoicesView;
import com.huawei.openalliance.ad.activity.ComplianceActivity;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.ErrorCode;
import com.huawei.openalliance.ad.constant.NotifyMessageNames;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.gc;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.BannerSize;
import com.huawei.openalliance.ad.inter.data.BiddingInfo;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.inter.data.RewardVerifyConfig;
import com.huawei.openalliance.ad.inter.listeners.BannerAdListener;
import com.huawei.openalliance.ad.jh;
import com.huawei.openalliance.ad.ji;
import com.huawei.openalliance.ad.lm;
import com.huawei.openalliance.ad.mh;
import com.huawei.openalliance.ad.mi;
import com.huawei.openalliance.ad.mz;
import com.huawei.openalliance.ad.nw;
import com.huawei.openalliance.ad.oq;
import com.huawei.openalliance.ad.pd;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.views.PPSNativeView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class PPSBannerView extends RelativeLayout implements lm, com.huawei.openalliance.ad.views.interfaces.h {
    private static final byte[] B = new byte[0];
    private ImageView A;
    private float C;
    private b D;
    private a E;
    private int F;
    private List<String> G;
    private String H;
    private String I;
    private com.huawei.openalliance.ad.analysis.g J;
    private RequestOptions K;
    private Location L;
    private String M;
    private RewardVerifyConfig N;
    private TextView O;
    private int P;
    private jh Q;

    /* renamed from: a, reason: collision with root package name */
    Handler f7846a;
    private nw b;
    private long c;
    private long d;
    private String e;
    private BannerAdListener f;
    private BannerSize g;
    private ChoicesView h;
    private ImageView i;
    private com.huawei.hms.ads.a j;
    private boolean k;
    private PPSNativeView l;
    private PPSNativeView m;
    private ImageView n;
    private ImageView o;
    private LinearLayout p;
    private PPSLabelSourceView q;
    private PPSLabelView r;
    private TextView s;
    private AutoScaleSizeRelativeLayout t;
    private INativeAd u;
    private INativeAd v;
    private int w;
    private gc x;
    private boolean y;
    private String z;

    enum a {
        STARTED,
        PAUSED,
        RESUMED,
        DESTROYED
    }

    enum b {
        IDLE,
        LOADING
    }

    @Override // com.huawei.openalliance.ad.lm
    public View getOpenMeasureView() {
        return this;
    }

    public void setRewardVerifyConfig(RewardVerifyConfig rewardVerifyConfig) {
        this.N = rewardVerifyConfig;
    }

    public void setRequestOptions(RequestOptions requestOptions) {
        this.K = requestOptions;
    }

    public void setLocation(Location location) {
        this.L = location;
    }

    public void setContentBundle(String str) {
        this.M = str;
    }

    public void setBannerState(a aVar) {
        synchronized (B) {
            this.E = aVar;
        }
    }

    public void setBannerSize(BannerSize bannerSize) {
        this.g = bannerSize;
        setAdViewParam(getContext());
    }

    public void setBannerRefresh(long j) {
        long b2 = b(j);
        this.c = b2;
        ho.b("PPSBannerView", "setBannerRefresh:%s", Long.valueOf(b2));
    }

    public void setAdListener(BannerAdListener bannerAdListener) {
        this.f = bannerAdListener;
    }

    public void setAdId(String str) {
        this.e = str;
    }

    public void resume() {
        if (getBannerState() == a.DESTROYED) {
            ho.b("PPSBannerView", "hasDestroyed");
            return;
        }
        ho.b("PPSBannerView", "resume");
        setBannerState(a.RESUMED);
        i();
    }

    public void pause() {
        if (getBannerState() == a.DESTROYED) {
            ho.b("PPSBannerView", "hasDestroyed");
            return;
        }
        ho.b("PPSBannerView", VastAttribute.PAUSE);
        setBannerState(a.PAUSED);
        j();
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        jh jhVar = this.Q;
        if (jhVar != null) {
            jhVar.j();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        jh jhVar = this.Q;
        if (jhVar != null) {
            jhVar.i();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        jh jhVar = this.Q;
        if (jhVar != null) {
            jhVar.h();
        }
        oq.a(getContext()).b(getContext());
    }

    public void loadAd() {
        if (h()) {
            return;
        }
        if (!this.b.a()) {
            a(k(), 1, 1001);
            return;
        }
        if (getAdLoadState() != b.IDLE) {
            ho.c("PPSBannerView", "ad is loading now!");
            a(k(), 1, 701);
            return;
        }
        setAdLoadState(b.LOADING);
        ArrayList arrayList = new ArrayList();
        String str = this.H;
        if (str == null || str.isEmpty()) {
            arrayList = null;
        } else {
            arrayList.add(this.H);
        }
        ho.a("PPSBannerView", "load ad cacheContentIds is %s", this.H);
        this.b.a(this.L);
        this.b.a(this.K);
        this.b.b(this.M);
        this.b.a(Integer.valueOf(this.g.a()));
        this.b.b(Integer.valueOf(this.g.b()));
        this.b.a(this.e, 8, arrayList, this.c == 0 ? 0 : 1);
        i();
    }

    public RequestOptions getRequestOptions() {
        return this.K;
    }

    public Location getLocation() {
        return this.L;
    }

    public BiddingInfo getBiddingInfo() {
        INativeAd iNativeAd = this.u;
        if (iNativeAd == null || iNativeAd.getBiddingInfo() == null) {
            return null;
        }
        return this.u.getBiddingInfo();
    }

    public a getBannerState() {
        a aVar;
        synchronized (B) {
            aVar = this.E;
        }
        return aVar;
    }

    public BannerSize getBannerSize() {
        return this.g;
    }

    public long getBannerRefresh() {
        return this.c;
    }

    public String getAdId() {
        return this.e;
    }

    public void destroy() {
        ho.b("PPSBannerView", "destroy");
        setBannerState(a.DESTROYED);
        j();
        n();
        this.f7846a = null;
        this.b = null;
    }

    public void b() {
        PPSNativeView pPSNativeView = this.l;
        if (pPSNativeView != null) {
            pPSNativeView.e();
        }
        PPSNativeView pPSNativeView2 = this.m;
        if (pPSNativeView2 != null) {
            pPSNativeView2.e();
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.h
    public void a(List<String> list) {
        this.G = list;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.h
    public void a(Drawable drawable, INativeAd iNativeAd) {
        if (drawable == null || iNativeAd == null) {
            a(k(), 1, ErrorCode.ERROR_CODE_OTHER);
            ho.c("PPSBannerView", "onAdContentLoaded,content is null");
        } else {
            this.u = iNativeAd;
            this.z = iNativeAd.getLabel();
            this.H = iNativeAd.getContentId();
            if (0 == a(iNativeAd)) {
                b(iNativeAd);
                ho.a("PPSBannerView", "do not show ad due to ad expired");
                a(false, 1, 704);
                if (a(this.I, this.G)) {
                    a(2, this.v, (List<String>) null);
                }
            } else if (a(this.H, this.G)) {
                ho.a("PPSBannerView", "do not show ad due to ad cancelled");
                c(iNativeAd);
                a(false, 1, 705);
            } else {
                l();
                a(drawable);
                a(k(), 0, 0);
                m();
            }
            this.I = this.H;
            this.v = iNativeAd;
        }
        setAdLoadState(b.IDLE);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.h
    public void a(long j) {
        this.d = b(j);
        i();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.h
    public void a(int i) {
        ho.a("PPSBannerView", "onReqAdFail ");
        if (a(this.H, this.G)) {
            a(2, this.u, (List<String>) null);
            a(false, 1, 705);
        } else {
            a(k(), 1, i);
        }
        setAdLoadState(b.IDLE);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.h
    public void a() {
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSBannerView.9
            @Override // java.lang.Runnable
            public void run() {
                PPSBannerView.this.J.a(PPSBannerView.this.u instanceof com.huawei.openalliance.ad.inter.data.e ? ((com.huawei.openalliance.ad.inter.data.e) PPSBannerView.this.u).f() : "", PPSBannerView.this.e, 8, ErrorCode.ERROR_CODE_OTHER, (String) null, (String) null);
            }
        });
    }

    private void setChoiceViewPosition(int i) {
        ho.a("PPSBannerView", "bannerView option = %d", Integer.valueOf(i));
        if (this.h == null) {
            ho.a("PPSBannerView", "choicesView is null, error");
            return;
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.h.getLayoutParams());
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen._2131363351_res_0x7f0a0617);
        int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen._2131363351_res_0x7f0a0617);
        if (i != 0) {
            if (i == 2) {
                layoutParams.addRule(12);
                layoutParams.addRule(21);
                layoutParams.setMargins(0, 0, dimensionPixelOffset, dimensionPixelOffset2);
            } else if (i == 3) {
                layoutParams.addRule(12);
                layoutParams.addRule(20);
                layoutParams.setMargins(dimensionPixelOffset, 0, 0, dimensionPixelOffset2);
            } else if (i == 4) {
                this.h.setVisibility(8);
                this.h.setLayoutParams(layoutParams);
                this.h.bringToFront();
            } else {
                layoutParams.addRule(10);
                layoutParams.addRule(21);
                layoutParams.setMargins(0, dimensionPixelOffset2, dimensionPixelOffset, 0);
            }
            layoutParams.setMarginEnd(dimensionPixelOffset);
            this.h.setLayoutParams(layoutParams);
            this.h.bringToFront();
        }
        layoutParams.addRule(10);
        layoutParams.addRule(20);
        layoutParams.setMargins(dimensionPixelOffset, dimensionPixelOffset2, 0, 0);
        layoutParams.setMarginStart(dimensionPixelOffset);
        this.h.setLayoutParams(layoutParams);
        this.h.bringToFront();
    }

    private void setChildrenViewsInVisible(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                viewGroup.getChildAt(i).setVisibility(4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBannerVisibility(int i) {
        synchronized (B) {
            this.F = i;
        }
    }

    private void setAdViewParam(Context context) {
        AutoScaleSizeRelativeLayout autoScaleSizeRelativeLayout = this.t;
        if (autoScaleSizeRelativeLayout == null || this.g == null || context == null) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) autoScaleSizeRelativeLayout.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -2;
        this.t.setLayoutParams(layoutParams);
        this.t.setRatio(Float.valueOf((this.g.a() * 1.0f) / this.g.b()));
    }

    private void setAdLoadState(b bVar) {
        synchronized (B) {
            this.D = bVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        Handler handler = this.f7846a;
        if (handler == null || !handler.hasMessages(1001)) {
            return;
        }
        ho.a("PPSBannerView", "stopCloseAdWhenExpire");
        this.f7846a.removeMessages(1001);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (this.f7846a == null || this.u == null || k()) {
            return;
        }
        if (this.f7846a.hasMessages(1001)) {
            this.f7846a.removeMessages(1001);
        }
        ho.a("PPSBannerView", "start closeAdWhenExpire");
        this.f7846a.sendEmptyMessageDelayed(1001, a(this.u));
    }

    private void l() {
        ho.b("PPSBannerView", "hide activity");
        ji.a().a(NotifyMessageNames.FEEDBACK_RECEIVE, new Intent("com.huawei.ads.feedback.action.FINISH_FEEDBACK_ACTIVITY"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean k() {
        return this.c > 0 || this.d > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        Handler handler = this.f7846a;
        if (handler == null || !handler.hasMessages(1000)) {
            return;
        }
        ho.b("PPSBannerView", "stopRefreshAd");
        this.f7846a.removeMessages(1000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        long j = this.c;
        if (j == 0) {
            j = this.d;
        }
        c(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean h() {
        if (this.f7846a != null && this.b != null) {
            return false;
        }
        ho.b("PPSBannerView", "view has been destroyed, need re init");
        return true;
    }

    private int getBannerVisibility() {
        int i;
        synchronized (B) {
            i = this.F;
        }
        return i;
    }

    private b getAdLoadState() {
        b bVar;
        synchronized (B) {
            bVar = this.D;
        }
        return bVar;
    }

    private void g() {
        TextView textView;
        int i;
        String str = this.z;
        if (str == null || str.isEmpty()) {
            textView = this.s;
            i = 8;
        } else {
            this.s.setText(this.z);
            textView = this.s;
            i = 0;
        }
        textView.setVisibility(i);
    }

    private void f() {
        if (this.j == null) {
            com.huawei.hms.ads.a aVar = new com.huawei.hms.ads.a(getContext(), this.t);
            this.j = aVar;
            aVar.setOnCloseCallBack(new com.huawei.hms.ads.b() { // from class: com.huawei.openalliance.ad.views.PPSBannerView.7
                @Override // com.huawei.hms.ads.b
                public List<String> c() {
                    if (PPSBannerView.this.u == null) {
                        return null;
                    }
                    return PPSBannerView.this.u.getAdCloseKeyWords();
                }

                @Override // com.huawei.hms.ads.b
                public void b() {
                    if (PPSBannerView.this.u instanceof com.huawei.openalliance.ad.inter.data.e) {
                        com.huawei.openalliance.ad.utils.d.a(PPSBannerView.this.getContext(), (com.huawei.openalliance.ad.inter.data.e) PPSBannerView.this.u);
                    }
                }

                @Override // com.huawei.hms.ads.b
                public void a(String str) {
                    if (PPSBannerView.this.l != null) {
                        PPSBannerView.this.l.setVisibility(8);
                    }
                    if (PPSBannerView.this.m != null) {
                        PPSBannerView.this.m.setVisibility(8);
                    }
                    ArrayList arrayList = new ArrayList();
                    if (str == null || str.isEmpty()) {
                        arrayList = null;
                    } else {
                        arrayList.add(str);
                    }
                    PPSBannerView pPSBannerView = PPSBannerView.this;
                    pPSBannerView.a(0, pPSBannerView.u, arrayList);
                    PPSBannerView pPSBannerView2 = PPSBannerView.this;
                    pPSBannerView2.a(pPSBannerView2.k(), 2, 0);
                }

                @Override // com.huawei.hms.ads.b
                public void a() {
                    if (PPSBannerView.this.l != null) {
                        PPSBannerView.this.l.setVisibility(8);
                    }
                    if (PPSBannerView.this.m != null) {
                        PPSBannerView.this.m.setVisibility(8);
                    }
                }
            });
            AutoScaleSizeRelativeLayout autoScaleSizeRelativeLayout = this.t;
            if (autoScaleSizeRelativeLayout != null) {
                autoScaleSizeRelativeLayout.addView(this.j);
            }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.j.getLayoutParams());
            layoutParams.addRule(13);
            this.j.setLayoutParams(layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        com.huawei.hms.ads.a aVar = this.j;
        if (aVar != null) {
            ViewGroup viewGroup = (ViewGroup) aVar.getParent();
            if (viewGroup != null && (viewGroup instanceof ViewGroup)) {
                setChildrenViewsInVisible(viewGroup);
            }
            this.j.setVisibility(0);
        }
        AutoScaleSizeRelativeLayout autoScaleSizeRelativeLayout = this.t;
        if (autoScaleSizeRelativeLayout != null) {
            autoScaleSizeRelativeLayout.setBackgroundColor(getResources().getColor(R.color._2131298015_res_0x7f0906df));
        }
    }

    private void d() {
        ho.a("PPSBannerView", "initChoicesView start");
        if (this.h == null) {
            ChoicesView choicesView = new ChoicesView(getContext());
            this.h = choicesView;
            choicesView.setId(R.id.hiad_choice_view);
            this.t.addView(this.h);
        }
        this.h.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSBannerView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PPSBannerView.this.j != null) {
                    PPSBannerView.this.e();
                    PPSBannerView.this.j.b();
                } else if (PPSBannerView.this.u instanceof com.huawei.openalliance.ad.inter.data.e) {
                    com.huawei.openalliance.ad.utils.d.a(PPSBannerView.this.getContext(), (com.huawei.openalliance.ad.inter.data.e) PPSBannerView.this.u);
                }
                PPSBannerView.this.h.setVisibility(8);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (BannerSize.BANNER == getBannerSize()) {
            this.h.a();
            this.h.a(R.dimen._2131363352_res_0x7f0a0618);
        }
    }

    private ContentRecord d(INativeAd iNativeAd) {
        String str;
        if (iNativeAd == null) {
            str = "nativeAd is null when convert";
        } else {
            if (iNativeAd instanceof com.huawei.openalliance.ad.inter.data.e) {
                return pd.a((com.huawei.openalliance.ad.inter.data.e) iNativeAd);
            }
            str = "ad is not native ad when convert";
        }
        ho.c("PPSBannerView", str);
        return null;
    }

    private void c(INativeAd iNativeAd) {
        if (this.J == null || iNativeAd == null) {
            return;
        }
        ho.a("PPSBannerView", "reportAdCancelled");
        this.J.a("49", d(iNativeAd), 0L);
    }

    private void c(long j) {
        Handler handler = this.f7846a;
        if (handler == null) {
            return;
        }
        if (handler.hasMessages(1000)) {
            this.f7846a.removeMessages(1000);
        }
        if (getBannerVisibility() == 4 || getBannerState() == a.PAUSED || getBannerState() == a.DESTROYED) {
            ho.b("PPSBannerView", "stopRefreshAd");
        } else if (0 != j) {
            ho.b("PPSBannerView", "start refreshAd ad will be refreshed in %d s", Long.valueOf(j));
            this.f7846a.sendEmptyMessageDelayed(1000, j * 1000);
        }
    }

    private void c() {
        if (this.A == null) {
            return;
        }
        ho.b("PPSBannerView", "init compliance activity");
        this.A.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSBannerView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PPSBannerView.this.u != null && (PPSBannerView.this.u instanceof com.huawei.openalliance.ad.inter.data.e)) {
                    ComplianceActivity.a(PPSBannerView.this.getContext(), view, pd.a((com.huawei.openalliance.ad.inter.data.e) PPSBannerView.this.u), false);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void b(PPSNativeView pPSNativeView) {
        mi adSessionAgent = pPSNativeView.getAdSessionAgent();
        if (adSessionAgent != null) {
            adSessionAgent.a(this.i, mh.CLOSE_AD, null);
            adSessionAgent.a(this.r, mh.OTHER, null);
            adSessionAgent.a(this.s, mh.OTHER, null);
            adSessionAgent.a(this.O, mh.OTHER, null);
            adSessionAgent.a(this.h, mh.OTHER, null);
            adSessionAgent.a(this.j, mh.OTHER, null);
        }
    }

    private void b(INativeAd iNativeAd) {
        if (this.J == null || iNativeAd == null) {
            return;
        }
        ho.a("PPSBannerView", "reportAdExpire");
        this.J.a("48", d(iNativeAd), iNativeAd.getEndTime());
    }

    private void b(Context context) {
        View.inflate(context, R.layout.hiad_view_banner_ad, this);
        this.l = (PPSNativeView) findViewById(R.id.hiad_banner_layout_1);
        this.m = (PPSNativeView) findViewById(R.id.hiad_banner_layout_2);
        this.n = (ImageView) findViewById(R.id.hiad_banner_image_1);
        this.o = (ImageView) findViewById(R.id.hiad_banner_image_2);
        this.p = (LinearLayout) findViewById(R.id.custom_ad_bg_layout_container);
        this.q = (PPSLabelSourceView) findViewById(R.id.custom_ad_bg_layout);
        this.A = (ImageView) findViewById(R.id.compliance_icon_banner);
        this.t = (AutoScaleSizeRelativeLayout) findViewById(R.id.hiad_banner_ad);
        setAdViewParam(context);
        this.t.setVisibility(8);
        this.k = bz.a(context).d();
        ho.b("PPSBannerView", "isChinaRom = " + this.k);
        if (this.k) {
            ImageView imageView = (ImageView) findViewById(R.id.hiad_banner_close_button);
            this.i = imageView;
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSBannerView.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    PPSBannerView pPSBannerView = PPSBannerView.this;
                    pPSBannerView.a(0, pPSBannerView.u, (List<String>) null);
                    PPSBannerView pPSBannerView2 = PPSBannerView.this;
                    pPSBannerView2.a(pPSBannerView2.k(), 2, 0);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        } else {
            f();
            d();
            c();
        }
        a(this.l);
        a(this.m);
    }

    private long b(long j) {
        gc gcVar;
        if (0 == j || (gcVar = this.x) == null) {
            return 0L;
        }
        long bp = gcVar.bp();
        long br = this.x.br();
        if (ho.a()) {
            ho.a("PPSBannerView", "setBannerRefresh,minInterval:%d,maxInterval:%d", Long.valueOf(bp), Long.valueOf(br));
        }
        if (bp > br) {
            return 0L;
        }
        return j < bp ? bp : Math.min(j, br);
    }

    private boolean a(String str, List<String> list) {
        if (TextUtils.isEmpty(str) || list == null || list.isEmpty()) {
            return false;
        }
        ho.a("PPSBannerView", "invalidcontentIds is %s", list);
        ho.a("PPSBannerView", "currentContentId is %s", str);
        return list.contains(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, int i, int i2) {
        ho.a("PPSBannerView", "notifyResult isRefreshAd:%s,resultType:%d", Boolean.valueOf(z), Integer.valueOf(i));
        if (!z) {
            j();
            a(i, i2);
        } else if (2 == i) {
            a(i, i2);
        } else if (this.y) {
            a(i, i2);
            this.y = false;
        }
    }

    private void a(String str) {
        if (!this.k || TextUtils.isEmpty(str)) {
            this.O.setVisibility(8);
            g();
        } else {
            this.O.setText(str);
            this.s.setVisibility(8);
            this.O.setVisibility(0);
        }
    }

    private void a(final PPSNativeView pPSNativeView) {
        pPSNativeView.setOnNativeAdImpressionListener(new PPSNativeView.b() { // from class: com.huawei.openalliance.ad.views.PPSBannerView.4
            @Override // com.huawei.openalliance.ad.views.PPSNativeView.b
            public void a() {
                if (PPSBannerView.this.h()) {
                    return;
                }
                pPSNativeView.setAdContainerSizeMatched(PPSBannerView.this.b.a(PPSBannerView.this.g, PPSBannerView.this.C) ? "1" : "0");
            }
        });
    }

    private void a(AttributeSet attributeSet) {
        StringBuilder sb;
        BannerSize bannerSize;
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100149_res_0x7f0601f5, R.attr._2131100150_res_0x7f0601f6});
        if (obtainStyledAttributes != null) {
            try {
                try {
                    String string = obtainStyledAttributes.getString(0);
                    if (string != null && !string.isEmpty()) {
                        this.e = string;
                    }
                    String string2 = obtainStyledAttributes.getString(1);
                    if (string2 != null && !string2.isEmpty()) {
                        if (string2.equals(BannerSize.BANNER_STR)) {
                            bannerSize = BannerSize.BANNER;
                        } else if (string2.equals(BannerSize.LARGE_BANNER_STR)) {
                            bannerSize = BannerSize.LARGE_BANNER;
                        }
                        this.g = bannerSize;
                    }
                } catch (RuntimeException e) {
                    sb = new StringBuilder("initDefAttr ");
                    sb.append(e.getClass().getSimpleName());
                    ho.c("PPSBannerView", sb.toString());
                    obtainStyledAttributes.recycle();
                } catch (Throwable th) {
                    sb = new StringBuilder("initDefAttr ");
                    sb.append(th.getClass().getSimpleName());
                    ho.c("PPSBannerView", sb.toString());
                    obtainStyledAttributes.recycle();
                }
                obtainStyledAttributes.recycle();
            } catch (Throwable th2) {
                obtainStyledAttributes.recycle();
                throw th2;
            }
        }
    }

    private void a(Drawable drawable, final ImageView imageView) {
        if (drawable instanceof com.huawei.openalliance.ad.views.gif.b) {
            ((com.huawei.openalliance.ad.views.gif.b) drawable).a(new com.huawei.openalliance.ad.views.gif.d() { // from class: com.huawei.openalliance.ad.views.PPSBannerView.8
                @Override // com.huawei.openalliance.ad.views.gif.d
                public void b() {
                }

                @Override // com.huawei.openalliance.ad.views.gif.d
                public void c() {
                }

                @Override // com.huawei.openalliance.ad.views.gif.d
                public void a() {
                    imageView.requestLayout();
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x016c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(android.graphics.drawable.Drawable r7) {
        /*
            Method dump skipped, instructions count: 434
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.views.PPSBannerView.a(android.graphics.drawable.Drawable):void");
    }

    private void a(Context context) {
        this.b = new mz(context, this);
        gc b2 = fh.b(context);
        this.x = b2;
        this.C = b2.bx();
        this.J = new com.huawei.openalliance.ad.analysis.g(context);
        this.P = ao.a(getContext(), getResources().getDimension(R.dimen._2131363291_res_0x7f0a05db));
        b(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x002a, code lost:
    
        r4.onClose(r6);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(int r4, com.huawei.openalliance.ad.inter.data.INativeAd r5, java.util.List<java.lang.String> r6) {
        /*
            r3 = this;
            com.huawei.openalliance.ad.views.AutoScaleSizeRelativeLayout r0 = r3.t
            if (r0 == 0) goto L49
            r1 = 2
            r2 = 1
            if (r4 == 0) goto L1b
            if (r4 == r2) goto L11
            if (r4 == r1) goto Ld
            goto L2d
        Ld:
            r3.c(r5)
            goto L2d
        L11:
            int r4 = r0.getVisibility()
            if (r4 != 0) goto L2d
            r3.b(r5)
            goto L2d
        L1b:
            int r4 = r3.w
            int r4 = r4 - r2
            int r4 = r4 % r1
            if (r4 != 0) goto L26
            com.huawei.openalliance.ad.views.PPSNativeView r4 = r3.l
            if (r4 == 0) goto L2d
            goto L2a
        L26:
            com.huawei.openalliance.ad.views.PPSNativeView r4 = r3.m
            if (r4 == 0) goto L2d
        L2a:
            r4.onClose(r6)
        L2d:
            com.huawei.openalliance.ad.views.PPSNativeView r4 = r3.l
            r5 = 8
            if (r4 == 0) goto L36
            r4.setVisibility(r5)
        L36:
            com.huawei.openalliance.ad.views.PPSNativeView r4 = r3.m
            if (r4 == 0) goto L3d
            r4.setVisibility(r5)
        L3d:
            com.huawei.openalliance.ad.views.AutoScaleSizeRelativeLayout r4 = r3.t
            r4.setVisibility(r5)
            com.huawei.openalliance.ad.jh r4 = r3.Q
            if (r4 == 0) goto L49
            r4.onGlobalLayout()
        L49:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.views.PPSBannerView.a(int, com.huawei.openalliance.ad.inter.data.INativeAd, java.util.List):void");
    }

    private void a(int i, int i2) {
        BannerAdListener bannerAdListener = this.f;
        if (bannerAdListener == null) {
            return;
        }
        if (i == 0) {
            bannerAdListener.onAdLoaded();
        } else if (i == 1) {
            bannerAdListener.onAdFailedToLoad(i2);
        } else {
            if (i != 2) {
                return;
            }
            bannerAdListener.onAdClosed();
        }
    }

    private long a(INativeAd iNativeAd) {
        if (iNativeAd != null) {
            long currentTimeMillis = System.currentTimeMillis();
            long endTime = iNativeAd.getEndTime();
            r0 = currentTimeMillis < endTime ? endTime - currentTimeMillis : 0L;
            ho.a("PPSBannerView", "calcAdLeftTime,currentTime:" + currentTimeMillis + ",expireTime:" + endTime + ",leftTime:" + r0);
        }
        return r0;
    }

    public PPSBannerView(Context context, BannerSize bannerSize, String str) {
        super(context);
        this.g = BannerSize.BANNER;
        this.k = true;
        this.w = 0;
        this.y = true;
        this.C = 0.05f;
        this.D = b.IDLE;
        this.E = a.STARTED;
        this.F = 0;
        this.Q = new jh(this) { // from class: com.huawei.openalliance.ad.views.PPSBannerView.1
            @Override // com.huawei.openalliance.ad.jh
            public void a(long j, int i) {
                ho.a("PPSBannerView", "onViewShowEnd");
                PPSBannerView.this.setBannerVisibility(4);
                PPSBannerView.this.j();
                PPSBannerView.this.n();
            }

            @Override // com.huawei.openalliance.ad.jh
            public void a() {
                ho.a("PPSBannerView", "onViewShowStart");
                PPSBannerView.this.setBannerVisibility(0);
                PPSBannerView.this.i();
                PPSBannerView.this.m();
            }
        };
        this.f7846a = new Handler(Looper.myLooper()) { // from class: com.huawei.openalliance.ad.views.PPSBannerView.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1000) {
                    PPSBannerView.this.loadAd();
                } else {
                    if (i != 1001) {
                        return;
                    }
                    PPSBannerView pPSBannerView = PPSBannerView.this;
                    pPSBannerView.a(1, pPSBannerView.u, (List<String>) null);
                }
            }
        };
        this.g = bannerSize;
        this.e = str;
        a(context);
    }

    public PPSBannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = BannerSize.BANNER;
        this.k = true;
        this.w = 0;
        this.y = true;
        this.C = 0.05f;
        this.D = b.IDLE;
        this.E = a.STARTED;
        this.F = 0;
        this.Q = new jh(this) { // from class: com.huawei.openalliance.ad.views.PPSBannerView.1
            @Override // com.huawei.openalliance.ad.jh
            public void a(long j, int i2) {
                ho.a("PPSBannerView", "onViewShowEnd");
                PPSBannerView.this.setBannerVisibility(4);
                PPSBannerView.this.j();
                PPSBannerView.this.n();
            }

            @Override // com.huawei.openalliance.ad.jh
            public void a() {
                ho.a("PPSBannerView", "onViewShowStart");
                PPSBannerView.this.setBannerVisibility(0);
                PPSBannerView.this.i();
                PPSBannerView.this.m();
            }
        };
        this.f7846a = new Handler(Looper.myLooper()) { // from class: com.huawei.openalliance.ad.views.PPSBannerView.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 1000) {
                    PPSBannerView.this.loadAd();
                } else {
                    if (i2 != 1001) {
                        return;
                    }
                    PPSBannerView pPSBannerView = PPSBannerView.this;
                    pPSBannerView.a(1, pPSBannerView.u, (List<String>) null);
                }
            }
        };
        a(attributeSet);
        a(context);
    }

    public PPSBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = BannerSize.BANNER;
        this.k = true;
        this.w = 0;
        this.y = true;
        this.C = 0.05f;
        this.D = b.IDLE;
        this.E = a.STARTED;
        this.F = 0;
        this.Q = new jh(this) { // from class: com.huawei.openalliance.ad.views.PPSBannerView.1
            @Override // com.huawei.openalliance.ad.jh
            public void a(long j, int i2) {
                ho.a("PPSBannerView", "onViewShowEnd");
                PPSBannerView.this.setBannerVisibility(4);
                PPSBannerView.this.j();
                PPSBannerView.this.n();
            }

            @Override // com.huawei.openalliance.ad.jh
            public void a() {
                ho.a("PPSBannerView", "onViewShowStart");
                PPSBannerView.this.setBannerVisibility(0);
                PPSBannerView.this.i();
                PPSBannerView.this.m();
            }
        };
        this.f7846a = new Handler(Looper.myLooper()) { // from class: com.huawei.openalliance.ad.views.PPSBannerView.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 1000) {
                    PPSBannerView.this.loadAd();
                } else {
                    if (i2 != 1001) {
                        return;
                    }
                    PPSBannerView pPSBannerView = PPSBannerView.this;
                    pPSBannerView.a(1, pPSBannerView.u, (List<String>) null);
                }
            }
        };
        a(attributeSet);
        a(context);
    }

    public PPSBannerView(Context context) {
        super(context);
        this.g = BannerSize.BANNER;
        this.k = true;
        this.w = 0;
        this.y = true;
        this.C = 0.05f;
        this.D = b.IDLE;
        this.E = a.STARTED;
        this.F = 0;
        this.Q = new jh(this) { // from class: com.huawei.openalliance.ad.views.PPSBannerView.1
            @Override // com.huawei.openalliance.ad.jh
            public void a(long j, int i2) {
                ho.a("PPSBannerView", "onViewShowEnd");
                PPSBannerView.this.setBannerVisibility(4);
                PPSBannerView.this.j();
                PPSBannerView.this.n();
            }

            @Override // com.huawei.openalliance.ad.jh
            public void a() {
                ho.a("PPSBannerView", "onViewShowStart");
                PPSBannerView.this.setBannerVisibility(0);
                PPSBannerView.this.i();
                PPSBannerView.this.m();
            }
        };
        this.f7846a = new Handler(Looper.myLooper()) { // from class: com.huawei.openalliance.ad.views.PPSBannerView.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 1000) {
                    PPSBannerView.this.loadAd();
                } else {
                    if (i2 != 1001) {
                        return;
                    }
                    PPSBannerView pPSBannerView = PPSBannerView.this;
                    pPSBannerView.a(1, pPSBannerView.u, (List<String>) null);
                }
            }
        };
        a(context);
    }
}
