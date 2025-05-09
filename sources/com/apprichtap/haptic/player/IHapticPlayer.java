package com.apprichtap.haptic.player;

import com.apprichtap.haptic.sync.SyncCallback;
import defpackage.nd;
import java.io.File;

/* loaded from: classes8.dex */
public interface IHapticPlayer {
    public static final float SPEED_MULTIPLE_MAX = 3.0f;
    public static final float SPEED_MULTIPLE_MIN = 0.5f;

    int getCurrentPosition();

    int getDuration();

    float getSpeed();

    boolean getSwitching();

    boolean isPlaying();

    void pause();

    void prepare();

    void registerPlayerEventCallback(PlayerEventCallback playerEventCallback);

    void release();

    void reset();

    void seekTo(int i);

    void setDataSource(File file, int i, int i2, SyncCallback syncCallback);

    void setDataSource(String str, int i, int i2, int i3, int i4, SyncCallback syncCallback);

    void setDataSource(String str, int i, int i2, SyncCallback syncCallback);

    void setLooping(boolean z);

    void setSpeed(float f);

    void setSwitching(boolean z);

    void start();

    void stop();

    void unregisterPlayerEventCallback();

    void updateHapticParameter(int i, int i2, int i3);

    static IHapticPlayer create(IHapticEffectPerformer iHapticEffectPerformer) {
        return new nd(iHapticEffectPerformer);
    }
}
