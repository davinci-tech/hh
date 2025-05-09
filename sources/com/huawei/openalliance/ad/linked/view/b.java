package com.huawei.openalliance.ad.linked.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.openalliance.ad.activity.PPSActivity;
import com.huawei.openalliance.ad.constant.RewardMethods;
import com.huawei.openalliance.ad.gs;
import com.huawei.openalliance.ad.gt;
import com.huawei.openalliance.ad.gv;
import com.huawei.openalliance.ad.gw;
import com.huawei.openalliance.ad.gx;
import com.huawei.openalliance.ad.hc;
import com.huawei.openalliance.ad.hd;
import com.huawei.openalliance.ad.he;
import com.huawei.openalliance.ad.hf;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.ij;
import com.huawei.openalliance.ad.inter.data.ImageInfo;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.jg;
import com.huawei.openalliance.ad.linked.view.c;
import com.huawei.openalliance.ad.media.MediaPlayerAgent;
import com.huawei.openalliance.ad.media.MediaState;
import com.huawei.openalliance.ad.media.listener.MediaBufferListener;
import com.huawei.openalliance.ad.media.listener.MediaErrorListener;
import com.huawei.openalliance.ad.media.listener.MediaStateListener;
import com.huawei.openalliance.ad.media.listener.MuteListener;
import com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener;
import com.huawei.openalliance.ad.os;
import com.huawei.openalliance.ad.utils.ae;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bv;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.m;
import com.huawei.openalliance.ad.views.VideoView;
import com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle;

/* loaded from: classes9.dex */
public class b extends d implements hf, IViewLifeCycle {
    private final MediaStateListener A;
    private MediaBufferListener B;
    private final MediaErrorListener C;
    private hc D;
    private LinkedAppDetailView E;
    private Context F;
    private boolean G;

    /* renamed from: a, reason: collision with root package name */
    public View f7169a;
    private a f;
    private c.a g;
    private boolean h;
    private hd i;
    private gs j;
    private gw k;
    private VideoInfo l;
    private ImageInfo m;
    private boolean n;
    private long o;
    private LinkedNativeViewControlPanel p;
    private VideoView q;
    private jg r;
    private boolean s;
    private int t;
    private final com.huawei.openalliance.ad.media.listener.a u;
    private final MuteListener v;
    private final hd.a w;
    private long x;
    private long y;
    private final ReportVideoTimeListener z;

    public interface a {
        void a();

        void a(boolean z);

        void b();

        void c();

        void d();
    }

    @Override // com.huawei.openalliance.ad.hf
    public void setVideoView(VideoView videoView) {
        this.q = videoView;
    }

    public void setVideoReleaseListener(c.a aVar) {
        this.g = aVar;
    }

    public void setVideoEventListener(a aVar) {
        this.f = aVar;
    }

    public void setPlayModeChangeListener(PPSActivity.b bVar) {
        hd hdVar = this.i;
        if (hdVar != null) {
            hdVar.a(bVar);
        }
    }

    public void setNotShowDataUsageAlert(boolean z) {
        this.i.h(z);
    }

    @Override // com.huawei.openalliance.ad.linked.view.d, com.huawei.openalliance.ad.hf
    public void setLinkedNativeAd(gs gsVar) {
        this.j = gsVar;
        this.D.a(gsVar);
        MediaState currentState = this.q.getCurrentState();
        if (this.j == gsVar && currentState.isNotState(MediaState.State.IDLE) && currentState.isNotState(MediaState.State.ERROR)) {
            ho.a("LinkedLandVideoView", "setLinkedNativeAd - has the same ad");
            return;
        }
        super.setLinkedNativeAd(gsVar);
        m();
        if (this.j == null) {
            this.i.d(true);
            this.l = null;
        } else {
            k();
            l();
            this.i.f(false);
            this.k.a(this.j);
        }
    }

    @Override // com.huawei.openalliance.ad.hf
    public void setLinkedLandView(he heVar) {
        this.D.a(this.E);
    }

