package com.huawei.health.suggestion.ui.fitness.helper.inter;

import android.media.PlaybackParams;
import android.net.Uri;
import java.util.List;

/* loaded from: classes4.dex */
public interface VideoInterface {
    VideoInterface next();

    VideoInterface pause();

    VideoInterface pre();

    VideoInterface release();

    VideoInterface repeat();

    void saveCurrent(int i);

    VideoInterface setAssetSources(String... strArr);

    VideoInterface setAudioAssetSources(List<String> list);

    VideoInterface setMediaSources(Uri... uriArr);

    VideoInterface setPlaybackParams(PlaybackParams playbackParams);

    VideoInterface setRawSources(Integer... numArr);

    VideoInterface setSdSources(String... strArr);

    VideoInterface start();

    VideoInterface stop();

    VideoInterface videoContinue();
}
