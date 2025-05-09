package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.Scheme;
import com.huawei.openalliance.ad.dh;
import com.huawei.openalliance.ad.dk;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.im;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.inter.data.ImageInfo;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.jg;
import com.huawei.openalliance.ad.media.MediaPlayerAgent;
import com.huawei.openalliance.ad.media.MediaState;
import com.huawei.openalliance.ad.media.listener.MediaBufferListener;
import com.huawei.openalliance.ad.media.listener.MediaErrorListener;
import com.huawei.openalliance.ad.media.listener.MediaStateListener;
import com.huawei.openalliance.ad.media.listener.MuteListener;
import com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener;
import com.huawei.openalliance.ad.nf;
import com.huawei.openalliance.ad.nx;
import com.huawei.openalliance.ad.utils.Cdo;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.views.interfaces.INativeVideoView;
import com.huawei.openalliance.ad.views.interfaces.IPPSNativeView;
import com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle;
import java.util.List;

/* loaded from: classes9.dex */
public class NativePureVideoView extends NativeMediaView implements INativeVideoView, IViewLifeCycle {
    private static final String g = "NativePureVideoView";
    private nx h;
    private VideoView i;
    private ImageView j;
    private boolean k;
    private VideoInfo l;
    private ImageInfo m;
    private boolean n;
    private long o;
    private long p;
    private boolean q;
    private IPPSNativeView r;
    private jg s;
    private Context t;
    private MediaBufferListener u;
    private final ReportVideoTimeListener v;
    private MediaStateListener w;
    private MediaErrorListener x;
    private MuteListener y;

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public void showFullScreenSwitchButton(boolean z) {
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public void updateStartShowTime(long j) {
        this.h.a(j);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public void updateContent(String str) {
        this.h.a(str);
    }

    public void unmuteSound() {
        this.i.unmute();
    }

    public boolean switchToFullScreen() {
        b(4);
        VideoInfo videoInfo = this.l;
        if (videoInfo != null) {
            videoInfo.b(true);
        }
        this.i.setNeedPauseOnSurfaceDestory(false);
        return this.h.a(this.i.getMediaPlayerAgent(), this.c);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public void stop() {
        this.i.stop();
    }

    public void setStandalone(boolean z) {
        this.i.setStandalone(z);
    }

    public void setPreferStartPlayTime(int i) {
        this.i.setPreferStartPlayTime(i);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public void setPpsNativeView(IPPSNativeView iPPSNativeView) {
        this.r = iPPSNativeView;
    }

    @Override // com.huawei.openalliance.ad.views.NativeMediaView, com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public void setNativeAd(INativeAd iNativeAd) {
        MediaState currentState = this.i.getCurrentState();
        if (this.c == iNativeAd && currentState.isNotState(MediaState.State.IDLE) && currentState.isNotState(MediaState.State.ERROR)) {
            ho.b(g, "setNativeAd - has the same ad");
            return;
        }
        super.setNativeAd(iNativeAd);
        i();
        this.h.a(this.c);
        if (this.c == null) {
            this.l = null;
        } else {
            g();
            h();
        }
    }

    public void setAudioFocusType(int i) {
        this.i.setAudioFocusType(i);
    }

    public void seekTo(int i, int i2) {
        this.i.c(i, i2);
    }

    public void seekTo(int i) {
        this.i.seekTo(i);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void resumeView() {
        this.f7815a = false;
        this.i.resumeView();
        this.i.setNeedPauseOnSurfaceDestory(true);
        this.f.onGlobalLayout();
    }

    public void removeNetworkChangeListener(NetworkChangeListener networkChangeListener) {
        this.i.removeNetworkChangeListener(networkChangeListener);
    }

    public void removeMuteListener(MuteListener muteListener) {
        this.i.removeMuteListener(muteListener);
    }

    public void removeMediaStateListener(MediaStateListener mediaStateListener) {
        this.i.removeMediaStateListener(mediaStateListener);
    }

    public void removeMediaErrorListener(MediaErrorListener mediaErrorListener) {
        this.i.removeMediaErrorListener(mediaErrorListener);
    }

    public void removeMediaBufferListener(MediaBufferListener mediaBufferListener) {
        this.i.removeMediaBufferListener(mediaBufferListener);
    }

    public void play(boolean z) {
        if (this.k) {
            a(z);
        } else {
            this.n = true;
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void pauseView() {
        this.i.pauseView();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public void pause() {
        this.i.pause();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public void onPreviewImageLoaded(ImageInfo imageInfo, Drawable drawable) {
        ImageInfo imageInfo2 = this.m;
        if (imageInfo2 == null || imageInfo == null || !TextUtils.equals(imageInfo2.getUrl(), imageInfo.getUrl())) {
            return;
        }
        this.j.setImageDrawable(drawable);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public void onCheckVideoHashResult(VideoInfo videoInfo, boolean z) {
        VideoInfo videoInfo2;
        ho.b(g, "onCheckVideoHashResult sucess: %s", Boolean.valueOf(z));
        if (!z || (videoInfo2 = this.l) == null || videoInfo == null || !TextUtils.equals(videoInfo2.getVideoDownloadUrl(), videoInfo.getVideoDownloadUrl())) {
            return;
        }
        this.k = true;
        a(videoInfo);
        if (this.n) {
            a(false);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public void notifyStreamError(int i) {
        MediaErrorListener mediaErrorListener = this.x;
        if (mediaErrorListener != null) {
            mediaErrorListener.onError(null, this.e, i, -1);
        }
        nx nxVar = this.h;
        if (nxVar != null) {
            nxVar.a(this.e, i);
        }
    }

    public void muteSound() {
        this.i.mute();
    }

    public boolean isPlaying() {
        return this.i.isPlaying();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public VideoView getVideoView() {
        return this.i;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.INativeVideoView
    public ImageView getPreviewImageView() {
        return this.j;
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

    public MediaState getCurrentState() {
        return this.i.getCurrentState();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void destroyView() {
        this.i.destroyView();
    }

    public void addNetworkChangeListener(NetworkChangeListener networkChangeListener) {
        this.i.addNetworkChangeListener(networkChangeListener);
    }

    public void addMuteListener(MuteListener muteListener) {
        this.i.addMuteListener(muteListener);
    }

    public void addMediaStateListener(MediaStateListener mediaStateListener) {
        this.i.addMediaStateListener(mediaStateListener);
    }

    public void addMediaErrorListener(MediaErrorListener mediaErrorListener) {
        this.i.addMediaErrorListener(mediaErrorListener);
    }

    public void addMediaBufferListener(MediaBufferListener mediaBufferListener) {
        this.i.addMediaBufferListener(mediaBufferListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (ho.a()) {
            ho.a(g, "hidePreviewView");
        }
        Cdo.a(this.j, 8, 300, 300);
        this.i.setAlpha(1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (ho.a()) {
            ho.a(g, "showPreviewView");
        }
        Animation animation = this.j.getAnimation();
        if (animation != null) {
            animation.cancel();
        }
        dd.a((View) this.j, true);
        this.i.setAlpha(0.0f);
    }

    private void i() {
        j();
        this.k = false;
        this.n = false;
    }

    private void h() {
        if (this.c == null) {
            return;
        }
        VideoInfo videoInfo = this.c.getVideoInfo();
        this.l = videoInfo;
        if (videoInfo != null) {
            Float videoRatio = videoInfo.getVideoRatio();
            if (videoRatio == null) {
                videoRatio = Float.valueOf(1.7777778f);
            }
            setRatio(videoRatio);
            this.i.setDefaultDuration(this.l.getVideoDuration());
            this.h.a(this.l);
        }
    }

    private String getTAG() {
        return g + "_" + hashCode();
    }

    private void g() {
        List<ImageInfo> imageInfos;
        if (this.c == null || (imageInfos = this.c.getImageInfos()) == null || imageInfos.size() <= 0) {
            return;
        }
        ImageInfo imageInfo = imageInfos.get(0);
        this.m = imageInfo;
        if (imageInfo != null) {
            if (cz.j(imageInfo.getUrl())) {
                ho.b(g, "don't load preview image with http url");
                return;
            }
            if (this.m.getHeight() > 0) {
                setRatio(Float.valueOf((this.m.getWidth() * 1.0f) / this.m.getHeight()));
            }
            this.h.a(this.m);
        }
    }

    private void b(int i) {
        IPPSNativeView iPPSNativeView = this.r;
        if (iPPSNativeView != null) {
            iPPSNativeView.a(Integer.valueOf(i));
        }
    }

    private String b(VideoInfo videoInfo) {
        if (videoInfo != null && cz.j(videoInfo.g()) && ao.a(videoInfo)) {
            return ao.a(this.t, videoInfo, new im(this));
        }
        return null;
    }

    private void a(boolean z) {
        ho.b(g, "doRealPlay, auto:" + z);
        this.s.a();
        this.i.play(z);
    }

    private void a(VideoInfo videoInfo) {
        String videoDownloadUrl = videoInfo.getVideoDownloadUrl();
        if (videoDownloadUrl.startsWith(Scheme.DISKCACHE.toString())) {
            ho.b(g, "video cached, play from local.");
        } else {
            String c = dh.a(this.t, "normal").c(dk.d(videoDownloadUrl));
            if (!TextUtils.isEmpty(c) && com.huawei.openalliance.ad.utils.ae.b(this.t, c)) {
                ho.b(g, "video cached, play from local.");
                this.i.setVideoFileUrl(c);
                return;
            }
            String str = g;
            ho.b(str, "video not cached, play from net.");
            String b = b(videoInfo);
            if (!TextUtils.isEmpty(b)) {
                ho.b(str, "play mode 3, cache while playing.");
                videoDownloadUrl = b;
            }
        }
        this.i.setVideoFileUrl(videoDownloadUrl);
    }

    private void a(Context context) {
        this.t = context;
        LayoutInflater.from(context).inflate(R.layout.hiad_native_pure_video_view, this);
        this.h = new nf(context, this);
        this.i = (VideoView) findViewById(R.id.hiad_id_video_view);
        this.j = (ImageView) findViewById(R.id.hiad_iv_preview_video);
        this.i.setScreenOnWhilePlaying(true);
        this.i.setAutoScaleResizeLayoutOnVideoSizeChange(false);
        this.i.addMediaStateListener(this.w);
        this.i.addMediaBufferListener(this.u);
        this.i.addMediaErrorListener(this.x);
        this.i.addMuteListener(this.y);
        this.i.addReportVideoTimeListenersSet(this.v);
        this.s = new jg(getTAG());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, boolean z) {
        this.s.c();
        if (this.q) {
            this.q = false;
            nx nxVar = this.h;
            long j = this.o;
            long currentTimeMillis = System.currentTimeMillis();
            long j2 = this.p;
            long j3 = i;
            if (!z) {
                nxVar.c(j, currentTimeMillis, j2, j3);
            } else {
                nxVar.b(j, currentTimeMillis, j2, j3);
                this.h.a(this.r, this.c);
            }
        }
    }

    public NativePureVideoView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.q = false;
        this.u = new MediaBufferListener() { // from class: com.huawei.openalliance.ad.views.NativePureVideoView.1
            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferUpdate(int i3) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingEnd() {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingStart() {
                if (ho.a()) {
                    ho.a(NativePureVideoView.g, "onBufferingStart");
                }
                NativePureVideoView.this.s.b();
            }
        };
        this.v = new ReportVideoTimeListener() { // from class: com.huawei.openalliance.ad.views.NativePureVideoView.2
            @Override // com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener
            public void reportVideoTime(long j) {
                if (ho.a()) {
                    ho.a(NativePureVideoView.g, "reportVideoTime: %s", Long.valueOf(j));
                }
                if (NativePureVideoView.this.h != null) {
                    NativePureVideoView.this.h.b(j);
                }
            }
        };
        this.w = new MediaStateListener() { // from class: com.huawei.openalliance.ad.views.NativePureVideoView.3
            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onProgress(int i3, int i4) {
                if (NativePureVideoView.this.q) {
                    NativePureVideoView.this.h.a(i3, i4, NativePureVideoView.this.p, NativePureVideoView.this.r, NativePureVideoView.this.c);
                    NativePureVideoView.this.h.a(i4, NativePureVideoView.this.l == null ? 0 : NativePureVideoView.this.l.getVideoDuration());
                }
                NativePureVideoView.this.e = i4;
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i3) {
                NativePureVideoView.this.j();
                NativePureVideoView.this.a(i3, false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i3) {
                if (ho.a()) {
                    ho.a(NativePureVideoView.g, "onMediaStart: %s", Integer.valueOf(i3));
                }
                NativePureVideoView.this.k();
                if (NativePureVideoView.this.q) {
                    return;
                }
                NativePureVideoView.this.q = true;
                NativePureVideoView.this.p = i3;
                NativePureVideoView.this.o = System.currentTimeMillis();
                nx nxVar = NativePureVideoView.this.h;
                if (i3 > 0) {
                    nxVar.i();
                } else {
                    nxVar.h();
                    NativePureVideoView.this.h.a(NativePureVideoView.this.s.e(), NativePureVideoView.this.s.d(), NativePureVideoView.this.o);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i3) {
                NativePureVideoView.this.a(i3, false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i3) {
                NativePureVideoView.this.j();
                NativePureVideoView.this.a(i3, true);
                if (NativePureVideoView.this.h != null) {
                    long j = i3;
                    NativePureVideoView.this.h.a(j, j);
                }
            }
        };
        this.x = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.views.NativePureVideoView.4
            @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
            public void onError(MediaPlayerAgent mediaPlayerAgent, int i3, int i4, int i5) {
                NativePureVideoView.this.j();
                NativePureVideoView.this.a(i3, false);
            }
        };
        this.y = new MuteListener() { // from class: com.huawei.openalliance.ad.views.NativePureVideoView.5
            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onUnmute() {
                if (NativePureVideoView.this.l != null) {
                    NativePureVideoView.this.l.e("y");
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onMute() {
                if (NativePureVideoView.this.l != null) {
                    NativePureVideoView.this.l.e("n");
                }
            }
        };
        a(context);
    }

    public NativePureVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.q = false;
        this.u = new MediaBufferListener() { // from class: com.huawei.openalliance.ad.views.NativePureVideoView.1
            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferUpdate(int i3) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingEnd() {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingStart() {
                if (ho.a()) {
                    ho.a(NativePureVideoView.g, "onBufferingStart");
                }
                NativePureVideoView.this.s.b();
            }
        };
        this.v = new ReportVideoTimeListener() { // from class: com.huawei.openalliance.ad.views.NativePureVideoView.2
            @Override // com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener
            public void reportVideoTime(long j) {
                if (ho.a()) {
                    ho.a(NativePureVideoView.g, "reportVideoTime: %s", Long.valueOf(j));
                }
                if (NativePureVideoView.this.h != null) {
                    NativePureVideoView.this.h.b(j);
                }
            }
        };
        this.w = new MediaStateListener() { // from class: com.huawei.openalliance.ad.views.NativePureVideoView.3
            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onProgress(int i3, int i4) {
                if (NativePureVideoView.this.q) {
                    NativePureVideoView.this.h.a(i3, i4, NativePureVideoView.this.p, NativePureVideoView.this.r, NativePureVideoView.this.c);
                    NativePureVideoView.this.h.a(i4, NativePureVideoView.this.l == null ? 0 : NativePureVideoView.this.l.getVideoDuration());
                }
                NativePureVideoView.this.e = i4;
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i3) {
                NativePureVideoView.this.j();
                NativePureVideoView.this.a(i3, false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i3) {
                if (ho.a()) {
                    ho.a(NativePureVideoView.g, "onMediaStart: %s", Integer.valueOf(i3));
                }
                NativePureVideoView.this.k();
                if (NativePureVideoView.this.q) {
                    return;
                }
                NativePureVideoView.this.q = true;
                NativePureVideoView.this.p = i3;
                NativePureVideoView.this.o = System.currentTimeMillis();
                nx nxVar = NativePureVideoView.this.h;
                if (i3 > 0) {
                    nxVar.i();
                } else {
                    nxVar.h();
                    NativePureVideoView.this.h.a(NativePureVideoView.this.s.e(), NativePureVideoView.this.s.d(), NativePureVideoView.this.o);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i3) {
                NativePureVideoView.this.a(i3, false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i3) {
                NativePureVideoView.this.j();
                NativePureVideoView.this.a(i3, true);
                if (NativePureVideoView.this.h != null) {
                    long j = i3;
                    NativePureVideoView.this.h.a(j, j);
                }
            }
        };
        this.x = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.views.NativePureVideoView.4
            @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
            public void onError(MediaPlayerAgent mediaPlayerAgent, int i3, int i4, int i5) {
                NativePureVideoView.this.j();
                NativePureVideoView.this.a(i3, false);
            }
        };
        this.y = new MuteListener() { // from class: com.huawei.openalliance.ad.views.NativePureVideoView.5
            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onUnmute() {
                if (NativePureVideoView.this.l != null) {
                    NativePureVideoView.this.l.e("y");
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onMute() {
                if (NativePureVideoView.this.l != null) {
                    NativePureVideoView.this.l.e("n");
                }
            }
        };
        a(context);
    }

    public NativePureVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.q = false;
        this.u = new MediaBufferListener() { // from class: com.huawei.openalliance.ad.views.NativePureVideoView.1
            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferUpdate(int i3) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingEnd() {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingStart() {
                if (ho.a()) {
                    ho.a(NativePureVideoView.g, "onBufferingStart");
                }
                NativePureVideoView.this.s.b();
            }
        };
        this.v = new ReportVideoTimeListener() { // from class: com.huawei.openalliance.ad.views.NativePureVideoView.2
            @Override // com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener
            public void reportVideoTime(long j) {
                if (ho.a()) {
                    ho.a(NativePureVideoView.g, "reportVideoTime: %s", Long.valueOf(j));
                }
                if (NativePureVideoView.this.h != null) {
                    NativePureVideoView.this.h.b(j);
                }
            }
        };
        this.w = new MediaStateListener() { // from class: com.huawei.openalliance.ad.views.NativePureVideoView.3
            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onProgress(int i3, int i4) {
                if (NativePureVideoView.this.q) {
                    NativePureVideoView.this.h.a(i3, i4, NativePureVideoView.this.p, NativePureVideoView.this.r, NativePureVideoView.this.c);
                    NativePureVideoView.this.h.a(i4, NativePureVideoView.this.l == null ? 0 : NativePureVideoView.this.l.getVideoDuration());
                }
                NativePureVideoView.this.e = i4;
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i3) {
                NativePureVideoView.this.j();
                NativePureVideoView.this.a(i3, false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i3) {
                if (ho.a()) {
                    ho.a(NativePureVideoView.g, "onMediaStart: %s", Integer.valueOf(i3));
                }
                NativePureVideoView.this.k();
                if (NativePureVideoView.this.q) {
                    return;
                }
                NativePureVideoView.this.q = true;
                NativePureVideoView.this.p = i3;
                NativePureVideoView.this.o = System.currentTimeMillis();
                nx nxVar = NativePureVideoView.this.h;
                if (i3 > 0) {
                    nxVar.i();
                } else {
                    nxVar.h();
                    NativePureVideoView.this.h.a(NativePureVideoView.this.s.e(), NativePureVideoView.this.s.d(), NativePureVideoView.this.o);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i3) {
                NativePureVideoView.this.a(i3, false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i3) {
                NativePureVideoView.this.j();
                NativePureVideoView.this.a(i3, true);
                if (NativePureVideoView.this.h != null) {
                    long j = i3;
                    NativePureVideoView.this.h.a(j, j);
                }
            }
        };
        this.x = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.views.NativePureVideoView.4
            @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
            public void onError(MediaPlayerAgent mediaPlayerAgent, int i3, int i4, int i5) {
                NativePureVideoView.this.j();
                NativePureVideoView.this.a(i3, false);
            }
        };
        this.y = new MuteListener() { // from class: com.huawei.openalliance.ad.views.NativePureVideoView.5
            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onUnmute() {
                if (NativePureVideoView.this.l != null) {
                    NativePureVideoView.this.l.e("y");
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onMute() {
                if (NativePureVideoView.this.l != null) {
                    NativePureVideoView.this.l.e("n");
                }
            }
        };
        a(context);
    }

    public NativePureVideoView(Context context) {
        super(context);
        this.q = false;
        this.u = new MediaBufferListener() { // from class: com.huawei.openalliance.ad.views.NativePureVideoView.1
            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferUpdate(int i3) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingEnd() {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingStart() {
                if (ho.a()) {
                    ho.a(NativePureVideoView.g, "onBufferingStart");
                }
                NativePureVideoView.this.s.b();
            }
        };
        this.v = new ReportVideoTimeListener() { // from class: com.huawei.openalliance.ad.views.NativePureVideoView.2
            @Override // com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener
            public void reportVideoTime(long j) {
                if (ho.a()) {
                    ho.a(NativePureVideoView.g, "reportVideoTime: %s", Long.valueOf(j));
                }
                if (NativePureVideoView.this.h != null) {
                    NativePureVideoView.this.h.b(j);
                }
            }
        };
        this.w = new MediaStateListener() { // from class: com.huawei.openalliance.ad.views.NativePureVideoView.3
            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onProgress(int i3, int i4) {
                if (NativePureVideoView.this.q) {
                    NativePureVideoView.this.h.a(i3, i4, NativePureVideoView.this.p, NativePureVideoView.this.r, NativePureVideoView.this.c);
                    NativePureVideoView.this.h.a(i4, NativePureVideoView.this.l == null ? 0 : NativePureVideoView.this.l.getVideoDuration());
                }
                NativePureVideoView.this.e = i4;
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i3) {
                NativePureVideoView.this.j();
                NativePureVideoView.this.a(i3, false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i3) {
                if (ho.a()) {
                    ho.a(NativePureVideoView.g, "onMediaStart: %s", Integer.valueOf(i3));
                }
                NativePureVideoView.this.k();
                if (NativePureVideoView.this.q) {
                    return;
                }
                NativePureVideoView.this.q = true;
                NativePureVideoView.this.p = i3;
                NativePureVideoView.this.o = System.currentTimeMillis();
                nx nxVar = NativePureVideoView.this.h;
                if (i3 > 0) {
                    nxVar.i();
                } else {
                    nxVar.h();
                    NativePureVideoView.this.h.a(NativePureVideoView.this.s.e(), NativePureVideoView.this.s.d(), NativePureVideoView.this.o);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i3) {
                NativePureVideoView.this.a(i3, false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i3) {
                NativePureVideoView.this.j();
                NativePureVideoView.this.a(i3, true);
                if (NativePureVideoView.this.h != null) {
                    long j = i3;
                    NativePureVideoView.this.h.a(j, j);
                }
            }
        };
        this.x = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.views.NativePureVideoView.4
            @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
            public void onError(MediaPlayerAgent mediaPlayerAgent, int i3, int i4, int i5) {
                NativePureVideoView.this.j();
                NativePureVideoView.this.a(i3, false);
            }
        };
        this.y = new MuteListener() { // from class: com.huawei.openalliance.ad.views.NativePureVideoView.5
            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onUnmute() {
                if (NativePureVideoView.this.l != null) {
                    NativePureVideoView.this.l.e("y");
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onMute() {
                if (NativePureVideoView.this.l != null) {
                    NativePureVideoView.this.l.e("n");
                }
            }
        };
        a(context);
    }
}
