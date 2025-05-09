package com.huawei.health.suggestion.ui.fitness.helper;

import android.content.Context;
import android.media.MediaPlayer;
import com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class LongMediaHelper extends VideoMediaHelper implements MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnInfoListener, MediaPlayer.OnErrorListener {
    private OnPreparedListener j;

    public interface OnPreparedListener {
        void onBufferingEnd();

        void onBufferingStart();

        void onPrepared();

        void onSeekComplete(MediaPlayer mediaPlayer);

        void onVideoError();
    }

    public LongMediaHelper(Context context) {
        super(context);
        this.e.setOnPreparedListener(this);
        this.e.setOnSeekCompleteListener(this);
        this.e.setOnInfoListener(this);
        this.e.setOnErrorListener(this);
        this.f3166a = false;
    }

    public MediaPlayer aCp_() {
        return this.e;
    }

    public void e(int i) {
        if (this.e != null) {
            this.e.seekTo(i, 2);
        } else {
            LogUtil.b("Suggestion_LongMediaHelper", "mediaPlayer is null");
        }
    }

    public void a(OnPreparedListener onPreparedListener) {
        this.j = onPreparedListener;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.MediaHelper, android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        OnPreparedListener onPreparedListener = this.j;
        if (onPreparedListener != null) {
            onPreparedListener.onPrepared();
        }
        LogUtil.a("Suggestion_LongMediaHelper", "onPrepared");
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.VideoMediaHelper, com.huawei.health.suggestion.ui.fitness.helper.MediaHelper, com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface videoContinue() {
        LogUtil.a("Suggestion_LongMediaHelper", "Continue");
        if (this.e != null) {
            this.e.start();
        }
        return this;
    }

    @Override // android.media.MediaPlayer.OnSeekCompleteListener
    public void onSeekComplete(MediaPlayer mediaPlayer) {
        OnPreparedListener onPreparedListener = this.j;
        if (onPreparedListener != null) {
            onPreparedListener.onSeekComplete(mediaPlayer);
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.MediaHelper
    protected void f_() {
        if (this.e != null) {
            this.e.setLooping(this.f3166a);
        }
        try {
            if (this.e != null) {
                this.e.prepareAsync();
            }
        } catch (IllegalStateException e) {
            ReleaseLogUtil.c("Suggestion_LongMediaHelper", "later player prepare()error: ", LogAnonymous.b((Throwable) e));
        }
    }

    @Override // android.media.MediaPlayer.OnInfoListener
    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        if (i == 701) {
            OnPreparedListener onPreparedListener = this.j;
            if (onPreparedListener != null) {
                onPreparedListener.onBufferingStart();
            }
            LogUtil.c("Suggestion_LongMediaHelper", "MEDIA_INFO_BUFFERING_START");
            return false;
        }
        if (i != 702) {
            return false;
        }
        OnPreparedListener onPreparedListener2 = this.j;
        if (onPreparedListener2 != null) {
            onPreparedListener2.onBufferingEnd();
        }
        LogUtil.c("Suggestion_LongMediaHelper", "MEDIA_INFO_BUFFERING_END");
        return false;
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        OnPreparedListener onPreparedListener = this.j;
        if (onPreparedListener != null) {
            onPreparedListener.onVideoError();
        }
        ReleaseLogUtil.c("Suggestion_LongMediaHelper", "onError() media player error:", ", what=", Integer.valueOf(i), ", extra=", Integer.valueOf(i2));
        return true;
    }
}
