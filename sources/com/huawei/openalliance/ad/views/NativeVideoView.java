package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.huawei.health.R;
import com.huawei.openalliance.ad.gz;
import com.huawei.openalliance.ad.ha;
import com.huawei.openalliance.ad.hb;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.im;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.inter.data.ImageInfo;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.jg;
import com.huawei.openalliance.ad.lm;
import com.huawei.openalliance.ad.lo;
import com.huawei.openalliance.ad.lz;
import com.huawei.openalliance.ad.media.MediaPlayerAgent;
import com.huawei.openalliance.ad.media.MediaState;
import com.huawei.openalliance.ad.media.listener.MediaBufferListener;
import com.huawei.openalliance.ad.media.listener.MediaErrorListener;
import com.huawei.openalliance.ad.media.listener.MediaStateListener;
import com.huawei.openalliance.ad.media.listener.MuteListener;
import com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener;
import com.huawei.openalliance.ad.mp;
import com.huawei.openalliance.ad.mq;
import com.huawei.openalliance.ad.nf;
import com.huawei.openalliance.ad.nx;
import com.huawei.openalliance.ad.os;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bv;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.views.ak;
import com.huawei.openalliance.ad.views.interfaces.INativeVideoView;
import com.huawei.openalliance.ad.views.interfaces.IPPSNativeView;
import com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle;
import com.huawei.operation.utils.Constants;
import java.util.List;

/* loaded from: classes5.dex */
public class NativeVideoView extends NativeMediaView implements lm, INativeVideoView, IViewLifeCycle {
    private final ReportVideoTimeListener A;
    private MediaStateListener B;
    private MediaErrorListener C;
    private com.huawei.openalliance.ad.media.listener.a D;
    private MuteListener E;
    private ak.a F;
    private lz g;
    private VideoEventListener h;
    private boolean i;
    private ak j;
    private nx k;
    private VideoInfo l;
    private ImageInfo m;
    private boolean n;
    private long o;
    private NativeVideoControlPanel p;
    private VideoView q;
    private IPPSNativeView r;
    private long s;
    private long t;
    private boolean u;
    private jg v;
    private im w;
    private boolean x;
    private boolean y;
    private MediaBufferListener z;

    /* loaded from: classes9.dex */
    public interface VideoEventListener {
        void onControlPanelHide(boolean z, int i);

        void onControlPanelShow(boolean z, int i);

        void onVideoComplete();

        void onVideoPause();

        void onVideoStart();

        void onVideoStop();
    }

    @Override // com.huawei.openalliance.ad.lm
    public View getOpenMeasureView() {
        return this;
    }

