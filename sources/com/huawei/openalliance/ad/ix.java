package com.huawei.openalliance.ad;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hms.ads.dynamic.ObjectWrapper;
import com.huawei.hms.ads.uiengine.IRemoteCreator;
import com.huawei.hms.ads.uiengine.common.IMediaStateApiTransfer;
import com.huawei.openalliance.ad.beans.inner.AdContentData;
import com.huawei.openalliance.ad.beans.metadata.DelayInfo;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.constant.AdLoadState;
import com.huawei.openalliance.ad.constant.AdShowExtras;
import com.huawei.openalliance.ad.constant.ErrorCode;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.openalliance.ad.constant.Scheme;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.EncryptionField;
import com.huawei.openalliance.ad.inter.HiAdSplash;
import com.huawei.openalliance.ad.inter.IHiAdSplash;
import com.huawei.openalliance.ad.inter.data.BiddingInfo;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.data.RewardVerifyConfig;
import com.huawei.openalliance.ad.inter.listeners.AdActionListener;
import com.huawei.openalliance.ad.inter.listeners.AdListener;
import com.huawei.openalliance.ad.inter.listeners.AdShowListener;
import com.huawei.openalliance.ad.inter.listeners.LinkedAdListener;
import com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager;
import com.huawei.openalliance.ad.processor.eventbeans.a;
import com.huawei.openalliance.ad.processor.eventbeans.b;
import com.huawei.openalliance.ad.utils.m;
import com.huawei.openalliance.ad.views.PPSSplashView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes9.dex */
public abstract class ix implements jb, qw {
    private AdActionListener A;
    private AdShowListener B;
    private String C;
    private ContentRecord D;
    private rp K;
    private qq L;

    /* renamed from: a, reason: collision with root package name */
    protected WeakReference<com.huawei.openalliance.ad.views.interfaces.n> f7086a;
    protected AdLoadState b;
    protected gc c;
    protected fs d;
    protected qq e;
    protected ContentRecord k;
    protected RewardVerifyConfig m;
    protected int n;
    protected Context o;
    private WeakReference<com.huawei.openalliance.ad.views.interfaces.l> p;
    private AdListener r;
    private ql s;
    private CountDownTimer t;
    private lz q = new lo();
    protected boolean f = false;
    private boolean u = false;
    private boolean v = false;
    protected boolean g = false;
    private boolean w = false;
    protected final String h = "load_timeout_" + hashCode();
    private boolean x = false;
    private boolean y = false;
    private boolean z = false;
    protected long i = 0;
    private int E = 0;
    private long F = -1;
    protected boolean j = false;
    protected DelayInfo l = new DelayInfo();
    private boolean G = false;
    private boolean H = false;
    private boolean I = false;
    private boolean J = false;

    protected abstract void c(ContentRecord contentRecord);

    protected abstract String r();

    protected abstract String z();

    @Override // com.huawei.openalliance.ad.jb
    public String y() {
        ContentRecord contentRecord = this.D;
        if (contentRecord != null) {
            return contentRecord.j();
        }
        return null;
    }

    @Override // com.huawei.openalliance.ad.jb
    public BiddingInfo x() {
        Float f = null;
        if (this.D == null) {
            return null;
        }
        BiddingInfo.a aVar = new BiddingInfo.a();
        aVar.a(this.D.bh()).b(this.D.bj()).setLurl(this.D.bk());
        EncryptionField<String> bi = this.D.bi();
        if (bi != null) {
            String a2 = bi.a(l());
            if (!TextUtils.isEmpty(a2)) {
                f = Float.valueOf(a2);
            }
        }
        aVar.a(f);
        return aVar.a();
    }

    @Override // com.huawei.openalliance.ad.jb
    public String w() {
        ContentRecord contentRecord = this.D;
        if (contentRecord != null) {
            return contentRecord.aa();
        }
        return null;
    }

    @Override // com.huawei.openalliance.ad.jb
    public void v() {
        if (this.H) {
            ho.b(z(), "already reset");
            return;
        }
        this.H = true;
        if (q() != null) {
            q().destroyView();
            if (!q().i()) {
                ho.b(z(), "not show Icon");
                dc.c(null);
                dc.a((Drawable) null);
                dc.a((Bitmap) null);
            }
        }
        com.huawei.openalliance.ad.views.interfaces.l p = p();
        if (p != null) {
            p.b();
        }
        dc.a((ContentRecord) null);
        dc.b((ContentRecord) null);
        com.huawei.openalliance.ad.utils.ao.i(this.o);
        com.huawei.openalliance.ad.inter.c.a(this.o).a(false);
    }

    @Override // com.huawei.openalliance.ad.jb
    public void u() {
        ho.b(z(), "onDisplayTimeUp hasShowFinish: %s", Boolean.valueOf(this.G));
        if (this.G) {
            return;
        }
        this.G = true;
        v();
        j();
    }

    @Override // com.huawei.openalliance.ad.jb
    public boolean t() {
        return this.j;
    }

    @Override // com.huawei.openalliance.ad.jb
    public void s() {
        this.l.u().k(System.currentTimeMillis());
    }