    public void setCoverClickListener(View.OnClickListener onClickListener) {
        this.i.a(onClickListener);
        this.E.setVideoCoverClickListener(onClickListener);
    }

    public void setAudioFocusType(int i) {
        this.q.setAudioFocusType(i);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void resumeView() {
        this.i.l();
        ho.b("LinkedLandVideoView", RewardMethods.RESUME_VIEW);
        j();
        this.c = false;
        this.e.onGlobalLayout();
        this.q.setNeedPauseOnSurfaceDestory(true);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void pauseView() {
        this.i.k();
    }

    @Override // com.huawei.openalliance.ad.hf
    public void i() {
        View view = this.f7169a;
        if (view == null) {
            return;
        }
        if (view.getParent() != null) {
            ho.a("LinkedLandVideoView", "removeSelf removeView");
            ((ViewGroup) this.f7169a.getParent()).removeView(this.f7169a);
        } else {
            ho.a("LinkedLandVideoView", "removeSelf gone");
            this.f7169a.setVisibility(8);
        }
    }

    @Override // com.huawei.openalliance.ad.hf
    public LinkedAppDetailView h() {
        return this.E;
    }

    @Override // com.huawei.openalliance.ad.hf
    public VideoView getVideoView() {
        return this.q;
    }

    public String getSoundSwtich() {
        VideoInfo videoInfo = this.l;
        return videoInfo != null ? videoInfo.getSoundSwitch() : "n";
    }

    public ImageView getPreviewImageView() {
        return this.p.m();
    }

    @Override // com.huawei.openalliance.ad.hf
    public hd getLinkedVideoControlBridge() {
        return this.i;
    }

    @Override // com.huawei.openalliance.ad.hf
    public gt getLinkedNativeAd() {
        return this.j;
    }

    @Override // com.huawei.openalliance.ad.linked.view.d
    protected int getHiddenAreaPercentageThreshhold() {
        VideoInfo videoInfo = this.l;
        return videoInfo != null ? Math.max(100 - videoInfo.getAutoStopPlayAreaRatio(), 0) : super.getHiddenAreaPercentageThreshhold();
    }

    public int getContinuePlayTime() {
        VideoInfo videoInfo = this.l;
        if (videoInfo == null) {
            return 0;
        }
        int b = videoInfo.b();
        ho.a("LinkedLandVideoView", "getContinuePlayTime %d", Integer.valueOf(b));
        return b;
    }

    @Override // com.huawei.openalliance.ad.linked.view.d
    protected int getAutoPlayAreaPercentageThresshold() {
        VideoInfo videoInfo = this.l;
        return videoInfo != null ? videoInfo.getAutoPlayAreaRatio() : super.getAutoPlayAreaPercentageThresshold();
    }

    public float getAspectRatio() {
        Float videoRatio;
        VideoInfo videoInfo = this.l;
        if (videoInfo == null || (videoRatio = videoInfo.getVideoRatio()) == null) {
            return 0.0f;
        }
        return videoRatio.floatValue();
    }

    @Override // com.huawei.openalliance.ad.hf
    public void g() {
        this.i.e();
    }

    public void f() {
        this.s = true;
        this.i.j();
    }

    public void e() {
        if (this.D == null || this.l == null || !this.c) {
            return;
        }
        this.i.a(this.l.getTimeBeforeVideoAutoPlay());
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void destroyView() {
        this.q.removeMediaErrorListener(this.C);
        this.q.removeMuteListener(this.v);
        this.q.destroyView();
    }

    @Override // com.huawei.openalliance.ad.linked.view.d
    protected void d() {
        super.d();
        this.q.setNeedPauseOnSurfaceDestory(true);
    }

    @Override // com.huawei.openalliance.ad.linked.view.d
    protected void c() {
        if (this.D.d()) {
            this.i.f(false);
            return;
        }
        ho.b("LinkedLandVideoView", "onViewShownBetweenFullAndPartial");
        this.i.a(getContinuePlayTime());
        this.i.f(true);
        j();
    }

    @Override // com.huawei.openalliance.ad.hf
    public void b(String str) {
        this.k.a(str);
    }

    @Override // com.huawei.openalliance.ad.linked.view.d
    protected void b() {
        ho.b("LinkedLandVideoView", "onViewPartialHidden");
        if (this.l != null) {
            this.i.f(false);
            this.i.c(false);
            this.i.d();
            this.i.j();
            this.i.a(getContinuePlayTime());
        }
    }

    public void a(String str) {
        ho.a("LinkedLandVideoView", "customToggleVideoMute %s", str);
        VideoInfo videoInfo = this.l;
        if (videoInfo != null) {
            videoInfo.e(str);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0023, code lost:
    
        if (r6 != false) goto L10;
     */
    @Override // com.huawei.openalliance.ad.hf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.huawei.openalliance.ad.inter.data.VideoInfo r5, boolean r6) {
        /*
            r4 = this;
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r6)
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "onCheckVideoResult: %s"
            java.lang.String r2 = "LinkedLandVideoView"
            com.huawei.openalliance.ad.ho.b(r2, r1, r0)
            if (r6 != 0) goto L23
            boolean r0 = r4.v()
            if (r0 == 0) goto L23
            boolean r0 = r4.a(r5)
            if (r0 == 0) goto L23
            java.lang.String r6 = "jssdk or api request type allow play http link video url when video mode is CACHE_MODE"
            com.huawei.openalliance.ad.ho.a(r2, r6)
            goto L25
        L23:
            if (r6 == 0) goto Lba
        L25:
            com.huawei.openalliance.ad.inter.data.VideoInfo r6 = r4.l
            if (r6 == 0) goto Lba
            if (r5 == 0) goto Lba
            java.lang.String r6 = r6.getVideoDownloadUrl()
            java.lang.String r0 = r5.getVideoDownloadUrl()
            boolean r6 = android.text.TextUtils.equals(r6, r0)
            if (r6 == 0) goto Lba
            r6 = 1
            r4.n = r6
            android.content.Context r6 = r4.getContext()
            android.content.Context r6 = r6.getApplicationContext()
            java.lang.String r6 = com.huawei.openalliance.ad.utils.ao.a(r6, r5)
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 == 0) goto L52
            java.lang.String r6 = r5.getVideoDownloadUrl()
        L52:
            boolean r0 = com.huawei.openalliance.ad.utils.cz.j(r6)
            if (r0 == 0) goto L6b
            java.lang.String r0 = r4.b(r5)
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L6b
            java.lang.String r6 = "play mode 3, cache while playing."
            com.huawei.openalliance.ad.ho.b(r2, r6)
            java.lang.String r6 = r4.b(r5)
        L6b:
            java.lang.String r0 = com.huawei.openalliance.ad.utils.cz.f(r6)
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "downloadurl: %s"
            com.huawei.openalliance.ad.ho.b(r2, r1, r0)
            com.huawei.openalliance.ad.hd r0 = r4.i
            r0.a(r6)
            boolean r6 = r4.c
            if (r6 == 0) goto Lc3
            com.huawei.openalliance.ad.hd r6 = r4.i
            int r0 = r4.getContinuePlayTime()
            r6.a(r0)
            boolean r6 = r4.o()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r6)
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "onCheckVideoResult - full shown, autoPlay: %s"
            com.huawei.openalliance.ad.ho.b(r2, r1, r0)
            com.huawei.openalliance.ad.hd r0 = r4.i
            r0.c(r6)
            long r0 = java.lang.System.currentTimeMillis()
            long r2 = r4.o
            int r5 = r5.getTimeBeforeVideoAutoPlay()
            long r5 = (long) r5
            long r0 = r0 - r2
            long r5 = r5 - r0
            r0 = 0
            int r2 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r2 >= 0) goto Lb4
            r5 = r0
        Lb4:
            com.huawei.openalliance.ad.hd r0 = r4.i
            r0.a(r5)
            goto Lc3
        Lba:
            com.huawei.openalliance.ad.linked.view.c$a r5 = r4.g
            if (r5 == 0) goto Lc3
            r6 = 0
            r0 = 0
            r5.a(r6, r0, r0, r0)
        Lc3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.linked.view.b.a(com.huawei.openalliance.ad.inter.data.VideoInfo, boolean):void");
    }

    @Override // com.huawei.openalliance.ad.hf
    public void a(ImageInfo imageInfo, Drawable drawable) {
        ImageInfo imageInfo2 = this.m;
        if (imageInfo2 == null || imageInfo == null || !TextUtils.equals(imageInfo2.getUrl(), imageInfo.getUrl())) {
            return;
        }
        this.i.a(drawable);
    }

    public void a(int i) {
        MediaErrorListener mediaErrorListener = this.C;
        if (mediaErrorListener != null) {
            mediaErrorListener.onError(null, this.t, i, -1);
        }
        gw gwVar = this.k;
        if (gwVar != null) {
            gwVar.a(this.t, i);
        }
    }

    @Override // com.huawei.openalliance.ad.linked.view.d
    protected void a() {
        if (this.D.d()) {
            this.i.v();
            return;
        }
        this.o = System.currentTimeMillis();
        this.i.f(true);
        j();
        ho.b("LinkedLandVideoView", "onViewFullShown hashCheckSuccess: %s hasPaused: %s", Boolean.valueOf(this.n), Boolean.valueOf(this.s));
        if (!this.n || this.s) {
            return;
        }
        boolean o = o();
        ho.b("LinkedLandVideoView", "onViewFullShown autoplay: %s", Boolean.valueOf(o));
        this.i.c(o);
        this.i.a(getContinuePlayTime());
        this.i.a(this.l.getTimeBeforeVideoAutoPlay());
    }

    private boolean v() {
        int t;
        gs gsVar = this.j;
        return (gsVar == null || gsVar.c() == null || ((t = this.j.c().t()) != 1 && t != 3)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        a aVar = this.f;
        if (aVar != null) {
            aVar.c();
        }
        c.a aVar2 = this.g;
        if (aVar2 != null) {
            aVar2.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        a aVar = this.f;
        if (aVar != null) {
            aVar.d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        a aVar = this.f;
        if (aVar != null) {
            aVar.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        a aVar = this.f;
        if (aVar != null) {
            aVar.a();
        }
    }

    private boolean q() {
        gs gsVar = this.j;
        return (gsVar == null || gsVar.m() == null) ? false : true;
    }

    private boolean o() {
        if (q() && !os.G(this.j.g())) {
            int autoPlayNetwork = this.j.m().getAutoPlayNetwork();
            ho.b("LinkedLandVideoView", "videoCfg landPage, can auto play net: %s.", Integer.valueOf(autoPlayNetwork));
            if (autoPlayNetwork == 2) {
                return false;
            }
            if (autoPlayNetwork == 1 || (autoPlayNetwork == 0 && bv.c(this.F))) {
                return true;
            }
        }
        VideoInfo videoInfo = this.l;
        return videoInfo != null && TextUtils.equals(videoInfo.getVideoAutoPlay(), "y");
    }

    private boolean n() {
        VideoInfo videoInfo = this.l;
        return videoInfo != null && TextUtils.equals(videoInfo.getSoundSwitch(), "y");
    }

    private void m() {
        this.n = false;
        this.i.g(true);
    }

    private void l() {
        gs gsVar = this.j;
        if (gsVar == null) {
            return;
        }
        VideoInfo f = gsVar.f();
        this.l = f;
        if (f == null) {
            this.i.a();
            return;
        }
        if (this.j.j() == 1) {
            this.l.a(this.j.h());
        }
        a(this.l.getSoundSwitch());
        Float videoRatio = this.l.getVideoRatio();
        if (videoRatio == null) {
            videoRatio = Float.valueOf(1.7777778f);
        }
        if (videoRatio.floatValue() < 1.0f) {
            videoRatio = Float.valueOf(1.7777778f);
        }
        setRatio(videoRatio);
        this.i.a(new gx(this.F, this.q, this.l, this.j));
        this.i.d(!n());
        this.i.a(getContinuePlayTime());
        this.i.b(this.l.getVideoDuration());
        this.i.i(true);
        int videoFileSize = this.l.getVideoFileSize();
        this.i.d(videoFileSize);
        this.i.a(Boolean.TRUE.toString().equals(this.l.i()));
        this.p.setNonWifiAlertMsg(videoFileSize > 0 ? getResources().getString(R.string._2130851052_res_0x7f0234ec, ae.a(getContext(), this.l.getVideoFileSize())) : getResources().getString(R.string._2130851053_res_0x7f0234ed));
        this.k.a(this.l);
    }

    private void k() {
        ImageInfo i;
        gs gsVar = this.j;
        if (gsVar == null || (i = gsVar.i()) == null) {
            return;
        }
        this.m = i;
        if (i != null) {
            a(i);
        }
    }

    private void j() {
        ho.b("LinkedLandVideoView", "setInnerListener");
        this.q.addMediaErrorListener(this.C);
        this.q.addMuteListener(this.v);
        this.i.d(!n());
    }

    private String b(VideoInfo videoInfo) {
        if (videoInfo != null && cz.j(videoInfo.g()) && ao.a(videoInfo)) {
            return ao.a(this.F, videoInfo, new ij(this));
        }
        return null;
    }

    private boolean a(VideoInfo videoInfo) {
        return videoInfo != null && cz.j(videoInfo.getVideoDownloadUrl());
    }

    private void a(ImageInfo imageInfo) {
        if (imageInfo.getHeight() > 0) {
            setRatio(Float.valueOf((imageInfo.getWidth() * 1.0f) / imageInfo.getHeight()));
        }
        this.k.a(imageInfo);
    }

    private void a(Context context) {
        String str;
        try {
            ho.a("LinkedLandVideoView", "init nativeVideoView");
            this.F = context;
            this.r = new jg("LinkedLandVideoView");
            this.k = new gv(context, this);
            this.f7169a = LayoutInflater.from(context).inflate(R.layout.hiad_linked_native_video_view, this);
            this.q = (VideoView) findViewById(R.id.hiad_id_video_view);
            this.p = (LinkedNativeViewControlPanel) findViewById(R.id.hiad_link_native_video_ctrl_panel);
            this.E = (LinkedAppDetailView) findViewById(R.id.hiad_link_app_detail);
            this.q.setStandalone(true);
            this.q.setScreenOnWhilePlaying(true);
            this.q.setAutoScaleResizeLayoutOnVideoSizeChange(false);
            hd hdVar = new hd(context, this.q, this.p);
            this.i = hdVar;
            hdVar.a(this.D);
            this.i.a(this.w);
            this.q.addMediaStateListener(this.A);
            this.q.addReportVideoTimeListenersSet(this.z);
            this.q.addMediaBufferListener(this.B);
            this.q.addMediaErrorListener(this.C);
            this.q.addMuteListener(this.v);
            this.q.addMediaInfoListener(this.u);
        } catch (RuntimeException unused) {
            str = "init RuntimeException";
            ho.c("LinkedLandVideoView", str);
        } catch (Exception unused2) {
            str = "init error";
            ho.c("LinkedLandVideoView", str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, boolean z) {
        VideoInfo videoInfo = this.l;
        if (videoInfo != null) {
            videoInfo.e(z ? 0 : i);
        }
        this.r.c();
        if (this.h) {
            this.h = false;
            if (z) {
                this.k.b(this.x, System.currentTimeMillis(), this.y, i);
            } else {
                this.k.c(this.x, System.currentTimeMillis(), this.y, i);
            }
        }
    }

    public b(Context context) {
        super(context);
        this.h = false;
        this.n = false;
        this.s = false;
        this.t = -1;
        this.u = new com.huawei.openalliance.ad.media.listener.a() { // from class: com.huawei.openalliance.ad.linked.view.b.1
            @Override // com.huawei.openalliance.ad.media.listener.a
            public void b(int i) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.a
            public void a(int i) {
                b.this.i.c(i);
            }
        };
        this.v = new MuteListener() { // from class: com.huawei.openalliance.ad.linked.view.b.2
            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onUnmute() {
                ho.b("LinkedLandVideoView", "onUnmute");
                if (b.this.l != null) {
                    b.this.l.e("y");
                    b.this.k.a(false);
                }
                b.this.i.e(false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onMute() {
                ho.b("LinkedLandVideoView", "onMute");
                if (b.this.l != null) {
                    b.this.l.e("n");
                    b.this.k.a(true);
                }
                b.this.i.e(true);
                if (b.this.f != null) {
                    b.this.f.a(true);
                }
            }
        };
        this.w = new hd.a() { // from class: com.huawei.openalliance.ad.linked.view.b.3
            @Override // com.huawei.openalliance.ad.hd.a
            public void d() {
                if (b.this.k != null) {
                    b.this.k.c();
                }
            }

            @Override // com.huawei.openalliance.ad.hd.a
            public void c() {
                if (b.this.k != null) {
                    m.h(new Runnable() { // from class: com.huawei.openalliance.ad.linked.view.b.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            b.this.k.a(b.this.x, System.currentTimeMillis(), b.this.y, b.this.q.getCurrentPosition());
                        }
                    });
                }
            }

            @Override // com.huawei.openalliance.ad.hd.a
            public void b() {
                if (b.this.k != null) {
                    b.this.k.b();
                }
            }

            @Override // com.huawei.openalliance.ad.hd.a
            public void a(boolean z) {
                ho.b("LinkedLandVideoView", "doRealPlay, auto:" + z);
                b.this.r.a();
            }

            @Override // com.huawei.openalliance.ad.hd.a
            public void a() {
                if (b.this.k != null) {
                    b.this.k.a();
                }
            }
        };
        this.z = new ReportVideoTimeListener() { // from class: com.huawei.openalliance.ad.linked.view.b.4
            @Override // com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener
            public void reportVideoTime(long j) {
                if (ho.a()) {
                    ho.a("LinkedLandVideoView", "reportVideoTime: %s", Long.valueOf(j));
                }
                if (b.this.k != null) {
                    b.this.k.b(j);
                }
            }
        };
        this.A = new MediaStateListener() { // from class: com.huawei.openalliance.ad.linked.view.b.5
            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onProgress(int i, int i2) {
                b.this.t = i2;
                if (b.this.k != null) {
                    b.this.k.a(i2, b.this.l == null ? 0L : b.this.l.getVideoDuration());
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i) {
                b.this.a(i, false);
                b.this.t();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i) {
                b.this.G = true;
                if (b.this.h) {
                    return;
                }
                if (ho.a()) {
                    ho.a("LinkedLandVideoView", "onMediaStart: %d", Integer.valueOf(i));
                }
                b.this.h = true;
                b.this.y = i;
                b.this.x = System.currentTimeMillis();
                b.this.r();
                gw gwVar = b.this.k;
                if (i > 0) {
                    gwVar.i();
                } else {
                    gwVar.h();
                    b.this.k.a(b.this.r.e(), b.this.r.d(), b.this.x);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i) {
                b.this.a(i, false);
                b.this.s();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i) {
                b.this.a(i, true);
                b.this.u();
                if (b.this.k != null) {
                    long j = i;
                    b.this.k.a(j, j);
                }
            }
        };
        this.B = new MediaBufferListener() { // from class: com.huawei.openalliance.ad.linked.view.b.6
            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferUpdate(int i) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingEnd() {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingStart() {
                if (ho.a()) {
                    ho.a("LinkedLandVideoView", "onBufferingStart");
                }
                b.this.r.b();
            }
        };
        this.C = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.linked.view.b.7
            @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
            public void onError(MediaPlayerAgent mediaPlayerAgent, int i, int i2, int i3) {
                b.this.a(i, false);
                if (b.this.g != null) {
                    b.this.g.a(mediaPlayerAgent, i, i2, i3);
                }
                if (b.this.d || bv.e(b.this.getContext())) {
                    return;
                }
                b.this.i.i();
            }
        };
        this.D = new hc();
        this.G = false;
        a(context);
    }
}
