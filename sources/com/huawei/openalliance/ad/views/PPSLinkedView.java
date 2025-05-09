package com.huawei.openalliance.ad.views;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.DisplayCutout;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.health.R;
import com.huawei.openalliance.ad.beans.metadata.InteractCfg;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.cb;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.RTCMethods;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.dc;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.gq;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.inter.data.IAd;
import com.huawei.openalliance.ad.inter.data.ILinkedSplashAd;
import com.huawei.openalliance.ad.inter.data.LinkedSplashAd;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.inter.listeners.AdActionListener;
import com.huawei.openalliance.ad.inter.listeners.AdShowListener;
import com.huawei.openalliance.ad.inter.listeners.LinkedAdListener;
import com.huawei.openalliance.ad.ja;
import com.huawei.openalliance.ad.jb;
import com.huawei.openalliance.ad.jd;
import com.huawei.openalliance.ad.jk;
import com.huawei.openalliance.ad.media.MediaPlayerAgent;
import com.huawei.openalliance.ad.media.listener.ILinkedMediaStateListener;
import com.huawei.openalliance.ad.media.listener.MuteListener;
import com.huawei.openalliance.ad.ms;
import com.huawei.openalliance.ad.nz;
import com.huawei.openalliance.ad.os;
import com.huawei.openalliance.ad.oz;
import com.huawei.openalliance.ad.ro;
import com.huawei.openalliance.ad.rp;
import com.huawei.openalliance.ad.th;
import com.huawei.openalliance.ad.ti;
import com.huawei.openalliance.ad.uc;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.views.i;
import com.huawei.openalliance.ad.views.interfaces.IAppDownloadButton;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class PPSLinkedView extends i {

    public interface OnLinkedAdClickListener {
        void onClick(int i);
    }

    public interface OnLinkedAdPreparedListener {
        void onPrepared();
    }

    public interface OnLinkedAdSwitchListener {
        void onSwitch(int i);
    }

    public void unregister(IAppDownloadButton iAppDownloadButton) {
        ho.b("PPSLinkedView", "begin unregister appDownloadButton");
        if (iAppDownloadButton == null || iAppDownloadButton != this.w) {
            return;
        }
        this.w.setPpsLinkedView(null);
        this.w.setNativeAd(null);
        this.w = null;
        this.ap = false;
    }

    public void unregister() {
        ho.b("PPSLinkedView", "unregister. ");
        O();
    }

    public void unmute() {
        ho.b("PPSLinkedView", "call unmute. ");
        this.e.c(this.o);
    }

    public void stopView() {
        ho.b("PPSLinkedView", "stopView, LinkedState: %s, isStartScale: %s", Integer.valueOf(this.ad), Boolean.valueOf(this.au));
        if (this.ad == 1) {
            if (!this.ak && this.N && !this.au) {
                ho.a("PPSLinkedView", "report imp on splash. ");
                a((Integer) 8, false);
                this.p.a(System.currentTimeMillis() - this.Q, 100);
                this.Q = -1L;
            }
            I();
            if (this.au && this.av != null) {
                this.aA = false;
                this.av.end();
            }
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSLinkedView.8
                @Override // java.lang.Runnable
                public void run() {
                    PPSLinkedView.this.c(false);
                }
            }, 200L);
            this.as = true;
            this.ad = 2;
        }
        if (this.B != null) {
            this.B.setVisibility(8);
            this.B = null;
        }
        if (this.H != null) {
            this.H = null;
        }
        if (this.L != null) {
            this.L = null;
        }
        this.e.g();
        if (this.l != null) {
            this.l.j(false);
        }
    }

    public void start(Context context) {
        if (this.an) {
            ho.c("PPSLinkedView", "register failed, can't start now");
            return;
        }
        if (this.ao || !this.aw) {
            ho.c("PPSLinkedView", "Already started:%s, isPrepared:%s, can't start now", Boolean.valueOf(this.ao), Boolean.valueOf(this.aw));
            return;
        }
        if (!(context instanceof Activity)) {
            ho.c("PPSLinkedView", "context not activity");
            x();
            i();
            unregister();
            return;
        }
        this.aW = new WeakReference<>(context);
        this.ao = true;
        if (this.m != null && this.m.t()) {
            ho.c("PPSLinkedView", "already started play normal ad. ");
            x();
            i();
            unregister();
            return;
        }
        Object[] objArr = new Object[1];
        objArr[0] = Boolean.valueOf(this.m == null);
        ho.b("PPSLinkedView", "start after prepare, mediator: %S", objArr);
        if (this.B != null) {
            ho.b("PPSLinkedView", "test, mediator: %S", this.B.getAdMediator());
        }
        if (this.m instanceof ja) {
            ho.b("PPSLinkedView", "start, set shown");
            ((ja) this.m).b(true);
        }
        if (!w()) {
            ho.c("PPSLinkedView", "exsplash unable user info or over time, can't start now");
            return;
        }
        this.ba = false;
        this.g = context.getApplicationContext();
        this.C = (WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        this.C.addView(this.v, b(context));
        a(context);
        a(this.l, this.h.az());
        v();
        b(true);
    }

    public void start() {
        ho.b("PPSLinkedView", "start. ");
        if (this.an || this.ao) {
            ho.c("PPSLinkedView", "can't start now, reg fail: %s, already start: %s", Boolean.valueOf(this.an), Boolean.valueOf(this.ao));
            return;
        }
        this.ao = true;
        if (this.m == null && this.B != null && this.B.getAdMediator() != null) {
            ho.b("PPSLinkedView", "set mediator.");
            this.m = this.B.getAdMediator();
        }
        if (this.l != null && this.l.isFromExsplash()) {
            this.I = HiAd.a(this.g).h();
            this.M = HiAd.a(this.g).i();
            this.al = HiAd.a(this.g).g();
            this.l.setListener(this.al);
        }
        if (this.m != null && this.m.t()) {
            ho.c("PPSLinkedView", "already started play normal ad. ");
            x();
            i();
            unregister();
            return;
        }
        ho.b("PPSLinkedView", "start, adMediator:%s, splashView:%s", this.m, this.B);
        if (this.B != null) {
            ho.b("PPSLinkedView", "start, splashView.getAdMediator():%s", this.B.getAdMediator());
        }
        if (this.m instanceof ja) {
            ho.b("PPSLinkedView", "start, set shown");
            ((ja) this.m).b(true);
        }
        if (u()) {
            return;
        }
        e(this.l.I());
        this.ba = false;
        this.ad = 1;
        this.ae = (((double) this.o.d()) < -1.0E-7d || ((double) this.o.d()) > 1.0E-7d) ? (int) (this.o.d() * 1000.0f) : IEventListener.EVENT_ID_NOTICE_DIALOG_SHOW;
        this.G = new i.d(this.ae, 1000L);
        z();
        if (!w()) {
            ho.c("PPSLinkedView", "exsplash unable user info or over time, can't start now");
            return;
        }
        this.C.addView(this.v, b(this.g));
        this.aW = new WeakReference<>(getContext());
        a(this.g);
        a(this.l, this.h.az());
        v();
        b(false);
    }

    public void setOnLinkedAdSwitchListener(OnLinkedAdSwitchListener onLinkedAdSwitchListener) {
        this.q = onLinkedAdSwitchListener;
    }

    public void setOnLinkedAdPreparedListener(OnLinkedAdPreparedListener onLinkedAdPreparedListener) {
        this.s = onLinkedAdPreparedListener;
    }

    public void setOnLinkedAdClickListener(OnLinkedAdClickListener onLinkedAdClickListener) {
        this.r = onLinkedAdClickListener;
    }

    public void setMuteOnlyOnLostAudioFocus(boolean z) {
        this.ay = z;
    }

    public void setLinkedAdActionListener(AdActionListener adActionListener) {
        ho.b("PPSLinkedView", "setLinkedAdActionListener. ");
        if (this.p != null) {
            this.p.a(adActionListener);
        }
    }

    public void setAdShowListener(AdShowListener adShowListener) {
        ho.b("PPSLinkedView", "setAdShowListener. ");
        if (this.p != null) {
            this.p.a(adShowListener);
        }
    }

    public void resumeView() {
        ho.b("PPSLinkedView", "resumeView, LinkedState: %s", Integer.valueOf(this.ad));
        if (this.as) {
            this.ad = 2;
        }
        if (this.ad == 2) {
            this.e.a(this.o);
            if (this.k == null) {
                G();
            }
            if (this.k != null) {
                this.k.f();
            }
        } else if (this.ad == 1 && this.S) {
            I();
            if (this.au && this.av != null) {
                this.av.end();
            }
            c(true);
            if (this.al != null) {
                this.al.onAdDismissed();
            }
            this.ad = 2;
        }
        if (this.B != null) {
            this.B = null;
        }
        if (this.H != null) {
            this.H = null;
        }
        if (this.L != null) {
            this.L = null;
        }
    }

    public void registerSplashView(PPSSplashView pPSSplashView) {
        ho.b("PPSLinkedView", "begin register splashView");
        if (this.aq) {
            ho.c("PPSLinkedView", "Already registered splashView, can't register now");
            return;
        }
        this.aq = true;
        this.B = pPSSplashView;
        if (pPSSplashView != null) {
            if (pPSSplashView.getAdListener() instanceof LinkedAdListener) {
                this.al = (LinkedAdListener) pPSSplashView.getAdListener();
            }
            this.I = pPSSplashView.getLogoResId();
            this.M = pPSSplashView.getMediaNameResId();
            this.L = pPSSplashView.getLogo();
            this.H = pPSSplashView.getSloganView();
            this.m = pPSSplashView.getAdMediator();
            this.n = pPSSplashView.getAudioFocusType();
        }
        ho.b("PPSLinkedView", "register PPSSplashView, linkedAdListener: %s. ", cz.b(this.al));
    }

    public boolean register(IAppDownloadButton iAppDownloadButton) {
        ho.b("PPSLinkedView", "begin register appDownloadButton");
        boolean z = false;
        if (this.ap) {
            ho.c("PPSLinkedView", "Already registered appDownloadButton, can't register now");
            return false;
        }
        this.ap = true;
        if (this.l == null) {
            throw new IllegalStateException("Register INativeAd first");
        }
        this.w = iAppDownloadButton;
        if (iAppDownloadButton != null) {
            iAppDownloadButton.setPpsLinkedView(this);
            z = iAppDownloadButton.setNativeAd(this.l);
        }
        if (ho.a()) {
            ho.a("PPSLinkedView", "register downloadbutton, succ:%s", Boolean.valueOf(z));
        }
        return z;
    }

    public void register(ILinkedSplashAd iLinkedSplashAd, List<View> list, final PPSDestView pPSDestView) {
        ho.b("PPSLinkedView", "register, linkedSplashAd: %s, clickableViews: %s, destView: %s. ", iLinkedSplashAd, list, pPSDestView);
        if (this.am) {
            ho.c("PPSLinkedView", "Already registered, can't register now");
            return;
        }
        if (iLinkedSplashAd == null || iLinkedSplashAd.getVideoInfo() == null) {
            ho.c("PPSLinkedView", "register failed, ad is null");
            return;
        }
        this.am = true;
        if (this.l == null) {
            register(iLinkedSplashAd);
        }
        if (pPSDestView == null) {
            ho.c("PPSLinkedView", "register failed, destView is null");
            this.an = true;
            return;
        }
        this.z = pPSDestView;
        setDestViewClickable(pPSDestView);
        this.e.a(this.g, iLinkedSplashAd);
        if (list != null) {
            this.A = list;
            a(list);
        }
        this.v = new ah(this.g);
        a(iLinkedSplashAd);
        r();
        t();
        q();
        this.J = this.v.getViewStub();
        s();
        setSplashViewClickable(this.v);
        ho.b("PPSLinkedView", "add view");
        pPSDestView.post(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSLinkedView.2
            @Override // java.lang.Runnable
            public void run() {
                PPSDestView pPSDestView2;
                ho.a("PPSLinkedView", "destView post");
                if (!dd.c() || (pPSDestView2 = pPSDestView) == null || pPSDestView2.getX() >= 0.0f) {
                    return;
                }
                PPSLinkedView.this.A();
                pPSDestView.setX((PPSLinkedView.this.aa - Math.abs(pPSDestView.getX())) - pPSDestView.getWidth());
            }
        });
        pPSDestView.addView(this.e.f(), getDestViewParam());
    }

    public void register(ILinkedSplashAd iLinkedSplashAd) {
        ho.b("PPSLinkedView", "register, linkedSplashAd: %s. ", iLinkedSplashAd);
        if (this.ar) {
            ho.c("PPSLinkedView", "Already registered ad, can't register now");
            return;
        }
        this.ar = true;
        if (iLinkedSplashAd instanceof LinkedSplashAd) {
            this.l = (LinkedSplashAd) iLinkedSplashAd;
            this.l.setListener(this.al);
            this.o = this.l.getVideoInfo();
            if (this.o != null && !this.o.e()) {
                this.F = false;
            }
            this.p.a(this.l);
            P();
            this.p.h();
        }
        Q();
    }

    public void prepare() {
        if (this.an) {
            ho.c("PPSLinkedView", "register failed, can't prepare now");
            return;
        }
        if (this.aw) {
            ho.c("PPSLinkedView", "already prepared");
            return;
        }
        if (this.l != null && this.l.isFromExsplash()) {
            this.al = HiAd.a(this.g).g();
            this.l.setListener(this.al);
        }
        if (!y()) {
            ho.c("PPSLinkedView", "prepare check failed");
            return;
        }
        if (this.l == null || this.o == null) {
            ho.c("PPSLinkedView", "prepare, linkedSplashAd is null");
            return;
        }
        e(this.l.I());
        ho.b("PPSLinkedView", "start prepare");
        z();
        this.e.b();
    }

    public void play() {
        ho.b("PPSLinkedView", "call play. ");
        this.e.h();
    }

    public void pause() {
        ho.b("PPSLinkedView", "call pause. ");
        this.e.g();
    }

    public boolean p() {
        if (this.k != null) {
            return this.k.n();
        }
        return false;
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        ho.a("PPSLinkedView", "onVisibilityChanged:");
        if (this.k != null) {
            this.k.j();
        }
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ho.a("PPSLinkedView", "onDetechedFromWindow");
        if (this.k != null) {
            this.k.i();
        }
        dj.a(this.c);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ho.a("PPSLinkedView", "onAttachedToWindow");
        if (this.k != null) {
            this.k.h();
        }
        dj.a(this.c);
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        ho.b("PPSLinkedView", "onApplyWindowInsets, sdk: %s", Integer.valueOf(Build.VERSION.SDK_INT));
        if (dd.b() && windowInsets != null) {
            DisplayCutout displayCutout = windowInsets.getDisplayCutout();
            if (displayCutout != null) {
                List<Rect> boundingRects = displayCutout.getBoundingRects();
                if (!bg.a(boundingRects)) {
                    this.U = boundingRects.get(0).height();
                }
            } else {
                ho.b("PPSLinkedView", "DisplayCutout is null");
            }
        }
        if (this.U <= 0 && bz.a(this.g).a(this.g)) {
            this.U = Math.max(this.U, bz.a(this.g).a(this));
        }
        ho.b("PPSLinkedView", "notchHeight:" + this.U);
        return super.onApplyWindowInsets(windowInsets);
    }

    public void o() {
        ho.b("PPSLinkedView", "onClose");
        N();
        this.p.c();
        a((Integer) 3, true);
        this.d.c();
    }

    @Override // com.huawei.openalliance.ad.views.i
    public /* bridge */ /* synthetic */ ah n() {
        return super.n();
    }

    public void mute() {
        ho.b("PPSLinkedView", "call mute. ");
        this.e.b(this.o);
    }

    @Override // com.huawei.openalliance.ad.views.i
    public /* bridge */ /* synthetic */ jb m() {
        return super.m();
    }

    @Override // com.huawei.openalliance.ad.views.i
    protected void j() {
        ho.b("PPSLinkedView", "startScaleDown. ");
        I();
        if (!C()) {
            if (this.ak || this.Q == -1) {
                return;
            }
            this.p.a(System.currentTimeMillis() - this.Q, 100);
            this.Q = -1L;
            return;
        }
        this.au = true;
        g();
        this.v.setClickable(false);
        this.av = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.av.setInterpolator(new gq(0.4f, 0.0f, 0.2f, 1.0f));
        this.av.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.openalliance.ad.views.PPSLinkedView.5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                try {
                    float animatedFraction = (valueAnimator.getAnimatedFraction() * (PPSLinkedView.this.af - 1.0f)) + 1.0f;
                    float animatedFraction2 = (valueAnimator.getAnimatedFraction() * (PPSLinkedView.this.ah - 1.0f)) + 1.0f;
                    PPSLinkedView.this.y.a(animatedFraction, valueAnimator.getAnimatedFraction() * PPSLinkedView.this.ag, animatedFraction2, (int) (PPSLinkedView.this.aa * animatedFraction2), (int) (PPSLinkedView.this.W * animatedFraction));
                } catch (Throwable th) {
                    ho.b("PPSLinkedView", "scaleAndTransAnimation err: %s", th.getClass().getSimpleName());
                }
            }
        });
        H();
        this.av.setDuration(1000L).start();
    }

    @Override // com.huawei.openalliance.ad.views.i
    protected void i() {
        ho.b("PPSLinkedView", "reportDisplayError, adMediator: %s, linkedAdListener: %s", cz.b(this.m), cz.b(this.al));
        if (!this.aF && this.m != null) {
            ho.b("PPSLinkedView", "report display error. ");
            this.aF = true;
            this.m.c(-3);
            this.m.B();
            return;
        }
        if (this.aF) {
            return;
        }
        ho.b("PPSLinkedView", "report fail to display. ");
        this.aF = true;
        c(-3);
    }

    @Override // com.huawei.openalliance.ad.views.i
    protected void h() {
        if (this.ad != 1 || this.aj) {
            return;
        }
        this.aj = true;
        I();
        this.ad = 0;
        this.e.i();
        F();
        if (this.x != null) {
            this.x.i();
        }
        this.B = null;
        this.H = null;
        this.L = null;
        R();
        if (this.aH != null) {
            this.aH.a();
        }
        if (this.ak || !this.N) {
            return;
        }
        ho.a("PPSLinkedView", "report imp and phyImp on splash. ");
        this.p.a(System.currentTimeMillis() - this.P, 100);
        a((Integer) 8, false);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IPPSLinkedView
    public String getSplashViewSlotPosition() {
        return dd.a((jk) this.B);
    }

    @Override // com.huawei.openalliance.ad.views.i
    protected void g() {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        int i;
        ho.b("PPSLinkedView", "calculateScaleAndTrans");
        A();
        if (this.W <= 0.0f || this.aa <= 0.0f) {
            ho.c("PPSLinkedView", "calculateScaleAndTrans, get screen size failed. ");
            i();
            unregister();
            return;
        }
        boolean m = dd.m(this.g);
        ho.b("PPSLinkedView", "calculateScaleAndTrans, MultiWindow:%s, screenHeight:%s,  screenWidth:%s", Boolean.valueOf(m), Float.valueOf(this.W), Float.valueOf(this.aa));
        this.z.getLocationOnScreen(this.ai);
        this.ab = this.z.getHeight();
        this.ac = this.z.getWidth();
        ho.b("PPSLinkedView", "calculateScaleAndTrans, destViewHeight:%s, destViewWidth:%s, locationX:%s, locationY:%s", Integer.valueOf(this.ab), Integer.valueOf(this.ac), Integer.valueOf(this.ai[0]), Integer.valueOf(this.ai[1]));
        Point point = new Point();
        this.C.getDefaultDisplay().getRealSize(point);
        ho.a("PPSLinkedView", "calculateScaleAndTrans, screenHeight:%s, point.y:%s", Float.valueOf(this.W), Integer.valueOf(point.y));
        if (this.U <= 0 && bz.a(this.g).a(this.g)) {
            this.U = Math.max(this.U, bz.a(this.g).a(this));
        }
        if ((point.y - this.U) - this.W > dd.j(this.g)) {
            this.V = dd.g(this.g);
        } else {
            this.V = 0;
        }
        ho.b("PPSLinkedView", "calculateScaleAndTrans, NotchEnable: %s, scrennHeight:%s, screenWidth:%s, navigationBarHeight:%s, notchHeight:%s", Boolean.valueOf(bz.a(this.g).a(this.g)), Float.valueOf(this.W), Float.valueOf(this.aa), Integer.valueOf(this.V), Integer.valueOf(this.U));
        if (bz.a(this.g).a(this.g)) {
            if (m) {
                this.af = (this.ab * 1.0f) / (this.W + this.U);
                f3 = this.ai[1] + ((this.ab * 1.0f) / 2.0f);
                f5 = this.W;
                i = this.U;
            } else {
                this.af = (this.ab * 1.0f) / ((this.W + this.U) + this.V);
                f3 = this.ai[1] + ((this.ab * 1.0f) / 2.0f);
                f5 = this.W + this.U;
                i = this.V;
            }
            f4 = ((f5 + i) * 1.0f) / 2.0f;
        } else {
            if (m) {
                this.af = (this.ab * 1.0f) / this.W;
                f = this.ai[1] + ((this.ab * 1.0f) / 2.0f);
                f2 = this.W;
            } else {
                this.af = (this.ab * 1.0f) / (this.V + this.W);
                f = this.ai[1] + ((this.ab * 1.0f) / 2.0f);
                f2 = this.W + this.V;
            }
            f3 = f - ((f2 * 1.0f) / 2.0f);
            f4 = this.U;
        }
        this.ag = f3 - f4;
        this.ah = ((this.ac * 1.0f) / this.aa) * 1.0f;
    }

    @Override // com.huawei.openalliance.ad.views.i
    protected void f() {
        if (!this.S) {
            this.S = true;
            if (this.l != null && this.l.isFromExsplash()) {
                ms.a(this.g).a(RTCMethods.DISMISS_SLOGAN, null, null, null);
            }
            this.Q = System.currentTimeMillis();
            if (!this.h.bb()) {
                a(null, null, 8, false);
                this.ak = true;
            }
            if (!this.ba && this.ad == 1) {
                J();
            }
        }
        if (this.H != null) {
            this.H.setVisibility(8);
            this.H = null;
        }
        if (this.B != null) {
            ho.a("PPSLinkedView", "PPSSplashView is not null. ");
            this.B.setVisibility(8);
            this.B = null;
        }
        if (this.L != null) {
            this.L.setVisibility(8);
            this.L = null;
        }
    }

    @Override // com.huawei.openalliance.ad.jd.a
    public void e() {
        ho.b("PPSLinkedView", "onViewShownBetweenFullAndPartial: ");
        this.e.d(this.o);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            int a2 = th.a(motionEvent);
            if (a2 == 0) {
                this.f = th.a(this, motionEvent);
            }
            if (1 == a2) {
                th.a(this, motionEvent, null, this.f);
            }
        } catch (Throwable th) {
            ho.c("PPSLinkedView", "dispatchTouchEvent exception : %s", th.getClass().getSimpleName());
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void destroyView() {
        ho.b("PPSLinkedView", "destroyView. ");
        unregister();
    }

    @Override // com.huawei.openalliance.ad.jd.a
    public void d() {
        ho.b("PPSLinkedView", "onViewPartialHidden: ");
        this.e.e(this.o);
    }

    @Override // com.huawei.openalliance.ad.jd.a
    public void c() {
        ho.b("PPSLinkedView", "onViewFullShown: ");
        this.e.a(this.o, this.az);
    }

    @Override // com.huawei.openalliance.ad.jd.a
    public void b(long j, int i) {
        ho.b("PPSLinkedView", "onViewPhysicalShowEnd: ");
        dj.a(this.b);
        if (this.l != null) {
            this.l.j(false);
        }
        this.e.e();
        ho.a("PPSLinkedView", "onViewPhysicalShowEnd, noPhyImp: %s. ", Boolean.valueOf(this.ak));
        if (this.ak || i <= 0) {
            return;
        }
        ho.a("PPSLinkedView", "report phyImp. ");
        if (this.Q == -1) {
            this.p.a(j, i);
        } else {
            this.p.a(System.currentTimeMillis() - this.Q, i);
            this.Q = -1L;
        }
    }

    @Override // com.huawei.openalliance.ad.jd.a
    public void b() {
        ho.b("PPSLinkedView", "onViewPhysicalShowStart");
        if (!this.aj || this.l == null || this.l.ag()) {
            return;
        }
        l();
        k();
    }

    public void addMuteListener(MuteListener muteListener) {
        this.u = muteListener;
    }

    public void addLinkedMediaStateListener(ILinkedMediaStateListener iLinkedMediaStateListener) {
        if (iLinkedMediaStateListener == null) {
            return;
        }
        this.t = iLinkedMediaStateListener;
    }

    @Override // com.huawei.openalliance.ad.views.i
    protected boolean a(MaterialClickInfo materialClickInfo) {
        if (this.aH != null && materialClickInfo != null) {
            int mode = this.aH.getMode();
            if (ho.a()) {
                ho.a("PPSLinkedView", "splashpro mode:%d", Integer.valueOf(mode));
            }
            if (1 != mode && mode != 0) {
                Rect rect = new Rect();
                this.aH.getHitRect(rect);
                boolean contains = rect.contains(materialClickInfo.a().intValue(), materialClickInfo.b().intValue());
                ho.b("PPSLinkedView", "check result:" + contains);
                return contains;
            }
        }
        return true;
    }

    @Override // com.huawei.openalliance.ad.views.i
    protected void a(boolean z) {
        VideoInfo videoInfo;
        String str;
        ho.b("PPSLinkedView", "switchSound enableSound: " + z);
        if (this.y == null || this.e.k() == null) {
            return;
        }
        uc ucVar = this.e;
        if (z) {
            ucVar.d();
            this.E.setSelected(true);
            if (this.o != null) {
                videoInfo = this.o;
                str = "y";
                videoInfo.e(str);
            }
            this.p.a(!z);
        }
        ucVar.c();
        this.E.setSelected(false);
        if (this.o != null) {
            videoInfo = this.o;
            str = "n";
            videoInfo.e(str);
        }
        this.p.a(!z);
    }

    @Override // com.huawei.openalliance.ad.views.i
    public void a(Integer num, boolean z) {
        ho.a("PPSLinkedView", "reportSplashAdShowEvent. ");
        a(Long.valueOf(System.currentTimeMillis() - this.P), 100, num, z);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IPPSLinkedView
    public void a(Integer num) {
        a(Long.valueOf(System.currentTimeMillis() - this.k.e()), Integer.valueOf(this.k.d()), num, false);
    }

    protected void a(ContentRecord contentRecord, int[] iArr, int[] iArr2) {
        if (ao.a(iArr, 2) && ao.a(iArr2, 2) && contentRecord != null) {
            if (ho.a()) {
                ho.a("PPSLinkedView", "addComplianceDialog, loc: %s, %s", Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]));
                ho.a("PPSLinkedView", "addComplianceDialog, size: %s, %s", Integer.valueOf(iArr2[0]), Integer.valueOf(iArr2[1]));
            }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            this.bb = new ti(getContext(), iArr, iArr2);
            this.v.addView(this.bb, layoutParams);
            contentRecord.u(cz.c(contentRecord.Z()));
            this.bb.setScreenWidth(this.v.getMeasuredWidth());
            this.bb.setScreenHeight(this.v.getMeasuredHeight());
            this.bb.setAdContent(contentRecord);
        }
    }

    @Override // com.huawei.openalliance.ad.jd.a
    public void a(long j, int i) {
        ho.b("PPSLinkedView", "onViewShowEndRecord");
        dj.a(this.b);
        if (!this.k.a(j) || this.T) {
            return;
        }
        this.T = true;
        a(Long.valueOf(j), Integer.valueOf(i), Integer.valueOf(this.ad == 2 ? 9 : 8), false);
    }

    @Override // com.huawei.openalliance.ad.views.i
    protected void a(int i, boolean z) {
        if (this.o != null) {
            this.o.e(z ? 0 : i);
        }
        if (this.N) {
            this.N = false;
            nz nzVar = this.p;
            long j = this.P;
            long currentTimeMillis = System.currentTimeMillis();
            long j2 = this.R;
            long j3 = i;
            if (z) {
                nzVar.a(j, currentTimeMillis, j2, j3);
                this.d.h();
            } else {
                nzVar.b(j, currentTimeMillis, j2, j3);
                this.d.i();
            }
        }
        this.e.b(false);
    }

    @Override // com.huawei.openalliance.ad.views.i
    protected void a(int i) {
        b(this.ad);
        if (this.p == null || this.p.a(i, this.f)) {
            I();
            if (18 == i) {
                Context context = this.aW.get();
                if (context instanceof Activity) {
                    ((Activity) context).overridePendingTransition(R.anim._2130772028_res_0x7f01003c, R.anim._2130772025_res_0x7f010039);
                }
            }
        } else if (this.w != null && (this.w instanceof AppDownloadButton)) {
            if (AppStatus.DOWNLOAD == ((AppDownloadButton) this.w).getStatus() && this.l != null && this.l.isAutoDownloadApp() && os.h(this.l.getCtrlSwitchs())) {
                ho.b("PPSLinkedView", "download app directly");
                ((AppDownloadButton) this.w).performClick();
            }
        }
        this.f = null;
        this.d.a();
        int i2 = 1;
        if (this.ad == 1) {
            this.ax = 3;
            if (this.r == null) {
                return;
            }
        } else {
            i2 = 2;
            if (this.ad != 2) {
                return;
            }
            this.ax = 4;
            if (this.r == null) {
                return;
            }
        }
        this.r.onClick(i2);
    }

    @Override // com.huawei.openalliance.ad.jd.a
    public void a() {
        ho.b("PPSLinkedView", "onViewShowStartRecord");
        LinkedSplashAd linkedSplashAd = this.l;
        if (linkedSplashAd == null || !this.aj) {
            return;
        }
        ho.a("PPSLinkedView", "ad.getMinEffectiveShowTime: %s. ", Long.valueOf(linkedSplashAd.getMinEffectiveShowTime()));
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSLinkedView.1
            @Override // java.lang.Runnable
            public void run() {
                PPSLinkedView pPSLinkedView;
                Long valueOf;
                Integer valueOf2;
                int i;
                LinkedSplashAd linkedSplashAd2 = PPSLinkedView.this.l;
                if (linkedSplashAd2 == null) {
                    return;
                }
                if (PPSLinkedView.this.ad == 2) {
                    pPSLinkedView = PPSLinkedView.this;
                    valueOf = Long.valueOf(linkedSplashAd2.getMinEffectiveShowTime());
                    valueOf2 = Integer.valueOf(PPSLinkedView.this.k.d());
                    i = 9;
                } else {
                    pPSLinkedView = PPSLinkedView.this;
                    valueOf = Long.valueOf(linkedSplashAd2.getMinEffectiveShowTime());
                    valueOf2 = Integer.valueOf(PPSLinkedView.this.k.d());
                    i = 8;
                }
                pPSLinkedView.a(valueOf, valueOf2, Integer.valueOf(i), false);
            }
        }, this.b, linkedSplashAd.getMinEffectiveShowTime());
    }

    private void z() {
        this.e.a(this.l.D(), this.o, this.n);
        this.y.setClickable(false);
    }

    private boolean y() {
        String str;
        if (this.m == null && this.B != null && this.B.getAdMediator() != null) {
            ho.b("PPSLinkedView", "set adMediator. ");
            this.m = this.B.getAdMediator();
        }
        if (getResources().getConfiguration().orientation != 1) {
            str = "orientation not portrait. ";
        } else if (!this.am) {
            str = "not register linkedSplashAd and destview. ";
        } else if (this.o == null) {
            str = "videoInfo is null. ";
        } else {
            if (this.v != null && this.y != null) {
                this.ad = 1;
                this.ae = (((double) this.o.d()) < -1.0E-7d || ((double) this.o.d()) > 1.0E-7d) ? (int) (this.o.d() * 1000.0f) : IEventListener.EVENT_ID_NOTICE_DIALOG_SHOW;
                this.G = new i.d(this.ae, 1000L);
                return true;
            }
            str = "splash view not ready. ";
        }
        ho.c("PPSLinkedView", str);
        x();
        i();
        unregister();
        return false;
    }

    private void x() {
        if (this.l == null || !this.l.isFromExsplash()) {
            return;
        }
        ms.a(this.g).a(RTCMethods.SHOW_SPLASH, null, null, null);
        this.aD.a(this.aC, 1, this.l.getSlotId(), this.l.getContentId());
    }

    private boolean w() {
        if (this.l == null || !this.l.isFromExsplash() || this.h == null) {
            return true;
        }
        if (this.al == null) {
            this.al = HiAd.a(this.g).g();
            this.l.setListener(this.al);
        }
        long longValue = this.h.bB().longValue();
        int bC = this.h.bC();
        long bD = this.h.bD();
        long j = bC;
        long j2 = longValue + j;
        long currentTimeMillis = System.currentTimeMillis();
        String contentId = this.l.getContentId();
        String slotId = this.l.getSlotId();
        ho.a("PPSLinkedView", "ExSplashPreCheck, sloganStartTime:%s, sloganShowTime:%s, redundancyTime:%s", Long.valueOf(longValue), Integer.valueOf(bC), Long.valueOf(bD));
        ho.a("PPSLinkedView", "ExSplashPreCheck, thresholdTime:%s, currentTime: %s", Long.valueOf(j2), Long.valueOf(System.currentTimeMillis()));
        if (HiAd.getInstance(this.g).isEnableUserInfo() && currentTimeMillis <= j2) {
            return true;
        }
        ho.c("PPSLinkedView", "ExSplashPreCheck, unable user info or over time");
        ms.a(this.g).a(RTCMethods.SHOW_SPLASH, null, null, null);
        this.aD.a(this.aC, 1, slotId, contentId, (currentTimeMillis - longValue) - j);
        i();
        F();
        unregister();
        return false;
    }

    private void v() {
        if (this.D != null) {
            this.D.setLinkedOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.PPSLinkedView.3
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 1) {
                        ho.b("PPSLinkedView", "onTouch: begin to scale");
                        PPSLinkedView.this.D.setVisibility(4);
                        PPSLinkedView.this.o();
                        PPSLinkedView.this.ax = 1;
                        PPSLinkedView.this.j();
                    }
                    return true;
                }
            });
        }
    }

    private boolean u() {
        String str;
        if (getResources().getConfiguration().orientation != 1) {
            str = "orientation not portrait. ";
        } else if (!this.am) {
            str = "not register linkedSplashAd and destview. ";
        } else if (this.l == null || this.o == null) {
            str = "videoInfo is null. ";
        } else {
            if (this.C != null && this.v != null && this.y != null) {
                return false;
            }
            str = "splash view not ready. ";
        }
        ho.c("PPSLinkedView", str);
        x();
        i();
        unregister();
        return true;
    }

    private void t() {
        if (this.x == null) {
            return;
        }
        this.e.a(this.x.h());
        MediaPlayerAgent k = this.e.k();
        if (k == null) {
            return;
        }
        k.a(this.bc);
        k.addMediaStateListener(this.be);
        k.addMediaErrorListener(this.bk);
        k.addMediaInfoListener(this.bf);
        k.addMuteListener(this.bl);
        k.addMediaBufferListener(this.bm);
        k.addReportVideoTimeListenersSet(this.bd);
    }

    private void setSplashViewClickable(ah ahVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(ahVar);
        a(arrayList);
    }

    private void setSkipBtnDelayTime(ContentRecord contentRecord) {
        if (contentRecord == null || contentRecord.at() <= 0) {
            return;
        }
        this.aG = contentRecord.at();
    }

    private void setDestViewClickable(PPSDestView pPSDestView) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(pPSDestView);
        a(arrayList);
    }

    private void s() {
        this.aH = this.v.getProView();
        this.aI = this.v.getSwipeView();
        this.aJ = this.v.getTwistView();
        this.aY = this.v.getSwipeClickView();
        this.aZ = this.v.getTwistClickView();
    }

    private void r() {
        this.x = new g(this.g);
        this.x.a(this.y);
        this.x.a(this.e.f());
    }

    private void q() {
        this.i = this.v.getPpswlsView();
        this.j = this.v.getPpsSplashAdSourceView();
    }

    private RelativeLayout.LayoutParams getDestViewParam() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        return layoutParams;
    }

    private boolean e(int i) {
        if (2 != i && 3 != i) {
            return false;
        }
        if (this.h.aL()) {
            return !com.huawei.openalliance.ad.utils.x.l(getContext().getApplicationContext());
        }
        return true;
    }

    private void e(ContentRecord contentRecord) {
        if (contentRecord == null || this.e.f() == null) {
            return;
        }
        ho.b("PPSLinkedView", "initOmsdkResource");
        this.d.a(this, contentRecord, this.z, this.g, this.v);
    }

    private void d(int i) {
        String str;
        String str2;
        if (this.aE != null) {
            ContentRecord a2 = oz.a(this.l);
            if (a2 != null) {
                str = a2.ag();
                str2 = a2.l();
            } else {
                str = null;
                str2 = null;
            }
            this.aE.a(i, new com.huawei.openalliance.ad.analysis.a(str, str2, 1), (String) null, a2);
        }
    }

    private String d(InteractCfg interactCfg) {
        if (interactCfg != null) {
            return interactCfg.j();
        }
        return null;
    }

    private Integer d(ContentRecord contentRecord) {
        int n = os.n(contentRecord.V());
        if (n == 0) {
            return null;
        }
        int c = c(contentRecord);
        ho.b("PPSLinkedView", "initial mode: %s", Integer.valueOf(c));
        if (c == 0) {
            return Integer.valueOf(c);
        }
        Map<String, String> a2 = be.a(this.h.ca());
        if (a2 != null) {
            if ((2 == c || 3 == c) && a(cz.i(a2.get(Constants.TWIST_TYPE)))) {
                c = 4;
            }
            if ((1 == c || 4 == c) && a(cz.i(a2.get(Constants.SWIPE_TYPE)))) {
                return 0;
            }
        }
        if (1 != getResources().getConfiguration().orientation || 2 != n) {
            return 0;
        }
        if (!e(c)) {
            return Integer.valueOf(c);
        }
        ho.b("PPSLinkedView", "can't use twist, enable : %s", Boolean.valueOf(this.h.aL()));
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        ho.b("PPSLinkedView", "moveLinkedView");
        if (C() && !this.aj) {
            F();
            if (this.q != null) {
                this.q.onSwitch(this.ax);
            }
            if (z) {
                G();
            }
            this.aj = true;
        }
    }

    private void c(int i) {
        if (this.al != null) {
            this.al.onAdFailedToLoad(i);
        }
        d(i);
    }

    private String c(InteractCfg interactCfg) {
        if (interactCfg != null) {
            return interactCfg.i();
        }
        return null;
    }

    private int c(ContentRecord contentRecord) {
        return (contentRecord.aJ() == null || contentRecord.aJ().a() == null) ? this.h.F() : contentRecord.aJ().a().intValue();
    }

    private void b(boolean z) {
        ho.b("PPSLinkedView", "start play. ");
        this.e.a(this.ay);
        if (z) {
            this.ba = true;
            J();
        }
        if (this.G != null) {
            ho.b("PPSLinkedView", "start count down. ");
            this.G.start();
        }
    }

    private void b(LinkedSplashAd linkedSplashAd) {
        ho.b("PPSLinkedView", "LinkedSplashAd:%s, isChinaRom:%s", linkedSplashAd, Boolean.valueOf(this.aB));
        if (linkedSplashAd != null) {
            Integer d = d(linkedSplashAd.I());
            InteractCfg aJ = linkedSplashAd.I().aJ();
            Integer e = aJ == null ? null : aJ.e();
            if (this.aB) {
                this.j.a(this, d, e, ao.a(true, linkedSplashAd.isTransparencyOpen(), linkedSplashAd.getTransparencyTplUrl()));
                this.j.setVisibility(0);
                this.j.a(linkedSplashAd.I(), false, this.U, 1, false);
                return;
            }
            this.i.setPpsLinkedView(this);
            this.i.a(d, e);
            this.i.setVisibility(0);
            this.i.a(linkedSplashAd.I(), false, this.U, 1, false);
            if (bg.a(linkedSplashAd.J())) {
                return;
            }
            this.i.setChoiceViewOnClickListener(new i.a(this, linkedSplashAd.I()));
        }
    }

    private void b(int i) {
        int i2;
        if (i == 1) {
            i2 = 12;
        } else if (i != 2) {
            return;
        } else {
            i2 = 13;
        }
        a(Integer.valueOf(i2), true);
    }

    private String b(ContentRecord contentRecord) {
        String a2 = com.huawei.openalliance.ad.utils.c.a(getContext(), contentRecord, 0);
        return !TextUtils.isEmpty(a2) ? a2 : !TextUtils.isEmpty(this.h.G()) ? this.h.G() : contentRecord.ax();
    }

    private String b(InteractCfg interactCfg, String str) {
        return !TextUtils.isEmpty(str) ? str : (interactCfg == null || interactCfg.k() == null) ? this.h.K() : interactCfg.k();
    }

    private String b(InteractCfg interactCfg) {
        if (interactCfg != null) {
            return interactCfg.h();
        }
        return null;
    }

    private WindowManager.LayoutParams b(Context context) {
        String str;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.flags = 134218760;
        layoutParams.format = 1;
        try {
            if (Build.VERSION.SDK_INT >= 28 && dd.i(context)) {
                ho.a("PPSLinkedView", " isNotchEnable");
                layoutParams.layoutInDisplayCutoutMode = 1;
            }
            cb.a(this.g).a(layoutParams);
        } catch (NoSuchMethodError unused) {
            str = "initOnApplyWindowInsets NoSuchMethodError setDisplaySideMode";
            ho.c("PPSLinkedView", str);
            return layoutParams;
        } catch (Throwable th) {
            str = "initOnApplyWindowInsets error:" + th.getClass().getSimpleName();
            ho.c("PPSLinkedView", str);
            return layoutParams;
        }
        return layoutParams;
    }

    private boolean a(Long l) {
        return fh.b(getContext()).a(l);
    }

    private void a(boolean z, int i, ContentRecord contentRecord) {
        this.v.setOnClickListener(null);
        InteractCfg aJ = contentRecord.aJ();
        String a2 = com.huawei.openalliance.ad.utils.c.a(getContext(), contentRecord, i);
        if (1 == i) {
            if (this.aI == null) {
                return;
            }
            this.aI.setVisibility(4);
            this.aI.a(a(aJ), a(aJ, a2));
            this.aI.setShowLogo(z);
            this.aI.setVisibility(0);
            this.v.setOnTouchListener(this.bh);
            return;
        }
        if (2 == i) {
            if (this.aJ == null) {
                return;
            }
            this.aJ.setVisibility(4);
            this.aJ.a(b(aJ), b(aJ, a2));
            this.aJ.setShowLogo(z);
            this.aJ.setVisibility(0);
            this.v.setOnTouchListener(f8089a);
            L();
            return;
        }
        if (3 == i) {
            if (this.aZ == null) {
                return;
            }
            this.aZ.setVisibility(4);
            this.aZ.a(d(aJ), b(aJ, a2));
            this.aZ.setShowLogo(z);
            this.aZ.setVisibility(0);
            this.v.setOnTouchListener(f8089a);
            this.aZ.getClickAreaView().setOnTouchListener(this.bi);
            L();
            return;
        }
        if (4 != i || this.aY == null) {
            return;
        }
        this.aY.setVisibility(4);
        this.aY.a(c(aJ), a(aJ, a2));
        this.aY.setShowLogo(z);
        this.aY.setVisibility(0);
        this.v.setOnTouchListener(this.bh);
        this.aY.getClickAreaView().setOnTouchListener(this.bi);
    }

    private void a(List<View> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (View view : list) {
            if (view instanceof NativeVideoView) {
                ((NativeVideoView) view).setCoverClickListener(this.bg);
            } else if (view != null) {
                view.setOnClickListener(this.bg);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Long l, Integer num, Integer num2, boolean z) {
        ho.a("PPSLinkedView", "reportAdShowEvent. ");
        if (this.l == null) {
            return;
        }
        boolean a2 = com.huawei.openalliance.ad.utils.c.a(this.l.d(), num2);
        if (!this.l.V() || (a2 && !this.l.b())) {
            if (!this.h.bb()) {
                this.l.h(true);
                this.p.a(null, null, num2);
            } else if (z || l.longValue() >= this.l.getMinEffectiveShowTime()) {
                this.l.h(true);
                ho.a("PPSLinkedView", "report imp. ");
                this.p.a(l, num, num2);
            }
            if (a2) {
                this.l.a(true);
            }
            this.d.d();
        }
    }

    private void a(LinkedSplashAd linkedSplashAd, int i) {
        float f;
        int i2;
        String str;
        String str2;
        if (linkedSplashAd != null) {
            String E = linkedSplashAd.E();
            String F = linkedSplashAd.F();
            f = linkedSplashAd.G();
            i2 = linkedSplashAd.H();
            setSkipBtnDelayTime(linkedSplashAd.I());
            str = E;
            str2 = F;
        } else {
            f = 0.0f;
            i2 = 0;
            str = null;
            str2 = null;
        }
        this.D = a(str, i, str2, false, f, i2);
        this.D.setId(R.id.hiad_btn_skip);
        this.v.addView(this.D);
        this.D.bringToFront();
        this.D.setVisibility(4);
    }

    private void a(LinkedSplashAd linkedSplashAd) {
        ContentRecord I;
        Integer d;
        if (this.aH == null || linkedSplashAd == null || (I = linkedSplashAd.I()) == null) {
            return;
        }
        int n = os.n(I.V());
        int t = os.t(I.V());
        ho.b("PPSLinkedView", "set splashpro mode:" + n);
        if (n == 0 || (d = d(I)) == null) {
            this.aH.setVisibility(8);
        } else if (d.intValue() == 0) {
            a(I, t);
        } else {
            a(I);
            a(false, d.intValue(), I);
        }
        this.aH.setMode(n);
    }

    private void a(ILinkedSplashAd iLinkedSplashAd) {
        this.y = this.v.getLinkedVideoView();
        if (iLinkedSplashAd.getVideoInfo() != null) {
            this.y.setVideoRatio(iLinkedSplashAd.getVideoInfo().getVideoRatio());
        }
    }

    private void a(ContentRecord contentRecord, int i) {
        K();
        this.aH.setVisibility(4);
        this.aH.setDesc(b(contentRecord));
        this.aH.a(false, i);
        if (i != 0) {
            this.aH.setVisibility(0);
        }
        this.v.setOnTouchListener(this.bj);
    }

    private void a(ContentRecord contentRecord) {
        if (contentRecord.aJ() == null) {
            this.aT = fh.b(getContext()).J();
            this.aV = fh.b(getContext()).M();
            this.aU = fh.b(getContext()).L();
        } else {
            InteractCfg aJ = contentRecord.aJ();
            this.aT = (aJ.b() == null || aJ.b().intValue() <= 0) ? fh.b(getContext()).J() : aJ.b().intValue();
            this.aV = (aJ.c() == null || aJ.c().intValue() <= 0) ? fh.b(getContext()).M() : aJ.c().intValue();
            this.aU = (aJ.d() == null || aJ.d().intValue() <= 0) ? fh.b(getContext()).L() : aJ.d().intValue();
            this.aX = aJ.f().intValue();
        }
    }

    private String a(InteractCfg interactCfg, String str) {
        return !TextUtils.isEmpty(str) ? str : (interactCfg == null || interactCfg.k() == null) ? this.h.I() : interactCfg.k();
    }

    private String a(InteractCfg interactCfg) {
        if (interactCfg != null) {
            return interactCfg.g();
        }
        return null;
    }

    private u a(String str, int i, String str2, boolean z, float f, int i2) {
        return new u(getContext(), str, 1, 4, i, str2, z, this.U, f, i2, false);
    }

    private void T() {
        if (this.D != null) {
            ho.a("PPSLinkedView", "%d delay, skip btn show", Integer.valueOf(this.aG));
            if (this.aG > 0) {
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSLinkedView.9
                    @Override // java.lang.Runnable
                    public void run() {
                        if (PPSLinkedView.this.D != null) {
                            ho.a("PPSLinkedView", "skip btn show");
                            PPSLinkedView.this.D.setVisibility(0);
                        }
                    }
                }, this.c, this.aG);
            } else {
                ho.a("PPSLinkedView", "skip btn show");
                this.D.setVisibility(0);
            }
        }
    }

    private void S() {
        if (this.F && this.E == null) {
            this.E = new ImageView(getContext());
            this.E.setImageResource(R.drawable._2131428584_res_0x7f0b04e8);
            dd.a(this.E);
            Resources resources = this.g.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen._2131363334_res_0x7f0a0606);
            this.E.setPadding(0, dimensionPixelSize, resources.getDimensionPixelSize(R.dimen._2131363375_res_0x7f0a062f), dimensionPixelSize);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(12);
            layoutParams.addRule(21);
            layoutParams.rightMargin = resources.getDimensionPixelSize(R.dimen._2131362830_res_0x7f0a040e);
            layoutParams.bottomMargin = resources.getDimensionPixelOffset(R.dimen._2131362829_res_0x7f0a040d);
            layoutParams.bottomMargin += dd.f(this.g);
            layoutParams.setMarginEnd(resources.getDimensionPixelSize(R.dimen._2131362830_res_0x7f0a040e));
            this.v.addView(this.E, layoutParams);
            this.E.bringToFront();
            this.E.setSelected(false);
            this.E.setOnClickListener(this.bn);
        }
    }

    private void R() {
        if (this.A == null || this.A.isEmpty()) {
            return;
        }
        for (View view : this.A) {
            if (view != null) {
                view.setOnClickListener(null);
            }
        }
        setOnClickListener(null);
    }

    private void Q() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this);
        this.A = arrayList;
        a(arrayList);
    }

    private void P() {
        if (!p() || this.l == null || this.l.ag()) {
            return;
        }
        ho.b("PPSLinkedView", " maybe report show start.");
        b();
    }

    private void O() {
        if (this.l != null) {
            this.l.j(false);
        }
        this.l = null;
        this.B = null;
        this.H = null;
        this.L = null;
        if (this.aW != null) {
            this.aW.clear();
        }
        if (this.y != null) {
            this.y.a();
        }
        aj f = this.e.f();
        if (f != null) {
            f.destroyView();
        }
        if (this.x != null) {
            this.x.i();
        }
        this.e.b(false);
        R();
        dc.a((IAd) null);
        dj.a(this.c);
        this.d.b();
        com.huawei.openalliance.ad.inter.c.a(this.g).a(false);
    }

    private void N() {
        if (this.w != null) {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSLinkedView.7
                @Override // java.lang.Runnable
                public void run() {
                    PPSLinkedView.this.w.cancel();
                }
            });
        }
    }

    private void M() {
        String str;
        try {
            if (this.K == null) {
                this.K = this.J.inflate();
                this.K.setId(R.id.hiad_full_logo_region);
            }
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.K.getLayoutParams();
            if (this.U > 0) {
                ho.a("PPSLinkedView", "left:%d, top:%d, right:%d", Integer.valueOf(layoutParams.leftMargin), Integer.valueOf(layoutParams.topMargin), Integer.valueOf(layoutParams.rightMargin));
                layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin + this.U, layoutParams.rightMargin, layoutParams.bottomMargin);
                this.K.setLayoutParams(layoutParams);
            }
            ImageView imageView = (ImageView) this.K.findViewById(R.id.hiad_full_mode_logo);
            if (this.I > 0) {
                imageView.setImageResource(this.I);
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(8);
            }
            TextView textView = (TextView) this.K.findViewById(R.id.hiad_media_name);
            if (this.M <= 0) {
                textView.setVisibility(8);
            } else {
                textView.setText(this.M);
                textView.setVisibility(0);
            }
        } catch (Resources.NotFoundException unused) {
            str = "showFullModeLogo res not found";
            ho.c("PPSLinkedView", str);
        } catch (Exception e) {
            str = "showFullModeLogo " + e.getClass().getSimpleName();
            ho.c("PPSLinkedView", str);
        }
    }

    private void L() {
        this.aK = new rp(this.g);
        this.aK.a(new i.c());
        this.aK.a();
        this.aL = new ro(this.g);
        this.aL.a(new i.b());
        this.aL.a();
    }

    private void K() {
        int H = this.h.H();
        if (H > 0) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.aH.getLayoutParams();
            int a2 = ao.a(this.g, H);
            this.aH.setPadding(a2, a2, a2, a2);
            if (layoutParams.isMarginRelative()) {
                layoutParams.setMarginStart(layoutParams.leftMargin - a2);
                layoutParams.setMarginEnd(layoutParams.rightMargin - a2);
            } else {
                layoutParams.setMargins(layoutParams.leftMargin - a2, layoutParams.topMargin, layoutParams.rightMargin - a2, layoutParams.bottomMargin);
            }
            this.aH.setLayoutParams(layoutParams);
        }
    }

    private void J() {
        T();
        S();
        b(this.l);
        M();
        a(this.l);
    }

    private void I() {
        if (this.G != null) {
            this.G.cancel();
            this.G = null;
        }
    }

    private void H() {
        this.av.addListener(new Animator.AnimatorListener() { // from class: com.huawei.openalliance.ad.views.PPSLinkedView.6
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                ho.b("PPSLinkedView", "onAnimationStart");
                if (PPSLinkedView.this.bb != null) {
                    PPSLinkedView.this.bb.setVisibility(8);
                }
                if (PPSLinkedView.this.E != null) {
                    PPSLinkedView.this.E.setVisibility(8);
                }
                if (PPSLinkedView.this.D != null) {
                    PPSLinkedView.this.D.setVisibility(8);
                }
                if (PPSLinkedView.this.j != null) {
                    PPSLinkedView.this.j.a();
                    PPSLinkedView.this.j.setVisibility(8);
                }
                if (PPSLinkedView.this.i != null) {
                    PPSLinkedView.this.i.setVisibility(8);
                }
                if (PPSLinkedView.this.K != null) {
                    PPSLinkedView.this.K.setVisibility(8);
                }
                if (PPSLinkedView.this.aH != null) {
                    PPSLinkedView.this.aH.setVisibility(8);
                    PPSLinkedView.this.aH.a();
                }
                if (PPSLinkedView.this.aI != null) {
                    PPSLinkedView.this.aI.setVisibility(8);
                    PPSLinkedView.this.aI.b();
                }
                if (PPSLinkedView.this.aJ != null) {
                    PPSLinkedView.this.aJ.setVisibility(8);
                }
                if (PPSLinkedView.this.aZ != null) {
                    PPSLinkedView.this.aZ.setVisibility(8);
                }
                if (PPSLinkedView.this.aY != null) {
                    PPSLinkedView.this.aY.setVisibility(8);
                }
                if (PPSLinkedView.this.v != null) {
                    PPSLinkedView.this.v.setOnTouchListener(null);
                }
                if (PPSLinkedView.this.aK != null) {
                    PPSLinkedView.this.aK.b();
                }
                if (PPSLinkedView.this.aL != null) {
                    PPSLinkedView.this.aL.b();
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                LinkedSurfaceView linkedSurfaceView;
                float floatValue;
                float f;
                int i;
                PPSLinkedView pPSLinkedView;
                ho.b("PPSLinkedView", "onAnimationEnd");
                try {
                    if (PPSLinkedView.this.ab > 0 && PPSLinkedView.this.o != null) {
                        ho.b("PPSLinkedView", "onAnimationEnd, VideoRatio = %s", PPSLinkedView.this.o.getVideoRatio());
                        if (PPSLinkedView.this.o.getVideoRatio().floatValue() < 1.0f) {
                            linkedSurfaceView = PPSLinkedView.this.y;
                            floatValue = (PPSLinkedView.this.ac * 1.0f) / (PPSLinkedView.this.ab * 1.0f);
                            f = (PPSLinkedView.this.ac * 1.0f) / (PPSLinkedView.this.ab * 1.0f);
                            i = PPSLinkedView.this.ac;
                            pPSLinkedView = PPSLinkedView.this;
                        } else {
                            linkedSurfaceView = PPSLinkedView.this.y;
                            floatValue = PPSLinkedView.this.o.getVideoRatio().floatValue();
                            f = (PPSLinkedView.this.ac * 1.0f) / (PPSLinkedView.this.ab * 1.0f);
                            i = PPSLinkedView.this.ac;
                            pPSLinkedView = PPSLinkedView.this;
                        }
                        linkedSurfaceView.a(floatValue, f, i, pPSLinkedView.ab);
                    }
                    PPSLinkedView.this.B();
                    PPSLinkedView.this.ad = 2;
                } catch (Throwable th) {
                    ho.b("PPSLinkedView", "onAnimationEnd err: %s", th.getClass().getSimpleName());
                }
            }
        });
    }

    private void G() {
        ho.b("PPSLinkedView", "addMonitor");
        this.k = new jd(this, this);
        this.k.h();
        if (this.l != null) {
            this.k.b(this.l.getMinEffectiveShowTime(), this.l.getMinEffectiveShowRatio());
        }
        this.k.a(this.l);
    }

    private void F() {
        ho.b("PPSLinkedView", "removeSplashView");
        if (this.v != null) {
            this.v.setVisibility(8);
            this.v.a();
        }
        if (this.y != null) {
            this.y.a();
            if (this.x != null) {
                this.x.b(this.y);
            }
            this.y = null;
        }
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSLinkedView.4
            @Override // java.lang.Runnable
            public void run() {
                if (PPSLinkedView.this.v != null) {
                    if (PPSLinkedView.this.v.isAttachedToWindow()) {
                        PPSLinkedView.this.C.removeView(PPSLinkedView.this.v);
                    }
                    PPSLinkedView.this.v.b();
                    PPSLinkedView.this.v = null;
                }
            }
        }, 20L);
        if (this.aH != null) {
            this.aH.a();
        }
        if (this.aI != null) {
            this.aI.b();
        }
        if (this.aK != null) {
            this.aK.b();
        }
        if (this.aL != null) {
            this.aL.b();
        }
    }

    private boolean E() {
        aj f = this.e.f();
        return f == null || !f.j();
    }

    private boolean D() {
        return this.z == null || this.z.getHeight() == 0 || this.z.getWidth() == 0;
    }

    private boolean C() {
        boolean D = D();
        boolean E = E();
        if (!D && !E) {
            return true;
        }
        ho.c("PPSLinkedView", "checkDestView, destView change null, linkedAdListener: %s, isMoved:%s. ", cz.b(this.al), Boolean.valueOf(this.aj));
        ho.b("PPSLinkedView", "isDestViewNull:%s, isDestViewNotAvalible:%s", Boolean.valueOf(D), Boolean.valueOf(E));
        if (!this.aF) {
            this.aF = true;
            c(-5);
            if (this.al != null) {
                this.al.onAdDismissed();
            }
        }
        if (!this.aj) {
            this.aj = true;
            this.ad = 0;
            this.e.i();
            F();
            R();
            if (this.q != null) {
                this.q.onSwitch(this.ax);
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B() {
        ho.b("PPSLinkedView", "switchViewOnAnimationEnd. ");
        c(this.aA);
        if (ho.a()) {
            ho.a("PPSLinkedView", "isMoved: %s, linkedAdListener on switch: %s ", Boolean.valueOf(this.aj), this.al);
        }
        if (this.al == null) {
            ho.c("PPSLinkedView", "linkedAdListener is null. ");
        } else {
            ho.a("PPSLinkedView", "splash show end. ");
            this.al.onAdDismissed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A() {
        DisplayMetrics displayMetrics = this.g.getResources().getDisplayMetrics();
        this.W = displayMetrics.heightPixels;
        this.aa = displayMetrics.widthPixels;
    }

    public PPSLinkedView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public PPSLinkedView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public PPSLinkedView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PPSLinkedView(Context context) {
        super(context);
    }
}
