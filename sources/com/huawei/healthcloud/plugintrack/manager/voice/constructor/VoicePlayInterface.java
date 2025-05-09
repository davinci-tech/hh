package com.huawei.healthcloud.plugintrack.manager.voice.constructor;

import android.content.Intent;

/* loaded from: classes4.dex */
public interface VoicePlayInterface {
    void continuePlay();

    void destroy();

    int getType();

    void insertVoice(Intent intent);

    void muteVolume();

    void pausePlay();

    void resetPlayer();

    void resumeVolume();

    void startPlay(String str);

    void stopPlay();
}
