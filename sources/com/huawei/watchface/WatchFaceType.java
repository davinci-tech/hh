package com.huawei.watchface;

/* loaded from: classes7.dex */
public enum WatchFaceType {
    VIDEO(4),
    ALBUM(5),
    KALEIDOSCOPE(7),
    WEAR(8),
    STICKER(9);

    private final int mValue;

    WatchFaceType(int i) {
        this.mValue = i;
    }

    public int value() {
        return this.mValue;
    }
}
