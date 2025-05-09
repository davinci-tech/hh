package com.huawei.health.device.wifi.control.logic;

/* loaded from: classes3.dex */
public abstract class AccessNetWorkTask {
    public static final int COMBINATION_MODE = 0;
    public static final int DEFAULT_BROADCAST_PORT = 58060;
    public static final int DEFAULT_UDOREV_PORT = 58780;
    public static final int MSG_BASE_EXPAND = 4;
    public static final int MSG_GET_MESSAGE = 1;
    public static final int MSG_RECEIVE_ERR = 3;
    public static final int MSG_STOP_SEND = 4;
    public static final int MSG_TIME_OUT = 2;
    public static final int MULTCAST_MODE = 1;
    public static final int PROBEREQUEST_MODE = 2;
    public static final int SOFTAP_MODE = 3;
    public static final int TIME_OUT_ALL = 90000;
    public static final int TIME_OUT_SEND = 60000;

    public abstract void pauseTask();

    public abstract void startTask();

    public abstract void stopTask();
}
