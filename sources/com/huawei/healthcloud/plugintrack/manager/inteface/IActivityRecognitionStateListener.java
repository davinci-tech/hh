package com.huawei.healthcloud.plugintrack.manager.inteface;

/* loaded from: classes4.dex */
public interface IActivityRecognitionStateListener {
    String getCurrentState();

    boolean isCurrentStateIsStill();

    void onStateChange(int i);
}
