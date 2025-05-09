package com.huawei.openalliance.ad.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.gc;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.LinkedSplashAd;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.inter.listeners.LinkedAdListener;
import com.huawei.openalliance.ad.jb;
import com.huawei.openalliance.ad.jd;
import com.huawei.openalliance.ad.media.MediaPlayerAgent;
import com.huawei.openalliance.ad.media.listener.ILinkedMediaStateListener;
import com.huawei.openalliance.ad.media.listener.MediaBufferListener;
import com.huawei.openalliance.ad.media.listener.MediaErrorListener;
import com.huawei.openalliance.ad.media.listener.MediaStateListener;
import com.huawei.openalliance.ad.media.listener.MuteListener;
import com.huawei.openalliance.ad.media.listener.PPSVideoRenderListener;
import com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener;
import com.huawei.openalliance.ad.nj;
import com.huawei.openalliance.ad.nz;
import com.huawei.openalliance.ad.ro;
import com.huawei.openalliance.ad.rp;
import com.huawei.openalliance.ad.th;
import com.huawei.openalliance.ad.ti;
import com.huawei.openalliance.ad.ub;
import com.huawei.openalliance.ad.uc;
import com.huawei.openalliance.ad.utils.Cdo;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.views.PPSLinkedView;
import com.huawei.openalliance.ad.views.interfaces.IAppDownloadButton;
import com.huawei.openalliance.ad.views.interfaces.IPPSLinkedView;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes9.dex */
abstract class i extends RelativeLayout implements jd.a, IPPSLinkedView {

