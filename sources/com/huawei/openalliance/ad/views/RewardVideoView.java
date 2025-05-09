package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.RewardMethods;
import com.huawei.openalliance.ad.dh;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.IRewardAd;
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
import com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener;
import com.huawei.openalliance.ad.mp;
import com.huawei.openalliance.ad.mq;
import com.huawei.openalliance.ad.nq;
import com.huawei.openalliance.ad.of;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bv;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.views.interfaces.IRewardVideoView;
import com.huawei.operation.utils.Constants;

/* loaded from: classes5.dex */
public class RewardVideoView extends RewardMediaView implements lm, IRewardVideoView {
    private final ReportVideoTimeListener A;
    private MediaStateListener B;
    private MuteListener C;
    private MediaErrorListener D;
    private lz e;
    private of f;
    private VideoView g;
    private boolean h;
    private VideoInfo i;
    private boolean j;
    private boolean k;
    private long l;
    private long m;
    private boolean n;
    private boolean o;
    private int p;
    private boolean q;
    private ImageView r;
    private jg s;
    private boolean t;
    private int u;
    private int v;
    private int w;
    private long x;
    private boolean y;
    private MediaBufferListener z;

    @Override // com.huawei.openalliance.ad.lm
    public View getOpenMeasureView() {
        return this;
    }

    public void setVideoScaleMode(int i) {
        VideoView videoView = this.g;
        if (videoView != null) {
            videoView.setVideoScaleMode(i);
        }
    }

    public void setVideoFinish(boolean z) {
        this.q = z;
    }

    public void setVideoBackgroundColor(int i) {
        VideoView videoView = this.g;
        if (videoView != null) {
            videoView.setBackgroundColor(i);
        }
    }

    public void setUnUseDefault(boolean z) {
        this.t = z;
    }

    @Override // com.huawei.openalliance.ad.views.RewardMediaView
    public void setRewardAd(IRewardAd iRewardAd) {
        MediaState currentState = this.g.getCurrentState();
        if (this.f8002a == iRewardAd && currentState.isNotState(MediaState.State.IDLE) && currentState.isNotState(MediaState.State.ERROR)) {
            ho.b("RewardVideoView", "setRewardVideoAd - has the same ad");
            return;
        }
        super.setRewardAd(iRewardAd);
        StringBuilder sb = new StringBuilder("set reward ad:");
        sb.append(iRewardAd == null ? Constants.NULL : iRewardAd.getContentId());
        ho.b("RewardVideoView", sb.toString());
        h();
        this.f.a(this.f8002a);
        if (this.f8002a == null) {
            this.i = null;
        } else {
            g();
            this.w = fh.b(getContext()).h();
        }
    }

    public void setPreferStartPlayTime(int i) {
        this.p = i;
        this.g.setPreferStartPlayTime(i);
    }

    public void setAutoScaleResizeLayoutOnVideoSizeChange(boolean z) {
        VideoView videoView = this.g;
        if (videoView != null) {
            videoView.setAutoScaleResizeLayoutOnVideoSizeChange(z);
        }
    }

