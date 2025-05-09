package defpackage;

import android.media.MediaPlayer;
import android.text.TextUtils;
import android.view.Surface;
import com.huawei.health.suggestion.videoplayer.IngotsMediaInterface;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.IOException;
import java.util.Locale;

/* loaded from: classes8.dex */
public class gif extends IngotsMediaInterface implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnVideoSizeChangedListener {

    /* renamed from: a, reason: collision with root package name */
    private MediaPlayer f12812a;

    @Override // com.huawei.health.suggestion.videoplayer.IngotsMediaInterface
    public void start() {
        this.f12812a.start();
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsMediaInterface
    public void prepare() {
        String currentDataSource;
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.f12812a = mediaPlayer;
        mediaPlayer.setAudioStreamType(3);
        this.f12812a.setLooping(isLoop());
        this.f12812a.setOnPreparedListener(this);
        this.f12812a.setOnCompletionListener(this);
        this.f12812a.setOnBufferingUpdateListener(this);
        this.f12812a.setScreenOnWhilePlaying(true);
        this.f12812a.setOnSeekCompleteListener(this);
        this.f12812a.setOnErrorListener(this);
        this.f12812a.setOnInfoListener(this);
        this.f12812a.setOnVideoSizeChangedListener(this);
        if (getDataSource().size() > 2) {
            currentDataSource = gil.b(getDataSource(), 2);
        } else {
            currentDataSource = getCurrentDataSource();
        }
        if (TextUtils.isEmpty(currentDataSource)) {
            LogUtil.h("Ingots_IngotsMediaSystem", "prepare() currentDataSource is null");
            return;
        }
        try {
            this.f12812a.setDataSource(currentDataSource);
        } catch (IOException unused) {
            LogUtil.b("Ingots_IngotsMediaSystem", "prepare() IOException");
        }
        this.f12812a.prepareAsync();
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsMediaInterface
    public void pause() {
        this.f12812a.pause();
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsMediaInterface
    public boolean isPlaying() {
        return this.f12812a.isPlaying();
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsMediaInterface
    public void seekTo(long j) {
        try {
            this.f12812a.seekTo((int) j);
        } catch (IllegalStateException unused) {
            LogUtil.b("Ingots_IngotsMediaSystem", "seekTo() IllegalStateException");
        }
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsMediaInterface
    public void release() {
        MediaPlayer mediaPlayer = this.f12812a;
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsMediaInterface
    public long getCurrentPosition() {
        if (this.f12812a != null) {
            return r0.getCurrentPosition();
        }
        return 0L;
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsMediaInterface
    public long getDuration() {
        if (this.f12812a != null) {
            return r0.getDuration();
        }
        return 0L;
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsMediaInterface
    public void setSurface(Surface surface) {
        this.f12812a.setSurface(surface);
    }

    @Override // com.huawei.health.suggestion.videoplayer.IngotsMediaInterface
    public void setVolume(float f, float f2) {
        this.f12812a.setVolume(f, f2);
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
        if (getCurrentDataSource().toLowerCase(Locale.ENGLISH).contains("mp3") || getCurrentDataSource().toLowerCase(Locale.ENGLISH).contains("wav")) {
            gih.e().aMW_().post(new Runnable() { // from class: gif.3
                @Override // java.lang.Runnable
                public void run() {
                    if (gii.b().a() != null) {
                        gii.b().a().onPrepared();
                    }
                }
            });
        }
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        gih.e().aMW_().post(new Runnable() { // from class: gif.4
            @Override // java.lang.Runnable
            public void run() {
                if (gii.b().a() != null) {
                    gii.b().a().onAutoCompletion();
                }
            }
        });
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(MediaPlayer mediaPlayer, final int i) {
        gih.e().aMW_().post(new Runnable() { // from class: gif.5
            @Override // java.lang.Runnable
            public void run() {
                if (gii.b().a() != null) {
                    gii.b().a().setBufferProgress(i);
                }
            }
        });
    }

    @Override // android.media.MediaPlayer.OnSeekCompleteListener
    public void onSeekComplete(MediaPlayer mediaPlayer) {
        gih.e().aMW_().post(new Runnable() { // from class: gif.2
            @Override // java.lang.Runnable
            public void run() {
                if (gii.b().a() != null) {
                    gii.b().a().onSeekComplete();
                }
            }
        });
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, final int i, final int i2) {
        gih.e().aMW_().post(new Runnable() { // from class: gif.1
            @Override // java.lang.Runnable
            public void run() {
                if (gii.b().a() != null) {
                    gii.b().a().onError(i, i2);
                }
            }
        });
        return true;
    }

    @Override // android.media.MediaPlayer.OnInfoListener
    public boolean onInfo(MediaPlayer mediaPlayer, final int i, int i2) {
        gih.e().aMW_().post(new Runnable() { // from class: gif.6
            @Override // java.lang.Runnable
            public void run() {
                if (gii.b().a() == null) {
                    LogUtil.h("Ingots_IngotsMediaSystem", "onInfo() getCurrentVideoPlayer() is null");
                    return;
                }
                if (i == 3 && (gii.b().a().getCurrentState() == 1 || gii.b().a().getCurrentState() == 2)) {
                    gii.b().a().onPrepared();
                }
                if (i == 701) {
                    gii.b().a().onStatePreparing();
                }
                if (i == 702) {
                    gii.b().a().onPrepared();
                }
            }
        });
        return false;
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        gih.e().a(i);
        gih.e().b(i2);
        gih.e().aMW_().post(new Runnable() { // from class: gif.9
            @Override // java.lang.Runnable
            public void run() {
                if (gii.b().a() != null) {
                    gii.b().a().onVideoSizeChanged();
                }
            }
        });
    }
}
