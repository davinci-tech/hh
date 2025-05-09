package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.health.musicservice.api.IAudioPlayer;
import com.huawei.health.musicservice.utils.HealthCountDownTimer;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.operation.utils.Constants;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogUtil;
import java.io.IOException;
import java.util.List;

@ApiDefine(uri = IAudioPlayer.class)
/* loaded from: classes3.dex */
public class eoe implements IAudioPlayer {

    /* renamed from: a, reason: collision with root package name */
    private d f12117a;
    private AudioManager b;
    private long c;
    private AudioAttributes d;
    private IAudioPlayer.AudioPlayerCallBack e;
    private final Context f;
    private c g;
    private final a h;
    private MediaPlayer i;
    private final b j;
    private boolean k;
    private boolean l;
    private final AudioManager.OnAudioFocusChangeListener m;
    private AudioFocusRequest n;
    private boolean r;
    private long s;
    private boolean t;
    private enq u;
    private MediaPlayer v;
    private i y;
    private final eof w = new eof();
    private volatile boolean q = false;
    private int x = 1;
    private volatile boolean o = false;
    private volatile boolean p = false;

    private eoe(Context context) {
        this.h = new a();
        this.j = new b();
        this.m = new e();
        this.f = context;
        d();
    }

    public static IAudioPlayer a(Context context) {
        return new eoe(context);
    }

    private void d() {
        LogUtil.c("R_AudioPlayer", "audio player inited");
        this.b = (AudioManager) this.f.getSystemService(PresenterUtils.AUDIO);
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.i = mediaPlayer;
        mediaPlayer.setAudioAttributes(arD_());
        this.i.setOnBufferingUpdateListener(this.h);
        this.i.setOnErrorListener(this.j);
        MediaPlayer mediaPlayer2 = new MediaPlayer();
        this.v = mediaPlayer2;
        mediaPlayer2.setAudioAttributes(arD_());
        this.v.setOnBufferingUpdateListener(this.h);
        this.v.setOnErrorListener(this.j);
        this.g = new c(0L, 0L);
        this.k = false;
        this.y = new i();
        f();
    }

