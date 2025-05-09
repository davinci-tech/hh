package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.VideoInfo;
import com.huawei.openalliance.ad.constant.ErrorCode;
import com.huawei.openalliance.ad.dc;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.lz;
import com.huawei.openalliance.ad.media.MediaPlayerAgent;
import com.huawei.openalliance.ad.media.listener.MediaBufferListener;
import com.huawei.openalliance.ad.media.listener.MediaErrorListener;
import com.huawei.openalliance.ad.media.listener.MediaStateListener;
import com.huawei.openalliance.ad.media.listener.MuteListener;
import com.huawei.openalliance.ad.media.listener.PPSVideoRenderListener;
import com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener;
import com.huawei.openalliance.ad.nk;
import com.huawei.openalliance.ad.oa;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;

/* loaded from: classes9.dex */
public class v extends m<oa> implements com.huawei.openalliance.ad.views.interfaces.m {
    private View.OnClickListener A;
    private PPSVideoRenderListener B;
    private final ReportVideoTimeListener C;
    private MediaStateListener D;
    private MediaErrorListener E;
    private final MediaBufferListener F;
    private MuteListener G;
    private VideoView f;
    private ImageView g;
    private boolean h;
    private VideoInfo i;
    private int j;
    private int k;
    private long l;
    private long m;
    private boolean n;
    private boolean o;
    private int p;
    private int q;
    private int r;
    private int s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private float y;
    private boolean z;

    public void setStartVol(float f) {
        this.y = f;
    }

    public void setMuteButtonState(boolean z) {
        this.u = z;
        if (this.g != null) {
            this.g.setImageResource(dd.a(z));
            this.g.setSelected(!z);
        }
    }

    public void setIgnoreSoundCtrl(boolean z) {
        this.w = z;
    }

    public void setHideSoundIcon(boolean z) {
        this.v = z;
    }

    @Override // com.huawei.openalliance.ad.views.m, com.huawei.openalliance.ad.views.interfaces.n
    public void setAudioFocusType(int i) {
        this.p = i;
        VideoView videoView = this.f;
        if (videoView != null) {
            videoView.setAudioFocusType(i);
        }
    }

