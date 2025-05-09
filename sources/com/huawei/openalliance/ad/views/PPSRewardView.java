package com.huawei.openalliance.ad.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.huawei.health.R;
import com.huawei.hms.ads.ChoicesView;
import com.huawei.openalliance.ad.activity.PPSRewardActivity;
import com.huawei.openalliance.ad.beans.metadata.AdSource;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.AdConfigMapKey;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.DialogClickType;
import com.huawei.openalliance.ad.constant.RewardMethods;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.dh;
import com.huawei.openalliance.ad.dk;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.IRewardAd;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.data.RewardEvent;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.inter.listeners.INonwifiActionListener;
import com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener;
import com.huawei.openalliance.ad.je;
import com.huawei.openalliance.ad.lm;
import com.huawei.openalliance.ad.media.listener.MuteListener;
import com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener;
import com.huawei.openalliance.ad.nl;
import com.huawei.openalliance.ad.np;
import com.huawei.openalliance.ad.oe;
import com.huawei.openalliance.ad.os;
import com.huawei.openalliance.ad.pf;
import com.huawei.openalliance.ad.pn;
import com.huawei.openalliance.ad.th;
import com.huawei.openalliance.ad.ud;
import com.huawei.openalliance.ad.ue;
import com.huawei.openalliance.ad.uf;
import com.huawei.openalliance.ad.ug;
import com.huawei.openalliance.ad.ui;
import com.huawei.openalliance.ad.ul;
import com.huawei.openalliance.ad.um;
import com.huawei.openalliance.ad.un;
import com.huawei.openalliance.ad.uo;
import com.huawei.openalliance.ad.up;
import com.huawei.openalliance.ad.uq;
import com.huawei.openalliance.ad.ur;
import com.huawei.openalliance.ad.us;
import com.huawei.openalliance.ad.utils.Cdo;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.au;
import com.huawei.openalliance.ad.utils.av;
import com.huawei.openalliance.ad.utils.aw;
import com.huawei.openalliance.ad.utils.bc;
import com.huawei.openalliance.ad.utils.bv;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.dl;
import com.huawei.openalliance.ad.uu;
import com.huawei.openalliance.ad.uv;
import com.huawei.openalliance.ad.uw;
import com.huawei.openalliance.ad.ux;
import com.huawei.openalliance.ad.uy;
import com.huawei.openalliance.ad.uz;
import com.huawei.openalliance.ad.va;
import com.huawei.openalliance.ad.vb;
import com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle;
import java.util.Map;

/* loaded from: classes5.dex */
public class PPSRewardView extends AutoScaleSizeRelativeLayout implements je.a, lm, SegmentMediaStateListener, IViewLifeCycle, com.huawei.openalliance.ad.views.interfaces.k {
    private int A;
    private ViewGroup B;
    private PPSAppDetailTemplateView C;
    private TextView D;
    private MaterialClickInfo E;
    private boolean F;
    private boolean G;
    private MuteListener H;
    private NetworkChangeListener I;
    private com.huawei.openalliance.ad.download.l J;
    private boolean K;
    private boolean L;
    private int M;
    private long N;
    private PPSWebView O;
    private Dialog P;
    private Dialog Q;
    private IRewardAdStatusListener R;
    private INonwifiActionListener S;
    private PPSAppDetailView T;
    private PPSAppDetailView U;
    private PPSExpandButtonDetailView V;
    private s W;

    /* renamed from: a, reason: collision with root package name */
    private final ui f7942a;
    private TextView aa;
    private AdLandingPageData ab;
    private av ac;
    private boolean ad;
    private ChoicesView ae;
    private boolean af;
    private boolean ag;
    private boolean ah;
    private boolean ai;
    private boolean aj;
    private boolean ak;
    private t al;
    private boolean am;
    private f an;
    private boolean ao;
    private boolean ap;
    private RelativeLayout aq;
    private LinearLayout ar;
    private aw as;
    private View.OnClickListener at;
    private VideoInfo au;
    private TextView av;
    private int aw;
    private String ax;
    private oe b;
    private je c;
    private com.huawei.openalliance.ad.inter.data.h d;
    private boolean e;
    private boolean f;
    private RewardVideoView g;
    private int h;
    private int i;
    private int j;
    private TextView k;
    private ImageView l;
    private ImageView m;
    private boolean n;
    private int o;
    private boolean p;
    private Context q;
    private ContentRecord r;
    private ProgressBar s;
    private PPSLabelSourceView t;
    private PPSLabelView u;
    private boolean v;
    private boolean w;
    private dk x;
    private AlertDialog y;
    private t z;

    private int a(int i, int i2) {
        int i3 = i2 - i;
        if (i3 < 0) {
            return 0;
        }
        return i3;
    }

    private boolean a(int i) {
        return (i == 4 || i == 3) ? false : true;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void b(boolean z) {
    }

    @Override // com.huawei.openalliance.ad.lm
    public View getOpenMeasureView() {
        return this;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void setTemplateErrorListener(com.huawei.openalliance.ad.views.interfaces.ab abVar) {
    }

    public void setWebPopUpView(t tVar) {
        this.al = tVar;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void setOrientation(int i) {
        if (i == 0 || 1 == i) {
            this.o = i;
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void setNonwifiDialog(Dialog dialog) {
        this.Q = dialog;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void setContentRecord(ContentRecord contentRecord) {
        this.r = contentRecord;
    }

    public void setClickInfo(MaterialClickInfo materialClickInfo) {
        this.E = materialClickInfo;
    }

    public void setAdDialog(AlertDialog alertDialog) {
        this.y = alertDialog;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void resumeView() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSRewardView.10
            @Override // java.lang.Runnable
            public void run() {
                String str;
                ho.b("PPSRewardView", RewardMethods.RESUME_VIEW);
                if (uu.a(PPSRewardView.this.P) || uu.a(PPSRewardView.this.getNonwifiDialog()) || uu.a(PPSRewardView.this.getAdDialog())) {
                    str = "do not resume when dialog is showing";
                } else {
                    if (PPSRewardView.this.O == null || PPSRewardView.this.O.getVisibility() != 0) {
                        if (PPSRewardView.this.g == null || PPSRewardView.this.r()) {
                            return;
                        }
                        PPSRewardView.this.g.resumeView();
                        if (PPSRewardView.this.p) {
                            PPSRewardView.this.g.a(true, PPSRewardView.this.K);
                            return;
                        }
                        return;
                    }
                    str = "do not resume landing page is showing";
                }
                ho.b("PPSRewardView", str);
            }
        });
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void removeRewardAdStatusListener() {
        this.R = null;
    }

    public void removeNonwifiActionListener() {
        this.S = null;
    }

    public boolean r() {
        return this.n;
    }

    public void q() {
        this.am = false;
        setWebPopUpView(null);
        c("130");
    }

    public void play() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSRewardView.7
            @Override // java.lang.Runnable
            public void run() {
                ho.b("PPSRewardView", "manual play()");
                if (PPSRewardView.this.d != null) {
                    PPSRewardView pPSRewardView = PPSRewardView.this;
                    pPSRewardView.a(pPSRewardView.d.I());
                }
            }
        });
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void pauseView() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSRewardView.11
            @Override // java.lang.Runnable
            public void run() {
                ho.b("PPSRewardView", RewardMethods.PAUSE_VIEW);
                if (PPSRewardView.this.g != null) {
                    PPSRewardView.this.g.pauseView();
                    PPSRewardView.this.g.a();
                }
            }
        });
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        je jeVar = this.c;
        if (jeVar != null) {
            jeVar.j();
        }
    }

