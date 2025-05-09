package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.RewardMethods;
import com.huawei.openalliance.ad.dh;
import com.huawei.openalliance.ad.dk;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.inter.data.IPlacementAd;
import com.huawei.openalliance.ad.inter.data.PlacementMediaFile;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.iq;
import com.huawei.openalliance.ad.jg;
import com.huawei.openalliance.ad.lm;
import com.huawei.openalliance.ad.lo;
import com.huawei.openalliance.ad.lz;
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
import com.huawei.openalliance.ad.no;
import com.huawei.openalliance.ad.od;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.views.interfaces.IPlacementVideoView;
import com.huawei.operation.utils.Constants;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class z extends PlacementMediaView implements lm, IPlacementVideoView {
    private lz g;
    private od h;
    private VideoView i;
    private boolean j;
    private PlacementMediaFile k;
    private boolean l;
    private boolean m;
    private long n;
    private long o;
    private long p;
    private boolean q;
    private boolean r;
    private int s;
    private jg t;
    private com.huawei.openalliance.ad.media.b u;
    private MediaBufferListener v;
    private MediaStateListener w;
    private MuteListener x;
    private MediaErrorListener y;

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView, com.huawei.openalliance.ad.lm
    public View getOpenMeasureView() {
        return this;
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void setSoundVolume(float f) {
        this.i.setSoundVolume(f);
    }

    public void setPreferStartPlayTime(int i) {
        this.s = i;
        this.i.setPreferStartPlayTime(i);
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void setPlacementAd(IPlacementAd iPlacementAd) {
        MediaState currentState = this.i.getCurrentState();
        if (this.f7996a == iPlacementAd && currentState.isNotState(MediaState.State.IDLE) && currentState.isNotState(MediaState.State.ERROR)) {
            ho.b(getTAG(), "setPlacementVideoAd - has the same ad");
            return;
        }
        super.setPlacementAd(iPlacementAd);
        String tag = getTAG();
        StringBuilder sb = new StringBuilder("set placement ad:");
        sb.append(iPlacementAd == null ? Constants.NULL : iPlacementAd.getContentId());
        ho.b(tag, sb.toString());
        k();
        this.h.a(this.f7996a);
        if (this.f7996a != null) {
            j();
        } else {
            this.k = null;
        }
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    void setMediaPlayerReleaseListener(MediaPlayerReleaseListener mediaPlayerReleaseListener) {
        VideoView videoView = this.i;
        if (videoView != null) {
            videoView.setMediaPlayerReleaseListener(mediaPlayerReleaseListener);
        }
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void setAudioFocusType(int i) {
        this.i.setAudioFocusType(i);
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView, com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void resumeView() {
        ho.b(getTAG(), RewardMethods.RESUME_VIEW);
        this.i.resumeView();
        this.i.setNeedPauseOnSurfaceDestory(true);
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView, com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void pauseView() {
        ho.b(getTAG(), RewardMethods.PAUSE_VIEW);
        this.i.pauseView();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.IPlacementVideoView
    public void onCheckVideoHashResult(PlacementMediaFile placementMediaFile, boolean z) {
        ho.b(getTAG(), "onCheckVideoHashResult sucess: %s", Boolean.valueOf(z));
        if (!z || this.k == null || placementMediaFile == null) {
            return;
        }
        this.k = placementMediaFile;
        this.j = true;
        String a2 = a(placementMediaFile);
        if (TextUtils.isEmpty(a2)) {
            a2 = a(getContext(), placementMediaFile);
            if (TextUtils.isEmpty(a2)) {
                a2 = placementMediaFile.getUrl();
            }
        }
        this.b = a2;
        this.i.setVideoFileUrl(a2);
        this.i.setContentId(this.f7996a == null ? null : this.f7996a.getContentId());
        if (this.l) {
            ho.b(getTAG(), "play when hash check success");
            b(true, this.r);
        }
        if (this.m) {
            ho.b(getTAG(), "prefect when hash check success");
            this.i.prefetch();
        }
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public boolean i() {
        return this.i.isPlaying();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.p
    public VideoView getVideoView() {
        return this.i;
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    MediaState getMediaState() {
        VideoView videoView = this.i;
        if (videoView != null) {
            return videoView.getMediaState();
        }
        return null;
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    ImageView getLastFrame() {
        if (this.i == null) {
            return null;
        }
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(this.i.getSurfaceBitmap());
        return imageView;
    }

    public MediaState getCurrentState() {
        return this.i.getCurrentState();
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void g() {
        this.i.stop();
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void f() {
        this.i.pause();
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void e() {
        com.huawei.openalliance.ad.media.b bVar = this.u;
        if (bVar != null) {
            bVar.a();
        }
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView, com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void destroyView() {
        ho.b(getTAG(), RewardMethods.DESTROY_VIEW);
        this.i.destroyView();
        this.g.b();
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    protected void d(int i) {
        od odVar = this.h;
        if (odVar != null) {
            odVar.a(this.p, i);
            this.h.a();
        }
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void d() {
        this.r = false;
        this.i.unmute();
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void c(int i) {
        com.huawei.openalliance.ad.media.b bVar = this.u;
        if (bVar != null) {
            bVar.b(i);
        }
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void c() {
        this.r = true;
        this.i.mute();
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void b(SegmentMediaStateListener segmentMediaStateListener) {
        this.i.addSegmentMediaStateListener(segmentMediaStateListener);
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void b(MuteListener muteListener) {
        this.i.removeMuteListener(muteListener);
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void b(MediaErrorListener mediaErrorListener) {
        this.i.removeMediaErrorListener(mediaErrorListener);
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void b(MediaBufferListener mediaBufferListener) {
        this.i.removeMediaBufferListener(mediaBufferListener);
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void b(long j) {
        com.huawei.openalliance.ad.media.b bVar = this.u;
        if (bVar != null) {
            bVar.a(j);
        }
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void b(int i) {
        com.huawei.openalliance.ad.media.b bVar = this.u;
        if (bVar != null) {
            bVar.a(i);
        }
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    void b() {
        if (this.i != null) {
            ho.b("PlacementVideoView", "release player");
            this.i.i();
        }
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void a(boolean z, boolean z2) {
        ho.b(getTAG(), "play, auto:" + z + ", isMute:" + z2);
        if (this.j) {
            b(z, z2);
        } else {
            this.l = true;
            this.r = z2;
        }
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void a(String str) {
        this.h.a(str);
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void a(SegmentMediaStateListener segmentMediaStateListener) {
        this.i.addOmSegmentMediaStateListener(segmentMediaStateListener);
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void a(PPSVideoRenderListener pPSVideoRenderListener) {
        this.i.addPPSVideoRenderListener(pPSVideoRenderListener);
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void a(MuteListener muteListener) {
        this.i.addMuteListener(muteListener);
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    void a(MediaPlayerReleaseListener mediaPlayerReleaseListener) {
        VideoView videoView = this.i;
        if (videoView != null) {
            videoView.a(mediaPlayerReleaseListener);
        }
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void a(MediaErrorListener mediaErrorListener) {
        this.i.addMediaErrorListener(mediaErrorListener);
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void a(MediaBufferListener mediaBufferListener) {
        this.i.addMediaBufferListener(mediaBufferListener);
    }

    public void a(lz lzVar) {
        this.g = lzVar;
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    public void a(long j) {
        this.h.a(j);
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    void a(int i) {
        a(i, true);
        a();
    }

    @Override // com.huawei.openalliance.ad.views.PlacementMediaView
    void a() {
        this.i.d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean l() {
        String str;
        if (!HiAd.getInstance(getContext().getApplicationContext()).isVideoCacheWhenPlay(60)) {
            return false;
        }
        if (!TextUtils.isEmpty(a(this.k))) {
            str = "local file exists.";
        } else {
            if (cz.j(a(getContext().getApplicationContext(), this.k))) {
                ho.b("PlacementVideoView", "enable video cache when play, checkAndDown mediaFile.");
                od odVar = this.h;
                if (odVar == null) {
                    return true;
                }
                odVar.a();
                return true;
            }
            str = "local proxy file exists.";
        }
        ho.b("PlacementVideoView", str);
        return false;
    }

    private void k() {
        ho.b(getTAG(), "resetVideoView");
        setPreferStartPlayTime(0);
        this.j = false;
        this.l = false;
        this.m = true;
    }

    private void j() {
        if (this.f7996a == null) {
            return;
        }
        ho.b(getTAG(), "loadVideoInfo");
        PlacementMediaFile mediaFile = this.f7996a.getMediaFile();
        if (mediaFile == null || !mediaFile.isVideo()) {
            return;
        }
        this.k = mediaFile;
        Float d = mediaFile.d();
        if (d != null) {
            setRatio(d);
            this.i.setRatio(d);
        }
        this.i.setDefaultDuration((int) this.k.getDuration());
        this.h.a(this.k);
        this.l = false;
        this.m = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getTAG() {
        return "PlacementVideoView_" + hashCode();
    }

    private void b(boolean z, boolean z2) {
        ho.b(getTAG(), "doRealPlay, auto:" + z + ", isMute:" + z2);
        this.t.a();
        if (z2) {
            this.i.mute();
        } else {
            this.i.unmute();
        }
        if (this.i.getCurrentState().isState(MediaState.State.PLAYBACK_COMPLETED)) {
            this.i.c(this.s, 1);
        } else {
            this.i.setPreferStartPlayTime(this.s);
        }
        this.i.play(z);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.hiad_placement_pure_video_view, this);
        this.h = new no(context, this);
        this.t = new jg(getTAG());
        VideoView videoView = (VideoView) findViewById(R.id.hiad_id_video_view);
        this.i = videoView;
        videoView.setScreenOnWhilePlaying(true);
        this.i.setAutoScaleResizeLayoutOnVideoSizeChange(false);
        this.i.addMediaBufferListener(this.v);
        this.i.addMediaStateListener(this.w);
        this.i.addMediaErrorListener(this.y);
        this.i.addMuteListener(this.x);
        this.i.setMuteOnlyOnLostAudioFocus(true);
        this.i.setRemediate(true);
        com.huawei.openalliance.ad.media.b bVar = new com.huawei.openalliance.ad.media.b(context);
        this.u = bVar;
        bVar.a(new ReportVideoTimeListener() { // from class: com.huawei.openalliance.ad.views.z.1
            @Override // com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener
            public void reportVideoTime(long j) {
                if (ho.a()) {
                    ho.a("PlacementVideoView", "reportVideoTime: %s", Long.valueOf(j));
                }
                if (z.this.h != null) {
                    z.this.h.b(j);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, boolean z) {
        this.t.c();
        long j = i;
        this.p = j;
        if (this.q) {
            this.q = false;
            setPreferStartPlayTime(i);
            od odVar = this.h;
            long j2 = this.n;
            long currentTimeMillis = System.currentTimeMillis();
            long j3 = this.o;
            if (z) {
                odVar.b(j2, currentTimeMillis, j3, j);
            } else {
                odVar.c(j2, currentTimeMillis, j3, j);
            }
        }
    }

    private String a(PlacementMediaFile placementMediaFile) {
        if (placementMediaFile == null) {
            return null;
        }
        String b = placementMediaFile.b();
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        dk a2 = dh.a(getContext(), "normal");
        return a2.c(a2.e(placementMediaFile.getUrl()));
    }

    private String a(Context context, PlacementMediaFile placementMediaFile) {
        if (context == null || placementMediaFile == null || !HiAd.getInstance(context).isVideoCacheWhenPlay(60)) {
            return null;
        }
        return ao.a(getContext(), new VideoInfo(placementMediaFile), new a(this), "placement");
    }

    static class a implements iq {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<z> f8207a;

        @Override // com.huawei.openalliance.ad.iq
        public void a(int i) {
            z zVar = this.f8207a.get();
            if (zVar == null) {
                return;
            }
            ho.b("PlacementVideoView", "placementStreamListener onError: %s", Integer.valueOf(i));
            zVar.h();
            zVar.d(i);
        }

        public a(z zVar) {
            this.f8207a = new WeakReference<>(zVar);
        }
    }

    public z(Context context) {
        super(context);
        this.g = new lo();
        this.m = true;
        this.p = 0L;
        this.v = new MediaBufferListener() { // from class: com.huawei.openalliance.ad.views.z.2
            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferUpdate(int i) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingStart() {
                if (ho.a()) {
                    ho.a(z.this.getTAG(), "contentId: %s onBufferingStart", z.this.c);
                }
                z.this.t.b();
                z.this.g.h();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingEnd() {
                z.this.g.i();
            }
        };
        this.w = new MediaStateListener() { // from class: com.huawei.openalliance.ad.views.z.3
            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onProgress(int i, int i2) {
                z.this.p = i2;
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i) {
                z.this.a(i, false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i) {
                if (ho.a()) {
                    ho.a(z.this.getTAG(), "onMediaStart: %d", Integer.valueOf(i));
                }
                z.this.q = true;
                long j = i;
                z.this.o = j;
                z.this.p = j;
                z.this.n = System.currentTimeMillis();
                if (mediaPlayerAgent != null) {
                    z.this.h.b(mediaPlayerAgent.a());
                }
                od odVar = z.this.h;
                if (i > 0) {
                    odVar.i();
                } else {
                    odVar.h();
                    z.this.h.a(z.this.t.e(), z.this.t.d(), z.this.n);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i) {
                z.this.a(i, false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i) {
                z.this.a(i, true);
                z.this.l();
            }
        };
        this.x = new MuteListener() { // from class: com.huawei.openalliance.ad.views.z.4
            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onUnmute() {
                if (z.this.k != null) {
                    z.this.k.a("y");
                    z.this.g.b(1.0f);
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onMute() {
                if (z.this.k != null) {
                    z.this.k.a("n");
                    z.this.g.b(0.0f);
                }
            }
        };
        this.y = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.views.z.5
            @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
            public void onError(MediaPlayerAgent mediaPlayerAgent, int i, int i2, int i3) {
                z.this.a(i, false);
            }
        };
        a(context);
    }
}
