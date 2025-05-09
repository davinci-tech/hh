package com.apprichtap.haptic.player;

import defpackage.my;
import defpackage.nc;

/* loaded from: classes8.dex */
public interface IHapticEffectPerformer {
    public static final String TAG = "IHapticEffectPerformer";

    default void updateParameter(int i, int i2) {
        nc.b.c(TAG, "default updateParameter(int, int)");
    }

    default void swapVibrationIndex(boolean z) {
        nc.b.c(TAG, "default ");
    }

    default boolean supportRealtimeAdjustment() {
        nc.b.c(TAG, "default supportRealtimeAdjustment()");
        return false;
    }

    default void stop() {
        nc.b.c(TAG, "default stop()");
    }

    default void start(String str) {
        nc.b.c(TAG, "default start(String)");
    }

    default void setSenderIdKey(String str) {
        nc.b.c(TAG, "default setSenderId(int[])");
    }

    default void setGain(int i) {
        nc.b.c(TAG, "default setGain");
    }

    default int getRichTapCoreMajorVersion() {
        nc.b.c(TAG, "default getRichTapCoreMajorVersion");
        return -1;
    }

    static int[] convertHEStringToIntArray(String str, int i, int i2, int i3, int i4, int i5, boolean z) {
        return my.b(str, i, i2, i3, i4, i5, z);
    }
}
