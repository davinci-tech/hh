package com.huawei.healthcloud.plugintrack.manager.voice.constructor;

import android.media.MediaPlayer;

/* loaded from: classes4.dex */
public interface IVoiceEngine {
    void destroy();

    void playVoice(Object obj, boolean z);

    void registerVoicePlayCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener);

    void setVoiceIntentBufferState(boolean z);

    void stopVoice();

    void unregisterVoicePlayCompletionListener();
}
