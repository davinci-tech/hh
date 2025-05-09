package com.huawei.health.marketing.views.audition;

import android.media.MediaPlayer;

/* loaded from: classes3.dex */
public interface AuditionPlayerListener {
    void onCompletion(MediaPlayer mediaPlayer);

    void onPlayerPause();

    void onPlayerStart();

    void onPrepared(MediaPlayer mediaPlayer);
}
