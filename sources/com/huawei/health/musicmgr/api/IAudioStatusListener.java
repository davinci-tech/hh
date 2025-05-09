package com.huawei.health.musicmgr.api;

import android.media.session.PlaybackState;
import com.huawei.health.musicmgr.model.AudioPlayItem;
import java.util.List;

/* loaded from: classes8.dex */
public interface IAudioStatusListener {
    void onPlayCompleted();

    void onPlayError(int i, String str);

    void onPlayProgress(long j, long j2);

    void onPlayStateChange(PlaybackState playbackState);

    void onQueueChanged(List<AudioPlayItem> list);

    void onSongChange(AudioPlayItem audioPlayItem, int i);
}
