package com.huawei.openalliance.ad.views;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hms.ads.uiengine.common.MediaStateApi;
import com.huawei.openalliance.ad.beans.metadata.AdSource;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.v3.Asset;
import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;
import com.huawei.openalliance.ad.constant.AdLoadMode;
import com.huawei.openalliance.ad.constant.AdLoadState;
import com.huawei.openalliance.ad.constant.ApiNames;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.gc;
import com.huawei.openalliance.ad.hl;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.inter.HiAdSplash;
import com.huawei.openalliance.ad.inter.IHiAdSplash;
import com.huawei.openalliance.ad.inter.listeners.AdActionListener;
import com.huawei.openalliance.ad.inter.listeners.AdListener;
import com.huawei.openalliance.ad.ix;
import com.huawei.openalliance.ad.iy;
import com.huawei.openalliance.ad.jb;
import com.huawei.openalliance.ad.jm;
import com.huawei.openalliance.ad.jn;
import com.huawei.openalliance.ad.jo;
import com.huawei.openalliance.ad.jp;
import com.huawei.openalliance.ad.jr;
import com.huawei.openalliance.ad.nb;
import com.huawei.openalliance.ad.nu;
import com.huawei.openalliance.ad.oh;
import com.huawei.openalliance.ad.ou;
import com.huawei.openalliance.ad.si;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.bi;
import com.huawei.openalliance.ad.utils.bm;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class SmartScreenSplashView extends RelativeLayout implements IViewLifeCycle, com.huawei.openalliance.ad.views.interfaces.l {
    private float A;
    private jp B;
    private ou C;

    /* renamed from: a, reason: collision with root package name */
    protected gc f8023a;
    private int b;
    private AdSlotParam c;
    private AdListener d;
    private jb e;
    private AdActionListener f;
    private oh g;
    private ad h;
    private View i;
    private View j;
    private com.huawei.openalliance.ad.views.interfaces.n k;
    private TextView l;
    private PPSCircleProgressBar m;
    private PPSLabelView n;
    private TextView o;
    private int p;
    private RelativeLayout q;
    private RelativeLayout r;
    private long s;
    private int t;
    private final String u;
    private long v;
    private boolean w;
    private boolean x;
    private int y;
    private a z;

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public Integer a(ContentRecord contentRecord) {
        return null;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public void a(int i, int i2, String str, boolean z, Integer num) {
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public void a(int i, boolean z) {
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public void a(View view, ContentRecord contentRecord) {
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public int getAdType() {
        return 18;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public int getAudioFocusType() {
        return 0;
    }

    @Override // com.huawei.openalliance.ad.lm
    public View getOpenMeasureView() {
        return this;
    }

    public String getUniqueId() {
        return null;
    }

    public void setStartMaxVol(float f) {
        if (f >= 0.0f) {
            if (f <= 1.0f) {
                AudioManager audioManager = (AudioManager) getContext().getSystemService(PresenterUtils.AUDIO);
                int streamMaxVolume = audioManager.getStreamMaxVolume(3);
                int streamVolume = audioManager.getStreamVolume(3);
                ho.b("SmartScreenSplashView", "music max %s, current %s， maxVol： %s", Integer.valueOf(streamMaxVolume), Integer.valueOf(streamVolume), Float.valueOf(f));
                float f2 = streamVolume;
                float f3 = streamMaxVolume * 1.0f * f;
                float floatValue = f2 * 1.0f >= f3 ? Float.valueOf(f3 / f2).floatValue() : 1.0f;
                if (ho.a()) {
                    ho.a("SmartScreenSplashView", "maxVol end: %s", Float.valueOf(floatValue));
                }
                this.A = floatValue;
                return;
            }
        }
        ho.c("SmartScreenSplashView", "valid max vol is from 0.0 to 1.0");
    }

    public void setSloganView(View view) {
        if (view != null) {
            this.i = view;
            view.setVisibility(8);
        }
    }

    public void setSloganResId(int i) {
        if (ao.b(getContext())) {
            if (dd.c(getContext())) {
                ho.c("SmartScreenSplashView", "setSloganResId - activity finished, not add view");
                return;
            }
            if (this.c == null) {
                throw new com.huawei.openalliance.ad.exception.b("Must invoke SplashAdView's setAdSlotParam method before invoke setSloganResId method");
            }
            if (this.h == null) {
                ad adVar = new ad(getContext(), this.c.b(), i, 18);
                this.h = adVar;
                this.q.addView(adVar, new RelativeLayout.LayoutParams(-1, -1));
                this.h.b();
            }
        }
    }

    public void setLogo(View view, int i) {
        this.j = view;
        view.setVisibility(i);
        this.p = i;
    }

    public void setLogo(View view) {
        setLogo(view, 0);
    }

    public void setLinkedSupportMode(int i) {
        this.b = i;
    }

    public void setAudioFocusType(int i) {
        this.y = i;
        com.huawei.openalliance.ad.views.interfaces.n nVar = this.k;
        if (nVar != null) {
            nVar.setAudioFocusType(i);
        }
    }

    public void setAdSlotParam(AdSlotParam adSlotParam) {
        if (ao.b(getContext())) {
            int a2 = com.huawei.openalliance.ad.utils.d.a(getContext(), adSlotParam.b());
            int b = com.huawei.openalliance.ad.utils.d.b(getContext(), adSlotParam.b());
            adSlotParam.b(a2);
            adSlotParam.c(b);
            adSlotParam.a(8);
            adSlotParam.b(Integer.valueOf(this.b));
            adSlotParam.c((Integer) 0);
            adSlotParam.d(Integer.valueOf((HiAd.a(getContext()).e() && com.huawei.openalliance.ad.utils.d.t(getContext())) ? 0 : 1));
            this.c = adSlotParam;
            IHiAdSplash hiAdSplash = HiAdSplash.getInstance(getContext());
            if (hiAdSplash instanceof HiAdSplash) {
                ((HiAdSplash) hiAdSplash).a(adSlotParam);
            }
        }
    }

    public void setAdListener(AdListener adListener) {
        this.d = adListener;
        this.g.a(adListener);
        jb jbVar = this.e;
        if (jbVar != null) {
            jbVar.a(adListener);
        }
    }

    public void setAdActionListener(AdActionListener adActionListener) {
        this.f = adActionListener;
        jb jbVar = this.e;
        if (jbVar != null) {
            jbVar.a(adActionListener);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void resumeView() {
        ho.b("SmartScreenSplashView", "resumeView ");
        com.huawei.openalliance.ad.views.interfaces.n nVar = this.k;
        if (nVar != null) {
            nVar.resumeView();
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void pauseView() {
        ho.b("SmartScreenSplashView", "pauseView ");
        com.huawei.openalliance.ad.views.interfaces.n nVar = this.k;
        if (nVar != null) {
            nVar.pauseView();
        }
        if (getContext() != null) {
            try {
                if (this.z != null) {
                    getContext().unregisterReceiver(this.z);
                    this.z = null;
                }
            } catch (Throwable th) {
                ho.c("SmartScreenSplashView", "unregister err: %s", th.getClass().getSimpleName());
            }
        }
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        jb jbVar;
        ho.b("SmartScreenSplashView", "onKeyDown, keyCode: %s", Integer.valueOf(keyEvent.getKeyCode()));
        if (this.x && 4 == keyEvent.getKeyCode() && keyEvent.getAction() == 1 && (jbVar = this.e) != null) {
            jbVar.a(0, 0);
        }
        return false;
    }

    public void loadAd() {
        AdSlotParam adSlotParam;
        this.s = System.currentTimeMillis();
        ho.b("SmartScreenSplashView", ApiNames.LOAD_AD);
        if (this.g.b()) {
            if (this.g.c() && (adSlotParam = this.c) != null) {
                Integer r = adSlotParam.r();
                ho.b("SmartScreenSplashView", "startMode %s", r);
                if ((r != null && r.intValue() == 0) || (r.intValue() == 1 && com.huawei.openalliance.ad.inter.c.a(getContext()).a())) {
                    d();
                    return;
                }
            }
            if (this.c != null) {
                bi.b(getContext().getApplicationContext(), this.c.l());
            }
            this.w = true;
            this.g.a();
        }
    }

    public boolean isLoading() {
        jb jbVar = this.e;
        return jbVar == null ? this.w : jbVar.b() == AdLoadState.LOADING;
    }

    public boolean isLoaded() {
        jb jbVar = this.e;
        return jbVar != null && jbVar.b() == AdLoadState.LOADED;
    }

    public float getStartMaxVol() {
        return this.A;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public ou getMonitorEp() {
        if (this.B instanceof jo) {
            return null;
        }
        return this.C;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public MediaStateApi getMediaProxy() {
        if (this.B instanceof jr) {
            return new jm((jr) this.B);
        }
        return null;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public AdSlotParam getAdSlotParam() {
        AdSlotParam adSlotParam = this.c;
        if (adSlotParam != null) {
            adSlotParam.d(18);
        }
        return this.c;
    }

    public AdListener getAdListener() {
        return this.d;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        jb jbVar;
        ho.b("SmartScreenSplashView", "dispatchKeyEvent:" + keyEvent.getKeyCode() + ", " + keyEvent.getAction());
        if (this.x && 4 == keyEvent.getKeyCode() && keyEvent.getAction() == 1 && (jbVar = this.e) != null) {
            jbVar.a(0, 0);
        }
        return true;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void destroyView() {
        ho.b("SmartScreenSplashView", "destroyView ");
        com.huawei.openalliance.ad.views.interfaces.n nVar = this.k;
        if (nVar != null) {
            nVar.destroyView();
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public void c() {
        ad adVar = this.h;
        if (adVar != null) {
            adVar.b();
        }
        View view = this.i;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public void b(int i) {
        ho.a("SmartScreenSplashView", "update left time, total: %s, left: %s", Long.valueOf(this.v), Integer.valueOf(i));
        long j = this.v;
        int doubleValue = j > 0 ? (int) ((1.0d - bm.a(Double.valueOf(((i - 1) * 1000) / j), 2, 4).doubleValue()) * 100.0d) : 0;
        if (doubleValue >= 100) {
            doubleValue = 100;
        }
        PPSCircleProgressBar pPSCircleProgressBar = this.m;
        if (pPSCircleProgressBar != null) {
            pPSCircleProgressBar.a(doubleValue, cz.a(Integer.valueOf(i)));
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public void b() {
        jp jpVar = this.B;
        if (jpVar != null) {
            jpVar.b();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public void a(com.huawei.openalliance.ad.views.interfaces.n nVar, ContentRecord contentRecord) {
        if (dd.c(getContext())) {
            ho.c("SmartScreenSplashView", "showAdView - activity finished, not add view");
            return;
        }
        if (nVar == 0 || !(nVar instanceof View)) {
            return;
        }
        View view = (View) nVar;
        this.k = nVar;
        a(false, view, contentRecord);
        nVar.setAudioFocusType(this.y);
        a(this.k);
        ViewParent parent = view.getParent();
        if (parent == this.q) {
            view.setVisibility(0);
            return;
        }
        if (parent != null && (parent instanceof ViewGroup)) {
            ho.b("SmartScreenSplashView", "showAdView, remove adView.");
            ((ViewGroup) parent).removeView(view);
        } else if (parent != null) {
            return;
        }
        setVisibleAndBringToFont(this.r);
        setVisibleAndBringToFont(this.j);
        this.q.addView(view, new RelativeLayout.LayoutParams(-1, -1));
        view.setVisibility(0);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public void a(hl hlVar) {
        View view = this.i;
        if (view != null) {
            view.setVisibility(0);
            new nb(this.f8023a, hlVar).a();
            return;
        }
        ad adVar = this.h;
        if (adVar == null) {
            ho.b("SmartScreenSplashView", "create default slogan");
            setSloganResId(R.drawable._2131428527_res_0x7f0b04af);
            adVar = this.h;
            if (adVar == null) {
                return;
            }
        }
        adVar.setSloganShowListener(hlVar);
        this.h.a();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public void a(ContentRecord contentRecord, int i) {
        ho.a("SmartScreenSplashView", "showLabelView and logo.");
        if (this.l != null && this.m != null) {
            if (contentRecord != null && contentRecord.h() != null && contentRecord.D() == 9) {
                long x = contentRecord.h().x();
                this.v = x;
                this.m.a(0, cz.a(Integer.valueOf((int) ((x * 1.0f) / 1000.0f))));
            }
            e();
        }
        if (this.r != null && this.j != null) {
            ho.b("SmartScreenSplashView", "show logo, visibility: %s", Integer.valueOf(this.p));
            this.r.addView(this.j);
            this.j.setVisibility(this.p);
        }
        c(contentRecord);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public void a(AdLoadMode adLoadMode) {
        ix a2 = iy.a(adLoadMode, this);
        this.e = a2;
        a2.a(this.d);
        this.e.a(this.f);
        this.e.a(this.b);
        this.e.b(this.s);
        this.e.A();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public void a() {
        jp jpVar = this.B;
        if (jpVar != null) {
            jpVar.a();
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.l
    public com.huawei.openalliance.ad.views.interfaces.n a(int i) {
        if (i == 2) {
            return new q(getContext(), 18);
        }
        if (i != 9) {
            return null;
        }
        v vVar = new v(getContext(), this.c.b(), 0, this.c.d(), 18);
        vVar.setHideSoundIcon(true);
        vVar.setIgnoreSoundCtrl(false);
        vVar.setStartVol(this.A);
        return vVar;
    }

    private void setVisibleAndBringToFont(View view) {
        if (view != null) {
            view.setVisibility(0);
            view.bringToFront();
        }
    }

    private void e() {
        if (this.l == null || this.m == null) {
            return;
        }
        int i = this.t;
        if (i > 0) {
            ho.b("SmartScreenSplashView", "%d delay, skip btn show", Integer.valueOf(i));
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.SmartScreenSplashView.1
                @Override // java.lang.Runnable
                public void run() {
                    if (SmartScreenSplashView.this.l != null) {
                        ho.a("SmartScreenSplashView", "skip hint show");
                        SmartScreenSplashView.this.l.setVisibility(0);
                    }
                    if (SmartScreenSplashView.this.m != null) {
                        ho.a("SmartScreenSplashView", "coutDownView show");
                        SmartScreenSplashView.this.m.setVisibility(0);
                    }
                    SmartScreenSplashView.this.x = true;
                }
            }, this.u, this.t);
        } else {
            ho.b("SmartScreenSplashView", "direct show skip hint");
            this.x = true;
            this.l.setVisibility(0);
            this.m.setVisibility(0);
        }
    }

    private void d() {
        List<String> a2 = this.c.a();
        this.g.a(!bg.a(a2) ? a2.get(0) : null, 18);
        this.g.h();
        jp jpVar = this.B;
        if (jpVar != null) {
            jpVar.b();
        }
        com.huawei.openalliance.ad.inter.c.a(getContext().getApplicationContext()).a(false);
    }

    private void c(ContentRecord contentRecord) {
        MetaData h;
        if (contentRecord == null) {
            return;
        }
        if (this.n != null) {
            String N = contentRecord.N();
            this.n.setDataAndRefreshUi(contentRecord);
            if (TextUtils.isEmpty(N) || !this.n.a()) {
                this.n.setVisibility(8);
            } else {
                MetaData h2 = contentRecord.h();
                if (h2 == null || AdSource.a(h2.K()) == null) {
                    this.n.setText(N);
                } else {
                    this.n.a(AdSource.a(h2.K()), N);
                }
                this.n.setVisibility(0);
            }
        }
        if (this.o == null || (h = contentRecord.h()) == null) {
            return;
        }
        String c = cz.c(h.k());
        if (TextUtils.isEmpty(c)) {
            this.o.setVisibility(8);
        } else {
            this.o.setText(c);
            this.o.setVisibility(0);
        }
    }

    private boolean b(ContentRecord contentRecord) {
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

    private void b(Context context) {
        inflate(context, ao.o(context) ? R.layout.hiad_view_tv_splash_ad_elderly : R.layout.hiad_view_tv_splash_ad, this);
        this.q = (RelativeLayout) findViewById(R.id.rl_splash_container);
        this.r = (RelativeLayout) findViewById(R.id.hiad_logo_container);
        this.l = (TextView) findViewById(R.id.hiad_skip_text);
        this.m = (PPSCircleProgressBar) findViewById(R.id.hiad_count_progress);
        this.n = (PPSLabelView) findViewById(R.id.hiad_ad_label);
        this.o = (TextView) findViewById(R.id.hiad_ad_source);
        setFocusable(true);
    }

    private void a(boolean z, View view, ContentRecord contentRecord) {
        if (contentRecord == null) {
            ho.b("SmartScreenSplashView", "getMonitor, contentRecord is null.");
            return;
        }
        this.C.a(contentRecord.a());
        jp a2 = jn.a(getContext(), b(contentRecord), this.C, contentRecord, z);
        this.B = a2;
        a2.a(view);
        if (view instanceof v) {
            this.B.a(((v) view).getVideoView());
        }
        jn.a(contentRecord.m(), this.B);
    }

    private void a(com.huawei.openalliance.ad.views.interfaces.n nVar) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.VOLUME_CHANGED_ACTION);
        if (this.z == null) {
            this.z = new a(nVar);
        }
        ao.a(getContext(), this.z, intentFilter);
    }

    private void a(Context context) {
        b(context);
        this.f8023a = fh.b(context.getApplicationContext());
        this.g = new nu(context.getApplicationContext(), this);
        this.t = this.f8023a.bW();
        this.C = new ou(context, new si(context, 1));
    }

    static class a extends BroadcastReceiver {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<com.huawei.openalliance.ad.views.interfaces.n> f8025a;

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            com.huawei.openalliance.ad.views.interfaces.n nVar;
            if (intent == null || !Constants.VOLUME_CHANGED_ACTION.equals(intent.getAction()) || (nVar = this.f8025a.get()) == null || !(nVar instanceof v)) {
                return;
            }
            ((v) nVar).j();
        }

        public a(com.huawei.openalliance.ad.views.interfaces.n nVar) {
            this.f8025a = new WeakReference<>(nVar);
        }
    }

    public SmartScreenSplashView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = 0;
        this.p = 0;
        this.t = 0;
        this.u = "skip_btn_delay_id_" + hashCode();
        this.w = false;
        this.x = false;
        this.y = 1;
        this.A = 0.18f;
        a(context);
    }

    public SmartScreenSplashView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = 0;
        this.p = 0;
        this.t = 0;
        this.u = "skip_btn_delay_id_" + hashCode();
        this.w = false;
        this.x = false;
        this.y = 1;
        this.A = 0.18f;
        a(context);
    }

    public SmartScreenSplashView(Context context) {
        super(context);
        this.b = 0;
        this.p = 0;
        this.t = 0;
        this.u = "skip_btn_delay_id_" + hashCode();
        this.w = false;
        this.x = false;
        this.y = 1;
        this.A = 0.18f;
        a(context);
    }
}
