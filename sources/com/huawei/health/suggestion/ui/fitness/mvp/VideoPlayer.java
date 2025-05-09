package com.huawei.health.suggestion.ui.fitness.mvp;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.huawei.health.suggestion.ui.fitness.mvp.VideoPlayer;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import health.compact.a.LogAnonymous;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes4.dex */
public class VideoPlayer {

    /* renamed from: a, reason: collision with root package name */
    private VideoCurrentPositionCallBack f3198a;
    private int b;
    private boolean c;
    private boolean d;
    private final Context e;
    private MediaPlayer f;
    private PreviewPrepareOkListener k;
    private MediaPlayer m;
    private SurfaceView n;
    private VideoPlayPositionListener o;
    private SurfaceView p;
    private Timer q;
    private ViewGroup r;
    private boolean j = false;
    private boolean g = false;
    private boolean i = false;
    private int h = -1;
    private ArrayList<VideoPlayStateListener> l = new ArrayList<>();

    public interface PreviewPrepareOkListener {
        void prepareOk();
    }

    public interface VideoCurrentPositionCallBack {
        void onCurrentPostionChange(int i);

        void onVideoDurationChange(int i);
    }

    public interface VideoPlayPositionListener {
        void onPlayPositionChange(int i, int i2);
    }

    public interface VideoPlayStateListener {
        public static final int STATE_PAUSE = 2;
        public static final int STATE_PLAYING = 1;
        public static final int STATE_SMALL_RESET = 3;
        public static final int STATE_STOP = 0;

        void onPlayStateChange(int i);
    }

    public VideoPlayer(Context context) {
        this.e = context;
    }

