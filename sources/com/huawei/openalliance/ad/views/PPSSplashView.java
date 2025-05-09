package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.DisplayCutout;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.WindowInsets;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.ads.uiengine.common.MediaStateApi;
import com.huawei.openalliance.ad.beans.metadata.InteractCfg;
import com.huawei.openalliance.ad.beans.metadata.v3.Asset;
import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.co;
import com.huawei.openalliance.ad.constant.AdLoadMode;
import com.huawei.openalliance.ad.constant.AdLoadState;
import com.huawei.openalliance.ad.constant.ApiNames;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.dc;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.gc;
import com.huawei.openalliance.ad.hl;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.inter.HiAdSplash;
import com.huawei.openalliance.ad.inter.IHiAdSplash;
import com.huawei.openalliance.ad.inter.c;
import com.huawei.openalliance.ad.inter.data.BiddingInfo;
import com.huawei.openalliance.ad.inter.data.RewardVerifyConfig;
import com.huawei.openalliance.ad.inter.listeners.AdActionListener;
import com.huawei.openalliance.ad.inter.listeners.AdListener;
import com.huawei.openalliance.ad.inter.listeners.AdShowListener;
import com.huawei.openalliance.ad.ix;
import com.huawei.openalliance.ad.iy;
import com.huawei.openalliance.ad.ja;
import com.huawei.openalliance.ad.jb;
import com.huawei.openalliance.ad.jm;
import com.huawei.openalliance.ad.jn;
import com.huawei.openalliance.ad.jo;
import com.huawei.openalliance.ad.jp;
import com.huawei.openalliance.ad.jr;
import com.huawei.openalliance.ad.nb;
import com.huawei.openalliance.ad.nu;
import com.huawei.openalliance.ad.oh;
import com.huawei.openalliance.ad.os;
import com.huawei.openalliance.ad.ou;
import com.huawei.openalliance.ad.si;
import com.huawei.openalliance.ad.ti;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.bi;
import com.huawei.openalliance.ad.utils.cv;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes5.dex */
public class PPSSplashView extends RelativeLayout implements IViewLifeCycle, com.huawei.openalliance.ad.views.interfaces.l {
    private View A;
    private boolean B;
    private long C;
    private int D;
    private final String E;
    private int F;
    private RewardVerifyConfig G;
    private PPSSplashProView H;
    private PPSSplashSwipeView I;
    private PPSSplashTwistView J;
    private PPSSplashSwipeClickView K;
    private PPSSplashTwistClickView L;
    private b M;
    private ContentRecord N;
    private jp O;
    private ou P;

    /* renamed from: a, reason: collision with root package name */
    ad f7978a;
    RelativeLayout b;
    RelativeLayout c;
    u d;
    protected gc e;
    private AdSlotParam f;
    private View g;
    private int h;
    private PPSWLSView i;
    private PPSSplashAdSourceView j;
    private jb k;
    private oh l;
    private AdListener m;
    private AdActionListener n;
    private AdShowListener o;
    private boolean p;
    private int q;
    private View r;
    private com.huawei.openalliance.ad.views.interfaces.n s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    private boolean c(int i) {
        return 2 == i || 3 == i;
    }

