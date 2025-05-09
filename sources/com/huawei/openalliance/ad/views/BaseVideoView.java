package com.huawei.openalliance.ad.views;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import com.huawei.openalliance.ad.constant.Scheme;
import com.huawei.openalliance.ad.dh;
import com.huawei.openalliance.ad.dk;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.media.IMultiMediaPlayingManager;
import com.huawei.openalliance.ad.media.MediaPlayerAgent;
import com.huawei.openalliance.ad.media.MediaState;
import com.huawei.openalliance.ad.media.listener.MediaBufferListener;
import com.huawei.openalliance.ad.media.listener.MediaErrorListener;
import com.huawei.openalliance.ad.media.listener.MediaPlayerReleaseListener;
import com.huawei.openalliance.ad.media.listener.MediaStateListener;
import com.huawei.openalliance.ad.media.listener.MuteListener;
import com.huawei.openalliance.ad.media.listener.PPSVideoRenderListener;
import com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener;
import com.huawei.openalliance.ad.media.listener.SegmentMediaStateListener;
import com.huawei.openalliance.ad.utils.bv;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.dl;
import com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes5.dex */
public abstract class BaseVideoView extends AutoScaleSizeRelativeLayout implements TextureView.SurfaceTextureListener, IViewLifeCycle {
    private boolean A;
    private boolean B;
    private boolean C;
    private String D;
    private String[] E;
    private int F;
    private SparseBooleanArray G;
    private SurfaceListener H;
    private boolean I;
    private boolean J;
    private boolean K;
    private String L;
    private Context M;
    private dk N;
    private boolean O;
    private com.huawei.openalliance.ad.media.b P;
    private MediaStateListener Q;
    private MediaBufferListener R;
    private PPSVideoRenderListener S;
    private MediaErrorListener T;
    private MuteListener U;
    private com.huawei.openalliance.ad.media.listener.a V;
    private e W;

    /* renamed from: a, reason: collision with root package name */
    private final String f7792a;
    private b aa;
    private g ab;
    private c ac;
    private f ad;
    private d ae;
    private BroadcastReceiver af;
    private int b;
    private IMultiMediaPlayingManager c;
    private final Set<NetworkChangeListener> d;
    private final Set<MediaStateListener> e;
    private final Set<MediaBufferListener> f;
    private final Set<MuteListener> g;
    private final Set<MediaErrorListener> h;
    private final Set<com.huawei.openalliance.ad.media.listener.a> i;
    private final Set<SegmentMediaStateListener> j;
    protected TextureView k;
    protected boolean l;
    protected boolean m;
    protected MediaPlayerAgent n;
    protected MediaPlayerAgent o;
    protected Surface p;
    protected SurfaceTexture q;
    protected boolean r;
    protected int s;
    protected boolean t;
    protected MediaPlayer.OnVideoSizeChangedListener u;
    protected int v;
    protected int w;
    protected i x;
    private final Set<SegmentMediaStateListener> y;
    private final Set<PPSVideoRenderListener> z;

    public interface SurfaceListener {
        void onSurfaceDestroyed();
    }

    protected void a(Context context) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void unmute() {
        ho.b(this.f7792a, "unmute");
        this.n.unmuteSound();
    }

    public void stop() {
        ho.b(this.f7792a, "stop standalone " + this.A);
        this.l = false;
        if (this.A) {
            this.n.stop();
        } else {
            this.c.stop(this.D, this.n);
        }
    }

    public void setVideoScaleMode(int i2) {
        if (i2 == 1 || i2 == 2) {
            this.s = i2;
        } else {
            throw new IllegalArgumentException("Not supported video scale mode: " + i2);
        }
    }

    public void setVideoFileUrls(String[] strArr) {
        String[] strArr2 = strArr != null ? (String[]) Arrays.copyOf(strArr, strArr.length) : null;
        this.E = strArr2;
        this.F = 0;
        this.G.clear();
        if (strArr2 == null || strArr2.length <= 0) {
            this.D = null;
            ho.c(this.f7792a, "setVideoFileUrls - url array is empty");
        } else {
            ho.b(this.f7792a, "setVideoFileUrls - size: %d", Integer.valueOf(strArr2.length));
            String str = strArr2[this.F];
            this.D = str;
            this.n.setMediaFile(str);
        }
    }

    public void setVideoFileUrl(String str) {
        a(str, true);
    }

    public void setSurfaceListener(SurfaceListener surfaceListener) {
        this.H = surfaceListener;
    }

    public void setStandalone(boolean z) {
        this.A = z;
    }

    public void setSoundVolume(float f2) {
        this.n.setSoundVolume(f2);
    }

    public void setScreenOnWhilePlaying(boolean z) {
        this.C = z;
        setKeepScreenOn(z && getCurrentState().isState(MediaState.State.PLAYING));
    }

    public void setRemediate(boolean z) {
        this.O = z;
    }

    public void setPreferStartPlayTime(int i2) {
        this.n.setPreferStartPlayTime(i2);
    }

    public void setNeedPauseOnSurfaceDestory(boolean z) {
        this.t = z;
    }

    public void setMuteOnlyOnLostAudioFocus(boolean z) {
        this.K = z;
        this.n.a(z);
    }

    public void setMediaPlayerReleaseListener(MediaPlayerReleaseListener mediaPlayerReleaseListener) {
        MediaPlayerAgent mediaPlayerAgent = this.n;
        if (mediaPlayerAgent != null) {
            mediaPlayerAgent.setMediaPlayerReleaseListener(mediaPlayerReleaseListener);
        }
    }

    public void setMediaPlayerAgent(MediaPlayerAgent mediaPlayerAgent) {
        if (mediaPlayerAgent == null) {
            return;
        }
        mediaPlayerAgent.acquire();
        MediaPlayerAgent a2 = a(mediaPlayerAgent);
        if (a2 != null) {
            a2.release();
        }
    }

    public void setDefaultDuration(int i2) {
        this.n.setDefaultDuration(i2);
    }

    public void setContentId(String str) {
        this.L = str;
    }

    public void setAutoScaleResizeLayoutOnVideoSizeChange(boolean z) {
        this.I = z;
    }

    public void setAudioFocusType(int i2) {
        this.b = i2;
        this.n.a(i2);
    }

