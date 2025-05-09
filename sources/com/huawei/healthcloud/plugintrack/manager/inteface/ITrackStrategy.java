package com.huawei.healthcloud.plugintrack.manager.inteface;

/* loaded from: classes4.dex */
public interface ITrackStrategy {
    void dispatchPhoneCurrentState(int i);

    void notifyUserOperateSportState(int i);

    void start();

    void stop();
}
