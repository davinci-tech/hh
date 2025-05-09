package com.huawei.openalliance.ad.media;

/* loaded from: classes5.dex */
public interface IMultiMediaPlayingManager {
    void autoPlay(String str, MediaPlayerAgent mediaPlayerAgent);

    void manualPlay(String str, MediaPlayerAgent mediaPlayerAgent);

    void pause(String str, MediaPlayerAgent mediaPlayerAgent);

    void removeListenersForMediaPlayerAgent(MediaPlayerAgent mediaPlayerAgent);

    void removeMediaPlayerAgent(MediaPlayerAgent mediaPlayerAgent);

    void stop(String str, MediaPlayerAgent mediaPlayerAgent);
}
