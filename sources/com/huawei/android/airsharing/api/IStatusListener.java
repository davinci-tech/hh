package com.huawei.android.airsharing.api;

import java.util.Map;

/* loaded from: classes2.dex */
public interface IStatusListener {
    public static final int CAST_MODE_APPCAST = 3;
    public static final int CAST_MODE_MIRROR = 1;
    public static final int CAST_MODE_PCEXPLORER = 4;
    public static final int CAST_MODE_STREAM = 2;
    public static final int STATUS_CONNECTED = 1;
    public static final int STATUS_DISCONNECTED = 0;

    void onStatusChanged(Map<Integer, Integer> map);
}
