package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.inter.data.ILinkedSplashAd;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.media.MediaPlayerAgent;

/* loaded from: classes9.dex */
public class uc {

    /* renamed from: a, reason: collision with root package name */
    private com.huawei.openalliance.ad.views.aj f7550a;
    private MediaPlayerAgent b;
    private boolean c = false;

    public MediaPlayerAgent k() {
        return this.b;
    }

    public boolean j() {
        return this.c;
    }

    public void i() {
        com.huawei.openalliance.ad.views.aj ajVar = this.f7550a;
        if (ajVar != null) {
            ajVar.pause();
            this.f7550a.destroyView();
        }
        b(false);
    }

    public void h() {
        MediaPlayerAgent mediaPlayerAgent;
        if (this.f7550a == null || j() || (mediaPlayerAgent = this.b) == null) {
            return;
        }
        mediaPlayerAgent.play();
        b(true);
    }

    public void g() {
        MediaPlayerAgent mediaPlayerAgent;
        if (this.f7550a == null || (mediaPlayerAgent = this.b) == null) {
            return;
        }
        mediaPlayerAgent.pause();
        this.b.b();
        b(false);
    }

    public com.huawei.openalliance.ad.views.aj f() {
        return this.f7550a;
    }

    public void e(VideoInfo videoInfo) {
        if (this.f7550a == null || this.b == null) {
            return;
        }
        ho.b("VideoPlayProxy", "onViewPartialHidden, start pause");
        this.b.muteSound();
        if (videoInfo != null) {
            videoInfo.e("n");
        }
        this.b.pause();
        this.b.b();
        b(false);
    }

    public void e() {
        if (this.f7550a != null) {
            ho.b("VideoPlayProxy", "onViewPhysicalShowEnd, start pause. ");
            this.b.pause();
            this.b.b();
            b(false);
        }
    }

    public void d(VideoInfo videoInfo) {
        if (this.f7550a == null || this.b == null) {
            return;
        }
        ho.b("VideoPlayProxy", "onViewShownBetweenFullAndPartial, start mute");
        this.b.muteSound();
        this.b.b();
        if (videoInfo != null) {
            videoInfo.e("n");
        }
    }

    public void d() {
        this.b.unmuteSound();
    }

    public void c(VideoInfo videoInfo) {
        MediaPlayerAgent mediaPlayerAgent;
        if (this.f7550a == null || (mediaPlayerAgent = this.b) == null) {
            return;
        }
        mediaPlayerAgent.unmuteSound();
        if (videoInfo != null) {
            videoInfo.e("y");
        }
    }

    public void c() {
        this.b.muteSound();
    }

    public void b(boolean z) {
        this.c = z;
    }

    public void b(VideoInfo videoInfo) {
        MediaPlayerAgent mediaPlayerAgent;
        if (this.f7550a == null || (mediaPlayerAgent = this.b) == null) {
            return;
        }
        mediaPlayerAgent.muteSound();
        this.b.b();
        if (videoInfo != null) {
            videoInfo.e("n");
        }
    }

    public void b() {
        this.b.prepare();
    }

    public void a(boolean z) {
        this.b.a(z);
        this.b.play();
        b(true);
    }

    public void a(String str, VideoInfo videoInfo, int i) {
        if (TextUtils.isEmpty(str)) {
            this.b.setMediaFile(videoInfo.getVideoDownloadUrl());
        } else {
            this.b.setMediaFile(str);
        }
        this.b.a(i);
        this.b.muteSound();
        videoInfo.e("n");
    }

    public void a(com.huawei.openalliance.ad.views.aj ajVar) {
        this.f7550a = ajVar;
    }

    public void a(MediaPlayerAgent mediaPlayerAgent) {
        this.b = mediaPlayerAgent;
    }

    public void a(VideoInfo videoInfo, Integer num) {
        if (this.f7550a == null || videoInfo == null || this.b == null) {
            return;
        }
        int b = videoInfo.b();
        if (j()) {
            return;
        }
        ho.b("VideoPlayProxy", "onViewFullShown, start play, duration: %s, playProgress: %s", num, Integer.valueOf(b));
        this.b.setPreferStartPlayTime(b);
        this.b.play();
        b(true);
        if (num == null || Math.abs(num.intValue() - b) >= 1000) {
            this.b.seekToMillis(b, 3);
        } else {
            ho.b("VideoPlayProxy", "onViewFullShown, seek to 0");
            this.b.seekToMillis(0L, 3);
        }
    }

    public void a(VideoInfo videoInfo) {
        if (videoInfo != null) {
            ho.b("VideoPlayProxy", "on progress resume %s  soundSwitch %s", Integer.valueOf(videoInfo.b()), videoInfo.getSoundSwitch());
            if (this.f7550a == null || this.b == null) {
                return;
            }
            if ("n".equals(videoInfo.getSoundSwitch())) {
                this.b.muteSound();
            } else {
                this.b.unmuteSound();
            }
        }
    }

    public void a(Context context, ILinkedSplashAd iLinkedSplashAd) {
        if (iLinkedSplashAd == null) {
            return;
        }
        a(new com.huawei.openalliance.ad.views.aj(context));
        this.f7550a.setAutoScaleResizeLayoutOnVideoSizeChange(false);
        this.f7550a.setVideoScaleMode(2);
        if (iLinkedSplashAd.getVideoInfo() != null) {
            this.f7550a.setVideoRatio(iLinkedSplashAd.getVideoInfo().getVideoRatio());
        }
    }

    public void a() {
        this.b.play();
        b(true);
    }
}