    public void setAudioFocusType(int i) {
        this.g.setAudioFocusType(i);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void resumeView() {
        ho.b("RewardVideoView", RewardMethods.RESUME_VIEW);
        this.g.resumeView();
        this.g.setNeedPauseOnSurfaceDestory(true);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void pauseView() {
        ho.b("RewardVideoView", RewardMethods.PAUSE_VIEW);
        j();
        setTryPlayStartTime(-1L);
        this.g.pauseView();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IRewardVideoView
    public void onCheckVideoHashResult(VideoInfo videoInfo, boolean z) {
        ho.b("RewardVideoView", "onCheckVideoHashResult success: %s", Boolean.valueOf(z));
        if (!z || this.i == null || videoInfo == null) {
            return;
        }
        this.i = videoInfo;
        this.h = true;
        String videoDownloadUrl = videoInfo.getVideoDownloadUrl();
        this.b = videoDownloadUrl;
        int videoPlayMode = videoInfo.getVideoPlayMode();
        if (cz.j(videoDownloadUrl)) {
            videoPlayMode = 2;
        }
        a(videoInfo.getVideoPlayMode(), videoPlayMode);
        this.g.setVideoFileUrl(videoDownloadUrl);
        if (this.j) {
            ho.b("RewardVideoView", "play when hash check success");
            b(true, this.o);
        }
        if (this.k) {
            ho.b("RewardVideoView", "prefect when hash check success");
            this.g.prefetch();
        }
    }

    public int getPlayedTime() {
        return this.v;
    }

    public int getPlayedProgress() {
        VideoInfo videoInfo = this.i;
        if (videoInfo != null && videoInfo.getVideoDuration() > 0) {
            this.u = (int) ((getPlayedTime() / this.i.getVideoDuration()) * 100.0f);
        }
        return this.u;
    }

    public MediaState getCurrentState() {
        return this.g.getCurrentState();
    }

    public void f() {
        Bitmap surfaceBitmap = this.g.getSurfaceBitmap();
        if (surfaceBitmap != null) {
            if (this.r == null) {
                ImageView imageView = new ImageView(getContext());
                this.r = imageView;
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams.addRule(13);
                addView(this.r, layoutParams);
            }
            this.r.setImageBitmap(surfaceBitmap);
            this.g.setVisibility(4);
        }
    }

    @Override // com.huawei.openalliance.ad.views.RewardMediaView, com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void destroyView() {
        ho.b("RewardVideoView", RewardMethods.DESTROY_VIEW);
        this.y = false;
        this.g.destroyView();
    }

    @Override // com.huawei.openalliance.ad.views.RewardMediaView
    public void d() {
        this.g.unmute();
    }

    @Override // com.huawei.openalliance.ad.views.RewardMediaView
    public void c() {
        this.g.mute();
    }

    @Override // com.huawei.openalliance.ad.views.RewardMediaView
    public void b() {
        this.g.stop();
    }

    public boolean a(int i, boolean z, int i2) {
        VideoInfo videoInfo;
        if (this.g == null || (videoInfo = this.i) == null || TextUtils.isEmpty(videoInfo.g())) {
            ho.c("RewardVideoView", "switch to online play, videoView or videoInfo is null");
            return false;
        }
        this.g.a(this.i.g(), false);
        this.p = i;
        b(true, z);
        a(this.i.getVideoPlayMode(), i2 == -5 ? 102 : 101);
        return true;
    }

    @Override // com.huawei.openalliance.ad.views.RewardMediaView
    public void a(boolean z, boolean z2) {
        ho.b("RewardVideoView", "play, auto:" + z + ", isMute:" + z2);
        if (this.h) {
            b(z, z2);
        } else {
            this.j = true;
            this.o = z2;
        }
    }

    @Override // com.huawei.openalliance.ad.views.RewardMediaView
    public void a(String str) {
        this.f.a(str);
    }

    public void a(NetworkChangeListener networkChangeListener) {
        this.g.addNetworkChangeListener(networkChangeListener);
    }

    @Override // com.huawei.openalliance.ad.views.RewardMediaView
    public void a(SegmentMediaStateListener segmentMediaStateListener) {
        this.g.addSegmentMediaStateListener(segmentMediaStateListener);
    }

    public void a(MuteListener muteListener) {
        this.g.addMuteListener(muteListener);
    }

    public void a(lz lzVar) {
        this.e = lzVar;
        this.e.a(mq.a(0.0f, i(), mp.STANDALONE));
    }

    @Override // com.huawei.openalliance.ad.views.RewardMediaView
    public void a(long j) {
        this.f.a(j);
    }

    public void a(int i, boolean z) {
        setVideoScaleMode(1);
        setVideoBackgroundColor(getResources().getColor(R.color._2131297924_res_0x7f090684));
        setBackgroundColor(getResources().getColor(R.color._2131297924_res_0x7f090684));
        if (!z || i == 1 || i == 2 || !(i == 3 || i == 4 || i == 5)) {
            setAutoScaleResizeLayoutOnVideoSizeChange(false);
        } else {
            setUnUseDefault(false);
            setAutoScaleResizeLayoutOnVideoSizeChange(true);
        }
    }

    @Override // com.huawei.openalliance.ad.views.RewardMediaView
    public void a() {
        this.g.pause();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTryPlayStartTime(long j) {
        this.x = j;
    }

    private boolean k() {
        return this.x > 0 && !this.n && ao.c() - this.x > ((long) this.w);
    }

    private void j() {
        VideoView videoView;
        if (this.f == null || this.s == null || (videoView = this.g) == null || this.y) {
            return;
        }
        MediaState currentState = videoView.getCurrentState();
        Integer num = currentState.isState(MediaState.State.ERROR) ? -2 : k() ? -1 : null;
        if (currentState.isState(MediaState.State.ERROR)) {
            num = -2;
        }
        if (num != null) {
            this.y = true;
            ho.b("RewardVideoView", "video error: %s", num);
            long c = this.x > 0 ? ao.c() - this.x : 0L;
            ho.a("RewardVideoView", "do play time: %s", Long.valueOf(c));
            this.f.a(getContext().getApplicationContext(), c, this.v, num.intValue());
        }
    }

    private boolean i() {
        if (this.i == null || !bv.e(getContext())) {
            return false;
        }
        if (bv.a(getContext())) {
            return true;
        }
        return (cz.j(this.i.getVideoDownloadUrl()) && TextUtils.isEmpty(dh.a(getContext(), "normal").e(this.i.getVideoDownloadUrl()))) ? false : true;
    }

    private void h() {
        ho.b("RewardVideoView", "resetVideoView");
        setPreferStartPlayTime(0);
        this.h = false;
        this.j = false;
        this.k = true;
        this.y = false;
    }

    private void g() {
        if (this.f8002a == null) {
            return;
        }
        ho.b("RewardVideoView", "loadVideoInfo");
        VideoInfo I = this.f8002a.I();
        if (I != null) {
            this.i = I;
            Float videoRatio = I.getVideoRatio();
            if (videoRatio != null && this.t) {
                setRatio(videoRatio);
                this.g.setRatio(videoRatio);
            }
            this.g.setDefaultDuration(this.i.getVideoDuration());
            this.f.a(this.i);
            this.j = false;
            this.k = true;
        }
    }

    private void b(boolean z, boolean z2) {
        ho.b("RewardVideoView", "doRealPlay, auto:" + z + ", isMute:" + z2);
        this.s.a();
        if (z2) {
            this.g.mute();
        } else {
            this.g.unmute();
        }
        if (this.g.getCurrentState().isState(MediaState.State.PLAYBACK_COMPLETED)) {
            this.g.c(this.p, 1);
        } else {
            this.g.setPreferStartPlayTime(this.p);
        }
        this.g.play(z);
        setTryPlayStartTime(ao.c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, boolean z) {
        this.s.c();
        if (this.n) {
            this.n = false;
            setPreferStartPlayTime(i);
            if (z || this.q) {
                this.f.b(this.l, System.currentTimeMillis(), this.m, i);
                this.e.g();
            } else {
                this.f.c(this.l, System.currentTimeMillis(), this.m, i);
                this.e.k();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        VideoInfo videoInfo = this.i;
        if (videoInfo != null) {
            videoInfo.e(z ? "n" : "y");
            this.e.b(z ? 0.0f : 1.0f);
        }
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.hiad_placement_pure_video_view, this);
        this.f = new nq(context, this);
        this.s = new jg("RewardVideoView");
        VideoView videoView = (VideoView) findViewById(R.id.hiad_id_video_view);
        this.g = videoView;
        videoView.setScreenOnWhilePlaying(true);
        this.g.setAutoScaleResizeLayoutOnVideoSizeChange(false);
        this.g.addMediaStateListener(this.B);
        this.g.addMediaBufferListener(this.z);
        this.g.addMediaErrorListener(this.D);
        this.g.addMuteListener(this.C);
        this.g.addReportVideoTimeListenersSet(this.A);
        this.g.setMuteOnlyOnLostAudioFocus(true);
    }

    private void a(int i, int i2) {
        of ofVar = this.f;
        if (ofVar == null || i != 1 || i == i2) {
            return;
        }
        ofVar.a(i2);
    }

    public RewardVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new lo();
        this.k = true;
        this.q = false;
        this.t = true;
        this.u = 0;
        this.v = 0;
        this.w = 5000;
        this.x = -1L;
        this.y = false;
        this.z = new MediaBufferListener() { // from class: com.huawei.openalliance.ad.views.RewardVideoView.1
            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferUpdate(int i2) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingStart() {
                if (ho.a()) {
                    ho.a("RewardVideoView", "onBufferingStart");
                }
                RewardVideoView.this.s.b();
                RewardVideoView.this.e.h();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingEnd() {
                RewardVideoView.this.e.i();
            }
        };
        this.A = new ReportVideoTimeListener() { // from class: com.huawei.openalliance.ad.views.RewardVideoView.2
            @Override // com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener
            public void reportVideoTime(long j) {
                if (ho.a()) {
                    ho.a("RewardVideoView", "reportVideoTime: %s", Long.valueOf(j));
                }
                if (RewardVideoView.this.f != null) {
                    RewardVideoView.this.f.b(j);
                }
            }
        };
        this.B = new MediaStateListener() { // from class: com.huawei.openalliance.ad.views.RewardVideoView.3
            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onProgress(int i2, int i3) {
                RewardVideoView.this.v = i3;
                if (RewardVideoView.this.e == null || !RewardVideoView.this.n) {
                    return;
                }
                RewardVideoView.this.e.a(i2);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i2) {
                RewardVideoView.this.b(i2, false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i2) {
                if (ho.a()) {
                    ho.a("RewardVideoView", "onMediaStart: %d", Integer.valueOf(i2));
                }
                RewardVideoView.this.setTryPlayStartTime(-1L);
                RewardVideoView.this.n = true;
                RewardVideoView.this.m = i2;
                RewardVideoView.this.l = System.currentTimeMillis();
                RewardVideoView rewardVideoView = RewardVideoView.this;
                if (i2 > 0) {
                    rewardVideoView.f.i();
                    if (RewardVideoView.this.e != null) {
                        RewardVideoView.this.e.l();
                        return;
                    }
                    return;
                }
                if (rewardVideoView.e != null && RewardVideoView.this.i != null) {
                    ho.b("RewardVideoView", "om start");
                    RewardVideoView.this.e.a(RewardVideoView.this.i.getVideoDuration(), !"y".equals(RewardVideoView.this.i.getSoundSwitch()));
                }
                RewardVideoView.this.f.h();
                RewardVideoView.this.f.a(RewardVideoView.this.s.e(), RewardVideoView.this.s.d(), RewardVideoView.this.l);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i2) {
                RewardVideoView.this.b(i2, false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i2) {
                RewardVideoView.this.b(i2, true);
            }
        };
        this.C = new MuteListener() { // from class: com.huawei.openalliance.ad.views.RewardVideoView.4
            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onUnmute() {
                RewardVideoView.this.a(false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onMute() {
                RewardVideoView.this.a(true);
            }
        };
        this.D = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.views.RewardVideoView.5
            @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
            public void onError(MediaPlayerAgent mediaPlayerAgent, int i2, int i3, int i4) {
                RewardVideoView.this.b(i2, false);
            }
        };
        a(context);
    }

    public RewardVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new lo();
        this.k = true;
        this.q = false;
        this.t = true;
        this.u = 0;
        this.v = 0;
        this.w = 5000;
        this.x = -1L;
        this.y = false;
        this.z = new MediaBufferListener() { // from class: com.huawei.openalliance.ad.views.RewardVideoView.1
            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferUpdate(int i2) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingStart() {
                if (ho.a()) {
                    ho.a("RewardVideoView", "onBufferingStart");
                }
                RewardVideoView.this.s.b();
                RewardVideoView.this.e.h();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingEnd() {
                RewardVideoView.this.e.i();
            }
        };
        this.A = new ReportVideoTimeListener() { // from class: com.huawei.openalliance.ad.views.RewardVideoView.2
            @Override // com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener
            public void reportVideoTime(long j) {
                if (ho.a()) {
                    ho.a("RewardVideoView", "reportVideoTime: %s", Long.valueOf(j));
                }
                if (RewardVideoView.this.f != null) {
                    RewardVideoView.this.f.b(j);
                }
            }
        };
        this.B = new MediaStateListener() { // from class: com.huawei.openalliance.ad.views.RewardVideoView.3
            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onProgress(int i2, int i3) {
                RewardVideoView.this.v = i3;
                if (RewardVideoView.this.e == null || !RewardVideoView.this.n) {
                    return;
                }
                RewardVideoView.this.e.a(i2);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i2) {
                RewardVideoView.this.b(i2, false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i2) {
                if (ho.a()) {
                    ho.a("RewardVideoView", "onMediaStart: %d", Integer.valueOf(i2));
                }
                RewardVideoView.this.setTryPlayStartTime(-1L);
                RewardVideoView.this.n = true;
                RewardVideoView.this.m = i2;
                RewardVideoView.this.l = System.currentTimeMillis();
                RewardVideoView rewardVideoView = RewardVideoView.this;
                if (i2 > 0) {
                    rewardVideoView.f.i();
                    if (RewardVideoView.this.e != null) {
                        RewardVideoView.this.e.l();
                        return;
                    }
                    return;
                }
                if (rewardVideoView.e != null && RewardVideoView.this.i != null) {
                    ho.b("RewardVideoView", "om start");
                    RewardVideoView.this.e.a(RewardVideoView.this.i.getVideoDuration(), !"y".equals(RewardVideoView.this.i.getSoundSwitch()));
                }
                RewardVideoView.this.f.h();
                RewardVideoView.this.f.a(RewardVideoView.this.s.e(), RewardVideoView.this.s.d(), RewardVideoView.this.l);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i2) {
                RewardVideoView.this.b(i2, false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i2) {
                RewardVideoView.this.b(i2, true);
            }
        };
        this.C = new MuteListener() { // from class: com.huawei.openalliance.ad.views.RewardVideoView.4
            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onUnmute() {
                RewardVideoView.this.a(false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onMute() {
                RewardVideoView.this.a(true);
            }
        };
        this.D = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.views.RewardVideoView.5
            @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
            public void onError(MediaPlayerAgent mediaPlayerAgent, int i2, int i3, int i4) {
                RewardVideoView.this.b(i2, false);
            }
        };
        a(context);
    }

    public RewardVideoView(Context context) {
        super(context);
        this.e = new lo();
        this.k = true;
        this.q = false;
        this.t = true;
        this.u = 0;
        this.v = 0;
        this.w = 5000;
        this.x = -1L;
        this.y = false;
        this.z = new MediaBufferListener() { // from class: com.huawei.openalliance.ad.views.RewardVideoView.1
            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferUpdate(int i2) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingStart() {
                if (ho.a()) {
                    ho.a("RewardVideoView", "onBufferingStart");
                }
                RewardVideoView.this.s.b();
                RewardVideoView.this.e.h();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingEnd() {
                RewardVideoView.this.e.i();
            }
        };
        this.A = new ReportVideoTimeListener() { // from class: com.huawei.openalliance.ad.views.RewardVideoView.2
            @Override // com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener
            public void reportVideoTime(long j) {
                if (ho.a()) {
                    ho.a("RewardVideoView", "reportVideoTime: %s", Long.valueOf(j));
                }
                if (RewardVideoView.this.f != null) {
                    RewardVideoView.this.f.b(j);
                }
            }
        };
        this.B = new MediaStateListener() { // from class: com.huawei.openalliance.ad.views.RewardVideoView.3
            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onProgress(int i2, int i3) {
                RewardVideoView.this.v = i3;
                if (RewardVideoView.this.e == null || !RewardVideoView.this.n) {
                    return;
                }
                RewardVideoView.this.e.a(i2);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i2) {
                RewardVideoView.this.b(i2, false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i2) {
                if (ho.a()) {
                    ho.a("RewardVideoView", "onMediaStart: %d", Integer.valueOf(i2));
                }
                RewardVideoView.this.setTryPlayStartTime(-1L);
                RewardVideoView.this.n = true;
                RewardVideoView.this.m = i2;
                RewardVideoView.this.l = System.currentTimeMillis();
                RewardVideoView rewardVideoView = RewardVideoView.this;
                if (i2 > 0) {
                    rewardVideoView.f.i();
                    if (RewardVideoView.this.e != null) {
                        RewardVideoView.this.e.l();
                        return;
                    }
                    return;
                }
                if (rewardVideoView.e != null && RewardVideoView.this.i != null) {
                    ho.b("RewardVideoView", "om start");
                    RewardVideoView.this.e.a(RewardVideoView.this.i.getVideoDuration(), !"y".equals(RewardVideoView.this.i.getSoundSwitch()));
                }
                RewardVideoView.this.f.h();
                RewardVideoView.this.f.a(RewardVideoView.this.s.e(), RewardVideoView.this.s.d(), RewardVideoView.this.l);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i2) {
                RewardVideoView.this.b(i2, false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i2) {
                RewardVideoView.this.b(i2, true);
            }
        };
        this.C = new MuteListener() { // from class: com.huawei.openalliance.ad.views.RewardVideoView.4
            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onUnmute() {
                RewardVideoView.this.a(false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onMute() {
                RewardVideoView.this.a(true);
            }
        };
        this.D = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.views.RewardVideoView.5
            @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
            public void onError(MediaPlayerAgent mediaPlayerAgent, int i2, int i3, int i4) {
                RewardVideoView.this.b(i2, false);
            }
        };
        a(context);
    }
}