    @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
    public void onSegmentProgress(String str, String str2, int i, int i2) {
        int i3;
        if (r()) {
            return;
        }
        boolean z = this.L;
        if (!z && this.M < 0) {
            this.M = i2;
            this.L = true;
        } else if (z && (i3 = this.M) >= 0) {
            long j = i2 - i3;
            this.N = j;
            je jeVar = this.c;
            if (jeVar != null) {
                c(j, jeVar.c());
            }
            oe oeVar = this.b;
            if (oeVar != null) {
                oeVar.a(i2, this.h);
            }
        }
        oe oeVar2 = this.b;
        if (oeVar2 != null && oeVar2.a(str2, i2)) {
            ho.c("PPSRewardView", "play localFile timeout.");
            onSegmentMediaError(str, str2, i2, -5, -1);
            return;
        }
        int i4 = this.h;
        if (i2 > i4 && i4 > 0) {
            i2 = i4;
        }
        int i5 = i2 / 1000;
        ho.a("PPSRewardView", "onSegmentProgress: %d, originTime: %d", Integer.valueOf(i5), Integer.valueOf(i2));
        g(i5);
        h(i5);
        c(i5);
        if (i2 >= this.h) {
            ho.b("PPSRewardView", "time countdown finish, manually stop");
            RewardVideoView rewardVideoView = this.g;
            if (rewardVideoView != null) {
                rewardVideoView.setVideoFinish(true);
                onSegmentMediaCompletion(null, str2, i2);
                this.g.b();
            }
        }
    }

    @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
    public void onSegmentMediaStop(String str, String str2, int i) {
        ho.b("PPSRewardView", "onSegmentMediaStop:" + dl.a(str2));
        if (r()) {
            return;
        }
        f(i);
    }

    @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
    public void onSegmentMediaStart(String str, String str2, int i) {
        ho.b("PPSRewardView", "onSegmentMediaStart:" + dl.a(str2));
        this.L = true;
        this.ag = true;
        this.M = i;
        ProgressBar progressBar = this.s;
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
    }

    @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
    public void onSegmentMediaPause(String str, String str2, int i) {
        ho.b("PPSRewardView", "onSegmentMediaPause:" + dl.a(str2));
        f(i);
    }

    @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
    public void onSegmentMediaError(String str, String str2, int i, int i2, int i3) {
        ho.c("PPSRewardView", "onSegmentMediaError:" + dl.a(str2) + ", playTime:" + i + ",errorCode:" + i2 + ",extra:" + i3);
        if (a(str2, i, i2)) {
            ho.b("PPSRewardView", "switch to online play.");
            this.b.b(str2);
            return;
        }
        TextView textView = this.k;
        if (textView != null) {
            textView.setVisibility(8);
        }
        c();
        f(i);
        IRewardAdStatusListener iRewardAdStatusListener = this.R;
        if (iRewardAdStatusListener != null) {
            iRewardAdStatusListener.onAdError(i2, i3);
        }
        if (bv.e(getContext())) {
            return;
        }
        F();
    }

    @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
    public void onSegmentMediaCompletion(String str, String str2, int i) {
        ho.b("PPSRewardView", "onSegmentMediaCompletion:" + dl.a(str2));
        if (r()) {
            return;
        }
        this.n = true;
        f(i);
        N();
        oe oeVar = this.b;
        if (oeVar != null) {
            oeVar.a(i, this.h);
        }
    }

