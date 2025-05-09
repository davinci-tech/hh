package com.huawei.openalliance.ad.views;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.ads.ChoicesView;
import com.huawei.hms.ads.uiengine.common.IInterstitialView;
import com.huawei.hms.ads.uiengine.common.InterstitialApi;
import com.huawei.openalliance.ad.activity.ComplianceActivity;
import com.huawei.openalliance.ad.beans.metadata.AdSource;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.RewardMethods;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.dh;
import com.huawei.openalliance.ad.dk;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.je;
import com.huawei.openalliance.ad.jk;
import com.huawei.openalliance.ad.lm;
import com.huawei.openalliance.ad.lo;
import com.huawei.openalliance.ad.lz;
import com.huawei.openalliance.ad.media.listener.MuteListener;
import com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener;
import com.huawei.openalliance.ad.mo;
import com.huawei.openalliance.ad.nc;
import com.huawei.openalliance.ad.os;
import com.huawei.openalliance.ad.ox;
import com.huawei.openalliance.ad.sz;
import com.huawei.openalliance.ad.ta;
import com.huawei.openalliance.ad.th;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.ax;
import com.huawei.openalliance.ad.utils.az;
import com.huawei.openalliance.ad.utils.bc;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.bv;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.y;
import com.huawei.openalliance.ad.views.gif.GifPlayView;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class PPSInterstitialView extends AutoScaleSizeRelativeLayout implements View.OnClickListener, IInterstitialView, je.a, jk, lm, MuteListener, SegmentMediaStateListener, com.huawei.openalliance.ad.media.listener.a, NetworkChangeListener {
    private int A;
    private boolean B;
    private long C;
    private int D;
    private long E;
    private boolean F;
    private boolean G;
    private boolean H;
    private int I;
    private int J;
    private long K;
    private boolean L;
    private boolean M;
    private je N;
    private com.huawei.openalliance.ad.inter.listeners.a O;
    private final String P;
    private AdLandingPageData Q;
    private int R;
    private CountDownTimer S;
    private int T;
    private Context U;
    private boolean V;
    private boolean W;

    /* renamed from: a, reason: collision with root package name */
    private lz f7869a;
    private dk aa;
    private PPSAppDetailTemplateView ab;
    private TextView ac;
    private PPSAppDownLoadWithAnimationView ad;
    private MaterialClickInfo ae;
    private boolean af;
    private boolean ag;
    private TextView ah;
    private int ai;
    private int aj;
    private com.huawei.openalliance.ad.inter.data.d b;
    private ContentRecord c;
    private String d;
    private PPSInterstitialVideoView e;
    private ImageView f;
    private int g;
    private View h;
    private PPSAppDetailView i;
    private PPSAppDetailView j;
    private PPSExpandButtonDetailView k;
    private TextView l;
    private ImageView m;
    private GifPlayView n;
    private ViewGroup o;
    private TextView p;
    private PPSLabelSourceView q;
    private PPSLabelView r;
    private boolean s;
    private b t;
    private nc u;
    private ChoicesView v;
    private boolean w;
    private Dialog x;
    private ProgressBar y;
    private boolean z;

    public interface b {
        void b();
    }

    @Override // com.huawei.openalliance.ad.media.listener.a
    public void b(int i) {
    }

    @Override // com.huawei.hms.ads.uiengine.common.IInterstitialView
    public void bindData(String str) {
    }

    @Override // com.huawei.hms.ads.uiengine.common.IInterstitialView
    public ViewGroup getContentContainer() {
        return null;
    }

    @Override // com.huawei.openalliance.ad.lm
    public View getOpenMeasureView() {
        return this;
    }

    @Override // com.huawei.hms.ads.uiengine.common.IInterstitialView
    public void onAppStatusChanged(String str) {
    }

    @Override // com.huawei.hms.ads.uiengine.common.IInterstitialView
    public void onBtnClick(Bundle bundle) {
    }

    @Override // com.huawei.hms.ads.uiengine.common.IInterstitialView
    public void onCallBack(String str, Bundle bundle) {
    }

    @Override // com.huawei.openalliance.ad.views.NetworkChangeListener
    public void onNetworkDisconnected() {
    }

    @Override // com.huawei.hms.ads.uiengine.common.IInterstitialView
    public void setIsNeedRemindData(boolean z) {
    }

    @Override // com.huawei.hms.ads.uiengine.common.IInterstitialView
    public void setProxy(InterstitialApi interstitialApi) {
    }

    public void setOnCloseListener(b bVar) {
        this.t = bVar;
    }

    public void setFullScreen(int i) {
        this.T = i;
    }

    @Override // com.huawei.hms.ads.uiengine.common.IInterstitialView
    public void resumeVideoView() {
        b(true);
    }

    public void removeInterstitialAdStatusListener() {
        this.O = null;
    }

    @Override // com.huawei.hms.ads.uiengine.common.IInterstitialView
    public void pauseView() {
        ho.b("PPSInterstitialView", RewardMethods.PAUSE_VIEW);
        setTryResumeVideoView(false);
        PPSInterstitialVideoView pPSInterstitialVideoView = this.e;
        if (pPSInterstitialVideoView != null) {
            pPSInterstitialVideoView.d();
        }
        if (t()) {
            View view = this.h;
            if (view != null) {
                view.setVisibility(0);
            }
            PPSInterstitialVideoView pPSInterstitialVideoView2 = this.e;
            if (pPSInterstitialVideoView2 != null) {
                pPSInterstitialVideoView2.a();
            }
        }
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        je jeVar = this.N;
        if (jeVar != null) {
            jeVar.j();
        }
    }

    @Override // com.huawei.openalliance.ad.media.listener.MuteListener
    public void onUnmute() {
        this.s = false;
        a(false);
    }

    @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
    public void onSegmentProgress(String str, String str2, int i, int i2) {
        int i3;
        boolean z = this.B;
        if (!z && this.A < 0) {
            this.A = i2;
            this.B = true;
        } else if (z && (i3 = this.A) >= 0) {
            long j = i2 - i3;
            this.C = j;
            je jeVar = this.N;
            if (jeVar != null) {
                c(this.E + j, jeVar.c());
            }
            nc ncVar = this.u;
            if (ncVar != null) {
                ncVar.a(i2, this.J);
            }
        }
        int i4 = this.J;
        if (i2 > i4 && i4 > 0) {
            i2 = i4;
        }
        int i5 = i2 / 1000;
        if (this.R * 1000 >= i4) {
            this.R = i4 / 1000;
        }
        int i6 = this.R - i5;
        if (i6 > 0) {
            a(i6, false);
        } else {
            ImageView imageView = this.m;
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            a(i5, true);
        }
        if (i2 >= this.J) {
            this.E += i2 - this.A;
            PPSInterstitialVideoView pPSInterstitialVideoView = this.e;
            if (pPSInterstitialVideoView != null) {
                pPSInterstitialVideoView.c(i2);
            }
            TextView textView = this.p;
            if (textView != null) {
                textView.setVisibility(8);
            }
            ho.b("PPSInterstitialView", "check end");
            onSegmentMediaCompletion(null, str2, i2);
        }
    }

    @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
    public void onSegmentMediaStop(String str, String str2, int i) {
        if (ho.a()) {
            ho.a("PPSInterstitialView", "video stop");
        }
        if (this.H) {
            return;
        }
        c(i);
    }

    @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
    public void onSegmentMediaStart(String str, String str2, int i) {
        com.huawei.openalliance.ad.inter.listeners.a aVar;
        if (ho.a()) {
            ho.a("PPSInterstitialView", "video start");
        }
        if (this.V) {
            TextView textView = this.p;
            if (textView != null) {
                textView.setVisibility(0);
            }
            this.V = false;
        }
        ProgressBar progressBar = this.y;
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
        if (!this.B && (aVar = this.O) != null) {
            aVar.f();
        }
        this.B = true;
        this.A = i;
    }

    @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
    public void onSegmentMediaPause(String str, String str2, int i) {
        if (ho.a()) {
            ho.a("PPSInterstitialView", "video pause");
        }
        c(i);
    }

    @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
    public void onSegmentMediaError(String str, String str2, int i, int i2, int i3) {
        ho.b("PPSInterstitialView", "onSegmentMediaError");
        TextView textView = this.p;
        if (textView != null) {
            textView.setVisibility(8);
        }
        ImageView imageView = this.m;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        if (!bv.e(getContext())) {
            Toast.makeText(getContext().getApplicationContext(), R.string._2130851113_res_0x7f023529, 0).show();
        }
        c(i);
        com.huawei.openalliance.ad.inter.listeners.a aVar = this.O;
        if (aVar != null) {
            aVar.a(i2, i3);
        }
    }

    @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
    public void onSegmentMediaCompletion(String str, String str2, int i) {
        if (ho.a()) {
            ho.a("PPSInterstitialView", "video end");
        }
        if (!this.H) {
            this.H = true;
            c(i);
            j();
        }
        r();
    }

    @Override // com.huawei.openalliance.ad.views.NetworkChangeListener
    public void onNetworkConnectedOrChanged(boolean z) {
        this.z = !z;
        com.huawei.openalliance.ad.inter.data.d dVar = this.b;
        boolean z2 = dVar != null && dVar.ac();
        if (z || !q() || o() || !z2) {
            return;
        }
        pauseView();
        w();
    }

    @Override // com.huawei.openalliance.ad.media.listener.MuteListener
    public void onMute() {
        this.s = true;
        a(true);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ho.b("PPSInterstitialView", "onDetechedFromWindow");
        je jeVar = this.N;
        if (jeVar != null) {
            jeVar.i();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (!this.af) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        this.af = false;
        int id = view.getId();
        com.huawei.openalliance.ad.utils.ad.b();
        if (id == R.id.interstitial_close) {
            i();
        } else if (id == R.id.interstitial_video_view || id == R.id.interstitial_image_view || id == R.id.interstitial_content_view) {
            h();
        } else {
            ho.d("PPSInterstitialView", "un handle action");
        }
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSInterstitialView.5
            @Override // java.lang.Runnable
            public void run() {
                PPSInterstitialView.this.af = true;
            }
        }, 500L);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ho.a("PPSInterstitialView", "onAttachedToWindow");
        je jeVar = this.N;
        if (jeVar != null) {
            jeVar.h();
        }
    }

    public int getmInsreTemplate() {
        return this.g;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            int a2 = th.a(motionEvent);
            if (a2 == 0) {
                this.ae = th.a(this, motionEvent);
            }
            if (1 == a2) {
                th.a(this, motionEvent, null, this.ae);
            }
        } catch (Throwable th) {
            ho.c("PPSInterstitialView", "dispatchTouchEvent exception : %s", th.getClass().getSimpleName());
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.huawei.hms.ads.uiengine.common.IInterstitialView
    public void destroyView() {
        dj.a(this.P);
        nc ncVar = this.u;
        if (ncVar != null) {
            ncVar.a((com.huawei.openalliance.ad.inter.data.d) null);
        }
        je jeVar = this.N;
        if (jeVar != null) {
            jeVar.b();
        }
        if (this.e != null) {
            setTryResumeVideoView(false);
            if (t()) {
                this.e.f();
            }
            this.e.e();
        }
        CountDownTimer countDownTimer = this.S;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.S = null;
        }
        this.f7869a.b();
    }

    @Override // com.huawei.openalliance.ad.je.a
    public void b(long j, int i) {
        dj.a(this.P);
        if (!this.G) {
            this.G = true;
            nc ncVar = this.u;
            if (ncVar != null) {
                ncVar.a(j, i);
            }
        }
        G();
        pauseView();
    }

    @Override // com.huawei.openalliance.ad.je.a
    public void b() {
        this.F = false;
        this.G = false;
        long c = ao.c();
        ho.a("PPSInterstitialView", "onViewPhysicalShowStart, currentTime: %s", Long.valueOf(c));
        String valueOf = String.valueOf(c);
        com.huawei.openalliance.ad.inter.data.d dVar = this.b;
        if (dVar != null) {
            dVar.g(false);
            this.b.a(false);
        }
        ContentRecord contentRecord = this.c;
        if (contentRecord != null) {
            contentRecord.c(valueOf);
            this.c.f(c);
        }
        nc ncVar = this.u;
        if (ncVar != null) {
            ncVar.a(valueOf);
            this.u.a(c);
        }
        PPSInterstitialVideoView pPSInterstitialVideoView = this.e;
        if (pPSInterstitialVideoView != null) {
            pPSInterstitialVideoView.a(valueOf);
            this.e.a(c);
        }
        AdLandingPageData adLandingPageData = this.Q;
        if (adLandingPageData != null && this.i != null) {
            adLandingPageData.c(valueOf);
            this.Q.a(c);
            this.i.setAdLandingData(this.Q);
        }
        nc ncVar2 = this.u;
        if (ncVar2 != null) {
            ncVar2.c();
        }
        resumeVideoView();
    }

    public void addInterstitialAdStatusListener(com.huawei.openalliance.ad.inter.listeners.a aVar) {
        if (aVar == null) {
            return;
        }
        this.O = aVar;
    }

    public void a(Integer num) {
        if (this.f7869a == null || num == null) {
            return;
        }
        int intValue = num.intValue();
        if (intValue == 1) {
            this.f7869a.a(mo.CLICK);
        } else if (intValue == 3 && !this.H) {
            this.f7869a.j();
            this.f7869a.b();
        }
    }

    public void a(com.huawei.openalliance.ad.inter.data.d dVar, int i, String str) {
        this.R = fh.b(this.U).bP();
        this.b = dVar;
        ContentRecord a2 = ox.a(dVar);
        this.c = a2;
        this.Q = new AdLandingPageData(a2, getContext(), true);
        this.d = str;
        this.I = i;
        H();
        try {
            a(this.U, this.c);
            je jeVar = this.N;
            if (jeVar != null) {
                jeVar.b(this.b.getMinEffectiveShowTime(), this.b.getMinEffectiveShowRatio());
            }
            nc ncVar = this.u;
            if (ncVar != null) {
                ncVar.a(this.b);
            }
            x();
            g();
            f();
            E();
            d(R.drawable._2131428575_res_0x7f0b04df);
            b(this.b.getLabel());
            com.huawei.openalliance.ad.inter.listeners.a aVar = this.O;
            if (aVar != null) {
                aVar.a();
            }
            if ((this.f.getVisibility() == 0 || this.v.getVisibility() == 0) && this.ah.getVisibility() == 0) {
                this.aj = this.ai == R.layout.hiad_interstitial_half_layout ? getResources().getDimensionPixelSize(R.dimen._2131362825_res_0x7f0a0409) : getResources().getDimensionPixelSize(R.dimen._2131362824_res_0x7f0a0408);
                if (dd.c()) {
                    this.aj = 0;
                }
                this.q.setPadding(0, 0, ao.a(getContext(), this.aj), 0);
            }
            d(ox.a(dVar));
            if (fh.b(this.U).bR() == 1 && this.n != null && e()) {
                ViewGroup.LayoutParams layoutParams = this.n.getLayoutParams();
                layoutParams.width = -1;
                layoutParams.height = -1;
                this.n.setLayoutParams(layoutParams);
            }
        } catch (RuntimeException | Exception unused) {
            ho.d("PPSInterstitialView", "refresh ui error");
        }
    }

    @Override // com.huawei.openalliance.ad.je.a
    public void a(long j, int i) {
        je jeVar;
        dj.a(this.P);
        if (this.L) {
            if ((u() || v()) && (jeVar = this.N) != null) {
                c(j - (this.K - jeVar.d()), i);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.media.listener.a
    public void a(int i) {
        ho.a("PPSInterstitialView", "onDurationReady %d", Integer.valueOf(i));
        if (i > 0) {
            this.J = i;
        }
    }

    @Override // com.huawei.openalliance.ad.je.a
    public void a() {
        this.A = -1;
        this.B = false;
        this.E = 0L;
        if (this.L) {
            F();
        }
    }

    private boolean z() {
        return this.T != 1 || ao.n(this.U);
    }

    private void y() {
        if (z() && c(this.c) && A()) {
            this.i = this.ad;
            return;
        }
        this.j = (PPSAppDetailView) findViewById(R.id.interstitial_download_area);
        this.k = (PPSExpandButtonDetailView) findViewById(R.id.interstitial_expand_button_download_area);
        PPSAppDetailView pPSAppDetailView = (D() || this.W) ? this.k : this.j;
        this.i = pPSAppDetailView;
        pPSAppDetailView.setBackgroundColor(getResources().getColor(R.color._2131297903_res_0x7f09066f));
    }

    private void x() {
        if (bc.b(this.b.C())) {
            this.W = true;
        }
        y();
        this.i.setVisibility(0);
        if (bc.d(this.b.C())) {
            this.Q.c(true);
        } else {
            B();
        }
        this.i.setAppDetailClickListener(new com.huawei.openalliance.ad.views.interfaces.c() { // from class: com.huawei.openalliance.ad.views.PPSInterstitialView.7
            @Override // com.huawei.openalliance.ad.views.interfaces.c
            public void a(com.huawei.openalliance.ad.views.interfaces.b bVar) {
                if (bVar == null || bVar.e() == null) {
                    ho.c("PPSInterstitialView", "invalid click");
                    return;
                }
                int intValue = bVar.e().intValue();
                ho.b("PPSInterstitialView", "action: %d", Integer.valueOf(intValue));
                if (intValue != 0) {
                    return;
                }
                PPSInterstitialView.this.a(bVar);
            }
        });
        this.i.setNeedPerBeforDownload(true);
        int i = this.g;
        if (i == 1 || i == 3 || i == 5) {
            this.i.setLoadAppIconSelf(false);
        }
        this.i.setAdLandingData(this.Q);
        this.i.setInteractedListener(new com.huawei.openalliance.ad.views.interfaces.a() { // from class: com.huawei.openalliance.ad.views.PPSInterstitialView.8
            @Override // com.huawei.openalliance.ad.views.interfaces.a
            public void a(int i2) {
                PPSInterstitialView.this.c(Integer.valueOf(i2));
            }
        });
        if (this.i.getVisibility() == 8) {
            this.q.setVisibility(0);
        }
        a(this.Q.getAppInfo());
    }

    private void w() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSInterstitialView.6
            @Override // java.lang.Runnable
            public void run() {
                if (PPSInterstitialView.this.x != null && PPSInterstitialView.this.x.isShowing()) {
                    PPSInterstitialView.this.x.dismiss();
                }
                ho.b("PPSInterstitialView", "pop up dialog");
                Resources resources = PPSInterstitialView.this.getResources();
                String string = resources.getString(R.string._2130851053_res_0x7f0234ed);
                String string2 = resources.getString(R.string._2130851147_res_0x7f02354b);
                String string3 = resources.getString(R.string._2130851146_res_0x7f02354a);
                PPSInterstitialView pPSInterstitialView = PPSInterstitialView.this;
                pPSInterstitialView.x = com.huawei.openalliance.ad.utils.y.a(pPSInterstitialView.getContext(), "", string, string2, string3, new y.a() { // from class: com.huawei.openalliance.ad.views.PPSInterstitialView.6.1
                    @Override // com.huawei.openalliance.ad.utils.y.a
                    public void a(boolean z) {
                        PPSInterstitialView.this.i();
                    }

                    @Override // com.huawei.openalliance.ad.utils.y.a
                    public void a() {
                        PPSInterstitialView.this.z = false;
                        PPSInterstitialView.this.b(false);
                    }
                });
                PPSInterstitialView.this.x.setCancelable(false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean v() {
        com.huawei.openalliance.ad.inter.data.d dVar = this.b;
        return dVar != null && dVar.getCreativeType() == 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean u() {
        com.huawei.openalliance.ad.inter.data.d dVar = this.b;
        return dVar != null && dVar.getCreativeType() == 2;
    }

    private boolean t() {
        com.huawei.openalliance.ad.inter.data.d dVar = this.b;
        return (dVar == null || dVar.I() == null || this.b.getCreativeType() != 9) ? false : true;
    }

    private void setTryResumeVideoView(boolean z) {
        this.ag = z;
    }

    private void s() {
        if (q()) {
            ho.b("PPSInterstitialView", "played");
            return;
        }
        View view = this.h;
        if (view != null) {
            view.setVisibility(8);
        }
        PPSInterstitialVideoView pPSInterstitialVideoView = this.e;
        if (pPSInterstitialVideoView != null) {
            pPSInterstitialVideoView.a(true, this.s);
        }
    }

    private void r() {
        ho.b("PPSInterstitialView", "loop");
        if (n()) {
            s();
            return;
        }
        if (q()) {
            this.e.a();
        }
        this.z = true;
        View view = this.h;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    private boolean q() {
        return this.e != null && t() && this.e.b();
    }

    private boolean o() {
        String videoDownloadUrl = this.b.I().getVideoDownloadUrl();
        boolean z = (cz.j(videoDownloadUrl) && TextUtils.isEmpty(this.aa.e(videoDownloadUrl))) ? false : true;
        ho.b("PPSInterstitialView", "cache %s", Boolean.valueOf(z));
        return z;
    }

    private boolean n() {
        if (t()) {
            return o() || bv.c(getContext());
        }
        return false;
    }

    private boolean m() {
        return (os.G(this.b.getCtrlSwitchs()) || this.b.ac()) ? false : true;
    }

    private boolean l() {
        if (!q()) {
            return n() || m();
        }
        ho.b("PPSInterstitialView", "in play");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (!l()) {
            if (!bv.e(getContext())) {
                Toast.makeText(getContext().getApplicationContext(), R.string._2130851113_res_0x7f023529, 0).show();
                return;
            } else if (this.z) {
                w();
                return;
            }
        }
        s();
    }

    private void j() {
        TextView textView = this.p;
        if (textView != null) {
            textView.setVisibility(8);
        }
        ImageView imageView = this.m;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        com.huawei.openalliance.ad.inter.listeners.a aVar = this.O;
        if (aVar != null) {
            aVar.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        b bVar = this.t;
        if (bVar != null) {
            bVar.b();
        }
        nc ncVar = this.u;
        if (ncVar != null) {
            ncVar.b();
        }
        c((Integer) 3);
        a((Integer) 3);
        com.huawei.openalliance.ad.inter.listeners.a aVar = this.O;
        if (aVar != null) {
            aVar.d();
        }
        CountDownTimer countDownTimer = this.S;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.S = null;
        }
    }

    private void h() {
        com.huawei.openalliance.ad.inter.listeners.a aVar = this.O;
        if (aVar != null) {
            aVar.b();
        }
        c((Integer) 1);
        b((Integer) null);
    }

    private void g() {
        if (c(this.c)) {
            int i = this.g;
            if (i == 3 || i == 4 || i == 5) {
                PPSAppDetailTemplateView pPSAppDetailTemplateView = this.ab;
                if (pPSAppDetailTemplateView != null) {
                    pPSAppDetailTemplateView.setVisibility(0);
                    this.ab.setAdLandingData(this.Q);
                }
                if (this.ac != null) {
                    com.huawei.openalliance.ad.inter.data.d dVar = this.b;
                    if (dVar == null || dVar.getAppInfo() == null || TextUtils.isEmpty(this.b.getAppInfo().getAppDesc())) {
                        this.ac.setVisibility(4);
                    } else {
                        this.ac.setVisibility(0);
                        this.ac.setText(this.b.getAppInfo().getAppDesc());
                    }
                }
                a(this.U);
                C();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(int i) {
        this.p.setVisibility(0);
        CountDownTimer countDownTimer = this.S;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = new CountDownTimer(i, 500L) { // from class: com.huawei.openalliance.ad.views.PPSInterstitialView.4
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                int i2 = (int) (j / 1000);
                PPSInterstitialView.this.a(i2 + 1, false);
                ho.a("PPSInterstitialView", "count down time: %d seconds: %d", Long.valueOf(j), Integer.valueOf(i2));
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                PPSInterstitialView.this.p.setVisibility(8);
                PPSInterstitialView.this.m.setVisibility(0);
            }
        };
        this.S = countDownTimer2;
        countDownTimer2.start();
    }

    private void f() {
        if (fh.b(this.U).bS() == 0 || !z()) {
            return;
        }
        this.o.setOnClickListener(this);
        PPSAppDetailTemplateView pPSAppDetailTemplateView = this.ab;
        if (pPSAppDetailTemplateView != null) {
            pPSAppDetailTemplateView.setEnabled(false);
        }
    }

    private boolean e() {
        int i = this.g;
        return (i == 3 || i == 4 || i == 5) ? false : true;
    }

    private int e(int i) {
        int i2 = (this.J / 1000) - i;
        if (i2 < 0) {
            return 0;
        }
        return i2;
    }

    private void d(ContentRecord contentRecord) {
        if (contentRecord == null || contentRecord.b(this.U) == null) {
            return;
        }
        this.f7869a.a(getContext(), contentRecord, this, true);
        this.f7869a.a(false);
        this.f7869a.c();
        a(this.f7869a, contentRecord);
    }

    private void d(final int i) {
        if (t()) {
            this.n.setVisibility(8);
            return;
        }
        String c = this.aa.c(this.b.aa());
        if (com.huawei.openalliance.ad.utils.ae.b(c)) {
            az.a(getContext(), c, new az.a() { // from class: com.huawei.openalliance.ad.views.PPSInterstitialView.12
                @Override // com.huawei.openalliance.ad.utils.az.a
                public void a(final Drawable drawable) {
                    dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSInterstitialView.12.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (PPSInterstitialView.this.T == 1 && !ao.n(PPSInterstitialView.this.U)) {
                                PPSInterstitialView.this.o.setBackgroundColor(PPSInterstitialView.this.getResources().getColor(R.color._2131297899_res_0x7f09066b));
                            }
                            if (drawable instanceof com.huawei.openalliance.ad.views.gif.b) {
                                PPSInterstitialView.this.n.setImageDrawable(drawable);
                                ((com.huawei.openalliance.ad.views.gif.b) drawable).a(PPSInterstitialView.this.new a(drawable));
                            } else {
                                PPSInterstitialView.this.y.setVisibility(8);
                                PPSInterstitialView.this.n.setImageDrawable(drawable);
                                PPSInterstitialView.this.L = true;
                                PPSInterstitialView.this.K = System.currentTimeMillis();
                                PPSInterstitialView.this.F();
                            }
                            PPSInterstitialView.this.f(PPSInterstitialView.this.R * 1000);
                        }
                    });
                }

                @Override // com.huawei.openalliance.ad.utils.az.a
                public void a() {
                    ho.d("PPSInterstitialView", "loadImage fail");
                    dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSInterstitialView.12.2
                        @Override // java.lang.Runnable
                        public void run() {
                            PPSInterstitialView.this.p.setVisibility(8);
                            PPSInterstitialView.this.m.setVisibility(0);
                            PPSInterstitialView.this.n.setImageResource(i);
                        }
                    });
                }
            });
        } else {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSInterstitialView.2
                @Override // java.lang.Runnable
                public void run() {
                    PPSInterstitialView.this.p.setVisibility(8);
                    PPSInterstitialView.this.m.setVisibility(0);
                }
            });
        }
    }

    private void d() {
        this.p.setMaxWidth((int) (com.huawei.openalliance.ad.utils.d.a(getContext(), dd.k(getContext())) * 0.5d));
    }

    private boolean c(ContentRecord contentRecord) {
        if (contentRecord == null) {
            return false;
        }
        return bc.a(contentRecord.B());
    }

    private boolean c() {
        int i = this.ai;
        return i == R.layout.hiad_interstitial_layout || i == R.layout.hiad_interstitial_half_layout || this.g == 5 || bc.c(this.c.B());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Integer num) {
        if (this.N != null) {
            a(Long.valueOf(System.currentTimeMillis() - this.N.d()), Integer.valueOf(this.N.c()), num);
        }
    }

    private void c(long j, int i) {
        com.huawei.openalliance.ad.inter.data.d dVar = this.b;
        if (dVar == null || this.F || j <= dVar.getMinEffectiveShowTime() || i < this.b.getMinEffectiveShowRatio()) {
            return;
        }
        this.F = true;
        a(Long.valueOf(j), Integer.valueOf(i), (Integer) null);
    }

    private void c(int i) {
        int i2;
        if (this.B && (i2 = this.A) >= 0) {
            this.C = i - i2;
            this.B = false;
        }
        this.A = -1;
    }

    private boolean b(Integer num) {
        HashMap hashMap = new HashMap();
        com.huawei.openalliance.ad.inter.data.d dVar = this.b;
        if (dVar != null) {
            hashMap.put("appId", dVar.H());
            hashMap.put("thirdId", this.b.G());
        }
        ta a2 = sz.a(getContext(), this.c, hashMap);
        if (!a2.a()) {
            return false;
        }
        com.huawei.openalliance.ad.inter.listeners.a aVar = this.O;
        if (aVar != null) {
            aVar.e();
        }
        nc ncVar = this.u;
        if (ncVar != null) {
            ncVar.a(a2, num, this.ae);
            this.ae = null;
        }
        a((Integer) 1);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        ho.b("PPSInterstitialView", "resume %s", Boolean.valueOf(z));
        if (t()) {
            if (z && this.ag) {
                return;
            }
            this.ag = true;
            PPSInterstitialVideoView pPSInterstitialVideoView = this.e;
            if (pPSInterstitialVideoView != null) {
                pPSInterstitialVideoView.c();
                k();
                if (this.s) {
                    this.e.g();
                } else {
                    this.e.h();
                }
            }
            a(this.s);
        }
    }

    private void b(String str) {
        if (!this.w || TextUtils.isEmpty(str)) {
            this.l.setVisibility(8);
        } else {
            this.l.setText(str);
            if (this.ah.getVisibility() == 8) {
                this.l.setVisibility(0);
            }
        }
        if (this.w) {
            return;
        }
        String adChoiceUrl = this.b.getAdChoiceUrl();
        String adChoiceIcon = this.b.getAdChoiceIcon();
        if (!TextUtils.isEmpty(adChoiceUrl)) {
            if (TextUtils.isEmpty(adChoiceIcon)) {
                this.v.b();
            } else {
                this.v.setAdChoiceIcon(adChoiceIcon);
            }
        }
        this.v.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSInterstitialView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PPSInterstitialView.this.b == null) {
                    ho.c("PPSInterstitialView", "AdInfo is null or contentData is null");
                } else if (bg.a(PPSInterstitialView.this.b.getCompliance())) {
                    com.huawei.openalliance.ad.utils.d.a(PPSInterstitialView.this.getContext(), PPSInterstitialView.this.b);
                } else {
                    ComplianceActivity.a(PPSInterstitialView.this.getContext(), view, ox.a(PPSInterstitialView.this.b), true);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private String b(int i, boolean z) {
        return (z || "1".equals(this.b.getAdSign())) ? getResources().getQuantityString(R.plurals._2130903535_res_0x7f0301ef, i, Integer.valueOf(i)) : getResources().getQuantityString(R.plurals._2130903531_res_0x7f0301eb, i, Integer.valueOf(i));
    }

    private int b(ContentRecord contentRecord) {
        int H = (contentRecord == null || contentRecord.h() == null) ? 2 : contentRecord.h().H();
        if ((this.T == 1 && !ao.n(this.U)) || H < 1 || H > 5 || ((H == 1 || H == 5) && (contentRecord == null || contentRecord.ae() == null || TextUtils.isEmpty(contentRecord.ae().getIconUrl())))) {
            return 2;
        }
        if (2 == getResources().getConfiguration().orientation && H != 1) {
            return 2;
        }
        int k = dd.k(getContext());
        if (((k == 0 || 8 == k) && H != 1) || !c(contentRecord)) {
            return 2;
        }
        return H;
    }

    private void a(boolean z) {
        int a2 = dd.a(this.g, z);
        ImageView imageView = this.f;
        if (imageView != null) {
            imageView.setImageResource(a2);
        }
    }

    private void a(String str) {
        if (!this.w || TextUtils.isEmpty(str)) {
            this.ah.setVisibility(8);
            return;
        }
        this.ah.setText(str);
        this.l.setVisibility(8);
        this.ah.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Long l, Integer num, Integer num2) {
        com.huawei.openalliance.ad.inter.data.d dVar = this.b;
        if (dVar == null) {
            return;
        }
        boolean a2 = com.huawei.openalliance.ad.utils.c.a(dVar.d(), num2);
        if (!this.b.P() || (a2 && !this.b.b())) {
            nc ncVar = this.u;
            if (ncVar != null) {
                ncVar.a(l.longValue(), num.intValue(), num2);
            }
            if (a2) {
                this.b.a(true);
            }
            if (this.b.P()) {
                return;
            }
            this.b.g(true);
            lz lzVar = this.f7869a;
            if (lzVar != null) {
                lzVar.e();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.huawei.openalliance.ad.views.interfaces.b bVar) {
        ho.b("PPSInterstitialView", "onClick, isAppRelated: %s, isHandled: %s, destination:%s", Boolean.valueOf(bVar.a()), Boolean.valueOf(bVar.b()), bVar.c());
        com.huawei.openalliance.ad.inter.listeners.a aVar = this.O;
        if (aVar != null) {
            aVar.b();
        }
        c((Integer) 1);
        if (bVar.b()) {
            a((Integer) 1);
        } else {
            b(bVar.d());
        }
    }

    private void a(lz lzVar, ContentRecord contentRecord) {
        if (this.e != null && contentRecord.R() != null) {
            this.e.a(lzVar);
        } else {
            if (this.n == null || cz.b(contentRecord.y())) {
                return;
            }
            lzVar.f();
        }
    }

    private void a(AppInfo appInfo) {
        if (appInfo == null || TextUtils.isEmpty(appInfo.getIconUrl()) || !c(this.c)) {
            return;
        }
        int i = this.g;
        if (i == 1 || i == 3 || i == 5) {
            this.i.a(new ImageView(this.U), appInfo.getIconUrl(), new az.a() { // from class: com.huawei.openalliance.ad.views.PPSInterstitialView.9
                @Override // com.huawei.openalliance.ad.utils.az.a
                public void a(final Drawable drawable) {
                    if (drawable != null) {
                        final Drawable a2 = ax.a(PPSInterstitialView.this.U, drawable, 5.0f, 8.0f);
                        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSInterstitialView.9.1
                            @Override // java.lang.Runnable
                            public void run() {
                                PPSInterstitialView.this.i.setAppIconImageDrawable(drawable);
                                if (PPSInterstitialView.this.ab != null) {
                                    PPSInterstitialView.this.ab.setAppIconImageDrawable(drawable);
                                }
                                if (!(drawable instanceof com.huawei.openalliance.ad.views.gif.b) && PPSInterstitialView.this.o != null && a2 != null && PPSInterstitialView.this.g != 3) {
                                    PPSInterstitialView.this.o.setBackground(a2);
                                    View d = com.huawei.openalliance.ad.utils.dk.d(PPSInterstitialView.this.U);
                                    if (d != null) {
                                        PPSInterstitialView.this.o.addView(d, 0);
                                    }
                                }
                                ho.a("PPSInterstitialView", "get icon suucess");
                            }
                        });
                    }
                }

                @Override // com.huawei.openalliance.ad.utils.az.a
                public void a() {
                    ho.b("PPSInterstitialView", "get icon failed");
                }
            });
        }
    }

    private void a(ContentRecord contentRecord) {
        int i;
        this.e.setVideoBackgroundColor(getResources().getColor(R.color._2131297924_res_0x7f090684));
        this.e.setBackgroundColor(getResources().getColor(R.color._2131297924_res_0x7f090684));
        this.e.setVideoScaleMode(1);
        if (!c(contentRecord) || (i = this.g) == 1 || i == 2 || !(i == 3 || i == 4 || i == 5)) {
            this.e.setAutoScaleResizeLayoutOnVideoSizeChange(false);
        } else {
            this.e.setUnUseDefault(false);
            this.e.setAutoScaleResizeLayoutOnVideoSizeChange(true);
        }
    }

    private void a(Context context, ContentRecord contentRecord) {
        int b2 = b(contentRecord);
        this.g = b2;
        ho.b("PPSInterstitialView", "insreTemplate %s", Integer.valueOf(b2));
        if (this.T != 1 || ao.n(context)) {
            if (c(contentRecord)) {
                int i = this.g;
                if (i == 1) {
                    a(context, R.layout.hiad_interstitial_layout, R.id.interstitial_content_view, R.color._2131297959_res_0x7f0906a7);
                } else if (i != 2) {
                    if (i != 3) {
                        if (i == 4) {
                            a(context, R.layout.hiad_interstitial_layout4, R.id.interstitial_content_view, R.color._2131297959_res_0x7f0906a7);
                            this.ab = (PPSAppDetailTemplateView) findViewById(R.id.interstitial_app_detail_template);
                        } else if (i == 5) {
                            a(context, R.layout.hiad_interstitial_layout5, R.id.interstitial_content_view, R.color._2131297959_res_0x7f0906a7);
                            PPSAppDetailTemplateView pPSAppDetailTemplateView = (PPSAppDetailTemplateView) findViewById(R.id.interstitial_app_detail_template);
                            this.ab = pPSAppDetailTemplateView;
                            pPSAppDetailTemplateView.setLoadAppIconSelf(false);
                        }
                        this.ac = (TextView) findViewById(R.id.interstitial_app_detail_appdesc_template);
                        this.ad = (PPSAppDownLoadWithAnimationView) findViewById(R.id.interstitial_app_download_btn_template);
                    } else {
                        a(context, R.layout.hiad_interstitial_layout3, R.id.interstitial_content_view, R.color._2131297959_res_0x7f0906a7);
                        PPSAppDetailTemplateView pPSAppDetailTemplateView2 = (PPSAppDetailTemplateView) findViewById(R.id.interstitial_app_detail_template);
                        this.ab = pPSAppDetailTemplateView2;
                        pPSAppDetailTemplateView2.setLoadAppIconSelf(false);
                        this.ac = (TextView) findViewById(R.id.interstitial_app_detail_appdesc_template);
                    }
                }
            }
            a(context, R.layout.hiad_interstitial_layout, R.id.interstitial_content_view, R.color._2131297924_res_0x7f090684);
        } else {
            a(context, R.layout.hiad_interstitial_half_layout, R.id.interstitial_content_view, R.color._2131297924_res_0x7f090684);
        }
        this.o = (ViewGroup) findViewById(R.id.interstitial_content_view);
        PPSInterstitialVideoView pPSInterstitialVideoView = (PPSInterstitialVideoView) findViewById(R.id.interstitial_video_view);
        this.e = pPSInterstitialVideoView;
        pPSInterstitialVideoView.setOnClickListener(this);
        this.h = findViewById(R.id.video_control_view);
        this.v = (ChoicesView) findViewById(R.id.interstitial_info);
        boolean d = bz.a(context).d();
        this.w = d;
        if (d) {
            this.v.setVisibility(8);
        }
        PPSLabelSourceView pPSLabelSourceView = (PPSLabelSourceView) findViewById(R.id.custom_ad_bg_layout);
        this.q = pPSLabelSourceView;
        pPSLabelSourceView.setBackgroundColor(getResources().getColor(R.color._2131298002_res_0x7f0906d2));
        boolean a2 = ao.a(this.w, contentRecord.bc(), contentRecord.bb());
        this.q.b(context, a2);
        this.q.a(this, contentRecord, a2);
        this.r = this.q.getAdLabel();
        this.l = this.q.getAdSource();
        this.ah = this.q.getAdJumpText();
        if (!a2 || c()) {
            if (a2) {
                this.ah.setBackground(getResources().getDrawable(R.drawable._2131428502_res_0x7f0b0496));
                this.ah.setPadding(ao.a(getContext(), getResources().getDimension(R.dimen._2131363291_res_0x7f0a05db)), 0, ao.a(getContext(), getResources().getDimension(R.dimen._2131363291_res_0x7f0a05db)), 0);
            }
            this.ah.setTextColor(getResources().getColor(R.color._2131297906_res_0x7f090672));
            this.ah.setTextSize(1, 10.0f);
        }
        a(com.huawei.openalliance.ad.utils.c.a(this.c, getContext(), false));
        this.f = (ImageView) findViewById((context.getResources().getConfiguration().orientation == 1 || !this.w) ? R.id.interstitial_oversea_mute_icon : R.id.interstitial_mute_icon);
        this.f.setImageResource(dd.a(this.g, this.s));
        dd.a(this.f);
        this.f.setVisibility(0);
        this.r.setVisibility(0);
        ImageView imageView = (ImageView) findViewById(R.id.interstitial_close);
        this.m = imageView;
        imageView.setOnClickListener(this);
        this.j = (PPSAppDetailView) findViewById(R.id.interstitial_download_area);
        this.k = (PPSExpandButtonDetailView) findViewById(R.id.interstitial_expand_button_download_area);
        GifPlayView gifPlayView = (GifPlayView) findViewById(R.id.interstitial_image_view);
        this.n = gifPlayView;
        gifPlayView.setOnClickListener(this);
        this.y = (ProgressBar) findViewById(R.id.interstitial_progress);
        this.p = (TextView) findViewById(R.id.insterstitial_count_down);
        AdSource a3 = AdSource.a(this.b.t());
        this.r.setDataAndRefreshUi(contentRecord);
        if (!this.r.a()) {
            this.R = 0;
        }
        this.r.a(a3, this.b.u());
        d();
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes;
        if (attributeSet != null && (obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100125_res_0x7f0601dd})) != null) {
            try {
                int integer = obtainStyledAttributes.getInteger(0, 0);
                this.T = integer;
                ho.a("PPSInterstitialView", "mFullScreen %d", Integer.valueOf(integer));
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        this.U = context;
        this.V = true;
        this.aa = dh.a(context, "normal");
        this.u = new nc(context, this);
        this.N = new je(this, this);
    }

    private void a(Context context, int i, int i2, int i3) {
        inflate(context, i, this);
        this.ai = i;
        ViewGroup viewGroup = (ViewGroup) findViewById(i2);
        this.o = viewGroup;
        viewGroup.setBackgroundColor(getResources().getColor(i3));
    }

    private void a(Context context) {
        if (ao.n(context)) {
            this.ac.setTextSize(1, 21.0f);
            int i = this.g;
            if (i == 3) {
                this.ac.setVisibility(8);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.ab.getLayoutParams();
                layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin - ((int) com.huawei.openalliance.ad.utils.dk.a(context, 14)));
                this.ab.setLayoutParams(layoutParams);
                return;
            }
            if (i == 4) {
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.ab.getLayoutParams();
                layoutParams2.setMargins(layoutParams2.leftMargin, layoutParams2.topMargin - ((int) com.huawei.openalliance.ad.utils.dk.a(context, 8)), layoutParams2.rightMargin, layoutParams2.bottomMargin);
                this.ab.setLayoutParams(layoutParams2);
                RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.ac.getLayoutParams();
                layoutParams3.setMargins(layoutParams3.leftMargin, layoutParams3.topMargin, layoutParams3.rightMargin, layoutParams3.bottomMargin);
                this.ac.setLayoutParams(layoutParams3);
                return;
            }
            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.ab.getLayoutParams();
            layoutParams4.setMargins(layoutParams4.leftMargin, layoutParams4.topMargin, layoutParams4.rightMargin, layoutParams4.bottomMargin + ((int) com.huawei.openalliance.ad.utils.dk.a(context, 2)));
            this.ab.setLayoutParams(layoutParams4);
            RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) this.ac.getLayoutParams();
            layoutParams5.setMargins(layoutParams5.leftMargin, layoutParams5.topMargin, layoutParams5.rightMargin, layoutParams5.bottomMargin);
            this.ac.setLayoutParams(layoutParams5);
            PPSAppDownLoadWithAnimationView pPSAppDownLoadWithAnimationView = this.ad;
            if (pPSAppDownLoadWithAnimationView != null) {
                RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) pPSAppDownLoadWithAnimationView.getLayoutParams();
                layoutParams6.setMargins(layoutParams6.leftMargin, layoutParams6.topMargin + ((int) com.huawei.openalliance.ad.utils.dk.a(context, 12)), layoutParams6.rightMargin, layoutParams6.bottomMargin);
                this.ad.setLayoutParams(layoutParams6);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, boolean z) {
        if (this.p == null) {
            return;
        }
        if (z && (i = e(i)) == 0) {
            this.p.setVisibility(8);
        }
        this.p.setText(b(i, z));
    }

    private void H() {
        com.huawei.openalliance.ad.inter.data.d dVar = this.b;
        if (dVar == null || os.H(dVar.getCtrlSwitchs())) {
            return;
        }
        this.s = this.b.S();
    }

    private void G() {
        PPSLabelView pPSLabelView = this.r;
        if (pPSLabelView == null) {
            return;
        }
        pPSLabelView.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F() {
        if (this.b != null) {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSInterstitialView.3
                @Override // java.lang.Runnable
                public void run() {
                    com.huawei.openalliance.ad.inter.data.d dVar = PPSInterstitialView.this.b;
                    if (PPSInterstitialView.this.L) {
                        if ((PPSInterstitialView.this.u() || PPSInterstitialView.this.v()) && PPSInterstitialView.this.N != null) {
                            PPSInterstitialView.this.a(Long.valueOf(dVar.getMinEffectiveShowTime()), Integer.valueOf(PPSInterstitialView.this.N.c()), (Integer) null);
                        }
                    }
                }
            }, this.P, this.b.getMinEffectiveShowTime());
        }
    }

    private void E() {
        if (!t()) {
            this.e.setVisibility(8);
            this.f.setVisibility(8);
            this.h.setVisibility(8);
            return;
        }
        this.e.a((com.huawei.openalliance.ad.media.listener.a) this);
        this.e.a((MuteListener) this);
        this.e.a((SegmentMediaStateListener) this);
        this.e.a((NetworkChangeListener) this);
        a(this.c);
        this.e.a(this.b, this.c);
        this.f.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSInterstitialView.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PPSInterstitialView.this.s) {
                    PPSInterstitialView.this.e.h();
                } else {
                    PPSInterstitialView.this.e.g();
                }
                if (PPSInterstitialView.this.u != null) {
                    PPSInterstitialView.this.u.a(!PPSInterstitialView.this.s);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        VideoInfo I = this.b.I();
        if (I != null) {
            if (!I.e()) {
                this.f.setVisibility(4);
            }
            if (this.J <= 0) {
                this.J = I.getVideoDuration();
            }
        }
        if (this.J <= 0) {
            this.J = (int) this.b.U();
        }
        this.h.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSInterstitialView.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PPSInterstitialView.this.k();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private boolean D() {
        return os.f(this.b.getCtrlSwitchs()) == 2 || ao.n(this.U);
    }

    private void C() {
        TextView textView;
        if ((D() || this.W) && (textView = this.ac) != null && this.g == 3) {
            textView.setVisibility(8);
        }
    }

    private void B() {
        if (TextUtils.isEmpty(this.b.E()) && TextUtils.isEmpty(this.b.getCta())) {
            this.i.setVisibility(8);
        } else {
            AppInfo appInfo = new AppInfo();
            appInfo.m(this.b.E());
            this.Q.a(appInfo);
            this.i.setAppRelated(false);
        }
        if (this.b.C() == 0) {
            this.i.setVisibility(8);
        }
    }

    private boolean A() {
        int i = this.g;
        return i == 4 || i == 5;
    }

    class a implements com.huawei.openalliance.ad.views.gif.d {
        private Drawable b;

        @Override // com.huawei.openalliance.ad.views.gif.d
        public void c() {
        }

        @Override // com.huawei.openalliance.ad.views.gif.d
        public void b() {
            PPSInterstitialView.this.p.setVisibility(8);
            PPSInterstitialView.this.m.setVisibility(0);
        }

        @Override // com.huawei.openalliance.ad.views.gif.d
        public void a() {
            if (PPSInterstitialView.this.M || this.b.getIntrinsicHeight() == PPSInterstitialView.this.D || this.b.getIntrinsicWidth() == PPSInterstitialView.this.D) {
                return;
            }
            PPSInterstitialView.this.M = true;
            PPSInterstitialView.this.y.setVisibility(8);
            PPSInterstitialView.this.n.requestLayout();
            PPSInterstitialView.this.L = true;
            PPSInterstitialView.this.K = System.currentTimeMillis();
            PPSInterstitialView.this.F();
        }

        public a(Drawable drawable) {
            this.b = drawable;
        }
    }

    public PPSInterstitialView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f7869a = new lo();
        this.s = true;
        this.w = true;
        this.z = true;
        this.A = -1;
        this.B = false;
        this.C = -1L;
        this.D = -1;
        this.E = 0L;
        this.F = false;
        this.G = false;
        this.H = false;
        this.L = false;
        this.M = false;
        this.P = "interstitial_imp_monitor_" + hashCode();
        this.T = 0;
        this.W = false;
        this.af = true;
        this.ag = false;
        a(context, attributeSet);
    }

    public PPSInterstitialView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7869a = new lo();
        this.s = true;
        this.w = true;
        this.z = true;
        this.A = -1;
        this.B = false;
        this.C = -1L;
        this.D = -1;
        this.E = 0L;
        this.F = false;
        this.G = false;
        this.H = false;
        this.L = false;
        this.M = false;
        this.P = "interstitial_imp_monitor_" + hashCode();
        this.T = 0;
        this.W = false;
        this.af = true;
        this.ag = false;
        a(context, attributeSet);
    }

    public PPSInterstitialView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7869a = new lo();
        this.s = true;
        this.w = true;
        this.z = true;
        this.A = -1;
        this.B = false;
        this.C = -1L;
        this.D = -1;
        this.E = 0L;
        this.F = false;
        this.G = false;
        this.H = false;
        this.L = false;
        this.M = false;
        this.P = "interstitial_imp_monitor_" + hashCode();
        this.T = 0;
        this.W = false;
        this.af = true;
        this.ag = false;
        a(context, attributeSet);
    }

    public PPSInterstitialView(Context context) {
        super(context);
        this.f7869a = new lo();
        this.s = true;
        this.w = true;
        this.z = true;
        this.A = -1;
        this.B = false;
        this.C = -1L;
        this.D = -1;
        this.E = 0L;
        this.F = false;
        this.G = false;
        this.H = false;
        this.L = false;
        this.M = false;
        this.P = "interstitial_imp_monitor_" + hashCode();
        this.T = 0;
        this.W = false;
        this.af = true;
        this.ag = false;
        a(context, (AttributeSet) null);
    }
}
