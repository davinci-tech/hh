package com.huawei.unitedevice.constant;

/* loaded from: classes7.dex */
public enum ConnectState {
    CONNECTING,
    CONNECTED,
    DISCONNECTED;

    public static ConnectState getConnectState(int i) {
        if (i == 1) {
            return CONNECTING;
        }
        if (i == 2) {
            return CONNECTED;
        }
        if (i == 3) {
            return DISCONNECTED;
        }
        return DISCONNECTED;
    }
}
