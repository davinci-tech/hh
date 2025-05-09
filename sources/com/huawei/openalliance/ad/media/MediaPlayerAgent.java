package com.huawei.openalliance.ad.media;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Surface;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.Scheme;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.openalliance.ad.dh;
import com.huawei.openalliance.ad.exception.c;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.media.MediaState;
import com.huawei.openalliance.ad.media.listener.MediaBufferListener;
import com.huawei.openalliance.ad.media.listener.MediaErrorListener;
import com.huawei.openalliance.ad.media.listener.MediaPlayerReleaseListener;
import com.huawei.openalliance.ad.media.listener.MediaStateListener;
import com.huawei.openalliance.ad.media.listener.MuteListener;
import com.huawei.openalliance.ad.media.listener.PPSVideoRenderListener;
import com.huawei.openalliance.ad.media.listener.ReportVideoTimeListener;
import com.huawei.openalliance.ad.utils.ae;
import com.huawei.openalliance.ad.utils.al;
import com.huawei.openalliance.ad.utils.cy;
import com.huawei.openalliance.ad.utils.dc;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.dl;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes5.dex */
public class MediaPlayerAgent {

    /* renamed from: a, reason: collision with root package name */
    private static final al f7204a = new al("thread_media_player_ctrl");
    private int A;
    private Context C;
    private boolean D;
    private b b;
    private MediaPlayer c;
    private String e;
    private volatile String f;
    private boolean g;
    private int l;
    private long m;
    private AudioManager s;
    private Object y;
    private WeakReference<Surface> z;
    private int d = 0;
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private int k = 0;
    private final MediaState n = new MediaState();
    private final byte[] o = new byte[0];
    private final byte[] p = new byte[0];
    private final byte[] q = new byte[0];
    private int r = 0;
    private boolean t = false;
    private boolean u = false;
    private int v = 0;
    private boolean w = false;
    private volatile int x = 0;
    private boolean B = false;
    private boolean E = false;
    private final CopyOnWriteArraySet<MediaStateListener> F = new CopyOnWriteArraySet<>();
    private final CopyOnWriteArraySet<MediaBufferListener> G = new CopyOnWriteArraySet<>();
    private final CopyOnWriteArraySet<MediaErrorListener> H = new CopyOnWriteArraySet<>();
    private final CopyOnWriteArraySet<MuteListener> I = new CopyOnWriteArraySet<>();
    private final CopyOnWriteArraySet<com.huawei.openalliance.ad.media.listener.a> J = new CopyOnWriteArraySet<>();
    private final CopyOnWriteArraySet<MediaPlayer.OnVideoSizeChangedListener> K = new CopyOnWriteArraySet<>();
    private final CopyOnWriteArraySet<PPSVideoRenderListener> L = new CopyOnWriteArraySet<>();
    private final CopyOnWriteArraySet<MediaPlayerReleaseListener> M = new CopyOnWriteArraySet<>();
    private final MediaPlayer.OnVideoSizeChangedListener N = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.1
        @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
        public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
            MediaPlayerAgent.this.a(mediaPlayer, i, i2);
        }
    };
    private MediaPlayer.OnCompletionListener O = new MediaPlayer.OnCompletionListener() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.12
        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            if (MediaPlayerAgent.this.n.isState(MediaState.State.ERROR) || MediaPlayerAgent.this.n.isState(MediaState.State.PLAYBACK_COMPLETED)) {
                return;
            }
            MediaPlayerAgent.this.n.a(MediaState.State.PLAYBACK_COMPLETED);
            int currentPosition = mediaPlayer.getCurrentPosition();
            int h = MediaPlayerAgent.this.h();
            ho.b("MediaPlayerAgent", "onCompletion %d duration: %d", Integer.valueOf(currentPosition), Integer.valueOf(h));
            int max = Math.max(currentPosition, h);
            MediaPlayerAgent.this.a(100, max);
            MediaPlayerAgent.this.b(max);
            MediaPlayerAgent.this.q();
            MediaPlayerAgent.d(MediaPlayerAgent.this.e);
            MediaPlayerAgent.this.k = 0;
            MediaPlayerAgent.this.r = 0;
        }
    };
    private MediaPlayer.OnInfoListener P = new MediaPlayer.OnInfoListener() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.23
        @Override // android.media.MediaPlayer.OnInfoListener
        public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
            ho.b("MediaPlayerAgent", "onInfo what: %d extra: %d", Integer.valueOf(i), Integer.valueOf(i2));
            if (i == 3) {
                MediaPlayerAgent.this.o();
            } else {
                if (i == 701) {
                    MediaPlayerAgent.this.n();
                    return true;
                }
                if (i != 702) {
                    if (i != 804 && i != 805) {
                        return true;
                    }
                    MediaPlayerAgent.this.b(i, i2);
                    return true;
                }
            }
            MediaPlayerAgent.this.q();
            return true;
        }
    };
    private MediaPlayer.OnPreparedListener Q = new MediaPlayer.OnPreparedListener() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.34
        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            ho.b("MediaPlayerAgent", "onPrepared");
            MediaPlayerAgent.this.i = false;
            mediaPlayer.setOnInfoListener(MediaPlayerAgent.this.P);
            if (MediaPlayerAgent.this.j || MediaPlayerAgent.this.n.isNotState(MediaState.State.PREPARING)) {
                MediaPlayerAgent.this.n.a(MediaState.State.PREPARED);
                MediaPlayerAgent mediaPlayerAgent = MediaPlayerAgent.this;
                mediaPlayerAgent.g(mediaPlayerAgent.h());
                return;
            }
            try {
                MediaPlayerAgent.this.n.a(MediaState.State.PREPARED);
                mediaPlayer.start();
                MediaPlayerAgent.b(mediaPlayer, MediaPlayerAgent.this.m, 3);
                MediaPlayerAgent.this.n.a(MediaState.State.PLAYING);
                if (ho.a()) {
                    ho.a("MediaPlayerAgent", "seek to prefer pos: %d", Long.valueOf(MediaPlayerAgent.this.m));
                }
                MediaPlayerAgent.this.d(mediaPlayer.getCurrentPosition());
                MediaPlayerAgent mediaPlayerAgent2 = MediaPlayerAgent.this;
                mediaPlayerAgent2.g(mediaPlayerAgent2.h());
                MediaPlayerAgent.this.t();
            } catch (IllegalStateException unused) {
                ho.c("MediaPlayerAgent", "onPrepared - IllegalStateException");
                MediaPlayerAgent.this.n.a(MediaState.State.ERROR);
                MediaPlayerAgent.this.a(0, -1, -1);
            }
        }
    };
    private MediaPlayer.OnErrorListener R = new MediaPlayer.OnErrorListener() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.39
        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            ho.c("MediaPlayerAgent", "onError - what: %d extra: %d currentState: %s - agent: %s", Integer.valueOf(i), Integer.valueOf(i2), MediaPlayerAgent.this.n, MediaPlayerAgent.this);
            MediaPlayerAgent.this.q();
            if (MediaPlayerAgent.this.n.isState(MediaState.State.ERROR)) {
                ho.b("MediaPlayerAgent", "do not notify error when already error");
                return true;
            }
            MediaPlayerAgent.this.n.a(MediaState.State.ERROR);
            MediaPlayerAgent.this.a(mediaPlayer.getCurrentPosition(), i, i2);
            return true;
        }
    };
    private MediaPlayer.OnBufferingUpdateListener S = new MediaPlayer.OnBufferingUpdateListener() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.40
        @Override // android.media.MediaPlayer.OnBufferingUpdateListener
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
            if (MediaPlayerAgent.this.n.a()) {
                if (i < 0) {
                    i = 0;
                }
                if (i > 100) {
                    i = 100;
                }
                MediaPlayerAgent.this.c(i);
            }
        }
    };
    private Callable<Boolean> T = new Callable<Boolean>() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.8
        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean call() {
            return Boolean.valueOf(MediaPlayerAgent.this.j());
        }
    };
    private Runnable U = new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.32
        @Override // java.lang.Runnable
        public void run() {
            int h;
            MediaPlayerAgent.d(MediaPlayerAgent.this.e);
            if (MediaPlayerAgent.this.n.isNotState(MediaState.State.PREPARING) && MediaPlayerAgent.this.n.isNotState(MediaState.State.PLAYING) && MediaPlayerAgent.this.n.isNotState(MediaState.State.PREPARED)) {
                return;
            }
            int currentPlayPosition = MediaPlayerAgent.this.getCurrentPlayPosition();
            if (MediaPlayerAgent.this.F.size() > 0 && (h = MediaPlayerAgent.this.h()) > 0) {
                int ceil = (int) Math.ceil((currentPlayPosition * 100.0f) / h);
                if (ceil > 100) {
                    ceil = 100;
                }
                MediaPlayerAgent.this.a(ceil, currentPlayPosition);
                if (currentPlayPosition >= h) {
                    MediaPlayerAgent.y(MediaPlayerAgent.this);
                    if (MediaPlayerAgent.this.r > 2) {
                        ho.b("MediaPlayerAgent", "reach end count exceeds");
                        MediaPlayerAgent.this.O.onCompletion(MediaPlayerAgent.this.c());
                        return;
                    }
                }
            }
            if (MediaPlayerAgent.this.g && MediaPlayerAgent.this.G.size() > 0 && MediaPlayerAgent.this.r == 0) {
                if (Math.abs(currentPlayPosition - MediaPlayerAgent.this.k) < 100) {
                    MediaPlayerAgent.this.n();
                } else {
                    MediaPlayerAgent.this.q();
                    MediaPlayerAgent.this.k = currentPlayPosition;
                }
            }
            MediaPlayerAgent.b(MediaPlayerAgent.this.U, MediaPlayerAgent.this.e, 200L);
        }
    };
    private AudioManager.OnAudioFocusChangeListener V = new AudioManager.OnAudioFocusChangeListener() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.38
        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(final int i) {
            MediaPlayerAgent.b(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.38.1
                @Override // java.lang.Runnable
                public void run() {
                    ho.b("MediaPlayerAgent", "onAudioFocusChange %d previous: %d", Integer.valueOf(i), Integer.valueOf(MediaPlayerAgent.this.v));
                    int i2 = i;
                    if (i2 == -3) {
                        b();
                    } else if (i2 == -2 || i2 == -1) {
                        a();
                    } else if (i2 == 1 || i2 == 2) {
                        c();
                    }
                    MediaPlayerAgent.this.v = i;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c() {
            ho.b("MediaPlayerAgent", "handleAudioFocusGain - muteOnlyOnLostAudioFocus: " + MediaPlayerAgent.this.B);
            if (MediaPlayerAgent.this.B) {
                if (MediaPlayerAgent.this.u) {
                    MediaPlayerAgent.this.m();
                }
            } else {
                if (MediaPlayerAgent.this.v == -2 || MediaPlayerAgent.this.v == -1) {
                    if (MediaPlayerAgent.this.t) {
                        MediaPlayerAgent.this.a((Long) null);
                        MediaPlayerAgent.this.t = false;
                        return;
                    }
                    return;
                }
                if (MediaPlayerAgent.this.v == -3 && MediaPlayerAgent.this.u) {
                    MediaPlayerAgent.this.m();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            ho.b("MediaPlayerAgent", "handleAudioFocusLossTransientCanDuck soundMuted: " + MediaPlayerAgent.this.w);
            if (MediaPlayerAgent.this.w) {
                return;
            }
            MediaPlayerAgent.this.l();
            MediaPlayerAgent.this.u = true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            if (!MediaPlayerAgent.this.B) {
                boolean j = MediaPlayerAgent.this.j();
                ho.b("MediaPlayerAgent", "handleAudioFocusLoss isPlaying: %s", Boolean.valueOf(j));
                if (j) {
                    MediaPlayerAgent.this.pause();
                    MediaPlayerAgent.this.t = true;
                    return;
                }
                return;
            }
            ho.b("MediaPlayerAgent", "handleAudioFocusLoss muteOnlyOnLostAudioFocus: " + MediaPlayerAgent.this.B);
            b();
        }
    };

    public void unmuteSound() {
        b(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.13
            @Override // java.lang.Runnable
            public void run() {
                MediaPlayerAgent.this.m();
            }
        });
    }

    public String toString() {
        return "MediaPlayerAgent@" + Integer.toHexString(hashCode()) + " [" + dl.a(this.f) + "]";
    }

    public void stopWhenUrlMatchs(final String str) {
        b(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.4
            @Override // java.lang.Runnable
            public void run() {
                String str2 = str;
                if (str2 == null || !TextUtils.equals(str2, MediaPlayerAgent.this.f)) {
                    return;
                }
                MediaPlayerAgent.this.f();
            }
        });
    }

    public void stop() {
        c(true);
    }

    public void setVideoSizeChangeListener(MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        a(onVideoSizeChangedListener);
    }

    public void setSurface(final Surface surface) {
        b(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.10
            @Override // java.lang.Runnable
            public void run() {
                MediaPlayerAgent.this.a(surface);
            }
        });
    }

    public void setSoundVolume(final float f) {
        b(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.14
            @Override // java.lang.Runnable
            public void run() {
                ho.a("MediaPlayerAgent", "setSoundVolume %f result: %s", Float.valueOf(f), Boolean.valueOf(MediaPlayerAgent.this.b(f)));
            }
        });
    }

    public void setPreferStartPlayTime(int i) {
        this.m = i;
    }

    public void setPPSVideoRenderListener(PPSVideoRenderListener pPSVideoRenderListener) {
        a(pPSVideoRenderListener);
    }

    public void setMediaPlayerReleaseListener(MediaPlayerReleaseListener mediaPlayerReleaseListener) {
        a(mediaPlayerReleaseListener);
    }

    public void setMediaFile(final String str) {
        b(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.9
            @Override // java.lang.Runnable
            public void run() {
                try {
                    MediaPlayerAgent.this.b(str);
                } catch (c e) {
                    ho.a("MediaPlayerAgent", "set media file error:%s", e.getMessage());
                    ho.c("MediaPlayerAgent", "set media file error:" + e.getClass().getSimpleName());
                }
            }
        });
    }

    public void setDefaultDuration(int i) {
        synchronized (this.p) {
            this.l = i;
        }
    }

    public void seekToMillis(final long j, final int i) {
        b(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.7
            @Override // java.lang.Runnable
            public void run() {
                MediaPlayerAgent.this.a(j, i);
            }
        });
    }

    public void seekTo(int i, int i2) {
        MediaPlayer mediaPlayer;
        try {
            if (!this.n.a() || this.i) {
                return;
            }
            synchronized (this.o) {
                mediaPlayer = this.c;
            }
            int h = (h() * i) / 100;
            b(mediaPlayer, h, i2);
            a(i, h);
        } catch (IllegalStateException unused) {
            ho.c("MediaPlayerAgent", "seekTo IllegalStateException");
        }
    }

    public void seekTo(int i) {
        seekTo(i, 0);
    }

    public void reset() {
        b(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.36
            @Override // java.lang.Runnable
            public void run() {
                MediaPlayerAgent.this.v();
            }
        });
    }

    public void removeVideoSizeChangeListener(MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        if (onVideoSizeChangedListener == null) {
            return;
        }
        this.K.remove(onVideoSizeChangedListener);
    }

    public void removePPSVideoRenderListener(PPSVideoRenderListener pPSVideoRenderListener) {
        if (pPSVideoRenderListener == null) {
            return;
        }
        this.L.remove(pPSVideoRenderListener);
    }

    public void removeMuteListener(MuteListener muteListener) {
        if (muteListener == null) {
            return;
        }
        this.I.remove(muteListener);
    }

    public void removeMediaStateListener(MediaStateListener mediaStateListener) {
        if (mediaStateListener == null) {
            return;
        }
        this.F.remove(mediaStateListener);
    }

    public void removeMediaPlayerReleaseListener(MediaPlayerReleaseListener mediaPlayerReleaseListener) {
        if (mediaPlayerReleaseListener == null) {
            return;
        }
        this.M.remove(mediaPlayerReleaseListener);
    }

    public void removeMediaInfoListener(com.huawei.openalliance.ad.media.listener.a aVar) {
        if (aVar == null) {
            return;
        }
        this.J.remove(aVar);
    }

    public void removeMediaErrorListener(MediaErrorListener mediaErrorListener) {
        if (mediaErrorListener == null) {
            return;
        }
        this.H.remove(mediaErrorListener);
    }

    public void removeMediaBufferListener(MediaBufferListener mediaBufferListener) {
        if (mediaBufferListener == null) {
            return;
        }
        this.G.remove(mediaBufferListener);
    }

    public void release() {
        synchronized (this.q) {
            int i = this.d - 1;
            this.d = i;
            if (i < 0) {
                this.d = 0;
            }
            if (ho.a()) {
                ho.a("MediaPlayerAgent", "release - instanceRefCount: %d - agent: %s", Integer.valueOf(this.d), this);
            }
            if (this.d == 0) {
                b(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.33
                    @Override // java.lang.Runnable
                    public void run() {
                        MediaPlayerAgent.this.u();
                    }
                });
            }
        }
    }

    public void prepare() {
        b(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.2
            @Override // java.lang.Runnable
            public void run() {
                MediaPlayerAgent.this.e();
            }
        });
    }

    public void playWhenUrlMatchs(final String str) {
        b(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.43
            @Override // java.lang.Runnable
            public void run() {
                String str2 = str;
                if (str2 == null || !TextUtils.equals(str2, MediaPlayerAgent.this.f)) {
                    ho.b("MediaPlayerAgent", "playWhenUrlMatchs - url not match");
                } else {
                    MediaPlayerAgent.this.a((Long) null);
                }
            }
        });
    }

    public void play(final Long l) {
        b(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.42
            @Override // java.lang.Runnable
            public void run() {
                MediaPlayerAgent.this.a(l);
            }
        });
    }

    public void play() {
        b(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.41
            @Override // java.lang.Runnable
            public void run() {
                MediaPlayerAgent.this.a((Long) null);
            }
        });
    }

    public void pauseWhenUrlMatchs(final String str) {
        b(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.6
            @Override // java.lang.Runnable
            public void run() {
                String str2 = str;
                if (str2 == null || !TextUtils.equals(str2, MediaPlayerAgent.this.f)) {
                    return;
                }
                MediaPlayerAgent.this.g();
            }
        });
    }

    public void pause() {
        b(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.5
            @Override // java.lang.Runnable
            public void run() {
                MediaPlayerAgent.this.g();
            }
        });
    }

    public void muteSound() {
        b(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.11
            @Override // java.lang.Runnable
            public void run() {
                MediaPlayerAgent.this.l();
            }
        });
    }

    public boolean isPlaying() {
        if (this.n.isState(MediaState.State.END)) {
            return false;
        }
        return ((Boolean) dc.a(this.T, 300L, Boolean.valueOf(this.n.isState(MediaState.State.PLAYING)))).booleanValue();
    }

    public int getInstanceRefCount() {
        int i;
        synchronized (this.q) {
            i = this.d;
        }
        return i;
    }

    public MediaState getCurrentState() {
        return this.n;
    }

    public int getCurrentPlayPosition() {
        MediaPlayer mediaPlayer;
        if (this.n.isState(MediaState.State.END) || this.n.isState(MediaState.State.ERROR) || this.n.isState(MediaState.State.IDLE)) {
            return 0;
        }
        try {
            synchronized (this.o) {
                mediaPlayer = this.c;
            }
            if (mediaPlayer != null) {
                return mediaPlayer.getCurrentPosition();
            }
            return 0;
        } catch (IllegalStateException unused) {
            ho.c("MediaPlayerAgent", "getCurrentPlayPosition IllegalStateException");
            return 0;
        }
    }

    protected void finalize() {
        super.finalize();
        b(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.35
            @Override // java.lang.Runnable
            public void run() {
                MediaPlayerAgent.this.u();
            }
        });
    }

    public void b() {
        b(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.37
            @Override // java.lang.Runnable
            public void run() {
                MediaPlayerAgent.this.x();
            }
        });
    }

    public void addReportVideoTimeListenersSet(ReportVideoTimeListener reportVideoTimeListener) {
        if (this.b == null) {
            this.b = new b(this.C);
        }
        this.b.a(reportVideoTimeListener);
    }

    public void addMuteListener(MuteListener muteListener) {
        if (muteListener == null) {
            return;
        }
        this.I.add(muteListener);
    }

    public void addMediaStateListener(MediaStateListener mediaStateListener) {
        if (mediaStateListener == null) {
            return;
        }
        this.F.add(mediaStateListener);
    }

    public void addMediaInfoListener(com.huawei.openalliance.ad.media.listener.a aVar) {
        if (aVar == null) {
            return;
        }
        this.J.add(aVar);
    }

    public void addMediaErrorListener(MediaErrorListener mediaErrorListener) {
        if (mediaErrorListener == null) {
            return;
        }
        this.H.add(mediaErrorListener);
    }

    public void addMediaBufferListener(MediaBufferListener mediaBufferListener) {
        if (mediaBufferListener == null) {
            return;
        }
        this.G.add(mediaBufferListener);
    }

    public void acquire() {
        synchronized (this.q) {
            this.d++;
            if (ho.a()) {
                ho.a("MediaPlayerAgent", "acquire - instanceRefCount: %d - agent: %s", Integer.valueOf(this.d), this);
            }
        }
    }

    boolean a(String str, MediaPlayer mediaPlayer) {
        AssetFileDescriptor openTypedAssetFileDescriptor = this.C.getContentResolver().openTypedAssetFileDescriptor(Uri.parse(str), "*/*", null);
        if (openTypedAssetFileDescriptor == null) {
            cy.a(openTypedAssetFileDescriptor);
            return false;
        }
        try {
            if (openTypedAssetFileDescriptor.getDeclaredLength() < 0) {
                mediaPlayer.setDataSource(openTypedAssetFileDescriptor.getFileDescriptor());
            } else {
                mediaPlayer.setDataSource(openTypedAssetFileDescriptor.getFileDescriptor(), openTypedAssetFileDescriptor.getStartOffset(), openTypedAssetFileDescriptor.getDeclaredLength());
            }
            cy.a(openTypedAssetFileDescriptor);
            return true;
        } catch (Throwable th) {
            cy.a(openTypedAssetFileDescriptor);
            throw th;
        }
    }

    public void a(boolean z) {
        this.B = z;
    }

    public void a(PPSVideoRenderListener pPSVideoRenderListener) {
        if (pPSVideoRenderListener == null) {
            return;
        }
        this.L.add(pPSVideoRenderListener);
    }

    public void a(MediaPlayerReleaseListener mediaPlayerReleaseListener) {
        if (mediaPlayerReleaseListener == null) {
            return;
        }
        this.M.add(mediaPlayerReleaseListener);
    }

    public void a(MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        if (onVideoSizeChangedListener == null) {
            return;
        }
        this.K.add(onVideoSizeChangedListener);
    }

    public void a(long j, int i) {
        MediaPlayer mediaPlayer;
        try {
            ho.b("MediaPlayerAgent", "seekToMillis " + j);
            if (this.n.a()) {
                synchronized (this.o) {
                    mediaPlayer = this.c;
                }
                b(mediaPlayer, j, i);
                long h = h();
                if (h > 0) {
                    a((int) ((100 * j) / h), (int) j);
                }
            }
        } catch (IllegalStateException unused) {
            ho.c("MediaPlayerAgent", "seekTo IllegalStateException");
        }
    }

    public void a(int i) {
        this.x = i;
    }

    public void a(final float f) {
        b(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.15
            @Override // java.lang.Runnable
            public void run() {
                MediaPlayerAgent.this.c(f);
            }
        });
    }

    public String a() {
        return this.f;
    }

    private boolean y() {
        ho.b("MediaPlayerAgent", "isNeedAudioFocus type: %s soundMute: %s", Integer.valueOf(this.x), Boolean.valueOf(this.w));
        if (this.x == 0) {
            return true;
        }
        if (this.x == 2) {
            return false;
        }
        return (this.x == 1 && this.w) ? false : true;
    }

    static /* synthetic */ int y(MediaPlayerAgent mediaPlayerAgent) {
        int i = mediaPlayerAgent.r;
        mediaPlayerAgent.r = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void x() {
        String str;
        try {
            try {
                ho.b("MediaPlayerAgent", "abandonAudioFocus");
                Object obj = this.y;
                if (obj instanceof AudioFocusRequest) {
                    this.s.abandonAudioFocusRequest((AudioFocusRequest) obj);
                }
                this.y = null;
            } catch (IllegalStateException unused) {
                str = "abandonAudioFocus IllegalStateException";
                ho.c("MediaPlayerAgent", str);
            } catch (Exception e) {
                str = "abandonAudioFocus " + e.getClass().getSimpleName();
                ho.c("MediaPlayerAgent", str);
            }
        } finally {
            this.u = false;
            this.t = false;
            this.v = 0;
        }
    }

    private void w() {
        String str;
        if (!y()) {
            ho.c("MediaPlayerAgent", "audio focus is not needed");
            return;
        }
        try {
            ho.b("MediaPlayerAgent", "requestAudioFocus");
            AudioFocusRequest build = new AudioFocusRequest.Builder(2).setOnAudioFocusChangeListener(this.V).build();
            this.y = build;
            this.s.requestAudioFocus(build);
        } catch (IllegalStateException unused) {
            str = "requestAudioFocus IllegalStateException";
            ho.c("MediaPlayerAgent", str);
        } catch (Exception e) {
            str = "requestAudioFocus " + e.getClass().getSimpleName();
            ho.c("MediaPlayerAgent", str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        synchronized (this.o) {
            ho.b("MediaPlayerAgent", "resetInternal - agent: %s", this);
            try {
                if (this.c != null) {
                    if (this.n.a()) {
                        int currentPosition = this.c.getCurrentPosition();
                        this.c.stop();
                        if (this.n.isState(MediaState.State.PLAYBACK_COMPLETED)) {
                            currentPosition = 0;
                        }
                        e(currentPosition);
                        a(0, 0);
                        c(0);
                    }
                    this.c.reset();
                }
            } catch (IllegalStateException unused) {
                ho.c("MediaPlayerAgent", "media player reset IllegalStateException");
            } catch (Throwable th) {
                ho.c("MediaPlayerAgent", "media player reset exception: %s", th.getClass().getSimpleName());
            }
            this.k = 0;
            this.r = 0;
            this.i = false;
            this.u = false;
            this.t = false;
            this.v = 0;
            this.A = 0;
            this.n.a(MediaState.State.IDLE);
            q();
            d(this.e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v5, types: [android.media.MediaPlayer, android.media.MediaPlayer$OnVideoSizeChangedListener] */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v9 */
    public void u() {
        String str;
        synchronized (this.o) {
            if (this.n.isState(MediaState.State.END)) {
                return;
            }
            this.n.a(MediaState.State.END);
            ho.b("MediaPlayerAgent", "release - agent: %s", this);
            f7204a.b();
            v();
            MediaPlayer mediaPlayer = this.c;
            if (mediaPlayer != null) {
                ?? r2 = 0;
                try {
                    try {
                        mediaPlayer.setSurface(null);
                        this.c.setOnVideoSizeChangedListener(null);
                        this.c.release();
                        this.c = null;
                        str = "MediaPlayerAgent";
                        r2 = "release media player";
                    } catch (Throwable th) {
                        this.c.setOnVideoSizeChangedListener(r2);
                        this.c.release();
                        this.c = r2;
                        ho.b("MediaPlayerAgent", "release media player");
                        p();
                        throw th;
                    }
                } catch (IllegalStateException unused) {
                    ho.c("MediaPlayerAgent", "media player reset surface IllegalStateException");
                    this.c.setOnVideoSizeChangedListener(null);
                    this.c.release();
                    this.c = null;
                    str = "MediaPlayerAgent";
                    r2 = "release media player";
                }
                ho.b(str, r2);
                p();
            }
            this.F.clear();
            this.G.clear();
            this.H.clear();
            this.I.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        if (ho.a()) {
            ho.a("MediaPlayerAgent", "progress");
        }
        d(this.e);
        if (this.F.size() > 0) {
            b(this.U);
        }
    }

    private void s() {
        if (!this.w) {
            ho.b("MediaPlayerAgent", "already unmuted, don't notify");
            return;
        }
        ho.b("MediaPlayerAgent", "notifyUnmute");
        this.w = false;
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.29
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = MediaPlayerAgent.this.I.iterator();
                while (it.hasNext()) {
                    MuteListener muteListener = (MuteListener) it.next();
                    if (muteListener != null) {
                        muteListener.onUnmute();
                    }
                }
            }
        });
    }

    private void r() {
        if (this.w) {
            ho.b("MediaPlayerAgent", "already muted, don't notify");
            return;
        }
        ho.b("MediaPlayerAgent", "notifyMute");
        this.w = true;
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.28
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = MediaPlayerAgent.this.I.iterator();
                while (it.hasNext()) {
                    MuteListener muteListener = (MuteListener) it.next();
                    if (muteListener != null) {
                        muteListener.onMute();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        if (this.h && this.g) {
            this.h = false;
            ho.b("MediaPlayerAgent", "notifyBufferingEnd currentState: %s", this.n);
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.22
                @Override // java.lang.Runnable
                public void run() {
                    Iterator it = MediaPlayerAgent.this.G.iterator();
                    while (it.hasNext()) {
                        MediaBufferListener mediaBufferListener = (MediaBufferListener) it.next();
                        if (mediaBufferListener != null) {
                            mediaBufferListener.onBufferingEnd();
                        }
                    }
                }
            });
        }
    }

    private void p() {
        CopyOnWriteArraySet<MediaPlayerReleaseListener> copyOnWriteArraySet = this.M;
        if (copyOnWriteArraySet == null || copyOnWriteArraySet.size() == 0) {
            return;
        }
        ho.b("MediaPlayerAgent", "notify player release");
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.21
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = MediaPlayerAgent.this.M.iterator();
                while (it.hasNext()) {
                    MediaPlayerReleaseListener mediaPlayerReleaseListener = (MediaPlayerReleaseListener) it.next();
                    if (mediaPlayerReleaseListener != null) {
                        mediaPlayerReleaseListener.onPlayerRelease();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        CopyOnWriteArraySet<PPSVideoRenderListener> copyOnWriteArraySet = this.L;
        if (copyOnWriteArraySet == null || copyOnWriteArraySet.size() == 0) {
            return;
        }
        ho.b("MediaPlayerAgent", "notifyRenderStart");
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.20
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = MediaPlayerAgent.this.L.iterator();
                while (it.hasNext()) {
                    PPSVideoRenderListener pPSVideoRenderListener = (PPSVideoRenderListener) it.next();
                    if (pPSVideoRenderListener != null) {
                        pPSVideoRenderListener.onVideoRenderStart();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (!this.h && this.g && this.G.size() > 0) {
            if (this.n.isState(MediaState.State.PLAYING) || this.n.isState(MediaState.State.PREPARING)) {
                ho.b("MediaPlayerAgent", "notifyBufferingStart currentState: %s", this.n);
                this.h = true;
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.19
                    @Override // java.lang.Runnable
                    public void run() {
                        Iterator it = MediaPlayerAgent.this.G.iterator();
                        while (it.hasNext()) {
                            MediaBufferListener mediaBufferListener = (MediaBufferListener) it.next();
                            if (mediaBufferListener != null) {
                                mediaBufferListener.onBufferingStart();
                            }
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        this.u = false;
        if (b(1.0f)) {
            s();
        }
        if (this.x == 1 && j()) {
            w();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        this.u = false;
        if (b(0.0f)) {
            r();
        }
        if (this.x == 1 && j()) {
            b();
        }
    }

    private Surface k() {
        WeakReference<Surface> weakReference = this.z;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean j() {
        MediaPlayer mediaPlayer;
        if (this.n.a()) {
            try {
                synchronized (this.o) {
                    mediaPlayer = this.c;
                }
                if (mediaPlayer != null) {
                    ho.b("MediaPlayerAgent", "in play %s", Boolean.valueOf(mediaPlayer.isPlaying()));
                    return mediaPlayer.isPlaying();
                }
            } catch (IllegalStateException unused) {
                ho.c("MediaPlayerAgent", "isPlaying IllegalStateException");
            }
        }
        return false;
    }

    private int i() {
        int i;
        synchronized (this.p) {
            i = this.l;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int h() {
        MediaPlayer mediaPlayer;
        int duration;
        if (this.n.isState(MediaState.State.END)) {
            return 0;
        }
        int i = i();
        if (!this.n.a() || this.i) {
            return i;
        }
        try {
            synchronized (this.o) {
                mediaPlayer = this.c;
            }
            return (mediaPlayer == null || (duration = mediaPlayer.getDuration()) <= 0) ? i : duration;
        } catch (IllegalStateException unused) {
            ho.c("MediaPlayerAgent", "getDuration IllegalStateException");
            return i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(final int i) {
        ho.b("MediaPlayerAgent", "notifyDurationReady: %d", Integer.valueOf(i));
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.30
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = MediaPlayerAgent.this.J.iterator();
                while (it.hasNext()) {
                    com.huawei.openalliance.ad.media.listener.a aVar = (com.huawei.openalliance.ad.media.listener.a) it.next();
                    if (aVar != null) {
                        aVar.a(i);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        ho.b("MediaPlayerAgent", "pauseInternal before State: %s - agent: %s", this.n, this);
        this.t = false;
        if (this.n.isState(MediaState.State.ERROR) || a(this.n) || b(this.n)) {
            return;
        }
        try {
            MediaPlayer c = c();
            if (c.isPlaying()) {
                c.pause();
            }
            this.n.a(MediaState.State.PAUSED);
            f(c.getCurrentPosition());
        } catch (IllegalStateException unused) {
            ho.c("MediaPlayerAgent", "pause IllegalStateException");
            this.n.a(MediaState.State.ERROR);
        }
        q();
        d(this.e);
        ho.b("MediaPlayerAgent", VastAttribute.PAUSE);
    }

    private void f(final int i) {
        ho.b("MediaPlayerAgent", "notifyMediaPause playTime: %d", Integer.valueOf(i));
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.26
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = MediaPlayerAgent.this.F.iterator();
                while (it.hasNext()) {
                    MediaStateListener mediaStateListener = (MediaStateListener) it.next();
                    if (mediaStateListener != null) {
                        mediaStateListener.onMediaPause(MediaPlayerAgent.this, i);
                    }
                }
            }
        });
        b bVar = this.b;
        if (bVar != null) {
            bVar.b(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        d(true);
    }

    private void e(final int i) {
        ho.b("MediaPlayerAgent", "notifyMediaStop playTime: %d", Integer.valueOf(i));
        b();
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.25
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = MediaPlayerAgent.this.F.iterator();
                while (it.hasNext()) {
                    MediaStateListener mediaStateListener = (MediaStateListener) it.next();
                    if (mediaStateListener != null) {
                        mediaStateListener.onMediaStop(MediaPlayerAgent.this, i);
                    }
                }
            }
        });
        b bVar = this.b;
        if (bVar != null) {
            bVar.a(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        ho.b("MediaPlayerAgent", "prepareInternal - current state: %s - agent: %s", this.n, this);
        if (this.n.isState(MediaState.State.END)) {
            return;
        }
        if (this.D && k() == null) {
            ho.b("MediaPlayerAgent", "setSurfaceFirst, current surface is null, prepare on setSurface.");
            this.E = true;
            return;
        }
        ho.b("MediaPlayerAgent", "prepareInternal - current state after set file: %s", this.n);
        if (this.n.isState(MediaState.State.INITIALIZED)) {
            this.j = true;
            b(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        if (this.n.isState(MediaState.State.END) || this.n.isState(MediaState.State.ERROR) || this.n.isState(MediaState.State.IDLE)) {
            return;
        }
        if (this.n.a() || this.n.isState(MediaState.State.PREPARING)) {
            try {
                MediaPlayer c = c();
                int currentPosition = c.getCurrentPosition();
                if (this.n.a() && !this.i) {
                    c.stop();
                }
                if (this.n.isState(MediaState.State.PLAYBACK_COMPLETED)) {
                    currentPosition = 0;
                }
                e(currentPosition);
                if (z) {
                    a(0, 0);
                }
                this.n.a(MediaState.State.INITIALIZED);
            } catch (IllegalStateException unused) {
                ho.c("MediaPlayerAgent", "stop IllegalStateException");
                this.n.a(MediaState.State.ERROR);
            }
        }
        this.k = 0;
        this.r = 0;
        q();
        d(this.e);
        ho.b("MediaPlayerAgent", "stop - agent: %s", this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(String str) {
        f7204a.a(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final int i) {
        ho.b("MediaPlayerAgent", "notifyMediaStart playTime: %d", Integer.valueOf(i));
        w();
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.24
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = MediaPlayerAgent.this.F.iterator();
                while (it.hasNext()) {
                    MediaStateListener mediaStateListener = (MediaStateListener) it.next();
                    if (mediaStateListener != null) {
                        mediaStateListener.onMediaStart(MediaPlayerAgent.this, i);
                    }
                }
            }
        });
        b bVar = this.b;
        if (bVar != null) {
            bVar.a();
        }
    }

    private void d() {
        try {
            b(this.f);
            ho.b("MediaPlayerAgent", "play - current state after set file: %s", this.n);
            if (this.n.isState(MediaState.State.INITIALIZED)) {
                b(true);
            }
        } catch (c e) {
            ho.a("MediaPlayerAgent", "set media file error:%s", e.getMessage());
            ho.c("MediaPlayerAgent", "set media file error:" + e.getClass().getSimpleName());
            this.n.a(MediaState.State.ERROR);
            a(0, -1, -1);
        }
    }

    private void c(final boolean z) {
        b(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.3
            @Override // java.lang.Runnable
            public void run() {
                MediaPlayerAgent.this.d(z);
            }
        });
    }

    private void c(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        MediaPlayer c = c();
        if (Uri.parse(str).getScheme() != null) {
            if (!str.startsWith(Scheme.FILE.toString())) {
                if (str.startsWith(Scheme.DISKCACHE.toString())) {
                    String c2 = dh.a(this.C, "normal").c(str);
                    if (!ae.d(new File(c2))) {
                        c2 = dh.a(this.C, Constants.TPLATE_CACHE).c(str);
                    }
                    c.setDataSource(c2);
                } else if (str.startsWith(Scheme.CONTENT.toString())) {
                    if (!a(str, c)) {
                        ho.c("MediaPlayerAgent", "set remote media fail");
                        throw new c();
                    }
                } else if (str.startsWith(Scheme.HTTP.toString()) || str.startsWith(Scheme.HTTPS.toString())) {
                    this.g = true;
                }
                c.setVideoScalingMode(1);
                this.n.a(MediaState.State.INITIALIZED);
            }
            str = str.substring(Scheme.FILE.toString().length());
        }
        c.setDataSource(str);
        c.setVideoScalingMode(1);
        this.n.a(MediaState.State.INITIALIZED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final int i) {
        if (this.g) {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.18
                @Override // java.lang.Runnable
                public void run() {
                    Iterator it = MediaPlayerAgent.this.G.iterator();
                    while (it.hasNext()) {
                        MediaBufferListener mediaBufferListener = (MediaBufferListener) it.next();
                        if (mediaBufferListener != null) {
                            mediaBufferListener.onBufferUpdate(i);
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(float f) {
        this.u = false;
        if (b(f)) {
            s();
        }
        if (this.x == 1 && j()) {
            w();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaPlayer c() {
        MediaPlayer mediaPlayer;
        synchronized (this.o) {
            if (this.c == null) {
                MediaPlayer mediaPlayer2 = new MediaPlayer();
                mediaPlayer2.setOnCompletionListener(this.O);
                mediaPlayer2.setOnPreparedListener(this.Q);
                mediaPlayer2.setOnErrorListener(this.R);
                mediaPlayer2.setOnBufferingUpdateListener(this.S);
                mediaPlayer2.setOnVideoSizeChangedListener(this.N);
                mediaPlayer2.setLooping(false);
                mediaPlayer2.setAudioStreamType(3);
                this.c = mediaPlayer2;
            }
            mediaPlayer = this.c;
        }
        return mediaPlayer;
    }

    private boolean b(MediaState mediaState) {
        return mediaState.isState(MediaState.State.END) || mediaState.isState(MediaState.State.PAUSED) || mediaState.isState(MediaState.State.PLAYBACK_COMPLETED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(float f) {
        if (this.n.isState(MediaState.State.END)) {
            return false;
        }
        try {
            c().setVolume(f, f);
            return true;
        } catch (IllegalStateException unused) {
            ho.c("MediaPlayerAgent", "mute IllegalStateException");
            return false;
        }
    }

    private void b(boolean z) {
        if (this.n.isState(MediaState.State.END)) {
            return;
        }
        try {
            ho.b("MediaPlayerAgent", "prepareMediaPlayer");
            this.n.a(MediaState.State.PREPARING);
            this.i = true;
            c().prepareAsync();
            if (z) {
                n();
            }
        } catch (IllegalStateException unused) {
            ho.c("MediaPlayerAgent", "prepareMediaPlayer IllegalStateException");
            this.n.a(MediaState.State.ERROR);
            a(0, -1, -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        if (this.n.isState(MediaState.State.END)) {
            return;
        }
        ho.a("MediaPlayerAgent", "setMediaFileUrl: %s", dl.a(str));
        MediaPlayer c = c();
        try {
            if (this.n.a()) {
                c.stop();
            }
        } catch (IllegalStateException unused) {
            ho.c("MediaPlayerAgent", "setMediaFileUrl stop IllegalStateException");
        } catch (Throwable th) {
            ho.c("MediaPlayerAgent", "setMediaFileUrl exception: %s", th.getClass().getSimpleName());
        }
        try {
            c.reset();
            this.n.a(MediaState.State.IDLE);
        } catch (Throwable th2) {
            ho.c("MediaPlayerAgent", "mediaPlayer reset exception: %s", th2.getClass().getSimpleName());
        }
        this.f = str;
        if (TextUtils.isEmpty(str)) {
            ho.c("MediaPlayerAgent", "media file url is empty");
            this.n.a(MediaState.State.ERROR);
            throw new c("media file url is empty");
        }
        try {
            c(str);
        } catch (Exception unused2) {
            ho.c("MediaPlayerAgent", "setMediaFileUrl Exception");
            this.n.a(MediaState.State.ERROR);
            throw new c("setMediaFileUrl Exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Runnable runnable, String str, long j) {
        f7204a.a(runnable, str, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Runnable runnable) {
        f7204a.a(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(MediaPlayer mediaPlayer, long j, int i) {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(j, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, final int i2) {
        ho.b("MediaPlayerAgent", "notifyVideoPictureNotPlaying");
        if (i2 < -10000 || i2 == -1004) {
            int i3 = this.A;
            if (i3 < 20) {
                this.A = i3 + 1;
                c(false);
                play();
            } else {
                stop();
                this.R.onError(c(), i, i2);
            }
        }
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.31
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = MediaPlayerAgent.this.J.iterator();
                while (it.hasNext()) {
                    com.huawei.openalliance.ad.media.listener.a aVar = (com.huawei.openalliance.ad.media.listener.a) it.next();
                    if (aVar != null) {
                        aVar.b(i2);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final int i) {
        ho.b("MediaPlayerAgent", "notifyMediaCompletion playTime: %d", Integer.valueOf(i));
        b();
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.17
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = MediaPlayerAgent.this.F.iterator();
                while (it.hasNext()) {
                    MediaStateListener mediaStateListener = (MediaStateListener) it.next();
                    if (mediaStateListener != null) {
                        mediaStateListener.onMediaCompletion(MediaPlayerAgent.this, i);
                    }
                }
            }
        });
        b bVar = this.b;
        if (bVar != null) {
            bVar.a(i);
        }
    }

    private boolean a(MediaState mediaState) {
        return mediaState.isState(MediaState.State.INITIALIZED) || mediaState.isState(MediaState.State.IDLE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00af A[Catch: IllegalStateException -> 0x00c1, TryCatch #0 {IllegalStateException -> 0x00c1, blocks: (B:26:0x008a, B:28:0x008f, B:29:0x00a0, B:30:0x00a3, B:33:0x00b3, B:37:0x00af, B:38:0x0094, B:40:0x009e), top: B:25:0x008a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.lang.Long r8) {
        /*
            Method dump skipped, instructions count: 284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.media.MediaPlayerAgent.a(java.lang.Long):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Surface surface) {
        String str;
        if (this.n.isState(MediaState.State.END)) {
            return;
        }
        if (surface != null && !surface.isValid()) {
            ho.c("MediaPlayerAgent", "setSurfaceInternal - surface is invalid");
            return;
        }
        if (surface == k()) {
            ho.b("MediaPlayerAgent", "setSurfaceInternal - pass-in surface is the same as currentSurface");
            return;
        }
        this.z = new WeakReference<>(surface);
        try {
            ho.b("MediaPlayerAgent", "setSurfaceInternal, prepareOnSetSurface: %s", Boolean.valueOf(this.E));
            c().setSurface(surface);
            if (this.E) {
                this.E = false;
                e();
            }
        } catch (IllegalArgumentException unused) {
            str = "setSurface IllegalArgumentException";
            ho.c("MediaPlayerAgent", str);
        } catch (IllegalStateException unused2) {
            str = "setSurface IllegalStateException";
            ho.c("MediaPlayerAgent", str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(MediaPlayer mediaPlayer, int i, int i2) {
        Iterator<MediaPlayer.OnVideoSizeChangedListener> it = this.K.iterator();
        while (it.hasNext()) {
            it.next().onVideoSizeChanged(mediaPlayer, i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i, final int i2, final int i3) {
        ho.b("MediaPlayerAgent", "notifyError playTime: %d", Integer.valueOf(i));
        b();
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.27
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = MediaPlayerAgent.this.H.iterator();
                while (it.hasNext()) {
                    MediaErrorListener mediaErrorListener = (MediaErrorListener) it.next();
                    if (mediaErrorListener != null) {
                        mediaErrorListener.onError(MediaPlayerAgent.this, i, i2, i3);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i, final int i2) {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.media.MediaPlayerAgent.16
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = MediaPlayerAgent.this.F.iterator();
                while (it.hasNext()) {
                    MediaStateListener mediaStateListener = (MediaStateListener) it.next();
                    if (mediaStateListener != null) {
                        mediaStateListener.onProgress(i, i2);
                    }
                }
            }
        });
    }

    public MediaPlayerAgent(Context context) {
        this.D = false;
        Context applicationContext = context.getApplicationContext();
        this.C = applicationContext;
        this.D = HiAd.a(applicationContext).n();
        this.s = (AudioManager) context.getSystemService(PresenterUtils.AUDIO);
        this.e = "progress_task" + hashCode();
        f7204a.a();
    }
}
