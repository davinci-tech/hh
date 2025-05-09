package com.huawei.healthcloud.plugintrack.golf.device;

/* loaded from: classes4.dex */
public interface GolfDataReceiver {
    boolean isMatch(int i);

    void onDataReceived(GolfMsgHeader golfMsgHeader, int i, byte[] bArr);
}