    private boolean d(int i) {
        return 1 == i || 4 == i;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public int getAdType() {
        return 1;
    }

    @Override // com.huawei.openalliance.ad.lm
    public View getOpenMeasureView() {
        return this;
    }

    public void setStartMaxVol(float f) {
    }

    public void startShowAd() {
        ja jaVar;
        ho.b("PPSSplashView", "startShowAd. ");
        jb jbVar = this.k;
        if (jbVar instanceof ja) {
            jaVar = (ja) jbVar;
            if (jaVar.C()) {
                ho.b("PPSSplashView", "startShowAd, adHadShown.");
                return;
            }
        } else {
            jaVar = null;
        }
        ContentRecord g = dc.g();
        if (g == null || TextUtils.isEmpty(g.y())) {
            return;
        }
        ho.b("PPSSplashView", "startShowAd, find normal ad. ");
        if (this.k == null) {
            ho.c("PPSSplashView", "startShowAd, adMediator is null, can't show");
            return;
        }
        if (jaVar != null) {
            jaVar.b(true);
        }
        boolean b2 = this.k.b(g);
        this.k.e(1201);
        if (ho.a()) {
            ho.a("PPSSplashView", "startShowAd, showResult: %s", Boolean.valueOf(b2));
        }
        dc.a((ContentRecord) null);
    }

    public void setWideSloganResId(int i) {
        ad adVar = this.f7978a;
        if (adVar != null) {
            adVar.setWideSloganResId(i);
        } else {
            this.u = i;
        }
    }

    public void setSloganView(View view) {
        if (view != null) {
            this.A = view;
            view.setVisibility(8);
        }
    }

    public void setSloganResId(int i) {
        if (ao.b(getContext())) {
            if (dd.c(getContext())) {
                ho.c("PPSSplashView", "setSloganResId - activity finished, not add view");
                return;
            }
            if (this.f == null) {
                throw new com.huawei.openalliance.ad.exception.b("Must invoke SplashAdView's setAdSlotParam method before invoke setSloganResId method");
            }
            if (this.f7978a == null) {
                ad adVar = new ad(getContext(), this.f.b(), i, 1);
                this.f7978a = adVar;
                int i2 = this.u;
                if (i2 > 0) {
                    adVar.setWideSloganResId(i2);
                }
                this.b.addView(this.f7978a, new RelativeLayout.LayoutParams(-1, -1));
                this.f7978a.b();
            }
        }
    }

    public void setRewardVerifyConfig(RewardVerifyConfig rewardVerifyConfig) {
        this.G = rewardVerifyConfig;
    }

    public void setMediaNameResId(int i) {
        this.t = i;
    }

    public void setLogoResId(int i) {
        this.q = i;
    }

    public void setLogo(View view, int i) {
        this.g = view;
        view.setVisibility(i);
        this.h = i;
    }

    public void setLogo(View view) {
        setLogo(view, 8);
    }

    public void setLinkedSupportMode(int i) {
        this.z = i;
    }

    public void setAudioFocusType(int i) {
        this.v = i;
        com.huawei.openalliance.ad.views.interfaces.n nVar = this.s;
        if (nVar != null) {
            nVar.setAudioFocusType(i);
        }
    }

    public void setAdSlotParam(AdSlotParam adSlotParam) {
        if (ao.b(getContext())) {
            int a2 = com.huawei.openalliance.ad.utils.d.a(getContext(), adSlotParam.b());
            int b2 = com.huawei.openalliance.ad.utils.d.b(getContext(), adSlotParam.b());
            adSlotParam.b(a2);
            adSlotParam.c(b2);
            adSlotParam.a(this.F);
            adSlotParam.b(Integer.valueOf(this.z));
            adSlotParam.c((Integer) 0);
            adSlotParam.d(Integer.valueOf((HiAd.a(getContext()).e() && com.huawei.openalliance.ad.utils.d.t(getContext())) ? 0 : 1));
            this.f = adSlotParam;
            IHiAdSplash hiAdSplash = HiAdSplash.getInstance(getContext());
            if (hiAdSplash instanceof HiAdSplash) {
                ((HiAdSplash) hiAdSplash).a(adSlotParam);
            }
        }
    }

    public void setAdShowListener(AdShowListener adShowListener) {
        this.o = adShowListener;
        jb jbVar = this.k;
        if (jbVar != null) {
            jbVar.a(adShowListener);
        }
    }

    public void setAdListener(AdListener adListener) {
        this.m = adListener;
        this.l.a(adListener);
        jb jbVar = this.k;
        if (jbVar != null) {
            jbVar.a(adListener);
        }
    }

    public void setAdActionListener(AdActionListener adActionListener) {
        this.n = adActionListener;
        jb jbVar = this.k;
        if (jbVar != null) {
            jbVar.a(adActionListener);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void resumeView() {
        ho.b("PPSSplashView", "resumeView ");
        com.huawei.openalliance.ad.views.interfaces.n nVar = this.s;
        if (nVar != null) {
            nVar.resumeView();
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void pauseView() {
        ho.b("PPSSplashView", "pauseView ");
        com.huawei.openalliance.ad.views.interfaces.n nVar = this.s;
        if (nVar != null) {
            nVar.pauseView();
        }
        PPSSplashProView pPSSplashProView = this.H;
        if (pPSSplashProView != null) {
            pPSSplashProView.a();
        }
        PPSSplashSwipeView pPSSplashSwipeView = this.I;
        if (pPSSplashSwipeView != null) {
            pPSSplashSwipeView.b();
        }
        PPSSplashSwipeClickView pPSSplashSwipeClickView = this.K;
        if (pPSSplashSwipeClickView != null) {
            pPSSplashSwipeClickView.b();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cv.a(this.E);
        PPSSplashProView pPSSplashProView = this.H;
        if (pPSSplashProView != null) {
            pPSSplashProView.a();
        }
        PPSSplashSwipeView pPSSplashSwipeView = this.I;
        if (pPSSplashSwipeView != null) {
            pPSSplashSwipeView.b();
        }
        PPSSplashSwipeClickView pPSSplashSwipeClickView = this.K;
        if (pPSSplashSwipeClickView != null) {
            pPSSplashSwipeClickView.b();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ho.b("PPSSplashView", "onAttachedToWindow");
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        String str;
        ho.b("PPSSplashView", "onApplyWindowInsets, sdk: %s", Integer.valueOf(Build.VERSION.SDK_INT));
        if (dd.b() && windowInsets != null) {
            DisplayCutout displayCutout = windowInsets.getDisplayCutout();
            if (displayCutout != null) {
                List<Rect> boundingRects = displayCutout.getBoundingRects();
                StringBuilder sb = new StringBuilder("boundingRects:");
                sb.append(boundingRects == null);
                ho.b("PPSSplashView", sb.toString());
                if (!bg.a(boundingRects)) {
                    this.w = boundingRects.get(0).height();
                }
                this.x = displayCutout.getSafeInsetLeft();
                ho.b("PPSSplashView", "notchHeight left:" + this.x);
                this.y = displayCutout.getSafeInsetRight();
                str = "notchHeight right:" + this.y;
            } else {
                str = "DisplayCutout is null";
            }
            ho.b("PPSSplashView", str);
        }
        if (this.w <= 0 && bz.a(getContext()).a(getContext())) {
            this.w = Math.max(this.w, bz.a(getContext()).a(this));
        }
        ho.b("PPSSplashView", "notchHeight:" + this.w);
        return super.onApplyWindowInsets(windowInsets);
    }

    public void loadAd() {
        AdSlotParam adSlotParam;
        this.C = System.currentTimeMillis();
        ho.b("PPSSplashView", ApiNames.LOAD_AD);
        if (this.l.b()) {
            if (this.l.c() && (adSlotParam = this.f) != null) {
                Integer r = adSlotParam.r();
                boolean a2 = com.huawei.openalliance.ad.inter.c.a(getContext()).a();
                ho.b("PPSSplashView", "startMode: %s, isExSplashStart: %s", r, Boolean.valueOf(a2));
                if ((r != null && r.intValue() == 0) || (r.intValue() == 1 && a2)) {
                    d();
                    return;
                }
            }
            if (this.f != null) {
                bi.b(getContext().getApplicationContext(), this.f.l());
            }
            this.p = true;
            this.l.a();
        }
    }

    public boolean isLoading() {
        jb jbVar = this.k;
        return jbVar == null ? this.p : jbVar.b() == AdLoadState.LOADING;
    }

    public boolean isLoaded() {
        jb jbVar = this.k;
        return jbVar != null && jbVar.b() == AdLoadState.LOADED;
    }

    public String getUniqueId() {
        jb jbVar = this.k;
        if (jbVar != null) {
            return jbVar.w();
        }
        return null;
    }

    public View getSloganView() {
        return this.A;
    }

    public RewardVerifyConfig getRewardVerifyConfig() {
        return this.G;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public ou getMonitorEp() {
        if (this.O instanceof jo) {
            return null;
        }
        return this.P;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public MediaStateApi getMediaProxy() {
        jp jpVar = this.O;
        if (jpVar instanceof jr) {
            return new jm((jr) jpVar);
        }
        return null;
    }

    public int getMediaNameResId() {
        return this.t;
    }

    public int getLogoResId() {
        return this.q;
    }

    public View getLogo() {
        return this.g;
    }

    public BiddingInfo getBiddingInfo() {
        jb jbVar = this.k;
        if (jbVar != null) {
            return jbVar.x();
        }
        return null;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public int getAudioFocusType() {
        return this.v;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public AdSlotParam getAdSlotParam() {
        AdSlotParam adSlotParam = this.f;
        if (adSlotParam != null) {
            adSlotParam.d(1);
        }
        return this.f;
    }

    public jb getAdMediator() {
        return this.k;
    }

    public AdListener getAdListener() {
        return this.m;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void destroyView() {
        ho.b("PPSSplashView", "destroyView ");
        com.huawei.openalliance.ad.views.interfaces.n nVar = this.s;
        if (nVar != null) {
            nVar.destroyView();
        }
        try {
            PPSSplashProView pPSSplashProView = this.H;
            if (pPSSplashProView != null) {
                pPSSplashProView.a();
            }
            com.huawei.openalliance.ad.inter.c.a(getContext().getApplicationContext()).b(this.M);
            com.huawei.openalliance.ad.inter.c.a(getContext().getApplicationContext()).a(false);
            PPSSplashSwipeView pPSSplashSwipeView = this.I;
            if (pPSSplashSwipeView != null) {
                pPSSplashSwipeView.b();
            }
            PPSSplashSwipeClickView pPSSplashSwipeClickView = this.K;
            if (pPSSplashSwipeClickView != null) {
                pPSSplashSwipeClickView.b();
            }
            RelativeLayout relativeLayout = this.b;
            if (relativeLayout != null) {
                relativeLayout.removeAllViews();
            }
        } catch (Throwable th) {
            ho.b("PPSSplashView", "destroy err: %s", th.getClass().getSimpleName());
        }
        this.p = false;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public void c() {
        ad adVar = this.f7978a;
        if (adVar != null) {
            adVar.b();
        }
        View view = this.A;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public void b(int i) {
        u uVar = this.d;
        if (uVar != null) {
            uVar.a(i);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public void b() {
        jp jpVar = this.O;
        if (jpVar != null) {
            jpVar.b();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public void a(com.huawei.openalliance.ad.views.interfaces.n nVar, ContentRecord contentRecord) {
        Integer a2 = a(contentRecord);
        if (dd.c(getContext())) {
            ho.c("PPSSplashView", "showAdView - activity finished, not add view");
            return;
        }
        if (nVar == 0 || !(nVar instanceof View)) {
            return;
        }
        View view = (View) nVar;
        this.s = nVar;
        a(false, view, contentRecord);
        ho.b("PPSSplashView", "showAdView %s,", contentRecord.toString());
        ViewParent parent = view.getParent();
        if (parent == this.b) {
            view.setVisibility(0);
            return;
        }
        if (parent != null && (parent instanceof ViewGroup)) {
            ho.b("PPSSplashView", "showAdView, remove adView.");
            ((ViewGroup) parent).removeView(view);
        } else if (parent != null) {
            return;
        }
        this.b.addView(view, new RelativeLayout.LayoutParams(-1, -1));
        view.setVisibility(0);
        nVar.setAudioFocusType(this.v);
        ho.b("PPSSplashView", "set splashpro view to adview");
        if (a2 != null && a2.intValue() == 4) {
            nVar.a(this.K.getClickAreaView(), a2);
        } else if (a2 == null || a2.intValue() != 3) {
            nVar.a(this.H, a2);
        } else {
            nVar.a(this.L.getClickAreaView(), a2);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public void a(hl hlVar) {
        View view = this.g;
        if (view != null) {
            view.setVisibility(this.h);
        }
        View view2 = this.A;
        if (view2 != null) {
            view2.setVisibility(0);
            new nb(this.e, hlVar).a();
            return;
        }
        ad adVar = this.f7978a;
        if (adVar == null) {
            ho.b("PPSSplashView", "create default slogan");
            setSloganResId(R.drawable._2131428527_res_0x7f0b04af);
            adVar = this.f7978a;
            if (adVar == null) {
                return;
            }
        }
        adVar.setSloganShowListener(hlVar);
        this.f7978a.a();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public void a(ContentRecord contentRecord, int i) {
        this.N = contentRecord;
        setSkipBtnDelayTime(contentRecord);
        if (this.d == null) {
            b(contentRecord, i);
        }
        u uVar = this.d;
        if (uVar != null) {
            com.huawei.openalliance.ad.views.interfaces.n nVar = this.s;
            if (nVar != null) {
                uVar.setShowLeftTime(nVar.e());
            }
            if (contentRecord != null && contentRecord.h() != null && contentRecord.D() == 9) {
                this.d.a((int) ((contentRecord.h().x() * 1.0f) / 1000.0f));
            }
            k();
        }
        d(contentRecord);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public void a(AdLoadMode adLoadMode) {
        ix a2 = iy.a(adLoadMode, this);
        this.k = a2;
        a2.a(this.m);
        this.k.a(this.n);
        this.k.a(this.o);
        this.k.a(this.z);
        this.k.b(this.C);
        this.k.a(this.G);
        this.k.A();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public void a(View view, ContentRecord contentRecord) {
        ho.b("PPSSplashView", "showTemplateView");
        if (dd.c(getContext())) {
            ho.c("PPSSplashView", "showAdView - activity finished, not add view");
            return;
        }
        a(true, view, contentRecord);
        this.b.addView(view, new RelativeLayout.LayoutParams(-1, -1));
        view.setVisibility(0);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public void a(int i, boolean z) {
        View view = this.g;
        if (view == null) {
            return;
        }
        if (1 == i) {
            view.setVisibility(0);
            return;
        }
        view.setVisibility(8);
        if (z) {
            g();
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public void a(int i, int i2, String str, boolean z, Integer num) {
        if (this.H == null) {
            return;
        }
        ho.b("PPSSplashView", "set splashpro mode: %d", Integer.valueOf(i));
        ho.b("PPSSplashView", "interactCfg = %s", num);
        if (num == null) {
            this.H.setVisibility(8);
        } else if (num.intValue() == 0) {
            a(i2, str, z);
        } else {
            a(z, num.intValue());
        }
        this.H.setMode(i);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public void a() {
        jp jpVar = this.O;
        if (jpVar != null) {
            jpVar.a();
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public Integer a(ContentRecord contentRecord) {
        int n = os.n(contentRecord.V());
        if (n == 0) {
            return null;
        }
        int b2 = b(contentRecord);
        ho.b("PPSSplashView", "initial mode: %s", Integer.valueOf(b2));
        if (b2 == 0) {
            return Integer.valueOf(b2);
        }
        Map<String, String> a2 = be.a(fh.b(getContext()).ca());
        if (a2 != null) {
            if (c(b2)) {
                boolean B = dd.B(getContext());
                if (a(cz.i(a2.get(Constants.TWIST_TYPE))) || !B) {
                    b2 = 4;
                }
            }
            if (d(b2) && a(cz.i(a2.get(Constants.SWIPE_TYPE)))) {
                return 0;
            }
        }
        int i = (!c(b2) || dd.B(getContext())) ? b2 : 4;
        if (1 != this.f.b() || 2 != n) {
            ho.b("PPSSplashView", "proMode: %s", Integer.valueOf(n));
        } else {
            if (!c(i) || !e()) {
                return Integer.valueOf(i);
            }
            ho.b("PPSSplashView", "can't use twist, enable : %s", Boolean.valueOf(this.e.aL()));
        }
        return 0;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public com.huawei.openalliance.ad.views.interfaces.n a(int i) {
        if (i == 2) {
            return new q(getContext(), 1);
        }
        if (i != 9) {
            return null;
        }
        Context context = getContext();
        int b2 = this.f.b();
        int i2 = this.y;
        if (i2 <= 0) {
            i2 = 0;
        }
        return new v(context, b2, i2, this.f.d(), 1);
    }

    private void setSkipBtnDelayTime(ContentRecord contentRecord) {
        if (contentRecord == null || contentRecord.at() <= 0) {
            return;
        }
        this.D = contentRecord.at();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (!this.p || this.f == null) {
            return;
        }
        ho.b("PPSSplashView", " exsplash start, dismiss");
        d();
    }

    private void k() {
        if (this.d != null) {
            ho.a("PPSSplashView", "%d delay, skip btn show", Integer.valueOf(this.D));
            if (this.D > 0) {
                cv.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSSplashView.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (PPSSplashView.this.d != null) {
                            ho.a("PPSSplashView", "skip btn show");
                            PPSSplashView.this.d.setVisibility(0);
                        }
                    }
                }, this.E, this.D);
            } else {
                ho.a("PPSSplashView", "skip btn show");
                this.d.setVisibility(0);
            }
        }
    }

    private void j() {
        Context applicationContext = getContext().getApplicationContext();
        co a2 = bz.a(applicationContext);
        boolean a3 = a2.a(applicationContext);
        if (this.w <= 0) {
            this.w = a3 ? a2.a(this) : ao.h(getContext().getApplicationContext());
        }
    }

    private void i() {
        int i;
        ImageView imageView = (ImageView) this.r.findViewById(R.id.hiad_full_mode_logo);
        int i2 = this.q;
        if (i2 > 0) {
            imageView.setImageResource(i2);
            i = 0;
        } else {
            i = 8;
        }
        imageView.setVisibility(i);
    }

    private void h() {
        int i;
        TextView textView = (TextView) this.r.findViewById(R.id.hiad_media_name);
        int i2 = this.t;
        if (i2 > 0) {
            textView.setText(i2);
            i = 0;
        } else {
            i = 8;
        }
        textView.setVisibility(i);
    }

    private void g() {
        String str;
        try {
            if (this.r == null) {
                View inflate = ((ViewStub) findViewById(R.id.hiad_logo_stub)).inflate();
                this.r = inflate;
                inflate.setId(R.id.hiad_full_logo_region);
            }
            a((RelativeLayout.LayoutParams) this.r.getLayoutParams());
            i();
            h();
        } catch (Resources.NotFoundException unused) {
            str = "showFullModeLogo res not found";
            ho.c("PPSSplashView", str);
        } catch (Exception e) {
            str = "showFullModeLogo " + e.getClass().getSimpleName();
            ho.c("PPSSplashView", str);
        }
    }

    private void f() {
        int H = this.e.H();
        if (H > 0) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.H.getLayoutParams();
            int a2 = ao.a(getContext(), H);
            this.H.setPadding(a2, a2, a2, a2);
            if (layoutParams.isMarginRelative()) {
                layoutParams.setMarginStart(layoutParams.leftMargin - a2);
                layoutParams.setMarginEnd(layoutParams.rightMargin - a2);
            } else {
                layoutParams.setMargins(layoutParams.leftMargin - a2, layoutParams.topMargin, layoutParams.rightMargin - a2, layoutParams.bottomMargin);
            }
            this.H.setLayoutParams(layoutParams);
        }
    }

    private boolean e() {
        if (this.e.aL()) {
            return !com.huawei.openalliance.ad.utils.x.l(getContext().getApplicationContext());
        }
        return true;
    }

    private void d(ContentRecord contentRecord) {
        boolean z;
        int i;
        boolean z2;
        PPSSplashAdSourceView pPSSplashAdSourceView;
        boolean z3;
        boolean z4;
        int i2;
        boolean z5;
        PPSWLSView pPSWLSView;
        if (contentRecord != null) {
            int b2 = this.f.b();
            Integer a2 = a(contentRecord);
            InteractCfg aJ = contentRecord.aJ();
            Integer e = aJ == null ? null : aJ.e();
            j();
            boolean z6 = false;
            if (this.B) {
                this.j.a(this, a2, e, ao.a(true, contentRecord.bc(), contentRecord.bb()));
                this.j.setVisibility(0);
                if (1 == b2) {
                    PPSSplashAdSourceView pPSSplashAdSourceView2 = this.j;
                    z3 = contentRecord.o() == 1;
                    i = this.w;
                    z2 = z3;
                    pPSSplashAdSourceView = pPSSplashAdSourceView2;
                } else {
                    ho.b("PPSSplashView", "showAdLabel, orientation: %s, leftNotchHeight: %s, rightNotchHeight: %s", Integer.valueOf(b2), Integer.valueOf(this.x), Integer.valueOf(this.y));
                    int i3 = this.x;
                    if (i3 > 0) {
                        z = true;
                    } else {
                        i3 = this.y;
                        z = false;
                    }
                    PPSSplashAdSourceView pPSSplashAdSourceView3 = this.j;
                    i = i3;
                    if (contentRecord.o() == 1) {
                        z6 = z;
                        pPSSplashAdSourceView = pPSSplashAdSourceView3;
                        z2 = true;
                    } else {
                        z2 = false;
                        z6 = z;
                        pPSSplashAdSourceView = pPSSplashAdSourceView3;
                    }
                }
                pPSSplashAdSourceView.a(contentRecord, z2, i, b2, z6);
                return;
            }
            this.i.setAdMediator(this.k);
            this.i.a(a2, e);
            this.i.setVisibility(0);
            if (1 == b2) {
                PPSWLSView pPSWLSView2 = this.i;
                z3 = contentRecord.o() == 1;
                i2 = this.w;
                z5 = z3;
                pPSWLSView = pPSWLSView2;
            } else {
                ho.b("PPSSplashView", "showAdLabel, orientation: %s, leftNotchHeight: %s, rightNotchHeight: %s", Integer.valueOf(b2), Integer.valueOf(this.x), Integer.valueOf(this.y));
                int i4 = this.x;
                if (i4 > 0) {
                    z4 = true;
                } else {
                    i4 = this.y;
                    z4 = false;
                }
                PPSWLSView pPSWLSView3 = this.i;
                i2 = i4;
                if (contentRecord.o() == 1) {
                    z6 = z4;
                    pPSWLSView = pPSWLSView3;
                    z5 = true;
                } else {
                    z5 = false;
                    z6 = z4;
                    pPSWLSView = pPSWLSView3;
                }
            }
            pPSWLSView.a(contentRecord, z5, i2, b2, z6);
            if (bg.a(contentRecord.aS())) {
                return;
            }
            this.i.setChoiceViewOnClickListener(new a(this, contentRecord));
        }
    }

    private void d() {
        List<String> a2 = this.f.a();
        this.l.a(!bg.a(a2) ? a2.get(0) : null, 1);
        this.l.h();
        jp jpVar = this.O;
        if (jpVar != null) {
            jpVar.b();
        }
        com.huawei.openalliance.ad.inter.c.a(getContext().getApplicationContext()).a(false);
    }

    private String d(InteractCfg interactCfg) {
        if (interactCfg != null) {
            return interactCfg.j();
        }
        return null;
    }

    private boolean c(ContentRecord contentRecord) {
        if (contentRecord.aO() == 2 && contentRecord.R() != null) {
            return false;
        }
        if (contentRecord.aO() != 3 || bg.a(contentRecord.aV())) {
            return true;
        }
        Iterator<Asset> it = contentRecord.aV().iterator();
        while (it.hasNext()) {
            if (it.next().e() != null) {
                return false;
            }
        }
        return true;
    }

    private String c(InteractCfg interactCfg) {
        if (interactCfg != null) {
            return interactCfg.i();
        }
        return null;
    }

    private void b(ContentRecord contentRecord, int i) {
        float f;
        boolean z;
        int i2;
        String str;
        String str2;
        if (dd.c(getContext())) {
            ho.c("PPSSplashView", "addSkipAdButton - activity finished, not add view");
            return;
        }
        if (contentRecord != null) {
            boolean z2 = contentRecord.o() == 1;
            String f2 = contentRecord.f();
            String L = contentRecord.L();
            f = contentRecord.ai();
            i2 = contentRecord.aj();
            z = z2;
            str = f2;
            str2 = L;
        } else {
            f = 0.0f;
            z = false;
            i2 = 0;
            str = null;
            str2 = null;
        }
        u a2 = a(str, i, str2, z, f, i2);
        this.d = a2;
        a2.setId(R.id.hiad_btn_skip);
        addView(this.d);
        this.d.setVisibility(4);
    }

    private void b(Context context) {
        inflate(context, R.layout.hiad_view_splash_ad, this);
        this.b = (RelativeLayout) findViewById(R.id.rl_splash_container);
        this.c = (RelativeLayout) findViewById(R.id.ar_install_container);
        this.i = (PPSWLSView) findViewById(R.id.splash_wls_view);
        this.j = (PPSSplashAdSourceView) findViewById(R.id.splash_ad_source_view);
        this.B = bz.a(context).d();
        this.H = (PPSSplashProView) findViewById(R.id.hiad_splash_pro_view);
        this.I = (PPSSplashSwipeView) findViewById(R.id.hiad_splash_swipe_view);
        this.J = (PPSSplashTwistView) findViewById(R.id.hiad_splash_twist_view);
        this.L = (PPSSplashTwistClickView) findViewById(R.id.hiad_splash_twist_click_view);
        this.K = (PPSSplashSwipeClickView) findViewById(R.id.hiad_splash_swipe_click_view);
    }

    private String b(InteractCfg interactCfg, String str) {
        return !TextUtils.isEmpty(str) ? str : (interactCfg == null || interactCfg.k() == null) ? this.e.I() : interactCfg.k();
    }

    private String b(InteractCfg interactCfg) {
        if (interactCfg != null) {
            return interactCfg.h();
        }
        return null;
    }

    private int b(ContentRecord contentRecord) {
        return (contentRecord.aJ() == null || contentRecord.aJ().a() == null) ? this.e.F() : contentRecord.aJ().a().intValue();
    }

    private boolean a(Long l) {
        return fh.b(getContext()).a(l);
    }

    private void a(boolean z, View view, ContentRecord contentRecord) {
        if (contentRecord == null) {
            ho.b("PPSSplashView", "getMonitor, contentRecord is null.");
            return;
        }
        ho.b("PPSSplashView", "getMonitor, sdkMonitor is %s", Integer.valueOf(contentRecord.a()));
        this.P.a(contentRecord.a());
        this.P.a(contentRecord);
        jp a2 = jn.a(getContext(), c(contentRecord), this.P, contentRecord, z);
        this.O = a2;
        a2.a(this);
        if (view instanceof v) {
            this.O.a(((v) view).getVideoView());
        }
        jn.a(contentRecord.m(), this.O);
    }

    private void a(boolean z, int i) {
        PPSSplashSwipeClickView pPSSplashSwipeClickView;
        ho.b("PPSSplashView", "showNewStyle, cfg= %s", Integer.valueOf(i));
        String a2 = com.huawei.openalliance.ad.utils.c.a(getContext(), this.N, i);
        ContentRecord contentRecord = this.N;
        InteractCfg aJ = contentRecord != null ? contentRecord.aJ() : null;
        if (1 == i) {
            PPSSplashSwipeView pPSSplashSwipeView = this.I;
            if (pPSSplashSwipeView == null) {
                return;
            }
            pPSSplashSwipeView.setVisibility(4);
            this.I.a(a(aJ), b(aJ, a2));
            this.I.setOrientation(this.f.b());
            this.I.setShowLogo(z);
            this.I.setVisibility(0);
            return;
        }
        if (2 == i) {
            PPSSplashTwistView pPSSplashTwistView = this.J;
            if (pPSSplashTwistView == null) {
                return;
            }
            pPSSplashTwistView.setVisibility(4);
            this.J.a(b(aJ), a(aJ, a2));
            this.J.setOrientation(this.f.b());
            this.J.setShowLogo(z);
            this.J.setVisibility(0);
            return;
        }
        if (3 == i) {
            PPSSplashTwistClickView pPSSplashTwistClickView = this.L;
            if (pPSSplashTwistClickView == null) {
                return;
            }
            pPSSplashTwistClickView.setVisibility(4);
            this.L.a(d(aJ), a(aJ, a2));
            this.L.setOrientation(this.f.b());
            this.L.setShowLogo(z);
            this.L.setVisibility(0);
            return;
        }
        if (4 != i || (pPSSplashSwipeClickView = this.K) == null) {
            return;
        }
        pPSSplashSwipeClickView.setVisibility(4);
        this.K.a(c(aJ), b(aJ, a2));
        this.K.setOrientation(this.f.b());
        this.K.setShowLogo(z);
        this.K.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ContentRecord contentRecord, int[] iArr, int[] iArr2) {
        if (ao.a(iArr, 2) && ao.a(iArr2, 2) && contentRecord != null) {
            if (ho.a()) {
                ho.a("PPSSplashView", "addComplianceDialog, loc: %s, %s", Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]));
                ho.a("PPSSplashView", "addComplianceDialog, size: %s, %s", Integer.valueOf(iArr2[0]), Integer.valueOf(iArr2[1]));
            }
            ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            ti tiVar = new ti(getContext(), iArr, iArr2);
            addView(tiVar, layoutParams);
            contentRecord.u(cz.c(contentRecord.Z()));
            tiVar.setScreenHeight(getMeasuredHeight());
            tiVar.setScreenWidth(getMeasuredWidth());
            tiVar.setAdContent(contentRecord);
        }
    }

    private void a(RelativeLayout.LayoutParams layoutParams) {
        int f;
        int f2;
        if (1 == this.f.b()) {
            j();
            if (this.w <= 0) {
                return;
            }
            ho.a("PPSSplashView", "left:" + layoutParams.leftMargin + ", top:" + layoutParams.topMargin + ", right:" + layoutParams.rightMargin);
            layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin + this.w, layoutParams.rightMargin, layoutParams.bottomMargin);
        } else {
            ho.b("PPSSplashView", "showFullModeLogo, orientation: %s, leftNotchHeight: %s", Integer.valueOf(this.f.b()), Integer.valueOf(this.x));
            ho.a("PPSSplashView", "left:%d, top:%d, right:%d, leftNotchHeight:%d", Integer.valueOf(layoutParams.leftMargin), Integer.valueOf(layoutParams.topMargin), Integer.valueOf(layoutParams.rightMargin), Integer.valueOf(this.x));
            if (!bz.b(getContext()) || this.x <= 0) {
                if (!bz.b(getContext()) || (bz.b(getContext()) && TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1 && !com.huawei.openalliance.ad.utils.x.n(getContext()))) {
                    if (layoutParams.isMarginRelative()) {
                        f2 = dd.f(getContext());
                        layoutParams.setMarginStart(f2);
                    } else {
                        f = dd.f(getContext());
                        layoutParams.setMargins(f, layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);
                    }
                }
                layoutParams.topMargin += ao.a(getContext(), 12.0f);
            } else if (layoutParams.isMarginRelative()) {
                f2 = layoutParams.leftMargin + this.x;
                layoutParams.setMarginStart(f2);
            } else {
                f = layoutParams.leftMargin + this.x;
                layoutParams.setMargins(f, layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);
            }
            this.r.setLayoutParams(layoutParams);
            layoutParams.topMargin += ao.a(getContext(), 12.0f);
        }
        this.r.setLayoutParams(layoutParams);
    }

    private void a(Context context) {
        b(context);
        this.l = new nu(context.getApplicationContext(), this);
        this.e = fh.b(context.getApplicationContext());
        this.P = new ou(context, new si(context, 1));
        this.F = com.huawei.openalliance.ad.utils.x.k(context.getApplicationContext());
        this.M = new b();
        com.huawei.openalliance.ad.inter.c.a(context.getApplicationContext()).a(this.M);
    }

    private void a(int i, String str, boolean z) {
        ho.b("PPSSplashView", "showClickButton");
        f();
        this.H.setVisibility(4);
        this.H.setDesc(a(str));
        this.H.setOrientation(this.f.b());
        this.H.a(z, i);
        if (i != 0) {
            this.H.setVisibility(0);
        }
    }

    private String a(String str) {
        String a2 = com.huawei.openalliance.ad.utils.c.a(getContext(), this.N, 0);
        return !TextUtils.isEmpty(a2) ? a2 : !TextUtils.isEmpty(this.e.G()) ? this.e.G() : str;
    }

    private String a(InteractCfg interactCfg, String str) {
        return !TextUtils.isEmpty(str) ? str : (interactCfg == null || interactCfg.k() == null) ? this.e.K() : interactCfg.k();
    }

    private String a(InteractCfg interactCfg) {
        if (interactCfg != null) {
            return interactCfg.g();
        }
        return null;
    }

    private u a(String str, int i, String str2, boolean z, float f, int i2) {
        int i3;
        boolean z2;
        u uVar;
        int b2 = this.f.b();
        int d = this.f.d();
        j();
        if (1 == b2) {
            uVar = new u(getContext(), str, b2, d, i, str2, z, this.w, f, i2, false);
        } else {
            ho.b("PPSSplashView", "createSkipAdButton, orientation: %s, leftNotchHeight: %s, rightNotchHeight: %s", Integer.valueOf(b2), Integer.valueOf(this.x), Integer.valueOf(this.y));
            int i4 = this.x;
            if (i4 > 0) {
                z2 = true;
                i3 = i4;
            } else {
                i3 = this.y;
                z2 = false;
            }
            uVar = new u(getContext(), str, b2, d, i, str2, z, i3, f, i2, z2);
        }
        uVar.setAdMediator(this.k);
        return uVar;
    }

    /* loaded from: classes9.dex */
    static class a implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<PPSSplashView> f7980a;
        private ContentRecord b;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            final PPSSplashView pPSSplashView = this.f7980a.get();
            if (pPSSplashView != null) {
                final int[] choiceViewLoc = pPSSplashView.i.getChoiceViewLoc();
                final int[] choiceViewSize = pPSSplashView.i.getChoiceViewSize();
                if (ao.a(choiceViewLoc, 2) && ao.a(choiceViewSize, 2)) {
                    dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSSplashView.a.1
                        @Override // java.lang.Runnable
                        public void run() {
                            pPSSplashView.a(a.this.b, choiceViewLoc, choiceViewSize);
                        }
                    });
                }
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        public a(PPSSplashView pPSSplashView, ContentRecord contentRecord) {
            this.f7980a = new WeakReference<>(pPSSplashView);
            this.b = contentRecord;
        }
    }

    /* loaded from: classes9.dex */
    class b implements c.b {
        @Override // com.huawei.openalliance.ad.inter.c.b
        public void a() {
            ho.b("PPSSplashView", "onStart");
            PPSSplashView.this.l();
        }

        private b() {
        }
    }

    public PPSSplashView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = 8;
        this.p = false;
        this.t = 0;
        this.u = 0;
        this.v = 1;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.B = true;
        this.D = 0;
        this.E = "skip_btn_delay_id_" + hashCode();
        a(context);
    }

    public PPSSplashView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = 8;
        this.p = false;
        this.t = 0;
        this.u = 0;
        this.v = 1;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.B = true;
        this.D = 0;
        this.E = "skip_btn_delay_id_" + hashCode();
        a(context);
    }

    public PPSSplashView(Context context) {
        super(context);
        this.h = 8;
        this.p = false;
        this.t = 0;
        this.u = 0;
        this.v = 1;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.B = true;
        this.D = 0;
        this.E = "skip_btn_delay_id_" + hashCode();
        a(context);
    }
}
