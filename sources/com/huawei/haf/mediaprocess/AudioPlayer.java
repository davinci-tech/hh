package com.huawei.haf.mediaprocess;

import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.SystemClock;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import health.compact.a.LogUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class AudioPlayer implements AudioManager.OnAudioFocusChangeListener {
    private static final Object d = new Object();
    private static final Object e = new Object();
    private long aa;
    private CountDownTimer ab;
    private int ac;
    private Timer ad;
    private AudioAttributes c;
    private AudioManager f;
    private volatile LinkedList<Integer> g;
    private List<Uri> h;
    private AudioPlayCallback i;
    private long j;
    private int k;
    private MediaPlayer l;
    private AudioFocusRequest m;
    private boolean n;
    private long o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private MediaPlayer u;
    private long w;
    private boolean x;
    private boolean y;
    private MediaPlayer.OnCompletionListener z;

    /* renamed from: a, reason: collision with root package name */
    private boolean f2127a = true;
    private boolean b = false;
    private volatile boolean v = false;

    public AudioPlayer() {
        Object systemService = BaseApplication.e().getSystemService(PresenterUtils.AUDIO);
        this.f = systemService instanceof AudioManager ? (AudioManager) systemService : null;
        this.h = new ArrayList();
        this.q = false;
        this.k = -1;
        this.n = false;
        this.t = false;
        this.i = null;
        this.ac = 1;
        this.y = false;
        this.x = false;
        this.g = new LinkedList<>();
        this.z = new MediaPlayer.OnCompletionListener() { // from class: com.huawei.haf.mediaprocess.AudioPlayer$$ExternalSyntheticLambda0
            @Override // android.media.MediaPlayer.OnCompletionListener
            public final void onCompletion(MediaPlayer mediaPlayer) {
                AudioPlayer.this.yY_(mediaPlayer);
            }
        };
    }

    /* synthetic */ void yY_(MediaPlayer mediaPlayer) {
        LogUtil.c("AudioPlayer", "onCompletion is called mCountDownTimerUnFinishedMills=", Long.valueOf(this.o), " isCurrentPlayer=", Boolean.valueOf(this.f2127a));
        this.s = true;
        if (this.n) {
            LogUtil.a("AudioPlayer", "audio focus loss");
            c(1004, "audio focus loss");
        }
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int i) {
        if (i == -3) {
            e(0.5f, 0.5f);
            LogUtil.d("AudioPlayer", "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
            return;
        }
        if (i == -2) {
            a();
            this.r = true;
            c(1006, "AUDIOFOCUS_LOSS");
            LogUtil.d("AudioPlayer", "AUDIOFOCUS_LOSS_TRANSIENT");
            return;
        }
        if (i == -1) {
            this.n = true;
            a();
            g();
            c(1004, "AUDIOFOCUS_LOSS");
            LogUtil.d("AudioPlayer", "AUDIOFOCUS_LOSS");
            return;
        }
        if (i != 1) {
            return;
        }
        if (this.r) {
            f();
        }
        e(1.0f, 1.0f);
        this.r = false;
        this.n = false;
        LogUtil.d("AudioPlayer", "AUDIOFOCUS_GAIN");
    }

    private void b(int i, int i2) {
        CountDownTimer countDownTimer = this.ab;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if (i <= 0 || i2 <= 0) {
            LogUtil.e("AudioPlayer", "setDownTimerByTime totalTime or time is null");
        } else {
            this.ab = new CountDownTimer(i, i2) { // from class: com.huawei.haf.mediaprocess.AudioPlayer.1
                @Override // com.huawei.haf.mediaprocess.CountDownTimer
                public void onTick(long j) {
                    int i3;
                    if (!AudioPlayer.this.v) {
                        AudioPlayer.this.m();
                        AudioPlayer.this.o = j;
                        if (!AudioPlayer.this.s) {
                            AudioPlayer audioPlayer = AudioPlayer.this;
                            i3 = audioPlayer.yU_(audioPlayer.f2127a ? AudioPlayer.this.l : AudioPlayer.this.u);
                        } else {
                            LogUtil.c("AudioPlayer", "player is call completion millisUntilFinished=", Long.valueOf(j));
                            i3 = 0;
                        }
                        if (i3 > 0 && j > 0) {
                            long j2 = i3;
                            long abs = Math.abs(j2 - j);
                            if (abs >= 500) {
                                LogUtil.c("AudioPlayer", "setDownTimerByTime millisUntilFinished=", Long.valueOf(j), " duration=", Integer.valueOf(i3), " differMills=", Long.valueOf(abs));
                                AudioPlayer.this.ab.setMillisInFuture(j2, i3 > 3000 ? 1000 : 10);
                            }
                        }
                        if (i3 > 3000) {
                            return;
                        }
                        AudioPlayer.this.b(i3);
                        return;
                    }
                    LogUtil.e("AudioPlayer", "setDownTimerByTime player release is call");
                }

                @Override // com.huawei.haf.mediaprocess.CountDownTimer
                public void onFinish() {
                    LogUtil.c("AudioPlayer", "setDownTimerByTime countDownTimer onFinish mIsPlayerRelease=", Boolean.valueOf(AudioPlayer.this.v), " mCountDownTimerUnFinishedMills=", Long.valueOf(AudioPlayer.this.o));
                    if (!AudioPlayer.this.i()) {
                        if (!AudioPlayer.this.x) {
                            AudioPlayer.this.c(21, "all audio play finish");
                            AudioPlayer.this.h();
                            return;
                        } else {
                            LogUtil.c("AudioPlayer", "back to first song");
                            AudioPlayer.this.j();
                            AudioPlayer.this.c(10, "play next audio");
                            return;
                        }
                    }
                    LogUtil.e("AudioPlayer", "onFinish abnormal end");
                    AudioPlayer.this.c(10, "play next audio");
                }
            }.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i() {
        LogUtil.c("AudioPlayer", "dealAbnormalEnd mCurrentIndex=", Integer.valueOf(this.k));
        int i = this.k;
        if (i < 0) {
            LogUtil.e("AudioPlayer", "dealAbnormalEnd illegal index");
            return false;
        }
        if (this.q) {
            LogUtil.c("AudioPlayer", "resume play from abnormal end in loop mode");
            a(this.k);
        } else if (!this.y) {
            if (i >= this.h.size() - 1) {
                return false;
            }
            LogUtil.c("AudioPlayer", "resume play from abnormal end mIsRepeat=", Boolean.valueOf(this.x));
            a(this.k + 1);
        } else {
            LogUtil.c("AudioPlayer", "resume play from abnormal end in random mode");
            a(e(true));
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (!this.v) {
            if (!this.b) {
                CountDownTimer countDownTimer = this.ab;
                if (countDownTimer == null) {
                    LogUtil.a("AudioPlayer", "mTimerPlay is null");
                    return;
                }
                if (countDownTimer.getCurrentTickInterval() != 10) {
                    LogUtil.c("AudioPlayer", "changeTimerInterval mCountDownTimerUnFinishedMills=", Long.valueOf(this.o), " currentPlayer remainDuration=", Integer.valueOf(i));
                    this.ab.setMillisInFuture(i, 10L);
                }
                if (!this.q) {
                    if (!this.y && this.k + 1 >= this.h.size()) {
                        return;
                    } else {
                        o();
                    }
                }
                this.b = true;
            }
            if (i > 1000) {
                return;
            }
            if (this.f2127a) {
                yS_(this.l, this.u, i);
                return;
            } else {
                yS_(this.u, this.l, i);
                return;
            }
        }
        LogUtil.e("AudioPlayer", "changeTimerInterval release is call");
    }

    private void yS_(MediaPlayer mediaPlayer, MediaPlayer mediaPlayer2, int i) {
        if (this.v || mediaPlayer == null || mediaPlayer2 == null) {
            LogUtil.e("AudioPlayer", "changeToNextPlayer release is call or currentPlayer|otherPlayer is null");
            return;
        }
        if (!yV_(mediaPlayer2)) {
            mediaPlayer2.setVolume(0.25f, 0.25f);
            mediaPlayer2.start();
            LogUtil.c("AudioPlayer", "changeToNextPlayer start otherPlayer remainDuration=", Integer.valueOf(i), " mCountDownTimerUnFinishedMills=", Long.valueOf(this.o));
        }
        if (i <= 1000 && i >= 500) {
            float f = 1.0f - (i / 1000.0f);
            float f2 = 1.0f - f;
            try {
                mediaPlayer.setVolume(f2, f2);
                float f3 = f + 0.25f;
                mediaPlayer2.setVolume(f3, f3);
            } catch (IllegalStateException e2) {
                LogUtil.e("AudioPlayer", "changeToNextPlayer setVolume IllegalStateException=", ExceptionUtils.d(e2));
            }
        }
        if (i <= 500) {
            LogUtil.c("AudioPlayer", "changeToNextPlayer end currentPlayer remainDuration=", Integer.valueOf(i), " mCountDownTimerUnFinishedMills=", Long.valueOf(this.o));
            if (yV_(mediaPlayer)) {
                mediaPlayer.seekTo(0);
                mediaPlayer.pause();
            }
            try {
                mediaPlayer2.setVolume(1.0f, 1.0f);
            } catch (IllegalStateException e3) {
                LogUtil.e("AudioPlayer", "otherPlayer setVolume IllegalStateException=", ExceptionUtils.d(e3));
            }
            this.f2127a = !this.f2127a;
            int yU_ = yU_(mediaPlayer2);
            LogUtil.c("AudioPlayer", "changeToNextPlayer setMillisInFuture duration=", Integer.valueOf(yU_), " isCurrentPlayer=", Boolean.valueOf(this.f2127a));
            this.ab.setMillisInFuture(yU_, 1000L);
            this.s = false;
            this.b = false;
            this.p = false;
            if (!this.q) {
                if (this.y) {
                    int e4 = e(true);
                    this.k = e4;
                    LogUtil.c("AudioPlayer", "random mode next index:", Integer.valueOf(e4));
                } else if (this.k + 1 < this.h.size()) {
                    this.k++;
                }
                LogUtil.c("AudioPlayer", "changeToNextPlayer mCurrentIndex=", Integer.valueOf(this.k));
            }
            c(10, "play next audio");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int yU_(MediaPlayer mediaPlayer) {
        int i = 0;
        try {
            if (mediaPlayer != null) {
                i = mediaPlayer.getDuration() - mediaPlayer.getCurrentPosition();
            } else {
                LogUtil.e("AudioPlayer", "getRemainDuration player is null", " isCurrentPlayer=", Boolean.valueOf(this.f2127a));
            }
        } catch (IllegalStateException e2) {
            LogUtil.e("AudioPlayer", "getRemainDuration isCurrentPlayer=", Boolean.valueOf(this.f2127a), " IllegalStateException: ", ExceptionUtils.d(e2));
        }
        if (i < 0) {
            LogUtil.e("AudioPlayer", "getRemainDuration remainDuration=", Integer.valueOf(i), " isCurrentPlayer=", Boolean.valueOf(this.f2127a), " mCountDownTimerUnFinishedMills=", Long.valueOf(this.o));
        }
        return i;
    }

    public AudioPlayer yZ_(Uri uri) {
        this.h.add(uri);
        s();
        return this;
    }

    public AudioPlayer d(List<Uri> list) {
        List<Uri> list2 = this.h;
        this.h = new ArrayList(list);
        if (c() && this.k < list2.size()) {
            Uri uri = list2.get(this.k);
            int i = this.k;
            int i2 = 0;
            while (true) {
                if (i2 >= this.h.size()) {
                    break;
                }
                if (uri.equals(this.h.get(i2))) {
                    i = i2;
                    break;
                }
                i2++;
            }
            this.k = i;
        }
        s();
        LogUtil.c("AudioPlayer", "audio list size=", Integer.valueOf(this.h.size()));
        return this;
    }

    private void s() {
        synchronized (this) {
            this.g = new LinkedList<>();
            for (int i = 0; i < this.h.size(); i++) {
                this.g.add(Integer.valueOf(i));
            }
            if (this.y) {
                Collections.shuffle(this.g);
                LogUtil.c("AudioPlayer", "queue after shuffle:", this.g.toString());
            }
        }
    }

    public AudioPlayer d(long j, TimeUnit timeUnit) {
        long millis = timeUnit.toMillis(j);
        this.w = millis;
        LogUtil.c("AudioPlayer", "keep is call mKeepMills=", Long.valueOf(millis));
        this.t = true;
        if (c()) {
            k();
        }
        return this;
    }

    public boolean c() {
        boolean yV_ = yV_(this.f2127a ? this.l : this.u);
        LogUtil.c("AudioPlayer", "isCurrentPlayer=", Boolean.valueOf(this.f2127a), " isPlaying=", Boolean.valueOf(yV_));
        return yV_;
    }

    private boolean yV_(MediaPlayer mediaPlayer) {
        boolean z = false;
        try {
            if (mediaPlayer == null) {
                LogUtil.a("AudioPlayer", "isPlaying player is null,isCurrentPlayer=", Boolean.valueOf(this.f2127a));
            } else {
                z = mediaPlayer.isPlaying();
            }
        } catch (IllegalStateException e2) {
            LogUtil.e("AudioPlayer", "isPlaying isCurrentPlayer=", Boolean.valueOf(this.f2127a), " IllegalStateException=", ExceptionUtils.d(e2));
        }
        return z;
    }

    public boolean b() {
        LogUtil.c("AudioPlayer", "cancelKeep is call");
        if (!this.t) {
            LogUtil.e("AudioPlayer", "not keep mode");
            return false;
        }
        if (this.ad == null) {
            return true;
        }
        LogUtil.c("AudioPlayer", "cancelKeep timer is cancel");
        this.ad.cancel();
        this.ad = null;
        return true;
    }

    public AudioPlayer a(AudioPlayCallback audioPlayCallback) {
        this.i = audioPlayCallback;
        return this;
    }

    public AudioPlayer e(float f, float f2) {
        if (!this.f2127a) {
            if (this.u != null && d() != -1) {
                this.u.setVolume(f, f2);
            }
        } else if (this.l != null && d() != -1) {
            this.l.setVolume(f, f2);
        } else {
            LogUtil.a("AudioPlayer", "mPlayer released, setVolume fail");
        }
        return this;
    }

    public AudioPlayer d(int i) {
        this.ac = i;
        return this;
    }

    public AudioPlayer c(boolean z) {
        LogUtil.c("AudioPlayer", "isLoop=", Boolean.valueOf(z));
        if (!this.q && z) {
            this.q = true;
            o();
        } else {
            this.q = z;
        }
        return this;
    }

    public void a() {
        if (!this.f2127a) {
            yW_(this.u);
        } else {
            yW_(this.l);
        }
        CountDownTimer countDownTimer = this.ab;
        if (countDownTimer != null) {
            countDownTimer.pause();
        }
    }

    private void yW_(MediaPlayer mediaPlayer) {
        if (yV_(mediaPlayer)) {
            LogUtil.c("AudioPlayer", "pause is called");
            try {
                mediaPlayer.pause();
                l();
                c(2, "mediaPlayer paused");
                return;
            } catch (IllegalStateException e2) {
                LogUtil.e("AudioPlayer", "pausePlay IllegalStateException=", ExceptionUtils.d(e2));
                return;
            }
        }
        LogUtil.a("AudioPlayer", "mPlayer in wrong state");
        c(1002, "mPlayer in wrong state");
    }

    public void f() {
        LogUtil.c("AudioPlayer", "resume is called");
        try {
            if (!this.f2127a) {
                yX_(this.u);
            } else {
                yX_(this.l);
            }
        } catch (IllegalStateException unused) {
            LogUtil.e("AudioPlayer", "call start at wrong state");
            c(1002, "call start at wrong state");
        }
    }

    private void yX_(MediaPlayer mediaPlayer) {
        if (yV_(mediaPlayer) || mediaPlayer == null) {
            return;
        }
        if (!n()) {
            LogUtil.a("AudioPlayer", "request focus fail");
            this.n = true;
            return;
        }
        mediaPlayer.start();
        this.s = false;
        c(3, "resume play");
        int yU_ = yU_(mediaPlayer);
        int i = yU_ > 3000 ? 1000 : 10;
        CountDownTimer countDownTimer = this.ab;
        if (countDownTimer == null) {
            b(yU_, i);
        } else {
            countDownTimer.setMillisInFuture(yU_, i);
        }
        this.n = false;
        k();
    }

    public int d() {
        MediaPlayer mediaPlayer;
        MediaPlayer mediaPlayer2;
        try {
            boolean z = this.f2127a;
            if (z && (mediaPlayer2 = this.l) != null) {
                return mediaPlayer2.getCurrentPosition();
            }
            if (z || (mediaPlayer = this.u) == null) {
                return -1;
            }
            return mediaPlayer.getCurrentPosition();
        } catch (IllegalStateException e2) {
            LogUtil.e("AudioPlayer", "getCurrentPosition IllegalStateException: ", ExceptionUtils.d(e2));
            return -1;
        }
    }

    public int e() {
        MediaPlayer mediaPlayer;
        MediaPlayer mediaPlayer2;
        try {
            boolean z = this.f2127a;
            if (z && (mediaPlayer2 = this.l) != null) {
                return mediaPlayer2.getDuration();
            }
            if (z || (mediaPlayer = this.u) == null) {
                return -1;
            }
            return mediaPlayer.getDuration();
        } catch (IllegalStateException e2) {
            LogUtil.e("AudioPlayer", "getAudioDuration IllegalStateException: ", ExceptionUtils.d(e2));
            return -1;
        }
    }

    public void h() {
        LogUtil.c("AudioPlayer", "release is call");
        this.v = true;
        if (this.l != null) {
            synchronized (d) {
                MediaPlayer mediaPlayer = this.l;
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                    this.l = null;
                    c(22, "mCurrentPlayer release");
                } else {
                    c(22, "mPlayer release before");
                }
            }
        }
        if (this.u != null) {
            synchronized (e) {
                MediaPlayer mediaPlayer2 = this.u;
                if (mediaPlayer2 != null) {
                    mediaPlayer2.release();
                    this.u = null;
                }
            }
        }
        g();
        Timer timer = this.ad;
        if (timer != null) {
            timer.cancel();
            this.ad = null;
        }
        CountDownTimer countDownTimer = this.ab;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.ab = null;
        }
    }

    public boolean j() {
        return a(0);
    }

    public boolean a(int i) {
        a();
        if (i >= 0 && i < this.h.size()) {
            if (!n()) {
                c(1003, "requestAudioFocus() fail");
                this.n = true;
                return false;
            }
            this.n = false;
            this.v = false;
            this.k = i;
            LogUtil.c("AudioPlayer", "play mCurrentIndex=", Integer.valueOf(i));
            Uri yT_ = yT_(this.k);
            if (yT_ == null) {
                LogUtil.e("AudioPlayer", "play audio uri is null");
                return false;
            }
            try {
                synchronized (d) {
                    MediaPlayer mediaPlayer = this.l;
                    if (mediaPlayer != null) {
                        mediaPlayer.release();
                    }
                    MediaPlayer mediaPlayer2 = this.u;
                    if (mediaPlayer2 != null) {
                        mediaPlayer2.reset();
                    }
                    MediaPlayer mediaPlayer3 = new MediaPlayer();
                    this.l = mediaPlayer3;
                    mediaPlayer3.setAudioAttributes(new AudioAttributes.Builder().setUsage(this.ac).setContentType(2).build());
                    this.l.setDataSource(BaseApplication.e(), yT_);
                    this.l.prepare();
                    this.l.setOnCompletionListener(this.z);
                    this.l.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.huawei.haf.mediaprocess.AudioPlayer.2
                        @Override // android.media.MediaPlayer.OnErrorListener
                        public boolean onError(MediaPlayer mediaPlayer4, int i2, int i3) {
                            LogUtil.e("AudioPlayer", " error on ErrorListener, what:", Integer.valueOf(i2), " extra:", Integer.valueOf(i3));
                            AudioPlayer.this.c(1002, " error on ErrorListener, what:" + i2 + " extra:" + i3);
                            return false;
                        }
                    });
                    this.l.start();
                    this.f2127a = true;
                    this.p = false;
                    this.s = false;
                    c(3, "playing");
                    int yU_ = yU_(this.l);
                    LogUtil.c("AudioPlayer", "play start mCurrentPlayer duration=", Integer.valueOf(yU_));
                    b(yU_, 1000);
                }
                LogUtil.c("AudioPlayer", "start play ", "mIsKeepMode:", Boolean.valueOf(this.t));
                if (this.q) {
                    o();
                }
                k();
                return true;
            } catch (IOException e2) {
                LogUtil.e("AudioPlayer", "mPlayer play fail:", ExceptionUtils.d(e2));
                c(1002, "mPlayer play fail");
                return false;
            } catch (IllegalStateException e3) {
                LogUtil.e("AudioPlayer", "mPlayer at illegal state", ExceptionUtils.d(e3));
                c(1002, "mPlayer at illegal state");
                return false;
            } catch (RuntimeException e4) {
                LogUtil.e("AudioPlayer", "mPlayer at runtimeException:", ExceptionUtils.d(e4));
                c(1002, "mPlayer at runtimeException");
                return false;
            }
        }
        LogUtil.e("AudioPlayer", "error play index:", Integer.valueOf(i));
        c(1001, "out of index");
        return false;
    }

    private void o() {
        int i = this.k + 1;
        if (this.y) {
            i = e(false);
        }
        if (this.q) {
            i = this.k;
        }
        if (i >= this.h.size()) {
            return;
        }
        LogUtil.c("AudioPlayer", "initNextPlayer nextIndex=", Integer.valueOf(i), " isCurrentPlayer=", Boolean.valueOf(this.f2127a), " mIsRandom=", Boolean.valueOf(this.y), " mIsLoop=", Boolean.valueOf(this.q));
        try {
            if (this.f2127a) {
                g(i);
            } else {
                e(i);
            }
        } catch (IOException e2) {
            LogUtil.e("AudioPlayer", "mNextPlayer prepare fail:", ExceptionUtils.d(e2));
            c(1001, "mNextPlayer prepare  fail");
        }
    }

    private void g(int i) throws IOException {
        LogUtil.c("AudioPlayer", "setNextPlayer is call");
        synchronized (e) {
            MediaPlayer mediaPlayer = this.u;
            if (mediaPlayer != null) {
                mediaPlayer.release();
                this.u = null;
            }
            Uri yT_ = yT_(i);
            if (yT_ == null) {
                LogUtil.e("AudioPlayer", "setNextPlayer audio uri is null");
                this.u = null;
                return;
            }
            MediaPlayer mediaPlayer2 = new MediaPlayer();
            this.u = mediaPlayer2;
            mediaPlayer2.setAudioAttributes(new AudioAttributes.Builder().setUsage(this.ac).setContentType(2).build());
            this.u.setDataSource(BaseApplication.e(), yT_);
            this.u.prepare();
            this.u.setOnCompletionListener(this.z);
            this.u.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.huawei.haf.mediaprocess.AudioPlayer.3
                @Override // android.media.MediaPlayer.OnErrorListener
                public boolean onError(MediaPlayer mediaPlayer3, int i2, int i3) {
                    LogUtil.e("AudioPlayer", " mNextPlayer error on ErrorListener, what:", Integer.valueOf(i2), " extra:", Integer.valueOf(i3));
                    AudioPlayer.this.c(1002, " mNextPlayer error on ErrorListener, what:" + i2 + " extra:" + i3);
                    return false;
                }
            });
        }
    }

    private Uri yT_(int i) {
        Uri uri = (i < 0 || i >= this.h.size()) ? null : this.h.get(i);
        if (uri == null) {
            c(1001, "fatal error: audio uri is null");
        }
        return uri;
    }

    private void e(int i) throws IOException {
        LogUtil.c("AudioPlayer", "setCurrentPlayer is call");
        synchronized (d) {
            MediaPlayer mediaPlayer = this.l;
            if (mediaPlayer != null) {
                mediaPlayer.release();
                this.l = null;
            }
            Uri yT_ = yT_(i);
            if (yT_ == null) {
                LogUtil.e("AudioPlayer", "setCurrentPlayer audio uri is null");
                this.l = null;
                return;
            }
            MediaPlayer mediaPlayer2 = new MediaPlayer();
            this.l = mediaPlayer2;
            mediaPlayer2.setAudioAttributes(new AudioAttributes.Builder().setUsage(this.ac).setContentType(2).build());
            this.l.setDataSource(BaseApplication.e(), yT_);
            this.l.prepare();
            this.l.setOnCompletionListener(this.z);
            this.l.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.huawei.haf.mediaprocess.AudioPlayer.4
                @Override // android.media.MediaPlayer.OnErrorListener
                public boolean onError(MediaPlayer mediaPlayer3, int i2, int i3) {
                    LogUtil.e("AudioPlayer", " mCurrentPlayer error on ErrorListener, what:", Integer.valueOf(i2), " extra:", Integer.valueOf(i3));
                    AudioPlayer.this.c(1002, " mCurrentPlayer error on ErrorListener, what:" + i2 + " extra:" + i3);
                    return false;
                }
            });
        }
    }

    private void k() {
        if (this.t) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.aa = elapsedRealtime;
            LogUtil.c("AudioPlayer", "handleKeepModePlay timer start mills=", Long.valueOf(elapsedRealtime));
            q();
        }
    }

    private void l() {
        if (this.ad == null || !this.t || this.aa <= 0) {
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        LogUtil.c("AudioPlayer", "handleKeepModePause pause mills=", Long.valueOf(elapsedRealtime));
        long j = this.aa;
        if (elapsedRealtime > j) {
            long j2 = this.w;
            long j3 = j2 - (elapsedRealtime - j);
            if (j3 > 0) {
                j2 = j3;
            }
            this.w = j2;
        }
        LogUtil.c("AudioPlayer", "handleKeepModePause mKeepMills after pause: ", Long.valueOf(this.w));
    }

    private void q() {
        if (this.t) {
            LogUtil.c("AudioPlayer", "startTimer mKeepMills: ", Long.valueOf(this.w));
            if (this.w <= 0) {
                LogUtil.a("AudioPlayer", "startTimer fatal:: mKeepMills <= 0");
                return;
            }
            Timer timer = this.ad;
            if (timer != null) {
                timer.cancel();
                this.ad = null;
            }
            Timer timer2 = new Timer("AudioPlayer");
            this.ad = timer2;
            timer2.schedule(new TimerTask() { // from class: com.huawei.haf.mediaprocess.AudioPlayer.5
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    LogUtil.c("AudioPlayer", "startTimer keep duration finish");
                    AudioPlayer.this.c(21, "keep duration finish");
                    AudioPlayer.this.h();
                }
            }, this.w);
            return;
        }
        LogUtil.c("AudioPlayer", "startTimer not keep mode");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, String str) {
        LogUtil.c("AudioPlayer", "state:", Integer.valueOf(i), " message:", str, " index:", Integer.valueOf(this.k));
        AudioPlayCallback audioPlayCallback = this.i;
        if (audioPlayCallback != null) {
            audioPlayCallback.mediaPlayCallBack(i, this.k, str);
        } else {
            LogUtil.a("AudioPlayer", "callback is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        CountDownTimer countDownTimer = this.ab;
        if (this.i != null && countDownTimer != null) {
            if (!this.p) {
                if (yU_(this.f2127a ? this.l : this.u) <= 500) {
                    this.i.onProgressChanged(e(), e());
                    LogUtil.c("AudioPlayer", "do audio finish progress callback");
                    this.p = true;
                    return;
                }
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long currentTickInterval = countDownTimer.getCurrentTickInterval();
            if (currentTickInterval == 1000) {
                this.i.onProgressChanged(d(), e());
                this.j = 0L;
                return;
            }
            if (currentTickInterval == 10) {
                long j = this.j;
                if (j == 0) {
                    this.j = elapsedRealtime;
                    return;
                } else {
                    if (j <= 0 || elapsedRealtime - j < 1000) {
                        return;
                    }
                    LogUtil.c("AudioPlayer", "doProgressCallback in the last less than three seconds");
                    this.i.onProgressChanged(d(), e());
                    this.j = elapsedRealtime;
                    return;
                }
            }
            LogUtil.c("AudioPlayer", "doProgressCallback not play state");
            return;
        }
        LogUtil.e("AudioPlayer", "doProgressCallback callback is null");
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0042, code lost:
    
        if (r3.f.requestAudioFocus(r0) == 1) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean n() {
        /*
            r3 = this;
            android.media.AudioManager r0 = r3.f
            if (r0 == 0) goto L45
            android.media.AudioFocusRequest r0 = r3.m
            if (r0 != 0) goto L22
            android.media.AudioAttributes r0 = r3.c
            if (r0 != 0) goto L22
            android.media.AudioAttributes$Builder r0 = new android.media.AudioAttributes$Builder
            r0.<init>()
            int r1 = r3.ac
            android.media.AudioAttributes$Builder r0 = r0.setUsage(r1)
            r1 = 2
            android.media.AudioAttributes$Builder r0 = r0.setContentType(r1)
            android.media.AudioAttributes r0 = r0.build()
            r3.c = r0
        L22:
            android.media.AudioFocusRequest$Builder r0 = new android.media.AudioFocusRequest$Builder
            r1 = 1
            r0.<init>(r1)
            android.media.AudioAttributes r2 = r3.c
            android.media.AudioFocusRequest$Builder r0 = r0.setAudioAttributes(r2)
            android.media.AudioFocusRequest$Builder r0 = r0.setWillPauseWhenDucked(r1)
            android.media.AudioFocusRequest$Builder r0 = r0.setOnAudioFocusChangeListener(r3)
            android.media.AudioFocusRequest r0 = r0.build()
            r3.m = r0
            android.media.AudioManager r2 = r3.f
            int r0 = r2.requestAudioFocus(r0)
            if (r0 != r1) goto L45
            goto L46
        L45:
            r1 = 0
        L46:
            java.lang.String r0 = "requestAudioFocus isFocus="
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r1)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r2}
            java.lang.String r2 = "AudioPlayer"
            health.compact.a.LogUtil.c(r2, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.haf.mediaprocess.AudioPlayer.n():boolean");
    }

    private void g() {
        AudioManager audioManager;
        AudioFocusRequest audioFocusRequest = this.m;
        if (audioFocusRequest != null && (audioManager = this.f) != null) {
            audioManager.abandonAudioFocusRequest(audioFocusRequest);
        } else {
            LogUtil.c("AudioPlayer", "abandonAudioFocus has released before");
        }
    }

    private int e(boolean z) {
        synchronized (this) {
            if (this.g.isEmpty()) {
                s();
            }
            Integer first = this.g.getFirst();
            if (first == null) {
                LogUtil.e("AudioPlayer", "all songs have been played, go back to the first");
                return 0;
            }
            LogUtil.c("AudioPlayer", "random index", first, " isNeedUpdate=", Boolean.valueOf(z));
            if (z) {
                r();
            }
            return first.intValue();
        }
    }

    private void r() {
        synchronized (this) {
            LogUtil.c("AudioPlayer", " updateNextRandomIndex");
            if (!this.g.isEmpty()) {
                this.g.pop();
            } else {
                s();
            }
        }
    }
}
