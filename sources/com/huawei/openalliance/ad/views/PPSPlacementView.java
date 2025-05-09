package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.activity.ComplianceActivity;
import com.huawei.openalliance.ad.bx;
import com.huawei.openalliance.ad.constant.NotifyMessageNames;
import com.huawei.openalliance.ad.constant.PlacementPlayState;
import com.huawei.openalliance.ad.constant.RewardMethods;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.dh;
import com.huawei.openalliance.ad.dk;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.IAd;
import com.huawei.openalliance.ad.inter.data.IPlacementAd;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.data.PlacementEvent;
import com.huawei.openalliance.ad.inter.data.PlacementMediaFile;
import com.huawei.openalliance.ad.je;
import com.huawei.openalliance.ad.ji;
import com.huawei.openalliance.ad.jn;
import com.huawei.openalliance.ad.jo;
import com.huawei.openalliance.ad.jp;
import com.huawei.openalliance.ad.lo;
import com.huawei.openalliance.ad.lz;
import com.huawei.openalliance.ad.media.MediaState;
import com.huawei.openalliance.ad.media.listener.MediaBufferListener;
import com.huawei.openalliance.ad.media.listener.MediaErrorListener;
import com.huawei.openalliance.ad.media.listener.MediaPlayerReleaseListener;
import com.huawei.openalliance.ad.media.listener.MuteListener;
import com.huawei.openalliance.ad.media.listener.PPSVideoRenderListener;
import com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener;
import com.huawei.openalliance.ad.mh;
import com.huawei.openalliance.ad.mi;
import com.huawei.openalliance.ad.mo;
import com.huawei.openalliance.ad.mp;
import com.huawei.openalliance.ad.mq;
import com.huawei.openalliance.ad.nm;
import com.huawei.openalliance.ad.oq;
import com.huawei.openalliance.ad.ou;
import com.huawei.openalliance.ad.pi;
import com.huawei.openalliance.ad.qq;
import com.huawei.openalliance.ad.sg;
import com.huawei.openalliance.ad.th;
import com.huawei.openalliance.ad.tm;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.dl;
import com.huawei.openalliance.ad.vd;
import com.huawei.openalliance.ad.views.interfaces.IPPSPlacementView;
import com.huawei.openalliance.ad.views.interfaces.IPlacementMediaChangeListener;
import com.huawei.openalliance.ad.views.interfaces.IPlacementMediaStateListener;
import com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes5.dex */
public class PPSPlacementView extends AutoScaleSizeRelativeLayout implements je.a, MuteListener, SegmentMediaStateListener, IPPSPlacementView, IViewLifeCycle {
    private int[] A;
    private PlacementMediaView B;
    private List<View> C;
    private boolean D;
    private com.huawei.openalliance.ad.analysis.c E;
    private boolean F;
    private int G;
    private int H;
    private long I;
    private boolean J;
    private boolean K;
    private int L;
    private ImageView M;
    private boolean N;
    private boolean O;
    private boolean P;
    private boolean Q;
    private MaterialClickInfo R;
    private dk S;
    private boolean T;
    private long U;
    private MediaPlayerReleaseListener V;
    private int W;

    /* renamed from: a, reason: collision with root package name */
    protected lz f7914a;
    private boolean aa;
    private AudioManager ab;
    private Object ac;
    private PlacementPlayState ad;
    private tm ae;
    private List<jp> af;
    private AtomicBoolean ag;
    private List<ou> ah;
    private int ai;
    private PPSVideoRenderListener aj;
    private Handler ak;
    private SegmentMediaStateListener al;
    private View.OnTouchListener am;
    private View.OnClickListener an;
    private AudioManager.OnAudioFocusChangeListener ao;
    protected lz b;
    protected lz c;
    int d;
    int e;
    private MaterialClickInfo f;
    private String g;
    private boolean h;
    private nm i;
    private je j;
    private List<com.huawei.openalliance.ad.inter.data.g> k;
    private com.huawei.openalliance.ad.inter.data.g l;
    private com.huawei.openalliance.ad.inter.data.g m;
    private int n;
    private OnPlacementAdClickListener o;
    private List<View> p;
    private boolean q;
    private boolean r;
    private PlacementMediaView s;
    private PlacementMediaView t;
    private PlacementMediaView u;
    private MediaBufferListener v;
    private MuteListener w;
    private MediaErrorListener x;
    private IPlacementMediaStateListener y;
    private IPlacementMediaChangeListener z;

    /* loaded from: classes9.dex */
    public interface OnPlacementAdClickListener {
        void onClick();
    }

