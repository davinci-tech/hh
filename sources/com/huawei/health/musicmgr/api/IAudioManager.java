package com.huawei.health.musicmgr.api;

import android.content.Context;
import android.os.Bundle;
import com.huawei.health.musicmgr.model.AudioPlayItem;
import java.util.List;

/* loaded from: classes8.dex */
public interface IAudioManager {
    void addPlayItem(AudioPlayItem audioPlayItem, int i);

    void addPlayerStatusListener(IAudioStatusListener iAudioStatusListener);

    void connect();

    void disconnect();

    List<AudioPlayItem> getAllPlaylist();

    void init(Context context);

    boolean isPlaying();

    boolean isQueueEmpty();

    void pause();

    void play();

    void play(int i);

    void playNext();

    void playPre();

    void removeListByIndex(int i);

    void removeListByItem(AudioPlayItem audioPlayItem);

    void removePlayerStatusListener(IAudioStatusListener iAudioStatusListener);

    void seekTo(long j);

    void setPlayMode(int i, Bundle bundle);

    void setPlaylist(List<AudioPlayItem> list);

    void setTargetView(String str);

    void stop();
}
