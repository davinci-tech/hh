package com.huawei.watchface.videoedit.param;

import com.huawei.watchface.videoedit.param.TimeLineImpl;

/* loaded from: classes9.dex */
public interface ItimeLine {
    long getCurrentTime();

    void pause();

    void reset();

    void resume();

    TimeLineImpl.TimeLineStatus seekCompleted();

    void seekTo(long j);

    void start();
}
