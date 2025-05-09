package com.huawei.motiondetection.motionrelay;

/* loaded from: classes5.dex */
public interface IRelay {
    void destroy();

    void setRelayListener(RelayListener relayListener);

    void startMotionReco(int i);

    void startMotionService();

    void stopMotionReco(int i);

    void stopMotionService();
}