    public void c() {
        if (this.f == null) {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.f = mediaPlayer;
            mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() { // from class: frh
                @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
                public final void onVideoSizeChanged(MediaPlayer mediaPlayer2, int i, int i2) {
                    VideoPlayer.this.aEQ_(mediaPlayer2, i, i2);
                }
            });
            if (this.p == null) {
                this.p = new SurfaceView(this.e);
                LogUtil.a("Suggestion_VideoPlayer", "new mSurfaceView");
            }
            this.p.getHolder().addCallback(new SurfaceHolder.Callback() { // from class: com.huawei.health.suggestion.ui.fitness.mvp.VideoPlayer.5
                @Override // android.view.SurfaceHolder.Callback
                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    if (surfaceHolder == null) {
                        LogUtil.b("Suggestion_VideoPlayer", "createPlayer surfaceHolder is null");
                        return;
                    }
                    Surface surface = surfaceHolder.getSurface();
                    if (surface == null) {
                        LogUtil.b("Suggestion_VideoPlayer", "surface is null");
                        return;
                    }
                    if (surface.isValid()) {
                        if (VideoPlayer.this.f != null) {
                            VideoPlayer.this.f.setDisplay(surfaceHolder);
                            LogUtil.a("Suggestion_VideoPlayer", "surfaceCreated");
                            return;
                        }
                        return;
                    }
                    LogUtil.b("Suggestion_VideoPlayer", "surface is not Valid");
                }

                @Override // android.view.SurfaceHolder.Callback
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                    LogUtil.c("Suggestion_VideoPlayer", "surfaceChanged");
                }

                @Override // android.view.SurfaceHolder.Callback
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    LogUtil.c("Suggestion_VideoPlayer", "surfaceDestroyed");
                }
            });
        }
    }

    public /* synthetic */ void aEQ_(MediaPlayer mediaPlayer, int i, int i2) {
        if (this.r != null) {
            int videoWidth = this.f.getVideoWidth();
            int videoHeight = this.f.getVideoHeight();
            LogUtil.a("Suggestion_VideoPlayer", "createPlayer videoWidth:", Integer.valueOf(videoWidth), ", videoHeight:", Integer.valueOf(videoHeight));
            if (videoWidth <= 0 || videoHeight <= 0) {
                return;
            }
            aEX_(this.p, videoWidth, videoHeight);
        }
    }

    public void e() {
        if (this.m == null) {
            LogUtil.c("Suggestion_VideoPlayer", "createPlayer mPreviewPlayer");
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.m = mediaPlayer;
            mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() { // from class: frm
                @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
                public final void onVideoSizeChanged(MediaPlayer mediaPlayer2, int i, int i2) {
                    VideoPlayer.this.aER_(mediaPlayer2, i, i2);
                }
            });
            this.m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: frk
                @Override // android.media.MediaPlayer.OnCompletionListener
                public final void onCompletion(MediaPlayer mediaPlayer2) {
                    VideoPlayer.this.aES_(mediaPlayer2);
                }
            });
            this.n = new SurfaceView(this.e);
            LogUtil.c("Suggestion_VideoPlayer", "new mPreviewSurfaceView");
            this.n.getHolder().addCallback(new SurfaceHolder.Callback() { // from class: com.huawei.health.suggestion.ui.fitness.mvp.VideoPlayer.1
                @Override // android.view.SurfaceHolder.Callback
                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    if (surfaceHolder == null) {
                        LogUtil.b("Suggestion_VideoPlayer", "preview surfaceHolder is null");
                        return;
                    }
                    Surface surface = surfaceHolder.getSurface();
                    if (surface == null) {
                        LogUtil.b("Suggestion_VideoPlayer", "preview surface is null");
                        return;
                    }
                    if (surface.isValid()) {
                        if (VideoPlayer.this.m != null) {
                            VideoPlayer.this.m.setDisplay(surfaceHolder);
                            LogUtil.a("Suggestion_VideoPlayer", "preview surfaceCreated");
                            return;
                        }
                        return;
                    }
                    LogUtil.b("Suggestion_VideoPlayer", "preview surface is not Valid");
                }

                @Override // android.view.SurfaceHolder.Callback
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                    LogUtil.c("Suggestion_VideoPlayer", "preview surfaceChanged");
                }

                @Override // android.view.SurfaceHolder.Callback
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    LogUtil.c("Suggestion_VideoPlayer", "preview surfaceDestroyed");
                }
            });
        }
    }

    public /* synthetic */ void aER_(MediaPlayer mediaPlayer, int i, int i2) {
        i();
    }

    public /* synthetic */ void aES_(MediaPlayer mediaPlayer) {
        o();
    }

    public void i() {
        MediaPlayer mediaPlayer;
        if (this.r == null || (mediaPlayer = this.m) == null) {
            return;
        }
        int videoWidth = mediaPlayer.getVideoWidth();
        int videoHeight = this.m.getVideoHeight();
        LogUtil.a("Suggestion_VideoPlayer", "setPreviewSize videoWidth :", Integer.valueOf(videoWidth), " , videoHeight :", Integer.valueOf(videoHeight));
        if (videoWidth <= 0 || videoHeight <= 0) {
            return;
        }
        aEX_(this.n, videoWidth, videoHeight);
    }

    public void n() {
        if (this.q == null) {
            LogUtil.c("Suggestion_VideoPlayer", "startTimer new Timer");
            Timer timer = new Timer("Suggestion_VideoPlayer");
            this.q = timer;
            timer.schedule(new TimerTask() { // from class: com.huawei.health.suggestion.ui.fitness.mvp.VideoPlayer.3
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (VideoPlayer.this.f3198a == null || !VideoPlayer.this.g) {
                        return;
                    }
                    VideoPlayer.this.f3198a.onCurrentPostionChange(VideoPlayer.this.m.getCurrentPosition());
                }
            }, 0L, 1000L);
        }
    }

    public void a(VideoCurrentPositionCallBack videoCurrentPositionCallBack) {
        this.f3198a = videoCurrentPositionCallBack;
    }

    public void aEV_(ViewGroup viewGroup) {
        if (viewGroup != null) {
            this.r = viewGroup;
            viewGroup.removeAllViews();
            m();
            this.r.addView(this.p);
            this.p.setZOrderMediaOverlay(true);
            this.p.getHolder().setFormat(-3);
            LogUtil.a("Suggestion_VideoPlayer", "setContentView ok");
        }
    }

    public SurfaceView aEP_() {
        return this.n;
    }

    public void aEW_(ViewGroup viewGroup) {
        if (viewGroup != null) {
            this.r = viewGroup;
            viewGroup.removeAllViews();
            g();
            this.r.addView(this.n);
            this.n.getHolder().setFormat(-3);
            LogUtil.a("Suggestion_VideoPlayer", "setPreviewContentView ok");
        }
    }

    public void j() {
        LogUtil.c("Suggestion_VideoPlayer", "resetPreviewPlay");
        l();
        LogUtil.c("Suggestion_VideoPlayer", "resetPreviewPlay mIsPreviewPlaying release");
        MediaPlayer mediaPlayer = this.m;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.m.release();
        }
        this.m = null;
        this.g = false;
    }

    public void aEX_(SurfaceView surfaceView, int i, int i2) {
        if (surfaceView == null) {
            LogUtil.h("Suggestion_VideoPlayer", "tryResetSurfaceSize surfaceView is null");
            return;
        }
        if (!(surfaceView.getParent() instanceof ViewGroup)) {
            LogUtil.h("Suggestion_VideoPlayer", "tryResetSurfaceSize getParent is not ViewGroup");
            return;
        }
        ViewGroup viewGroup = (ViewGroup) surfaceView.getParent();
        int width = viewGroup.getWidth();
        int height = viewGroup.getHeight();
        LogUtil.a("Suggestion_VideoPlayer", "tryResetSurfaceSize, width:", Integer.valueOf(width), " height:", Integer.valueOf(height), " videoWidth:", Integer.valueOf(i), " videoHeight:", Integer.valueOf(i2));
        if (width <= 0 || height <= 0) {
            return;
        }
        if (!(surfaceView.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
            LogUtil.h("Suggestion_VideoPlayer", "!(view.getLayoutParams() instanceof FrameLayout.LayoutParams)");
            return;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) surfaceView.getLayoutParams();
        if (i2 == 0) {
            LogUtil.h("Suggestion_VideoPlayer", "tryResetSurfaceSize videoHeight == 0");
            return;
        }
        float f = i / i2;
        if (f == 0.0f) {
            LogUtil.h("Suggestion_VideoPlayer", "tryResetSurfaceSize scaleVideo is zero");
            return;
        }
        if (f > width / height) {
            layoutParams.width = width;
        } else {
            layoutParams.width = (height * 16) / 9;
        }
        layoutParams.height = (int) (layoutParams.width / f);
        layoutParams.gravity = 17;
        LogUtil.a("Suggestion_VideoPlayer", "params.width:", Integer.valueOf(layoutParams.width), " params.height:", Integer.valueOf(layoutParams.height));
        surfaceView.setLayoutParams(layoutParams);
    }

    public void o() {
        LogUtil.c("Suggestion_VideoPlayer", "stopPreviewPlay");
        this.g = false;
        this.c = false;
        this.i = false;
        this.b = 0;
        l();
        a(0);
        LogUtil.c("Suggestion_VideoPlayer", "stopPreviewPlay notifyStateChange STATE_STOP");
        MediaPlayer mediaPlayer = this.m;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.m.release();
        }
        this.m = null;
    }

    public void f() {
        a(2);
        if (this.j) {
            this.f.pause();
        }
        this.j = false;
        this.h = -1;
        VideoPlayPositionListener videoPlayPositionListener = this.o;
        if (videoPlayPositionListener != null) {
            videoPlayPositionListener.onPlayPositionChange(-1, -1);
        }
    }

    public void a(boolean z) {
        MediaPlayer mediaPlayer;
        LogUtil.a("Suggestion_VideoPlayer", "pausePreviewPlay");
        if (z) {
            LogUtil.a("Suggestion_VideoPlayer", "pausePreviewPlay notify:", Boolean.valueOf(z));
            a(2);
        }
        if (h() && (mediaPlayer = this.m) != null) {
            mediaPlayer.pause();
        }
        this.g = false;
    }

    private void a(int i) {
        LogUtil.c("Suggestion_VideoPlayer", "notifyStateChange start");
        if (koq.c(this.l)) {
            LogUtil.c("Suggestion_VideoPlayer", "notifyStateChange mStateListeners not empty");
            Iterator<VideoPlayStateListener> it = this.l.iterator();
            while (it.hasNext()) {
                VideoPlayStateListener next = it.next();
                if (next != null) {
                    LogUtil.h("Suggestion_VideoPlayer", "notifyStateChange: notify state is ", Integer.valueOf(i));
                    next.onPlayStateChange(i);
                } else {
                    LogUtil.b("Suggestion_VideoPlayer", "notifyStateChange: listener is null");
                }
            }
        }
    }

    public boolean b() {
        return this.j;
    }

    public boolean h() {
        MediaPlayer mediaPlayer = this.m;
        if (mediaPlayer != null) {
            return mediaPlayer.isPlaying();
        }
        return false;
    }

    public void c(boolean z) {
        this.d = z;
    }

    public void a() {
        MediaPlayer mediaPlayer = this.f;
        if (mediaPlayer != null) {
            this.j = false;
            mediaPlayer.release();
        }
        if (this.l != null) {
            LogUtil.c("Suggestion_VideoPlayer", "destroyPlay mStateListeners");
            this.l = null;
        }
        l();
        m();
    }

    private void m() {
        SurfaceView surfaceView = this.p;
        if (surfaceView == null || surfaceView.getParent() == null || !(this.p.getParent() instanceof ViewGroup)) {
            return;
        }
        LogUtil.a("Suggestion_VideoPlayer", "removeFromParentView:", this.p.getParent());
        ((ViewGroup) this.p.getParent()).removeView(this.p);
    }

    public void g() {
        SurfaceView surfaceView = this.n;
        if (surfaceView == null || surfaceView.getParent() == null || !(this.n.getParent() instanceof ViewGroup)) {
            return;
        }
        ((ViewGroup) this.n.getParent()).removeView(this.n);
    }

    public void e(int i) {
        LogUtil.a("Suggestion_VideoPlayer", "previewSeekTo");
        MediaPlayer mediaPlayer = this.m;
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(i, 2);
        } else {
            LogUtil.b("Suggestion_VideoPlayer", "previewSeekTo mediaPlayer is null");
        }
    }

    public void l() {
        Timer timer = this.q;
        if (timer == null) {
            LogUtil.h("Suggestion_VideoPlayer", "setTimerCancel mTimer is null.");
        } else {
            timer.cancel();
            this.q = null;
        }
    }

    public void d(int i) {
        LogUtil.a("Suggestion_VideoPlayer", "restorePreviewPlay currentPosintion:", Integer.valueOf(i), ", !isPreviewPlaying():", Boolean.valueOf(h()), ", mIsPrepareOk:", Boolean.valueOf(this.i));
        if (this.i && !h()) {
            LogUtil.a("Suggestion_VideoPlayer", "restorePreviewPlay mIsPrepareOk ok");
            c(i);
        } else {
            this.c = true;
            this.b = i;
            LogUtil.a("Suggestion_VideoPlayer", "restorePreviewPlay mIsPrepareOk else");
        }
    }

    public void d(String str, boolean z) {
        e();
        this.i = false;
        this.c = z;
        this.b = 0;
        try {
            this.m.setDataSource(str);
            LogUtil.a("Suggestion_VideoPlayer", "mPreviewPlayer.setDataSource :", str);
            this.m.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: frf
                @Override // android.media.MediaPlayer.OnPreparedListener
                public final void onPrepared(MediaPlayer mediaPlayer) {
                    VideoPlayer.this.aET_(mediaPlayer);
                }
            });
            this.m.prepareAsync();
            this.m.setScreenOnWhilePlaying(true);
        } catch (IOException e) {
            LogUtil.b("Suggestion_VideoPlayer", "IOException mPreviewPlayer:", LogAnonymous.b((Throwable) e));
        } catch (IllegalArgumentException e2) {
            LogUtil.b("Suggestion_VideoPlayer", "IllegalArgumentException startPreviewPlay:", LogAnonymous.b((Throwable) e2));
        } catch (IllegalStateException e3) {
            LogUtil.b("Suggestion_VideoPlayer", "IllegalStateException startPreviewPlay:", LogAnonymous.b((Throwable) e3));
        } catch (SecurityException e4) {
            LogUtil.b("Suggestion_VideoPlayer", "SecurityException startPreviewPlay:", LogAnonymous.b((Throwable) e4));
        }
    }

    public /* synthetic */ void aET_(MediaPlayer mediaPlayer) {
        this.i = true;
        if (this.k != null) {
            LogUtil.c("Suggestion_VideoPlayer", "previewPrepare mPreviewPrepareOkListener prepareOk");
            this.k.prepareOk();
        }
        if (this.m != null) {
            LogUtil.a("Suggestion_VideoPlayer", "previewPrepare ok mIsAutoPlay:", Boolean.valueOf(this.c));
            if (this.c) {
                c(this.b);
                LogUtil.a("Suggestion_VideoPlayer", "previewPrepare mIsAutoPlay mCurrentPosintion:", Integer.valueOf(this.b));
            }
            this.m.setLooping(this.d);
            VideoCurrentPositionCallBack videoCurrentPositionCallBack = this.f3198a;
            if (videoCurrentPositionCallBack != null) {
                videoCurrentPositionCallBack.onVideoDurationChange(this.m.getDuration());
            }
        }
    }

    public void c(int i) {
        LogUtil.c("Suggestion_VideoPlayer", "startPreviewPlay");
        MediaPlayer mediaPlayer = this.m;
        if (mediaPlayer != null) {
            this.g = true;
            mediaPlayer.start();
            e(i);
            n();
            a(1);
            LogUtil.a("Suggestion_VideoPlayer", "startPreviewPlay notifyStateChange STATE_PLAYING");
        }
    }

    public void d(String str, int i) {
        VideoPlayPositionListener videoPlayPositionListener = this.o;
        if (videoPlayPositionListener != null) {
            videoPlayPositionListener.onPlayPositionChange(this.h, i);
        }
        this.h = i;
        MediaPlayer mediaPlayer = this.f;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            this.f.release();
            this.f = null;
            LogUtil.c("Suggestion_VideoPlayer", "startPlay mPlayer.reset");
        }
        c();
        try {
            this.f.setDataSource(str);
            this.f.prepareAsync();
            this.f.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: frj
                @Override // android.media.MediaPlayer.OnPreparedListener
                public final void onPrepared(MediaPlayer mediaPlayer2) {
                    VideoPlayer.this.aEU_(mediaPlayer2);
                }
            });
            this.f.setScreenOnWhilePlaying(true);
        } catch (IOException e) {
            LogUtil.b("Suggestion_VideoPlayer", "IOException startPlay position ", LogAnonymous.b((Throwable) e));
        } catch (IllegalArgumentException e2) {
            LogUtil.b("Suggestion_VideoPlayer", "IllegalArgumentException startPlay position", LogAnonymous.b((Throwable) e2));
        } catch (IllegalStateException e3) {
            LogUtil.b("Suggestion_VideoPlayer", "IllegalStateException startPlay position ", LogAnonymous.b((Throwable) e3));
        } catch (SecurityException e4) {
            LogUtil.b("Suggestion_VideoPlayer", "SecurityException startPlay position ", LogAnonymous.b((Throwable) e4));
        }
    }

    public /* synthetic */ void aEU_(MediaPlayer mediaPlayer) {
        MediaPlayer mediaPlayer2 = this.f;
        if (mediaPlayer2 != null) {
            this.j = true;
            mediaPlayer2.start();
            this.f.setLooping(this.d);
            a(1);
        }
    }

    public void b(VideoPlayStateListener videoPlayStateListener) {
        if (videoPlayStateListener == null) {
            return;
        }
        this.l.add(videoPlayStateListener);
    }

    public void b(VideoPlayPositionListener videoPlayPositionListener) {
        this.o = videoPlayPositionListener;
    }
}
