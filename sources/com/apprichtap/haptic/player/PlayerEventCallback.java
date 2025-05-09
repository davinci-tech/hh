package com.apprichtap.haptic.player;

/* loaded from: classes8.dex */
public interface PlayerEventCallback {
    default void onError(int i) {
    }

    void onPlayerStateChanged(int i);

    void onSeekCompleted(int i);
}