    @Override // com.huawei.openalliance.ad.views.m, com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void pauseView() {
        VideoView videoView = this.f;
        if (videoView != null) {
            videoView.pauseView();
            this.f.pause();
        }
    }

    @Override // com.huawei.openalliance.ad.views.m, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        VideoView videoView = this.f;
        if (videoView != null) {
            removeView(videoView);
            this.f.destroyView();
            this.f = null;
        }
        this.k = Integer.MAX_VALUE;
    }

    public void j() {
        ho.b("PPSVideoView", "unMuteCustomized");
        this.z = true;
        VideoView videoView = this.f;
        if (videoView != null) {
            float f = this.y;
            if (f > 0.0f) {
                videoView.a(f);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.views.m, com.huawei.openalliance.ad.views.interfaces.n
    public void h() {
        super.h();
        VideoView videoView = this.f;
        if (videoView != null) {
            videoView.stop();
        }
    }

    public VideoView getVideoView() {
        return this.f;
    }

    @Override // com.huawei.openalliance.ad.views.m, com.huawei.openalliance.ad.views.interfaces.n
    public void g() {
        super.g();
        VideoView videoView = this.f;
        if (videoView != null) {
            videoView.stop();
        }
    }

    @Override // com.huawei.openalliance.ad.views.m
    protected void f() {
        pauseView();
    }

    @Override // com.huawei.openalliance.ad.views.m, com.huawei.openalliance.ad.views.interfaces.n
    public boolean e() {
        return this.j > 0;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.m
    public void a(String str) {
        VideoInfo R = this.c.R();
        this.i = R;
        if (R != null) {
            if (TextUtils.equals("n", R.n()) || this.v) {
                this.h = false;
            }
            this.j = this.i.b();
            this.x = TextUtils.equals("y", this.i.e());
        }
        MetaData h = this.c.h();
        if (h != null && h.x() > 0) {
            this.j = (int) h.x();
        }
        k();
        this.f.setAudioFocusType(this.p);
        this.f.setAlpha(0.0f);
        this.f.setVideoFileUrl(str);
        if (this.w || !this.x) {
            this.f.mute();
        } else {
            this.f.unmute();
        }
        if (this.e.d() == 1) {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.v.2
                @Override // java.lang.Runnable
                public void run() {
                    if (v.this.f != null) {
                        v.this.f.play(true);
                    }
                }
            }, 200L);
        } else {
            this.f.play(true);
        }
    }

    @Override // com.huawei.openalliance.ad.views.m, com.huawei.openalliance.ad.views.interfaces.n
    public void a(int i, int i2) {
        super.a(i, i2);
        VideoView videoView = this.f;
        if (videoView != null) {
            videoView.stop();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        VideoView videoView = this.f;
        if (videoView == null || this.w || !this.x) {
            return;
        }
        float f = this.y;
        if (f > 0.0f) {
            videoView.setSoundVolume(f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00c2, code lost:
    
        if (com.huawei.openalliance.ad.utils.x.q(getContext()) != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00ee, code lost:
    
        if (r6.c.o() != 1) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void l() {
        /*
            Method dump skipped, instructions count: 271
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.views.v.l():void");
    }

    private void k() {
        if (this.f == null) {
            VideoView videoView = new VideoView(getContext());
            this.f = videoView;
            videoView.setBackgroundColor(0);
            this.f.setScreenOnWhilePlaying(true);
            this.f.setStandalone(true);
            this.f.setAutoScaleResizeLayoutOnVideoSizeChange(false);
            this.f.setVideoScaleMode(2);
            this.f.setMuteOnlyOnLostAudioFocus(true);
            this.f.addPPSVideoRenderListener(this.B);
            this.f.addMediaStateListener(this.D);
            this.f.addMediaErrorListener(this.E);
            this.f.addMuteListener(this.G);
            this.f.addMediaBufferListener(this.F);
            this.f.addReportVideoTimeListenersSet(this.C);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(13);
            addView(this.f, layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        ho.b("PPSVideoView", "switchSound enableSound: " + z);
        VideoView videoView = this.f;
        if (videoView == null) {
            return;
        }
        if (z) {
            videoView.unmute();
        } else {
            videoView.mute();
        }
        ((oa) this.f8116a).a(!z);
    }

    public v(Context context, int i, int i2, int i3, int i4) {
        super(context);
        this.h = true;
        this.j = 0;
        this.k = Integer.MAX_VALUE;
        this.n = false;
        this.o = false;
        this.p = 1;
        this.t = false;
        this.u = true;
        this.v = false;
        this.w = true;
        this.x = false;
        this.y = 0.0f;
        this.z = false;
        this.A = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.v.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                v.this.a(!view.isSelected());
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.B = new PPSVideoRenderListener() { // from class: com.huawei.openalliance.ad.views.v.3
            @Override // com.huawei.openalliance.ad.media.listener.PPSVideoRenderListener
            public void onVideoRenderStart() {
                ho.a("PPSVideoView", "onVideoRenderStart, alreadyNotified: %s", Boolean.valueOf(v.this.t));
                if (v.this.t) {
                    return;
                }
                v.this.t = true;
                if (v.this.f != null) {
                    v.this.f.setAlpha(1.0f);
                }
                v.this.c();
                if (v.this.v) {
                    v.this.h = false;
                }
                v.this.l();
            }
        };
        this.C = new ReportVideoTimeListener() { // from class: com.huawei.openalliance.ad.views.v.4
            @Override // com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener
            public void reportVideoTime(long j) {
                if (ho.a()) {
                    ho.a("PPSVideoView", "reportVideoTime: %s", Long.valueOf(j));
                }
                if (v.this.f8116a != 0) {
                    ((oa) v.this.f8116a).b(j);
                }
            }
        };
        this.D = new MediaStateListener() { // from class: com.huawei.openalliance.ad.views.v.5
            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onProgress(int i5, int i6) {
                ho.a("PPSVideoView", "onProgress, playTime: %d, alreadyNotified: %s", Integer.valueOf(i6), Boolean.valueOf(v.this.t));
                if (i6 > 0 && !v.this.t) {
                    v.this.t = true;
                    if (v.this.f != null) {
                        v.this.f.setAlpha(1.0f);
                    }
                    v.this.c();
                    v.this.l();
                }
                if (v.this.f != null && v.this.f.getCurrentState().a() && v.this.j > 0) {
                    int i7 = v.this.j - i6;
                    if (i7 < 0) {
                        i7 = 0;
                    }
                    int max = Math.max(1, (int) Math.ceil((i7 * 1.0f) / 1000.0f));
                    ho.a("PPSVideoView", "left seconds: %d", Integer.valueOf(max));
                    if (max < v.this.k) {
                        v.this.k = max;
                        v.this.c(max);
                    }
                }
                if (v.this.n) {
                    v.this.b.a(i5);
                    if (v.this.f8116a != 0) {
                        ((oa) v.this.f8116a).a(i6, v.this.j);
                    }
                }
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i5) {
                a(i5, false);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i5) {
                if (v.this.n) {
                    return;
                }
                v.this.m();
                v.this.n = true;
                v.this.m = i5;
                v.this.l = ao.c();
                v vVar = v.this;
                if (i5 > 0) {
                    vVar.b.l();
                } else if (vVar.i != null) {
                    v.this.b.a(v.this.i.b(), v.this.u);
                }
                ((oa) v.this.f8116a).i();
                v.this.e.a(v.this.l);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaPause(MediaPlayerAgent mediaPlayerAgent, final int i5) {
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.v.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        a(i5, false);
                    }
                }, 1000L);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaStateListener
            public void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i5) {
                if (v.this.f != null) {
                    dc.a(v.this.f.getSurfaceBitmap());
                }
                dc.a((Drawable) null);
                a(i5, true);
                if (v.this.f8116a != 0) {
                    long j = i5;
                    ((oa) v.this.f8116a).a(j, j);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void a(int i5, boolean z) {
                if (v.this.n) {
                    v.this.n = false;
                    a(i5);
                    ((oa) v.this.f8116a).b();
                    lz lzVar = v.this.b;
                    if (z) {
                        lzVar.g();
                    } else {
                        lzVar.k();
                    }
                }
            }

            private void a(int i5) {
                if (v.this.o) {
                    ho.b("PPSVideoView", "has reported play end event");
                } else {
                    v.this.o = true;
                    ((oa) v.this.f8116a).a(v.this.l, ao.c(), v.this.m, i5);
                }
            }
        };
        this.E = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.views.v.6
            @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
            public void onError(MediaPlayerAgent mediaPlayerAgent, int i5, int i6, int i7) {
                v.this.b(ErrorCode.ERROR_CODE_FAIL_TO_DISPLAY_VIDEO_AD);
                v.this.a();
            }
        };
        this.F = new MediaBufferListener() { // from class: com.huawei.openalliance.ad.views.v.7
            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferUpdate(int i5) {
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingStart() {
                v.this.b.h();
            }

            @Override // com.huawei.openalliance.ad.media.listener.MediaBufferListener
            public void onBufferingEnd() {
                v.this.b.i();
            }
        };
        this.G = new MuteListener() { // from class: com.huawei.openalliance.ad.views.v.8
            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onUnmute() {
                v.this.setMuteButtonState(false);
                v.this.b.b(1.0f);
            }

            @Override // com.huawei.openalliance.ad.media.listener.MuteListener
            public void onMute() {
                v.this.setMuteButtonState(true);
                v.this.b.b(0.0f);
            }
        };
        this.r = i2;
        this.q = i;
        this.s = i3;
        this.f8116a = new nk(context, this, i4);
        k();
    }
}