    private void f() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("notification_play_or_pause_btn_clicked");
        intentFilter.addAction("notification_removed");
        BroadcastManagerUtil.bFA_(this.f, this.y, intentFilter, LocalBroadcast.c, null);
    }

    private void i() {
        this.f.unregisterReceiver(this.y);
    }

    @Override // com.huawei.health.musicservice.api.IAudioPlayer
    public void setPlayMode(IAudioPlayer.PlayMode playMode) {
        LogUtil.c("R_AudioPlayer", "setPlayMode: " + playMode.toString());
        this.w.d(playMode);
    }

    @Override // com.huawei.health.musicservice.api.IAudioPlayer
    public void setUsage(int i2) {
        LogUtil.c("R_AudioPlayer", "setUsage: " + i2);
        this.x = i2;
    }

    @Override // com.huawei.health.musicservice.api.IAudioPlayer
    public void setCallback(IAudioPlayer.AudioPlayerCallBack audioPlayerCallBack) {
        this.e = audioPlayerCallBack;
    }

    @Override // com.huawei.health.musicservice.api.IAudioPlayer
    public void cancelAutoStop() {
        LogUtil.c("R_AudioPlayer", "cancelAutoStop");
        d dVar = this.f12117a;
        if (dVar != null) {
            dVar.removeMessages(0);
            this.f12117a = null;
        }
    }

    @Override // com.huawei.health.musicservice.api.IAudioPlayer
    public void resetPlayList(List<enq> list) {
        if (!CollectionUtils.d(this.w.c())) {
            this.i.reset();
            this.l = false;
            this.r = false;
            this.t = false;
            this.g.cancel();
            d(IAudioPlayer.PlayStatus.STOPPED, this.w.e());
            this.w.b();
            this.u = null;
        }
        this.w.b(list);
        LogUtil.c("R_AudioPlayer", "resetPlayList play list set, size: " + list.size());
        a(list);
    }

    @Override // com.huawei.health.musicservice.api.IAudioPlayer
    public void setPlayList(List<enq> list) {
        this.w.b();
        this.w.b(list);
        eof eofVar = this.w;
        eofVar.b(eofVar.e());
        LogUtil.c("R_AudioPlayer", "play list set, size: " + list.size());
        a(list);
    }

    private void a(List<enq> list) {
        StringBuilder sb = new StringBuilder("[");
        for (int i2 = 0; i2 < list.size(); i2++) {
            String n = list.get(i2).n();
            if (TextUtils.isEmpty(n)) {
                LogUtil.e("R_AudioPlayer", "audioItem title is empty, mediaId:", list.get(i2).h());
            } else {
                sb.append(n.substring(0, Math.min(n.length() - 1, 10)));
                sb.append("|");
            }
        }
        sb.append("]");
        LogUtil.c("R_AudioPlayer", "AudioItemList:", sb.toString());
    }

    @Override // com.huawei.health.musicservice.api.IAudioPlayer
    public void addPlayList(List<enq> list) {
        this.w.b(list);
        eof eofVar = this.w;
        eofVar.b(eofVar.e());
        LogUtil.c("R_AudioPlayer", "play list add, size: " + list.size());
    }

    @Override // com.huawei.health.musicservice.api.IAudioPlayer
    public void removeItem(enq enqVar) {
        synchronized (this) {
            if (enqVar == null) {
                LogUtil.a("R_AudioPlayer", "removeItem failed, input audioItem is null");
                return;
            }
            if (enqVar.equals(this.w.e())) {
                if (this.w.c(enqVar)) {
                    LogUtil.c("R_AudioPlayer", "remove current item... list is empty, stop player...");
                    stop();
                } else if (!this.l && !this.t) {
                    LogUtil.c("R_AudioPlayer", "remove current playing item... play next one: " + this.w.e());
                    this.g.cancel();
                    e(this.w.e());
                } else {
                    LogUtil.c("R_AudioPlayer", "remove current paused item, prepare next item: " + this.w.e());
                    arE_(this.i, this.w.e());
                    this.l = true;
                    this.t = false;
                    this.r = false;
                    arF_(this.v);
                    this.g.cancel();
                    d(IAudioPlayer.PlayStatus.PAUSED, this.w.e());
                    e();
                }
            } else {
                this.w.c(enqVar);
                LogUtil.c("R_AudioPlayer", "remove un-current item: " + enqVar);
            }
        }
    }

    private void arF_(MediaPlayer mediaPlayer) {
        try {
            mediaPlayer.reset();
        } catch (IllegalStateException e2) {
            LogUtil.e("R_AudioPlayer", "resetPlayer failed, e: ", ExceptionUtils.d(e2));
        }
    }

    private void arE_(MediaPlayer mediaPlayer, enq enqVar) {
        try {
            Uri parse = Uri.parse(enqVar.g());
            mediaPlayer.reset();
            mediaPlayer.setOnErrorListener(null);
            mediaPlayer.setOnPreparedListener(null);
            mediaPlayer.setDataSource(this.f, parse);
            mediaPlayer.prepare();
            LogUtil.e("R_AudioPlayer", "prepare player success");
        } catch (IOException | IllegalStateException e2) {
            LogUtil.e("R_AudioPlayer", "prepare player failed, e: " + ExceptionUtils.d(e2));
        }
    }

    @Override // com.huawei.health.musicservice.api.IAudioPlayer
    public void play() {
        if (this.q) {
            LogUtil.a("R_AudioPlayer", "audio player already released, do nothing in play()");
            return;
        }
        if (this.l) {
            g();
            return;
        }
        if (this.t) {
            e(this.w.e());
            return;
        }
        if (this.r) {
            LogUtil.a("R_AudioPlayer", "current player is running, do nothing in play()");
            return;
        }
        if (this.i.isPlaying()) {
            LogUtil.a("R_AudioPlayer", "current player is playing, do nothing in play()");
            return;
        }
        enq c2 = this.w.c(false);
        if (c2 == null) {
            LogUtil.a("R_AudioPlayer", "play from start failed, no track");
            d(IAudioPlayer.PlayStatus.FAILED, (enq) null);
        } else {
            LogUtil.c("R_AudioPlayer", "starts to play: ", c2.n());
            e(c2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.q) {
            LogUtil.a("R_AudioPlayer", "audio player already released, do nothing in resume");
            return;
        }
        if (this.t) {
            LogUtil.a("R_AudioPlayer", "audio player already stopped, do nothing in resume");
            return;
        }
        if (!h()) {
            LogUtil.a("R_AudioPlayer", "resume failed, request focus fail");
            d(IAudioPlayer.PlayStatus.FAILED, this.w.e());
            return;
        }
        try {
            this.i.start();
            if (this.i.isPlaying()) {
                LogUtil.c("R_AudioPlayer", "resume success");
                this.l = false;
                this.t = false;
                this.r = true;
                this.g.resetAndTick(this.i.getDuration() - this.i.getCurrentPosition(), 1000L);
                d(IAudioPlayer.PlayStatus.PLAYING, this.w.e());
            } else {
                LogUtil.c("R_AudioPlayer", "resume failed");
                this.l = false;
                this.t = true;
                this.r = false;
                play();
            }
        } catch (IllegalStateException e2) {
            LogUtil.e("R_AudioPlayer", "resume failed, IllegalStateException:", ExceptionUtils.d(e2));
            d(IAudioPlayer.PlayStatus.FAILED, this.w.e());
        }
    }

    private void e(enq enqVar) {
        e(enqVar, false);
    }

    @Override // com.huawei.health.musicservice.api.IAudioPlayer
    public void play(int i2) {
        enq a2 = this.w.a(i2);
        if (a2 == null) {
            LogUtil.c("R_AudioPlayer", "play by index failed, no AudioItem for index: ", Integer.valueOf(i2));
        } else {
            LogUtil.c("R_AudioPlayer", "play by index: ", Integer.valueOf(i2), ", title: ", a2.n());
            e(a2, true);
        }
    }

    private void e(final enq enqVar, boolean z) {
        if (enqVar == null) {
            LogUtil.d("R_AudioPlayer", "play with AudioItem failed, audioItem is null");
            d(IAudioPlayer.PlayStatus.FAILED, (enq) null);
            return;
        }
        if (enqVar.g() == null || enqVar.g().isEmpty()) {
            LogUtil.d("R_AudioPlayer", "play with AudioItem failed, audioItem.getMediaUrl() is null");
            d(IAudioPlayer.PlayStatus.FAILED, (enq) null);
            return;
        }
        if (this.q) {
            LogUtil.a("R_AudioPlayer", "audio player already released, do nothing in innerPlay");
            d(IAudioPlayer.PlayStatus.FAILED, enqVar);
            return;
        }
        if (!h()) {
            LogUtil.a("R_AudioPlayer", "inner play failed, request audio focus return false...");
            d(IAudioPlayer.PlayStatus.FAILED, enqVar);
            return;
        }
        this.g.cancel();
        if (z) {
            d(IAudioPlayer.PlayStatus.SKIP_TO_INDEX, enqVar);
        }
        d(IAudioPlayer.PlayStatus.BUFFERING, enqVar);
        try {
            this.w.b(enqVar);
            this.i.reset();
            this.v.reset();
            this.l = false;
            this.t = false;
            this.r = true;
            this.i.setDataSource(this.f, Uri.parse(enqVar.g()));
            this.i.prepareAsync();
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            this.i.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: eoh
                @Override // android.media.MediaPlayer.OnPreparedListener
                public final void onPrepared(MediaPlayer mediaPlayer) {
                    eoe.this.arG_(elapsedRealtime, enqVar, mediaPlayer);
                }
            });
        } catch (Exception e2) {
            d(IAudioPlayer.PlayStatus.FAILED, enqVar);
            LogUtil.e("R_AudioPlayer", "inner play failed, exception: ", ExceptionUtils.d(e2));
        }
    }

    /* synthetic */ void arG_(long j, enq enqVar, MediaPlayer mediaPlayer) {
        if (this.l || this.t) {
            LogUtil.a("R_AudioPlayer", "someone called pause or stop too fast, while player is not started yet...");
            return;
        }
        mediaPlayer.start();
        if (mediaPlayer.isPlaying()) {
            LogUtil.d("R_AudioPlayer", "inner play success, buffering cost:", Long.valueOf(SystemClock.elapsedRealtime() - j), ", timer ticking, total duration: " + this.i.getDuration());
            d(IAudioPlayer.PlayStatus.PLAYING, enqVar);
            this.g.resetAndTick((long) this.i.getDuration(), 1000L);
            return;
        }
        d(IAudioPlayer.PlayStatus.FAILED, enqVar);
        LogUtil.a("R_AudioPlayer", "inner play failed, starting player failed...");
    }

    @Override // com.huawei.health.musicservice.api.IAudioPlayer
    public void pause() {
        b(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (this.q) {
            LogUtil.a("R_AudioPlayer", "audio player already released, do nothing in pause");
            return;
        }
        if (this.t) {
            LogUtil.a("R_AudioPlayer", "audio player already stopped, do nothing in pause");
            return;
        }
        if (!this.r) {
            LogUtil.c("R_AudioPlayer", "audio player is not running, do nothing in pause");
            return;
        }
        LogUtil.c("R_AudioPlayer", "player pausing...");
        this.l = true;
        this.t = false;
        this.r = false;
        this.g.cancel();
        try {
            this.i.pause();
        } catch (IllegalStateException unused) {
            LogUtil.e("R_AudioPlayer", "pause failed, IllegalStateException");
        }
        this.v.reset();
        d(IAudioPlayer.PlayStatus.PAUSED, this.w.e());
        if (z) {
            e();
        }
    }

    @Override // com.huawei.health.musicservice.api.IAudioPlayer
    public void playNext() {
        if (this.q) {
            LogUtil.a("R_AudioPlayer", "audio player already released, do nothing in play next");
            return;
        }
        this.g.cancel();
        this.i.reset();
        this.v.reset();
        enq e2 = this.w.e();
        if (e2 != null) {
            e2.b(0);
        }
        enq c2 = this.w.c(false);
        if (c2 == null) {
            LogUtil.c("R_AudioPlayer", "play next failed, no track, this should NOT happen, check if you have called addTrack()");
            d(IAudioPlayer.PlayStatus.FAILED, (enq) null);
        } else {
            LogUtil.c("R_AudioPlayer", "play next: ", c2.n(), ", current: ", eow.c(this.w.e()));
            d(IAudioPlayer.PlayStatus.SKIP_TO_NEXT, c2);
            e(c2);
        }
    }

    @Override // com.huawei.health.musicservice.api.IAudioPlayer
    public void playPrevious() {
        if (this.q) {
            LogUtil.a("R_AudioPlayer", "audio player already released, do nothing in play previous");
            return;
        }
        this.g.cancel();
        this.i.reset();
        this.v.reset();
        enq e2 = this.w.e();
        if (e2 != null) {
            e2.b(0);
        }
        enq d2 = this.w.d();
        if (d2 == null) {
            LogUtil.a("R_AudioPlayer", "play previous failed, no track, this should NOT happen, check if you have called addTrack()");
            d(IAudioPlayer.PlayStatus.FAILED, (enq) null);
        } else {
            LogUtil.c("R_AudioPlayer", "play previous: ", d2.n(), ", current: ", eow.c(this.w.e()));
            d(IAudioPlayer.PlayStatus.SKIP_TO_PREV, d2);
            e(d2);
        }
    }

    @Override // com.huawei.health.musicservice.api.IAudioPlayer
    public void seekTo(int i2) {
        if (this.q) {
            LogUtil.a("R_AudioPlayer", "audio player already released, do nothing in seekTo");
            return;
        }
        if (i2 < 0) {
            LogUtil.e("R_AudioPlayer", "seek failed, target position: " + i2);
            return;
        }
        int duration = this.i.getDuration();
        int i3 = duration - i2;
        if (i3 < 1000) {
            LogUtil.c("R_AudioPlayer", "seek to very end, play next... total: " + duration + ", seek target: " + i2);
            playNext();
            return;
        }
        if (this.r) {
            this.g.cancel();
        }
        LogUtil.c("R_AudioPlayer", "seek, from: " + this.i.getCurrentPosition() + ", to: " + i2 + ". total:" + duration);
        this.i.seekTo(i2);
        enq e2 = this.w.e();
        if (e2 == null) {
            LogUtil.a("R_AudioPlayer", "currentItem is null");
            return;
        }
        if (this.r) {
            e2.b(i2);
            d(IAudioPlayer.PlayStatus.PLAYING, e2);
            if (i3 > 10000) {
                this.g.resetAndTick(i3, 1000L);
                return;
            } else {
                this.g.resetAndTick(i3, 100L);
                return;
            }
        }
        e2.b(i2);
        d(IAudioPlayer.PlayStatus.PAUSED, e2);
    }

    @Override // com.huawei.health.musicservice.api.IAudioPlayer
    public void setVolume(float f) {
        if (f < 0.0f || f > 1.0f) {
            LogUtil.a("R_AudioPlayer", "wrong volume: " + f);
        } else {
            this.i.setVolume(f, f);
            this.v.setVolume(f, f);
        }
    }

    @Override // com.huawei.health.musicservice.api.IAudioPlayer
    public void autoStop(long j) {
        if (this.q) {
            LogUtil.a("R_AudioPlayer", "audio player already released, do nothing in autoStop");
            return;
        }
        if (j <= 0) {
            LogUtil.a("R_AudioPlayer", "audio player stopTime is invalid");
            return;
        }
        d dVar = this.f12117a;
        if (dVar == null) {
            this.f12117a = new d(Looper.getMainLooper());
        } else {
            dVar.removeMessages(0);
        }
        this.c = System.currentTimeMillis() + j;
        this.f12117a.sendEmptyMessageDelayed(0, j);
        LogUtil.c("R_AudioPlayer", "auto stop launched, will stop in(ms): " + j);
    }

    @Override // com.huawei.health.musicservice.api.IAudioPlayer
    public void stop() {
        if (this.q) {
            LogUtil.a("R_AudioPlayer", "audio player already released, do nothing in stop");
            return;
        }
        this.g.cancel();
        this.t = true;
        this.l = false;
        this.r = false;
        enq e2 = this.w.e();
        if (e2 != null) {
            e2.b(0);
        }
        try {
            LogUtil.c("R_AudioPlayer", "stopping player...");
            this.i.stop();
            this.v.reset();
        } catch (IllegalStateException unused) {
            LogUtil.e("R_AudioPlayer", "IllegalStateException occurred when stopping!");
        }
        d(IAudioPlayer.PlayStatus.STOPPED, this.w.e());
        e();
    }

    @Override // com.huawei.health.musicservice.api.IAudioPlayer
    public void release() {
        this.q = true;
        this.i.release();
        this.v.release();
        e();
        i();
        d(IAudioPlayer.PlayStatus.RELEASED, (enq) null);
        this.g.release();
        d dVar = this.f12117a;
        if (dVar != null) {
            dVar.removeMessages(0);
        }
        LogUtil.c("R_AudioPlayer", "audio player released, can NOT be used again");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        int max = Math.max(this.i.getCurrentPosition(), 0);
        int duration = this.i.getDuration();
        LogUtil.d("R_AudioPlayer", "play progress change to: " + max);
        if (this.w.e() == null) {
            LogUtil.a("R_AudioPlayer", "current item is null when do progress callback, this should not happen...");
            return;
        }
        if (max == this.w.e().j()) {
            LogUtil.a("R_AudioPlayer", "player is buffering, last position:" + this.w.e().j());
            d(IAudioPlayer.PlayStatus.BUFFERING, this.w.e());
            return;
        }
        this.w.e().b(max);
        IAudioPlayer.AudioPlayerCallBack audioPlayerCallBack = this.e;
        if (audioPlayerCallBack == null || duration < 0) {
            LogUtil.a("R_AudioPlayer", "audioPlayCallback is null or duration is invalid, no need to callback, duration:", Integer.valueOf(duration));
            return;
        }
        if (duration - max <= 500) {
            audioPlayerCallBack.onProgressChanged(this.w.e(), duration);
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - this.s >= 950) {
            this.s = elapsedRealtime;
            this.e.onProgressChanged(this.w.e(), max);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(IAudioPlayer.PlayStatus playStatus, enq enqVar) {
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder("play state change to: ");
        sb.append(playStatus.toString());
        sb.append(", audioItem: ");
        sb.append(enqVar == null ? Constants.NULL : enqVar.n());
        objArr[0] = sb.toString();
        LogUtil.d("R_AudioPlayer", objArr);
        IAudioPlayer.AudioPlayerCallBack audioPlayerCallBack = this.e;
        if (audioPlayerCallBack != null) {
            audioPlayerCallBack.onStatusChanged(playStatus, enqVar);
        } else {
            LogUtil.a("R_AudioPlayer", "audioPlayCallback is null, no need to callback, please check.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (this.q) {
            LogUtil.c("R_AudioPlayer", "audioPlayer released, no need to prepare next player");
            return;
        }
        enq c2 = this.w.c(true);
        this.u = c2;
        if (c2 == null) {
            LogUtil.c("R_AudioPlayer", "no track waiting to be played, no need to prepare next player");
            return;
        }
        LogUtil.c("R_AudioPlayer", "preparing next player...");
        final long currentTimeMillis = System.currentTimeMillis();
        this.v.reset();
        this.o = false;
        this.v.setAudioAttributes(arD_());
        try {
            this.v.setDataSource(this.f, Uri.parse(this.u.g()));
            this.v.prepareAsync();
            this.v.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: eog
                @Override // android.media.MediaPlayer.OnPreparedListener
                public final void onPrepared(MediaPlayer mediaPlayer) {
                    eoe.this.arH_(currentTimeMillis, mediaPlayer);
                }
            });
        } catch (IOException | IllegalStateException e2) {
            LogUtil.e("R_AudioPlayer", "prepareNextPlayer failed, e: " + ExceptionUtils.d(e2));
        }
    }

    /* synthetic */ void arH_(long j, MediaPlayer mediaPlayer) {
        this.o = true;
        LogUtil.c("R_AudioPlayer", "nextPlayer prepare success, time consume: " + (System.currentTimeMillis() - j));
        if (this.p) {
            c();
        }
        this.p = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("R_AudioPlayer", "enter handoverRetry");
        if (!this.r) {
            LogUtil.a("R_AudioPlayer", "not running, don't need to handover");
            return;
        }
        MediaPlayer mediaPlayer = this.i;
        MediaPlayer mediaPlayer2 = this.v;
        this.i = mediaPlayer2;
        this.v = mediaPlayer;
        mediaPlayer2.start();
        if (this.i.isPlaying()) {
            LogUtil.c("R_AudioPlayer", "handoverRetry play success, total duration: " + this.i.getDuration());
            d(IAudioPlayer.PlayStatus.PLAYING, this.u);
            this.g.resetAndTick((long) this.i.getDuration(), 1000L);
            return;
        }
        d(IAudioPlayer.PlayStatus.FAILED, this.u);
        LogUtil.a("R_AudioPlayer", "handoverRetry play failed, starting player failed...");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i2) {
        if (this.q) {
            LogUtil.a("R_AudioPlayer", "handoverPlayer failed, audioPlayer released, no need to handover");
            return;
        }
        if (this.u == null) {
            j();
            if (this.u == null) {
                LogUtil.d("R_AudioPlayer", "handoverPlayer failed, nextAudioItem is null, no need to handover");
                return;
            }
        }
        if (this.i == null || this.v == null) {
            LogUtil.e("R_AudioPlayer", "handoverPlayer failed, currentPlayer|otherPlayer is null");
            return;
        }
        if (!this.o) {
            LogUtil.c("R_AudioPlayer", "handoverPlayer failed, nextPlayer is not prepared, try in next heartbeat");
            return;
        }
        if (!this.v.isPlaying()) {
            LogUtil.c("R_AudioPlayer", "start nextPlayer");
            this.v.setVolume(0.25f, 0.25f);
            this.v.start();
            return;
        }
        if (i2 > 500) {
            LogUtil.c("R_AudioPlayer", "increase next player volume...");
            float f = 1.0f - (i2 / 1000.0f);
            float f2 = 1.0f - f;
            this.i.setVolume(f2, f2);
            float f3 = f + 0.25f;
            this.v.setVolume(f3, f3);
            return;
        }
        LogUtil.c("R_AudioPlayer", "stopping current player...");
        this.i.stop();
        this.v.setVolume(1.0f, 1.0f);
        d(IAudioPlayer.PlayStatus.SKIP_TO_NEXT, this.w.e());
        MediaPlayer mediaPlayer = this.i;
        this.i = this.v;
        this.v = mediaPlayer;
        this.g.resetAndTick(r0.getDuration(), 1000L);
        this.w.b(this.u);
        d(IAudioPlayer.PlayStatus.PLAYING, this.w.e());
        LogUtil.c("R_AudioPlayer", "player handover finished...");
    }

    private boolean h() {
        if (this.k) {
            return true;
        }
        if (this.b == null) {
            LogUtil.e("R_AudioPlayer", "AudioManager is null, request system service failed...");
            return false;
        }
        if (this.n == null && this.d == null) {
            this.d = arD_();
            this.n = new AudioFocusRequest.Builder(1).setAudioAttributes(this.d).setWillPauseWhenDucked(true).setOnAudioFocusChangeListener(this.m).build();
        }
        int requestAudioFocus = this.b.requestAudioFocus(this.n);
        boolean z = requestAudioFocus == 1;
        if (z) {
            LogUtil.d("R_AudioPlayer", "request audio focus success");
        } else {
            LogUtil.a("R_AudioPlayer", "request audio focus failed, result: " + requestAudioFocus);
        }
        return z;
    }

    private void e() {
        LogUtil.c("R_AudioPlayer", "abandon audio focus");
        AudioFocusRequest audioFocusRequest = this.n;
        if (audioFocusRequest != null) {
            this.b.abandonAudioFocusRequest(audioFocusRequest);
        } else {
            LogUtil.c("R_AudioPlayer", "abandonAudioFocus has released before");
        }
    }

    private AudioAttributes arD_() {
        return new AudioAttributes.Builder().setUsage(this.x).setContentType(2).build();
    }

    static class b implements MediaPlayer.OnErrorListener {
        private b() {
        }

        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            LogUtil.e("R_AudioPlayer", "media player error occur, what:", Integer.valueOf(i), " extra:", Integer.valueOf(i2));
            return false;
        }
    }

    class c extends HealthCountDownTimer {
        public c(long j, long j2) {
            super(j, j2);
        }

        @Override // com.huawei.health.musicservice.utils.HealthCountDownTimer
        public void onTick(long j) {
            int i;
            if (!eoe.this.l && !eoe.this.t) {
                try {
                    eoe.this.a();
                    int duration = eoe.this.i.getDuration() - eoe.this.i.getCurrentPosition();
                    if (duration <= 0 || duration >= 10000) {
                        i = 1000;
                    } else {
                        if (getCurrentTickInterval() != 50) {
                            eoe.this.j();
                        }
                        i = 50;
                    }
                    if (duration > 0 && duration < 1000) {
                        eoe.this.d(duration);
                    }
                    resetAndTick(duration, i);
                    return;
                } catch (IllegalStateException e) {
                    LogUtil.e("R_AudioPlayer", "exception during onTick, e: " + e);
                    return;
                }
            }
            LogUtil.c("R_AudioPlayer", "player paused or stopped, stop callback progress");
        }

        @Override // com.huawei.health.musicservice.utils.HealthCountDownTimer
        public void onFinish() {
            try {
                eoe.this.i.stop();
                eoe.this.g.cancel();
                enq e = eoe.this.w.e();
                if (e != null) {
                    e.b(0);
                }
                if (eoe.this.u == null) {
                    LogUtil.c("R_AudioPlayer", "current item play finished, no next item, calling stop...");
                    eoe.this.v.reset();
                    eoe.this.t = true;
                    eoe.this.l = false;
                    eoe.this.r = false;
                    eoe.this.d(IAudioPlayer.PlayStatus.STOPPED, eoe.this.w.e());
                    return;
                }
                LogUtil.c("R_AudioPlayer", "current item play finished, but nextAudioItem is not null, next player is ready:", Boolean.valueOf(eoe.this.o));
                eoe.this.w.b(eoe.this.u);
                if (!eoe.this.o) {
                    eoe.this.p = true;
                    eoe.this.d(IAudioPlayer.PlayStatus.BUFFERING, eoe.this.u);
                } else {
                    LogUtil.c("R_AudioPlayer", "nextPlayer is ready, but heartbeat is jammed, too late to start..");
                    eoe.this.c();
                }
            } catch (IllegalStateException e2) {
                LogUtil.e("R_AudioPlayer", "onFinish IllegalStateException:", ExceptionUtils.d(e2));
                eoe.this.d(IAudioPlayer.PlayStatus.FAILED, eoe.this.u);
            }
        }
    }

    class d extends Handler {
        public d(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (eoe.this.q) {
                LogUtil.a("R_AudioPlayer", "audio player already released, do nothing in AutoStopHandler");
            } else if (message.what == 0) {
                eoe.this.pause();
            }
        }
    }

    class i extends BroadcastReceiver {
        private i() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("notification_removed")) {
                LogUtil.c("R_AudioPlayer", "notification removed, will stop player...");
                eoe.this.stop();
            } else {
                if (intent.getAction().equals("notification_play_or_pause_btn_clicked")) {
                    if (eoe.this.r) {
                        LogUtil.c("R_AudioPlayer", "notification button clicked, pausing...");
                        eoe.this.pause();
                        return;
                    } else {
                        LogUtil.c("R_AudioPlayer", "notification button clicked, resumimg...");
                        eoe.this.play();
                        return;
                    }
                }
                LogUtil.e("R_AudioPlayer", "unknown event, this should NOT happen...");
            }
        }
    }

    class e implements AudioManager.OnAudioFocusChangeListener {
        private boolean d;

        private e() {
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            LogUtil.c("R_AudioPlayer", "audio focus changed, status: " + i);
            if (i == -3) {
                eoe.this.setVolume(0.5f);
                return;
            }
            if (i == -2) {
                this.d = true;
                eoe.this.k = false;
                eoe.this.b(false);
            } else if (i == -1) {
                eoe.this.k = false;
                eoe.this.pause();
                eoe.this.d(IAudioPlayer.PlayStatus.FOCUS_LOSS, eoe.this.w.e());
            } else {
                if (i != 1) {
                    return;
                }
                if (this.d) {
                    this.d = false;
                    eoe.this.g();
                }
                eoe.this.setVolume(1.0f);
                eoe.this.k = true;
            }
        }
    }

    static class a implements MediaPlayer.OnBufferingUpdateListener {
        private a() {
        }

        @Override // android.media.MediaPlayer.OnBufferingUpdateListener
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
            LogUtil.c("R_AudioPlayer", "buffering updated, percent: ", Integer.valueOf(i));
        }
    }
}
