package com.huawei.health.playercontroller.api;

import defpackage.evl;

/* loaded from: classes3.dex */
public interface PlayerEventApi {
    void onPlayStatusChangeFromNotification(evl evlVar, int i);

    void onPlayerFinishedMediaEvent(evl evlVar, long j, int i, long j2);

    void onPlayerPauseEvent(evl evlVar, long j, int i, long j2);

    void onPlayerProgressChange(evl evlVar, long j, int i);

    void onPlayerStartPlayEvent(evl evlVar, long j, int i, long j2);

    void onPlayerStopEvent(evl evlVar, long j, int i, long j2, boolean z);
}
