package com.huawei.openalliance.ad.media.listener;

import com.huawei.openalliance.ad.media.MediaPlayerAgent;

/* loaded from: classes5.dex */
public interface MediaStateListener {
    void onMediaCompletion(MediaPlayerAgent mediaPlayerAgent, int i);

    void onMediaPause(MediaPlayerAgent mediaPlayerAgent, int i);

    void onMediaStart(MediaPlayerAgent mediaPlayerAgent, int i);

    void onMediaStop(MediaPlayerAgent mediaPlayerAgent, int i);

    void onProgress(int i, int i2);
}