    protected com.huawei.openalliance.ad.views.interfaces.n q() {
        WeakReference<com.huawei.openalliance.ad.views.interfaces.n> weakReference = this.f7086a;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    protected com.huawei.openalliance.ad.views.interfaces.l p() {
        return this.p.get();
    }

    protected void o() {
        j();
    }

    protected void n() {
        int aF = this.c.aF();
        ho.b(z(), "startAdLoadTimeoutTask - max load time: %d", Integer.valueOf(aF));
        com.huawei.openalliance.ad.utils.cv.a(new Runnable() { // from class: com.huawei.openalliance.ad.ix.14
            @Override // java.lang.Runnable
            public void run() {
                synchronized (ix.this) {
                    ho.b(ix.this.z(), "on load task timeout, loadingTimeout: %s", Boolean.valueOf(ix.this.f));
                    if (!ix.this.f && !ix.this.d(-2)) {
                        ix.this.c(-2);
                        ix.this.o();
                    }
                }
            }
        }, this.h, aF);
    }

    protected void m() {
        Context context;
        AdSlotParam k = k();
        if (k == null) {
            c((ContentRecord) null);
            return;
        }
        Pair<String, Boolean> b = com.huawei.openalliance.ad.utils.d.b(this.o, true);
        if (b == null && this.c.be() && (context = this.o) != null && bz.h(context)) {
            ho.b(z(), "start to request oaid");
            a(k, this.o);
            return;
        }
        if (b != null) {
            ho.b(z(), "use cached oaid ");
            k.a((String) b.first);
            k.a((Boolean) b.second);
        }
        a(k);
    }

    public Context l() {
        com.huawei.openalliance.ad.views.interfaces.l p = p();
        if (p == null) {
            return null;
        }
        return p.getContext();
    }

    protected AdSlotParam k() {
        com.huawei.openalliance.ad.views.interfaces.l p = p();
        if (p == null) {
            return null;
        }
        AdSlotParam adSlotParam = p.getAdSlotParam();
        if (adSlotParam != null) {
            this.l.a(adSlotParam.a());
        }
        return adSlotParam;
    }

    @Override // com.huawei.openalliance.ad.jb
    public void j() {
        ho.b(z(), "notifyAdDismissed");
        if (this.u) {
            ho.b(z(), "ad already dismissed");
            return;
        }
        this.u = true;
        AdListener adListener = this.r;
        if (adListener != null) {
            adListener.onAdDismissed();
        }
        ContentRecord contentRecord = this.D;
        if (contentRecord != null && contentRecord.aO() != 3) {
            pp.a(this.o).a(this.D.aa(), -10);
        }
        com.huawei.openalliance.ad.views.interfaces.n q = q();
        if (q != null) {
            q.destroyView();
        }
        com.huawei.openalliance.ad.views.interfaces.l p = p();
        if (p != null) {
            p.b();
        }
    }

    @Override // com.huawei.openalliance.ad.jb
    public void i() {
        if (this.z) {
            return;
        }
        this.z = true;
        if (this.F < 0) {
            this.F = com.huawei.openalliance.ad.utils.ao.c();
        }
        D();
        this.e.b();
        lz lzVar = this.q;
        if (lzVar != null) {
            lzVar.f();
        }
    }

    protected void h() {
        this.d.b();
        et.b(this.o).b();
    }

    protected void g(int i) {
        this.w = true;
        this.l.a(i);
        i(this.D);
    }

    public void g() {
        AdActionListener adActionListener = this.A;
        if (adActionListener != null) {
            adActionListener.onAdClick();
        }
        com.huawei.openalliance.ad.utils.ao.i(this.o);
    }

    @Override // com.huawei.openalliance.ad.jb
    public void f(int i) {
        com.huawei.openalliance.ad.views.interfaces.l p = p();
        if (p != null) {
            p.b(i);
        }
    }

    @Override // com.huawei.openalliance.ad.jb
    public void f() {
        a(11, "feedback hasShowFinish", Long.valueOf(this.i));
        com.huawei.openalliance.ad.views.interfaces.n q = q();
        if (q != null) {
            q.h();
        }
    }

    @Override // com.huawei.openalliance.ad.jb
    public void e(final ContentRecord contentRecord) {
        if (contentRecord == null) {
            return;
        }
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.ix.5
            @Override // java.lang.Runnable
            public void run() {
                if (contentRecord.aO() == 3) {
                    et.b(ix.this.o).a(contentRecord.m(), contentRecord.aN(), contentRecord.l(), "load failed");
                    return;
                }
                String y = contentRecord.y();
                if (!com.huawei.openalliance.ad.utils.cz.b(y)) {
                    com.huawei.openalliance.ad.utils.ae.a(ix.this.o, y);
                }
                es.a(ix.this.o).b(contentRecord.m(), contentRecord.l());
            }
        });
    }

    @Override // com.huawei.openalliance.ad.jb
    public void e(int i) {
        i(i);
        AdListener adListener = this.r;
        if (adListener instanceof LinkedAdListener) {
            adListener.onAdFailedToLoad(-6);
        }
    }

    @Override // com.huawei.openalliance.ad.jb
    public void e() {
        a(10, "onWhyThisAd hasShowFinish", Long.valueOf(this.i));
        com.huawei.openalliance.ad.views.interfaces.n q = q();
        if (q != null) {
            q.g();
        }
    }

    public boolean d(int i) {
        ho.b(z(), "showSpare");
        ContentRecord h = dc.h();
        String z = z();
        Object[] objArr = new Object[2];
        objArr[0] = Boolean.valueOf(this.f);
        objArr[1] = Boolean.valueOf(h == null);
        ho.b(z, "showSpare, loadingTimeout: %s %s", objArr);
        if (this.f || h == null || !f(h)) {
            this.f = true;
            return false;
        }
        ho.b(z(), "show spare splash");
        com.huawei.openalliance.ad.utils.cv.a(this.h);
        this.f = true;
        this.g = true;
        h.c(true);
        this.i = System.currentTimeMillis();
        this.l.b(System.currentTimeMillis());
        h(i);
        a(h, i);
        c(h);
        return true;
    }

    protected void d(ContentRecord contentRecord) {
        this.D = contentRecord;
        if (contentRecord == null || contentRecord.aO() != 3) {
            return;
        }
        this.d = et.b(this.o);
    }

    @Override // com.huawei.openalliance.ad.jb
    public int d() {
        return this.E;
    }

    @Override // com.huawei.openalliance.ad.jb
    public void c(int i) {
        ho.b(z(), "ad failed:" + i);
        if (this.v) {
            ho.b(z(), "ad is already failed");
            return;
        }
        this.v = true;
        this.i = System.currentTimeMillis();
        this.l.b(System.currentTimeMillis());
        AdListener adListener = this.r;
        if (adListener != null) {
            adListener.onAdFailedToLoad(i);
        }
        com.huawei.openalliance.ad.utils.ao.i(this.o);
        J();
        h(i);
        dh.b();
    }

    public AdListener c() {
        return this.r;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huawei.openalliance.ad.jb
    public boolean b(ContentRecord contentRecord) {
        ho.b(z(), "showAdContent");
        this.j = true;
        if (this.m != null) {
            ho.b(z(), "set verifyConfig.");
            contentRecord.G(com.huawei.openalliance.ad.utils.cz.d(this.m.getData()));
            contentRecord.H(com.huawei.openalliance.ad.utils.cz.d(this.m.getUserId()));
        }
        this.e.a(contentRecord);
        com.huawei.openalliance.ad.views.interfaces.l p = p();
        if (p == null) {
            return false;
        }
        ho.b(z(), "showAdContent, getTemplateIdV3 = %s, sdkMonitor %s", contentRecord.aN(), Integer.valueOf(contentRecord.a()));
        if (!com.huawei.openalliance.ad.utils.cz.b(contentRecord.aN()) || com.huawei.openalliance.ad.utils.c.a(this.o, contentRecord.aU(), contentRecord.l(), 1)) {
            this.I = true;
            if (!(p instanceof PPSSplashView)) {
                ho.c(z(), "not PPSSplashView");
                return false;
            }
            IRemoteCreator a2 = e.a(this.o);
            if (a2 == null) {
                ho.b(z(), "Creator is null");
                return false;
            }
            bw bwVar = new bw(p.getContext(), this, contentRecord);
            String b = com.huawei.openalliance.ad.utils.be.b(AdContentData.a(this.o, contentRecord));
            Bundle bundle = new Bundle();
            bundle.putInt("audioFocusType", p.getAudioFocusType());
            PPSSplashView pPSSplashView = (PPSSplashView) p;
            bundle.putInt(ParamConstants.Param.MEDIA_NAME_RES_ID, pPSSplashView.getMediaNameResId());
            bundle.putInt(ParamConstants.Param.LOGO_RES_ID, pPSSplashView.getLogoResId());
            bundle.putString("content", b);
            ho.a(z(), "contentJson : %s", b);
            AdSlotParam k = k();
            if (k != null) {
                bundle.putInt(ParamConstants.Param.ORIENTATION, k.b());
                bundle.putInt(ParamConstants.Param.END_MODE, k.F() != null ? k.F().intValue() : 1);
            }
            try {
                View view = (View) ObjectWrapper.unwrap(a2.newSplashTemplateView(bundle, bwVar));
                if (view == 0) {
                    ho.c(z(), "templateView is null");
                    return false;
                }
                this.q = null;
                p.a(view, contentRecord);
                this.L = p.getMonitorEp();
                a2.bindData(ObjectWrapper.wrap(view), b);
                if (view instanceof IMediaStateApiTransfer) {
                    ((IMediaStateApiTransfer) view).setProxy(p.getMediaProxy());
                } else {
                    ho.c(z(), "splash template view is not a IMediaStateApiTransfer instance");
                }
                H();
            } catch (Throwable th) {
                ho.c(z(), "create splashTemplateView err: %s", th.getClass().getSimpleName());
                return false;
            }
        } else {
            com.huawei.openalliance.ad.views.interfaces.n a3 = a(contentRecord, p);
            if (a3 == null) {
                return false;
            }
            lz lzVar = this.q;
            if (lzVar != null) {
                lzVar.c();
            }
            h(contentRecord);
            this.f7086a = new WeakReference<>(a3);
            p.a(a3, contentRecord);
            this.L = p.getMonitorEp();
            a3.b();
        }
        return true;
    }

    @Override // com.huawei.openalliance.ad.jb
    public void b(long j) {
        this.l.a(j);
    }

    @Override // com.huawei.openalliance.ad.jb
    public void b(int i) {
        ContentRecord h;
        ho.b(z(), "toShowSpare");
        if (!this.D.as() && (h = dc.h()) != null && f(h)) {
            this.l.b(System.currentTimeMillis());
            this.i = System.currentTimeMillis();
            h(i);
            this.D = h;
            this.e.a(h);
            a(h, i);
            if (b(h)) {
                return;
            } else {
                i = ErrorCode.ERROR_CODE_NULL_AD_VIEW;
            }
        }
        c(i);
        B();
    }

    @Override // com.huawei.openalliance.ad.jb
    public AdLoadState b() {
        return this.b;
    }

    @Override // com.huawei.openalliance.ad.jb
    public boolean a(int i, int i2, ContentRecord contentRecord, Long l, MaterialClickInfo materialClickInfo, int i3) {
        boolean z;
        ho.b(z(), "onTouch");
        ta a2 = sz.a(this.o, contentRecord, new HashMap(0));
        if (a2.a()) {
            if (18 == i3 && (l() instanceof Activity)) {
                ((Activity) l()).overridePendingTransition(R.anim._2130772028_res_0x7f01003c, R.anim._2130772025_res_0x7f010039);
            }
            a(i, i2, a2, l, materialClickInfo, i3);
            z = true;
        } else {
            z = false;
        }
        com.huawei.openalliance.ad.inter.c.a(this.o).a(false);
        return z;
    }

    public boolean a() {
        return this.y;
    }

    public void a(boolean z) {
        this.y = z;
    }

    public void a(final List<ContentRecord> list, final int i, final int i2) {
        com.huawei.openalliance.ad.utils.m.h(new Runnable() { // from class: com.huawei.openalliance.ad.ix.4
            @Override // java.lang.Runnable
            public void run() {
                if (list == null || ix.this.o == null) {
                    return;
                }
                com.huawei.openalliance.ad.analysis.c cVar = new com.huawei.openalliance.ad.analysis.c(ix.this.o);
                for (ContentRecord contentRecord : list) {
                    if (contentRecord != null) {
                        if (contentRecord.Q() != null) {
                            if (i == 0) {
                                if (contentRecord.Q().e() >= contentRecord.Q().d()) {
                                    cVar.a(contentRecord, i, i2, ix.this.C);
                                }
                            }
                            if (i == 1 && contentRecord.Q().e() < contentRecord.Q().d()) {
                                cVar.a(contentRecord, i, i2, ix.this.C);
                            }
                        } else if (contentRecord.R() != null) {
                            if (ix.this.a(contentRecord, contentRecord.R().m(), i)) {
                                cVar.a(contentRecord, i, i2, ix.this.C);
                            }
                        }
                    }
                }
            }
        });
    }

    public void a(Long l, Integer num, Integer num2, jk jkVar) {
        ContentRecord contentRecord = this.D;
        boolean a2 = com.huawei.openalliance.ad.utils.c.a(contentRecord != null ? contentRecord.aZ() : null, num2);
        if (I() && (!a2 || a())) {
            ho.c(z(), "show event already reported before, ignore this");
            return;
        }
        String E = E();
        if (this.D != null) {
            ho.a(z(), "slotId: %s, contentId: %s, slot pos: %s", this.D.l(), this.D.m(), E);
        }
        a.C0207a c0207a = new a.C0207a();
        if (!com.huawei.openalliance.ad.utils.cz.b(E)) {
            c0207a.d(E);
        }
        c0207a.a(l).a(num).b(num2).a(com.huawei.openalliance.ad.utils.b.a(jkVar)).c(F());
        a(c0207a);
        this.e.a(c0207a.a());
        if (a2) {
            a(true);
        }
        if (I()) {
            return;
        }
        b(true);
        AdActionListener adActionListener = this.A;
        if (adActionListener != null) {
            adActionListener.onAdShowed();
        }
        lz lzVar = this.q;
        if (lzVar != null) {
            lzVar.e();
        }
    }

    @Override // com.huawei.openalliance.ad.jb
    public void a(AdShowListener adShowListener) {
        this.B = adShowListener;
    }

    @Override // com.huawei.openalliance.ad.jb
    public void a(AdListener adListener) {
        this.r = adListener;
    }

    @Override // com.huawei.openalliance.ad.jb
    public void a(AdActionListener adActionListener) {
        this.A = adActionListener;
    }

    @Override // com.huawei.openalliance.ad.jb
    public void a(RewardVerifyConfig rewardVerifyConfig) {
        this.m = rewardVerifyConfig;
    }

    @Override // com.huawei.openalliance.ad.jb
    public void a(ContentRecord contentRecord, long j, int i) {
        String z;
        String str;
        J();
        if (!this.c.bb()) {
            ho.c(z(), "onAdShowEnd - use old adshow event");
            return;
        }
        ho.b(z(), "onAdShowEnd duration: %d showRatio: %d", Long.valueOf(j), Integer.valueOf(i));
        this.e.a(j, i);
        if (contentRecord != null) {
            MetaData h = contentRecord.h();
            if (h != null) {
                if (j < h.i() || i < h.j()) {
                    ho.c(z(), "duration or show ratio is invalid for showId %s", contentRecord.j());
                    return;
                } else {
                    a(Long.valueOf(j), Integer.valueOf(i), (Integer) null, q());
                    return;
                }
            }
            z = z();
            str = "onAdShowEnd - metaData is null";
        } else {
            z = z();
            str = "onAdShowEnd - content record is null";
        }
        ho.c(z, str);
    }

    @Override // com.huawei.openalliance.ad.jb
    public void a(final ContentRecord contentRecord) {
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.ix.9
            @Override // java.lang.Runnable
            public void run() {
                ix.this.g(contentRecord);
                if (!ix.this.I || 3 == contentRecord.aO()) {
                    return;
                }
                fm.a(ix.this.o).a(contentRecord.l(), System.currentTimeMillis());
            }
        });
        com.huawei.openalliance.ad.views.interfaces.l p = p();
        if (p != null) {
            int o = contentRecord.o();
            p.a(o, !this.I);
            p.c();
            if (!this.I) {
                p.a(contentRecord, this.c.az());
                p.a(os.n(contentRecord.V()), os.t(contentRecord.V()), contentRecord.ax(), 1 == o, p.a(contentRecord));
            }
        }
        this.b = AdLoadState.LOADED;
        CountDownTimer countDownTimer = this.t;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        ho.b(z(), "ad loaded");
        this.i = System.currentTimeMillis();
        this.l.b(System.currentTimeMillis());
        dc.c(contentRecord);
        AdListener adListener = this.r;
        if (adListener != null) {
            adListener.onAdLoaded();
        }
        if (this.B != null && this.n == 1 && contentRecord != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(AdShowExtras.PRELOAD_MODE, String.valueOf(fd.a(this.o).a(contentRecord.l())));
            hashMap.put(AdShowExtras.DOWNLOAD_SOURCE, com.huawei.openalliance.ad.utils.d.a(contentRecord.T()));
            this.B.onAdShowed(hashMap);
        }
        g(200);
        D();
        i();
        if (!this.c.bb()) {
            a((Long) null, (Integer) null, (Integer) null, p);
        }
        dh.b();
    }

    @Override // com.huawei.openalliance.ad.jb
    public void a(long j) {
        if (this.F < 0) {
            this.F = j;
        }
    }

    @Override // com.huawei.openalliance.ad.jb
    public void a(int i, int i2) {
        com.huawei.openalliance.ad.views.interfaces.n q = q();
        if (q != null) {
            q.a(i, i2);
        }
        this.e.b(i, i2);
        a(Long.valueOf(this.i), 3);
        j();
        v();
    }

    @Override // com.huawei.openalliance.ad.jb
    public void a(int i) {
        this.E = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(ContentRecord contentRecord) {
        if (!this.g || !this.w || this.o == null || this.l.a() <= 0) {
            return;
        }
        if (ho.a()) {
            ho.b(z(), "reportSplashCostTime");
        }
        this.w = false;
        com.huawei.openalliance.ad.analysis.d.a(this.o, r(), this.l, this.C, contentRecord, this.n);
    }

    private void i(int i) {
        List<String> a2;
        if (this.k == null || this.o == null) {
            return;
        }
        new com.huawei.openalliance.ad.analysis.g(this.o).a(i, new com.huawei.openalliance.ad.analysis.a(this.C, (k() == null || (a2 = k().a()) == null || a2.isEmpty()) ? null : a2.get(0), this.n), r(), this.k);
    }

    private void h(ContentRecord contentRecord) {
        if (this.q == null) {
            return;
        }
        if (contentRecord != null && contentRecord.D() == 9) {
            this.q.a(mq.a(0.0f, true, mp.STANDALONE));
        } else if (contentRecord != null) {
            if (contentRecord.D() == 4 || contentRecord.D() == 2) {
                this.q.f();
            }
        }
    }

    private void h(int i) {
        String r;
        ContentRecord contentRecord;
        List<String> a2;
        if (this.o != null) {
            com.huawei.openalliance.ad.analysis.g gVar = new com.huawei.openalliance.ad.analysis.g(this.o);
            com.huawei.openalliance.ad.analysis.a aVar = new com.huawei.openalliance.ad.analysis.a(this.C, (k() == null || (a2 = k().a()) == null || a2.isEmpty()) ? null : a2.get(0), this.n);
            if (i == -6) {
                r = r();
                contentRecord = this.k;
            } else {
                r = r();
                contentRecord = this.D;
            }
            gVar.a(i, aVar, r, contentRecord);
        }
        g(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(ContentRecord contentRecord) {
        if (contentRecord == null) {
            return;
        }
        contentRecord.H();
        ArrayList arrayList = new ArrayList();
        arrayList.add("priority");
        arrayList.add(ContentRecord.LAST_SHOW_TIME);
        arrayList.add(ContentRecord.DISPLAY_COUNT);
        this.d.a(contentRecord, arrayList);
        this.d.a(contentRecord.n(), com.huawei.openalliance.ad.utils.ao.c());
    }

    private boolean f(ContentRecord contentRecord) {
        String str;
        Context context;
        int i;
        ho.b(z(), "checkMediaPath.");
        if (contentRecord.aO() == 3) {
            return true;
        }
        if (TextUtils.isEmpty(contentRecord.z())) {
            String y = contentRecord.y();
            if (TextUtils.isEmpty(y)) {
                context = this.o;
                i = -1;
            } else {
                if (!com.huawei.openalliance.ad.utils.cz.j(y)) {
                    str = y;
                } else {
                    if (a(contentRecord, this.o)) {
                        return false;
                    }
                    str = dk.d(y);
                }
                if (!y.startsWith(Scheme.CONTENT.toString())) {
                    String a2 = a(this.o, str, contentRecord.m(), contentRecord.l());
                    if (TextUtils.isEmpty(a2)) {
                        context = this.o;
                        i = 4;
                    } else {
                        contentRecord.j(a2);
                        contentRecord.i(str);
                    }
                }
            }
            com.huawei.openalliance.ad.analysis.d.a(context, contentRecord, i, this.n);
            return false;
        }
        ho.b(z(), "Spare splash is valid.");
        com.huawei.openalliance.ad.analysis.d.a(this.o, contentRecord, 200, this.n);
        return true;
    }

    private void b(boolean z) {
        this.x = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(ContentRecord contentRecord, Float f, int i) {
        if (f == null) {
            return i == 0;
        }
        if (i != 0 || f.floatValue() >= 1.0f) {
            return contentRecord.D() == 12 ? i == 0 : i == 1 && f.floatValue() > 1.0f;
        }
        return true;
    }

    private boolean a(ContentRecord contentRecord, Context context) {
        AdSlotParam k = k();
        if (k == null) {
            return true;
        }
        int b = k.b();
        int t = contentRecord.t();
        int s = contentRecord.s();
        if (b == 0 && (contentRecord.D() == 12 || s <= t)) {
            com.huawei.openalliance.ad.analysis.d.a(context, contentRecord, 2, this.n);
            return true;
        }
        if (b == 1 && contentRecord.D() != 12 && s > t) {
            com.huawei.openalliance.ad.analysis.d.a(context, contentRecord, 3, this.n);
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis >= contentRecord.r() && currentTimeMillis <= contentRecord.q()) {
            return false;
        }
        com.huawei.openalliance.ad.analysis.d.a(context, contentRecord, 1, this.n);
        return true;
    }

    private void a(Long l, int i) {
        a(l != null ? Long.valueOf(System.currentTimeMillis() - l.longValue()) : null, (Integer) 100, Integer.valueOf(i), (jk) q());
    }

    private void a(a.C0207a c0207a) {
        Object p = p();
        if (!(p instanceof View) || c0207a == null) {
            return;
        }
        c0207a.e(vd.b((View) p));
    }

    private void a(ContentRecord contentRecord, com.huawei.openalliance.ad.views.interfaces.l lVar, com.huawei.openalliance.ad.views.interfaces.n nVar) {
        if (contentRecord == null || nVar == null || this.q == null) {
            ho.c(z(), "there is no splash ad or adView is null");
            return;
        }
        ho.b(z(), "initOmsdkResource");
        this.q.a(l(), contentRecord, lVar, true);
        nVar.a(this.q);
    }

    private void a(final ContentRecord contentRecord, final int i) {
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.ix.2
            @Override // java.lang.Runnable
            public void run() {
                ho.b(ix.this.z(), "start report");
                if (ix.this.o == null) {
                    return;
                }
                new com.huawei.openalliance.ad.analysis.c(ix.this.o).a(contentRecord, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AdContentRsp adContentRsp, long j) {
        AdSlotParam k = k();
        if (k == null) {
            ho.c(z(), "onPostAdRequest adSlotParam is null");
        } else {
            this.s.a(adContentRsp, k, this, new qk() { // from class: com.huawei.openalliance.ad.ix.13
                @Override // com.huawei.openalliance.ad.qk
                public void a(final ContentRecord contentRecord) {
                    ho.b(ix.this.z(), "onDownloaded");
                    if (contentRecord != null) {
                        ho.a(ix.this.z(), "sdkMonitor is %s", Integer.valueOf(contentRecord.a()));
                    }
                    ix.this.l.u().j(com.huawei.openalliance.ad.utils.ao.c());
                    synchronized (ix.this) {
                        ho.b(ix.this.z(), "onDownloaded, loadingTimeout:" + ix.this.f);
                        ix.this.g = true;
                        if (ix.this.f) {
                            ix.this.l.a(-2);
                            ix.this.w = true;
                        } else {
                            com.huawei.openalliance.ad.utils.cv.a(ix.this.h);
                            ho.b(ix.this.z(), "cancel loadTimeoutTask");
                            ix.this.l.f(com.huawei.openalliance.ad.utils.ao.c());
                            if (contentRecord == null || 12 != contentRecord.D()) {
                                final long c = com.huawei.openalliance.ad.utils.ao.c();
                                com.huawei.openalliance.ad.utils.cv.a(ix.this.o, new Runnable() { // from class: com.huawei.openalliance.ad.ix.13.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        ix ixVar;
                                        ContentRecord contentRecord2;
                                        ix.this.l.g(com.huawei.openalliance.ad.utils.ao.c() - c);
                                        if (contentRecord != null) {
                                            ix.this.f = true;
                                            ixVar = ix.this;
                                            contentRecord2 = contentRecord;
                                        } else {
                                            if (ix.this.d(ErrorCode.ERROR_CODE_NO_AD_RECORD)) {
                                                return;
                                            }
                                            ixVar = ix.this;
                                            contentRecord2 = null;
                                        }
                                        ixVar.c(contentRecord2);
                                    }
                                });
                            } else {
                                ho.b(ix.this.z(), "notify linked ad on current thread");
                                ix.this.f = true;
                                ix.this.c(contentRecord);
                            }
                        }
                        ix.this.i(contentRecord);
                    }
                }
            }, j, 0);
        }
    }

    private void a(final AdSlotParam adSlotParam, final Context context) {
        OAIDServiceManager.getInstance(context).requireOaid(new OAIDServiceManager.OaidResultCallback() { // from class: com.huawei.openalliance.ad.ix.10
            @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
            public int b() {
                return ix.this.n;
            }

            @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
            public void a(String str, boolean z) {
                ho.b(ix.this.z(), "onOaidAcquired");
                adSlotParam.a(str);
                adSlotParam.a(Boolean.valueOf(z));
                ix.this.a(adSlotParam);
                com.huawei.openalliance.ad.utils.d.a(context, str, z);
            }

            @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
            public void a() {
                ho.b(ix.this.z(), "onOaidAcquireFailed");
                ix.this.a(adSlotParam);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final AdSlotParam adSlotParam) {
        final long currentTimeMillis = System.currentTimeMillis();
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.ix.11
            @Override // java.lang.Runnable
            public void run() {
                long currentTimeMillis2 = System.currentTimeMillis();
                ho.b(ix.this.z(), "doAdRequest " + currentTimeMillis2);
                ix.this.l.d(currentTimeMillis2 - currentTimeMillis);
                ix.this.C = com.huawei.openalliance.ad.utils.ao.a();
                adSlotParam.b(ix.this.C);
                ix.this.a(ix.this.s.a(ix.this.o, adSlotParam, ix.this.n), currentTimeMillis2);
                ix.this.G();
            }
        }, m.a.SPLASH_NET, false);
    }

    private void a(int i, final String str, Long l) {
        a(l, i);
        if (this.G) {
            ho.b(z(), str);
            return;
        }
        this.G = true;
        v();
        if (d() == 1) {
            com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.ix.8
                @Override // java.lang.Runnable
                public void run() {
                    ho.b(ix.this.z(), "%s pauseView", str);
                    if (ix.this.q() != null) {
                        ix.this.q().pauseView();
                    }
                }
            }, 150L);
        }
    }

    private void a(int i, int i2, ta taVar, Long l, MaterialClickInfo materialClickInfo, int i3) {
        a(l, 1);
        b.a aVar = new b.a();
        aVar.a(i).b(i2).b(taVar.c()).a(Integer.valueOf(i3)).a(materialClickInfo).d(com.huawei.openalliance.ad.utils.b.a(p())).e(F());
        this.e.a(aVar.a());
        qq qqVar = this.L;
        if (qqVar != null) {
            qqVar.a(aVar.a());
        }
        com.huawei.openalliance.ad.views.interfaces.l p = p();
        if (p != null) {
            p.a();
        }
        if (this.G) {
            ho.b(z(), "onDoActionSucc hasShowFinish");
            return;
        }
        this.G = true;
        g();
        if (d() == 1) {
            com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.ix.6
                @Override // java.lang.Runnable
                public void run() {
                    ho.b(ix.this.z(), "onDoActionSucc pauseView");
                    if (ix.this.q() != null) {
                        ix.this.q().pauseView();
                    }
                }
            }, 150L);
        }
        v();
    }

    private String a(final Context context, final String str, final String str2, final String str3) {
        try {
            return (String) com.huawei.openalliance.ad.utils.dc.c(new Callable<String>() { // from class: com.huawei.openalliance.ad.ix.7
                @Override // java.util.concurrent.Callable
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public String call() {
                    String c = dh.a(context, "normal").c(str);
                    if (com.huawei.openalliance.ad.utils.ae.c(context, c, "normal")) {
                        return c;
                    }
                    com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.ix.7.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (context != null) {
                                es.a(context).b(str2, str3);
                            }
                            com.huawei.openalliance.ad.utils.ae.a(context, str);
                        }
                    }, m.a.DISK_CACHE, false);
                    return "";
                }
            });
        } catch (Throwable unused) {
            ho.c(z(), "get media local path exception");
            return "";
        }
    }

    private com.huawei.openalliance.ad.views.interfaces.n a(ContentRecord contentRecord, com.huawei.openalliance.ad.views.interfaces.l lVar) {
        if (contentRecord == null) {
            return null;
        }
        com.huawei.openalliance.ad.views.interfaces.n a2 = lVar.a(contentRecord.D());
        if (a2 == null) {
            return a2;
        }
        a2.setAdContent(contentRecord);
        a2.setAdMediator(this);
        if (2 == contentRecord.D() || 4 == contentRecord.D()) {
            a2.setDisplayDuration((contentRecord.at() > 0 ? contentRecord.at() : 0) + (contentRecord.au() >= 2000 ? contentRecord.au() : this.c.ax()));
        }
        a(contentRecord, lVar, a2);
        return a2;
    }

    private void J() {
        try {
            if (this.J) {
                ho.b(z(), "already end");
                return;
            }
            com.huawei.openalliance.ad.views.interfaces.l p = p();
            if (p != null) {
                p.b();
            }
            ho.b(z(), "onAdEnd");
            this.J = true;
            rp rpVar = this.K;
            if (rpVar != null) {
                rpVar.b();
            }
            com.huawei.hms.ads.uiengine.e b = e.b();
            if (b != null) {
                b.b(this.n, (Bundle) null);
            }
        } catch (Throwable th) {
            ho.b(z(), "end err: %s", th.getClass().getSimpleName());
        }
    }

    private boolean I() {
        return this.x;
    }

    private void H() {
        CountDownTimer countDownTimer = new CountDownTimer(2000L, 500L) { // from class: com.huawei.openalliance.ad.ix.3
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                ho.a(ix.this.z(), "onTick = %s", Long.valueOf(j));
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                ho.b(ix.this.z(), "onFinish");
                if (ix.this.b != AdLoadState.LOADED) {
                    ix.this.c(-10);
                    ix.this.B();
                }
            }
        };
        this.t = countDownTimer;
        countDownTimer.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G() {
        com.huawei.openalliance.ad.utils.cv.a(new Runnable() { // from class: com.huawei.openalliance.ad.ix.12
            @Override // java.lang.Runnable
            public void run() {
                IHiAdSplash hiAdSplash = HiAdSplash.getInstance(ix.this.o);
                if (hiAdSplash instanceof HiAdSplash) {
                    HiAdSplash hiAdSplash2 = (HiAdSplash) hiAdSplash;
                    long bo = ix.this.c.bo();
                    long a2 = hiAdSplash2.a();
                    if (System.currentTimeMillis() - a2 >= bo) {
                        if (ix.this.n == 1) {
                            hiAdSplash2.preloadAd();
                            return;
                        } else {
                            hiAdSplash2.preloadSmartScreenAd();
                            return;
                        }
                    }
                    ho.b(ix.this.z(), "request time limit, timeInter=" + bo + ", lastTime=" + a2);
                }
            }
        }, (this.c.ax() * 3) + 1000);
    }

    private String F() {
        ContentRecord contentRecord;
        if (!this.I || (contentRecord = this.D) == null || 3 == contentRecord.aO() || this.D.aU() == null) {
            return null;
        }
        return this.D.aU().a();
    }

    private String E() {
        return com.huawei.openalliance.ad.utils.dd.a(p());
    }

    private void D() {
        if (this.D == null) {
            return;
        }
        long j = this.F;
        if (j <= 0) {
            j = com.huawei.openalliance.ad.utils.ao.c();
        }
        ho.a(z(), "recordShowStartTime: %s", Long.valueOf(j));
        this.D.c(String.valueOf(j));
        this.D.f(j);
        this.e.a(this.D);
    }

    private void C() {
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.ix.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ix ixVar = ix.this;
                    ixVar.K = new rp(ixVar.o);
                    ix.this.K.a();
                    com.huawei.hms.ads.uiengine.e b = e.b();
                    if (b != null) {
                        b.a(ix.this.n, (Bundle) null);
                    }
                } catch (Throwable th) {
                    ho.b(ix.this.z(), "inform err: %s", th.getClass().getSimpleName());
                }
            }
        });
    }

    public ix(com.huawei.openalliance.ad.views.interfaces.l lVar) {
        this.p = new WeakReference<>(lVar);
        this.n = lVar.getAdType();
        Context applicationContext = lVar.getContext().getApplicationContext();
        this.o = applicationContext;
        this.c = fh.b(applicationContext);
        this.d = es.a(this.o);
        this.e = new ou(this.o, new si(this.o, this.n));
        ol olVar = new ol(this.o);
        this.s = olVar;
        olVar.a(this.l);
        C();
    }
}
