package com.huawei.openalliance.ad.media.listener;

/* loaded from: classes5.dex */
public interface SegmentMediaStateListener {
    void onSegmentMediaCompletion(String str, String str2, int i);

    void onSegmentMediaError(String str, String str2, int i, int i2, int i3);

    void onSegmentMediaPause(String str, String str2, int i);

    void onSegmentMediaStart(String str, String str2, int i);

    void onSegmentMediaStop(String str, String str2, int i);

    void onSegmentProgress(String str, String str2, int i, int i2);
}