    /* renamed from: a, reason: collision with root package name */
    protected static View.OnTouchListener f8089a = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.i.1
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    };
    protected List<View> A;
    protected PPSSplashView B;
    protected WindowManager C;
    protected u D;
    protected ImageView E;
    protected boolean F;
    protected d G;
    protected View H;
    protected int I;
    protected ViewStub J;
    protected View K;
    protected View L;
    protected int M;
    protected boolean N;
    protected long O;
    protected long P;
    protected long Q;
    protected long R;
    protected boolean S;
    protected boolean T;
    protected int U;
    protected int V;
    protected float W;
    protected boolean aA;
    protected boolean aB;
    protected String aC;
    protected com.huawei.openalliance.ad.analysis.c aD;
    protected com.huawei.openalliance.ad.analysis.g aE;
    protected boolean aF;
    protected int aG;
    protected PPSSplashProView aH;
    protected PPSSplashSwipeView aI;
    protected PPSSplashTwistView aJ;
    protected rp aK;
    protected ro aL;
    protected double aM;
    protected double aN;
    protected double aO;
    protected float aP;
    protected float aQ;
    protected long aR;
    protected int aS;
    protected int aT;
    protected int aU;
    protected int aV;
    protected WeakReference<Context> aW;
    protected int aX;
    protected PPSSplashSwipeClickView aY;
    protected PPSSplashTwistClickView aZ;
    protected float aa;
    protected int ab;
    protected int ac;
    protected int ad;
    protected int ae;
    protected float af;
    protected float ag;
    protected float ah;
    protected int[] ai;
    protected boolean aj;
    protected boolean ak;
    protected LinkedAdListener al;
    protected boolean am;
    protected boolean an;
    protected boolean ao;
    protected boolean ap;
    protected boolean aq;
    protected boolean ar;
    protected boolean as;
    protected boolean at;
    protected boolean au;
    protected ValueAnimator av;
    protected boolean aw;
    protected int ax;
    protected boolean ay;
    protected Integer az;
    protected final String b;
    protected boolean ba;
    protected ti bb;
    protected PPSVideoRenderListener bc;
    protected final ReportVideoTimeListener bd;
    protected MediaStateListener be;
    protected com.huawei.openalliance.ad.media.listener.a bf;
    protected View.OnClickListener bg;
    protected View.OnTouchListener bh;
    protected View.OnTouchListener bi;
    protected View.OnTouchListener bj;
    protected MediaErrorListener bk;
    protected MuteListener bl;
    protected MediaBufferListener bm;
    protected View.OnClickListener bn;
    private boolean bo;
    private boolean bp;
    private boolean bq;
    protected final String c;
    protected final ub d;
    protected final uc e;
    protected MaterialClickInfo f;
    protected Context g;
    protected gc h;
    protected PPSWLSView i;
    protected PPSSplashAdSourceView j;
    protected jd k;
    protected LinkedSplashAd l;
    protected jb m;
    protected int n;
    protected VideoInfo o;
    protected nz p;
    protected PPSLinkedView.OnLinkedAdSwitchListener q;
    protected PPSLinkedView.OnLinkedAdClickListener r;
    protected PPSLinkedView.OnLinkedAdPreparedListener s;
    protected ILinkedMediaStateListener t;
    protected MuteListener u;
    protected ah v;
    protected IAppDownloadButton w;
    protected g x;
    protected LinkedSurfaceView y;
    protected PPSDestView z;

    protected abstract void a(int i);

    protected abstract void a(int i, boolean z);

    protected abstract void a(Integer num, boolean z);

    protected abstract void a(boolean z);

    protected abstract boolean a(MaterialClickInfo materialClickInfo);

    protected abstract void f();

    protected abstract void g();

    protected abstract void h();

    protected abstract void i();

    protected abstract void j();

    public ah n() {
        return this.v;
    }

    public jb m() {
        return this.m;
    }

    protected void l() {
        long currentTimeMillis = System.currentTimeMillis();
        this.O = currentTimeMillis;
        jb jbVar = this.m;
        if (jbVar != null) {
            jbVar.a(currentTimeMillis);
        }
    }

    protected void k() {
        ho.b("PPSBaseLinkedView", "reportAdShowStartEvent, showStartTime: %s", Long.valueOf(this.O));
        this.T = false;
        String valueOf = String.valueOf(this.O);
        LinkedSplashAd linkedSplashAd = this.l;
        if (linkedSplashAd == null) {
            ho.c("PPSBaseLinkedView", "linkedSplashAd is null! please register first");
            return;
        }
        linkedSplashAd.h(valueOf);
        this.l.c(this.O);
        this.l.h(false);
        this.l.a(false);
        this.l.j(true);
        if (!this.l.S()) {
            this.l.g(true);
        }
        this.p.a(valueOf);
        this.p.a(this.O);
        IAppDownloadButton iAppDownloadButton = this.w;
        if (iAppDownloadButton != null) {
            iAppDownloadButton.updateContent(valueOf);
            this.w.updateStartShowTime(this.O);
        }
        ho.a("PPSBaseLinkedView", "report showStart. ");
        this.p.b();
    }

    protected void a(Context context) {
        int h;
        if (this.U <= 0 && bz.a(context).a(context)) {
            h = Math.max(this.U, bz.a(context).a(this));
        } else if (this.U > 0 || bz.a(context).a(context)) {
            return;
        } else {
            h = ao.h(context);
        }
        this.U = h;
    }

    private void b(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.g = applicationContext;
        this.h = fh.b(applicationContext);
        this.p = new nj(this.g, this);
        this.C = (WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        this.aB = bz.a(this.g).d();
        this.aC = this.g.getPackageName();
        this.aD = new com.huawei.openalliance.ad.analysis.c(this.g);
        this.aE = new com.huawei.openalliance.ad.analysis.g(this.g);
    }

    public i(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.b = Constants.IMP_EVENT_MONITOR_ID + hashCode();
        this.c = "skip_btn_delay_id_" + hashCode();
        this.d = new ub();
        this.e = new uc();
        this.n = 1;
        this.F = true;
        this.M = 0;
        this.N = false;
        this.Q = -1L;
        this.S = false;
        this.T = false;
        this.U = 0;
        this.V = 0;
        this.ae = IEventListener.EVENT_ID_NOTICE_DIALOG_SHOW;
        this.ai = new int[2];
        this.aj = false;
        this.ak = false;
        this.am = false;
        this.an = false;
        this.ao = false;
        this.ap = false;
        this.aq = false;
        this.ar = false;
        this.as = false;
        this.at = false;
        this.au = false;
        this.aw = false;
        this.ay = true;
        this.aA = true;
        this.aB = true;
        this.aF = false;
        this.aG = 0;
        this.ba = false;
        this.bc = new PPSVideoRenderListener() { // from class: com.huawei.openalliance.ad.views.i.6
            @Override // com.huawei.openalliance.ad.media.listener.PPSVideoRenderListener
            public void onVideoRenderStart() {
                ho.a("PPSBaseLinkedView", "onVideoRenderStart, alreadyNotified: %s", Boolean.valueOf(i.this.bp));
                if (i.this.bp) {
                    return;
                }
                i.this.bp = true;
                i.this.f();
            }
        };
        this.bd = new ReportVideoTimeListener() { // from class: com.huawei.openalliance.ad.views.i.7
            @Override // com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener
            public void reportVideoTime(long j) {
                if (ho.a()) {
                    ho.a("PPSBaseLinkedView", "reportVideoTime: %s", Long.valueOf(j));
                }
                if (i.this.p != null) {
                    i.this.p.b(j);
                }
            }
        };
        this.be = new MediaStateListener() { // from class: com.huawei.openalliance.ad.views.i.8
            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onProgress(int i3, int i4) {
                if (i4 > 0 && !i.this.bp) {
                    ho.a("PPSBaseLinkedView", "onProgress onRenderStart, playtime: %s", Integer.valueOf(i4));
                    i.this.bp = true;
                    i.this.f();
                }
                if (i4 > 0) {
                    i.this.o.e(i4);
                    i.this.e.b(true);
                }
                if (i.this.t != null) {
                    i.this.t.onMediaProgress(i3, i4);
                }
                if (i.this.N) {
                    i.this.d.a(i3);
                }
                if (i.this.p != null) {
                    i.this.p.a(i4, i.this.o == null ? 0L : i.this.o.getVideoDuration());
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i3) {
                ho.b("PPSBaseLinkedView", "onMediaStop: %s", Integer.valueOf(i3));
                i.this.a(i3, false);
                if (i.this.t != null) {
                    i.this.t.onMediaStop(i3);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i3) {
                ho.b("PPSBaseLinkedView", "onMediaStart: %s", Integer.valueOf(i3));
                i.this.N = true;
                i.this.e.b(true);
                i.this.R = i3;
                i.this.P = System.currentTimeMillis();
                if (!i.this.aj) {
                    i.this.l();
                    i.this.k();
                }
                nz nzVar = i.this.p;
                if (i3 > 0) {
                    nzVar.j();
                    i.this.d.e();
                } else {
                    nzVar.i();
                    i.this.d.a(i.this.o);
                }
                if (i.this.l != null && i.this.l.isFromExsplash()) {
                    i.this.aD.b(i.this.aC, 1, i.this.l.getSlotId(), i.this.l.getContentId(), (System.currentTimeMillis() - i.this.h.bB().longValue()) - i.this.h.bC());
                }
                if (i.this.t != null) {
                    i.this.t.onMediaStart(i3);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i3) {
                ho.b("PPSBaseLinkedView", "onMediaPause: %s", Integer.valueOf(i3));
                i.this.a(i3, false);
                if (i.this.t != null) {
                    i.this.t.onMediaPause(i3);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i3) {
                ho.b("PPSBaseLinkedView", "onMediaCompletion: %s", Integer.valueOf(i3));
                i.this.a(i3, true);
                i.this.bq = true;
                if (i.this.ad == 2 && i.this.k != null && i.this.k.g()) {
                    ho.b("PPSBaseLinkedView", "onMediaCompletion, start play");
                    i.this.e.a();
                }
                if (i.this.t != null) {
                    i.this.t.onMediaCompletion(i3);
                }
            }
        };
        this.bf = new com.huawei.openalliance.ad.media.listener.a() { // from class: com.huawei.openalliance.ad.views.i.9
            @Override // com.huawei.openalliance.ad.media.listener.a
            public void b(int i3) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.a
            public void a(int i3) {
                ho.b("PPSBaseLinkedView", "onDurationReady:");
                if (!i.this.aw && i.this.s != null) {
                    i.this.aw = true;
                    i.this.s.onPrepared();
                }
                if (i.this.az == null) {
                    i.this.az = Integer.valueOf(i3);
                    if (i.this.l == null || i.this.l.getVideoInfo() == null) {
                        return;
                    }
                    i.this.l.getVideoInfo().a(i3);
                }
            }
        };
        this.bg = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.i.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i3;
                if (!i.this.bo) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                if (i.this.ad == 1) {
                    i iVar = i.this;
                    if (!iVar.a(iVar.f)) {
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                }
                i.this.bo = false;
                ho.b("PPSBaseLinkedView", "onClick");
                if (i.this.ad == 2) {
                    i3 = 10;
                } else {
                    i3 = 2 == i.this.aH.getMode() ? 17 : 9;
                    i.this.g();
                }
                i.this.a(i3);
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.i.10.1
                    @Override // java.lang.Runnable
                    public void run() {
                        i.this.bo = true;
                    }
                }, 500L);
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.bh = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.i.11
            private float b;
            private float c;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    this.b = motionEvent.getX();
                    this.c = motionEvent.getY();
                    if (ho.a()) {
                        ho.a("PPSBaseLinkedView", "startX = %s, startY = %s", Float.valueOf(this.b), Float.valueOf(this.c));
                    }
                    i.this.f = th.a(view, motionEvent);
                }
                if (2 == motionEvent.getAction()) {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    if (ho.a()) {
                        ho.a("PPSBaseLinkedView", "endX = %s, endY = %s, startX - endX = %s, startY-endY = %s", Float.valueOf(x), Float.valueOf(y), Float.valueOf(this.b - x), Float.valueOf(this.c - y));
                    }
                    if (Cdo.a(i.this.aX, i.this.aT, this.b - x, this.c - y)) {
                        i.this.v.setOnTouchListener(null);
                        th.a(view, motionEvent, 1, i.this.f);
                        i.this.a(18);
                    }
                }
                if (1 == motionEvent.getAction()) {
                    th.a(view, motionEvent, null, i.this.f);
                }
                return true;
            }
        };
        this.bi = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.i.12
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.setEnabled(false);
                i.this.v.setOnTouchListener(null);
                if (motionEvent.getAction() != 0) {
                    return true;
                }
                i.this.f = th.b(view, motionEvent);
                if (i.this.f != null) {
                    i.this.f.c(0);
                    i.this.f.a(Float.valueOf(com.huawei.openalliance.ad.utils.d.j(i.this.g)));
                }
                i.this.a(17);
                return true;
            }
        };
        this.bj = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.i.13
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    i.this.f = th.a(view, motionEvent);
                }
                if (1 != motionEvent.getAction()) {
                    return false;
                }
                th.a(view, motionEvent, null, i.this.f);
                return false;
            }
        };
        this.bk = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.views.i.2
            @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
            public void onError(MediaPlayerAgent mediaPlayerAgent, int i3, int i4, int i5) {
                ho.c("PPSBaseLinkedView", "media play error, isMoved: %s", Boolean.valueOf(i.this.aj));
                i.this.h();
                i.this.i();
                i.this.e.b(false);
                if (i.this.t != null) {
                    ho.b("PPSBaseLinkedView", "call onMediaError. ");
                    i.this.t.onMediaError(i3, i4, i5);
                }
            }
        };
        this.bl = new MuteListener() { // from class: com.huawei.openalliance.ad.views.i.3
            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onUnmute() {
                ho.b("PPSBaseLinkedView", "onUnmute");
                if (i.this.u != null) {
                    i.this.u.onUnmute();
                }
                i.this.d.b(1);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onMute() {
                ho.b("PPSBaseLinkedView", "onMute");
                if (i.this.u != null) {
                    i.this.u.onMute();
                }
                i.this.d.b(0);
            }
        };
        this.bm = new MediaBufferListener() { // from class: com.huawei.openalliance.ad.views.i.4
            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferUpdate(int i3) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingStart() {
                i.this.d.g();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingEnd() {
                i.this.d.f();
            }
        };
        this.bn = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.i.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                i.this.a(!view.isSelected());
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.bo = true;
        this.bp = false;
        this.bq = false;
        b(context);
    }

    protected static class a implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<PPSLinkedView> f8103a;
        private ContentRecord b;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            final PPSLinkedView pPSLinkedView = this.f8103a.get();
            if (pPSLinkedView != null) {
                final int[] choiceViewLoc = pPSLinkedView.i.getChoiceViewLoc();
                final int[] choiceViewSize = pPSLinkedView.i.getChoiceViewSize();
                if (ao.a(choiceViewLoc, 2) && ao.a(choiceViewSize, 2)) {
                    dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.i.a.1
                        @Override // java.lang.Runnable
                        public void run() {
                            pPSLinkedView.a(a.this.b, choiceViewLoc, choiceViewSize);
                        }
                    });
                }
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        public a(PPSLinkedView pPSLinkedView, ContentRecord contentRecord) {
            this.f8103a = new WeakReference<>(pPSLinkedView);
            this.b = contentRecord;
        }
    }

    protected class b implements ro.a {
        @Override // com.huawei.openalliance.ad.ro.a
        public void a(float f, float f2, float f3) {
            if (ho.a()) {
                ho.a("PPSBaseLinkedView", "limitAcc: %s, xAcc: %s yAcc: %s zAcc: %s", Integer.valueOf(i.this.aV), Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3));
            }
            if (Math.abs(f) >= i.this.aV && i.this.aP * f <= 0.0f) {
                i.this.aS++;
                i.this.aP = f;
            } else if (Math.abs(f2) >= i.this.aV && i.this.aQ * f2 <= 0.0f) {
                i.this.aS++;
                i.this.aQ = f2;
            }
            a(i.this.aU);
        }

        private void a(int i) {
            String str;
            if (i.this.aR == 0) {
                i.this.aR = System.currentTimeMillis();
                return;
            }
            if (i.this.aS <= 2 || System.currentTimeMillis() - i.this.aR <= 1000) {
                return;
            }
            double d = i;
            if (i.this.aM >= d || i.this.aN >= d || i.this.aO >= d) {
                ho.b("PPSBaseLinkedView", "limitDegree: %s X: %s Y: %s Z: %s", Integer.valueOf(i), Double.valueOf(i.this.aM), Double.valueOf(i.this.aN), Double.valueOf(i.this.aO));
                i.this.aR = System.currentTimeMillis();
                i.this.aS = 0;
                i.this.aL.b();
                i.this.aK.b();
                if (i.this.v != null) {
                    str = i.this.v.getWidth() + "*" + i.this.v.getHeight();
                } else {
                    str = null;
                }
                i.this.f = new MaterialClickInfo.a().a(Float.valueOf(com.huawei.openalliance.ad.utils.d.j(i.this.getContext()))).b(str).c((Integer) 2).a();
                i.this.a(19);
            }
        }

        protected b() {
        }
    }

    protected class d extends CountDownTimer {
        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            ho.a("PPSBaseLinkedView", "onTick: %d", Long.valueOf(j));
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            ho.b("PPSBaseLinkedView", "CountDownTimer onFinish");
            if (i.this.ad == 1) {
                i.this.a((Integer) 8, false);
                i.this.ax = 2;
                dj.a(i.this.c);
                if (i.this.at) {
                    return;
                }
                i.this.j();
                i.this.at = true;
            }
        }

        d(long j, long j2) {
            super(j, j2);
        }
    }

    public i(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = Constants.IMP_EVENT_MONITOR_ID + hashCode();
        this.c = "skip_btn_delay_id_" + hashCode();
        this.d = new ub();
        this.e = new uc();
        this.n = 1;
        this.F = true;
        this.M = 0;
        this.N = false;
        this.Q = -1L;
        this.S = false;
        this.T = false;
        this.U = 0;
        this.V = 0;
        this.ae = IEventListener.EVENT_ID_NOTICE_DIALOG_SHOW;
        this.ai = new int[2];
        this.aj = false;
        this.ak = false;
        this.am = false;
        this.an = false;
        this.ao = false;
        this.ap = false;
        this.aq = false;
        this.ar = false;
        this.as = false;
        this.at = false;
        this.au = false;
        this.aw = false;
        this.ay = true;
        this.aA = true;
        this.aB = true;
        this.aF = false;
        this.aG = 0;
        this.ba = false;
        this.bc = new PPSVideoRenderListener() { // from class: com.huawei.openalliance.ad.views.i.6
            @Override // com.huawei.openalliance.ad.media.listener.PPSVideoRenderListener
            public void onVideoRenderStart() {
                ho.a("PPSBaseLinkedView", "onVideoRenderStart, alreadyNotified: %s", Boolean.valueOf(i.this.bp));
                if (i.this.bp) {
                    return;
                }
                i.this.bp = true;
                i.this.f();
            }
        };
        this.bd = new ReportVideoTimeListener() { // from class: com.huawei.openalliance.ad.views.i.7
            @Override // com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener
            public void reportVideoTime(long j) {
                if (ho.a()) {
                    ho.a("PPSBaseLinkedView", "reportVideoTime: %s", Long.valueOf(j));
                }
                if (i.this.p != null) {
                    i.this.p.b(j);
                }
            }
        };
        this.be = new MediaStateListener() { // from class: com.huawei.openalliance.ad.views.i.8
            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onProgress(int i3, int i4) {
                if (i4 > 0 && !i.this.bp) {
                    ho.a("PPSBaseLinkedView", "onProgress onRenderStart, playtime: %s", Integer.valueOf(i4));
                    i.this.bp = true;
                    i.this.f();
                }
                if (i4 > 0) {
                    i.this.o.e(i4);
                    i.this.e.b(true);
                }
                if (i.this.t != null) {
                    i.this.t.onMediaProgress(i3, i4);
                }
                if (i.this.N) {
                    i.this.d.a(i3);
                }
                if (i.this.p != null) {
                    i.this.p.a(i4, i.this.o == null ? 0L : i.this.o.getVideoDuration());
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i3) {
                ho.b("PPSBaseLinkedView", "onMediaStop: %s", Integer.valueOf(i3));
                i.this.a(i3, false);
                if (i.this.t != null) {
                    i.this.t.onMediaStop(i3);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i3) {
                ho.b("PPSBaseLinkedView", "onMediaStart: %s", Integer.valueOf(i3));
                i.this.N = true;
                i.this.e.b(true);
                i.this.R = i3;
                i.this.P = System.currentTimeMillis();
                if (!i.this.aj) {
                    i.this.l();
                    i.this.k();
                }
                nz nzVar = i.this.p;
                if (i3 > 0) {
                    nzVar.j();
                    i.this.d.e();
                } else {
                    nzVar.i();
                    i.this.d.a(i.this.o);
                }
                if (i.this.l != null && i.this.l.isFromExsplash()) {
                    i.this.aD.b(i.this.aC, 1, i.this.l.getSlotId(), i.this.l.getContentId(), (System.currentTimeMillis() - i.this.h.bB().longValue()) - i.this.h.bC());
                }
                if (i.this.t != null) {
                    i.this.t.onMediaStart(i3);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i3) {
                ho.b("PPSBaseLinkedView", "onMediaPause: %s", Integer.valueOf(i3));
                i.this.a(i3, false);
                if (i.this.t != null) {
                    i.this.t.onMediaPause(i3);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i3) {
                ho.b("PPSBaseLinkedView", "onMediaCompletion: %s", Integer.valueOf(i3));
                i.this.a(i3, true);
                i.this.bq = true;
                if (i.this.ad == 2 && i.this.k != null && i.this.k.g()) {
                    ho.b("PPSBaseLinkedView", "onMediaCompletion, start play");
                    i.this.e.a();
                }
                if (i.this.t != null) {
                    i.this.t.onMediaCompletion(i3);
                }
            }
        };
        this.bf = new com.huawei.openalliance.ad.media.listener.a() { // from class: com.huawei.openalliance.ad.views.i.9
            @Override // com.huawei.openalliance.ad.media.listener.a
            public void b(int i3) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.a
            public void a(int i3) {
                ho.b("PPSBaseLinkedView", "onDurationReady:");
                if (!i.this.aw && i.this.s != null) {
                    i.this.aw = true;
                    i.this.s.onPrepared();
                }
                if (i.this.az == null) {
                    i.this.az = Integer.valueOf(i3);
                    if (i.this.l == null || i.this.l.getVideoInfo() == null) {
                        return;
                    }
                    i.this.l.getVideoInfo().a(i3);
                }
            }
        };
        this.bg = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.i.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i3;
                if (!i.this.bo) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                if (i.this.ad == 1) {
                    i iVar = i.this;
                    if (!iVar.a(iVar.f)) {
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                }
                i.this.bo = false;
                ho.b("PPSBaseLinkedView", "onClick");
                if (i.this.ad == 2) {
                    i3 = 10;
                } else {
                    i3 = 2 == i.this.aH.getMode() ? 17 : 9;
                    i.this.g();
                }
                i.this.a(i3);
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.i.10.1
                    @Override // java.lang.Runnable
                    public void run() {
                        i.this.bo = true;
                    }
                }, 500L);
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.bh = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.i.11
            private float b;
            private float c;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    this.b = motionEvent.getX();
                    this.c = motionEvent.getY();
                    if (ho.a()) {
                        ho.a("PPSBaseLinkedView", "startX = %s, startY = %s", Float.valueOf(this.b), Float.valueOf(this.c));
                    }
                    i.this.f = th.a(view, motionEvent);
                }
                if (2 == motionEvent.getAction()) {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    if (ho.a()) {
                        ho.a("PPSBaseLinkedView", "endX = %s, endY = %s, startX - endX = %s, startY-endY = %s", Float.valueOf(x), Float.valueOf(y), Float.valueOf(this.b - x), Float.valueOf(this.c - y));
                    }
                    if (Cdo.a(i.this.aX, i.this.aT, this.b - x, this.c - y)) {
                        i.this.v.setOnTouchListener(null);
                        th.a(view, motionEvent, 1, i.this.f);
                        i.this.a(18);
                    }
                }
                if (1 == motionEvent.getAction()) {
                    th.a(view, motionEvent, null, i.this.f);
                }
                return true;
            }
        };
        this.bi = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.i.12
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.setEnabled(false);
                i.this.v.setOnTouchListener(null);
                if (motionEvent.getAction() != 0) {
                    return true;
                }
                i.this.f = th.b(view, motionEvent);
                if (i.this.f != null) {
                    i.this.f.c(0);
                    i.this.f.a(Float.valueOf(com.huawei.openalliance.ad.utils.d.j(i.this.g)));
                }
                i.this.a(17);
                return true;
            }
        };
        this.bj = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.i.13
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    i.this.f = th.a(view, motionEvent);
                }
                if (1 != motionEvent.getAction()) {
                    return false;
                }
                th.a(view, motionEvent, null, i.this.f);
                return false;
            }
        };
        this.bk = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.views.i.2
            @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
            public void onError(MediaPlayerAgent mediaPlayerAgent, int i3, int i4, int i5) {
                ho.c("PPSBaseLinkedView", "media play error, isMoved: %s", Boolean.valueOf(i.this.aj));
                i.this.h();
                i.this.i();
                i.this.e.b(false);
                if (i.this.t != null) {
                    ho.b("PPSBaseLinkedView", "call onMediaError. ");
                    i.this.t.onMediaError(i3, i4, i5);
                }
            }
        };
        this.bl = new MuteListener() { // from class: com.huawei.openalliance.ad.views.i.3
            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onUnmute() {
                ho.b("PPSBaseLinkedView", "onUnmute");
                if (i.this.u != null) {
                    i.this.u.onUnmute();
                }
                i.this.d.b(1);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onMute() {
                ho.b("PPSBaseLinkedView", "onMute");
                if (i.this.u != null) {
                    i.this.u.onMute();
                }
                i.this.d.b(0);
            }
        };
        this.bm = new MediaBufferListener() { // from class: com.huawei.openalliance.ad.views.i.4
            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferUpdate(int i3) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingStart() {
                i.this.d.g();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingEnd() {
                i.this.d.f();
            }
        };
        this.bn = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.i.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                i.this.a(!view.isSelected());
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.bo = true;
        this.bp = false;
        this.bq = false;
        b(context);
    }

    protected class c implements rp.a {
        private Integer b;
        private Integer c;
        private Integer d;
        private int e;
        private int f;
        private int g;

        @Override // com.huawei.openalliance.ad.rp.a
        public void a(double d, double d2, double d3) {
            ho.b("PPSBaseLinkedView", "xDegree=%s, yDegree=%s, zDegree=%s", Double.valueOf(d), Double.valueOf(d2), Double.valueOf(d3));
            if (this.b == null) {
                this.b = Integer.valueOf((int) d);
            }
            if (this.c == null) {
                this.c = Integer.valueOf((int) d2);
            }
            if (this.d == null) {
                this.d = Integer.valueOf((int) d3);
            }
            i.this.aM = Math.abs(d - ((double) this.e)) > 180.0d ? 360.0d - Math.abs(d - this.b.intValue()) : Math.abs(d - this.b.intValue());
            i.this.aN = Math.abs(d2 - ((double) this.f)) > 180.0d ? 360.0d - Math.abs(d2 - this.c.intValue()) : Math.abs(d - this.b.intValue());
            i.this.aO = Math.abs(d3 - ((double) this.g)) > 180.0d ? 360.0d - Math.abs(d3 - this.d.intValue()) : Math.abs(d - this.b.intValue());
            if (ho.a()) {
                ho.a("PPSBaseLinkedView", "diffX: %s diffY: %s diffZ: %s", Double.valueOf(i.this.aM), Double.valueOf(i.this.aN), Double.valueOf(i.this.aO));
            }
            this.e = (int) d;
            this.f = (int) d2;
            this.g = (int) d3;
        }

        protected c() {
        }
    }

    public i(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = Constants.IMP_EVENT_MONITOR_ID + hashCode();
        this.c = "skip_btn_delay_id_" + hashCode();
        this.d = new ub();
        this.e = new uc();
        this.n = 1;
        this.F = true;
        this.M = 0;
        this.N = false;
        this.Q = -1L;
        this.S = false;
        this.T = false;
        this.U = 0;
        this.V = 0;
        this.ae = IEventListener.EVENT_ID_NOTICE_DIALOG_SHOW;
        this.ai = new int[2];
        this.aj = false;
        this.ak = false;
        this.am = false;
        this.an = false;
        this.ao = false;
        this.ap = false;
        this.aq = false;
        this.ar = false;
        this.as = false;
        this.at = false;
        this.au = false;
        this.aw = false;
        this.ay = true;
        this.aA = true;
        this.aB = true;
        this.aF = false;
        this.aG = 0;
        this.ba = false;
        this.bc = new PPSVideoRenderListener() { // from class: com.huawei.openalliance.ad.views.i.6
            @Override // com.huawei.openalliance.ad.media.listener.PPSVideoRenderListener
            public void onVideoRenderStart() {
                ho.a("PPSBaseLinkedView", "onVideoRenderStart, alreadyNotified: %s", Boolean.valueOf(i.this.bp));
                if (i.this.bp) {
                    return;
                }
                i.this.bp = true;
                i.this.f();
            }
        };
        this.bd = new ReportVideoTimeListener() { // from class: com.huawei.openalliance.ad.views.i.7
            @Override // com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener
            public void reportVideoTime(long j) {
                if (ho.a()) {
                    ho.a("PPSBaseLinkedView", "reportVideoTime: %s", Long.valueOf(j));
                }
                if (i.this.p != null) {
                    i.this.p.b(j);
                }
            }
        };
        this.be = new MediaStateListener() { // from class: com.huawei.openalliance.ad.views.i.8
            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onProgress(int i3, int i4) {
                if (i4 > 0 && !i.this.bp) {
                    ho.a("PPSBaseLinkedView", "onProgress onRenderStart, playtime: %s", Integer.valueOf(i4));
                    i.this.bp = true;
                    i.this.f();
                }
                if (i4 > 0) {
                    i.this.o.e(i4);
                    i.this.e.b(true);
                }
                if (i.this.t != null) {
                    i.this.t.onMediaProgress(i3, i4);
                }
                if (i.this.N) {
                    i.this.d.a(i3);
                }
                if (i.this.p != null) {
                    i.this.p.a(i4, i.this.o == null ? 0L : i.this.o.getVideoDuration());
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i3) {
                ho.b("PPSBaseLinkedView", "onMediaStop: %s", Integer.valueOf(i3));
                i.this.a(i3, false);
                if (i.this.t != null) {
                    i.this.t.onMediaStop(i3);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i3) {
                ho.b("PPSBaseLinkedView", "onMediaStart: %s", Integer.valueOf(i3));
                i.this.N = true;
                i.this.e.b(true);
                i.this.R = i3;
                i.this.P = System.currentTimeMillis();
                if (!i.this.aj) {
                    i.this.l();
                    i.this.k();
                }
                nz nzVar = i.this.p;
                if (i3 > 0) {
                    nzVar.j();
                    i.this.d.e();
                } else {
                    nzVar.i();
                    i.this.d.a(i.this.o);
                }
                if (i.this.l != null && i.this.l.isFromExsplash()) {
                    i.this.aD.b(i.this.aC, 1, i.this.l.getSlotId(), i.this.l.getContentId(), (System.currentTimeMillis() - i.this.h.bB().longValue()) - i.this.h.bC());
                }
                if (i.this.t != null) {
                    i.this.t.onMediaStart(i3);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i3) {
                ho.b("PPSBaseLinkedView", "onMediaPause: %s", Integer.valueOf(i3));
                i.this.a(i3, false);
                if (i.this.t != null) {
                    i.this.t.onMediaPause(i3);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i3) {
                ho.b("PPSBaseLinkedView", "onMediaCompletion: %s", Integer.valueOf(i3));
                i.this.a(i3, true);
                i.this.bq = true;
                if (i.this.ad == 2 && i.this.k != null && i.this.k.g()) {
                    ho.b("PPSBaseLinkedView", "onMediaCompletion, start play");
                    i.this.e.a();
                }
                if (i.this.t != null) {
                    i.this.t.onMediaCompletion(i3);
                }
            }
        };
        this.bf = new com.huawei.openalliance.ad.media.listener.a() { // from class: com.huawei.openalliance.ad.views.i.9
            @Override // com.huawei.openalliance.ad.media.listener.a
            public void b(int i3) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.a
            public void a(int i3) {
                ho.b("PPSBaseLinkedView", "onDurationReady:");
                if (!i.this.aw && i.this.s != null) {
                    i.this.aw = true;
                    i.this.s.onPrepared();
                }
                if (i.this.az == null) {
                    i.this.az = Integer.valueOf(i3);
                    if (i.this.l == null || i.this.l.getVideoInfo() == null) {
                        return;
                    }
                    i.this.l.getVideoInfo().a(i3);
                }
            }
        };
        this.bg = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.i.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i3;
                if (!i.this.bo) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                if (i.this.ad == 1) {
                    i iVar = i.this;
                    if (!iVar.a(iVar.f)) {
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                }
                i.this.bo = false;
                ho.b("PPSBaseLinkedView", "onClick");
                if (i.this.ad == 2) {
                    i3 = 10;
                } else {
                    i3 = 2 == i.this.aH.getMode() ? 17 : 9;
                    i.this.g();
                }
                i.this.a(i3);
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.i.10.1
                    @Override // java.lang.Runnable
                    public void run() {
                        i.this.bo = true;
                    }
                }, 500L);
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.bh = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.i.11
            private float b;
            private float c;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    this.b = motionEvent.getX();
                    this.c = motionEvent.getY();
                    if (ho.a()) {
                        ho.a("PPSBaseLinkedView", "startX = %s, startY = %s", Float.valueOf(this.b), Float.valueOf(this.c));
                    }
                    i.this.f = th.a(view, motionEvent);
                }
                if (2 == motionEvent.getAction()) {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    if (ho.a()) {
                        ho.a("PPSBaseLinkedView", "endX = %s, endY = %s, startX - endX = %s, startY-endY = %s", Float.valueOf(x), Float.valueOf(y), Float.valueOf(this.b - x), Float.valueOf(this.c - y));
                    }
                    if (Cdo.a(i.this.aX, i.this.aT, this.b - x, this.c - y)) {
                        i.this.v.setOnTouchListener(null);
                        th.a(view, motionEvent, 1, i.this.f);
                        i.this.a(18);
                    }
                }
                if (1 == motionEvent.getAction()) {
                    th.a(view, motionEvent, null, i.this.f);
                }
                return true;
            }
        };
        this.bi = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.i.12
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.setEnabled(false);
                i.this.v.setOnTouchListener(null);
                if (motionEvent.getAction() != 0) {
                    return true;
                }
                i.this.f = th.b(view, motionEvent);
                if (i.this.f != null) {
                    i.this.f.c(0);
                    i.this.f.a(Float.valueOf(com.huawei.openalliance.ad.utils.d.j(i.this.g)));
                }
                i.this.a(17);
                return true;
            }
        };
        this.bj = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.i.13
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    i.this.f = th.a(view, motionEvent);
                }
                if (1 != motionEvent.getAction()) {
                    return false;
                }
                th.a(view, motionEvent, null, i.this.f);
                return false;
            }
        };
        this.bk = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.views.i.2
            @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
            public void onError(MediaPlayerAgent mediaPlayerAgent, int i3, int i4, int i5) {
                ho.c("PPSBaseLinkedView", "media play error, isMoved: %s", Boolean.valueOf(i.this.aj));
                i.this.h();
                i.this.i();
                i.this.e.b(false);
                if (i.this.t != null) {
                    ho.b("PPSBaseLinkedView", "call onMediaError. ");
                    i.this.t.onMediaError(i3, i4, i5);
                }
            }
        };
        this.bl = new MuteListener() { // from class: com.huawei.openalliance.ad.views.i.3
            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onUnmute() {
                ho.b("PPSBaseLinkedView", "onUnmute");
                if (i.this.u != null) {
                    i.this.u.onUnmute();
                }
                i.this.d.b(1);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onMute() {
                ho.b("PPSBaseLinkedView", "onMute");
                if (i.this.u != null) {
                    i.this.u.onMute();
                }
                i.this.d.b(0);
            }
        };
        this.bm = new MediaBufferListener() { // from class: com.huawei.openalliance.ad.views.i.4
            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferUpdate(int i3) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingStart() {
                i.this.d.g();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingEnd() {
                i.this.d.f();
            }
        };
        this.bn = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.i.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                i.this.a(!view.isSelected());
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.bo = true;
        this.bp = false;
        this.bq = false;
        b(context);
    }

    public i(Context context) {
        super(context);
        this.b = Constants.IMP_EVENT_MONITOR_ID + hashCode();
        this.c = "skip_btn_delay_id_" + hashCode();
        this.d = new ub();
        this.e = new uc();
        this.n = 1;
        this.F = true;
        this.M = 0;
        this.N = false;
        this.Q = -1L;
        this.S = false;
        this.T = false;
        this.U = 0;
        this.V = 0;
        this.ae = IEventListener.EVENT_ID_NOTICE_DIALOG_SHOW;
        this.ai = new int[2];
        this.aj = false;
        this.ak = false;
        this.am = false;
        this.an = false;
        this.ao = false;
        this.ap = false;
        this.aq = false;
        this.ar = false;
        this.as = false;
        this.at = false;
        this.au = false;
        this.aw = false;
        this.ay = true;
        this.aA = true;
        this.aB = true;
        this.aF = false;
        this.aG = 0;
        this.ba = false;
        this.bc = new PPSVideoRenderListener() { // from class: com.huawei.openalliance.ad.views.i.6
            @Override // com.huawei.openalliance.ad.media.listener.PPSVideoRenderListener
            public void onVideoRenderStart() {
                ho.a("PPSBaseLinkedView", "onVideoRenderStart, alreadyNotified: %s", Boolean.valueOf(i.this.bp));
                if (i.this.bp) {
                    return;
                }
                i.this.bp = true;
                i.this.f();
            }
        };
        this.bd = new ReportVideoTimeListener() { // from class: com.huawei.openalliance.ad.views.i.7
            @Override // com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener
            public void reportVideoTime(long j) {
                if (ho.a()) {
                    ho.a("PPSBaseLinkedView", "reportVideoTime: %s", Long.valueOf(j));
                }
                if (i.this.p != null) {
                    i.this.p.b(j);
                }
            }
        };
        this.be = new MediaStateListener() { // from class: com.huawei.openalliance.ad.views.i.8
            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onProgress(int i3, int i4) {
                if (i4 > 0 && !i.this.bp) {
                    ho.a("PPSBaseLinkedView", "onProgress onRenderStart, playtime: %s", Integer.valueOf(i4));
                    i.this.bp = true;
                    i.this.f();
                }
                if (i4 > 0) {
                    i.this.o.e(i4);
                    i.this.e.b(true);
                }
                if (i.this.t != null) {
                    i.this.t.onMediaProgress(i3, i4);
                }
                if (i.this.N) {
                    i.this.d.a(i3);
                }
                if (i.this.p != null) {
                    i.this.p.a(i4, i.this.o == null ? 0L : i.this.o.getVideoDuration());
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i3) {
                ho.b("PPSBaseLinkedView", "onMediaStop: %s", Integer.valueOf(i3));
                i.this.a(i3, false);
                if (i.this.t != null) {
                    i.this.t.onMediaStop(i3);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i3) {
                ho.b("PPSBaseLinkedView", "onMediaStart: %s", Integer.valueOf(i3));
                i.this.N = true;
                i.this.e.b(true);
                i.this.R = i3;
                i.this.P = System.currentTimeMillis();
                if (!i.this.aj) {
                    i.this.l();
                    i.this.k();
                }
                nz nzVar = i.this.p;
                if (i3 > 0) {
                    nzVar.j();
                    i.this.d.e();
                } else {
                    nzVar.i();
                    i.this.d.a(i.this.o);
                }
                if (i.this.l != null && i.this.l.isFromExsplash()) {
                    i.this.aD.b(i.this.aC, 1, i.this.l.getSlotId(), i.this.l.getContentId(), (System.currentTimeMillis() - i.this.h.bB().longValue()) - i.this.h.bC());
                }
                if (i.this.t != null) {
                    i.this.t.onMediaStart(i3);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i3) {
                ho.b("PPSBaseLinkedView", "onMediaPause: %s", Integer.valueOf(i3));
                i.this.a(i3, false);
                if (i.this.t != null) {
                    i.this.t.onMediaPause(i3);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i3) {
                ho.b("PPSBaseLinkedView", "onMediaCompletion: %s", Integer.valueOf(i3));
                i.this.a(i3, true);
                i.this.bq = true;
                if (i.this.ad == 2 && i.this.k != null && i.this.k.g()) {
                    ho.b("PPSBaseLinkedView", "onMediaCompletion, start play");
                    i.this.e.a();
                }
                if (i.this.t != null) {
                    i.this.t.onMediaCompletion(i3);
                }
            }
        };
        this.bf = new com.huawei.openalliance.ad.media.listener.a() { // from class: com.huawei.openalliance.ad.views.i.9
            @Override // com.huawei.openalliance.ad.media.listener.a
            public void b(int i3) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.a
            public void a(int i3) {
                ho.b("PPSBaseLinkedView", "onDurationReady:");
                if (!i.this.aw && i.this.s != null) {
                    i.this.aw = true;
                    i.this.s.onPrepared();
                }
                if (i.this.az == null) {
                    i.this.az = Integer.valueOf(i3);
                    if (i.this.l == null || i.this.l.getVideoInfo() == null) {
                        return;
                    }
                    i.this.l.getVideoInfo().a(i3);
                }
            }
        };
        this.bg = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.i.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i3;
                if (!i.this.bo) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                if (i.this.ad == 1) {
                    i iVar = i.this;
                    if (!iVar.a(iVar.f)) {
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                }
                i.this.bo = false;
                ho.b("PPSBaseLinkedView", "onClick");
                if (i.this.ad == 2) {
                    i3 = 10;
                } else {
                    i3 = 2 == i.this.aH.getMode() ? 17 : 9;
                    i.this.g();
                }
                i.this.a(i3);
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.i.10.1
                    @Override // java.lang.Runnable
                    public void run() {
                        i.this.bo = true;
                    }
                }, 500L);
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.bh = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.i.11
            private float b;
            private float c;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    this.b = motionEvent.getX();
                    this.c = motionEvent.getY();
                    if (ho.a()) {
                        ho.a("PPSBaseLinkedView", "startX = %s, startY = %s", Float.valueOf(this.b), Float.valueOf(this.c));
                    }
                    i.this.f = th.a(view, motionEvent);
                }
                if (2 == motionEvent.getAction()) {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    if (ho.a()) {
                        ho.a("PPSBaseLinkedView", "endX = %s, endY = %s, startX - endX = %s, startY-endY = %s", Float.valueOf(x), Float.valueOf(y), Float.valueOf(this.b - x), Float.valueOf(this.c - y));
                    }
                    if (Cdo.a(i.this.aX, i.this.aT, this.b - x, this.c - y)) {
                        i.this.v.setOnTouchListener(null);
                        th.a(view, motionEvent, 1, i.this.f);
                        i.this.a(18);
                    }
                }
                if (1 == motionEvent.getAction()) {
                    th.a(view, motionEvent, null, i.this.f);
                }
                return true;
            }
        };
        this.bi = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.i.12
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.setEnabled(false);
                i.this.v.setOnTouchListener(null);
                if (motionEvent.getAction() != 0) {
                    return true;
                }
                i.this.f = th.b(view, motionEvent);
                if (i.this.f != null) {
                    i.this.f.c(0);
                    i.this.f.a(Float.valueOf(com.huawei.openalliance.ad.utils.d.j(i.this.g)));
                }
                i.this.a(17);
                return true;
            }
        };
        this.bj = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.i.13
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    i.this.f = th.a(view, motionEvent);
                }
                if (1 != motionEvent.getAction()) {
                    return false;
                }
                th.a(view, motionEvent, null, i.this.f);
                return false;
            }
        };
        this.bk = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.views.i.2
            @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
            public void onError(MediaPlayerAgent mediaPlayerAgent, int i3, int i4, int i5) {
                ho.c("PPSBaseLinkedView", "media play error, isMoved: %s", Boolean.valueOf(i.this.aj));
                i.this.h();
                i.this.i();
                i.this.e.b(false);
                if (i.this.t != null) {
                    ho.b("PPSBaseLinkedView", "call onMediaError. ");
                    i.this.t.onMediaError(i3, i4, i5);
                }
            }
        };
        this.bl = new MuteListener() { // from class: com.huawei.openalliance.ad.views.i.3
            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onUnmute() {
                ho.b("PPSBaseLinkedView", "onUnmute");
                if (i.this.u != null) {
                    i.this.u.onUnmute();
                }
                i.this.d.b(1);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onMute() {
                ho.b("PPSBaseLinkedView", "onMute");
                if (i.this.u != null) {
                    i.this.u.onMute();
                }
                i.this.d.b(0);
            }
        };
        this.bm = new MediaBufferListener() { // from class: com.huawei.openalliance.ad.views.i.4
            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferUpdate(int i3) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingStart() {
                i.this.d.g();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingEnd() {
                i.this.d.f();
            }
        };
        this.bn = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.i.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                i.this.a(!view.isSelected());
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.bo = true;
        this.bp = false;
        this.bq = false;
        b(context);
    }
}
