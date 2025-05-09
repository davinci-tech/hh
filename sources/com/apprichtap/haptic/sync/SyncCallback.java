package com.apprichtap.haptic.sync;

/* loaded from: classes8.dex */
public interface SyncCallback {
    int getCurrentPosition();

    default int getDuration() {
        return -1;
    }
}