    public void seekTo(int i2) {
        this.n.seekTo(i2);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void resumeView() {
        this.B = false;
    }

    public void removeSegmentMediaStateListener(SegmentMediaStateListener segmentMediaStateListener) {
        if (segmentMediaStateListener != null) {
            this.j.remove(segmentMediaStateListener);
        }
    }

    public void removePPSVideoRenderListener(PPSVideoRenderListener pPSVideoRenderListener) {
        if (pPSVideoRenderListener == null) {
            return;
        }
        this.z.remove(pPSVideoRenderListener);
    }

    public void removeOmSegmentMediaStateListener(SegmentMediaStateListener segmentMediaStateListener) {
        if (segmentMediaStateListener != null) {
            this.y.remove(segmentMediaStateListener);
        }
    }

    public void removeNetworkChangeListener(NetworkChangeListener networkChangeListener) {
        if (networkChangeListener == null) {
            return;
        }
        this.d.remove(networkChangeListener);
    }

    public void removeMuteListener(MuteListener muteListener) {
        if (muteListener == null) {
            return;
        }
        this.g.remove(muteListener);
    }

    public void removeMediaStateListener(MediaStateListener mediaStateListener) {
        if (mediaStateListener == null) {
            return;
        }
        this.e.remove(mediaStateListener);
    }

    public void removeMediaInfoListener(com.huawei.openalliance.ad.media.listener.a aVar) {
        if (aVar == null) {
            return;
        }
        this.i.remove(aVar);
    }

    public void removeMediaErrorListener(MediaErrorListener mediaErrorListener) {
        if (mediaErrorListener == null) {
            return;
        }
        this.h.remove(mediaErrorListener);
    }

    public void removeMediaBufferListener(MediaBufferListener mediaBufferListener) {
        if (mediaBufferListener == null) {
            return;
        }
        this.f.remove(mediaBufferListener);
    }

    public void prefetch() {
        this.n.prepare();
    }

    public void play(boolean z, Long l) {
        if (this.B) {
            ho.c(this.f7792a, "play action is not performed - view paused");
            return;
        }
        ho.b(this.f7792a, "play auto: %s surfaceAvailable: %s standalone: %s url: %s", Boolean.valueOf(z), Boolean.valueOf(this.m), Boolean.valueOf(this.A), dl.a(this.D));
        if (!this.m) {
            this.l = true;
            this.r = z;
            return;
        }
        Surface surface = this.p;
        if (surface != null) {
            this.n.setSurface(surface);
        }
        if (this.A) {
            this.n.play(l);
        } else if (z) {
            this.c.autoPlay(this.D, this.n);
        } else {
            this.c.manualPlay(this.D, this.n);
        }
    }

    public void play(boolean z) {
        play(z, null);
    }

    public void play(long j) {
        play(false, Long.valueOf(j));
    }

    public void play() {
        play(false);
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void pauseView() {
        this.B = true;
        this.n.b();
    }

    public void pause() {
        ho.b(this.f7792a, "pause standalone " + this.A);
        this.l = false;
        if (this.A) {
            this.n.pause();
        } else {
            this.c.pause(this.D, this.n);
        }
    }

    @Override // com.huawei.openalliance.ad.views.AutoScaleSizeRelativeLayout
    public boolean p() {
        return this.O;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
        if (ho.a()) {
            ho.a(this.f7792a, "onSurfaceTextureSizeChanged width: %d height: %d", Integer.valueOf(i2), Integer.valueOf(i3));
        }
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.7
            @Override // java.lang.Runnable
            public void run() {
                BaseVideoView.this.x.a(BaseVideoView.this.v, BaseVideoView.this.w);
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        String str;
        String str2;
        super.onDetachedFromWindow();
        try {
            HiAd.a(getContext()).a(this.af);
        } catch (IllegalStateException unused) {
            str = this.f7792a;
            str2 = "unregisterReceiver IllegalArgumentException";
            ho.c(str, str2);
        } catch (Exception unused2) {
            str = this.f7792a;
            str2 = "unregisterReceiver Exception";
            ho.c(str, str2);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isHardwareAccelerated()) {
            ho.d(this.f7792a, "hardware acceleration is off");
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        HiAd.a(getContext()).a(this.af, intentFilter);
    }

    public void mute() {
        ho.b(this.f7792a, "mute");
        this.n.muteSound();
    }

    public boolean isPlaying() {
        return this.n.isPlaying();
    }

    public void i() {
        MediaPlayerAgent mediaPlayerAgent = this.n;
        if (mediaPlayerAgent != null) {
            mediaPlayerAgent.release();
        }
    }

    public void h() {
        this.n.b();
    }

    public int getVideoWidth() {
        return this.v;
    }

    public int getVideoHeight() {
        return this.w;
    }

    public MediaState getMediaState() {
        MediaPlayerAgent mediaPlayerAgent = this.n;
        if (mediaPlayerAgent != null) {
            return mediaPlayerAgent.getCurrentState();
        }
        return null;
    }

    public MediaPlayerAgent getMediaPlayerAgent() {
        return this.n;
    }

    public MediaState getCurrentState() {
        return this.n.getCurrentState();
    }

    public int getCurrentPosition() {
        return this.n.getCurrentPlayPosition();
    }

    public String getContentId() {
        return this.L;
    }

    protected void g() {
        SurfaceListener surfaceListener = this.H;
        if (surfaceListener != null) {
            surfaceListener.onSurfaceDestroyed();
        }
    }

    protected void f() {
        IMultiMediaPlayingManager iMultiMediaPlayingManager;
        ho.b(this.f7792a, "resetVideoView");
        if (this.n.getInstanceRefCount() <= 1) {
            this.n.setSurface(null);
            this.n.reset();
            if (!this.A && (iMultiMediaPlayingManager = this.c) != null) {
                iMultiMediaPlayingManager.removeMediaPlayerAgent(this.n);
            }
        }
        MediaPlayerAgent mediaPlayerAgent = this.o;
        if (mediaPlayerAgent != null) {
            mediaPlayerAgent.setSurface(null);
            this.o.reset();
        }
        Surface surface = this.p;
        if (surface != null) {
            surface.release();
            this.p = null;
        }
        SurfaceTexture surfaceTexture = this.q;
        if (surfaceTexture != null) {
            surfaceTexture.release();
        }
        this.q = null;
        this.l = false;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void destroyView() {
        this.n.removeVideoSizeChangeListener(this.u);
        if (!this.A) {
            this.c.removeMediaPlayerAgent(this.n);
        }
        this.n.release();
        MediaPlayerAgent mediaPlayerAgent = this.o;
        if (mediaPlayerAgent != null) {
            mediaPlayerAgent.release();
        }
    }

    public void d() {
        TextureView textureView = this.k;
        if (textureView != null) {
            textureView.setSurfaceTextureListener(null);
            ViewParent parent = this.k.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.k);
            }
            TextureView textureView2 = new TextureView(getContext());
            this.k = textureView2;
            textureView2.setSurfaceTextureListener(this);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(13);
            addView(this.k, layoutParams);
        }
    }

    public void c(int i2, int i3) {
        this.n.seekTo(i2, i3);
    }

    public void addSegmentMediaStateListener(SegmentMediaStateListener segmentMediaStateListener) {
        if (segmentMediaStateListener != null) {
            this.j.add(segmentMediaStateListener);
        }
    }

    public void addReportVideoTimeListenersSet(ReportVideoTimeListener reportVideoTimeListener) {
        if (this.P == null) {
            this.P = new com.huawei.openalliance.ad.media.b(this.M);
        }
        this.P.a(reportVideoTimeListener);
    }

    public void addPPSVideoRenderListener(PPSVideoRenderListener pPSVideoRenderListener) {
        if (pPSVideoRenderListener == null) {
            return;
        }
        this.z.add(pPSVideoRenderListener);
    }

    public void addOmSegmentMediaStateListener(SegmentMediaStateListener segmentMediaStateListener) {
        if (segmentMediaStateListener != null) {
            this.y.add(segmentMediaStateListener);
        }
    }

    public void addNetworkChangeListener(NetworkChangeListener networkChangeListener) {
        if (networkChangeListener == null) {
            return;
        }
        this.d.add(networkChangeListener);
    }

    public void addMuteListener(MuteListener muteListener) {
        if (muteListener == null) {
            return;
        }
        this.g.add(muteListener);
    }

    public void addMediaStateListener(MediaStateListener mediaStateListener) {
        if (mediaStateListener == null) {
            return;
        }
        this.e.add(mediaStateListener);
    }

    public void addMediaInfoListener(com.huawei.openalliance.ad.media.listener.a aVar) {
        if (aVar == null) {
            return;
        }
        this.i.add(aVar);
    }

    public void addMediaErrorListener(MediaErrorListener mediaErrorListener) {
        if (mediaErrorListener == null) {
            return;
        }
        this.h.add(mediaErrorListener);
    }

    public void addMediaBufferListener(MediaBufferListener mediaBufferListener) {
        if (mediaBufferListener == null) {
            return;
        }
        this.f.add(mediaBufferListener);
    }

    public void a(String str, boolean z) {
        if (z) {
            str = a(str);
        }
        setVideoFileUrls(new String[]{str});
    }

    public void a(MediaPlayerReleaseListener mediaPlayerReleaseListener) {
        MediaPlayerAgent mediaPlayerAgent = this.n;
        if (mediaPlayerAgent != null) {
            mediaPlayerAgent.removeMediaPlayerReleaseListener(mediaPlayerReleaseListener);
        }
    }

    protected void a(float f2, float f3, int i2, int i3) {
        Matrix matrix;
        float f4;
        if (this.k == null) {
            return;
        }
        float f5 = 1.0f;
        float f6 = (i2 * 1.0f) / 2.0f;
        float f7 = (i3 * 1.0f) / 2.0f;
        int i4 = this.s;
        if (i4 == 1) {
            ho.b(this.f7792a, "set video scale mode as fit");
            matrix = new Matrix();
            matrix.setScale(1.0f, 1.0f, f6, f7);
        } else {
            if (i4 != 2) {
                return;
            }
            ho.b(this.f7792a, "set video scale mode as fit with cropping");
            if (f3 < f2) {
                float f8 = f2 / f3;
                f4 = 1.0f;
                f5 = f8;
            } else {
                f4 = f3 / f2;
            }
            ho.a(this.f7792a, "calculateScaleMatrix scaleX: %s scaleY: %s pivotPointX: %s pivotPointY: %s", Float.valueOf(f5), Float.valueOf(f4), Float.valueOf(f6), Float.valueOf(f7));
            matrix = new Matrix();
            matrix.setScale(f5, f4, f6, f7);
        }
        this.k.setTransform(matrix);
    }

    public void a(float f2) {
        ho.b(this.f7792a, "unmute, volume: %s", Float.valueOf(f2));
        this.n.a(f2);
    }

    protected MediaPlayerAgent a(MediaPlayerAgent mediaPlayerAgent) {
        if (mediaPlayerAgent == null) {
            ho.c(this.f7792a, "no agent to switch");
            return null;
        }
        MediaPlayerAgent mediaPlayerAgent2 = this.n;
        if (mediaPlayerAgent2 != null) {
            mediaPlayerAgent2.removeMediaStateListener(this.W);
            mediaPlayerAgent2.removeMediaBufferListener(this.aa);
            mediaPlayerAgent2.removePPSVideoRenderListener(this.ab);
            mediaPlayerAgent2.removeMediaErrorListener(this.ac);
            mediaPlayerAgent2.removeMuteListener(this.ad);
            mediaPlayerAgent2.removeMediaInfoListener(this.ae);
            mediaPlayerAgent2.setSurface(null);
        }
        mediaPlayerAgent.addMediaStateListener(this.W);
        mediaPlayerAgent.addMediaBufferListener(this.aa);
        mediaPlayerAgent.a(this.ab);
        mediaPlayerAgent.addMediaErrorListener(this.ac);
        mediaPlayerAgent.addMuteListener(this.ad);
        mediaPlayerAgent.addMediaInfoListener(this.ae);
        mediaPlayerAgent.a(this.K);
        mediaPlayerAgent.a(this.b);
        Surface surface = this.p;
        if (surface != null) {
            mediaPlayerAgent.setSurface(surface);
        }
        this.n = mediaPlayerAgent;
        return mediaPlayerAgent2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        if (this.C) {
            setKeepScreenOn(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        Iterator<MuteListener> it = this.g.iterator();
        while (it.hasNext()) {
            it.next().onUnmute();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        Iterator<MuteListener> it = this.g.iterator();
        while (it.hasNext()) {
            it.next().onMute();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (ho.a()) {
            ho.a(this.f7792a, "notifyNetworkDisconnected");
        }
        Iterator<NetworkChangeListener> it = this.d.iterator();
        while (it.hasNext()) {
            it.next().onNetworkDisconnected();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        Iterator<PPSVideoRenderListener> it = this.z.iterator();
        while (it.hasNext()) {
            it.next().onVideoRenderStart();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        Iterator<MediaBufferListener> it = this.f.iterator();
        while (it.hasNext()) {
            it.next().onBufferingEnd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        Iterator<MediaBufferListener> it = this.f.iterator();
        while (it.hasNext()) {
            it.next().onBufferingStart();
        }
    }

    private int getVideoFileUrlArrayLength() {
        String[] strArr = this.E;
        if (strArr != null) {
            return strArr.length;
        }
        return 0;
    }

    private String getNextVideoUrl() {
        int i2 = this.F + 1;
        if (i2 < getVideoFileUrlArrayLength()) {
            return this.E[i2];
        }
        return null;
    }

    private MediaPlayerAgent getNextPlayerAgent() {
        if (this.o == null) {
            MediaPlayerAgent mediaPlayerAgent = new MediaPlayerAgent(getContext());
            this.o = mediaPlayerAgent;
            mediaPlayerAgent.acquire();
        }
        return this.o;
    }

    private String getCurrentVideoUrl() {
        if (this.F < getVideoFileUrlArrayLength()) {
            return this.E[this.F];
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(int i2) {
        Iterator<com.huawei.openalliance.ad.media.listener.a> it = this.i.iterator();
        while (it.hasNext()) {
            it.next().b(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(int i2) {
        Iterator<com.huawei.openalliance.ad.media.listener.a> it = this.i.iterator();
        while (it.hasNext()) {
            it.next().a(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e() {
        String nextVideoUrl;
        int i2 = this.F + 1;
        if (!this.G.get(i2) || (nextVideoUrl = getNextVideoUrl()) == null) {
            ho.b(this.f7792a, "no next player to switch, current: %d", Integer.valueOf(this.F));
            return false;
        }
        this.D = nextVideoUrl;
        this.o = a(getNextPlayerAgent());
        if (!TextUtils.equals(nextVideoUrl, this.n.a())) {
            this.n.setMediaFile(nextVideoUrl);
        }
        if (this.J) {
            this.n.muteSound();
        } else {
            this.n.unmuteSound();
        }
        this.n.play();
        this.F = i2;
        ho.b(this.f7792a, "switch to next player [%d] and play", Integer.valueOf(i2));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i2) {
        Iterator<SegmentMediaStateListener> it = this.y.iterator();
        while (it.hasNext()) {
            it.next().onSegmentMediaCompletion(getContentId(), getCurrentVideoUrl(), i2);
        }
        Iterator<SegmentMediaStateListener> it2 = this.j.iterator();
        while (it2.hasNext()) {
            it2.next().onSegmentMediaCompletion(getContentId(), getCurrentVideoUrl(), i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(MediaPlayerAgent mediaPlayerAgent, int i2) {
        Iterator<MediaStateListener> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().onMediaCompletion(mediaPlayerAgent, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i2) {
        Iterator<SegmentMediaStateListener> it = this.y.iterator();
        while (it.hasNext()) {
            it.next().onSegmentMediaStop(getContentId(), getCurrentVideoUrl(), i2);
        }
        Iterator<SegmentMediaStateListener> it2 = this.j.iterator();
        while (it2.hasNext()) {
            it2.next().onSegmentMediaStop(getContentId(), getCurrentVideoUrl(), i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(MediaPlayerAgent mediaPlayerAgent, int i2) {
        Iterator<MediaStateListener> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().onMediaStop(mediaPlayerAgent, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i2) {
        Iterator<SegmentMediaStateListener> it = this.y.iterator();
        while (it.hasNext()) {
            it.next().onSegmentMediaPause(getContentId(), getCurrentVideoUrl(), i2);
        }
        Iterator<SegmentMediaStateListener> it2 = this.j.iterator();
        while (it2.hasNext()) {
            it2.next().onSegmentMediaPause(getContentId(), getCurrentVideoUrl(), i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(MediaPlayerAgent mediaPlayerAgent, int i2) {
        Iterator<MediaStateListener> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().onMediaPause(mediaPlayerAgent, i2);
        }
    }

    private void b(Context context) {
        this.M = context;
        setBackgroundColor(-16777216);
        this.c = HiAd.a(context).c();
        setMediaPlayerAgent(new MediaPlayerAgent(context));
        a(context);
        this.N = dh.a(context, "normal");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i2, int i3) {
        Iterator<SegmentMediaStateListener> it = this.y.iterator();
        while (it.hasNext()) {
            it.next().onSegmentProgress(getContentId(), getCurrentVideoUrl(), i2, i3);
        }
        Iterator<SegmentMediaStateListener> it2 = this.j.iterator();
        while (it2.hasNext()) {
            it2.next().onSegmentProgress(getContentId(), getCurrentVideoUrl(), i2, i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i2) {
        Iterator<SegmentMediaStateListener> it = this.y.iterator();
        while (it.hasNext()) {
            it.next().onSegmentMediaStart(getContentId(), getCurrentVideoUrl(), i2);
        }
        Iterator<SegmentMediaStateListener> it2 = this.j.iterator();
        while (it2.hasNext()) {
            it2.next().onSegmentMediaStart(getContentId(), getCurrentVideoUrl(), i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (ho.a()) {
            ho.a(this.f7792a, "notifyNetworkConnectedOrChanged wifi: %s", Boolean.valueOf(z));
        }
        Iterator<NetworkChangeListener> it = this.d.iterator();
        while (it.hasNext()) {
            it.next().onNetworkConnectedOrChanged(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(MediaPlayerAgent mediaPlayerAgent, int i2, int i3, int i4) {
        Iterator<MediaErrorListener> it = this.h.iterator();
        while (it.hasNext()) {
            it.next().onError(mediaPlayerAgent, i2, i3, i4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(MediaPlayerAgent mediaPlayerAgent, int i2) {
        Iterator<MediaStateListener> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().onMediaStart(mediaPlayerAgent, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, int i3, int i4) {
        Iterator<SegmentMediaStateListener> it = this.j.iterator();
        while (it.hasNext()) {
            it.next().onSegmentMediaError(getContentId(), getCurrentVideoUrl(), i2, i3, i4);
        }
    }

    /* loaded from: classes9.dex */
    static class e implements MediaStateListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<MediaStateListener> f7807a;

        @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
        public void onProgress(int i, int i2) {
            MediaStateListener mediaStateListener = this.f7807a.get();
            if (mediaStateListener != null) {
                mediaStateListener.onProgress(i, i2);
            }
        }

        @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
        public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i) {
            MediaStateListener mediaStateListener = this.f7807a.get();
            if (mediaStateListener != null) {
                mediaStateListener.onMediaStop(mediaPlayerAgent, i);
            }
        }

        @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
        public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i) {
            MediaStateListener mediaStateListener = this.f7807a.get();
            if (mediaStateListener != null) {
                mediaStateListener.onMediaStart(mediaPlayerAgent, i);
            }
        }

        @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
        public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i) {
            MediaStateListener mediaStateListener = this.f7807a.get();
            if (mediaStateListener != null) {
                mediaStateListener.onMediaPause(mediaPlayerAgent, i);
            }
        }

        @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
        public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i) {
            MediaStateListener mediaStateListener = this.f7807a.get();
            if (mediaStateListener != null) {
                mediaStateListener.onMediaCompletion(mediaPlayerAgent, i);
            }
        }

        e(MediaStateListener mediaStateListener) {
            this.f7807a = new WeakReference<>(mediaStateListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, int i3) {
        Iterator<MediaStateListener> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().onProgress(i2, i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2) {
        Iterator<MediaBufferListener> it = this.f.iterator();
        while (it.hasNext()) {
            it.next().onBufferUpdate(i2);
        }
    }

    /* loaded from: classes9.dex */
    static class b implements MediaBufferListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<MediaBufferListener> f7804a;

        @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
        public void onBufferingStart() {
            MediaBufferListener mediaBufferListener = this.f7804a.get();
            if (mediaBufferListener != null) {
                mediaBufferListener.onBufferingStart();
            }
        }

        @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
        public void onBufferingEnd() {
            MediaBufferListener mediaBufferListener = this.f7804a.get();
            if (mediaBufferListener != null) {
                mediaBufferListener.onBufferingEnd();
            }
        }

        @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
        public void onBufferUpdate(int i) {
            MediaBufferListener mediaBufferListener = this.f7804a.get();
            if (mediaBufferListener != null) {
                mediaBufferListener.onBufferUpdate(i);
            }
        }

        b(MediaBufferListener mediaBufferListener) {
            this.f7804a = new WeakReference<>(mediaBufferListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        String nextVideoUrl = getNextVideoUrl();
        if (nextVideoUrl == null) {
            ho.b(this.f7792a, "no next video url need to prepare, current: %d", Integer.valueOf(this.F));
            return;
        }
        int i2 = this.F + 1;
        if (this.G.get(i2)) {
            ho.b(this.f7792a, "player for url %d is already set", Integer.valueOf(i2));
            return;
        }
        ho.b(this.f7792a, "prepare to set next player[%d]", Integer.valueOf(i2));
        MediaPlayerAgent nextPlayerAgent = getNextPlayerAgent();
        nextPlayerAgent.setMediaFile(nextVideoUrl);
        nextPlayerAgent.prepare();
        this.G.put(i2, true);
    }

    /* loaded from: classes9.dex */
    static class a extends ConnectivityManager.NetworkCallback {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<BaseVideoView> f7801a;

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            final BaseVideoView baseVideoView = this.f7801a.get();
            if (baseVideoView == null) {
                return;
            }
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.a.2
                @Override // java.lang.Runnable
                public void run() {
                    baseVideoView.m();
                }
            });
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            final BaseVideoView baseVideoView = this.f7801a.get();
            if (baseVideoView == null) {
                return;
            }
            final boolean c = bv.c(baseVideoView.getContext());
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.a.1
                @Override // java.lang.Runnable
                public void run() {
                    baseVideoView.a(c);
                }
            });
        }

        public a(BaseVideoView baseVideoView) {
            this.f7801a = new WeakReference<>(baseVideoView);
        }
    }

    /* loaded from: classes9.dex */
    static class d implements com.huawei.openalliance.ad.media.listener.a {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<com.huawei.openalliance.ad.media.listener.a> f7806a;

        @Override // com.huawei.openalliance.ad.media.listener.a
        public void b(int i) {
            com.huawei.openalliance.ad.media.listener.a aVar = this.f7806a.get();
            if (aVar != null) {
                aVar.b(i);
            }
        }

        @Override // com.huawei.openalliance.ad.media.listener.a
        public void a(int i) {
            com.huawei.openalliance.ad.media.listener.a aVar = this.f7806a.get();
            if (aVar != null) {
                aVar.a(i);
            }
        }

        d(com.huawei.openalliance.ad.media.listener.a aVar) {
            this.f7806a = new WeakReference<>(aVar);
        }
    }

    /* loaded from: classes9.dex */
    static class f implements MuteListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<MuteListener> f7808a;

        @Override // com.huawei.openalliance.ad.media.listener.MuteListener
        public void onUnmute() {
            MuteListener muteListener = this.f7808a.get();
            if (muteListener != null) {
                muteListener.onUnmute();
            }
        }

        @Override // com.huawei.openalliance.ad.media.listener.MuteListener
        public void onMute() {
            MuteListener muteListener = this.f7808a.get();
            if (muteListener != null) {
                muteListener.onMute();
            }
        }

        f(MuteListener muteListener) {
            this.f7808a = new WeakReference<>(muteListener);
        }
    }

    class i implements MediaPlayer.OnVideoSizeChangedListener {

        /* renamed from: a, reason: collision with root package name */
        float f7811a = 0.0f;
        float b = 0.0f;

        @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
        public void onVideoSizeChanged(MediaPlayer mediaPlayer, final int i, final int i2) {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.i.1
                @Override // java.lang.Runnable
                public void run() {
                    i.this.a(i, i2);
                }
            });
        }

        void a(int i, int i2) {
            ho.b(BaseVideoView.this.f7792a, "video size changed - w: " + i + " h: " + i2);
            if (i == 0 || i2 == 0) {
                return;
            }
            BaseVideoView.this.v = i;
            BaseVideoView.this.w = i2;
            float f = (i * 1.0f) / i2;
            float abs = Math.abs(f - this.f7811a);
            if (ho.a()) {
                ho.a(BaseVideoView.this.f7792a, "video ratio: %s oldRatio: %s diff: %s", Float.valueOf(f), Float.valueOf(this.f7811a), Float.valueOf(abs));
            }
            this.f7811a = f;
            if (BaseVideoView.this.I) {
                if (abs > 0.01f) {
                    ho.a(BaseVideoView.this.f7792a, "set video ratio");
                    BaseVideoView.this.setRatio(Float.valueOf(f));
                    BaseVideoView.this.requestLayout();
                    return;
                }
                return;
            }
            int width = BaseVideoView.this.getWidth();
            int height = BaseVideoView.this.getHeight();
            ho.b(BaseVideoView.this.f7792a, "resizeVideo view width: " + width + " height: " + height);
            if (height == 0 || width == 0) {
                return;
            }
            float f2 = (width * 1.0f) / height;
            float abs2 = Math.abs(f2 - this.b);
            if (ho.a()) {
                ho.a(BaseVideoView.this.f7792a, "view ratio: %s oldRatio: %s diff: %s", Float.valueOf(f2), Float.valueOf(this.b), Float.valueOf(abs2));
            }
            this.b = f2;
            if (abs2 > 0.01f) {
                BaseVideoView.this.a(f, f2, width, height);
            }
        }

        i() {
        }
    }

    private String a(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String str3 = "video cached, play from local.";
        if (str.startsWith(Scheme.DISKCACHE.toString()) || str.startsWith(Scheme.DATAPARTITION.toString())) {
            str2 = this.f7792a;
        } else {
            String c2 = this.N.c(dk.d(str));
            if (!TextUtils.isEmpty(c2) && com.huawei.openalliance.ad.utils.ae.b(this.M, c2)) {
                ho.b(this.f7792a, "video cached, play from local.");
                return c2;
            }
            str2 = this.f7792a;
            str3 = "video not cached, play from net.";
        }
        ho.b(str2, str3);
        return str;
    }

    /* loaded from: classes9.dex */
    static class c implements MediaErrorListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<MediaErrorListener> f7805a;

        @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
        public void onError(MediaPlayerAgent mediaPlayerAgent, int i, int i2, int i3) {
            MediaErrorListener mediaErrorListener = this.f7805a.get();
            if (mediaErrorListener != null) {
                mediaErrorListener.onError(mediaPlayerAgent, i, i2, i3);
            }
        }

        c(MediaErrorListener mediaErrorListener) {
            this.f7805a = new WeakReference<>(mediaErrorListener);
        }
    }

    /* loaded from: classes9.dex */
    static class g implements PPSVideoRenderListener {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<PPSVideoRenderListener> f7809a;

        @Override // com.huawei.openalliance.ad.media.listener.PPSVideoRenderListener
        public void onVideoRenderStart() {
            PPSVideoRenderListener pPSVideoRenderListener = this.f7809a.get();
            if (pPSVideoRenderListener != null) {
                pPSVideoRenderListener.onVideoRenderStart();
            }
        }

        public g(PPSVideoRenderListener pPSVideoRenderListener) {
            this.f7809a = new WeakReference<>(pPSVideoRenderListener);
        }
    }

    static class h implements MediaPlayer.OnVideoSizeChangedListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<MediaPlayer.OnVideoSizeChangedListener> f7810a;

        @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
        public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
            MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener = this.f7810a.get();
            if (onVideoSizeChangedListener != null) {
                onVideoSizeChangedListener.onVideoSizeChanged(mediaPlayer, i, i2);
            }
        }

        h(MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener) {
            this.f7810a = new WeakReference<>(onVideoSizeChangedListener);
        }
    }

    public BaseVideoView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f7792a = getClass().getSimpleName();
        this.b = 0;
        this.d = new CopyOnWriteArraySet();
        this.e = new CopyOnWriteArraySet();
        this.f = new CopyOnWriteArraySet();
        this.g = new CopyOnWriteArraySet();
        this.h = new CopyOnWriteArraySet();
        this.i = new CopyOnWriteArraySet();
        this.j = new CopyOnWriteArraySet();
        this.y = new CopyOnWriteArraySet();
        this.z = new CopyOnWriteArraySet();
        this.A = true;
        this.B = false;
        this.C = false;
        this.G = new SparseBooleanArray(3);
        this.s = 1;
        this.I = true;
        this.t = true;
        this.J = false;
        this.O = false;
        this.x = new i();
        this.Q = new MediaStateListener() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.1
            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onProgress(int i3, int i4) {
                BaseVideoView.this.b(i3, i4);
                BaseVideoView.this.a(i3, i4);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i3) {
                BaseVideoView.this.q();
                BaseVideoView.this.d(i3);
                BaseVideoView.this.c(mediaPlayerAgent, i3);
                if (BaseVideoView.this.P != null) {
                    BaseVideoView.this.P.a(i3);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i3) {
                if (BaseVideoView.this.C) {
                    BaseVideoView.this.setKeepScreenOn(true);
                }
                BaseVideoView.this.a();
                BaseVideoView.this.b(i3);
                BaseVideoView.this.a(mediaPlayerAgent, i3);
                if (BaseVideoView.this.P != null) {
                    BaseVideoView.this.P.a();
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i3) {
                BaseVideoView.this.q();
                BaseVideoView.this.c(i3);
                BaseVideoView.this.b(mediaPlayerAgent, i3);
                if (BaseVideoView.this.P != null) {
                    BaseVideoView.this.P.b(i3);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i3) {
                BaseVideoView.this.e(i3);
                if (BaseVideoView.this.e()) {
                    return;
                }
                BaseVideoView.this.q();
                BaseVideoView.this.d(mediaPlayerAgent, i3);
                if (BaseVideoView.this.P != null) {
                    BaseVideoView.this.P.a(i3);
                }
            }
        };
        this.R = new MediaBufferListener() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.2
            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingStart() {
                BaseVideoView.this.j();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingEnd() {
                BaseVideoView.this.k();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferUpdate(int i3) {
                BaseVideoView.this.a(i3);
            }
        };
        this.S = new PPSVideoRenderListener() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.3
            @Override // com.huawei.openalliance.ad.media.listener.PPSVideoRenderListener
            public void onVideoRenderStart() {
                BaseVideoView.this.l();
            }
        };
        this.T = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.4
            @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
            public void onError(MediaPlayerAgent mediaPlayerAgent, int i3, int i4, int i5) {
                BaseVideoView.this.q();
                BaseVideoView.this.a(i3, i4, i5);
                BaseVideoView.this.a(mediaPlayerAgent, i3, i4, i5);
            }
        };
        this.U = new MuteListener() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.5
            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onUnmute() {
                BaseVideoView.this.J = false;
                BaseVideoView.this.o();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onMute() {
                BaseVideoView.this.J = true;
                BaseVideoView.this.n();
            }
        };
        this.V = new com.huawei.openalliance.ad.media.listener.a() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.6
            @Override // com.huawei.openalliance.ad.media.listener.a
            public void b(int i3) {
                BaseVideoView.this.g(i3);
            }

            @Override // com.huawei.openalliance.ad.media.listener.a
            public void a(int i3) {
                BaseVideoView.this.f(i3);
            }
        };
        this.W = new e(this.Q);
        this.aa = new b(this.R);
        this.ab = new g(this.S);
        this.ac = new c(this.T);
        this.ad = new f(this.U);
        this.ae = new d(this.V);
        this.af = new BroadcastReceiver() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.8
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (TextUtils.equals("android.net.conn.CONNECTIVITY_CHANGE", intent.getAction())) {
                    try {
                        ConnectivityManager connectivityManager = (ConnectivityManager) context2.getSystemService("connectivity");
                        if (connectivityManager == null) {
                            return;
                        }
                        ho.b(BaseVideoView.this.f7792a, "Build.VERSION: %s", Integer.valueOf(Build.VERSION.SDK_INT));
                        if (Build.VERSION.SDK_INT < 29) {
                            NetworkInfo a2 = bv.a(connectivityManager);
                            if (a2 == null || !a2.isConnected()) {
                                BaseVideoView.this.m();
                            } else {
                                BaseVideoView.this.a(bv.c(context2));
                            }
                        } else {
                            connectivityManager.registerDefaultNetworkCallback(new a(BaseVideoView.this));
                        }
                    } catch (Throwable unused) {
                        ho.c(BaseVideoView.this.f7792a, "fail to get networkChangeReceiver");
                    }
                }
            }
        };
        b(context);
    }

    public BaseVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7792a = getClass().getSimpleName();
        this.b = 0;
        this.d = new CopyOnWriteArraySet();
        this.e = new CopyOnWriteArraySet();
        this.f = new CopyOnWriteArraySet();
        this.g = new CopyOnWriteArraySet();
        this.h = new CopyOnWriteArraySet();
        this.i = new CopyOnWriteArraySet();
        this.j = new CopyOnWriteArraySet();
        this.y = new CopyOnWriteArraySet();
        this.z = new CopyOnWriteArraySet();
        this.A = true;
        this.B = false;
        this.C = false;
        this.G = new SparseBooleanArray(3);
        this.s = 1;
        this.I = true;
        this.t = true;
        this.J = false;
        this.O = false;
        this.x = new i();
        this.Q = new MediaStateListener() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.1
            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onProgress(int i3, int i4) {
                BaseVideoView.this.b(i3, i4);
                BaseVideoView.this.a(i3, i4);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i3) {
                BaseVideoView.this.q();
                BaseVideoView.this.d(i3);
                BaseVideoView.this.c(mediaPlayerAgent, i3);
                if (BaseVideoView.this.P != null) {
                    BaseVideoView.this.P.a(i3);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i3) {
                if (BaseVideoView.this.C) {
                    BaseVideoView.this.setKeepScreenOn(true);
                }
                BaseVideoView.this.a();
                BaseVideoView.this.b(i3);
                BaseVideoView.this.a(mediaPlayerAgent, i3);
                if (BaseVideoView.this.P != null) {
                    BaseVideoView.this.P.a();
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i3) {
                BaseVideoView.this.q();
                BaseVideoView.this.c(i3);
                BaseVideoView.this.b(mediaPlayerAgent, i3);
                if (BaseVideoView.this.P != null) {
                    BaseVideoView.this.P.b(i3);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i3) {
                BaseVideoView.this.e(i3);
                if (BaseVideoView.this.e()) {
                    return;
                }
                BaseVideoView.this.q();
                BaseVideoView.this.d(mediaPlayerAgent, i3);
                if (BaseVideoView.this.P != null) {
                    BaseVideoView.this.P.a(i3);
                }
            }
        };
        this.R = new MediaBufferListener() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.2
            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingStart() {
                BaseVideoView.this.j();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingEnd() {
                BaseVideoView.this.k();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferUpdate(int i3) {
                BaseVideoView.this.a(i3);
            }
        };
        this.S = new PPSVideoRenderListener() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.3
            @Override // com.huawei.openalliance.ad.media.listener.PPSVideoRenderListener
            public void onVideoRenderStart() {
                BaseVideoView.this.l();
            }
        };
        this.T = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.4
            @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
            public void onError(MediaPlayerAgent mediaPlayerAgent, int i3, int i4, int i5) {
                BaseVideoView.this.q();
                BaseVideoView.this.a(i3, i4, i5);
                BaseVideoView.this.a(mediaPlayerAgent, i3, i4, i5);
            }
        };
        this.U = new MuteListener() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.5
            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onUnmute() {
                BaseVideoView.this.J = false;
                BaseVideoView.this.o();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onMute() {
                BaseVideoView.this.J = true;
                BaseVideoView.this.n();
            }
        };
        this.V = new com.huawei.openalliance.ad.media.listener.a() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.6
            @Override // com.huawei.openalliance.ad.media.listener.a
            public void b(int i3) {
                BaseVideoView.this.g(i3);
            }

            @Override // com.huawei.openalliance.ad.media.listener.a
            public void a(int i3) {
                BaseVideoView.this.f(i3);
            }
        };
        this.W = new e(this.Q);
        this.aa = new b(this.R);
        this.ab = new g(this.S);
        this.ac = new c(this.T);
        this.ad = new f(this.U);
        this.ae = new d(this.V);
        this.af = new BroadcastReceiver() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.8
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (TextUtils.equals("android.net.conn.CONNECTIVITY_CHANGE", intent.getAction())) {
                    try {
                        ConnectivityManager connectivityManager = (ConnectivityManager) context2.getSystemService("connectivity");
                        if (connectivityManager == null) {
                            return;
                        }
                        ho.b(BaseVideoView.this.f7792a, "Build.VERSION: %s", Integer.valueOf(Build.VERSION.SDK_INT));
                        if (Build.VERSION.SDK_INT < 29) {
                            NetworkInfo a2 = bv.a(connectivityManager);
                            if (a2 == null || !a2.isConnected()) {
                                BaseVideoView.this.m();
                            } else {
                                BaseVideoView.this.a(bv.c(context2));
                            }
                        } else {
                            connectivityManager.registerDefaultNetworkCallback(new a(BaseVideoView.this));
                        }
                    } catch (Throwable unused) {
                        ho.c(BaseVideoView.this.f7792a, "fail to get networkChangeReceiver");
                    }
                }
            }
        };
        b(context);
    }

    public BaseVideoView(Context context) {
        super(context);
        this.f7792a = getClass().getSimpleName();
        this.b = 0;
        this.d = new CopyOnWriteArraySet();
        this.e = new CopyOnWriteArraySet();
        this.f = new CopyOnWriteArraySet();
        this.g = new CopyOnWriteArraySet();
        this.h = new CopyOnWriteArraySet();
        this.i = new CopyOnWriteArraySet();
        this.j = new CopyOnWriteArraySet();
        this.y = new CopyOnWriteArraySet();
        this.z = new CopyOnWriteArraySet();
        this.A = true;
        this.B = false;
        this.C = false;
        this.G = new SparseBooleanArray(3);
        this.s = 1;
        this.I = true;
        this.t = true;
        this.J = false;
        this.O = false;
        this.x = new i();
        this.Q = new MediaStateListener() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.1
            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onProgress(int i3, int i4) {
                BaseVideoView.this.b(i3, i4);
                BaseVideoView.this.a(i3, i4);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i3) {
                BaseVideoView.this.q();
                BaseVideoView.this.d(i3);
                BaseVideoView.this.c(mediaPlayerAgent, i3);
                if (BaseVideoView.this.P != null) {
                    BaseVideoView.this.P.a(i3);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i3) {
                if (BaseVideoView.this.C) {
                    BaseVideoView.this.setKeepScreenOn(true);
                }
                BaseVideoView.this.a();
                BaseVideoView.this.b(i3);
                BaseVideoView.this.a(mediaPlayerAgent, i3);
                if (BaseVideoView.this.P != null) {
                    BaseVideoView.this.P.a();
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i3) {
                BaseVideoView.this.q();
                BaseVideoView.this.c(i3);
                BaseVideoView.this.b(mediaPlayerAgent, i3);
                if (BaseVideoView.this.P != null) {
                    BaseVideoView.this.P.b(i3);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i3) {
                BaseVideoView.this.e(i3);
                if (BaseVideoView.this.e()) {
                    return;
                }
                BaseVideoView.this.q();
                BaseVideoView.this.d(mediaPlayerAgent, i3);
                if (BaseVideoView.this.P != null) {
                    BaseVideoView.this.P.a(i3);
                }
            }
        };
        this.R = new MediaBufferListener() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.2
            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingStart() {
                BaseVideoView.this.j();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingEnd() {
                BaseVideoView.this.k();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferUpdate(int i3) {
                BaseVideoView.this.a(i3);
            }
        };
        this.S = new PPSVideoRenderListener() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.3
            @Override // com.huawei.openalliance.ad.media.listener.PPSVideoRenderListener
            public void onVideoRenderStart() {
                BaseVideoView.this.l();
            }
        };
        this.T = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.4
            @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
            public void onError(MediaPlayerAgent mediaPlayerAgent, int i3, int i4, int i5) {
                BaseVideoView.this.q();
                BaseVideoView.this.a(i3, i4, i5);
                BaseVideoView.this.a(mediaPlayerAgent, i3, i4, i5);
            }
        };
        this.U = new MuteListener() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.5
            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onUnmute() {
                BaseVideoView.this.J = false;
                BaseVideoView.this.o();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onMute() {
                BaseVideoView.this.J = true;
                BaseVideoView.this.n();
            }
        };
        this.V = new com.huawei.openalliance.ad.media.listener.a() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.6
            @Override // com.huawei.openalliance.ad.media.listener.a
            public void b(int i3) {
                BaseVideoView.this.g(i3);
            }

            @Override // com.huawei.openalliance.ad.media.listener.a
            public void a(int i3) {
                BaseVideoView.this.f(i3);
            }
        };
        this.W = new e(this.Q);
        this.aa = new b(this.R);
        this.ab = new g(this.S);
        this.ac = new c(this.T);
        this.ad = new f(this.U);
        this.ae = new d(this.V);
        this.af = new BroadcastReceiver() { // from class: com.huawei.openalliance.ad.views.BaseVideoView.8
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (TextUtils.equals("android.net.conn.CONNECTIVITY_CHANGE", intent.getAction())) {
                    try {
                        ConnectivityManager connectivityManager = (ConnectivityManager) context2.getSystemService("connectivity");
                        if (connectivityManager == null) {
                            return;
                        }
                        ho.b(BaseVideoView.this.f7792a, "Build.VERSION: %s", Integer.valueOf(Build.VERSION.SDK_INT));
                        if (Build.VERSION.SDK_INT < 29) {
                            NetworkInfo a2 = bv.a(connectivityManager);
                            if (a2 == null || !a2.isConnected()) {
                                BaseVideoView.this.m();
                            } else {
                                BaseVideoView.this.a(bv.c(context2));
                            }
                        } else {
                            connectivityManager.registerDefaultNetworkCallback(new a(BaseVideoView.this));
                        }
                    } catch (Throwable unused) {
                        ho.c(BaseVideoView.this.f7792a, "fail to get networkChangeReceiver");
                    }
                }
            }
        };
        b(context);
    }
}