    public void onEvent(final RewardEvent rewardEvent, final boolean z) {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSRewardView.3
            @Override // java.lang.Runnable
            public void run() {
                ho.b("PPSRewardView", "onEvent: %s", rewardEvent.a());
                if (RewardEvent.CLOSE != rewardEvent && (RewardEvent.BACKPRESSED != rewardEvent || PPSRewardView.this.Y())) {
                    return;
                }
                PPSRewardView.this.d(z);
            }
        });
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void onEvent(RewardEvent rewardEvent) {
        onEvent(rewardEvent, false);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ho.b("PPSRewardView", "onDetechedFromWindow");
        je jeVar = this.c;
        if (jeVar != null) {
            jeVar.i();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ho.a("PPSRewardView", "onAttachedToWindow");
        je jeVar = this.c;
        if (jeVar != null) {
            jeVar.h();
        }
    }

    public void o() {
        getWebPopUpView().b();
        setWebPopUpView(null);
        this.am = false;
    }

    public void n() {
        ho.b("PPSRewardView", "handleMaskingClick, isDownloadAd: %s", Boolean.valueOf(this.b.b(this.d)));
        a((Integer) 14);
        this.b.a(22, getClickInfo());
        f fVar = this.an;
        if (fVar != null) {
            fVar.a();
            removeView(this.an);
        }
        if (getEndCardView() != null) {
            getEndCardView().b();
        }
        setBottomViewVisibility(0);
        d(this.ax);
        this.v = false;
        this.ah = false;
        setClickInfo(null);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void m() {
        this.P = null;
        resumeView();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void l() {
        this.P = null;
        if (this.ag) {
            a((Integer) 3);
        }
        this.f7942a.a();
        K();
    }

    public void k() {
        if (getAdDialog() != null || getPopUpView() == null || this.d.Z()) {
            return;
        }
        setAdDialog(getPopUpView().getDialog());
        boolean a2 = getPopUpView().a();
        ho.b("PPSRewardView", "show ad dialog, ret: %s", Boolean.valueOf(a2));
        if (a2) {
            a("127");
            pauseView();
            getAdDialog().setOnCancelListener(new ud(this));
        }
    }

    public boolean j() {
        return this.b.b(this.d) && getAppDetailView() != null && this.T.getAppDownloadButton().getStatus() == AppStatus.DOWNLOAD;
    }

    public void i() {
        if (this.K) {
            I();
        } else {
            H();
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public boolean h() {
        return getAppDetailView() instanceof PPSExpandButtonDetailView;
    }

    public int getmInsreTemplate() {
        return this.A;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public WebSettings getWebViewSettings() {
        PPSWebView pPSWebView = this.O;
        if (pPSWebView != null) {
            return pPSWebView.getSettings();
        }
        return null;
    }

    public t getWebPopUpView() {
        return this.al;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public t getPopUpView() {
        return this.z;
    }

    public int getOrientation() {
        return this.o;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public Dialog getNonwifiDialog() {
        return this.Q;
    }

    public s getEndCardView() {
        return this.W;
    }

    public MaterialClickInfo getClickInfo() {
        return this.E;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public av getAppointJs() {
        return this.ac;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public AppDownloadButton getAppDownloadButton() {
        if (getAppDetailView() == null) {
            return null;
        }
        return getAppDetailView().getAppDownloadButton();
    }

    public PPSAppDetailView getAppDetailView() {
        return this.T;
    }

    public AlertDialog getAdDialog() {
        return this.y;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void g() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSRewardView.12
            @Override // java.lang.Runnable
            public void run() {
                ho.b("PPSRewardView", RewardMethods.STOP_VIEW);
                if (PPSRewardView.this.O != null && bc.e(PPSRewardView.this.d.B()) && PPSRewardView.this.ap) {
                    PPSRewardView.this.O.onStop();
                }
            }
        });
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void f() {
        setNonwifiDialog(null);
        this.p = true;
        K();
    }

    public void e(boolean z) {
        this.K = z;
        M();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void e() {
        setNonwifiDialog(null);
        this.p = true;
        this.ad = false;
        RewardVideoView rewardVideoView = this.g;
        if (rewardVideoView != null) {
            rewardVideoView.resumeView();
            this.g.a(true, this.K);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            int a2 = th.a(motionEvent);
            if (a2 == 0) {
                setClickInfo(th.a(this, motionEvent));
                if (getEndCardView() != null && getEndCardView().getVisibility() == 0) {
                    getEndCardView().setButtonAndSixElementsClickInfo(getClickInfo());
                }
            }
            if (1 == a2) {
                th.a(this, motionEvent, null, getClickInfo());
                if (getEndCardView() != null && getEndCardView().getVisibility() == 0) {
                    getEndCardView().setButtonAndSixElementsClickInfo(getClickInfo());
                }
            }
        } catch (Throwable th) {
            ho.c("PPSRewardView", "dispatchTouchEvent exception : %s", th.getClass().getSimpleName());
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void destroyView() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSRewardView.2
            @Override // java.lang.Runnable
            public void run() {
                ho.b("PPSRewardView", RewardMethods.DESTROY_VIEW);
                if (PPSRewardView.this.d != null && PPSRewardView.this.d.getAppInfo() != null) {
                    com.huawei.openalliance.ad.download.app.e.h().b(PPSRewardView.this.d.getAppInfo(), PPSRewardView.this.J);
                }
                if (PPSRewardView.this.g != null) {
                    PPSRewardView.this.g.b();
                    PPSRewardView.this.g.destroyView();
                }
                if (PPSRewardView.this.O != null) {
                    PPSRewardView.this.O.destroy();
                }
                if (PPSRewardView.this.P != null) {
                    if (PPSRewardView.this.P.isShowing()) {
                        PPSRewardView.this.P.dismiss();
                    }
                    PPSRewardView.this.P = null;
                }
                if (PPSRewardView.this.getAdDialog() != null) {
                    if (PPSRewardView.this.getAdDialog().isShowing() && PPSRewardView.this.getPopUpView() != null) {
                        PPSRewardView.this.getPopUpView().b();
                    }
                    PPSRewardView.this.setAdDialog(null);
                }
                PPSRewardView.this.f7942a.b();
                if (PPSRewardView.this.z != null) {
                    PPSRewardView.this.z.c();
                    PPSRewardView.this.z = null;
                }
            }
        });
    }

    public void d(boolean z) {
        ((PPSRewardActivity) getContext()).setRequestedOrientation(getOrientation());
        setAppDetailViewType(0);
        boolean X = fh.b(this.q).X();
        ho.b("PPSRewardView", "handleCloseClick, closeBtnClicked: %s, showDialogForCloseBtn: %s", Boolean.valueOf(z), Boolean.valueOf(X));
        if (!z || X) {
            g(z);
            return;
        }
        if (this.ag) {
            a((Integer) 3);
        }
        K();
    }

    public void d() {
        if (this.v || getAppDetailView() == null) {
            return;
        }
        getAppDetailView().setVisibility(0);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public boolean c(boolean z) {
        AppDownloadButton appDownloadButton;
        aw awVar;
        boolean z2 = false;
        if ((z && !os.v(this.ab.f())) || this.G || (appDownloadButton = getAppDetailView().getAppDownloadButton()) == null || appDownloadButton.d()) {
            return false;
        }
        if (!this.am && appDownloadButton.getStatus() == AppStatus.DOWNLOAD) {
            this.am = true;
            if (!z && (awVar = this.as) != null) {
                if (awVar.a()) {
                    return false;
                }
                this.as.a(true);
                this.G = true;
            }
            W();
            getWebPopUpView().setAdPopupData(this.ab);
            z2 = getWebPopUpView().a();
            if (z2) {
                c("127");
            }
        }
        return z2;
    }

    public void c(String str) {
        this.b.a(str, this.r);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void c() {
        ImageView imageView = this.m;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
    }

    public void b(String str) {
        this.b.a(str, this.d, this.R);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(com.huawei.openalliance.ad.views.interfaces.b r4) {
        /*
            r3 = this;
            boolean r0 = r4.a()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            boolean r1 = r4.b()
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            java.lang.String r2 = r4.c()
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1, r2}
            java.lang.String r1 = "PPSRewardView"
            java.lang.String r2 = "onClick, isAppRelated: %s, isHandled: %s, destination:%s"
            com.huawei.openalliance.ad.ho.b(r1, r2, r0)
            com.huawei.openalliance.ad.inter.data.h r0 = r3.d
            r1 = 1
            r0.e(r1)
            com.huawei.openalliance.ad.utils.ad.b()
            java.lang.String r0 = "web"
            java.lang.String r2 = r4.c()
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L40
            com.huawei.openalliance.ad.inter.data.h r0 = r3.d
            int r0 = r0.B()
            if (r0 == 0) goto L40
            java.lang.String r0 = "2"
            goto L5a
        L40:
            java.lang.String r0 = "app"
            java.lang.String r2 = r4.c()
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L58
            java.lang.String r0 = "harmonyService"
            java.lang.String r2 = r4.c()
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L5d
        L58:
            java.lang.String r0 = "4"
        L5a:
            r3.b(r0)
        L5d:
            com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener r0 = r3.R
            if (r0 == 0) goto L64
            r0.onAdClicked()
        L64:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r1)
            r3.a(r0)
            com.huawei.openalliance.ad.ui r0 = r3.f7942a
            r0.d()
            boolean r0 = r4.b()
            if (r0 != 0) goto L7d
            java.lang.Integer r4 = r4.d()
            r3.b(r4)
        L7d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.views.PPSRewardView.b(com.huawei.openalliance.ad.views.interfaces.b):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002d  */
    @Override // com.huawei.openalliance.ad.je.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(long r9, int r11) {
        /*
            r8 = this;
            boolean r0 = r8.f
            if (r0 != 0) goto L3d
            r0 = 1
            r8.f = r0
            com.huawei.openalliance.ad.oe r0 = r8.b
            if (r0 == 0) goto L3d
            boolean r0 = r8.r()
            r1 = 0
            if (r0 == 0) goto L19
            com.huawei.openalliance.ad.inter.data.VideoInfo r0 = r8.au
            int r0 = r0.getVideoDuration()
            goto L21
        L19:
            com.huawei.openalliance.ad.views.RewardVideoView r0 = r8.g
            if (r0 == 0) goto L23
            int r0 = r0.getPlayedTime()
        L21:
            r6 = r0
            goto L24
        L23:
            r6 = r1
        L24:
            boolean r0 = r8.r()
            if (r0 == 0) goto L2d
            r1 = 100
            goto L35
        L2d:
            com.huawei.openalliance.ad.views.RewardVideoView r0 = r8.g
            if (r0 == 0) goto L35
            int r1 = r0.getPlayedProgress()
        L35:
            r7 = r1
            com.huawei.openalliance.ad.oe r2 = r8.b
            r3 = r9
            r5 = r11
            r2.a(r3, r5, r6, r7)
        L3d:
            r8.Y()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.views.PPSRewardView.b(long, int):void");
    }

    @Override // com.huawei.openalliance.ad.je.a
    public void b() {
        this.e = false;
        this.f = false;
        long c = ao.c();
        ho.a("PPSRewardView", "onViewPhysicalShowStart: %s", Long.valueOf(c));
        String valueOf = String.valueOf(c);
        com.huawei.openalliance.ad.inter.data.h hVar = this.d;
        if (hVar != null) {
            hVar.g(false);
            this.d.a(false);
            this.d.h(valueOf);
            this.d.c(c);
        }
        oe oeVar = this.b;
        if (oeVar != null) {
            oeVar.a(valueOf);
            this.b.a(c);
        }
        RewardVideoView rewardVideoView = this.g;
        if (rewardVideoView != null) {
            rewardVideoView.a(valueOf);
            this.g.a(c);
        }
        AdLandingPageData adLandingPageData = this.ab;
        if (adLandingPageData != null && this.O != null) {
            adLandingPageData.c(valueOf);
            this.ab.a(c);
            this.O.setAdLandingPageData(this.ab);
        }
        if (this.ab != null && getAppDetailView() != null) {
            this.ab.c(valueOf);
            this.ab.a(c);
            getAppDetailView().a(valueOf);
            getAppDetailView().a(c);
        }
        if (getEndCardView() != null) {
            getEndCardView().a(valueOf);
            getEndCardView().a(c);
        }
        oe oeVar2 = this.b;
        if (oeVar2 != null) {
            oeVar2.b();
        }
        if (this.O != null && this.b.b(this.d) && this.ap) {
            this.O.onResume();
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void addRewardAdStatusListener(IRewardAdStatusListener iRewardAdStatusListener) {
        if (iRewardAdStatusListener == null) {
            return;
        }
        this.R = iRewardAdStatusListener;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void addNonwifiActionListener(INonwifiActionListener iNonwifiActionListener) {
        if (iNonwifiActionListener == null) {
            return;
        }
        this.S = iNonwifiActionListener;
        if (getAppDetailView() != null) {
            getAppDetailView().setOnNonWifiDownloadListener(iNonwifiActionListener);
        }
        if (getEndCardView() != null) {
            getEndCardView().setOnNonWifiDownloadListener(iNonwifiActionListener);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void a(boolean z, String str) {
        if (getAdDialog() != null) {
            getAdDialog().dismiss();
            if (z) {
                resumeView();
            }
            setAdDialog(null);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void a(boolean z) {
        com.huawei.openalliance.ad.inter.data.h hVar;
        if (z || !this.ad || (hVar = this.d) == null || !hVar.ad()) {
            return;
        }
        pauseView();
        D();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void a(String str) {
        if (this.d == null || this.q == null || getPopUpView() == null || TextUtils.isEmpty(str)) {
            ho.c("PPSRewardView", "invalid parameter");
            return;
        }
        setContentRecord(pn.a(this.d));
        getPopUpView();
        t.a(this.q, str, this.r);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void a(Integer num) {
        if (this.c != null) {
            a(Long.valueOf(System.currentTimeMillis() - this.c.d()), Integer.valueOf(this.c.c()), num);
        }
    }

    public void a(com.huawei.openalliance.ad.views.interfaces.b bVar) {
        if (bVar == null || bVar.e() == null) {
            ho.c("PPSRewardView", "invalid click");
            return;
        }
        int intValue = bVar.e().intValue();
        ho.b("PPSRewardView", "click action: %d", Integer.valueOf(intValue));
        if (intValue == 0) {
            b(bVar);
        } else if (intValue == 1 && j()) {
            this.G = true;
            k();
        }
    }

    public void a(MaterialClickInfo materialClickInfo) {
        com.huawei.openalliance.ad.inter.data.h hVar = this.d;
        if (hVar == null || TextUtils.isEmpty(hVar.F())) {
            ho.b("PPSRewardView", "on download dialog clicked, landing page url is empty.");
            return;
        }
        oe oeVar = this.b;
        if (oeVar != null && oeVar.a(21, materialClickInfo)) {
            ho.b("PPSRewardView", "click dialog to landing page.");
            b("2");
        }
        if (!this.e) {
            a(Long.valueOf(this.d.getMinEffectiveShowTime()), Integer.valueOf(this.d.getMinEffectiveShowRatio()), (Integer) 1);
        }
        a(false, DialogClickType.NO_BUTTON_AREA);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.k
    public void a(final IRewardAd iRewardAd, final boolean z) {
        if (this.d != null) {
            ho.c("PPSRewardView", "has been registered");
        } else {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSRewardView.1
                @Override // java.lang.Runnable
                public void run() {
                    IRewardAd iRewardAd2;
                    try {
                        iRewardAd2 = iRewardAd;
                    } catch (Throwable th) {
                        ho.d("PPSRewardView", "refresh ui error " + th.getClass().getSimpleName());
                    }
                    if (iRewardAd2 instanceof com.huawei.openalliance.ad.inter.data.h) {
                        PPSRewardView.this.d = (com.huawei.openalliance.ad.inter.data.h) iRewardAd2;
                        PPSRewardView pPSRewardView = PPSRewardView.this;
                        pPSRewardView.setContentRecord(pn.a(pPSRewardView.d));
                        PPSRewardView pPSRewardView2 = PPSRewardView.this;
                        pPSRewardView2.a(pPSRewardView2.getContext(), PPSRewardView.this.r);
                        if (PPSRewardView.this.d.Z()) {
                            PPSRewardView.this.c();
                        }
                        PPSRewardView pPSRewardView3 = PPSRewardView.this;
                        pPSRewardView3.au = pPSRewardView3.d.I();
                        if (PPSRewardView.this.au == null) {
                            ho.c("PPSRewardView", "there is no video");
                            return;
                        }
                        ho.b("PPSRewardView", "register:" + iRewardAd.getContentId());
                        PPSRewardView.this.w();
                        PPSRewardView.this.t();
                        PPSRewardView.this.f(z);
                        PPSRewardView.this.V();
                        PPSRewardView.this.v();
                        if (!PPSRewardView.this.af) {
                            String adChoiceUrl = iRewardAd.getAdChoiceUrl();
                            String adChoiceIcon = iRewardAd.getAdChoiceIcon();
                            if (!TextUtils.isEmpty(adChoiceUrl)) {
                                if (TextUtils.isEmpty(adChoiceIcon)) {
                                    PPSRewardView.this.ae.b();
                                } else {
                                    PPSRewardView.this.ae.setAdChoiceIcon(adChoiceIcon);
                                }
                            }
                            PPSRewardView.this.ae.setOnClickListener(new uf(PPSRewardView.this, iRewardAd));
                        }
                        if (PPSRewardView.this.R != null) {
                            PPSRewardView.this.R.onAdShown();
                        }
                        PPSRewardView.this.d.f(true);
                        ui uiVar = PPSRewardView.this.f7942a;
                        PPSRewardView pPSRewardView4 = PPSRewardView.this;
                        uiVar.a(pPSRewardView4, iRewardAd, pPSRewardView4.g);
                        return;
                    }
                    PPSRewardView.this.c();
                    ho.c("PPSRewardView", "there is no reward ad");
                }
            });
        }
    }

    public void a(Drawable drawable, Drawable drawable2) {
        ViewGroup viewGroup;
        getAppDetailView().setAppIconImageDrawable(drawable);
        PPSAppDetailTemplateView pPSAppDetailTemplateView = this.C;
        if (pPSAppDetailTemplateView != null) {
            pPSAppDetailTemplateView.setAppIconImageDrawable(drawable);
        }
        if (!(drawable instanceof com.huawei.openalliance.ad.views.gif.b) && (viewGroup = this.B) != null && drawable2 != null && this.A != 3) {
            viewGroup.setBackground(drawable2);
            View d = com.huawei.openalliance.ad.utils.dk.d(this.q);
            if (d != null) {
                this.B.addView(d, 0);
            }
        }
        ho.a("PPSRewardView", "get icon suucess");
    }

    @Override // com.huawei.openalliance.ad.je.a
    public void a(long j, int i) {
        c(this.N, i);
    }

    @Override // com.huawei.openalliance.ad.je.a
    public void a() {
        this.M = -1;
        this.L = false;
    }

    private void z() {
        PPSWebView pPSWebView = this.O;
        if (pPSWebView != null) {
            pPSWebView.setVisibility(8);
            this.O.setAdLandingPageData(this.ab);
            A();
            if (this.b.b(this.d) && this.ao) {
                this.O.loadPage();
                this.ap = true;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0036, code lost:
    
        if (com.huawei.openalliance.ad.utils.bg.a(r1) == false) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void y() {
        /*
            r4 = this;
            com.huawei.openalliance.ad.inter.data.AppInfo r0 = new com.huawei.openalliance.ad.inter.data.AppInfo
            r0.<init>()
            com.huawei.openalliance.ad.inter.data.h r1 = r4.d
            java.lang.String r1 = r1.C()
            r0.m(r1)
            com.huawei.openalliance.ad.inter.data.h r1 = r4.d
            java.util.List r1 = r1.E()
            boolean r2 = com.huawei.openalliance.ad.utils.bg.a(r1)
            r3 = 0
            if (r2 != 0) goto L2c
            java.lang.Object r2 = r1.get(r3)
            com.huawei.openalliance.ad.inter.data.ImageInfo r2 = (com.huawei.openalliance.ad.inter.data.ImageInfo) r2
            java.lang.String r2 = r2.getUrl()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L2c
            goto L38
        L2c:
            com.huawei.openalliance.ad.inter.data.h r1 = r4.d
            java.util.List r1 = r1.D()
            boolean r2 = com.huawei.openalliance.ad.utils.bg.a(r1)
            if (r2 != 0) goto L45
        L38:
            java.lang.Object r1 = r1.get(r3)
            com.huawei.openalliance.ad.inter.data.ImageInfo r1 = (com.huawei.openalliance.ad.inter.data.ImageInfo) r1
            java.lang.String r1 = r1.getUrl()
            r0.d(r1)
        L45:
            com.huawei.openalliance.ad.inter.data.AdLandingPageData r1 = r4.ab
            r1.a(r0)
            r0 = 1
            r4.ak = r0
            com.huawei.openalliance.ad.views.PPSAppDetailView r0 = r4.getAppDetailView()
            r0.setAppRelated(r3)
            com.huawei.openalliance.ad.inter.data.h r0 = r4.d
            int r0 = r0.B()
            if (r0 != 0) goto L63
            com.huawei.openalliance.ad.views.PPSAppDetailView r0 = r4.getAppDetailView()
            r0.b()
        L63:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.views.PPSRewardView.y():void");
    }

    private boolean x() {
        int i = this.A;
        return i == 1 || i == 3 || i == 4 || i == 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        int i;
        ho.b("PPSRewardView", "initContentView");
        this.u.setDataAndRefreshUi(this.r);
        if (this.b.b(this.r) && ((i = this.A) == 4 || i == 5)) {
            PPSExpandButtonDetailView pPSExpandButtonDetailView = this.V;
            this.T = pPSExpandButtonDetailView;
            pPSExpandButtonDetailView.setExtraViewVisibility(8);
        } else {
            this.T = C() ? this.V : this.U;
            getAppDetailView().setBackgroundColor(getResources().getColor(R.color._2131297903_res_0x7f09066f));
        }
        getAppDetailView().setVisibility(0);
        this.K = this.d.W();
        this.ao = fh.b(this.q).Y();
        if (np.a(this.r)) {
            this.ao = false;
            ((RelativeLayout.LayoutParams) this.aq.getLayoutParams()).addRule(2, R.id.reward_download_container);
        }
        this.ab = new AdLandingPageData(pn.a(this.d), getContext(), true);
        M();
        this.ab.e(this.d.ad());
        z();
        if (this.b.a(this.d)) {
            this.ab.c(true);
            com.huawei.openalliance.ad.download.app.e.h().a(this.d.getAppInfo(), this.J);
        } else {
            y();
        }
        getAppDetailView().setAppDetailClickListener(new um(this));
        getAppDetailView().setNeedPerBeforDownload(true);
        if (x()) {
            getAppDetailView().setLoadAppIconSelf(false);
        }
        getAppDetailView().setAdLandingData(this.ab);
        getAppDetailView().setInteractedListener(new ul(this));
        getAppDetailView().setInterType(this.d.B());
        a(this.r.ae());
        this.u.a(AdSource.a(this.d.t()), this.d.u());
        String a2 = com.huawei.openalliance.ad.utils.c.a(this.r, getContext(), true);
        this.ax = a2;
        d(a2);
        Cdo.a(this.t);
        this.F = this.b.c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        ho.b("PPSRewardView", "init pop-up");
        this.aj = os.s(this.d.getCtrlSwitchs());
        boolean U = fh.b(this.q).U();
        if (!this.aj) {
            ho.b("PPSRewardView", "switch is off, skip init popup.");
            return;
        }
        if (bc.f(this.d.B()) || this.d.getAppInfo() == null) {
            ho.b("PPSRewardView", "appInfo is null or web, skip init popup");
            return;
        }
        this.z = new t(getContext(), getOrientation());
        getPopUpView().setAdPopupData(this.ab);
        getPopUpView().setPopUpClickListener(new uy(this, U));
        u();
    }

    private void u() {
        View findViewById = findViewById(R.id.reward_content_area);
        findViewById.setClickable(true);
        findViewById.setOnTouchListener(new ug(this));
        PPSAppDetailTemplateView pPSAppDetailTemplateView = this.C;
        if (pPSAppDetailTemplateView != null) {
            pPSAppDetailTemplateView.setEnabled(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        PPSAppDetailTemplateView pPSAppDetailTemplateView;
        ho.b("PPSRewardView", "initTemplateView");
        if (this.b.b(this.r)) {
            int i = this.A;
            if ((i != 3 && i != 4 && i != 5) || (pPSAppDetailTemplateView = this.C) == null || this.D == null) {
                return;
            }
            pPSAppDetailTemplateView.setVisibility(0);
            this.C.setAdLandingData(this.ab);
            com.huawei.openalliance.ad.inter.data.h hVar = this.d;
            if (hVar == null || hVar.getAppInfo() == null || TextUtils.isEmpty(this.d.getAppInfo().getAppDesc())) {
                this.D.setVisibility(4);
            } else {
                this.D.setVisibility(0);
                this.D.setText(this.d.getAppInfo().getAppDesc());
            }
            b(this.q);
            B();
        }
    }

    private void setBottomViewVisibility(int i) {
        if (this.ak || this.d.getAppInfo() != null) {
            getAppDetailView().setVisibility(i);
        }
        this.u.setVisibility(i);
        if (this.aa != null && !cz.b(this.d.getLabel())) {
            this.aa.setVisibility(i);
        }
        TextView textView = this.av;
        if (textView != null) {
            textView.setVisibility(i);
        }
        Cdo.a(this.t);
    }

    private void setAppDetailViewType(int i) {
        if (getAppDetailView() != null) {
            getAppDetailView().setDetailViewType(i);
        }
    }

    private void s() {
        this.k.setMaxWidth((int) (com.huawei.openalliance.ad.utils.d.a(getContext(), dd.k(getContext())) * 0.5d));
    }

    private void h(int i) {
        if (this.d.Z()) {
            return;
        }
        int i2 = this.i;
        int i3 = i2 - i;
        if ((i3 >= 0 && i3 != 0) || i2 <= 0) {
            return;
        }
        O();
    }

    private void g(boolean z) {
        if (bv.e(getContext()) || E()) {
            if (!this.d.Z()) {
                pauseView();
                J();
                return;
            } else if (z) {
                if (this.ag) {
                    a((Integer) 3);
                }
                K();
                return;
            } else if (!this.w && (this.ah || T())) {
                pauseView();
                U();
                return;
            }
        }
        K();
    }

    private void g(int i) {
        if (this.d.Z()) {
            return;
        }
        int i2 = this.j;
        int i3 = i2 - i;
        if ((i3 >= 0 && i3 != 0) || i2 < 0) {
            return;
        }
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSRewardView.4
            @Override // java.lang.Runnable
            public void run() {
                PPSRewardView.this.c();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(boolean z) {
        ho.b("PPSRewardView", "initVideoView");
        dk dkVar = this.x;
        String c = dkVar.c(dkVar.e(this.au.getVideoDownloadUrl()));
        if (com.huawei.openalliance.ad.utils.ae.b(c)) {
            ho.b("PPSRewardView", "change path to local");
            this.au.a(c);
        }
        je jeVar = this.c;
        if (jeVar != null) {
            jeVar.b(this.d.getMinEffectiveShowTime(), this.d.getMinEffectiveShowRatio());
        }
        oe oeVar = this.b;
        if (oeVar != null) {
            oeVar.c(this.d);
        }
        RewardVideoView rewardVideoView = this.g;
        if (rewardVideoView != null) {
            rewardVideoView.setAudioFocusType(this.d.ae());
            this.g.a(this);
            this.g.a(this.H);
            this.g.a(this.A, this.b.b(this.r));
            this.g.setRewardAd(this.d);
            this.g.setVisibility(0);
            this.g.a(this.I);
        }
        int ac = (int) this.d.ac();
        this.h = ac;
        this.i = np.a(this.q, ac);
        this.j = b(this.h);
        c(0);
        if (z) {
            a(this.au);
        }
    }

    private void f(int i) {
        int i2;
        if (this.L && (i2 = this.M) >= 0) {
            this.N = i - i2;
            this.L = false;
        }
        this.M = -1;
    }

    private int e(String str) {
        Integer h;
        if (str == null || str.trim().length() == 0 || (h = cz.h(str)) == null || h.intValue() < 0 || h.intValue() > 100) {
            return 90;
        }
        return h.intValue();
    }

    private int e(int i) {
        return a(i, this.h / 1000);
    }

    private void d(String str) {
        if (!this.af || TextUtils.isEmpty(str)) {
            this.av.setVisibility(8);
            a(this.aa, this.d.getLabel());
        } else {
            this.av.setText(str);
            this.aa.setVisibility(8);
            this.av.setVisibility(0);
        }
    }

    private int d(int i) {
        return a(i, this.i);
    }

    private void c(long j, int i) {
        com.huawei.openalliance.ad.inter.data.h hVar = this.d;
        if (hVar == null || this.e || j <= hVar.getMinEffectiveShowTime()) {
            return;
        }
        this.e = true;
        a(Long.valueOf(j), Integer.valueOf(i), (Integer) null);
    }

    private void c(int i) {
        if (this.k == null) {
            return;
        }
        this.k.setText(this.b.a(e(i), d(i), this.d));
        if (this.k.getVisibility() != 0) {
            this.k.setVisibility(0);
        }
    }

    private void b(Integer num) {
        if (this.b != null) {
            if (num == null) {
                num = 7;
            }
            this.b.a(num.intValue(), getClickInfo());
        }
    }

    private void b(Context context, ContentRecord contentRecord) {
        int i;
        int a2 = this.b.a(contentRecord, getOrientation());
        this.A = a2;
        ho.b("PPSRewardView", "insreTemplate %s", Integer.valueOf(a2));
        int i2 = this.A;
        if (i2 == 1) {
            a(context, R.layout.hiad_reward_layout, R.id.reward_layout, R.color._2131297959_res_0x7f0906a7);
            return;
        }
        if (i2 == 3) {
            i = R.layout.hiad_reward_layout3;
        } else if (i2 == 4) {
            a(context, R.layout.hiad_reward_layout4, R.id.reward_layout, R.color._2131297959_res_0x7f0906a7);
            this.C = (PPSAppDetailTemplateView) findViewById(R.id.reward_app_detail_template);
            this.D = (TextView) findViewById(R.id.reward_app_detail_appdesc_template);
        } else {
            if (i2 != 5) {
                a(context, R.layout.hiad_reward_layout, R.id.reward_layout, R.color._2131297924_res_0x7f090684);
                return;
            }
            i = R.layout.hiad_reward_layout5;
        }
        a(context, i, R.id.reward_layout, R.color._2131297959_res_0x7f0906a7);
        PPSAppDetailTemplateView pPSAppDetailTemplateView = (PPSAppDetailTemplateView) findViewById(R.id.reward_app_detail_template);
        this.C = pPSAppDetailTemplateView;
        pPSAppDetailTemplateView.setLoadAppIconSelf(false);
        this.D = (TextView) findViewById(R.id.reward_app_detail_appdesc_template);
    }

    private void b(Context context) {
        RelativeLayout.LayoutParams layoutParams;
        if (ao.n(context)) {
            this.D.setTextSize(1, 21.0f);
            int i = this.A;
            if (i == 3) {
                this.D.setVisibility(8);
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.C.getLayoutParams();
                layoutParams2.setMargins(layoutParams2.leftMargin, layoutParams2.topMargin, layoutParams2.rightMargin, layoutParams2.bottomMargin - ((int) com.huawei.openalliance.ad.utils.dk.a(context, 14)));
                this.C.setLayoutParams(layoutParams2);
                return;
            }
            if (i == 4) {
                layoutParams = (RelativeLayout.LayoutParams) this.C.getLayoutParams();
                layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin - ((int) com.huawei.openalliance.ad.utils.dk.a(context, 8)), layoutParams.rightMargin, layoutParams.bottomMargin);
            } else {
                layoutParams = (RelativeLayout.LayoutParams) this.C.getLayoutParams();
                layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin + ((int) com.huawei.openalliance.ad.utils.dk.a(context, 2)));
            }
            this.C.setLayoutParams(layoutParams);
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.D.getLayoutParams();
            layoutParams3.setMargins(layoutParams3.leftMargin, layoutParams3.topMargin, layoutParams3.rightMargin, layoutParams3.bottomMargin);
            this.D.setLayoutParams(layoutParams3);
        }
    }

    private int b(int i) {
        String str;
        int i2;
        setContentRecord(pn.a(this.d));
        Map<String, String> aI = this.r.aI();
        if (aI != null) {
            str = aI.get(AdConfigMapKey.MAPKEY_RWDCLOSTBTN);
            i2 = e(str);
        } else {
            str = "";
            i2 = 90;
        }
        ho.b("PPSRewardView", "Reward close button input string is " + str);
        return Math.min((i * i2) / 100000, 27);
    }

    private boolean a(String str, int i, int i2) {
        return (this.g == null || cz.j(str) || !this.g.a(i, this.K, i2)) ? false : true;
    }

    private void a(Long l, Integer num, Integer num2) {
        com.huawei.openalliance.ad.inter.data.h hVar = this.d;
        if (hVar == null) {
            return;
        }
        boolean a2 = com.huawei.openalliance.ad.utils.c.a(hVar.d(), num2);
        if (!this.d.Q() || (a2 && !this.d.b())) {
            oe oeVar = this.b;
            if (oeVar != null) {
                oeVar.a(l.longValue(), num.intValue(), num2);
            }
            if (a2) {
                this.d.a(true);
            }
            if (this.d.Q()) {
                return;
            }
            this.d.g(true);
            this.f7942a.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final VideoInfo videoInfo) {
        if (videoInfo == null) {
            return;
        }
        boolean E = E();
        if (E || bv.c(getContext())) {
            ho.b("PPSRewardView", "video is cached or is wifi network");
            if (E) {
                this.ad = false;
            }
            RewardVideoView rewardVideoView = this.g;
            if (rewardVideoView != null) {
                rewardVideoView.a(true, this.K);
                return;
            }
            return;
        }
        if (!bv.e(getContext())) {
            F();
            return;
        }
        ho.b("PPSRewardView", "video not cached, stop");
        this.p = false;
        RewardVideoView rewardVideoView2 = this.g;
        if (rewardVideoView2 != null) {
            rewardVideoView2.b();
        }
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSRewardView.5
            @Override // java.lang.Runnable
            public void run() {
                if ((PPSRewardView.this.S == null || !PPSRewardView.this.S.onVideoPlay(videoInfo.getVideoFileSize())) && (PPSRewardView.this.d == null || PPSRewardView.this.d.ad())) {
                    PPSRewardView.this.D();
                } else {
                    ho.b("PPSRewardView", "app has handled, do not pop up dialog");
                    dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSRewardView.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            PPSRewardView.this.p = true;
                            PPSRewardView.this.ad = false;
                            if (PPSRewardView.this.g != null) {
                                PPSRewardView.this.g.a(true, PPSRewardView.this.K);
                            }
                        }
                    });
                }
            }
        });
    }

    private void a(AppInfo appInfo) {
        if (appInfo == null || TextUtils.isEmpty(appInfo.getIconUrl()) || !this.b.b(this.r)) {
            return;
        }
        int i = this.A;
        if (i == 1 || i == 3 || i == 5) {
            getAppDetailView().a(new ImageView(getContext()), appInfo.getIconUrl(), new uw(this));
        }
    }

    private static void a(TextView textView, String str) {
        if (textView == null || TextUtils.isEmpty(str)) {
            return;
        }
        textView.setText(str);
        textView.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, ContentRecord contentRecord) {
        b(context, contentRecord);
        this.aq = (RelativeLayout) findViewById(R.id.reward_content_area);
        this.ar = (LinearLayout) findViewById(R.id.reward_close_container);
        this.k = (TextView) findViewById(R.id.reward_count_down);
        this.l = (ImageView) findViewById(R.id.reward_mute_icon);
        this.m = (ImageView) findViewById(R.id.reward_close);
        com.huawei.openalliance.ad.inter.data.h hVar = this.d;
        if (hVar == null || !hVar.Z()) {
            L();
        }
        this.g = (RewardVideoView) findViewById(R.id.reward_video_view);
        this.U = (PPSAppDetailView) findViewById(R.id.reward_download_area);
        this.V = (PPSExpandButtonDetailView) findViewById(R.id.reward_expand_button_download_area);
        this.ae = (ChoicesView) findViewById(R.id.reward_why_this_ad);
        boolean d = bz.a(context).d();
        this.af = d;
        if (d) {
            this.ae.setVisibility(8);
        }
        setBackgroundColor(-16777216);
        setUseRatioInMatchParentMode(false);
        this.l.setImageResource(dd.a(this.A, true));
        this.l.setOnClickListener(this.at);
        dd.a(this.l);
        this.m.setOnClickListener(this.at);
        this.O = (PPSWebView) findViewById(R.id.reward_webview);
        this.s = (ProgressBar) findViewById(R.id.reward_progress);
        this.t = (PPSLabelSourceView) findViewById(R.id.custom_ad_bg_layout);
        boolean a2 = ao.a(this.af, contentRecord.bc(), contentRecord.bb());
        this.t.b(context, a2);
        this.t.a(this, contentRecord, a2);
        this.aa = this.t.getAdSource();
        this.u = this.t.getAdLabel();
        this.av = this.t.getAdJumpText();
        if (!a2 || a(this.A) || bc.c(contentRecord.B())) {
            if (a2) {
                this.av.setBackground(getResources().getDrawable(R.drawable._2131428502_res_0x7f0b0496));
                this.av.setPadding(ao.a(getContext(), getResources().getDimension(R.dimen._2131363291_res_0x7f0a05db)), 0, ao.a(getContext(), getResources().getDimension(R.dimen._2131363291_res_0x7f0a05db)), 0);
            }
            this.av.setTextColor(getResources().getColor(R.color._2131297906_res_0x7f090672));
            this.av.setTextSize(1, 10.0f);
        }
        s();
        if (np.a(contentRecord)) {
            this.O.setWebViewBackgroundColor(getResources().getColor(R.color._2131297924_res_0x7f090684));
            this.O.d();
        }
        this.O.setPPSWebEventCallback(nl.a());
        this.x = dh.a(context, "normal");
        dd.a(context, getOrientation(), findViewById(R.id.reward_layout));
    }

    private void a(Context context, int i, int i2, int i3) {
        inflate(context, i, this);
        this.aw = i;
        ViewGroup viewGroup = (ViewGroup) findViewById(i2);
        this.B = viewGroup;
        viewGroup.setBackgroundColor(getResources().getColor(i3));
    }

    private void a(Context context) {
        this.q = context;
        try {
            this.b = new np(context, this);
            this.c = new je(this, this);
        } catch (Throwable th) {
            ho.c("PPSRewardView", "init error " + th.getClass().getSimpleName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean Y() {
        PPSLabelView pPSLabelView = this.u;
        if (pPSLabelView == null || !pPSLabelView.c()) {
            return false;
        }
        this.u.b();
        return true;
    }

    private void X() {
        AdLandingPageData adLandingPageData = this.ab;
        if (adLandingPageData == null) {
            return;
        }
        if (os.B(adLandingPageData.f()) != 2) {
            ho.a("PPSRewardView", "no need popup strategy %s.", Integer.valueOf(os.B(this.ab.f())));
            return;
        }
        if (this.ab.getAppInfo() == null || this.ab.n() == null) {
            ho.a("PPSRewardView", "app or pageType para error.");
            return;
        }
        if (!"1".equals(this.ab.n()) && !"2".equals(this.ab.n())) {
            ho.a("PPSRewardView", "landing type no need pop.");
            return;
        }
        if (!this.b.b(this.d)) {
            ho.a("PPSRewardView", "not download related no need pop.");
            return;
        }
        long B = this.ab.getAppInfo().B();
        Object[] objArr = new Object[1];
        if (B < 0) {
            objArr[0] = Long.valueOf(B);
            ho.c("PPSRewardView", "delay time error:%s", objArr);
        } else {
            objArr[0] = Long.valueOf(B);
            ho.b("PPSRewardView", "show app download dialog start delayTime %s", objArr);
            dj.a(new ue(this), B);
        }
    }

    private void W() {
        if (getWebPopUpView() == null) {
            setWebPopUpView(new t(getContext(), 0));
            getWebPopUpView().setPopUpClickListener(new uz(this));
            getWebPopUpView().getDialog().setOnCancelListener(new va(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void V() {
        boolean l = os.l(this.d.getCtrlSwitchs());
        this.ah = l;
        if (!l) {
            ho.b("PPSRewardView", "switch is off, skip init endCard.");
            return;
        }
        if (this.d.B() == 0) {
            this.ah = false;
            ho.b("PPSRewardView", "display type, skip init endCard.");
            return;
        }
        if (!bc.f(this.d.B()) && this.d.getAppInfo() == null) {
            this.ah = false;
            ho.b("PPSRewardView", "appInfo is null, skip init endCard.");
            return;
        }
        boolean W = fh.b(this.q).W();
        this.ai = W;
        ho.b("PPSRewardView", "init endCard, showMasking: %s", Boolean.valueOf(W));
        this.W = new s(getContext(), getOrientation(), this.r);
        if (bc.f(this.d.B())) {
            getEndCardView().setAppRelated(false);
        }
        getEndCardView().setInterType(this.d.B());
        getEndCardView().setAdLandingPageData(this.ab);
        getEndCardView().setEndCardClickListener(new up(this));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        this.aq.addView(getEndCardView(), layoutParams);
        getEndCardView().b();
    }

    private void U() {
        if (!this.w && P()) {
            this.w = true;
        }
        TextView textView = this.k;
        if (textView != null) {
            textView.setVisibility(8);
        }
        ImageView imageView = this.l;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        RewardVideoView rewardVideoView = this.g;
        if (rewardVideoView != null) {
            rewardVideoView.f();
        }
        if (this.R == null || !r()) {
            return;
        }
        this.R.onAdCompleted();
    }

    private boolean T() {
        return this.b.b(this.d) && !TextUtils.isEmpty(this.d.F());
    }

    private void S() {
        PPSAppDetailTemplateView pPSAppDetailTemplateView = this.C;
        if (pPSAppDetailTemplateView != null) {
            pPSAppDetailTemplateView.setVisibility(4);
        }
        TextView textView = this.D;
        if (textView != null) {
            textView.setVisibility(4);
        }
        if (this.V == null || !this.b.b(this.r)) {
            return;
        }
        int i = this.A;
        if (i == 4 || i == 5) {
            this.V.setExtraViewVisibility(0);
        }
    }

    private void R() {
        ProgressBar progressBar;
        setAppDetailViewType(1);
        PPSWebView pPSWebView = this.O;
        if (pPSWebView != null) {
            if (!this.ao) {
                pPSWebView.loadPage();
                this.ap = true;
                this.O.onResume();
            }
            if (!this.L && (progressBar = this.s) != null) {
                progressBar.setVisibility(8);
            }
            b("2");
            RewardVideoView rewardVideoView = this.g;
            if (rewardVideoView != null) {
                rewardVideoView.setVisibility(4);
            }
            S();
            this.O.setVisibility(0);
            aw awVar = this.as;
            if (awVar != null) {
                awVar.a(false);
            }
            this.O.setRealOpenTime(System.currentTimeMillis());
        }
        if ("1".equals(this.d.ab()) && os.i(this.d.getCtrlSwitchs())) {
            getAppDetailView().setVisibility(8);
        }
        if (np.a(this.r)) {
            ((PPSRewardActivity) getContext()).setRequestedOrientation(1);
        }
        if (this.F) {
            this.F = false;
            Toast.makeText(getContext(), R.string._2130851170_res_0x7f023562, 0).show();
        }
        X();
    }

    private void Q() {
        if (!this.ai || TextUtils.isEmpty(this.d.F())) {
            return;
        }
        this.an = new f(this.q);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        RelativeLayout relativeLayout = this.aq;
        if (relativeLayout != null) {
            relativeLayout.addView(this.an, layoutParams);
        }
        LinearLayout linearLayout = this.ar;
        if (linearLayout != null) {
            linearLayout.bringToFront();
        }
        this.an.setOnClickListener(new uq(this));
    }

    private boolean P() {
        Y();
        if (!this.ah) {
            if (!T()) {
                return false;
            }
            R();
            return true;
        }
        setBottomViewVisibility(8);
        if (getEndCardView() != null) {
            getEndCardView().a();
        }
        Q();
        this.v = true;
        return true;
    }

    private void O() {
        b("1");
    }

    private void N() {
        b("-1");
        U();
    }

    private void M() {
        if (this.l == null) {
            return;
        }
        this.l.setImageResource(dd.a(this.A, this.K));
    }

    private void L() {
        ImageView imageView = this.m;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
    }

    private void K() {
        G();
        IRewardAdStatusListener iRewardAdStatusListener = this.R;
        if (iRewardAdStatusListener != null) {
            iRewardAdStatusListener.onAdClosed();
        }
    }

    private void J() {
        if (this.P == null) {
            Resources resources = getResources();
            int i = this.i;
            Dialog a2 = com.huawei.openalliance.ad.utils.y.a(getContext(), (String) null, resources.getQuantityString(R.plurals._2130903534_res_0x7f0301ee, i, Integer.valueOf(i)), getResources().getString(R.string._2130851147_res_0x7f02354b), getResources().getString(R.string._2130851146_res_0x7f02354a), new un(this));
            this.P = a2;
            a2.setOnCancelListener(new uo(this));
        }
    }

    private void I() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSRewardView.9
            @Override // java.lang.Runnable
            public void run() {
                ho.b("PPSRewardView", "unmuteSound");
                PPSRewardView.this.K = false;
                if (PPSRewardView.this.g != null) {
                    PPSRewardView.this.g.d();
                    if (PPSRewardView.this.b != null) {
                        PPSRewardView.this.b.a(false);
                    }
                }
            }
        });
    }

    private void H() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSRewardView.8
            @Override // java.lang.Runnable
            public void run() {
                ho.b("PPSRewardView", "muteSound");
                PPSRewardView.this.K = true;
                if (PPSRewardView.this.g != null) {
                    PPSRewardView.this.g.c();
                    if (PPSRewardView.this.b != null) {
                        PPSRewardView.this.b.a(true);
                    }
                }
            }
        });
    }

    private void G() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSRewardView.6
            @Override // java.lang.Runnable
            public void run() {
                ho.b("PPSRewardView", "onClose");
                if (PPSRewardView.this.b != null) {
                    PPSRewardView.this.b.a();
                }
            }
        });
        this.f7942a.b();
    }

    private void F() {
        Toast makeText = Toast.makeText(getContext(), R.string._2130851113_res_0x7f023529, 0);
        makeText.setGravity(17, 0, 0);
        makeText.show();
    }

    private boolean E() {
        VideoInfo videoInfo = this.au;
        if (videoInfo == null) {
            return false;
        }
        String videoDownloadUrl = videoInfo.getVideoDownloadUrl();
        return (cz.j(videoDownloadUrl) && TextUtils.isEmpty(this.x.e(videoDownloadUrl))) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D() {
        dj.a(new uu(this));
    }

    private boolean C() {
        return os.f(this.d.getCtrlSwitchs()) == 2 || ao.n(this.q);
    }

    private void B() {
        TextView textView;
        if (C() && (textView = this.D) != null && this.A == 3) {
            textView.setVisibility(8);
        }
    }

    private void A() {
        aw awVar = new aw(getContext(), this.ab, getAppDetailView().getAppDownloadButton(), this.O, new vb(this));
        this.as = awVar;
        awVar.a(1);
        this.O.addJavascriptInterface(this.as, Constants.PPS_JS_NAME);
        this.O.addJavascriptInterface(new au(getContext(), pf.a(this.ab)), Constants.LANDING_JS_NAME);
        av avVar = new av(getContext(), pf.a(this.ab), this.O);
        this.ac = avVar;
        this.O.addJavascriptInterface(avVar, Constants.APPOINT_JS_NAME);
    }

    public PPSRewardView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f7942a = new ui();
        this.e = false;
        this.f = false;
        this.n = false;
        this.o = 1;
        this.p = true;
        this.v = false;
        this.w = false;
        this.y = null;
        this.F = false;
        this.G = false;
        this.H = new ur(this);
        this.I = new us(this);
        this.J = new ux(this);
        this.K = true;
        this.L = false;
        this.M = -1;
        this.N = -1L;
        this.ad = true;
        this.af = true;
        this.ag = false;
        this.ah = true;
        this.ai = false;
        this.aj = true;
        this.ak = false;
        this.am = false;
        this.ao = true;
        this.ap = false;
        this.at = new uv(this);
        a(context);
    }

    public PPSRewardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7942a = new ui();
        this.e = false;
        this.f = false;
        this.n = false;
        this.o = 1;
        this.p = true;
        this.v = false;
        this.w = false;
        this.y = null;
        this.F = false;
        this.G = false;
        this.H = new ur(this);
        this.I = new us(this);
        this.J = new ux(this);
        this.K = true;
        this.L = false;
        this.M = -1;
        this.N = -1L;
        this.ad = true;
        this.af = true;
        this.ag = false;
        this.ah = true;
        this.ai = false;
        this.aj = true;
        this.ak = false;
        this.am = false;
        this.ao = true;
        this.ap = false;
        this.at = new uv(this);
        a(context);
    }

    public PPSRewardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7942a = new ui();
        this.e = false;
        this.f = false;
        this.n = false;
        this.o = 1;
        this.p = true;
        this.v = false;
        this.w = false;
        this.y = null;
        this.F = false;
        this.G = false;
        this.H = new ur(this);
        this.I = new us(this);
        this.J = new ux(this);
        this.K = true;
        this.L = false;
        this.M = -1;
        this.N = -1L;
        this.ad = true;
        this.af = true;
        this.ag = false;
        this.ah = true;
        this.ai = false;
        this.aj = true;
        this.ak = false;
        this.am = false;
        this.ao = true;
        this.ap = false;
        this.at = new uv(this);
        a(context);
    }

    public PPSRewardView(Context context) {
        super(context);
        this.f7942a = new ui();
        this.e = false;
        this.f = false;
        this.n = false;
        this.o = 1;
        this.p = true;
        this.v = false;
        this.w = false;
        this.y = null;
        this.F = false;
        this.G = false;
        this.H = new ur(this);
        this.I = new us(this);
        this.J = new ux(this);
        this.K = true;
        this.L = false;
        this.M = -1;
        this.N = -1L;
        this.ad = true;
        this.af = true;
        this.ag = false;
        this.ah = true;
        this.ai = false;
        this.aj = true;
        this.ak = false;
        this.am = false;
        this.ao = true;
        this.ap = false;
        this.at = new uv(this);
        a(context);
    }
}
