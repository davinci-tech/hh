package com.huawei.health.sportservice.inter;

import defpackage.kxd;

/* loaded from: classes8.dex */
public interface SkipDataCallback {
    void onBreakTimesChanges(int i);

    void onContinuousJumpTimesChanges(int i);

    void onFootPointChanges(kxd kxdVar);

    void onSkipNumberChanges(int i);

    void onSkipSpeedChanges(float f);

    void onStatusChanges(int i);
}
