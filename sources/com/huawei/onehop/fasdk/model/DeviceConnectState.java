package com.huawei.onehop.fasdk.model;

/* loaded from: classes5.dex */
public enum DeviceConnectState {
    FAILURE(-1),
    IDLE(0),
    CONNECTING(1),
    CONNECTED(2),
    DIS_CONNECTING(3),
    PEER_DEVICE_BUSY(7002),
    PEER_DEVICE_NO_RESPONSE(7003),
    OPERATION_FREQUENTLY(7005),
    PEER_NETWORK_ERR(7007),
    REQUIRE_UNLOCK_DEVICE(7009),
    NOT_SUPPORT_TRANSFER(7901),
    NETWORK_NOT_CONNECTED(7902),
    DEVICE_CONNECTION_FAILED(7004),
    PEER_DEVICE_EXITED(7903),
    NETWORK_INTERRUPTED(7904),
    APPLICATION_EXCEPTION(7905);

    private final int mState;

    DeviceConnectState(int i) {
        this.mState = i;
    }

    public int getState() {
        return this.mState;
    }

    public static DeviceConnectState getConnectState(int i) {
        for (DeviceConnectState deviceConnectState : values()) {
            if (i == deviceConnectState.mState) {
                return deviceConnectState;
            }
        }
        return IDLE;
    }
}