    public void unregister() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.21
            @Override // java.lang.Runnable
            public void run() {
                ho.b("PPSPlacementView", "unregister");
                PPSPlacementView pPSPlacementView = PPSPlacementView.this;
                pPSPlacementView.a(pPSPlacementView.s, true);
                PPSPlacementView pPSPlacementView2 = PPSPlacementView.this;
                pPSPlacementView2.a(pPSPlacementView2.t, true);
                PPSPlacementView.this.l = null;
                PPSPlacementView.this.m = null;
                PPSPlacementView.this.k.clear();
                PPSPlacementView.this.j.b();
                PPSPlacementView.this.i.a((com.huawei.openalliance.ad.inter.data.g) null);
                PPSPlacementView.this.h();
                PPSPlacementView.this.f7914a.b();
                PPSPlacementView.this.b.b();
                PPSPlacementView.this.c.b();
            }
        });
    }

    public void unmuteSound() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.9
            @Override // java.lang.Runnable
            public void run() {
                ho.b("PPSPlacementView", "unmuteSound");
                boolean z = true;
                if (PPSPlacementView.this.L == 1) {
                    PPSPlacementView.this.r();
                }
                boolean z2 = false;
                PPSPlacementView.this.D = false;
                if (PPSPlacementView.this.s != null) {
                    PPSPlacementView.this.s.d();
                    z2 = true;
                }
                if (PPSPlacementView.this.t != null) {
                    PPSPlacementView.this.t.d();
                } else {
                    z = z2;
                }
                if (PPSPlacementView.this.u != null) {
                    PPSPlacementView.this.u.d();
                } else if (!z) {
                    return;
                }
                PPSPlacementView.this.i.a(PPSPlacementView.this.D);
            }
        });
    }

    public void stop() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.6
            @Override // java.lang.Runnable
            public void run() {
                ho.b("PPSPlacementView", "stop");
                if (PPSPlacementView.this.B != null) {
                    PPSPlacementView.this.B.g();
                }
            }
        });
    }

    public void showTransparencyDialog(View view, int[] iArr) {
        if (view == null) {
            ho.c("PPSPlacementView", "anchorView is null");
        }
        try {
            com.huawei.openalliance.ad.inter.data.g currentAd = getCurrentAd();
            if (currentAd == null) {
                ho.c("PPSPlacementView", "adInfo is null");
                return;
            }
            ContentRecord a2 = pi.a(currentAd);
            if (com.huawei.openalliance.ad.utils.x.t(getContext())) {
                a(view, a2, iArr);
            } else if (a(iArr)) {
                bx.a(getContext(), view, iArr, a2);
            } else {
                bx.a(getContext(), view, a2);
            }
        } catch (Throwable th) {
            ho.c("PPSPlacementView", "showTransparencyDialog has exception %s", th.getClass().getSimpleName());
        }
    }

    public void showTransparencyDialog(View view) {
        showTransparencyDialog(view, null);
    }

    public void showAdvertiserInfoDialog(View view, boolean z) {
        if (view == null) {
            ho.c("PPSPlacementView", "anchorView is null");
        }
        try {
            com.huawei.openalliance.ad.inter.data.g currentAd = getCurrentAd();
            if (currentAd == null) {
                ho.c("PPSPlacementView", "adInfo is null");
                return;
            }
            ContentRecord a2 = pi.a(currentAd);
            if (bg.a(a2.aS())) {
                ho.c("PPSPlacementView", "advertiser Info is null");
            } else {
                ComplianceActivity.a(getContext(), view, a2, z);
            }
        } catch (Throwable th) {
            ho.c("PPSPlacementView", "showAdvertiserInfoDialog has exception %s", th.getClass().getSimpleName());
        }
    }

    public void setSoundVolume(final float f) {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.8
            @Override // java.lang.Runnable
            public void run() {
                lz lzVar;
                ho.b("PPSPlacementView", "set sound volume: %s", Float.valueOf(f));
                if (PPSPlacementView.this.B != null) {
                    PPSPlacementView.this.B.setSoundVolume(f);
                    if (PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
                        lzVar = PPSPlacementView.this.c;
                    } else {
                        lzVar = PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? PPSPlacementView.this.f7914a : PPSPlacementView.this.b;
                    }
                    lzVar.b(f);
                }
            }
        });
    }

    public void setOverlays(List<View> list) {
        this.C = list;
    }

    public void setOnPlacementAdClickListener(OnPlacementAdClickListener onPlacementAdClickListener) {
        this.o = onPlacementAdClickListener;
    }

    public void setMediaPlayerReleaseListener(MediaPlayerReleaseListener mediaPlayerReleaseListener) {
        if (mediaPlayerReleaseListener == null) {
            return;
        }
        this.V = mediaPlayerReleaseListener;
    }

    public void setAudioFocusType(int i) {
        this.L = i;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void resumeView() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.10
            @Override // java.lang.Runnable
            public void run() {
                ho.b("PPSPlacementView", RewardMethods.RESUME_VIEW);
                if (PPSPlacementView.this.B != null) {
                    PPSPlacementView.this.B.resumeView();
                    PPSPlacementView.this.B.a(true, PPSPlacementView.this.D);
                    PPSPlacementView pPSPlacementView = PPSPlacementView.this;
                    pPSPlacementView.a(pPSPlacementView.getCurrentAdDuration() * 2);
                }
            }
        });
    }

    public void removePlacementMediaStateListener() {
        this.y = null;
    }

    public void removeMuteListener(MuteListener muteListener) {
        if (muteListener == null) {
            return;
        }
        PlacementMediaView placementMediaView = this.s;
        if (placementMediaView != null) {
            placementMediaView.b(muteListener);
        } else {
            this.w = null;
        }
    }

    public void removeMediaPlayerReleaseListener(MediaPlayerReleaseListener mediaPlayerReleaseListener) {
        PlacementMediaView placementMediaView = this.u;
        if (placementMediaView != null) {
            placementMediaView.a(mediaPlayerReleaseListener);
        }
        this.V = null;
    }

    public void removeMediaErrorListener(MediaErrorListener mediaErrorListener) {
        if (mediaErrorListener == null) {
            return;
        }
        PlacementMediaView placementMediaView = this.s;
        if (placementMediaView != null) {
            placementMediaView.b(mediaErrorListener);
        } else {
            this.x = null;
        }
    }

    public void removeMediaChangeListener() {
        this.z = null;
    }

    public void removeMediaBufferListener(MediaBufferListener mediaBufferListener) {
        if (mediaBufferListener == null) {
            return;
        }
        PlacementMediaView placementMediaView = this.s;
        if (placementMediaView != null) {
            placementMediaView.b(mediaBufferListener);
        } else {
            this.v = null;
        }
    }

    public void register(final List<IPlacementAd> list, final List<View> list2) {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.19
            @Override // java.lang.Runnable
            public void run() {
                PlacementMediaView placementMediaView;
                StringBuilder sb = new StringBuilder("register:");
                List list3 = list;
                sb.append(list3 == null ? 0 : list3.size());
                ho.b("PPSPlacementView", sb.toString());
                PPSPlacementView.this.a((List<IPlacementAd>) list);
                if (bg.a(list) || bg.a(PPSPlacementView.this.k)) {
                    return;
                }
                PPSPlacementView.this.getMonitor();
                PPSPlacementView.this.n = 0;
                PPSPlacementView pPSPlacementView = PPSPlacementView.this;
                pPSPlacementView.l = pPSPlacementView.getCurrentAd();
                PPSPlacementView pPSPlacementView2 = PPSPlacementView.this;
                pPSPlacementView2.m = pPSPlacementView2.getNextAd();
                PPSPlacementView.this.e();
                PPSPlacementView.this.p = list2;
                PPSPlacementView.this.b((List<View>) list2);
                if (!PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
                    placementMediaView = PPSPlacementView.this.s;
                } else {
                    placementMediaView = PPSPlacementView.this.u;
                }
                PPSPlacementView.this.f();
                PPSPlacementView.this.a(placementMediaView);
                if (PPSPlacementView.this.K) {
                    PPSPlacementView.this.c();
                }
            }
        });
    }

    public void register(final List<IPlacementAd> list) {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.18
            @Override // java.lang.Runnable
            public void run() {
                PlacementMediaView placementMediaView;
                StringBuilder sb = new StringBuilder("register:");
                List list2 = list;
                sb.append(list2 == null ? 0 : list2.size());
                ho.b("PPSPlacementView", sb.toString());
                PPSPlacementView.this.a((List<IPlacementAd>) list);
                if (bg.a(list) || bg.a(PPSPlacementView.this.k)) {
                    return;
                }
                PPSPlacementView.this.getMonitor();
                PPSPlacementView.this.n = 0;
                PPSPlacementView pPSPlacementView = PPSPlacementView.this;
                pPSPlacementView.l = pPSPlacementView.getCurrentAd();
                PPSPlacementView pPSPlacementView2 = PPSPlacementView.this;
                pPSPlacementView2.m = pPSPlacementView2.getNextAd();
                PPSPlacementView.this.e();
                PPSPlacementView.this.i();
                if (!PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
                    placementMediaView = PPSPlacementView.this.s;
                } else {
                    placementMediaView = PPSPlacementView.this.u;
                }
                PPSPlacementView.this.f();
                PPSPlacementView.this.a(placementMediaView);
                if (PPSPlacementView.this.K) {
                    PPSPlacementView.this.c();
                }
            }
        });
    }

    public void play(final boolean z) {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.4
            @Override // java.lang.Runnable
            public void run() {
                ho.b("PPSPlacementView", "play, auto:" + z);
                if (PPSPlacementView.this.B != null) {
                    PPSPlacementView.this.B.a(z, PPSPlacementView.this.D);
                }
            }
        });
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void pauseView() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.11
            @Override // java.lang.Runnable
            public void run() {
                ho.b("PPSPlacementView", RewardMethods.PAUSE_VIEW);
                if (PPSPlacementView.this.B != null) {
                    PPSPlacementView.this.B.pauseView();
                    PPSPlacementView.this.B.f();
                    PPSPlacementView.this.n();
                }
            }
        });
    }

    public void pause() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.5
            @Override // java.lang.Runnable
            public void run() {
                ho.b("PPSPlacementView", VastAttribute.PAUSE);
                if (PPSPlacementView.this.B != null) {
                    PPSPlacementView.this.B.f();
                }
            }
        });
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.j.j();
    }

    @Override // com.huawei.openalliance.ad.media.listener.MuteListener
    public void onUnmute() {
        ho.b("PPSPlacementView", "onUnmute");
        this.D = false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x005b, code lost:
    
        if (r7.I >= r2) goto L28;
     */
    @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onSegmentProgress(java.lang.String r8, java.lang.String r9, int r10, int r11) {
        /*
            r7 = this;
            com.huawei.openalliance.ad.media.MediaState r0 = r7.getCurrentMediaState()
            java.lang.String r1 = "PPSPlacementView"
            if (r0 == 0) goto L16
            com.huawei.openalliance.ad.media.MediaState$State r2 = com.huawei.openalliance.ad.media.MediaState.State.PLAYING
            boolean r0 = r0.isNotState(r2)
            if (r0 == 0) goto L16
            java.lang.String r8 = "progress callback on nonPlaying state."
            com.huawei.openalliance.ad.ho.c(r1, r8)
            return
        L16:
            java.lang.String r0 = r7.getCurrentContentId()
            if (r8 == 0) goto L23
            boolean r0 = r8.equalsIgnoreCase(r0)
            if (r0 != 0) goto L23
            return
        L23:
            r7.G = r10
            com.huawei.openalliance.ad.inter.data.g r10 = r7.getCurrentAd()
            long r2 = r7.getCurrentAdDuration()
            boolean r0 = r7.F
            r4 = 1
            if (r0 != 0) goto L3b
            int r5 = r7.H
            if (r5 >= 0) goto L3b
            r7.H = r11
            r7.F = r4
            goto L5e
        L3b:
            if (r0 == 0) goto L5e
            int r0 = r7.H
            if (r0 < 0) goto L5e
            int r0 = r11 - r0
            long r5 = (long) r0
            r7.I = r5
            com.huawei.openalliance.ad.je r0 = r7.j
            int r0 = r0.c()
            r7.c(r5, r0)
            com.huawei.openalliance.ad.nm r0 = r7.i
            if (r0 == 0) goto L57
            long r5 = (long) r11
            r0.a(r5, r2)
        L57:
            long r5 = r7.I
            int r0 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r0 < 0) goto L5e
            goto L5f
        L5e:
            r4 = 0
        L5f:
            int r11 = r7.a(r11, r10, r2)
            r7.a(r11)
            if (r4 == 0) goto L71
            java.lang.String r0 = "time countdown finish, manual stop."
            com.huawei.openalliance.ad.ho.b(r1, r0)
            r7.a(r8, r9, r11, r10)
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.views.PPSPlacementView.onSegmentProgress(java.lang.String, java.lang.String, int, int):void");
    }

    @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
    public void onSegmentMediaStop(String str, String str2, int i) {
        ho.b("PPSPlacementView", "onSegmentMediaStop, contentId: %s, url: %s", str, dl.a(str2));
        if (str != null && str.equalsIgnoreCase(getCurrentContentId())) {
            b(i);
        }
        if (this.y == null || !str.equalsIgnoreCase(getCurrentContentId())) {
            Object[] objArr = new Object[2];
            objArr[0] = Boolean.valueOf(this.y == null);
            objArr[1] = getCurrentContentId();
            ho.b("PPSPlacementView", "skip mediaStop callback, isListener null ? %s, currentContentId: %s", objArr);
            return;
        }
        PlacementMediaView placementMediaView = this.B;
        if (placementMediaView instanceof z) {
            placementMediaView.b(i);
        }
        int currentPlayTime = getCurrentPlayTime() + i;
        int[] iArr = this.A;
        int i2 = (iArr == null || iArr.length <= 0 || iArr[iArr.length - 1] <= 0) ? -1 : (int) ((currentPlayTime / iArr[iArr.length - 1]) * 100.0f);
        com.huawei.openalliance.ad.inter.data.g currentAd = getCurrentAd();
        if (currentAd != null && currentAd.isVideoAd()) {
            this.d = currentPlayTime;
            this.e = i2;
        }
        ho.b("PPSPlacementView", "meidaStop callback, playedTime: %s", Integer.valueOf(currentPlayTime));
        this.y.onMediaStop(currentPlayTime);
    }

    @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
    public void onSegmentMediaStart(String str, String str2, int i) {
        com.huawei.openalliance.ad.inter.data.g currentAd;
        ho.b("PPSPlacementView", "onSegmentMediaStart, contentId: %s, url: %s", str, dl.a(str2));
        this.F = true;
        this.H = i;
        PlacementMediaView placementMediaView = this.B;
        if (placementMediaView != null) {
            placementMediaView.setAlpha(1.0f);
        }
        if (ho.a() && (currentAd = getCurrentAd()) != null) {
            ho.a("PPSPlacementView", "customExposureType: %s , getMinEffectiveShowTime %s , getMinEffectiveVideoPlayProgress %s", currentAd.q(), Long.valueOf(currentAd.getMinEffectiveShowTime()), currentAd.r());
        }
        if (this.y != null && this.n == 0) {
            ho.b("PPSPlacementView", "need notify media start.");
            this.O = true;
        }
        if (this.z != null && this.B != null) {
            ho.b("PPSPlacementView", "mediaChange callback.");
            this.z.onSegmentMediaChange(this.B.getPlacementAd());
        }
        PlacementMediaView placementMediaView2 = this.B;
        if (placementMediaView2 instanceof z) {
            placementMediaView2.e();
        }
    }

    @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
    public void onSegmentMediaPause(String str, String str2, int i) {
        ho.b("PPSPlacementView", "onSegmentMediaPause:" + dl.a(str2));
        if (str != null && str.equalsIgnoreCase(getCurrentContentId())) {
            b(i);
            PlacementMediaView placementMediaView = this.B;
            if (placementMediaView instanceof z) {
                placementMediaView.c(i);
            }
        }
        if (this.y != null) {
            int currentPlayTime = getCurrentPlayTime() + i;
            int[] iArr = this.A;
            int i2 = (iArr == null || iArr.length <= 0 || iArr[iArr.length + (-1)] <= 0) ? -1 : (int) ((currentPlayTime / iArr[iArr.length - 1]) * 100.0f);
            com.huawei.openalliance.ad.inter.data.g currentAd = getCurrentAd();
            if (currentAd != null && currentAd.isVideoAd()) {
                this.d = currentPlayTime;
                this.e = i2;
            }
            ho.b("PPSPlacementView", "meidaPause callback, playedTime: %s.", Integer.valueOf(currentPlayTime));
            this.y.onMediaPause(currentPlayTime);
        }
    }

    @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
    public void onSegmentMediaError(String str, String str2, int i, int i2, int i3) {
        PlacementMediaView placementMediaView;
        PlacementMediaFile mediaFile;
        ho.b("PPSPlacementView", "onMediaError, contentId: %s, url: %s", str, dl.a(str2));
        String currentContentId = getCurrentContentId();
        if (str != null && !str.equalsIgnoreCase(currentContentId)) {
            ho.b("PPSPlacementView", "onError, contentId not match, currentConentId: %s", currentContentId);
            return;
        }
        q();
        n();
        ho.c("PPSPlacementView", "onSegmentMediaError:" + dl.a(str2) + ", playTime:" + i + ",errorCode:" + i2 + ",extra:" + i3);
        b(i);
        if (this.y != null) {
            int currentPlayTime = getCurrentPlayTime() + i;
            ho.b("PPSPlacementView", "meidaError callback, playedTime: %s", Integer.valueOf(currentPlayTime));
            this.y.onMediaError(currentPlayTime, i2, i3);
            this.y.onErrorWithContentId(i, i2, i3, str);
        }
        if (!this.P) {
            ho.b("PPSPlacementView", "error before start callback.");
            this.O = true;
        }
        this.j.m();
        this.B.a(i);
        com.huawei.openalliance.ad.inter.data.g currentAd = getCurrentAd();
        if (currentAd != null && (mediaFile = currentAd.getMediaFile()) != null) {
            this.E.a(mediaFile.getUrl(), i2, i3, pi.a(currentAd));
        }
        boolean j = j();
        a(j);
        if (this.y == null || !j || this.A.length <= 0) {
            return;
        }
        ho.b("PPSPlacementView", "last ad play error");
        IPlacementMediaStateListener iPlacementMediaStateListener = this.y;
        int[] iArr = this.A;
        iPlacementMediaStateListener.onMediaCompletion(iArr[iArr.length - 1]);
        if (!this.ad.isState(PlacementPlayState.State.SINGLE_INST) || (placementMediaView = this.u) == null) {
            return;
        }
        placementMediaView.b();
    }

    @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
    public void onSegmentMediaCompletion(String str, String str2, int i) {
        PlacementMediaView placementMediaView;
        String currentContentId = getCurrentContentId();
        if (str != null && !str.equalsIgnoreCase(currentContentId)) {
            ho.b("PPSPlacementView", "onCompletion, %s not match current content id: %s", str, currentContentId);
            return;
        }
        boolean j = j();
        if (!j) {
            PlacementMediaView placementMediaView2 = this.B;
            if (placementMediaView2 instanceof z) {
                this.M = placementMediaView2.getLastFrame();
                o();
            }
        }
        PlacementMediaView placementMediaView3 = this.B;
        if (placementMediaView3 instanceof z) {
            placementMediaView3.b(i);
        }
        n();
        ho.b("PPSPlacementView", "onSegmentMediaCompletion, contentId: %s, url: %s", str, dl.a(str2));
        b(i);
        this.j.m();
        this.B.a(i);
        a(j);
        if (this.y == null || !j) {
            return;
        }
        int currentPlayTime = getCurrentPlayTime() + i;
        ho.b("PPSPlacementView", "onCompletion callback, playedTime: %s", Integer.valueOf(currentPlayTime));
        this.y.onMediaCompletion(currentPlayTime);
        if (this.ad.isState(PlacementPlayState.State.SINGLE_INST) && (placementMediaView = this.u) != null) {
            placementMediaView.b();
        }
        s();
    }

    @Override // com.huawei.openalliance.ad.media.listener.MuteListener
    public void onMute() {
        ho.b("PPSPlacementView", "onMute");
        this.D = true;
    }

    public void onEvent(final PlacementEvent placementEvent) {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.14
            @Override // java.lang.Runnable
            public void run() {
                ho.b("PPSPlacementView", "onEvent:" + placementEvent.a());
                if (PlacementEvent.CLOSE == placementEvent) {
                    PPSPlacementView.this.a((Integer) 3);
                    PPSPlacementView.this.onClose();
                }
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ho.b("PPSPlacementView", "onDetechedFromWindow");
        this.j.i();
        this.f7914a.b();
        this.b.b();
        this.c.b();
    }

    public void onClose() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.3
            @Override // java.lang.Runnable
            public void run() {
                lz lzVar;
                ho.b("PPSPlacementView", "onClose");
                PPSPlacementView.this.i.a();
                if (PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
                    lzVar = PPSPlacementView.this.c;
                } else {
                    lzVar = PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? PPSPlacementView.this.f7914a : PPSPlacementView.this.b;
                }
                lzVar.j();
                if (PPSPlacementView.this.af != null) {
                    for (jp jpVar : PPSPlacementView.this.af) {
                        if (jpVar != null) {
                            jpVar.b();
                        }
                    }
                }
                PPSPlacementView.this.f7914a.b();
                PPSPlacementView.this.b.b();
                PPSPlacementView.this.c.b();
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ho.a("PPSPlacementView", "onAttachedToWindow");
        this.j.h();
        oq.a(getContext()).b(getContext());
    }

    public void muteSound() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.7
            @Override // java.lang.Runnable
            public void run() {
                boolean z;
                ho.b("PPSPlacementView", "muteSound");
                boolean z2 = true;
                if (PPSPlacementView.this.L == 1) {
                    PPSPlacementView.this.s();
                }
                PPSPlacementView.this.D = true;
                if (PPSPlacementView.this.s != null) {
                    PPSPlacementView.this.s.c();
                    z = true;
                } else {
                    z = false;
                }
                if (PPSPlacementView.this.t != null) {
                    PPSPlacementView.this.t.c();
                } else {
                    z2 = z;
                }
                if (PPSPlacementView.this.u != null) {
                    PPSPlacementView.this.u.c();
                } else if (!z2) {
                    return;
                }
                PPSPlacementView.this.i.a(PPSPlacementView.this.D);
            }
        });
    }

    public boolean isTransparencyDialogVisible() {
        tm tmVar = this.ae;
        return tmVar != null && tmVar.getVisibility() == 0 && this.ae.getLocalVisibleRect(new Rect());
    }

    public boolean isPlaying() {
        PlacementMediaView placementMediaView = this.B;
        if (placementMediaView != null) {
            return placementMediaView.i();
        }
        return false;
    }

    public void hideTransparencyDialog() {
        ji.a().a(NotifyMessageNames.FEEDBACK_RECEIVE, new Intent("com.huawei.ads.feedback.action.FINISH_FEEDBACK_ACTIVITY"));
    }

    public void hideAdvertiserInfoDialog() {
        ji.a().a(NotifyMessageNames.FEEDBACK_RECEIVE, new Intent("com.huawei.ads.feedback.action.FINISH_FEEDBACK_ACTIVITY"));
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IPPSPlacementView
    public String getShowId() {
        return this.g;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.f
    public IAd getAd() {
        return getCurrentAd();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            int a2 = th.a(motionEvent);
            if (a2 == 0) {
                this.R = th.a(this, motionEvent);
            }
            if (1 == a2) {
                th.a(this, motionEvent, null, this.R);
            }
        } catch (Throwable th) {
            ho.c("PPSPlacementView", "dispatchTouchEvent exception : %s", th.getClass().getSimpleName());
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!com.huawei.openalliance.ad.utils.x.t(getContext())) {
            return super.dispatchKeyEvent(keyEvent);
        }
        try {
            ho.a("PPSPlacementView", "keyCode:" + keyEvent.getKeyCode());
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 4) {
                if (keyCode == 23 || keyCode == 66) {
                    ho.a("PPSPlacementView", "enter--->");
                    if (isTransparencyDialogVisible()) {
                        if (keyEvent.getAction() == 0) {
                            ho.a("PPSPlacementView", "enter---> ACTION_DOWN: pressWhyThisAdClickBtn");
                            this.ae.l();
                        } else if (keyEvent.getAction() == 1) {
                            ho.a("PPSPlacementView", "enter---> ACTION_UP: outerProcessWhyEventUnified");
                            u();
                            tm tmVar = this.ae;
                            if (tmVar != null) {
                                removeView(tmVar);
                            }
                            this.ae = null;
                            return true;
                        }
                    }
                }
            } else {
                if (keyEvent.getAction() == 0) {
                    return super.dispatchKeyEvent(keyEvent);
                }
                ho.a("PPSPlacementView", "back--->");
                tm tmVar2 = this.ae;
                if (tmVar2 != null) {
                    removeView(tmVar2);
                    this.ae = null;
                    return true;
                }
            }
        } catch (Throwable th) {
            ho.c("PPSPlacementView", "dispatchKeyEvent() exception:" + th.toString());
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void destroyView() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.13
            @Override // java.lang.Runnable
            public void run() {
                ho.b("PPSPlacementView", RewardMethods.DESTROY_VIEW);
                if (PPSPlacementView.this.B != null) {
                    PPSPlacementView.this.B.g();
                    PPSPlacementView.this.B.destroyView();
                }
                PPSPlacementView.this.removeMediaChangeListener();
                PPSPlacementView.this.removePlacementMediaStateListener();
                PPSPlacementView.this.n();
                PPSPlacementView.this.f7914a.b();
                PPSPlacementView.this.b.b();
                PPSPlacementView.this.c.b();
            }
        });
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.f
    public boolean d() {
        je jeVar = this.j;
        if (jeVar != null) {
            return jeVar.n();
        }
        return false;
    }

    @Override // com.huawei.openalliance.ad.je.a
    public void b(long j, int i) {
        if (!this.r) {
            this.r = true;
            if (ho.a()) {
                ho.a("PPSPlacementView", "onAdPhyShow  duration: %s  maxShowRatio: %s  playedTime: %s  playedProgress: %s", Long.valueOf(j), Integer.valueOf(i), Integer.valueOf(this.d), Integer.valueOf(this.e));
            }
            com.huawei.openalliance.ad.inter.data.g currentAd = getCurrentAd();
            if (currentAd == null || !currentAd.isVideoAd()) {
                this.i.a(j, i, this.d, this.e);
            } else {
                if (this.d <= 0) {
                    this.d = 0;
                    this.e = 0;
                }
                this.i.a(j, i, this.d, this.e);
            }
        }
        this.K = false;
        this.J = false;
    }

    @Override // com.huawei.openalliance.ad.je.a
    public void b() {
        this.K = true;
        this.q = false;
        this.r = false;
        long c = ao.c();
        this.U = c;
        ho.a("PPSPlacementView", "onViewPhysicalShowStart: %s", Long.valueOf(c));
        com.huawei.openalliance.ad.inter.data.g currentAd = getCurrentAd();
        if (currentAd != null && (!currentAd.isVideoAd() || currentAd.q() == null || currentAd.q().intValue() == 0)) {
            currentAd.g(false);
            currentAd.a(false);
        }
        f();
        if (this.l != null) {
            c();
            (this.ad.isState(PlacementPlayState.State.SINGLE_INST) ? this.c : this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? this.f7914a : this.b).f();
        }
    }

    public void addPlacementMediaStateListener(IPlacementMediaStateListener iPlacementMediaStateListener) {
        if (iPlacementMediaStateListener == null) {
            return;
        }
        this.y = iPlacementMediaStateListener;
    }

    public void addMuteListener(MuteListener muteListener) {
        if (muteListener == null) {
            return;
        }
        PlacementMediaView placementMediaView = this.s;
        if (placementMediaView != null) {
            placementMediaView.a(muteListener);
        } else {
            this.w = muteListener;
        }
    }

    public void addMediaErrorListener(MediaErrorListener mediaErrorListener) {
        if (mediaErrorListener == null) {
            return;
        }
        PlacementMediaView placementMediaView = this.s;
        if (placementMediaView != null) {
            placementMediaView.a(mediaErrorListener);
        } else {
            this.x = mediaErrorListener;
        }
    }

    public void addMediaChangeListener(IPlacementMediaChangeListener iPlacementMediaChangeListener) {
        if (iPlacementMediaChangeListener == null) {
            return;
        }
        this.z = iPlacementMediaChangeListener;
    }

    public void addMediaBufferListener(MediaBufferListener mediaBufferListener) {
        if (mediaBufferListener == null) {
            return;
        }
        PlacementMediaView placementMediaView = this.s;
        if (placementMediaView != null) {
            placementMediaView.a(mediaBufferListener);
        } else {
            this.v = mediaBufferListener;
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IPPSPlacementView
    public void a(Integer num) {
        a(Long.valueOf(System.currentTimeMillis() - this.j.d()), Integer.valueOf(this.j.c()), num);
    }

    @Override // com.huawei.openalliance.ad.je.a
    public void a(long j, int i) {
        com.huawei.openalliance.ad.inter.data.g currentAd = getCurrentAd();
        if (currentAd != null) {
            Integer q = currentAd.q();
            if (!currentAd.isVideoAd() || q == null || q.intValue() == 0) {
                c(this.I, i);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.je.a
    public void a() {
        this.H = -1;
        this.G = -1;
        this.F = false;
    }

    private void u() {
        if (isTransparencyDialogVisible()) {
            this.ae.k();
        }
    }

    private boolean t() {
        ho.b("PPSPlacementView", "isNeedAudioFocus type: %s soundMute: %s", Integer.valueOf(this.L), Boolean.valueOf(this.D));
        int i = this.L;
        if (i == 0) {
            return true;
        }
        if (i == 2) {
            return false;
        }
        return (i == 1 && this.D) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void s() {
        String str;
        try {
            try {
                ho.b("PPSPlacementView", "abandonAudioFocus");
                Object obj = this.ac;
                if (obj instanceof AudioFocusRequest) {
                    this.ab.abandonAudioFocusRequest((AudioFocusRequest) obj);
                }
                this.ac = null;
            } catch (IllegalStateException unused) {
                str = "abandonAudioFocus IllegalStateException";
                ho.c("PPSPlacementView", str);
            } catch (Exception e) {
                str = "abandonAudioFocus " + e.getClass().getSimpleName();
                ho.c("PPSPlacementView", str);
            }
        } finally {
            this.aa = false;
            this.W = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        String str;
        if (!t()) {
            ho.c("PPSPlacementView", "audio focus is not needed");
            return;
        }
        try {
            ho.b("PPSPlacementView", "requestAudioFocus");
            AudioFocusRequest build = new AudioFocusRequest.Builder(2).setOnAudioFocusChangeListener(this.ao).build();
            this.ac = build;
            this.ab.requestAudioFocus(build);
        } catch (IllegalStateException unused) {
            str = "requestAudioFocus IllegalStateException";
            ho.c("PPSPlacementView", str);
        } catch (Exception e) {
            str = "requestAudioFocus " + e.getClass().getSimpleName();
            ho.c("PPSPlacementView", str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.15
            @Override // java.lang.Runnable
            public void run() {
                if (PPSPlacementView.this.M == null) {
                    return;
                }
                try {
                    ho.b("PPSPlacementView", "hide last frame.");
                    PPSPlacementView.this.M.setVisibility(8);
                    PPSPlacementView pPSPlacementView = PPSPlacementView.this;
                    pPSPlacementView.removeView(pPSPlacementView.M);
                    PPSPlacementView.this.M = null;
                    PPSPlacementView.this.N = true;
                } catch (Throwable unused) {
                    ho.c("PPSPlacementView", "hideLastFrame error.");
                }
            }
        });
    }

    private void o() {
        if (this.M == null) {
            return;
        }
        try {
            ho.b("PPSPlacementView", "showLastFrame");
            this.N = false;
            this.M.setVisibility(0);
            this.M.setScaleType(ImageView.ScaleType.FIT_CENTER);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(13);
            addView(this.M, layoutParams);
        } catch (Throwable unused) {
            ho.c("PPSPlacementView", "showLastFrame error.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        this.Q = false;
        ho.b("PPSPlacementView", "timeout, cancel.");
        this.ak.removeMessages(1001);
    }

    private void m() {
        PlacementMediaView placementMediaView;
        ho.b("PPSPlacementView", "showNextAd");
        com.huawei.openalliance.ad.inter.data.g nextAd = getNextAd();
        if (nextAd != null) {
            this.j.b(nextAd.getMinEffectiveShowTime(), nextAd.getMinEffectiveShowRatio());
        }
        this.i.a(nextAd);
        this.i.e();
        if (this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
            this.u = b(this.u, nextAd);
            a(new PlacementPlayState(PlacementPlayState.State.SINGLE_INST), nextAd, this.u);
            a(this.u);
        } else {
            if (Math.abs(this.s.getAlpha() - 1.0f) < 0.01f) {
                this.ad.switchToState(PlacementPlayState.State.BACKUP_VIEW);
                a(this.t);
                placementMediaView = this.s;
            } else {
                this.ad.switchToState(PlacementPlayState.State.MAIN_VIEW);
                a(this.s);
                placementMediaView = this.t;
            }
            a(placementMediaView, false);
        }
        this.j.l();
        ho.b("PPSPlacementView", "show " + this.n + " ad");
    }

    private void l() {
        PlacementPlayState placementPlayState;
        com.huawei.openalliance.ad.inter.data.g gVar;
        PlacementMediaView placementMediaView;
        this.n++;
        ho.b("PPSPlacementView", "load " + this.n + " ad");
        if (getNextAd() == null || this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
            return;
        }
        if (Math.abs(this.s.getAlpha() - 1.0f) < 0.01f) {
            com.huawei.openalliance.ad.inter.data.g nextAd = getNextAd();
            this.m = nextAd;
            this.t = b(this.t, nextAd);
            placementPlayState = new PlacementPlayState(PlacementPlayState.State.BACKUP_VIEW);
            gVar = this.m;
            placementMediaView = this.t;
        } else {
            com.huawei.openalliance.ad.inter.data.g nextAd2 = getNextAd();
            this.l = nextAd2;
            this.s = b(this.s, nextAd2);
            placementPlayState = new PlacementPlayState(PlacementPlayState.State.MAIN_VIEW);
            gVar = this.l;
            placementMediaView = this.s;
        }
        a(placementPlayState, gVar, placementMediaView);
    }

    private void k() {
        tm tmVar = this.ae;
        if (tmVar != null) {
            removeView(tmVar);
            this.ae = null;
        }
    }

    private boolean j() {
        return this.n == this.k.size() - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this);
        this.p = arrayList;
        b(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        List<View> list = this.p;
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator<View> it = this.p.iterator();
        while (it.hasNext()) {
            it.next().setOnClickListener(null);
        }
        setOnClickListener(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.huawei.openalliance.ad.inter.data.g getNextAd() {
        if (this.n < this.k.size() - 1) {
            return this.k.get(this.n + 1);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getMonitor() {
        if (this.ag.get()) {
            return;
        }
        this.ag.set(true);
        this.af = new ArrayList(this.k.size());
        for (com.huawei.openalliance.ad.inter.data.g gVar : this.k) {
            ContentRecord a2 = pi.a(gVar);
            ou ouVar = new ou(getContext(), new sg(getContext()));
            ouVar.a(a2.a());
            ouVar.a(a2);
            this.ah.add(ouVar);
            jp a3 = jn.a(getContext(), gVar.isImageAd(), ouVar, a2, false);
            jn.a(a2.m(), a3);
            this.af.add(a3);
        }
    }

    private int getCurrentPlayTime() {
        int i = this.n;
        if (i < 1) {
            return 0;
        }
        return this.A[i - 1];
    }

    private MediaState getCurrentMediaState() {
        PlacementMediaView placementMediaView = this.B;
        if (placementMediaView == null) {
            return null;
        }
        return placementMediaView.getMediaState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PlacementMediaFile getCurrentMediaFile() {
        if (getCurrentAd() != null) {
            return getCurrentAd().getMediaFile();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCurrentContentId() {
        com.huawei.openalliance.ad.inter.data.g currentAd = getCurrentAd();
        if (currentAd == null) {
            return null;
        }
        return currentAd.getContentId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getCurrentAdDuration() {
        PlacementMediaFile mediaFile;
        com.huawei.openalliance.ad.inter.data.g currentAd = getCurrentAd();
        if (currentAd == null || (mediaFile = currentAd.getMediaFile()) == null) {
            return 0L;
        }
        return mediaFile.getDuration();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.huawei.openalliance.ad.inter.data.g getCurrentAd() {
        if (this.n < this.k.size()) {
            return this.k.get(this.n);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.h) {
            com.huawei.openalliance.ad.utils.ad.b();
            this.h = false;
            ho.b("PPSPlacementView", "onClick");
            a((Integer) 1);
            if (this.R == null) {
                this.R = this.f;
            }
            if (this.af != null) {
                ho.a("PPSPlacementView", "actualIndex %s", Integer.valueOf(this.ai));
                jp jpVar = this.af.get(this.ai - 1);
                if (jpVar != null) {
                    jpVar.a();
                }
                if (jpVar instanceof jo) {
                    this.i.a((qq) null);
                } else {
                    this.i.a(this.ah.get(this.ai - 1));
                }
            }
            this.i.a(this.R);
            this.f = null;
            this.R = null;
            b(this.ad.isState(PlacementPlayState.State.SINGLE_INST) ? this.c : this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? this.f7914a : this.b);
            OnPlacementAdClickListener onPlacementAdClickListener = this.o;
            if (onPlacementAdClickListener != null) {
                onPlacementAdClickListener.onClick();
            }
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.2
                @Override // java.lang.Runnable
                public void run() {
                    PPSPlacementView.this.h = true;
                }
            }, 500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        String valueOf = String.valueOf(this.U);
        this.g = valueOf;
        this.i.a(valueOf);
        this.i.a(this.U);
        PlacementMediaView placementMediaView = this.u;
        if (placementMediaView != null) {
            placementMediaView.a(this.g);
            this.u.a(this.U);
        }
        PlacementMediaView placementMediaView2 = this.s;
        if (placementMediaView2 != null) {
            placementMediaView2.a(this.g);
            this.s.a(this.U);
        }
        PlacementMediaView placementMediaView3 = this.t;
        if (placementMediaView3 != null) {
            placementMediaView3.a(this.g);
            this.t.a(this.U);
        }
        List<com.huawei.openalliance.ad.inter.data.g> list = this.k;
        if (list != null) {
            Iterator<com.huawei.openalliance.ad.inter.data.g> it = list.iterator();
            while (it.hasNext()) {
                it.next().h(this.g);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        PlacementPlayState placementPlayState;
        com.huawei.openalliance.ad.inter.data.g gVar;
        PlacementMediaView placementMediaView;
        ho.a("PPSPlacementView", "initPlacementView, singlePlayerInst: %s", Boolean.valueOf(this.ad.isState(PlacementPlayState.State.SINGLE_INST)));
        this.j.b(this.l.getMinEffectiveShowTime(), this.l.getMinEffectiveShowRatio());
        this.i.a(this.l);
        if (this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
            PlacementMediaView b = b(this.u, this.l);
            this.u = b;
            b.setMediaPlayerReleaseListener(this.V);
            placementPlayState = new PlacementPlayState(PlacementPlayState.State.SINGLE_INST);
            gVar = this.l;
            placementMediaView = this.u;
        } else {
            this.s = b(this.s, this.l);
            a(new PlacementPlayState(PlacementPlayState.State.MAIN_VIEW), this.l, this.s);
            this.t = b(this.t, this.m);
            placementPlayState = new PlacementPlayState(PlacementPlayState.State.BACKUP_VIEW);
            gVar = this.m;
            placementMediaView = this.t;
        }
        a(placementPlayState, gVar, placementMediaView);
    }

    private void d(lz lzVar) {
        List<View> list;
        mi a2 = lzVar.a();
        if (a2 == null || (list = this.C) == null || list.size() <= 0) {
            return;
        }
        Iterator<View> it = this.C.iterator();
        while (it.hasNext()) {
            a2.a(it.next(), mh.OTHER, null);
        }
    }

    private void c(lz lzVar) {
        if (lzVar != null) {
            lzVar.e();
        }
    }

    private void c(long j, int i) {
        com.huawei.openalliance.ad.inter.data.g currentAd = getCurrentAd();
        if (currentAd != null) {
            Integer q = currentAd.q();
            if (currentAd.isVideoAd()) {
                if (q == null || q.intValue() == 0 || 1 == q.intValue()) {
                    if (this.q || j <= currentAd.getMinEffectiveShowTime()) {
                        return;
                    }
                    this.q = true;
                    ho.b("PPSPlacementView", "reportAdShowEvent, customExposureType： %s", q);
                } else {
                    if (2 != q.intValue() || currentAd.r() == null || this.q || this.G < currentAd.r().intValue()) {
                        return;
                    }
                    this.q = true;
                    ho.b("PPSPlacementView", "reportAdShowEvent, customExposureType： %s", q);
                }
            } else if (this.q || j <= currentAd.getMinEffectiveShowTime()) {
                return;
            } else {
                this.q = true;
            }
            a(Long.valueOf(j), Integer.valueOf(i), (Integer) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.J) {
            return;
        }
        this.J = true;
        this.i.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<View> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (View view : list) {
            if (view instanceof z) {
                ((z) view).setOnClickListener(this.an);
            } else {
                view.setOnClickListener(this.an);
                view.setOnTouchListener(this.am);
            }
        }
    }

    private void b(lz lzVar) {
        if (lzVar != null) {
            lzVar.a(mo.CLICK);
        }
    }

    private void b(int i) {
        int i2;
        if (this.F && (i2 = this.H) >= 0) {
            this.I = i - i2;
            this.F = false;
        }
        this.H = -1;
    }

    private PlacementMediaView b(PlacementMediaView placementMediaView, com.huawei.openalliance.ad.inter.data.g gVar) {
        if (gVar == null) {
            return null;
        }
        ho.a("PPSPlacementView", "init media view for content:%s", gVar.getContentId());
        boolean z = false;
        if (a(placementMediaView, gVar)) {
            a(placementMediaView, false);
        } else {
            a(placementMediaView, true);
            placementMediaView = null;
        }
        if (placementMediaView == null) {
            placementMediaView = a(gVar);
            z = true;
        }
        if (placementMediaView != null) {
            ho.b("PPSPlacementView", "meida view created");
            placementMediaView.b((SegmentMediaStateListener) this);
            MediaBufferListener mediaBufferListener = this.v;
            if (mediaBufferListener != null) {
                placementMediaView.a(mediaBufferListener);
            }
            PPSVideoRenderListener pPSVideoRenderListener = this.aj;
            if (pPSVideoRenderListener != null) {
                placementMediaView.a(pPSVideoRenderListener);
            }
            MuteListener muteListener = this.w;
            if (muteListener != null) {
                placementMediaView.a(muteListener);
            }
            placementMediaView.a((MuteListener) this);
            MediaErrorListener mediaErrorListener = this.x;
            if (mediaErrorListener != null) {
                placementMediaView.a(mediaErrorListener);
            }
            SegmentMediaStateListener segmentMediaStateListener = this.al;
            if (segmentMediaStateListener != null) {
                placementMediaView.a(segmentMediaStateListener);
            }
            if (z) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams.addRule(13);
                addView(placementMediaView, layoutParams);
            }
            placementMediaView.setAlpha(0.0f);
            placementMediaView.setPlacementAd(gVar);
            placementMediaView.setAudioFocusType(2);
        }
        return placementMediaView;
    }

    private boolean a(int[] iArr) {
        return iArr != null && iArr.length == 2;
    }

    private boolean a(PlacementMediaView placementMediaView, com.huawei.openalliance.ad.inter.data.g gVar) {
        return ((placementMediaView instanceof z) && gVar.isVideoAd()) || ((placementMediaView instanceof y) && gVar.isImageAd());
    }

    private void a(boolean z) {
        if (this.n < this.k.size() - 1) {
            k();
            m();
            if (z) {
                return;
            }
            l();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<IPlacementAd> list) {
        PlacementMediaFile mediaFile;
        PlacementMediaFile mediaFile2;
        if (bg.a(list)) {
            return;
        }
        ArrayList arrayList = new ArrayList(list);
        this.k.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            IPlacementAd iPlacementAd = (IPlacementAd) arrayList.get(i);
            if ((iPlacementAd instanceof com.huawei.openalliance.ad.inter.data.g) && (mediaFile2 = iPlacementAd.getMediaFile()) != null) {
                dk dkVar = this.S;
                String c = dkVar.c(dkVar.e(mediaFile2.getUrl()));
                mediaFile2.b(c);
                if (2 == mediaFile2.getPlayMode() || com.huawei.openalliance.ad.utils.ae.b(c)) {
                    this.k.add((com.huawei.openalliance.ad.inter.data.g) iPlacementAd);
                } else {
                    ho.b("PPSPlacementView", "has no cache, discard " + iPlacementAd.getContentId());
                }
            }
        }
        int size2 = this.k.size();
        this.A = new int[size2];
        if (bg.a(this.k)) {
            return;
        }
        Collections.sort(this.k);
        for (int i2 = 0; i2 < size2; i2++) {
            com.huawei.openalliance.ad.inter.data.g gVar = this.k.get(i2);
            int duration = (gVar == null || (mediaFile = gVar.getMediaFile()) == null) ? 0 : (int) mediaFile.getDuration();
            int[] iArr = this.A;
            if (i2 == 0) {
                iArr[i2] = duration;
            } else {
                iArr[i2] = duration + iArr[i2 - 1];
            }
        }
    }

    private void a(String str, String str2, int i, com.huawei.openalliance.ad.inter.data.g gVar) {
        if (gVar != null && gVar.q() != null) {
            Integer q = gVar.q();
            if (gVar.isVideoAd() && !gVar.D() && (q.intValue() == 1 || q.intValue() == 2)) {
                ho.b("PPSPlacementView", "reportAdShowEvent Media Over Completion reportAdShowEvent by customExposureType: %s", q);
                a((Integer) null);
            }
        }
        this.B.g();
        SegmentMediaStateListener segmentMediaStateListener = this.al;
        if (segmentMediaStateListener != null) {
            segmentMediaStateListener.onSegmentMediaCompletion(str, str2, i);
        }
        onSegmentMediaCompletion(str, str2, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str, final String str2, final int i) {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.16
            @Override // java.lang.Runnable
            public void run() {
                PPSPlacementView.this.onSegmentMediaError(str, str2, i, -1, -1);
            }
        });
    }

    private void a(Long l, Integer num, Integer num2) {
        com.huawei.openalliance.ad.inter.data.g currentAd = getCurrentAd();
        this.i.a(cz.a(Long.valueOf(this.U)));
        this.i.a(this.U);
        if (currentAd == null) {
            return;
        }
        boolean a2 = com.huawei.openalliance.ad.utils.c.a(currentAd.d(), num2);
        if (!currentAd.D() || (a2 && !currentAd.b())) {
            ho.b("PPSPlacementView", "reportAdShowEvent, customExposureType： real onAdShow");
            this.i.a(l.longValue(), num.intValue(), num2);
            if (a2) {
                currentAd.a(true);
            }
            if (currentAd.D()) {
                return;
            }
            currentAd.g(true);
            c(this.ad.isState(PlacementPlayState.State.SINGLE_INST) ? this.c : this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? this.f7914a : this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final PlacementMediaView placementMediaView, boolean z) {
        if (placementMediaView != null) {
            IPlacementAd placementAd = placementMediaView.getPlacementAd();
            ho.b("PPSPlacementView", "unloadMediaView, contentId: %s, remove: %s", placementAd != null ? placementAd.getContentId() : null, Boolean.valueOf(z));
            placementMediaView.g();
            placementMediaView.setPlacementAd(null);
            final ViewParent parent = placementMediaView.getParent();
            if (parent == null || !(parent instanceof ViewGroup)) {
                return;
            }
            placementMediaView.setAlpha(0.0f);
            if (z) {
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.20
                    @Override // java.lang.Runnable
                    public void run() {
                        PlacementMediaView placementMediaView2 = placementMediaView;
                        if (placementMediaView2 != null) {
                            placementMediaView2.a();
                        }
                        ViewParent viewParent = parent;
                        if (viewParent != null) {
                            ((ViewGroup) viewParent).removeView(placementMediaView);
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(PlacementMediaView placementMediaView) {
        if (placementMediaView == null) {
            ho.c("PPSPlacementView", "show ad with null media view");
            return;
        }
        jp jpVar = this.af.get(this.ai);
        int i = this.ai;
        if (i > 0 && i < this.af.size()) {
            this.af.get(this.ai - 1).b();
        }
        jpVar.a(placementMediaView);
        if (placementMediaView instanceof z) {
            jpVar.a(placementMediaView.getVideoView());
        }
        this.ai++;
        this.I = -1L;
        ho.a("PPSPlacementView", "showAd:%d", Integer.valueOf(this.n));
        this.B = placementMediaView;
        placementMediaView.setAlpha(1.0f);
        placementMediaView.a(true, this.D);
        if (!isShown()) {
            ho.c("PPSPlacementView", "view not visible, pause.");
            pauseView();
        }
        a(placementMediaView.getDuration() * 2);
    }

    private void a(lz lzVar, PlacementMediaView placementMediaView) {
        if (placementMediaView instanceof z) {
            lzVar.a(mq.a(0.0f, true, mp.STANDALONE));
            ((z) placementMediaView).a(lzVar);
        } else if (placementMediaView instanceof y) {
            lzVar.f();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(lz lzVar) {
        if (lzVar == null || getCurrentAd() == null || getCurrentAd().getMediaFile() == null) {
            return;
        }
        ho.b("PPSPlacementView", "om start");
        lzVar.a(getCurrentAd().getMediaFile().getDuration(), !"y".equals(getCurrentAd().getMediaFile().getSoundSwitch()));
    }

    private void a(ContentRecord contentRecord, tm tmVar, int[] iArr, int[] iArr2) {
        if (contentRecord == null || !contentRecord.bc() || cz.b(contentRecord.bb())) {
            ho.b("PPSPlacementView", "addTransparencyDialog failed, switch close or empty url.");
            return;
        }
        if (ao.a(iArr, 2) && ao.a(iArr2, 2)) {
            if (ho.a()) {
                ho.a("PPSPlacementView", "addTransparencyDialog, loc: %s, %s", Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]));
                ho.a("PPSPlacementView", "addTransparencyDialog, size: %s, %s", Integer.valueOf(iArr2[0]), Integer.valueOf(iArr2[1]));
            }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            ho.b("PPSPlacementView", "添加弹框view");
            addView(tmVar, layoutParams);
            contentRecord.u(cz.c(contentRecord.Z()));
            tmVar.setScreenWidth(getMeasuredWidth());
            tmVar.setScreenHeight(getMeasuredHeight());
            tmVar.setAdContent(contentRecord);
        }
    }

    private void a(PlacementPlayState placementPlayState, IAd iAd, PlacementMediaView placementMediaView) {
        if (iAd instanceof com.huawei.openalliance.ad.inter.data.g) {
            ContentRecord a2 = pi.a((com.huawei.openalliance.ad.inter.data.g) iAd);
            lz a3 = a(placementPlayState);
            a3.a(getContext(), a2, placementMediaView, true);
            d(a3);
            a3.c();
            a(a3, placementMediaView);
        }
    }

    private void a(View view, ContentRecord contentRecord, int[] iArr) {
        int[] b = dd.b(view);
        int[] c = dd.c(view);
        if (!a(iArr)) {
            iArr = b;
        }
        tm tmVar = new tm(getContext(), iArr, c, (Boolean) true);
        this.ae = tmVar;
        a(contentRecord, tmVar, iArr, c);
    }

    private void a(Context context) {
        PlacementPlayState placementPlayState;
        PlacementPlayState.State state;
        setBackgroundColor(-16777216);
        setUseRatioInMatchParentMode(false);
        this.i = new nm(context, this);
        this.j = new je(this, this);
        this.E = new com.huawei.openalliance.ad.analysis.c(context);
        this.S = dh.a(context, "normal");
        this.ab = (AudioManager) context.getSystemService(PresenterUtils.AUDIO);
        if (fh.b(context).ce()) {
            placementPlayState = this.ad;
            state = PlacementPlayState.State.SINGLE_INST;
        } else {
            placementPlayState = this.ad;
            state = PlacementPlayState.State.MAIN_VIEW;
        }
        placementPlayState.switchToState(state);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j) {
        if (this.Q) {
            return;
        }
        this.Q = true;
        ho.b("PPSPlacementView", "timeout, submit: %s", Long.valueOf(j));
        this.ak.sendEmptyMessageDelayed(1001, j);
    }

    private void a(int i) {
        IPlacementMediaStateListener iPlacementMediaStateListener;
        if (i > 0 && !this.N) {
            q();
        }
        if (i <= 0 || !this.O || (iPlacementMediaStateListener = this.y) == null) {
            return;
        }
        this.O = false;
        this.P = true;
        iPlacementMediaStateListener.onMediaStart(this.H);
        r();
    }

    private PlacementMediaView a(com.huawei.openalliance.ad.inter.data.g gVar) {
        if (gVar == null) {
            ho.c("PPSPlacementView", "create media view with null ad");
            return null;
        }
        ho.a("PPSPlacementView", "create media view for content:%s", gVar.getContentId());
        if (gVar.isVideoAd()) {
            ho.b("PPSPlacementView", "create video view");
            return new z(getContext());
        }
        if (gVar.isImageAd()) {
            ho.b("PPSPlacementView", "create image view");
            return new y(getContext());
        }
        ho.b("PPSPlacementView", "return image view for default");
        return new y(getContext());
    }

    private lz a(PlacementPlayState placementPlayState) {
        if (placementPlayState.isState(PlacementPlayState.State.SINGLE_INST)) {
            this.c.b();
            lo loVar = new lo();
            this.c = loVar;
            return loVar;
        }
        if (placementPlayState.isState(PlacementPlayState.State.MAIN_VIEW)) {
            this.f7914a.b();
            lo loVar2 = new lo();
            this.f7914a = loVar2;
            return loVar2;
        }
        this.b.b();
        lo loVar3 = new lo();
        this.b = loVar3;
        return loVar3;
    }

    private int a(int i, com.huawei.openalliance.ad.inter.data.g gVar, long j) {
        if (this.y != null || this.F || i > 0) {
            if (i > j && j > 0) {
                i = (int) j;
            }
            int currentPlayTime = getCurrentPlayTime() + i;
            int[] iArr = this.A;
            int i2 = (iArr == null || iArr.length <= 0 || iArr[iArr.length + (-1)] <= 0) ? -1 : (int) ((currentPlayTime / iArr[iArr.length - 1]) * 100.0f);
            if (gVar != null) {
                this.d = currentPlayTime;
                this.e = i2;
            }
            IPlacementMediaStateListener iPlacementMediaStateListener = this.y;
            if (iPlacementMediaStateListener != null) {
                iPlacementMediaStateListener.onMediaProgress(i2, currentPlayTime);
            }
        }
        return i;
    }

    /* loaded from: classes9.dex */
    static class a implements AudioManager.OnAudioFocusChangeListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<PPSPlacementView> f7939a;

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(final int i) {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.a.1
                @Override // java.lang.Runnable
                public void run() {
                    PPSPlacementView pPSPlacementView = (PPSPlacementView) a.this.f7939a.get();
                    if (pPSPlacementView == null) {
                        return;
                    }
                    ho.b("PPSPlacementView", "onAudioFocusChange %d previous: %d", Integer.valueOf(i), Integer.valueOf(pPSPlacementView.W));
                    int i2 = i;
                    if (i2 == -3) {
                        a.this.b(pPSPlacementView);
                    } else if (i2 == -2 || i2 == -1) {
                        a.this.a(pPSPlacementView);
                    } else if (i2 == 1 || i2 == 2) {
                        a.this.c(pPSPlacementView);
                    }
                    pPSPlacementView.W = i;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(PPSPlacementView pPSPlacementView) {
            ho.b("PPSPlacementView", "handleAudioFocusGain.");
            if (!pPSPlacementView.aa || pPSPlacementView.B == null) {
                return;
            }
            pPSPlacementView.B.d();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(PPSPlacementView pPSPlacementView) {
            ho.b("PPSPlacementView", "handleAudioFocusLossTransientCanDuck soundMuted: " + pPSPlacementView.D);
            if (pPSPlacementView.D || pPSPlacementView.B == null) {
                return;
            }
            pPSPlacementView.B.c();
            pPSPlacementView.aa = true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(PPSPlacementView pPSPlacementView) {
            b(pPSPlacementView);
        }

        public a(PPSPlacementView pPSPlacementView) {
            this.f7939a = new WeakReference<>(pPSPlacementView);
        }
    }

    public PPSPlacementView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f7914a = new lo();
        this.b = new lo();
        this.c = new lo();
        this.h = true;
        this.k = new ArrayList(4);
        this.n = 0;
        this.q = false;
        this.r = false;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.D = false;
        this.F = false;
        this.G = -1;
        this.H = -1;
        this.I = -1L;
        this.J = false;
        this.K = false;
        this.L = -1;
        this.M = null;
        this.N = false;
        this.O = false;
        this.P = false;
        this.Q = false;
        this.d = -1;
        this.e = -1;
        this.W = 0;
        this.aa = false;
        this.ad = new PlacementPlayState();
        this.ag = new AtomicBoolean();
        this.ah = new ArrayList();
        this.ai = 0;
        this.aj = new PPSVideoRenderListener() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.1
            @Override // com.huawei.openalliance.ad.media.listener.PPSVideoRenderListener
            public void onVideoRenderStart() {
                ho.b("PPSPlacementView", "videoRenderStart");
                PPSPlacementView.this.q();
                if (!PPSPlacementView.this.O || PPSPlacementView.this.y == null) {
                    return;
                }
                PPSPlacementView.this.O = false;
                PPSPlacementView.this.P = true;
                ho.b("PPSPlacementView", "onMediaStart callback, playTime: %s", Integer.valueOf(PPSPlacementView.this.H));
                PPSPlacementView.this.y.onMediaStart(PPSPlacementView.this.H);
                PPSPlacementView.this.r();
            }
        };
        this.ak = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.12
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                int i3;
                com.huawei.openalliance.ad.inter.data.g currentAd = PPSPlacementView.this.getCurrentAd();
                PlacementMediaFile currentMediaFile = PPSPlacementView.this.getCurrentMediaFile();
                String str = "";
                String contentId = currentAd != null ? currentAd.getContentId() : "";
                if (currentMediaFile != null) {
                    str = currentMediaFile.getUrl();
                    i3 = (int) currentMediaFile.getDuration();
                } else {
                    i3 = 0;
                }
                ho.b("PPSPlacementView", "callback timeout: %s", contentId);
                if (PPSPlacementView.this.B == null) {
                    return true;
                }
                ho.b("PPSPlacementView", "notify Error");
                PPSPlacementView.this.a(contentId, str, i3);
                return true;
            }
        });
        this.al = new SegmentMediaStateListener() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.17
            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentMediaError(String str, String str2, int i3, int i4, int i5) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentProgress(String str, String str2, int i3, int i4) {
                lz lzVar;
                if ((str == null || str.equalsIgnoreCase(PPSPlacementView.this.getCurrentContentId())) && !PPSPlacementView.this.T && (PPSPlacementView.this.B instanceof z)) {
                    if (PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
                        lzVar = PPSPlacementView.this.c;
                    } else {
                        lzVar = PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? PPSPlacementView.this.f7914a : PPSPlacementView.this.b;
                    }
                    lzVar.a(i3);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentMediaStop(String str, String str2, int i3) {
                lz lzVar;
                if (ho.a()) {
                    ho.a("PPSPlacementView", "OM onSegmentMediaStop");
                }
                if (str != null && !str.equalsIgnoreCase(PPSPlacementView.this.getCurrentContentId())) {
                    ho.b("PPSPlacementView", "OM onSegmentMediaStop not equals");
                    return;
                }
                if (PPSPlacementView.this.T) {
                    return;
                }
                PPSPlacementView.this.T = true;
                if (PPSPlacementView.this.B instanceof z) {
                    if (PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
                        lzVar = PPSPlacementView.this.c;
                    } else {
                        lzVar = PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? PPSPlacementView.this.f7914a : PPSPlacementView.this.b;
                    }
                    lzVar.g();
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentMediaStart(String str, String str2, int i3) {
                PPSPlacementView pPSPlacementView;
                lz lzVar;
                lz lzVar2;
                if (ho.a()) {
                    ho.a("PPSPlacementView", "OM onSegmentMediaStart");
                }
                PPSPlacementView.this.T = false;
                if (PPSPlacementView.this.B instanceof z) {
                    boolean isState = PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST);
                    if (i3 > 0) {
                        if (isState) {
                            lzVar2 = PPSPlacementView.this.c;
                        } else {
                            lzVar2 = PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? PPSPlacementView.this.f7914a : PPSPlacementView.this.b;
                        }
                        lzVar2.l();
                        return;
                    }
                    if (isState) {
                        pPSPlacementView = PPSPlacementView.this;
                        lzVar = pPSPlacementView.c;
                    } else if (PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW)) {
                        pPSPlacementView = PPSPlacementView.this;
                        lzVar = pPSPlacementView.f7914a;
                    } else {
                        pPSPlacementView = PPSPlacementView.this;
                        lzVar = pPSPlacementView.b;
                    }
                    pPSPlacementView.a(lzVar);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentMediaPause(String str, String str2, int i3) {
                lz lzVar;
                if (ho.a()) {
                    ho.a("PPSPlacementView", "OM onSegmentMediaPause");
                }
                if ((str == null || str.equalsIgnoreCase(PPSPlacementView.this.getCurrentContentId())) && (PPSPlacementView.this.B instanceof z)) {
                    if (PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
                        lzVar = PPSPlacementView.this.c;
                    } else {
                        lzVar = PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? PPSPlacementView.this.f7914a : PPSPlacementView.this.b;
                    }
                    lzVar.k();
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentMediaCompletion(String str, String str2, int i3) {
                lz lzVar;
                if (ho.a()) {
                    ho.a("PPSPlacementView", "OM onSegmentMediaCompletion");
                }
                if (str != null && !str.equalsIgnoreCase(PPSPlacementView.this.getCurrentContentId())) {
                    ho.b("PPSPlacementView", "OM onSegmentMediaCompletion not equals");
                    return;
                }
                if (PPSPlacementView.this.T) {
                    return;
                }
                PPSPlacementView.this.T = true;
                if (PPSPlacementView.this.B instanceof z) {
                    if (PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
                        lzVar = PPSPlacementView.this.c;
                    } else {
                        lzVar = PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? PPSPlacementView.this.f7914a : PPSPlacementView.this.b;
                    }
                    lzVar.g();
                }
            }
        };
        this.am = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.22
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ho.a("PPSPlacementView", "clickable.OnTouchListener.ontouch");
                try {
                    int a2 = th.a(motionEvent);
                    if (a2 == 0) {
                        PPSPlacementView.this.f = th.a(view, motionEvent);
                        PPSPlacementView.this.f.a(vd.b(PPSPlacementView.this));
                    }
                    if (1 != a2) {
                        return false;
                    }
                    th.a(view, motionEvent, null, PPSPlacementView.this.f);
                    return false;
                } catch (Throwable th) {
                    ho.c("PPSPlacementView", "clickable.OnTouchListener.ontouch exception : %s", th.getClass().getSimpleName());
                    return false;
                }
            }
        };
        this.an = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.23.1
                    @Override // java.lang.Runnable
                    public void run() {
                        PPSPlacementView.this.g();
                    }
                });
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.ao = new a(this);
        a(context);
    }

    public PPSPlacementView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7914a = new lo();
        this.b = new lo();
        this.c = new lo();
        this.h = true;
        this.k = new ArrayList(4);
        this.n = 0;
        this.q = false;
        this.r = false;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.D = false;
        this.F = false;
        this.G = -1;
        this.H = -1;
        this.I = -1L;
        this.J = false;
        this.K = false;
        this.L = -1;
        this.M = null;
        this.N = false;
        this.O = false;
        this.P = false;
        this.Q = false;
        this.d = -1;
        this.e = -1;
        this.W = 0;
        this.aa = false;
        this.ad = new PlacementPlayState();
        this.ag = new AtomicBoolean();
        this.ah = new ArrayList();
        this.ai = 0;
        this.aj = new PPSVideoRenderListener() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.1
            @Override // com.huawei.openalliance.ad.media.listener.PPSVideoRenderListener
            public void onVideoRenderStart() {
                ho.b("PPSPlacementView", "videoRenderStart");
                PPSPlacementView.this.q();
                if (!PPSPlacementView.this.O || PPSPlacementView.this.y == null) {
                    return;
                }
                PPSPlacementView.this.O = false;
                PPSPlacementView.this.P = true;
                ho.b("PPSPlacementView", "onMediaStart callback, playTime: %s", Integer.valueOf(PPSPlacementView.this.H));
                PPSPlacementView.this.y.onMediaStart(PPSPlacementView.this.H);
                PPSPlacementView.this.r();
            }
        };
        this.ak = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.12
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                int i3;
                com.huawei.openalliance.ad.inter.data.g currentAd = PPSPlacementView.this.getCurrentAd();
                PlacementMediaFile currentMediaFile = PPSPlacementView.this.getCurrentMediaFile();
                String str = "";
                String contentId = currentAd != null ? currentAd.getContentId() : "";
                if (currentMediaFile != null) {
                    str = currentMediaFile.getUrl();
                    i3 = (int) currentMediaFile.getDuration();
                } else {
                    i3 = 0;
                }
                ho.b("PPSPlacementView", "callback timeout: %s", contentId);
                if (PPSPlacementView.this.B == null) {
                    return true;
                }
                ho.b("PPSPlacementView", "notify Error");
                PPSPlacementView.this.a(contentId, str, i3);
                return true;
            }
        });
        this.al = new SegmentMediaStateListener() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.17
            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentMediaError(String str, String str2, int i3, int i4, int i5) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentProgress(String str, String str2, int i3, int i4) {
                lz lzVar;
                if ((str == null || str.equalsIgnoreCase(PPSPlacementView.this.getCurrentContentId())) && !PPSPlacementView.this.T && (PPSPlacementView.this.B instanceof z)) {
                    if (PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
                        lzVar = PPSPlacementView.this.c;
                    } else {
                        lzVar = PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? PPSPlacementView.this.f7914a : PPSPlacementView.this.b;
                    }
                    lzVar.a(i3);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentMediaStop(String str, String str2, int i3) {
                lz lzVar;
                if (ho.a()) {
                    ho.a("PPSPlacementView", "OM onSegmentMediaStop");
                }
                if (str != null && !str.equalsIgnoreCase(PPSPlacementView.this.getCurrentContentId())) {
                    ho.b("PPSPlacementView", "OM onSegmentMediaStop not equals");
                    return;
                }
                if (PPSPlacementView.this.T) {
                    return;
                }
                PPSPlacementView.this.T = true;
                if (PPSPlacementView.this.B instanceof z) {
                    if (PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
                        lzVar = PPSPlacementView.this.c;
                    } else {
                        lzVar = PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? PPSPlacementView.this.f7914a : PPSPlacementView.this.b;
                    }
                    lzVar.g();
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentMediaStart(String str, String str2, int i3) {
                PPSPlacementView pPSPlacementView;
                lz lzVar;
                lz lzVar2;
                if (ho.a()) {
                    ho.a("PPSPlacementView", "OM onSegmentMediaStart");
                }
                PPSPlacementView.this.T = false;
                if (PPSPlacementView.this.B instanceof z) {
                    boolean isState = PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST);
                    if (i3 > 0) {
                        if (isState) {
                            lzVar2 = PPSPlacementView.this.c;
                        } else {
                            lzVar2 = PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? PPSPlacementView.this.f7914a : PPSPlacementView.this.b;
                        }
                        lzVar2.l();
                        return;
                    }
                    if (isState) {
                        pPSPlacementView = PPSPlacementView.this;
                        lzVar = pPSPlacementView.c;
                    } else if (PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW)) {
                        pPSPlacementView = PPSPlacementView.this;
                        lzVar = pPSPlacementView.f7914a;
                    } else {
                        pPSPlacementView = PPSPlacementView.this;
                        lzVar = pPSPlacementView.b;
                    }
                    pPSPlacementView.a(lzVar);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentMediaPause(String str, String str2, int i3) {
                lz lzVar;
                if (ho.a()) {
                    ho.a("PPSPlacementView", "OM onSegmentMediaPause");
                }
                if ((str == null || str.equalsIgnoreCase(PPSPlacementView.this.getCurrentContentId())) && (PPSPlacementView.this.B instanceof z)) {
                    if (PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
                        lzVar = PPSPlacementView.this.c;
                    } else {
                        lzVar = PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? PPSPlacementView.this.f7914a : PPSPlacementView.this.b;
                    }
                    lzVar.k();
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentMediaCompletion(String str, String str2, int i3) {
                lz lzVar;
                if (ho.a()) {
                    ho.a("PPSPlacementView", "OM onSegmentMediaCompletion");
                }
                if (str != null && !str.equalsIgnoreCase(PPSPlacementView.this.getCurrentContentId())) {
                    ho.b("PPSPlacementView", "OM onSegmentMediaCompletion not equals");
                    return;
                }
                if (PPSPlacementView.this.T) {
                    return;
                }
                PPSPlacementView.this.T = true;
                if (PPSPlacementView.this.B instanceof z) {
                    if (PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
                        lzVar = PPSPlacementView.this.c;
                    } else {
                        lzVar = PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? PPSPlacementView.this.f7914a : PPSPlacementView.this.b;
                    }
                    lzVar.g();
                }
            }
        };
        this.am = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.22
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ho.a("PPSPlacementView", "clickable.OnTouchListener.ontouch");
                try {
                    int a2 = th.a(motionEvent);
                    if (a2 == 0) {
                        PPSPlacementView.this.f = th.a(view, motionEvent);
                        PPSPlacementView.this.f.a(vd.b(PPSPlacementView.this));
                    }
                    if (1 != a2) {
                        return false;
                    }
                    th.a(view, motionEvent, null, PPSPlacementView.this.f);
                    return false;
                } catch (Throwable th) {
                    ho.c("PPSPlacementView", "clickable.OnTouchListener.ontouch exception : %s", th.getClass().getSimpleName());
                    return false;
                }
            }
        };
        this.an = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.23.1
                    @Override // java.lang.Runnable
                    public void run() {
                        PPSPlacementView.this.g();
                    }
                });
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.ao = new a(this);
        a(context);
    }

    public PPSPlacementView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7914a = new lo();
        this.b = new lo();
        this.c = new lo();
        this.h = true;
        this.k = new ArrayList(4);
        this.n = 0;
        this.q = false;
        this.r = false;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.D = false;
        this.F = false;
        this.G = -1;
        this.H = -1;
        this.I = -1L;
        this.J = false;
        this.K = false;
        this.L = -1;
        this.M = null;
        this.N = false;
        this.O = false;
        this.P = false;
        this.Q = false;
        this.d = -1;
        this.e = -1;
        this.W = 0;
        this.aa = false;
        this.ad = new PlacementPlayState();
        this.ag = new AtomicBoolean();
        this.ah = new ArrayList();
        this.ai = 0;
        this.aj = new PPSVideoRenderListener() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.1
            @Override // com.huawei.openalliance.ad.media.listener.PPSVideoRenderListener
            public void onVideoRenderStart() {
                ho.b("PPSPlacementView", "videoRenderStart");
                PPSPlacementView.this.q();
                if (!PPSPlacementView.this.O || PPSPlacementView.this.y == null) {
                    return;
                }
                PPSPlacementView.this.O = false;
                PPSPlacementView.this.P = true;
                ho.b("PPSPlacementView", "onMediaStart callback, playTime: %s", Integer.valueOf(PPSPlacementView.this.H));
                PPSPlacementView.this.y.onMediaStart(PPSPlacementView.this.H);
                PPSPlacementView.this.r();
            }
        };
        this.ak = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.12
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                int i3;
                com.huawei.openalliance.ad.inter.data.g currentAd = PPSPlacementView.this.getCurrentAd();
                PlacementMediaFile currentMediaFile = PPSPlacementView.this.getCurrentMediaFile();
                String str = "";
                String contentId = currentAd != null ? currentAd.getContentId() : "";
                if (currentMediaFile != null) {
                    str = currentMediaFile.getUrl();
                    i3 = (int) currentMediaFile.getDuration();
                } else {
                    i3 = 0;
                }
                ho.b("PPSPlacementView", "callback timeout: %s", contentId);
                if (PPSPlacementView.this.B == null) {
                    return true;
                }
                ho.b("PPSPlacementView", "notify Error");
                PPSPlacementView.this.a(contentId, str, i3);
                return true;
            }
        });
        this.al = new SegmentMediaStateListener() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.17
            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentMediaError(String str, String str2, int i3, int i4, int i5) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentProgress(String str, String str2, int i3, int i4) {
                lz lzVar;
                if ((str == null || str.equalsIgnoreCase(PPSPlacementView.this.getCurrentContentId())) && !PPSPlacementView.this.T && (PPSPlacementView.this.B instanceof z)) {
                    if (PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
                        lzVar = PPSPlacementView.this.c;
                    } else {
                        lzVar = PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? PPSPlacementView.this.f7914a : PPSPlacementView.this.b;
                    }
                    lzVar.a(i3);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentMediaStop(String str, String str2, int i3) {
                lz lzVar;
                if (ho.a()) {
                    ho.a("PPSPlacementView", "OM onSegmentMediaStop");
                }
                if (str != null && !str.equalsIgnoreCase(PPSPlacementView.this.getCurrentContentId())) {
                    ho.b("PPSPlacementView", "OM onSegmentMediaStop not equals");
                    return;
                }
                if (PPSPlacementView.this.T) {
                    return;
                }
                PPSPlacementView.this.T = true;
                if (PPSPlacementView.this.B instanceof z) {
                    if (PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
                        lzVar = PPSPlacementView.this.c;
                    } else {
                        lzVar = PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? PPSPlacementView.this.f7914a : PPSPlacementView.this.b;
                    }
                    lzVar.g();
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentMediaStart(String str, String str2, int i3) {
                PPSPlacementView pPSPlacementView;
                lz lzVar;
                lz lzVar2;
                if (ho.a()) {
                    ho.a("PPSPlacementView", "OM onSegmentMediaStart");
                }
                PPSPlacementView.this.T = false;
                if (PPSPlacementView.this.B instanceof z) {
                    boolean isState = PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST);
                    if (i3 > 0) {
                        if (isState) {
                            lzVar2 = PPSPlacementView.this.c;
                        } else {
                            lzVar2 = PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? PPSPlacementView.this.f7914a : PPSPlacementView.this.b;
                        }
                        lzVar2.l();
                        return;
                    }
                    if (isState) {
                        pPSPlacementView = PPSPlacementView.this;
                        lzVar = pPSPlacementView.c;
                    } else if (PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW)) {
                        pPSPlacementView = PPSPlacementView.this;
                        lzVar = pPSPlacementView.f7914a;
                    } else {
                        pPSPlacementView = PPSPlacementView.this;
                        lzVar = pPSPlacementView.b;
                    }
                    pPSPlacementView.a(lzVar);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentMediaPause(String str, String str2, int i3) {
                lz lzVar;
                if (ho.a()) {
                    ho.a("PPSPlacementView", "OM onSegmentMediaPause");
                }
                if ((str == null || str.equalsIgnoreCase(PPSPlacementView.this.getCurrentContentId())) && (PPSPlacementView.this.B instanceof z)) {
                    if (PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
                        lzVar = PPSPlacementView.this.c;
                    } else {
                        lzVar = PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? PPSPlacementView.this.f7914a : PPSPlacementView.this.b;
                    }
                    lzVar.k();
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentMediaCompletion(String str, String str2, int i3) {
                lz lzVar;
                if (ho.a()) {
                    ho.a("PPSPlacementView", "OM onSegmentMediaCompletion");
                }
                if (str != null && !str.equalsIgnoreCase(PPSPlacementView.this.getCurrentContentId())) {
                    ho.b("PPSPlacementView", "OM onSegmentMediaCompletion not equals");
                    return;
                }
                if (PPSPlacementView.this.T) {
                    return;
                }
                PPSPlacementView.this.T = true;
                if (PPSPlacementView.this.B instanceof z) {
                    if (PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
                        lzVar = PPSPlacementView.this.c;
                    } else {
                        lzVar = PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? PPSPlacementView.this.f7914a : PPSPlacementView.this.b;
                    }
                    lzVar.g();
                }
            }
        };
        this.am = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.22
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ho.a("PPSPlacementView", "clickable.OnTouchListener.ontouch");
                try {
                    int a2 = th.a(motionEvent);
                    if (a2 == 0) {
                        PPSPlacementView.this.f = th.a(view, motionEvent);
                        PPSPlacementView.this.f.a(vd.b(PPSPlacementView.this));
                    }
                    if (1 != a2) {
                        return false;
                    }
                    th.a(view, motionEvent, null, PPSPlacementView.this.f);
                    return false;
                } catch (Throwable th) {
                    ho.c("PPSPlacementView", "clickable.OnTouchListener.ontouch exception : %s", th.getClass().getSimpleName());
                    return false;
                }
            }
        };
        this.an = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.23.1
                    @Override // java.lang.Runnable
                    public void run() {
                        PPSPlacementView.this.g();
                    }
                });
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.ao = new a(this);
        a(context);
    }

    public PPSPlacementView(Context context) {
        super(context);
        this.f7914a = new lo();
        this.b = new lo();
        this.c = new lo();
        this.h = true;
        this.k = new ArrayList(4);
        this.n = 0;
        this.q = false;
        this.r = false;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.D = false;
        this.F = false;
        this.G = -1;
        this.H = -1;
        this.I = -1L;
        this.J = false;
        this.K = false;
        this.L = -1;
        this.M = null;
        this.N = false;
        this.O = false;
        this.P = false;
        this.Q = false;
        this.d = -1;
        this.e = -1;
        this.W = 0;
        this.aa = false;
        this.ad = new PlacementPlayState();
        this.ag = new AtomicBoolean();
        this.ah = new ArrayList();
        this.ai = 0;
        this.aj = new PPSVideoRenderListener() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.1
            @Override // com.huawei.openalliance.ad.media.listener.PPSVideoRenderListener
            public void onVideoRenderStart() {
                ho.b("PPSPlacementView", "videoRenderStart");
                PPSPlacementView.this.q();
                if (!PPSPlacementView.this.O || PPSPlacementView.this.y == null) {
                    return;
                }
                PPSPlacementView.this.O = false;
                PPSPlacementView.this.P = true;
                ho.b("PPSPlacementView", "onMediaStart callback, playTime: %s", Integer.valueOf(PPSPlacementView.this.H));
                PPSPlacementView.this.y.onMediaStart(PPSPlacementView.this.H);
                PPSPlacementView.this.r();
            }
        };
        this.ak = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.12
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                int i3;
                com.huawei.openalliance.ad.inter.data.g currentAd = PPSPlacementView.this.getCurrentAd();
                PlacementMediaFile currentMediaFile = PPSPlacementView.this.getCurrentMediaFile();
                String str = "";
                String contentId = currentAd != null ? currentAd.getContentId() : "";
                if (currentMediaFile != null) {
                    str = currentMediaFile.getUrl();
                    i3 = (int) currentMediaFile.getDuration();
                } else {
                    i3 = 0;
                }
                ho.b("PPSPlacementView", "callback timeout: %s", contentId);
                if (PPSPlacementView.this.B == null) {
                    return true;
                }
                ho.b("PPSPlacementView", "notify Error");
                PPSPlacementView.this.a(contentId, str, i3);
                return true;
            }
        });
        this.al = new SegmentMediaStateListener() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.17
            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentMediaError(String str, String str2, int i3, int i4, int i5) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentProgress(String str, String str2, int i3, int i4) {
                lz lzVar;
                if ((str == null || str.equalsIgnoreCase(PPSPlacementView.this.getCurrentContentId())) && !PPSPlacementView.this.T && (PPSPlacementView.this.B instanceof z)) {
                    if (PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
                        lzVar = PPSPlacementView.this.c;
                    } else {
                        lzVar = PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? PPSPlacementView.this.f7914a : PPSPlacementView.this.b;
                    }
                    lzVar.a(i3);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentMediaStop(String str, String str2, int i3) {
                lz lzVar;
                if (ho.a()) {
                    ho.a("PPSPlacementView", "OM onSegmentMediaStop");
                }
                if (str != null && !str.equalsIgnoreCase(PPSPlacementView.this.getCurrentContentId())) {
                    ho.b("PPSPlacementView", "OM onSegmentMediaStop not equals");
                    return;
                }
                if (PPSPlacementView.this.T) {
                    return;
                }
                PPSPlacementView.this.T = true;
                if (PPSPlacementView.this.B instanceof z) {
                    if (PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
                        lzVar = PPSPlacementView.this.c;
                    } else {
                        lzVar = PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? PPSPlacementView.this.f7914a : PPSPlacementView.this.b;
                    }
                    lzVar.g();
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentMediaStart(String str, String str2, int i3) {
                PPSPlacementView pPSPlacementView;
                lz lzVar;
                lz lzVar2;
                if (ho.a()) {
                    ho.a("PPSPlacementView", "OM onSegmentMediaStart");
                }
                PPSPlacementView.this.T = false;
                if (PPSPlacementView.this.B instanceof z) {
                    boolean isState = PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST);
                    if (i3 > 0) {
                        if (isState) {
                            lzVar2 = PPSPlacementView.this.c;
                        } else {
                            lzVar2 = PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? PPSPlacementView.this.f7914a : PPSPlacementView.this.b;
                        }
                        lzVar2.l();
                        return;
                    }
                    if (isState) {
                        pPSPlacementView = PPSPlacementView.this;
                        lzVar = pPSPlacementView.c;
                    } else if (PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW)) {
                        pPSPlacementView = PPSPlacementView.this;
                        lzVar = pPSPlacementView.f7914a;
                    } else {
                        pPSPlacementView = PPSPlacementView.this;
                        lzVar = pPSPlacementView.b;
                    }
                    pPSPlacementView.a(lzVar);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentMediaPause(String str, String str2, int i3) {
                lz lzVar;
                if (ho.a()) {
                    ho.a("PPSPlacementView", "OM onSegmentMediaPause");
                }
                if ((str == null || str.equalsIgnoreCase(PPSPlacementView.this.getCurrentContentId())) && (PPSPlacementView.this.B instanceof z)) {
                    if (PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
                        lzVar = PPSPlacementView.this.c;
                    } else {
                        lzVar = PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? PPSPlacementView.this.f7914a : PPSPlacementView.this.b;
                    }
                    lzVar.k();
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener
            public void onSegmentMediaCompletion(String str, String str2, int i3) {
                lz lzVar;
                if (ho.a()) {
                    ho.a("PPSPlacementView", "OM onSegmentMediaCompletion");
                }
                if (str != null && !str.equalsIgnoreCase(PPSPlacementView.this.getCurrentContentId())) {
                    ho.b("PPSPlacementView", "OM onSegmentMediaCompletion not equals");
                    return;
                }
                if (PPSPlacementView.this.T) {
                    return;
                }
                PPSPlacementView.this.T = true;
                if (PPSPlacementView.this.B instanceof z) {
                    if (PPSPlacementView.this.ad.isState(PlacementPlayState.State.SINGLE_INST)) {
                        lzVar = PPSPlacementView.this.c;
                    } else {
                        lzVar = PPSPlacementView.this.ad.isState(PlacementPlayState.State.MAIN_VIEW) ? PPSPlacementView.this.f7914a : PPSPlacementView.this.b;
                    }
                    lzVar.g();
                }
            }
        };
        this.am = new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.22
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ho.a("PPSPlacementView", "clickable.OnTouchListener.ontouch");
                try {
                    int a2 = th.a(motionEvent);
                    if (a2 == 0) {
                        PPSPlacementView.this.f = th.a(view, motionEvent);
                        PPSPlacementView.this.f.a(vd.b(PPSPlacementView.this));
                    }
                    if (1 != a2) {
                        return false;
                    }
                    th.a(view, motionEvent, null, PPSPlacementView.this.f);
                    return false;
                } catch (Throwable th) {
                    ho.c("PPSPlacementView", "clickable.OnTouchListener.ontouch exception : %s", th.getClass().getSimpleName());
                    return false;
                }
            }
        };
        this.an = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.PPSPlacementView.23.1
                    @Override // java.lang.Runnable
                    public void run() {
                        PPSPlacementView.this.g();
                    }
                });
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.ao = new a(this);
        a(context);
    }
}