    @Deprecated
    public void setNotShowDataUsageAlert(boolean z) {
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    @Deprecated
    public void showFullScreenSwitchButton(boolean z) {
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public void updateStartShowTime(long j) {
        this.k.a(j);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public void updateContent(String str) {
        this.k.a(str);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public void stop() {
        this.j.b();
    }

    public void setVideoEventListener(VideoEventListener videoEventListener) {
        this.h = videoEventListener;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public void setPpsNativeView(IPPSNativeView iPPSNativeView) {
        this.r = iPPSNativeView;
    }

    @Override // com.huawei.openalliance.ad.views.NativeMediaView, com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public void setNativeAd(INativeAd iNativeAd) {
        StringBuilder sb = new StringBuilder("setNativeAd ");
        sb.append(iNativeAd != null ? iNativeAd.getContentId() : Constants.NULL);
        ho.b("NativeVideoView", sb.toString());
        MediaState currentState = this.q.getCurrentState();
        if (this.c == iNativeAd && currentState.isNotState(MediaState.State.IDLE) && currentState.isNotState(MediaState.State.ERROR)) {
            ho.b("NativeVideoView", "setNativeAd - has the same ad");
            return;
        }
        super.setNativeAd(iNativeAd);
        j();
        this.k.a(this.c);
        if (this.c != null) {
            i();
            g();
        } else {
            this.j.d(true);
            this.l = null;
        }
        if (!m() || k()) {
            return;
        }
        ho.b("NativeVideoView", "video auto play without sound.");
        this.u = true;
    }

    public void setCoverClickListener(View.OnClickListener onClickListener) {
        this.j.a(onClickListener);
    }

    public void setAutoPlayOnFirstShow(boolean z) {
        this.j.a(z);
    }

    public void setAudioFocusType(int i) {
        this.q.setAudioFocusType(i);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void resumeView() {
        this.j.f();
        f();
        this.f7815a = false;
        this.f.onGlobalLayout();
        this.q.setNeedPauseOnSurfaceDestory(true);
    }

    public void play() {
        this.j.b(false);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void pauseView() {
        this.j.e();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public void pause() {
        this.j.g();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public void onPreviewImageLoaded(ImageInfo imageInfo, Drawable drawable) {
        ImageInfo imageInfo2 = this.m;
        if (imageInfo2 == null || imageInfo == null || !TextUtils.equals(imageInfo2.getUrl(), imageInfo.getUrl())) {
            return;
        }
        this.j.a(drawable);
    }

    @Override // com.huawei.openalliance.ad.views.NativeMediaView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.g.b();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public void onCheckVideoHashResult(VideoInfo videoInfo, boolean z) {
        VideoInfo videoInfo2;
        ho.b("NativeVideoView", "onCheckVideoHashResult sucess: %s", Boolean.valueOf(z));
        if (!z || (videoInfo2 = this.l) == null || videoInfo == null || !TextUtils.equals(videoInfo2.getVideoDownloadUrl(), videoInfo.getVideoDownloadUrl())) {
            return;
        }
        this.n = true;
        String a2 = ao.a(getContext().getApplicationContext(), videoInfo);
        if (TextUtils.isEmpty(a2)) {
            a2 = videoInfo.getVideoDownloadUrl();
        }
        if (cz.j(a2) && ao.a(videoInfo)) {
            String a3 = ao.a(getContext(), videoInfo, this.w);
            ho.a("NativeVideoView", "proxyUrl: %s", a3);
            if (!TextUtils.isEmpty(a3)) {
                a2 = a3;
            }
        }
        this.j.a(a2);
        if (this.f7815a) {
            this.j.a(getContinuePlayTime());
            boolean m = m();
            ho.b("NativeVideoView", "onCheckVideoHashResult - has full shown, autoPlay: %s", Boolean.valueOf(m));
            this.j.c(m);
            if (this.l.isBackFromFullScreen()) {
                t();
            } else if (u()) {
                long timeBeforeVideoAutoPlay = videoInfo.getTimeBeforeVideoAutoPlay() - (System.currentTimeMillis() - this.o);
                if (timeBeforeVideoAutoPlay < 0) {
                    timeBeforeVideoAutoPlay = 0;
                }
                this.j.a(timeBeforeVideoAutoPlay);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.views.NativeMediaView, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public void notifyStreamError(int i) {
        MediaErrorListener mediaErrorListener = this.C;
        if (mediaErrorListener != null && i != -3) {
            mediaErrorListener.onError(null, this.e, i, -1);
        }
        nx nxVar = this.k;
        if (nxVar != null) {
            nxVar.a(this.e, i);
        }
        if (this.c == null || this.x) {
            return;
        }
        this.x = true;
        ho.b("NativeVideoView", "reInject videoInfo.");
        VideoInfo videoInfo = this.c.getVideoInfo();
        this.l = videoInfo;
        videoInfo.d(2);
        h();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public VideoView getVideoView() {
        return this.q;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public ImageView getPreviewImageView() {
        return this.p.f();
    }

    @Override // com.huawei.openalliance.ad.views.NativeMediaView
    public int getPlayedTime() {
        return this.e;
    }

    @Override // com.huawei.openalliance.ad.views.NativeMediaView
    public int getPlayedProgress() {
        VideoInfo videoInfo = this.l;
        if (videoInfo != null && videoInfo.getVideoDuration() > 0) {
            this.d = (int) ((getPlayedTime() / this.l.getVideoDuration()) * 100.0f);
        }
        return this.d;
    }

    @Override // com.huawei.openalliance.ad.views.NativeMediaView
    protected int getHiddenAreaPercentageThreshhold() {
        VideoInfo videoInfo = this.l;
        return videoInfo != null ? Math.max(100 - videoInfo.getAutoStopPlayAreaRatio(), 0) : super.getHiddenAreaPercentageThreshhold();
    }

    @Override // com.huawei.openalliance.ad.views.NativeMediaView
    protected int getAutoPlayAreaPercentageThresshold() {
        VideoInfo videoInfo = this.l;
        return videoInfo != null ? videoInfo.getAutoPlayAreaRatio() : super.getAutoPlayAreaPercentageThresshold();
    }

    @Override // com.huawei.openalliance.ad.views.NativeMediaView
    protected void e() {
        ho.b("NativeVideoView", "onViewShownBetweenFullAndPartial");
        this.j.f(true);
        f();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void destroyView() {
        this.q.destroyView();
    }

    @Override // com.huawei.openalliance.ad.views.NativeMediaView
    protected void d() {
        this.q.removeMediaErrorListener(this.C);
        this.q.removeMuteListener(this.E);
        ho.b("NativeVideoView", "onViewPartialHidden");
        this.j.f(false);
        this.j.c(false);
        this.y = true;
        this.j.a();
        this.j.b();
        VideoInfo videoInfo = this.l;
        if (videoInfo != null) {
            videoInfo.b(true);
            this.l.c(false);
        }
    }

    @Override // com.huawei.openalliance.ad.views.NativeMediaView
    protected void c() {
        this.o = System.currentTimeMillis();
        this.j.f(true);
        a(this.l);
        f();
        ho.b("NativeVideoView", "onViewFullShown hashCheckSuccess: %s", Boolean.valueOf(this.n));
        if (this.n) {
            boolean m = m();
            this.y = false;
            ho.b("NativeVideoView", "onViewFullShown autoplay: %s", Boolean.valueOf(m));
            this.j.c(m);
            this.j.a(getContinuePlayTime());
            VideoInfo videoInfo = this.l;
            if (videoInfo != null && videoInfo.isBackFromFullScreen()) {
                t();
            } else if (u()) {
                this.j.a(this.l.getTimeBeforeVideoAutoPlay());
            }
        }
    }

    @Override // com.huawei.openalliance.ad.views.NativeMediaView
    protected void b() {
        super.b();
        this.q.setNeedPauseOnSurfaceDestory(true);
    }

    public void autoPlay() {
        if (this.l == null) {
            return;
        }
        this.j.a(true);
        this.j.a(this.l.getTimeBeforeVideoAutoPlay());
    }

    public void a(lz lzVar, com.huawei.openalliance.ad.inter.data.e eVar) {
        this.g = lzVar;
        a(eVar);
    }

    private void v() {
        ha.a((gz) null);
        hb.a(getContext()).b();
    }

    private boolean u() {
        if (this.l == null || !bv.e(getContext()) || !m()) {
            return false;
        }
        if (n() && !os.G(this.c.getCtrlSwitchs())) {
            int autoPlayNetwork = this.c.getVideoConfiguration().getAutoPlayNetwork();
            if (autoPlayNetwork == 2) {
                return false;
            }
            if (autoPlayNetwork == 1 || (autoPlayNetwork == 0 && bv.c(getContext()))) {
                return true;
            }
            if (autoPlayNetwork == 0 && !bv.c(getContext())) {
                return false;
            }
        }
        if (this.l.getAutoPlayNetwork() == 1) {
            return true;
        }
        return this.l.getAutoPlayNetwork() == 0 && bv.c(getContext());
    }

    private void t() {
        this.l.c(false);
        if (this.l.c()) {
            this.j.c();
        } else {
            this.j.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        VideoEventListener videoEventListener = this.h;
        if (videoEventListener != null) {
            videoEventListener.onVideoComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        VideoEventListener videoEventListener = this.h;
        if (videoEventListener != null) {
            videoEventListener.onVideoPause();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        VideoEventListener videoEventListener = this.h;
        if (videoEventListener != null) {
            videoEventListener.onVideoStop();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        VideoEventListener videoEventListener = this.h;
        if (videoEventListener != null) {
            videoEventListener.onVideoStart();
        }
    }

    private boolean n() {
        return (this.c == null || this.c.getVideoConfiguration() == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean m() {
        VideoInfo videoInfo = this.l;
        if (videoInfo == null) {
            return false;
        }
        if (videoInfo.b() >= this.l.getVideoDuration()) {
            this.l.e(0);
            ho.b("NativeVideoView", "play progress bigger than video duration, skip autoPlay.");
            return false;
        }
        if (n() && !os.G(this.c.getCtrlSwitchs())) {
            int autoPlayNetwork = this.c.getVideoConfiguration().getAutoPlayNetwork();
            ho.b("NativeVideoView", "videoCfg, auto play net: %s.", Integer.valueOf(autoPlayNetwork));
            if (autoPlayNetwork == 2) {
                return false;
            }
            if (autoPlayNetwork == 1 || (autoPlayNetwork == 0 && bv.c(getContext()))) {
                return true;
            }
            if (autoPlayNetwork == 0 && !bv.c(getContext())) {
                return false;
            }
        }
        return TextUtils.equals(this.l.getVideoAutoPlay(), "y");
    }

    private boolean l() {
        ak akVar = this.j;
        return akVar != null && akVar.h();
    }

    private boolean k() {
        if (!n() || os.H(this.c.getCtrlSwitchs()) || l()) {
            VideoInfo videoInfo = this.l;
            return videoInfo != null && TextUtils.equals(videoInfo.getSoundSwitch(), "y");
        }
        boolean isMute = this.c.getVideoConfiguration().isMute();
        ho.b("NativeVideoView", "videoCfg, isMute: %s", Boolean.valueOf(isMute));
        return !isMute;
    }

    private void j() {
        this.n = false;
        this.j.g(true);
    }

    private void i() {
        List<ImageInfo> imageInfos;
        if (this.c == null || (imageInfos = this.c.getImageInfos()) == null || imageInfos.size() <= 0) {
            return;
        }
        ImageInfo imageInfo = imageInfos.get(0);
        this.m = imageInfo;
        if (imageInfo != null) {
            if (cz.j(imageInfo.getUrl())) {
                ho.b("NativeVideoView", "don't load preview image with http url");
                return;
            }
            if (this.m.getHeight() > 0) {
                setRatio(Float.valueOf((this.m.getWidth() * 1.0f) / this.m.getHeight()));
            }
            this.k.a(this.m);
        }
    }

    private void h() {
        VideoInfo videoInfo = this.l;
        if (videoInfo == null) {
            return;
        }
        this.j.a(videoInfo);
        Float videoRatio = this.l.getVideoRatio();
        if (videoRatio == null) {
            videoRatio = Float.valueOf(1.7777778f);
        }
        setRatio(videoRatio);
        this.j.d(!k());
        this.j.a(getContinuePlayTime());
        this.j.b(this.l.getVideoDuration());
        this.j.c(getAutoPlayNetForVideoCtrlBridge());
        this.k.a(this.l);
        this.p.setNonWifiAlertMsg(this.l.getVideoFileSize() > 0 ? getResources().getString(R.string._2130851052_res_0x7f0234ec, com.huawei.openalliance.ad.utils.ae.a(getContext(), this.l.getVideoFileSize())) : getResources().getString(R.string._2130851053_res_0x7f0234ed));
    }

    private String getTAG() {
        return "NativeVideoView_" + hashCode();
    }

    private int getContinuePlayTime() {
        VideoInfo videoInfo = this.l;
        if (videoInfo == null) {
            ho.a("NativeVideoView", "getContinuePlayTime other");
            return 0;
        }
        int b = videoInfo.b();
        if (b >= 5000) {
            return b;
        }
        return 0;
    }

    private int getAutoPlayNetForVideoCtrlBridge() {
        if (this.c == null) {
            return 0;
        }
        return (this.c.getVideoConfiguration() == null || (os.G(this.c.getCtrlSwitchs()) && this.l != null)) ? this.l.getAutoPlayNetwork() : this.c.getVideoConfiguration().getAutoPlayNetwork();
    }

    private void g() {
        if (this.c == null) {
            return;
        }
        this.l = this.c.getVideoInfo();
        h();
    }

    private void f() {
        this.q.addMediaErrorListener(this.C);
        this.q.addMuteListener(this.E);
        this.j.d(!k());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z, int i) {
        VideoEventListener videoEventListener = this.h;
        if (videoEventListener != null) {
            videoEventListener.onControlPanelHide(z, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, int i) {
        VideoEventListener videoEventListener = this.h;
        if (videoEventListener != null) {
            videoEventListener.onControlPanelShow(z, i);
        }
    }

    private void a(com.huawei.openalliance.ad.inter.data.e eVar) {
        if (eVar.getVideoInfo() != null) {
            this.g.a(mq.a(0.0f, u(), mp.STANDALONE));
        }
    }

    private void a(VideoInfo videoInfo) {
        gz a2 = ha.a();
        if (a2 == null || videoInfo == null) {
            return;
        }
        int c = a2.c();
        videoInfo.e(c);
        ho.b("NativeVideoView", "obtain progress from linked view " + c);
        v();
    }

    private void a(Context context) {
        this.k = new nf(context, this);
        LayoutInflater.from(context).inflate(R.layout.hiad_native_video_view, this);
        this.q = (VideoView) findViewById(R.id.hiad_id_video_view);
        this.p = (NativeVideoControlPanel) findViewById(R.id.hiad_native_video_ctrl_panel);
        this.q.setStandalone(false);
        this.q.setScreenOnWhilePlaying(true);
        this.q.setAutoScaleResizeLayoutOnVideoSizeChange(false);
        ak akVar = new ak(this.q, this.p);
        this.j = akVar;
        akVar.a(this.F);
        this.q.addMediaStateListener(this.B);
        this.q.addMediaBufferListener(this.z);
        this.q.addMediaErrorListener(this.C);
        this.q.addMuteListener(this.E);
        this.q.addMediaInfoListener(this.D);
        this.q.addReportVideoTimeListenersSet(this.A);
        this.w = new im(this);
        this.v = new jg(getTAG());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, boolean z) {
        VideoInfo videoInfo = this.l;
        if (videoInfo != null) {
            videoInfo.e(z ? 0 : i);
        }
        this.v.c();
        if (this.i) {
            this.i = false;
            if (!z) {
                this.k.c(this.s, System.currentTimeMillis(), this.t, i);
                this.g.k();
            } else {
                this.k.b(this.s, System.currentTimeMillis(), this.t, i);
                this.k.a(this.r, this.c);
                this.g.g();
            }
        }
    }

    public NativeVideoView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.g = new lo();
        this.i = false;
        this.n = false;
        this.x = false;
        this.y = false;
        this.z = new MediaBufferListener() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.1
            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferUpdate(int i3) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingStart() {
                if (ho.a()) {
                    ho.a("NativeVideoView", "onBufferingStart");
                }
                NativeVideoView.this.v.b();
                NativeVideoView.this.g.h();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingEnd() {
                NativeVideoView.this.g.i();
            }
        };
        this.A = new ReportVideoTimeListener() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.2
            @Override // com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener
            public void reportVideoTime(long j) {
                if (ho.a()) {
                    ho.a("NativeVideoView", "reportVideoTime: %s", Long.valueOf(j));
                }
                if (NativeVideoView.this.k != null) {
                    NativeVideoView.this.k.b(j);
                }
            }
        };
        this.B = new MediaStateListener() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.3
            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onProgress(int i3, int i4) {
                NativeVideoView.this.e = i4;
                if (NativeVideoView.this.i) {
                    NativeVideoView.this.g.a(i3);
                    NativeVideoView.this.k.a(i3, i4, NativeVideoView.this.t, NativeVideoView.this.r, NativeVideoView.this.c);
                    NativeVideoView.this.k.a(i4, NativeVideoView.this.l == null ? 0 : NativeVideoView.this.l.getVideoDuration());
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i3) {
                NativeVideoView.this.a(i3, false);
                NativeVideoView.this.q();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i3) {
                if (ho.a()) {
                    ho.a("NativeVideoView", "onMediaStart: %s", Integer.valueOf(i3));
                }
                if (NativeVideoView.this.i) {
                    return;
                }
                NativeVideoView.this.i = true;
                NativeVideoView.this.t = i3;
                NativeVideoView.this.s = System.currentTimeMillis();
                NativeVideoView.this.o();
                lz lzVar = NativeVideoView.this.g;
                if (i3 <= 0) {
                    if (lzVar != null && NativeVideoView.this.l != null) {
                        NativeVideoView.this.g.a(NativeVideoView.this.l.getVideoDuration(), !"y".equals(NativeVideoView.this.l.getSoundSwitch()));
                    }
                    NativeVideoView.this.k.h();
                    NativeVideoView.this.k.a(NativeVideoView.this.v.e(), NativeVideoView.this.v.d(), NativeVideoView.this.s);
                    return;
                }
                lzVar.l();
                NativeVideoView.this.k.i();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i3) {
                NativeVideoView.this.a(i3, false);
                NativeVideoView.this.r();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i3) {
                if (NativeVideoView.this.l != null) {
                    NativeVideoView.this.l.b(true);
                }
                NativeVideoView.this.a(i3, true);
                NativeVideoView.this.s();
                if (NativeVideoView.this.k != null) {
                    long j = i3;
                    NativeVideoView.this.k.a(j, j);
                }
            }
        };
        this.C = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.4
            @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
            public void onError(MediaPlayerAgent mediaPlayerAgent, int i3, int i4, int i5) {
                NativeVideoView.this.a(i3, false);
                if (NativeVideoView.this.b || bv.e(NativeVideoView.this.getContext())) {
                    return;
                }
                Toast makeText = Toast.makeText(NativeVideoView.this.getContext(), R.string._2130851113_res_0x7f023529, 0);
                makeText.setGravity(17, 0, 0);
                makeText.show();
            }
        };
        this.D = new com.huawei.openalliance.ad.media.listener.a() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.5
            @Override // com.huawei.openalliance.ad.media.listener.a
            public void b(int i3) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.a
            public void a(int i3) {
                NativeVideoView.this.j.b(i3);
            }
        };
        this.E = new MuteListener() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.6
            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onUnmute() {
                ho.b("NativeVideoView", "onUnmute");
                if (NativeVideoView.this.l != null) {
                    NativeVideoView.this.u = false;
                    NativeVideoView.this.l.e("y");
                    NativeVideoView.this.k.a(false);
                    NativeVideoView.this.g.b(1.0f);
                }
                NativeVideoView.this.j.e(false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onMute() {
                ho.b("NativeVideoView", "onMute");
                if (NativeVideoView.this.l != null) {
                    NativeVideoView.this.l.e("n");
                    if (!NativeVideoView.this.u && NativeVideoView.this.i) {
                        NativeVideoView.this.k.a(true);
                    } else {
                        NativeVideoView.this.u = false;
                    }
                    NativeVideoView.this.g.b(0.0f);
                }
                NativeVideoView.this.j.e(true);
            }
        };
        this.F = new ak.a() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.7
            @Override // com.huawei.openalliance.ad.views.ak.a
            public boolean b() {
                return NativeVideoView.this.m() && !NativeVideoView.this.y;
            }

            @Override // com.huawei.openalliance.ad.views.ak.a
            public void b(boolean z, int i3) {
                NativeVideoView.this.b(z, i3);
            }

            @Override // com.huawei.openalliance.ad.views.ak.a
            public void b(boolean z) {
                ho.b("NativeVideoView", "doRealPlay, auto:" + z);
                NativeVideoView.this.v.a();
            }

            @Override // com.huawei.openalliance.ad.views.ak.a
            public void a(boolean z, int i3) {
                NativeVideoView.this.a(z, i3);
            }

            @Override // com.huawei.openalliance.ad.views.ak.a
            public void a(boolean z) {
                if (NativeVideoView.this.l != null) {
                    NativeVideoView.this.l.b(!z);
                }
            }

            @Override // com.huawei.openalliance.ad.views.ak.a
            public void a() {
                if (NativeVideoView.this.r != null) {
                    NativeVideoView.this.r.a(5);
                }
            }
        };
        a(context);
    }

    public NativeVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = new lo();
        this.i = false;
        this.n = false;
        this.x = false;
        this.y = false;
        this.z = new MediaBufferListener() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.1
            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferUpdate(int i3) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingStart() {
                if (ho.a()) {
                    ho.a("NativeVideoView", "onBufferingStart");
                }
                NativeVideoView.this.v.b();
                NativeVideoView.this.g.h();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingEnd() {
                NativeVideoView.this.g.i();
            }
        };
        this.A = new ReportVideoTimeListener() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.2
            @Override // com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener
            public void reportVideoTime(long j) {
                if (ho.a()) {
                    ho.a("NativeVideoView", "reportVideoTime: %s", Long.valueOf(j));
                }
                if (NativeVideoView.this.k != null) {
                    NativeVideoView.this.k.b(j);
                }
            }
        };
        this.B = new MediaStateListener() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.3
            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onProgress(int i3, int i4) {
                NativeVideoView.this.e = i4;
                if (NativeVideoView.this.i) {
                    NativeVideoView.this.g.a(i3);
                    NativeVideoView.this.k.a(i3, i4, NativeVideoView.this.t, NativeVideoView.this.r, NativeVideoView.this.c);
                    NativeVideoView.this.k.a(i4, NativeVideoView.this.l == null ? 0 : NativeVideoView.this.l.getVideoDuration());
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i3) {
                NativeVideoView.this.a(i3, false);
                NativeVideoView.this.q();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i3) {
                if (ho.a()) {
                    ho.a("NativeVideoView", "onMediaStart: %s", Integer.valueOf(i3));
                }
                if (NativeVideoView.this.i) {
                    return;
                }
                NativeVideoView.this.i = true;
                NativeVideoView.this.t = i3;
                NativeVideoView.this.s = System.currentTimeMillis();
                NativeVideoView.this.o();
                lz lzVar = NativeVideoView.this.g;
                if (i3 <= 0) {
                    if (lzVar != null && NativeVideoView.this.l != null) {
                        NativeVideoView.this.g.a(NativeVideoView.this.l.getVideoDuration(), !"y".equals(NativeVideoView.this.l.getSoundSwitch()));
                    }
                    NativeVideoView.this.k.h();
                    NativeVideoView.this.k.a(NativeVideoView.this.v.e(), NativeVideoView.this.v.d(), NativeVideoView.this.s);
                    return;
                }
                lzVar.l();
                NativeVideoView.this.k.i();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i3) {
                NativeVideoView.this.a(i3, false);
                NativeVideoView.this.r();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i3) {
                if (NativeVideoView.this.l != null) {
                    NativeVideoView.this.l.b(true);
                }
                NativeVideoView.this.a(i3, true);
                NativeVideoView.this.s();
                if (NativeVideoView.this.k != null) {
                    long j = i3;
                    NativeVideoView.this.k.a(j, j);
                }
            }
        };
        this.C = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.4
            @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
            public void onError(MediaPlayerAgent mediaPlayerAgent, int i3, int i4, int i5) {
                NativeVideoView.this.a(i3, false);
                if (NativeVideoView.this.b || bv.e(NativeVideoView.this.getContext())) {
                    return;
                }
                Toast makeText = Toast.makeText(NativeVideoView.this.getContext(), R.string._2130851113_res_0x7f023529, 0);
                makeText.setGravity(17, 0, 0);
                makeText.show();
            }
        };
        this.D = new com.huawei.openalliance.ad.media.listener.a() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.5
            @Override // com.huawei.openalliance.ad.media.listener.a
            public void b(int i3) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.a
            public void a(int i3) {
                NativeVideoView.this.j.b(i3);
            }
        };
        this.E = new MuteListener() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.6
            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onUnmute() {
                ho.b("NativeVideoView", "onUnmute");
                if (NativeVideoView.this.l != null) {
                    NativeVideoView.this.u = false;
                    NativeVideoView.this.l.e("y");
                    NativeVideoView.this.k.a(false);
                    NativeVideoView.this.g.b(1.0f);
                }
                NativeVideoView.this.j.e(false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onMute() {
                ho.b("NativeVideoView", "onMute");
                if (NativeVideoView.this.l != null) {
                    NativeVideoView.this.l.e("n");
                    if (!NativeVideoView.this.u && NativeVideoView.this.i) {
                        NativeVideoView.this.k.a(true);
                    } else {
                        NativeVideoView.this.u = false;
                    }
                    NativeVideoView.this.g.b(0.0f);
                }
                NativeVideoView.this.j.e(true);
            }
        };
        this.F = new ak.a() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.7
            @Override // com.huawei.openalliance.ad.views.ak.a
            public boolean b() {
                return NativeVideoView.this.m() && !NativeVideoView.this.y;
            }

            @Override // com.huawei.openalliance.ad.views.ak.a
            public void b(boolean z, int i3) {
                NativeVideoView.this.b(z, i3);
            }

            @Override // com.huawei.openalliance.ad.views.ak.a
            public void b(boolean z) {
                ho.b("NativeVideoView", "doRealPlay, auto:" + z);
                NativeVideoView.this.v.a();
            }

            @Override // com.huawei.openalliance.ad.views.ak.a
            public void a(boolean z, int i3) {
                NativeVideoView.this.a(z, i3);
            }

            @Override // com.huawei.openalliance.ad.views.ak.a
            public void a(boolean z) {
                if (NativeVideoView.this.l != null) {
                    NativeVideoView.this.l.b(!z);
                }
            }

            @Override // com.huawei.openalliance.ad.views.ak.a
            public void a() {
                if (NativeVideoView.this.r != null) {
                    NativeVideoView.this.r.a(5);
                }
            }
        };
        a(context);
    }

    public NativeVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = new lo();
        this.i = false;
        this.n = false;
        this.x = false;
        this.y = false;
        this.z = new MediaBufferListener() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.1
            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferUpdate(int i3) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingStart() {
                if (ho.a()) {
                    ho.a("NativeVideoView", "onBufferingStart");
                }
                NativeVideoView.this.v.b();
                NativeVideoView.this.g.h();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingEnd() {
                NativeVideoView.this.g.i();
            }
        };
        this.A = new ReportVideoTimeListener() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.2
            @Override // com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener
            public void reportVideoTime(long j) {
                if (ho.a()) {
                    ho.a("NativeVideoView", "reportVideoTime: %s", Long.valueOf(j));
                }
                if (NativeVideoView.this.k != null) {
                    NativeVideoView.this.k.b(j);
                }
            }
        };
        this.B = new MediaStateListener() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.3
            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onProgress(int i3, int i4) {
                NativeVideoView.this.e = i4;
                if (NativeVideoView.this.i) {
                    NativeVideoView.this.g.a(i3);
                    NativeVideoView.this.k.a(i3, i4, NativeVideoView.this.t, NativeVideoView.this.r, NativeVideoView.this.c);
                    NativeVideoView.this.k.a(i4, NativeVideoView.this.l == null ? 0 : NativeVideoView.this.l.getVideoDuration());
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i3) {
                NativeVideoView.this.a(i3, false);
                NativeVideoView.this.q();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i3) {
                if (ho.a()) {
                    ho.a("NativeVideoView", "onMediaStart: %s", Integer.valueOf(i3));
                }
                if (NativeVideoView.this.i) {
                    return;
                }
                NativeVideoView.this.i = true;
                NativeVideoView.this.t = i3;
                NativeVideoView.this.s = System.currentTimeMillis();
                NativeVideoView.this.o();
                lz lzVar = NativeVideoView.this.g;
                if (i3 <= 0) {
                    if (lzVar != null && NativeVideoView.this.l != null) {
                        NativeVideoView.this.g.a(NativeVideoView.this.l.getVideoDuration(), !"y".equals(NativeVideoView.this.l.getSoundSwitch()));
                    }
                    NativeVideoView.this.k.h();
                    NativeVideoView.this.k.a(NativeVideoView.this.v.e(), NativeVideoView.this.v.d(), NativeVideoView.this.s);
                    return;
                }
                lzVar.l();
                NativeVideoView.this.k.i();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i3) {
                NativeVideoView.this.a(i3, false);
                NativeVideoView.this.r();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i3) {
                if (NativeVideoView.this.l != null) {
                    NativeVideoView.this.l.b(true);
                }
                NativeVideoView.this.a(i3, true);
                NativeVideoView.this.s();
                if (NativeVideoView.this.k != null) {
                    long j = i3;
                    NativeVideoView.this.k.a(j, j);
                }
            }
        };
        this.C = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.4
            @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
            public void onError(MediaPlayerAgent mediaPlayerAgent, int i3, int i4, int i5) {
                NativeVideoView.this.a(i3, false);
                if (NativeVideoView.this.b || bv.e(NativeVideoView.this.getContext())) {
                    return;
                }
                Toast makeText = Toast.makeText(NativeVideoView.this.getContext(), R.string._2130851113_res_0x7f023529, 0);
                makeText.setGravity(17, 0, 0);
                makeText.show();
            }
        };
        this.D = new com.huawei.openalliance.ad.media.listener.a() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.5
            @Override // com.huawei.openalliance.ad.media.listener.a
            public void b(int i3) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.a
            public void a(int i3) {
                NativeVideoView.this.j.b(i3);
            }
        };
        this.E = new MuteListener() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.6
            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onUnmute() {
                ho.b("NativeVideoView", "onUnmute");
                if (NativeVideoView.this.l != null) {
                    NativeVideoView.this.u = false;
                    NativeVideoView.this.l.e("y");
                    NativeVideoView.this.k.a(false);
                    NativeVideoView.this.g.b(1.0f);
                }
                NativeVideoView.this.j.e(false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onMute() {
                ho.b("NativeVideoView", "onMute");
                if (NativeVideoView.this.l != null) {
                    NativeVideoView.this.l.e("n");
                    if (!NativeVideoView.this.u && NativeVideoView.this.i) {
                        NativeVideoView.this.k.a(true);
                    } else {
                        NativeVideoView.this.u = false;
                    }
                    NativeVideoView.this.g.b(0.0f);
                }
                NativeVideoView.this.j.e(true);
            }
        };
        this.F = new ak.a() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.7
            @Override // com.huawei.openalliance.ad.views.ak.a
            public boolean b() {
                return NativeVideoView.this.m() && !NativeVideoView.this.y;
            }

            @Override // com.huawei.openalliance.ad.views.ak.a
            public void b(boolean z, int i3) {
                NativeVideoView.this.b(z, i3);
            }

            @Override // com.huawei.openalliance.ad.views.ak.a
            public void b(boolean z) {
                ho.b("NativeVideoView", "doRealPlay, auto:" + z);
                NativeVideoView.this.v.a();
            }

            @Override // com.huawei.openalliance.ad.views.ak.a
            public void a(boolean z, int i3) {
                NativeVideoView.this.a(z, i3);
            }

            @Override // com.huawei.openalliance.ad.views.ak.a
            public void a(boolean z) {
                if (NativeVideoView.this.l != null) {
                    NativeVideoView.this.l.b(!z);
                }
            }

            @Override // com.huawei.openalliance.ad.views.ak.a
            public void a() {
                if (NativeVideoView.this.r != null) {
                    NativeVideoView.this.r.a(5);
                }
            }
        };
        a(context);
    }

    public NativeVideoView(Context context) {
        super(context);
        this.g = new lo();
        this.i = false;
        this.n = false;
        this.x = false;
        this.y = false;
        this.z = new MediaBufferListener() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.1
            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferUpdate(int i3) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingStart() {
                if (ho.a()) {
                    ho.a("NativeVideoView", "onBufferingStart");
                }
                NativeVideoView.this.v.b();
                NativeVideoView.this.g.h();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingEnd() {
                NativeVideoView.this.g.i();
            }
        };
        this.A = new ReportVideoTimeListener() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.2
            @Override // com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener
            public void reportVideoTime(long j) {
                if (ho.a()) {
                    ho.a("NativeVideoView", "reportVideoTime: %s", Long.valueOf(j));
                }
                if (NativeVideoView.this.k != null) {
                    NativeVideoView.this.k.b(j);
                }
            }
        };
        this.B = new MediaStateListener() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.3
            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onProgress(int i3, int i4) {
                NativeVideoView.this.e = i4;
                if (NativeVideoView.this.i) {
                    NativeVideoView.this.g.a(i3);
                    NativeVideoView.this.k.a(i3, i4, NativeVideoView.this.t, NativeVideoView.this.r, NativeVideoView.this.c);
                    NativeVideoView.this.k.a(i4, NativeVideoView.this.l == null ? 0 : NativeVideoView.this.l.getVideoDuration());
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i3) {
                NativeVideoView.this.a(i3, false);
                NativeVideoView.this.q();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i3) {
                if (ho.a()) {
                    ho.a("NativeVideoView", "onMediaStart: %s", Integer.valueOf(i3));
                }
                if (NativeVideoView.this.i) {
                    return;
                }
                NativeVideoView.this.i = true;
                NativeVideoView.this.t = i3;
                NativeVideoView.this.s = System.currentTimeMillis();
                NativeVideoView.this.o();
                lz lzVar = NativeVideoView.this.g;
                if (i3 <= 0) {
                    if (lzVar != null && NativeVideoView.this.l != null) {
                        NativeVideoView.this.g.a(NativeVideoView.this.l.getVideoDuration(), !"y".equals(NativeVideoView.this.l.getSoundSwitch()));
                    }
                    NativeVideoView.this.k.h();
                    NativeVideoView.this.k.a(NativeVideoView.this.v.e(), NativeVideoView.this.v.d(), NativeVideoView.this.s);
                    return;
                }
                lzVar.l();
                NativeVideoView.this.k.i();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i3) {
                NativeVideoView.this.a(i3, false);
                NativeVideoView.this.r();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i3) {
                if (NativeVideoView.this.l != null) {
                    NativeVideoView.this.l.b(true);
                }
                NativeVideoView.this.a(i3, true);
                NativeVideoView.this.s();
                if (NativeVideoView.this.k != null) {
                    long j = i3;
                    NativeVideoView.this.k.a(j, j);
                }
            }
        };
        this.C = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.4
            @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
            public void onError(MediaPlayerAgent mediaPlayerAgent, int i3, int i4, int i5) {
                NativeVideoView.this.a(i3, false);
                if (NativeVideoView.this.b || bv.e(NativeVideoView.this.getContext())) {
                    return;
                }
                Toast makeText = Toast.makeText(NativeVideoView.this.getContext(), R.string._2130851113_res_0x7f023529, 0);
                makeText.setGravity(17, 0, 0);
                makeText.show();
            }
        };
        this.D = new com.huawei.openalliance.ad.media.listener.a() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.5
            @Override // com.huawei.openalliance.ad.media.listener.a
            public void b(int i3) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.a
            public void a(int i3) {
                NativeVideoView.this.j.b(i3);
            }
        };
        this.E = new MuteListener() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.6
            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onUnmute() {
                ho.b("NativeVideoView", "onUnmute");
                if (NativeVideoView.this.l != null) {
                    NativeVideoView.this.u = false;
                    NativeVideoView.this.l.e("y");
                    NativeVideoView.this.k.a(false);
                    NativeVideoView.this.g.b(1.0f);
                }
                NativeVideoView.this.j.e(false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onMute() {
                ho.b("NativeVideoView", "onMute");
                if (NativeVideoView.this.l != null) {
                    NativeVideoView.this.l.e("n");
                    if (!NativeVideoView.this.u && NativeVideoView.this.i) {
                        NativeVideoView.this.k.a(true);
                    } else {
                        NativeVideoView.this.u = false;
                    }
                    NativeVideoView.this.g.b(0.0f);
                }
                NativeVideoView.this.j.e(true);
            }
        };
        this.F = new ak.a() { // from class: com.huawei.openalliance.ad.views.NativeVideoView.7
            @Override // com.huawei.openalliance.ad.views.ak.a
            public boolean b() {
                return NativeVideoView.this.m() && !NativeVideoView.this.y;
            }

            @Override // com.huawei.openalliance.ad.views.ak.a
            public void b(boolean z, int i3) {
                NativeVideoView.this.b(z, i3);
            }

            @Override // com.huawei.openalliance.ad.views.ak.a
            public void b(boolean z) {
                ho.b("NativeVideoView", "doRealPlay, auto:" + z);
                NativeVideoView.this.v.a();
            }

            @Override // com.huawei.openalliance.ad.views.ak.a
            public void a(boolean z, int i3) {
                NativeVideoView.this.a(z, i3);
            }

            @Override // com.huawei.openalliance.ad.views.ak.a
            public void a(boolean z) {
                if (NativeVideoView.this.l != null) {
                    NativeVideoView.this.l.b(!z);
                }
            }

            @Override // com.huawei.openalliance.ad.views.ak.a
            public void a() {
                if (NativeVideoView.this.r != null) {
                    NativeVideoView.this.r.a(5);
                }
            }
        };
        a(context);
    }
}
